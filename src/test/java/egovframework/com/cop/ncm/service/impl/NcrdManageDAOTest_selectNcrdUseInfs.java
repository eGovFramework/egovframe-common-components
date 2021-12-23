package egovframework.com.cop.ncm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ncm.service.NameCard;
import egovframework.com.cop.ncm.service.NameCardUser;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { NcrdManageDAOTest_Configuration.class })
public class NcrdManageDAOTest_selectNcrdUseInfs extends EgovTestV1 {

	@Resource(name = "NcrdManageDAO")
	private NcrdManageDAO ncrdManageDAO;

	@Resource(name = "egovNcrdIdGnrService")
	private EgovIdGnrService egovNcrdIdGnrService;

	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;

	// testData
	String today;
	LoginVO authenticatedUser;

	NameCard nameCard;

	// given
	NameCardUser ncrdUser;

	// when
	List<NameCardUser> ncrdUseInfs;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData_insertNcrdItem();
		testData_insertNcrdUseInf();
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

	void testData_insertNcrdUseInf() {
		ncrdUser = new NameCardUser();
		ncrdUser.setNcrdId(nameCard.getNcrdId()); // 명함ID
		ncrdUser.setEmplyrId(authenticatedUser.getUniqId()); // 업무사용자ID
		ncrdUser.setRegistSeCode("REGC04"); // 명함등록
		ncrdUser.setUseAt("Y"); // 사용여부

		try {
			ncrdManageDAO.insertNcrdUseInf(ncrdUser);
		} catch (Exception e) {
			log.error("insertNcrdUseInf Exception");
		}
	}

	void given() {
//		ncrdUser.setEmplyrId(0);
		ncrdUser.setRecordCountPerPage(propertiesService.getInt("pageUnit"));
		ncrdUser.setFirstIndex(0);

		ncrdUser.setSearchCnd("0");
		ncrdUser.setSearchWrd(nameCard.getNcrdNm());

//		ncrdUser.setSearchCnd("1");
//		ncrdUser.setSearchWrd(nameCard.getCmpnyNm());

//		ncrdUser.setSearchCnd("2");
//		ncrdUser.setSearchWrd(nameCard.getDeptNm());
	}

	void when() {
		try {
			ncrdUseInfs = ncrdManageDAO.selectNcrdUseInfs(ncrdUser);
		} catch (Exception e) {
			log.error("selectNcrdUseInfs Exception");
		}

		ncrdUser.setCreatDt(EgovDateUtil.toString(new Date(), "yyyy-MM-dd", null));
	}

	void then() {
		log.debug("ncrdId={}, {}", ncrdUseInfs.get(0).getNcrdId(), nameCard.getNcrdId());
		log.debug("ncrdNm={}, {}", ncrdUseInfs.get(0).getNcrdNm(), nameCard.getNcrdNm());
		log.debug("cmpnyNm={}, {}", ncrdUseInfs.get(0).getCmpnyNm(), nameCard.getCmpnyNm());
		log.debug("deptNm={}, {}", ncrdUseInfs.get(0).getDeptNm(), nameCard.getDeptNm());
		log.debug("frstRegisterPnttm={}, {}", ncrdUseInfs.get(0).getFrstRegisterPnttm(),
				nameCard.getFrstRegisterPnttm());
		log.debug("emplyrId={}, {}", ncrdUseInfs.get(0).getEmplyrId(), ncrdUser.getEmplyrId());
		log.debug("useAt={}, {}", ncrdUseInfs.get(0).getUseAt(), ncrdUser.getUseAt());
		log.debug("userNm={}, {}", ncrdUseInfs.get(0).getUserNm(), authenticatedUser.getName());
		log.debug("creatDt={}, {}", ncrdUseInfs.get(0).getCreatDt(), ncrdUser.getCreatDt());
		log.debug("frstRegisterId={}, {}", ncrdUseInfs.get(0).getFrstRegisterId(), nameCard.getFrstRegisterId());

		assertEquals(ncrdUseInfs.get(0).getNcrdId(), nameCard.getNcrdId());
		assertEquals(ncrdUseInfs.get(0).getNcrdNm(), nameCard.getNcrdNm());
		assertEquals(ncrdUseInfs.get(0).getCmpnyNm(), nameCard.getCmpnyNm());
		assertEquals(ncrdUseInfs.get(0).getDeptNm(), nameCard.getDeptNm());
//		assertEquals(ncrdUseInfs.get(0).getFrstRegisterPnttm(), nameCard.getFrstRegisterPnttm());
		assertEquals(ncrdUseInfs.get(0).getEmplyrId(), ncrdUser.getEmplyrId());
		assertEquals(ncrdUseInfs.get(0).getUseAt(), ncrdUser.getUseAt());
//		assertEquals(ncrdUseInfs.get(0).getUserNm(), authenticatedUser.getName());
		assertEquals(ncrdUseInfs.get(0).getCreatDt(), ncrdUser.getCreatDt());
		assertEquals(ncrdUseInfs.get(0).getFrstRegisterId(), nameCard.getFrstRegisterId());
	}

}