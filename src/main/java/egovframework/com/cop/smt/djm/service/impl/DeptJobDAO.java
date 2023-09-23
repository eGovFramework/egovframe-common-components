package egovframework.com.cop.smt.djm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.smt.djm.service.ChargerVO;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
import egovframework.com.cop.smt.djm.service.DeptVO;

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
 *
 *          </pre>
 */
@Repository("DeptJobDAO")
public class DeptJobDAO extends EgovComAbstractDAO {

    /**
     * 주어진 조건에 맞는 담당자를 불러온다.
     * 
     * @param chargerVO
     * @return List
     */
    public List<ChargerVO> selectChargerList(ChargerVO chargerVO) {
        return selectList("DeptJobDAO.selectChargerList", chargerVO);
    }

    /**
     * 담당자 목록에 대한 전체 건수를 조회한다.
     * 
     * @param chargerVO
     * @return int
     */
    public int selectChargerListCnt(ChargerVO chargerVO) {
        return selectOne("DeptJobDAO.selectChargerListCnt", chargerVO);
    }

    /**
     * 주어진 조건에 맞는 부서를 불러온다.
     * 
     * @param deptVO
     * @return List
     */
    public List<DeptVO> selectDeptList(DeptVO deptVO) {
        return selectList("DeptJobDAO.selectDeptList", deptVO);
    }

    /**
     * 부서 목록에 대한 전체 건수를 조회한다.
     * 
     * @param deptVO
     * @return int
     */
    public int selectDeptListCnt(DeptVO deptVO) {
        return selectOne("DeptJobDAO.selectDeptListCnt", deptVO);
    }

    /**
     * 주어진 조건에 맞는 부서를 불러온다.
     * 
     * @param orgnztId
     * @return String
     */
    public String selectDept(String orgnztId) {
        return selectOne("DeptJobDAO.selectDept", orgnztId);
    }

    /**
     * 주어진 조건에 따른 부서업무함 목록을 불러온다.
     * 
     * @param deptJobBxVO
     * @return List
     */
    public List<DeptJobBxVO> selectDeptJobBxList(DeptJobBxVO deptJobBxVO) {
        return selectList("DeptJobDAO.selectDeptJobBxList", deptJobBxVO);
    }

    /**
     * 주어진 조건에 따른 부서업무함 목록 전체를 불러온다.
     * 
     * @return List
     */
    public List<DeptJobBxVO> selectDeptJobBxListAll() {
        return selectList("DeptJobDAO.selectDeptJobBxListAll");
    }

    /**
     * 부서업무함 목록에 대한 전체 건수를 조회한다.
     * 
     * @param DeptJobBxVO
     * @return int
     * 
     * @param deptJobBxVO
     */
    public int selectDeptJobBxListCnt(DeptJobBxVO deptJobBxVO) {
        return selectOne("DeptJobDAO.selectDeptJobBxListCnt", deptJobBxVO);
    }

    /**
     * 주어진 조건에 맞는 부서업무함을 불러온다.
     * 
     * @param deptJobBxVO
     * @return DeptJobBxVO
     */
    public DeptJobBxVO selectDeptJobBx(DeptJobBxVO deptJobBxVO) {
        return selectOne("DeptJobDAO.selectDeptJobBx", deptJobBxVO);
    }

    /**
     * 부서업무함 정보를 수정한다.
     * 
     * @param deptJobBxVO
     * @return int
     */
    public int updateDeptJobBx(DeptJobBx deptJobBx) {
        return update("DeptJobDAO.updateDeptJobBx", deptJobBx);
    }

    /**
     * 부서업무함의 표시순서가 중복되는지를 조회한다.
     * 
     * @param deptJobBxVO
     * @return int
     */
    public int selectDeptJobBxOrdr(DeptJobBxVO deptJobBxVO) {
        return selectOne("DeptJobDAO.selectDeptJobBxOrdr", deptJobBxVO);
    }

    /**
     * 부서업무함 정보의 표시순서를 수정한다. (표시순서 증가)
     * 
     * @param deptJobBx
     * @return int
     */
    public int updateDeptJobBxOrdrUp(DeptJobBx deptJobBx) {
        return update("DeptJobDAO.updateDeptJobBxOrdrUp", deptJobBx);
    }

    /**
     * 부서업무함 정보의 표시순서를 수정한다. (표시순서 감소)
     * 
     * @param deptJobBx
     * @return int
     */
    public int updateDeptJobBxOrdrDown(DeptJobBx deptJobBx) {
        return update("DeptJobDAO.updateDeptJobBxOrdrDown", deptJobBx);
    }

    /**
     * 부서업무함 정보의 표시순서를 수정한다.
     * 
     * @param deptJobBx
     * @return int
     */
    public int updateDeptJobBxOrdr(DeptJobBx deptJobBx) {
        return update("DeptJobDAO.updateDeptJobBxOrdr", deptJobBx);
    }

    /**
     * 주어진 조건에 만족하는 전체 부서업무함 정보의 표시순서를 수정한다.
     * 
     * @param deptJobBxVO
     * @return int
     */
    public int updateDeptJobBxOrdrAll(DeptJobBxVO deptJobBxVO) {
        return update("DeptJobDAO.updateDeptJobBxOrdrAll", deptJobBxVO);
    }

    /**
     * 등록시 부서업무함의 표시순서를 조회한다.
     * 
     * @param deptId
     * @return int
     */
    public int selectMaxDeptJobBxOrdr(String deptId) {
        return selectOne("DeptJobDAO.selectMaxDeptJobBxOrdr", deptId);
    }

    /**
     * 부서업무함 정보를 등록한다.
     * 
     * @param DeptJobBx
     * 
     * @param deptJobBx
     */
    public int insertDeptJobBx(DeptJobBx deptJobBx) {
        return insert("DeptJobDAO.insertDeptJobBx", deptJobBx);
    }

    /**
     * 부서내 부서업무함명의 건수를 조회한다.
     * 
     * @param deptJobBx
     * @return int
     */
    public int selectDeptJobBxCheck(DeptJobBx deptJobBx) {
        return selectOne("DeptJobDAO.selectDeptJobBxCheck", deptJobBx);
    }

    /**
     * 부서업무함 정보를 삭제한다.
     * 
     * @param deptJobBx
     * @return int
     */
    public int deleteDeptJobBx(DeptJobBx deptJobBx) {
        return delete("DeptJobDAO.deleteDeptJobBx", deptJobBx);
    }

    /**
     * 주어진 조건에 따른 부서업무 목록을 불러온다.
     * 
     * @param deptJobVO
     * @return List
     */
    public List<DeptJobVO> selectDeptJobList(DeptJobVO deptJobVO) {
        return selectList("DeptJobDAO.selectDeptJobList", deptJobVO);
    }

    /**
     * 부서업무 목록에 대한 전체 건수를 조회한다.
     * 
     * @param deptJobVO
     * @return int
     */
    public int selectDeptJobListCnt(DeptJobVO deptJobVO) {
        return selectOne("DeptJobDAO.selectDeptJobListCnt", deptJobVO);
    }

    /**
     * 주어진 조건에 맞는 부서업무를 불러온다.
     * 
     * @param deptJobVO
     * @return DeptJobVO
     */
    public DeptJobVO selectDeptJob(DeptJobVO deptJobVO) {
        return selectOne("DeptJobDAO.selectDeptJob", deptJobVO);
    }

    /**
     * 부서업무 정보를 수정한다.
     * 
     * @param deptJob
     * @return int
     */
    public int updateDeptJob(DeptJob deptJob) {
        return update("DeptJobDAO.updateDeptJob", deptJob);
    }

    /**
     * 부서업무 정보를 등록한다.
     * 
     * @param deptJob
     * @return int
     */
    public int insertDeptJob(DeptJob deptJob) {
        return insert("DeptJobDAO.insertDeptJob", deptJob);
    }

    /**
     * 부서업무 정보를 삭제한다.
     * 
     * @param deptJob
     * @return int
     */
    public int deleteDeptJob(DeptJob deptJob) {
        return delete("DeptJobDAO.deleteDeptJob", deptJob);
    }

}