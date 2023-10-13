package egovframework.com.uss.cmt.service;

import java.io.Serializable;
import java.util.List;

/**
 * 출퇴근관리 VO 클래스
 * @author 표준프레임워크센터 개발팀
 * @since 2014.12.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자       수정내용
 *  -----------    --------    ---------------------------
 *   2014.12.20     개발팀       최초 생성
 *
 * </pre>
 */
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

	/**
	 * @return 댓글 관리 목록을 반환합니다.
	 */
	public List<CmtManageVO> getCmtManageList() {
		return cmtManageList;
	}

	/**
	 * @param cmtManageList 설정할 댓글 관리 목록
	 */
	public void setCmtManageList(List<CmtManageVO> cmtManageList) {
		this.cmtManageList = cmtManageList;
	}

	/** 사용자 이름 문자열 */
	private String userNmString;

	/**
	 * @return 사용자 이름 문자열을 반환합니다.
	 */
	public String getUserNmString() {
		return userNmString;
	}

	/**
	 * @param userNmString 설정할 사용자 이름 문자열
	 */
	public void setUserNmString(String userNmString) {
		this.userNmString = userNmString;
	}

	/** 사용자 식별자 */
	private String usid;

	/**
	 * @return 사용자 식별자를 반환합니다.
	 */
	public String getUsid() {
		return usid;
	}

	/**
	 * @param usid 설정할 사용자 식별자
	 */
	public void setUsid(String usid) {
		this.usid = usid;
	}

	/** 작업 시작 상태 */
	private String wrkStartStatus;

	/**
	 * @return 작업 시작 상태를 반환합니다.
	 */
	public String getWrkStartStatus() {
		return wrkStartStatus;
	}

	/**
	 * @param workStartStatus 설정할 작업 시작 상태
	 */
	public void setWrkStartStatus(String workStartStatus) {
		this.wrkStartStatus = workStartStatus;
	}

	/** 작업 종료 상태 */
	private String wrkEndStatus;

	/**
	 * @return 작업 종료 상태를 반환합니다.
	 */
	public String getWrkEndStatus() {
		return wrkEndStatus;
	}

	/**
	 * @param workEndStatus 설정할 작업 종료 상태
	 */
	public void setWrkEndStatus(String workEndStatus) {
		this.wrkEndStatus = workEndStatus;
	}

	/** 날짜 */
	private String date;

	/**
	 * @return 날짜를 반환합니다.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date 설정할 날짜
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/** 작업 시간 */
	private String wrkHours;

	/**
	 * @return 작업 시간을 반환합니다.
	 */
	public String getWrkHours() {
		return wrkHours;
	}

	/**
	 * @param workHours 설정할 작업 시간
	 */
	public void setWrkHours(String workHours) {
		this.wrkHours = workHours;
	}

	/** 작업 시간 ID */
	private String wrktmId;

	/**
	 * @return 작업 시간 ID를 반환합니다.
	 */
	public String getWrktmId() {
		return wrktmId;
	}

	/**
	 * @param wrktmId 설정할 작업 시간 ID
	 */
	public void setWrktmId(String wrktmId) {
		this.wrktmId = wrktmId;
	}

	/** 작업 시작 시간 */
	private String wrkStartTime;

	/**
	 * @return 작업 시작 시간을 반환합니다.
	 */
	public String getWrkStartTime() {
		return wrkStartTime;
	}

	/**
	 * @param wrkStartTime 설정할 작업 시작 시간
	 */
	public void setWrkStartTime(String wrkStartTime) {
		this.wrkStartTime = wrkStartTime;
	}

	/** 작업 종료 시간 */
	private String wrkEndTime;

	/**
	 * @return 작업 종료 시간을 반환합니다.
	 */
	public String getWrkEndTime() {
		return wrkEndTime;
	}

	/**
	 * @param wrkEndTime 설정할 작업 종료 시간
	 */
	public void setWrkEndTime(String wrkEndTime) {
		this.wrkEndTime = wrkEndTime;
	}

	/** 초과 작업 시간 */
	private String ovtmwrkHours;

	/**
	 * @return 초과 작업 시간을 반환합니다.
	 */
	public String getOvtmwrkHours() {
		return ovtmwrkHours;
	}

	/**
	 * @param ovtmwrkHours 설정할 초과 작업 시간
	 */
	public void setOvtmwrkHours(String ovtmwrkHours) {
		this.ovtmwrkHours = ovtmwrkHours;
	}

	/** 비고 */
	private String rm;

	/**
	 * @return 비고를 반환합니다.
	 */
	public String getRm() {
		return rm;
	}

	/**
	 * @param rm 설정할 비고
	 */
	public void setRm(String rm) {
		this.rm = rm;
	}

	/** 직원 ID */
	private String emplyrId;

	/**
	 * @return 직원 ID를 반환합니다.
	 */
	public String getEmplyrId() {
		return emplyrId;
	}

	/**
	 * @param emplyrId 설정할 직원 ID
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	/** 조직 ID */
	private String orgnztId;

	/**
	 * @return 조직 ID를 반환합니다.
	 */
	public String getOrgnztId() {
		return orgnztId;
	}

	/**
	 * @param orgnztId 설정할 조직 ID
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	/** 작업 날짜 */
	private String wrktDt;

	/**
	 * @return 작업 날짜를 반환합니다.
	 */
	public String getWrktDt() {
		return wrktDt;
	}

	/**
	 * @param wrkt_dt 설정할 작업 날짜
	 */
	public void setWrktDt(String wrkt_dt) {
		this.wrktDt = wrkt_dt;
	}

}
