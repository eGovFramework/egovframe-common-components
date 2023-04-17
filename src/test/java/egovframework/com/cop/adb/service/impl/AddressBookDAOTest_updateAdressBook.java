package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_updateAdressBook extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBook addressBook = new AddressBook();
		addressBook.setAdbkId("ADBK_000000000000071");

		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		addressBook.setAdbkNm("test 주소록명" + today);
		addressBook.setOthbcScope("test 공개범위");
		addressBook.setUseAt("N");
		addressBook.setLastUpdusrId("test 최종수정자ID");

		// when
		boolean result = false;
		try {
			addressBookDAO.updateAdressBook(addressBook);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("result={}", result);

		// then
		assertEquals(result, true);
	}

}