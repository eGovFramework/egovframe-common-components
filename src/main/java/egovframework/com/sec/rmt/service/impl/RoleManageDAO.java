package egovframework.com.sec.rmt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sec.rmt.service.RoleManage;
import egovframework.com.sec.rmt.service.RoleManageVO;

/**
 * 롤관리에 대한 DAO 클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *   2024.08.06  이백행          시큐어코딩 Exception 제거
 *
 *      </pre>
 */

@Repository("roleManageDAO")
public class RoleManageDAO extends EgovComAbstractDAO {

	/**
	 * 등록된 롤 정보 조회
	 * 
	 * @param roleManageVO RoleManageVO
	 * @return RoleManageVO
	 */
	public RoleManageVO selectRole(RoleManageVO roleManageVO) {
		return (RoleManageVO) selectOne("roleManageDAO.selectRole", roleManageVO);
	}

	/**
	 * 등록된 롤 정보 목록 조회
	 * 
	 * @param roleManageVO RoleManageVO
	 * @return List<RoleManageVO>
	 */
	public List<RoleManageVO> selectRoleList(RoleManageVO roleManageVO) {
		return selectList("roleManageDAO.selectRoleList", roleManageVO);
	}

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
	 * 
	 * @param roleManage RoleManage
	 */
	public void insertRole(RoleManage roleManage) {
		insert("roleManageDAO.insertRole", roleManage);
	}

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
	 * 
	 * @param roleManage RoleManage
	 */
	public void updateRole(RoleManage roleManage) {
		update("roleManageDAO.updateRole", roleManage);
	}

	/**
	 * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param roleManage RoleManage
	 */
	public void deleteRole(RoleManage roleManage) {
		delete("roleManageDAO.deleteRole", roleManage);
	}

	/**
	 * 롤목록 총 개수를 조회한다.
	 * 
	 * @param roleManageVO RoleManageVO
	 * @return int
	 */
	public int selectRoleListTotCnt(RoleManageVO roleManageVO) {
		return (Integer) selectOne("roleManageDAO.selectAuthorListTotCnt", roleManageVO);
	}

	/**
	 * 등록된 모든 롤 정보 목록 조회
	 * 
	 * @param roleManageVO RoleManageVO
	 * @return List<RoleManageVO>
	 */
	public List<RoleManageVO> selectRoleAllList(RoleManageVO roleManageVO) {
		return selectList("roleManageDAO.selectRoleAllList", roleManageVO);
	}

}