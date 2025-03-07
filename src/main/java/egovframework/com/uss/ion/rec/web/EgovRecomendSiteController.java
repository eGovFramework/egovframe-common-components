package egovframework.com.uss.ion.rec.web;

import java.util.List;

import javax.annotation.Resource;

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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rec.service.EgovRecomendSiteService;
import egovframework.com.uss.ion.rec.service.RecomendSiteVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
/**
*
* 추천사이트처리를 하는 Controller 클래스
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
*   2011.8.26	 정진오			IncludedInfo annotation 추가
*   2016.08.22	 김연호			표준프레임워크 3.6 개선
*
* </pre>
*/

@Controller
public class EgovRecomendSiteController {
	
	@Resource(name = "EgovRecomendSiteService")
    private EgovRecomendSiteService egovRecomendSiteService;

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
     * 추천사이트정보 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	"/uss/ion/rec/EgovRecomendSiteList"
     * @throws Exception
     */
    @IncludedInfo(name="추천사이트관리", order = 700 ,gid = 50)
    @RequestMapping(value="/uss/ion/rec/selectRecomendSiteList.do")
    public String selectRecomendSiteList(@ModelAttribute("searchVO") RecomendSiteVO searchVO, ModelMap model) throws Exception {

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

        List<RecomendSiteVO> RecomendSiteList = egovRecomendSiteService.selectRecomendSiteList(searchVO);
        model.addAttribute("resultList", RecomendSiteList);

        int totCnt = egovRecomendSiteService.selectRecomendSiteListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/ion/rec/EgovRecomendSiteList";
    }
    
    /**
     * 추천사이트정보 목록에 대한 상세정보를 조회한다.
     * @param recomendSiteVO
     * @param searchVO
     * @param model
     * @return	"/uss/ion/rec/EgovRecomendSiteDetail"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/rec/selectRecomendSiteDetail.do")
    public String	selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO,
            @ModelAttribute("searchVO") RecomendSiteVO searchVO,
            ModelMap model) throws Exception {

		RecomendSiteVO vo = egovRecomendSiteService.selectRecomendSiteDetail(recomendSiteVO);

		model.addAttribute("result", vo);

        return	"egovframework/com/uss/ion/rec/EgovRecomendSiteDetail";
    }
    
    /**
     * 추천사이트정보를 등록하기 전 처리
     * @param searchVO
     * @param model
     * @return	"/uss/ion/rec/EgovRecomendSiteRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/rec/insertRecomendSiteView.do")
    public String insertRecomendSiteView(@ModelAttribute("searchVO") RecomendSiteVO searchVO, Model model) throws Exception {

        model.addAttribute("recomendSiteVO", new RecomendSiteVO());

        return "egovframework/com/uss/ion/rec/EgovRecomendSiteRegist";

    }
    
    /**
     * 추천사이트정보를 등록한다.
     * @param searchVO
     * @param recomendSiteVO
     * @param bindingResult
     * @return	"forward:/uss/ion/rec/selectRecomendSiteList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/rec/insertRecomendSite.do")
    public String insertRecomendSite(
            @ModelAttribute("searchVO") RecomendSiteVO searchVO,
            @ModelAttribute("recomendSiteVO") RecomendSiteVO recomendSiteVO,
            BindingResult bindingResult)
            throws Exception {

    	beanValidator.validate(recomendSiteVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "egovframework/com/uss/olh/rec/EgovRecomendSiteRegist";
		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String	frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	recomendSiteVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	recomendSiteVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID

        egovRecomendSiteService.insertRecomendSite(recomendSiteVO);


        return "forward:/uss/ion/rec/selectRecomendSiteList.do";
    }
    
    /**
     * 추천사이트정보를 수정하기 전 처리
     * @param recomendSiteId
     * @param searchVO
     * @param model
     * @return	"/uss/ion/rec/EgovRecomendSiteUpdt"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/rec/updateRecomendSiteView.do")
    public String updateRecomendSiteView(@RequestParam("recomendSiteId") String recomendSiteId ,
            @ModelAttribute("searchVO") RecomendSiteVO searchVO, ModelMap model)
            throws Exception {


        RecomendSiteVO recomendSiteVO = new RecomendSiteVO();

        // Primary Key 값 세팅
        recomendSiteVO.setRecomendSiteId(recomendSiteId);
        model.addAttribute("recomendSiteVO", egovRecomendSiteService.selectRecomendSiteDetail(recomendSiteVO));


        return "egovframework/com/uss/ion/rec/EgovRecomendSiteUpdt";
    }
    
    /**
     * 추천사이트정보를 수정처리한다.
     * @param searchVO
     * @param recomendSiteManageVO
     * @param bindingResult
     * @return	"forward:/uss/ion/rec/selectRecomendSiteList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/rec/updateRecomendSite.do")
    public String updateRecomendSite(@ModelAttribute("searchVO") RecomendSiteVO searchVO, @ModelAttribute("recomendSiteVO") RecomendSiteVO recomendSiteVO,
            BindingResult bindingResult)
            throws Exception {

    	// Validation
    	beanValidator.validate(recomendSiteVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "egovframework/com/uss/olh/rec/EgovRecomendSiteUpdt";
		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	String	lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
    	recomendSiteVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID

    	egovRecomendSiteService.updateRecomendSite(recomendSiteVO);

        return "forward:/uss/ion/rec/selectRecomendSiteList.do";

    }

    /**
     * 추천사이트정보를 삭제처리한다.
     * @param recomendSiteVO
     * @param searchVO
     * @return	"forward:/uss/ion/rec/selectRecomendSiteList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/rec/deleteRecomendSite.do")
    public String deleteRecomendSite(RecomendSiteVO recomendSiteVO, @ModelAttribute("searchVO") RecomendSiteVO searchVO) throws Exception {

    	egovRecomendSiteService.deleteRecomendSite(recomendSiteVO);

        return "forward:/uss/ion/rec/selectRecomendSiteList.do";
    }
    
}
