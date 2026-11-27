package egovframework.com.uss.cmt.service;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 출퇴근관리 VO 클래스
 *
 * @author 표준프레임워크센터 개발팀
 * @since 2014.12.20
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.12.20  개발팀          최초 생성
 *   2025.08.01  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FormalParameterNamingConventions(변수명에 밑줄 사용)
 *
 *      </pre>
 */
@Getter
@Setter
public class CmtManageVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 기본 생성자.
	 */
	public CmtManageVO() {
		// constructor
	}

	/** 댓글 관리 목록 */
	private List<CmtManageVO> cmtManageList;

	/** 사용자 이름 문자열 */
	private String userNmString;

	/** 사용자 식별자 */
	private String usid;

	/** 작업 시작 상태 */
	private String wrkStartStatus;

	/**
	 * @param workStartStatus 설정할 작업 시작 상태
	 */
	public void setWrkStartStatus(String workStartStatus) {
		this.wrkStartStatus = workStartStatus;
	}

	/** 작업 종료 상태 */
	private String wrkEndStatus;

	/**
	 * @param workEndStatus 설정할 작업 종료 상태
	 */
	public void setWrkEndStatus(String workEndStatus) {
		this.wrkEndStatus = workEndStatus;
	}

	/** 날짜 */
	private String date;

	/** 작업 시간 */
	private String wrkHours;

	/**
	 * @param workHours 설정할 작업 시간
	 */
	public void setWrkHours(String workHours) {
		this.wrkHours = workHours;
	}

	/** 작업 시간 ID */
	private String wrktmId;

	/** 작업 시작 시간 */
	private String wrkStartTime;

	/** 작업 종료 시간 */
	private String wrkEndTime;

	/** 초과 작업 시간 */
	private String ovtmwrkHours;

	/** 비고 */
	private String rm;

	/** 직원 ID */
	private String emplyrId;

	/** 조직 ID */
	private String orgnztId;

	/** 작업 날짜 */
	private String wrktDt;

}
