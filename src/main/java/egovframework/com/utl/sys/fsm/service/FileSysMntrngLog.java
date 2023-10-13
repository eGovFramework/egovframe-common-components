package egovframework.com.utl.sys.fsm.service;

import java.io.Serializable;

/**
 * 개요
 * - 파일시스템 모니터링 로그에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그ID, 파일시스템ID, 파일시스템명, 파일시스템관리명, 파일시스템크기, 파일시스템임계치, 파일시스템임계치율, 파일시스템사용량, 파일시스템사용률, 서비스상태, 로그정보, 생성일시 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:26
 */
@SuppressWarnings("serial")
public class FileSysMntrngLog implements Serializable  {
	/**
	 * 로그ID
	 */
	private String logId;
	/**
	 * 파일시스템ID
	 */
	private String fileSysId;
	/**
	 * 파일시스템명
	 */
	private String fileSysNm;
	/**
	 * 파일시스템관리명
	 */
	private String fileSysManageNm;
	/**
	 * 파일시스템크기
	 */
	private int fileSysMg;
	/**
	 * 파일시스템임계치
	 */
	private int fileSysThrhld;
	/**
	 * 파일시스템임계치율
	 */
	private int fileSysThrhldRt;
	/**
	 * 파일시스템사용량
	 */
	private int fileSysUsgQty;
	/**
	 * 파일시스템사용률
	 */
	private double fileSysUsgRt;
	/**
	 * 모니터링상태
	 */
	private String mntrngSttus;
	/**
	 * 로그정보
	 */
	private String logInfo;
	/**
	 * 생성일시
	 */
	private String creatDt;
	/**
	 * 최초등록자ID
	 */
	private String frstRegisterId = "";
	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm = "";
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId = "";
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm = "";
	/**
	 * 로그 ID 반환
	 */
	public String getLogId() {
		return logId;
	}

	/**
	 * 로그 ID 설정
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}

	/**
	 * 파일 시스템 ID 반환
	 */
	public String getFileSysId() {
		return fileSysId;
	}

	/**
	 * 파일 시스템 ID 설정
	 */
	public void setFileSysId(String fileSysId) {
		this.fileSysId = fileSysId;
	}

	/**
	 * 파일 시스템 이름 반환
	 */
	public String getFileSysNm() {
		return fileSysNm;
	}

	/**
	 * 파일 시스템 이름 설정
	 */
	public void setFileSysNm(String fileSysNm) {
		this.fileSysNm = fileSysNm;
	}

	/**
	 * 파일 시스템 관리 이름 반환
	 */
	public String getFileSysManageNm() {
		return fileSysManageNm;
	}

	/**
	 * 파일 시스템 관리 이름 설정
	 */
	public void setFileSysManageNm(String fileSysManageNm) {
		this.fileSysManageNm = fileSysManageNm;
	}

	/**
	 * 파일 시스템 크기 반환
	 */
	public int getFileSysMg() {
		return fileSysMg;
	}

	/**
	 * 파일 시스템 크기 설정
	 */
	public void setFileSysMg(int fileSysMg) {
		this.fileSysMg = fileSysMg;
	}

	/**
	 * 파일 시스템 임계값 반환
	 */
	public int getFileSysThrhld() {
		return fileSysThrhld;
	}

	/**
	 * 파일 시스템 임계값 설정
	 */
	public void setFileSysThrhld(int fileSysThrhld) {
		this.fileSysThrhld = fileSysThrhld;
	}

	/**
	 * 파일 시스템 임계값 비율 반환
	 */
	public int getFileSysThrhldRt() {
		return fileSysThrhldRt;
	}

	/**
	 * 파일 시스템 임계값 비율 설정
	 */
	public void setFileSysThrhldRt(int fileSysThrhldRt) {
		this.fileSysThrhldRt = fileSysThrhldRt;
	}

	/**
	 * 파일 시스템 사용량 반환
	 */
	public int getFileSysUsgQty() {
		return fileSysUsgQty;
	}

	/**
	 * 파일 시스템 사용량 설정
	 */
	public void setFileSysUsgQty(int fileSysUsgQty) {
		this.fileSysUsgQty = fileSysUsgQty;
	}

	/**
	 * 파일 시스템 사용률 반환
	 */
	public double getFileSysUsgRt() {
		return fileSysUsgRt;
	}

	/**
	 * 파일 시스템 사용률 설정
	 */
	public void setFileSysUsgRt(double fileSysUsgRt) {
		this.fileSysUsgRt = fileSysUsgRt;
	}

	/**
	 * 모니터링 상태 반환
	 */
	public String getMntrngSttus() {
		return mntrngSttus;
	}

	/**
	 * 모니터링 상태 설정
	 */
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
	}

	/**
	 * 로그 정보 반환
	 */
	public String getLogInfo() {
		return logInfo;
	}

	/**
	 * 로그 정보 설정
	 */
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	/**
	 * 생성 날짜 반환
	 */
	public String getCreatDt() {
		return creatDt;
	}

	/**
	 * 생성 날짜 설정
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

	/**
	 * 최초 등록자 ID 반환
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * 최초 등록자 ID 설정
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * 최초 등록 시점 반환
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * 최초 등록 시점 설정
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * 마지막 수정자 ID 반환
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * 마지막 수정자 ID 설정
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * 마지막 수정 시점 반환
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * 마지막 수정 시점 설정
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	
}