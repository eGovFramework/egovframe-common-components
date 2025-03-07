package egovframework.com.uss.umt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.umt.service.DeptManageVO;

@Repository("deptManageDAO")
public class DeptManageDAO extends EgovComAbstractDAO {

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * @param deptManageVO - 부서 Vo
	 * @return List - 부서 목록
	 * @exception Exception
	 */
	public List<DeptManageVO> selectDeptManageList(DeptManageVO deptManageVO) throws Exception {
		return selectList("deptManageDAO.selectDeptManageList", deptManageVO);
	}

    /**
	 * 부서목록 총 개수를 조회한다.
	 * @param deptManageVO - 부서 Vo
	 * @return int - 부서 카운트 수
	 * @exception Exception
	 */
    public int selectDeptManageListTotCnt(DeptManageVO deptManageVO) throws Exception {
        return (Integer)selectOne("deptManageDAO.selectDeptManageListTotCnt", deptManageVO);
    }

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param deptManageVO - 부서 Vo
	 * @return deptManageVO - 부서 Vo
	 * 
	 * @param bannerVO
	 */
	public DeptManageVO selectDeptManage(DeptManageVO deptManageVO) throws Exception {
		return (DeptManageVO) selectOne("deptManageDAO.selectDeptManage", deptManageVO);
	}

	/**
	 * 부서정보를 신규로 등록한다.
	 * @param deptManageVO - 부서 model
	 */
	public void insertDeptManage(DeptManageVO deptManageVO) throws Exception {
		insert("deptManageDAO.insertDeptManage", deptManageVO);
	}

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * @param deptManageVO - 부서 model
	 */
	public void updateDeptManage(DeptManageVO deptManageVO) throws Exception {
        update("deptManageDAO.updateDeptManage", deptManageVO);
	}

	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * @param deptManageVO - 부서 model
	 * 
	 * @param banner
	 */
	public void deleteDeptManage(DeptManageVO deptManageVO) throws Exception {
		delete("deptManageDAO.deleteDeptManage", deptManageVO);
	}

}
