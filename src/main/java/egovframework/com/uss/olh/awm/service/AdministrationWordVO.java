package egovframework.com.uss.olh.awm.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 행정전문용어사전관리 VO Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
public class AdministrationWordVO extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = -5313141971613650852L;

	/** 행정용어사전 아이디 */
    private String administWordId;

    /** 행정용어사전 명 */
    private String administWordNm;

    /** 행정용어사전 영문명 */
    private String administWordEngNm;

    /** 행정용어사전 약어  */
    private String administWordAbrv;

    /** 주제영역  */
    private String themaRelm;

    /** 용어구분  */
    private String wordDomn;
    
    /** 용어구분명  */
    private String wordDomnNm;

    /** 관련표준용어  */
    private String stdWord;

    /** 행정용어사전 정의 */
    private String administWordDf;

    /** 행정용어사전 설명 */
    private String administWordDc;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록아이디 */
    private String frstRegisterId;

    /** 최종수정일 */
    private String lastUpdusrPnttm;

    /** 최종수정자 아이디 */
    private String lastUpdusrId;

    /** 초성검색 */
    private String choseongA;

    /** 초성검색 */
    private String choseongB;

    /** 컨트롤 명령어 */
    private String cmd;


    /**
     * administWordId 리턴
     *
     * @return the administWordId
     */
    public String getAdministWordId() {
        return administWordId;
    }

    /**
     * administWordId 설정
     *
     * @param administWordId the administWordId to set
     */
    public void setAdministWordId(String administWordId) {
        this.administWordId = administWordId;
    }

    /**
     * administWordNm 리턴
     *
     * @return the administWordNm
     */
    public String getAdministWordNm() {
        return administWordNm;
    }

    /**
     * administWordNm 설정
     *
     * @param administWordNm the administWordNm to set
     */
    public void setAdministWordNm(String administWordNm) {
        this.administWordNm = administWordNm;
    }

    /**
     * administWordEngNm 리턴
     *
     * @return the administWordEngNm
     */
    public String getAdministWordEngNm() {
        return administWordEngNm;
    }

    /**
     * administWordEngNm 설정
     *
     * @param administWordEngNm the administWordEngNm to set
     */
    public void setAdministWordEngNm(String administWordEngNm) {
        this.administWordEngNm = administWordEngNm;
    }

    /**
     * administWordAbrv 리턴
     *
     * @return the administWordAbrv
     */
    public String getAdministWordAbrv() {
        return administWordAbrv;
    }

    /**
     * administWordAbrv 설정
     *
     * @param administWordAbrv the administWordAbrv to set
     */
    public void setAdministWordAbrv(String administWordAbrv) {
        this.administWordAbrv = administWordAbrv;
    }

    /**
     * themaRelm 리턴
     *
     * @return the themaRelm
     */
    public String getThemaRelm() {
        return themaRelm;
    }

    /**
     * themaRelm 설정
     *
     * @param themaRelm the themaRelm to set
     */
    public void setThemaRelm(String themaRelm) {
        this.themaRelm = themaRelm;
    }

    /**
     * wordDomn 리턴
     *
     * @return the wordDomn
     */
    public String getWordDomn() {
        return wordDomn;
    }

    /**
     * wordDomn 설정
     *
     * @param wordDomn the wordDomn to set
     */
    public void setWordDomn(String wordDomn) {
        this.wordDomn = wordDomn;
    }

    /**
     * wordDomnNm 리턴
     *
     * @return the wordDomn
     */
    public String getWordDomnNm() {
    	return wordDomnNm;
    }
    
    /**
     * wordDomnNm 설정
     *
     * @param wordDomnNm the wordDomnNm to set
     */
    public void setWordDomnNm(String wordDomnNm) {
    	this.wordDomnNm = wordDomnNm;
    }
    
    /**
     * stdWord 리턴
     *
     * @return the stdWord
     */
    public String getStdWord() {
        return stdWord;
    }

    /**
     * stdWord 설정
     *
     * @param stdWord the stdWord to set
     */
    public void setStdWord(String stdWord) {
        this.stdWord = stdWord;
    }

    /**
     * administWordDf 리턴
     *
     * @return the administWordDf
     */
    public String getAdministWordDf() {
        return administWordDf;
    }

    /**
     * administWordDf 설정
     *
     * @param administWordDf the administWordDf to set
     */
    public void setAdministWordDf(String administWordDf) {
        this.administWordDf = administWordDf;
    }

    /**
     * administWordDc 리턴
     *
     * @return the administWordDc
     */
    public String getAdministWordDc() {
        return administWordDc;
    }

    /**
     * administWordDc 설정
     *
     * @param administWordDc the administWordDc to set
     */
    public void setAdministWordDc(String administWordDc) {
        this.administWordDc = administWordDc;
    }

    /**
     * frstRegisterPnttm 리턴
     *
     * @return the frstRegisterPnttm
     */
    public String getFrstRegisterPnttm() {
        return frstRegisterPnttm;
    }

    /**
     * frstRegisterPnttm 설정
     *
     * @param frstRegisterPnttm the frstRegisterPnttm to set
     */
    public void setFrstRegisterPnttm(String frstRegisterPnttm) {
        this.frstRegisterPnttm = frstRegisterPnttm;
    }

    /**
     * frstRegisterId 리턴
     *
     * @return the frstRegisterId
     */
    public String getFrstRegisterId() {
        return frstRegisterId;
    }

    /**
     * frstRegisterId 설정
     *
     * @param frstRegisterId the frstRegisterId to set
     */
    public void setFrstRegisterId(String frstRegisterId) {
        this.frstRegisterId = frstRegisterId;
    }

    /**
     * lastUpdusrPnttm 리턴
     *
     * @return the lastUpdusrPnttm
     */
    public String getLastUpdusrPnttm() {
        return lastUpdusrPnttm;
    }

    /**
     * lastUpdusrPnttm 설정
     *
     * @param lastUpdusrPnttm the lastUpdusrPnttm to set
     */
    public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
        this.lastUpdusrPnttm = lastUpdusrPnttm;
    }

    /**
     * lastUpdusrId 리턴
     *
     * @return the lastUpdusrId
     */
    public String getLastUpdusrId() {
        return lastUpdusrId;
    }

    /**
     * lastUpdusrId 설정
     *
     * @param lastUpdusrId the lastUpdusrId to set
     */
    public void setLastUpdusrId(String lastUpdusrId) {
        this.lastUpdusrId = lastUpdusrId;
    }

    /**
     * choseongA 리턴
     *
     * @return the choseongA
     */
    public String getChoseongA() {
        return choseongA;
    }

    /**
     * choseongA 설정
     *
     * @param choseongA the choseongA to set
     */
    public void setChoseongA(String choseongA) {
        this.choseongA = choseongA;
    }

    /**
     * choseongB 리턴
     *
     * @return the choseongB
     */
    public String getChoseongB() {
        return choseongB;
    }

    /**
     * choseongB 설정
     *
     * @param choseongB the choseongB to set
     */
    public void setChoseongB(String choseongB) {
        this.choseongB = choseongB;
    }

    /**
     * cmd 리턴
     *
     * @return the cmd
     */
    public String getCmd() {
        return cmd;
    }

    /**
     * cmd 설정
     *
     * @param cmd the cmd to set
     */
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
	 * toString 메소드를 대치한다.
	 */
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

}