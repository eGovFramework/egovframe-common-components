package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_selectManList extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookUserVO adbkUserVO = new AddressBookUserVO();
		adbkUserVO.setSearchWrd("테스트1");
		adbkUserVO.setFirstIndex(0);
		adbkUserVO.setRecordCountPerPage(10);

		// when
		List<AddressBookUserVO> manList = addressBookDAO.selectManList(adbkUserVO);

		log.debug("manList={}", manList);

		for (AddressBookUserVO man : manList) {
			log.debug("man={}", man);
			log.debug("getNm={}", man.getNm());
		}

		// then
		assertEquals(manList.get(0).getNm(), adbkUserVO.getSearchWrd());
	}

}