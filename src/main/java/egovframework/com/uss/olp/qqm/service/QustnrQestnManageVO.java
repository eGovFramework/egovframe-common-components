package egovframework.com.uss.olp.qqm.service;

import java.io.Serializable;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 설문문항 VO Class 구현
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
public class QustnrQestnManageVO implements Serializable {

	private static final long serialVersionUID = -1010670861596333788L;

	/** 설문제목 */
	private String qestnrSj = "";

	/** 설문문항 ID */
	private String qestnrQesitmId = "";

	/** 설문지 ID */
	private String qestnrId = "";

	/** 질문순번 */
	@EgovNullCheck
	@Size(max=10)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String qestnSn = "";

	/** 질문유형코드 */
	@EgovNullCheck
	private String qestnTyCode = "";

	/** 질문내용 */
	@EgovNullCheck
	@Size(max=2500)
	private String qestnCn = "";

	/** 초대선택건수 */
	private String mxmmChoiseCo = "";

	/** 템플릿 ID */
	@EgovNullCheck
	private String qestnrTmplatId = "";

	/** 최초등록자아이디 */
	private String frstRegisterPnttm = "";

	/** 최초등록시점  */
	private String frstRegisterId = "";

	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";

	/** 최종수정시점아이디  */
	private String lastUpdusrId = "";

	/** 검색모드설정  */
	private String searchMode = "";

}
