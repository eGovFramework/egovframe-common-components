package egovframework.com.sec.ram.service;

import java.util.List;

/**
 * 권한별 롤 관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일                수정자             수정내용
 *  ----------   ---------   ---------------------------
 *  2009.03.20   이문준              최초 생성
 *  2021.02-09   신용호              updateAuthorRole 삭제
 *
 * </pre>
 */

public interface EgovAuthorRoleManageService {

	/**
	 * 권한 롤 관계정보 목록 조회
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return List<AuthorRoleManageVO>
	 * @exception Exception
	 */
	public List<AuthorRoleManageVO> selectAuthorRoleList(AuthorRoleManageVO authorRoleManageVO) throws Exception;
	
	/**
	 * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param authorRoleManage AuthorRoleManage
	 * @exception Exception
	 */
	public void insertAuthorRole(AuthorRoleManage authorRoleManage) throws Exception;
	
	/**
	 * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param authorRoleManage AuthorRoleManage
	 * @exception Exception
	 */
	public void deleteAuthorRole(AuthorRoleManage authorRoleManage) throws Exception;

	/**
	 * 여러 롤코드에 대한 권한 배정을 배정여부(regYn)에 따라 일괄 처리한다.
	 * 항목마다 기존 배정을 삭제하고, 배정(Y)이면 다시 등록하며, 전체 목록을 하나의 트랜잭션으로 처리한다.
	 * @param authorRoleManage AuthorRoleManage(공통 필드가 채워진 커맨드 객체)
	 * @param roleCodes 처리할 롤코드 배열
	 * @param regYns roleCodes 와 같은 순서의 배정여부(Y/N) 배열
	 * @exception Exception
	 */
	public void updateAuthorRoleList(AuthorRoleManage authorRoleManage, String[] roleCodes, String[] regYns) throws Exception;

    /**
	 * 목록조회 카운트를 반환한다
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectAuthorRoleListTotCnt(AuthorRoleManageVO authorRoleManageVO) throws Exception;	

}
