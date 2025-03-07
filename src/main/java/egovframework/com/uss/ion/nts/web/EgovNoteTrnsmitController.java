package egovframework.com.uss.ion.nts.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.resolver.EgovSecurityMap;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.nts.service.EgovNoteTrnsmitService;
import egovframework.com.uss.ion.nts.service.NoteTrnsmit;
import egovframework.com.utl.fcc.service.EgovStringUtil;
/**
 * 보낸쪽지함관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.16  장동한          최초 생성
 *    2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovNoteTrnsmitController {

//    @Autowired
//    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "egovNoteTrnsmitService")
    private EgovNoteTrnsmitService egovNoteTrnsmitService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovNoteTrnsmitController.class);

    /**
     * 보낸쪽지함관리 목록을 조회한다.
     * @param searchVO -검색정보가 담긴 객체
     * @param commandMap -Request Variable
     * @param noteTrnsmit -보낸쪽지함정보가 담긴객체
     * @param model -Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
	@IncludedInfo(name="보낸쪽지함관리", order = 860 ,gid = 50)
    @RequestMapping(value = "/uss/ion/nts/listNoteTrnsmit.do")
    public String EgovNoteTrnsmitList(
    		@ModelAttribute("searchVO") NoteTrnsmit searchVO,
    		@ModelAttribute("userMap") @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("noteTrnsmit") NoteTrnsmit noteTrnsmit,
            EgovSecurityMap securitymap,
            ModelMap model) throws Exception {

    	//변수 설정
    	String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

    	
    	LOGGER.info("userMap>"+commandMap);
    	
		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }

        //로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        //삭제 모드로 실행시
        if(sCmd.equals("del")){
	        
        	LOGGER.debug("##### EgovNoteTrnsmitController EgovNoteTrnsmitList()  start");
        	LOGGER.debug("noteId > {}", commandMap.get("noteIdAll"));
        	LOGGER.debug("noteTrnsmitId > {}", commandMap.get("noteTrnsmitIdAll"));
        	
        	String[] aNoteId = ((String) commandMap.get("noteIdAll")).split(",");
            String[] aNoteTrnsmitId = ((String)commandMap.get("noteTrnsmitIdAll")).split(",");
        	
            for(int i=0; i < aNoteId.length; i++) {
            	String sNoteId = aNoteId[i];
            	String sNoteTrnsmitId = aNoteTrnsmitId[i];
            	
            	securitymap.put("noteId", sNoteId);
	            securitymap.put("noteTrnsmitId", sNoteTrnsmitId);
            	
	            LOGGER.debug("sArrCheckListValue[0] > {}", securitymap.get("noteId"));
	            LOGGER.debug("sArrCheckListValue[1] > {}", securitymap.get("noteTrnsmitId"));
	            
	            noteTrnsmit.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	            noteTrnsmit.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	            noteTrnsmit.setNoteId(securitymap.get("noteId"));
	            noteTrnsmit.setNoteTrnsmitId(securitymap.get("noteTrnsmitId"));
	            noteTrnsmit.setTrnsmiterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	            
	            egovNoteTrnsmitService.deleteNoteTrnsmit(noteTrnsmit);
            	
            }

	        //삭제후 페이지 인덱스 설정
	        searchVO.setPageIndex(1);
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
        searchVO.setTrnsmiterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        List<EgovMap> reusltList = egovNoteTrnsmitService.selectNoteTrnsmitList(searchVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        int totCnt = egovNoteTrnsmitService.selectNoteTrnsmitListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);


    	return "egovframework/com/uss/ion/nts/EgovNoteTrnsmitList";

    }

    /**
     * 보낸쪽지함관리 목록을 상세조회 조회한다.
     * @param searchVO -검색정보가 담긴 객체
     * @param commandMap -Request Variable
     * @param model -Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @RequestMapping(value = "/uss/ion/nts/detailNoteTrnsmit.do")
    public String EgovNoteTrnsmitDetail(
    		@ModelAttribute("searchVO") NoteTrnsmit searchVO,
    		EgovSecurityMap securityMap,
            ModelMap model) throws Exception {

    		String sLocationUrl = "egovframework/com/uss/ion/nts/EgovNoteTrnsmitDetail";

            String sCmd = securityMap.get("cmd") == null ? "" : (String) securityMap.get("cmd");

    		//Spring Security 사용자권한 처리
    	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	    if (!isAuthenticated) {
    	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    	        return "redirect:/uat/uia/egovLoginUsr.do";
    	    }

            //로그인 객체 선언
            LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
            
            securityMap.put("noteId",searchVO.getNoteId());
            securityMap.put("noteTrnsmitId", searchVO.getNoteTrnsmitId());

            if(sCmd.equals("del")){
            	searchVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            	searchVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            	searchVO.setTrnsmiterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            	egovNoteTrnsmitService.deleteNoteTrnsmit(searchVO);

            	sLocationUrl = "redirect:/uss/ion/nts/listNoteTrnsmit.do";
            }else{
            	searchVO.setTrnsmiterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            	
            	Map<?, ?> noteTrnsmitMap = egovNoteTrnsmitService.selectNoteTrnsmitDetail(searchVO);
            	model.addAttribute("noteTrnsmit", noteTrnsmitMap);

            	egovframework.com.uss.ion.nts.service.NoteTrnsmit noteTrnsmit = new egovframework.com.uss.ion.nts.service.NoteTrnsmit();
            	noteTrnsmit.setNoteId(searchVO.getNoteId());
            	
                List<EgovMap> resultRecptnEmp = egovNoteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmit);
            	model.addAttribute("resultRecptnEmp", resultRecptnEmp);
            }

    		return sLocationUrl;
    }

    /**
     * 수신자목록을 조회한다.
     * @param noteTrnsmit -보낸쪽지함 정보가 담긴 객체
     * @param commandMap -Request Variable
     * @param model -Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @RequestMapping(value = "/uss/ion/nts/selectNoteTrnsmitCnfirm.do")
    public String EgovNoteTrnsmitCnfirm(
    		NoteTrnsmit noteTrnsmit,
    		@RequestParam Map<?, ?> commandMap,
            ModelMap model) throws Exception {

            List<EgovMap> resultList = egovNoteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmit);
        	model.addAttribute("resultList", resultList);

    		return "egovframework/com/uss/ion/nts/EgovNoteTrnsmitCnfirm";
    }

}
