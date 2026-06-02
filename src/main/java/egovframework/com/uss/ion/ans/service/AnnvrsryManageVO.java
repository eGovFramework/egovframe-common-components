package egovframework.com.uss.ion.ans.service;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 기념일관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 기념일관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

//public class AnnvrsryManageVO extends AnnvrsryManage implements Serializable {
@Getter
@Setter
public class AnnvrsryManageVO extends AnnvrsryManage{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 기념일관리 목록
	 */	
	List<AnnvrsryManageVO> annvrsryManageList;

	/**
	*  list 번호      
	*/ 
	private int rowCount;

	/**
	*  출력 변수	1
	*/ 
	private String annvrsryTemp1;

	/**
	*  출력 변수	2
	*/ 
	private String annvrsryTemp2;

	/**
	*  출력 변수	3
	*/ 
	private String annvrsryTemp3;

	/**
	*  출력 변수	4
	*/ 
	private String annvrsryTemp4;

	/**
	*  출력 변수	5
	*/ 
	private String annvrsryTemp5;


}
