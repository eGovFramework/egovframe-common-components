package egovframework.com.cop.sms.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cop.sms.service.Sms;
import egovframework.com.cop.sms.service.SmsRecptn;
import egovframework.com.cop.sms.service.SmsVO;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsBasicDAOTest_selectSmsInfs {

	private SmsBasicDAO smsDao = new SmsBasicDAO();

	// testData
	String today;
	LoginVO authenticatedUser;

	Sms sms;
	String smsId;

	SmsRecptn smsRecptn;

	// given
	SmsVO vo;

	// when
	List<SmsVO> smsInfs;

	@Test
	public void test() {
		testData();
		testData_insertSmsInf();
		testData_insertSmsRecptnInf();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
//		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void testData_insertSmsInf() {
		sms = new Sms();
		sms.setTrnsmitTelno("010-0000-0000".replaceAll("-", ""));
		sms.setTrnsmitCn("test 전송내용" + today);
		sms.setFrstRegisterId("USRCNFRM_00000000000");
//		sms.setFrstRegisterId(authenticatedUser.getUniqId());

		try {
			smsId = smsDao.insertSmsInf(sms);
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("insertSmsInf Exception");
		}
	}

	void testData_insertSmsRecptnInf() {
		smsRecptn = new SmsRecptn();
		smsRecptn.setSmsId(smsId);
		smsRecptn.setRecptnTelno("010-0000-0001".replaceAll("-", ""));
		smsRecptn.setResultCode("3000");
		smsRecptn.setResultMssage("착발신 번호 포맷 오류 또는 부재");

		try {
			smsDao.insertSmsRecptnInf(smsRecptn);
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("insertSmsRecptnInf Exception");
		}
	}

	void given() {
		vo = new SmsVO();

//		vo.setSearchCnd("0");
//		vo.setSearchWrd(smsRecptn.getRecptnTelno());

		vo.setSearchCnd("1");
		vo.setSearchWrd(sms.getTrnsmitCn());

		vo.setRecordCountPerPage(10);
		vo.setFirstIndex(0);
	}

	void when() {
		try {
			smsInfs = smsDao.selectSmsInfs(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("selectSmsInfs Exception");
		}
	}

	void then() {
		log.debug("getSmsId={}, {}", smsInfs.get(0).getSmsId(), smsId);
		log.debug("getTrnsmitCn={}, {}", smsInfs.get(0).getTrnsmitTelno(), sms.getTrnsmitTelno());
		log.debug("getTrnsmitCn={}, {}", smsInfs.get(0).getTrnsmitCn(), sms.getTrnsmitCn());

		assertEquals(smsInfs.get(0).getSmsId(), smsId);
		assertEquals(smsInfs.get(0).getTrnsmitTelno(), sms.getTrnsmitTelno());
		assertEquals(smsInfs.get(0).getTrnsmitCn(), sms.getTrnsmitCn());
	}

}
