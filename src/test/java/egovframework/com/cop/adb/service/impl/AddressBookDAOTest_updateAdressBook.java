package egovframework.com.cop.adb.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_updateAdressBook extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Autowired
	private AddressBookDAOTestData addressBookDAOTestData;

	@Test
	void updateAdressBook() {
		AddressBook insertAdressBookTestData = addressBookDAOTestData.insertAdressBookTestData();

		// given
		AddressBook addressBook = new AddressBook();
		addressBook.setAdbkId(insertAdressBookTestData.getAdbkId());

		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		addressBook.setAdbkNm("test 주소록명" + today);
		addressBook.setOthbcScope("test 공개범위");
		addressBook.setUseAt("N");
		addressBook.setLastUpdusrId("test 최종수정자ID");

		// when
		int result = addressBookDAO.updateAdressBook(addressBook);

		log.debug("result={}", result);

		// then
		assertTrue(result > 0);
	}

}