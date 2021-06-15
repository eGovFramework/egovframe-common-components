package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleDAOTest_Configuration.class })
public class EgovArticleDAOTest_selectBlogNmList extends EgovTestV1 {

	@Autowired
	@Qualifier("egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	@Autowired
	private EgovArticleDAO egovArticleDAO;

	@Test
//	@Commit
	public void test() throws Exception {
		log.debug("test");

		// given
		BoardVO boardVO = new BoardVO();
		boardVO.setBlogId(egovBlogIdGnrService.getNextStringId());

		// when
		List<BoardVO> blogNmList = egovArticleDAO.selectBlogNmList(boardVO);
		log.debug("blogNmList={}", blogNmList);

		// then
		assertEquals(blogNmList.size(), 0);
	}

}
