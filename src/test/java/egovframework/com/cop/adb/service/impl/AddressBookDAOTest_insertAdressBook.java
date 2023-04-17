package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_insertAdressBook extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Autowired
	@Qualifier("egovAdbkIdGnrService")
	private EgovIdGnrService egovAdbkIdGnrService;

	@Test
	@Rollback(true)
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBook addressBook = new AddressBook();
		log.debug("getAdbkId={}", addressBook.getAdbkId());
		addressBook.setAdbkId(egovAdbkIdGnrService.getNextStringId());
		log.debug("getAdbkId={}", addressBook.getAdbkId());

		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		addressBook.setAdbkNm("test 주소록명" + today);

		// when
		boolean result = false;
		try {
			addressBookDAO.insertAdressBook(addressBook);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("result={}", result);

		// then
		assertEquals(result, true);
	}

}