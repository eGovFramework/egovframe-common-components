package egovframework.com.sym.sym.srv.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 개요
 * - 서버정보에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버정보의 서버ID, 서버명, 서버종류 등의 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 28-6-2010 오전 10:44:54
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.28   이문준     최초 생성
 *  2025.05.21   dasomel    Lombok @Getter/@Setter 적용
 * </pre>
 */
@Getter
@Setter
public class Server extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	/**
	 * 서버 ID
	 */
	private String serverId;
	/**
	 * 서버 명
	 */
	@EgovNullCheck
	@Size(max=30)
	private String serverNm;
	/**
	 * 서버 종류
	 */
	private String serverKnd;
	/**
	 * 서버 종류명
	 */
	private String serverKndNm;
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