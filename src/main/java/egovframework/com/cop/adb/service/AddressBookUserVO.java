package egovframework.com.cop.adb.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 주소록구성원 관리를 위한 VO 모델 클래스
 * @author 공통컴포넌트개발팀 윤성록
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
@Getter
@Setter
@SuppressWarnings("serial")
public class AddressBookUserVO extends AddressBookUser {

    /** 사용자 아이디 */
    private String userId = "";

    /** 사용자 명 */
    private String userNm = "";

    /** 사용자 이메일 */
    private String userEmail = "";

    /** 사용자 접전화 처음 */
    private String areaNo = "";

    /** 사용자 집전화 가운데 */
    private String homemiddleTelno = "";

    /** 사용자 집전화 마지막 */
    private String homeendTelno = "";

    /** 검색시작일 */
    private String searchBgnDe = "";

    /** 검색조건 */
    private String searchCnd = "";

    /** 검색종료일 */
    private String searchEndDe = "";

    /** 검색단어 */
    private String searchWrd = "";

    /** 정렬순서(DESC,ASC) */
    private long sortOrdr = 0L;

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지개수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** 첫페이지 인덱스 */
    private int firstIndex = 1;

    /** 마지막페이지 인덱스 */
    private int lastIndex = 1;

    /** 페이지당 레코드 개수 */
    private int recordCountPerPage = 10;

    /** 레코드 번호 */
    private int rowNo = 0;

    /** 최초 등록자명 */
    private String frstRegisterNm = "";

    /** 최종 수정자명 */
    private String lastUpdusrNm = "";

}
