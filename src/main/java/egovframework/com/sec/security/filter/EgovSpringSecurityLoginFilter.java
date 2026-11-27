package egovframework.com.sec.security.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.com.utl.sim.service.EgovClntInfo;
import egovframework.com.utl.sim.service.EgovFileScrty;

/**
 * Spring Security 간소화 설정(4.3.x) 로그인 필터
 *
 * 로그인 처리 URL(/uat/uia/actionLogin.do) 요청을 가로채 EgovLoginService 로 DB 인증(ID salt SHA-256 비밀번호 검증)을 수행한 뒤,
 * 인증에 성공하면 Spring Security 의 UsernamePasswordAuthenticationFilter 를 직접 호출하여
 * (username = userSe + id, password = SHA-256(id + 비밀번호) Base64 = DB 저장 해시) 시큐리티 인증을 완료한다.
 * (context-security.xml : jdbcUsersByUsernameQuery 가 PASSWORD 를 조회, hash="plaintext")
 *
 * 보안 : UsernamePasswordAuthenticationFilter 는 이 필터가 만든 내부 요청(RequestWrapperForSecurity)만 인증 요청으로 처리하도록
 * 제한한다. 외부에서 /egov_security_login 으로 직접 보낸 요청은 인증 처리되지 않는다.
 *
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 29.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 *  수정일               수정자        	 수정내용
 *  ----------   --------   ---------------------------
 *  2011.08.29   서준식            최초생성
 *  2011.12.12   유지보수          사용자 로그인 정보 간섭 가능성 문제(멤버 변수 EgovUserDetails userDetails를 로컬변수로 변경)
 *  2014.03.07   유지보수          로그인된 상태에서 다시 로그인 시 미처리 되는 문제 수정 (로그인 처리 URL 파라미터화)
 *  2017.03.03   조성원 	       시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *  2017.07.10   장동한            실행환경 v3.7(Spring Security 4.0.3 적용)
 *  2017.07.21   장동한 		   로그인인증제한 작업
 *  2020.06.25   신용호 		   로그인 메시지 처리 수정
 *  2026.11.08   유지보수          실행환경 4.3.2 (javax.servlet, XML 네임스페이스 간소화 설정) 적용
 *
 *  </pre>
 */
public class EgovSpringSecurityLoginFilter implements Filter {

	private FilterConfig config;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSpringSecurityLoginFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		LOGGER.debug("EgovSpringSecurityLoginFilter called...");

		// 로그인 URL
		String loginURL = config.getInitParameter("loginURL");
		loginURL = loginURL.replaceAll("\r", "").replaceAll("\n", "");

		String loginProcessURL = config.getInitParameter("loginProcessURL");
		loginProcessURL = loginProcessURL.replaceAll("\r", "").replaceAll("\n", "");

		ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		EgovLoginService loginService = (EgovLoginService) act.getBean("loginService");
		EgovLoginConfig egovLoginConfig = (EgovLoginConfig) act.getBean("egovLoginConfig");

		EgovMessageSource egovMessageSource = (EgovMessageSource) act.getBean("egovMessageSource");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String isRemotelyAuthenticated = (String) session.getAttribute("isRemotelyAuthenticated");

		String requestURL = httpRequest.getRequestURI();

		//스프링 시큐리티 인증이 처리 되었는지 EgovUserDetailsHelper.getAuthenticatedUser() 메서드를 통해 확인한다.
		//context-egovuserdetailshelper.xml 빈 설정에 egovUserDetailsSecurityService를 등록 해서 사용해야 정상적으로 동작한다.
		if (EgovUserDetailsHelper.getAuthenticatedUser() == null || requestURL.contains(loginProcessURL)) {

			if (isRemotelyAuthenticated != null && isRemotelyAuthenticated.equals("true")) {
				try {
					//세션 토큰 정보를 가지고 DB로부터 사용자 정보를 가져옴
					LoginVO loginVO = (LoginVO) session.getAttribute("loginVOForDBAuthentication");
					loginVO = loginService.actionLoginByEsntlId(loginVO);

					if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {

						String userIp = EgovClntInfo.getClntIP(httpRequest);
						loginVO.setIp(userIp);

						//세션 로그인
						session.setAttribute("loginVO", loginVO);
						session.setAttribute("accessUser", loginVO.getUserSe().concat(loginVO.getId()));

						//로컬 인증결과 세션에 저장
						session.setAttribute("isLocallyAuthenticated", "true");

						//스프링 시큐리티 로그인
						UsernamePasswordAuthenticationFilter springSecurity = lookupAuthenticationFilter(act, request);

						LOGGER.debug("before security filter call....");
						// SSO 조회 결과의 PASSWORD 는 DB 저장 해시이다
						springSecurity.doFilter(new RequestWrapperForSecurity(httpRequest, loginVO.getUserSe() + loginVO.getId(), loginVO.getPassword()), httpResponse, chain);
						LOGGER.debug("after security filter call....");

					}
				//2017.03.03 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
				} catch(IllegalArgumentException e) {
					LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
				} catch(Exception e) {
					LOGGER.error("["+e.getClass()+"] Try/Catch...Exception : " + e.getMessage());
				}

			} else if (isRemotelyAuthenticated == null) {
				if (requestURL.contains(loginProcessURL)) {

					String password = httpRequest.getParameter("password");
					String id = httpRequest.getParameter("id");

					// 보안점검 후속 조치(Password 검증)
					if ((id == null || "".equals(id)) && (password == null || "".equals(password))) {
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						httpRequest.setAttribute("loginMessage", "");
						dispatcher.forward(httpRequest, httpResponse);
						return;
					}
					else if (password == null || password.equals("") || password.length() < 8 || password.length() > 20) {
						httpRequest.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login.password",request.getLocale()));
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);

						dispatcher.forward(httpRequest, httpResponse);
						return;
					}

					LoginVO loginVO = new LoginVO();

					loginVO.setId(id.trim());
					loginVO.setPassword(password.trim());
					// Spring Security 비밀번호 : DB 저장 형식과 같은 SHA-256(id + 비밀번호) Base64 (5.1.0 egov-sha256 과 동일 알고리즘)
					final String securityPassword = EgovFileScrty.encryptPassword(password.trim(), id.trim());
					String userSe = httpRequest.getParameter("userSe");
					loginVO.setUserSe(userSe == null || "".equals(userSe.trim()) ? "GNR" : userSe);

					//------------------------------------------------------------------
				    // 로그인시 로그인인증제한 활성화 처리
				    //------------------------------------------------------------------
				    if(egovLoginConfig.isLock()){
				        try{
				             Map<?,?> mapLockUserInfo = loginService.selectLoginIncorrect(loginVO);
				             if(mapLockUserInfo != null){
				                //로그인인증제한 처리
				                String sLoginIncorrectCode = loginService.processLoginIncorrect(loginVO, mapLockUserInfo);
				                if(!sLoginIncorrectCode.equals("E")){
				                    if(sLoginIncorrectCode.equals("L")){
				                        request.setAttribute("loginMessage", egovMessageSource.getMessageArgs("fail.common.loginIncorrect", new Object[] {egovLoginConfig.getLockCount(),request.getLocale()}));
				                    }else if(sLoginIncorrectCode.equals("C")){
				                        request.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
				                    }
				                    httpRequest.getRequestDispatcher(loginURL).forward(request, response);
				                    return;
				                }
				            }else{
				                request.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
				                httpRequest.getRequestDispatcher(loginURL).forward(request, response);
				                return;
				            }
				        } catch(IllegalArgumentException e) {
				            LOGGER.error("[IllegalArgumentException] : "+ e.getMessage());
				        } catch(Exception ex) {
							LOGGER.error("Login Exception : {}", ex.getCause(), ex);
							httpRequest.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
							RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
							dispatcher.forward(httpRequest, httpResponse);
							return;
				        }
				    }

					//------------------------------------------------------------------
				    // 사용자 로그인 처리
				    //------------------------------------------------------------------
					try {
						//사용자 입력 id, password로 DB 인증을 실행함
						loginVO = loginService.actionLogin(loginVO);
						if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
							//사용자 IP 기록
							String userIp = EgovClntInfo.getClntIP(httpRequest);
							loginVO.setIp(userIp);

							//세션 로그인
							session.setAttribute("loginVO", loginVO);
							session.setAttribute("accessUser", loginVO.getUserSe().concat(loginVO.getId()));

							//로컬 인증결과 세션에 저장
							session.setAttribute("isLocallyAuthenticated", "true");

							//스프링 시큐리티 로그인
							UsernamePasswordAuthenticationFilter springSecurity = lookupAuthenticationFilter(act, request);

							LOGGER.debug("before security filter call....");
							springSecurity.doFilter(new RequestWrapperForSecurity(httpRequest, loginVO.getUserSe() + loginVO.getId(), securityPassword), httpResponse, chain);
							LOGGER.debug("after security filter call....");

						} else {
							//사용자 정보가 없는 경우 로그인 화면으로 redirect 시킴
							httpRequest.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
							RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
							dispatcher.forward(httpRequest, httpResponse);
							return;
						}
			        } catch(IllegalArgumentException e) {
			            LOGGER.error("[IllegalArgumentException] : "+ e.getMessage());
					} catch (Exception ex) {
						//DB인증 예외가 발생할 경우 로그인 화면으로 redirect 시킴
						LOGGER.error("Login Exception : {}", ex.getCause(), ex);
						httpRequest.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						dispatcher.forward(httpRequest, httpResponse);
						return;
					}
					return;
				}

			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * 시큐리티 설정(context-security.xml)이 생성한 UsernamePasswordAuthenticationFilter 를 찾아 egov 로그인 파라미터로 설정한다.
	 */
	private UsernamePasswordAuthenticationFilter lookupAuthenticationFilter(ApplicationContext act, ServletRequest request) {
		Map<String, UsernamePasswordAuthenticationFilter> beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
		if (beans.isEmpty()) {
			LOGGER.error("No AuthenticationProcessingFilter");
			throw new IllegalStateException("No AuthenticationProcessingFilter");
		}
		UsernamePasswordAuthenticationFilter springSecurity = beans.values().iterator().next();
		configureAuthenticationFilter(springSecurity);
		return springSecurity;
	}

	/**
	 * egov 로그인 파라미터를 지정하고, 이 필터가 만든 내부 요청(RequestWrapperForSecurity)만 인증 요청으로 처리하도록 제한한다.
	 * (외부에서 /egov_security_login 으로 직접 보낸 요청은 인증 처리되지 않는다)
	 */
	private static void configureAuthenticationFilter(UsernamePasswordAuthenticationFilter springSecurity) {
		springSecurity.setUsernameParameter("egov_security_username");
		springSecurity.setPasswordParameter("egov_security_password");
		springSecurity.setRequiresAuthenticationRequestMatcher(req -> req instanceof RequestWrapperForSecurity
				&& "POST".equalsIgnoreCase(((HttpServletRequest) req).getMethod()));
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;

		// 기동 시점부터 /egov_security_login 직접 호출을 차단하도록 인증 필터를 미리 설정한다
		try {
			ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
			for (UsernamePasswordAuthenticationFilter springSecurity : act.getBeansOfType(UsernamePasswordAuthenticationFilter.class).values()) {
				configureAuthenticationFilter(springSecurity);
			}
		} catch (IllegalStateException e) {
			LOGGER.warn("EgovSpringSecurityLoginFilter init : root WebApplicationContext not available yet ({})", e.getMessage());
		}
	}
}

class RequestWrapperForSecurity extends HttpServletRequestWrapper {
	private String username = null;
	private String password = null;

	public RequestWrapperForSecurity(HttpServletRequest request, String username, String password) {
		super(request);

		this.username = username;
		this.password = password;
	}

	@Override
	public String getServletPath() {
		return ((HttpServletRequest) super.getRequest()).getContextPath() + "/egov_security_login";
	}

	@Override
	public String getRequestURI() {
		return ((HttpServletRequest) super.getRequest()).getContextPath() + "/egov_security_login";
	}

	@Override
	public String getParameter(String name) {
		if (name.equals("egov_security_username")) {
			return username;
		}

		if (name.equals("egov_security_password")) {
			return password;
		}

		return super.getParameter(name);
	}
}
