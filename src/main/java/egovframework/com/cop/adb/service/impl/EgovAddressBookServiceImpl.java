package egovframework.com.cop.adb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.cop.adb.service.AddressBookVO;
import egovframework.com.cop.adb.service.EgovAddressBookService;
import jakarta.annotation.Resource;

/**
 * 주소록정보를 관리하기 위한 서비스 구현  클래스
 * @author 공통컴포넌트팀 윤성록
 * @since 2009.09.25
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.9.25  윤성록          최초 생성
 *   2016.12.13 최두영          클래스명 변경
 *   2026.08.31  이백행          [2026년 컨트리뷰션] 불필요한 예외 제거
 *
 * </pre>
 */
@Service("EgovAdressBookService")
public class EgovAddressBookServiceImpl extends EgovAbstractServiceImpl implements EgovAddressBookService{


    @Resource(name = "AdressBookDAO")
    private AddressBookDAO adbkDAO;

    @Resource(name = "egovAdbkIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "egovAdbkUserIdGnrService")
    private EgovIdGnrService idgenService2;

    /**
     * 주소록 목록을 조회한다.
     * @param AddressBookVO
     * @return  Map<String, Object>
     */
    @Override
	public Map<String, Object> selectAdressBookList(AddressBookVO adbkVO) {

        List<AddressBookVO> result = adbkDAO.selectAdressBookList(adbkVO);

        int cnt = adbkDAO.selectAdressBookListCnt(adbkVO);

        Map<String, Object> map = new HashMap<>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 주소록 정보를 조회한다.
     * @param AddressBookVO
     * @return  AdressBookVO
     */
    @Override
	public AddressBookVO selectAdressBook(AddressBookVO addressBookVO) {

        AddressBookVO adbkVO = adbkDAO.selectAdressBook(addressBookVO);

        if(adbkVO != null) {
        	adbkVO.setAdbkMan(adbkDAO.selectUserList(adbkVO));
        }

        return  adbkVO;
    }

    /**
     * 주소록 정보를 삭제한다.
     * @param AddressBook
     * @return
     */
    @Override
	public void deleteAdressBook(AddressBook addressBook) {
        adbkDAO.updateAdressBook(addressBook);
    }

    /**
     * 사용자 목록을 조회한다.
     * @param AddressBookUserVO
     * @return Map<String, Object>
     */
    @Override
	public Map<String, Object> selectManList(AddressBookUserVO addressBookUserVO) {

        List<AddressBookUserVO> result = adbkDAO.selectManList(addressBookUserVO);
        int cnt = adbkDAO.selectManListCnt(addressBookUserVO);

        Map<String, Object> map = new HashMap<>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 명함 목록을 조회한다.
     * @param AddressBookUserVO
     * @return Map<String, Object>
     */
    @Override
	public Map<String, Object> selectCardList(AddressBookUserVO addressBookUserVO) {

        List<AddressBookUserVO> result = adbkDAO.selectCardList(addressBookUserVO);
        int cnt = adbkDAO.selectCardListCnt(addressBookUserVO);

        Map<String, Object> map = new HashMap<>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 주소록 정보를 등록한다.
     * @param AddressBookVO
     * @return M
     */
    @Override
	public void insertAdressBook(AddressBookVO adbkVO) {

        try {
			adbkVO.setAdbkId(idgenService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}
        adbkVO.setUseAt("Y");

        adbkDAO.insertAdressBook(adbkVO);

        for (AddressBookUser element : adbkVO.getAdbkMan()) {
            try {
				element.setAdbkUserId(idgenService2.getNextStringId());
			} catch (FdlException e) {
				throw new BaseRuntimeException(e);
			}
            element.setAdbkId(adbkVO.getAdbkId());
            adbkDAO.insertAdressBookUser(element);
        }
    }

    /**
     * 주소록 정보를 수정한다.
     * @param AddressBookVO
     * @return
     */
    @Override
	public void updateAdressBook(AddressBookVO adbkVO) {

        adbkDAO.updateAdressBook(adbkVO);

        List<AddressBookUser> temp = adbkDAO.selectUserList(adbkVO);


        for (AddressBookUser element : temp) {
            if(element.getEmplyrId() == null) {
				element.setEmplyrId("");
			}

            if(element.getNcrdId() == null){
                element.setNcrdId("");
            }else{
            	element.setNcrdId(element.getNcrdId().trim());
            }
        }

        for (AddressBookUser element : adbkVO.getAdbkMan()) {
            if(element.getEmplyrId() == null) {
				element.setEmplyrId("");
			}

            if(element.getNcrdId() == null){
                element.setNcrdId("");
            }else{
            	element.setNcrdId(element.getNcrdId().trim());
            }
        }


        for (AddressBookUser element : adbkVO.getAdbkMan()) {

            boolean check = false;

            for (AddressBookUser element2 : temp) {
                if(element.getEmplyrId().equals(element2.getEmplyrId())  &&
                        element.getNcrdId().equals(element2.getNcrdId()) ) {
                    check = true;
                    break;
                }
            }
            if(!check){
                try {
					element.setAdbkUserId(idgenService2.getNextStringId());
				} catch (FdlException e) {
					throw new BaseRuntimeException(e);
				}
                element.setAdbkId(adbkVO.getAdbkId());
                adbkDAO.insertAdressBookUser(element);
            }
        }

        for (AddressBookUser element : temp) {

            boolean check = false;

            for (AddressBookUser element2 : adbkVO.getAdbkMan()) {
                if(element.getEmplyrId().equals(element2.getEmplyrId())  &&
                        element.getNcrdId().equals(element2.getNcrdId()) ) {
                    check = true;
                    break;
                }
            }
            if(!check){
                adbkDAO.deleteAdressBookUser(element);
            }
        }
    }

    /**
     * 주소록 구성원 정보를 불러온다.
     * @param String
     * @return
     */
    @Override
	public AddressBookUser selectAdbkUser(String id) {

        AddressBookUser adbkUser = new AddressBookUser();

        if(id.length() > 4 && id.substring(0,4).equals("NCRD") ){
            adbkUser = adbkDAO.selectCardUser(id);
        }else{
            adbkUser = adbkDAO.selectManUser(id);
        }

        return adbkUser;
    }
}
