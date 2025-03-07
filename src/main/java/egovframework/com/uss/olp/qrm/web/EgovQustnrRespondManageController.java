package egovframework.com.uss.olp.qrm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qrm.service.EgovQustnrRespondManageService;
import egovframework.com.uss.olp.qrm.service.QustnrRespondManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
/**
 * 설문응답자관리 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자       수정내용
 *  -----------    --------    ---------------------------
 *   2009.03.20  장동한		최초 생성
 *   2011.08.26  정진오		IncludedInfo annotation 추가
 *   2024.10.29  권태성		등록 /수정 화면과 처리 로직 분리
 *
 * </pre>
 */

@Controller
public class EgovQustnrRespondManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrRespondManageController.class);

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovQustnrRespondManageService")
    private EgovQustnrRespondManageService egovQustnrRespondManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    /**
     * 응답자정보 목록을 조회한다.
     *
     * @param searchVO
     * @param commandMap
     * @param qustnrRespondManageVO
     * @param model
     * @return "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageList"
     * @throws Exception
     */
    @IncludedInfo(name = "응답자관리", order = 620, gid = 50)
    @RequestMapping(value = "/uss/olp/qrm/EgovQustnrRespondManageList.do")
    public String egovQustnrRespondManageList(@ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap, QustnrRespondManageVO qustnrRespondManageVO, ModelMap model) throws Exception {

        String sSearchMode = commandMap.get("searchMode") == null ? "" : (String) commandMap.get("searchMode");

        // 설문지정보에서 넘어오면 자동검색 설정
        if (sSearchMode.equals("Y")) {
            searchVO.setSearchCondition("QESTNR_ID");
            searchVO.setSearchKeyword(qustnrRespondManageVO.getQestnrId());
        }

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

        List<EgovMap> sampleList = egovQustnrRespondManageService.selectQustnrRespondManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        int totCnt = egovQustnrRespondManageService.selectQustnrRespondManageListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageList";
    }

    /**
     * 응답자정보 목록을 상세조회 조회한다.
     *
     * @param searchVO
     * @param qustnrRespondManageVO
     * @param commandMap
     * @param model
     * @return "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageDetail"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/qrm/EgovQustnrRespondManageDetail.do")
    public String egovQustnrRespondManageDetail(@ModelAttribute QustnrRespondManageVO qustnrRespondManageVO, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

        String sLocationUrl = "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageDetail";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

        if (sCmd.equals("del")) {
            egovQustnrRespondManageService.deleteQustnrRespondManage(qustnrRespondManageVO);
            sLocationUrl = "redirect:/uss/olp/qrm/EgovQustnrRespondManageList.do";
        } else {
            // 성별코드조회
            ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
            voComCode.setCodeId("COM014");
            List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
            model.addAttribute("comCode014", listComCode);

            // 직업코드조회
            voComCode.setCodeId("COM034");
            listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
            model.addAttribute("comCode034", listComCode);

            List<EgovMap> resultList = egovQustnrRespondManageService.selectQustnrRespondManageDetail(qustnrRespondManageVO);
            model.addAttribute("resultList", resultList);
        }

        return sLocationUrl;
    }

	/**
	 * 응답자정보 수정화면
	 *
	 * @param searchVO
	 * @param qustnrRespondManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/olp/qrm/EgovQustnrRespondManageModifyView.do")
	public String qustnrRespondManageModify(@ModelAttribute QustnrRespondManageVO qustnrRespondManageVO, ModelMap model) throws Exception {

		//###
		LOGGER.debug("##### qustnrRespondManageModify vo >>> {}", qustnrRespondManageVO.getQestnrRespondId());
		
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 성별코드조회
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
		voComCode.setCodeId("COM014");
		List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("comCode014", listComCode);

		// 직업코드조회
		voComCode.setCodeId("COM034");
		listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("comCode034", listComCode);

		List<EgovMap> resultList = egovQustnrRespondManageService.selectQustnrRespondManageDetail(qustnrRespondManageVO);
		model.addAttribute("resultList", resultList);

		return "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageModify";
	}

	/**
	 * 응답자정보를 수정한다.
	 *
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrRespondManageVO
	 * @param bindingResult
	 * @param model
	 * @return "redirect:/uss/olp/qrm/EgovQustnrRespondManageList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/olp/qrm/EgovQustnrRespondManageModify.do")
	public String qustnrRespondManageModify(@ModelAttribute("qustnrRespondManageVO") QustnrRespondManageVO qustnrRespondManageVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 성별코드조회
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
		voComCode.setCodeId("COM014");
		List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("comCode014", listComCode);

		// 직업코드조회
		voComCode.setCodeId("COM034");
		listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("comCode034", listComCode);

		// 서버 validate 체크
		beanValidator.validate(qustnrRespondManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageModify";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String uniqId = (loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		// 아이디 설정
		qustnrRespondManageVO.setFrstRegisterId(uniqId);
		qustnrRespondManageVO.setLastUpdusrId(uniqId);

		egovQustnrRespondManageService.updateQustnrRespondManage(qustnrRespondManageVO);

		return "redirect:/uss/olp/qrm/EgovQustnrRespondManageList.do";
	}



	/**
	 * 응답자정보 등록화면
	 *
	 * @param searchVO
	 * @param qustnrRespondManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/olp/qrm/EgovQustnrRespondManageRegistView.do")
	public String qustnrRespondManageRegist(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("qustnrRespondManageVO") QustnrRespondManageVO qustnrRespondManageVO,
			ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 성별코드조회
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
		voComCode.setCodeId("COM014");
		List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("comCode014", listComCode);

		// 직업코드조회
		voComCode.setCodeId("COM034");
		listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("comCode034", listComCode);

		return "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageRegist";
	}
	
	/**
	 * 응답자정보를 등록한다.
	 *
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrRespondManageVO
	 * @param bindingResult
	 * @param model
	 * @return "redirect:/uss/olp/qrm/EgovQustnrRespondManageList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/olp/qrm/EgovQustnrRespondManageRegist.do")
	public String qustnrRespondManageRegist(@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrRespondManageVO") QustnrRespondManageVO qustnrRespondManageVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 성별코드조회
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
		voComCode.setCodeId("COM014");
		List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("comCode014", listComCode);

		// 직업코드조회
		voComCode.setCodeId("COM034");
		listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
		model.addAttribute("comCode034", listComCode);

		// 서버 validate 체크
		beanValidator.validate(qustnrRespondManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/olp/qrm/EgovQustnrRespondManageRegist";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String uniqId = (loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		// 아이디 설정
		qustnrRespondManageVO.setFrstRegisterId(uniqId);
		qustnrRespondManageVO.setLastUpdusrId(uniqId);

		egovQustnrRespondManageService.insertQustnrRespondManage(qustnrRespondManageVO);

		return "redirect:/uss/olp/qrm/EgovQustnrRespondManageList.do";
	}

}
