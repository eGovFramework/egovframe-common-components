package egovframework.com.cop.smt.djm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.smt.djm.service.ChargerVO;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.DeptVO;

/**
 * 부서업무에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("deptJobMapper")
public interface DeptJobMapper {

	List<ChargerVO> selectChargerList(ChargerVO chargerVO);

	int selectChargerListCnt(ChargerVO chargerVO);

	List<DeptVO> selectDeptList(DeptVO deptVO);

	int selectDeptListCnt(DeptVO deptVO);

	String selectDept(String orgnztId);

	List<DeptJobBxVO> selectDeptJobBxList(DeptJobBxVO deptJobBxVO);

	List<DeptJobBxVO> selectDeptJobBxListAll();

	int selectDeptJobBxListCnt(DeptJobBxVO deptJobBxVO);

	DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO);

	int updateDeptJobBx(DeptJobBx deptJobBx);

	int selectDeptJobBxOrdr(DeptJobBxVO deptJobBxVO);

	int updateDeptJobBxOrdrUp(DeptJobBx deptJobBx);

	int updateDeptJobBxOrdrDown(DeptJobBx deptJobBx);

	int updateDeptJobBxOrdr(DeptJobBx deptJobBx);

	int updateDeptJobBxOrdrAll(DeptJobBxVO deptJobBxVO);

	int selectMaxDeptJobBxOrdr(String deptId);

	int insertDeptJobBx(DeptJobBx deptJobBx);

	int selectDeptJobBxCheck(DeptJobBx deptJobBx);

	int deleteDeptJobBx(DeptJobBx deptJobBx);

	List<DeptJobVO> selectDeptJobList(DeptJobVO deptJobVO);

	int selectDeptJobListCnt(DeptJobVO deptJobVO);

	DeptJobVO selectDeptJob(DeptJobVO deptJobVO);

	int updateDeptJob(DeptJob deptJob);

	int insertDeptJob(DeptJob deptJob);

	int deleteDeptJob(DeptJob deptJob);

}
