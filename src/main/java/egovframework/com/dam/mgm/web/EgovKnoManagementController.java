package egovframework.com.dam.mgm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import egovframework.com.dam.mgm.service.EgovKnoManagementService;
import egovframework.com.dam.mgm.service.KnoManagement;
import egovframework.com.dam.mgm.service.KnoManagementVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 지식정보에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:38
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------        --------    ---------------------------
 *   2010.8.12  박종선          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovKnoManagementController {

	@Resource(name = "KnoManagementService")
    private EgovKnoManagementService knoManagementService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
	 * 등록된 지식정보 정보를 조회 한다.
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 리턴 Url
	 *
	 * @param KnoManagementVO
	 */
    @IncludedInfo(name="지식정보관리", listUrl="/dam/mgm/EgovComDamManagementList.do", order = 1280 ,gid = 80)
	@RequestMapping(value="/dam/mgm/EgovComDamManagementList.do")
	public String selectKnoManagementList(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") KnoManagementVO searchVO
			, ModelMap model
			) throws Exception {

		/** EgovPropertyService.mapMaterial */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> KnoManagementList = knoManagementService.selectKnoManagementList(searchVO);
		model.addAttribute("resultList", KnoManagementList);

		int totCnt = knoManagementService.selectKnoManagementTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/dam/mgm/EgovComDamManagementList";
	}

	/**
	 * 지식정보 상세 정보를 조회 한다.
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 리턴 Url
	 *
	 * @param KnoManagementVO
	 */
	@RequestMapping(value="/dam/mgm/EgovComDamManagement.do")
	public String selectKnoManagement(
			KnoManagement knoManagement
			, ModelMap model
			, @RequestParam Map<?, ?> commandMap
			) throws Exception {

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "egovframework/com/uat/uia/EgovLoginUsr";
	    }
        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        knoManagement.setEmplyrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		KnoManagement vo = knoManagementService.selectKnoManagement(knoManagement);
		model.addAttribute("result", vo);
		return "egovframework/com/dam/mgm/EgovComDamManagementDetail";
	}

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * @param KnoNm - 지식정보 model
	 * @return String - 리턴 Url
	 *
	 * @param knoNm
	 */
	//public String  insertKnoManagement(knoNm knoNm){
	//	return null;
	//}

	/**
	 * 기 등록 된 지식정보 정보를 수정 한다.
	 * @param ManagementKnoNm - 지식정보 model
	 * @return String - 리턴 Url
	 *
	 * @param knoNm
	 */
	@RequestMapping(value="/dam/mgm/EgovComDamManagementModify.do")
	public String  updateKnoManagement(
			KnoManagement knoManagementBlank
			, @RequestParam Map<?, ?> commandMap
			,@ModelAttribute("knoManagement") KnoManagement knoManagement
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "egovframework/com/uat/uia/EgovLoginUsr";
	    }

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sCmd = commandMap.get("cmd") == null ? "": (String)commandMap.get("cmd");
		System.out.println("cmd>"+sCmd);

		if (sCmd.equals("")) {

	        knoManagement.setEmplyrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			knoManagement = knoManagementService.selectKnoManagement(knoManagement);
			model.addAttribute("knoManagement", knoManagement);

			System.out.println("knoManagement>"+knoManagement);
			System.out.println("knoManagement>"+model.get("knoManagement"));

			return "egovframework/com/dam/mgm/EgovComDamManagementModify";

		} else if (sCmd.equals("Modify")) {

			beanValidator.validate(knoManagement, bindingResult);
			if (bindingResult.hasErrors()){

		        knoManagement.setEmplyrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
				knoManagement = knoManagementService.selectKnoManagement(knoManagement);
				model.addAttribute("knoManagement", knoManagement);
				return "egovframework/com/dam/mgm/EgovComDamManagementModify";
			}

	        knoManagement.setEmplyrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			knoManagementService.updateKnoManagement(knoManagement);
			return "forward:/dam/mgm/EgovComDamManagementList.do";
		} else {
			return "forward:/dam/mgm/EgovComDamManagementList.do";
		}
	}



	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * @param ManagementKnoNm - 지식정보 model
	 * @return String - 리턴 Url
	 *
	 * @param knoNm
	 */
	//public String  deleteKnoManagement(knoNm knoNm){
	//	return null;
	//}

}