package egovframework.com.uss.olh.omm.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.com.cmm.ComDefaultVO;

import lombok.Getter;
import lombok.Setter;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

/**
 * 온라인메뉴얼 VO Class 구현
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
public class OnlineManualVO extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = -7024282928339275971L;

	/** 온라인메뉴얼 아이디 */
    private String onlineMnlId;

    /** 온라인메뉴얼 명 */
    @EgovNullCheck
    @Size(max=255)
    private String onlineMnlNm;

    /** 온라인메뉴얼 구분코드 */
    @EgovNullCheck
    private String onlineMnlSeCode;

    /** 온라인메뉴얼 구분코드 */
    private String onlineMnlSeCodeNm;

    /** 온라인메뉴얼 정의 */
    @EgovNullCheck
    private String onlineMnlDf;

    /** 온라인메뉴얼 설명 */
    @EgovNullCheck
    private String onlineMnlDc;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록아이디 */
    private String frstRegisterId;

    /** 최초등록자 */
    private String frstRegisterNm;

    /** 최종수정일 */
    private String lastUpdusrPnttm;

    /** 최종수정자 아이디 */
    private String lastUpdusrId;

    /** 컨트롤 명령어 */
    private String cmd;

    /**
  	 * toString 메소드를 대치한다.
  	 */
  	public String toString(){
  		return ToStringBuilder.reflectionToString(this);
  	}

}
