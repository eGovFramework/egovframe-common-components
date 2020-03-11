package egovframework.com.dam.app.web;

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
import egovframework.com.dam.app.service.EgovKnoAppraisalService;
import egovframework.com.dam.app.service.KnoAppraisal;
import egovframework.com.dam.app.service.KnoAppraisalVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 지식정보평가에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 지식정보평가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식정보평가의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:36
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
public class EgovKnoAppraisalController {

	@Resource(name = "KnoAppraisalService")
    private EgovKnoAppraisalService knoAppraisalService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
	 * 등록된 지식정보평가 정보를 조회 한다.
	 * @param KnoAppraisalVO -app 지식정보평가 VO
	 * @return String - 리턴 Url
	 *
	 * @param KnoAppraisalVO
	 */
    @IncludedInfo(name="지식평가관리", listUrl="/dam/app/EgovComDamAppraisalList.do", order = 1290 ,gid = 80)
	@RequestMapping(value="/dam/app/EgovComDamAppraisalList.do")
	public String selectKnoAppraisalList(
			@ModelAttribute("searchVO") KnoAppraisalVO searchVO
			, ModelMap model
			) throws Exception {

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "egovframework/com/uat/uia/EgovLoginUsr";
	    }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

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

		searchVO.setEmplyrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		List<?> KnoAppraisalList = knoAppraisalService.selectKnoAppraisalList(searchVO);
		model.addAttribute("resultList", KnoAppraisalList);

		int totCnt = knoAppraisalService.selectKnoAppraisalTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/dam/app/EgovComDamAppraisalList";
	}

	/**
	 * 지식정보평가 상세 정보를 조회 한다.
	 * @param KnoAppraisalVO - 지식정보평가 VO
	 * @return String - 리턴 Url
	 *
	 * @param KnoAppraisalVO
	 */
	@RequestMapping(value="/dam/app/EgovComDamAppraisal.do")
	public String selectKnoAppraisal(@ModelAttribute("loginVO") LoginVO loginVO
			, KnoAppraisal knoAppraisal
			, ModelMap model
			, @RequestParam Map<?, ?> commandMap
			) throws Exception {
		KnoAppraisal vo = knoAppraisalService.selectKnoAppraisal(knoAppraisal);
		model.addAttribute("result", vo);
		return "egovframework/com/dam/app/EgovComDamAppraisalDetail";
	}

	/**
	 * 지식정보평가 정보를 신규로 등록한다.
	 * @param knoAps - 지식정보평가 model
	 * @return String - 리턴 Url
	 *
	 * @param knoAps
	 */
	//@RequestMapping(value="/dam/app/EgovComDamAppraisalRegist.do")
	//public String insertKnoAppraisal(knoAps knoAps){
	//	return "egovframework/com/";
	//}

	/**
	 * 기 등록 된 지식정보평가 정보를 수정 한다.
	 * @param AppraisalknoAps - 지식정보평가 model
	 * @return String - 리턴 Url
	 *
	 * @param knoAps
	 */
	@RequestMapping(value="/dam/app/EgovComDamAppraisalModify.do")
	public String updateKnoAppraisal(@ModelAttribute("knoId") KnoAppraisal knoAppraisal
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sCmd = commandMap.get("cmd") == null ? "": (String)commandMap.get("cmd");
		if (sCmd.equals("")) {
			KnoAppraisal vo = knoAppraisalService.selectKnoAppraisal(knoAppraisal);
			model.addAttribute("knoAppraisal", vo);
			return "egovframework/com/dam/app/EgovComDamAppraisalModify";
		} else if (sCmd.equals("Modify")) {
			beanValidator.validate(knoAppraisal, bindingResult);
			if (bindingResult.hasErrors()){
				KnoAppraisal vo = knoAppraisalService.selectKnoAppraisal(knoAppraisal);
				model.addAttribute("knoAppraisal", vo);
				return "egovframework/com/dam/app/EgovComDamAppraisalModify";
			}

        	// 아이디 설정
			//knoAppraisal.setFrstRegisterId((String)loginVO.getUniqId());
			//knoAppraisal.setLastUpdusrId((String)loginVO.getUniqId());
			knoAppraisal.setSpeId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

			knoAppraisalService.updateKnoAppraisal(knoAppraisal);
			return "forward:/dam/app/EgovComDamAppraisalList.do";
		} else {
			return "forward:/dam/app/EgovComDamAppraisalList.do";
		}
	}

	/**
	 * 기 등록된 지식정보평가 정보를 삭제한다.
	 * @param AppraisalknoAps - 지식정보평가 model
	 * @return String - 리턴 Url
	 *
	 * @param knoAps
	 */
	//public String deleteKnoAppraisal(knoAps knoAps){
	//	return "egovframework/com/";
	//}

}