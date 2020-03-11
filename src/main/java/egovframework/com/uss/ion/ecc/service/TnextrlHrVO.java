package egovframework.com.uss.ion.ecc.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 외부인사관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *
 * </pre>
 */
public class TnextrlHrVO extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = 1739374172177710041L;

	/**
	 * 성별코드
	 */
	private String sexdstnCode = "";
	
	/**
	 * 성별코드명
	 */
	private String sexdstnCodeNm = "";

	/**
	 * 외부인사명
	 */
	private String extrlHrNm = "";

	/**
	 * 이메일주소
	 */
	private String emailAdres = "";

	/**
	 * 직업유형코드
	 */
	private String occpTyCode = "";
	
	/**
	 * 직업유형코드명
	 */
	private String occpTyCodeNm = "";

	/**
	 * 소속기관명
	 */
	private String psitnInsttNm = "";

	/**
	 * 외부인사ID
	 */
	private String extrlHrId = "";

	/**
	 * 행사/이벤트/캠페인 아이디
	 */
	private String eventId = "";
	
	/**
	 * 행사/이벤트/캠페인내용
	 */
	private String eventCn = "";

	/**
	 * 생년월일
	 */
	private String brth = "";

	/**
	 * 지역번호
	 */
	private String areaNo = "";

	/**
	 * 중간전화번호
	 */
	private String middleTelno = "";

	/**
	 * 끝전화번호
	 */
	private String endTelno = "";

	/**
	 * 생년월일(년)
	 */
	private String brthYYYY = "";

	/**
	 * 생년월일(월)
	 */
	private String brthMM = "";

	/**
	 * 생년월일(일)
	 */
	private String brthDD = "";

	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm = "";

	/**
	 * 최초등록ID
	 */
	private String frstRegisterId = "";
	
	/**
	 * 최초등록ID
	 */
	private String frstRegisterNm = "";

	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm = "";

	/**
	 * 최종수정ID
	 */
	private String lastUpdusrId = "";

	/**
	 * sexdstnCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSexdstnCode() {
		return sexdstnCode;
	}

	/**
	 * sexdstnCode attribute 값을 설정한다.
	 * @return sexdstnCode String
	 */
	public void setSexdstnCode(String sexdstnCode) {
		this.sexdstnCode = sexdstnCode;
	}
	
	/**
	 * sexdstnCodeNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSexdstnCodeNm() {
		return sexdstnCodeNm;
	}
	
	/**
	 * sexdstnCodeNm attribute 값을 설정한다.
	 * @return sexdstnCodeNm String
	 */
	public void setSexdstnCodeNm(String sexdstnCodeNm) {
		this.sexdstnCodeNm = sexdstnCodeNm;
	}

	/**
	 * extrlHrNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getExtrlHrNm() {
		return extrlHrNm;
	}

	/**
	 * extrlHrNm attribute 값을 설정한다.
	 * @return extrlHrNm String
	 */
	public void setExtrlHrNm(String extrlHrNm) {
		this.extrlHrNm = extrlHrNm;
	}

	/**
	 * emailAdres attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEmailAdres() {
		return emailAdres;
	}

	/**
	 * emailAdres attribute 값을 설정한다.
	 * @return emailAdres String
	 */
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	/**
	 * occpTyCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getOccpTyCode() {
		return occpTyCode;
	}

	/**
	 * occpTyCode attribute 값을 설정한다.
	 * @return occpTyCode String
	 */
	public void setOccpTyCode(String occpTyCode) {
		this.occpTyCode = occpTyCode;
	}
	
	/**
	 * occpTyCodeNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getOccpTyCodeNm() {
		return occpTyCodeNm;
	}
	
	/**
	 * occpTyCodeNm attribute 값을 설정한다.
	 * @return occpTyCodeNm String
	 */
	public void setOccpTyCodeNm(String occpTyCodeNm) {
		this.occpTyCodeNm = occpTyCodeNm;
	}

	/**
	 * psitnInsttNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getPsitnInsttNm() {
		return psitnInsttNm;
	}

	/**
	 * psitnInsttNm attribute 값을 설정한다.
	 * @return psitnInsttNm String
	 */
	public void setPsitnInsttNm(String psitnInsttNm) {
		this.psitnInsttNm = psitnInsttNm;
	}

	/**
	 * extrlHrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getExtrlHrId() {
		return extrlHrId;
	}

	/**
	 * extrlHrId attribute 값을 설정한다.
	 * @return extrlHrId String
	 */
	public void setExtrlHrId(String extrlHrId) {
		this.extrlHrId = extrlHrId;
	}

	/**
	 * eventId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * eventId attribute 값을 설정한다.
	 * @return eventId String
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	/**
	 * eventCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEventCn() {
		return eventCn;
	}
	
	/**
	 * eventCn attribute 값을 설정한다.
	 * @return eventCn String
	 */
	public void setEventCn(String eventCn) {
		this.eventCn = eventCn;
	}

	/**
	 * brth attribute 를 리턴한다.
	 * @return the String
	 */
	public String getBrth() {
		return brth;
	}

	/**
	 * brth attribute 값을 설정한다.
	 * @return brth String
	 */
	public void setBrth(String brth) {
		this.brth = brth;
	}

	/**
	 * areaNo attribute 를 리턴한다.
	 * @return the String
	 */
	public String getAreaNo() {
		return areaNo;
	}

	/**
	 * areaNo attribute 값을 설정한다.
	 * @return areaNo String
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	/**
	 * middleTelno attribute 를 리턴한다.
	 * @return the String
	 */
	public String getMiddleTelno() {
		return middleTelno;
	}

	/**
	 * middleTelno attribute 값을 설정한다.
	 * @return middleTelno String
	 */
	public void setMiddleTelno(String middleTelno) {
		this.middleTelno = middleTelno;
	}

	/**
	 * endTelno attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEndTelno() {
		return endTelno;
	}

	/**
	 * endTelno attribute 값을 설정한다.
	 * @return endTelno String
	 */
	public void setEndTelno(String endTelno) {
		this.endTelno = endTelno;
	}

	/**
	 * brthYYYY attribute 를 리턴한다.
	 * @return the String
	 */
	public String getBrthYYYY() {
		return brthYYYY;
	}

	/**
	 * brthYYYY attribute 값을 설정한다.
	 * @return brthYYYY String
	 */
	public void setBrthYYYY(String brthYYYY) {
		this.brthYYYY = brthYYYY;
	}

	/**
	 * brthMM attribute 를 리턴한다.
	 * @return the String
	 */
	public String getBrthMM() {
		return brthMM;
	}

	/**
	 * brthMM attribute 값을 설정한다.
	 * @return brthMM String
	 */
	public void setBrthMM(String brthMM) {
		this.brthMM = brthMM;
	}

	/**
	 * brthDD attribute 를 리턴한다.
	 * @return the String
	 */
	public String getBrthDD() {
		return brthDD;
	}

	/**
	 * brthDD attribute 값을 설정한다.
	 * @return brthDD String
	 */
	public void setBrthDD(String brthDD) {
		this.brthDD = brthDD;
	}

	/**
	 * frstRegisterPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * @return frstRegisterPnttm String
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @return frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	
	/**
	 * frstRegisterNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}
	
	/**
	 * frstRegisterNm attribute 값을 설정한다.
	 * @return frstRegisterNm String
	 */
	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}

	/**
	 * lastUpdusrPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * @return lastUpdusrPnttm String
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @return lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}





}
