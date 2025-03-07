package egovframework.com.uat.uia.onepass.web;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.uat.uia.onepass.service.EgovOnepassService;
import egovframework.com.uat.uia.service.EgovLoginService;
import kr.go.onepass.client.dto.api.send.OnepassUserResponse;
import kr.go.onepass.client.dto.api.send.OnepassUserResponse.PROCESS_RESULT;
import kr.go.onepass.client.dto.api.send.OnepassUserResponse.USER_STATUS;
import kr.go.onepass.client.dto.saml.OnepassResponse;
import kr.go.onepass.client.handler.api.ApiSendHandler;
import kr.go.onepass.client.handler.saml.OnepassRequestHandler;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler.RESULT_CODE;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler.STATUS;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler.TYPE;

/**
 * 디티털원패스 연동을 처리하는 컨트롤러 클래스
 * @author 전자정부 표준프레임워크 정진오
 * @since 2021.05.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일       수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2021.05.30   정진오           최초 생성
 *  
 *  </pre>
 */
@Controller
public class EgovOnepassController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovOnepassController.class);

	@Resource(name="egovOnepassService")
	private EgovOnepassService egovOnepassService;

	/** EgovLoginService */
	@Resource(name="loginService")
	private EgovLoginService loginService;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;

	/**
	 * 디지털원패스 로그인 처리
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uat/uia/onepass/onepassLogin.do", method=RequestMethod.POST)
	public String onepassLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String serviceType = request.getParameter("serviceType");
		String action = null;
		String inputName = null;
		String inputValue = null;
		String pageType = OnepassRequestHandler.pageType(request);

		try {
			if ("LOGIN".equals(serviceType)) {
				action = OnepassRequestHandler.LOGIN_DEST;
				inputName = OnepassRequestHandler.LOGIN_INPUT_NAME;
				inputValue = OnepassRequestHandler.login();
			} else if ("LOGOUT".equals(serviceType)) {
				action = OnepassRequestHandler.logoutDest(request);
				inputName = OnepassRequestHandler.LOGOUT_INPUT_NAME;
				inputValue = OnepassRequestHandler.logout(request);
			} else {
				action = request.getContextPath()+"/index.do";
				inputName = "";
				inputValue = "";
			}
		} catch (Exception e) {
			return "egovframework/com/cmm/error/onepassAccessDenied";
		}

		model.addAttribute("redirectUrl", action);
		model.addAttribute("inputName", inputName);
		model.addAttribute("inputValue", inputValue);
		model.addAttribute("pageType", pageType);

		return "egovframework/com/uat/uia/onepass/onepassLogin";
	}

	/**
	 * 디지털원패스 로그인 완료 후 응답받은 callback
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uat/uia/onepass/onepassCallback.do", method=RequestMethod.POST)
	public String onepassCallback(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		// 인증체크
		OnepassResponse onepassResponse = OnepassResponseHandler.check(request);

		// 인증 성공
		if (onepassResponse.getStatus() == STATUS.SUCCESS  && onepassResponse.getResultCode() == RESULT_CODE.SUCCESS) {

			// type 이 LOGIN
			if (onepassResponse.getType() == TYPE.LOGIN) {
				String userKey = onepassResponse.getUserKey();
			    String intfToken = onepassResponse.getIntfToken();

			    // 인증 후 사용자 정보 조회
			    ApiSendHandler apiSendHandler = new ApiSendHandler();
			    OnepassUserResponse findOnepassUser = apiSendHandler.findUser(userKey, intfToken);

			    // 사용자 정보 조회 성공
			    if (findOnepassUser != null && findOnepassUser.getStatus() == USER_STATUS.USE) {

			    	// 디지털원패스 사용자 정보 - 신규회원일 경우 이 정보를 이용할 수 있음
			    	LOGGER.info("getId:{}", findOnepassUser.getId()); // 사용자의 ID (최대 30자)
			    	LOGGER.info("getName:{}", findOnepassUser.getName()); // 사용자의 이름 (최대 70자)
			    	LOGGER.info("getUserKey:{}", findOnepassUser.getUserKey()); // 사용자 유일값인 user key 가 전달된다. (최대 50자)
			    	LOGGER.info("getCi:{}", findOnepassUser.getCi()); // 사용자의 CI (최대 255자)
			        LOGGER.info("getBirth:{}", findOnepassUser.getBirth()); // 사용자의 생년월일 (최대 8자)
			        LOGGER.info("getPhone:{}", findOnepassUser.getPhone()); // 사용자의 휴대전화번호 (최대 14자)
			        LOGGER.info("getEmail:{}", findOnepassUser.getEmail()); // 사용자의 E-mail (최대 70자)
			    	LOGGER.info("getSex:{}", findOnepassUser.getSex()); // 사용자의 성별 (남:M, 여:F)
			        LOGGER.info("getNation:{}", findOnepassUser.getNation()); // 내외국인 구분(내국인:L, 외국인:F)

			    	// 기관 아이디 조회
			    	int usedCnt = egovOnepassService.onePassCheckIdDplct(findOnepassUser.getId());

					// 기관 아이디 조회 성공
					if (usedCnt > 0) {

						// 디지털원패스에서 받은 아이디로 회원정보 조회 후 세션 저장
				    	LoginVO resultVO = loginService.onepassLogin(findOnepassUser.getId());
				    	resultVO.setOnepassUserkey(userKey);
				    	resultVO.setOnepassIntfToken(intfToken);
				    	request.getSession().setAttribute("loginVO", resultVO);

						// 로그인 인증세션 추가
						request.getSession().setAttribute("accessUser", resultVO.getUserSe().concat(resultVO.getId()));

						model.addAttribute("resultMessage", egovMessageSource.getMessageArgs("digital.onepass.connect.success", new Object[]{resultVO.getId()}));
						model.addAttribute("redirectUrl", request.getContextPath()+"/index.do");
						return "egovframework/com/uat/uia/onepass/onepassResult";

					}

					// 기관 아이디 조회 실패
					else {
						model.addAttribute("resultMessage", egovMessageSource.getMessage("digital.onepass.connect.join.failure"));
						model.addAttribute("redirectUrl", request.getContextPath()+"/index.do");
						return "egovframework/com/uat/uia/onepass/onepassResult";
					}

			    }

			    // 사용자 정보 조회 실패
			    else {
			    	model.addAttribute("resultMessage", egovMessageSource.getMessage("digital.onepass.connect.user.infomation.failure"));
			    	model.addAttribute("redirectUrl", request.getContextPath()+"/index.do");
			    	return "egovframework/com/uat/uia/onepass/onepassResult";
			    }

			}

			// type 이 LOGIN 이 아님
			else {
		    	model.addAttribute("resultMessage", egovMessageSource.getMessage("digital.onepass.connect.athentication.failure"));
		    	model.addAttribute("redirectUrl", request.getContextPath()+"/index.do");
		    	return "egovframework/com/uat/uia/onepass/onepassResult";
		    }

		}

		// 인증 실패
		else {
			model.addAttribute("resultMessage", egovMessageSource.getMessage("digital.onepass.connect.athentication.failure"));
			model.addAttribute("redirectUrl", request.getContextPath()+"/index.do");
			return "egovframework/com/uat/uia/onepass/onepassResult";
		}

	}

	/**
	 * 디지털원패스 연동해제 처리
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uat/uia/onepass/onepassCancel.do", method=RequestMethod.POST)
	public String onepassCancel(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ApiSendHandler apiSendHandler = new ApiSendHandler();
		String userKey = request.getParameter("userKey");
		String intfToken = request.getParameter("intfToken");

		OnepassUserResponse onepassUser = apiSendHandler.InterLockRelease(userKey, intfToken);

		if (Objects.isNull(onepassUser)) {
			onepassUser = new OnepassUserResponse();
			model.addAttribute("resultMessage", egovMessageSource.getMessage("digital.onepass.connect.athentication.failure"));
			model.addAttribute("redirectUrl", request.getContextPath()+"/uat/uia/actionLogout.do");
			return "egovframework/com/uat/uia/onepass/onepassResult";
		} else {
			if (onepassUser.getProcess_result() == PROCESS_RESULT.SUCESS) {
				model.addAttribute("resultMessage", egovMessageSource.getMessage("digital.onepass.disconnect.success"));
				model.addAttribute("redirectUrl", request.getContextPath()+"/uat/uia/actionLogout.do");
				return "egovframework/com/uat/uia/onepass/onepassResult";
			} else {
				model.addAttribute("resultMessage", egovMessageSource.getMessage("digital.onepass.connect.user.infomation.failure"));
				model.addAttribute("redirectUrl", request.getContextPath()+"/uat/uia/actionLogout.do");
				return "egovframework/com/uat/uia/onepass/onepassResult";
			}
		}

	}

}
