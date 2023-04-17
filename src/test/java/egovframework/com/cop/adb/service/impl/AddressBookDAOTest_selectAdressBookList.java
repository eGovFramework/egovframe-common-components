package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_selectAdressBookList extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookVO adbkVO = new AddressBookVO();
		adbkVO.setFirstIndex(0);
		adbkVO.setRecordCountPerPage(10);
		adbkVO.setWrterId("test 작성자ID");
		adbkVO.setTrgetOrgnztId("test 대상조직ID");

		adbkVO.setSearchCnd("0");
		adbkVO.setSearchWrd("test 주소록명");

//		adbkVO.setSearchCnd("1");
//		adbkVO.setSearchWrd("test 공개범위");

//		adbkVO.setSearchCnd("2");
//		adbkVO.setSearchWrd("test 작성자ID");

		// when
		List<AddressBookVO> adressBookList = addressBookDAO.selectAdressBookList(adbkVO);
		int size = adressBookList.size();

		log.debug("adressBookList={}", adressBookList);
		log.debug("size={}", size);

		for (AddressBookVO adressBook : adressBookList) {
			log.debug("adressBook={}", adressBook);
		}

		// then
		assertEquals(true, true);
	}

}