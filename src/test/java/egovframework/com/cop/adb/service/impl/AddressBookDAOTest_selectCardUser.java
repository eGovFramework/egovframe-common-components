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
public class AddressBookDAOTest_selectCardUser extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		String id="12345678901234567890";

		// when
		AddressBookUser cardUser = addressBookDAO.selectCardUser(id);

		log.debug("cardUser={}", cardUser);

		// then
		assertEquals(true, true);
	}

}