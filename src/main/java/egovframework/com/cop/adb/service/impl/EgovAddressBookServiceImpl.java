package egovframework.com.cop.adb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;

/**
 * 주소록정보를 관리하기 위한 서비스 구현 클래스
 * 
 * @author 공통컴포넌트팀 윤성록
 * @since 2009.09.25
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.09.25  윤성록          최초 생성
 *   2016.12.13  최두영          클래스명 변경
 *   2024.09.05  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *      </pre>
 */
@Service("EgovAdressBookService")
public class EgovAddressBookServiceImpl extends EgovAbstractServiceImpl implements EgovAddressBookService {

	@Resource(name = "AdressBookDAO")
	private AddressBookDAO adbkDAO;

	@Resource(name = "egovAdbkIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name = "egovAdbkUserIdGnrService")
	private EgovIdGnrService idgenService2;

	/**
	 * 주소록 목록을 조회한다.
	 * 
	 * @param AddressBookVO
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectAdressBookList(AddressBookVO adbkVO) {

		List<AddressBookVO> result = adbkDAO.selectAdressBookList(adbkVO);

		int cnt = adbkDAO.selectAdressBookListCnt(adbkVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 주소록 정보를 조회한다.
	 * 
	 * @param AddressBookVO
	 * @return AdressBookVO
	 */
	@Override
	public AddressBookVO selectAdressBook(AddressBookVO addressBookVO) {

		AddressBookVO adbkVO = adbkDAO.selectAdressBook(addressBookVO);

		if (adbkVO != null) {
			adbkVO.setAdbkMan(adbkDAO.selectUserList(adbkVO));
		}

		return adbkVO;
	}

	/**
	 * 주소록 정보를 삭제한다.
	 * 
	 * @param AddressBook
	 * @return
	 */
	@Override
	public void deleteAdressBook(AddressBook addressBook) {
		adbkDAO.updateAdressBook(addressBook);
	}

	/**
	 * 사용자 목록을 조회한다.
	 * 
	 * @param AddressBookUserVO
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectManList(AddressBookUserVO addressBookUserVO) {

		List<AddressBookUserVO> result = adbkDAO.selectManList(addressBookUserVO);
		int cnt = adbkDAO.selectManListCnt(addressBookUserVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 명함 목록을 조회한다.
	 * 
	 * @param AddressBookUserVO
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> selectCardList(AddressBookUserVO addressBookUserVO) {

		List<AddressBookUserVO> result = adbkDAO.selectCardList(addressBookUserVO);
		int cnt = adbkDAO.selectCardListCnt(addressBookUserVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 주소록 정보를 등록한다.
	 * 
	 * @param AddressBookVO
	 * @return M
	 * @throws Exception
	 */
	@Override
	public void insertAdressBook(AddressBookVO adbkVO) throws Exception {

		try {
			adbkVO.setAdbkId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw processException("FdlException: egovAdbkIdGnrService", e);
		}
		adbkVO.setUseAt("Y");

		adbkDAO.insertAdressBook(adbkVO);

		for (int i = 0; i < adbkVO.getAdbkMan().size(); i++) {
			try {
				adbkVO.getAdbkMan().get(i).setAdbkUserId(idgenService2.getNextStringId());
			} catch (FdlException e) {
				throw processException("FdlException: egovAdbkUserIdGnrService", e);
			}
			adbkVO.getAdbkMan().get(i).setAdbkId(adbkVO.getAdbkId());
			adbkDAO.insertAdressBookUser(adbkVO.getAdbkMan().get(i));
		}
	}

	/**
	 * 주소록 정보를 수정한다.
	 * 
	 * @param AddressBookVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateAdressBook(AddressBookVO adbkVO) throws Exception {

		adbkDAO.updateAdressBook(adbkVO);

		List<AddressBookUser> temp = adbkDAO.selectUserList(adbkVO);

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getEmplyrId() == null)
				temp.get(i).setEmplyrId("");

			if (temp.get(i).getNcrdId() == null) {
				temp.get(i).setNcrdId("");
			} else {
				temp.get(i).setNcrdId(temp.get(i).getNcrdId().trim());
			}
		}

		for (int i = 0; i < adbkVO.getAdbkMan().size(); i++) {
			if (adbkVO.getAdbkMan().get(i).getEmplyrId() == null)
				adbkVO.getAdbkMan().get(i).setEmplyrId("");

			if (adbkVO.getAdbkMan().get(i).getNcrdId() == null) {
				adbkVO.getAdbkMan().get(i).setNcrdId("");
			} else {
				adbkVO.getAdbkMan().get(i).setNcrdId(adbkVO.getAdbkMan().get(i).getNcrdId().trim());
			}
		}

		for (int i = 0; i < adbkVO.getAdbkMan().size(); i++) {

			boolean check = false;

			for (int j = 0; j < temp.size(); j++) {
				if (adbkVO.getAdbkMan().get(i).getEmplyrId().equals(temp.get(j).getEmplyrId())
						&& adbkVO.getAdbkMan().get(i).getNcrdId().equals(temp.get(j).getNcrdId())) {
					check = true;
					break;
				}
			}
			if (!check) {
				try {
					adbkVO.getAdbkMan().get(i).setAdbkUserId(idgenService2.getNextStringId());
				} catch (FdlException e) {
					throw processException("FdlException: egovAdbkUserIdGnrService", e);
				}
				adbkVO.getAdbkMan().get(i).setAdbkId(adbkVO.getAdbkId());
				adbkDAO.insertAdressBookUser(adbkVO.getAdbkMan().get(i));
			}
		}

		for (int i = 0; i < temp.size(); i++) {

			boolean check = false;

			for (int j = 0; j < adbkVO.getAdbkMan().size(); j++) {
				if (temp.get(i).getEmplyrId().equals(adbkVO.getAdbkMan().get(j).getEmplyrId())
						&& temp.get(i).getNcrdId().equals(adbkVO.getAdbkMan().get(j).getNcrdId())) {
					check = true;
					break;
				}
			}
			if (!check) {
				adbkDAO.deleteAdressBookUser(temp.get(i));
			}
		}
	}

	/**
	 * 주소록 구성원 정보를 불러온다.
	 * 
	 * @param String
	 * @return
	 */
	@Override
	public AddressBookUser selectAdbkUser(String id) {

		AddressBookUser adbkUser = new AddressBookUser();

		if (id.length() > 4 && id.substring(0, 4).equals("NCRD")) {
			adbkUser = adbkDAO.selectCardUser(id);
		} else {
			adbkUser = adbkDAO.selectManUser(id);
		}

		return adbkUser;
	}
}
