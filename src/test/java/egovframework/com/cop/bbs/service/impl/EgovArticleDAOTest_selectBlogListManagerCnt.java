package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

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
public class EgovArticleDAOTest_selectBlogListManagerCnt extends EgovTestV1 {

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
		BoardVO vo = new BoardVO();
		vo.setBlogId(egovBlogIdGnrService.getNextStringId());

		vo.setSearchCnd("0");
		vo.setSearchWrd("test 게시판명");

//		vo.setSearchCnd("1");
//		vo.setSearchWrd("test 게시판소개");

		// when
		int blogListManagerCnt = egovArticleDAO.selectBlogListManagerCnt(vo);
		log.debug("blogListManagerCnt={}", blogListManagerCnt);

		// then
		assertEquals(blogListManagerCnt, 0);
	}

}
