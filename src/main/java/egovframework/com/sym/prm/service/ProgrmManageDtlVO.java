package egovframework.com.sym.prm.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 프로그램변경관리 처리를 위한 VO 클래스르를 정의한다
 *
 * @author 개발환경 개발팀 이용
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이용           최초 생성
 *   2025.07.19  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FormalParameterNamingConventions(변수명에 밑줄 사용)
 *   2026.05.27  기여자          Lombok @Getter/@Setter 적용으로 보일러플레이트 코드 제거
 *
 *      </pre>
 */
public class ProgrmManageDtlVO {

	/** 프로그램파일명 */
	@Getter
	@Setter
	@EgovNullCheck
	@Size(max=50)
	private String progrmFileNm;
	/** 요청번호 */
	@Getter
	@Setter
	@NotNull
	private int rqesterNo;
	/** 요청제목 */
	@Getter
	@Setter
	@EgovNullCheck
	@Size(max=50)
	private String rqesterSj;
	/** 요청자ID */
	@Getter
	@Setter
	@EgovNullCheck
	@Size(max=20)
	private String rqesterPersonId;
	/** 요청일자 */
	@Getter
	@Setter
	@EgovNullCheck
	private String rqesterDe;
	/** 변경요청내용 */
	@Getter
	@Setter
	@Size(max=1000)
	private String changerqesterCn;
	/** 처리자ID */
	@Getter
	@Setter
	@Size(max=20)
	private String opetrId;
	/** 처리상태코드 */
	@Getter
	@Setter
	private String processSttus;
	/** 처리일자 */
	@Getter
	@Setter
	@EgovNullCheck
	private String processDe;
	/** 요청처리내용 */
	@Getter
	@Setter
	@Size(max=1000)
	private String rqesterProcessCn;

	/** 요청시작일자 */
	@Getter
	@Setter
	private String rqesterDeBegin;
	/** 요청종료일자 */
	@Getter
	@Setter
	private String rqesterDeEnd;

	/** 프로그램파일명 */
	@Getter
	@Setter
	private String tmpProgrmNm;
	/** 요청번호 */
	@Getter
	@Setter
	private int tmpRqesterNo;
	/** tmp_Email */
	@Getter
	@Setter
	private String tmpEmail;

}
