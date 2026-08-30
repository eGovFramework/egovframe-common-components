package egovframework.com.cop.adb.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_selectCardUser extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	void selectCardUser() {
		// given
		String id = "12345678901234567890";

		// when
		AddressBookUser cardUser = addressBookDAO.selectCardUser(id);

		log.debug("cardUser={}", cardUser);

		// then
//		assertNotNull(cardUser);
//		assertNull(cardUser);
	}

}