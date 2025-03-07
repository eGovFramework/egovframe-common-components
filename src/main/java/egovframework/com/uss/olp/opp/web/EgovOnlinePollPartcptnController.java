package egovframework.com.uss.olp.opp.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.opp.service.EgovOnlinePollPartcptnService;
import egovframework.com.uss.olp.opp.service.OnlinePollPartcptn;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 온라인POLL참여를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자      수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한		최초 생성
 *   2011.08.26	 정진오		IncludedInfo annotation 추가
 *   2011.10.27  서준식		온라인 POLL 중복 투표 방지 기능 추가
 *   2024.10.29  권태성		화면에서 사용할 현재일자 정보 model에 추가(egovOnlinePollPartcptnList())
 * </pre>
 */


@Controller
public class EgovOnlinePollPartcptnController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovOnlinePollPartcptnController.class);

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "egovOnlinePollPartcptnService")
    private EgovOnlinePollPartcptnService egovOnlinePollPartcptnService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Egov Common Code Service */
    @Resource(name="EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    /**
     * 온라인POLL참여 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param onlinePollPartcptn
     * @param model
     * @return "egovframework/com/uss/olp/opp/EgovOnlinePollList"
     * @throws Exception
     */
	@RequestMapping(value = "/uss/olp/opp/listEgovOnlinePollPartcptnMain.do")
    public String egovOnlinePollPartcptnMainList(
            @ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap,
            OnlinePollPartcptn onlinePollPartcptn, ModelMap model)
            throws Exception {

//        String sSearchMode = commandMap.get("searchMode") == null ? "" : (String) commandMap.get("searchMode");

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

        List<EgovMap> reusltList = egovOnlinePollPartcptnService.selectOnlinePollManageList(searchVO);
        model.addAttribute("resultList", reusltList);


        return "egovframework/com/uss/olp/opp/EgovOnlinePollPartcptnMainList";
    }


    /**
     * 온라인POLL참여 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param onlinePollPartcptn
     * @param model
     * @return "egovframework/com/uss/olp/opp/EgovOnlinePollList"
     * @throws Exception
     */
	@IncludedInfo(name="온라인poll참여", order = 661 ,gid = 50)
    @RequestMapping(value = "/uss/olp/opp/listOnlinePollPartcptn.do")
    public String egovOnlinePollPartcptnList(
            @ModelAttribute("searchVO") ComDefaultVO searchVO, @RequestParam Map<?, ?> commandMap,
            OnlinePollPartcptn onlinePollPartcptn, ModelMap model)
            throws Exception {

//        String sSearchMode = commandMap.get("searchMode") == null ? "" : (String) commandMap.get("searchMode");

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

        List<EgovMap> reusltList = egovOnlinePollPartcptnService.selectOnlinePollManageList(searchVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        int totCnt = egovOnlinePollPartcptnService.selectOnlinePollManageListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("now", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        return "egovframework/com/uss/olp/opp/EgovOnlinePollPartcptnList";
    }

    /**
     * 온라인POLL참여를 등록한다.
     * @param searchVO
     * @param commandMap
     * @param onlinePollPartcptn
     * @param bindingResult
     * @param model
     * @return
     *         "/uss/olp/opp/EgovOnlinePollPartcptnRegist"
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/uss/olp/opp/registOnlinePollPartcptn.do")
    public String egovOnlinePollPartcptnRegist(
            @ModelAttribute("searchVO") ComDefaultVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("onlinePollPartcptn") OnlinePollPartcptn onlinePollPartcptn,
            BindingResult bindingResult, ModelMap model) throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        String sLocationUrl = "egovframework/com/uss/olp/opp/EgovOnlinePollPartcptnRegist";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        LOGGER.info("cmd => {}", sCmd);

        if (sCmd.equals("save")) {
            //아이디 설정
            onlinePollPartcptn.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            onlinePollPartcptn.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

            //투표여부 체크
            if(egovOnlinePollPartcptnService.selectOnlinePollResult(onlinePollPartcptn) != 0){
            	 String ReusltScript = "";

                 ReusltScript += "<script type='text/javaScript' language='javascript'>";
                 ReusltScript += "alert('한 온라인POLL엔 한번만 투표 가능합니다. ');";
                 ReusltScript += "</script>";

                 model.addAttribute("reusltScript", ReusltScript);
                 return "forward:/uss/olp/opp/listOnlinePollPartcptn.do";
            }

            egovOnlinePollPartcptnService.insertOnlinePollResult(onlinePollPartcptn);

            String ReusltScript = "";

            ReusltScript += "<script type='text/javaScript' language='javascript'>";
            ReusltScript += "alert(' 온라인POLL참여에 응해주셔서 감사합니다!  ');";
            ReusltScript += "</script>";

            model.addAttribute("reusltScript", ReusltScript);
            sLocationUrl = "forward:/uss/olp/opp/listOnlinePollPartcptn.do";
        } else {
            //POLL종류 설정
            ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
            voComCode = new ComDefaultCodeVO();
            voComCode.setCodeId("COM039");
            List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
            model.addAttribute("pollKindCodeList", listComCode );

            //POLL페기유무 설정 /POLL자동페기유무
            List<Object> listPollDeuseYn = new ArrayList<Object>();
            voComCode  = new ComDefaultCodeVO();
            voComCode.setCodeId("COM038");
            model.addAttribute("pollDeuseYnList", cmmUseService.selectCmmCodeDetail(voComCode));

            //온라인POLL관리 정보 설정
            List<EgovMap> reusltPollManage = egovOnlinePollPartcptnService.selectOnlinePollManageDetail(onlinePollPartcptn);
            model.addAttribute("PollManage", reusltPollManage);
            //온라인POLL항목 정보 설정
            List<EgovMap> reusltPollItem = egovOnlinePollPartcptnService.selectOnlinePollItemDetail(onlinePollPartcptn);
            model.addAttribute("PollItem", reusltPollItem);
        }

        return sLocationUrl;
    }

    /**
     * 온라인POLL관리 통계를 조회한다.
     * @param onlinePollPartcptn
     * @param model
     * @return
     *         "/uss/olp/opm/EgovOnlinePollManageStatistics"
     * @throws Exception
     */
	@RequestMapping(value = "/uss/olp/opp/statisticsOnlinePollPartcptn.do")
    public String egovOnlinePollManageStatistics(
    		@RequestParam Map<?, ?> commandMap,
            @ModelAttribute("onlinePollPartcptn") OnlinePollPartcptn onlinePollPartcptn,
            HttpServletRequest request,
            ModelMap model) throws Exception {

        //POLL종류 설정
        ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
        voComCode = new ComDefaultCodeVO();
        voComCode.setCodeId("COM039");
        List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
        model.addAttribute("pollKindCodeList", listComCode );

        //POLL페기유무 설정 /POLL자동페기유무
//        List<?> listPollDeuseYn = new ArrayList<Object>();
        voComCode  = new ComDefaultCodeVO();
        voComCode.setCodeId("COM038");
        model.addAttribute("pollDeuseYnList", cmmUseService.selectCmmCodeDetail(voComCode));

        //온라인POLL관리 정보 설정
        List<EgovMap> reusltPollManageList = egovOnlinePollPartcptnService.selectOnlinePollManageDetail(onlinePollPartcptn);
        model.addAttribute("PollManageList", reusltPollManageList);
        //온라인POLL항목 정보 설정
        List<EgovMap> reusltPollItemList = egovOnlinePollPartcptnService.selectOnlinePollItemDetail(onlinePollPartcptn);
        model.addAttribute("PollItemList", reusltPollItemList);
        //온라인POLL결과 정보 설정
        List<EgovMap> reusltList = egovOnlinePollPartcptnService.selectOnlinePollManageStatistics(onlinePollPartcptn);
        model.addAttribute("statisticsList", reusltList);

        //이전 주소
        model.addAttribute("returnUrl", request.getHeader("REFERER"));

        model.addAttribute("linkType", commandMap.get("linkType") == null ? "" : (String) commandMap.get("linkType"));


        return "egovframework/com/uss/olp/opp/EgovOnlinePollPartcptnStatistics";
    }

}
