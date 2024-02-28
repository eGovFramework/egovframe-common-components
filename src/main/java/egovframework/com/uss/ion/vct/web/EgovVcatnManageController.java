package egovframework.com.uss.ion.vct.web;

//import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.vct.service.EgovVcatnManageService;
import egovframework.com.uss.ion.vct.service.VcatnManage;
import egovframework.com.uss.ion.vct.service.VcatnManageVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 개요
 * - 휴가관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 휴가관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 휴가관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 * <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.6.15  이용          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovVcatnManageController {

	@Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovVcatnManageService")
    private EgovVcatnManageService egovVcatnManageService;

    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 휴가관리 목록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/uss/ion/vct/EgovVcatnManageListView.do")
    public String selectVcatnManageListView() throws Exception {

        return "egovframework/com/uss/ion/vct/EgovVcatnManageList";
    }

    /**
     * 휴가관리정보를 관리하기 위해 등록된 휴가관리 목록을 조회한다.
     * @param vcatnManageVO - 휴가관리 VO
     * @return String - 리턴 Url
     */
    @IncludedInfo(name = "휴가관리", order = 900, gid = 50)
    @RequestMapping(value = "/uss/ion/vct/EgovVcatnManageList.do")
    public String selectVcatnManageList(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO,
        ModelMap model) throws Exception {

        String searchKeyword = vcatnManageVO.getSearchKeyword();

        java.util.Calendar cal = java.util.Calendar.getInstance();
        String[] yearList = new String[5];
        for (int x = 0; x < 5; x++) {
            yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) - x);
        }

        LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        if (user == null) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        vcatnManageVO = egovVcatnManageService.selectIndvdlYrycManage(user.getUniqId());

        if (vcatnManageVO == null) {
            model.addAttribute("messageTemp",
                egovMessageSource.getMessage("comUssIonVct.vcatnManageList.validate.move")); // 휴가 사용을 위한 개인연차 등록을 위해 개인연차관리 콤포넌트로 이동
            return "egovframework/com/uss/ion/yrc/EgovIndvdlYrycManageList";
        } else {

            vcatnManageVO.setSearchKeyword(searchKeyword);

            /** paging */
            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(vcatnManageVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(vcatnManageVO.getPageUnit());
            paginationInfo.setPageSize(vcatnManageVO.getPageSize());

            vcatnManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            vcatnManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
            vcatnManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            model.addAttribute("vcatnManageVO", vcatnManageVO);

            vcatnManageVO.setApplcntId(user.getUniqId());
            vcatnManageVO.setVcatnManageList(egovVcatnManageService.selectVcatnManageList(vcatnManageVO));

            model.addAttribute("vcatnManageList", vcatnManageVO.getVcatnManageList());

            int totCnt = egovVcatnManageService.selectVcatnManageListTotCnt(vcatnManageVO);
            paginationInfo.setTotalRecordCount(totCnt);

            String accessControll = user.getOrgnztId();

            model.addAttribute("access", accessControll);
            model.addAttribute("yearList", yearList);
            model.addAttribute("paginationInfo", paginationInfo);
            model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

            return "egovframework/com/uss/ion/vct/EgovVcatnManageList";
        }
    }

    /**
     * 등록된 휴가관리의 상세정보를 조회한다.
     *
     * @param vcatnManageVO - 휴가관리 VO
     * @return String - 리턴 Url
     */
    @RequestMapping(value = "/uss/ion/vct/EgovVcatnManageDetail.do")
    public String selectVcatnManage(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, @ModelAttribute("vcatnManage") VcatnManage vcatnManage, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd"); // 상세정보 구분
        vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
        vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));

        // 등록 상세정보
        VcatnManageVO vcatnManageVOTemp = egovVcatnManageService.selectVcatnManage(vcatnManageVO);

        model.addAttribute("vcatnManageVO", vcatnManageVOTemp);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        if (sCmd.equals("updt")) {

            ComDefaultCodeVO vo = new ComDefaultCodeVO();
            vo.setCodeId("COM056");
            List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);

            model.addAttribute("vcatnSeCode", vcatnSeCodeList);
            model.addAttribute("vcatnManage", vcatnManageVOTemp);
            return "egovframework/com/uss/ion/vct/EgovVcatnUpdt";
        } else {
            return "egovframework/com/uss/ion/vct/EgovVcatnDetail";
        }
    }

    /**
     * 휴가관리 등록 화면으로 이동한다.
     *
     * @return String - 리턴 Url
     */
    @RequestMapping(value = "/uss/ion/vct/EgovVcatnRegist.do")
    public String insertViewVcatnManage(@ModelAttribute("vcatnManage") VcatnManage vcatnManage, @ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, ModelMap model) throws Exception {
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        VcatnManageVO vcatnManageVO1 = egovVcatnManageService.selectIndvdlYrycManage(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
        vcatnManageVO1.setApplcntId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
        vcatnManageVO1.setApplcntNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));
        vcatnManageVO1.setOrgnztNm(user == null ? "" : EgovStringUtil.isNullToString(user.getOrgnztNm()));

        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM056");
        List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);

        model.addAttribute("vcatnSeCode", vcatnSeCodeList);
        model.addAttribute("vcatnManageVO", vcatnManageVO1);

        return "egovframework/com/uss/ion/vct/EgovVcatnRegist";
    }

    /**
     * 휴가관리정보를 신규로 등록한다.
     *
     * @param vcatnManage - 휴가관리 model
     * @return String - 리턴 Url
     */
    @RequestMapping(value = "/uss/ion/vct/insertVcatnManage.do")
    public String insertVcatnManage(@ModelAttribute("vcatnManage") VcatnManage vcatnManage, @ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, BindingResult bindingResult, SessionStatus status, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        // 승인권자 소속명, 성명 유지
        model.addAttribute("infSanctnDtNm", commandMap.get("sanctnDtNm") == null ? "" : (String) commandMap.get("sanctnDtNm"));
        model.addAttribute("infOrgnztNm", commandMap.get("orgnztNm") == null ? "" : (String) commandMap.get("orgnztNm"));

        String sEnddeView = commandMap.get("enddeView") == null ? "" : (String) commandMap.get("enddeView"); // 종료일자 구분
        if (!sEnddeView.equals("")) {
            vcatnManage.setEndde(sEnddeView);
        }

        String sTemp = null;
        String sTempMessage = null;
        int iTemp = 0;

        beanValidator.validate(vcatnManage, bindingResult); // validation 수행

        if (bindingResult.hasErrors()) {
            model.addAttribute("vcatnManageVO", vcatnManageVO);
            return "egovframework/com/uss/ion/vct/EgovVcatnRegist";
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        if (user != null) {
            if (vcatnManage.getSanctnerId() != null) {
                vcatnManage.setConfmAt("A");
            }
            vcatnManage.setApplcntId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
            vcatnManage.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

            vcatnManageVO.setApplcntId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
            vcatnManageVO.setSearchKeyword(EgovStringUtil.removeMinusChar(vcatnManage.getBgnde()));
            // 시작일자 포함여부
            iTemp = egovVcatnManageService.selectVcatnManageDplctAt(vcatnManageVO);
            vcatnManageVO.setSearchKeyword(EgovStringUtil.removeMinusChar(vcatnManage.getEndde()));
            // 종료일자 포함여부
            iTemp += egovVcatnManageService.selectVcatnManageDplctAt(vcatnManageVO);

            if (iTemp == 0) {
                status.setComplete();
                sTemp = egovVcatnManageService.insertVcatnManage(vcatnManage, vcatnManageVO);

                if (sTemp.equals("01")) {
                    model.addAttribute("message", egovMessageSource.getMessage("comUssIonVct.common.inputSuccess"));
                    return "forward:/uss/ion/vct/EgovVcatnManageList.do";
                } else {
                    if (sTemp.equals("99")) {
                        sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.vacationSelectError");
                    } else if (sTemp.equals("09")) {
                        sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.thatYearOnly");
                    } else if (sTemp.equals("02")) {
                        sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.vacationFail");
                    } else if (sTemp.equals("03")) {
                        sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.halfVacationFail");
                    } else {
                        sTempMessage = "undefined error";
                    }
                    model.addAttribute("errorMessage", sTempMessage);

                    VcatnManageVO vcatnManageVO1 = egovVcatnManageService.selectIndvdlYrycManage(user.getUniqId());
                    vcatnManageVO1.setApplcntId(user.getUniqId());
                    vcatnManageVO1.setApplcntNm(user.getName());
                    vcatnManageVO1.setOrgnztNm(user.getOrgnztNm());
                    vcatnManageVO1.setTempBgnde(EgovDateUtil.formatDate(vcatnManage.getBgnde(), "-"));
                    vcatnManageVO1.setTempEndde(EgovDateUtil.formatDate(vcatnManage.getEndde(), "-"));

                    model.addAttribute("vcatnManageVO", vcatnManageVO1);
                    ComDefaultCodeVO vo = new ComDefaultCodeVO();
                    vo.setCodeId("COM056");
                    List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
                    model.addAttribute("vcatnSeCode", vcatnSeCodeList);

                    return "egovframework/com/uss/ion/vct/EgovVcatnRegist";
                }
            } else {

                model.addAttribute("errorMessage", egovMessageSource.getMessage("comUssIonVct.common.validate.duplicate"));

                VcatnManageVO vcatnManageVO1 = egovVcatnManageService.selectIndvdlYrycManage(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
                vcatnManageVO1.setApplcntId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
                vcatnManageVO1.setApplcntNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));
                vcatnManageVO1.setOrgnztNm(user == null ? "" : EgovStringUtil.isNullToString(user.getOrgnztNm()));
                model.addAttribute("vcatnManageVO", vcatnManageVO1);

                ComDefaultCodeVO vo = new ComDefaultCodeVO();
                vo.setCodeId("COM056");
                List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
                model.addAttribute("vcatnSeCode", vcatnSeCodeList);

                return "egovframework/com/uss/ion/vct/EgovVcatnRegist";
            }
        } else {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }
    }

    /**
     * 기 등록된 휴가관리정보를 수정한다.
     *
     * @param vcatnManage - 휴가관리 model
     * @return String - 리턴 Url
     */
    @RequestMapping(value = "/uss/ion/vct/updtVcatnManage.do")
    public String updtVcatnManage(@ModelAttribute("vcatnManage") VcatnManage vcatnManage, @ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {
        String sTemp = null;
        String sTempMessage = null;
        /*
         * beanValidator.validate(vcatnManage, bindingResult); //validation 수행
         *
         * if (bindingResult.hasErrors()) { model.addAttribute("vcatnManageVO",
         * vcatnManageVO); return "egovframework/com/uss/ion/vct/EgovVcatnUpdt"; } else
         * {
         *
         * vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(vcatnManage.getBgnde()));
         * vcatnManage.setEndde(EgovStringUtil.removeMinusChar(vcatnManage.getEndde()));
         * sTempMessage = egovVcatnManageService.updtVcatnManage(vcatnManage,
         * vcatnManageVO); return "forward:/uss/ion/vct/EgovVcatnManageList.do"; }
         */
        // KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if (!isAuthenticated) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        if (user != null) {
            // 221116 김혜준 2022 시큐어코딩 조치
            vcatnManage.setFrstRegisterId(EgovStringUtil.isNullToString(user.getUniqId()));
            sTemp = egovVcatnManageService.updtVcatnManage(vcatnManage, vcatnManageVO);
            // 221116 김혜준 2022 시큐어코딩 조치
            status.setComplete();
            // sTemp = egovVcatnManageService.insertVcatnManage(vcatnManage, vcatnManageVO);

            if (sTemp.equals("01")) {
                model.addAttribute("message", egovMessageSource.getMessage("comUssIonVct.common.inputSuccess"));
                return "forward:/uss/ion/vct/EgovVcatnManageList.do";
            } else {

                if (sTemp.equals("99")) {
                    sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.vacationSelectError");
                } else if (sTemp.equals("09")) {
                    sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.thatYearOnly");
                } else if (sTemp.equals("02")) {
                    sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.vacationFail");
                } else if (sTemp.equals("03")) {
                    sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.halfVacationFail");
                } else {
                    sTempMessage = "undefined error";
                }

                model.addAttribute("errorMessage", sTempMessage);

                VcatnManageVO vcatnManageVO1 = egovVcatnManageService.selectIndvdlYrycManage(user.getUniqId());
                vcatnManageVO1.setApplcntId(user.getUniqId());
                vcatnManageVO1.setApplcntNm(user.getName());
                vcatnManageVO1.setOrgnztNm(user.getOrgnztNm());
                vcatnManageVO1.setTempBgnde(EgovDateUtil.formatDate(vcatnManage.getBgnde(), "-"));
                vcatnManageVO1.setTempEndde(EgovDateUtil.formatDate(vcatnManage.getEndde(), "-"));

                model.addAttribute("vcatnManageVO", vcatnManageVO1);
                ComDefaultCodeVO vo = new ComDefaultCodeVO();
                vo.setCodeId("COM056");
                List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
                model.addAttribute("vcatnSeCode", vcatnSeCodeList);

                return "egovframework/com/uss/ion/vct/EgovVcatnUpdt";
            }
        } else {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }
    }

    /**
     * 기 등록된 휴가관리정보를 삭제한다.
     * @param vcatnManage - 휴가관리 model
     * @return String - 리턴 Url
     */
    @RequestMapping(value = "/uss/ion/vct/deleteVcatnManage.do")
    public String deleteVcatnManage(@ModelAttribute("vcatnManage") VcatnManage vcatnManage,
        SessionStatus status,
        ModelMap model) throws Exception {
        vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(vcatnManage.getBgnde()));
        vcatnManage.setEndde(EgovStringUtil.removeMinusChar(vcatnManage.getEndde()));
        egovVcatnManageService.deleteVcatnManage(vcatnManage);
        status.setComplete();
        model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/uss/ion/vct/EgovVcatnManageList.do";
    }

    /*** 승인관련 ***/
    /**
     * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록을 조회한다.
     * @param vcatnManageVO - 휴가관리 VO
     * @return String - 리턴 Url
     */
    @IncludedInfo(name = "휴가승인관리", order = 901, gid = 50)
    @RequestMapping(value = "/uss/ion/vct/EgovVcatnConfmList.do")
    public String selectVcatnManageConfmList(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO,
        ModelMap model) throws Exception {

        java.util.Calendar cal = java.util.Calendar.getInstance();
        String[] yearList = new String[5];
        for (int x = 0; x < 5; x++) {
            yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) - x);
        }

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(vcatnManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(vcatnManageVO.getPageUnit());
        paginationInfo.setPageSize(vcatnManageVO.getPageSize());

        vcatnManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        vcatnManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        vcatnManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        if (user == null) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        vcatnManageVO.setSanctnerId(user.getUniqId()); //사용자가 승인권자인지 조건값 setting

        vcatnManageVO.setSearchKeyword(vcatnManageVO.getSearchYear() + vcatnManageVO.getSearchMonth());
        vcatnManageVO.setVcatnManageList(egovVcatnManageService.selectVcatnManageConfmList(vcatnManageVO));

        model.addAttribute("vcatnManageList", vcatnManageVO.getVcatnManageList());

        int totCnt = egovVcatnManageService.selectVcatnManageConfmListTotCnt(vcatnManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("yearList", yearList);
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/uss/ion/vct/EgovVcatnConfmList";
    }

    /**
     * 휴가승인관리 상세정보를 조회한다.
     * @param vcatnManageVO - 휴가관리 VO
     * @return String - 리턴 Url
     */
    @RequestMapping(value = "/uss/ion/vct/EgovVcatnConfm.do")
    public String selectVcatnConfm(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO,
        @ModelAttribute("vcatnManage") VcatnManage vcatnManage,
        ModelMap model) throws Exception {
        vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
        vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));

        // 등록 상세정보
        VcatnManageVO vcatnManageVOTemp = egovVcatnManageService.selectVcatnManage(vcatnManageVO);

        model.addAttribute("vcatnManageVO", vcatnManageVOTemp);
        model.addAttribute("vcatnManage", vcatnManageVOTemp);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/uss/ion/vct/EgovVcatnConfm";
    }

    /**
     * 신청된 휴가를 승인처리한다.
     * @param vcatnManage - 휴가관리 model
     * @return String - 리턴 Url
     */
    @RequestMapping(value = "/uss/ion/vct/updtVcatnConfm.do")
    public String updtVcatnManageConfm(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO,
        @ModelAttribute("vcatnManage") VcatnManage vcatnManage,
        BindingResult bindingResult,
        SessionStatus status,
        ModelMap model) throws Exception {

        vcatnManage.setBgnde(EgovStringUtil.removeMinusChar(vcatnManage.getBgnde()));
        vcatnManage.setEndde(EgovStringUtil.removeMinusChar(vcatnManage.getEndde()));
        beanValidator.validate(vcatnManage, bindingResult); //validation 수행

        LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        // KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if (!isAuthenticated) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("vcatnManageVO", vcatnManageVO);
            return "egovframework/com/uss/ion/vct/EgovVcatnConfm";
        } else {
            vcatnManage.setSanctnerId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
            vcatnManage.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

            egovVcatnManageService.updtVcatnManageConfm(vcatnManage);
            return "forward:/uss/ion/vct/EgovVcatnConfmList.do";
        }
    }

    /**
     *  휴가정보 반려처리 화면을 호출한다.
     * @param vcatnManage - 휴가관리 model
     * @return  String
     *
     * @param vcatnManage
     */
    @RequestMapping("/uss/ion/vct/EgovVcatnReturn.do")
    public String selectSanctnerListPopup(@ModelAttribute("vcatnManage") VcatnManage vcatnManage,
        ModelMap model) throws Exception {
        return "egovframework/com/uss/ion/vct/EgovVcatnReturn";
    }
}
