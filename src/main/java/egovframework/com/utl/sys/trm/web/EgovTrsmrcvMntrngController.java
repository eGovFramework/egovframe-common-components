package egovframework.com.utl.sys.trm.web;
import java.util.List;

import javax.annotation.Resource;

import java.sql.SQLException;
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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.trm.service.CntcVO;
import egovframework.com.utl.sys.trm.service.EgovTrsmrcvMntrngService;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrng;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrngLog;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 송수신모니터링에 대한 controller 클래스를 정의한다.
 *
 * 송수신모니터링관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * 송수신모니터링관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2017-02-14   이정은            시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *  2019.12.06   신용호            KISA 보안약점 조치 (부적절한 예외처리)
 *  
 * </pre>
 */

@Controller
public class EgovTrsmrcvMntrngController {

	@Resource(name = "egovTrsmrcvMntrngService")
	private EgovTrsmrcvMntrngService egovTrsmrcvMntrngService;

    @Resource(name="propertiesService")
    private EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovTrsmrcvMntrngController.class);

	/**
	 * 송수신모니터링을 삭제한다.
	 * @return 리턴URL
	 *
	 * @param trsmrcvMntrng 삭제대상 송수신모니터링model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
    @RequestMapping("/utl/sys/trm/deleteTrsmrcvMntrng.do")
	public String deleteTrsmrcvMntrng(@ModelAttribute("searchVO") TrsmrcvMntrng trsmrcvMntrng, ModelMap model)
	  throws Exception{
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		egovTrsmrcvMntrngService.deleteTrsmrcvMntrng(trsmrcvMntrng);

    	return "forward:/utl/sys/trm/getTrsmrcvMntrngList.do";
	}

	/**
	 * 송수신모니터링을 등록한다.
	 * @return 리턴URL
	 *
	 * @param trsmrcvMntrng 등록대상 송수신모니터링model
	 * @param bindingResult	BindingResult
	 * @param model			ModelMap
	 * @exception Exception Exception
	 */
    @RequestMapping("/utl/sys/trm/addTrsmrcvMntrng.do")
	public String insertTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng, BindingResult bindingResult, ModelMap model)
	  throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        beanValidator.validate(trsmrcvMntrng, bindingResult);
        checkDuplication(trsmrcvMntrng, bindingResult);
    	if (bindingResult.hasErrors()){
    		model.addAttribute("trsmrcvMntrng", trsmrcvMntrng);
    		return "egovframework/com/utl/sys/trm/EgovTrsmrcvMntrngRegist";
		}else{
    		//아이디 설정
			trsmrcvMntrng.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

			egovTrsmrcvMntrngService.insertTrsmrcvMntrng(trsmrcvMntrng);
	        //Exception 없이 진행시 등록성공메시지
	        model.addAttribute("resultMsg", "success.common.insert");
		}
    	return "forward:/utl/sys/trm/getTrsmrcvMntrngList.do";
	}

	/**
	 * 송수신모니터링정보을 상세조회한다.
	 * @return 리턴URL
	 *
	 * @param trsmrcvMntrng 조회대상 송수신모니터링model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
    @RequestMapping("/utl/sys/trm/getTrsmrcvMntrng.do")
	public String selectTrsmrcvMntrng(@ModelAttribute("searchVO") TrsmrcvMntrng trsmrcvMntrng, ModelMap model)
	  throws Exception{
    	LOGGER.debug(" 조회조건 : {}", trsmrcvMntrng);
		TrsmrcvMntrng result = egovTrsmrcvMntrngService.selectTrsmrcvMntrng(trsmrcvMntrng);
		model.addAttribute("resultInfo", result);
		LOGGER.debug(" 결과값 : {}", result);

      return "egovframework/com/utl/sys/trm/EgovTrsmrcvMntrngDetail";

	}

	/**
	 * 송수신모니터링로그정보을 상세조회한다.
	 * @return 리턴URL
	 *
	 * @param trsmrcvMntrngLog 조회대상 송수신모니터링로그model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
    @RequestMapping("/utl/sys/trm/getTrsmrcvMntrngLog.do")
	public String selectTrsmrcvMntrngLog(@ModelAttribute("searchVO") TrsmrcvMntrngLog trsmrcvMntrngLog, ModelMap model)
	  throws Exception{
    	LOGGER.debug(" 조회조건 : {}", trsmrcvMntrngLog);
		TrsmrcvMntrngLog result = egovTrsmrcvMntrngService.selectTrsmrcvMntrngLog(trsmrcvMntrngLog);
		model.addAttribute("resultInfo", result);
		LOGGER.debug(" 결과값 : {}", result);

      return "egovframework/com/utl/sys/trm/EgovTrsmrcvMntrngLogDetail";

	}

	/**
	 * 등록화면을 위한 송수신모니터링정보을 조회한다.
	 * @return 리턴URL
	 *
	 * @param trsmrcvMntrng 조회대상 송수신모니터링model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/utl/sys/trm/getTrsmrcvMntrngForRegist.do")
	public String selectTrsmrcvMntrngForRegist(@ModelAttribute("searchVO")TrsmrcvMntrng trsmrcvMntrng, ModelMap model)
	  throws Exception{
        model.addAttribute("trsmrcvMntrng", trsmrcvMntrng);

        return "egovframework/com/utl/sys/trm/EgovTrsmrcvMntrngRegist";
	}

	/**
	 * 수정화면을 위한 송수신모니터링정보을 조회한다.
	 * @return 리턴URL
	 *
	 * @param trsmrcvMntrng 조회대상 송수신모니터링model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/utl/sys/trm/getTrsmrcvMntrngForUpdate.do")
	public String selectTrsmrcvMntrngForUpdate(@ModelAttribute("searchVO") TrsmrcvMntrng trsmrcvMntrng, ModelMap model)
	  throws Exception{

        // DB서비스모니터링 정보 조회.
		LOGGER.debug(" 조회조건 : {}", trsmrcvMntrng);
        TrsmrcvMntrng result = egovTrsmrcvMntrngService.selectTrsmrcvMntrng(trsmrcvMntrng);
        model.addAttribute("trsmrcvMntrng", result);
        LOGGER.debug(" 결과값 : {}", result);

      return "egovframework/com/utl/sys/trm/EgovTrsmrcvMntrngUpdt";

	}

	/**
	 * 송수신모니터링 목록을 조회한다.
	 * @return 리턴URL
	 *
	 * @param searchVO 목록조회조건VO
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unused")
	@IncludedInfo(name="송수신모니터링",order = 2080 ,gid = 90)
	@RequestMapping("/utl/sys/trm/getTrsmrcvMntrngList.do")
	public String selectTrsmrcvMntrngList(@ModelAttribute("searchVO") TrsmrcvMntrng searchVO, ModelMap model)
	  throws Exception{
		LOGGER.debug(" 조회조건 : {}", searchVO);

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();


		searchVO.setPageUnit(propertyService.getInt("pageUnit"));
		searchVO.setPageSize(propertyService.getInt("pageSize")/2);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<TrsmrcvMntrng> resultList = egovTrsmrcvMntrngService.selectTrsmrcvMntrngList(searchVO);
		int totCnt = egovTrsmrcvMntrngService.selectTrsmrcvMntrngListCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/trm/EgovTrsmrcvMntrngList";
	}

	/**
	 * 송수신모니터링로그 목록을 조회한다.
	 * @return 리턴URL
	 *
	 * @param searchVO 목록조회조건VO
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/utl/sys/trm/getTrsmrcvMntrngLogList.do")
	public String selectTrsmrcvMntrngLogList(@ModelAttribute("searchVO") TrsmrcvMntrngLog searchVO, ModelMap model)
	  throws Exception{
		LOGGER.debug(" 조회조건 : {}", searchVO);

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();


		searchVO.setPageUnit(propertyService.getInt("pageUnit"));
		searchVO.setPageSize(propertyService.getInt("pageSize")/2);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<TrsmrcvMntrngLog> resultList = egovTrsmrcvMntrngService.selectTrsmrcvMntrngLogList(searchVO);
		int totCnt = egovTrsmrcvMntrngService.selectTrsmrcvMntrngLogListCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/trm/EgovTrsmrcvMntrngLogList";
	}

	/**
	 * 송수신모니터링을 수정한다.
	 * @return 리턴URL
	 *
	 * @param trsmrcvMntrng 수정대상 송수신모니터링model
	 * @param bindingResult		BindingResult
	 * @param model				ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/utl/sys/trm/updateTrsmrcvMntrng.do")
	public String updateTrsmrcvMntrng(@ModelAttribute("searchVO") TrsmrcvMntrng trsmrcvMntrng, BindingResult bindingResult, ModelMap model)
	  throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(trsmrcvMntrng, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("trsmrcvMntrng", trsmrcvMntrng);
		    return "egovframework/com/utl/sys/trm/EgovTrsmrcvMntrngUpdt";
		}

		// 정보 업데이트
		trsmrcvMntrng.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	    egovTrsmrcvMntrngService.updateTrsmrcvMntrng(trsmrcvMntrng);

		return "forward:/utl/sys/trm/getTrsmrcvMntrngList.do";
	}

	/**
	 * 연계정보 목록을 조회한다.
	 * @return 리턴URL
	 *
	 * @param searchVO 목록조회조건VO
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/utl/sys/trm/getCntcList.do")
	public String selectCntcList(@ModelAttribute("searchVO") CntcVO searchVO, ModelMap model)
	  throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		searchVO.setPageUnit(propertyService.getInt("pageUnit"));
		searchVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<CntcVO> resultList = egovTrsmrcvMntrngService.selectCntcList(searchVO);
		int totCnt = egovTrsmrcvMntrngService.selectCntcListCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/trm/EgovCntcListPopup";
	}
	/**
	 * 연계정보 조회팝업을 실행한다.
	 * @return 리턴URL
	 *
	 * @param searchVO 목록조회조건VO
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/utl/sys/trm/getCntcListPopup.do")
	public String openPopupWindow(@ModelAttribute("searchVO") CntcVO searchVO, ModelMap model)
	  throws Exception{
		return "egovframework/com/utl/sys/trm/EgovCntcListPopupFrame";
	}

	private void checkDuplication(TrsmrcvMntrng obj, Errors errors) {
		TrsmrcvMntrng trsmrcvMntrng = obj;
		String cntcId = trsmrcvMntrng.getCntcId();

		TrsmrcvMntrng exist = null;

		try {
			exist = egovTrsmrcvMntrngService.selectTrsmrcvMntrng(trsmrcvMntrng);
			if (exist != null) {
				errors.rejectValue("cntcId", "errors.cntcId", new Object [] { cntcId },
			    "모니터링대상으로 연계ID {0}이 이미 존재합니다.");
				return ;
			}
		} catch (SQLException  se) {
			errors.rejectValue("cntcId", "errors.cntcId", new Object [] { cntcId },
				    " 모니터링대상으로 연계ID {0}을 중복체크중 시스템에러가 발생했습니다. ");
					return ;
		} catch (Exception  se) {
			errors.rejectValue("cntcId", "errors.cntcId", new Object [] { cntcId },
		    " 모니터링대상으로 연계ID {0}을 중복체크중 시스템에러가 발생했습니다. ");
			return ;
		}
	}
}