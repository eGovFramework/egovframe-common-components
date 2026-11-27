package egovframework.com.sts.com;

import lombok.Getter;
import lombok.Setter;

/**
 * 통계 결과 VO 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.19  박지욱          최초 생성
 *  2011.06.30  이기하          패키지 분리(sts -> sts.com)
 *  2026.05.27  기여자          Lombok @Getter/@Setter 적용
 *
 *  </pre>
 */
@Getter
@Setter
public class StatsVO {

	/** 결과통계수 */
	private int statsCo;
	/** 결과일자 */
	private String statsDate;
	/** 최대통계수 */
	private int maxStatsCo;
	/** 최소통계수 */
	private int minStatsCo;
	/** 생성글수 */
	private int creatCo;
	/** 총조회수 */
	private int totInqireCo;
	/** 평균조회수 */
	private float avrgInqireCo;
	/** 최대조회게시물ID */
	private String mxmmInqireBbsId;
	/** 최대조회게시물제목 */
	private String mxmmInqireBbsNm;
	/** 최소조회게시물ID */
	private String mummInqireBbsId;
	/** 최소조회게시물제목 */
	private String mummInqireBbsNm;
	/** 최고게시자ID */
	private String topNtcepersonId;
	/** 최고게시글수 */
	private int topNtcepersonCo;
	/** 접속프로그램메소드 */
	private String conectMethod;
	/** 수정글수 */
	private int updtCo;
	/** 조회글수 */
	private int inqireCo;
	/** 삭제글수 */
	private int deleteCo;
	/** 출력횟수 */
	private int outptCo;
	/** 에러횟수 */
	private int errorCo;
	/** 시작일자 */
	private String fromDate;
	/** 종료일자 */
	private String toDate;
	/** 기간구분 */
	private String pdKind;
	/** 통계구분 */
	private String statsKind;
	/** 세부통계구분 */
	private String detailStatsKind;
	/** 탭구분 */
	private String tabKind;
	/** 그래프길이 */
	private float maxUnit;
}
