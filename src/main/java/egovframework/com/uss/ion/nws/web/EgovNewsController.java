package egovframework.com.uss.ion.nws.web;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.nws.service.EgovNewsService;
import egovframework.com.uss.ion.nws.service.NewsVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

@Controller
public class EgovNewsController {
	
	@Resource(name = "EgovNewsService")
    private EgovNewsService egovNewsService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
     * 뉴스정보 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	"/uss/ion/nws/EgovNewsList"
     * @throws Exception
     */
    @IncludedInfo(name="뉴스관리",order = 670 ,gid = 50)
    @RequestMapping(value="/uss/ion/nws/selectNewsList.do")
    public String selectNewsList(@ModelAttribute("searchVO") NewsVO searchVO, ModelMap model) throws Exception {

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

        List<NewsVO> NewsList = egovNewsService.selectNewsList(searchVO);
        model.addAttribute("resultList", NewsList);

        int totCnt = egovNewsService.selectNewsListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/ion/nws/EgovNewsList";
    }
	
    /**
     * 뉴스정보 목록에 대한 상세정보를 조회한다.
     * @param newsVO
     * @param searchVO
     * @param model
     * @return	"/uss/ion/nws/EgovNewsDetail"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/nws/selectNewsDetail.do")
    public String	selectNewsDetail(NewsVO newsVO,
            @ModelAttribute("searchVO") NewsVO searchVO,
            ModelMap model) throws Exception {

		NewsVO vo = egovNewsService.selectNewsDetail(newsVO);

		model.addAttribute("result", vo);

        return	"egovframework/com/uss/ion/nws/EgovNewsDetail";
    }
    
    /**
     * 뉴스정보를 등록 전 단계처리
     * @param searchVO
     * @param model
     * @return	"/uss/ion/nws/EgovNewsRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/nws/insertNewsView.do")
    public String insertNewsView(@ModelAttribute("searchVO") NewsVO searchVO, Model model) throws Exception {

        model.addAttribute("newsVO", new NewsVO());

        return "egovframework/com/uss/ion/nws/EgovNewsRegist";

    }
    
    /**
     * 뉴스정보를 등록한다.
     * @param multiRequest
     * @param searchVO
     * @param newsVO
     * @param bindingResult
     * @return	"forward:/uss/ion/nws/selectNewsList.do"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/nws/insertNews.do")
    public String insertNews(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") NewsVO searchVO,
            @ModelAttribute("newsVO") NewsVO newsVO, BindingResult bindingResult) throws Exception {


    	// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> _result = null;
		String _atchFileId = "";

		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if(!files.isEmpty()){
		 _result = fileUtil.parseFileInf(files, "NEWS_", 0, "", "");
		 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		}

    	// 리턴받은 첨부파일ID를 셋팅한다..
		newsVO.setAtchFileId(_atchFileId);			// 첨부파일 ID

    	beanValidator.validate(newsVO, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/uss/ion/nws/EgovNewsRegist";

		}

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String	frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	newsVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	newsVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID

        egovNewsService.insertNews(newsVO);

        return "forward:/uss/ion/nws/selectNewsList.do";
    }
    
    /**
     * 뉴스정보를 수정하기 전 단계처리
     * @param newsId
     * @param searchVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/uss/ion/nws/updateNewsView.do")
    public String updateNewsView(@RequestParam("newsId") String newsId ,
            @ModelAttribute("searchVO") NewsVO searchVO, ModelMap model)
            throws Exception {


        NewsVO newsVO = new NewsVO();

        // Primary Key 값 세팅
        newsVO.setNewsId(newsId);
        model.addAttribute("newsVO", egovNewsService.selectNewsDetail(newsVO));


        return "egovframework/com/uss/ion/nws/EgovNewsUpdt";
    }
	
    /**
     * 뉴스정보를 수정 처리한다
     * @param atchFileAt
     * @param multiRequest
     * @param searchVO
     * @param newsVO
     * @param bindingResult
     * @param model
     * @return	"forward:/uss/ion/nws/NewsInfoListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/ion/nws/updateNews.do")
    public String updateNewsInfo(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") NewsVO searchVO,
            @ModelAttribute("newsVO") NewsVO newsVO, BindingResult bindingResult, ModelMap model) throws Exception {

    	// Validation
    	beanValidator.validate(newsVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "egovframework/com/uss/olh/nws/EgovNewsInfoUpdt";
		}


    	// 첨부파일 관련 ID 생성 start....
		String _atchFileId = newsVO.getAtchFileId();

		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");
		
		if(!files.isEmpty()){
			if("".equals(_atchFileId)){
				List<FileVO> _result = fileUtil.parseFileInf(files, "NEWS_", 0, _atchFileId, "");
				_atchFileId = fileMngService.insertFileInfs(_result);
				newsVO.setAtchFileId(_atchFileId);    	// 첨부파일 ID

			}else{
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(_atchFileId);
				int _cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO> _result = fileUtil.parseFileInf(files, "NEWS_", _cnt, _atchFileId, "");
				fileMngService.updateFileInfs(_result);
			}
		}
		// 첨부파일 관련 ID 생성 end...


    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
    	newsVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID

    	egovNewsService.updateNews(newsVO);

        return "forward:/uss/ion/nws/selectNewsList.do";

    }
    
    /**
     * 뉴스정보를 삭제한다.
     * @param newsVO
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/uss/ion/nws/deleteNews.do")
    public String deleteNews(NewsVO newsVO, @ModelAttribute("searchVO") NewsVO searchVO) throws Exception {

    	egovNewsService.deleteNews(newsVO);

        return "forward:/uss/ion/nws/selectNewsList.do";
    }
    
}
