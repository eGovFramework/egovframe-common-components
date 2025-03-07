package egovframework.com.uss.ion.ntr.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import egovframework.com.uss.ion.ntr.service.EgovNoteRecptnService;
import egovframework.com.uss.ion.ntr.service.NoteRecptn;
import egovframework.com.uss.ion.nts.service.EgovNoteTrnsmitService;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 받은쪽지함관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.16  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovNoteRecptnController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovNoteRecptnController.class);

//    @Autowired
//    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "egovNoteRecptnService")
    private EgovNoteRecptnService egovNoteRecptnService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** egovNoteTrnsmitService */
    @Resource(name = "egovNoteTrnsmitService")
    private EgovNoteTrnsmitService egovNoteTrnsmitService;
    /**
     * 받은쪽지함관리 목록을 조회한다.
     * @param request -HttpServletRequest 객체
     * @param response -HttpServletResponse 객체
     * @param searchVO -검색정보가 담긴 Model
     * @param commandMap -Request  Variable
     * @param noteRecptn -받은쪽지함관리 Model
     * @param model -Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @IncludedInfo(name="받은쪽지함관리", order = 850 ,gid = 50)
    @RequestMapping(value = "/uss/ion/ntr/listNoteRecptn.do")
    public String EgovNoteRecptnList(
			 HttpServletRequest request,
			 HttpServletResponse response,
    		@ModelAttribute("searchVO") NoteRecptn searchVO,
    		@RequestParam Map<?, ?> commandMap,
            @ModelAttribute("noteRecptn") NoteRecptn noteRecptn,
            EgovSecurityMap securitymap,
            ModelMap model) throws Exception {

    	//변수 설정
    	String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

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
        	LOGGER.debug("##### EgovNoteRecptnController EgovNoteRecptnList()  start");
        	LOGGER.debug("noteId > {}", commandMap.get("noteIdAll"));
        	LOGGER.debug("noteTrnsmitId > {}", commandMap.get("noteTrnsmitIdAll"));
        	LOGGER.debug("noteRecptnId > {}", commandMap.get("noteRecptnIdAll"));
        	
        	String[] aNoteId = ((String) commandMap.get("noteIdAll")).split(",");
            String[] aNoteTrnsmitId = ((String)commandMap.get("noteTrnsmitIdAll")).split(",");
            String[] aNoteRecptnId = ((String)commandMap.get("noteRecptnIdAll")).split(",");
        	
            for(int i=0; i < aNoteId.length; i++) {
            	String sNoteId = aNoteId[i];
            	String sNoteTrnsmitId = aNoteTrnsmitId[i];
            	String sNoteRecptnId = aNoteRecptnId[i];
            	
            	securitymap.put("noteId", sNoteId);
	            securitymap.put("noteTrnsmitId", sNoteTrnsmitId);
	            securitymap.put("noteRecptnId", sNoteRecptnId);
            	
	            LOGGER.debug("sArrCheckListValue[0] > {}", securitymap.get("noteId"));
	            LOGGER.debug("sArrCheckListValue[1] > {}", securitymap.get("noteTrnsmitId"));
	            LOGGER.debug("sArrCheckListValue[2] > {}", securitymap.get("noteRecptnId"));
	            
	            noteRecptn.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	            noteRecptn.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	            noteRecptn.setNoteId(securitymap.get("noteId"));
	            noteRecptn.setNoteTrnsmitId(securitymap.get("noteTrnsmitId"));
	            noteRecptn.setNoteRecptnId(securitymap.get("noteRecptnId"));
	            noteRecptn.setRcverId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	            
	            egovNoteRecptnService.deleteNoteRecptn(noteRecptn);
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
        //수신자설정
        searchVO.setRcverId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        List<EgovMap> reusltList = egovNoteRecptnService.selectNoteRecptnList(searchVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        int totCnt = egovNoteRecptnService.selectNoteRecptnListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

    	return "egovframework/com/uss/ion/ntr/EgovNoteRecptnList";

    }

    /**
     * 받은쪽지함관리 목록을 상세조회 조회한다.
     * @param searchVO -검색정보가 담긴 Model
     * @param commandMap -Request  Variable
     * @param noteRecptn -받은쪽지함관리 Model
     * @param model -Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/uss/ion/ntr/detailNoteRecptn.do")
    public String EgovNoteRecptnDetail(
    		@ModelAttribute("searchVO") NoteRecptn searchVO,
            @ModelAttribute("noteRecptn") NoteRecptn noteRecptn,
    		EgovSecurityMap securityMap,
            ModelMap model) throws Exception {

		String sLocationUrl = "egovframework/com/uss/ion/nts/EgovNoteTrnsmitDetail";

        String sCmd = securityMap.get("cmd") == null ? "" : (String) securityMap.get("cmd");

        if(sCmd.equals("del")){
        	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
            searchVO.setRcverId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));      
        	egovNoteRecptnService.deleteNoteRecptn(searchVO);
      
        	return "redirect:/uss/ion/ntr/listNoteRecptn.do";
        }else{
            //로그인 객체 선언/아이디설정
            LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
            searchVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            searchVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            searchVO.setRcverId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        	Map<?, ?> noteRecptnMap = egovNoteRecptnService.selectNoteRecptnDetail(searchVO);
        	model.addAttribute("noteRecptn", noteRecptnMap);

        	egovframework.com.uss.ion.nts.service.NoteTrnsmit noteTrnsmit = new egovframework.com.uss.ion.nts.service.NoteTrnsmit();
        	LOGGER.debug("===> SecurityMap = "+securityMap);
        	securityMap.put("noteId", searchVO.getNoteId());
        	noteTrnsmit.setNoteId(searchVO.getNoteId());

            List<EgovMap> resultRecptnEmp = egovNoteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmit);
        	model.addAttribute("resultRecptnEmp", resultRecptnEmp);
        }

    	return "egovframework/com/uss/ion/ntr/EgovNoteRecptnDetail";
    }

}
