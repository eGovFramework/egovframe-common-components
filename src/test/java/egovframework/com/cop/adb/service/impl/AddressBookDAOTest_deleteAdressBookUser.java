package egovframework.com.cop.adb.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_deleteAdressBookUser extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Autowired
	private AddressBookDAOTestData addressBookDAOTestData;

	@Test
	void deleteAdressBookUser() {
		AddressBook insertAdressBookTestData = addressBookDAOTestData.insertAdressBookTestData();
		AddressBookUser insertAdressBookUserTestData = addressBookDAOTestData
				.insertAdressBookUserTestData(insertAdressBookTestData.getAdbkId());

		// given
		AddressBookUser adbkUser = new AddressBookUser();
		adbkUser.setEmplyrId(""); // 업무사용자ID
		adbkUser.setNcrdId(""); // 명함ID
		adbkUser.setAdbkId(insertAdressBookUserTestData.getAdbkId()); // 주소록ID

//		// PK
//		adbkUser.setAdbkUserId(""); // 주소록구성원ID
//		adbkUser.setAdbkId("ADBK_000000000000071"); // 주소록ID

		// when
		int result = addressBookDAO.deleteAdressBookUser(adbkUser);

		log.debug("result={}", result);

		// then
		assertTrue(result > 0);
	}

}