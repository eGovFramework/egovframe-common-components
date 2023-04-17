package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_deleteAdressBookUser extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookUser adbkUser = new AddressBookUser();
		adbkUser.setEmplyrId(""); // 업무사용자ID
		adbkUser.setNcrdId(""); // 명함ID
		adbkUser.setAdbkId("ADBK_000000000000071"); // 주소록ID

//		// PK
//		adbkUser.setAdbkUserId(""); // 주소록구성원ID
//		adbkUser.setAdbkId("ADBK_000000000000071"); // 주소록ID

		// when
		boolean result = false;
		try {
			addressBookDAO.deleteAdressBookUser(adbkUser);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("result={}", result);

		// then
		assertEquals(result, true);
	}

}