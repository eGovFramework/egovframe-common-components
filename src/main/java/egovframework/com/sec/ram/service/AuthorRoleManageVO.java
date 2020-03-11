package egovframework.com.sec.ram.service;

import java.util.List;

/**
 * 권한별 롤 관리에 대한 Vo 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public class AuthorRoleManageVO extends AuthorRoleManage {

	private static final long serialVersionUID = 1L;

	List <AuthorRoleManageVO> authorRoleList;
	
	/**
	 * authorRoleList attribute 를 리턴한다.
	 * @return List<AuthorRoleManageVO>
	 */
	public List<AuthorRoleManageVO> getAuthorRoleList() {
		return authorRoleList;
	}

	/**
	 * authorRoleList attribute 값을 설정한다.
	 * @param authorRoleList List<AuthorRoleManageVO> 
	 */
	public void setAuthorRoleList(List<AuthorRoleManageVO> authorRoleList) {
		this.authorRoleList = authorRoleList;
	}



}