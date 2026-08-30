package egovframework.com.cop.adb.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_selectManUser extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	void selectManUser() {
		// given
		String id = "TEST1";

		// when
		AddressBookUser manUser = addressBookDAO.selectManUser(id);

		log.debug("manUser={}", manUser);
		log.debug("getEmplyrId={}", manUser.getEmplyrId());

		// then
//		assertEquals(manUser.getEmplyrId(), id);
	}

}