package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class EgovAddressBookServiceImplTest_selectManList extends EgovTestV1 {

	@Autowired
	private EgovAddressBookService egovAddressBookService;

	@SuppressWarnings("unchecked")
	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookUserVO adbkUserVO = new AddressBookUserVO();
		adbkUserVO.setSearchWrd("테스트1");
		adbkUserVO.setFirstIndex(0);
		adbkUserVO.setRecordCountPerPage(10);

		// when
		Map<String, Object> manList = egovAddressBookService.selectManList(adbkUserVO);
		List<AddressBookUserVO> resultList = (List<AddressBookUserVO>) manList.get("resultList");
		String resultCnt = (String) manList.get("resultCnt");

		log.debug("manList={}", manList);

		log.debug("resultList={}", resultList);
		for (AddressBookUserVO result : resultList) {
			log.debug("result={}", result);
			log.debug("getNm={}", result.getNm());
		}

		log.debug("resultCnt={}", resultCnt);

		// then
		assertEquals(resultList.get(0).getNm(), adbkUserVO.getSearchWrd());
	}

}