package egovframework.com.uss.umt.service.impl;

import java.util.List;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.umt.service.DeptManageVO;

/**
 * 부서관리에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("deptManageDAO")
public class DeptManageDAO {

    @Resource(name = "deptManageMapper")
    private DeptManageMapper deptManageMapper;

    /**
     * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
     * @param deptManageVO - 부서 Vo
     * @return List - 부서 목록
     * @exception Exception
     */
    public List<DeptManageVO> selectDeptManageList(DeptManageVO deptManageVO) throws Exception {
        return deptManageMapper.selectDeptManageList(deptManageVO);
    }

    /**
     * 부서목록 총 개수를 조회한다.
     * @param deptManageVO - 부서 Vo
     * @return int - 부서 카운트 수
     * @exception Exception
     */
    public int selectDeptManageListTotCnt(DeptManageVO deptManageVO) throws Exception {
        return deptManageMapper.selectDeptManageListTotCnt(deptManageVO);
    }

    /**
     * 등록된 부서의 상세정보를 조회한다.
     * @param deptManageVO - 부서 Vo
     * @return deptManageVO - 부서 Vo
     */
    public DeptManageVO selectDeptManage(DeptManageVO deptManageVO) throws Exception {
        return deptManageMapper.selectDeptManage(deptManageVO);
    }

    /**
     * 부서정보를 신규로 등록한다.
     * @param deptManageVO - 부서 model
     */
    public void insertDeptManage(DeptManageVO deptManageVO) throws Exception {
        deptManageMapper.insertDeptManage(deptManageVO);
    }

    /**
     * 기 등록된 부서정보를 수정한다.
     * @param deptManageVO - 부서 model
     */
    public void updateDeptManage(DeptManageVO deptManageVO) throws Exception {
        deptManageMapper.updateDeptManage(deptManageVO);
    }

    /**
     * 기 등록된 부서정보를 삭제한다.
     * @param deptManageVO - 부서 model
     */
    public void deleteDeptManage(DeptManageVO deptManageVO) throws Exception {
        deptManageMapper.deleteDeptManage(deptManageVO);
    }

}
