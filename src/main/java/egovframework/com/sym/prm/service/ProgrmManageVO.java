package egovframework.com.sym.prm.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 프로그램목록 처리를 위한 VO 클래스르를 정의한다
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
 *   2024.10.29  권태성          필수값 BindingResult 검증을 위한 @EgovNullCheck 추가
 *   2025.07.21  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FormalParameterNamingConventions(변수명에 밑줄 사용)
 *   2026.05.27  기여자          Lombok @Getter/@Setter 적용으로 보일러플레이트 코드 제거
 *
 *      </pre>
 */
public class ProgrmManageVO {

	/** 프로그램파일명 */
	@Getter
	@Setter
	@EgovNullCheck
	@Size(max=50)
	private String progrmFileNm;

	/** 프로그램저장경로 */
	@Getter
	@Setter
	@EgovNullCheck
	@Size(max=100)
	private String progrmStrePath;

	/** 프로그램한글명 */
	@Getter
	@Setter
	@EgovNullCheck
	@Size(max=50)
	private String progrmKoreanNm;

	/** URL */
	@Getter
	@Setter
	@EgovNullCheck
	@Size(max=100)
	private String URL;

	/** 프로그램설명 */
	@Getter
	@Setter
	@EgovNullCheck
	@Size(max=100)
	private String progrmDc;

}
