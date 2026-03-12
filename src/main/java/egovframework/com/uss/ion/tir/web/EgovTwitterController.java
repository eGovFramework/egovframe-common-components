package egovframework.com.uss.ion.tir.web;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * Twitter(X) TIR 화면 이동을 담당하는 컨트롤러.
 * 기존 전송/저장 로직은 X API 전용 컨트롤러로 분리되어 페이지 라우팅만 유지한다.
 */
@Controller
public class EgovTwitterController {

	@Resource(name = "egovMessageSource")
	private EgovMessageSource egovMessageSource;

	@IncludedInfo(name = "Twitter연동", order = 830, gid = 50)
	@RequestMapping(value = "/uss/ion/tir/selectTwitterMain.do")
	public String selectTwitterMain() {
		return "egovframework/com/uss/ion/tir/EgovTwitterMain";
	}

	@RequestMapping(value = "/uss/ion/tir/selectTwitterV2Demo.do")
	public String selectTwitterV2Demo(ModelMap model) {
		if (!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		return "egovframework/com/uss/ion/tir/EgovXV2Demo";
	}

	@RequestMapping(value = "/uss/ion/tir/selectTwitterPopup.do")
	public String selectTwitterPopup(ModelMap model) {
		if (!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		return "egovframework/com/uss/ion/tir/EgovTwitterPopup";
	}
}
