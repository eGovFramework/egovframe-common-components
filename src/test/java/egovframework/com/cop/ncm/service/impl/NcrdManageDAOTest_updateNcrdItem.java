package egovframework.com.cop.ncm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ncm.service.NameCard;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { NcrdManageDAOTest_Configuration.class })
public class NcrdManageDAOTest_updateNcrdItem extends EgovTestV1 {

	@Resource(name = "NcrdManageDAO")
	private NcrdManageDAO ncrdManageDAO;

	@Resource(name = "egovNcrdIdGnrService")
	private EgovIdGnrService egovNcrdIdGnrService;

	// testData
	String today;
	String today2;
	LoginVO authenticatedUser;

	// given
	NameCard nameCard;

	// when
	boolean result = false;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData_insertNcrdItem();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData_insertNcrdItem() {
		nameCard = new NameCard();
		try {
			nameCard.setNcrdId(egovNcrdIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("egovNcrdIdGnrService FdlException");
		}

		nameCard.setNcrdNm("test 이름" + today);
		nameCard.setNcrdTrgterId(authenticatedUser.getUniqId()); // 명함대상자ID
		nameCard.setExtrlUserAt("Y"); // 외부사용자여부
//		nameCard.setExtrlUserAt(At.Y.name()); // 외부사용자여부
		nameCard.setOthbcAt("Y"); // 공개여부
//		nameCard.setOthbcAt(At.N.name()); // 공개여부
		nameCard.setClsfNm("test 직급명" + today);
		nameCard.setOfcpsNm("test 직위명" + today);
		nameCard.setCmpnyNm("test 회사명" + today);
		nameCard.setDeptNm("test 부서명" + today);
		nameCard.setEmailAdres("test 이메일주소" + today);
		nameCard.setTelNo("test 전화번호");
		nameCard.setNationNo("test 국가번호");
		nameCard.setAreaNo("0000"); // 지역번호
		nameCard.setMiddleTelNo("0000"); // 중간전화번호
		nameCard.setEndTelNo("0000"); // 끝전화번호
		nameCard.setMbtlNum("010"); // 이동전화번호 010, 011, 016, 017, 018, 019
		nameCard.setIdntfcNo("test 식별번호"); // 식별번호
		nameCard.setMiddleMbtlNum("0000"); // 중간이동전화번호
		nameCard.setEndMbtlNum("0000"); // 끝이동전화번호
		nameCard.setAdres("test 주소" + today);
		nameCard.setDetailAdres("test 상세주소" + today);
		nameCard.setFrstRegisterId(authenticatedUser.getUniqId());// 최초등록자ID
		nameCard.setRemark("test 비고" + today);

		try {
			ncrdManageDAO.insertNcrdItem(nameCard);
		} catch (Exception e) {
			log.error("insertNcrdItem Exception");
		}
	}

	void given() {
		today2 = " " + EgovDateUtil.toString(new Date(), null, null);

		nameCard.setNcrdNm("test 이름 수정" + today2);
		nameCard.setExtrlUserAt("N"); // 외부사용자여부
		nameCard.setOthbcAt("N"); // 공개여부
		nameCard.setClsfNm("test 직급명 수정" + today2);
		nameCard.setOfcpsNm("test 직위명 수정" + today2);
		nameCard.setCmpnyNm("test 회사명 수정" + today2);
		nameCard.setDeptNm("test 부서명 수정" + today2);
		nameCard.setEmailAdres("test 이메일주소 수정" + today2);
		nameCard.setTelNo("test 전화번호");
		nameCard.setNationNo("test 국가번호");
		nameCard.setAreaNo("0001"); // 지역번호
		nameCard.setMiddleTelNo("0001"); // 중간전화번호
		nameCard.setEndTelNo("0001"); // 끝전화번호
		nameCard.setMbtlNum("011"); // 이동전화번호 010, 011, 016, 017, 018, 019
		nameCard.setIdntfcNo("test 식별번호"); // 식별번호
		nameCard.setMiddleMbtlNum("0001"); // 중간이동전화번호
		nameCard.setEndMbtlNum("0001"); // 끝이동전화번호
		nameCard.setAdres("test 주소 수정" + today2);
		nameCard.setDetailAdres("test 상세주소 수정" + today2);
		nameCard.setRemark("test 비고 수정" + today2);
		nameCard.setLastUpdusrId(authenticatedUser.getUniqId());
//		nameCard.setNcrdId("");
	}

	void when() {
		try {
			ncrdManageDAO.updateNcrdItem(nameCard);
			result = true;
		} catch (Exception e) {
			log.error("updateNcrdItem Exception");
		}
	}

	void then() {
		log.debug("result={}, {}", result, true);

		assertEquals(result, true);
	}

}