package egovframework.com.dam.spe.req.service;


/**
 * 지식정보제공/지식정보요청 Model and VO Class 구현
 * @author 공통서비스 장동한
 * @since 2010.08.30
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.30  장동한          최초 생성
 *
 * </pre>
 */
public class RequestOffer {

	/** 지식ID */
	private String knoId;

	/** 조식ID */
	private String orgnztId;

	/** 전문가ID */
	private String speId;

	/** 지식유형코드 */
	private String knoTypeCd;

	/** 사용자ID */
	private String emplyrId;

	/** 지식명 */
	private String knoNm;

	/** 지식내용 */
	private String knoCn;

	/** 첨부파일ID */
	private String atchFileId;

	/** 부모지식ID */
	private String ansParents;

	/** 답변깊이 */
	private Integer ansDepth;

	/** 답변순서 */
	private Integer ansSeq;

	/** 답변그룹번호 */
	private Integer ansNumber;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록아이디 */
    private String frstRegisterId;

    /** 최종수정일 */
    private String lastUpdusrPnttm;

    /** 최종수정자 아이디 */
    private String lastUpdusrId;

	/**
	 * @return the knoId
	 */
	public String getKnoId() {
		return knoId;
	}

	/**
	 * @param knoId the knoId to set
	 */
	public void setKnoId(String knoId) {
		this.knoId = knoId;
	}

	/**
	 * @return the orgnztId
	 */
	public String getOrgnztId() {
		return orgnztId;
	}

	/**
	 * @param orgnztId the orgnztId to set
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	/**
	 * @return the speId
	 */
	public String getSpeId() {
		return speId;
	}

	/**
	 * @param speId the speId to set
	 */
	public void setSpeId(String speId) {
		this.speId = speId;
	}

	/**
	 * @return the knoTypeCd
	 */
	public String getKnoTypeCd() {
		return knoTypeCd;
	}

	/**
	 * @param knoTypeCd the knoTypeCd to set
	 */
	public void setKnoTypeCd(String knoTypeCd) {
		this.knoTypeCd = knoTypeCd;
	}

	/**
	 * @return the emplyrId
	 */
	public String getEmplyrId() {
		return emplyrId;
	}

	/**
	 * @param emplyrId the emplyrId to set
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	/**
	 * @return the knoNm
	 */
	public String getKnoNm() {
		return knoNm;
	}

	/**
	 * @param knoNm the knoNm to set
	 */
	public void setKnoNm(String knoNm) {
		this.knoNm = knoNm;
	}

	/**
	 * @return the knoCn
	 */
	public String getKnoCn() {
		return knoCn;
	}

	/**
	 * @param knoCn the knoCn to set
	 */
	public void setKnoCn(String knoCn) {
		this.knoCn = knoCn;
	}

	/**
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * @param atchFileId the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * @return the ansParents
	 */
	public String getAnsParents() {
		return ansParents;
	}

	/**
	 * @param ansParents the ansParents to set
	 */
	public void setAnsParents(String ansParents) {
		this.ansParents = ansParents;
	}

	/**
	 * @return the ansDepth
	 */
	public Integer getAnsDepth() {
		return ansDepth;
	}

	/**
	 * @param ansDepth the ansDepth to set
	 */
	public void setAnsDepth(Integer ansDepth) {
		this.ansDepth = ansDepth;
	}

	/**
	 * @return the ansSeq
	 */
	public Integer getAnsSeq() {
		return ansSeq;
	}

	/**
	 * @param ansSeq the ansSeq to set
	 */
	public void setAnsSeq(Integer ansSeq) {
		this.ansSeq = ansSeq;
	}

	/**
	 * @return the ansNumber
	 */
	public Integer getAnsNumber() {
		return ansNumber;
	}

	/**
	 * @param ansNumber the ansNumber to set
	 */
	public void setAnsNumber(Integer ansNumber) {
		this.ansNumber = ansNumber;
	}

	/**
	 * @return the frstRegisterPnttm
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * @param frstRegisterPnttm the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * @return the frstRegisterId
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * @param frstRegisterId the frstRegisterId to set
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * @return the lastUpdusrPnttm
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * @param lastUpdusrPnttm the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * @return the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * @param lastUpdusrId the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}


}
