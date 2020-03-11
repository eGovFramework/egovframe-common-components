package egovframework.com.cop.smt.djm.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cop.smt.djm.service.ChargerVO;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.DeptVO;
import egovframework.com.cop.smt.djm.service.EgovDeptJobService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 부서업무에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 부서업무에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 부서업무의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:05
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *
 * </pre>
 */
@Service("EgovDeptJobService")
public class EgovDeptJobServiceImpl extends EgovAbstractServiceImpl implements EgovDeptJobService {
	
	@Resource(name = "DeptJobDAO")
    private DeptJobDAO deptJobDAO;
	
	@Resource(name="egovDeptJobIdGnrService")
	private EgovIdGnrService idgenServiceDeptJob;
	
	@Resource(name="egovDeptJobBxIdGnrService")
	private EgovIdGnrService idgenServiceDeptJobBx;
	
	/**
	 * 담당자 목록을 조회한다.
	 * @param ChargerVO
	 * @return  Map<String, Object>
	 * 
	 * @param chargerVO
	 */
	public Map<String, Object> selectChargerList(ChargerVO chargerVO) throws Exception{
		List<ChargerVO> result = deptJobDAO.selectChargerList(chargerVO);
		int cnt = deptJobDAO.selectChargerListCnt(chargerVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	
	/**
	 * 부서 목록을 조회한다.
	 * @param DeptVO
	 * @return  List
	 * 
	 * @param deptVO
	 */
	public Map<String, Object> selectDeptList(DeptVO deptVO) throws Exception{
		List<DeptVO> result = deptJobDAO.selectDeptList(deptVO);
		int cnt = deptJobDAO.selectDeptListCnt(deptVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	/**
	 * 부서 정보를 조회한다.
	 * @param String
	 * @return  String
	 * 
	 * @param String
	 */
	public String selectDept(String deptVO) throws Exception{
		return deptJobDAO.selectDept(deptVO);
	}
	
	/**
	 * 부서업무함 목록 전체를 조회한다.
	 * @param DeptJobBxVO
	 * @return  List
	 * 
	 * @param deptJobBxVO
	 */
	public List<DeptJobBxVO> selectDeptJobBxListAll() throws Exception{
		return deptJobDAO.selectDeptJobBxListAll();
	}
	
	/**
	 * 부서업무함 목록을 조회한다.
	 * @param DeptJobBxVO
	 * @return  List
	 * 
	 * @param deptJobBxVO
	 */
	public Map<String, Object> selectDeptJobBxList(DeptJobBxVO deptJobBxVO) throws Exception{
		List<DeptJobBxVO> result = deptJobDAO.selectDeptJobBxList(deptJobBxVO);
		int cnt = deptJobDAO.selectDeptJobBxListCnt(deptJobBxVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 부서업무함을 조회한다.
	 * @param DeptJobBxVO
	 * @return  DeptJobBxVO
	 * 
	 * @param deptJobBxVO
	 */
	public DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO) throws Exception{
		return deptJobDAO.selectDeptJobBx(deptJobBxVO);
	}
	
	/**
	 * 부서업무함 정보를 수정한다.
	 * @param DeptJobBxVO
	 * @return
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBx(DeptJobBxVO deptJobBxVO) throws Exception{
		if(deptJobDAO.selectDeptJobBxOrdr(deptJobBxVO) > 0){
			deptJobDAO.updateDeptJobBxOrdrAll(deptJobBxVO);
		}
		deptJobDAO.updateDeptJobBx(deptJobBxVO);
	}

	/**
	 * 부서업무함 정보의 표시순서를 수정한다.
	 * @param DeptJobBx
	 * @return boolean
	 * 
	 * @param deptJobBx
	 */
	public boolean updateDeptJobBxOrdr(DeptJobBxVO deptJobBxVO) throws Exception{
		
		boolean changed = false;
		if(deptJobBxVO.getOrdrCnd().equals("up")){
			deptJobBxVO.setIndictOrdr(deptJobBxVO.getIndictOrdr() - 1);
			if(deptJobDAO.selectDeptJobBxOrdr(deptJobBxVO) > 0){
				deptJobDAO.updateDeptJobBxOrdrUp(deptJobBxVO);
				deptJobDAO.updateDeptJobBxOrdr(deptJobBxVO);
				
				changed = true;
			}
		}else if(deptJobBxVO.getOrdrCnd().equals("down")){
			deptJobBxVO.setIndictOrdr(deptJobBxVO.getIndictOrdr() + 1);
			if(deptJobDAO.selectDeptJobBxOrdr(deptJobBxVO) > 0){
				deptJobDAO.updateDeptJobBxOrdrDown(deptJobBxVO);
				deptJobDAO.updateDeptJobBxOrdr(deptJobBxVO);
				
				changed = true;
			}
		}
		
		return changed;
	}
	
	/**
	 * 등록시 부서업무함의 순서를 조회한다.
	 * @param String
	 * @return  int
	 * 
	 * @param deptId
	 */
	public int selectDeptJobBxOrdr(String deptId) throws Exception{
		return deptJobDAO.selectMaxDeptJobBxOrdr(deptId);
	}
	
	/**
	 * 부서업무함 정보를 등록한다.
	 * @param DeptJobBxVO
	 * @return
	 * 
	 * @param deptJobBxVO
	 */
	public void insertDeptJobBx(DeptJobBxVO deptJobBxVO) throws Exception{
		deptJobBxVO.setDeptJobBxId(idgenServiceDeptJobBx.getNextStringId());
		if(deptJobDAO.selectDeptJobBxOrdr(deptJobBxVO) > 0){
			deptJobDAO.updateDeptJobBxOrdrAll(deptJobBxVO);
		}
		deptJobDAO.insertDeptJobBx(deptJobBxVO);
	}
	
	/**
	 * 부서내 부서업무함명의 건수를 조회한다.
	 * @param DeptJobBx
	 * @return int
	 * 
	 * @param deptJobBx
	 */
	public int selectDeptJobBxCheck(DeptJobBx deptJobBx) throws Exception{
		return deptJobDAO.selectDeptJobBxCheck(deptJobBx);
	}
	
	/**
	 * 부서업무함 정보를 삭제한다.
	 * @param DeptJobBx
	 * @return
	 * 
	 * @param deptJobBx
	 */
	public void deleteDeptJobBx(DeptJobBx deptJobBx) throws Exception{
		deptJobDAO.deleteDeptJobBx(deptJobBx);
	}

	/**
	 * 부서업무 목록을 조회한다.
	 * @param DeptJobVO
	 * @return  List
	 * 
	 * @param deptJobVO
	 */
	public Map<String, Object> selectDeptJobList(DeptJobVO deptJobVO) throws Exception{
		List<DeptJobVO> result = deptJobDAO.selectDeptJobList(deptJobVO);
		int cnt = deptJobDAO.selectDeptJobListCnt(deptJobVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 부서업무 정보를 조회한다.
	 * @param DeptJobVO
	 * @return  DeptJobVO
	 * 
	 * @param deptJobVO
	 */
	public DeptJobVO selectDeptJob(DeptJobVO deptJobVO) throws Exception{
		return deptJobDAO.selectDeptJob(deptJobVO);
	}

	/**
	 * 부서업무를 수정한다.
	 * @param DeptJob
	 * 
	 * @param deptJob
	 */
	public void updateDeptJob(DeptJob deptJob) throws Exception{
		deptJobDAO.updateDeptJob(deptJob);
	}

	/**
	 * 부서업무를 등록한다.
	 * @param DeptJob
	 * 
	 * @param deptJob
	 */
	public void insertDeptJob(DeptJob deptJob) throws Exception{
		deptJob.setDeptJobId(idgenServiceDeptJob.getNextStringId());
		deptJobDAO.insertDeptJob(deptJob);
	}

	/**
	 * 부서업무를 삭제한다.
	 * @param DeptJob
	 * 
	 * @param deptJob
	 */
	public void deleteDeptJob(DeptJob deptJob) throws Exception{
		deptJobDAO.deleteDeptJob(deptJob);
	}

}