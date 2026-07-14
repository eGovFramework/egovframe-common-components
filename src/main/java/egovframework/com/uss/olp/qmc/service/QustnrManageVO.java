package egovframework.com.uss.olp.qmc.service;

import java.io.Serializable;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 설문관리 VO Class 구현
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
public class QustnrManageVO implements Serializable {

	private static final long serialVersionUID = 1525075114445382036L;

	/** 설문지ID */
	private String qestnrId =  "";

	/**  설문제목 */
	@EgovNullCheck
	@Size(max=250)
	private String qestnrSj =  "";

	/**  설문목적 */
	@EgovNullCheck
	@Size(max=1000)
	private String qestnrPurps =  "";

	/**  설문작성안내내용 */
	@EgovNullCheck
	@Size(max=2000)
	private String qestnrWritngGuidanceCn =  "";

	/**  설문시작일자 */
	@EgovNullCheck
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="{validation.pattern.date}")
	private String qestnrBeginDe =  "";

	/**  설문종료일자 */
	@EgovNullCheck
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="{validation.pattern.date}")
	private String qestnrEndDe =  "";

	/**  설문대상 */
	@EgovNullCheck
	private String qestnrTrget =  "";

	/**  설문시작일자 */
	private String qestnrTmplatId =  "";

	/**  설문템플릿유형 */
	private String qestnrTmplatTy =  "";

	/**  최초등록시점 */
	private String frstRegisterPnttm =  "";

	/**  최초등록자아이디 */
	private String frstRegisterId =  "";

	/**  최종수정시점 */
	private String lastUpdusrPnttm =  "";

	/**  최종수정자아이디 */
	private String lastUpdusrId =  "";

}
