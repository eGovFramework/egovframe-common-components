package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_insertAdressBookUser extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Autowired
	@Qualifier("egovAdbkUserIdGnrService")
	private EgovIdGnrService egovAdbkUserIdGnrService;

	@Test
	@Rollback(true)
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookUser addressBookUser = new AddressBookUser();
		addressBookUser.setAdbkUserId(egovAdbkUserIdGnrService.getNextStringId());

		// when
		boolean result = false;
		try {
			addressBookDAO.insertAdressBookUser(addressBookUser);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("result={}", result);

		// then
		assertEquals(result, true);
	}

}
