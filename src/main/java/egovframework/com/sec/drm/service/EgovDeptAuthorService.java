package egovframework.com.sec.drm.service;

import java.util.List;


/**
 * 부서권한관리에 관한 서비스 인터페이스 클래스를 정의한다.
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

public interface EgovDeptAuthorService {

	/**
	 * 부서별 할당된 권한목록 조회
	 * @param deptAuthorVO DeptAuthorVO
	 * @return List<DeptAuthorVO>
	 * @exception Exception
	 */
	public List<DeptAuthorVO> selectDeptAuthorList(DeptAuthorVO deptAuthorVO) throws Exception;

	/**
	 * 부서에 해당하는 사용자에게 시스템 메뉴/접근권한을 일괄 할당
	 * @param deptAuthor DeptAuthor
	 * @exception Exception
	 */
	public void insertDeptAuthor(DeptAuthor deptAuthor) throws Exception;

	/**
	 * 부서별 시스템 메뉴 접근권한을 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param deptAuthor
	 * @exception Exception
	 */
	public void updateDeptAuthor(DeptAuthor deptAuthor) throws Exception;

	/**
	 * 불필요한 부서권한를 조회하여 데이터베이스에서 삭제
	 * @param deptAuthor DeptAuthor
	 * @exception Exception
	 */
	public void deleteDeptAuthor(DeptAuthor deptAuthor) throws Exception;

	/**
	 * 여러 사용자에 대한 부서 권한 배정을 배정여부(regYn)에 따라 일괄 처리한다.
	 * 항목마다 등록(regYn=N) 또는 수정(그 외)을 수행하며, 전체 목록을 하나의 트랜잭션으로 처리한다.
	 * @param deptAuthor DeptAuthor(공통 필드가 채워진 커맨드 객체)
	 * @param userIds 처리할 사용자 ID 배열
	 * @param authorCodes userIds 와 같은 순서의 권한코드 배열
	 * @param regYns userIds 와 같은 순서의 배정여부 배열
	 * @exception Exception
	 */
	public void updateDeptAuthorList(DeptAuthor deptAuthor, String[] userIds, String[] authorCodes, String[] regYns) throws Exception;

	/**
	 * 여러 사용자에 대한 부서 권한을 일괄 삭제한다. 전체 목록을 하나의 트랜잭션으로 처리한다.
	 * @param deptAuthor DeptAuthor(공통 필드가 채워진 커맨드 객체)
	 * @param userIds 삭제할 사용자 ID 배열
	 * @exception Exception
	 */
	public void deleteDeptAuthorList(DeptAuthor deptAuthor, String[] userIds) throws Exception;

    /**
	 * 부서권한 목록조회 카운트를 반환한다
	 * @param deptAuthorVO DeptAuthorVO
	 * @return int
	 * @exception Exception
	 */
	public int selectDeptAuthorListTotCnt(DeptAuthorVO deptAuthorVO) throws Exception;		
	
	/**
	 * 부서목록 조회
	 * @param deptAuthorVO DeptAuthorVO
	 * @return List<DeptAuthorVO>
	 * @exception Exception
	 */
	public List<DeptAuthorVO> selectDeptList(DeptAuthorVO deptAuthorVO) throws Exception;	
	
    /**
	 * 부서 목록조회 카운트를 반환한다
	 * @param deptAuthorVO DeptAuthorVO
	 * @return int
	 * @exception Exception
	 */
	public int selectDeptListTotCnt(DeptAuthorVO deptAuthorVO) throws Exception;	
}