package egovframework.com.cop.smt.djm.service;

import java.util.List;
import java.util.Map;

/**
 * 개요
 * - 부서업무에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 부서업무에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 부서업무의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:05
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *
 * </pre>
 */
public interface EgovDeptJobService {
	
	/**
	 * 담당자 목록을 조회한다.
	 * @param ChargerVO
	 * @return  Map<String, Object>
	 * 
	 * @param chargerVO
	 */
	public Map<String, Object> selectChargerList(ChargerVO chargerVO) throws Exception;
	
	
	/**
	 * 부서 목록을 조회한다.
	 * @param DeptVO
	 * @return  List
	 * 
	 * @param deptVO
	 */
	public Map<String, Object> selectDeptList(DeptVO deptVO) throws Exception;
	
	/**
	 * 부서 정보를 조회한다.
	 * @param String
	 * @return  String
	 * 
	 * @param String
	 */
	public String selectDept(String deptVO) throws Exception;
	
	/**
	 * 부서업무함 목록을 조회한다.
	 * @param DeptJobBxVO
	 * @return  List
	 * 
	 * @param deptJobBxVO
	 */
	public Map<String, Object> selectDeptJobBxList(DeptJobBxVO deptJobBxVO) throws Exception;
	
	/**
	 * 부서업무함 목록 전체를 조회한다.
	 * @param DeptJobBxVO
	 * @return  List
	 * 
	 * @param deptJobBxVO
	 */
	public List<DeptJobBxVO> selectDeptJobBxListAll() throws Exception;

	/**
	 * 부서업무함 정보를 조회한다.
	 * @param DeptJobBxVO
	 * @return  DeptJobBxVO
	 * 
	 * @param deptJobBxVO
	 */
	public DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO) throws Exception;

	/**
	 * 부서업무함 정보를 수정한다.
	 * @param DeptJobBxVO
	 * @return
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBx(DeptJobBxVO deptJobBxVO) throws Exception;

	/**
	 * 부서업무함 정보의 표시순서를 수정한다.
	 * @param DeptJobBx
	 * @return boolean
	 * 
	 * @param deptJobBx
	 */
	public boolean updateDeptJobBxOrdr(DeptJobBxVO deptJobBxVO) throws Exception;
	
	/**
	 * 등록시 부서업무함의 순서를 조회한다.
	 * @param String
	 * @return  int
	 * 
	 * @param deptId
	 */
	public int selectDeptJobBxOrdr(String deptId) throws Exception;
	
	/**
	 * 부서업무함 정보를 등록한다.
	 * @param DeptJobBxVO
	 * @return
	 * 
	 * @param deptJobBxVO
	 */
	public void insertDeptJobBx(DeptJobBxVO deptJobBxVO) throws Exception;
	
	/**
	 * 부서내 부서업무함명의 건수를 조회한다.
	 * @param DeptJobBx
	 * @return int
	 * 
	 * @param deptJobBx
	 */
	public int selectDeptJobBxCheck(DeptJobBx deptJobBx) throws Exception;

	/**
	 * 부서업무함 정보를 삭제한다.
	 * @param DeptJobBx
	 * @return
	 * 
	 * @param deptJobBx
	 */
	public void deleteDeptJobBx(DeptJobBx deptJobBx) throws Exception;

	/**
	 * 부서업무 목록을 조회한다.
	 * @param DeptJobVO
	 * @return  List
	 * 
	 * @param deptJobVO
	 */
	public Map<String, Object> selectDeptJobList(DeptJobVO deptJobVO) throws Exception;

	/**
	 * 부서업무 정보를 조회한다.
	 * @param DeptJobVO
	 * @return DeptJobVO
	 * 
	 * @param deptJobVO
	 */
	public DeptJobVO selectDeptJob(DeptJobVO deptJobVO) throws Exception;

	/**
	 * 부서업무 정보를 수정한다.
	 * @param DeptJob
	 * @return
	 * 
	 * @param deptJob
	 */
	public void updateDeptJob(DeptJob deptJob) throws Exception;

	/**
	 * 부서업무 정보를 등록한다.
	 * @param DeptJob
	 * @return
	 * 
	 * @param deptJob
	 */
	public void insertDeptJob(DeptJob deptJob) throws Exception;

	/**
	 * 부서업무 정보를 삭제한다.
	 * @param DeptJob
	 * @return
	 * 
	 * @param deptJob
	 */
	public void deleteDeptJob(DeptJob deptJob) throws Exception;

}