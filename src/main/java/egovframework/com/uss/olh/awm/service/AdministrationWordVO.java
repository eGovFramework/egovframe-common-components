package egovframework.com.uss.olh.awm.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.com.cmm.ComDefaultVO;

import lombok.Getter;
import lombok.Setter;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
 *   2025.05.25  장동한          Lombok @Getter/@Setter 적용
 *
 * </pre>
 */
@Getter
@Setter
public class AdministrationWordVO extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = -5313141971613650852L;

	/** 행정용어사전 아이디 */
    private String administWordId;

    /** 행정용어사전 명 */
    @EgovNullCheck
    @Size(max=255)
    private String administWordNm;

    /** 행정용어사전 영문명 */
    @EgovNullCheck
    @Size(max=255)
    @Pattern(regexp="^[a-zA-Z0-9_]*$", message="{validation.pattern.english}")
    private String administWordEngNm;

    /** 행정용어사전 약어  */
    @EgovNullCheck
    @Size(max=255)
    private String administWordAbrv;

    /** 주제영역  */
    @EgovNullCheck
    private String themaRelm;

    /** 용어구분  */
    @EgovNullCheck
    private String wordDomn;

    /** 용어구분명  */
    private String wordDomnNm;

    /** 관련표준용어  */
    private String stdWord;

    /** 행정용어사전 정의 */
    @Size(max=2500)
    private String administWordDf;

    /** 행정용어사전 설명 */
    @Size(max=2500)
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
	 * toString 메소드를 대치한다.
	 */
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

}
