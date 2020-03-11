package egovframework.com.utl.sys.dbm.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

/**
 * DB서비스모니터링에 대한 model 클래스
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
public class DbMntrng extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = 5555816004403245980L;
	/**
	 * 데이터소스명
	 */
	private String dataSourcNm;
	/**
	 * 서버명
	 */
	private String serverNm;
	/**
	 * DBMS종류
	 */
	private String dbmsKind;
	/**
	 * 체크SQL
	 */
	private String ceckSql;
	/**
	 * 관리자명
	 */
	private String mngrNm;
	/**
	 * 관리자이메일주소
	 */
	private String mngrEmailAddr;
	/**
	 * 모니터링상태
	 */
	private String mntrngSttus;

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
	 * 생성일시
	 */
	private String creatDt;


	/**
	 * 모니터링상태명
	 */
	private String mntrngSttusNm;
	/**
	 * DBMS종류명
	 */
	private String dbmsKindNm;

	/**
	 * @return the dataSourcNm
	 */
	public String getDataSourcNm() {
		return dataSourcNm;
	}
	/**
	 * @return the serverNm
	 */
	public String getServerNm() {
		return serverNm;
	}
	/**
	 * @return the dbmsKind
	 */
	public String getDbmsKind() {
		return dbmsKind;
	}
	/**
	 * @return the ceckSql
	 */
	public String getCeckSql() {
		return ceckSql;
	}
	/**
	 * @return the mngrNm
	 */
	public String getMngrNm() {
		return mngrNm;
	}
	/**
	 * @return the mngrEmailAddr
	 */
	public String getMngrEmailAddr() {
		return mngrEmailAddr;
	}
	/**
	 * @return the mntrngSttus
	 */
	public String getMntrngSttus() {
		return mntrngSttus;
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
	 * @return the mntrngSttusNm
	 */
	public String getMntrngSttusNm() {
		return mntrngSttusNm;
	}
	/**
	 * @return the dbmsKindNm
	 */
	public String getDbmsKindNm() {
		return dbmsKindNm;
	}
	/**
	 * @param dataSourcNm the dataSourcNm to set
	 */
	public void setDataSourcNm(String dataSourcNm) {
		this.dataSourcNm = dataSourcNm;
	}
	/**
	 * @param serverNm the serverNm to set
	 */
	public void setServerNm(String serverNm) {
		this.serverNm = serverNm;
	}
	/**
	 * @param dbmsKind the dbmsKind to set
	 */
	public void setDbmsKind(String dbmsKind) {
		this.dbmsKind = dbmsKind;
	}
	/**
	 * @param ceckSql the ceckSql to set
	 */
	public void setCeckSql(String ceckSql) {
		this.ceckSql = ceckSql;
	}
	/**
	 * @param mngrNm the mngrNm to set
	 */
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	/**
	 * @param mngrEmailAddr the mngrEmailAddr to set
	 */
	public void setMngrEmailAddr(String mngrEmailAddr) {
		this.mngrEmailAddr = mngrEmailAddr;
	}
	/**
	 * @param mntrngSttus the mntrngSttus to set
	 */
	public void setMntrngSttus(String mntrngSttus) {
		this.mntrngSttus = mntrngSttus;
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
	 * @param mntrngSttusNm the mntrngSttusNm to set
	 */
	public void setMntrngSttusNm(String mntrngSttusNm) {
		this.mntrngSttusNm = mntrngSttusNm;
	}
	/**
	 * @param dbmsKindNm the dbmsKindNm to set
	 */
	public void setDbmsKindNm(String dbmsKindNm) {
		this.dbmsKindNm = dbmsKindNm;
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
	 * @return the creatDt
	 */
	public String getCreatDt() {
		return creatDt;
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
	 * @param creatDt the creatDt to set
	 */
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}


}