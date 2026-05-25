package egovframework.com.uss.sam.cpy.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 저작권보호정책내용을 처리하는 VO 클래스
 *
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자       수정내용
 *  -----------    --------    ---------------------------
 *   2009.04.01     박정규       최초 생성
 *
 *      </pre>
 */
@Getter
@Setter
public class CpyrhtPrtcPolicyVO extends CpyrhtPrtcPolicyDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 저작권 ID */
	private String cpyrhtId;

	/** 저작권보호정책내용 */
	@EgovNullCheck
	@Size(max=2500)
	private String cpyrhtPrtcPolicyCn;

	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최종수정시점 */
	private String lastUpdusrPnttm;

	/** 최종수정자ID */
	private String lastUpdusrId;

}
