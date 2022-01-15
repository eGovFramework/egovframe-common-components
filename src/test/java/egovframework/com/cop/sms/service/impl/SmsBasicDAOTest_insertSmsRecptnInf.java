package egovframework.com.cop.sms.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cop.sms.service.Sms;
import egovframework.com.cop.sms.service.SmsRecptn;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsBasicDAOTest_insertSmsRecptnInf {

	private SmsBasicDAO smsDao = new SmsBasicDAO();

	// testData
	String today;
	LoginVO authenticatedUser;

	Sms sms;
	String smsId;

	// given
	SmsRecptn smsRecptn;

	// when
	boolean result = false;

	@Test
	public void test() {
		testData();
		testData_insertSmsInf();
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

	void given() {
		smsRecptn = new SmsRecptn();
		smsRecptn.setSmsId(smsId);
		smsRecptn.setRecptnTelno("010-0000-0001".replaceAll("-", ""));
		smsRecptn.setResultCode("3000");
		smsRecptn.setResultMssage("착발신 번호 포맷 오류 또는 부재");
	}

	void when() {
		try {
			smsDao.insertSmsRecptnInf(smsRecptn);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("insertSmsRecptnInf Exception");
		}
	}

	void then() {
		log.debug("smsId={}", smsId);
		log.debug("result={}", result, true);

		assertEquals(result, true);
	}

}
