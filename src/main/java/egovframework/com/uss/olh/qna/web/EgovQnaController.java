package egovframework.com.uss.olh.qna.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.EgovXssChecker;
import egovframework.com.uss.olh.qna.service.EgovQnaService;
import egovframework.com.uss.olh.qna.service.QnaDefaultVO;
import egovframework.com.uss.olh.qna.service.QnaVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
/**
*
* Q&A를 처리하는 Controller 클래스
* @author 공통서비스 개발팀 박정규
* @since 2009.04.01
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일     	수정자           			수정내용
*  ------------   --------    ---------------------------------------------
*   2009.04.01  	박정규          최초 생성
*   2011.08.26		정진오			IncludedInfo annotation 추가
*   2011.10.21		이기하			삭제시 비밀번호 확인 추가(최종감리 반영)
*   2016.08.05		김연호			표준프레임워크 3.6 개선
*
* </pre>
*/
@Controller
public class EgovQnaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQnaController.class);
	
	@Resource(name = "EgovQnaService")
	private EgovQnaService egovQnaService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	// Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * Q&A정보 목록을 조회한다. (pageing)
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaListInqire"
	 * @throws Exception
	 */
	@IncludedInfo(name = "Q&A관리", order = 550, gid = 50)
	@RequestMapping(value = "/uss/olh/qna/selectQnaList.do")
	public String selectQnaList(@ModelAttribute("searchVO") QnaVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.SiteList */
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

		List<QnaVO> QnaList = egovQnaService.selectQnaList(searchVO);
		model.addAttribute("resultList", QnaList);

		// 인증여부 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) {
			model.addAttribute("certificationAt", "N");
		} else {
			model.addAttribute("certificationAt", "Y");
		}

		int totCnt = egovQnaService.selectQnaListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olh/qna/EgovQnaList";
	}
	
	/**
	 * Q&A정보 목록에 대한 상세정보를 조회한다.
	 * @param passwordConfirmAt
	 * @param qnaVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaDetail"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/selectQnaDetail.do")
	public String selectQnaDetail(@RequestParam("qaId") String qaId, QnaVO qnaVO, @ModelAttribute("searchVO") QnaDefaultVO searchVO, ModelMap model) throws Exception {

		qnaVO.setQaId(qaId);
		
		//조회수 수정처리
		egovQnaService.updateQnaInqireCo(qnaVO);
		
		QnaVO vo = egovQnaService.selectQnaDetail(qnaVO);

		// 작성 비밀번호를 얻는다.
//		String writngPassword = vo.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
//		vo.setWritngPassword(EgovFileScrty.decode(writngPassword));
		
		model.addAttribute("result", vo);

		return "egovframework/com/uss/olh/qna/EgovQnaDetail";
	}
	
	/**
	 * Q&A정보를 등록하기 위한 전 처리(인증체크)
	 * @param searchVO
	 * @param qnaManageVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaRegist"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/insertQnaView.do")
	public String insertQnaView(@ModelAttribute("searchVO") QnaVO searchVO, QnaVO qnaVO, Model model) throws Exception {

		// 인증여부 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) {
			model.addAttribute("qnaVO", qnaVO);
			return "egovframework/com/uss/olh/qna/EgovQnaRegist";
		}

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String wrterNm = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()); // 사용자명
		String emailAdres = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getEmail()); // email 주소

		qnaVO.setWrterNm(wrterNm); // 작성자명
		qnaVO.setEmailAdres(emailAdres); // email 주소

		model.addAttribute("qnaVO", qnaVO);

		return "egovframework/com/uss/olh/qna/EgovQnaRegist";

	}
	
	/**
	 * Q&A정보를 등록한다.
	 * @param searchVO
	 * @param qnaVO
	 * @param bindingResult
	 * @return	"forward:/uss/olh/qna/selectQnaList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/insertQna.do")
	public String insertQna(@ModelAttribute("searchVO") QnaVO searchVO, @ModelAttribute("qnaVO") QnaVO qnaVO, BindingResult bindingResult,
			ModelMap model) throws Exception {

		beanValidator.validate(qnaVO, bindingResult);

		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/olh/qna/EgovQnaRegist";
		}

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

		qnaVO.setFrstRegisterId(frstRegisterId); // 최초등록자ID
		qnaVO.setLastUpdusrId(frstRegisterId); // 최종수정자ID

		// 작성비밀번호를 암호화 하기 위해서 Get
//		String writngPassword = qnaVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
//		qnaVO.setWritngPassword(EgovFileScrty.encode(writngPassword));

		egovQnaService.insertQna(qnaVO);

		return "forward:/uss/olh/qna/selectQnaList.do";
	}
	
	/**
	 * Q&A정보를 수정하기 위한 전 처리
	 * @param qnaVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaUpdt
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/updateQnaView.do")
	public String updateQnaView(QnaVO qnaVO, @ModelAttribute("searchVO") QnaVO searchVO, ModelMap model) throws Exception {

		QnaVO vo = egovQnaService.selectQnaDetail(qnaVO);

		// 작성 비밀번호를 얻는다.
//		String writngPassword = vo.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
//		vo.setWritngPassword(EgovFileScrty.decode(writngPassword));

		model.addAttribute("qnaVO", vo);

		return "egovframework/com/uss/olh/qna/EgovQnaUpdt";
	}
	
	/**
	 * Q&A정보를 수정처리한다.
	 * @param searchVO
	 * @param qnaVO
	 * @param bindingResult
	 * @return	"forward:/uss/olh/qna/selectQnaList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/updateQna.do")
	public String updateQna(
    		HttpServletRequest request,
			@ModelAttribute("searchVO") QnaVO searchVO,
			@ModelAttribute("qnaVO") QnaVO qnaVO, 
			BindingResult bindingResult) throws Exception {

		// Validation
		beanValidator.validate(qnaVO, bindingResult);

		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/olh/qna/EgovQnaUpdt";
		}
		
    	//--------------------------------------------------------------------------------------------
    	// @ XSS 사용자권한체크 START
    	// param1 : 사용자고유ID(uniqId,esntlId)
    	//--------------------------------------------------------
    	LOGGER.debug("@ XSS 권한체크 START ----------------------------------------------");
    	//step1 DB에서 해당 게시물의 uniqId 조회
    	QnaVO vo = egovQnaService.selectQnaDetail(qnaVO);;
    	
    	//step2 EgovXssChecker 공통모듈을 이용한 권한체크
    	EgovXssChecker.checkerUserXss(request, vo.getFrstRegisterId()); 
      	LOGGER.debug("@ XSS 권한체크 END ------------------------------------------------");
    	//--------------------------------------------------------
    	// @ XSS 사용자권한체크 END
    	//--------------------------------------------------------------------------------------------

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

		qnaVO.setLastUpdusrId(lastUpdusrId); // 최종수정자ID

		// 작성비밀번호를 암호화 하기 위해서 Get
//		String writngPassword = qnaManageVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
//		qnaManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));

		egovQnaService.updateQna(qnaVO);

		return "forward:/uss/olh/qna/selectQnaList.do";

	}
	
	/**
	 * Q&A정보를 삭제처리한다.
	 * @param qnaVO
	 * @param searchVO
	 * @return	"forward:/uss/olh/qna/selectQnaList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/deleteQna.do")
	public String deleteQna(
    		HttpServletRequest request,
			QnaVO qnaVO, 
			@ModelAttribute("searchVO") QnaVO searchVO) throws Exception {

    	//--------------------------------------------------------------------------------------------
    	// @ XSS 사용자권한체크 START
    	// param1 : 사용자고유ID(uniqId,esntlId)
    	//--------------------------------------------------------
    	LOGGER.debug("@ XSS 권한체크 START ----------------------------------------------");
    	
    	//step1 DB에서 해당 게시물의 uniqId 조회
    	QnaVO vo = egovQnaService.selectQnaDetail(qnaVO);;
    	
    	//step2 EgovXssChecker 공통모듈을 이용한 권한체크
    	EgovXssChecker.checkerUserXss(request, vo.getFrstRegisterId()); 
      	LOGGER.debug("@ XSS 권한체크 END ------------------------------------------------");
    	//--------------------------------------------------------
    	// @ XSS 사용자권한체크 END
    	//--------------------------------------------------------------------------------------------
    
		egovQnaService.deleteQna(qnaVO);

		return "forward:/uss/olh/qna/selectQnaList.do";
	}
	
	/**
	 * Q&A답변정보 목록을 조회한다. (pageing)
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaAnswerList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "Q&A답변관리", order = 551, gid = 50)
	@RequestMapping(value = "/uss/olh/qna/selectQnaAnswerList.do")
	public String selectQnaAnswerList(@ModelAttribute("searchVO") QnaVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.SiteList */
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

		List<QnaVO> QnaAnswerList = egovQnaService.selectQnaAnswerList(searchVO);
		model.addAttribute("resultList", QnaAnswerList);

		int totCnt = egovQnaService.selectQnaAnswerListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olh/qna/EgovQnaAnswerList";
	}
	
	/**
	 * Q&A답변정보 목록에 대한 상세정보를 조회한다.
	 * @param qnaVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaAnswerDetail"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/selectQnaAnswerDetail.do")
	public String selectQnaAnswerDetail(QnaVO qnaVO, @ModelAttribute("searchVO") QnaVO searchVO, ModelMap model) throws Exception {

		QnaVO vo = egovQnaService.selectQnaDetail(qnaVO);

		model.addAttribute("result", vo);

		return "egovframework/com/uss/olh/qna/EgovQnaAnswerDetail";
	}
	
	/**
	 * Q&A답변정보를 수정하기 위한 전 처리(공통코드 처리)
	 * @param qnaVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaAnswerUpdt"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/updateQnaAnswerView.do")
	public String updateQnaAnswerView(QnaVO qnaVO, @ModelAttribute("searchVO") QnaVO searchVO, ModelMap model) throws Exception {

		// 공통코드를 가져오기 위한 Vo
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM028");

		List<CmmnDetailCode> _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("qnaProcessSttusCode", _result);

		qnaVO = egovQnaService.selectQnaDetail(qnaVO);
		model.addAttribute("qnaVO", qnaVO);

		return "egovframework/com/uss/olh/qna/EgovQnaAnswerUpdt";
	}
	
	/**
	 * Q&A답변정보를 수정처리한다.
	 * @param qnaVO
	 * @param searchVO
	 * @return	"forward:/uss/olh/qnm/selectQnaAnswerList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/updateQnaAnswer.do")
	public String updateQnaAnswer(QnaVO qnaVO, @ModelAttribute("searchVO") QnaVO searchVO) throws Exception {

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		qnaVO.setLastUpdusrId(lastUpdusrId); // 최종수정자ID

		egovQnaService.updateQnaAnswer(qnaVO);

		return "forward:/uss/olh/qna/selectQnaAnswerList.do";

	}
	
}
