package egovframework.com.uss.ion.ism.web;
import java.util.Map;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ism.service.EgovInfrmlSanctnService;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.uss.ion.ism.service.SanctnerVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 개요
 * - 약식결재관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 약식결재관리에 대한 등록, 승인, 반려, 삭제기능을 제공한다.
 * - 결재자에 대한 목록조회기능을 제공한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:29:25
 */

@Controller
public class EgovInfrmlSanctnController {

	@Resource(name="EgovInfrmlSanctnService")
    protected EgovInfrmlSanctnService infrmlSanctnService;

	@Resource(name="propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
	 * 결재자 정보에 대한 팝업 목록을 조회한다.
	 * @param SanctnerVO
	 * @return  String
	 *
	 * @param sanctnerVO
	 */
	@RequestMapping("/uss/ion/ism/selectSanctnerListPopup.do")
	public String selectSanctnerListPopup(@ModelAttribute("searchVO") SanctnerVO sanctnerVO, ModelMap model) throws Exception{
		return "egovframework/com/uss/ion/ism/EgovSanctnerListPopup";
	}

	/**
	 * 결재자 정보에 대한 목록을 조회한다.
	 * @param SanctnerVO
	 * @return  String
	 *
	 * @param sanctnerVO
	 */
	@RequestMapping("/uss/ion/ism/selectSanctnerList.do")
	public String selectSanctnerList(@ModelAttribute("searchVO") SanctnerVO sanctnerVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//sanctnerVO.setUniqId(user.getUniqId());

		sanctnerVO.setPageUnit(propertyService.getInt("pageUnit"));
		sanctnerVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sanctnerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sanctnerVO.getPageUnit());
		paginationInfo.setPageSize(sanctnerVO.getPageSize());

		sanctnerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sanctnerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sanctnerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = infrmlSanctnService.selectSanctnerList(sanctnerVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/ism/EgovSanctnerList";
	}
	
	/**
	 * 결재자 정보에 대한 목록을 조회한다. Old 삭제 후 반영
	 * @param SanctnerVO
	 * @return  String
	 *
	 * @param sanctnerVO
	 */
	@RequestMapping("/uss/ion/ism/selectSanctnerListNew.do")
	public String selectSanctnerListNew(@ModelAttribute("searchVO") SanctnerVO sanctnerVO, ModelMap model) throws Exception{
		//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//sanctnerVO.setUniqId(user.getUniqId());

		sanctnerVO.setPageUnit(propertyService.getInt("pageUnit"));
		sanctnerVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sanctnerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(sanctnerVO.getPageUnit());
		paginationInfo.setPageSize(sanctnerVO.getPageSize());

		sanctnerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sanctnerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		sanctnerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = infrmlSanctnService.selectSanctnerList(sanctnerVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/ism/EgovSanctnerListNew";
	}

	/**
	 * 약식결재 정보의 상세화면으로 이동한다.
	 * @param InfrmlSanctn
	 * @return  String
	 *
	 * @param InfrmlSanctn
	 */
	@RequestMapping("/uss/ion/ism/selectInfrmlSanctn.do")
	public String selectInfrmlSanctn(
			@ModelAttribute("infrmlSanctn") InfrmlSanctn infrmlSanctn, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	if(infrmlSanctn.getInfrmlSanctnId() != null){
    		if(infrmlSanctn.getInfrmlSanctnId().indexOf(",") > 0){
    			infrmlSanctn.setInfrmlSanctnId(infrmlSanctn.getInfrmlSanctnId().substring(0, infrmlSanctn.getInfrmlSanctnId().indexOf(",")));
    		}
    	}

    	model.addAttribute("infrmlSanctnVO", infrmlSanctnService.selectInfrmlSanctn(infrmlSanctn));

		return "egovframework/com/uss/ion/ism/EgovInfrmlSanctnDetail";
	}

	/**
	 * 약식결재 반려처리 화면을 호출한다.
	 * @param
	 * @return  String
	 *
	 * @param
	 */
	@RequestMapping("/uss/ion/ism/EgovReturnPopup.do")
	public String selectReturnPopup() throws Exception{
		return "egovframework/com/uss/ion/ism/EgovReturnPopup";
	}

	/**
	 * 약식결재 승인처리 화면을 호출한다.
	 * @param
	 * @return  String
	 *
	 * @param
	 */
	@RequestMapping("/uss/ion/ism/EgovConfmPopup.do")
	public String selectConfmPopup() throws Exception{
		return "egovframework/com/uss/ion/ism/EgovConfmPopup";
	}
	
	/**
	 * 약식결재 반려처리 화면을 호출한다. Old 삭제 후 반영
	 * @param
	 * @return  String
	 *
	 * @param
	 */
	@RequestMapping("/uss/ion/ism/EgovReturnPopupNew.do")
	public String selectReturnPopupNew() throws Exception{
		return "egovframework/com/uss/ion/ism/EgovReturnPopupNew";
	}
	
	
	/**
	 * 약식결재 승인처리 화면을 호출한다. Old 삭제 후 반영
	 * @param
	 * @return  String
	 *
	 * @param
	 */
	@RequestMapping("/uss/ion/ism/EgovConfmPopupNew.do")
	public String selectConfmPopupNew() throws Exception{
		return "egovframework/com/uss/ion/ism/EgovConfmPopupNew";
	}

}