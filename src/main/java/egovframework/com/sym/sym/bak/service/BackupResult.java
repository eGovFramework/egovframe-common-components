package egovframework.com.sym.sym.bak.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 백업결과관리에 대한 model 클래스
 *
 * @author 김진만
 * @since 2010.06.17
 * @version 1.0
 * @updated 17-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.17   김진만     최초 생성
 * </pre>
 */
public class BackupResult extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = -743292072713546949L;
	/**
	 * 백업결과ID
	 */
	private String backupResultId;
	/**
	 * 백업작업ID
	 */
	private String backupOpertId;
	/**
	 * 백업화일
	 */
	private String backupFile;
	/**
	 * 상태
	 */
	private String sttus;
	/**
	 * 실행시작시각
	 */
	private String executBeginTime;
	/**
	 * 실행종료시각
	 */
	private String executEndTime;
	/**
	 * 최종수정자 아이디
	 */
	private String lastUpdusrId;
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm;
	/**
	 * 최초등록자 아이디
	 */
	private String frstRegisterId;
	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm;

	/**
	 * 에러정보
	 */
	private String errorInfo;

	/**
	 * 백업작업명
	 */
	private String backupOpertNm;
	/**
	 * 상태명
	 */
	private String sttusNm;
	/**
	 * 백업원본디렉토리
	 */
	private String backupOrginlDrctry;
	/**
	 * 백업저장디렉토리
	 */
	private String backupStreDrctry;
	/**
	 * @return the backupResultId
	 */
	public String getBackupResultId() {
		return backupResultId;
	}
	/**
	 * @return the backupOpertId
	 */
	public String getBackupOpertId() {
		return backupOpertId;
	}
	/**
	 * @return the backupFile
	 */
	public String getBackupFile() {
		return backupFile;
	}
	/**
	 * @return the sttus
	 */
	public String getSttus() {
		return sttus;
	}
	/**
	 * @return the executBeginTime
	 */
	public String getExecutBeginTime() {
		return executBeginTime;
	}
	/**
	 * @return the executEndTime
	 */
	public String getExecutEndTime() {
		return executEndTime;
	}
	/**
	 * @return the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @return the lastUpdusrPnttm
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * @return the frstRegisterId
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * @return the frstRegisterPnttm
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * @return the errorInfo
	 */
	public String getErrorInfo() {
		return errorInfo;
	}
	/**
	 * @return the backupOpertNm
	 */
	public String getBackupOpertNm() {
		return backupOpertNm;
	}
	/**
	 * @return the sttusNm
	 */
	public String getSttusNm() {
		return sttusNm;
	}
	/**
	 * @return the backupOrginlDrctry
	 */
	public String getBackupOrginlDrctry() {
		return backupOrginlDrctry;
	}
	/**
	 * @return the backupStreDrctry
	 */
	public String getBackupStreDrctry() {
		return backupStreDrctry;
	}
	/**
	 * @param backupResultId the backupResultId to set
	 */
	public void setBackupResultId(String backupResultId) {
		this.backupResultId = backupResultId;
	}
	/**
	 * @param backupOpertId the backupOpertId to set
	 */
	public void setBackupOpertId(String backupOpertId) {
		this.backupOpertId = backupOpertId;
	}
	/**
	 * @param backupFile the backupFile to set
	 */
	public void setBackupFile(String backupFile) {
		this.backupFile = backupFile;
	}
	/**
	 * @param sttus the sttus to set
	 */
	public void setSttus(String sttus) {
		this.sttus = sttus;
	}
	/**
	 * @param executBeginTime the executBeginTime to set
	 */
	public void setExecutBeginTime(String executBeginTime) {
		this.executBeginTime = executBeginTime;
	}
	/**
	 * @param executEndTime the executEndTime to set
	 */
	public void setExecutEndTime(String executEndTime) {
		this.executEndTime = executEndTime;
	}
	/**
	 * @param lastUpdusrId the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * @param lastUpdusrPnttm the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * @param frstRegisterId the frstRegisterId to set
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * @param frstRegisterPnttm the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * @param errorInfo the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	/**
	 * @param backupOpertNm the backupOpertNm to set
	 */
	public void setBackupOpertNm(String backupOpertNm) {
		this.backupOpertNm = backupOpertNm;
	}
	/**
	 * @param sttusNm the sttusNm to set
	 */
	public void setSttusNm(String sttusNm) {
		this.sttusNm = sttusNm;
	}
	/**
	 * @param backupOrginlDrctry the backupOrginlDrctry to set
	 */
	public void setBackupOrginlDrctry(String backupOrginlDrctry) {
		this.backupOrginlDrctry = backupOrginlDrctry;
	}
	/**
	 * @param backupStreDrctry the backupStreDrctry to set
	 */
	public void setBackupStreDrctry(String backupStreDrctry) {
		this.backupStreDrctry = backupStreDrctry;
	}


}