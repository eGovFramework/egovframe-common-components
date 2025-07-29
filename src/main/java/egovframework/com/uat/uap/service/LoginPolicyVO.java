package egovframework.com.uat.uap.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 개요
 * - 로그인정책에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인정책정보의 목록 항목을 관리한다.
 * </pre>
 * 
 * @author 공통컴포넌트 개발팀 이문준
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
 *   2025.07.30  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-MethodReturnsInternalArray(Private 배열에 Public 데이터 할당)
 *   2025.07.30  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-ArrayIsStoredDirectly(Public 메소드부터 반환된 Private 배열)
 *
 *      </pre>
 */
public class LoginPolicyVO extends LoginPolicy {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 로그인 정책 목록
	 */
	private List<LoginPolicyVO> loginPolicyList;

	/**
	 * 삭제 여부
	 */
	@Getter
	@Setter
	private String[] delYn;

	/**
	 * @return the loginPolicyList
	 */
	public List<LoginPolicyVO> getLoginPolicyList() {
		return loginPolicyList;
	}

	/**
	 * @param loginPolicyList the loginPolicyList to set
	 */
	public void setLoginPolicyList(List<LoginPolicyVO> loginPolicyList) {
		this.loginPolicyList = loginPolicyList;
	}

}
