package egovframework.com.sym.mnu.bmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.mnu.bmm.service.BkmkMenuManage;
import egovframework.com.sym.mnu.bmm.service.BkmkMenuManageVO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;


/**
 * @Class Name : BkmkMenuManageDAO.java
 * @Description : 바로가기메뉴를 관리하는 서비스를 정의하기위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 9. 25.     윤성록
 *    2026. 6. 16.     이백행       [2026년 컨트리뷰션] 불필요한 예외(throws Exception) 제거
 *
 * @author 공통 컴포넌트 개발팀 윤성록
 * @since 2009. 9. 25.
 * @version
 * @see
 *
 */
@Repository("bkmkMenuManageDAO")
public class BkmkMenuManageDAO extends EgovComAbstractDAO{

    /**
     * 바로가기메뉴관리 정보를 삭제한다.
     *
     * @param BkmkMenuManage
     * @return
     */
    public void deleteBkmkMenuManage(BkmkMenuManage bkmkMenuManage) {
        delete("BkmkMenuManageDAO.deleteBkmkMenuManage", bkmkMenuManage);
    }

    /**
     * 바로가기메뉴관리 정보를 등록한다.
     *
     * @param BkmkMenuManage
     * @return
     */
    public void insertBkmkMenuManage(BkmkMenuManage bkmkMenuManage) {
        insert("BkmkMenuManageDAO.insertBkmkMenuManage", bkmkMenuManage);
    }

    /**
     * 바로가기메뉴관리 정보를 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     */
    public BkmkMenuManageVO selectBkmkMenuManageResult(BkmkMenuManageVO bkmkMenuManageVO)
            {
        BkmkMenuManageVO vo = new BkmkMenuManageVO();
        vo = (BkmkMenuManageVO)selectOne("BkmkMenuManageDAO.selectBkmkMenuManage", bkmkMenuManageVO);
        return vo;
    }

    /**
     * 조건에 맞는 바로가기메뉴관리 정보 목록을 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     */
	public List<BkmkMenuManageVO> selectBkmkMenuManageList(BkmkMenuManageVO bkmkMenuManageVO)
            {
        return selectList("BkmkMenuManageDAO.selectBkmkMenuManageList", bkmkMenuManageVO);
    }

    /**
     * 조건에 맞는 바로가기메뉴관리 정보 목록의 건수를 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     */
    public int selectBkmkMenuManageListCnt(BkmkMenuManageVO bkmkMenuManageVO) {
        return (Integer)selectOne("BkmkMenuManageDAO.selectBkmkMenuManageListCnt", bkmkMenuManageVO);
    }

    /**
     * 등록할  메뉴정보 목록을 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     */
	public List<BkmkMenuManageVO> selectBkmkMenuList(BkmkMenuManageVO bkmkMenuManageVO)
            {
        return selectList("BkmkMenuManageDAO.selectBkmkMenuList", bkmkMenuManageVO);
    }

    /**
     * 등록할  메뉴정보 목록의 건수를 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     */
    public int selectBkmkMenuListCnt(BkmkMenuManageVO bkmkMenuManageVO) {
        return (Integer)selectOne("BkmkMenuManageDAO.selectBkmkMenuListCnt", bkmkMenuManageVO);
    }

    /**
     * 미리보기를 할 바로가기메뉴관리의 목록을 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     */
    public List<MenuManageVO> selectBkmkPreview(BkmkMenuManageVO bkmkMenuManageVO) {
        return selectList("BkmkMenuManageDAO.selectBkmkPreview", bkmkMenuManageVO);
    }

    /**
     * 선택된 메뉴의 URL 을 조회한다.
     *
     * @param bkmkMenuManage
     * @return
     */
    public String selectUrl(BkmkMenuManage bkmkMenuManage) {
        return (String)selectOne("BkmkMenuManageDAO.selectUrl", bkmkMenuManage);
    }
}

