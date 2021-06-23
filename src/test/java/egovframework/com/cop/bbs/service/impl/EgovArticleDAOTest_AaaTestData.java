package egovframework.com.cop.bbs.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EgovArticleDAOTest_AaaTestData {

	private final EgovBBSMasterDAO egovBBSMasterDAO;
	private final EgovArticleDAO egovArticleDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	public Blog insertBlogMaster(LoginVO loginVO) throws FdlException {
		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		Blog blog = new Blog();
		blog.setBlogId(egovBlogIdGnrService.getNextStringId());
		blog.setBbsId(egovBBSMstrIdGnrService.getNextStringId()); // 게시판 ID
		blog.setBlogIntrcn("test 블로그 소개" + today);
		blog.setBlogNm("test 블로그 명" + today);
		blog.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
//		blog.setFrstRegisterPnttm("");
//		blog.setLastUpdusrId("");
//		blog.setLastUpdusrPnttm("");
		blog.setRegistSeCode("REGC02"); // 등록구분코드 COM001 REGC02 커뮤니티 등록
//		blog.setTmplatId(""); // 템플릿 ID
		blog.setUseAt("Y"); // 사용여부
//		blog.setEmplyrId("");
//		blog.setUserNm("");
//		blog.setTmplatNm("");
		blog.setBlogAt("Y"); // 블로그 여부

		egovBBSMasterDAO.insertBlogMaster(blog);

		return blog;
	}

}
