package egovframework.com.uss.ion.uas.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 개요
 * - 사용자부재에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 사용자부재의 목록 항목을 관리한다.
 * </pre>
 * 
 * @author 이문준
 * @since 2009.08.03
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.03  이문준          최초 생성
 *   2025.08.16  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-MethodReturnsInternalArray(Private 배열에 Public 데이터 할당)
 *   2025.08.16  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-ArrayIsStoredDirectly(Public 메소드부터 반환된 Private 배열)
 *
 *      </pre>
 */
public class UserAbsnceVO extends UserAbsnce {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 사용자부재 목록
	 */
	private List<UserAbsnceVO> userAbsnceList;

	/**
	 * 삭제대상 목록
	 */
	@Getter
	@Setter
	private String[] delYn;

	/**
	 * 부재여부 조회조건
	 */
	private String selAbsnceAt;

	/**
	 * @return the userAbsnceList
	 */
	public List<UserAbsnceVO> getUserAbsnceList() {
		return userAbsnceList;
	}

	/**
	 * @param userAbsnceList the userAbsnceList to set
	 */
	public void setUserAbsnceList(List<UserAbsnceVO> userAbsnceList) {
		this.userAbsnceList = userAbsnceList;
	}

	/**
	 * @return the selAbsnceAt
	 */
	public String getSelAbsnceAt() {
		return selAbsnceAt;
	}

	/**
	 * @param selAbsnceAt the selAbsnceAt to set
	 */
	public void setSelAbsnceAt(String selAbsnceAt) {
		this.selAbsnceAt = selAbsnceAt;
	}

}
