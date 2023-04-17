package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class EgovAddressBookServiceImplTest_insertAdressBook extends EgovTestV1 {

	@Autowired
	private EgovAddressBookService egovAddressBookService;

	@Test
	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookVO adbkVO = new AddressBookVO();

		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		adbkVO.setAdbkNm("test 주소록명" + today);

		List<AddressBookUser> adbkMan = new ArrayList<>();

		AddressBookUser addressBookUser = new AddressBookUser();
		addressBookUser.setNm("test 이름" + today);
		adbkMan.add(addressBookUser);

		addressBookUser = new AddressBookUser();
		addressBookUser.setNm("test 이름2" + today);
		adbkMan.add(addressBookUser);

		adbkVO.setAdbkMan(adbkMan);

		// when
		boolean result = false;
		try {
			egovAddressBookService.insertAdressBook(adbkVO);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("result={}", result);

		// then
		assertEquals(result, true);
	}

}