package egovframework.com.uss.umt.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.uss.umt.service.DeptManageVO;
import egovframework.com.uss.umt.service.EgovDeptManageService;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 부서관련 처리를  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 공통서비스 개발팀 조재영
 * @since 2009.00.00
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.01    lee.m.j     최초 생성
 *   2015.06.16	 조정국	  서비스 화면 접근시 조회결과를 표시하도록 수정
 * </pre>
 */

@Controller
public class EgovDeptManageController {

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovDeptManageService")
	private EgovDeptManageService egovDeptManageService;

	/** Message ID Generation */
	@Resource(name = "egovDeptManageIdGnrService")
	private EgovIdGnrService egovDeptManageIdGnrService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 부서 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@IncludedInfo(name = "부서관리", order = 461, gid = 50)
	@RequestMapping("/uss/umt/dpt/selectDeptManageListView.do")
	public String selectDeptManageListView() throws Exception {

		return "forward:/uss/umt/dpt/selectDeptManageList.do";
//		return "egovframework/com/uss/umt/EgovDeptManageList";
	}

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return String - 리턴 URL
	 * @throws Exception
	 */

	@RequestMapping(value = "/uss/umt/dpt/selectDeptManageList.do")
	public String selectDeptManageList(@ModelAttribute("deptManageVO") DeptManageVO deptManageVO, ModelMap model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(deptManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(deptManageVO.getPageUnit());
		paginationInfo.setPageSize(deptManageVO.getPageSize());

		deptManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		deptManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		deptManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("deptManageList", egovDeptManageService.selectDeptManageList(deptManageVO));

		int totCnt = egovDeptManageService.selectDeptManageListTotCnt(deptManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/uss/umt/EgovDeptManageList";
	}

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param bannerVO - 부서 Vo
	 * @return String - 리턴 Url
	 */

	@RequestMapping(value = "/uss/umt/dpt/getDeptManage.do")
	public String selectDeptManage(@RequestParam("orgnztId") String orgnztId, @ModelAttribute("deptManageVO") DeptManageVO deptManageVO, ModelMap model) throws Exception {

		deptManageVO.setOrgnztId(orgnztId);

		model.addAttribute("deptManage", egovDeptManageService.selectDeptManage(deptManageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/uss/umt/EgovDeptManageUpdt";
	}

	/**
	 * 부서등록 화면으로 이동한다.
	 * @param banner - 부서 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/umt/dpt/addViewDeptManage.do")
	public String insertViewDeptManage(@ModelAttribute("deptManageVO") DeptManageVO deptManageVO, ModelMap model) throws Exception {

		model.addAttribute("deptManage", deptManageVO);
		return "egovframework/com/uss/umt/EgovDeptManageInsert";
	}

	/**
	 * 부서정보를 신규로 등록한다.
	 * @param banner - 부서 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/umt/dpt/addDeptManage.do")
	public String insertDeptManage(@ModelAttribute("deptManageVO") DeptManageVO deptManageVO, BindingResult bindingResult,  ModelMap model) throws Exception {

		beanValidator.validate(deptManageVO, bindingResult); //validation 수행

		deptManageVO.setOrgnztId(egovDeptManageIdGnrService.getNextStringId());

		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/umt/EgovDeptManageInsert";
		} else {
			egovDeptManageService.insertDeptManage(deptManageVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/uss/umt/dpt/selectDeptManageList.do";
		}
	}

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * @param banner - 부서 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/umt/dpt/updtDeptManage.do")
	public String updateDeptManage(@ModelAttribute("deptManageVO") DeptManageVO deptManageVO, BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(deptManageVO, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/umt/EgovDeptManageUpdt";
		} else {
			egovDeptManageService.updateDeptManage(deptManageVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/uss/umt/dpt/selectDeptManageList.do";
		}
	}

	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * @param banner Banner
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/uss/umt/dpt/removeDeptManage.do")
	public String deleteDeptManage(@ModelAttribute("deptManageVO") DeptManageVO deptManageVO, Model model) throws Exception {

		egovDeptManageService.deleteDeptManage(deptManageVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/umt/dpt/selectDeptManageList.do";
	}

	/**
	 * 기 등록된 부서정보목록을 일괄 삭제한다.
	 * @param banners String
	 * @param banner Banner
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/uss/umt/dpt/removeDeptManageList.do")
	public String deleteDeptManageList(@RequestParam("deptManages") String deptManages, @ModelAttribute("deptManageVO") DeptManageVO deptManageVO, ModelMap model) throws Exception {

		String[] strDeptManages = deptManages.split(";");
		for (int i = 0; i < strDeptManages.length; i++) {
			deptManageVO.setOrgnztId(strDeptManages[i]);
			egovDeptManageService.deleteDeptManage(deptManageVO);
		}
		
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/umt/dpt/selectDeptManageList.do";
	}

}
