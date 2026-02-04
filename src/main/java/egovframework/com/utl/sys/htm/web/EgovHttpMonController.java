package egovframework.com.utl.sys.htm.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.htm.service.EgovHttpMonService;
import egovframework.com.utl.sys.htm.service.HttpMntrngChecker;
import egovframework.com.utl.sys.htm.service.HttpMon;
import egovframework.com.utl.sys.htm.service.HttpMonLog;
import egovframework.com.utl.sys.htm.service.HttpMonLogVO;
import egovframework.com.utl.sys.htm.service.HttpMonVO;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 개요 - HTTP서비스모니터링에 대한 controller 클래스를 정의한다.
 *
 * 상세내용 - HTTP서비스모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. - HTTP서비스모니터링의 조회기능은 목록조회,
 * 상세조회로 구분된다.
 *
 * @author 박종선
 * @version 1.0
 * @created 17-6-2010 오후 5:12:43
 *
 *          <pre>
 * == 개정이력(Modification Information) ==
 *
 *  수정일         수정자       수정내용
 *  ----------   --------   ---------------------------
 *  2010.06.17   박종선       최초 생성
 *  2011.08.26   정진오		IncludedInfo annotation 추가
 *  2026.01.23   신용호       KISA 보안약점 조치 (SSRF 공격 취약점)
 *  
 *          </pre>
 */
@Controller
public class EgovHttpMonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovHttpMonController.class);

	@Resource(name = "EgovHttpMonService")
	protected EgovHttpMonService egovHttpMonService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 등록된 HTTP서비스모니터링 정보를 조회 한다.
	 *
	 * @param httpMonVO- HTTP서비스모니터링 VO
	 * @return String - 리턴 Url
	 *
	 * @param httpMonVO
	 */
	@IncludedInfo(name = "HTTP서비스모니터링", order = 2100, gid = 90)
	@RequestMapping(value = "/utl/sys/htm/EgovComUtlHttpMonList.do")
	public String selectHttpMonList(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("searchVO") HttpMonVO searchVO, ModelMap model) throws Exception {
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<HttpMonVO> resultList = egovHttpMonService.selectHttpMonList(searchVO);
		model.addAttribute("resultList", resultList);

		int totCnt = egovHttpMonService.selectHttpMonTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonList";
	}

	/**
	 * HTTP서비스모니터링상세 정보를 조회 한다.
	 *
	 * @param HttpMonVO - HTTP서비스모니터링 VO
	 * @return String - 리턴 Url
	 *
	 * @param httpMonVO
	 */
	@RequestMapping(value = "/utl/sys/htm/EgovComUtlHttpMonDetail.do")
	public String selectHttpMonDetail(@ModelAttribute("loginVO") LoginVO loginVO, HttpMon httpMon, ModelMap model)
			throws Exception {
		HttpMon vo = egovHttpMonService.selectHttpMonDetail(httpMon);
		model.addAttribute("result", vo);

		// * 다음 기능을 커스텀하여 사용시 보안 준수 여부를 반드시 확인하시기 바랍니다.
		// SSRF(Server-Side Request Forgery) 취약점 주의
		// SSRF는 "서버가 공격자를 대신해서 요청을 보내게 만드는 취약점"이다

		//LOGGER.info("SiteUrl============================컨트롤러 파라미터 확인========================>" + vo.getSiteUrl());
		//model.addAttribute("siteUrlHealth", HttpMntrngChecker.getPrductStatus(vo.getSiteUrl()));
		model.addAttribute("siteUrlHealth", "-");

		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonDetail";
	}

	/**
	 * Http서비스모니터링 정보를 신규로 등록한다.
	 *
	 * @param siteUrl - Http서비스모니터링 model
	 * @return String - 리턴 Url
	 *
	 * @param siteUrl
	 */
	@RequestMapping(value = "/utl/sys/htm/EgovComUtlHttpMonRegist.do")
	public String insertHttpMon(
		@Valid @ModelAttribute("httpMon") HttpMon httpMon,
		BindingResult bindingResult,
		ModelMap model,
		RedirectAttributes redirectAttributes) throws Exception {

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (httpMon.getWebKind() == null || httpMon.getWebKind().equals("") || bindingResult.hasErrors()) {
			return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonRegist";
		}

		// 아이디 설정
		httpMon.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		httpMon.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		egovHttpMonService.insertHttpMon(httpMon);
		return "forward:/utl/sys/htm/EgovComUtlHttpMonList.do";
	}

	/**
	 * Http서비스모니터링 수정 화면으로 이동한다.
	 *
	 * @param httpMon - Http서비스모니터링 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/htm/EgovComUtlHttpMonModifyView.do")
	public String selectHttpMonForUpdate(
		@ModelAttribute("httpMon") HttpMon httpMon,
		ModelMap model) throws Exception {

		HttpMon vo = egovHttpMonService.selectHttpMonDetail(httpMon);
		model.addAttribute("httpMon", vo);

		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonModify";
	}

	/**
	 * 기 등록 된 Http서비스모니터링 정보를 수정 한다.
	 *
	 * @param httpMon - Http서비스모니터링 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/htm/EgovComUtlHttpMonModify.do")
	public String updateHttpMon(
		@ModelAttribute("loginVO") LoginVO loginVO,
		@Valid @ModelAttribute("httpMon") HttpMon httpMon,
		BindingResult bindingResult,
		ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			//HttpMon vo = egovHttpMonService.selectHttpMonDetail(httpMon);
			//model.addAttribute("httpMon", vo);

			return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonModify";
		}
		httpMon.setLastUpdusrId(loginVO.getUniqId());
		egovHttpMonService.updateHttpMon(httpMon);
		return "forward:/utl/sys/htm/EgovComUtlHttpMonList.do";
	}

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 *
	 * @param siteUrl - HTTP서비스모니터링 model
	 * @return String - 리턴 Url
	 *
	 * @param siteUrl
	 */
	@RequestMapping(value = "/utl/sys/htm/EgovComUtlHttpMonRemove.do")
	public String deleteHttpMon(@ModelAttribute("loginVO") LoginVO loginVO, HttpMon cmmWebKind, ModelMap model)
			throws Exception {
		egovHttpMonService.deleteHttpMon(cmmWebKind);
		return "forward:/utl/sys/htm/EgovComUtlHttpMonList.do";
	}

	/**
	 * HTTP 서비스 상태를 조회한다.
	 * [주의] 이 메소드는 예시이며 커스텀시 안티패턴이 만들어져 보안취약점이 발생할수 있으니 주의가 필요합니다.
	 *
	 * @param sysId
	 * @return String
	 *
	 * @param httpSttusCd
	 */
	@RequestMapping("/utl/sys/htm/selectHttpMonSttus.do")
	public String selectProcessSttus(@ModelAttribute("httpMonVO") HttpMonVO httpMonVO, ModelMap model)
			throws Exception {

		HttpMon httpMonResult = egovHttpMonService.selectHttpMonDetail(httpMonVO);
		LOGGER.info("SiteUrl = " + httpMonResult.getSiteUrl());

		// * 다음 기능을 커스텀하여 사용시 보안 준수 여부를 반드시 확인하시기 바랍니다.
		// SSRF(Server-Side Request Forgery) 취약점 주의
		// SSRF는 "서버가 공격자를 대신해서 요청을 보내게 만드는 취약점"이다

		model.addAttribute("siteUrlHealth", HttpMntrngChecker.getPrductStatus(httpMonResult.getSiteUrl()));
		//model.addAttribute("siteUrlHealth", "-");
		
		model.addAttribute("result", httpMonResult);

		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonDetail";
	}

	/**
	 * 등록된 HTTP서비스모니터링로그 정보를 조회 한다.
	 *
	 * @param httpMonVO- HTTP서비스모니터링 VO
	 * @return String - 리턴 Url
	 *
	 * @param httpMonVO
	 */
	@RequestMapping(value = "/utl/sys/htm/EgovComUtlHttpMonLogList.do")
	public String selectHttpMonLogList(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("searchVO") HttpMonLogVO httpMonLogVO, ModelMap model) throws Exception {
		/** EgovPropertyService.sample */
		httpMonLogVO.setPageUnit(propertiesService.getInt("pageUnit"));
		httpMonLogVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(httpMonLogVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(httpMonLogVO.getPageUnit());
		paginationInfo.setPageSize(httpMonLogVO.getPageSize());

		httpMonLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		httpMonLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
		httpMonLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 조회기간설정
		if (httpMonLogVO.getSearchBgnDe() != null && httpMonLogVO.getSearchEndDe() != null) {
			if (!httpMonLogVO.getSearchBgnDe().equals("") && !httpMonLogVO.getSearchEndDe().equals("")) {
				httpMonLogVO.setSearchBgnDt(httpMonLogVO.getSearchBgnDe() + " " + httpMonLogVO.getSearchBgnHour());
				httpMonLogVO.setSearchEndDt(httpMonLogVO.getSearchEndDe() + " " + httpMonLogVO.getSearchEndHour());
			}
		}

		Map<String, Object> map = egovHttpMonService.selectHttpMonLogList(httpMonLogVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		// 조회시작시
		model.addAttribute("searchBgnHour", getTimeHH());
		// 조회종료시
		model.addAttribute("searchEndHour", getTimeHH());

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonLogList";

	}

	/**
	 * HTTP서비스모니터링로그상세 정보를 조회 한다.
	 *
	 * @param HttpMonVO - HTTP서비스모니터링 VO
	 * @return String - 리턴 Url
	 *
	 * @param httpMonVO
	 */
	@RequestMapping(value = "/utl/sys/htm/EgovComUtlHttpMonDetailLog.do")
	public String selectHttpMonDetailLog(@ModelAttribute("loginVO") LoginVO loginVO, HttpMonLog httpMonLog,
			ModelMap model) throws Exception {
		HttpMonLog vo = egovHttpMonService.selectHttpMonDetailLog(httpMonLog);
		model.addAttribute("result", vo);

		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonDetailLog";
	}

	/**
	 * 시간의 LIST를 반환한다.
	 *
	 * @return List
	 * @throws
	 */
	private List<ComDefaultCodeVO> getTimeHH() {
		ArrayList<ComDefaultCodeVO> listHH = new ArrayList<>();
		// HashMap hmHHMM;
		for (int i = 0; i < 24; i++) {
			String sHH = "";
			String strI = String.valueOf(i);
			if (i < 10) {
				sHH = "0" + strI;
			} else {
				sHH = strI;
			}

			ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
			codeVO.setCode(sHH);
			codeVO.setCodeNm(sHH + ":00");

			listHH.add(codeVO);
		}

		return listHH;
	}

}
