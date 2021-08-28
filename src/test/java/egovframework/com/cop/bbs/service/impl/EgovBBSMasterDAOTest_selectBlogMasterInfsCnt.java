package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterDAOTest_AAA_Configuration.class })
public class EgovBBSMasterDAOTest_selectBlogMasterInfsCnt extends EgovTestV1 {

	@Autowired
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Test
//	@Commit
	public void test() {
		log.debug("test");

		// given
		BoardMasterVO boardMasterVO = testData();

		// when
		int blogMasterInfsCnt = egovBBSMasterDAO.selectBlogMasterInfsCnt(boardMasterVO);

		// then
		assertEquals(blogMasterInfsCnt, 1);
	}

	public BoardMasterVO testData() {
		String today = " " + EgovDateUtil.toString(new Date(), null, null);
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// insertBlogMaster
		Blog blog = new Blog();
		try {
			blog.setBlogId(egovBlogIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
		blog.setBlogNm("test 블로그 명" + today);
//		blog.setBlogIntrcn("");
		blog.setRegistSeCode("REGC02"); // 커뮤니티 등록
//		blog.setTmplatId("");
		blog.setUseAt("Y");
		blog.setFrstRegisterId(loginVO.getUniqId());
		try {
			blog.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
//		blog.setBlogAt("");
		egovBBSMasterDAO.insertBlogMaster(blog);

		// insertBBSMasterInf
		BoardMaster boardMaster = new BoardMaster();
		boardMaster.setBbsId(blog.getBbsId());
		boardMaster.setBbsTyCode("BBST01"); // COM101 BBST01 통합게시판
		boardMaster.setBbsNm("test 게시판명" + today); // 게시판명
		boardMaster.setBbsIntrcn("test 게시판소개" + today); // 게시판소개
		boardMaster.setReplyPosblAt("Y"); // 답장가능여부
		boardMaster.setFileAtchPosblAt("Y"); // 파일첨부가능여부
		boardMaster.setAtchPosblFileNumber(3); // 첨부가능파일숫자
		boardMaster.setTmplatId(""); // 템플릿ID
		boardMaster.setUseAt("Y"); // 사용여부
		boardMaster.setCmmntyId(""); // 커뮤니티ID
		boardMaster.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
		boardMaster.setBlogId(blog.getBlogId()); // 블로그 ID
		boardMaster.setBlogAt("Y"); // 블로그 여부

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		BoardMasterVO boardMasterVO = new BoardMasterVO();
		boardMasterVO.setRecordCountPerPage(10);
		boardMasterVO.setFirstIndex(0);

		boardMasterVO.setBlogId(boardMaster.getBlogId());
		boardMasterVO.setBbsId(boardMaster.getBbsId());
		boardMasterVO.setSearchWrd(blog.getBlogNm());
		boardMasterVO.setRegistSeCode(blog.getRegistSeCode());
//		log.debug("registSeCodeNm={}", boardVO.getRegistSeCodeNm());
		boardMasterVO.setUseAt(blog.getUseAt());
		boardMasterVO.setFrstRegisterId(blog.getFrstRegisterId());
//		log.debug("frstRegisterNm={}", boardVO.getFrstRegisterNm());
//		log.debug("frstRegisterPnttm={}", boardVO.getFrstRegisterPnttm());

		return boardMasterVO;
	}

}