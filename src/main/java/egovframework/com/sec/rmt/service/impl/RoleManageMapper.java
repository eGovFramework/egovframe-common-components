package egovframework.com.sec.rmt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sec.rmt.service.RoleManage;
import egovframework.com.sec.rmt.service.RoleManageVO;

/**
 * 롤관리에 대한 MyBatis Mapper 인터페이스를 정의한다.
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
 *   2009.03.11  이문준          최초 생성
 *   2025.05.28  표준프레임워크센터  @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("roleManageMapper")
public interface RoleManageMapper {

	/**
	 * 등록된 롤 정보 조회
	 * @param roleManageVO RoleManageVO
	 * @return RoleManageVO
	 */
	RoleManageVO selectRole(RoleManageVO roleManageVO);

	/**
	 * 등록된 롤 정보 목록 조회
	 * @param roleManageVO RoleManageVO
	 * @return List&lt;RoleManageVO&gt;
	 */
	List<RoleManageVO> selectRoleList(RoleManageVO roleManageVO);

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
	 * @param roleManage RoleManage
	 */
	void insertRole(RoleManage roleManage);

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
	 * @param roleManage RoleManage
	 */
	void updateRole(RoleManage roleManage);

	/**
	 * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param roleManage RoleManage
	 */
	void deleteRole(RoleManage roleManage);

	/**
	 * 롤목록 총 개수를 조회한다.
	 * @param roleManageVO RoleManageVO
	 * @return int
	 */
	int selectRoleListTotCnt(RoleManageVO roleManageVO);

	/**
	 * 등록된 모든 롤 정보 목록 조회
	 * @param roleManageVO RoleManageVO
	 * @return List&lt;RoleManageVO&gt;
	 */
	List<RoleManageVO> selectRoleAllList(RoleManageVO roleManageVO);

}
