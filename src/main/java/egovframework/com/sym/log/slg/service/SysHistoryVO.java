package egovframework.com.sym.log.slg.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * @Class Name : SysHistoryVO.java
 * @Description : 시스템 처리 이력관리를 위한 데이터 객체
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 9.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 9.
 * @version
 * @see
 *
 */
@Getter
@Setter
public class SysHistoryVO extends SysHistory {

	private static final long serialVersionUID = 3236791243469450106L;

	/**
	 * 최초 등록자 명
	 */
	private String frstRegisterNm = "";

	/**
	 * 최종 수정자 명
	 */
	private String lastUpdusrNm = "";

	/**
	 * 등록 구분코드 명
	 */
	private String histSeCodeNm = "";

	/**
	 * 첨부파일ID
	 */
	private String atchFileId = "";
	/**
	 * 검색시작일
	 */
	private String searchBgnDe = "";
	/**
	 * 검색조건
	 */
	private String searchCnd = "";
	/**
	 * 검색종료일
	 */
	private String searchEndDe = "";
	/**
	 * 검색단어
	 */
	private String searchWrd = "";
	/**
	 * 정렬순서(DESC,ASC)
	 */
	private String sortOrdr = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지개수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;

	/** recordCountPerPage */
	private int recordCountPerPage = 10;

	/** rowNo  */
	private int rowNo = 0;

	@Override
	public String getAtchFileId() {
		return atchFileId;
	}

	@Override
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
