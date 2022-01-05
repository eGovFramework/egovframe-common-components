package egovframework.com.cop.scp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.scp.service.Scrap;
import egovframework.com.cop.scp.service.ScrapVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleScrapDAOTest_Configuration.class })
public class EgovArticleScrapDAOTest_selectArticleScrapListCnt extends EgovTestV1 {

	@Resource(name = "EgovArticleScrapDAO")
	private EgovArticleScrapDAO egovArticleScrapDAO;

	@Resource(name = "egovScrapIdGnrService")
	private EgovIdGnrService egovScrapIdGnrService;

	// testData
	String today;
	LoginVO authenticatedUser;

	Scrap scrap;

	// given
	ScrapVO scrapVO;

	// when
	int articleScrapsCnt;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData_insertArticleScrap();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData_insertArticleScrap() {
		scrap = new Scrap();
		try {
			scrap.setScrapId(egovScrapIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("egovScrapIdGnrService FdlException");
		}

//		scrap.setNttId(0l); // 게시물ID
//		scrap.setBbsId(""); // 게시판ID
		scrap.setScrapNm("test 스크랩명" + today); // 스크랩명
		scrap.setFrstRegisterId(authenticatedUser.getUniqId()); // 최초등록자ID

		try {
			egovArticleScrapDAO.insertArticleScrap(scrap);
		} catch (Exception e) {
			log.error("insertArticleScrap Exception");
		}
	}

	void given() {
		scrapVO = new ScrapVO();
		scrapVO.setUniqId(scrap.getFrstRegisterId());

		scrapVO.setSearchCnd("0");
		scrapVO.setSearchWrd(scrap.getScrapNm());
	}

	void when() {
		try {
			articleScrapsCnt = egovArticleScrapDAO.selectArticleScrapListCnt(scrapVO);
		} catch (Exception e) {
			log.error("selectArticleScrapListCnt Exception");
		}
	}

	void then() {
		log.debug("articleScrapsCnt={}, {}", articleScrapsCnt, 1);

		assertEquals(articleScrapsCnt, 1);
	}

}