package egovframework.com.cop.adb.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_insertAdressBook extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Autowired
	private EgovIdGnrService egovAdbkIdGnrService;

	@Test
	void insertAdressBook() {
		// given
		AddressBook addressBook = new AddressBook();
		log.debug("getAdbkId={}", addressBook.getAdbkId());
		try {
			addressBook.setAdbkId(egovAdbkIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}
		log.debug("getAdbkId={}", addressBook.getAdbkId());

		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		addressBook.setAdbkNm("test 주소록명" + today);

		// when
		int result = addressBookDAO.insertAdressBook(addressBook);

		log.debug("result={}", result);

		// then
		assertTrue(result > 0);
	}

}