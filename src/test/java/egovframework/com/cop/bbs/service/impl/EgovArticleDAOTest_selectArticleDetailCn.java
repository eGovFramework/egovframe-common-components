package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectArticleDetailCn extends EgovTestV1 {

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Test
//	@Commit
	public void test() throws Exception {
		log.debug("test");

		// given
		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		boardVO.setNttId(egovNttIdGnrService.getNextLongId());

		// when
		List<BoardVO> articleDetailCn = egovArticleDAO.selectArticleDetailCn(boardVO);
		log.debug("articleDetailCn={}", articleDetailCn);

		// then
		assertEquals(articleDetailCn.size(), 0);
	}

}
