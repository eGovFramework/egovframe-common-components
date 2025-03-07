package egovframework.com.sym.ccm.cde.web;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.com.sym.ccm.ccc.service.EgovCcmCmmnClCodeManageService;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;

/**
*
* 공통상세코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
* @author 공통서비스 개발팀 이중호
* @since 2009.04.01
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2009.04.01  이중호       최초 생성
*   2011.08.26	정진오	IncludedInfo annotation 추가
*   2017.08.08	이정은	표준프레임워크 v3.7 개선
*	2024.10.29	LeeBaekHaeng	검색조건 유지
*
* </pre>
*/

@Controller
public class EgovCcmCmmnDetailCodeManageController {

	@Resource(name = "CmmnDetailCodeManageService")
	private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;

	@Resource(name = "CmmnClCodeManageService")
	private EgovCcmCmmnClCodeManageService cmmnClCodeManageService;

	@Resource(name = "CmmnCodeManageService")
	private EgovCcmCmmnCodeManageService cmmnCodeManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 공통상세코드 목록을 조회한다.
	  * @param loginVO
	  * @param searchVO
	  * @param model
	  * @return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeList"
	  * @throws Exception
	  */
	@IncludedInfo(name = "공통상세코드", listUrl = "/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do", order = 970, gid = 60)
	@RequestMapping(value = "/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do")
	public String selectCmmnDetailCodeList(@ModelAttribute("loginVO") LoginVO loginVO,
		@ModelAttribute("searchVO") CmmnDetailCodeVO searchVO, ModelMap model) throws Exception {

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

		List<CmmnDetailCodeVO> CmmnCodeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchVO);
		model.addAttribute("resultList", CmmnCodeList);

		int totCnt = cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeList";
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param loginVO
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/cde/SelectCcmCmmnDetailCodeDetail.do")
	public String selectCmmnDetailCodeDetail(@ModelAttribute("loginVO") LoginVO loginVO,
		CmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model) throws Exception {
		CmmnDetailCode vo = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
		model.addAttribute("result", vo);

		return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeDetail";
	}

	/**
	 * 공통상세코드를 삭제한다.
	 * @param loginVO
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return "forward:/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/cde/RemoveCcmCmmnDetailCode.do")
	public String deleteCmmnDetailCode(@ModelAttribute("loginVO") LoginVO loginVO, CmmnDetailCodeVO cmmnDetailCodeVO,
		ModelMap model) throws Exception {
		cmmnDetailCodeManageService.deleteCmmnDetailCode(cmmnDetailCodeVO);

		model.addAttribute("searchCondition", cmmnDetailCodeVO.getSearchCondition());
		model.addAttribute("searchKeyword", cmmnDetailCodeVO.getSearchKeyword());
		model.addAttribute("pageIndex", cmmnDetailCodeVO.getPageIndex());

		return "redirect:/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do";
	}

	/**
	 * 공통상세코드 등록을 위한 등록페이지로 이동한다.
	 *
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sym/ccm/cde/RegistCcmCmmnDetailCodeView.do")
	public String insertCmmnDetailCodeView(@ModelAttribute("loginVO") LoginVO loginVO,
		@ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO,
		@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model) throws Exception {

		CmmnClCodeVO searchClCodeVO = new CmmnClCodeVO();
		searchClCodeVO.setFirstIndex(0);
		List<CmmnClCodeVO> clCodeList = cmmnClCodeManageService.selectCmmnClCodeList(searchClCodeVO);
		model.addAttribute("clCodeList", clCodeList);

		CmmnCodeVO clCode = new CmmnCodeVO();
		clCode.setClCode(cmmnCodeVO.getClCode());

		if (!cmmnCodeVO.getClCode().equals("")) {

			CmmnCodeVO searchCodeVO = new CmmnCodeVO();
			searchCodeVO.setRecordCountPerPage(999999);
			searchCodeVO.setFirstIndex(0);
			searchCodeVO.setSearchCondition("clCode");
			searchCodeVO.setSearchKeyword(cmmnCodeVO.getClCode());

			List<CmmnCodeVO> codeList = cmmnCodeManageService.selectCmmnCodeList(searchCodeVO);
			model.addAttribute("codeList", codeList);
		}

		return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeRegist";
	}

	/**
	 * 공통상세코드를 등록한다.
	 *
	 * @param CmmnDetailCodeVO
	 * @param CmmnDetailCodeVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sym/ccm/cde/RegistCcmCmmnDetailCode.do")
	public String insertCmmnDetailCode(@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO,
		BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		CmmnClCodeVO searchClCodeVO = new CmmnClCodeVO();
		searchClCodeVO.setFirstIndex(0);

		beanValidator.validate(cmmnDetailCodeVO, bindingResult);

		if (bindingResult.hasErrors()) {

			List<CmmnClCodeVO> clCodeList = cmmnClCodeManageService.selectCmmnClCodeList(searchClCodeVO);
			model.addAttribute("clCodeList", clCodeList);

			return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeRegist";
		}

		if (cmmnDetailCodeVO.getCodeId() != null) {

			CmmnDetailCode vo = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
			if (vo != null) {
				model.addAttribute("message", egovMessageSource.getMessage("comSymCcmCde.validate.codeCheck"));

				List<CmmnClCodeVO> clCodeList = cmmnClCodeManageService.selectCmmnClCodeList(searchClCodeVO);
				model.addAttribute("clCodeList", clCodeList);

				return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeRegist";
			}
		}

		cmmnDetailCodeVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		cmmnDetailCodeManageService.insertCmmnDetailCode(cmmnDetailCodeVO);

		model.addAttribute("searchCondition", cmmnDetailCodeVO.getSearchCondition());
		model.addAttribute("searchKeyword", cmmnDetailCodeVO.getSearchKeyword());
		model.addAttribute("pageIndex", cmmnDetailCodeVO.getPageIndex());

		return "redirect:/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do";
	}

	/**
	 * 공통상세코드 수정을 위한 수정페이지로 이동한다.
	 *
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeUpdt"
	 * @throws Exception
	 */
	@RequestMapping("/sym/ccm/cde/UpdateCcmCmmnDetailCodeView.do")
	public String updateCmmnDetailCodeView(@ModelAttribute("loginVO") LoginVO loginVO,
		@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model)
		throws Exception {

		CmmnDetailCode result = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
		model.addAttribute("cmmnDetailCodeVO", result);

		return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeUpdt";
	}

	/**
	 * 공통상세코드를 수정한다.
	 *
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeUpdt", "/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do"
	 * @throws Exception
	 */
	@RequestMapping("/sym/ccm/cde/UpdateCcmCmmnDetailCode.do")
	public String updateCmmnDetailCode(@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO,
		ModelMap model, BindingResult bindingResult)
		throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(cmmnDetailCodeVO, bindingResult);

		if (bindingResult.hasErrors()) {
			CmmnDetailCode result = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
			model.addAttribute("cmmnDetailCodeVO", result);

			return "egovframework/com/sym/ccm/cde/EgovCcmCmmnDetailCodeUpdt";
		}

		cmmnDetailCodeVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		cmmnDetailCodeManageService.updateCmmnDetailCode(cmmnDetailCodeVO);

		model.addAttribute("searchCondition", cmmnDetailCodeVO.getSearchCondition());
		model.addAttribute("searchKeyword", cmmnDetailCodeVO.getSearchKeyword());
		model.addAttribute("pageIndex", cmmnDetailCodeVO.getPageIndex());

		return "redirect:/sym/ccm/cde/SelectCcmCmmnDetailCodeList.do";
	}

}
