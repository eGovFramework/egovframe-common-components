package egovframework.com.cop.adb.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_selectAdressBookList extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Autowired
	private AddressBookDAOTestData addressBookDAOTestData;

	@Test
	void selectAdressBookList() {
		AddressBook insertAdressBookTestData = addressBookDAOTestData.insertAdressBookTestData();

		// given
		AddressBookVO adbkVO = new AddressBookVO();
		adbkVO.setFirstIndex(0);
		adbkVO.setRecordCountPerPage(10);
//		adbkVO.setWrterId("test 작성자ID");
//		adbkVO.setTrgetOrgnztId("test 대상조직ID");

		adbkVO.setSearchCnd("0");
		adbkVO.setSearchWrd(insertAdressBookTestData.getAdbkNm());

//		adbkVO.setSearchCnd("1");
//		adbkVO.setSearchWrd("test 공개범위");

//		adbkVO.setSearchCnd("2");
//		adbkVO.setSearchWrd("test 작성자ID");

		// when
		List<AddressBookVO> adressBookList = addressBookDAO.selectAdressBookList(adbkVO);
		int size = adressBookList.size();

		log.debug("adressBookList={}", adressBookList);
		log.debug("size={}", size);

		// then
		assertFalse(adressBookList.isEmpty());

		for (AddressBookVO adressBook : adressBookList) {
			log.debug("getAdbkNm={}, {}", adressBook.getAdbkNm(), adbkVO.getSearchWrd());

			assertTrue(adressBook.getAdbkNm().contains(adbkVO.getSearchWrd()));
		}
	}

}