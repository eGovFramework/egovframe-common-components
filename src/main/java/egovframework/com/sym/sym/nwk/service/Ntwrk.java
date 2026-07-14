/**
 * 개요
 * - 네트워크정보에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 장애관리의 네트워크ID, 네트워크IP, 게이트웨이, SUBNET, 도메인이름서버, 관리항목, 사용자명, 사용여부, 
 *   최종수정자ID, 최종수정시점 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 01-7-2010 오전 10:44:57
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.07.01   lee.m.j    최초 생성
 *  2025.05.21   dasomel    Lombok @Getter/@Setter 적용
 * </pre>
 */

package egovframework.com.sym.sym.nwk.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import egovframework.com.cmm.ComDefaultVO;

@Getter
@Setter
public class Ntwrk extends ComDefaultVO {

    private static final long serialVersionUID = 1L;
	/**
	 * 네트워크ID
	 */
    private String ntwrkId;
	/**
	 * 네트워크IP
	 */
	@EgovNullCheck
	@Size(max=23)
    private String ntwrkIp;
    /**
	 * 게이트웨이
	 */
	@EgovNullCheck
	@Size(max=23)
    private String gtwy;
    /**
	 * SUBNET
	 */
	@EgovNullCheck
	@Size(max=23)
    private String subnet;
    /**
	 * 도메인이름서버
	 */
	@EgovNullCheck
	@Size(max=23)
    private String domnServer;
    /**
	 * 관리항목
	 */
	@EgovNullCheck
    private String manageIem;
    /**
	 * 사용자명
	 */
	@EgovNullCheck
	@Size(max=30)
    private String userNm;
    /**
	 * 사용여부
	 */
	@EgovNullCheck
    private String useAt;
    /**
	 * 등록일자
	 */
	@EgovNullCheck
    private String regstYmd;    
    /**
	 * 최초등록시점
	 */   
    private String frstRegisterPnttm;
    /**
	 * 최초등록자ID
	 */        
    private String frstRegisterId;
    /**
	 * 최종수정시점
	 */    
    private String lastUpdusrPnttm;
    /**
	 * 최종수정자ID
	 */        
    private String lastUpdusrId;
}
