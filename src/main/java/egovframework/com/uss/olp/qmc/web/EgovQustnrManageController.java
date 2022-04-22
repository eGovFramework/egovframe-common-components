package egovframework.com.uss.olp.qmc.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qmc.service.EgovQustnrManageService;
import egovframework.com.uss.olp.qmc.service.QustnrManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 설문관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovQustnrManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovQustnrManageService")
	private EgovQustnrManageService egovQustnrManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/**
	 * 설문관리 팝업 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qmc/EgovQustnrManageListPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qmc/EgovQustnrManageListPopup.do")
	public String egovQustnrManageListPopup(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrManageVO qustnrManageVO,
    		ModelMap model)
    throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		if(sCmd.equals("del")){
			egovQustnrManageService.deleteQustnrManage(qustnrManageVO);
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

        List<?> sampleList = egovQustnrManageService.selectQustnrManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrManageService.selectQustnrManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);


		return "egovframework/com/uss/olp/qmc/EgovQustnrManageListPopup";
	}

	/**
	 * 설문관리 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrManageVO
	 * @param model
	 * @return  "/uss/olp/qmc/EgovQustnrManageList"
	 * @throws Exception
	 */
	@IncludedInfo(name="설문관리", order = 590 ,gid = 50)
	@RequestMapping(value="/uss/olp/qmc/EgovQustnrManageList.do")
	public String egovQustnrManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrManageVO qustnrManageVO,
    		ModelMap model)
    throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		if(sCmd.equals("del")){
			egovQustnrManageService.deleteQustnrManage(qustnrManageVO);
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

        List<?> sampleList = egovQustnrManageService.selectQustnrManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrManageService.selectQustnrManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olp/qmc/EgovQustnrManageList";
	}

	/**
	 * 설문관리 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/qmc/EgovQustnrManageDetail";
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qmc/EgovQustnrManageDetail.do")
	public String egovQustnrManageDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			QustnrManageVO qustnrManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/uss/olp/qmc/EgovQustnrManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			egovQustnrManageService.deleteQustnrManage(qustnrManageVO);
			sLocationUrl = "redirect:/uss/olp/qmc/EgovQustnrManageList.do";
		}else{

	     	//공통코드  직업유형 조회
	    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	    	voComCode.setCodeId("COM034");
	    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("comCode034", listComCode);

	        List<?> sampleList = egovQustnrManageService.selectQustnrManageDetail(qustnrManageVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	 * 설문관리를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/uss/olp/qmc/EgovQustnrManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qmc/EgovQustnrManageModify.do")
	public String qustnrManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrManageVO qustnrManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/uss/olp/qmc/EgovQustnrManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

     	//공통코드  직업유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM034");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode034", listComCode);

        if(sCmd.equals("save")){

            beanValidator.validate(qustnrManageVO, bindingResult);
    		if (bindingResult.hasErrors()){

                List<?> sampleList = egovQustnrManageService.selectQustnrManageDetail(qustnrManageVO);
                model.addAttribute("resultList", sampleList);

            	//설문템플릿 정보 불러오기
                List<?> listQustnrTmplat = egovQustnrManageService.selectQustnrTmplatManageList(qustnrManageVO);
                model.addAttribute("listQustnrTmplat", listQustnrTmplat);

    			return sLocationUrl;
    		}

    		//아이디 설정
    		qustnrManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		qustnrManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        	egovQustnrManageService.updateQustnrManage(qustnrManageVO);
        	sLocationUrl = "redirect:/uss/olp/qmc/EgovQustnrManageList.do";
        }else{
            List<?> sampleList = egovQustnrManageService.selectQustnrManageDetail(qustnrManageVO);
            model.addAttribute("resultList", sampleList);

            QustnrManageVO newQustnrManageVO =  egovQustnrManageService.selectQustnrManageDetailModel(qustnrManageVO);
            model.addAttribute("qustnrManageVO", newQustnrManageVO);

        	//설문템플릿 정보 불러오기
            List<?> listQustnrTmplat = egovQustnrManageService.selectQustnrTmplatManageList(qustnrManageVO);
            model.addAttribute("listQustnrTmplat", listQustnrTmplat);
        }

		return sLocationUrl;
	}

	/**
	 * 설문관리를 등록한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/uss/olp/qmc/EgovQustnrManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qmc/EgovQustnrManageRegist.do")
	public String qustnrManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrManageVO") QustnrManageVO qustnrManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/uss/olp/qmc/EgovQustnrManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

     	//공통코드  직업유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM034");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode034", listComCode);

        if(sCmd.equals("save")){

            beanValidator.validate(qustnrManageVO, bindingResult);
    		if (bindingResult.hasErrors()){
            	//설문템플릿 정보 불러오기
                List<?> listQustnrTmplat = egovQustnrManageService.selectQustnrTmplatManageList(qustnrManageVO);
                model.addAttribute("listQustnrTmplat", listQustnrTmplat);
    			return sLocationUrl;
    		}

    		//아이디 설정
    		qustnrManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    		qustnrManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        	egovQustnrManageService.insertQustnrManage(qustnrManageVO);
        	sLocationUrl = "redirect:/uss/olp/qmc/EgovQustnrManageList.do";
        }else{
        	//설문템플릿 정보 불러오기
            List<?> listQustnrTmplat = egovQustnrManageService.selectQustnrTmplatManageList(qustnrManageVO);
            model.addAttribute("listQustnrTmplat", listQustnrTmplat);

        }

		return sLocationUrl;
	}
}


