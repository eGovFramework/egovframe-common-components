package egovframework.com.cop.adb.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_selectManListCnt extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	void selectManListCnt() {
		// given
		AddressBookUserVO adbkUserVO = new AddressBookUserVO();
		adbkUserVO.setSearchWrd("테스트1");

		// when
		int manListCnt = addressBookDAO.selectManListCnt(adbkUserVO);

		log.debug("manListCnt={}", manListCnt);

		// then
	}

}