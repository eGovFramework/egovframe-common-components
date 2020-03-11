package egovframework.com.uss.umt.service;

import java.util.List;

public interface EgovDeptManageService {

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * @param deptManageVO - 부서 Vo
	 * @return List - 부서 목록
	 * 
	 * @param deptManageVO
	 */
	public List<DeptManageVO> selectDeptManageList(DeptManageVO deptManageVO) throws Exception;

	/**
	 * 부서목록 총 갯수를 조회한다.
	 * @param deptManageVO - 부서 Vo
	 * @return int - 부서 카운트 수
	 * 
	 * @param deptManageVO
	 */
	public int selectDeptManageListTotCnt(DeptManageVO deptManageVO) throws Exception;
	
	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param deptManageVO - 부서 Vo
	 * @return deptManageVO - 부서 Vo
	 * 
	 * @param deptManageVO
	 */
	public DeptManageVO selectDeptManage(DeptManageVO deptManageVO) throws Exception;

	/**
	 * 부서정보를 신규로 등록한다.
	 * @param deptManageVO - 부서 model
	 * 
	 * @param deptManageVO
	 */
	public void insertDeptManage(DeptManageVO deptManageVO) throws Exception;

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * @param deptManageVO - 부서 model
	 * 
	 * @param deptManageVO
	 */
	public void updateDeptManage(DeptManageVO deptManageVO) throws Exception;

	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * @param deptManageVO - 부서 model
	 * 
	 * @param deptManageVO
	 */
	public void deleteDeptManage(DeptManageVO deptManageVO) throws Exception;
}
