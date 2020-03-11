package egovframework.com.cop.cmy.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.cmy.service.CommunityUser;
import egovframework.com.cop.cmy.service.CommunityUserVO;
import egovframework.com.cop.cmy.service.CommunityVO;
import egovframework.com.cop.cmy.service.EgovCommuBBSMasterService;
import egovframework.com.cop.cmy.service.EgovCommuManageService;
import egovframework.com.cop.cmy.service.EgovCommuMasterService;
import egovframework.com.cop.tpl.service.EgovTemplateManageService;
import egovframework.com.cop.tpl.service.TemplateInfVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 커뮤니티 사용자관리, 커뮤니티 게시판을 관리하기 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 김연호
 * @since 2016.08.01
 * @version 3.6
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일               수정자            수정내용
 *   ----------   --------   ---------------------------
 *   2016.06.13   김연호            최초 생성 - 표준프레임워크 v3.6 개선
 *   2019.05.17   신용호            KISA 취약점 조치 및 보완
 *   
 * </pre>
 */

@Controller
public class EgovCommuManageController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovCommuManageController.class);
	
	@Resource(name = "EgovCommuManageService")
    private EgovCommuManageService egovCommuManageService;
	
	@Resource(name = "EgovCommuBBSMasterService")
	private EgovCommuBBSMasterService egovCommuBBSMasterService;
	
	@Resource(name = "EgovCommuMasterService")
	private EgovCommuMasterService egovCommuMasterService;
	
	@Resource(name = "EgovArticleService")
	private EgovArticleService egovArticleService;

	@Resource(name = "EgovTemplateManageService")
	private EgovTemplateManageService egovTemplateManageService;
	
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Autowired
    private DefaultBeanValidator beanValidator;
	
    /** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
    
	/**
     * 커뮤니티 메인페이지를 조회한다.
     * 
     * @param cmmntyVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/cmmntyMain.do")
    public String selectCmmntyMain(@ModelAttribute("searchVO") CommunityVO cmmntyVO
    		,ModelMap model
    		,HttpServletRequest request) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        cmmntyVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	
//		String tmplatCours = cmmntyService.selectCmmntyTemplat(cmmntyVO);
		String tmplatCours = "";
		if ("".equals(tmplatCours) || tmplatCours == null) {
		    tmplatCours = "egovframework/com/cop/tpl/EgovCmmntyBaseTmpl";
		}
		Map<String, Object> map = egovCommuManageService.selectCommuInf(cmmntyVO);
	
		//model.addAttribute("cmmntyVO", cmmntyVO);
		model.addAttribute("cmmntyVO", (CommunityVO)map.get("cmmntyVO"));
		model.addAttribute("cmmntyUser", (CommunityUser)map.get("cmmntyUser"));
			
		//--------------------------------
		// 게시판 목록 정보 처리
		//--------------------------------
		BoardMasterVO bbsVo = new BoardMasterVO();
		
		bbsVo.setCmmntyId(cmmntyVO.getCmmntyId());
		
		List<BoardMasterVO> bbsResult = egovCommuBBSMasterService.selectCommuBBSMasterListMain(bbsVo);
	
		model.addAttribute("bbsList", bbsResult);
		////------------------------------
		
		if (isAuthenticated) {
		    model.addAttribute("isAuthenticated", "Y");
		} else {
		    model.addAttribute("isAuthenticated", "N");
		}
		model.addAttribute("returnMsg", request.getParameter("returnMsg"));
		
		return "egovframework/com/cop/cmy/EgovCommuMain";
    }
    
    /**
     * 커뮤니티 메인페이지의 기본 내용(게시판 4개 표시) 조회한다.
     * 
     * @param cmmntyVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/cmmntyMainContents.do")
    public String selectCmmntyMainContents(@ModelAttribute("searchVO") CommunityVO cmmntyVO, ModelMap model) throws Exception {
		
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		// KISA 보안취약점 조치 (2018-12-10, 신용호)
        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
	
		cmmntyVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	
		//--------------------------------
		// 게시판 목록 정보 처리
		//--------------------------------
		BoardMasterVO bbsVo = new BoardMasterVO();
		
		bbsVo.setCmmntyId(cmmntyVO.getCmmntyId());

		List<BoardMasterVO> bbsResult = egovCommuBBSMasterService.selectCommuBBSMasterListMain(bbsVo);
	
		// 방명록 제외 처리
		for (int i = 0; i < bbsResult.size(); i++) {
		    if ("BBST04".equals(bbsResult.get(i).getBbsTyCode())) {
			bbsResult.remove(i);
		    }
		}
	
		model.addAttribute("bbsList", bbsResult);
	
		//--------------------------------
		// 게시물 목록 정보 처리
		//--------------------------------
		BoardVO boardVo = null;
		BoardMasterVO masterVo = null;
		
		ArrayList<Object> target = new ArrayList<Object>();	// Object => List<BoardVO>
		for (int i = 0; i < bbsResult.size() && i < 4; i++) {
		    masterVo = bbsResult.get(i);
		    boardVo = new BoardVO();
	
		    boardVo.setBbsId(masterVo.getBbsId());
		    boardVo.setBbsNm(masterVo.getBbsNm());
	
		    boardVo.setPageUnit(4);
		    boardVo.setPageSize(4);
	
		    boardVo.setFirstIndex(0);
		    boardVo.setRecordCountPerPage(4);
	
		    Map<String, Object> map = egovArticleService.selectArticleList(boardVo);
	
		    target.add(map.get("resultList"));
		}
	
		model.addAttribute("articleList", target);
	
		return "egovframework/com/cop/cmy/EgovCmmntyBaseTmplContents";
    }
    
    /**
     * 커뮤니티 가입신청을 등록한다.
     * 
     * @param cmmntyUser
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/insertCommuUserBySelf.do")
    public String insertCmmntyUserBySelf(@ModelAttribute("cmmntyUser") CommunityUser cmmntyUser, ModelMap model) throws Exception {
    	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		//KISA 보안취약점 조치 (2018-12-10, 신용호)
        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
	
		String retVal = "";
	
		if ("".equals(cmmntyUser.getMngrAt())) {
		    cmmntyUser.setMngrAt("N");
		}
		cmmntyUser.setUseAt("Y");
		cmmntyUser.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		cmmntyUser.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		cmmntyUser.setMberSttus("A");
		
		if (isAuthenticated) {
	
		    //---------------------------------------------
		    // 승인요청 처리
		    //---------------------------------------------
		    retVal = egovCommuManageService.checkCommuUserDetail(cmmntyUser);
	
		    //요청건이 없을 경우 
		    if (!retVal.equals("EXIST")) {
				
				egovCommuManageService.insertCommuUserRqst(cmmntyUser);
				retVal = egovMessageSource.getMessage("comCopCmy.commuMain.joinMember.info.success"); //가입신청이 정상처리되었습니다.
		    } else {
		    	
		    	retVal = egovMessageSource.getMessage("comCopCmy.commuMain.joinMember.info.fail"); //이미 가입처리가 되어 있습니다.
		    }
		    ////-------------------------------------------
		}
		model.addAttribute("returnMsg", retVal);
		model.addAttribute("cmmntyId", cmmntyUser.getCmmntyId());
		
		return "redirect:/cop/cmy/cmmntyMain.do";
    }
    
    /**
     * 커뮤니티를 탈퇴한다.
     * 
     * @param cmmntyUser
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/deleteCommuUserBySelf.do")
    public String deleteCmmntyUserBySelf(@ModelAttribute("cmmntyUser") CommunityUserVO cmmntyUserVO, ModelMap model) throws Exception {
    	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		//KISA 보안취약점 조치 (2018-12-10, 신용호)
        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
		
		//로그인한 사용자가 관리자인지 확인한다.
		CommunityUserVO userVO = new CommunityUserVO();
		userVO.setCmmntyId(cmmntyUserVO.getCmmntyId());
		userVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		Boolean isCommuAdmin = egovCommuManageService.selectIsCommuAdmin(userVO);
	
		//관리자는 탈퇴할 수 없음.
		String resultMsg = "";
		if(isAuthenticated && !isCommuAdmin) {
			cmmntyUserVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			egovCommuManageService.deleteCommuUser(cmmntyUserVO);
			resultMsg = egovMessageSource.getMessage("comCopCmy.commuMain.deleteMember.info.success"); //탈퇴신청이 정상처리되었습니다.
		} else {
			resultMsg = egovMessageSource.getMessage("comCopCmy.commuMain.deleteMember.info.admin"); //관리자는 탈퇴할수 없습니다.
		}

		model.addAttribute("cmmntyId", cmmntyUserVO.getCmmntyId());
		model.addAttribute("returnMsg", resultMsg);
		
		return "redirect:/cop/cmy/cmmntyMain.do";
    }
    
    /**
     * 커뮤니티 사용자 목록을 조회한다.
     * 
     * @param cmmntyUserVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/selectCommuUserList.do")
    public String selectCommuUserList(@ModelAttribute("searchVO") CommunityUserVO cmmntyUserVO, ModelMap model) throws Exception {
		cmmntyUserVO.setPageUnit(propertyService.getInt("pageUnit"));
		cmmntyUserVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(cmmntyUserVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(cmmntyUserVO.getPageUnit());
		paginationInfo.setPageSize(cmmntyUserVO.getPageSize());
	
		cmmntyUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		cmmntyUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
		cmmntyUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = egovCommuManageService.selectCommuUserList(cmmntyUserVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
	
		return "egovframework/com/cop/cmy/EgovCommuUserList";
    }
    
    /**
     * 커뮤니티 사용자를 등록한다.
     * 
     * @param cmmntyUserVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/insertCommuUser.do")
    public String insertCommuUser(@ModelAttribute("searchVO") CommunityUserVO cmmntyUserVO, ModelMap model) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
    	
		//로그인한 사용자가 관리자인지 확인한다.
		CommunityUserVO userVO = new CommunityUserVO();
		userVO.setCmmntyId(cmmntyUserVO.getCmmntyId());
		userVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		Boolean isCommuAdmin = egovCommuManageService.selectIsCommuAdmin(userVO);
		
		
		if(isAuthenticated && isCommuAdmin) {
			cmmntyUserVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			egovCommuManageService.insertCommuUser(cmmntyUserVO);
		}
		
		
		
		return "forward:/cop/cmy/selectCommuUserList.do";
    }
    
    /**
     * 커뮤니티 사용자를 탈퇴시킨다. (가입거절 포함)
     * 
     * @param cmmntyUserVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/deleteCommuUser.do")
    public String deleteCommuUser(@ModelAttribute("searchVO") CommunityUserVO cmmntyUserVO, ModelMap model) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
    	
		//로그인한 사용자가 관리자인지 확인한다.
		CommunityUserVO userVO = new CommunityUserVO();
		userVO.setCmmntyId(cmmntyUserVO.getCmmntyId());
		userVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		Boolean isCommuAdmin = egovCommuManageService.selectIsCommuAdmin(userVO);
		
		
		if(isAuthenticated && isCommuAdmin) {
			egovCommuManageService.deleteCommuUser(cmmntyUserVO);
		}
		
		
		
		return "forward:/cop/cmy/selectCommuUserList.do";
    }
    
    /**
     * 커뮤니티 관리자를 등록한다.
     * 
     * @param cmmntyUserVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/insertCommuUserAdmin.do")
    public String insertCommuUserAdmin(@ModelAttribute("searchVO") CommunityUserVO cmmntyUserVO, ModelMap model) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
    	
		//로그인한 사용자가 관리자인지 확인한다.
		CommunityUserVO userVO = new CommunityUserVO();
		userVO.setCmmntyId(cmmntyUserVO.getCmmntyId());
		userVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		Boolean isCommuAdmin = egovCommuManageService.selectIsCommuAdmin(userVO);
		
		
		if(isAuthenticated && isCommuAdmin) {
			cmmntyUserVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			egovCommuManageService.insertCommuUserAdmin(cmmntyUserVO);
		}
		
		
		
		return "forward:/cop/cmy/selectCommuUserList.do";
    }
    
    /**
     * 커뮤니티 관리자를 해제한다.
     * 
     * @param cmmntyUserVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/deleteCommuUserAdmin.do")
    public String deleteCommuUserAdmin(@ModelAttribute("searchVO") CommunityUserVO cmmntyUserVO, ModelMap model) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
    	
		//로그인한 사용자가 관리자인지 확인한다.
		CommunityUserVO userVO = new CommunityUserVO();
		userVO.setCmmntyId(cmmntyUserVO.getCmmntyId());
		userVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		Boolean isCommuAdmin = egovCommuManageService.selectIsCommuAdmin(userVO);
		
		
		//커뮤니티 개설자는 관리자해제를 할 수 없음.
		CommunityVO cmmntyVO = new CommunityVO();
		cmmntyVO.setCmmntyId(cmmntyUserVO.getCmmntyId());
		cmmntyVO = egovCommuMasterService.selectCommuMaster(cmmntyVO);
		//커뮤니티 최초등록자를 확인한다. 일치할 경우 관리자 해제 불가.
		if(cmmntyVO.getFrstRegisterId().equals(cmmntyUserVO.getEmplyrId())) {
			return "forward:/cop/cmy/selectCommuUserList.do";
		}
		
		
		
		if(isAuthenticated && isCommuAdmin) {
			cmmntyUserVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			egovCommuManageService.deleteCommuUserAdmin(cmmntyUserVO);
		}
		
		
		
		return "forward:/cop/cmy/selectCommuUserList.do";
    }

    /**
     * 미리보기 커뮤니티 메인페이지를 조회한다.
     * 
     * @param cmmntyVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/previewCmmntyMainPage.do")
    public String previewCmmntyMainPage(@ModelAttribute("searchVO") CommunityVO cmmntyVO, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		cmmntyVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	
		String tmplatCours = cmmntyVO.getSearchWrd();
		
		CommunityVO vo = new CommunityVO();
		
		vo.setCmmntyNm("미리보기 커뮤니티");
		vo.setCmmntyIntrcn("미리보기를 위한 커뮤니티입니다.");
		vo.setUseAt("Y");
		vo.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));	// 본인
		
		CommunityUser cmmntyUser = new CommunityUser();
		
		cmmntyUser.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		cmmntyUser.setEmplyrNm("관리자");
	
		model.addAttribute("cmmntyVO", vo);
		model.addAttribute("cmmntyUser", cmmntyUser);
			
		//--------------------------------
		// 게시판 목록 정보 처리
		//--------------------------------
		List<BoardMasterVO> bbsResult = new ArrayList<BoardMasterVO>();
		
		BoardMasterVO target = null;
		
		target = new BoardMasterVO();
		target.setBbsNm("방명록");
		bbsResult.add(target);
		
		target = new BoardMasterVO();
		target.setBbsNm("공지게시판");
		bbsResult.add(target);
		
		target = new BoardMasterVO();
		target.setBbsNm("갤러리");
		bbsResult.add(target);
		
		target = new BoardMasterVO();
		target.setBbsNm("자유게시판");
		bbsResult.add(target);
		
		target = new BoardMasterVO();
		target.setBbsNm("자료실");
		bbsResult.add(target);
		
		model.addAttribute("bbsList", bbsResult);
		////------------------------------
	
		if (isAuthenticated) {
		    model.addAttribute("isAuthenticated", "Y");
		} else {
		    model.addAttribute("isAuthenticated", "N");
		}
		
		model.addAttribute("preview", "true");
		
		// 안전한 경로 문자열로 조치
		tmplatCours = EgovWebUtil.filePathBlackList(tmplatCours);

		// 화이트 리스트 체크
		List<TemplateInfVO> templateWhiteList = egovTemplateManageService.selectTemplateWhiteList();
		LOGGER.debug("Template > WhiteList Count = {}",templateWhiteList.size());
		if ( tmplatCours == null ) tmplatCours = "";
		for(TemplateInfVO templateInfVO : templateWhiteList){
			LOGGER.debug("Template > whiteList TmplatCours = "+templateInfVO.getTmplatCours());
            if ( tmplatCours.equals(templateInfVO.getTmplatCours()) ) {
            	return tmplatCours;
            }
        }
		
		LOGGER.debug("Template > WhiteList mismatch! Please check Admin page!");
		return "egovframework/com/cmm/egovError";
    }
    
    /**
     * 커뮤니티 메인페이지의 기본 내용(게시판 4개 표시) 조회한다.
     * 
     * @param cmmntyVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/cmy/previewCmmntyMainContents.do")
    public String previewCmmntyMainContents(@ModelAttribute("searchVO") CommunityVO cmmntyVO, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		@SuppressWarnings("unused")
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		cmmntyVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	
		//--------------------------------
		// 게시판 목록 정보 처리
		//--------------------------------
		List<BoardMasterVO> bbsResult = new ArrayList<BoardMasterVO>();
	
		BoardMasterVO master = null;
		
		master = new BoardMasterVO();
		master.setBbsNm("공지게시판");
		bbsResult.add(master);
		
		master = new BoardMasterVO();
		master.setBbsNm("갤러리");
		bbsResult.add(master);
		
		master = new BoardMasterVO();
		master.setBbsNm("자유게시판");
		bbsResult.add(master);
		
		master = new BoardMasterVO();
		master.setBbsNm("자료실");
		bbsResult.add(master);
	
		model.addAttribute("bbsList", bbsResult);
	
		//--------------------------------
		// 게시물 목록 정보 처리
		//--------------------------------	
		ArrayList<Object> target = new ArrayList<Object>();	// Object => List<BoardVO>
		for (int i = 0; i < bbsResult.size() && i < 4; i++) {
	
		    target.add(null);
		}
	
		model.addAttribute("boardList", target);
		
		model.addAttribute("preview", "true");
	
		return "egovframework/com/cop/tpl/EgovCmmntyBaseTmplContents";
    }

    
}
