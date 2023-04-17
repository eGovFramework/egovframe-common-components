package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_selectAdressBookListCnt extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	@Rollback(true)
//	@Rollback(false)
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookVO adbkVO = new AddressBookVO();
		adbkVO.setWrterId("test 작성자ID");
		adbkVO.setTrgetOrgnztId("test 대상조직ID");

		adbkVO.setSearchCnd("0");
		adbkVO.setSearchWrd("test 주소록명");

//		adbkVO.setSearchCnd("1");
//		adbkVO.setSearchWrd("test 공개범위");
//		
//		adbkVO.setSearchCnd("2");
//		adbkVO.setSearchWrd("test 작성자ID");

		// when
		int adressBookListCnt = addressBookDAO.selectAdressBookListCnt(adbkVO);

		log.debug("adressBookListCnt={}", adressBookListCnt);

		// then
		assertEquals(true, true);
	}

}