package egovframework.com.cop.smt.djm.web;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.annotation.RequireAdmin;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovAttachmentGrants;
import egovframework.com.cmm.util.EgovAuthorizationHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.djm.service.ChargerVO;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.DeptVO;
import egovframework.com.cop.smt.djm.service.EgovDeptJobService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * <pre>
 * 개요
 * - 부서업무에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 부서업무에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 부서업무의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 장철호
 * @since 28-6-2010 오전 10:59:05
 * @version 1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.28  장철호          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2019.12.09  신용호          KISA 보안약점 조치 (위험한 형식 파일 업로드)
 *   2020.10.27  신용호          파일 업로드 수정 (multiRequest.getFiles), 널(null) 값 체크
 *   2022.11.11  김혜준          시큐어코딩 처리
 *   2025.06.10  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(지역 변수 명명 규칙)
 *
 *      </pre>
 */
@Controller
public class EgovDeptJobController {

	@Resource(name = "EgovDeptJobService")
	protected EgovDeptJobService deptJobService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

    // 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	/**
	 * 담당자 정보에 대한 팝업 목록을 조회한다.
	 * 
	 * @param ChargerVO
	 * @return String
	 *
	 * @param chargerVO
	 */
	@RequestMapping("/cop/smt/djm/selectChargerListPopup.do")
	@RequireAdmin
	public String selectChargerListPopup(@ModelAttribute("searchVO") ChargerVO chargerVO, ModelMap model) {
		return "egovframework/com/cop/smt/djm/EgovChargerListPopup";
	}

	/**
	 * 담당자 정보에 대한 목록을 조회한다.
	 * 
	 * @param ChargerVO
	 * @return String
	 *
	 * @param chargerVO
	 */
	@RequestMapping("/cop/smt/djm/selectChargerList.do")
	@RequireAdmin
	public String selectChargerList(@ModelAttribute("searchVO") ChargerVO chargerVO, ModelMap model) {

		chargerVO.setPageUnit(propertyService.getInt("pageUnit"));
		chargerVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(chargerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(chargerVO.getPageUnit());
		paginationInfo.setPageSize(chargerVO.getPageSize());

		chargerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		chargerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		chargerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = deptJobService.selectChargerList(chargerVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/djm/EgovChargerList";
	}

	/**
	 * 부서 정보에 대한 팝업 목록을 조회한다.
	 * 
	 * @param DeptVO
	 * @return String
	 *
	 * @param deptVO
	 */
	@RequestMapping("/cop/smt/djm/selectDeptListPopup.do")
	@RequireAdmin
	public String selectDeptListPopup(@ModelAttribute("searchVO") DeptVO deptVO, ModelMap model) {
		return "egovframework/com/cop/smt/djm/EgovDeptListPopup";
	}

	/**
	 * 부서 정보에 대한 목록을 조회한다.
	 * 
	 * @param DeptVO
	 * @return String
	 *
	 * @param deptVO
	 */
	@RequestMapping("/cop/smt/djm/selectDeptList.do")
	@RequireAdmin
	public String selectDeptList(@ModelAttribute("searchVO") DeptVO deptVO, ModelMap model) {

		deptVO.setPageUnit(propertyService.getInt("pageUnit"));
		deptVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(deptVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(deptVO.getPageUnit());
		paginationInfo.setPageSize(deptVO.getPageSize());

		deptVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		deptVO.setLastIndex(paginationInfo.getLastRecordIndex());
		deptVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = deptJobService.selectDeptList(deptVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/djm/EgovDeptList";
	}

	/**
	 * 부서업무함 정보에 대한 팝업 목록을 조회한다.
	 * 
	 * @param DeptVO
	 * @return String
	 *
	 * @param deptVO
	 */
	@RequestMapping("/cop/smt/djm/selectDeptJobBxListPopup.do")
	@RequireAdmin
	public String selectDeptJobBxListPopup(@ModelAttribute("searchVO") DeptJobBxVO deptJobBxVO, ModelMap model) {
		return "egovframework/com/cop/smt/djm/EgovDeptJobBxListPopup";
	}

	/**
	 * 부서업무함 정보에 대한 목록을 조회한다.
	 * 
	 * @param DeptJobBxVO
	 * @return String
	 *
	 * @param deptJobBxVO
	 */
	@SuppressWarnings("unchecked")
	@IncludedInfo(name = "부서업무함관리", order = 400, gid = 40)
	@RequestMapping("/cop/smt/djm/selectDeptJobBxList.do")
	public String selectDeptJobBxList(@ModelAttribute("searchVO") DeptJobBxVO deptJobBxVO, ModelMap model) {

		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxList";

		if (deptJobBxVO.getPopupCnd() != null && !deptJobBxVO.getPopupCnd().equals("")) {
			sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxListS";
		}

		deptJobBxVO.setPageUnit(propertyService.getInt("pageUnit"));
		deptJobBxVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(deptJobBxVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(deptJobBxVO.getPageUnit());
		paginationInfo.setPageSize(deptJobBxVO.getPageSize());

		deptJobBxVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		deptJobBxVO.setLastIndex(paginationInfo.getLastRecordIndex());
		deptJobBxVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = deptJobService.selectDeptJobBxList(deptJobBxVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		List<DeptJobBxVO> list = (List<DeptJobBxVO>) map.get("resultList");

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		// KISA 보안약점 조치 - 널(null) 값 체크
		if (list == null) {
			model.addAttribute("resultNum", 0);
		} else {
			model.addAttribute("resultNum", list.size());
		}
		model.addAttribute("paginationInfo", paginationInfo);

		return sLocationUrl;
	}

	/**
	 * 부서업무함 정보의 등록화면으로 이동한다.
	 * 
	 * @param DeptJobBx
	 * @return String
	 *
	 * @param DeptJobBx
	 */
	@PostMapping("/cop/smt/djm/addDeptJobBx.do")
	@RequireAdmin
	public String addDeptJobBx(@ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO, ModelMap model) {
		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxRegist";

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		return sLocationUrl;
	}

	/**
	 * 부서업무함 등록시 표시순서를 조회한다.
	 * 
	 * @param DeptJobBx
	 * @return String
	 *
	 * @param DeptJobBx
	 */
	@RequestMapping("/cop/smt/djm/getDeptJobBxOrdr.do")
	@RequireAdmin
	public String getDeptJobBxOrdr(final HttpServletRequest request, @ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO, ModelMap model) {

		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxRegist";
		String referer = request.getHeader("Referer");

		if (referer == null || referer.indexOf("addDeptJobBx.do") < 0) {
			sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxUpdt";
		}

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		model.addAttribute("indictOrdrValue", deptJobService.selectDeptJobBxOrdr(deptJobBxVO.getDeptId()) + 1);
		return sLocationUrl;
	}

	/**
	 * 부서업무함 정보의 수정화면으로 이동한다.
	 * 
	 * @param DeptJobBx
	 * @return String
	 *
	 * @param DeptJobBx
	 */
	@PostMapping("/cop/smt/djm/modifyDeptJobBx.do")
	@RequireAdmin
	public String modifyDeptJobBx(@ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO, ModelMap model) {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		DeptJobBxVO resultVO = deptJobService.selectDeptJobBx(deptJobBxVO);

		// 삭제 경로와 동일하게, 서버 조회 결과가 없으면 목록으로 돌려보낸다.
		if (resultVO == null) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.select"));
			return "forward:/cop/smt/djm/selectDeptJobBxList.do";
		}

		resultVO.setSearchCnd(deptJobBxVO.getSearchCnd());
		resultVO.setSearchWrd(deptJobBxVO.getSearchWrd());
		resultVO.setPageIndex(deptJobBxVO.getPageIndex());

		model.addAttribute("indictOrdrValue", resultVO.getIndictOrdr());
		EgovAuthorizationHelper.assertSameDept(resultVO.getDeptId());
		model.addAttribute("deptJobBxVO", resultVO);

		return "egovframework/com/cop/smt/djm/EgovDeptJobBxUpdt";
	}

	/**
	 * 부서업무함 정보를 수정한다.
	 * 
	 * @param DeptJobBxVO
	 * @return String
	 *
	 * @param deptJobBxVO
	 */
	@PostMapping("/cop/smt/djm/updateDeptJobBx.do")
	@RequireAdmin
	public String updateDeptJobBx(@Valid @ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO, BindingResult bindingResult,
			@RequestParam(value = "deptIndictOrdr", required = false) String deptIndictOrdr, ModelMap model) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		// 같은 부서원만 수정한다. 부서는 저장된 값으로 고정해 다른 부서로 옮기지 못하게 한다
		DeptJobBxVO storedBx = deptJobService.selectDeptJobBx(deptJobBxVO);
		EgovAuthorizationHelper.assertSameDept(storedBx == null ? null : storedBx.getDeptId());
		deptJobBxVO.setDeptId(storedBx.getDeptId());

		if (bindingResult.hasErrors()) {
			// modifyDeptJobBx 와 같이 화면의 표시순서 상한(deptIndictOrdr)을 다시 담는다.
			model.addAttribute("indictOrdrValue", deptIndictOrdr);
			return "egovframework/com/cop/smt/djm/EgovDeptJobBxUpdt";
		}

		if (isAuthenticated) {
			deptJobBxVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			deptJobService.updateDeptJobBx(deptJobBxVO);
		}

		return "forward:/cop/smt/djm/selectDeptJobBxList.do";
	}

	/**
	 * 부서업무함 정보의 표시순서를 수정한다.
	 * 
	 * @param DeptJobBx
	 * @return String
	 *
	 * @param deptJobBx
	 */
	@PostMapping("/cop/smt/djm/updateDeptJobBxOrdr.do")
	@RequireAdmin
	public String updateDeptJobBxOrdr(@ModelAttribute("searchVO") DeptJobBxVO deptJobBxVO, ModelMap model) {
		DeptJobBxVO storedBx = deptJobService.selectDeptJobBx(deptJobBxVO);
		EgovAuthorizationHelper.assertSameDept(storedBx == null ? null : storedBx.getDeptId());
		// 부서·현재 순서는 원본 값을 쓰고 요청에서는 이동 방향(ordrCnd)만 받는다(다른 부서 업무함 순서 변경 차단)
		deptJobBxVO.setDeptId(storedBx.getDeptId());
		deptJobBxVO.setIndictOrdr(storedBx.getIndictOrdr());
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		boolean changed = false;

		if (isAuthenticated) {
			deptJobBxVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			changed = deptJobService.updateDeptJobBxOrdr(deptJobBxVO);
		}

		if (!changed) {
			model.addAttribute("indictOrdrChanged", "false");
		}

		return "forward:/cop/smt/djm/selectDeptJobBxList.do";
	}

	/**
	 * 부서업무함 정보를 등록한다.
	 * 
	 * @param DeptJobBxVO
	 * @return String
	 *
	 * @param deptJobBxVO
	 */
	@PostMapping("/cop/smt/djm/insertDeptJobBx.do")
	@RequireAdmin
	public String insertDeptJobBx(@Valid @ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO, BindingResult bindingResult,
			@RequestParam(value = "deptIndictOrdr", required = false) String deptIndictOrdr,
			RedirectAttributes redirectAttributes, ModelMap model) {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxRegist";

		// 업무함은 자기 부서에만 만든다
		EgovAuthorizationHelper.assertSameDept(deptJobBxVO.getDeptId());

		if(bindingResult.hasErrors()){
			// getDeptJobBxOrdr 와 같이 화면의 표시순서 상한(deptIndictOrdr)을 다시 담는다.
			model.addAttribute("indictOrdrValue", deptIndictOrdr);
			return sLocationUrl;
		}

		// 아이디 설정
		deptJobBxVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		deptJobBxVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		// 부서내 부서업무함명 중복체크
		if (deptJobService.selectDeptJobBxCheck(deptJobBxVO) > 0) {
			model.addAttribute("deptJobBxNmDuplicated", "true");
			sLocationUrl = "forward:/cop/smt/djm/addDeptJobBx.do";
		} else {
			deptJobService.insertDeptJobBx(deptJobBxVO);
			sLocationUrl = "forward:/cop/smt/djm/selectDeptJobBxList.do";
		}
		return sLocationUrl;
	}

	/**
	 * 부서업무함 정보를 삭제한다.
	 * 
	 * @param DeptJobBx
	 * @return String
	 *
	 * @param DeptJobBx
	 */
	@PostMapping("/cop/smt/djm/deleteDeptJobBx.do")
	@RequireAdmin
	public String deleteDeptJobBx(@ModelAttribute("deptJobBxVO") DeptJobBx deptJobBx, ModelMap model) {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		// 함에 부서업무가 남아 있으면 삭제하지 않는다.
		// 함을 지우면 그 업무들이 목록 조회의 부서 조건에 걸리지 않아 화면에서 회수하거나 지울 수 없다.
		DeptJobBxVO lookup = new DeptJobBxVO();
		lookup.setDeptJobBxId(deptJobBx.getDeptJobBxId());
		DeptJobBxVO stored = deptJobService.selectDeptJobBx(lookup);
		EgovAuthorizationHelper.assertSameDept(stored == null ? null : stored.getDeptId());
		// 하위 업무 수는 원본 업무함의 부서·업무함 ID 로 센다(목록 조회가 부서 조건을 필수로 쓴다)
		DeptJobVO childVO = new DeptJobVO();
		childVO.setSearchDeptId(stored.getDeptId());
		childVO.setSearchDeptJobBxId(deptJobBx.getDeptJobBxId());
		Map<String, Object> childMap = deptJobService.selectDeptJobList(childVO);

		if (Integer.parseInt((String) childMap.get("resultCnt")) > 0) {
			model.addAttribute("deptJobBxNotEmpty", "true");
			return "forward:/cop/smt/djm/selectDeptJobBxList.do";
		}

		deptJobService.deleteDeptJobBx(deptJobBx);
		return "forward:/cop/smt/djm/selectDeptJobBxList.do";
	}

	/**
	 * 부서업무 정보에 대한 목록을 조회한다.
	 * 
	 * @param DeptJobVO
	 * @return String
	 *
	 * @param deptJobVO
	 */
	@IncludedInfo(name = "부서업무정보", order = 401, gid = 40)
	@RequestMapping("/cop/smt/djm/selectDeptJobList.do")
	public String selectDeptJobList(@ModelAttribute("searchVO") DeptJobVO deptJobVO, ModelMap model) {
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		// KISA 보안취약점 조치 (2018-12-10, 신용호)
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		deptJobVO.setPageUnit(propertyService.getInt("pageUnit"));
		deptJobVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(deptJobVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(deptJobVO.getPageUnit());
		paginationInfo.setPageSize(deptJobVO.getPageSize());

		deptJobVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		deptJobVO.setLastIndex(paginationInfo.getLastRecordIndex());
		deptJobVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 로그인 사용자 부서의 부서업무만 조회한다(요청 값은 신뢰하지 않고 로그인 사용자 부서로 고정, 부서가 없으면 빈 목록)
		deptJobVO.setSearchDeptId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getOrgnztId()));

		Map<String, Object> map = deptJobService.selectDeptJobList(deptJobVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/djm/EgovDeptJobList";
	}

	/**
	 * 부서업무 정보의 등록화면으로 이동한다.
	 * 
	 * @param DeptJob
	 * @return String
	 *
	 * @param deptJob
	 */
	@PostMapping("/cop/smt/djm/addDeptJob.do")
	@RequireAdmin
	public String addDeptJob(@ModelAttribute("deptJobVO") DeptJobVO deptJobVO, ModelMap model) {
		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobRegist";

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 부서는 로그인 사용자의 부서, 업무담당자는 로그인 사용자 본인으로 채운다
		LoginVO loginVO = EgovAuthorizationHelper.assertLoginUser();
		deptJobVO.setDeptId(loginVO.getOrgnztId());
		deptJobVO.setDeptNm(deptJobService.selectDept(loginVO.getOrgnztId()));
		deptJobVO.setDeptJobBxId(deptJobVO.getSearchDeptJobBxId());
		deptJobVO.setChargerId(loginVO.getUniqId());
		deptJobVO.setChargerNm(loginVO.getName());

		// 파일업로드 제한
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

		model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
		model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		return sLocationUrl;
	}

	/**
	 * 부서업무 정보의 수정화면으로 이동한다.
	 * 
	 * @param DeptJob
	 * @return String
	 *
	 * @param deptJob
	 */
	@PostMapping("/cop/smt/djm/modifyDeptJob.do")
	@RequireAdmin
	public String modifyDeptJob(@ModelAttribute("deptJobVO") DeptJobVO deptJobVO, ModelMap model, HttpServletRequest request) {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		DeptJobVO resultVO = deptJobService.selectDeptJob(deptJobVO);

		// 삭제 경로와 동일하게, 서버 조회 결과가 없으면 목록으로 돌려보낸다.
		if (resultVO == null) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.select"));
			return "forward:/cop/smt/djm/selectDeptJobList.do";
		}

		resultVO.setSearchCnd(deptJobVO.getSearchCnd());
		resultVO.setSearchWrd(deptJobVO.getSearchWrd());
		resultVO.setSearchDeptId(deptJobVO.getSearchDeptId());
		resultVO.setSearchDeptJobBxId(deptJobVO.getSearchDeptJobBxId());
		resultVO.setPageIndex(deptJobVO.getPageIndex());
		// 같은 부서 업무만 수정한다 (다른 부서 업무는 관리자도 불가)
		EgovAuthorizationHelper.assertSameDept(resultVO.getDeptId());
		EgovAttachmentGrants.allowDelete(request, resultVO.getAtchFileId());
		model.addAttribute("deptJobVO", resultVO);

		return "egovframework/com/cop/smt/djm/EgovDeptJobUpdt";
	}

	/**
	 * 부서업무 정보를 조회한다.
	 * 
	 * @param DeptJobVO
	 * @return String
	 *
	 * @param deptJobVO
	 */
	@PostMapping("/cop/smt/djm/selectDeptJob.do")
	@RequireAdmin
	public String selectDeptJob(@ModelAttribute("deptJobVO") DeptJobVO deptJobVO, ModelMap model) {
		DeptJob deptJob = deptJobService.selectDeptJob(deptJobVO);
		LoginVO loginVO = EgovAuthorizationHelper.assertLoginUser();
		// 일반 사용자는 자신이 업무담당자·등록자이거나 같은 부서의 부서업무만 조회한다(관리자는 종전과 동일)
		if (!EgovAuthorizationHelper.isAdmin()) {
			boolean permitted = deptJob != null && (loginVO.getUniqId().equals(deptJob.getChargerId())
					|| loginVO.getUniqId().equals(deptJob.getFrstRegisterId())
					|| (deptJob.getDeptId() != null && deptJob.getDeptId().equals(loginVO.getOrgnztId())));
			if (!permitted) {
				throw new egovframework.com.cmm.exception.EgovAccessDeniedException("권한이 없습니다.");
			}
		}
		model.addAttribute("deptJob", deptJob);
		// 수정·삭제 버튼: 같은 부서 업무에만 보인다
		model.addAttribute("canModify", deptJob != null && EgovAuthorizationHelper.isSameDept(deptJob.getDeptId()));

		/*
		 * 공통코드 우선순위 조회
		 */
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
		voComCode.setCodeId("COM059");
		List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("priort", listComCode);

		return "egovframework/com/cop/smt/djm/EgovDeptJobDetail";
	}

	/**
	 * 부서업무 정보를 수정한다.
	 * 
	 * @param DeptJob
	 * @return String
	 *
	 * @param deptJob
	 */
	@PostMapping("/cop/smt/djm/updateDeptJob.do")
	@RequireAdmin
	public String updateDeptJob(final MultipartHttpServletRequest multiRequest,
			@RequestParam Map<String, Object> commandMap, @Valid @ModelAttribute("deptJobVO") DeptJobVO deptJobVO,
			BindingResult bindingResult, ModelMap model) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		// KISA 보안취약점 조치 (2018-12-10, 신용호)

		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 2026.07.30 보안 조치 - 수정 대상 검증 (같은 부서 업무만, 다른 부서 업무는 관리자도 불가)
		// 오류 재표시도 첨부 목록을 보여주므로 검증을 그보다 먼저 한다.
		DeptJob storedDeptJob = deptJobService.selectDeptJob(deptJobVO);
		EgovAuthorizationHelper.assertSameDept(storedDeptJob == null ? null : storedDeptJob.getDeptId());
		// 옮겨 갈 업무함도 같은 부서여야 한다(다른 부서 업무함으로 이동 차단)
		DeptJobBxVO targetBx = new DeptJobBxVO();
		targetBx.setDeptJobBxId(deptJobVO.getDeptJobBxId());
		DeptJobBxVO storedTargetBx = deptJobService.selectDeptJobBx(targetBx);
		EgovAuthorizationHelper.assertSameDept(storedTargetBx == null ? null : storedTargetBx.getDeptId());
		// 첨부 그룹은 요청값이 아니라 소유권을 확인한 원본의 것만 쓴다(남의 첨부 ID 저장 → 삭제 허가 우회 차단)
		deptJobVO.setAtchFileId(Objects.toString(storedDeptJob.getAtchFileId(), ""));

		if (bindingResult.hasErrors()) {
			model.addAttribute("deptJob", storedDeptJob);
			return "egovframework/com/cop/smt/djm/EgovDeptJobUpdt";
		}

		/*
		 * ***************************************************************** // 첨부파일 관련
		 * ID 생성 start....
		 */
		// 2022.11.11 시큐어코딩 처리
		String atchFileId = deptJobVO.getAtchFileId();

		// final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if (!files.isEmpty()) {
			String atchFileAt = commandMap.get("atchFileAt") == null ? "" : (String) commandMap.get("atchFileAt");
			if ("N".equals(atchFileAt)) {
				List<FileVO> fvoList = fileUtil.parseFileInf(files, "DSCH_", 0, atchFileId, "");
				atchFileId = fileMngService.insertFileInfs(fvoList);
				// 첨부파일 ID 셋팅
				deptJobVO.setAtchFileId(atchFileId); // 첨부파일 ID

			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int fileKeyParam = fileMngService.getMaxFileSN(fvo);
				List<FileVO> fvoList = fileUtil.parseFileInf(files, "DSCH_", fileKeyParam, atchFileId, "");
				fileMngService.updateFileInfs(fvoList);
			}
		}

		// 첨부파일이 없어도 수정 내용은 저장한다
		deptJobVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		deptJobService.updateDeptJob(deptJobVO);

		return "forward:/cop/smt/djm/selectDeptJobList.do";
	}

	/**
	 * 부서업무 정보를 등록한다.
	 * 
	 * @param DeptJob
	 * @return String
	 *
	 * @param deptJob
	 */
	@PostMapping("/cop/smt/djm/insertDeptJob.do")
	@RequireAdmin
	public String insertDeptJob(final MultipartHttpServletRequest multiRequest,
			@Valid @ModelAttribute("deptJobVO") DeptJobVO deptJobVO, BindingResult bindingResult, ModelMap model) {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobRegist";

		// 업무담당자는 요청 값이 아니라 로그인 사용자로 고정한다
		deptJobVO.setChargerId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		// 등록할 업무함은 같은 부서 것이어야 한다(다른 부서 업무함에 등록 차단)
		DeptJobBxVO targetBx = new DeptJobBxVO();
		targetBx.setDeptJobBxId(deptJobVO.getDeptJobBxId());
		DeptJobBxVO storedTargetBx = deptJobService.selectDeptJobBx(targetBx);
		EgovAuthorizationHelper.assertSameDept(storedTargetBx == null ? null : storedTargetBx.getDeptId());

		if (bindingResult.hasErrors()) {

			// 파일업로드 제한
			String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
			String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

			model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
			model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

			return sLocationUrl;
		}

		// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> fvoList = null;
		String atchFileId = "";

		// final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if (!files.isEmpty()) {
			fvoList = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(fvoList); // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		}

		// 리턴받은 첨부파일ID를 셋팅한다..
		deptJobVO.setAtchFileId(atchFileId); // 첨부파일 ID

		// 아이디 설정
		deptJobVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		deptJobVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		deptJobService.insertDeptJob(deptJobVO);
		sLocationUrl = "forward:/cop/smt/djm/selectDeptJobList.do";

		return sLocationUrl;
	}

	/**
	 * 부서업무 정보를 삭제한다.
	 * 
	 * @param DeptJob
	 * @return String
	 *
	 * @param deptJob
	 */
	@PostMapping("/cop/smt/djm/deleteDeptJob.do")
	@RequireAdmin
	public String deleteDeptJob(@ModelAttribute("deptJobVO") DeptJob deptJob, ModelMap model) {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// IDOR 조치: 요청으로 전달된 atchFileId/deptJob 값을 그대로 신뢰하지 않고,
		// deptJobId로 서버에서 원본 레코드를 조회하여 존재 여부를 확인한 뒤,
		// 삭제에 사용할 atchFileId/deptJobId는 반드시 서버에서 조회한 값만 사용한다.
		DeptJobVO checkVO = new DeptJobVO();
		checkVO.setDeptJobId(deptJob.getDeptJobId());
		DeptJobVO originDeptJob = deptJobService.selectDeptJob(checkVO);

		if (originDeptJob == null) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.select"));
			return "forward:/cop/smt/djm/selectDeptJobList.do";
		}

		// 같은 부서 업무만 삭제한다 (다른 부서 업무는 관리자도 불가)
		EgovAuthorizationHelper.assertSameDept(originDeptJob.getDeptId());

		// 첨부파일 삭제를 위한 ID 생성 start....
		// 클라이언트가 임의로 조작할 수 있는 값이 아닌, 서버에서 조회한 원본 첨부파일ID만 사용한다.
		String atchFileId = originDeptJob.getAtchFileId();

		if (atchFileId != null && !atchFileId.isEmpty()) {
			// 첨부파일을 삭제하기 위한 Vo
			FileVO fvo = new FileVO();
			fvo.setAtchFileId(atchFileId);

			fileMngService.deleteAllFileInf(fvo);
		}
		// 첨부파일 삭제 End.............

		// 삭제 대상 deptJob 역시 서버에서 조회한 원본 deptJobId 기준으로만 삭제한다.
		DeptJob targetDeptJob = new DeptJob();
		targetDeptJob.setDeptJobId(originDeptJob.getDeptJobId());
		deptJobService.deleteDeptJob(targetDeptJob);
		return "forward:/cop/smt/djm/selectDeptJobList.do";
	}

}
