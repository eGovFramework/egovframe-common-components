package egovframework.com.utl.sec.service;

import java.io.Serializable;

/**
 * 인증서 정보 Value Object
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.08.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.06  한성곤          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class CertInfoVO implements Serializable {
    /** 인증서 DN */
    private String subjectDn = null;
    /** 발급자 DN */
    private String issuerDn = null;
    
    /**
     * subjectDn attribute를 리턴한다.
     * @return the subjectDn
     */
    public String getSubjectDn() {
        return subjectDn;
    }
    /**
     * subjectDn attribute 값을 설정한다.
     * @param subjectDn the subjectDn to set
     */
    public void setSubjectDn(String subjectDn) {
        this.subjectDn = subjectDn;
    }
    /**
     * issuerDn attribute를 리턴한다.
     * @return the issuerDn
     */
    public String getIssuerDn() {
        return issuerDn;
    }
    /**
     * issuerDn attribute 값을 설정한다.
     * @param issuerDn the issuerDn to set
     */
    public void setIssuerDn(String issuerDn) {
        this.issuerDn = issuerDn;
    }
    
}
