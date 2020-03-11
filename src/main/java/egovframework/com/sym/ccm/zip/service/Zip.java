package egovframework.com.sym.ccm.zip.service;

import java.io.Serializable;

/**
 * 우편번호 모델 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.11.21  이기하          도로명주소 추가(rdmn, bdnbrMnnm, bdnbrSlno, buldNm, detailBuldNm)
 *
 * </pre>
 */
public class Zip implements Serializable {

	private static final long serialVersionUID = -8767083970521429218L;

	/*
	 * 우편번호
	 */
    private String zip            = "";

    /*
     * 일련번호
     */
    private int    sn             = 0;

    /*
     * 시도명
     */
	private String ctprvnNm       = "";

	/*
	 * 시군구명
	 */
    private String signguNm       = "";

    /*
     * 읍면동명
     */
    private String emdNm          = "";

    /*
     * 리건물명
     */
    private String liBuldNm      = "";

    /*
     * 도로명코드
     */
    private String rdmnCode       = "";

	/*
     * 도로명
     */
    private String rdmn       = "";

    /*
     * 건물번호본번
     */
    private String bdnbrMnnm          = "";

    /*
     * 건물번호부번
     */
    private String bdnbrSlno      = "";

    /*
     * 건물명
     */
    private String buldNm      = "";

    /*
     * 상세건물명
     */
    private String detailBuldNm      = "";

    /*
     * 번지동호
     */
    private String lnbrDongHo     = "";

	/*
     * 최초등록자ID
     */
    private String frstRegisterId = "";

    /*
     * 최종수정자ID
     */
    private String lastUpdusrId   = "";

	/**
	 * zip attribute 를 리턴한다.
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
	 * sn attribute 를 리턴한다.
	 * @return int
	 */
	public int getSn() {
		return sn;
	}

	/**
	 * sn attribute 값을 설정한다.
	 * @param sn int
	 */
	public void setSn(int sn) {
		this.sn = sn;
	}

	/**
	 * ctprvnNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCtprvnNm() {
		return ctprvnNm;
	}

	/**
	 * ctprvnNm attribute 값을 설정한다.
	 * @param ctprvnNm String
	 */
	public void setCtprvnNm(String ctprvnNm) {
		this.ctprvnNm = ctprvnNm;
	}

	/**
	 * signguNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getSignguNm() {
		return signguNm;
	}

	/**
	 * signguNm attribute 값을 설정한다.
	 * @param signguNm String
	 */
	public void setSignguNm(String signguNm) {
		this.signguNm = signguNm;
	}

	/**
	 * emdNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getEmdNm() {
		return emdNm;
	}

	/**
	 * emdNm attribute 값을 설정한다.
	 * @param emdNm String
	 */
	public void setEmdNm(String emdNm) {
		this.emdNm = emdNm;
	}

	/**
	 * liBuldNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getLiBuldNm() {
		return liBuldNm;
	}

	/**
	 * liBuldNm attribute 값을 설정한다.
	 * @param liBuldNm String
	 */
	public void setLiBuldNm(String liBuldNm) {
		this.liBuldNm = liBuldNm;
	}

	/**
	 * lnbrDongHo attribute 를 리턴한다.
	 * @return String
	 */
	public String getLnbrDongHo() {
		return lnbrDongHo;
	}

	/**
	 * lnbrDongHo attribute 값을 설정한다.
	 * @param lnbrDongHo String
	 */
	public void setLnbrDongHo(String lnbrDongHo) {
		this.lnbrDongHo = lnbrDongHo;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getRdmn() {
		return rdmn;
	}

	public void setRdmn(String rdmn) {
		this.rdmn = rdmn;
	}

	public String getBdnbrMnnm() {
		return bdnbrMnnm;
	}

	public void setBdnbrMnnm(String bdnbrMnnm) {
		this.bdnbrMnnm = bdnbrMnnm;
	}

	public String getBdnbrSlno() {
		return bdnbrSlno;
	}

	public void setBdnbrSlno(String bdnbrSlno) {
		this.bdnbrSlno = bdnbrSlno;
	}

	public String getBuldNm() {
		return buldNm;
	}

	public void setBuldNm(String buldNm) {
		this.buldNm = buldNm;
	}

	public String getDetailBuldNm() {
		return detailBuldNm;
	}

	public void setDetailBuldNm(String detailBuldNm) {
		this.detailBuldNm = detailBuldNm;
	}

    public String getRdmnCode() {
		return rdmnCode;
	}

	public void setRdmnCode(String rdmnCode) {
		this.rdmnCode = rdmnCode;
	}
}
