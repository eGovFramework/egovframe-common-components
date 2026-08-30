package egovframework.com.cop.adb.service;

import java.util.Map;

/**
 * 주소록정보를 관리하기 위한 서비스 인터페이스 클래스
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
 * </pre>
 */
public interface EgovAddressBookService {
    
  
    /**
     * 주소록 목록을 조회한다.
     * @param AddressBookVO
     * @return  Map<String, Object>
     */
    Map<String, Object> selectAdressBookList(AddressBookVO addressBookVO);
    
    /**
     * 주소록 정보를 조회한다.
     * @param AddressBookVO
     * @return  AdressBookVO
     */
    AddressBookVO selectAdressBook(AddressBookVO addressBookVO);
    
    /**
     * 주소록 정보를 삭제한다.
     * @param AddressBook
     * @return 
     */
    int deleteAdressBook(AddressBook addressBook);
    
    /**
     * 사용자 목록을 조회한다.
     * @param AddressBookUserVO
     * @return Map<String, Object>
     */
    Map<String, Object> selectManList(AddressBookUserVO addressBookUserVO);
    
    /**
     * 명함 목록을 조회한다.
     * @param AddressBookUserVO
     * @return Map<String, Object>
     */
    Map<String, Object> selectCardList(AddressBookUserVO addressBookUserVO);
    
    /**
     * 주소록 정보를 등록한다.
     * 
     * @param AddressBook
     */
    int insertAdressBook(AddressBookVO adbkVO);   
          
    /**
     * 주소록 정보를 수정한다.
     * @param AddressBookVO
     * @return 
     */
    int updateAdressBook(AddressBookVO addressBookVO);
    
    /**
     * 주소록 구성원 정보를 불러온다.
     * @param String
     * @return 
     */
    AddressBookUser selectAdbkUser(String id);

}