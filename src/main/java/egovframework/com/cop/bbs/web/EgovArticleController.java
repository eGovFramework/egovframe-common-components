package egovframework.com.cop.bbs.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.EgovHtmlSanitizer;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovAttachmentGrants;
import egovframework.com.cmm.util.EgovAuthorizationHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.cop.bbs.service.EgovBBSSatisfactionService;
import egovframework.com.cop.cmt.service.CommentVO;
import egovframework.com.cop.cmt.service.EgovArticleCommentService;
import egovframework.com.cop.tpl.service.EgovTemplateManageService;
import egovframework.com.cop.tpl.service.TemplateInfVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * 게시물 관리를 위한 컨트롤러 클래스
 * 
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.19  이삼섭          최초 생성
 *   2009.06.29  한성곤          2단계 기능 추가 (댓글관리, 만족도조사)
 *   2011.07.01  안민정          댓글, 스크랩, 만족도 조사 기능의 종속성 제거
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2011.09.07  서준식          유효 게시판 게시일 지나도 게시물이 조회되던 오류 수정
 *   2016.06.13  김연호          표준프레임워크 3.6 개선
 *   2019.05.17  신용호          KISA 취약점 조치 및 보완
 *   2020.10.27  신용호          파일 업로드 수정 (multiRequest.getFiles)
 *   2022.11.11  김혜준          시큐어코딩 처리
 *   2024.10.29  이백행          게시판 검색조건 유지
 *   2024.10.29  inganyoyo     Transaction 처리 오류 수정(Article)
 *   2025.06.03  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidReassigningParameters(매개변수 재할당 방지)
 *
 *      </pre>
 */

@Controller
public class EgovArticleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovArticleController.class);

	@Resource(name = "EgovArticleService")
	private EgovArticleService egovArticleService;

	@Resource(name = "EgovBBSMasterService")
	private EgovBBSMasterService egovBBSMasterService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "EgovArticleCommentService")
	protected EgovArticleCommentService egovArticleCommentService;

	@Resource(name = "EgovBBSSatisfactionService")
	private EgovBBSSatisfactionService bbsSatisfactionService;

	@Resource(name = "EgovTemplateManageService")
	private EgovTemplateManageService egovTemplateManageService;

    //protected Logger log = Logger.getLogger(this.getClass());

	/**
	 * XSS 방지 처리.
	 * 
	 * @param data
	 * @return
	 */
	protected String unscript(String data) {
		if (data == null || data.trim().isEmpty()) {
			return "";
		}

		String ret = data;

		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
		ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");

		ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
		ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");

		ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
		ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");

		ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
		ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;/embed");

		ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
		ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;/form");

		return ret;
	}

	/**
	 * 게시물에 대한 목록을 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/cop/bbs/selectArticleList.do")
	public String selectArticleList(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); // KISA 보안취약점 조치 (2018-12-10, 이정은)

		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		BoardMasterVO vo = new BoardMasterVO();

		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		BoardMasterVO master = egovBBSMasterService.selectBBSMasterInf(vo);

		// 방명록은 방명록 게시판으로 이동
		if (master.getBbsTyCode().equals("BBST03")) {
			return "forward:/cop/bbs/selectGuestArticleList.do";
		}

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovArticleService.selectArticleList(boardVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		// 공지사항 추출
		List<BoardVO> noticeList = egovArticleService.selectNoticeArticleList(boardVO);

		paginationInfo.setTotalRecordCount(totCnt);

		// -------------------------------
		// 기본 BBS template 지정
		// -------------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}
		//// -----------------------------

		if (user != null) {
			model.addAttribute("sessionUniqId", user.getUniqId());
		}

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("articleVO", boardVO);
		model.addAttribute("boardMasterVO", master);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("noticeList", noticeList);
		return "egovframework/com/cop/bbs/EgovArticleList";
	}

	/**
	 * 게시물에 대한 상세 정보를 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/selectArticleDetail.do")
	public String selectArticleDetail(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); // KISA 보안취약점 조치 (2018-12-10, 이정은)

		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		boardVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		BoardVO vo = egovArticleService.selectArticleDetailNoCount(boardVO);

		model.addAttribute("result", vo);
		model.addAttribute("sessionUniqId", (user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		// 비밀글은 작성자만 볼수 있음
		EgovAuthorizationHelper.assertArticleReadable(vo.getSecretAt(), vo.getFrstRegisterId());

		// 조회수는 열람이 허용된 뒤에만 올린다(거부된 요청은 DB 를 바꾸지 않는다)
		egovArticleService.increaseInqireCo(boardVO);
		vo.setInqireCo(boardVO.getInqireCo());

		// ----------------------------
		// template 처리 (기본 BBS template 지정 포함)
		// ----------------------------
		BoardMasterVO master = new BoardMasterVO();

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		BoardMasterVO masterVo = egovBBSMasterService.selectBBSMasterInf(master);

		if (masterVo.getTmplatCours() == null || masterVo.getTmplatCours().equals("")) {
			masterVo.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		//// -----------------------------

		// ----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 만족도 조사 기능의 종속성 제거
		// ----------------------------
		if (egovArticleCommentService != null) {
			if (egovArticleCommentService.canUseComment(boardVO.getBbsId())) {
				model.addAttribute("useComment", "true");
			}
		}
		if (bbsSatisfactionService != null) {
			if (bbsSatisfactionService.canUseSatisfaction(boardVO.getBbsId())) {
				model.addAttribute("useSatisfaction", "true");
			}
		}
		//// --------------------------

		model.addAttribute("boardMasterVO", masterVo);

		return "egovframework/com/cop/bbs/EgovArticleDetail";
	}

	/**
	 * 게시물 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param boardVO
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/insertArticleView.do")
	public String insertArticleView(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		BoardMasterVO bdMstr = new BoardMasterVO();

		if (isAuthenticated) {

			BoardMasterVO vo = new BoardMasterVO();
			vo.setBbsId(boardVO.getBbsId());
			vo.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			bdMstr = egovBBSMasterService.selectBBSMasterInf(vo);
		}

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (bdMstr.getTmplatCours() == null || bdMstr.getTmplatCours().equals("")) {
			bdMstr.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("articleVO", boardVO);
		model.addAttribute("boardMasterVO", bdMstr);
		//// -----------------------------

		return "egovframework/com/cop/bbs/EgovArticleRegist";
	}

	/**
	 * 게시물을 등록한다.
	 * 
	 * @param boardVO
	 * @param board
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/insertArticle.do")
	public String insertArticle(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("bdMstr") BoardMaster bdMstr,
			@Valid @ModelAttribute("articleVO") BoardVO board, BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectAttributes) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) { // KISA 보안취약점 조치 (2018-12-10, 이정은)
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		if (bindingResult.hasErrors()) {

			BoardMasterVO master = new BoardMasterVO();

			master.setBbsId(boardVO.getBbsId());
			master.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			master = egovBBSMasterService.selectBBSMasterInf(master);

			// ----------------------------
			// 기본 BBS template 지정
			// ----------------------------
			if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
				master.setTmplatCours("css/egovframework/com/cop/tpl/egovBaseTemplate.css");
			}

			model.addAttribute("boardMasterVO", master);
			//// -----------------------------

			return "egovframework/com/cop/bbs/EgovArticleRegist";
		}

		// 2022.11.11 시큐어코딩 처리

		// final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		board.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		board.setBbsId(boardVO.getBbsId());
		// 블로그 게시판이면 블로그 개설자만 글을 쓴다. 블로그 ID 는 요청값이 아니라 게시판 마스터 값을 쓴다
		BoardMasterVO blogBoard = new BoardMasterVO();
		blogBoard.setBbsId(boardVO.getBbsId());
		String blogId = EgovStringUtil.isNullToString(egovBBSMasterService.selectBBSMasterInf(blogBoard).getBlogId());
		if (!blogId.isEmpty()) {
			BoardVO blogOwner = new BoardVO();
			blogOwner.setBlogId(blogId);
			blogOwner.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
			if (egovArticleService.selectLoginUser(blogOwner) == 0) {
				throw new EgovAccessDeniedException("블로그 개설자만 글을 쓸 수 있습니다.");
			}
		}
		board.setBlogId(blogId);

		// 익명등록 처리
		if (board.getAnonymousAt() != null && board.getAnonymousAt().equals("Y")) {
			board.setNtcrId("anonymous"); // 게시물 통계 집계를 위해 등록자 ID 저장
			board.setNtcrNm("익명"); // 게시물 통계 집계를 위해 등록자 Name 저장
			board.setFrstRegisterId("anonymous");

		} else {
			board.setNtcrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId()); // 게시물 통계 집계를 위해 등록자 ID
																									// 저장
			board.setNtcrNm((user == null || user.getName() == null) ? "" : user.getName()); // 게시물 통계 집계를 위해 등록자 Name
																								// 저장

		}

		board.setNttCn(unscript(board.getNttCn())); // XSS 방지
		egovArticleService.insertArticleAndFiles(board, files);

		if ("Y".equals(boardVO.getBlogAt())) {
			return "forward:/cop/bbs/selectArticleBlogList.do";
		} else {
			redirectAttributes.addAttribute("bbsId", boardVO.getBbsId());
			redirectAttributes.addAttribute("searchCnd", boardVO.getSearchCnd());
			redirectAttributes.addAttribute("searchWrd", boardVO.getSearchWrd());
			redirectAttributes.addAttribute("pageIndex", boardVO.getPageIndex());
			return "redirect:/cop/bbs/selectArticleList.do";
		}

	}

	/**
	 * 게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
	 * 
	 * @param boardVO
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/replyArticleView.do")
	public String addReplyBoardArticle(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();// KISA 보안취약점 조치 (2018-12-10, 이정은)

		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		BoardMasterVO master = new BoardMasterVO();
		BoardVO articleVO = new BoardVO();
		master.setBbsId(boardVO.getBbsId());
		master.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		master = egovBBSMasterService.selectBBSMasterInf(master);
		BoardVO result = EgovAuthorizationHelper.requireTarget(egovArticleService.selectArticleDetailNoCount(boardVO));
		// 답글 화면도 원글 제목·본문을 보여 주므로 비밀글은 작성자만 연다
		EgovAuthorizationHelper.assertArticleReadable(result.getSecretAt(), result.getFrstRegisterId());

		// Set initial reply title with RE: prefix
		articleVO.setNttSj("RE: " + (result.getNttSj() != null ? result.getNttSj() : ""));

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("boardMasterVO", master);
		model.addAttribute("result", result);

		model.addAttribute("articleVO", articleVO);

		if (result.getBlogAt().equals("chkBlog")) {
			return "egovframework/com/cop/bbs/EgovArticleBlogReply";
		} else {
			return "egovframework/com/cop/bbs/EgovArticleReply";
		}
	}

	/**
	 * 게시물에 대한 답변을 등록한다.
	 * 
	 * @param boardVO
	 * @param board
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/replyArticle.do")
	public String replyBoardArticle(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("bdMstr") BoardMaster bdMstr,
			@Valid @ModelAttribute("articleVO") BoardVO board, BindingResult bindingResult, ModelMap model) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) { // KISA 보안취약점 조치 (2018-12-10, 이정은)
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		BoardMasterVO master = new BoardMasterVO();

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		master = egovBBSMasterService.selectBBSMasterInf(master);

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		// 원글 조회(조회수 증가 없음) — 재표시 화면을 답변 진입 화면과 같게(블로그형/일반) 고른다
		BoardVO vo = egovArticleService.selectArticleDetailNoCount(boardVO);
		String replyView = (vo != null && "chkBlog".equals(vo.getBlogAt()))
				? "egovframework/com/cop/bbs/EgovArticleBlogReply" : "egovframework/com/cop/bbs/EgovArticleReply";

		if (bindingResult.hasErrors()) {
			model.addAttribute("boardMasterVO", master);
			// 재표시 화면의 hidden(parnts·sortOrdr·replyLc·nttId)은 제출된 요청값에 이미 있다.
			// selectArticleDetail 은 조회수를 올리므로(updateInqireCo) 여기서는 요청값을 그대로 쓴다.
			model.addAttribute("result", boardVO);
			//// -----------------------------

			return replyView;
		}

		// 인증된 권한 목록
		List<String> authList = EgovUserDetailsHelper.getAuthorities();
		// 관리자 권한 체크
		if (!authList.contains("ROLE_ADMIN")) {
			if (vo == null || "Y".equals(vo.getSecretAt())) {

				model.addAttribute("articleVO", boardVO);
				model.addAttribute("boardMasterVO", master);

				model.addAttribute("resultMsg", "errors.auth.invalid");

				return replyView;
			}
		}

		// 2022.11.11 시큐어코딩 처리
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		board.setReplyAt("Y");
		board.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		board.setBbsId(board.getBbsId());
		board.setParnts(Long.toString(boardVO.getNttId()));
		board.setSortOrdr(boardVO.getSortOrdr());
		board.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));

		// 익명등록 처리
		if (board.getAnonymousAt() != null && board.getAnonymousAt().equals("Y")) {
			board.setNtcrId("anonymous"); // 게시물 통계 집계를 위해 등록자 ID 저장
			board.setNtcrNm("익명"); // 게시물 통계 집계를 위해 등록자 Name 저장
			board.setFrstRegisterId("anonymous");

		} else {
			board.setNtcrId((user == null || user.getId() == null) ? "" : user.getId()); // 게시물 통계 집계를 위해 등록자 ID 저장
			board.setNtcrNm((user == null || user.getName() == null) ? "" : user.getName()); // 게시물 통계 집계를 위해 등록자 Name
																								// 저장

		}
		board.setNttCn(unscript(board.getNttCn())); // XSS 방지

		egovArticleService.insertArticleAndFiles(board, files);

		return "forward:/cop/bbs/selectArticleList.do";
	}

	/**
	 * 게시물 수정을 위한 수정페이지로 이동한다.
	 * 
	 * @param boardVO
	 * @param vo
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/updateArticleView.do")
	public String updateArticleView(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO vo,
			ModelMap model, HttpServletRequest request) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		boardVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		BoardMasterVO bmvo = new BoardMasterVO();
		BoardVO bdvo = new BoardVO();

		vo.setBbsId(boardVO.getBbsId());

		bmvo.setBbsId(boardVO.getBbsId());
		bmvo.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		if (isAuthenticated) {
			bmvo = egovBBSMasterService.selectBBSMasterInf(bmvo);
			bdvo = egovArticleService.selectArticleDetailNoCount(boardVO);
		}

		// 비밀글 포함 수정 폼은 작성자만 — 아래 작성자 검사가 비밀글 검사를 겸한다

		EgovAuthorizationHelper.assertOwner(bdvo == null ? null : bdvo.getFrstRegisterId());
		EgovAttachmentGrants.allowDelete(request, bdvo.getAtchFileId());

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (bmvo.getTmplatCours() == null || bmvo.getTmplatCours().equals("")) {
			bmvo.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		// 익명 등록글인 경우 수정 불가
		if (bdvo.getNtcrId().equals("anonymous")) {
			model.addAttribute("result", bdvo);
			model.addAttribute("boardMasterVO", bmvo);
			return "egovframework/com/cop/bbs/EgovArticleDetail";
		}

		model.addAttribute("articleVO", bdvo);
		model.addAttribute("boardMasterVO", bmvo);

		if ("chkBlog".equals(boardVO.getBlogAt())) {
			return "egovframework/com/cop/bbs/EgovArticleBlogUpdt";
		} else {
			return "egovframework/com/cop/bbs/EgovArticleUpdt";
		}

	}

	/**
	 * 게시물에 대한 내용을 수정한다.
	 * 
	 * @param boardVO
	 * @param board
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/updateArticle.do")
	public String updateBoardArticle(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("bdMstr") BoardMaster bdMstr,
			@Valid @ModelAttribute("articleVO") Board board, BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectAttributes) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) { // KISA 보안취약점 조치 (2018-12-10, 이정은)
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// --------------------------------------------------------------------------------------------
		// @ XSS 대응 권한체크 체크 START
		// param1 : 사용자고유ID(uniqId,esntlId)
		// --------------------------------------------------------
		LOGGER.debug("@ XSS 권한체크 START ----------------------------------------------");
		// step1 DB에서 해당 게시물의 uniqId 조회
		BoardVO vo = egovArticleService.selectArticleDetailNoCount(boardVO);

		// step2 작성자 본인인지 확인(EgovAuthorizationHelper.assertOwner)
		EgovAuthorizationHelper.assertOwner(vo == null ? null : vo.getFrstRegisterId());
		LOGGER.debug("@ XSS 권한체크 END ------------------------------------------------");
		// --------------------------------------------------------
		// @ XSS 대응 권한체크 체크 END
		// --------------------------------------------------------------------------------------------

		// IDOR 조치: 클라이언트가 전달한 atchFileId(boardVO.getAtchFileId())를 그대로 신뢰하지 않고,
		// 위에서 조회/소유권 검증을 마친 원본 게시물(vo)의 atchFileId만 사용하여
		// 다른 사용자의 첨부파일 그룹에 파일을 추가하지 못하도록 한다.
		String atchFileId = vo.getAtchFileId();
		// 저장 SQL 은 board 의 첨부 ID 를 기록하므로 board 도 원본 값으로 맞춘다(남의 첨부 ID 저장 → 삭제 허가 우회 차단)
		board.setAtchFileId(Objects.toString(atchFileId, ""));

		if (bindingResult.hasErrors()) {

			boardVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			BoardMasterVO bmvo = new BoardMasterVO();

			bmvo.setBbsId(boardVO.getBbsId());
			bmvo.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			bmvo = egovBBSMasterService.selectBBSMasterInf(bmvo);

			model.addAttribute("boardMasterVO", bmvo);

			return "egovframework/com/cop/bbs/EgovArticleUpdt";
		}

		board.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		board.setNtcrNm(""); // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
		board.setPassword(""); // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)

		board.setNttCn(unscript(board.getNttCn())); // XSS 방지

		// 2022.11.11 시큐어코딩 처리
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		egovArticleService.updateArticleAndFiles(board, files, atchFileId);

		// 2026.08.25 Spring 6 이관 조치 - ignoreDefaultModelOnRedirect 기본값이 true 로 바뀌어
		// model 속성이 redirect URL 로 승격되지 않는다. RedirectAttributes 로 명시 전달한다.
		redirectAttributes.addAttribute("bbsId", boardVO.getBbsId());
		redirectAttributes.addAttribute("searchCnd", boardVO.getSearchCnd());
		redirectAttributes.addAttribute("searchWrd", boardVO.getSearchWrd());
		redirectAttributes.addAttribute("pageIndex", boardVO.getPageIndex());

		return "redirect:/cop/bbs/selectArticleList.do";
	}

	/**
	 * 게시물에 대한 내용을 삭제한다.
	 * 
	 * @param boardVO
	 * @param board
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/deleteArticle.do")
	public String deleteBoardArticle(HttpServletRequest request, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("board") Board board, @ModelAttribute("bdMstr") BoardMaster bdMstr, ModelMap model,
			RedirectAttributes redirectAttributes) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		// --------------------------------------------------------------------------------------------
		// @ XSS 대응 권한체크 체크 START
		// param1 : 사용자고유ID(uniqId,esntlId)
		// --------------------------------------------------------
		LOGGER.debug("@ XSS 권한체크 START ----------------------------------------------");
		// step1 DB에서 해당 게시물의 uniqId 조회
		BoardVO vo = egovArticleService.selectArticleDetailNoCount(boardVO);

		// step2 작성자 본인인지 확인(EgovAuthorizationHelper.assertOwner)
		EgovAuthorizationHelper.assertOwner(vo == null ? null : vo.getFrstRegisterId());
		LOGGER.debug("@ XSS 권한체크 END ------------------------------------------------");
		// --------------------------------------------------------
		// @ XSS 대응 권한체크 체크 END
		// --------------------------------------------------------------------------------------------

		BoardVO bdvo = egovArticleService.selectArticleDetailNoCount(boardVO);
		// 익명 등록글인 경우 수정 불가
		if (bdvo.getNtcrId().equals("anonymous")) {
			model.addAttribute("result", bdvo);
			model.addAttribute("boardMasterVO", bdMstr);
			return "egovframework/com/cop/bbs/EgovArticleDetail";
		}

		if (isAuthenticated) {
			board.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
			// 삭제 폼(EgovArticleDetail.jsp의 formDelete)은 atchFileId를 전송하지 않아 요청 바인딩 값이
			// 비어 있다. 그대로 넘기면 deleteArticle의 첨부그룹 정리 분기가 실행되지 않으므로,
			// updateBoardArticle과 동일하게 조회·권한검증을 마친 원본(vo)의 값을 사용한다.
			board.setAtchFileId(vo.getAtchFileId());

			egovArticleService.deleteArticle(board);
		}

		if (boardVO.getBlogAt().equals("chkBlog")) {
			return "forward:/cop/bbs/selectArticleBlogList.do";
		} else {
			// 2026.08.25 Spring 6 이관 조치 - RedirectAttributes 로 명시 전달
			redirectAttributes.addAttribute("bbsId", boardVO.getBbsId());
			redirectAttributes.addAttribute("searchCnd", boardVO.getSearchCnd());
			redirectAttributes.addAttribute("searchWrd", boardVO.getSearchWrd());
			redirectAttributes.addAttribute("pageIndex", boardVO.getPageIndex());
			return "redirect:/cop/bbs/selectArticleList.do";
		}
	}

	/**
	 * 방명록에 대한 목록을 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/cop/bbs/selectGuestArticleList.do")
	public String selectGuestArticleList(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) { // KISA 보안취약점 조치 (2018-12-10, 이정은)
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 수정 및 삭제 기능 제어를 위한 처리
		model.addAttribute("sessionUniqId", (user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		BoardVO vo = new BoardVO();

		vo.setBbsId(boardVO.getBbsId());
		vo.setBbsNm(boardVO.getBbsNm());
		vo.setNtcrNm((user == null || user.getName() == null) ? "" : user.getName());
		vo.setNtcrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		BoardMasterVO masterVo = new BoardMasterVO();

		masterVo.setBbsId(vo.getBbsId());
		masterVo.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		BoardMasterVO mstrVO = egovBBSMasterService.selectBBSMasterInf(masterVo);

		vo.setPageIndex(boardVO.getPageIndex());
		vo.setPageUnit(propertyService.getInt("pageUnit"));
		vo.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovArticleService.selectGuestArticleList(vo);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("user", user);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("boardMasterVO", mstrVO);
		model.addAttribute("articleVO", vo);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/bbs/EgovGuestArticleList";
	}

	/**
	 * 방명록에 대한 내용을 등록한다.
	 * 
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/insertGuestArticle.do")
	public String insertGuestList(@ModelAttribute("searchVO") BoardVO boardVO, @Valid @ModelAttribute("Board") Board board,
			BindingResult bindingResult, ModelMap model) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) { // KISA 보안취약점 조치 (2018-12-10, 이정은)
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 방명록 글(비밀번호 방식)은 방명록 게시판에만 쓴다
		BoardMasterVO guestKey = new BoardMasterVO();
		guestKey.setBbsId(board.getBbsId());
		guestKey.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		BoardMasterVO guestMaster = egovBBSMasterService.selectBBSMasterInf(guestKey);
		if (guestMaster == null || !"BBST03".equals(guestMaster.getBbsTyCode())) {
			throw new EgovAccessDeniedException("방명록 게시판이 아닙니다.");
		}

		if (bindingResult.hasErrors()) {

			BoardVO vo = new BoardVO();

			vo.setBbsId(boardVO.getBbsId());
			vo.setBbsNm(boardVO.getBbsNm());
			vo.setNtcrNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));
			vo.setNtcrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			BoardMasterVO masterVo = new BoardMasterVO();

			masterVo.setBbsId(vo.getBbsId());
			masterVo.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			BoardMasterVO mstrVO = egovBBSMasterService.selectBBSMasterInf(masterVo);

			vo.setPageUnit(propertyService.getInt("pageUnit"));
			vo.setPageSize(propertyService.getInt("pageSize"));

			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getPageIndex());
			paginationInfo.setRecordCountPerPage(vo.getPageUnit());
			paginationInfo.setPageSize(vo.getPageSize());

			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			Map<String, Object> map = egovArticleService.selectGuestArticleList(vo);
			int totCnt = Integer.parseInt((String) map.get("resultCnt"));

			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("boardMasterVO", mstrVO);
			model.addAttribute("articleVO", vo);
			model.addAttribute("paginationInfo", paginationInfo);

			return "egovframework/com/cop/bbs/EgovGuestArticleList";

		}

		// 2022.11.11 시큐어코딩 처리
		board.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		egovArticleService.insertArticleAndFiles(board, null);

		boardVO.setNttCn("");
		boardVO.setPassword("");
		boardVO.setNtcrId("");
		boardVO.setNttId((long) 0);

		return "forward:/cop/bbs/selectGuestArticleList.do";
	}

	/**
	 * 방명록에 대한 내용을 삭제한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/deleteGuestArticle.do")
	public String deleteGuestList(HttpServletRequest request, @ModelAttribute("searchVO") BoardVO boardVO,
			@Valid @ModelAttribute("articleVO") Board board, ModelMap model) {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (isAuthenticated) {
			BoardVO vo = egovArticleService.selectArticleDetailNoCount(boardVO);
			EgovAuthorizationHelper.assertOwner(vo == null ? null : vo.getFrstRegisterId());

			// 익명 게시글은 작성비밀번호가 유일한 인가 수단이므로 서버측에서 반드시 검증한다.
			if (vo.getPassword() == null || board.getPassword() == null
					|| !vo.getPassword().equals(board.getPassword())) {
				model.addAttribute("msg", egovMessageSource.getMessage("cop.password.not.same.msg"));
				return "forward:/cop/bbs/selectGuestArticleList.do";
			}

			// 첨부그룹 정리는 요청값이 아니라 권한검증을 마친 원본(vo)의 첨부 ID 로 한다(남의 첨부 삭제 차단)
			boardVO.setAtchFileId(vo.getAtchFileId());
			egovArticleService.deleteArticle(boardVO);
		}

		return "forward:/cop/bbs/selectGuestArticleList.do";
	}

	/**
	 * 방명록 수정을 위한 특정 내용을 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/updateGuestArticleView.do")
	public String updateGuestArticleView(HttpServletRequest request, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("boardMasterVO") BoardMasterVO brdMstrVO, ModelMap model) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) { // KISA 보안취약점 조치 (2018-12-10, 이정은)
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 수정 및 삭제 기능 제어를 위한 처리
		model.addAttribute("sessionUniqId", (user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		BoardVO vo = egovArticleService.selectArticleDetailNoCount(boardVO);
		EgovAuthorizationHelper.assertOwner(vo == null ? null : vo.getFrstRegisterId());

		boardVO.setBbsId(boardVO.getBbsId());
		boardVO.setBbsNm(boardVO.getBbsNm());
		boardVO.setNtcrNm((user == null || user.getName() == null) ? "" : user.getName());

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovArticleService.selectGuestArticleList(boardVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("articleVO", vo);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/bbs/EgovGuestArticleList";
	}

	/**
	 * 방명록을 수정하고 게시판 메인페이지를 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/updateGuestArticle.do")
	public String updateGuestArticle(HttpServletRequest request, @ModelAttribute("searchVO") BoardVO boardVO,
			@Valid @ModelAttribute Board board, BindingResult bindingResult, ModelMap model) {

		// BBST02, BBST04
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) { // KISA 보안취약점 조치 (2018-12-10, 이정은)
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		BoardVO article = egovArticleService.selectArticleDetailNoCount(boardVO);
		EgovAuthorizationHelper.assertOwner(article == null ? null : article.getFrstRegisterId());

		if (bindingResult.hasErrors()) {

			BoardVO vo = new BoardVO();

			vo.setBbsId(boardVO.getBbsId());
			vo.setBbsNm(boardVO.getBbsNm());
			vo.setNtcrNm((user == null || user.getName() == null) ? "" : user.getName());
			vo.setNtcrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			BoardMasterVO masterVo = new BoardMasterVO();

			masterVo.setBbsId(vo.getBbsId());
			masterVo.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			BoardMasterVO mstrVO = egovBBSMasterService.selectBBSMasterInf(masterVo);

			vo.setPageUnit(propertyService.getInt("pageUnit"));
			vo.setPageSize(propertyService.getInt("pageSize"));

			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getPageIndex());
			paginationInfo.setRecordCountPerPage(vo.getPageUnit());
			paginationInfo.setPageSize(vo.getPageSize());

			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			Map<String, Object> map = egovArticleService.selectGuestArticleList(vo);
			int totCnt = Integer.parseInt((String) map.get("resultCnt"));

			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("boardMasterVO", mstrVO);
			model.addAttribute("articleVO", vo);
			model.addAttribute("paginationInfo", paginationInfo);

			return "egovframework/com/cop/bbs/EgovGuestArticleList";
		}

		// 익명 게시글은 작성비밀번호가 유일한 인가 수단이므로 서버측에서 반드시 검증한다.
		BoardVO storedVo = egovArticleService.selectArticleDetailNoCount(boardVO);
		if (storedVo.getPassword() == null || board.getPassword() == null
				|| !storedVo.getPassword().equals(board.getPassword())) {
			model.addAttribute("msg", egovMessageSource.getMessage("cop.password.not.same.msg"));
			return "forward:/cop/bbs/selectGuestArticleList.do";
		}

		// 저장 SQL 은 board 의 첨부 ID 를 기록하므로 원본 값으로 맞춘다(남의 첨부 ID 저장 → 삭제 허가 우회 차단)
		board.setAtchFileId(Objects.toString(article.getAtchFileId(), ""));

		// 2022.11.11 시큐어코딩 처리
		egovArticleService.updateArticle(board);
		boardVO.setNttCn("");
		boardVO.setPassword("");
		boardVO.setNtcrId("");
		boardVO.setNttId((long) 0);

		return "forward:/cop/bbs/selectGuestArticleList.do";
	}

	/*********************
	 * 블로그관련
	 ********************/

	/**
	 * 블로그 게시판에 대한 목록을 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/cop/bbs/selectArticleBlogList.do")
	public String selectArticleBlogList(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); // KISA 보안취약점 조치 (2018-12-10, 이정은)

		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		BlogVO blogVo = new BlogVO();
		blogVo.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		blogVo.setBbsId(boardVO.getBbsId());
		blogVo.setBlogId(boardVO.getBlogId());
		BlogVO master = egovBBSMasterService.selectBlogDetail(blogVo);

		boardVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		// 블로그 카테고리관리 권한(로그인 한 사용자만 가능)
		int loginUserCnt = egovArticleService.selectLoginUser(boardVO);

		// 블로그 게시판 제목 추출
		List<BoardVO> blogNameList = egovArticleService.selectBlogNmList(boardVO);

		// 2022.11.11 시큐어코딩 처리
		model.addAttribute("sessionUniqId", (user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		model.addAttribute("articleVO", boardVO);
		model.addAttribute("boardMasterVO", master);
		model.addAttribute("blogNameList", blogNameList);
		model.addAttribute("loginUserCnt", loginUserCnt);

		return "egovframework/com/cop/bbs/EgovArticleBlogList";
	}

	/**
	 * 블로그 게시물에 대한 상세 타이틀을 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/selectArticleBlogDetail.do")
	public ModelAndView selectArticleBlogDetail(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); // KISA 보안취약점 조치 (2018-12-10, 이정은)

		if (!isAuthenticated) {
			throw new BaseRuntimeException("Login Required!");
		}

		BoardVO vo = new BoardVO();

		boardVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<BoardVO> blogSubJectList = egovArticleService.selectArticleDetailDefault(boardVO);
		vo = egovArticleService.selectArticleCnOne(boardVO);

		int totCnt = egovArticleService.selectArticleDetailDefaultCnt(boardVO);
		paginationInfo.setTotalRecordCount(totCnt);

		// 비밀글 본문은 작성자에게만 내려준다(제목 목록은 그대로 둔다).
		// 화면이 본문을 HTML 로 그리므로 게시글 상세(egovc:sanitizeHtml)와 같이 허용 태그만 남긴다
		for (BoardVO item : blogSubJectList) {
			if (!EgovAuthorizationHelper.isArticleReadable(item.getSecretAt(), item.getFrstRegisterId())) {
				item.setNttCn("");
			} else {
				item.setNttCn(EgovHtmlSanitizer.sanitize(item.getNttCn()));
			}
		}
		if (!EgovAuthorizationHelper.isArticleReadable(vo.getSecretAt(), vo.getFrstRegisterId())) {
			vo.setNttCn("");
		} else if (vo.getNttCn() != null) {
			vo.setNttCn(EgovHtmlSanitizer.sanitize(vo.getNttCn()));
		}

		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("blogSubJectList", blogSubJectList);
		mav.addObject("paginationInfo", paginationInfo);

		if (vo.getNttCn() != null) {
			mav.addObject("blogCnOne", vo);
		}
		return mav;
	}

	/**
	 * 블로그 게시물에 대한 상세 내용을 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@PostMapping("/cop/bbs/selectArticleBlogDetailCn.do")
	public ModelAndView selectArticleBlogDetailCn(@ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("commentVO") CommentVO commentVO, ModelMap model) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		boardVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); // KISA 보안취약점 조치 (2018-12-10, 이정은)

		if (!isAuthenticated) {
			throw new BaseRuntimeException("Login Required!");
		}

		BoardVO vo = egovArticleService.selectArticleDetailNoCount(boardVO);

		// 비밀글은 작성자만 볼수 있음 — 조회수를 올리거나 본문을 읽기 전에 거부한다
		EgovAuthorizationHelper.assertArticleReadable(vo.getSecretAt(), vo.getFrstRegisterId());

		egovArticleService.increaseInqireCo(boardVO);
		vo.setInqireCo(boardVO.getInqireCo());

		// ----------------------------
		// 댓글 처리
		// ----------------------------
		CommentVO articleCommentVO = new CommentVO();
		commentVO.setWrterNm((user == null || user.getName() == null) ? "" : user.getName());

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(commentVO.getSubPageIndex());
		paginationInfo.setRecordCountPerPage(commentVO.getSubPageUnit());
		paginationInfo.setPageSize(commentVO.getSubPageSize());

		commentVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
		commentVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
		commentVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovArticleCommentService.selectArticleCommentList(commentVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		// 댓글 처리 END
		// ----------------------------

		List<BoardVO> blogCnList = egovArticleService.selectArticleDetailCn(boardVO);
		for (BoardVO item : blogCnList) {
			item.setNttCn(EgovHtmlSanitizer.sanitize(item.getNttCn()));
			item.setPassword(null); // 작성 비밀번호는 응답에 싣지 않는다
		}
		vo.setPassword(null);
		ModelAndView mav = new ModelAndView("jsonView");

		// 수정 처리된 후 댓글 등록 화면으로 처리되기 위한 구현
		if (commentVO.isModified()) {
			commentVO.setCommentNo("");
			commentVO.setCommentCn("");
		}

		// 수정을 위한 처리
		if (commentVO.getCommentNo() != null && !"".equals(commentVO.getCommentNo())) {
			mav.setViewName("forward:/cop/cmt/updateArticleCommentView.do");
		}

		mav.addObject("blogCnList", blogCnList);
		mav.addObject("resultUnder", vo);
		mav.addObject("paginationInfo", paginationInfo);
		mav.addObject("resultList", map.get("resultList"));
		mav.addObject("resultCnt", map.get("resultCnt"));
		mav.addObject("articleCommentVO", articleCommentVO); // validator 용도

		commentVO.setCommentCn(""); // 등록 후 댓글 내용 처리

		return mav;

	}

	/**
	 * 개인블로그 관리
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/cop/bbs/selectBlogListManager.do")
	public String selectBlogMasterList(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		boardVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		Map<String, Object> map = egovArticleService.selectBlogListManager(boardVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/bbs/EgovBlogListManager";
	}

	/**
	 * 템플릿에 대한 미리보기용 게시물 목록을 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/cop/bbs/previewBoardList.do")
	public String previewBoardArticles(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {
		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String template = boardVO.getSearchWrd(); // 템플릿 URL

		BoardMasterVO master = new BoardMasterVO();

		master.setBbsNm("미리보기 게시판");

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		BoardVO target = null;
		List<BoardVO> list = new ArrayList<>();

		target = new BoardVO();
		target.setNttSj("게시판 기능 설명");
		target.setFrstRegisterId("ID");
		target.setFrstRegisterNm("관리자");
		target.setFrstRegisterPnttm("2019-01-01");
		target.setInqireCo(7);
		target.setParnts("0");
		target.setReplyAt("N");
		target.setReplyLc("0");
		target.setUseAt("Y");

		list.add(target);

		target = new BoardVO();
		target.setNttSj("게시판 부가 기능 설명");
		target.setFrstRegisterId("ID");
		target.setFrstRegisterNm("관리자");
		target.setFrstRegisterPnttm("2019-01-01");
		target.setInqireCo(7);
		target.setParnts("0");
		target.setReplyAt("N");
		target.setReplyLc("0");
		target.setUseAt("Y");

		list.add(target);

		boardVO.setSearchWrd("");

		int totCnt = list.size();

		// 공지사항 추출
		List<BoardVO> noticeList = egovArticleService.selectNoticeArticleList(boardVO);

		paginationInfo.setTotalRecordCount(totCnt);

		master.setTmplatCours(template);

		model.addAttribute("resultList", list);
		model.addAttribute("resultCnt", Integer.toString(totCnt));
		model.addAttribute("articleVO", boardVO);
		model.addAttribute("boardMasterVO", master);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("noticeList", noticeList);

		model.addAttribute("preview", "true");

		return "egovframework/com/cop/bbs/EgovArticleList";
	}

	/**
	 * 미리보기 커뮤니티 메인페이지를 조회한다.
	 * 
	 * @param cmmntyVO
	 * @param sessionVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/cop/bbs/previewBlogMainPage.do")
	public String previewBlogMainPage(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); // KISA 보안취약점 조치 (2018-12-10, 이정은)

		if (!isAuthenticated) {
			throw new BaseRuntimeException("Login Required!");
		}

		String tmplatCours = boardVO.getSearchWrd();

		BlogVO master = new BlogVO();
		master.setBlogNm("미리보기 블로그");
		master.setBlogIntrcn("미리보기를 위한 블로그입니다.");
		master.setUseAt("Y");
		master.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		boardVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		// 블로그 카테고리관리 권한(로그인 한 사용자만 가능)
		int loginUserCnt = egovArticleService.selectLoginUser(boardVO);

		// 블로그 게시판 제목 추출
		List<BoardVO> blogNameList = new ArrayList<>();

		BoardVO target = null;
		target = new BoardVO();
		target.setBbsNm("블로그게시판#1");

		blogNameList.add(target);

		if (user != null) {
			model.addAttribute("sessionUniqId", user.getUniqId());
		}

		model.addAttribute("articleVO", boardVO);
		model.addAttribute("boardMasterVO", master);
		model.addAttribute("blogNameList", blogNameList);
		model.addAttribute("loginUserCnt", loginUserCnt);

		model.addAttribute("preview", "true");

		// 안전한 경로 문자열로 조치
		tmplatCours = EgovWebUtil.filePathBlackList(tmplatCours);
		if (tmplatCours == null) {
			tmplatCours = "";
		}

		// 뷰 이름 인젝션 방지 - 커뮤니티 미리보기와 같은 기준. forward:/redirect: 등 콜론이 포함된 값,
		// 화이트리스트 등록값이라도 WEB-INF 등 애플리케이션 내부 자원을 가리키는 값은 뷰 이름으로 사용할 수 없다.
		if (tmplatCours.contains(":") || tmplatCours.startsWith("/") || tmplatCours.toUpperCase(Locale.ROOT).contains("WEB-INF")) {
			LOGGER.debug("Template > Unsafe tmplatCours rejected: {}", tmplatCours);
			return "egovframework/com/cmm/error/egovError";
		}

		// 화이트 리스트 체크
		List<TemplateInfVO> templateWhiteList = egovTemplateManageService.selectTemplateWhiteList();
		LOGGER.debug("Template > WhiteList Count = {}", templateWhiteList.size());
		for (TemplateInfVO templateInfVO : templateWhiteList) {
			LOGGER.debug("Template > whiteList TmplatCours = " + templateInfVO.getTmplatCours());
			if (tmplatCours.equals(templateInfVO.getTmplatCours())) {
				return tmplatCours;
			}
		}

		LOGGER.debug("Template > WhiteList mismatch! Please check Admin page!");
		return "egovframework/com/cmm/error/egovError";
	}

}
