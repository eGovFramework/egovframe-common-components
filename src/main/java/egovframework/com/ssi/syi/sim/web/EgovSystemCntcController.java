package egovframework.com.ssi.syi.sim.web;

import java.util.List;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.ssi.syi.iis.service.CntcInsttVO;
import egovframework.com.ssi.syi.iis.service.CntcServiceVO;
import egovframework.com.ssi.syi.iis.service.CntcSystemVO;
import egovframework.com.ssi.syi.iis.service.EgovCntcInsttService;
import egovframework.com.ssi.syi.sim.service.EgovSystemCntcService;
import egovframework.com.ssi.syi.sim.service.SystemCntc;
import egovframework.com.ssi.syi.sim.service.SystemCntcVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 시스템연계 관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한
 * Controller를 정의한다
 *
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2025.07.01  이백행          컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovSystemCntcController {

	@Resource(name = "SystemCntcService")
	private EgovSystemCntcService systemCntcService;

	@Resource(name = "CntcInsttService")
	private EgovCntcInsttService cntcInsttService;

	/** EgovIdGnrService */
	@Resource(name = "egovSystemCntcIdGnrService")
	private EgovIdGnrService idgenService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 시스템연계를 삭제한다.
	 *
	 * @param systemCntc
	 * @param model
	 * @return "forward:/ssi/syi/sim/getSystemCntcList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/sim/removeSystemCntc.do")
	public String deleteSystemCntc(SystemCntc systemCntc, ModelMap model) throws Exception {
		systemCntcService.deleteSystemCntc(systemCntc);
		return "forward:/ssi/syi/sim/getSystemCntcList.do";
	}

	/**
	 * 시스템연계 등록 화면으로 이동한다.
	 *
	 * @param searchVO 검색조건
	 * @param systemCntc
	 * @param model
	 * @return "egovframework/com/ssi/syi/sim/EgovSystemCntcRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/sim/addSystemCntc.do", params = "!cmd")
	public String insertSystemCntcView(@ModelAttribute("searchVO") SystemCntcVO searchVO,
			@ModelAttribute("systemCntc") SystemCntc systemCntc,
			ModelMap model) throws Exception {
		// 드롭다운 리스트 데이터 설정
		setDropdownListData(systemCntc, model);
		return "egovframework/com/ssi/syi/sim/EgovSystemCntcRegist";
	}

	/**
	 * 시스템연계를 등록한다.
	 *
	 * @param searchVO 검색조건
	 * @param systemCntc
	 * @param bindingResult
	 * @param model
	 * @return "forward:/ssi/syi/sim/getSystemCntcList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/sim/addSystemCntc.do", params = "cmd=Regist")
	public String insertSystemCntc(@ModelAttribute("searchVO") SystemCntcVO searchVO,
			@Valid @ModelAttribute("systemCntc") SystemCntc systemCntc,
			BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			// 드롭다운 리스트 데이터 설정
			setDropdownListData(systemCntc, model);
			return "egovframework/com/ssi/syi/sim/EgovSystemCntcRegist";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		systemCntc.setFrstRegisterId(uniqId);

		// ID Generation
		String sCntcId = idgenService.getNextStringId();
		systemCntc.setCntcId(sCntcId);

		systemCntcService.insertSystemCntc(systemCntc);
		return "forward:/ssi/syi/sim/getSystemCntcList.do";
	}

	/**
	 * 시스템연계 상세내역을 조회한다.
	 *
	 * @param searchVO 검색조건
	 * @param systemCntc
	 * @param model
	 * @param redirectAttributes
	 * @return "egovframework/com/ssi/syi/sim/EgovSystemCntcDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/sim/getSystemCntcDetail.do")
	public String selectSystemCntcDetail(@ModelAttribute("searchVO") SystemCntcVO searchVO,
			SystemCntc systemCntc, ModelMap model,
			RedirectAttributes redirectAttributes) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		String selfUri = "/ssi/syi/sim/getSystemCntcDetail.do";
		model.addAttribute("selfUri", selfUri);

		SystemCntc vo = systemCntcService.selectSystemCntcDetail(systemCntc);
		model.addAttribute("result", vo);

		// 드롭다운 리스트 데이터 설정 (상세 조회용)
		setDropdownListDataForDetail(vo, model);

		return "egovframework/com/ssi/syi/sim/EgovSystemCntcDetail";
	}

	/**
	 * 시스템연계 목록을 조회한다.
	 *
	 * @param searchVO
	 * @param model
	 * @param redirectAttributes
	 * @return "egovframework/com/ssi/syi/sim/EgovSystemCntcList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "시스템연계관리", listUrl = "/ssi/syi/sim/getSystemCntcList.do", order = 1210, gid = 70)
	@RequestMapping(value = "/ssi/syi/sim/getSystemCntcList.do")
	public String selectSystemCntcList(@ModelAttribute("searchVO") SystemCntcVO searchVO, ModelMap model,
			RedirectAttributes redirectAttributes) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		String selfUri = "/ssi/syi/sim/getSystemCntcList.do";
		model.addAttribute("selfUri", selfUri);

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

		List<EgovMap> resultList = systemCntcService.selectSystemCntcList(searchVO);
		model.addAttribute("resultList", resultList);

		int totCnt = systemCntcService.selectSystemCntcListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/ssi/syi/sim/EgovSystemCntcList";
	}

	/**
	 * 시스템연계 수정 화면으로 이동한다.
	 *
	 * @param searchVO 검색조건
	 * @param systemCntc
	 * @param model
	 * @return "egovframework/com/ssi/syi/sim/EgovSystemCntcUpdt"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/sim/updateSystemCntc.do", params = "!cmd")
	public String updateSystemCntcView(@ModelAttribute("searchVO") SystemCntcVO searchVO,
			@ModelAttribute("systemCntc") SystemCntc systemCntc,
			ModelMap model) throws Exception {

		SystemCntc vo = systemCntcService.selectSystemCntcDetail(systemCntc);
		model.addAttribute("systemCntc", vo);

		// 드롭다운 리스트 데이터 설정 (상세 조회용)
		setDropdownListDataForDetail(vo, model);

		return "egovframework/com/ssi/syi/sim/EgovSystemCntcUpdt";
	}

	/**
	 * 시스템연계를 수정한다.
	 *
	 * @param searchVO 검색조건
	 * @param systemCntc
	 * @param bindingResult
	 * @param model
	 * @return "forward:/ssi/syi/sim/getSystemCntcList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/sim/updateSystemCntc.do", params = "cmd=Modify")
	public String updateSystemCntc(@ModelAttribute("searchVO") SystemCntcVO searchVO,
			@Valid @ModelAttribute("systemCntc") SystemCntc systemCntc,
			BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			// 드롭다운 리스트 데이터 설정 (상세 조회용)
			setDropdownListDataForDetail(systemCntc, model);
			return "egovframework/com/ssi/syi/sim/EgovSystemCntcUpdt";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

		systemCntc.setLastUpdusrId(uniqId);
		systemCntcService.updateSystemCntc(systemCntc);
		return "forward:/ssi/syi/sim/getSystemCntcList.do";
	}

	/**
	 * 시스템연계 승인 목록을 조회한다.
	 *
	 * @param searchVO
	 * @param model
	 * @param redirectAttributes
	 * @return "egovframework/com/ssi/syi/sim/EgovSystemCntcList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/scm/getConfirmSystemCntcList.do")
	public String selectConfirmSystemCntcList(@ModelAttribute("searchVO") SystemCntcVO searchVO, ModelMap model,
			RedirectAttributes redirectAttributes) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		String selfUri = "/ssi/syi/scm/getConfirmSystemCntcList.do";
		model.addAttribute("selfUri", selfUri);

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

		List<EgovMap> resultList = systemCntcService.selectSystemCntcList(searchVO);
		model.addAttribute("resultList", resultList);

		int totCnt = systemCntcService.selectSystemCntcListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/ssi/syi/sim/EgovSystemCntcList";
	}

	/**
	 * 시스템연계 승인 상세내역을 조회한다.
	 *
	 * @param searchVO 검색조건
	 * @param systemCntc
	 * @param cmd
	 * @param model
	 * @param redirectAttributes
	 * @return "egovframework/com/ssi/syi/sim/EgovSystemCntcDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/scm/getConfirmSystemCntcDetail.do")
	public String selectConfirmSystemCntcDetail(@ModelAttribute("searchVO") SystemCntcVO searchVO,
			SystemCntc systemCntc,
			@RequestParam(value = "cmd", required = false, defaultValue = "") String cmd,
			ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		if ("Confirm".equals(cmd)) {
			// 로그인VO에서 사용자 정보 가져오기
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

			systemCntc.setLastUpdusrId(uniqId);
			systemCntcService.confirmSystemCntc(systemCntc);
		}

		String selfUri = "/ssi/syi/scm/getConfirmSystemCntcDetail.do";
		model.addAttribute("selfUri", selfUri);

		SystemCntc vo = systemCntcService.selectSystemCntcDetail(systemCntc);
		model.addAttribute("result", vo);

		// 드롭다운 리스트 데이터 설정 (상세 조회용)
		setDropdownListDataForDetail(vo, model);

		return "egovframework/com/ssi/syi/sim/EgovSystemCntcDetail";
	}

	/**
	 * 드롭다운 리스트 데이터를 설정한다. (등록 화면용)
	 *
	 * @param systemCntc
	 * @param model
	 * @throws Exception
	 */
	private void setDropdownListData(SystemCntc systemCntc, ModelMap model) throws Exception {
		// 연계기관 리스트박스 데이터
		CntcInsttVO searchCntcInsttVO = new CntcInsttVO();
		searchCntcInsttVO.setRecordCountPerPage(999999);
		searchCntcInsttVO.setFirstIndex(0);
		searchCntcInsttVO.setSearchCondition("CodeList");
		List<EgovMap> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
		model.addAttribute("cntcInsttList", cntcInsttList);

		// 연계시스템 리스트박스 데이터
		CntcSystemVO searchCntcSystemVO = new CntcSystemVO();
		searchCntcSystemVO.setRecordCountPerPage(999999);
		searchCntcSystemVO.setFirstIndex(0);
		searchCntcSystemVO.setSearchCondition("CodeList");

		if (systemCntc.getProvdInsttId() == null || systemCntc.getProvdInsttId().equals("")) {
			if (cntcInsttList.size() > 0) {
				EgovMap emp = cntcInsttList.get(0);
				systemCntc.setProvdInsttId(emp.get("insttId").toString());
			}
		}
		searchCntcSystemVO.setInsttId(systemCntc.getProvdInsttId());
		List<EgovMap> cntcProvdSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
		model.addAttribute("cntcProvdSystemList", cntcProvdSystemList);

		if (systemCntc.getRequstInsttId() == null || systemCntc.getRequstInsttId().equals("")) {
			if (cntcInsttList.size() > 0) {
				EgovMap emp = cntcInsttList.get(0);
				systemCntc.setRequstInsttId(emp.get("insttId").toString());
			}
		}
		searchCntcSystemVO.setInsttId(systemCntc.getRequstInsttId());
		List<EgovMap> cntcRequstSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
		model.addAttribute("cntcRequstSystemList", cntcRequstSystemList);

		// 연계서비스 리스트박스 데이터
		CntcServiceVO searchCntcServiceVO = new CntcServiceVO();
		searchCntcServiceVO.setRecordCountPerPage(999999);
		searchCntcServiceVO.setFirstIndex(0);
		searchCntcServiceVO.setSearchCondition("CodeList");
		searchCntcServiceVO.setInsttId(systemCntc.getProvdInsttId());

		if (systemCntc.getProvdSysId() == null || systemCntc.getProvdSysId().equals("")) {
			if (cntcProvdSystemList.size() > 0) {
				EgovMap emp = cntcProvdSystemList.get(0);
				systemCntc.setProvdSysId(emp.get("sysId").toString());
			}
		}
		searchCntcServiceVO.setSysId(systemCntc.getProvdSysId());
		List<EgovMap> cntcProvdServiceList = cntcInsttService.selectCntcServiceList(searchCntcServiceVO);
		model.addAttribute("cntcProvdServiceList", cntcProvdServiceList);
	}

	/**
	 * 드롭다운 리스트 데이터를 설정한다. (상세/수정 화면용)
	 *
	 * @param systemCntc
	 * @param model
	 * @throws Exception
	 */
	private void setDropdownListDataForDetail(SystemCntc systemCntc, ModelMap model) throws Exception {
		// 연계기관 리스트박스 데이터
		CntcInsttVO searchCntcInsttVO = new CntcInsttVO();
		searchCntcInsttVO.setRecordCountPerPage(999999);
		searchCntcInsttVO.setFirstIndex(0);
		searchCntcInsttVO.setSearchCondition("CodeList");
		List<EgovMap> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
		model.addAttribute("cntcInsttList", cntcInsttList);

		// 연계시스템 리스트박스 데이터
		CntcSystemVO searchCntcSystemVO = new CntcSystemVO();
		searchCntcSystemVO.setRecordCountPerPage(999999);
		searchCntcSystemVO.setFirstIndex(0);
		searchCntcSystemVO.setSearchCondition("CodeList");
		searchCntcSystemVO.setInsttId(systemCntc.getProvdInsttId());
		List<EgovMap> cntcProvdSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
		model.addAttribute("cntcProvdSystemList", cntcProvdSystemList);

		searchCntcSystemVO.setInsttId(systemCntc.getRequstInsttId());
		List<EgovMap> cntcRequstSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
		model.addAttribute("cntcRequstSystemList", cntcRequstSystemList);

		// 연계서비스 리스트박스 데이터
		CntcServiceVO searchCntcServiceVO = new CntcServiceVO();
		searchCntcServiceVO.setRecordCountPerPage(999999);
		searchCntcServiceVO.setFirstIndex(0);
		searchCntcServiceVO.setSearchCondition("CodeList");
		searchCntcServiceVO.setInsttId(systemCntc.getProvdInsttId());
		searchCntcServiceVO.setSysId(systemCntc.getProvdSysId());
		List<EgovMap> cntcProvdServiceList = cntcInsttService.selectCntcServiceList(searchCntcServiceVO);
		model.addAttribute("cntcProvdServiceList", cntcProvdServiceList);
	}

}
