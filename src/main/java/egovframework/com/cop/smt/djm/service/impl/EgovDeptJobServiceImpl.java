package egovframework.com.cop.smt.djm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.cop.smt.djm.service.ChargerVO;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.DeptVO;
import egovframework.com.cop.smt.djm.service.EgovDeptJobService;

/**
 * <pre>
 * 개요
 * - 부서업무에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 부서업무에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 부서업무의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
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
 *   2010.06.28  장철호          최초 생성
 *   2024.09.09  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *          </pre>
 */
@Service("EgovDeptJobService")
public class EgovDeptJobServiceImpl extends EgovAbstractServiceImpl implements EgovDeptJobService {

	@Resource(name = "DeptJobDAO")
	private DeptJobDAO deptJobDAO;

	@Resource(name = "egovDeptJobIdGnrService")
	private EgovIdGnrService idgenServiceDeptJob;

	@Resource(name = "egovDeptJobBxIdGnrService")
	private EgovIdGnrService idgenServiceDeptJobBx;

	/**
	 * 담당자 목록을 조회한다.
	 * 
	 * @param ChargerVO
	 * @return Map<String, Object>
	 * 
	 * @param chargerVO
	 */
	@Override
	public Map<String, Object> selectChargerList(ChargerVO chargerVO) {
		List<ChargerVO> result = deptJobDAO.selectChargerList(chargerVO);
		int cnt = deptJobDAO.selectChargerListCnt(chargerVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 부서 목록을 조회한다.
	 * 
	 * @param DeptVO
	 * @return List
	 * 
	 * @param deptVO
	 */
	@Override
	public Map<String, Object> selectDeptList(DeptVO deptVO) {
		List<DeptVO> result = deptJobDAO.selectDeptList(deptVO);
		int cnt = deptJobDAO.selectDeptListCnt(deptVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 부서 정보를 조회한다.
	 * 
	 * @param String
	 * @return String
	 * 
	 * @param String
	 */
	@Override
	public String selectDept(String deptVO) {
		return deptJobDAO.selectDept(deptVO);
	}

	/**
	 * 부서업무함 목록 전체를 조회한다.
	 * 
	 * @param DeptJobBxVO
	 * @return List
	 * 
	 * @param deptJobBxVO
	 */
	@Override
	public List<DeptJobBxVO> selectDeptJobBxListAll() {
		return deptJobDAO.selectDeptJobBxListAll();
	}

	/**
	 * 부서업무함 목록을 조회한다.
	 * 
	 * @param DeptJobBxVO
	 * @return List
	 * 
	 * @param deptJobBxVO
	 */
	@Override
	public Map<String, Object> selectDeptJobBxList(DeptJobBxVO deptJobBxVO) {
		List<DeptJobBxVO> result = deptJobDAO.selectDeptJobBxList(deptJobBxVO);
		int cnt = deptJobDAO.selectDeptJobBxListCnt(deptJobBxVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 부서업무함을 조회한다.
	 * 
	 * @param DeptJobBxVO
	 * @return DeptJobBxVO
	 * 
	 * @param deptJobBxVO
	 */
	@Override
	public DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO) {
		return deptJobDAO.selectDeptJobBx(deptJobBxVO);
	}

	/**
	 * 부서업무함 정보를 수정한다.
	 * 
	 * @param DeptJobBxVO
	 * @return
	 * 
	 * @param deptJobBxVO
	 */
	@Override
	public void updateDeptJobBx(DeptJobBxVO deptJobBxVO) {
		if (deptJobDAO.selectDeptJobBxOrdr(deptJobBxVO) > 0) {
			deptJobDAO.updateDeptJobBxOrdrAll(deptJobBxVO);
		}
		deptJobDAO.updateDeptJobBx(deptJobBxVO);
	}

	/**
	 * 부서업무함 정보의 표시순서를 수정한다.
	 * 
	 * @param DeptJobBx
	 * @return boolean
	 * 
	 * @param deptJobBx
	 */
	@Override
	public boolean updateDeptJobBxOrdr(DeptJobBxVO deptJobBxVO) {

		boolean changed = false;
		if (deptJobBxVO.getOrdrCnd().equals("up")) {
			deptJobBxVO.setIndictOrdr(deptJobBxVO.getIndictOrdr() - 1);
			if (deptJobDAO.selectDeptJobBxOrdr(deptJobBxVO) > 0) {
				deptJobDAO.updateDeptJobBxOrdrUp(deptJobBxVO);
				deptJobDAO.updateDeptJobBxOrdr(deptJobBxVO);

				changed = true;
			}
		} else if (deptJobBxVO.getOrdrCnd().equals("down")) {
			deptJobBxVO.setIndictOrdr(deptJobBxVO.getIndictOrdr() + 1);
			if (deptJobDAO.selectDeptJobBxOrdr(deptJobBxVO) > 0) {
				deptJobDAO.updateDeptJobBxOrdrDown(deptJobBxVO);
				deptJobDAO.updateDeptJobBxOrdr(deptJobBxVO);

				changed = true;
			}
		}

		return changed;
	}

	/**
	 * 등록시 부서업무함의 순서를 조회한다.
	 * 
	 * @param String
	 * @return int
	 * 
	 * @param deptId
	 */
	@Override
	public int selectDeptJobBxOrdr(String deptId) {
		return deptJobDAO.selectMaxDeptJobBxOrdr(deptId);
	}

	/**
	 * 부서업무함 정보를 등록한다.
	 * 
	 * @param DeptJobBxVO
	 * @return
	 * 
	 * @param deptJobBxVO
	 * @throws Exception
	 */
	@Override
	public void insertDeptJobBx(DeptJobBxVO deptJobBxVO) throws Exception {
		try {
			deptJobBxVO.setDeptJobBxId(idgenServiceDeptJobBx.getNextStringId());
		} catch (FdlException e) {
			throw processException("FdlException: insertDeptJobBx( egovDeptJobBxIdGnrService", e);
		}
		if (deptJobDAO.selectDeptJobBxOrdr(deptJobBxVO) > 0) {
			deptJobDAO.updateDeptJobBxOrdrAll(deptJobBxVO);
		}
		deptJobDAO.insertDeptJobBx(deptJobBxVO);
	}

	/**
	 * 부서내 부서업무함명의 건수를 조회한다.
	 * 
	 * @param DeptJobBx
	 * @return int
	 * 
	 * @param deptJobBx
	 */
	@Override
	public int selectDeptJobBxCheck(DeptJobBx deptJobBx) {
		return deptJobDAO.selectDeptJobBxCheck(deptJobBx);
	}

	/**
	 * 부서업무함 정보를 삭제한다.
	 * 
	 * @param DeptJobBx
	 * @return
	 * 
	 * @param deptJobBx
	 */
	@Override
	public void deleteDeptJobBx(DeptJobBx deptJobBx) {
		deptJobDAO.deleteDeptJobBx(deptJobBx);
	}

	/**
	 * 부서업무 목록을 조회한다.
	 * 
	 * @param DeptJobVO
	 * @return List
	 * 
	 * @param deptJobVO
	 */
	@Override
	public Map<String, Object> selectDeptJobList(DeptJobVO deptJobVO) {
		List<DeptJobVO> result = deptJobDAO.selectDeptJobList(deptJobVO);
		int cnt = deptJobDAO.selectDeptJobListCnt(deptJobVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 부서업무 정보를 조회한다.
	 * 
	 * @param DeptJobVO
	 * @return DeptJobVO
	 * 
	 * @param deptJobVO
	 */
	@Override
	public DeptJobVO selectDeptJob(DeptJobVO deptJobVO) {
		return deptJobDAO.selectDeptJob(deptJobVO);
	}

	/**
	 * 부서업무를 수정한다.
	 * 
	 * @param DeptJob
	 * 
	 * @param deptJob
	 */
	@Override
	public void updateDeptJob(DeptJob deptJob) {
		deptJobDAO.updateDeptJob(deptJob);
	}

	/**
	 * 부서업무를 등록한다.
	 * 
	 * @param DeptJob
	 * 
	 * @param deptJob
	 * @throws Exception
	 */
	@Override
	public void insertDeptJob(DeptJob deptJob) throws Exception {
		try {
			deptJob.setDeptJobId(idgenServiceDeptJob.getNextStringId());
		} catch (FdlException e) {
			throw processException("FdlException: insertDeptJobBx( egovDeptJobIdGnrService", e);
		}
		deptJobDAO.insertDeptJob(deptJob);
	}

	/**
	 * 부서업무를 삭제한다.
	 * 
	 * @param DeptJob
	 * 
	 * @param deptJob
	 */
	@Override
	public void deleteDeptJob(DeptJob deptJob) {
		deptJobDAO.deleteDeptJob(deptJob);
	}

}