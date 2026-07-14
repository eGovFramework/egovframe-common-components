package egovframework.com.uss.sam.cpy.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 저작권보호정책내용을 처리하는 DefaultVO 클래스
 *
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자       수정내용
 *  -----------    --------    ---------------------------
 *   2009.04.01     박정규       최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class CpyrhtPrtcPolicyDefaultVO implements Serializable {

	private static final long serialVersionUID = -1756683013057173109L;

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
