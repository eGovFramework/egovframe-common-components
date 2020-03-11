package egovframework.com.cop.scp.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.scp.service.EgovArticleScrapService;
import egovframework.com.cop.scp.service.Scrap;
import egovframework.com.cop.scp.service.ScrapVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 스크랩관리 서비스 컨트롤러 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.07.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.10  한성곤          최초 생성
 *   2016.06.13	김연호		표준프레임워크 3.6 개선
 *
 * </pre>
 */

@Controller
public class EgovArticleScrapController {


    @Resource(name="EgovArticleScrapService")
    protected EgovArticleScrapService egovArticleScrapService;

    @Resource(name = "EgovArticleService")
    private EgovArticleService egovArticleService;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

    //Logger log = Logger.getLogger(this.getClass());

    /**
     * 스크랩관리 목록 조회를 제공한다.
     *
     * @param scrapVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="스크랩관리", order = 250 ,gid = 40)
    @RequestMapping("/cop/scp/selectArticleScrapList.do")
    public String selectArticleScrapList(@ModelAttribute("searchVO") ScrapVO scrapVO, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
	
		scrapVO.setUniqId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	
		scrapVO.setPageUnit(propertyService.getInt("pageUnit"));
		scrapVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(scrapVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(scrapVO.getPageUnit());
		paginationInfo.setPageSize(scrapVO.getPageSize());
	
		scrapVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		scrapVO.setLastIndex(paginationInfo.getLastRecordIndex());
		scrapVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = egovArticleScrapService.selectArticleScrapList(scrapVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
	
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
	
		return "egovframework/com/cop/scp/EgovArticleScrapList";
    }

    /**
     * 스크랩에 대한 상세정보를 조회한다.
     *
     * @param scrapVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/scp/selectArticleScrapDetail.do")
    public String selectArticleScrapDetail(@ModelAttribute("searchVO") ScrapVO scrapVO, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
	
		ScrapVO scrap = egovArticleScrapService.selectArticleScrapDetail(scrapVO);
	
		model.addAttribute("sessionUniqId", user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		model.addAttribute("result", scrap);
	
		//게시판 내용 취득
		BoardVO vo = new BoardVO();
		vo.setNttId(scrap.getNttId());
		vo.setBbsId(scrap.getBbsId());
		vo = egovArticleService.selectArticleDetail(vo);
	
		model.addAttribute("articleVO", vo);
		////-----------------------------------
	
		return "egovframework/com/cop/scp/EgovArticleScrapDetail";
    }

    /**
     * 스크랩 등록을 위한 등록 페이지로 이동한다.
     *
     * @param scrapVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/scp/insertArticleScrapView.do")
    public String insertArticleScrapView(@ModelAttribute("searchVO") ScrapVO scrapVO, ModelMap model) throws Exception {

		ScrapVO scrap = new ScrapVO();
	
		model.addAttribute("articleScrapVO", scrap);
	
		BoardVO vo = new BoardVO();
		vo.setNttId(scrapVO.getNttId());
		vo.setBbsId(scrapVO.getBbsId());
		
		//게시판 내용 취득
		vo = egovArticleService.selectArticleDetail(vo);
	
		model.addAttribute("articleVO", vo);
		////-----------------------------------
	
		return "egovframework/com/cop/scp/EgovArticleScrapRegist";
    }


    /**
     * 스크랩을 등록한다.
     *
     * @param scrapVO
     * @param scrap
     * @param bindingResult
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/scp/insertArticleScrap.do")
    public String insertArticleScrap(@ModelAttribute("searchVO") ScrapVO scrapVO, @ModelAttribute("scrap") Scrap scrap,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		beanValidator.validate(scrap, bindingResult);
		if (bindingResult.hasErrors()) {

			//게시판 내용 취득
		    BoardVO vo = new BoardVO();
			vo.setNttId(scrapVO.getNttId());
			vo.setBbsId(scrapVO.getBbsId());
			
			model.addAttribute("articleScrapVO", scrap);
		    model.addAttribute("articleVO", vo);
	
		    return "egovframework/com/cop/scp/EgovArticleScrapRegist";
		}
	
		if (isAuthenticated) {
		    scrap.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	
		    egovArticleScrapService.insertArticleScrap(scrap);
		}
	
		return "forward:/cop/scp/selectArticleScrapList.do";
    }

    /**
     * 스크랩을 삭제한다.
     *
     * @param ScrapVO
     * @param Scrap
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/scp/deleteArticleScrap.do")
    public String deleteArticleScrap(@ModelAttribute("searchVO") ScrapVO scrapVO, @ModelAttribute("Scrap") Scrap scrap, ModelMap model) throws Exception {
	@SuppressWarnings("unused")
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		if (isAuthenticated) {
		    egovArticleScrapService.deleteArticleScrap(scrapVO);
		}
	
		return "forward:/cop/scp/selectArticleScrapList.do";
    }

    /**
     * 스크랩 수정 페이지로 이동한다.
     *
     * @param scrapVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/scp/updateArticleScrapView.do")
    public String updateArticleScrapView(@ModelAttribute("searchVO") ScrapVO scrapVO, @ModelAttribute("scrap") Scrap scrap, ModelMap model) throws Exception {
		Scrap vo = egovArticleScrapService.selectArticleScrapDetail(scrapVO);
	
		model.addAttribute("articleScrapVO", vo);
	
		//게시판 내용 취득
		BoardVO boardVO = new BoardVO();
		boardVO.setNttId(vo.getNttId());
		boardVO.setBbsId(vo.getBbsId());
		boardVO = egovArticleService.selectArticleDetail(boardVO);
		
		
	    model.addAttribute("articleVO", boardVO);
	
	
		return "egovframework/com/cop/scp/EgovArticleScrapUpdt";
    }

    /**
     * 스크랩을 수정한다.
     *
     * @param ScrapVO
     * @param Scrap
     * @param bindingResult
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/scp/updateArticleScrap.do")
    public String updateArticleScrap(@ModelAttribute("searchVO") ScrapVO scrapVO, @ModelAttribute("Scrap") Scrap scrap,
	    BindingResult bindingResult, ModelMap model) throws Exception {

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(scrap, bindingResult);
		if (bindingResult.hasErrors()) {
	
		    Scrap vo = egovArticleScrapService.selectArticleScrapDetail(scrapVO);
	
		    model.addAttribute("result", vo);
	
		    return "egovframework/com/cop/scp/EgovArticleScrapUpdt";
		}
	
		if (isAuthenticated) {
		    scrap.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	
		    egovArticleScrapService.updateArticleScrap(scrap);
		}

	return "forward:/cop/scp/selectArticleScrapList.do";
    }
//
//    /**
//     * 마이페이지용 스크랩관리 목록 조회를 제공한다.
//     *
//     * @param scrapVO
//     * @param model
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("/cop/scp/selectScrapMainList.do")
//    public String selectScrapMainList(@ModelAttribute("searchVO") ScrapVO scrapVO, ModelMap model) throws Exception {
//	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//
//	scrapVO.setUniqId(user.getUniqId());
//
//	scrapVO.setPageUnit(propertyService.getInt("pageUnit"));
//	scrapVO.setPageSize(propertyService.getInt("pageSize"));
//
//	scrapVO.setFirstIndex(0);
//	scrapVO.setRecordCountPerPage(5);
//
//	Map<String, Object> map = bbsScrapService.selectScrapList(scrapVO);
//
//	model.addAttribute("resultList", map.get("resultList"));
//	model.addAttribute("resultCnt", map.get("resultCnt"));
//
//	return "egovframework/com/cop/scp/EgovScrapMainList";
//    }
	
}
