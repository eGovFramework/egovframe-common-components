package egovframework.com.cop.adb.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { AddressBookConfigurationTest.class })
public class AddressBookDAOTest_selectUserList extends EgovTestV1 {

	@Autowired
	private AddressBookDAO addressBookDAO;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		AddressBookVO adbkVO = new AddressBookVO();
		adbkVO.setAdbkId("test 주소록ID");

		// when
		List<AddressBookUser> userList = addressBookDAO.selectUserList(adbkVO);
		int size = userList.size();

		log.debug("userList={}", userList);
		log.debug("size={}", size);

		for (AddressBookUser user : userList) {
			log.debug("user={}", user);
			log.debug("getNm={}", user.getNm());
		}

		if (size == 0) {
			return;
		}

		// then
		assertEquals(userList.get(0).getAdbkId(), adbkVO.getAdbkId());
	}

}