package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_selectAdressBook extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookVO adbkVO = new AddressBookVO();
		adbkVO.setAdbkId("test 주소록ID");

		// when
		AddressBookVO adressBook = addressBookDAO.selectAdressBook(adbkVO);

		log.debug("adressBook={}", adressBook);

		if (adressBook == null) {
			return;
		}

		// then
		assertEquals(adressBook.getAdbkId(), adbkVO.getAdbkId());
	}

}