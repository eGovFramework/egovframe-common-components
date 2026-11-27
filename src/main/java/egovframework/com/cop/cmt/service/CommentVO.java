package egovframework.com.cop.cmt.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 댓글관리 서비스를 위한 VO 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.29  한성곤          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class CommentVO extends Comment {
    /** 정렬순서(DESC,ASC) */
    private long sortOrdr = 0L;

    /** 현재페이지 */
    private int subPageIndex = 1;

    /** 페이지개수 */
    private int subPageUnit = 5;

    /** 페이지사이즈 */
    private int subPageSize = 5;

    /** 첫페이지 인덱스 */
    private int subFirstIndex = 1;

    /** 마지막페이지 인덱스 */
    private int subLastIndex = 1;

    /** 페이지당 레코드 개수 */
    private int subRecordCountPerPage = 5;

    /** 레코드 번호 */
    private int subRowNo = 0;

    /** 호출 TYPE (head or body) */
    private String type = "";

    /** 수정 처리 여부 */
    private boolean isModified = false;
}
