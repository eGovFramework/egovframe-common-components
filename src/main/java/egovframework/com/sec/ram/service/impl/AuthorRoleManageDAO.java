package egovframework.com.sec.ram.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sec.ram.service.AuthorRoleManage;
import egovframework.com.sec.ram.service.AuthorRoleManageVO;

/**
 * 권한별 롤관리에 대한 DAO 인터페이스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자               수정내용
 *  ----------   ---------   ---------------------------
 *  2009.03.11   이문준              최초 생성
 *  2021.02.09   신용호              updateAuthorRole 삭제
 *  2026.05.28   dasomel            @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */

@EgovMapper("authorRoleManageDAO")
public interface AuthorRoleManageDAO {

	/**
	 * 권한 롤 관계정보 목록 조회
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return List<AuthorRoleManageVO>
	 */
	List<AuthorRoleManageVO> selectAuthorRoleList(AuthorRoleManageVO authorRoleManageVO);

	/**
	 * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param authorRoleManage AuthorRoleManage
	 */
	void insertAuthorRole(AuthorRoleManage authorRoleManage);

	/**
	 * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param authorRoleManage AuthorRoleManage
	 */
	void deleteAuthorRole(AuthorRoleManage authorRoleManage);

	/**
	 * 목록조회 카운트를 반환한다
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return int
	 */
	int selectAuthorRoleListTotCnt(AuthorRoleManageVO authorRoleManageVO);
}
