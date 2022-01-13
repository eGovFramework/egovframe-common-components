package egovframework.com.cop.sms.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cop.sms.service.Sms;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsBasicDAOTest_insertSmsInf {

	private SmsBasicDAO smsDao = new SmsBasicDAO();

	// testData
	String today;
	LoginVO authenticatedUser;

	// given
	Sms sms;

	// when
	String smsId;
	boolean result = false;

	@Test
	public void test() {
		testData();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
//		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	}

	void given() {
		sms = new Sms();
		sms.setTrnsmitTelno("010-0000-0000".replaceAll("-", ""));
		sms.setTrnsmitCn("test 전송내용" + today);
		sms.setFrstRegisterId("USRCNFRM_00000000000");
//		sms.setFrstRegisterId(authenticatedUser.getUniqId());
	}

	void when() {
		try {
			smsId = smsDao.insertSmsInf(sms);
			result = true;
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("insertSmsInf Exception");
		}
	}

	void then() {
		log.debug("smsId={}", smsId);
		log.debug("result={}", result, true);

		assertEquals(result, true);
	}

}
