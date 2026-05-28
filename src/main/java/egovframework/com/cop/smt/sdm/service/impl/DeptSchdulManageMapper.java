package egovframework.com.cop.smt.sdm.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cop.smt.sdm.service.DeptSchdulManageVO;

/**
 * 부서일정관리를 위한 Mapper 인터페이스
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
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 *
 * </pre>
 */
@EgovMapper("deptSchdulManageMapper")
public interface DeptSchdulManageMapper {

	List<EgovMap> selectDeptSchdulAuthorGroupPopup(ComDefaultVO searchVO);

	List<EgovMap> selectDeptSchdulEmpLyrPopup(ComDefaultVO searchVO);

	List<EgovMap> selectDeptSchdulManageMainList(Map<String, String> map);

	List<EgovMap> selectDeptSchdulManageRetrieve(Map<String, String> map);

	DeptSchdulManageVO selectDeptSchdulManageDetailVO(DeptSchdulManageVO deptSchdulManageVO);

	List<EgovMap> selectDeptSchdulManage(ComDefaultVO searchVO);

	List<EgovMap> selectDeptSchdulManageDetail(DeptSchdulManageVO deptSchdulManageVO);

	int selectDeptSchdulManageCnt(ComDefaultVO searchVO);

	int insertDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO);

	int updateDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO);

	int deleteDiaryManage(DeptSchdulManageVO deptSchdulManageVO);

	int deleteDeptSchdulManage(DeptSchdulManageVO deptSchdulManageVO);

}
