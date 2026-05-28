package egovframework.com.cop.smt.sdm.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cop.smt.sdm.service.DeptSchdulManageVO;
import jakarta.annotation.Resource;

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
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 *
 * </pre>
 */
@Repository("deptSchdulManageDao")
public class DeptSchdulManageDao {

	@Resource(name = "deptSchdulManageMapper")
	private DeptSchdulManageMapper mapper;

	/**
	 * 부서 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageAuthorGroupPopup(ComDefaultVO searchVO) {
		return mapper.selectDeptSchdulAuthorGroupPopup(searchVO);
	}

	/**
	 * 아이디 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 */
	public List<EgovMap> selectDeptSchdulManageEmpLyrPopup(ComDefaultVO searchVO) {
		return mapper.selectDeptSchdulEmpLyrPopup(searchVO);
	}

	/**
	 * 부서일정 목록을 Map(map)형식으로 조회한다.
	 * @param map - 조회할 정보가 담긴 Map
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectDeptSchdulManageMainList(Map<String, String> map) throws Exception {
		return mapper.selectDeptSchdulManageMainList(map);
	}

	/**
	 * 부서일정 목록을 Map(map)형식으로 조회한다.
	 * @param map - 조회할 정보가 담긴 Map
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectDeptSchdulManageRetrieve(Map<String, String> map) throws Exception {
		return mapper.selectDeptSchdulManageRetrieve(map);
	}

	/**
	 * 부서일정 목록을 VO(model)형식으로 조회한다.
	 * @param deptSchdulManageVO - 조회할 정보가 담긴 VO
	 * @return DeptSchdulManageVO
	 * @throws Exception
	 */
	public DeptSchdulManageVO selectDeptSchdulManageDetailVO(DeptSchdulManageVO deptSchdulManageVO) throws Exception {
		return mapper.selectDeptSchdulManageDetailVO(deptSchdulManageVO);
	}

	/**
	 * 부서일정 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectDeptSchdulManageList(ComDefaultVO searchVO) throws Exception {
		return mapper.selectDeptSchdulManage(searchVO);
	}

	/**
	 * 부서일정를(을) 상세조회 한다.
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectDeptSchdulManageDetail(DeptSchdulManageVO deptSchdulManageVO) throws Exception {
		return mapper.selectDeptSchdulManageDetail(deptSchdulManageVO);
	}

	/**
	 * 부서일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectDeptSchdulManageListCnt(ComDefaultVO searchVO) throws Exception {
		return mapper.selectDeptSchdulManageCnt(searchVO);
	}

	/**
	 * 부서일정를(을) 등록한다.
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @throws Exception
	 */
	public void insertDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception {
		mapper.insertDeptSchdulManage(deptSchdulManageVO);
	}

	/**
	 * 부서일정를(을) 수정한다.
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @throws Exception
	 */
	public void updateDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception {
		mapper.updateDeptSchdulManage(deptSchdulManageVO);
	}

	/**
	 * 부서일정를(을) 삭제한다.
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @throws Exception
	 */
	public void deleteDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO) throws Exception {
		// 일지 삭제
		mapper.deleteDiaryManage(deptSchdulManageVO);
		// 부서일정 삭제
		mapper.deleteDeptSchdulManage(deptSchdulManageVO);
	}
}
