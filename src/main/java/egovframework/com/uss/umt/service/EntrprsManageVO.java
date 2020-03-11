package egovframework.com.uss.umt.service;

/**
 * 기업회원VO클래스로서 기업회원관리 비지니스로직 처리용 항목을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 * </pre>
 */
public class EntrprsManageVO  extends UserDefaultVO{

	private static final long serialVersionUID = -6532736688851136256L;

	/** 이전비밀번호 - 비밀번호 변경시 사용*/
    private String oldPassword = "";

    /**
	 * 사용자고유아이디
	 */
	private String uniqId="";
	/**
	 * 사용자 유형
	 */
	private String userTy;
	/**
	 * 주소
	 */
	private String adres;
	/**
	 * 상세주소
	 */
	private String detailAdres;
	/**
	 * 신청자 주민등록번호
	 */
	private String applcntIhidnum;
	/**
	 * 신청자 명
	 */
	private String applcntNm;
	/**
	 * 사업자번호
	 */
	private String bizrno;
	/**
	 * 회사명
	 */
	private String cmpnyNm;
	/**
	 * 대표이사
	 */
	private String cxfc;
	/**
	 * 기업 회원 ID
	 */
	private String entrprsmberId;
	/**
	 * 기업 회원 비밀번호
	 */
	private String entrprsMberPassword;
	/**
	 * 기업 회원 비밀번호 정답
	 */
	private String entrprsMberPasswordCnsr;
	/**
	 * 기업 회원 비밀번호 힌트
	 */
	private String entrprsMberPasswordHint;
	/**
	 * 기업 회원 상태
	 */
	private String entrprsMberSttus;
	/**
	 * 기업구분코드
	 */
	private String entrprsSeCode;
	/**
	 * 팩스번호
	 */
	private String fxnum;
	/**
	 * 그룹 ID
	 */
	private String groupId;
	/**
	 * 업종코드
	 */
	private String indutyCode;
	/**
	 * 법인등록번호
	 */
	private String jurirno;
	/**
	 * 지역번호
	 */
	private String areaNo;
	/**
	 * 회사끝전화번호
	 */
	private String entrprsEndTelno;
	/**
	 * 회사중간전화번호
	 */
	private String entrprsMiddleTelno;
	/**
	 * 가입 일자
	 */
	private String sbscrbDe;
	/**
	 * 우편번호
	 */
	private String zip;
	/**
	 * 신청자 이메일주소
	 */
	private String applcntEmailAdres;
	
	private String lockAt;
	public String getLockAt() {return lockAt;}
	public void setLockAt(String lockAt) {this.lockAt = lockAt;}
	
	/**
	 * oldPassword attribute 값을  리턴한다.
	 * @return String
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * oldPassword attribute 값을 설정한다.
	 * @param oldPassword String
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	/**
	 * uniqId attribute 값을  리턴한다.
	 * @return String
	 */
	public String getUniqId() {
		return uniqId;
	}
	/**
	 * uniqId attribute 값을 설정한다.
	 * @param uniqId String
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}
	/**
	 * userTy attribute 값을  리턴한다.
	 * @return String
	 */
	public String getUserTy() {
		return userTy;
	}
	/**
	 * userTy attribute 값을 설정한다.
	 * @param userTy String
	 */
	public void setUserTy(String userTy) {
		this.userTy = userTy;
	}
	/**
	 * adres attribute 값을  리턴한다.
	 * @return String
	 */
	public String getAdres() {
		return adres;
	}
	/**
	 * adres attribute 값을 설정한다.
	 * @param adres String
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}
	/**
	 * detailAdres attribute 값을  리턴한다.
	 * @return String
	 */
	public String getDetailAdres() {
		return detailAdres;
	}
	/**
	 * detailAdres attribute 값을 설정한다.
	 * @param detailAdres String
	 */
	public void setDetailAdres(String detailAdres) {
		this.detailAdres = detailAdres;
	}
	/**
	 * applcntIhidnum attribute 값을  리턴한다.
	 * @return String
	 */
	public String getApplcntIhidnum() {
		return applcntIhidnum;
	}
	/**
	 * applcntIhidnum attribute 값을 설정한다.
	 * @param applcntIhidnum String
	 */
	public void setApplcntIhidnum(String applcntIhidnum) {
		this.applcntIhidnum = applcntIhidnum;
	}
	/**
	 * applcntNm attribute 값을  리턴한다.
	 * @return String
	 */
	public String getApplcntNm() {
		return applcntNm;
	}
	/**
	 * applcntNm attribute 값을 설정한다.
	 * @param applcntNm String
	 */
	public void setApplcntNm(String applcntNm) {
		this.applcntNm = applcntNm;
	}
	/**
	 * bizrno attribute 값을  리턴한다.
	 * @return String
	 */
	public String getBizrno() {
		return bizrno;
	}
	/**
	 * bizrno attribute 값을 설정한다.
	 * @param bizrno String
	 */
	public void setBizrno(String bizrno) {
		this.bizrno = bizrno;
	}
	/**
	 * cmpnyNm attribute 값을  리턴한다.
	 * @return String
	 */
	public String getCmpnyNm() {
		return cmpnyNm;
	}
	/**
	 * cmpnyNm attribute 값을 설정한다.
	 * @param cmpnyNm String
	 */
	public void setCmpnyNm(String cmpnyNm) {
		this.cmpnyNm = cmpnyNm;
	}
	/**
	 * cxfc attribute 값을  리턴한다.
	 * @return String
	 */
	public String getCxfc() {
		return cxfc;
	}
	/**
	 * cxfc attribute 값을 설정한다.
	 * @param cxfc String
	 */
	public void setCxfc(String cxfc) {
		this.cxfc = cxfc;
	}
	/**
	 * entrprsmberId attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsmberId() {
		return entrprsmberId;
	}
	/**
	 * entrprsmberId attribute 값을 설정한다.
	 * @param entrprsmberId String
	 */
	public void setEntrprsmberId(String entrprsmberId) {
		this.entrprsmberId = entrprsmberId;
	}
	/**
	 * entrprsMberPassword attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsMberPassword() {
		return entrprsMberPassword;
	}
	/**
	 * entrprsMberPassword attribute 값을 설정한다.
	 * @param entrprsMberPassword String
	 */
	public void setEntrprsMberPassword(String entrprsMberPassword) {
		this.entrprsMberPassword = entrprsMberPassword;
	}
	/**
	 * entrprsMberPasswordCnsr attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsMberPasswordCnsr() {
		return entrprsMberPasswordCnsr;
	}
	/**
	 * entrprsMberPasswordCnsr attribute 값을 설정한다.
	 * @param entrprsMberPasswordCnsr String
	 */
	public void setEntrprsMberPasswordCnsr(String entrprsMberPasswordCnsr) {
		this.entrprsMberPasswordCnsr = entrprsMberPasswordCnsr;
	}
	/**
	 * entrprsMberPasswordHint attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsMberPasswordHint() {
		return entrprsMberPasswordHint;
	}
	/**
	 * entrprsMberPasswordHint attribute 값을 설정한다.
	 * @param entrprsMberPasswordHint String
	 */
	public void setEntrprsMberPasswordHint(String entrprsMberPasswordHint) {
		this.entrprsMberPasswordHint = entrprsMberPasswordHint;
	}
	/**
	 * entrprsMberSttus attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsMberSttus() {
		return entrprsMberSttus;
	}
	/**
	 * entrprsMberSttus attribute 값을 설정한다.
	 * @param entrprsMberSttus String
	 */
	public void setEntrprsMberSttus(String entrprsMberSttus) {
		this.entrprsMberSttus = entrprsMberSttus;
	}
	/**
	 * entrprsSeCode attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsSeCode() {
		return entrprsSeCode;
	}
	/**
	 * entrprsSeCode attribute 값을 설정한다.
	 * @param entrprsSeCode String
	 */
	public void setEntrprsSeCode(String entrprsSeCode) {
		this.entrprsSeCode = entrprsSeCode;
	}
	/**
	 * fxnum attribute 값을  리턴한다.
	 * @return String
	 */
	public String getFxnum() {
		return fxnum;
	}
	/**
	 * fxnum attribute 값을 설정한다.
	 * @param fxnum String
	 */
	public void setFxnum(String fxnum) {
		this.fxnum = fxnum;
	}
	/**
	 * groupId attribute 값을  리턴한다.
	 * @return String
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * groupId attribute 값을 설정한다.
	 * @param groupId String
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * indutyCode attribute 값을  리턴한다.
	 * @return String
	 */
	public String getIndutyCode() {
		return indutyCode;
	}
	/**
	 * indutyCode attribute 값을 설정한다.
	 * @param indutyCode String
	 */
	public void setIndutyCode(String indutyCode) {
		this.indutyCode = indutyCode;
	}
	/**
	 * jurirno attribute 값을  리턴한다.
	 * @return String
	 */
	public String getJurirno() {
		return jurirno;
	}
	/**
	 * jurirno attribute 값을 설정한다.
	 * @param jurirno String
	 */
	public void setJurirno(String jurirno) {
		this.jurirno = jurirno;
	}
	/**
	 * areaNo attribute 값을  리턴한다.
	 * @return String
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * areaNo attribute 값을 설정한다.
	 * @param areaNo String
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	/**
	 * entrprsEndTelno attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsEndTelno() {
		return entrprsEndTelno;
	}
	/**
	 * entrprsEndTelno attribute 값을 설정한다.
	 * @param entrprsEndTelno String
	 */
	public void setEntrprsEndTelno(String entrprsEndTelno) {
		this.entrprsEndTelno = entrprsEndTelno;
	}
	/**
	 * entrprsMiddleTelno attribute 값을  리턴한다.
	 * @return String
	 */
	public String getEntrprsMiddleTelno() {
		return entrprsMiddleTelno;
	}
	/**
	 * entrprsMiddleTelno attribute 값을 설정한다.
	 * @param entrprsMiddleTelno String
	 */
	public void setEntrprsMiddleTelno(String entrprsMiddleTelno) {
		this.entrprsMiddleTelno = entrprsMiddleTelno;
	}
	/**
	 * sbscrbDe attribute 값을  리턴한다.
	 * @return String
	 */
	public String getSbscrbDe() {
		return sbscrbDe;
	}
	/**
	 * sbscrbDe attribute 값을 설정한다.
	 * @param sbscrbDe String
	 */
	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}
	/**
	 * zip attribute 값을  리턴한다.
	 * @return String
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * zip attribute 값을 설정한다.
	 * @param zip String
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * applcntEmailAdres attribute 값을  리턴한다.
	 * @return String
	 */
	public String getApplcntEmailAdres() {
		return applcntEmailAdres;
	}
	/**
	 * applcntEmailAdres attribute 값을 설정한다.
	 * @param applcntEmailAdres String
	 */
	public void setApplcntEmailAdres(String applcntEmailAdres) {
		this.applcntEmailAdres = applcntEmailAdres;
	}


}