package egovframework.com.utl.sys.dbm.web;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.dbm.service.DbMntrng;
import egovframework.com.utl.sys.dbm.service.DbMntrngLog;
import egovframework.com.utl.sys.dbm.service.EgovDbMntrngService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * DB서비스모니터링관리에 대한 controller 클래스를 정의한다.
 *
 * DB서비스모니터링관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * DB서비스모니터링관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 김진만
 * @since 2010.06.21
 * @version 1.0
 * @updated 21-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *  수정일                수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2010.06.21   김진만            최초 생성
 *  2011.08.26	 정진오             IncludedInfo annotation 추가
 *  2019-12-06   신용호            KISA 보안약점 조치 (부적절한 예외처리)
 *  
 * </pre>
 */

@Controller
public class EgovDbMntrngController {

	/** egovDbMntrngService */
	@Resource(name = "egovDbMntrngService")
	private EgovDbMntrngService egovDbMntrngService;

    @Resource(name="propertiesService")
    private EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** cmmUseService */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

	/** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(EgovDbMntrngController.class);

	/**
	 * DB서비스모니터링을 삭제한다.
	 * @return 리턴URL
	 *
	 * @param dbMntrng 삭제대상 DB서비스모니터링model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
    @RequestMapping("/utl/sys/dbm/deleteDbMntrng.do")
	public String deleteDbMntrng(DbMntrng dbMntrng, ModelMap model)
	  throws Exception{
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		egovDbMntrngService.deleteDbMntrng(dbMntrng);

    	return "forward:/utl/sys/dbm/getDbMntrngList.do";
	}

	/**
	 * DB서비스모니터링을 등록한다.
	 * @return 리턴URL
	 *
	 * @param dbMntrng 등록대상 DB서비스모니터링model
	 * @param bindingResult	BindingResult
	 * @param model			ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/utl/sys/dbm/addDbMntrng.do")
	public String insertDbMntrng(DbMntrng dbMntrng, BindingResult bindingResult, ModelMap model)
	  throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        beanValidator.validate(dbMntrng, bindingResult);
        checkDuplication(dbMntrng, bindingResult);
    	if (bindingResult.hasErrors()){
    		referenceData(model);
    		model.addAttribute("dbMntrng", dbMntrng);
    		return "egovframework/com/utl/sys/dbm/EgovDbMntrngRegist";
		}else{
    		//아이디 설정
			dbMntrng.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			dbMntrng.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

			egovDbMntrngService.insertDbMntrng(dbMntrng);
	        //Exception 없이 진행시 등록성공메시지
	        model.addAttribute("resultMsg", "success.common.insert");
		}
    	return "forward:/utl/sys/dbm/getDbMntrngList.do";
	}

	/**
	 * DB서비스모니터링정보을 상세조회한다.
	 * @return 리턴URL
	 *
	 * @param dbMntrng 조회대상 DB서비스모니터링model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/utl/sys/dbm/getDbMntrng.do")
	public String selectDbMntrng(@ModelAttribute("searchVO")DbMntrng dbMntrng, ModelMap model)
	  throws Exception{
		LOGGER.debug(" 조회조건 : {}", dbMntrng);
        DbMntrng result = egovDbMntrngService.selectDbMntrng(dbMntrng);
        model.addAttribute("resultInfo", result);
        LOGGER.debug(" 결과값 : {}", result);

        return "egovframework/com/utl/sys/dbm/EgovDbMntrngDetail";
	}

	/**
	 * DB서비스모니터링로그정보을 상세조회한다.
	 * @return 리턴URL
	 *
	 * @param dbMntrng 조회대상 DB서비스모니터링로그model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */

	@RequestMapping("/utl/sys/dbm/getDbMntrngLog.do")
	public String selectDbMntrngLog(@ModelAttribute("searchVO")DbMntrngLog dbMntrngLog, ModelMap model)
	  throws Exception{
		LOGGER.debug(" 조회조건 : {}", dbMntrngLog);
        DbMntrngLog result = egovDbMntrngService.selectDbMntrngLog(dbMntrngLog);
        model.addAttribute("resultInfo", result);
        LOGGER.debug(" 결과값 : {}", result);

        return "egovframework/com/utl/sys/dbm/EgovDbMntrngLogDetail";
	}

	/**
	 * 등록화면을 위한 DB서비스모니터링정보을 조회한다.
	 * @return 리턴URL
	 *
	 * @param dbMntrng 조회대상 DB서비스모니터링model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/utl/sys/dbm/getDbMntrngForRegist.do")
	public String selectDbMntrngForRegist(@ModelAttribute("searchVO")DbMntrng dbMntrng, ModelMap model)
	  throws Exception{
        referenceData(model);
        model.addAttribute("dbMntrng", dbMntrng);

        return "egovframework/com/utl/sys/dbm/EgovDbMntrngRegist";
	}

	/**
	 * Reference Data 를 설정한다.
	 * @param model   화면용spring Model객체
	 * @throws Exception
	 */
	private void referenceData(ModelMap model) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

        //DBMS종류코드목록을 코드정보로부터 조회
        vo.setCodeId("COM048");
        List<CmmnDetailCode> dbmsKindList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("dbmsKindList",      dbmsKindList);     //DBMS종류코드목록
	}

	/**
	 * 수정화면을 위한 DB서비스모니터링정보을 조회한다.
	 * @return 리턴URL
	 *
	 * @param dbMntrng 조회대상 DB서비스모니터링model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/utl/sys/dbm/getDbMntrngForUpdate.do")
	public String selectDbMntrngForUpdate(@ModelAttribute("searchVO") DbMntrng dbMntrng, ModelMap model)
	  throws Exception{
        referenceData(model);

        // DB서비스모니터링 정보 조회.
        LOGGER.debug(" 조회조건 : {}", dbMntrng);
        DbMntrng result = egovDbMntrngService.selectDbMntrng(dbMntrng);
        model.addAttribute("dbMntrng", result);
        LOGGER.debug(" 결과값 : {}", result);

      return "egovframework/com/utl/sys/dbm/EgovDbMntrngUpdt";
	}

	/**
	 * DB서비스모니터링 목록을 조회한다.
	 * @return 리턴URL
	 *
	 * @param searchVO 목록조회조건VO
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unused")
	@IncludedInfo(name="DB서비스모니터링", order = 2090 ,gid = 90)
	@RequestMapping("/utl/sys/dbm/getDbMntrngList.do")
	public String selectDbMntrngList(@ModelAttribute("searchVO") DbMntrng searchVO, ModelMap model)
	  throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//searchVO.setUniqId(user.getUniqId());
		searchVO.setPageUnit(propertyService.getInt("pageUnit"));
		searchVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<DbMntrng> resultList = egovDbMntrngService.selectDbMntrngList(searchVO);
		int totCnt = egovDbMntrngService.selectDbMntrngListCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/dbm/EgovDbMntrngList";
	}

	/**
	 * DB서비스모니터링로그 목록을 조회한다.
	 * @return 리턴URL
	 *
	 * @param searchVO 목록조회조건VO
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/utl/sys/dbm/getDbMntrngLogList.do")
	public String selectDbMntrngLogList(@ModelAttribute("searchVO") DbMntrngLog searchVO, ModelMap model)
	  throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//searchVO.setUniqId(user.getUniqId());
        // DB서비스모니터링 정보 조회.
		LOGGER.debug(" 조회조건 : {}", searchVO);


		searchVO.setPageUnit(propertyService.getInt("pageUnit"));
		searchVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<DbMntrngLog> resultList = egovDbMntrngService.selectDbMntrngLogList(searchVO);
		int totCnt = egovDbMntrngService.selectDbMntrngLogListCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/dbm/EgovDbMntrngLogList";
	}

	/**
	 * DB서비스모니터링을 수정한다.
	 * @return 리턴URL
	 *
	 * @param dbMntrng 수정대상 DB서비스모니터링model
	 * @param bindingResult		BindingResult
	 * @param model				ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/utl/sys/dbm/updateDbMntrng.do")
	public String updateDbMntrng(DbMntrng dbMntrng, BindingResult bindingResult, ModelMap model)
	  throws Exception{

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(dbMntrng, bindingResult);
		if (bindingResult.hasErrors()) {
			referenceData(model);
			model.addAttribute("dbMntrng", dbMntrng);
		    return "egovframework/com/utl/sys/dbm/EgovDbMntrngUpdt";
		}

		// 정보 업데이트
		dbMntrng.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	    egovDbMntrngService.updateDbMntrng(dbMntrng);

		return "forward:/utl/sys/dbm/getDbMntrngList.do";
	}

	private void checkDuplication(DbMntrng obj, Errors errors) {
		DbMntrng dbMntrng = obj;
		String dataSourcNm = dbMntrng.getDataSourcNm();

		DbMntrng exist = null;

		try {
			exist = egovDbMntrngService.selectDbMntrng(dbMntrng);
			if (exist != null) {
				errors.rejectValue("dataSourcNm", "errors.dataSourcNm", new Object [] { dataSourcNm },
			    "모니터링대상으로 데이타소스명 {0}이 이미 존재합니다.");
				return ;
			}
		} catch (SQLException  se) {
			errors.rejectValue("dataSourcNm", "errors.dataSourcNm", new Object [] { dataSourcNm },
				    " 모니터링대상으로 데이타소스명 {0}을 중복체크중 시스템에러가 발생했습니다. ");
					return ;
		} catch (Exception  se) {
			errors.rejectValue("dataSourcNm", "errors.dataSourcNm", new Object [] { dataSourcNm },
		    " 모니터링대상으로 데이타소스명 {0}을 중복체크중 시스템에러가 발생했습니다. ");
			return ;
		}

	}

}