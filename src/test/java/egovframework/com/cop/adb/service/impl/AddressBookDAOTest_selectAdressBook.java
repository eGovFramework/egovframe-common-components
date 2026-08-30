package egovframework.com.cop.adb.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_selectAdressBook extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Autowired
	private AddressBookDAOTestData addressBookDAOTestData;

	@Test
	void selectAdressBook() {
		AddressBook insertAdressBookTestData = addressBookDAOTestData.insertAdressBookTestData();

		// given
		AddressBookVO adbkVO = new AddressBookVO();
		adbkVO.setAdbkId(insertAdressBookTestData.getAdbkId());

		// when
		AddressBookVO adressBook = addressBookDAO.selectAdressBook(adbkVO);

		log.debug("adressBook={}", adressBook);

		// then
		assertEquals(adbkVO.getAdbkId(), adressBook.getAdbkId());
	}

}