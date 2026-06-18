package egovframework.com.uss.cmt.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 출퇴근관리 VO 클래스
 * @author 표준프레임워크센터 개발팀
 * @since 2014.12.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자       수정내용
 *  -----------    --------    ---------------------------
 *   2014.12.20     개발팀       최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class CmtDefaultVO implements Serializable {

	private static final long serialVersionUID = -2782974258506027986L;

	/** 검색조건 */
    private String searchCondition = "";

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
