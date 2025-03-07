package egovframework.com.dam.mgm.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.validator.GenericValidator;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.mgm.service.EgovKnoManagementService;
import egovframework.com.dam.mgm.service.KnoManagement;
import egovframework.com.dam.mgm.service.KnoManagementVO;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovKnoManagementController.class);
	
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
    public String selectKnoManagementList(@ModelAttribute("searchVO") KnoManagementVO searchVO
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

		List<EgovMap> resultList = knoManagementService.selectKnoManagementList(searchVO);
		model.addAttribute("resultList", resultList);

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
			) throws Exception {

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }
        // 로그인 객체 선언
	    LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

	    knoManagement.setEmplyrId(loginVO.getUniqId());

		KnoManagement result = knoManagementService.selectKnoManagement(knoManagement);
		model.addAttribute("result", result);
		return "egovframework/com/dam/mgm/EgovComDamManagementDetail";
	}

	/**
	 * 기 등록 된 지식정보 정보를 수정 한다.
	 * @param ManagementKnoNm - 지식정보 model
	 * @return String - 리턴 Url
	 *
	 * @param knoNm
	 */
	@GetMapping(value="/dam/mgm/EgovComDamManagementModify.do")
	public String updateKnoManagementView(KnoManagement knoManagement
			, ModelMap model
			) throws Exception {

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "redirect:/uat/uia/egovLoginUsr.do";
	    }

		//로그인 객체 선언
	    LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        if (loginVO != null) {
            knoManagement.setEmplyrId(loginVO.getUniqId());
        }
        updateKnoManagementViewInit(knoManagement, model);

		return "egovframework/com/dam/mgm/EgovComDamManagementModify";
	}

	/**
     * 기 등록 된 지식정보 정보를 수정 한다. 초기값
     * 
     * @param knoManagement
     * @param model
     * @throws Exception
     */
    private void updateKnoManagementViewInit(KnoManagement knoManagement, ModelMap model) throws Exception {
        model.addAttribute("resultKnoManagement", knoManagementService.selectKnoManagement(knoManagement));

        LOGGER.debug("knoManagement>{}", knoManagement);
        LOGGER.debug("knoManagement>{}", model.get("knoManagement"));
    }

    /**
     * 기 등록 된 지식정보 정보를 수정 한다.
     * @param ManagementKnoNm - 지식정보 model
     * @return String - 리턴 Url
     *
     * @param knoNm
     */
    @PostMapping(value = "/dam/mgm/EgovComDamManagementModify.do")
    public String updateKnoManagement(KnoManagement knoManagement, BindingResult bindingResult, ModelMap model)
            throws Exception {

        // Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        if (loginVO != null) {
            knoManagement.setEmplyrId(loginVO.getUniqId());
            knoManagement.setLastUpdusrId(loginVO.getUniqId());
        }

        beanValidator.validate(knoManagement, bindingResult);
        if (bindingResult.hasErrors()) {
            if (GenericValidator.isDate(knoManagement.getJunkYmd(), "yyyyMMdd", true)) {
                knoManagement.setJunkYmd(LocalDate.parse(knoManagement.getJunkYmd(), DateTimeFormatter.BASIC_ISO_DATE).format(DateTimeFormatter.ISO_LOCAL_DATE));
            }
            updateKnoManagementViewInit(knoManagement, model);
            return "egovframework/com/dam/mgm/EgovComDamManagementModify";
        }

        knoManagementService.updateKnoManagement(knoManagement);
        return "forward:/dam/mgm/EgovComDamManagementList.do";
	}

}