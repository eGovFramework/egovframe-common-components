package egovframework.com.cop.adb.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.cop.adb.service.AddressBookVO;

import org.springframework.stereotype.Repository;


/**
 * @Class Name : AdressBookDAO.java
 * @Description : 주소록을 관리하는 서비스를 정의하기위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일          수정자         수정내용
 *   -------        -------     -------------------
 *    2009.9.25.    윤성록       최초 생성
 *    2016.12.13    최두영       클래스명 변경
 * @author 공통 컴포넌트 개발팀 윤성록
 * @since 2009. 9. 25.
 * @version
 * @see
 *
 */
@SuppressWarnings("unchecked")
@Repository("AdressBookDAO")
public class AddressBookDAO extends EgovComAbstractDAO{
    
    /**
     * 주어진 조건에 따른 주소록목록을 불러온다.
     * 
     * @param AddressBookVO
     * @return
     * @throws Exception
     */
	public List<AddressBookVO> selectAdressBookList(AddressBookVO adbkVO) throws Exception {
        return (List<AddressBookVO>) list("AdressBookDAO.selectAdressBookList", adbkVO);
    }
    
    /**
     * 주어진 조건에 따라 주소록에 추가할 사용자목록을 불러온다.
     * 
     * @param AddressBookUserVO
     * @return
     * @throws Exception
     */
    public List<AddressBookUserVO> selectManList(AddressBookUserVO adbkUserVO) throws Exception {
        return (List<AddressBookUserVO>) list("AdressBookDAO.selectManList", adbkUserVO);
    }
    
    /**
     * 주어진 조건에 따라 주소록에 추가할 명함목록을 불러온다.
     * 
     * @param AddressBookUserVO
     * @return
     * @throws Exception
     */
    public List<AddressBookUserVO> selectCardList(AddressBookUserVO adbkUserVO) throws Exception {
        return (List<AddressBookUserVO>) list("AdressBookDAO.selectCardList", adbkUserVO);
    }
    
    /**
     * 주어진 조건에 따라 주소록에 기등록된 구성원의 목록을 불러온다.
     * 
     * @param AddressBookVO
     * @return
     * @throws Exception
     */
    public List<AddressBookUser> selectUserList(AddressBookVO adbkVO) throws Exception {
        return (List<AddressBookUser>) list("AdressBookDAO.selectUserList", adbkVO);
    }  

    /**
     * 주어진 조건에 맞는 주소록을 불러온다.
     * 
     * @param AddressBookVO
     * @return
     * @throws Exception
     */
    public AddressBookVO selectAdressBook(AddressBookVO adbkVO) throws Exception {
        return (AddressBookVO)selectOne("AdressBookDAO.selectAdressBook", adbkVO);
    }        
    
    /**
     * 주소록 정보를 등록한다.
     * 
     * @param AddressBook
     * @throws Exception
     */
    public void insertAdressBook(AddressBook addressBook) throws Exception {
        insert("AdressBookDAO.insertAdressBook", addressBook);
    }
    
    /**
     * 주소록을 구성하는 구성원을 등록한다.
     * 
     * @param AddressBookUser
     * @throws Exception
     */
    public void insertAdressBookUser(AddressBookUser addressBookUser) throws Exception {
        insert("AdressBookDAO.insertAdressBookUser", addressBookUser);
    }

    /**
     * 주소록 정보를 수정한다.
     * 
     * @param AddressBook
     * @throws Exception
     */
    public void updateAdressBook(AddressBook addressBook) throws Exception {
        update("AdressBookDAO.updateAdressBook", addressBook);
    }
    
    /**
     * 주소록 구성원을 삭제한다.
     * 
     * @param AddressBookUser
     * @throws Exception
     */
    public void deleteAdressBookUser(AddressBookUser adbkUser) throws Exception {
        delete("AdressBookDAO.deleteAdressBookUser", adbkUser);
    }    
    
    /**
     * 주소록 목록에 대한 전체 건수를 조회한다.
     * 
     * @param AddressBookUser
     * @throws Exception
     */
    public int selectAdressBookListCnt(AddressBookVO adbkVO) throws Exception {
        return (Integer)selectOne("AdressBookDAO.selectAdressBookListCnt", adbkVO);
    }
    
    /**
     * 사용자 목록에 대한 전체 건수를 조회한다.
     * 
     * @param AddressBookUser
     * @throws Exception
     */
    public int selectManListCnt(AddressBookUserVO adbkUserVO) throws Exception {
        return (Integer)selectOne("AdressBookDAO.selectManListCnt", adbkUserVO);
    }
    
    /**
     * 명함 목록에 대한 전체 건수를 조회한다.
     * 
     * @param AddressBookUser
     * @throws Exception
     */
    public int selectCardListCnt(AddressBookUserVO adbkUserVO) throws Exception {
        return (Integer)selectOne("AdressBookDAO.selectCardListCnt", adbkUserVO);
    }
    
    /**
     * 주소록을 구성할 사용자의 정보를 조회한다.
     * 
     * @param AddressBookUser
     * @throws Exception
     */
    public AddressBookUser selectManUser(String id) throws Exception {
        return (AddressBookUser)selectOne("AdressBookDAO.selectManUser", id);
    }
    
    /**
     * 주소록을 구성할 명함의 정보를 조회한다.
     * 
     * @param AddressBookUser
     * @throws Exception
     */
    public AddressBookUser selectCardUser(String id) throws Exception {
        return (AddressBookUser)selectOne("AdressBookDAO.selectCardUser", id);
    }

}

