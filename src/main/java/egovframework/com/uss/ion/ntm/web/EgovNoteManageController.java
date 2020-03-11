package egovframework.com.uss.ion.ntm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ntm.service.EgovNoteManageService;
import egovframework.com.uss.ion.ntm.service.NoteManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 쪽지 관리(보내기)를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.16  장동한          최초 생성
 *   2011.8.26	 정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovNoteManageController {

	@Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "egovNoteManageService")
    private EgovNoteManageService egovNoteManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** 공통코드 서비스 */
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** 파일첨부 관리 서비스 */
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	/** 파일첨부 Util */
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    /**
     * 쪽지 관리(보내기) 목록을 조회한다.
     * @param noteManage 	-쪽지관리 Model
     * @param commandMap 	-Request  Variable
     * @param model 		-Spring 제공하는 ModelMap
     * @return String 		-리턴 URL
     * @throws Exception
     */
    @IncludedInfo(name="쪽지관리", order = 840 ,gid = 50)
    @RequestMapping(value = "/uss/ion/ntm/registEgovNoteManage.do")
    public String EgovNoteRecptnRegistForm(NoteManageVO noteManage, @RequestParam Map<?, ?> commandMap,ModelMap model) throws Exception {

    	String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "egovframework/com/uat/uia/EgovLoginUsr";
	    }

     	//수신구분
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM050");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("recptnSe", listComCode);

    	//답변처리
    	if(sCmd.equals("reply")){
    		model.addAttribute("cmd", sCmd);

    		Map<?, ?> mapNoteManage = egovNoteManageService.selectNoteManage(noteManage);

    		noteManage.setNoteSj("RE : " + (String)mapNoteManage.get("noteSj"));
    		/*
    		HTML EDIT 용
    		noteManage.setNoteCn(
    				"<br><br><br><br><font color='green' size='2'>" +
    				"[ 원 본 글 ]=================================================================<br>" +
    				"* 발 신 자 : " + (String)mapNoteManage.get("trnsmiterNm") + "("+ (String)mapNoteManage.get("trnsmiterNm") +")<br>" +
    				"* 발신시각 : "+ (String)mapNoteManage.get("trnsmiterPnttm") + "</font><br>" +
    				(String)mapNoteManage.get("noteCn")
    		);
    		*/
    		noteManage.setNoteCn(
    				"\r\n" +
    				"\r\n" +
    				"\r\n" +
    				"\r\n" +
    				"\r\n" +
    				"[ 원 본 글 ]================================================================" + "\r\n" +
    				"* 발 신 자 : " + (String)mapNoteManage.get("trnsmiterNm") + "("+ (String)mapNoteManage.get("trnsmiterNm") +")<br>" + "\r\n" +
    				"* 발신시각 : "+ (String)mapNoteManage.get("trnsmiterPnttm") + "\r\n" +
    				(String)mapNoteManage.get("noteCn")
      		);

    		noteManage.setAtchFileId((String)mapNoteManage.get("atchFileId"));

    		model.addAttribute("noteManage", noteManage);
    		model.addAttribute("noteManageMap", mapNoteManage);
    	}else{
    		model.addAttribute("noteManage", new NoteManageVO());
    	}

    	return "egovframework/com/uss/ion/ntm/EgovNoteManage";

    }

    /**
     * 쪽지 관리(보내기) 목록을 조회한다.(POST형식)
     * @param multiRequest 	-Multipart Request
     * @param commandMap 	-Request  Variable
     * @param noteManage 	-쪽지관리 Model
     * @param bindingResult	-Validator 하기위한 객체
     * @param model 		-Spring 제공하는 ModelMap
     * @return String 		-리턴 URL
     * @throws Exception
     */
    @RequestMapping(value = "/uss/ion/ntm/registEgovNoteManageActor.do")
    public String EgovNoteRecptnRegist(
    		final MultipartHttpServletRequest multiRequest,
    		@RequestParam Map<?, ?> commandMap,
            NoteManageVO noteManage,
            BindingResult bindingResult,
            ModelMap model) throws Exception {

    	String sLocationUrl = "egovframework/com/uss/ion/ntm/EgovNoteManage";

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "egovframework/com/uat/uia/EgovLoginUsr";
	    }

        //서버  validate 체크
        beanValidator.validate(noteManage, bindingResult);
        if(bindingResult.hasErrors()){
        	model.addAttribute("noteManage", noteManage);
            return sLocationUrl;
        }
        //로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        //아이디 설정
        noteManage.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        noteManage.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

       	// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> _result = null;
		String _atchFileId = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(!files.isEmpty()){
		 _result = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
		 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		}
		noteManage.setAtchFileId(_atchFileId);

		//쪽지등록
        egovNoteManageService.insertNoteManage(noteManage, commandMap);
        //NoteManage 빈 객체 생성
        model.addAttribute("noteManage", new NoteManageVO());

        //등록메세지 설정
        String ReusltScript = "";

        ReusltScript += "<script type='text/javaScript' language='javascript'>";
        ReusltScript += "alert(' 작성된 쪽지를 전송하였습니다!  ');";
        ReusltScript += "</script>";

        model.addAttribute("reusltScript", ReusltScript);

    	return sLocationUrl;
    }


    /**
     * 쪽지 관리(보내기) 사용자 목록을 조회한다.
     * @param searchVO 		-검색정보가 담긴 Model
     * @param commandMap 	-Request  Variable
     * @param model 		-Spring 제공하는 ModelMap
     * @return String 		-리턴 URL
     * @throws Exception
     */

    @RequestMapping(value = "/uss/ion/ntm/listEgovNoteEmpListPopup.do")
    public String EgovEgovNoteEmpList(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		@RequestParam Map<?, ?> commandMap,
            ModelMap model) throws Exception {


    		List<?> resultList = egovNoteManageService.selectNoteEmpListPopup(searchVO);
			model.addAttribute("resultList", resultList);

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

	        List<?> reusltList = egovNoteManageService.selectNoteEmpListPopup(searchVO);
	        model.addAttribute("resultList", reusltList);

	        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
	        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

	        int totCnt = egovNoteManageService.selectNoteEmpListPopupCnt(searchVO);
	        paginationInfo.setTotalRecordCount(totCnt);
	        model.addAttribute("paginationInfo", paginationInfo);

    	return "egovframework/com/uss/ion/ntm/EgovNoteEmpList";
    }


}
