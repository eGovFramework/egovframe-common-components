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
import egovframework.com.cop.ncm.service.NameCardVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { NcrdManageDAOTest_Configuration.class })
public class NcrdManageDAOTest_selectNcrdItemList extends EgovTestV1 {

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
	NameCardVO ncrdVO;

	// when
	List<NameCardVO> ncrdItems;

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
		ncrdVO = new NameCardVO();
		ncrdVO.setRecordCountPerPage(propertiesService.getInt("pageUnit"));
		ncrdVO.setFirstIndex(0);

		ncrdVO.setSearchCnd("0");
		ncrdVO.setSearchWrd(nameCard.getNcrdNm());

//		ncrdVO.setSearchCnd("1");
//		ncrdVO.setSearchWrd(nameCard.getCmpnyNm());

//		ncrdVO.setSearchCnd("2");
//		ncrdVO.setSearchWrd(nameCard.getDeptNm());
	}

	void when() {
		try {
			ncrdItems = ncrdManageDAO.selectNcrdItemList(ncrdVO);
		} catch (Exception e) {
			log.error("selectNcrdItemList Exception");
		}
	}

	void then() {
		log.debug("ncrdId={}, {}", ncrdItems.get(0).getNcrdId(), nameCard.getNcrdId());
		log.debug("ncrdNm={}, {}", ncrdItems.get(0).getNcrdNm(), nameCard.getNcrdNm());
		log.debug("ncrdTrgterId={}, {}", ncrdItems.get(0).getNcrdTrgterId(), nameCard.getNcrdTrgterId());
		log.debug("othbcAt={}, {}", ncrdItems.get(0).getOthbcAt(), nameCard.getOthbcAt());
		log.debug("extrlUserAt={}, {}", ncrdItems.get(0).getExtrlUserAt(), nameCard.getExtrlUserAt());
		log.debug("cmpnyNm={}, {}", ncrdItems.get(0).getCmpnyNm(), nameCard.getCmpnyNm());
		log.debug("deptNm={}, {}", ncrdItems.get(0).getDeptNm(), nameCard.getDeptNm());
		log.debug("idntfcNo={}, {}", ncrdItems.get(0).getIdntfcNo(), nameCard.getIdntfcNo());
		log.debug("middleMbtlNum={}, {}", ncrdItems.get(0).getMiddleMbtlNum(), nameCard.getMiddleMbtlNum());
		log.debug("endMbtlNum={}, {}", ncrdItems.get(0).getEndMbtlNum(), nameCard.getEndMbtlNum());
		log.debug("frstRegisterPnttm={}, {}", ncrdItems.get(0).getFrstRegisterPnttm(), nameCard.getFrstRegisterPnttm());
		log.debug("frstRegisterId={}, {}", ncrdItems.get(0).getFrstRegisterId(), nameCard.getFrstRegisterId());

		assertEquals(ncrdItems.get(0).getNcrdId(), nameCard.getNcrdId());
		assertEquals(ncrdItems.get(0).getNcrdNm(), nameCard.getNcrdNm());
		assertEquals(ncrdItems.get(0).getNcrdTrgterId(), nameCard.getNcrdTrgterId());
		assertEquals(ncrdItems.get(0).getOthbcAt(), nameCard.getOthbcAt());
		assertEquals(ncrdItems.get(0).getExtrlUserAt(), nameCard.getExtrlUserAt());
		assertEquals(ncrdItems.get(0).getCmpnyNm(), nameCard.getCmpnyNm());
		assertEquals(ncrdItems.get(0).getDeptNm(), nameCard.getDeptNm());
		assertEquals(ncrdItems.get(0).getIdntfcNo(), nameCard.getIdntfcNo());
		assertEquals(ncrdItems.get(0).getMiddleMbtlNum(), nameCard.getMiddleMbtlNum());
		assertEquals(ncrdItems.get(0).getEndMbtlNum(), nameCard.getEndMbtlNum());
//		assertEquals(ncrdItems.get(0).getFrstRegisterPnttm(), nameCard.getFrstRegisterPnttm());
		assertEquals(ncrdItems.get(0).getFrstRegisterId(), nameCard.getFrstRegisterId());
	}

}