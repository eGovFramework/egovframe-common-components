package egovframework.com.uss.umt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.umt.service.DeptManageVO;

/**
 * 부서관리에 관한 데이터 접근 Mapper 인터페이스를 정의한다.
 * @author 공통서비스 개발팀
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  공통서비스 개발팀   최초 생성
 *   2026.05.28  표준프레임워크센터   @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("deptManageMapper")
public interface DeptManageMapper {

    /**
     * 부서목록을 조회한다.
     * @param deptManageVO 부서 Vo
     * @return List 부서 목록
     */
    List<DeptManageVO> selectDeptManageList(DeptManageVO deptManageVO);

    /**
     * 부서목록 총 개수를 조회한다.
     * @param deptManageVO 부서 Vo
     * @return int 부서 카운트 수
     */
    int selectDeptManageListTotCnt(DeptManageVO deptManageVO);

    /**
     * 부서 상세정보를 조회한다.
     * @param deptManageVO 부서 Vo
     * @return DeptManageVO 부서 Vo
     */
    DeptManageVO selectDeptManage(DeptManageVO deptManageVO);

    /**
     * 부서정보를 신규로 등록한다.
     * @param deptManageVO 부서 model
     */
    void insertDeptManage(DeptManageVO deptManageVO);

    /**
     * 기 등록된 부서정보를 수정한다.
     * @param deptManageVO 부서 model
     */
    void updateDeptManage(DeptManageVO deptManageVO);

    /**
     * 기 등록된 부서정보를 삭제한다.
     * @param deptManageVO 부서 model
     */
    void deleteDeptManage(DeptManageVO deptManageVO);

}
