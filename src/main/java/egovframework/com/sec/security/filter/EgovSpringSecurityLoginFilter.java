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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 29.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 *     수정일                 수정자        	  수정내용
 *  -----------    --------   ---------------------------
 *  2011.08.29    	 서준식      최초생성
 *  2011.12.12      유지보수      사용자 로그인 정보 간섭 가능성 문제(멤버 변수 EgovUserDetails userDetails를 로컬변수로 변경)
 *  2014.03.07      유지보수      로그인된 상태에서 다시 로그인 시 미처리 되는 문제 수정 (로그인 처리 URL 파라미터화)
 *  2017.03.03 		조성원 	    시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *  2017.07.10      장동한       실행환경 v3.7(Spring Security 4.0.3 적용)
 *  2017.07.21 		 장동한 		로그인인증제한 작업
 *
 *  </pre>
 */

public class EgovSpringSecurityLoginFilter implements Filter {

	private FilterConfig config;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSpringSecurityLoginFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		LOGGER.info("EgovSpringSecurityLoginFilter called...");

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
		//String isLocallyAuthenticated = (String)session.getAttribute("isLocallyAuthenticated");
		String isRemotelyAuthenticated = (String) session.getAttribute("isRemotelyAuthenticated");

		String requestURL = ((HttpServletRequest) request).getRequestURI();

		//스프링 시큐리티 인증이 처리 되었는지 EgovUserDetailsHelper.getAuthenticatedUser() 메서드를 통해 확인한다.
		//context-common.xml 빈 설정에 egovUserDetailsSecurityService를 등록 해서 사용해야 정상적으로 동작한다.
		if (EgovUserDetailsHelper.getAuthenticatedUser() == null || requestURL.contains(loginProcessURL)) {

			if (isRemotelyAuthenticated != null && isRemotelyAuthenticated.equals("true")) {
				try {
					//세션 토큰 정보를 가지고 DB로부터 사용자 정보를 가져옴
					LoginVO loginVO = (LoginVO) session.getAttribute("loginVOForDBAuthentication");
					loginVO = loginService.actionLoginByEsntlId(loginVO);

					if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
						//세션 로그인
						session.setAttribute("loginVO", loginVO);

						//로컬 인증결과 세션에 저장
						session.setAttribute("isLocallyAuthenticated", "true");

						//스프링 시큐리티 로그인
						//httpResponse.sendRedirect(httpRequest.getContextPath() + "/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId());

						UsernamePasswordAuthenticationFilter springSecurity = null;

						Map<String, UsernamePasswordAuthenticationFilter> beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
						if (beans.size() > 0) {
							springSecurity = (UsernamePasswordAuthenticationFilter) beans.values().toArray()[0];
							springSecurity.setUsernameParameter("egov_security_username");
							springSecurity.setPasswordParameter("egov_security_password");
							springSecurity.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(request.getServletContext().getContextPath() +"/egov_security_login", "POST"));
						} else {
							LOGGER.error("No AuthenticationProcessingFilter");
							throw new IllegalStateException("No AuthenticationProcessingFilter");
						}
						//springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);	// false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

						LOGGER.debug("before security filter call....");
						springSecurity.doFilter(new RequestWrapperForSecurity(httpRequest, loginVO.getUserSe() + loginVO.getId(), loginVO.getUniqId()), httpResponse, chain);
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
					
					// 보안점검 후속 조치(Password 검증)
					if (password == null || password.equals("") || password.length() < 8 || password.length() > 20) {
						httpRequest.setAttribute("message", egovMessageSource.getMessage("fail.common.login.password",request.getLocale()));
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						dispatcher.forward(httpRequest, httpResponse);
						//chain.doFilter(request, response);
						return;
					}

					LoginVO loginVO = new LoginVO();

					loginVO.setId(httpRequest.getParameter("id"));
					loginVO.setPassword(password);
					loginVO.setUserSe(httpRequest.getParameter("userSe"));
					
					//------------------------------------------------------------------
				    // 로그인시 로그인인증제한 활성화 처리
				    //------------------------------------------------------------------
				    if(egovLoginConfig.isLock()){
				        try{
				             Map<?,?> mapLockUserInfo = (EgovMap)loginService.selectLoginIncorrect(loginVO);
				             if(mapLockUserInfo != null){		
				                //로그인인증제한 처리
				                String sLoginIncorrectCode = loginService.processLoginIncorrect(loginVO, mapLockUserInfo);
				                if(!sLoginIncorrectCode.equals("E")){
				                    if(sLoginIncorrectCode.equals("L")){
				                        request.setAttribute("message", egovMessageSource.getMessageArgs("fail.common.loginIncorrect", new Object[] {egovLoginConfig.getLockCount(),request.getLocale()}));
				                    }else if(sLoginIncorrectCode.equals("C")){
				                        request.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
				                    }
				                    httpRequest.getRequestDispatcher(loginURL).forward(request, response);
				                    return;
				                }
				            }else{
				                request.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
				                httpRequest.getRequestDispatcher(loginURL).forward(request, response);
				                return;
				            }
				        } catch(IllegalArgumentException e) {
				            LOGGER.error("[IllegalArgumentException] : "+ e.getMessage());
				        } catch(Exception ex) {
							LOGGER.error("Login Exception : {}", ex.getCause(), ex);
							httpRequest.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
							RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
							dispatcher.forward(httpRequest, httpResponse);
				        }
				    }

					//------------------------------------------------------------------
				    // 사용자 로그인 처리
				    //------------------------------------------------------------------
					try {
						//사용자 입력 id, password로 DB 인증을 실행함
						loginVO = loginService.actionLogin(loginVO);
						//사용자 IP 기록
						loginVO.setIp(request.getRemoteAddr());
						if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
							//세션 로그인
							session.setAttribute("loginVO", loginVO);

							//로컬 인증결과 세션에 저장
							session.setAttribute("isLocallyAuthenticated", "true");

							//스프링 시큐리티 로그인
							//httpResponse.sendRedirect(httpRequest.getContextPath() + "/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId());

							UsernamePasswordAuthenticationFilter springSecurity = null;

							Map<String, UsernamePasswordAuthenticationFilter> beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
							if (beans.size() > 0) {
								springSecurity = (UsernamePasswordAuthenticationFilter) beans.values().toArray()[0];
								springSecurity.setUsernameParameter("egov_security_username");
								springSecurity.setPasswordParameter("egov_security_password");
								springSecurity.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(request.getServletContext().getContextPath() +"/egov_security_login", "POST"));
							} else {
								LOGGER.error("No AuthenticationProcessingFilter");
								throw new IllegalStateException("No AuthenticationProcessingFilter");
							}
							//springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);	// false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

							LOGGER.debug("before security filter call....");
							springSecurity.doFilter(new RequestWrapperForSecurity(httpRequest, loginVO.getUserSe() + loginVO.getId(), loginVO.getUniqId()), httpResponse, chain);
							LOGGER.debug("after security filter call....");

						} else {
							//사용자 정보가 없는 경우 로그인 화면으로 redirect 시킴
							httpRequest.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
							RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
							dispatcher.forward(httpRequest, httpResponse);
							
							//chain.doFilter(request, response);

							return;

						}
			        } catch(IllegalArgumentException e) {
			            LOGGER.error("[IllegalArgumentException] : "+ e.getMessage());
					} catch (Exception ex) {
						//DB인증 예외가 발생할 경우 로그인 화면으로 redirect 시킴
						LOGGER.error("Login Exception : {}", ex.getCause(), ex);
						httpRequest.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						dispatcher.forward(httpRequest, httpResponse);
						//chain.doFilter(request, response);

						return;

					}
					return;
				}

			}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
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
