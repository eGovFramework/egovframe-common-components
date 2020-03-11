package egovframework.com.cop.smt.djm.service.impl;
import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.smt.djm.service.ChargerVO;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.DeptVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 부서업무에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 부서업무에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * -  부서업무의 조회기능은 목록조회, 상세조회로 구분된다.
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
@Repository("DeptJobDAO")
public class DeptJobDAO extends EgovComAbstractDAO{
	
	/**
	 * 주어진 조건에 맞는 담당자를 불러온다.
	 * @param ChargerVO
	 * @return List
	 * 
	 * @param chargerVO
	 */
	public List<ChargerVO> selectChargerList(ChargerVO chargerVO) throws Exception{
		return selectList("DeptJobDAO.selectChargerList", chargerVO);
	}
	
	/**
	 * 담당자 목록에 대한 전체 건수를 조회한다.
	 * @param ChargerVO
	 * @return int
	 * 
	 * @param chargerVO
	 */
	public int selectChargerListCnt(ChargerVO chargerVO) throws Exception{
		return (Integer)selectOne("DeptJobDAO.selectChargerListCnt", chargerVO);
	}
	
	/**
	 * 주어진 조건에 맞는 부서를 불러온다.
	 * @param DeptVO
	 * @return List
	 * 
	 * @param DeptVO
	 */
	public List<DeptVO> selectDeptList(DeptVO deptVO) throws Exception{
		return selectList("DeptJobDAO.selectDeptList", deptVO);
	}

	/**
	 * 부서 목록에 대한 전체 건수를 조회한다.
	 * @param DeptVO
	 * @return int
	 * 
	 * @param deptVO
	 */
	public int selectDeptListCnt(DeptVO deptVO) throws Exception{
		return (Integer)selectOne("DeptJobDAO.selectDeptListCnt", deptVO);
	}
	
	/**
	 * 주어진 조건에 맞는 부서를 불러온다.
	 * @param DeptVO
	 * @return String
	 * 
	 * @param deptVO
	 */
	public String selectDept(String deptVO) throws Exception{
		return (String)selectOne("DeptJobDAO.selectDept", deptVO);
	}
	
	/**
	 * 주어진 조건에 따른 부서업무함 목록을 불러온다.
	 * @param DeptJobBxVO
	 * @return List
	 * 
	 * @param deptJobBxVO
	 */
	public List<DeptJobBxVO> selectDeptJobBxList(DeptJobBxVO deptJobBxVO) throws Exception{
		return selectList("DeptJobDAO.selectDeptJobBxList", deptJobBxVO);
	}
	
	/**
	 * 주어진 조건에 따른 부서업무함 목록 전체를 불러온다.
	 * @param DeptJobBxVO
	 * @return List
	 * 
	 * @param deptJobBxVO
	 */
	public List<DeptJobBxVO> selectDeptJobBxListAll() throws Exception{
		return selectList("DeptJobDAO.selectDeptJobBxListAll", "");
	}
	
	/**
	 * 부서업무함 목록에 대한 전체 건수를 조회한다.
	 * @param DeptJobBxVO
	 * @return int
	 * 
	 * @param deptJobBxVO
	 */
	public int selectDeptJobBxListCnt(DeptJobBxVO deptJobBxVO) throws Exception{
		return (Integer)selectOne("DeptJobDAO.selectDeptJobBxListCnt", deptJobBxVO);
	}
	
	/**
	 * 주어진 조건에 맞는 부서업무함을 불러온다.
	 * @param DeptJobBxVO
	 * @return DeptJobBxVO
	 * 
	 * @param deptJobBxVO
	 */
	public DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO) throws Exception{
		return (DeptJobBxVO)selectOne("DeptJobDAO.selectDeptJobBx", deptJobBxVO);
	}

	/**
	 * 부서업무함 정보를 수정한다.
	 * @param DeptJobBxVO
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBx(DeptJobBx deptJobBx) throws Exception{
		update("DeptJobDAO.updateDeptJobBx", deptJobBx);
	}
	
	/**
	 * 부서업무함의 표시순서가 중복되는지를 조회한다.
	 * @param DeptJobBxVO
	 * @return int
	 * 
	 * @param deptJobBxVO
	 */
	public int selectDeptJobBxOrdr(DeptJobBxVO deptJobBxVO) throws Exception{
		return (Integer)selectOne("DeptJobDAO.selectDeptJobBxOrdr", deptJobBxVO);
	}
	
	/**
	 * 부서업무함 정보의 표시순서를 수정한다. (표시순서 증가)
	 * @param DeptJobBx
	 * 
	 * @param deptJobBx
	 */
	public void updateDeptJobBxOrdrUp(DeptJobBx deptJobBx) throws Exception{
		update("DeptJobDAO.updateDeptJobBxOrdrUp", deptJobBx);
	}
	
	/**
	 * 부서업무함 정보의 표시순서를 수정한다. (표시순서 감소)
	 * @param DeptJobBx
	 * 
	 * @param deptJobBx
	 */
	public void updateDeptJobBxOrdrDown(DeptJobBx deptJobBx) throws Exception{
		update("DeptJobDAO.updateDeptJobBxOrdrDown", deptJobBx);
	}
	
	/**
	 * 부서업무함 정보의 표시순서를 수정한다. 
	 * @param DeptJobBx
	 * 
	 * @param deptJobBx
	 */
	public void updateDeptJobBxOrdr(DeptJobBx deptJobBx) throws Exception{
		update("DeptJobDAO.updateDeptJobBxOrdr", deptJobBx);
	}
	
	/**
	 * 주어진 조건에 만족하는 전체 부서업무함 정보의 표시순서를 수정한다. 
	 * @param DeptJobBxVO
	 * 
	 * @param deptJobBxVO
	 */
	public void updateDeptJobBxOrdrAll(DeptJobBxVO deptJobBxVO) throws Exception{
		update("DeptJobDAO.updateDeptJobBxOrdrAll", deptJobBxVO);
	}
	
	/**
	 * 등록시 부서업무함의 표시순서를 조회한다.
	 * @param String
	 * @return int
	 * 
	 * @param deptId
	 */
	public int selectMaxDeptJobBxOrdr(String deptId) throws Exception{
		return (Integer)selectOne("DeptJobDAO.selectMaxDeptJobBxOrdr", deptId);
	}
	
	/**
	 * 부서업무함 정보를 등록한다.
	 * @param DeptJobBx
	 * 
	 * @param deptJobBx
	 */
	public void insertDeptJobBx(DeptJobBx deptJobBx) throws Exception{
		insert("DeptJobDAO.insertDeptJobBx", deptJobBx);
	}
	
	/**
	 * 부서내 부서업무함명의 건수를 조회한다.
	 * @param DeptJobBx
	 * @return int
	 * 
	 * @param deptJobBx
	 */
	public int selectDeptJobBxCheck(DeptJobBx deptJobBx) throws Exception{
		return (Integer)selectOne("DeptJobDAO.selectDeptJobBxCheck", deptJobBx);
	}

	/**
	 * 부서업무함 정보를 삭제한다.
	 * @param DeptJobBx
	 * 
	 * @param deptJobBx
	 */
	public void deleteDeptJobBx(DeptJobBx deptJobBx) throws Exception{
		delete("DeptJobDAO.deleteDeptJobBx", deptJobBx);
	}

	/**
	 * 주어진 조건에 따른 부서업무 목록을 불러온다.
	 * @param DeptJobVO
	 * @return List
	 * 
	 * @param deptJobVO
	 */
	public List<DeptJobVO> selectDeptJobList(DeptJobVO deptJobVO) throws Exception{
		return selectList("DeptJobDAO.selectDeptJobList", deptJobVO);
	}
	
	/**
	 * 부서업무 목록에 대한 전체 건수를 조회한다.
	 * @param DeptJobVO
	 * @return int
	 * 
	 * @param deptJobVO
	 */
	public int selectDeptJobListCnt(DeptJobVO deptJobVO) throws Exception{
		return (Integer)selectOne("DeptJobDAO.selectDeptJobListCnt", deptJobVO);
	}
	
	/**
	 * 주어진 조건에 맞는 부서업무를 불러온다.
	 * @param DeptJobVO
	 * @return DeptJobVO
	 * 
	 * @param deptJobVO
	 */
	public DeptJobVO selectDeptJob(DeptJobVO deptJobVO) throws Exception{
		return (DeptJobVO)selectOne("DeptJobDAO.selectDeptJob", deptJobVO);
	}

	/**
	 * 부서업무 정보를 수정한다.
	 * @param DeptJob
	 * 
	 * @param deptJob
	 */
	public void updateDeptJob(DeptJob deptJob) throws Exception{
		update("DeptJobDAO.updateDeptJob", deptJob);
	}

	/**
	 * 부서업무 정보를 등록한다.
	 * @param DeptJob
	 * 
	 * @param deptJob
	 */
	public void insertDeptJob(DeptJob deptJob) throws Exception{
		insert("DeptJobDAO.insertDeptJob", deptJob);
	}

	/**
	 * 부서업무 정보를 삭제한다.
	 * @param DeptJob
	 * 
	 * @param deptJob
	 */
	public void deleteDeptJob(DeptJob deptJob) throws Exception{
		delete("DeptJobDAO.deleteDeptJob", deptJob);
	}

	

}