package egovframework.com.uss.ion.ans.service;

import egovframework.com.cmm.ComDefaultVO;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 기념일관리에 대한 model 클래스를 정의한다.
 *
 * 상세내용
 * - 기념일관리의 사용자ID,기념일명,기념일자,달력구분,알림설정,알림시작일자,메모,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Getter
@Setter
public class AnnvrsryManage extends ComDefaultVO {

	/**
	* serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
	*  기념일ID	      
	*/ 
	private String annId;

	
	/**
	*  사용자ID	      
	*/ 
	@EgovNullCheck
	private String usid;

	/**
	*  기념일구분	      
	*/ 
	@EgovNullCheck
	@Size(max=2)
	private String annvrsrySe;

	
	/**
	*  기념일명	      
	*/ 
	@EgovNullCheck
	@Size(max=255)
	private String annvrsryNm;

	/**
	*  기념일자	      
	*/ 
	@EgovNullCheck
	@Size(max=10)
	@Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="{validation.pattern.date}")
	private String annvrsryDe;

	/**
	*  달력구분	      
	*/ 
	@EgovNullCheck
	private String cldrSe;

	/**
	*  반복구분	      
	*/ 
	private String reptitSe;
	
	/**
	*  알림설정	      
	*/ 
	private String annvrsrySetup;

	/**
	*  알림시작일자	
	*/ 
	private String annvrsryBeginDe;

	/**
	*  메모	         
	*/ 
	@EgovNullCheck
	@Size(max=2500)
	private String memo;

	/**
	*  최초등록자ID	
	*/ 
	private String frstRegisterId;

	/**
	*  최초등록시점	
	*/ 
	private String frstRegisterPnttm;

	/**
	*  최종수정자ID	
	*/ 
	private String lastUpdusrId;

	/**
	*  최종수정시점	
	*/ 
	private String lastUpdusrPnttm;
	


}