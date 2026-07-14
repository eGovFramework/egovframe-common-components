package egovframework.com.cop.cmt.service;

import java.io.Serializable;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 댓글관리 서비스 데이터 처리 모델
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
public class Comment implements Serializable {
    /** 댓글번호 */
    private String commentNo = "";

    /** 게시판 ID */
    private String bbsId = "";

    /** 게시물 번호 */
    private long nttId = 0L;

    /** 작성자 ID */
    private String wrterId = "";

    /** 작성자명 */
    private String wrterNm = "";

    /** 패스워드 */
    private String commentPassword = "";

    /** 댓글 내용 */
    @EgovNullCheck
    @Size(max=200)
    private String commentCn = "";

    /** 사용 여부 */
    private String useAt = "";

    /** 최초등록자 아이디 */
    private String frstRegisterId = "";

    /** 최초 등록자명 */
    private String frstRegisterNm = "";

    /** 최초등록시점 */
    private String frstRegisterPnttm = "";

    /** 최종수정자 아이디 */
    private String lastUpdusrId = "";

    /** 최종수정시점 */
    private String lastUpdusrPnttm = "";

    /** 확인 패스워드 */
    private String confirmPassword = "";
}
