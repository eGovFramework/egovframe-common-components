package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_selectManListCnt extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookUserVO adbkUserVO = new AddressBookUserVO();
		adbkUserVO.setSearchWrd("테스트1");

		// when
		int manListCnt = addressBookDAO.selectManListCnt(adbkUserVO);

		log.debug("manListCnt={}", manListCnt);

		// then
		assertEquals(true, true);
	}

}