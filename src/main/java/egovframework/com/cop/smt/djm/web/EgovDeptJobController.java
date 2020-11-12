package egovframework.com.cop.smt.djm.web;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.djm.service.ChargerVO;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.DeptVO;
import egovframework.com.cop.smt.djm.service.EgovDeptJobService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 부서업무에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 부서업무에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 부서업무의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:05
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2010.06.28   장철호            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2019.12.09   신용호            KISA 보안약점 조치 (위험한 형식 파일 업로드)
 *  2020.10.27   신용호            파일 업로드 수정 (multiRequest.getFiles), 널(null) 값 체크
 *
 * </pre>
 *
 */

@Controller
public class EgovDeptJobController {

	@Resource(name="EgovDeptJobService")
    protected EgovDeptJobService deptJobService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	@Resource(name="propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

    // 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    //Logger log = Logger.getLogger(this.getClass());

    /**
	 * 담당자 정보에 대한 팝업 목록을 조회한다.
	 * @param ChargerVO
	 * @return  String
	 *
	 * @param chargerVO
	 */
	@RequestMapping("/cop/smt/djm/selectChargerListPopup.do")
	public String selectChargerListPopup(@ModelAttribute("searchVO") ChargerVO chargerVO, ModelMap model) throws Exception{
		return "egovframework/com/cop/smt/djm/EgovChargerListPopup";
	}

	/**
	 * 담당자 정보에 대한 목록을 조회한다.
	 * @param ChargerVO
	 * @return  String
	 *
	 * @param chargerVO
	 */
	@RequestMapping("/cop/smt/djm/selectChargerList.do")
	public String selectChargerList(@ModelAttribute("searchVO") ChargerVO chargerVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//chargerVO.setUniqId(user.getUniqId());

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
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/djm/EgovChargerList";
	}

	/**
	 * 부서 정보에 대한 팝업 목록을 조회한다.
	 * @param DeptVO
	 * @return  String
	 *
	 * @param deptVO
	 */
	@RequestMapping("/cop/smt/djm/selectDeptListPopup.do")
	public String selectDeptListPopup(@ModelAttribute("searchVO") DeptVO deptVO, ModelMap model) throws Exception{
		return "egovframework/com/cop/smt/djm/EgovDeptListPopup";
	}

	/**
	 * 부서 정보에 대한 목록을 조회한다.
	 * @param DeptVO
	 * @return  String
	 *
	 * @param deptVO
	 */
	@RequestMapping("/cop/smt/djm/selectDeptList.do")
	public String selectDeptList(@ModelAttribute("searchVO") DeptVO deptVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

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
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/djm/EgovDeptList";
	}


	/**
	 * 부서업무함 정보에 대한 팝업 목록을 조회한다.
	 * @param DeptVO
	 * @return  String
	 *
	 * @param deptVO
	 */
	@RequestMapping("/cop/smt/djm/selectDeptJobBxListPopup.do")
	public String selectDeptJobBxListPopup(@ModelAttribute("searchVO") DeptJobBxVO deptJobBxVO, ModelMap model) throws Exception{
		return "egovframework/com/cop/smt/djm/EgovDeptJobBxListPopup";
	}


	/**
	 * 부서업무함 정보에 대한 목록을 조회한다.
	 * @param DeptJobBxVO
	 * @return  String
	 *
	 * @param deptJobBxVO
	 */
	@SuppressWarnings("unchecked")
	@IncludedInfo(name="부서업무함관리", order = 400 ,gid = 40)
	@RequestMapping("/cop/smt/djm/selectDeptJobBxList.do")
	public String selectDeptJobBxList(@ModelAttribute("searchVO") DeptJobBxVO deptJobBxVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxList";

		if(deptJobBxVO.getPopupCnd() != null && !deptJobBxVO.getPopupCnd().equals("")) sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxListS";

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
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		List<DeptJobBxVO> list = (List<DeptJobBxVO>)map.get("resultList");

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		// KISA 보안약점 조치 - 널(null) 값 체크
		if ( list == null )
			model.addAttribute("resultNum", 0);
		else
			model.addAttribute("resultNum", list.size());
		model.addAttribute("paginationInfo", paginationInfo);

		return sLocationUrl;
	}

	/**
	 * 부서업무함 정보를 조회한다.
	 * @param DeptJobBxVO
	 * @return  String
	 *
	 * @param deptJobBxVO
	 */
//	@RequestMapping("/cop/smt/djm/selectDeptJobBx.do")
//	public String selectDeptJobBx(@ModelAttribute("searchVO") DeptJobBxVO deptJobBxVO, ModelMap model) throws Exception{
//
//		DeptJobBx deptJobBx = deptJobService.selectDeptJobBx(deptJobBxVO);
//        model.addAttribute("deptJobBx", deptJobBx);
//
//		return "egovframework/com/cop/smt/djm/EgovDeptJobBxDetail";
//	}

	/**
	 * 부서업무함 정보의 등록화면으로 이동한다.
	 * @param DeptJobBx
	 * @return  String
	 *
	 * @param DeptJobBx
	 */
	@RequestMapping("/cop/smt/djm/addDeptJobBx.do")
	public String addDeptJobBx(
			@ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO,
			ModelMap model) throws Exception{
		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxRegist";

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	return sLocationUrl;
	}

	/**
	 * 부서업무함 등록시 표시순서를 조회한다.
	 * @param DeptJobBx
	 * @return  String
	 *
	 * @param DeptJobBx
	 */
	@RequestMapping("/cop/smt/djm/getDeptJobBxOrdr.do")
	public String getDeptJobBxOrdr(
			final HttpServletRequest request,
			@ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO,
			ModelMap model) throws Exception{

		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxRegist";

		if(request.getHeader("Referer").indexOf("addDeptJobBx.do") < 0){
			sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxUpdt";
		}

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}


    	//System.out.println(deptJobService.selectDeptJobBxOrdr(deptJobBxVO.getDeptId()));
    	//deptJobBxVO.setIndictOrdr(deptJobService.selectDeptJobBxOrdr(deptJobBxVO.getDeptId()) + 1);

    	model.addAttribute("indictOrdrValue", deptJobService.selectDeptJobBxOrdr(deptJobBxVO.getDeptId()) + 1);
    	return sLocationUrl;
	}

	/**
	 * 부서업무함 정보의 수정화면으로 이동한다.
	 * @param DeptJobBx
	 * @return  String
	 *
	 * @param DeptJobBx
	 */
	@RequestMapping("/cop/smt/djm/modifyDeptJobBx.do")
	public String modifyDeptJobBx(@ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	DeptJobBxVO resultVO = deptJobService.selectDeptJobBx(deptJobBxVO);
		resultVO.setSearchCnd(deptJobBxVO.getSearchCnd());
		resultVO.setSearchWrd(deptJobBxVO.getSearchWrd());
		resultVO.setPageIndex(deptJobBxVO.getPageIndex());

		model.addAttribute("indictOrdrValue", resultVO.getIndictOrdr());
        model.addAttribute("deptJobBxVO", resultVO);

		return "egovframework/com/cop/smt/djm/EgovDeptJobBxUpdt";
	}

	/**
	 * 부서업무함 정보를 수정한다.
	 * @param DeptJobBxVO
	 * @return  String
	 *
	 * @param deptJobBxVO
	 */
	@RequestMapping("/cop/smt/djm/updateDeptJobBx.do")
	public String updateDeptJobBx(@ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO, BindingResult bindingResult, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(deptJobBxVO, bindingResult);
		if (bindingResult.hasErrors()) {
			//DeptJobBx result = deptJobService.selectDeptJobBx(deptJobBxVO);
		    //model.addAttribute("deptJobBx", result);
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
	 * @param DeptJobBx
	 * @return  String
	 *
	 * @param deptJobBx
	 */
	@RequestMapping("/cop/smt/djm/updateDeptJobBxOrdr.do")
	public String updateDeptJobBxOrdr(@ModelAttribute("searchVO") DeptJobBxVO deptJobBxVO, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		boolean changed = false;

		if (isAuthenticated) {
			deptJobBxVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			changed = deptJobService.updateDeptJobBxOrdr(deptJobBxVO);
		}

		if(!changed){
			model.addAttribute("indictOrdrChanged", "false");
		}

		return "forward:/cop/smt/djm/selectDeptJobBxList.do";
	}

	/**
	 * 부서업무함 정보를 등록한다.
	 * @param DeptJobBxVO
	 * @return  String
	 *
	 * @param deptJobBxVO
	 */
	@RequestMapping("/cop/smt/djm/insertDeptJobBx.do")
	public String insertDeptJobBx(@ModelAttribute("deptJobBxVO") DeptJobBxVO deptJobBxVO, BindingResult bindingResult, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobBxRegist";

		//서버  validate 체크
        beanValidator.validate(deptJobBxVO, bindingResult);
		if(bindingResult.hasErrors()){
			return sLocationUrl;
		}

		//아이디 설정
		deptJobBxVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		deptJobBxVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		//부서내 부서업무함명 중복체크
		if(deptJobService.selectDeptJobBxCheck(deptJobBxVO) > 0){
			model.addAttribute("deptJobBxNmDuplicated", "true");
			sLocationUrl = "forward:/cop/smt/djm/addDeptJobBx.do";
		}else{
			deptJobService.insertDeptJobBx(deptJobBxVO);
	    	sLocationUrl = "forward:/cop/smt/djm/selectDeptJobBxList.do";
		}
		return sLocationUrl;
	}

	/**
	 * 부서업무함 정보를 삭제한다.
	 * @param DeptJobBx
	 * @return  String
	 *
	 * @param DeptJobBx
	 */
	@RequestMapping("/cop/smt/djm/deleteDeptJobBx.do")
	public String deleteDeptJobBx(@ModelAttribute("deptJobBxVO") DeptJobBx deptJobBx, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	deptJobService.deleteDeptJobBx(deptJobBx);
		return "forward:/cop/smt/djm/selectDeptJobBxList.do";
	}

	/**
	 * 부서업무 정보에 대한 목록을 조회한다.
	 * @param DeptJobVO
	 * @return  String
	 *
	 * @param deptJobVO
	 */
	@IncludedInfo(name="부서업무정보", order = 401 ,gid = 40)
	@RequestMapping("/cop/smt/djm/selectDeptJobList.do")
	public String selectDeptJobList(@ModelAttribute("searchVO") DeptJobVO deptJobVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
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

		if(deptJobVO.getSearchDeptId() == null || deptJobVO.getSearchDeptId().equals("")){
			deptJobVO.setSearchDeptId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getOrgnztId()));
		}

		Map<String, Object> map = deptJobService.selectDeptJobList(deptJobVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultBxList", deptJobService.selectDeptJobBxListAll());
		//model.addAttribute("deptId", loginVO.getOrgnztId());
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/djm/EgovDeptJobList";
	}

	/**
	 * 부서업무 정보의 등록화면으로 이동한다.
	 * @param DeptJob
	 * @return  String
	 *
	 * @param deptJob
	 */
	@RequestMapping("/cop/smt/djm/addDeptJob.do")
	public String addDeptJob(@ModelAttribute("deptJobVO") DeptJobVO deptJobVO, ModelMap model) throws Exception{
		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobRegist";

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	deptJobVO.setDeptId(deptJobVO.getSearchDeptId());
    	deptJobVO.setDeptNm(deptJobService.selectDept(deptJobVO.getSearchDeptId()));
    	deptJobVO.setDeptJobBxId(deptJobVO.getSearchDeptJobBxId());

    	// 파일업로드 제한
    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
    	
    	return sLocationUrl;
	}

	/**
	 * 부서업무 정보의 수정화면으로 이동한다.
	 * @param DeptJob
	 * @return  String
	 *
	 * @param deptJob
	 */
	@RequestMapping("/cop/smt/djm/modifyDeptJob.do")
	public String modifyDeptJob(@ModelAttribute("deptJobVO") DeptJobVO deptJobVO, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		DeptJobVO resultVO = deptJobService.selectDeptJob(deptJobVO);
		resultVO.setSearchCnd(deptJobVO.getSearchCnd());
		resultVO.setSearchWrd(deptJobVO.getSearchWrd());
		resultVO.setSearchDeptId(deptJobVO.getSearchDeptId());
		resultVO.setSearchDeptJobBxId(deptJobVO.getSearchDeptJobBxId());
		resultVO.setPageIndex(deptJobVO.getPageIndex());
        model.addAttribute("deptJobVO", resultVO);

		return "egovframework/com/cop/smt/djm/EgovDeptJobUpdt";
	}

	/**
	 * 부서업무 정보를 조회한다.
	 * @param DeptJobVO
	 * @return  String
	 *
	 * @param deptJobVO
	 */
	@RequestMapping("/cop/smt/djm/selectDeptJob.do")
	public String selectDeptJob(@ModelAttribute("deptJobVO") DeptJobVO deptJobVO, ModelMap model) throws Exception{
		DeptJob deptJob = deptJobService.selectDeptJob(deptJobVO);
		model.addAttribute("deptJob", deptJob);

		/*
    	 * 공통코드
    	 * 우선순위 조회
    	 */
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM059");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("priort", listComCode);

		return "egovframework/com/cop/smt/djm/EgovDeptJobDetail";
	}


	/**
	 * 부서업무 정보를 수정한다.
	 * @param DeptJob
	 * @return  String
	 *
	 * @param deptJob
	 */
	@RequestMapping("/cop/smt/djm/updateDeptJob.do")
	public String updateDeptJob(final MultipartHttpServletRequest multiRequest, @RequestParam Map<String, Object> commandMap, @ModelAttribute("deptJobVO") DeptJobVO deptJobVO, BindingResult bindingResult, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

		beanValidator.validate(deptJobVO, bindingResult);
		if (bindingResult.hasErrors()) {
			DeptJob deptJob = deptJobService.selectDeptJob(deptJobVO);
		    model.addAttribute("deptJob", deptJob);
		    return "egovframework/com/cop/smt/djm/EgovDeptJobUpdt";
		}

		if (isAuthenticated) {
			/* *****************************************************************
        	// 첨부파일 관련 ID 생성 start....
			****************************************************************** */
    		String _atchFileId = deptJobVO.getAtchFileId();

    		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		final List<MultipartFile> files = multiRequest.getFiles("file_1");

    		if(!files.isEmpty()){
    			String atchFileAt = commandMap.get("atchFileAt") == null ? "" : (String)commandMap.get("atchFileAt");
    			if("N".equals(atchFileAt)){
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", 0, _atchFileId, "");
    				_atchFileId = fileMngService.insertFileInfs(_result);

    				// 첨부파일 ID 셋팅
    				deptJobVO.setAtchFileId(_atchFileId);    	// 첨부파일 ID

    			}else{
    				FileVO fvo = new FileVO();
    				fvo.setAtchFileId(_atchFileId);
    				int _cnt = fileMngService.getMaxFileSN(fvo);
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", _cnt, _atchFileId, "");
    				fileMngService.updateFileInfs(_result);
    			}
    		}

			deptJobVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			deptJobService.updateDeptJob(deptJobVO);
		}

		return "forward:/cop/smt/djm/selectDeptJobList.do";
	}

	/**
	 * 부서업무 정보를 등록한다.
	 * @param DeptJob
	 * @return  String
	 *
	 * @param deptJob
	 */
	@RequestMapping("/cop/smt/djm/insertDeptJob.do")
	public String insertDeptJob(final MultipartHttpServletRequest multiRequest, @ModelAttribute("deptJobVO") DeptJobVO deptJobVO, BindingResult bindingResult, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/cop/smt/djm/EgovDeptJobRegist";

		//서버  validate 체크
        beanValidator.validate(deptJobVO, bindingResult);
		if(bindingResult.hasErrors()){
			
			// 파일업로드 제한
	    	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
	    	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

	        model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
	        model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

			return sLocationUrl;
		}

		// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> _result = null;
		String _atchFileId = "";

		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if(!files.isEmpty()){
			_result = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
			_atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		}

    	// 리턴받은 첨부파일ID를 셋팅한다..
		deptJobVO.setAtchFileId(_atchFileId);			// 첨부파일 ID

		//아이디 설정
		deptJobVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		deptJobVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		deptJobService.insertDeptJob(deptJobVO);
    	sLocationUrl = "forward:/cop/smt/djm/selectDeptJobList.do";

        return sLocationUrl;
	}

	/**
	 * 부서업무 정보를 삭제한다.
	 * @param DeptJob
	 * @return  String
	 *
	 * @param deptJob
	 */
	@RequestMapping("/cop/smt/djm/deleteDeptJob.do")
	public String deleteDeptJob(@ModelAttribute("deptJobVO") DeptJob deptJob, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	// 첨부파일 삭제를 위한 ID 생성 start....
		String _atchFileId = deptJob.getAtchFileId();

		// 첨부파일을 삭제하기 위한  Vo
    	FileVO fvo = new FileVO();
    	fvo.setAtchFileId(_atchFileId);

    	fileMngService.deleteAllFileInf(fvo);
    	// 첨부파일 삭제 End.............

    	deptJobService.deleteDeptJob(deptJob);
		return "forward:/cop/smt/djm/selectDeptJobList.do";
	}

}