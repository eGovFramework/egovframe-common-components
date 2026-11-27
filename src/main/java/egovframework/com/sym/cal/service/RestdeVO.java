package egovframework.com.sym.cal.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 휴일 VO 클래스
 *
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2024.10.29  권태성          날짜 검색 시 검색어를 DB에 저장된 포맷으로 변환하여 반환
 *   2025.07.04  이백행          컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UselessParentheses(불필요한 괄호사용)
 *
 *      </pre>
 */
@Getter
@Setter
public class RestdeVO extends Restde implements Serializable {

	private static final long serialVersionUID = 2548377950888283294L;

	/** 검색조건 */
	private String searchCondition = "";

	/** 검색Keyword */
	private String searchKeyword = "";

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

	/**
	 * 날짜 검색 시 검색어를 DB에 저장된 포맷으로 변환하여 반환
	 *
	 * @return
	 */
	public String getFormattedDtKeyword() {
		return this.searchKeyword != null ? this.searchKeyword.replace("-", "") : "";
	}

}
