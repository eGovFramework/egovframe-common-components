package egovframework.com.uss.olp.qrm.service;

import java.io.Serializable;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 설문응답자관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class QustnrRespondManageVO implements Serializable {

	private static final long serialVersionUID = -4378392561239344699L;

	/** 설문지ID */
	private String qestnrId = "";

	/** 설문응답자아이디 */
	private String qestnrRespondId = "";

	/** 성별코드 */
	@EgovNullCheck
	private String sexdstnCode = "";

	/** 직업유형코드 */
	@EgovNullCheck
	private String occpTyCode = "";

	/** 응답자명 */
	@EgovNullCheck
	@Size(max=50)
	private String respondNm = "";

	/** 생년월일 */
	private String brth = "";

	/** 첫번째전화번호 */
	private String areaNo = "";

	/** 두번째전화번호 */
	private String middleTelno = "";

	/** 마지막전화번호 */
	private String endTelno = "";

	/** 최초등록시점 */
	private String frstRegisterPnttm = "";

	/** 최초등록자ID */
	private String frstRegisterId = "";

	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";

	/** 최종수정ID */
	private String lastUpdusrId = "";

	/** 설문템플릿ID */
	private String qestnrTmplatId = "";

}
