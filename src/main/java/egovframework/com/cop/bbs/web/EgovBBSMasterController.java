package egovframework.com.cop.bbs.web;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovAuthorizationHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 게시판 속성관리를 위한 컨트롤러  클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *   2009.3.12   이삼섭      최초 생성
 *   2009.06.26	 한성곤		 2단계 기능 추가 (댓글관리, 만족도조사)
 *	 2011.07.21  안민정      커뮤니티 관련 메소드 분리 (->EgovBBSAttributeManageController)
 *	 2011.8.26	 정진오		 IncludedInfo annotation 추가
 *   2011.09.15  서준식      2단계 기능 추가 (댓글관리, 만족도조사) 적용방법 변경
 *   2016.06.13  김연호      표준프레임워크 v3.6 개선
 *   2022.11.11  김혜준      시큐어코딩 처리
 *   2024.10.29	inganyoyo	Controller는 Transaction 처리를 하지 않아 Controller에서 오류 발생 시 데이터 정합성 오류 문제 발생
 * </pre>
 */

@Controller
public class EgovBBSMasterController {

    @Resource(name = "EgovBBSMasterService")
    private EgovBBSMasterService egovBBSMasterService;

    @Resource(name = "EgovArticleService")
    private EgovArticleService egovArticleService;

    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenServiceBbs;

    @Resource(name = "egovBlogIdGnrService")
    private EgovIdGnrService idgenServiceBlog;

    /** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

    //Logger log = Logger.getLogger(this.getClass());

    /**
     * 추가 선택사항(댓글, 만족도조사) 컴포넌트의 설치 여부를 모델에 담는다.
     * 2011.09.15 : 2단계 기능 추가 반영 방법 변경
     *
     * @param model
     */
    private void addAddedOptionsAttribute(ModelMap model) {
        if (EgovComponentChecker.hasComponent("EgovArticleCommentService")) {
            model.addAttribute("useComment", "true");
        }
        if (EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")) {
            model.addAttribute("useSatisfaction", "true");
        }
    }

    /**
     * 신규 게시판 마스터 등록을 위한 등록페이지로 이동한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     */
    @GetMapping("/cop/bbs/insertBBSMasterView.do")
    public String insertBBSMasterView(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) {
		BoardMasterVO boardMaster = new BoardMasterVO();
		//공통코드(게시판유형)
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM101");
		List<CmmnDetailCode> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("bbsTyCode", codeResult);
		model.addAttribute("boardMasterVO", boardMaster);


		addAddedOptionsAttribute(model);

		return "egovframework/com/cop/bbs/EgovBBSMasterRegist";
    }

    /**
     * 신규 게시판 마스터 정보를 등록한다.
     *
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     */
    @PostMapping("/cop/bbs/insertBBSMaster.do")
    public String insertBBSMaster(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @Valid @ModelAttribute("boardMasterVO") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();

		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<CmmnDetailCode> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    model.addAttribute("bbsTyCode", codeResult);
		    addAddedOptionsAttribute(model);

		    return "egovframework/com/cop/bbs/EgovBBSMasterRegist";
		}

		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((boardMasterVO == null ? "" : EgovStringUtil.isNullToString(boardMasterVO.getBlogAt())).equals("Y")){
		    	// 블로그 카테고리는 그 블로그 개설자만 만든다
		    	BoardVO blogOwner = new BoardVO();
		    	blogOwner.setBlogId(boardMaster.getBlogId());
		    	blogOwner.setFrstRegisterId(boardMaster.getFrstRegisterId());
		    	if (egovArticleService.selectLoginUser(blogOwner) == 0) {
		    		throw new EgovAccessDeniedException("블로그 개설자만 카테고리를 만들 수 있습니다.");
		    	}
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }
		    egovBBSMasterService.insertBBSMasterInf(boardMaster);
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selectBBSMasterInfs.do";
		}

    }

    /**
     * 게시판 마스터 목록을 조회한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     */
    @IncludedInfo(name="게시판관리",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectBBSMasterInfs.do")
    public String selectBBSMasterInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());

		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectBBSMasterInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/bbs/EgovBBSMasterList";
    }

    /**
     * 블로그에 대한 목록을 조회한다.
     *
     * @param blogVO
     * @param model
     * @return
     */
    @IncludedInfo(name="블로그관리", order = 170 ,gid = 40)
    @RequestMapping("/cop/bbs/selectBlogList.do")
    public String selectBlogMasterList(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	 //KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());

		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		boardMasterVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

		Map<String, Object> map = egovBBSMasterService.selectBlogMasterInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/bbs/EgovBlogList";
    }

    /**
     * 블로그 등록을 위한 등록페이지로 이동한다.
     *
     * @param blogVO
     * @param model
     * @return
     */
    @PostMapping("/cop/bbs/insertBlogMasterView.do")
    public String insertBlogMasterView(@ModelAttribute("searchVO") BlogVO blogVO, ModelMap model) {
    	model.addAttribute("blogMasterVO", new BlogVO());
	return "egovframework/com/cop/bbs/EgovBlogRegist";
    }

    /**
     * 블로그 생성 유무를 판단한다.
     *
     * @param blogVO
     * @param model
     * @return
     */
    @RequestMapping("/cop/bbs/selectChkBloguser.do")
    public ModelAndView chkBlogUser(@ModelAttribute("searchVO") BlogVO blogVO, ModelMap model) {
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
			throw new BaseRuntimeException("Login Required!");
        }

    	model.addAttribute("blogMasterVO", new BlogVO());

    	String userVal="";
    	blogVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
    	userVal = egovBBSMasterService.checkBlogUser(blogVO);

    	ModelAndView mav = new ModelAndView("jsonView");
    	mav.addObject("userChk", userVal);
    	return mav;
    }

    /**
     * 블로그 정보를 등록한다.
     *
     * @param blogVO
     * @param blog
     * @param status
     * @param model
     * @return
     */
    @PostMapping("/cop/bbs/insertBlogMaster.do")
    public String insertBlogMaster(@ModelAttribute("searchVO") BlogVO blogVO, @Valid @ModelAttribute("blogMasterVO") Blog blog,
	    BindingResult bindingResult, ModelMap model) {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) { //KISA 보안약점 조치 (2018-12-10, 신용호)
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

		blogVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		BlogVO vo = egovBBSMasterService.checkBlogUser2(blogVO);

		if(vo != null) {
			model.addAttribute("blogMasterVO", new BlogVO());
			model.addAttribute("message", egovMessageSource.getMessage("comCopBlog.validate.blogUserCheck"));
			return "egovframework/com/cop/bbs/EgovBlogRegist";
		}

		if (bindingResult.hasErrors()) {
		    return "egovframework/com/cop/bbs/EgovBlogRegist";
		}

    // 블로그 정보와 개설자 정보 등록한다
    // Controller는 Transaction처리를 하지 않아 Controller에서 오류 발생 시 데이터 정합성 오류 문제 발생
    egovBBSMasterService.insertBlogMasterAndBoardBlogUserRqst(blog, user);

		return "forward:/cop/bbs/selectBlogList.do";
    }

    /**
     * 게시판 마스터 상세내용을 조회한다.
     *
     * @param boardMasterVO
     * @param model
     * @return
     */
    @PostMapping("/cop/bbs/selectBBSMasterDetail.do")
    public String selectBBSMasterDetail(@ModelAttribute("searchVO") BoardMasterVO searchVO, ModelMap model) {
		BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(searchVO);
		model.addAttribute("result", vo);

		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------

		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}

		return "egovframework/com/cop/bbs/EgovBBSMasterDetail";
    }

    /**
     * 게시판 마스터정보를 수정하기 위한 전 처리
     * @param bbsId
     * @param searchVO
     * @param model
     */
    @PostMapping("/cop/bbs/updateBBSMasterView.do")
    public String updateBBSMasterView(@RequestParam("bbsId") String bbsId ,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model) {
		// 2026.07.13 KISA 보안취약점 조치
		LoginVO _loginVO = EgovAuthorizationHelper.assertLoginUser();

        BoardMasterVO boardMasterVO = new BoardMasterVO();

        // Primary Key 값 세팅
        boardMasterVO.setBbsId(bbsId);

        // 소유권(개설자) 검증 - 개설자 본인만 수정폼을 조회할 수 있다.
        BoardMasterVO existingBoard = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
        EgovAuthorizationHelper.assertOwner(existingBoard == null ? null : existingBoard.getFrstRegisterId());

        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<CmmnDetailCode> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);

        model.addAttribute("boardMasterVO", existingBoard);

		addAddedOptionsAttribute(model);

        return "egovframework/com/cop/bbs/EgovBBSMasterUpdt";
    }


    /**
     * 게시판 마스터 정보를 수정한다.
     *
     * @param boardMasterVO
     * @param boardMaster
     * @param model
     * @return
     */
    @PostMapping("/cop/bbs/updateBBSMaster.do")
    public String updateBBSMaster(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @Valid @ModelAttribute("boardMasterVO") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		// 소유권(개설자) 검증 - 개설자 본인만 게시판 설정을 수정할 수 있다.
		BoardMasterVO existingBoard = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
		EgovAuthorizationHelper.assertOwner(existingBoard == null ? null : existingBoard.getFrstRegisterId());

		if (bindingResult.hasErrors()) {
		    model.addAttribute("result", existingBoard);

		    ComDefaultCodeVO comVo = new ComDefaultCodeVO();
	        comVo.setCodeId("COM101");
	        List<CmmnDetailCode> codeResult = cmmUseService.selectCmmCodeDetail(comVo);
	        model.addAttribute("bbsTyCode", codeResult);
		    addAddedOptionsAttribute(model);

		    return "egovframework/com/cop/bbs/EgovBBSMasterUpdt";
		}

		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    egovBBSMasterService.updateBBSMasterInf(boardMaster);
		}

		return "forward:/cop/bbs/selectBBSMasterInfs.do";
    }

    /**
     * 게시판 마스터 정보를 삭제한다.
     *
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     */
    @PostMapping("/cop/bbs/deleteBBSMaster.do")
    public String deleteBBSMaster(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster
	    ) {

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	// 소유권(개설자) 검증 - 개설자 본인만 게시판을 삭제할 수 있다.
	BoardMasterVO existingBoard = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
	EgovAuthorizationHelper.assertOwner(existingBoard == null ? null : existingBoard.getFrstRegisterId());

	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    egovBBSMasterService.deleteBBSMasterInf(boardMaster);
	}
	// status.setComplete();
	return "forward:/cop/bbs/selectBBSMasterInfs.do";
    }

    /**
     * 포트릿을 위한 블로그 목록 정보를 조회한다.
     *
     * @param blogVO
     * @param model
     * @return
     */
    @RequestMapping("/cop/bbs/selectBlogListPortlet.do")
    public String selectBlogListPortlet(@ModelAttribute("searchVO") BlogVO blogVO, ModelMap model) {
	List<BlogVO> result = egovBBSMasterService.selectBlogListPortlet(blogVO);

	model.addAttribute("resultList", result);

	return "egovframework/com/cop/bbs/EgovBlogListPortlet";
    }

    /**
     * 포트릿을 위한 게시판 목록 정보를 조회한다.
     *
     * @param blogVO
     * @param model
     * @return
     */
    @RequestMapping("/cop/bbs/selectBBSListPortlet.do")
    public String selectBBSListPortlet(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) {
    	List<BoardMasterVO> result = egovBBSMasterService.selectBBSListPortlet(boardMasterVO);

    	model.addAttribute("resultList", result);

    	return "egovframework/com/cop/bbs/EgovBBSListPortlet";
    }



}
