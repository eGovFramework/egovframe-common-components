package egovframework.com.cop.smt.sdm.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 부서일정관리를 처리하는 Service Class 구현
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
 *
 * </pre>
 */
public interface EgovDeptSchdulManageService {

    /**
	 * 부서 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectDeptSchdulManageAuthorGroupPopup(ComDefaultVO searchVO) throws Exception;
    /**
	 * 아이디 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectDeptSchdulManageEmpLyrPopup(ComDefaultVO searchVO) throws Exception;
	
	/**
	 * 메인페이지/부서일정관리조회 
	 * @param map
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectDeptSchdulManageMainList(Map<?, ?> map) throws Exception;
	
	/**
	 * 부서일정 목록을 조회한다.
	 * @param map
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectDeptSchdulManageRetrieve(Map<?, ?> map) throws Exception;
	
	/**
	 * 부서일정 목록을 VO(model)형식으로 조회한다. 
	 * @param deptSchdulManageVO
	 * @return List
	 * @throws Exception
	 */
	public DeptSchdulManageVO selectDeptSchdulManageDetailVO(DeptSchdulManageVO deptSchdulManageVO) throws Exception;

	/**
	 * 부서일정 목록을 조회한다. 
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectDeptSchdulManageList(ComDefaultVO searchVO) throws Exception;
	
	/**
	 *  부서일정를(을) 상세조회 한다.
	 * @param deptSchdulManageVO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectDeptSchdulManageDetail(DeptSchdulManageVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 부서일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO
	 * @return int
	 * @throws Exception
	 */
	public int selectDeptSchdulManageListCnt(ComDefaultVO searchVO) throws Exception;
	
	/**
	 * 부서일정을 등록한다.
	 * @param deptSchdulManageVO
	 * @throws Exception
	 */
	void  insertDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 부서일정를(을) 수정한다.
	 * @param deptSchdulManageVO
	 * @throws Exception
	 */
	void  updateDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 부서일정를(을) 삭제한다.
	 * @param deptSchdulManageVO
	 * @throws Exception
	 */
	void  deleteDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception;
	
	
}
