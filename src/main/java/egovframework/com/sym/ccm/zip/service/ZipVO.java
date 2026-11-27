package egovframework.com.sym.ccm.zip.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 우편번호 VO 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.11.21  이기하          도로명주소 추가(searchList, searchCondition, searchCondition2)
 *
 * </pre>
 */
@Getter
@Setter
public class ZipVO extends Zip implements Serializable {

	private static final long serialVersionUID = -1884618301732456348L;

	/** 일반주소와 도로명주소 구분 */
    private String searchList = "";

    /** 일반주소 검색조건 */
    private String searchCondition = "";

    /** 도로명주소 검색조건 */
    private String searchCondition2 = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지개수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

}
