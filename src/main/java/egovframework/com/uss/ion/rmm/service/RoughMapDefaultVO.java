package egovframework.com.uss.ion.rmm.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 약도관리에 대한 VO 클래스를 정의한다.
 *
 * 상세내용
 * - 약도정보 조회를 위해 필요한 정보를 관리한다.
 *
 * @author 옥찬우
 * @since 2014.08.27
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일			수정자		수정내용
 *  -----------		------		---------
 *   2014.08.27		옥찬우		최초 생성
 *
 * </pre>
 */

@Getter
@Setter
public class RoughMapDefaultVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 첫페이지 인덱스 */
    private int firstIndex = 1;

    /** * 마지막페이지 인덱스 */
    private int lastIndex = 1;

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지 사이즈 */
    private int pageSize = 10;

    /** 페이지 개수 */
    private int pageUnit = 10;

    /** 페이지당 레코드 개수 */
    private int recordCountPerPage = 10;

    /** 검색조건 */
    private String searchCondition = "";

    /** 검색단어 */
    private String searchKeyword = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

}
