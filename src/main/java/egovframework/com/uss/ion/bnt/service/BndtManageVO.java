package egovframework.com.uss.ion.bnt.service;

import java.util.List;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 당직관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 당직관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */
@Getter
@Setter
public class BndtManageVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	*  당직ID
	*/
	@EgovNullCheck
	@Size(max=20)
	private String bndtId;

	/**
	*  당직일자
	*/
	@EgovNullCheck
	private String bndtDe;

	/**
	*  비고
	*/
	@Size(max=2500)
	private String remark;

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

	/**
	 * 배너 목록
	 */
	List<BndtManageVO> bndtManageList;

	/**
	 * 당직 bndtTemp1
	 */
	private String bndtTemp1;

	/**
	 * 당직 bndtTemp2
	 */
	private String bndtTemp2;

	/**
	 * 당직 tempBndtNm
	 */
	private String tempBndtNm;

	/**
	 * 당직 tempBndtId
	 */
	private String tempBndtId;

	/**
	 * 당직 tempBndtWeek
	 */
	private String tempBndtWeek;

	/**
	 * 당직 tempOrgnztNm
	 */
	private String tempOrgnztNm;

	/**
	 * 당직 tempCount
	 */
	private int tempCount;

	/**
	 * 당직 dateWeek
	 */
	private int dateWeek;

}
