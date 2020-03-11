package egovframework.com.uss.sam.cpy.web;

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
import egovframework.com.uss.sam.cpy.service.CpyrhtPrtcPolicyDefaultVO;
import egovframework.com.uss.sam.cpy.service.CpyrhtPrtcPolicyVO;
import egovframework.com.uss.sam.cpy.service.EgovCpyrhtPrtcPolicyService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



/**
 *
 * 저작권보호정책내용을 처리하는 컨트롤러 클래스
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
 *
 * </pre>
 */

@Controller
public class EgovCpyrhtPrtcPolicyController {

    @Resource(name = "CpyrhtPrtcPolicyService")
    private EgovCpyrhtPrtcPolicyService cpyrhtPrtcPolicyService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 개별 배포시 메인메뉴를 조회한다.
     * @param model
     * @return	"/uss/sam/cpy/"
     * @throws Exception
     */
    @RequestMapping(value="/uss/sam/cpy/EgovMain.do")
    public String egovMain(ModelMap model) throws Exception {
    	return "egovframework/com/uss/sam/cpy/EgovMain";
    }

    /**
     * 메뉴를 조회한다.
     * @param model
     * @return	"/uss/sam/cpy/EgovLeft"
     * @throws Exception
     */
    @RequestMapping(value="/uss/sam/cpy/EgovLeft.do")
    public String egovLeft(ModelMap model) throws Exception {
    	return "egovframework/com/uss/sam/cpy/EgovLeft";
    }

    /**
     * 저작권보호정책 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return	"/uss/sam/cpy/EgovCpyrhtPrtcPolicyListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="저작권보호정책", order = 500 ,gid = 50)
    @RequestMapping(value="/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do")
    public String selectCpyrhtPrtcPolicyList(@ModelAttribute("searchVO") CpyrhtPrtcPolicyDefaultVO searchVO, ModelMap model) throws Exception {

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

        List<?> CpyrhtPrtcPolicyList = cpyrhtPrtcPolicyService.selectCpyrhtPrtcPolicyList(searchVO);
        model.addAttribute("resultList", CpyrhtPrtcPolicyList);

        int totCnt = cpyrhtPrtcPolicyService.selectCpyrhtPrtcPolicyListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/sam/cpy/EgovCpyrhtPrtcPolicyListInqire";
    }

    /**
     * 저작권보호정책 목록에 대한 상세정보를 조회한다.
     * @param cpyrhtPrtcPolicyVO
     * @param searchVO
     * @param model
     * @return	"/uss/sam/cpy/EgovCpyrhtPrtcPolicyDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/cpy/CpyrhtPrtcPolicyDetailInqire.do")
    public String	selectCpyrhtPrtcPolicyDetail(CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO,
            @ModelAttribute("searchVO") CpyrhtPrtcPolicyDefaultVO searchVO,
            ModelMap model) throws Exception {

		CpyrhtPrtcPolicyVO vo = cpyrhtPrtcPolicyService.selectCpyrhtPrtcPolicyDetail(cpyrhtPrtcPolicyVO);

		model.addAttribute("result", vo);

        return	"egovframework/com/uss/sam/cpy/EgovCpyrhtPrtcPolicyDetailInqire";
    }

    /**
     * 저작권보호정책를 등록하기 위한 전 처리
     * @param searchVO
     * @param model
     * @return	"/uss/sam/cpy/EgovCpyrhtPrtcPolicyCnRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/cpy/CpyrhtPrtcPolicyCnRegistView.do")
    public String insertCpyrhtPrtcPolicyCnView(
            @ModelAttribute("searchVO") CpyrhtPrtcPolicyDefaultVO searchVO, Model model)
            throws Exception {

        model.addAttribute("cpyrhtPrtcPolicyVO", new CpyrhtPrtcPolicyVO());

        return "egovframework/com/uss/sam/cpy/EgovCpyrhtPrtcPolicyCnRegist";

    }

    /**
     * 저작권보호정책를 등록한다.
     * @param searchVO
     * @param cpyrhtPrtcPolicyVO
     * @param bindingResult
     * @return	"forward:/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/cpy/CpyrhtPrtcPolicyCnRegist.do")
    public String insertCpyrhtPrtcPolicyCn(
            @ModelAttribute("searchVO") CpyrhtPrtcPolicyDefaultVO searchVO,
    		@ModelAttribute("cpyrhtPrtcPolicyVO") CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO,
            BindingResult bindingResult)
            throws Exception {

    	beanValidator.validate(cpyrhtPrtcPolicyVO, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/uss/sam/cpy/EgovCpyrhtPrtcPolicyCnRegist";

		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String	frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	cpyrhtPrtcPolicyVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	cpyrhtPrtcPolicyVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID

        cpyrhtPrtcPolicyService.insertCpyrhtPrtcPolicyCn(cpyrhtPrtcPolicyVO);

        return "forward:/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do";
    }

    /**
     * 저작권보호정책를 수정하기 위한 전 처리
     * @param cpyrhtId
     * @param searchVO
     * @param model
     * @return	"/uss/sam/cpy/EgovCpyrhtPrtcPolicyCnUpdt"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/cpy/CpyrhtPrtcPolicyCnUpdtView.do")
    public String updateCpyrhtPrtcPolicyCnView(@RequestParam("cpyrhtId") String cpyrhtId ,
            @ModelAttribute("searchVO") CpyrhtPrtcPolicyDefaultVO searchVO, ModelMap model)
            throws Exception {


        CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO = new CpyrhtPrtcPolicyVO();

        // Primary Key 값 세팅
        cpyrhtPrtcPolicyVO.setCpyrhtId(cpyrhtId);

        // 변수명은 CoC 에 따라
        model.addAttribute(selectCpyrhtPrtcPolicyDetail(cpyrhtPrtcPolicyVO, searchVO, model));

        // 변수명은 CoC 에 따라 JSTL사용을 위해
        model.addAttribute("cpyrhtPrtcPolicyVO", cpyrhtPrtcPolicyService.selectCpyrhtPrtcPolicyDetail(cpyrhtPrtcPolicyVO));

        return "egovframework/com/uss/sam/cpy/EgovCpyrhtPrtcPolicyCnUpdt";
    }

   /**
    * 저작권보호정책를 수정처리한다.
    * @param searchVO
    * @param cpyrhtPrtcPolicyVO
    * @param bindingResult
    * @return	"forward:/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do"
    * @throws Exception
    */
    @RequestMapping("/uss/sam/cpy/CpyrhtPrtcPolicyCnUpdt.do")
    public String updateCpyrhtPrtcPolicyCn(
            @ModelAttribute("searchVO") CpyrhtPrtcPolicyDefaultVO searchVO,
    		@ModelAttribute("cpyrhtPrtcPolicyVO") CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO,
            BindingResult bindingResult)
            throws Exception {

    	// Validation
    	beanValidator.validate(cpyrhtPrtcPolicyVO, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/uss/olh/wor/EgovCpyrhtPrtcPolicyCnUpdt";

		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String	lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	cpyrhtPrtcPolicyVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID

    	cpyrhtPrtcPolicyService.updateCpyrhtPrtcPolicyCn(cpyrhtPrtcPolicyVO);

        return "forward:/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do";

    }

    /**
     * 저작권보호정책를 삭제처리한다.
     * @param cpyrhtPrtcPolicyVO
     * @param searchVO
     * @return	"forward:/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/sam/cpy/CpyrhtPrtcPolicyCnDelete.do")
    public String deleteCpyrhtPrtcPolicyCn(
            CpyrhtPrtcPolicyVO cpyrhtPrtcPolicyVO,
            @ModelAttribute("searchVO") CpyrhtPrtcPolicyDefaultVO searchVO)
            throws Exception {

    	cpyrhtPrtcPolicyService.deleteCpyrhtPrtcPolicyCn(cpyrhtPrtcPolicyVO);

        return "forward:/uss/sam/cpy/CpyrhtPrtcPolicyListInqire.do";
    }

}
