package egovframework.com.uss.olh.hpc.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

/**
 * 도움말을 처리하는 VO 클래스
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
public class HpcmVO extends HpcmDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 도움말 ID */
	private String hpcmId;

	/** 도움말구분코드 */
	@EgovNullCheck
	private String hpcmSeCode;

	/** 도움말구분코드명 */
	private String hpcmSeCodeNm;

	/** 도움말정의 */
	@EgovNullCheck
	@Size(max=1000)
	private String hpcmDf;

	/** 도움말 설명 */
	@EgovNullCheck
	@Size(max=2500)
	private String hpcmDc;

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
