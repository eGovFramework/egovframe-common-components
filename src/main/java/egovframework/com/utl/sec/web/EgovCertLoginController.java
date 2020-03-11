package egovframework.com.utl.sec.web;

import egovframework.com.utl.sec.service.EgovCertInfoUtil;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * GPKISecureWeb 인증서 로그인 Controller
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.08.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.06  한성곤          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovCertLoginController {
    /**
     * 인증서 로그인에 관련된 환경변수를 설정한다.
     *
     * @param type
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/utl/sec/certVar.do")
    public String var(@RequestParam(value="type", required=false) String type, Model model) throws Exception {
	String typeInfo = type;

	//------------------------------------------------------------
	// 서버 인증서 정보 처리
	//------------------------------------------------------------
	String serverCert = EgovCertInfoUtil.getBase64ServerCert();

	model.addAttribute("serverCert", serverCert);

	//------------------------------------------------------------
	// install 후 이동될 페이지 지정 (프로젝트에 맞게 수정 필요)
	//-----------------------------------------------------------
	if (typeInfo == null) {
	    typeInfo = "";
	}

	String startPage = null;

	if (typeInfo.equalsIgnoreCase("login")) {
		startPage = "/utl/sec/certLogin.do";
	} else if (typeInfo.equalsIgnoreCase("regist")) {
		startPage = "/utl/sec/certInfoPopup.do";
	} else {
	    startPage = "/utl/sec/certLogin.do";
	}

	model.addAttribute("startPage", startPage);

	return "egovframework/com/utl/sec/EgovCertVar";
    }

    /**
     * 인증서 관련 설치 페이지를 표시한다.
     *
     * @param type
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/utl/sec/certInstall.do")
    public String certInstall(@RequestParam(value="type", required=false) String type, Model model) throws Exception {
	String typeInfo = type;

	//------------------------------------------------------------
	// install 후 이동될 페이지 지정 (프로젝트에 맞게 수정 필요)
	//-----------------------------------------------------------
	if (typeInfo == null || typeInfo.equals("")) {
	    typeInfo = "login";
	}

	model.addAttribute("type", typeInfo);

	return "egovframework/com/utl/sec/EgovCertInstall";
    }

    /**
     * 인증서 로그인 관련 설치 정보를 제공하는 설정 페이지를 표시한다.
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/utl/sec/certSetup.do")
    public String certSetup() throws Exception {
	return "egovframework/com/utl/sec/EgovCertSetup";
    }

    /**
     * 인증서 로그인 관련 오류메시지 페이지를 표시한다.
     *
     * @param errMsg
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/utl/sec/certGPKIError.do")
    public String certGPKIError(@RequestParam("errmsg") String errMsg, Model model) throws Exception {

	model.addAttribute("errmsg", errMsg);

	return "egovframework/com/utl/sec/EgovCertGPKIError";
    }

    /**
     * 인증서 DN 등록을 위한 팝업 페이지를 표시한다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/utl/sec/certInfoPopup.do")
    public String certInfoPopup(Model model) throws Exception {

	return "egovframework/com/utl/sec/EgovCertInfoPopup";
    }

    /**
     * 인증서 로그인 페이지(테스트)를 표시한다.
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/utl/sec/certLogin.do", method=RequestMethod.GET)
    public String certLogin() throws Exception {

	return "egovframework/com/utl/sec/EgovCertLogin";
    }

    /**
     * 인증서 로그인 확인 페이지(테스트)를 표시한다.
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/utl/sec/certLogin.do", method=RequestMethod.POST)
    public String certLoginConfirm(HttpServletRequest request, Model model) throws Exception {

	// 인증서 정보
	model.addAttribute("certInfo", EgovCertInfoUtil.getCertInfo(request));

	return "egovframework/com/utl/sec/EgovCertLoginConfirm";
    }

    /**
     * 인증서 등록을 위한 팝업 페이지(테스트)를 표시한다.
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/utl/sec/certLoginInfo.do")
    public String certLoginPopup() throws Exception {

	return "egovframework/com/utl/sec/EgovCertLoginInfo";
    }
}
