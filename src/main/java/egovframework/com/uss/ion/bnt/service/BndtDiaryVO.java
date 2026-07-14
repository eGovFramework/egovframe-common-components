package egovframework.com.uss.ion.bnt.service;

import java.util.List;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 당직일지에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 당직일지의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */
@Getter
@Setter
public class BndtDiaryVO extends ComDefaultVO {

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
	@Size(max=8)
	@Pattern(regexp="^\\d{8}$", message="{validation.pattern.date}")
	private String bndtDe;

	/**
	*  당직체크구분
	*/
	private String bndtCeckSe;

	/**
	*  당직체크코드
	*/
	private String bndtCeckCd;

	/**
	*  당직점검상태
	*/
	private String chckSttus;

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
	List<BndtDiaryVO> bndtDiaryList;

	/**
	*  당직체크코드명
	*/
	private String bndtCeckCdNm;

}
