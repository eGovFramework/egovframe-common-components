package egovframework.com.sym.ccm.adc.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.sym.ccm.adc.service.AdministCode;
import egovframework.com.sym.ccm.adc.service.AdministCodeVO;
import egovframework.com.sym.ccm.adc.service.EgovCcmAdministCodeManageService;

/**
 *
 * 행정코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
 *   2009.04.01  이중호			최초 생성
 *   2011.08.26  정진오			IncludedInfo annotation 추가
 *   2024.10.29  권태성			등록 & 수정의 화면과 데이터를 처리하는 method 분리, validation 적용
 *
 * </pre>
 */
@Controller
public class EgovCcmAdministCodeManageController {

	@Resource(name = "AdministCodeManageService")
	private EgovCcmAdministCodeManageService administCodeManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 행정코드를 삭제한다.
	 * 
	 * @param loginVO
	 * @param administCode
	 * @param model
	 * @return "forward:/sym/ccm/adc/EgovCcmAdministCodeList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/adc/EgovCcmAdministCodeRemove.do")
	public String deleteAdministCode(@ModelAttribute("loginVO") LoginVO loginVO, AdministCode administCode,
			ModelMap model) throws Exception {
		administCodeManageService.deleteAdministCode(administCode);
		return "forward:/sym/ccm/adc/EgovCcmAdministCodeList.do";
	}

	/**
	 * 행정코드를 등록화면
	 * 
	 * @param loginVO
	 * @param administCode
	 * @param model
	 * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/adc/EgovCcmAdministCodeRegistView.do")
	public String insertAdministCodeView(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("administCode") AdministCode administCode, ModelMap model) throws Exception {

		return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeRegist";

	}

	/**
	 * 행정코드를 등록한다.
	 * 
	 * @param loginVO
	 * @param administCode
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/adc/EgovCcmAdministCodeRegist.do")
	public String insertAdministCode(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("administCode") AdministCode administCode, BindingResult bindingResult, ModelMap model)
			throws Exception {
		beanValidator.validate(administCode, bindingResult);

		if (bindingResult.hasErrors()) {
			return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeRegist";
		}

		AdministCode vo = administCodeManageService.selectAdministCodeDetail(administCode);
		if (vo != null) {
			administCode.setAdministZoneNm("");
			administCode.setAdministZoneCode("");
			model.addAttribute("message", "이미 등록된 행정구역코드가 존재합니다.");
			return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeRegist";
		}

		administCode.setFrstRegisterId(loginVO.getUniqId());
		administCodeManageService.insertAdministCode(administCode);

		return "redirect:/sym/ccm/adc/EgovCcmAdministCodeList.do";
	}

	/**
	 * 행정코드 상세항목을 조회한다.
	 * 
	 * @param loginVO
	 * @param administCode
	 * @param model
	 * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/adc/EgovCcmAdministCodeDetail.do")
	public String selectAdministCodeDetail(@ModelAttribute("loginVO") LoginVO loginVO, AdministCode administCode,
			ModelMap model) throws Exception {
		AdministCode vo = administCodeManageService.selectAdministCodeDetail(administCode);
		model.addAttribute("result", vo);

		return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeDetail";
	}

	/**
	 * 행정코드 목록을 조회한다.
	 * 
	 * @param loginVO
	 * @param searchVO
	 * @param model
	 * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "행정코드관리", order = 1010, gid = 60)
	@RequestMapping(value = "/sym/ccm/adc/EgovCcmAdministCodeList.do")
	public String selectAdministCodeList(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("searchVO") AdministCodeVO searchVO, ModelMap model) throws Exception {
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

		List<EgovMap> CmmnCodeList = administCodeManageService.selectAdministCodeList(searchVO);
		model.addAttribute("resultList", CmmnCodeList);

		int totCnt = administCodeManageService.selectAdministCodeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeList";
	}

	/**
	 * 일반달력 팝업 메인창을 호출한다.
	 * 
	 * @param model
	 * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodePopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/adc/EgovCcmAdministCodePopup.do")
	public String callAdministCodePopup(ModelMap model) throws Exception {
		return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodePopup";
	}

	/**
	 * 행정코드 팝업 목록을 조회한다.
	 * 
	 * @param loginVO
	 * @param searchVO
	 * @param model
	 * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCode"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/adc/EgovCcmAdministCode.do")
	public String selectAdministCode(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("searchVO") AdministCodeVO searchVO, ModelMap model) throws Exception {
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

		List<EgovMap> CmmnCodeList = administCodeManageService.selectAdministCodeList(searchVO);
		model.addAttribute("resultList", CmmnCodeList);

		int totCnt = administCodeManageService.selectAdministCodeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/ccm/adc/EgovCcmAdministCode";
	}

	/**
	 * 행정코드 수정화면
	 * 
	 * @param loginVO
	 * @param administCode
	 * @param model
	 * @return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeModify"
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/adc/EgovCcmAdministCodeModifyView.do")
	public String updateAdministCodeView(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("administCode") AdministCode administCode, ModelMap model) throws Exception {
		AdministCode vo = administCodeManageService.selectAdministCodeDetail(administCode);
		if (vo != null) {
			model.addAttribute("administCode", vo);
			return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeModify";
		} else {
			return "redirect:/sym/ccm/adc/EgovCcmAdministCodeList.do";
		}
	}

	/**
	 * 행정코드를 수정한다.
	 * 
	 * @param loginVO
	 * @param administCode
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sym/ccm/adc/EgovCcmAdministCodeModify.do")
	public String updateAdministCode(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("administCode") AdministCode administCode, BindingResult bindingResult,
			@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
		beanValidator.validate(administCode, bindingResult);

		AdministCode vo = administCodeManageService.selectAdministCodeDetail(administCode);
		if (vo != null) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("administCode", vo);
				return "egovframework/com/sym/ccm/adc/EgovCcmAdministCodeModify";
			}

			administCode.setLastUpdusrId(loginVO.getUniqId());
			administCodeManageService.updateAdministCode(administCode);
		}
		return "redirect:/sym/ccm/adc/EgovCcmAdministCodeList.do";
	}

	/**
	 * Map 내용을 확인한다.
	 * 
	 * @param commandMap
	 * @return
	 */
	public String printParameterMap(@RequestParam Map<?, ?> commandMap) {
		String ret = "";
		for (Object key : commandMap.keySet()) {
			Object value = commandMap.get(key);

			ret += "key:" + key.toString() + " value:" + value.toString();
		}
		return ret;
	}

}