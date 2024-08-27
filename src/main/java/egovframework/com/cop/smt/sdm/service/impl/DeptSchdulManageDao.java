package egovframework.com.cop.smt.sdm.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.smt.sdm.service.DeptSchdulManageVO;

/**
 * 부서일정관리를 처리하는 Dao Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *   2016.08.01  장동한          표준프레임워크 v3.6 개선
 *   2024.08.28  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *      </pre>
 */
@Repository("deptSchdulManageDao")
public class DeptSchdulManageDao extends EgovComAbstractDAO {

	/**
	 * 부서 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List @
	 */
	public List<EgovMap> selectDeptSchdulManageAuthorGroupPopup(ComDefaultVO searchVO) {
		return selectList("DeptSchdulManage.selectDeptSchdulAuthorGroupPopup", searchVO);
	}

	/**
	 * 아이디 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List @
	 */
	public List<EgovMap> selectDeptSchdulManageEmpLyrPopup(ComDefaultVO searchVO) {
		return selectList("DeptSchdulManage.selectDeptSchdulEmpLyrPopup", searchVO);
	}

	/**
	 * 부서일정 목록을 Map(map)형식으로 조회한다.
	 * 
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageMainList(Map<String, String> map) {
		return selectList("DeptSchdulManage.selectDeptSchdulManageMainList", map);
	}

	/**
	 * 부서일정 목록을 Map(map)형식으로 조회한다.
	 * 
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageRetrieve(Map<String, String> map) {
		return selectList("DeptSchdulManage.selectDeptSchdulManageRetrieve", map);
	}

	/**
	 * 부서일정 목록을 VO(model)형식으로 조회한다.
	 * 
	 * @param deptSchdulManageVO - 조회할 정보가 담긴 VO
	 * @return DeptSchdulManageVO
	 */
	public DeptSchdulManageVO selectDeptSchdulManageDetailVO(DeptSchdulManageVO deptSchdulManageVO) {
		return (DeptSchdulManageVO) selectOne("DeptSchdulManage.selectDeptSchdulManageDetailVO", deptSchdulManageVO);
	}

	/**
	 * 부서일정 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageList(ComDefaultVO searchVO) {
		return selectList("DeptSchdulManage.selectDeptSchdulManage", searchVO);
	}

	/**
	 * 부서일정를(을) 상세조회 한다.
	 * 
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageDetail(DeptSchdulManageVO deptSchdulManageVO) {
		return selectList("DeptSchdulManage.selectDeptSchdulManageDetail", deptSchdulManageVO);
	}

	/**
	 * 부서일정를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 */
	public int selectDeptSchdulManageListCnt(ComDefaultVO searchVO) {
		return (Integer) selectOne("DeptSchdulManage.selectDeptSchdulManageCnt", searchVO);
	}

	/**
	 * 부서일정를(을) 등록한다.
	 * 
	 * @param qdeptSchdulManageVO - 부서일정 정보 담김 VO
	 */
	public void insertDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) {
		insert("DeptSchdulManage.insertDeptSchdulManage", deptSchdulManageVO);
	}

	/**
	 * 부서일정를(을) 수정한다.
	 * 
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 */
	public void updateDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) {
		insert("DeptSchdulManage.updateDeptSchdulManage", deptSchdulManageVO);
	}

	/**
	 * 부서일정를(을) 삭제한다.
	 * 
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 */
	public void deleteDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) {
		// 일지 삭제
		delete("DeptSchdulManage.deleteDiaryManage", deptSchdulManageVO);
		// 부서일정 삭제
		delete("DeptSchdulManage.deleteDeptSchdulManage", deptSchdulManageVO);
	}
}
