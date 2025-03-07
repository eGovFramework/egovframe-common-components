package egovframework.com.uss.olh.wor.web;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olh.wor.service.EgovWordDicaryService;
import egovframework.com.uss.olh.wor.service.WordDicaryVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
*
* 용어사전을 처리하는 Controller 클래스
* @author 공통서비스 개발팀 박정규
* @since 2009.04.01
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2009.04.01  박정규          최초 생성
*   2011.8.26	정진오			IncludedInfo annotation 추가
*   2016.08.02	김연호			표준프레임워크 3.6 적용
*   
*
* </pre>
*/

@Controller
public class EgovWordDicaryController {

	@Resource(name = "EgovWordDicaryService")
	private EgovWordDicaryService egovWordDicaryService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	// Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 용어사전목록을 조회한다.
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/wor/EgovWordDicaryListInqire"
	 * @throws Exception
	 */
	@IncludedInfo(name = "용어사전", order = 530, gid = 50)
	@RequestMapping(value = "/uss/olh/wor/selectWordDicaryList.do")
	public String selectWordDicaryList(@ModelAttribute("searchVO") WordDicaryVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.WordDicaryList */
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

		List<WordDicaryVO> wordDicaryList = egovWordDicaryService.selectWordDicaryList(searchVO);
		model.addAttribute("resultList", wordDicaryList);

		int totCnt = egovWordDicaryService.selectWordDicaryListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olh/wor/EgovWordDicaryList";
	}
	
	/**
	 * 용어사전 목록에 대한 상세정보를 조회한다.
	 * @param wordDicaryVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/wor/EgovWordDicaryDetail"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/wor/selectWordDicaryDetail.do")
	public String selectWordDicaryDetail(WordDicaryVO wordDicaryVO, @ModelAttribute("searchVO") WordDicaryVO searchVO, ModelMap model) throws Exception {

		WordDicaryVO vo = egovWordDicaryService.selectWordDicaryDetail(wordDicaryVO);

		model.addAttribute("result", vo);

		return "egovframework/com/uss/olh/wor/EgovWordDicaryDetail";
	}
	
	/**
	 * 용어사전정보를 등록하기 위한 첫 화면
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/wor/EgovWordDicaryRegist"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/wor/insertWordDicaryView.do")
	public String insertWordDicaryView(@ModelAttribute("searchVO") WordDicaryVO searchVO, Model model) throws Exception {

		model.addAttribute("wordDicaryVO", new WordDicaryVO());

		return "egovframework/com/uss/olh/wor/EgovWordDicaryRegist";
	}
	
	/**
	 * 용어사전정보를 등록한다.
	 * @param searchVO
	 * @param wordDicaryVO
	 * @param bindingResult
	 * @param model
	 * @return	"forward:/uss/olh/wor/selectWordDicaryList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/wor/insertWordDicary.do")
	public String insertWordDicary(@ModelAttribute("searchVO") WordDicaryVO searchVO, @ModelAttribute("wordDicaryVO") WordDicaryVO wordDicaryVO,
			BindingResult bindingResult, Model model) throws Exception {

		beanValidator.validate(wordDicaryVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/olh/wor/EgovWordDicaryRegist";
		}

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

		wordDicaryVO.setFrstRegisterId(frstRegisterId); // 최초등록자ID
		wordDicaryVO.setLastUpdusrId(frstRegisterId); // 최종수정자ID

		egovWordDicaryService.insertWordDicary(wordDicaryVO);

		return "forward:/uss/olh/wor/selectWordDicaryList.do";
	}
	
	/**
	 * 용어사전정보를 수정하기 위한 초기 화면
	 * @param wordId
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/wor/EgovWordDicaryUpdt"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/wor/updateWordDicaryView.do")
	public String updateWordDicaryView(@RequestParam("wordId") String wordId, @ModelAttribute("searchVO") WordDicaryVO searchVO, ModelMap model) throws Exception {

		WordDicaryVO wordDicaryVO = new WordDicaryVO();
		wordDicaryVO.setWordId(wordId);

		model.addAttribute("wordDicaryVO", egovWordDicaryService.selectWordDicaryDetail(wordDicaryVO));

		return "egovframework/com/uss/olh/wor/EgovWordDicaryUpdt";
	}
	
	/**
	 * 용어사전정보를 수정한다.
	 * @param searchVO
	 * @param wordDicaryVO
	 * @param bindingResult
	 * @param model
	 * @return	"forward:/uss/olh/wor/selectWordDicaryList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/wor/updateWordDicary.do")
	public String updateWordDicary(@ModelAttribute("searchVO") WordDicaryVO searchVO, @ModelAttribute("wordDicaryVO") WordDicaryVO wordDicaryVO,
			BindingResult bindingResult, Model model) throws Exception {

		// Validation
		beanValidator.validate(wordDicaryVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/olh/wor/EgovWordDicaryUpdt";
		}

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

		wordDicaryVO.setLastUpdusrId(frstRegisterId); // 최종수정자ID
		egovWordDicaryService.updateWordDicary(wordDicaryVO);

		return "forward:/uss/olh/wor/selectWordDicaryList.do";
	}
	
	/**
	 * 용어사전정보를 삭제한다.
	 * @param wordDicaryVO
	 * @param searchVO
	 * @return	"forward:/uss/olh/wor/selectWordDicaryList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/wor/deleteWordDicary.do")
	public String deleteWordDicary(WordDicaryVO wordDicaryVO, @ModelAttribute("searchVO") WordDicaryVO searchVO) throws Exception {

		egovWordDicaryService.deleteWordDicary(wordDicaryVO);

		return "forward:/uss/olh/wor/selectWordDicaryList.do";
	}
	
}
