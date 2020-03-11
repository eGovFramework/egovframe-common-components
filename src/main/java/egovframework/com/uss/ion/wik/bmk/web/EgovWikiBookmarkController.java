package egovframework.com.uss.ion.wik.bmk.web;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.wik.bmk.service.EgovWikiBookmarkService;
import egovframework.com.uss.ion.wik.bmk.service.WikiBookmark;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 위키북마크를 처리하는 Controller Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.10.20
 * @version 1.0
 * @see
 * <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.10.20  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */


@Controller
public class EgovWikiBookmarkController {

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "egovWikiBookmarkService")
    private EgovWikiBookmarkService egovWikiBookmarkService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovWikiBookmarkController.class);
    
    /**
     * 위키북마크 목록을 조회한다.
     * @param searchVO -위키북마크 model
     * @param searchVO -위키북마크 model
     * @param commandMap -Request Variable
     * @param model -Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @IncludedInfo(name="Wiki기능", order = 810 ,gid = 50)
    @RequestMapping(value = "/uss/ion/wik/bmk/listWikiBookmark.do")
    public String EgovWikiBookmarkList(
    		@ModelAttribute("searchVO") WikiBookmark searchVO,
    		WikiBookmark wikiBookmark,
    		@RequestParam Map<?, ?> commandMap,
    		@RequestParam(value="checkList", required=false) List<String> checkList,
            ModelMap model) throws Exception {

    	//변수 설정
    	String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "egovframework/com/uat/uia/EgovLoginUsr";
	    }

        //로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        //삭제 모드로 실행시
        if(sCmd.equals("del")){
	              	
        	for(String checkData : checkList) {
        		LOGGER.debug("===>>> checkData = "+checkData);   
                wikiBookmark.setWikiBkmkId(checkData);
	            egovWikiBookmarkService.deleteWikiBookmark(wikiBookmark);
            }

	        //페이지 인텍스 설정
	        searchVO.setPageIndex(1);

	        return "redirect:/uss/ion/wik/bmk/listWikiBookmark.do";
        }


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

        searchVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        List<?> reusltList = egovWikiBookmarkService.selectWikiBookmarkList(searchVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        int totCnt = egovWikiBookmarkService.selectWikiBookmarkListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

    	return "egovframework/com/uss/ion/wik/bmk/EgovWikiBookmarkList";
	}

    /**
     * 위키북마크를 등록 한다.
     * @param wikiBookmark -위키북마크 model
     * @param model -Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @RequestMapping(value = "/uss/ion/wik/bmk/registWikiBookmark.do")
    public String EgovWikiBookmarkRegist(
    		WikiBookmark wikiBookmark,
            ModelMap model) throws Exception {

    	String sDupl = "N";

    	if(wikiBookmark.getUsid() != null &&  wikiBookmark.getWikiBkmkNm() != null){
    		if(egovWikiBookmarkService.selectWikiBookmarkDuplicationCnt(wikiBookmark) > 0){
    			sDupl = "Y";
    		}else{
    			egovWikiBookmarkService.insertWikiBookmark(wikiBookmark);
    		}
    	}
    	//log.debug("Controller EgovWikiBookmarkRegist.WikiBookmark>" + wikiBookmark);
    	//중복 설정
    	model.addAttribute("S_DUPL", sDupl);
    	return "egovframework/com/uss/ion/wik/bmk/EgovWikiBookmarkRegist";
	}

}
