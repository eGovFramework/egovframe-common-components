package egovframework.com.cop.adb.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class, })
public class EgovAddressBookServiceImpl_selectUserListTest extends EgovTestV1 {

	@Autowired
	private EgovAddressBookService egovAddressBookService;

	@Test
	void test() {
		// given
		AddressBookVO addressBookVO = new AddressBookVO();
		// 존재하지 않는 ID 를 조회하면 null 이 반환되므로, 조회할 주소록을 먼저 등록한다 (테스트 종료 시 롤백).
		addressBookVO.setAdbkNm("test 주소록명");
		AddressBookUser addressBookUser = new AddressBookUser();
		addressBookUser.setNm("test 이름");
		addressBookVO.setAdbkMan(new java.util.ArrayList<>(java.util.Collections.singletonList(addressBookUser)));
		egovAddressBookService.insertAdressBook(addressBookVO);

		// when
		AddressBookVO adressBook = egovAddressBookService.selectAdressBook(addressBookVO);
		List<AddressBookUser> adbkMan = adressBook.getAdbkMan();

		log.debug("adressBook={}", adressBook);
		log.debug("adbkMan={}", adbkMan);

		// then
		assertEquals(adressBook.getAdbkId(), addressBookVO.getAdbkId());
	}

}