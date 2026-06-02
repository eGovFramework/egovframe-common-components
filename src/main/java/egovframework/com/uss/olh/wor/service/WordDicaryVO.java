package egovframework.com.uss.olh.wor.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * 용어사전정보 VO 클래스
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
public class WordDicaryVO extends WordDicaryDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 용어ID */
	private String wordId;

	/** 용어명 */
	@EgovNullCheck
	@Size(max=250)
	private String wordNm;

	/** 영문명 */
	@EgovNullCheck
	@Size(max=60)
	@Pattern(regexp="^[a-zA-Z0-9_]*$", message="{validation.pattern.english}")
	private String engNm;

	/** 용어설명 */
	@EgovNullCheck
	@Size(max=2500)
	private String wordDc;

	/** 동의어 */
	@Size(max=100)
	private String synonm;

	/** 등록자명 */
	private String emplyrNm;

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
