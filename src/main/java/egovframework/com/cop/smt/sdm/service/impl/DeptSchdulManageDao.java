package egovframework.com.cop.smt.sdm.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.smt.sdm.service.DeptSchdulManageVO;

import org.springframework.stereotype.Repository;
/**
 * 부서일정관리를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *   2016.08.01  장동한          표준프레임워크 v3.6 개선
 *
 * </pre>
 */
@Repository("deptSchdulManageDao")
public class DeptSchdulManageDao extends EgovComAbstractDAO {
	
    /**
	 * 부서 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectDeptSchdulManageAuthorGroupPopup(ComDefaultVO searchVO){
		return  (List)list("DeptSchdulManage.selectDeptSchdulAuthorGroupPopup", searchVO);
	}

    /**
	 * 아이디 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectDeptSchdulManageEmpLyrPopup(ComDefaultVO searchVO){
		return  (List)list("DeptSchdulManage.selectDeptSchdulEmpLyrPopup", searchVO);
	}
	
    /**
	 * 부서일정 목록을 Map(map)형식으로 조회한다. 
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectDeptSchdulManageMainList(Map map) throws Exception{
		 return  (List)list("DeptSchdulManage.selectDeptSchdulManageMainList", map);
	}
	
    /**
	 * 부서일정 목록을 Map(map)형식으로 조회한다. 
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectDeptSchdulManageRetrieve(Map map) throws Exception{
		 return  (List)list("DeptSchdulManage.selectDeptSchdulManageRetrieve", map);
	}
	
	
    /**
	 * 부서일정 목록을 VO(model)형식으로 조회한다. 
	 * @param deptSchdulManageVO - 조회할 정보가 담긴 VO
	 * @return DeptSchdulManageVO
	 * @exception Exception
	 */
	public DeptSchdulManageVO selectDeptSchdulManageDetailVO(DeptSchdulManageVO deptSchdulManageVO) throws Exception{
		return (DeptSchdulManageVO)selectOne("DeptSchdulManage.selectDeptSchdulManageDetailVO", deptSchdulManageVO);
	}
	
    /**
	 * 부서일정 목록을 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectDeptSchdulManageList(ComDefaultVO searchVO) throws Exception{
		return (List)list("DeptSchdulManage.selectDeptSchdulManage", searchVO);
	}
	
    /**
	 * 부서일정를(을) 상세조회 한다.
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @return List
	 * @exception Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectDeptSchdulManageDetail(DeptSchdulManageVO deptSchdulManageVO) throws Exception{
		return (List)list("DeptSchdulManage.selectDeptSchdulManageDetail", deptSchdulManageVO);
	}

    /**
	 * 부서일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectDeptSchdulManageListCnt(ComDefaultVO searchVO) throws Exception{
		return (Integer)selectOne("DeptSchdulManage.selectDeptSchdulManageCnt", searchVO);
	}
	
    /**
	 * 부서일정를(을) 등록한다.
	 * @param qdeptSchdulManageVO - 부서일정 정보 담김 VO
	 * @exception Exception
	 */
	public void insertDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception{
		insert("DeptSchdulManage.insertDeptSchdulManage", deptSchdulManageVO);
	}

    /**
	 * 부서일정를(을) 수정한다.
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @exception Exception
	 */
	public void updateDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception{
		insert("DeptSchdulManage.updateDeptSchdulManage", deptSchdulManageVO);
	}
	
    /**
	 * 부서일정를(을) 삭제한다.
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @exception Exception
	 */
	public void deleteDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception{
		// 일지 삭제
		delete("DeptSchdulManage.deleteDiaryManage", deptSchdulManageVO);
		// 부서일정 삭제
		delete("DeptSchdulManage.deleteDeptSchdulManage", deptSchdulManageVO);
	}
}
