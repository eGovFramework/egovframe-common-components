package egovframework.com.cop.cmy.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovCommuMasterDAOTest_Configuration.class })
public class EgovCommuMasterDAOTest_selectCommuMasterDetail extends EgovTestV1 {

	@Resource(name = "EgovCommuMasterDAO")
	private EgovCommuMasterDAO egovCommuMasterDAO;

	@Resource(name = "egovCmmntyIdGnrService")
	private EgovIdGnrService egovCmmntyIdGnrService;

	// testData
	String today;
	LoginVO authenticatedUser;

	Community community;

	// given
	CommunityVO cmmntyVO;

	// when
	CommunityVO commuMasterDetail;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData_insertCommuMaster();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData_insertCommuMaster() {
		community = new Community();

		try {
			community.setCmmntyId(egovCmmntyIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("FdlException");
		}

		community.setCmmntyNm("test 커뮤니티명" + today);
		community.setCmmntyIntrcn("test 커뮤니티소개" + today);
		community.setRegistSeCode("REGC06"); // COM001(등록구분코드): REGC06(커뮤니티 게시판 등록)
		community.setTmplatId("TMPLAT_CMNTY_DEFAULT"); // 템플릿ID, COMTNTMPLATINFO: TMPLAT_CMNTY_DEFAULT(커뮤니티 기본템플릿)
		community.setUseAt("Y");
		community.setFrstRegisterId(authenticatedUser.getUniqId());

		egovCommuMasterDAO.insertCommuMaster(community);
	}

	void given() {
		cmmntyVO = new CommunityVO();
		cmmntyVO.setCmmntyId(community.getCmmntyId());
	}

	void when() {
		commuMasterDetail = egovCommuMasterDAO.selectCommuMasterDetail(cmmntyVO);
	}

	void then() {
		log.debug("cmmntyId={}, {}", commuMasterDetail.getCmmntyId(), cmmntyVO.getCmmntyId());
		log.debug("cmmntyNm={}, {}", commuMasterDetail.getCmmntyNm(), community.getCmmntyNm());
		log.debug("cmmntyIntrcn={}, {}", commuMasterDetail.getCmmntyIntrcn(), community.getCmmntyIntrcn());
		log.debug("useAt={}, {}", commuMasterDetail.getUseAt(), community.getUseAt());
		log.debug("tmplatId={}, {}", commuMasterDetail.getTmplatId(), community.getTmplatId());
		log.debug("tmplatNm={}, {}", commuMasterDetail.getTmplatNm(), community.getTmplatNm());
		log.debug("frstRegisterId={}, {}", commuMasterDetail.getFrstRegisterId(), community.getFrstRegisterId());
		log.debug("frstRegisterNm={}, {}", commuMasterDetail.getFrstRegisterNm(), authenticatedUser.getName());
		log.debug("frstRegisterPnttm={}, {}", commuMasterDetail.getFrstRegisterPnttm(),
				community.getFrstRegisterPnttm());

		assertEquals(commuMasterDetail.getCmmntyId(), cmmntyVO.getCmmntyId());
		assertEquals(commuMasterDetail.getCmmntyNm(), community.getCmmntyNm());
		assertEquals(commuMasterDetail.getCmmntyIntrcn(), community.getCmmntyIntrcn());
		assertEquals(commuMasterDetail.getUseAt(), community.getUseAt());
		assertEquals(commuMasterDetail.getTmplatId(), community.getTmplatId());
//		assertEquals(commuMasterDetail.getTmplatNm(), community.getTmplatNm());
		assertEquals(commuMasterDetail.getTmplatNm(), "커뮤니티 기본템플릿");
		assertEquals(commuMasterDetail.getFrstRegisterId(), community.getFrstRegisterId());
//		assertEquals(commuMasterDetail.getFrstRegisterNm(), authenticatedUser.getName());
		assertEquals(commuMasterDetail.getFrstRegisterNm(), "테스트1");
//		assertEquals(commuMasterDetail.getFrstRegisterPnttm(), community.getFrstRegisterPnttm());
//		assertEquals(commuMasterDetail.getFrstRegisterPnttm(), "2021-11-18");
		assertEquals(commuMasterDetail.getFrstRegisterPnttm(), EgovDateUtil.toString(new Date(), "yyyy-MM-dd", null));
		
		
	}

}