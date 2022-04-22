package egovframework.com.uss.olp.opm.web;

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
import egovframework.com.uss.olp.opm.service.EgovOnlinePollManageService;
import egovframework.com.uss.olp.opm.service.OnlinePollItem;
import egovframework.com.uss.olp.opm.service.OnlinePollManage;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 온라인POLL관리를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */


@Controller
public class EgovOnlinePollManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovOnlinePollManageController.class);

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "egovOnlinePollManageService")
    private EgovOnlinePollManageService egovOnlinePollManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Egov Common Code Service */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    /**
     * 온라인POLL관리 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param onlinePollManage
     * @param model
     * @return "egovframework/com/uss/olp/opm/EgovOnlinePollList"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@IncludedInfo(name="온라인poll관리", order = 660 ,gid = 50)
    @RequestMapping(value = "/uss/olp/opm/listOnlinePollManage.do")
    public String egovOnlinePollManageList(
            @ModelAttribute("searchVO") ComDefaultVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            OnlinePollManage onlinePollManage, ModelMap model)
            throws Exception {

        String sSearchMode = commandMap.get("searchMode") == null ? "" : (String) commandMap.get("searchMode");

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

        List<?> reusltList = egovOnlinePollManageService.selectOnlinePollManageList(searchVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        int totCnt = egovOnlinePollManageService.selectOnlinePollManageListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/olp/opm/EgovOnlinePollManageList";
    }

    /**
     * 온라인POLL관리 목록을 상세조회 조회한다.
     * @param searchVO
     * @param onlinePollVO
     * @param commandMap
     * @param model
     * @return
     *         "/uss/olp/opm/EgovOnlinePollDetail"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/opm/detailOnlinePollManage.do")
    public String egovOnlinePollManageDetail(
            @ModelAttribute("searchVO") ComDefaultVO searchVO,
            OnlinePollManage onlinePollManage,
            @RequestParam Map<?, ?> commandMap,
            ModelMap model) throws Exception {

        String sLocationUrl = "egovframework/com/uss/olp/opm/EgovOnlinePollManageDetail";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

        //게시물 삭제
        if (sCmd.equals("del")) {
            egovOnlinePollManageService.deleteOnlinePollManage(onlinePollManage);
            sLocationUrl = "redirect:/uss/olp/opm/listOnlinePollManage.do";
        } else {


            model.addAttribute("onlinePollManage", egovOnlinePollManageService.selectOnlinePollManageDetail(onlinePollManage));
        }

        //POLL종류 설정
        ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
        voComCode = new ComDefaultCodeVO();
        voComCode.setCodeId("COM039");
        List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
        model.addAttribute("pollKindCodeList", listComCode );

        return sLocationUrl;
    }

    /**
     * 온라인POLL관리를 수정한다.
     * @param searchVO
     * @param commandMap
     * @param onlinePollManage
     * @param bindingResult
     * @param model
     * @return
     *         "/uss/olp/opm/EgovOnlinePollUpdt"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/opm/updtOnlinePollManage.do")
    public String egovOnlinePollManageModify(
            @ModelAttribute("searchVO") ComDefaultVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            OnlinePollManage onlinePollManage,
            BindingResult bindingResult,
            ModelMap model) throws Exception {

        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        String sLocationUrl = "egovframework/com/uss/olp/opm/EgovOnlinePollManageUpdt";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

        if (sCmd.equals("save")) {
            //서버  validate 체크
            beanValidator.validate(onlinePollManage, bindingResult);
            if(bindingResult.hasErrors()){
                return sLocationUrl;
            }
            //아이디 설정
            onlinePollManage.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            onlinePollManage.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

            egovOnlinePollManageService.updateOnlinePollManage(onlinePollManage);

            sLocationUrl = "redirect:/uss/olp/opm/listOnlinePollManage.do";
        } else {


            //게시물 정보 설정
            OnlinePollManage onlinePollManageVO = egovOnlinePollManageService.selectOnlinePollManageDetail(onlinePollManage);
            model.addAttribute("onlinePollManage", onlinePollManageVO);
        }

        //POLL종류 Select박스 설정
        ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
        voComCode = new ComDefaultCodeVO();
        voComCode.setCodeId("COM039");
        List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
        model.addAttribute("pollKindCodeList", listComCode );

        return sLocationUrl;
    }

    /**
     * 온라인POLL관리를 등록한다.
     * @param searchVO
     * @param commandMap
     * @param onlinePollManage
     * @param bindingResult
     * @param model
     * @return
     *         "/uss/olp/opm/EgovOnlinePollRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/opm/registOnlinePollManage.do")
    public String egovOnlinePollManageRegist(
            @ModelAttribute("searchVO") ComDefaultVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("onlinePollManage") OnlinePollManage onlinePollManage,
            BindingResult bindingResult, ModelMap model) throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        String sLocationUrl = "egovframework/com/uss/olp/opm/EgovOnlinePollManageRegist";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        LOGGER.info("cmd => {}", sCmd);

        if (sCmd.equals("save")) {
            //서버  validate 체크
            beanValidator.validate(onlinePollManage, bindingResult);
            if(bindingResult.hasErrors()){
                return sLocationUrl;
            }
            //아이디 설정
            onlinePollManage.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            onlinePollManage.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

            egovOnlinePollManageService.insertOnlinePollManage(onlinePollManage);

            sLocationUrl = "redirect:/uss/olp/opm/listOnlinePollManage.do";
        }

        //POLL종류 Select박스 설정
        ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
        voComCode = new ComDefaultCodeVO();
        voComCode.setCodeId("COM039");
        List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
        model.addAttribute("pollKindCodeList", listComCode );

        return sLocationUrl;
    }

    /**
     * 온라인POLL항목을조회한다.
     * @param searchVO
     * @param commandMap
     * @param onlinePollItem
     * @param bindingResult
     * @param model
     * @return
     *         "/uss/olp/opm/EgovOnlinePollRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/opm/listOnlinePollItem.do")
    public String egovOnlinePollItemList(
            @ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("onlinePollItem") OnlinePollItem onlinePollItem,
            ModelMap model)
            throws Exception {

        List<?> reusltList = egovOnlinePollManageService.selectOnlinePollItemList(onlinePollItem);
        model.addAttribute("resultList", reusltList);

        return "egovframework/com/uss/olp/opm/EgovOnlinePollItemList";
    }

    /**
     * 온라인POLL항목을 등록한다.
     * @param searchVO
     * @param commandMap
     * @param olinePollItem
     * @param bindingResult
     * @param model
     * @return
     *         "/uss/olp/opm/EgovOnlinePollRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/opm/registOnlinePollItem.do")
    public String egovOnlinePollItemRegist(
            @ModelAttribute("searchVO") ComDefaultVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            OnlinePollItem onlinePollItem,
            BindingResult bindingResult, ModelMap model) throws Exception {

        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        //아이디 설정
        onlinePollItem.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        onlinePollItem.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        egovOnlinePollManageService.insertOnlinePollItem(onlinePollItem);

        return "forward:/uss/olp/opm/listOnlinePollItem.do";
    }

    /**
     * 온라인POLL항목을 수정한다.
     * @param searchVO
     * @param commandMap
     * @param olinePollItem
     * @param bindingResult
     * @param model
     * @return
     *         "/uss/olp/opm/EgovOnlinePollRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/opm/updtOnlinePollItem.do")
    public String egovOnlinePollItemModify(
            @ModelAttribute("searchVO") ComDefaultVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            OnlinePollItem onlinePollItem,
            BindingResult bindingResult, ModelMap model) throws Exception {

        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        //아이디 설정
        onlinePollItem.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        onlinePollItem.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        egovOnlinePollManageService.updateOnlinePollItem(onlinePollItem);

        return "forward:/uss/olp/opm/listOnlinePollItem.do";
    }

    /**
     * 온라인POLL항목을 삭제한다.
     * @param searchVO
     * @param commandMap
     * @param olinePollItem
     * @param bindingResult
     * @param model
     * @return
     *         "/uss/olp/opm/EgovOnlinePollRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/opm/delOnlinePollItem.do")
    public String egovOnlinePollItemDelete(
            @ModelAttribute("searchVO") ComDefaultVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            OnlinePollItem onlinePollItem,
            BindingResult bindingResult, ModelMap model) throws Exception {

        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        egovOnlinePollManageService.deleteOnlinePollItem(onlinePollItem);

        return "forward:/uss/olp/opm/listOnlinePollItem.do";
    }


}
