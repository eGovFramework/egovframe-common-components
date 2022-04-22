package egovframework.com.sym.sym.bak.web;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.sym.bak.service.BackupOpert;
import egovframework.com.sym.sym.bak.service.BackupScheduler;
import egovframework.com.sym.sym.bak.service.EgovBackupOpertService;
import egovframework.com.sym.sym.bak.validation.BackupOpertValidator;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 백업작업관리에 대한 controller 클래스를 정의한다.
 *
 * 백업작업관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * 백업작업관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 김진만
 * @since 2010.06.21
 * @version 1.0
 * @updated 21-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.21   김진만     최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 * </pre>
 */

@Controller
public class EgovBackupOpertController {

	/** egovBackupOpertService */
	@Resource(name = "egovBackupOpertService")
	private EgovBackupOpertService egovBackupOpertService;

	/* Property 서비스 */
    @Resource(name="propertiesService")
    private EgovPropertyService propertyService;

    /* 메세지 서비스 */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    /* common  validator */
    @Autowired
    private DefaultBeanValidator beanValidator;

    /* backupOpert bean validator */
    @Resource(name="backupOpertValidator")
    private BackupOpertValidator backupOpertValidator;

    /** ID Generation */
	@Resource(name="egovBackupOpertIdGnrService")
	private EgovIdGnrService idgenService;

    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

	/** 백업스케줄러 서비스 */
	@Resource(name = "backupScheduler")
	private BackupScheduler backupScheduler;

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBackupOpertController.class);

	/**
	 * 백업작업을 삭제한다.
	 * @return 리턴URL
	 *
	 * @param backupOpert 삭제대상 백업작업model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
    @RequestMapping("/sym/sym/bak/deleteBackupOpert.do")
	public String deleteBackupOpert(BackupOpert backupOpert, ModelMap model)
	  throws Exception{
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		// 백업스케줄러에 스케줄정보반영
		backupScheduler.deleteBackupOpert(backupOpert);

    	egovBackupOpertService.deleteBackupOpert(backupOpert);

    	return "forward:/sym/sym/bak/getBackupOpertList.do";
	}

	/**
	 * 백업작업을 등록한다.
	 * @return 리턴URL
	 *
	 * @param backupOpert 등록대상 백업작업model
	 * @param bindingResult	BindingResult
	 * @param model			ModelMap
	 * @exception Exception Exception
	 */
    @RequestMapping("/sym/sym/bak/addBackupOpert.do")
	public String insertBackupOpert(BackupOpert backupOpert, BindingResult bindingResult, ModelMap model)
	  throws Exception{
    	LOGGER.debug(" 인서트 대상정보 : {}", backupOpert);

	  	// 0. Spring Security 사용자권한 처리
	  	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	  	if(!isAuthenticated) {
	  		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	      	return "egovframework/com/uat/uia/EgovLoginUsr";
	  	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(backupOpert, bindingResult);
		backupOpertValidator.validate(backupOpert, bindingResult);
	  	if (bindingResult.hasErrors()){
	  		referenceData(model);
	  		return "egovframework/com/sym/sym/bak/EgovBackupOpertRegist";
			}else{
				backupOpert.setBackupOpertId(idgenService.getNextStringId());
				//아이디 설정
				backupOpert.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
				backupOpert.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

				egovBackupOpertService.insertBackupOpert(backupOpert);

				// 배치스케줄러에 스케줄정보반영
				BackupOpert target = egovBackupOpertService.selectBackupOpert(backupOpert);
				backupScheduler.insertBackupOpert(target);

		        //Exception 없이 진행시 등록성공메시지
		        model.addAttribute("resultMsg", "success.common.insert");
			}
	  	return "forward:/sym/sym/bak/getBackupOpertList.do";
	}

	/**
	 * 백업작업정보을 상세조회한다.
	 * @return 리턴URL
	 *
	 * @param backupOpert 조회대상 백업작업model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
    @RequestMapping("/sym/sym/bak/getBackupOpert.do")
	public String selectBackupOpert(@ModelAttribute("searchVO")BackupOpert backupOpert, ModelMap model)
	  throws Exception{
    	LOGGER.debug(" 조회조건 : {}", backupOpert);
		BackupOpert result = egovBackupOpertService.selectBackupOpert(backupOpert);
		model.addAttribute("resultInfo", result);
		LOGGER.debug(" 결과값 : {}", result);

		return "egovframework/com/sym/sym/bak/EgovBackupOpertDetail";
	}

	/**
	 * 등록화면을 위한 백업작업정보을 조회한다.
	 * @return 리턴URL
	 *
	 * @param backupSchdul 조회대상 백업작업model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/sym/bak/getBackupOpertForRegist.do")
	public String selectBackupOpertForRegist(@ModelAttribute("searchVO")BackupOpert backupOpert, ModelMap model)
	  throws Exception{
		referenceData(model);

        model.addAttribute("backupOpert", backupOpert);

        return "egovframework/com/sym/sym/bak/EgovBackupOpertRegist";
	}

	/**
	 * 수정화면을 위한 백업작업정보을 조회한다.
	 * @return 리턴URL
	 *
	 * @param backupOpert 조회대상 백업작업model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/sym/bak/getBackupOpertForUpdate.do")
	public String selectBackupOpertForUpdate(@ModelAttribute("searchVO")BackupOpert backupOpert, ModelMap model)
	  throws Exception{
		referenceData(model);

		LOGGER.debug(" 조회조건 : {}", backupOpert);
		BackupOpert result = egovBackupOpertService.selectBackupOpert(backupOpert);
		model.addAttribute("backupOpert", result);
		LOGGER.debug(" 결과값 : {}", result);

        return "egovframework/com/sym/sym/bak/EgovBackupOpertUpdt";
	}

	/**
	 * 백업작업 목록을 조회한다.
	 * @return 리턴URL
	 *
	 * @param searchVO 목록조회조건VO
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unchecked")
	@IncludedInfo(name="백업관리", order = 1150 ,gid = 60)
	@RequestMapping("/sym/sym/bak/getBackupOpertList.do")
	public String selectBackupOpertList(@ModelAttribute("searchVO")BackupOpert searchVO, ModelMap model)
	  throws Exception{
		searchVO.setPageUnit(propertyService.getInt("pageUnit"));
		searchVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<BackupOpert> resultList = (List<BackupOpert>) egovBackupOpertService.selectBackupOpertList(searchVO);
		int totCnt = egovBackupOpertService.selectBackupOpertListCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/sym/bak/EgovBackupOpertList";
	}

	/**
	 * 백업작업을 수정한다.
	 * @return 리턴URL
	 *
	 * @param backupOpert 수정대상 백업작업model
	 * @param bindingResult		BindingResult
	 * @param model				ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/sym/bak/updateBackupOpert.do")
	public String updateBackupOpert(BackupOpert backupOpert, BindingResult bindingResult, ModelMap model)
	  throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(backupOpert, bindingResult);
		backupOpertValidator.validate(backupOpert, bindingResult);
		if (bindingResult.hasErrors()) {
			referenceData(model);
			model.addAttribute("batchSchdul", backupOpert);
		    return "egovframework/com/sym/sym/bak/EgovBackupOpertUpdt";
		}

		// 정보 업데이트
		backupOpert.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	    egovBackupOpertService.updateBackupOpert(backupOpert);

		// 백업스케줄러에 스케줄정보반영
	    BackupOpert target = egovBackupOpertService.selectBackupOpert(backupOpert);
		backupScheduler.updateBackupOpert(target);


		return "forward:/sym/sym/bak/getBackupOpertList.do";
	}

	/**
	 * Reference Data 를 설정한다.
	 * @param model   화면용spring Model객체
	 * @throws Exception
	 */
	private void referenceData(ModelMap model) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
        //실행주기구분 코드목록을 코드정보로부터 조회
        vo.setCodeId("COM047");
        List<CmmnDetailCode> executCycleList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("executCycleList",      executCycleList);
        //요일구분코드목록을 코드정보로부터 조회
        vo.setCodeId("COM074");
        List<CmmnDetailCode> executSchdulDfkSeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("executSchdulDfkSeList",      executSchdulDfkSeList);
        //압축구분코드목록을 코드정보로부터 조회
        vo.setCodeId("COM049");
        List<CmmnDetailCode> cmprsSeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("cmprsSeList",      cmprsSeList);

        // 실행스케줄 시, 분, 초 값 설정.
    	Map<String, String> executSchdulHourList =new LinkedHashMap<String, String>();
    	for (int i = 0; i < 24; i++) {
    		if (i < 10) {
    			executSchdulHourList.put("0" + Integer.toString(i), "0" + Integer.toString(i));
    		} else {
    			executSchdulHourList.put(Integer.toString(i), Integer.toString(i));
    		}
    	}
    	model.addAttribute("executSchdulHourList",executSchdulHourList);
    	Map<String, String> executSchdulMntList =new LinkedHashMap<String, String>();
    	for (int i = 0; i < 60; i++) {
    		if (i < 10) {
    			executSchdulMntList.put("0" + Integer.toString(i), "0" + Integer.toString(i));
    		} else {
    			executSchdulMntList.put(Integer.toString(i), Integer.toString(i));
    		}
    	}
    	model.addAttribute("executSchdulMntList",executSchdulMntList);
    	Map<String, String> executSchdulSecndList =new LinkedHashMap<String, String>();
    	for (int i = 0; i < 60; i++) {
    		if (i < 10) {
    			executSchdulSecndList.put("0" + Integer.toString(i), "0" + Integer.toString(i));
    		} else {
    			executSchdulSecndList.put(Integer.toString(i), Integer.toString(i));
    		}
    	}
    	model.addAttribute("executSchdulSecndList",executSchdulSecndList);
	}


}