package egovframework.com.cop.adb.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class EgovAddressBookServiceImplTest_updateAdressBook extends EgovTestV1 {

	@Autowired
	private EgovAddressBookService egovAddressBookService;

	@Autowired
	private AddressBookDAOTestData addressBookDAOTestData;

	@Test
	void updateAdressBook() {
		AddressBook insertAdressBookTestData = addressBookDAOTestData.insertAdressBookTestData();

		// given
		AddressBookVO addressBookVO = new AddressBookVO();
		addressBookVO.setAdbkId(insertAdressBookTestData.getAdbkId());

		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		addressBookVO.setAdbkNm("test 주소록명" + today);
		addressBookVO.setOthbcScope("test 공개범위");
		addressBookVO.setUseAt("N");
		addressBookVO.setLastUpdusrId("test 최종수정자ID");

		// when
		int result = egovAddressBookService.updateAdressBook(addressBookVO);

		log.debug("result={}", result);

		// then
		assertTrue(result > 0);
	}

}