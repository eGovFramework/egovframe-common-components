package egovframework.com.cop.adb.service.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class AddressBookDAOTest_selectCardList extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	void selectCardList() {
		// given
		AddressBookUserVO adbkUserVO = new AddressBookUserVO();
		adbkUserVO.setSearchWrd("test 이름");
		adbkUserVO.setFirstIndex(0);
		adbkUserVO.setRecordCountPerPage(10);

		// when
		List<AddressBookUserVO> cardList = addressBookDAO.selectCardList(adbkUserVO);
		int size = cardList.size();

		log.debug("cardList={}", cardList);
		log.debug("size={}", size);

		for (AddressBookUserVO card : cardList) {
			log.debug("card={}", card);
			log.debug("getNm={}", card.getNm());
		}

		if (size == 0) {
			return;
		}

		// then
//		assertFalse(cardList.isEmpty());

//		assertEquals(cardList.get(0).getNm(), adbkUserVO.getSearchWrd());
	}

}