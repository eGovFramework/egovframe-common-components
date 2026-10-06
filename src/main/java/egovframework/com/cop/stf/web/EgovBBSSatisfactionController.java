package egovframework.com.cop.stf.web;

import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cmm.util.EgovAuthorizationHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.EgovBBSSatisfactionService;
import egovframework.com.cop.bbs.service.Satisfaction;
import egovframework.com.cop.bbs.service.SatisfactionVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 만족도 서비스 컨트롤러 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.29  한성곤          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class EgovBBSSatisfactionController {

	@Resource(name="EgovBBSSatisfactionService")
    protected EgovBBSSatisfactionService bbsSatisfactionService;

    @Resource(name = "EgovArticleService")
    private EgovArticleService egovArticleService;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    //Logger log = Logger.getLogger(this.getClass());

    /**
     * 만족도조사 목록 조회를 제공한다.
     *
     * @param boardVO
     * @param model
     * @return
     */
    @RequestMapping("/cop/stf/selectSatisfactionList.do")
    public String selectSatisfactionList(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, ModelMap model) {

	// 수정 처리된 후 만족도조사 등록 화면으로 처리되기 위한 구현
	if (satisfactionVO.isModified()) {
	    satisfactionVO.setStsfdgNo("");
	    satisfactionVO.setStsfdgCn("");
	    satisfactionVO.setStsfdg(0);
	}

	// 수정을 위한 처리
	if (!satisfactionVO.getStsfdgNo().equals("")) {
	    return "forward:/cop/stf/selectSingleSatisfaction.do";
	}

	//------------------------------------------
	// JSP의 <head> 부분 처리 (javascript 생성)
	//------------------------------------------
	model.addAttribute("type", satisfactionVO.getType());	// head or body

	if (satisfactionVO.getType().equals("head")) {
	    return "egovframework/com/cop/stf/EgovSatisfactionList";
	}
	////----------------------------------------

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	// 부모 글이 비밀글이면 작성자만 — 게시글 상세와 같은 검사. 조회수는 올리지 않는다
	BoardVO parent = new BoardVO();
	parent.setBbsId(satisfactionVO.getBbsId());
	parent.setNttId(satisfactionVO.getNttId());
	parent = egovArticleService.selectArticleDetailNoCount(parent);
	if (parent != null) {
		EgovAuthorizationHelper.assertArticleReadable(parent.getSecretAt(), parent.getFrstRegisterId());
	}

	model.addAttribute("sessionUniqId", user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

	satisfactionVO.setWrterNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));

	satisfactionVO.setSubPageUnit(propertyService.getInt("pageUnit"));
	satisfactionVO.setSubPageSize(propertyService.getInt("pageSize"));

	PaginationInfo paginationInfo = new PaginationInfo();
	paginationInfo.setCurrentPageNo(satisfactionVO.getSubPageIndex());
	paginationInfo.setRecordCountPerPage(satisfactionVO.getSubPageUnit());
	paginationInfo.setPageSize(satisfactionVO.getSubPageSize());

	satisfactionVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
	satisfactionVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
	satisfactionVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	Map<String, Object> map = bbsSatisfactionService.selectSatisfactionList(satisfactionVO);
	int totCnt = Integer.parseInt((String)map.get("resultCnt"));

	paginationInfo.setTotalRecordCount(totCnt);

	model.addAttribute("resultList", map.get("resultList"));
	model.addAttribute("resultCnt", map.get("resultCnt"));
	model.addAttribute("summary", map.get("summary"));
	model.addAttribute("paginationInfo", paginationInfo);

	satisfactionVO.setStsfdgCn("");	// 등록 후 만족도 내용 처리
	satisfactionVO.setStsfdg(0);	// 등록 후 만족도 처리

	return "egovframework/com/cop/stf/EgovSatisfactionList";
    }

    /**
     * 익명용 만족도조사 목록 조회를 제공한다.
     *
     * @param boardVO
     * @param model
     * @return
     *
     * @deprecated 이 경로를 부르는 화면이 없다. 익명 게시판 기능이 빠지면서 이동 대상(/cop/bbs/anonymous/*)도 없어져 동작하지 않는다. 삭제 예정.
     */
    @Deprecated(forRemoval = true)
    @RequestMapping("/cop/stf/anonymous/selectSatisfactionList.do")
    public String selectAnonymousSatisfactionList(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, ModelMap model) {

	// 수정 처리된 후 만족도조사 등록 화면으로 처리되기 위한 구현
	if (satisfactionVO.isModified()) {
	    satisfactionVO.setStsfdgNo("");
	    satisfactionVO.setStsfdgCn("");
	    satisfactionVO.setStsfdg(0);
	    satisfactionVO.setWrterNm("");
	}

	// 수정을 위한 처리
	if (!"".equals(satisfactionVO.getStsfdgNo())) {
	    return "forward:/cop/stf/anonymous/selectSingleSatisfaction.do";
	}

	//------------------------------------------
	// JSP의 <head> 부분 처리 (javascript 생성)
	//------------------------------------------
	model.addAttribute("type", satisfactionVO.getType());	// head or body

	if ("head".equals(satisfactionVO.getType())) {
	    return "egovframework/com/cop/stf/EgovSatisfactionList";
	}
	////----------------------------------------

	// 부모 글이 비밀글이면 작성자만 — 게시글 상세와 같은 기준(익명 경로도 같다)
	BoardVO parent = new BoardVO();
	parent.setBbsId(satisfactionVO.getBbsId());
	parent.setNttId(satisfactionVO.getNttId());
	parent = egovArticleService.selectArticleDetailNoCount(parent);
	if (parent != null) {
		EgovAuthorizationHelper.assertArticleReadable(parent.getSecretAt(), parent.getFrstRegisterId());
	}

	model.addAttribute("anonymous", "true");

	satisfactionVO.setSubPageUnit(propertyService.getInt("pageUnit"));
	satisfactionVO.setSubPageSize(propertyService.getInt("pageSize"));

	PaginationInfo paginationInfo = new PaginationInfo();
	paginationInfo.setCurrentPageNo(satisfactionVO.getSubPageIndex());
	paginationInfo.setRecordCountPerPage(satisfactionVO.getSubPageUnit());
	paginationInfo.setPageSize(satisfactionVO.getSubPageSize());

	satisfactionVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
	satisfactionVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
	satisfactionVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	Map<String, Object> map = bbsSatisfactionService.selectSatisfactionList(satisfactionVO);
	int totCnt = Integer.parseInt((String)map.get("resultCnt"));

	paginationInfo.setTotalRecordCount(totCnt);

	model.addAttribute("resultList", map.get("resultList"));
	model.addAttribute("resultCnt", map.get("resultCnt"));
	model.addAttribute("summary", map.get("summary"));
	model.addAttribute("paginationInfo", paginationInfo);

	satisfactionVO.setWrterNm("");
	satisfactionVO.setStsfdgCn("");	// 등록 후 만족도 내용 처리
	satisfactionVO.setStsfdg(0);	// 등록 후 만족도 처리

	return "egovframework/com/cop/stf/EgovSatisfactionList";
    }

    /**
     * 만족도조사를 등록한다.
     *
     * @param satisfactionVO
     * @param satisfaction
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/cop/stf/insertSatisfaction.do")
    public String insertSatisfaction(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, @Valid @ModelAttribute("satisfaction") Satisfaction satisfaction,
	    BindingResult bindingResult, ModelMap model) {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (bindingResult.hasErrors()) {
		    model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");

		    return "forward:/cop/bbs/selectBoardArticle.do";
		}

		if (isAuthenticated) {
		    // 부모 글이 비밀글이면 작성자만 — 게시글 상세와 같은 검사. 조회수는 올리지 않는다
		    BoardVO parent = new BoardVO();
		    parent.setBbsId(satisfaction.getBbsId());
		    parent.setNttId(satisfaction.getNttId());
		    parent = egovArticleService.selectArticleDetailNoCount(parent);
		    if (parent != null) {
		    	EgovAuthorizationHelper.assertArticleReadable(parent.getSecretAt(), parent.getFrstRegisterId());
		    }
		    satisfaction.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    satisfaction.setWrterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

		    satisfaction.setStsfdgPassword("");	// dummy

		    bbsSatisfactionService.insertSatisfaction(satisfaction);

		    satisfactionVO.setStsfdgCn("");
		    satisfactionVO.setStsfdgNo("");
		    satisfactionVO.setStsfdg(0);
		}

		return "forward:/cop/bbs/selectArticleDetail.do";
    }

    /**
     * 익명 만족도조사를 등록한다.
     *
     * @param satisfactionVO
     * @param satisfaction
     * @param bindingResult
     * @param model
     * @return
     *
     * @deprecated 이 경로를 부르는 화면이 없다. 익명 게시판 기능이 빠지면서 이동 대상(/cop/bbs/anonymous/*)도 없어져 동작하지 않는다. 삭제 예정.
     */
    @Deprecated(forRemoval = true)
    @PostMapping("/cop/stf/anonymous/insertSatisfaction.do")
    public String insertAnonymousSatisfaction(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, @Valid @ModelAttribute("satisfaction") Satisfaction satisfaction,
	    BindingResult bindingResult, ModelMap model) {

		if (bindingResult.hasErrors()) {
		    model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");

		    return "forward:/cop/stf/anonymous/selectBoardArticle.do";
		}

		// 부모 글이 비밀글이면 작성자만 — 게시글 상세와 같은 기준(익명 경로도 같다)
		BoardVO parent = new BoardVO();
		parent.setBbsId(satisfaction.getBbsId());
		parent.setNttId(satisfaction.getNttId());
		parent = egovArticleService.selectArticleDetailNoCount(parent);
		if (parent != null) {
			EgovAuthorizationHelper.assertArticleReadable(parent.getSecretAt(), parent.getFrstRegisterId());
		}
		satisfaction.setFrstRegisterId("ANONYMOUS");
		satisfaction.setWrterId("");
		satisfaction.setStsfdgPassword(EgovFileScrty.encryptPassword(satisfaction.getStsfdgPassword(), satisfaction.getStsfdgNo()));

		bbsSatisfactionService.insertSatisfaction(satisfaction);

		satisfactionVO.setStsfdgNo("");
		satisfactionVO.setStsfdgCn("");
		satisfactionVO.setStsfdg(0);
		satisfactionVO.setWrterNm("");

		return "forward:/cop/bbs/anonymous/selectArticleDetail.do";
    }

    /**
     * 만족도조사를 삭제한다.
     *
     * @param satisfactionVO
     * @param satisfaction
     * @param model
     * @return
     */
    @PostMapping("/cop/stf/deleteSatisfaction.do")
    public String deleteSatisfaction(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, @ModelAttribute("satisfaction") Satisfaction satisfaction, ModelMap model) {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	if (isAuthenticated) {
	    // 작성자 본인만 삭제 가능하도록 소유권 검증 (회원 만족도조사는 로그인 사용자 명의로만 등록됨)
	    Satisfaction stored = bbsSatisfactionService.selectSatisfaction(satisfactionVO);
	    String loginUniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
	    if (stored == null || stored.getFrstRegisterId() == null
		    || !stored.getFrstRegisterId().equals(loginUniqId)) {
		model.addAttribute("subMsg", egovMessageSource.getMessage("errors.xss.checkerUser"));
		return "forward:/cop/bbs/selectArticleDetail.do";
	    }

	    bbsSatisfactionService.deleteSatisfaction(satisfactionVO);
	}

	satisfactionVO.setStsfdgCn("");
	satisfactionVO.setStsfdgNo("");
	satisfactionVO.setStsfdg(0);

	return "forward:/cop/bbs/selectArticleDetail.do";
    }

    /**
     * 익명 만족도조사를 삭제한다.
     *
     * @param satisfactionVO
     * @param satisfaction
     * @param model
     * @return
     *
     * @deprecated 이 경로를 부르는 화면이 없다. 익명 게시판 기능이 빠지면서 이동 대상(/cop/bbs/anonymous/*)도 없어져 동작하지 않는다. 삭제 예정.
     */
    @Deprecated(forRemoval = true)
    @PostMapping("/cop/stf/anonymous/deleteSatisfaction.do")
    public String deleteAnonymousSatisfaction(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, @ModelAttribute("satisfaction") Satisfaction satisfaction, ModelMap model) {

	//-------------------------------
	// 패스워드 비교
	//-------------------------------
	String dbpassword = bbsSatisfactionService.getSatisfactionPassword(satisfactionVO);
	String enpassword = EgovFileScrty.encryptPassword(satisfactionVO.getConfirmPassword(), satisfaction.getStsfdgNo());

	if (!dbpassword.equals(enpassword)) {

	    model.addAttribute("subMsg", egovMessageSource.getMessage("cop.password.not.same.msg"));

	    return "forward:/cop/bbs/anonymous/selectArticleDetail.do";
	}
	////-----------------------------

	bbsSatisfactionService.deleteSatisfaction(satisfactionVO);

	satisfactionVO.setStsfdgNo("");
	satisfactionVO.setStsfdgCn("");
	satisfactionVO.setStsfdg(0);
	satisfactionVO.setWrterNm("");

	return "forward:/cop/bbs/anonymous/selectBoardArticle.do";
    }

    /**
     * 만족도조사 수정 페이지로 이동한다.
     *
     * @param satisfactionVO
     * @param model
     * @return
     */
    @RequestMapping("/cop/stf/selectSingleSatisfaction.do")
    public String selectSingleSatisfaction(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, ModelMap model) {

	//------------------------------------------
	// JSP의 <head> 부분 처리 (javascript 생성)
	//------------------------------------------
	model.addAttribute("type", satisfactionVO.getType());	// head or body

	if ("head".equals(satisfactionVO.getType())) {
	    return "egovframework/com/cop/stf/EgovSatisfactionList";
	}
	////----------------------------------------

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	satisfactionVO.setWrterNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));

	satisfactionVO.setSubPageUnit(propertyService.getInt("pageUnit"));
	satisfactionVO.setSubPageSize(propertyService.getInt("pageSize"));

	PaginationInfo paginationInfo = new PaginationInfo();
	paginationInfo.setCurrentPageNo(satisfactionVO.getSubPageIndex());
	paginationInfo.setRecordCountPerPage(satisfactionVO.getSubPageUnit());
	paginationInfo.setPageSize(satisfactionVO.getSubPageSize());

	satisfactionVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
	satisfactionVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
	satisfactionVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	Map<String, Object> map = bbsSatisfactionService.selectSatisfactionList(satisfactionVO);
	int totCnt = Integer.parseInt((String)map.get("resultCnt"));

	paginationInfo.setTotalRecordCount(totCnt);

	model.addAttribute("resultList", map.get("resultList"));
	model.addAttribute("resultCnt", map.get("resultCnt"));
	model.addAttribute("summary", map.get("summary"));
	model.addAttribute("paginationInfo", paginationInfo);

	Satisfaction data = bbsSatisfactionService.selectSatisfaction(satisfactionVO);

	// 작성자 본인만 상세정보를 볼 수 있도록 소유권 검증 (수정·삭제와 동일)
	String loginUniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
	if (data == null || data.getFrstRegisterId() == null || !data.getFrstRegisterId().equals(loginUniqId)) {
	    model.addAttribute("subMsg", egovMessageSource.getMessage("errors.xss.checkerUser"));
	    return "egovframework/com/cop/stf/EgovSatisfactionList";
	}

	satisfactionVO.setStsfdgNo(data.getStsfdgNo());
	satisfactionVO.setNttId(data.getNttId());
	satisfactionVO.setBbsId(data.getBbsId());
	satisfactionVO.setWrterId(data.getWrterId());
	satisfactionVO.setWrterNm(data.getWrterNm());
	satisfactionVO.setStsfdgPassword(null);
	satisfactionVO.setStsfdgCn(data.getStsfdgCn());
	satisfactionVO.setStsfdg(data.getStsfdg());
	satisfactionVO.setUseAt(data.getUseAt());
	satisfactionVO.setFrstRegisterPnttm(data.getFrstRegisterPnttm());
	satisfactionVO.setFrstRegisterNm(data.getFrstRegisterNm());

	return "egovframework/com/cop/stf/EgovSatisfactionList";
    }

    /**
     * 익명 만족도조사 수정 페이지로 이동한다.
     *
     * @param satisfactionVO
     * @param model
     * @return
     *
     * @deprecated 이 경로를 부르는 화면이 없다. 익명 게시판 기능이 빠지면서 이동 대상(/cop/bbs/anonymous/*)도 없어져 동작하지 않는다. 삭제 예정.
     */
    @Deprecated(forRemoval = true)
    @RequestMapping("/cop/stf/anonymous/selectSingleSatisfaction.do")
    public String selectAnonymousSingleSatisfaction(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, ModelMap model) {

	//------------------------------------------
	// JSP의 <head> 부분 처리 (javascript 생성)
	//------------------------------------------
	model.addAttribute("type", satisfactionVO.getType());	// head or body

	if ("head".equals(satisfactionVO.getType())) {
	    return "egovframework/com/cop/stf/EgovSatisfactionList";
	}
	////----------------------------------------

	model.addAttribute("anonymous", "true");

	satisfactionVO.setSubPageUnit(propertyService.getInt("pageUnit"));
	satisfactionVO.setSubPageSize(propertyService.getInt("pageSize"));

	PaginationInfo paginationInfo = new PaginationInfo();
	paginationInfo.setCurrentPageNo(satisfactionVO.getSubPageIndex());
	paginationInfo.setRecordCountPerPage(satisfactionVO.getSubPageUnit());
	paginationInfo.setPageSize(satisfactionVO.getSubPageSize());

	satisfactionVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
	satisfactionVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
	satisfactionVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	Map<String, Object> map = bbsSatisfactionService.selectSatisfactionList(satisfactionVO);
	int totCnt = Integer.parseInt((String)map.get("resultCnt"));

	paginationInfo.setTotalRecordCount(totCnt);

	model.addAttribute("resultList", map.get("resultList"));
	model.addAttribute("resultCnt", map.get("resultCnt"));
	model.addAttribute("summary", map.get("summary"));
	model.addAttribute("paginationInfo", paginationInfo);

	//-------------------------------
	// 패스워드 비교
	//-------------------------------
	String dbpassword = bbsSatisfactionService.getSatisfactionPassword(satisfactionVO);
	String enpassword = EgovFileScrty.encryptPassword(satisfactionVO.getConfirmPassword(), satisfactionVO.getStsfdgNo());

	if (!dbpassword.equals(enpassword)) {

	    model.addAttribute("subMsg", egovMessageSource.getMessage("cop.password.not.same.msg"));

	    satisfactionVO.setStsfdgNo("");
	    satisfactionVO.setStsfdgCn("");
	    satisfactionVO.setStsfdg(0);
	    satisfactionVO.setWrterNm("");

	} else {

	    Satisfaction data = bbsSatisfactionService.selectSatisfaction(satisfactionVO);

	    satisfactionVO.setStsfdgNo(data.getStsfdgNo());
	    satisfactionVO.setNttId(data.getNttId());
	    satisfactionVO.setBbsId(data.getBbsId());
	    satisfactionVO.setWrterId(data.getWrterId());
	    satisfactionVO.setWrterNm(data.getWrterNm());
	    // 2026.07.30 보안 조치 - 화면 모델에 저장 비밀번호를 싣지 않는다.
	    satisfactionVO.setStsfdgPassword(null);
	    satisfactionVO.setStsfdgCn(data.getStsfdgCn());
	    satisfactionVO.setStsfdg(data.getStsfdg());
	    satisfactionVO.setUseAt(data.getUseAt());
	    satisfactionVO.setFrstRegisterPnttm(data.getFrstRegisterPnttm());
	    satisfactionVO.setFrstRegisterNm(data.getFrstRegisterNm());
	}
	////-----------------------------

	return "egovframework/com/cop/stf/EgovSatisfactionList";
    }

    /**
     * 만족도조사를 수정한다.
     *
     * @param satisfactionVO
     * @param satisfaction
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/cop/stf/updateSatisfaction.do")
    public String updateSatisfaction(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, @Valid @ModelAttribute("satisfaction") Satisfaction satisfaction,
	    BindingResult bindingResult, ModelMap model) {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (bindingResult.hasErrors()) {
		    model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");

		    return "forward:/cop/bbs/selectArticleDetail.do";
		}

		if (isAuthenticated) {
		    // 작성자 본인만 수정 가능하도록 소유권 검증 (회원 만족도조사는 로그인 사용자 명의로만 등록됨)
		    Satisfaction stored = bbsSatisfactionService.selectSatisfaction(satisfactionVO);
		    String loginUniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
		    if (stored == null || stored.getFrstRegisterId() == null
			    || !stored.getFrstRegisterId().equals(loginUniqId)) {
			model.addAttribute("subMsg", egovMessageSource.getMessage("errors.xss.checkerUser"));
			return "forward:/cop/bbs/selectArticleDetail.do";
		    }

		    satisfaction.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

		    satisfaction.setStsfdgPassword("");	// dummy

		    bbsSatisfactionService.updateSatisfaction(satisfaction);

		    satisfactionVO.setStsfdgCn("");
		    satisfactionVO.setStsfdgNo("");
		    satisfactionVO.setStsfdg(0);
		}

		return "forward:/cop/bbs/selectArticleDetail.do";
    }

    /**
     * 익명 만족도조사를 수정한다.
     *
     * @param satisfactionVO
     * @param satisfaction
     * @param bindingResult
     * @param model
     * @return
     *
     * @deprecated 이 경로를 부르는 화면이 없다. 익명 게시판 기능이 빠지면서 이동 대상(/cop/bbs/anonymous/*)도 없어져 동작하지 않는다. 삭제 예정.
     */
    @Deprecated(forRemoval = true)
    @PostMapping("/cop/stf/anonymous/updateSatisfaction.do")
    public String updateAnonymousSatisfaction(@ModelAttribute("searchVO") SatisfactionVO satisfactionVO, @Valid @ModelAttribute("satisfaction") Satisfaction satisfaction,
	    BindingResult bindingResult, ModelMap model) {
		// 2026.07.13 KISA 보안취약점 조치
		LoginVO _loginVO = EgovAuthorizationHelper.assertLoginUser();


		if (bindingResult.hasErrors()) {
		    model.addAttribute("msg", "작성자 및 만족도는 필수 입력값입니다.");

		    return "forward:/cop/bbs/anonymous/selectBoardArticle.do";
		}

		// 2026.07.30 보안 조치 - deleteAnonymousSatisfaction과 동일한 작성비밀번호 검증
		satisfactionVO.setStsfdgNo(satisfaction.getStsfdgNo());
		String dbpasswordForUpdate = bbsSatisfactionService.getSatisfactionPassword(satisfactionVO);
		String enpasswordForUpdate = EgovFileScrty.encryptPassword(satisfactionVO.getConfirmPassword(), satisfaction.getStsfdgNo());
		if (dbpasswordForUpdate == null || !dbpasswordForUpdate.equals(enpasswordForUpdate)) {
		    model.addAttribute("subMsg", egovMessageSource.getMessage("cop.password.not.same.msg"));
		    return "forward:/cop/bbs/anonymous/selectArticleDetail.do";
		}

		satisfaction.setLastUpdusrId("ANONYMOUS");
		satisfaction.setStsfdgPassword(EgovFileScrty.encryptPassword(satisfaction.getStsfdgPassword(), satisfaction.getStsfdgNo()));

		bbsSatisfactionService.updateSatisfaction(satisfaction);

		satisfactionVO.setStsfdgNo("");
		satisfactionVO.setStsfdgCn("");
		satisfactionVO.setStsfdg(0);
		satisfactionVO.setWrterNm("");

		return "forward:/cop/bbs/anonymous/selectBoardArticle.do";
    }

}
