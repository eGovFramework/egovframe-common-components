package egovframework.com.sym.sym.bak.service;

import java.io.Serializable;

/**
 * 백업스케줄요일에 대한 model 클래스
 *
 * @author 김진만
 * @version 1.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.09.01   김진만     최초 생성
 * </pre>
 */
public class BackupSchdulDfk implements Serializable {

	private static final long serialVersionUID = -6208617298024325398L;

	/**
	 * 백업작업ID
	 */
	private String backupOpertId;

	/**
	 * 실행스케줄요일
	 */
	private String executSchdulDfkSe;

	/**
	 * 실행스케줄요일명
	 */
	private String executSchdulDfkSeNm;


	/**
	 * @return the backupOpertId
	 */
	public String getBackupOpertId() {
		return backupOpertId;
	}
	/**
	 * @return the executSchdulDfkSe
	 */
	public String getExecutSchdulDfkSe() {
		return executSchdulDfkSe;
	}
	/**
	 * @param backupOpertId the backupOpertId to set
	 */
	public void setBackupOpertId(String backupOpertId) {
		this.backupOpertId = backupOpertId;
	}
	/**
	 * @param executSchdulDfkSe the executSchdulDfkSe to set
	 */
	public void setExecutSchdulDfkSe(String executSchdulDfkSe) {
		this.executSchdulDfkSe = executSchdulDfkSe;
	}
	/**
	 * @return the executSchdulDfkSeNm
	 */
	public String getExecutSchdulDfkSeNm() {
		return executSchdulDfkSeNm;
	}
	/**
	 * @param executSchdulDfkSeNm the executSchdulDfkSeNm to set
	 */
	public void setExecutSchdulDfkSeNm(String executSchdulDfkSeNm) {
		this.executSchdulDfkSeNm = executSchdulDfkSeNm;
	}



}