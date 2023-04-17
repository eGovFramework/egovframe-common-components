package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class EgovAddressBookServiceImplTest_updateAdressBook extends EgovTestV1 {

	@Autowired
	private EgovAddressBookService egovAddressBookService;

	@Test
	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookVO addressBookVO = new AddressBookVO();
		addressBookVO.setAdbkId("ADBK_000000000000071");

		String today = " " + EgovDateUtil.toString(new Date(), null, null);

		addressBookVO.setAdbkNm("test 주소록명" + today);
		addressBookVO.setOthbcScope("test 공개범위");
		addressBookVO.setUseAt("N");
		addressBookVO.setLastUpdusrId("test 최종수정자ID");

		// when
		boolean result = false;
		try {
			egovAddressBookService.updateAdressBook(addressBookVO);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("result={}", result);

		// then
		assertEquals(result, true);
	}

}