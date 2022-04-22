package egovframework.com.uss.ion.sit.web;

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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.sit.service.EgovSiteService;
import egovframework.com.uss.ion.sit.service.SiteVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
*
* 사이트정보를 처리하는 Controller 클래스
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
public class EgovSiteController {
	
	@Resource(name = "EgovSiteService")
    private EgovSiteService egovSiteService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
     * 사이트목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	"/uss/ion/sit/EgovSiteList"
     * @throws Exception
     */
    @IncludedInfo(name="사이트관리", order = 680 ,gid = 50)
    @RequestMapping(value="/uss/ion/sit/selectSiteList.do")
    public String selectSiteList(@ModelAttribute("searchVO") SiteVO searchVO, ModelMap model) throws Exception {

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

        List<?> SiteList = egovSiteService.selectSiteList(searchVO);
        model.addAttribute("resultList", SiteList);

        int totCnt = egovSiteService.selectSiteListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/ion/sit/EgovSiteList";
    }
    
    /**
     * 사이트정보 목록에 대한 상세정보를 조회한다.
     * @param siteVO
     * @param searchVO
     * @param model
     * @return	"/uss/ion/sit/EgovSiteDetail"
     * @throws Exception
     */
     @RequestMapping("/uss/ion/sit/selectSiteDetail.do")
     public String	selectSiteDetail(SiteVO siteVO, @ModelAttribute("searchVO") SiteVO searchVO, ModelMap model) throws Exception {

 		SiteVO vo = egovSiteService.selectSiteDetail(siteVO);

 		model.addAttribute("result", vo);

         return	"egovframework/com/uss/ion/sit/EgovSiteDetail";
     }
     
     /**
      * 사이트정보 등록전 단계
      * @param searchVO
      * @param model
      * @return	"/uss/ion/sit/EgovSiteRegist"
      * @throws Exception
      */
     @RequestMapping("/uss/ion/sit/insertSiteView.do")
     public String insertSiteView(@ModelAttribute("searchVO") SiteVO searchVO, Model model) throws Exception {

     	// 공통코드를 가져오기 위한 Vo
     	ComDefaultCodeVO vo = new ComDefaultCodeVO();
 		vo.setCodeId("COM023");

 		List<?> _result = cmmUseService.selectCmmCodeDetail(vo);
 		model.addAttribute("siteThemaClCode", _result);


         model.addAttribute("siteVO", new SiteVO());

         return "egovframework/com/uss/ion/sit/EgovSiteRegist";

     }
     
     /**
      * 사이트정보를 등록한다.
      * @param searchVO
      * @param siteVO
      * @param bindingResult
      * @return	"forward:/uss/ion/sit/selectSiteList.do"
      * @throws Exception
      */
      @RequestMapping("/uss/ion/sit/insertSite.do")
      public String insertSite(
              @ModelAttribute("searchVO") SiteVO searchVO,
              @ModelAttribute("siteVO") SiteVO siteVO,
              BindingResult bindingResult)
              throws Exception {

      	beanValidator.validate(siteVO, bindingResult);
  		if(bindingResult.hasErrors()){
  			return "egovframework/com/uss/olh/sit/EgovSiteRegist";
  		}

      	// 로그인VO에서  사용자 정보 가져오기
      	LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

      	String	frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

      	siteVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
      	siteVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID

          egovSiteService.insertSite(siteVO);

          return "forward:/uss/ion/sit/selectSiteList.do";
      }
    
      /**
       * 사이트정보 수정 전 처리
       * @param siteId
       * @param searchVO
       * @param model
       * @return	"/uss/ion/sit/EgovSiteUpdt"
       * @throws Exception
       */
      @RequestMapping("/uss/ion/sit/updateSiteView.do")
      public String updateSiteView(@RequestParam("siteId") String siteId ,
              @ModelAttribute("searchVO") SiteVO searchVO, ModelMap model)
              throws Exception {

      	// 공통코드를 가져오기 위한 Vo
      	ComDefaultCodeVO vo = new ComDefaultCodeVO();
  		vo.setCodeId("COM023");

  		List<?> _result = cmmUseService.selectCmmCodeDetail(vo);
  		model.addAttribute("siteThemaClCode", _result);

          SiteVO siteVO = new SiteVO();

          // Primary Key 값 세팅
          siteVO.setSiteId(siteId);

          model.addAttribute("siteVO", egovSiteService.selectSiteDetail(siteVO));

          return "egovframework/com/uss/ion/sit/EgovSiteUpdt";
      }
      
      /**
       * 사이트정보를 수정한다.
       * @param searchVO
       * @param siteVO
       * @param bindingResult
       * @return	"forward:/uss/ion/sit/selectSiteList.do"
       * @throws Exception
       */
      @RequestMapping("/uss/ion/sit/updateSite.do")
      public String updateSite(@ModelAttribute("searchVO") SiteVO searchVO, @ModelAttribute("siteVO") SiteVO siteVO, BindingResult bindingResult)
              throws Exception {

      	// Validation
      	beanValidator.validate(siteVO, bindingResult);
  		if(bindingResult.hasErrors()){
  			return "egovframework/com/uss/ion/sit/EgovSiteUpdt";
  		}

      	// 로그인VO에서  사용자 정보 가져오기
      	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
      	String	lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
      	siteVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID

      	egovSiteService.updateSite(siteVO);

          return "forward:/uss/ion/sit/selectSiteList.do";

      }
      
      /**
       * 사이트정보를 삭제처리한다.
       * @param siteVO
       * @param searchVO
       * @return	"forward:/uss/ion/sit/selectSiteList.do"
       * @throws Exception
       */
      @RequestMapping("/uss/ion/sit/deleteSite.do")
      public String deleteSite(SiteVO siteVO, @ModelAttribute("searchVO") SiteVO searchVO) throws Exception {

      	egovSiteService.deleteSite(siteVO);

          return "forward:/uss/ion/sit/selectSiteList.do";
      }
}
