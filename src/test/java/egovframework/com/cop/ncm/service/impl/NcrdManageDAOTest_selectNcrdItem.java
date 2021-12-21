package egovframework.com.cop.ncm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ncm.service.NameCard;
import egovframework.com.cop.ncm.service.NameCardVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { NcrdManageDAOTest_Configuration.class })
public class NcrdManageDAOTest_selectNcrdItem extends EgovTestV1 {

	@Resource(name = "NcrdManageDAO")
	private NcrdManageDAO ncrdManageDAO;

	@Resource(name = "egovNcrdIdGnrService")
	private EgovIdGnrService egovNcrdIdGnrService;

	// testData
	String today;
	LoginVO authenticatedUser;

	NameCard nameCard;

	// given

	// when
	NameCardVO ncrdItem;

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
		log.debug("getNcrdId={}", nameCard.getNcrdId());
	}

	void when() {
		try {
			ncrdItem = ncrdManageDAO.selectNcrdItem(nameCard);
		} catch (Exception e) {
			log.error("selectNcrdItem Exception");
		}
	}

	void then() {
		log.debug("ncrdId={}, {}", ncrdItem.getNcrdId(), nameCard.getNcrdId());
		log.debug("ncrdNm={}, {}", ncrdItem.getNcrdNm(), nameCard.getNcrdNm());
		log.debug("ncrdTrgterId={}, {}", ncrdItem.getNcrdTrgterId(), nameCard.getNcrdTrgterId());
		log.debug("othbcAt={}, {}", ncrdItem.getOthbcAt(), nameCard.getOthbcAt());
		log.debug("extrlUserAt={}, {}", ncrdItem.getExtrlUserAt(), nameCard.getExtrlUserAt());
		log.debug("clsfNm={}, {}", ncrdItem.getClsfNm(), nameCard.getClsfNm());
		log.debug("ofcpsNm={}, {}", ncrdItem.getOfcpsNm(), nameCard.getOfcpsNm());
		log.debug("cmpnyNm={}, {}", ncrdItem.getCmpnyNm(), nameCard.getCmpnyNm());
		log.debug("deptNm={}, {}", ncrdItem.getDeptNm(), nameCard.getDeptNm());
		log.debug("emailAdres={}, {}", ncrdItem.getEmailAdres(), nameCard.getEmailAdres());
		log.debug("telNo={}, {}", ncrdItem.getTelNo(), nameCard.getTelNo());
		log.debug("nationNo={}, {}", ncrdItem.getNationNo(), nameCard.getNationNo());
		log.debug("areaNo={}, {}", ncrdItem.getAreaNo(), nameCard.getAreaNo());
		log.debug("middleTelNo={}, {}", ncrdItem.getMiddleTelNo(), nameCard.getMiddleTelNo());
		log.debug("endTelNo={}, {}", ncrdItem.getEndTelNo(), nameCard.getEndTelNo());
		log.debug("mbtlNum={}, {}", ncrdItem.getMbtlNum(), nameCard.getMbtlNum());
		log.debug("idntfcNo={}, {}", ncrdItem.getIdntfcNo(), nameCard.getIdntfcNo());
		log.debug("middleMbtlNum={}, {}", ncrdItem.getMiddleMbtlNum(), nameCard.getMiddleMbtlNum());
		log.debug("endMbtlNum={}, {}", ncrdItem.getEndMbtlNum(), nameCard.getEndMbtlNum());
		log.debug("adres={}, {}", ncrdItem.getAdres(), nameCard.getAdres());
		log.debug("detailAdres={}, {}", ncrdItem.getDetailAdres(), nameCard.getDetailAdres());
		log.debug("remark={}, {}", ncrdItem.getRemark(), nameCard.getRemark());
		log.debug("frstRegisterPnttm={}, {}", ncrdItem.getFrstRegisterPnttm(), nameCard.getFrstRegisterPnttm());

		assertEquals(ncrdItem.getNcrdId(), nameCard.getNcrdId());
		assertEquals(ncrdItem.getNcrdNm(), nameCard.getNcrdNm());
		assertEquals(ncrdItem.getNcrdTrgterId(), nameCard.getNcrdTrgterId());
		assertEquals(ncrdItem.getOthbcAt(), nameCard.getOthbcAt());
		assertEquals(ncrdItem.getExtrlUserAt(), nameCard.getExtrlUserAt());
		assertEquals(ncrdItem.getClsfNm(), nameCard.getClsfNm());
		assertEquals(ncrdItem.getOfcpsNm(), nameCard.getOfcpsNm());
		assertEquals(ncrdItem.getCmpnyNm(), nameCard.getCmpnyNm());
		assertEquals(ncrdItem.getDeptNm(), nameCard.getDeptNm());
		assertEquals(ncrdItem.getEmailAdres(), nameCard.getEmailAdres());
		assertEquals(ncrdItem.getTelNo(), nameCard.getTelNo());
		assertEquals(ncrdItem.getNationNo(), nameCard.getNationNo());
		assertEquals(ncrdItem.getAreaNo(), nameCard.getAreaNo());
		assertEquals(ncrdItem.getMiddleTelNo(), nameCard.getMiddleTelNo());
		assertEquals(ncrdItem.getEndTelNo(), nameCard.getEndTelNo());
		assertEquals(ncrdItem.getMbtlNum(), nameCard.getMbtlNum());
		assertEquals(ncrdItem.getIdntfcNo(), nameCard.getIdntfcNo());
		assertEquals(ncrdItem.getMiddleMbtlNum(), nameCard.getMiddleMbtlNum());
		assertEquals(ncrdItem.getEndMbtlNum(), nameCard.getEndMbtlNum());
		assertEquals(ncrdItem.getAdres(), nameCard.getAdres());
		assertEquals(ncrdItem.getDetailAdres(), nameCard.getDetailAdres());
		assertEquals(ncrdItem.getRemark(), nameCard.getRemark());
//		assertEquals(ncrdItem.getFrstRegisterPnttm(), nameCard.getFrstRegisterPnttm());
	}

}