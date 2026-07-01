package egovframework.com.uss.olp.cns.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovEmailCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 상담내용을 처리하는 VO 클래스
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
public class CnsltManageVO extends CnsltManageDefaultVO {

    private static final long serialVersionUID = 1L;

    /** 상담 ID */
    private String cnsltId;

    /** 상담제목 */
    @EgovNullCheck
    @Size(max=250)
    private String cnsltSj;

    /** 상담내용 */
    @EgovNullCheck
    @Size(max=2500)
    private String cnsltCn;

    /** 공개여부 */
    private String othbcAt;

    /** 작성비밀번호 */
    @EgovNullCheck
    @Size(max=20)
    private String writngPassword;

    /** 지역번호 */
    @EgovNullCheck
    @Size(max=4)
    @Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
    private String areaNo;

    /** 중간전화번호 */
    @EgovNullCheck
    @Size(max=4)
    @Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
    private String middleTelno;

    /** 끝전화번호 */
    @EgovNullCheck
    @Size(max=4)
    @Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
    private String endTelno;

    /** 처음휴대폰번호 */
    @Size(max=4)
    @Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
    private String firstMoblphonNo;

    /** 중간휴대폰번호 */
    @Size(max=4)
    @Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
    private String middleMbtlnum;

    /** 끝휴대폰번호 */
    @Size(max=4)
    @Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
    private String endMbtlnum;

    /** 이메일 주소 */
    @EgovEmailCheck
    @Size(max=50)
    private String emailAdres;

    /** 이메일 답변여부 */
    private String emailAnswerAt;

    /** 첨부파일 ID */
    private String atchFileId;

    /** 작성자 명 */
    @EgovNullCheck
    @Size(max=20)
    private String wrterNm;

    /** 작성일자 */
    private String writngDe;

    /** 조회횟수 */
    private String inqireCo;

    /** 질의응답처리상태코드 */
    private String qnaProcessSttusCode;

    /** 질의응답처리상태코드명 */
    private String qnaProcessSttusCodeNm;

    /** 조치내용 */
    @EgovNullCheck
    @Size(max=2500)
    private String managtCn;

    /** 조치일자 */
    private String managtDe;

    /** 작성비밀번호 확인여부 */
    private String passwordConfirmAt;

    /** 답변자명 */
    private String emplyrNm;

    /** 사무실전화번호 */
    private String offmTelno;

    /** 답변자 EMAIL 주소 */
    private String aemailAdres;

    /** 부서명 */
    private String orgnztNm;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

}
