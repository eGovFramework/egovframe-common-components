package egovframework.com.uss.olh.qna.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovEmailCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * Q&A를 처리하는 VO 클래스
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
 *   2025.05.25  장동한          Lombok @Getter/@Setter 적용
 *
 * </pre>
 */
@Getter
@Setter
public class QnaVO extends QnaDefaultVO {

	private static final long serialVersionUID = 1L;

	/** QA ID */
	private String qaId;

	/** 질문제목 */
	@EgovNullCheck
	@Size(max=250)
	private String qestnSj;

	/** 질문내용 */
	@EgovNullCheck
	@Size(max=2500)
	private String qestnCn;

	/** 작성비밀번호 */
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

	/** 이메일 주소 */
	@EgovEmailCheck
	@Size(max=50)
	private String emailAdres;

	/** 이메일 답변여부 */
	private String emailAnswerAt;

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

	/** 답변내용 */
	@EgovNullCheck
	@Size(max=2500)
	private String answerCn;

	/** 답변일자 */
	private String answerDe;

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

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
