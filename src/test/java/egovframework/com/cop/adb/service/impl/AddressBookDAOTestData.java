package egovframework.com.cop.adb.service.impl;

import java.time.LocalDateTime;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookUser;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressBookDAOTestData {

	private final AddressBookDAO addressBookDAO;

	@Resource(name = "egovAdbkIdGnrService")
	private EgovIdGnrService egovAdbkIdGnrService;

	@Resource(name = "egovAdbkUserIdGnrService")
	private EgovIdGnrService egovAdbkUserIdGnrService;

	public AddressBook insertAdressBookTestData() {
		// given
		AddressBook addressBook = new AddressBook();

		try {
			addressBook.setAdbkId(egovAdbkIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

		addressBook.setUseAt("Y");

		LocalDateTime now = LocalDateTime.now();
		String test = "test 이백행 " + now + " ";

		addressBook.setAdbkNm(test + "주소록명");

		// when
		int result = addressBookDAO.insertAdressBook(addressBook);

		log.debug("result={}", result);

		// then

		return addressBook;
	}

	public AddressBookUser insertAdressBookUserTestData(String adbkId) {
		// given
		AddressBookUser addressBookUser = new AddressBookUser();

		try {
			addressBookUser.setAdbkUserId(egovAdbkUserIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

		addressBookUser.setAdbkId(adbkId);

		// when
		int result = addressBookDAO.insertAdressBookUser(addressBookUser);

		log.debug("result={}", result);

		// then

		return addressBookUser;
	}

}