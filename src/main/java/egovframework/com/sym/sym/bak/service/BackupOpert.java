package egovframework.com.sym.sym.bak.service;

import java.io.Serializable;
import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 백업작업관리에 대한 model 클래스
 *
 * @author 김진만
 * @since 2010.06.21
 * @version 1.0
 * @updated 21-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.21   김진만     최초 생성
 * </pre>
 */
@SuppressWarnings("serial")
public class BackupOpert extends ComDefaultVO implements Serializable {

	/**
	 * 백업작업ID
	 */
	private String backupOpertId;
	/**
	 * 백업작업명
	 */
	private String backupOpertNm;
	/**
	 * 백업원본디렉토리
	 */
	private String backupOrginlDrctry;
	/**
	 * 백업저장디렉토리
	 */
	private String backupStreDrctry;
	/**
	 * 압축구분
	 */
	private String cmprsSe;
	/**
	 * 실행주기
	 */
	private String executCycle;
	/**
	 * 실행스케줄일자
	 */
	private String executSchdulDe;
	/**
	 * 실행스케줄시
	 */
	private String executSchdulHour;
	/**
	 * 실행스케줄분
	 */
	private String executSchdulMnt;
	/**
	 * 실행스케줄초
	 */
	private String executSchdulSecnd;
	/**
	 * 실행스케줄요일
	 */
	private String[] executSchdulDfkSes;
	/**
	 * 사용여부
	 */
	private String useAt;

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
	 * 실행주기명
	 */
	private String executCycleNm;
	/**
	 * 실행스케줄
	 */
	private String executSchdul;
	/**
	 * 압축구분명
	 */
	private String cmprsSeNm;
	/**
	 * @return the backupOpertId
	 */
	public String getBackupOpertId() {
		return backupOpertId;
	}
	/**
	 * @return the backupOpertNm
	 */
	public String getBackupOpertNm() {
		return backupOpertNm;
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
	 * @return the cmprsSe
	 */
	public String getCmprsSe() {
		return cmprsSe;
	}
	/**
	 * @return the executCycle
	 */
	public String getExecutCycle() {
		return executCycle;
	}
	/**
	 * @return the executSchdulDe
	 */
	public String getExecutSchdulDe() {
		return executSchdulDe;
	}
	/**
	 * @return the executSchdulOur
	 */
	public String getExecutSchdulHour() {
		return executSchdulHour;
	}
	/**
	 * @return the executSchdulMnt
	 */
	public String getExecutSchdulMnt() {
		return executSchdulMnt;
	}
	/**
	 * @return the executSchdulSecnd
	 */
	public String getExecutSchdulSecnd() {
		return executSchdulSecnd;
	}
	/**
	 * @return the executSchdulDfkSes
	 */
    public String[] getExecutSchdulDfkSes() {
    	//return executSchdulDfkSes;
    	String[] ret = null;
    	if (this.executSchdulDfkSes != null) {
    	    ret = new String[executSchdulDfkSes.length];
    	    for (int i = 0; i < executSchdulDfkSes.length; i++) {
    		ret[i] = this.executSchdulDfkSes[i];
    	    }
    	}

    	return ret;
        }
	/**
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
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
	 * @return the executCycleNm
	 */
	public String getExecutCycleNm() {
		return executCycleNm;
	}
	/**
	 * @return the executSchdul
	 */
	public String getExecutSchdul() {
		return executSchdul;
	}
	/**
	 * @return the cmprsSeNm
	 */
	public String getCmprsSeNm() {
		return cmprsSeNm;
	}
	/**
	 * @param backupOpertId the backupOpertId to set
	 */
	public void setBackupOpertId(String backupOpertId) {
		this.backupOpertId = backupOpertId;
	}
	/**
	 * @param backupOpertNm the backupOpertNm to set
	 */
	public void setBackupOpertNm(String backupOpertNm) {
		this.backupOpertNm = backupOpertNm;
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
	/**
	 * @param cmprsSe the cmprsSe to set
	 */
	public void setCmprsSe(String cmprsSe) {
		this.cmprsSe = cmprsSe;
	}
	/**
	 * @param executCycle the executCycle to set
	 */
	public void setExecutCycle(String executCycle) {
		this.executCycle = executCycle;
	}
	/**
	 * @param executSchdulDe the executSchdulDe to set
	 */
	public void setExecutSchdulDe(String executSchdulDe) {
		this.executSchdulDe = executSchdulDe;
	}
	/**
	 * @param executSchdulOur the executSchdulOur to set
	 */
	public void setExecutSchdulHour(String executSchdulHour) {
		this.executSchdulHour = executSchdulHour;
	}
	/**
	 * @param executSchdulMnt the executSchdulMnt to set
	 */
	public void setExecutSchdulMnt(String executSchdulMnt) {
		this.executSchdulMnt = executSchdulMnt;
	}
	/**
	 * @param executSchdulSecnd the executSchdulSecnd to set
	 */
	public void setExecutSchdulSecnd(String executSchdulSecnd) {
		this.executSchdulSecnd = executSchdulSecnd;
	}
	/**
	 * @param executSchdulDfkSes the executSchdulDfkSes to set
	 */
    public void setExecutSchdulDfkSes(String[] executSchdulDfkSes) {
    	//this.executSchdulDfkSes = executSchdulDfkSes;
    	this.executSchdulDfkSes = new String[executSchdulDfkSes.length];
    	for (int i = 0; i < executSchdulDfkSes.length; ++i) {
    	    this.executSchdulDfkSes[i] = executSchdulDfkSes[i];
    	}
        }
	/**
	 * @param useAt the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
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
	 * @param executCycleNm the executCycleNm to set
	 */
	public void setExecutCycleNm(String executCycleNm) {
		this.executCycleNm = executCycleNm;
	}
	/**
	 * @param executSchdul the executSchdul to set
	 */
	public void setExecutSchdul(String executSchdul) {
		this.executSchdul = executSchdul;
	}
	/**
	 * @param cmprsSeNm the cmprsSeNm to set
	 */
	public void setCmprsSeNm(String cmprsSeNm) {
		this.cmprsSeNm = cmprsSeNm;
	}

	/**
	 * 리스트, 상세화면 화면표시용 실행스케줄속성을 만들어 executSchdul 필드에 저장한다.
	 *
	 * @param dfkSeList List<BackupSchdulDfk>형의 요일구분코드정보리스트
	 */
	public void makeExecutSchdul(List<BackupSchdulDfk> dfkSeList) {
		String executSchdul = "";
		String executSchdulDeNm = "";

		// 날짜 출력
		if (this.executCycle.equals("02") || this.executCycle.equals("01")) {
			// 매주, 매일인 경우는 스케줄일자를 사용하지 않는다.
			executSchdulDeNm = "";
		} else if (this.executCycle.equals("03")){
			// 매월 처리
			if (!"".equals(this.executSchdulDe)) {
				executSchdulDeNm = executSchdulDeNm + this.executSchdulDe.substring(6,8) + "일 ";
			}
		} else if (this.executCycle.equals("04")){
			// 매년의경우 처리
			if (!"".equals(this.executSchdulDe)) {
				executSchdulDeNm = executSchdulDeNm + this.executSchdulDe.substring(4,6) + "-" + this.executSchdulDe.substring(6,8) + " ";
			}
		} else {
			// 이외의경우 처리
			if (!"".equals(this.executSchdulDe)) {
				executSchdulDeNm = executSchdulDeNm + this.executSchdulDe.substring(0,4) + "-" + this.executSchdulDe.substring(4,6) + "-" + this.executSchdulDe.substring(6,8) + " ";
			}
		}

		// 날짜 출력
		executSchdul = executSchdul + executSchdulDeNm;

		// 요일출력
		if (this.executCycle.equals("02")) {
			// 실행주기가 매주인 경우에만 출력한다.
			if (dfkSeList.size() != 0) {
				for (int i = 0; i < dfkSeList.size(); i++) {
					if (i != 0) {
						executSchdul = executSchdul + ",";
					}
					executSchdul = executSchdul + dfkSeList.get(i).getExecutSchdulDfkSeNm();
				}
				executSchdul = executSchdul + " ";
			}
		}

		// 시, 분, 초 출력
		// 시분초는 항상출력한다.
		executSchdul = executSchdul + this.executSchdulHour + ":" + this.executSchdulMnt + ":" + this.executSchdulSecnd;

		// 값지정.
		this.executSchdul = executSchdul;

	}

	/**
	 * 실행스케줄을 CronExpression으로 바꿔서 리턴한다.
	 **/
	public String toCronExpression() {
		String cronExpression = "";

		// 초변환
		cronExpression = cronExpression + this.executSchdulSecnd;

		// 분변환
		cronExpression = cronExpression + " " + this.executSchdulMnt;

		// 시변환
		cronExpression = cronExpression + " " + this.executSchdulHour;

		// 일변환
		if (this.executCycle.equals("01")) {
			// 매일인경우 "*" 출력
			cronExpression = cronExpression + " " + "*";
		} else if (this.executCycle.equals("02")) {
			// 매주인 경우 "?" 출력
			cronExpression = cronExpression + " " + "?";
		} else {
			// 이외의 경우 그대로 출력
			cronExpression = cronExpression + " " + this.executSchdulDe.substring(6,8);
		}

		// 월변환
		if (this.executCycle.equals("01") || this.executCycle.equals("02") || this.executCycle.equals("03")) {
			// 매일,매월,매주인경우 "*" 출력
			cronExpression = cronExpression + " " + "*";
		} else {
			// 이외의 경우 그대로 출력
			cronExpression = cronExpression + " " + this.executSchdulDe.substring(4,6);
		}

		// 주 변환
		if (this.executCycle.equals("02")) {
			// 매주인경우 day of week를  출력
			String dayOfWeek = "";
			for (int i = 0; i < this.executSchdulDfkSes.length; i++) {
				if (i != 0) {
					dayOfWeek = dayOfWeek + ",";
				}
				dayOfWeek = dayOfWeek + this.executSchdulDfkSes[i];
			}
			cronExpression = cronExpression + " " + dayOfWeek;
		} else {
			// 이외의 경우 "?" 출력
			cronExpression = cronExpression + " " + "?";
		}

		// 년변환
		if (this.executCycle.equals("05")) {
			// 한번만인경우 년도 출력
			cronExpression = cronExpression + " " + this.executSchdulDe.substring(0,4);
		}

		return cronExpression;

	}



}