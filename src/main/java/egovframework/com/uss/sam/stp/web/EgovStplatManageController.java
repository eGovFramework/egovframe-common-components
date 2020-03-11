package egovframework.com.uss.sam.stp.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import egovframework.com.uss.sam.stp.service.EgovStplatManageService;
import egovframework.com.uss.sam.stp.service.StplatManageDefaultVO;
import egovframework.com.uss.sam.stp.service.StplatManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 * 약관내용을 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *   2016.06.13   장동한         표준프레임워크 v3.6 개선
 *
 * </pre>
 */
@Controller
public class EgovStplatManageController {

    @Resource(name = "StplatManageService")
    private EgovStplatManageService stplatManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/** beanValidator Member Variable */
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 개별 배포시 메인메뉴를 조회한다.
     * @param model
     * @return	"/uss/sam/stp/EgovMain"
     * @throws Exception
     */
    @RequestMapping(value="/uss/sam/stp/EgovMain.do")
    public String egovMain(ModelMap model) throws Exception {
    	return "egovframework/com/uss/sam/stp/EgovMain";
    }

    /**
     * 메뉴를 조회한다.
     * @param model
     * @return	"/uss/sam/stp/EgovLeft"
     * @throws Exception
     */
    @RequestMapping(value="/uss/sam/stp/EgovLeft.do")
    public String egovLeft(ModelMap model) throws Exception {
    	return "egovframework/com/uss/sam/stp/EgovLeft";
    }

    /**
     * 약관정보 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	"/uss/sam/stp/EgovStplatListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="약관관리", order = 490 ,gid = 50)
    @RequestMapping(value="/uss/sam/stp/StplatListInqire.do")
    public String selectStplatList(@ModelAttribute("searchVO") StplatManageDefaultVO searchVO, ModelMap model) throws Exception {

    	/** EgovPropertyService.SiteList */
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

        List<?> StplatList = stplatManageService.selectStplatList(searchVO);
        model.addAttribute("resultList", StplatList);

        int totCnt = stplatManageService.selectStplatListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/sam/stp/EgovStplatListInqire";
    }

    /**
     * 약관정보상세내용을 조회한다.
     * @param stplatManageVO
     * @param searchVO
     * @param model
     * @return	"/uss/sam/stp/EgovStplatDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatDetailInqire.do")
    public String	selectStplatDetail(StplatManageVO stplatManageVO,
            @ModelAttribute("searchVO") StplatManageDefaultVO searchVO,
            ModelMap model) throws Exception {

		StplatManageVO vo = stplatManageService.selectStplatDetail(stplatManageVO);

		model.addAttribute("result", vo);

        return	"egovframework/com/uss/sam/stp/EgovStplatDetailInqire";
    }

    /**
     * 약관정보를 등록하기 위한 전 처리
     * @param searchVO
     * @param model
     * @return	"/uss/sam/stp/EgovStplatCnRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnRegistView.do")
    public String insertStplatCnView(
            @ModelAttribute("searchVO") StplatManageDefaultVO searchVO, Model model)
            throws Exception {

        model.addAttribute("stplatManageVO", new StplatManageVO());

        return "egovframework/com/uss/sam/stp/EgovStplatCnRegist";

    }

    /**
     * 약관정보를 등록한다.
     * @param searchVO
     * @param stplatManageVO
     * @param bindingResult
     * @return	"forward:/uss/sam/stp/StplatListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnRegist.do")
    public String insertStplatCn(
            @ModelAttribute("searchVO") StplatManageDefaultVO searchVO,
    		@ModelAttribute("stplatManageVO") StplatManageVO stplatManageVO,
    		BindingResult bindingResult,
    		ModelMap model
    		)
            throws Exception {

    	beanValidator.validate(stplatManageVO, bindingResult);

    	if(bindingResult.hasErrors()){

			return "egovframework/com/uss/sam/stp/EgovStplatCnRegist";

		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	//KISA 보안취약점 조치 (2018-12-10, 이정은)

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

    	String	frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	stplatManageVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	stplatManageVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID

        stplatManageService.insertStplatCn(stplatManageVO);

        return "forward:/uss/sam/stp/StplatListInqire.do";
    }

    /**
     * 약관정보를 수정하기 위한 전 처리
     * @param useStplatId
     * @param searchVO
     * @param model
     * @return	"/uss/sam/stp/EgovStplatCnUpdt"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnUpdtView.do")
    public String updateStplatCnView(@RequestParam("useStplatId") String useStplatId ,
            @ModelAttribute("searchVO") StplatManageDefaultVO searchVO, ModelMap model)
            throws Exception {


        StplatManageVO stplatManageVO = new StplatManageVO();

        // Primary Key 값 세팅
        stplatManageVO.setUseStplatId(useStplatId);

        // 변수명은 CoC 에 따라
        model.addAttribute(selectStplatDetail(stplatManageVO, searchVO, model));

        // 변수명은 CoC 에 따라 JSTL사용을 위해
        model.addAttribute("stplatManageVO", stplatManageService.selectStplatDetail(stplatManageVO));

        return "egovframework/com/uss/sam/stp/EgovStplatCnUpdt";
    }

    /**
     * 약관정보를 수정 처리한다.
     * @param searchVO
     * @param stplatManageVO
     * @param bindingResult
     * @return	"forward:/uss/sam/stp/StplatListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnUpdt.do")
    public String updateStplatCn(
            @ModelAttribute("searchVO") StplatManageDefaultVO searchVO,
    		@ModelAttribute("stplatManageVO") StplatManageVO stplatManageVO,
            BindingResult bindingResult)
            throws Exception {

    	// Validation
    	beanValidator.validate(stplatManageVO, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/uss/sam/stp/EgovStplatCnUpdt";

		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	//KISA 보안취약점 조치 (2018-12-10, 이정은)

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        
    	String	lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	stplatManageVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID

    	stplatManageService.updateStplatCn(stplatManageVO);

        return "forward:/uss/sam/stp/StplatListInqire.do";
    }

    /**
     * 약관정보를 삭제 처리한다.
     * @param stplatManageVO
     * @param searchVO
     * @return	"forward:/uss/sam/stp/StplatListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/stp/StplatCnDelete.do")
    public String deleteStplatCn(
            StplatManageVO stplatManageVO,
            @ModelAttribute("searchVO") StplatManageDefaultVO searchVO)
            throws Exception {

    	stplatManageService.deleteStplatCn(stplatManageVO);

        return "forward:/uss/sam/stp/StplatListInqire.do";
    }

}
