package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterServiceImplTestConfiguration.class })
public class EgovBBSMasterServiceImplTest_selectBlogMasterInfs extends EgovTestV1 {

	@Resource
	private EgovBBSMasterService egovBBSMasterService;

	@Resource
	private EgovBBSMasterDAO egovBBSMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Resource(name = "egovTmplatIdGnrService")
	private EgovIdGnrService egovTmplatIdGnrService;

	// testData
	String today;
	LoginVO authenticatedUser;
	Blog blog;
	BoardMaster boardMaster;

	// given
	BoardMasterVO boardMasterVO;

	// when
	Map<String, Object> blogMasterInfs;
	List<BlogVO> resultList;
	String resultCnt;

	@Test
	public void test() {
		log.debug("test");
		testData();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// insertBlogMaster
		blog = new Blog();
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
		blog.setFrstRegisterId(authenticatedUser.getUniqId());
		try {
			blog.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error(e.getMessage());
		}
//		blog.setBlogAt("");
		egovBBSMasterDAO.insertBlogMaster(blog);

		// insertBBSMasterInf
		boardMaster = new BoardMaster();
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
		boardMaster.setFrstRegisterId(authenticatedUser.getUniqId()); // 최초등록자ID
		boardMaster.setBlogId(blog.getBlogId()); // 블로그 ID
		boardMaster.setBlogAt("Y"); // 블로그 여부

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		boardMasterVO = new BoardMasterVO();
		boardMasterVO.setBlogId(boardMaster.getBlogId());
		boardMasterVO.setBbsId(boardMaster.getBbsId());
		boardMasterVO.setSearchWrd(blog.getBlogNm());
		boardMasterVO.setRegistSeCode(blog.getRegistSeCode());
//		log.debug("registSeCodeNm={}", boardVO.getRegistSeCodeNm());
		boardMasterVO.setUseAt(blog.getUseAt());
		boardMasterVO.setFrstRegisterId(blog.getFrstRegisterId());
//		log.debug("frstRegisterNm={}", boardVO.getFrstRegisterNm());
//		log.debug("frstRegisterPnttm={}", boardVO.getFrstRegisterPnttm());
	}

	void given() {
		boardMasterVO.setRecordCountPerPage(10);
		boardMasterVO.setFirstIndex(0);
	}

	@SuppressWarnings("unchecked")
	void when() {
		blogMasterInfs = egovBBSMasterService.selectBlogMasterInfs(boardMasterVO);
		resultList = (List<BlogVO>) blogMasterInfs.get("resultList");
		resultCnt = (String) blogMasterInfs.get("resultCnt");
	}

	void then() {
		assertEquals(resultList.get(0).getBbsId(), boardMasterVO.getBbsId());
		assertEquals(blogMasterInfs.get("resultCnt"), "1");
	}

	@Test
	public void test2() {
		log.debug("test2");
		log.debug("today={}", today);
		log.debug("authenticatedUser={}", authenticatedUser);
		log.debug("boardMasterVO={}", boardMasterVO);
//		log.debug("getBbsId={}", boardMasterVO.getBbsId());
	}

}