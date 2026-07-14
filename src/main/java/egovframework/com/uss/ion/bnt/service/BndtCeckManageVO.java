package egovframework.com.uss.ion.bnt.service;

import java.util.List;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 당직체크관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 당직체크관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */
@Getter
@Setter
public class BndtCeckManageVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/**
	*  당직체크구분
	*/
	@EgovNullCheck
	@Size(max=10)
	private String bndtCeckSe;

	/**
	*  당직체크코드
	*/
	@EgovNullCheck
	@Size(max=10)
	private String bndtCeckCd;

	/**
	*  당직체크코드명
	*/
	@EgovNullCheck
	@Size(max=100)
	private String bndtCeckCdNm;

	/**
	*  사용여부
	*/
	@EgovNullCheck
	private String useAt;

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
	 * 당직체크리스트관리 목록
	 */
	List<BndtCeckManageVO> bndtCeckManageList;

	/**
	 * 당직체크리스트 Temp변수 1
	 */
	private String bndtCeckTemp1;

	/**
	 * 당직체크리스트 당직체크구분 조회조건 변수
	 */
	private String searchBndtCeckSe;

	/**
	 * 당직체크리스트 당직체크코드 조회조건 변수
	 */
	private String searchBndtCeckCd;

	/**
	 * 당직체크리스트 당직체크구분 조회조건 변수
	 */
	private String searchUseAt;

}
