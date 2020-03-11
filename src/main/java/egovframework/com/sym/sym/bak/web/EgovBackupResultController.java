package egovframework.com.sym.sym.bak.web;
import java.util.List;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.sym.bak.service.BackupResult;
import egovframework.com.sym.sym.bak.service.EgovBackupResultService;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 백업결과관리에 대한 controller 클래스
 *
 * 백업결과관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * 백업결과관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class EgovBackupResultController {

	/** egovBackupResultService */
	@Resource(name = "egovBackupResultService")
	private EgovBackupResultService egovBackupResultService;

	/* Property 서비스 */
    @Resource(name="propertiesService")
    private EgovPropertyService propertyService;

    /* 메세지 서비스 */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

	/** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(EgovBackupResultController.class);

	/**
	 * 백업결과을 삭제한다.
	 * @return 리턴URL
	 *
	 * @param backupResult 삭제대상 백업결과model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
    @RequestMapping("/sym/sym/bak/deleteBackupResult.do")
	public String deleteBackupResult(BackupResult backupResult, ModelMap model)
	  throws Exception{
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	egovBackupResultService.deleteBackupResult(backupResult);

    	return "forward:/sym/sym/bak/getBackupResultList.do";
	}


	/**
	 * 백업결과정보을 상세조회한다.
	 * @return 리턴URL
	 *
	 * @param backupResult 조회대상 백업결과model
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@RequestMapping("/sym/sym/bak/getBackupResult.do")
	public String selectBackupResult(@ModelAttribute("searchVO")BackupResult backupResult, ModelMap model)
	  throws Exception{
		LOGGER.debug(" 조회조건 : {}", backupResult);
		BackupResult result = egovBackupResultService.selectBackupResult(backupResult);
		model.addAttribute("resultInfo", result);
		LOGGER.debug(" 결과값 : {}", result);

		return "egovframework/com/sym/sym/bak/EgovBackupResultDetail";
	}

	/**
	 * 백업결과 목록을 조회한다.
	 * @return 리턴URL
	 *
	 * @param searchVO 목록조회조건VO
	 * @param model		ModelMap
	 * @exception Exception Exception
	 */
	@SuppressWarnings("unchecked")
	@IncludedInfo(name="백업결과관리", order = 1151 ,gid = 60)
	@RequestMapping("/sym/sym/bak/getBackupResultList.do")
	public String selectBackupResultList(@ModelAttribute("searchVO")BackupResult searchVO, ModelMap model)
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

		List<BackupResult> resultList = (List<BackupResult>) egovBackupResultService.selectBackupResultList(searchVO);
		int totCnt = egovBackupResultService.selectBackupResultListCnt(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/sym/bak/EgovBackupResultList";
	}


}