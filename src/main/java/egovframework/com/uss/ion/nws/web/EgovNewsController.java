package egovframework.com.uss.ion.nws.web;

import java.util.List;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 뉴스정보를 처리하는 Controller 클래스
 * 
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *   2025.08.11  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovNewsController {

	@Resource(name = "EgovNewsService")
	private EgovNewsService egovNewsService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	// 첨부파일 관련
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 뉴스정보 목록을 조회한다.
	 * 
	 * @param newsVO
	 * @param model
	 * @return "/uss/ion/nws/EgovNewsList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "뉴스관리", order = 670, gid = 50)
	@RequestMapping(value = "/uss/ion/nws/selectNewsList.do")
	public String selectNewsList(@ModelAttribute("newsVO") NewsVO newsVO, ModelMap model) throws Exception {

		/** EgovPropertyService.SiteList */
		newsVO.setPageUnit(propertiesService.getInt("pageUnit"));
		newsVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(newsVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(newsVO.getPageUnit());
		paginationInfo.setPageSize(newsVO.getPageSize());

		newsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		newsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		newsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<NewsVO> resultList = egovNewsService.selectNewsList(newsVO);
		model.addAttribute("resultList", resultList);

		int totCnt = egovNewsService.selectNewsListCnt(newsVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/nws/EgovNewsList";
	}

	/**
	 * 뉴스정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param newsVO
	 * @param model
	 * @return "/uss/ion/nws/EgovNewsDetail"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/nws/selectNewsDetail.do")
	public String selectNewsDetail(@ModelAttribute("newsVO") NewsVO newsVO, ModelMap model) throws Exception {
		NewsVO vo = egovNewsService.selectNewsDetail(newsVO);
		model.addAttribute("newsVO", vo);

		return "egovframework/com/uss/ion/nws/EgovNewsDetail";
	}

	/**
	 * 뉴스정보를 등록 전 단계처리
	 * 
	 * @param newsVO
	 * @param model
	 * @return "/uss/ion/nws/EgovNewsRegist"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/nws/insertNewsView.do")
	public String insertNewsView(@ModelAttribute("newsVO") NewsVO newsVO, ModelMap model) throws Exception {
		model.addAttribute("newsVO", newsVO);

		return "egovframework/com/uss/ion/nws/EgovNewsRegist";
	}

	/**
	 * 뉴스정보를 등록한다.
	 * 
	 * @param multiRequest
	 * @param newsVO
	 * @param bindingResult
	 * @return "forward:/uss/ion/nws/selectNewsList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/nws/insertNews.do")
	public String insertNews(final MultipartHttpServletRequest multiRequest, @Valid @ModelAttribute("newsVO") NewsVO newsVO, BindingResult bindingResult, ModelMap model) throws Exception {

		// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> fvoList = null;
		String atchFileId = "";

		// final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if (!files.isEmpty()) {
			fvoList = fileUtil.parseFileInf(files, "NEWS_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(fvoList); // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		}

		// 리턴받은 첨부파일ID를 셋팅한다..
		newsVO.setAtchFileId(atchFileId); // 첨부파일 ID

		if(bindingResult.hasErrors()){
			model.addAttribute("newsVO", newsVO);
			return "egovframework/com/uss/ion/nws/EgovNewsRegist";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		newsVO.setFrstRegisterId(frstRegisterId); // 최초등록자ID
		newsVO.setLastUpdusrId(frstRegisterId); // 최종수정자ID

		egovNewsService.insertNews(newsVO);

		return "forward:/uss/ion/nws/selectNewsList.do";
	}

	/**
	 * 뉴스정보를 수정하기 전 단계처리
	 * 
	 * @param newsVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/nws/updateNewsView.do")
	public String updateNewsView(@ModelAttribute("newsVO") NewsVO newsVO, ModelMap model) throws Exception {
		model.addAttribute("newsVO", egovNewsService.selectNewsDetail(newsVO));

		return "egovframework/com/uss/ion/nws/EgovNewsUpdt";
	}

	/**
	 * 뉴스정보를 수정 처리한다
	 * 
	 * @param atchFileAt
	 * @param multiRequest
	 * @param newsVO
	 * @param bindingResult
	 * @param model
	 * @return "forward:/uss/ion/nws/NewsInfoListInqire.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/nws/updateNews.do")
	public String updateNewsInfo(final MultipartHttpServletRequest multiRequest, @Valid @ModelAttribute("newsVO") NewsVO newsVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("newsVO", newsVO);
			return "egovframework/com/uss/ion/nws/EgovNewsUpdt";
		}

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = newsVO.getAtchFileId();

		// final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if (!files.isEmpty()) {
			if ("".equals(atchFileId)) {
				List<FileVO> fvoList = fileUtil.parseFileInf(files, "NEWS_", 0, atchFileId, "");
				atchFileId = fileMngService.insertFileInfs(fvoList);
				newsVO.setAtchFileId(atchFileId); // 첨부파일 ID

			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int fileKeyParam = fileMngService.getMaxFileSN(fvo);
				List<FileVO> fvoList = fileUtil.parseFileInf(files, "NEWS_", fileKeyParam, atchFileId, "");
				fileMngService.updateFileInfs(fvoList);
			}
		}
		// 첨부파일 관련 ID 생성 end...

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		newsVO.setLastUpdusrId(lastUpdusrId); // 최종수정자ID

		egovNewsService.updateNews(newsVO);

		return "forward:/uss/ion/nws/selectNewsList.do";
	}

	/**
	 * 뉴스정보를 삭제한다.
	 * 
	 * @param newsVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/nws/deleteNews.do")
	public String deleteNews(@ModelAttribute("newsVO") NewsVO newsVO) throws Exception {
		egovNewsService.deleteNews(newsVO);

		return "forward:/uss/ion/nws/selectNewsList.do";
	}

}
