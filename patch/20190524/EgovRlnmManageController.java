package egovframework.com.sec.rnc.web;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.sec.rnc.service.EgovRlnmManageService;

/**
 * 실명인증관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2009.04.10  조재영          최초 생성
 *  2019.04.25  신용호          moveToPage() 화이트리스트 처리
 *
 * </pre>
 */
@Controller
public class EgovRlnmManageController {

	/** rlnmManageService */
	@Resource(name = "rlnmManageService")
	private EgovRlnmManageService rlnmManageService;
	
	@Resource(name = "egovPageLinkWhitelist")
    protected List<String> egovWhitelist;

	@Resource(name = "egovNextUrlWhitelist")
    protected List<String> nextUrlWhitelist;

	/** Log Info */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovRlnmManageController.class);

	/**
	 * 실명인증확인화면 호출(주민번호)
	 * @param model 모델
	 * @return "egovframework/com/uss/umt/EgovStplatCnfirm"
	 * @exception Exception
	 */
	@RequestMapping("/sec/rnc/EgovRlnmCnfirm.do")
	public String rlnmCnfirm(Model model, @RequestParam Map<String, Object> commandMap) throws Exception {

		model.addAttribute("ihidnum", (String) commandMap.get("ihidnum")); 					//주민번호
		model.addAttribute("realname", (String) commandMap.get("realname")); 				//사용자이름
		model.addAttribute("sbscrbTy", (String) commandMap.get("sbscrbTy")); 					//사용자유형
		model.addAttribute("nextUrlName", (String) commandMap.get("nextUrlName")); 	//다음단계버튼명(이동할 URL에 따른)
		String nextUrl = (String) commandMap.get("nextUrl");
		if ( nextUrl == null ) nextUrl = "";
		model.addAttribute("nextUrl", nextUrl); 						//다음단계로 이동할 URL
		String result = "";

//		if ("".equals((String) commandMap.get("ihidnum"))) {
//			result = "info.user.rlnmCnfirm";
//			model.addAttribute("result", result); 	//실명확인 결과
//			return "egovframework/com/sec/rnc/EgovRlnmCnfirm";
//		}
//
//		try {
//			result = rlnmManageService.rlnmCnfirm((String) commandMap.get("ihidnum"), (String) commandMap.get("realname"), (String) commandMap.get("sbscrbTy"));
//		} finally {
//			model.addAttribute("result_tmp", result + "__" + result.substring(0, 2));
//			if (result.substring(0, 2).equals("00")) {
//				result = "success.user.rlnmCnfirm";
//			} else if (result.substring(0, 2).equals("01")) {
//				result = "fail.user.rlnmCnfirm";
//			} else {
//				result = "fail.user.connectFail";
//			}
//			model.addAttribute("result", result);		//실명확인 결과
//
//		}
		
		// 화이트 리스트 처리
		// whitelist목록에 있는 경우 결과가 true, 결과가 false인경우 FAIL처리
		if (nextUrlWhitelist.contains(nextUrl) == false) {
			LOGGER.debug("nextUrl WhiteList Error! Please check whitelist!");
			nextUrl="egovframework/com/cmm/egovError";
		}
		
		// 안전한 경로 문자열로 조치
		nextUrl = EgovWebUtil.filePathBlackList(nextUrl);

		
//		return "egovframework/com/sec/rnc/EgovRlnmCnfirm";
		// 실명인증기능 미탑재로 바로 회원가입 페이지로 이동.
		return "forward:" + nextUrl;
	}

	/**
	 * 실명인증확인화면 호출(GPIN)
	 * @param model 모델
	 * @return "egovframework/com/uss/umt/EgovStplatCnfirm"
	 * @exception Exception
	 */
	@RequestMapping("/sec/rnc/EgovRlnmPinCnfirm.do")
	public String rlnmPinCnfirm(Model model, @RequestParam Map<String, Object> commandMap, HttpServletRequest request) throws Exception {
		model.addAttribute("sbscrbTy", (String) commandMap.get("sbscrbTy")); 					//사용자유형
		model.addAttribute("nextUrlName", (String) commandMap.get("nextUrlName"));	//다음단계버튼명(이동할 URL에 따른)
		model.addAttribute("nextUrl", (String) commandMap.get("nextUrl")); 						//다음단계로 이동할 URL
		String realName = (String) commandMap.get("realName"); 										//결과_최초는 블랭크
		String result = (String) commandMap.get("result"); 													//결과_최초는 블랭크
		
		LOGGER.debug("realName: {}", realName);
		
		if ("".equals(result) && "".equals(realName)) {
			result = "info.user.rlnmPinCnfirm";
			model.addAttribute("result", result); //실명확인 결과
		} else {
			if (!realName.equals("null") && !realName.equals("")) {
				result = "success.user.rlnmPinCnfirm";
				model.addAttribute("result", result);		//실명확인 결과 메시지
				realName = URLDecoder.decode(realName, "UTF-8"); 	// gpin배포샘플은 기본인코딩이 euc-kr
				model.addAttribute("realName", realName); 	//실명확인 결과 이름
				//return "forward:"+(String)commandMap.get("nextUrl"); 성공시만 체크하여 직접 다음 URL로 이동할수도 있음
			} else {
				result = "fail.user.rlnmPinCnfirm";
				model.addAttribute("result", result); 	//실명확인 결과 메시지
				realName = new String(realName.getBytes("UTF-8"), "EUC-KR"); // gpin배포샘플은 기본인코딩이 euc-kr
				model.addAttribute("realName", realName); //실명확인 결과 이름
			}
		}
		LOGGER.debug("result: {}", result);
		
		return "egovframework/com/sec/rnc/EgovRlnmPinCnfirm";
	}

	/**
	 * 실명인증확인화면 호출(GPIN)
	 * @param model 모델
	 * @return "egovframework/com/uss/umt/EgovStplatCnfirm"
	 * @exception Exception
	 */
	@RequestMapping("/sec/rnc/EgovGPinCall.do")
	public String gpinCall(Model model, @RequestParam Map<String, Object> commandMap) throws Exception {
		model.addAttribute("gpinId", (String) commandMap.get("gpinId")); 						//GPIN 아이디
		model.addAttribute("gpinPassword", (String) commandMap.get("gpinPassword"));	//GPIN 패스워드
		model.addAttribute("sbscrbTy", (String) commandMap.get("sbscrbTy")); 					//사용자유형
		//model.addAttribute("urlName", (String)commandMap.get("urlName"));   				//다음단계버튼명(이동할 URL에 따른)
		//model.addAttribute("urlInfo", (String)commandMap.get("urlInfo"));   					//다음단계로 이동할 URL
		model.addAttribute("nextUrlName", (String) commandMap.get("nextUrlName")); 	//다음단계버튼명(이동할 URL에 따른)
		model.addAttribute("nextUrl", (String) commandMap.get("nextUrl")); 						//다음단계로 이동할 URL

		return "egovframework/com/sec/rnc/gpin/Sample-AuthRequest";
	}

	/**
	 * validato rule dynamic Javascript
	 */
	@RequestMapping("/sec/rnc/validator.do")
	public String validate() {
		return "egovframework/com/cmm/validator";
	}

	/**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value = "/sec/rnc/EgovPageLink.do")
	public String moveToPage(@RequestParam("link") String linkPage) {
		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		String link = linkPage;
		link = link.replace(";", "");
		link = link.replace(".", "");
		if (link == null || link.equals("")) {
			link = "egovframework/com/cmm/egovError";
		}

		// 화이트 리스트 처리
		// whitelist목록에 있는 경우 결과가 true, 결과가 false인경우 FAIL처리
		if (egovWhitelist.contains(link) == false) {
			LOGGER.debug("Page Link WhiteList Error! Please check whitelist!");
			link="egovframework/com/cmm/egovError";
		}
		
		// 안전한 경로 문자열로 조치
		link = EgovWebUtil.filePathBlackList(link);
		
		return link;
	}
}