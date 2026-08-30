package egovframework.com.cop.adb.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_selectAdressBookListCnt extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Autowired
	private AddressBookDAOTestData addressBookDAOTestData;

	@Test
	void selectAdressBookListCnt() {
		AddressBook insertAdressBookTestData = addressBookDAOTestData.insertAdressBookTestData();

		// given
		AddressBookVO adbkVO = new AddressBookVO();
//		adbkVO.setWrterId("test 작성자ID");
//		adbkVO.setTrgetOrgnztId("test 대상조직ID");

		adbkVO.setSearchCnd("0");
		adbkVO.setSearchWrd(insertAdressBookTestData.getAdbkNm());

//		adbkVO.setSearchCnd("1");
//		adbkVO.setSearchWrd("test 공개범위");
//		
//		adbkVO.setSearchCnd("2");
//		adbkVO.setSearchWrd("test 작성자ID");

		// when
		int adressBookListCnt = addressBookDAO.selectAdressBookListCnt(adbkVO);

		log.debug("adressBookListCnt={}", adressBookListCnt);

		// then
		assertTrue(adressBookListCnt > 0);
	}

}