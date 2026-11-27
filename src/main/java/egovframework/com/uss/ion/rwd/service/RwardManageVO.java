package egovframework.com.uss.ion.rwd.service;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 포상관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 포상관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */
@Getter
@Setter
public class RwardManageVO extends RwardManage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 포상자 목록
	 */
	List<RwardManageVO> rwardManageList;

	/**
	*  포상자명
	*/
	private String rwardManNm;

	/**
	*  승인자명
	*/
	private String sanctnerNm;

	/**
	*  포상코드명
	*/
	private String rwardCdNm;

	/**
	*  사용자 소속명
	*/
	private String orgnztNm;

	/**
	*  승인자 소속명
	*/
	private String sanctnerOrgnztNm;

	/**
	*  검색시작일자
	*/
	private String searchFromDate;

	/**
	*  검색종료일자
	*/
	private String searchToDate;

	/**
	*  검색 성명
	*/
	private String searchNm;

	/**
	*  검색 진행구분
	*/
	private String searchConfmAt;

	/**
	*  searchToDateView
	*/
	private String searchToDateView;

	/**
	*  searchFromDateView
	*/
	private String searchFromDateView;

}
