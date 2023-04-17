package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class EgovAddressBookServiceImplTest_selectAdressBook extends EgovTestV1 {

	@Autowired
	private EgovAddressBookService egovAddressBookService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookVO adbkVO = new AddressBookVO();
		adbkVO.setAdbkId("test 주소록ID");

		// when
		AddressBookVO adressBook = egovAddressBookService.selectAdressBook(adbkVO);

		log.debug("adressBook={}", adressBook);

		if (adressBook == null) {
			return;
		}

		List<AddressBookUser> adbkMan = adressBook.getAdbkMan();
		for (AddressBookUser addressBookUser : adbkMan) {
			log.debug("addressBookUser={}", addressBookUser);
		}

		// then
		assertEquals(adressBook.getAdbkId(), adbkVO.getAdbkId());
	}

}