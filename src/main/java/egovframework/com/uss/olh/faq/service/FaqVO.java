package egovframework.com.uss.olh.faq.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * FAQ를 처리하는 VO 클래스
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
public class FaqVO extends FaqDefaultVO {

	private static final long serialVersionUID = 1L;

	/** FAQ ID */
	private String faqId;

	/** 질문제목 */
	@EgovNullCheck
	@Size(max=100)
	private String qestnSj;

	/** 질문내용 */
	@EgovNullCheck
	@Size(max=2500)
	private String qestnCn;

	/** 답변내용 */
	@EgovNullCheck
	@Size(max=2500)
	private String answerCn;

	/** 조회횟수 */
	private String inqireCo;

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

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
