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
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.12.20  개발팀         최초 생성
 *
 * </pre>
 */
public class CmtManageVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public CmtManageVO() {
		// constructor
	}

	public List<CmtManageVO> getCmtManageList() {
		return cmtManageList;
	}

	public void setCmtManageList(List<CmtManageVO> cmtManageList) {
		this.cmtManageList = cmtManageList;
	}

	public String getUserNmString() {
		return userNmString;
	}

	public void setUserNmString(String userNmString) {
		this.userNmString = userNmString;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getWrkStartStatus() {
		return wrkStartStatus;
	}

	public void setWrkStartStatus(String workStartStatus) {
		this.wrkStartStatus = workStartStatus;
	}

	public String getWrkEndStatus() {
		return wrkEndStatus;
	}

	public void setWrkEndStatus(String workEndStatus) {
		this.wrkEndStatus = workEndStatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWrkHours() {
		return wrkHours;
	}

	public void setWrkHours(String workHours) {
		this.wrkHours = workHours;
	}

	public String getWrktmId() {
		return wrktmId;
	}

	public void setWrktmId(String wrktmId) {
		this.wrktmId = wrktmId;
	}

	public String getWrkStartTime() {
		return wrkStartTime;
	}

	public void setWrkStartTime(String wrkStartTime) {
		this.wrkStartTime = wrkStartTime;
	}

	public String getWrkEndTime() {
		return wrkEndTime;
	}

	public void setWrkEndTime(String wrkEndTime) {
		this.wrkEndTime = wrkEndTime;
	}

	public String getOvtmwrkHours() {
		return ovtmwrkHours;
	}

	public void setOvtmwrkHours(String ovtmwrkHours) {
		this.ovtmwrkHours = ovtmwrkHours;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public String getEmplyrId() {
		return emplyrId;
	}

	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	public String getOrgnztId() {
		return orgnztId;
	}

	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	public String getWrktDt() {
		return wrktDt;
	}

	public void setWrktDt(String wrkt_dt) {
		this.wrktDt = wrkt_dt;
	}

	/**
	 * 출퇴근 목록
	 */
	List<CmtManageVO> cmtManageList;

	/**
	*  사용자명
	*/
	private String userNmString;

	/**
	*  사용자ID
	*/
	private String usid;

	/**
	 * employee ID
	 */
	private String emplyrId;

	/**
	 * 부서Id
	 */
	private String orgnztId;

	/**
	* 근무시간
	*/
	private String wrkHours;

	/**
	 * 출퇴근 구분 ID
	 *
	 */
	private String wrktmId;

	/**
	 * 출근날짜
	 */
	private String wrktDt;

	/**
	 * 출근시간
	 */
	private String wrkStartTime;

	/**
	 * 퇴근시간
	 */
	private String wrkEndTime;

	/**
	*  출근상태
	*/
	private String wrkStartStatus;

	/**
	*  출근상태
	*/
	private String wrkEndStatus;

	/**
	 * 초과근무시간
	 */
	private String ovtmwrkHours;

	/**
	 * 비고
	 */
	private String rm;

	/**
	 * date
	 */
	private String date;

}
