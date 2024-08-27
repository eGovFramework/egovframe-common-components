package egovframework.com.cop.smt.sdm.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 부서일정관리를 처리하는 Service Class 구현
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
 *   2024.08.28  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *      </pre>
 */
public interface EgovDeptSchdulManageService {

	/**
	 * 부서 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageAuthorGroupPopup(ComDefaultVO searchVO);

	/**
	 * 아이디 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageEmpLyrPopup(ComDefaultVO searchVO);

	/**
	 * 메인페이지/부서일정관리조회
	 * 
	 * @param map
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageMainList(Map<String, String> map);

	/**
	 * 부서일정 목록을 조회한다.
	 * 
	 * @param map
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageRetrieve(Map<String, String> map);

	/**
	 * 부서일정 목록을 VO(model)형식으로 조회한다.
	 * 
	 * @param deptSchdulManageVO
	 * @return List
	 */
	public DeptSchdulManageVO selectDeptSchdulManageDetailVO(DeptSchdulManageVO deptSchdulManageVO);

	/**
	 * 부서일정 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageList(ComDefaultVO searchVO);

	/**
	 * 부서일정를(을) 상세조회 한다.
	 * 
	 * @param deptSchdulManageVO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageDetail(DeptSchdulManageVO deptSchdulManageVO);

	/**
	 * 부서일정를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO
	 * @return int
	 */
	public int selectDeptSchdulManageListCnt(ComDefaultVO searchVO);

	/**
	 * 부서일정을 등록한다.
	 * 
	 * @param deptSchdulManageVO
	 */
	void insertDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception;

	/**
	 * 부서일정를(을) 수정한다.
	 * 
	 * @param deptSchdulManageVO
	 */
	void updateDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO);

	/**
	 * 부서일정를(을) 삭제한다.
	 * 
	 * @param deptSchdulManageVO
	 */
	void deleteDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO);

}
