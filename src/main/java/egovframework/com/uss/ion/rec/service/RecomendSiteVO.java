package egovframework.com.uss.ion.rec.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * 추천사이트정보를 처리하는 VO 클래스
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
 *
 * </pre>
 */
@Getter
@Setter
public class RecomendSiteVO extends ComDefaultVO {

    private static final long serialVersionUID = 1L;

    /** 추천사이트 ID */
    private String recomendSiteId;

    /** 추천사이트 URL */
    @EgovNullCheck
    @Size(max=250)
    private String recomendSiteUrl;

    /** 추천사이트명 */
    @EgovNullCheck
    @Size(max=100)
    private String recomendSiteNm;

    /** 추천사이트설명 */
    @EgovNullCheck
    @Size(max=1000)
    private String recomendSiteDc;

    /** 추천사유내용 */
    @EgovNullCheck
    @Size(max=1000)
    private String recomendResnCn;

    /** 추천승인여부 */
    private String recomendConfmAt;

    /** 승인일자 */
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="{validation.pattern.date}")
    private String confmDe;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

}
