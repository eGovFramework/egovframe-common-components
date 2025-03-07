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
     * @throws Exception
     */
    public void deleteBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception {
        delete("BkmkMenuManageDAO.deleteBkmkMenuManage", bkmkMenuManage);
    }

    /**
     * 바로가기메뉴관리 정보를 등록한다.
     *
     * @param BkmkMenuManage
     * @return
     * @throws Exception
     */
    public void insertBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception {
        insert("BkmkMenuManageDAO.insertBkmkMenuManage", bkmkMenuManage);
    }

    /**
     * 바로가기메뉴관리 정보를 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     * @throws Exception
     */
    public BkmkMenuManageVO selectBkmkMenuManageResult(BkmkMenuManageVO bkmkMenuManageVO)
            throws Exception {
        BkmkMenuManageVO vo = new BkmkMenuManageVO();
        vo = (BkmkMenuManageVO)selectOne("BkmkMenuManageDAO.selectBkmkMenuManage", bkmkMenuManageVO);
        return vo;
    }

    /**
     * 조건에 맞는 바로가기메뉴관리 정보 목록을 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     * @throws Exception
     */
	public List<BkmkMenuManageVO> selectBkmkMenuManageList(BkmkMenuManageVO bkmkMenuManageVO)
            throws Exception {
        return selectList("BkmkMenuManageDAO.selectBkmkMenuManageList", bkmkMenuManageVO);
    }

    /**
     * 조건에 맞는 바로가기메뉴관리 정보 목록의 건수를 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     * @throws Exception
     */
    public int selectBkmkMenuManageListCnt(BkmkMenuManageVO bkmkMenuManageVO) throws Exception {
        return (Integer)selectOne("BkmkMenuManageDAO.selectBkmkMenuManageListCnt", bkmkMenuManageVO);
    }

    /**
     * 등록할  메뉴정보 목록을 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     * @throws Exception
     */
	public List<BkmkMenuManageVO> selectBkmkMenuList(BkmkMenuManageVO bkmkMenuManageVO)
            throws Exception {
        return selectList("BkmkMenuManageDAO.selectBkmkMenuList", bkmkMenuManageVO);
    }

    /**
     * 등록할  메뉴정보 목록의 건수를 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     * @throws Exception
     */
    public int selectBkmkMenuListCnt(BkmkMenuManageVO bkmkMenuManageVO) throws Exception {
        return (Integer)selectOne("BkmkMenuManageDAO.selectBkmkMenuListCnt", bkmkMenuManageVO);
    }

    /**
     * 미리보기를 할 바로가기메뉴관리의 목록을 조회한다.
     *
     * @param BkmkMenuManageVO
     * @return
     * @throws Exception
     */
    public List<MenuManageVO> selectBkmkPreview(BkmkMenuManageVO bkmkMenuManageVO) throws Exception {
        return selectList("BkmkMenuManageDAO.selectBkmkPreview", bkmkMenuManageVO);
    }

    /**
     * 선택된 메뉴의 URL 을 조회한다.
     *
     * @param bkmkMenuManage
     * @return
     * @throws Exception
     */
    public String selectUrl(BkmkMenuManage bkmkMenuManage) throws Exception {
        return (String)selectOne("BkmkMenuManageDAO.selectUrl", bkmkMenuManage);
    }
}

