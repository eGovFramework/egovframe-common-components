package egovframework.com.cop.adb.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.adb.service.AddressBook;
import egovframework.com.cop.adb.service.AddressBookUser;
import egovframework.com.cop.adb.service.AddressBookUserVO;
import egovframework.com.cop.adb.service.AddressBookVO;

/**
 * 주소록을 관리하는 Mapper 인터페이스
 * @author 공통 컴포넌트 개발팀 윤성록
 * @since 2009.09.25
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일          수정자         수정내용
 *   -------        -------     -------------------
 *    2009.9.25.    윤성록       최초 생성
 *    2016.12.13    최두영       클래스명 변경
 *    2026.05.28    dasomel      @EgovMapper 인터페이스 방식으로 전환
 * </pre>
 */
@EgovMapper("AdressBookDAO")
public interface AddressBookMapper {

    /**
     * 주어진 조건에 따른 주소록목록을 불러온다.
     *
     * @param adbkVO
     * @return
     */
    List<AddressBookVO> selectAdressBookList(AddressBookVO adbkVO);

    /**
     * 주어진 조건에 따라 주소록에 추가할 사용자목록을 불러온다.
     *
     * @param adbkUserVO
     * @return
     */
    List<AddressBookUserVO> selectManList(AddressBookUserVO adbkUserVO);

    /**
     * 주어진 조건에 따라 주소록에 추가할 명함목록을 불러온다.
     *
     * @param adbkUserVO
     * @return
     */
    List<AddressBookUserVO> selectCardList(AddressBookUserVO adbkUserVO);

    /**
     * 주어진 조건에 따라 주소록에 기등록된 구성원의 목록을 불러온다.
     *
     * @param adbkVO
     * @return
     */
    List<AddressBookUser> selectUserList(AddressBookVO adbkVO);

    /**
     * 주어진 조건에 맞는 주소록을 불러온다.
     *
     * @param adbkVO
     * @return
     */
    AddressBookVO selectAdressBook(AddressBookVO adbkVO);

    /**
     * 주소록 정보를 등록한다.
     *
     * @param addressBook
     */
    void insertAdressBook(AddressBook addressBook);

    /**
     * 주소록을 구성하는 구성원을 등록한다.
     *
     * @param addressBookUser
     */
    void insertAdressBookUser(AddressBookUser addressBookUser);

    /**
     * 주소록 정보를 수정한다.
     *
     * @param addressBook
     */
    void updateAdressBook(AddressBook addressBook);

    /**
     * 주소록 구성원을 삭제한다.
     *
     * @param adbkUser
     */
    void deleteAdressBookUser(AddressBookUser adbkUser);

    /**
     * 주소록 목록에 대한 전체 건수를 조회한다.
     *
     * @param adbkVO
     * @return
     */
    int selectAdressBookListCnt(AddressBookVO adbkVO);

    /**
     * 사용자 목록에 대한 전체 건수를 조회한다.
     *
     * @param adbkUserVO
     * @return
     */
    int selectManListCnt(AddressBookUserVO adbkUserVO);

    /**
     * 명함 목록에 대한 전체 건수를 조회한다.
     *
     * @param adbkUserVO
     * @return
     */
    int selectCardListCnt(AddressBookUserVO adbkUserVO);

    /**
     * 주소록을 구성할 사용자의 정보를 조회한다.
     *
     * @param id
     * @return
     */
    AddressBookUser selectManUser(String id);

    /**
     * 주소록을 구성할 명함의 정보를 조회한다.
     *
     * @param id
     * @return
     */
    AddressBookUser selectCardUser(String id);
}
