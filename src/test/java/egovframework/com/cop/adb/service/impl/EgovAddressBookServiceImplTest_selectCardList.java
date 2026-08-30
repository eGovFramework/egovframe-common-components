package egovframework.com.cop.adb.service.impl;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
class EgovAddressBookServiceImplTest_selectCardList extends EgovTestV1 {

	@Autowired
	private EgovAddressBookService egovAddressBookService;

	@Test
	void selectCardList() {
		// given
		AddressBookUserVO adbkUserVO = new AddressBookUserVO();
		adbkUserVO.setSearchWrd("test 이름");
		adbkUserVO.setFirstIndex(0);
		adbkUserVO.setRecordCountPerPage(10);

		// when
		Map<String, Object> cardList = egovAddressBookService.selectCardList(adbkUserVO);
		@SuppressWarnings("unchecked")
		List<AddressBookUserVO> resultList = (List<AddressBookUserVO>) cardList.get("resultList");
		String resultCnt = (String) cardList.get("resultCnt");

		log.debug("cardList={}", cardList);
		log.debug("resultList={}", resultList);
		log.debug("resultCnt={}", resultCnt);

		for (AddressBookUserVO result : resultList) {
			log.debug("result={}", result);
			log.debug("getNm={}", result.getNm());
		}

		// then
//		assertEquals(resultList.get(0).getNm(), adbkUserVO.getSearchWrd());
	}

}