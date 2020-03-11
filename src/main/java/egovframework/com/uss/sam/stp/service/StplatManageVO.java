package egovframework.com.uss.sam.stp.service;

/**
 * 
 * 약관내용을 처리하는 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *   2016.06.13  장동한          표준프레임워크 v3.6 개선
 *
 * </pre>
 */
public class StplatManageVO extends StplatManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 이용약관 ID */
    private String useStplatId;
    
	/** 이용약관명 */
    private String useStplatNm;    
    
    /** 이용약관내용 */
    private String useStplatCn;
    
    /** 정보제공동의내용 */
    private String infoProvdAgreCn;
    
    /** 최초등록시점 */
    private String frstRegistPnttm;
        
    /** 최초등록시점 */
    private String frstRegisterNm;
    
    /** 최초등록시점 */
    private String frstRegisterPnttm;
    
    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;
    
    /** 최종수정시점 */
    private String lastUpdtPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

	public String getUseStplatId() {
		return useStplatId;
	}

	public void setUseStplatId(String useStplatId) {
		this.useStplatId = useStplatId;
	}

	public String getUseStplatNm() {
		return useStplatNm;
	}

	public void setUseStplatNm(String useStplatNm) {
		this.useStplatNm = useStplatNm;
	}

	public String getUseStplatCn() {
		return useStplatCn;
	}

	public void setUseStplatCn(String useStplatCn) {
		this.useStplatCn = useStplatCn;
	}

	public String getInfoProvdAgreCn() {
		return infoProvdAgreCn;
	}

	public void setInfoProvdAgreCn(String infoProvdAgreCn) {
		this.infoProvdAgreCn = infoProvdAgreCn;
	}

	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}

	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}

	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

}
