package egovframework.com.cop.scp.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

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
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovArticleScrapDAOTest_Configuration.class })
public class EgovArticleScrapDAOTest_selectArticleScrapList extends EgovTestV1 {

	@Resource(name = "EgovArticleScrapDAO")
	private EgovArticleScrapDAO egovArticleScrapDAO;

	@Resource(name = "egovScrapIdGnrService")
	private EgovIdGnrService egovScrapIdGnrService;

	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;

	// testData
	String today;
	LoginVO authenticatedUser;

	Scrap scrap;

	// given
	ScrapVO scrapVO;

	// when
	List<ScrapVO> articleScraps;

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
		scrapVO.setUniqId(scrap.getFrstRegisterId());

		scrapVO.setSearchCnd("0");
		scrapVO.setSearchWrd(scrap.getScrapNm());

		scrapVO.setRecordCountPerPage(propertiesService.getInt("pageUnit"));
		scrapVO.setFirstIndex(0);
	}

	void when() {
		articleScraps = egovArticleScrapDAO.selectArticleScrapList(scrapVO);
	}

	void then() {
		scrap.setUseAt("Y");

		log.debug("scrapId={}, {}", articleScraps.get(0).getScrapId(), scrap.getScrapId());
		log.debug("nttId={}, {}", articleScraps.get(0).getNttId(), scrap.getNttId());
		log.debug("bbsId={}, {}", articleScraps.get(0).getBbsId(), scrap.getBbsId());
		log.debug("scrapNm={}, {}", articleScraps.get(0).getScrapNm(), scrap.getScrapNm());
		log.debug("useAt={}, {}", articleScraps.get(0).getUseAt(), scrap.getUseAt());
		log.debug("frstRegisterPnttm={}, {}", articleScraps.get(0).getFrstRegisterPnttm(),
				scrap.getFrstRegisterPnttm());
		log.debug("frstRegisterNm={}, {}", articleScraps.get(0).getFrstRegisterNm(), scrap.getFrstRegisterNm());

		assertEquals(articleScraps.get(0).getScrapId(), scrap.getScrapId());
		assertEquals(articleScraps.get(0).getNttId(), scrap.getNttId());
		assertEquals(articleScraps.get(0).getBbsId(), scrap.getBbsId());
		assertEquals(articleScraps.get(0).getScrapNm(), scrap.getScrapNm());
		assertEquals(articleScraps.get(0).getUseAt(), scrap.getUseAt());
//		assertEquals(articleScraps.get(0).getFrstRegisterPnttm(), scrap.getFrstRegisterPnttm());
//		assertEquals(articleScraps.get(0).getFrstRegisterNm(), scrap.getFrstRegisterNm());
	}

}