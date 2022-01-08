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
public class EgovArticleScrapDAOTest_selectArticleScrapDetail extends EgovTestV1 {

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
	ScrapVO articleScrap;

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

		egovArticleScrapDAO.insertArticleScrap(scrap);
	}

	void given() {
		scrapVO = new ScrapVO();
		scrapVO.setScrapId(scrap.getScrapId());
	}

	void when() {
		articleScrap = egovArticleScrapDAO.selectArticleScrapDetail(scrapVO);
	}

	void then() {
		scrap.setUseAt("Y");

		log.debug("scrapId={}, {}", articleScrap.getScrapId(), scrap.getScrapId());
		log.debug("nttId={}, {}", articleScrap.getNttId(), scrap.getNttId());
		log.debug("bbsId={}, {}", articleScrap.getBbsId(), scrap.getBbsId());
		log.debug("scrapNm={}, {}", articleScrap.getScrapNm(), scrap.getScrapNm());
		log.debug("useAt={}, {}", articleScrap.getUseAt(), scrap.getUseAt());
		log.debug("frstRegisterPnttm={}, {}", articleScrap.getFrstRegisterPnttm(), scrap.getFrstRegisterPnttm());
		log.debug("frstRegisterNm={}, {}", articleScrap.getFrstRegisterNm(), scrap.getFrstRegisterNm());
		log.debug("frstRegisterId={}, {}", articleScrap.getFrstRegisterId(), scrap.getFrstRegisterId());

		assertEquals(articleScrap.getScrapId(), scrap.getScrapId());
		assertEquals(articleScrap.getNttId(), scrap.getNttId());
		assertEquals(articleScrap.getBbsId(), scrap.getBbsId());
		assertEquals(articleScrap.getScrapNm(), scrap.getScrapNm());
		assertEquals(articleScrap.getUseAt(), scrap.getUseAt());
//		assertEquals(articleScrap.getFrstRegisterPnttm(), scrap.getFrstRegisterPnttm());
//		assertEquals(articleScrap.getFrstRegisterNm(), scrap.getFrstRegisterNm());
		assertEquals(articleScrap.getFrstRegisterId(), scrap.getFrstRegisterId());
	}

}