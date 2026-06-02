package egovframework.com.uss.ion.nws.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * 뉴스정보를 처리하는 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *   2025.05.20  기여자          Lombok @Getter/@Setter 적용으로 보일러플레이트 제거
 *
 * </pre>
 */
@Getter
@Setter
public class NewsVO extends ComDefaultVO {

    private static final long serialVersionUID = 1L;

    /** 뉴스 ID */
    private String newsId;

    /** 뉴스제목 */
    @EgovNullCheck
    @Size(max=100)
    private String newsSj;

    /** 뉴스내용 */
    @EgovNullCheck
    @Size(max=1000)
    private String newsCn;

    /** 뉴스출처 */
    private String newsOrigin;

    /** 게시일자 */
    private String ntceDe;

    /** 첨부파일ID */
    private String atchFileId;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

}
