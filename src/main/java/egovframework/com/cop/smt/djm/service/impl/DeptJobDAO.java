package egovframework.com.cop.smt.djm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.smt.djm.service.ChargerVO;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.DeptVO;
import jakarta.annotation.Resource;

/**
 * 개요 - 부서업무에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용 - 부서업무에 대한 등록, 수정, 삭제, 조회기능을 제공한다. - 부서업무의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:05
 *
 *          <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 *          </pre>
 */
@Repository("DeptJobDAO")
public class DeptJobDAO {

	@Resource(name = "deptJobMapper")
	private DeptJobMapper mapper;

	public List<ChargerVO> selectChargerList(ChargerVO chargerVO) {
		return mapper.selectChargerList(chargerVO);
	}

	public int selectChargerListCnt(ChargerVO chargerVO) {
		return mapper.selectChargerListCnt(chargerVO);
	}

	public List<DeptVO> selectDeptList(DeptVO deptVO) {
		return mapper.selectDeptList(deptVO);
	}

	public int selectDeptListCnt(DeptVO deptVO) {
		return mapper.selectDeptListCnt(deptVO);
	}

	public String selectDept(String orgnztId) {
		return mapper.selectDept(orgnztId);
	}

	public List<DeptJobBxVO> selectDeptJobBxList(DeptJobBxVO deptJobBxVO) {
		return mapper.selectDeptJobBxList(deptJobBxVO);
	}

	public List<DeptJobBxVO> selectDeptJobBxListAll() {
		return mapper.selectDeptJobBxListAll();
	}

	public int selectDeptJobBxListCnt(DeptJobBxVO deptJobBxVO) {
		return mapper.selectDeptJobBxListCnt(deptJobBxVO);
	}

	public DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO) {
		return mapper.selectDeptJobBx(deptJobBxVO);
	}

	public int updateDeptJobBx(DeptJobBx deptJobBx) {
		return mapper.updateDeptJobBx(deptJobBx);
	}

	public int selectDeptJobBxOrdr(DeptJobBxVO deptJobBxVO) {
		return mapper.selectDeptJobBxOrdr(deptJobBxVO);
	}

	public int updateDeptJobBxOrdrUp(DeptJobBx deptJobBx) {
		return mapper.updateDeptJobBxOrdrUp(deptJobBx);
	}

	public int updateDeptJobBxOrdrDown(DeptJobBx deptJobBx) {
		return mapper.updateDeptJobBxOrdrDown(deptJobBx);
	}

	public int updateDeptJobBxOrdr(DeptJobBx deptJobBx) {
		return mapper.updateDeptJobBxOrdr(deptJobBx);
	}

	public int updateDeptJobBxOrdrAll(DeptJobBxVO deptJobBxVO) {
		return mapper.updateDeptJobBxOrdrAll(deptJobBxVO);
	}

	public int selectMaxDeptJobBxOrdr(String deptId) {
		return mapper.selectMaxDeptJobBxOrdr(deptId);
	}

	public int insertDeptJobBx(DeptJobBx deptJobBx) {
		return mapper.insertDeptJobBx(deptJobBx);
	}

	public int selectDeptJobBxCheck(DeptJobBx deptJobBx) {
		return mapper.selectDeptJobBxCheck(deptJobBx);
	}

	public int deleteDeptJobBx(DeptJobBx deptJobBx) {
		return mapper.deleteDeptJobBx(deptJobBx);
	}

	public List<DeptJobVO> selectDeptJobList(DeptJobVO deptJobVO) {
		return mapper.selectDeptJobList(deptJobVO);
	}

	public int selectDeptJobListCnt(DeptJobVO deptJobVO) {
		return mapper.selectDeptJobListCnt(deptJobVO);
	}

	public DeptJobVO selectDeptJob(DeptJobVO deptJobVO) {
		return mapper.selectDeptJob(deptJobVO);
	}

	public int updateDeptJob(DeptJob deptJob) {
		return mapper.updateDeptJob(deptJob);
	}

	public int insertDeptJob(DeptJob deptJob) {
		return mapper.insertDeptJob(deptJob);
	}

	public int deleteDeptJob(DeptJob deptJob) {
		return mapper.deleteDeptJob(deptJob);
	}

}
