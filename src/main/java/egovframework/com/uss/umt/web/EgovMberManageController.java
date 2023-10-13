package egovframework.com.uss.umt.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.EgovMberManageService;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.com.uss.umt.service.StplatVO;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;

/**
 * 일반회원관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자       수정내용
 *  -----------    --------    ---------------------------
 *   2009.04.10     조재영       최초 생성
 *   2011.08.26     정진오       IncludedInfo annotation 추가
 *   2014.12.08     이기하       암호화방식 변경(EgovFileScrty.encryptPassword)
 *   2015.06.16     조정국       수정시 유효성체크 후 에러발생 시 목록으로 이동하여 에러메시지 표시
 *   2015.06.19     조정국       미인증 사용자에 대한 보안처리 기준 수정 (!isAuthenticated)
 *   2017.07.21     장동한       로그인인증제한 작업
 *   2021.05.30     정진오       디지털원패스 정보 조회
 *   2022.07.13     김해준       디지털원패스 정보 조회 null 판별 수정
 * </pre>
 */

@Controller
public class EgovMberManageController {

    /** mberManageService */
    @Resource(name = "mberManageService")
    private EgovMberManageService mberManageService;

    /** cmmUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** DefaultBeanValidator beanValidator */
    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 일반회원목록을 조회한다. (pageing)
     *
     * @param userSearchVO 검색조건정보
     * @param model        화면모델
     * @return uss/umt/EgovMberManage
     * @throws Exception
     */
    @IncludedInfo(name = "일반회원관리", order = 470, gid = 50)
    @RequestMapping(value = "/uss/umt/EgovMberManage.do")
    public String selectMberList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, ModelMap model) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "index";
        }

        /** EgovPropertyService */
        userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
        paginationInfo.setPageSize(userSearchVO.getPageSize());

        userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<MberManageVO> resultList = mberManageService.selectMberList(userSearchVO);
        model.addAttribute("resultList", resultList);

        int totCnt = mberManageService.selectMberListTotCnt(userSearchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        // 일반회원 상태코드를 코드정보로부터 조회
        ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
        comDefaultCodeVO.setCodeId("COM013");
        List<CmmnDetailCode> mberSttus_result = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
        model.addAttribute("entrprsMberSttus_result", mberSttus_result);// 기업회원상태코드목록

        return "egovframework/com/uss/umt/EgovMberManage";
    }

    /**
     * 일반회원등록화면으로 이동한다.
     *
     * @param userSearchVO 검색조건정보
     * @param mberManageVO 일반회원초기화정보
     * @param model        화면모델
     * @return uss/umt/EgovMberInsert
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovMberInsertView.do")
    public String insertMberView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, @ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "index";
        }

        ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();

        // 패스워드힌트목록을 코드정보로부터 조회
        comDefaultCodeVO.setCodeId("COM022");
        List<CmmnDetailCode> passwordHint_result = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
        // 성별구분코드를 코드정보로부터 조회
        comDefaultCodeVO.setCodeId("COM014");
        List<CmmnDetailCode> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
        // 사용자상태코드를 코드정보로부터 조회
        comDefaultCodeVO.setCodeId("COM013");
        List<CmmnDetailCode> mberSttus_result = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
        // 그룹정보를 조회 - GROUP_ID정보
        comDefaultCodeVO.setTableNm("COMTNORGNZTINFO");
        List<CmmnDetailCode> groupId_result = cmmUseService.selectGroupIdDetail(comDefaultCodeVO);

        model.addAttribute("passwordHint_result", passwordHint_result); // 패스워트힌트목록
        model.addAttribute("sexdstnCode_result", sexdstnCode_result); // 성별구분코드목록
        model.addAttribute("mberSttus_result", mberSttus_result); // 사용자상태코드목록
        model.addAttribute("groupId_result", groupId_result); // 그룹정보 목록

        return "egovframework/com/uss/umt/EgovMberInsert";
    }

    /**
     * 일반회원등록처리후 목록화면으로 이동한다.
     *
     * @param mberManageVO  일반회원등록정보
     * @param bindingResult 입력값검증용 bindingResult
     * @param model         화면모델
     * @return forward:/uss/umt/EgovMberManage.do
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovMberInsert.do")
    public String insertMber(@ModelAttribute("mberManageVO") MberManageVO mberManageVO, BindingResult bindingResult, Model model) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "index";
        }

        beanValidator.validate(mberManageVO, bindingResult);
        if (bindingResult.hasErrors()) {

            ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();

            // 패스워드힌트목록을 코드정보로부터 조회
            comDefaultCodeVO.setCodeId("COM022");
            List<CmmnDetailCode> passwordHint_result = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
            // 성별구분코드를 코드정보로부터 조회
            comDefaultCodeVO.setCodeId("COM014");
            List<CmmnDetailCode> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
            // 사용자상태코드를 코드정보로부터 조회
            comDefaultCodeVO.setCodeId("COM013");
            List<CmmnDetailCode> mberSttus_result = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
            // 그룹정보를 조회 - GROUP_ID정보
            comDefaultCodeVO.setTableNm("COMTNORGNZTINFO");
            List<CmmnDetailCode> groupId_result = cmmUseService.selectGroupIdDetail(comDefaultCodeVO);

            model.addAttribute("passwordHint_result", passwordHint_result); // 패스워트힌트목록
            model.addAttribute("sexdstnCode_result", sexdstnCode_result); // 성별구분코드목록
            model.addAttribute("mberSttus_result", mberSttus_result); // 사용자상태코드목록
            model.addAttribute("groupId_result", groupId_result); // 그룹정보 목록

            return "egovframework/com/uss/umt/EgovMberInsert";
        } else {
            if ("".equals(mberManageVO.getGroupId())) {// KISA 보안약점 조치 (2018-10-29, 윤창원)
                mberManageVO.setGroupId(null);
            }
            mberManageService.insertMber(mberManageVO);
            // Exception 없이 진행시 등록 성공메시지
            model.addAttribute("resultMsg", "success.common.insert");
        }
        return "forward:/uss/umt/EgovMberManage.do";
    }

    /**
     * 일반회원정보 수정을 위해 일반회원정보를 상세조회한다.
     *
     * @param mberId       상세조회대상 일반회원아이디
     * @param userSearchVO 검색조건
     * @param model        화면모델
     * @return uss/umt/EgovMberSelectUpdt
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovMberSelectUpdtView.do")
    public String updateMberView(@RequestParam("selectedId") String mberId, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, HttpServletRequest request, Model model) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "index";
        }

        ComDefaultCodeVO vo = new ComDefaultCodeVO();

        // 패스워드힌트목록을 코드정보로부터 조회
        vo.setCodeId("COM022");
        List<CmmnDetailCode> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);

        // 성별구분코드를 코드정보로부터 조회
        vo.setCodeId("COM014");
        List<CmmnDetailCode> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);

        // 사용자상태코드를 코드정보로부터 조회
        vo.setCodeId("COM013");
        List<CmmnDetailCode> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);

        // 그룹정보를 조회 - GROUP_ID정보
        vo.setTableNm("COMTNORGNZTINFO");
        List<CmmnDetailCode> groupId_result = cmmUseService.selectGroupIdDetail(vo);

        model.addAttribute("passwordHint_result", passwordHint_result); // 패스워트힌트목록
        model.addAttribute("sexdstnCode_result", sexdstnCode_result); // 성별구분코드목록
        model.addAttribute("mberSttus_result", mberSttus_result); // 사용자상태코드목록
        model.addAttribute("groupId_result", groupId_result); // 그룹정보 목록

        MberManageVO mberManageVO = mberManageService.selectMber(mberId);
        model.addAttribute("mberManageVO", mberManageVO);
        model.addAttribute("userSearchVO", userSearchVO);

        // 2021.05.30, 정진오, 디지털원패스 정보 조회
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
        String onepassUserId = loginVO.getUniqId();
        String onepassUserkey = loginVO.getOnepassUserkey();
        String onepassIntfToken = loginVO.getOnepassIntfToken();
        if (mberId.equals(onepassUserId)) {
            model.addAttribute("onepassUserkey", onepassUserkey); // 디지털원패스 사용자키
            model.addAttribute("onepassIntfToken", onepassIntfToken); // 디지털원패스 사용자세션값
        } else {
            model.addAttribute("onepassUserkey", "");
            model.addAttribute("onepassIntfToken", "");
        }

        return "egovframework/com/uss/umt/EgovMberSelectUpdt";
    }

    /**
     * 로그인인증제한 해제
     *
     * @param mberManageVO 일반회원등록정보
     * @param model        화면모델
     * @return uss/umt/EgovMberSelectUpdtView.do
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovMberLockIncorrect.do")
    public String updateLockIncorrect(MberManageVO mberManageVO, Model model) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "index";
        }

        mberManageService.updateLockIncorrect(mberManageVO);

        return "forward:/uss/umt/EgovMberSelectUpdtView.do";
    }

    /**
     * 일반회원정보 수정후 목록조회 화면으로 이동한다.
     *
     * @param mberManageVO  일반회원수정정보
     * @param bindingResult 입력값검증용 bindingResult
     * @param model         화면모델
     * @return forward:/uss/umt/EgovMberManage.do
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovMberSelectUpdt.do")
    public String updateMber(@ModelAttribute("mberManageVO") MberManageVO mberManageVO, BindingResult bindingResult, Model model) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "index";
        }

        beanValidator.validate(mberManageVO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "forward:/uss/umt/EgovMberManage.do";
        } else {
            if ("".equals(mberManageVO.getGroupId())) {// KISA 보안약점 조치 (2018-10-29, 윤창원)
                mberManageVO.setGroupId(null);
            }
            mberManageService.updateMber(mberManageVO);
            // Exception 없이 진행시 수정성공메시지
            model.addAttribute("resultMsg", "success.common.update");
            return "forward:/uss/umt/EgovMberManage.do";
        }
    }

    /**
     * 일반회원정보삭제후 목록조회 화면으로 이동한다.
     *
     * @param checkedIdForDel 삭제대상 아이디 정보
     * @param userSearchVO    검색조건정보
     * @param model           화면모델
     * @return forward:/uss/umt/EgovMberManage.do
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovMberDelete.do")
    public String deleteMber(@RequestParam("checkedIdForDel") String checkedIdForDel, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, HttpServletRequest request, Model model) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "index";
        }

        // 2021.05.30, 정진오, 디지털원패스 정보 조회
        // 2022.07.13, 김해준, null 판별 수정
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
        String onepassUserkey = loginVO.getOnepassUserkey();
        String onepassIntfToken = loginVO.getOnepassIntfToken();
        if (StringUtils.isNotEmpty(onepassUserkey) || StringUtils.isNotEmpty(onepassIntfToken)) {
            model.addAttribute("resultMsg", "digital.onepass.delete.alert");
        } else {
            mberManageService.deleteMber(checkedIdForDel);
            model.addAttribute("resultMsg", "success.common.delete");
        }

        return "forward:/uss/umt/EgovMberManage.do";
    }

    // 탈퇴 처리 기능에 대한 예시
    // 221114 김혜준 2022 시큐어코딩 조치
    @RequestMapping("/uss/umt/EgovMberWithdraw.do")
    public String withdrawMber(Model model) throws Exception {

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "fail.common.delete");

            return "redirect:/";
        }

        mberManageService.deleteMber(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
        // Exception 없이 진행시 삭제성공메시지
        model.addAttribute("resultMsg", "success.common.delete");

        return "redirect:/";
    }

    /**
     * 일반회원가입신청 등록화면으로 이동한다.
     *
     * @param userSearchVO 검색조건
     * @param mberManageVO 일반회원가입신청정보
     * @param commandMap   파라메터전달용 commandMap
     * @param model        화면모델
     * @return uss/umt/EgovMberSbscrb
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovMberSbscrbView.do")
    public String sbscrbMberView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, @ModelAttribute("mberManageVO") MberManageVO mberManageVO, @RequestParam Map<String, Object> commandMap, Model model) throws Exception {

        ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();

        // 패스워드힌트목록을 코드정보로부터 조회
        comDefaultCodeVO.setCodeId("COM022");
        List<CmmnDetailCode> passwordHint_result = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
        // 성별구분코드를 코드정보로부터 조회
        comDefaultCodeVO.setCodeId("COM014");
        List<CmmnDetailCode> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);

        model.addAttribute("passwordHint_result", passwordHint_result); // 패스워트힌트목록
        model.addAttribute("sexdstnCode_result", sexdstnCode_result); // 성별구분코드목록
        if (!"".equals(commandMap.get("realname"))) {
            model.addAttribute("mberNm", commandMap.get("realname")); // 실명인증된 이름 - 주민번호 인증
            model.addAttribute("ihidnum", commandMap.get("ihidnum")); // 실명인증된 주민등록번호 - 주민번호 인증
        }
        if (!"".equals(commandMap.get("realName"))) {
            model.addAttribute("mberNm", commandMap.get("realName")); // 실명인증된 이름 - ipin인증
        }

        mberManageVO.setMberSttus("DEFAULT");

        return "egovframework/com/uss/umt/EgovMberSbscrb";
    }

    /**
     * 일반회원가입신청등록처리후로그인화면으로 이동한다.
     *
     * @param mberManageVO 일반회원가입신청정보
     * @return forward:/uat/uia/egovLoginUsr.do
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovMberSbscrb.do")
    public String sbscrbMber(@ModelAttribute("mberManageVO") MberManageVO mberManageVO) throws Exception {

        // 가입상태 초기화
        mberManageVO.setMberSttus("A");
        // 그룹정보 초기화
        // mberManageVO.setGroupId("1");
        // 일반회원가입신청 등록시 일반회원등록기능을 사용하여 등록한다.
        mberManageService.insertMber(mberManageVO);
        return "forward:/uat/uia/egovLoginUsr.do";
    }

    /**
     * 일반회원 약관확인
     *
     * @param model 화면모델
     * @return uss/umt/EgovStplatCnfirm
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovStplatCnfirmMber.do")
    public String sbscrbEntrprsMber(Model model) throws Exception {

        // 미인증 사용자에 대한 보안처리
        // Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        // if (!isAuthenticated) {
        // return "index";
        // }

        // 일반회원용 약관 아이디 설정
        String stplatId = "STPLAT_0000000000001";
        // 회원가입유형 설정-일반회원
        String sbscrbTy = "USR01";
        // 약관정보 조회
        List<StplatVO> stplatList = mberManageService.selectStplat(stplatId);
        model.addAttribute("stplatList", stplatList); // 약관정보 포함
        model.addAttribute("sbscrbTy", sbscrbTy); // 회원가입유형 포함

        return "egovframework/com/uss/umt/EgovStplatCnfirm";
    }

    /**
     * @param model        화면모델
     * @param commandMap   파라메터전달용 commandMap
     * @param userSearchVO 검색조건
     * @param mberManageVO 일반회원수정정보(비밀번호)
     * @return uss/umt/EgovMberPasswordUpdt
     * @throws Exception
     */
    @RequestMapping(value = "/uss/umt/EgovMberPasswordUpdt.do")
    public String updatePassword(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, @ModelAttribute("mberManageVO") MberManageVO mberManageVO) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "index";
        }

        String oldPassword = (String) commandMap.get("oldPassword");
        String newPassword = (String) commandMap.get("newPassword");
        String newPassword2 = (String) commandMap.get("newPassword2");
        String uniqId = (String) commandMap.get("uniqId");

        boolean isCorrectPassword = false;
        MberManageVO resultVO = new MberManageVO();
        mberManageVO.setPassword(newPassword);
        mberManageVO.setOldPassword(oldPassword);
        mberManageVO.setUniqId(uniqId);

        String resultMsg = "";
        resultVO = mberManageService.selectPassword(mberManageVO);
        // 패스워드 암호화
        String encryptPass = EgovFileScrty.encryptPassword(oldPassword, mberManageVO.getMberId());
        if (encryptPass.equals(resultVO.getPassword())) {
            if (newPassword.equals(newPassword2)) {
                isCorrectPassword = true;
            } else {
                isCorrectPassword = false;
                resultMsg = "fail.user.passwordUpdate2";
            }
        } else {
            isCorrectPassword = false;
            resultMsg = "fail.user.passwordUpdate1";
        }

        if (isCorrectPassword) {
            mberManageVO.setPassword(EgovFileScrty.encryptPassword(newPassword, mberManageVO.getMberId()));
            mberManageService.updatePassword(mberManageVO);
            model.addAttribute("mberManageVO", mberManageVO);
            resultMsg = "success.common.update";
        } else {
            model.addAttribute("mberManageVO", mberManageVO);
        }
        model.addAttribute("userSearchVO", userSearchVO);
        model.addAttribute("resultMsg", resultMsg);

        return "egovframework/com/uss/umt/EgovMberPasswordUpdt";
    }

    /**
     * 일반회원 암호 수정 화면 이동
     *
     * @param model        화면모델
     * @param commandMap   파라메터전달용 commandMap
     * @param userSearchVO 검색조건
     * @param mberManageVO 일반회원수정정보(비밀번호)
     * @return uss/umt/EgovMberPasswordUpdt
     * @throws Exception
     */
    @RequestMapping(value = "/uss/umt/EgovMberPasswordUpdtView.do")
    public String updatePasswordView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, @ModelAttribute("mberManageVO") MberManageVO mberManageVO) throws Exception {

        // 미인증 사용자에 대한 보안처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "index";
        }

        String userTyForPassword = (String) commandMap.get("userTyForPassword");
        mberManageVO.setUserTy(userTyForPassword);

        model.addAttribute("userSearchVO", userSearchVO);
        model.addAttribute("mberManageVO", mberManageVO);

        return "egovframework/com/uss/umt/EgovMberPasswordUpdt";
    }
}