package egovframework.com.sym.mnu.bmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.sym.mnu.bmm.service.BkmkMenuManage;
import egovframework.com.sym.mnu.bmm.service.BkmkMenuManageVO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import jakarta.annotation.Resource;

/**
 * @Class Name : BkmkMenuManageDAO.java
 * @Description : 바로가기메뉴를 관리하는 서비스를 정의하기위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 9. 25.     윤성록
 *    2025. 5. 28.               BkmkMenuManageMapper(@EgovMapper) 위임 방식으로 전환
 *
 * @author 공통 컴포넌트 개발팀 윤성록
 * @since 2009. 9. 25.
 * @version 1.0
 * @see
 *
 */
@Repository("bkmkMenuManageDAO")
public class BkmkMenuManageDAO {

    @Resource(name = "bkmkMenuManageMapper")
    private BkmkMenuManageMapper bkmkMenuManageMapper;

    /**
     * 바로가기메뉴관리 정보를 삭제한다.
     *
     * @param bkmkMenuManage
     * @throws Exception
     */
    public void deleteBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception {
        bkmkMenuManageMapper.deleteBkmkMenuManage(bkmkMenuManage);
    }

    /**
     * 바로가기메뉴관리 정보를 등록한다.
     *
     * @param bkmkMenuManage
     * @throws Exception
     */
    public void insertBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception {
        bkmkMenuManageMapper.insertBkmkMenuManage(bkmkMenuManage);
    }

    /**
     * 바로가기메뉴관리 정보를 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return BkmkMenuManageVO
     * @throws Exception
     */
    public BkmkMenuManageVO selectBkmkMenuManageResult(BkmkMenuManageVO bkmkMenuManageVO) throws Exception {
        return bkmkMenuManageMapper.selectBkmkMenuManage(bkmkMenuManageVO);
    }

    /**
     * 조건에 맞는 바로가기메뉴관리 정보 목록을 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return List<BkmkMenuManageVO>
     * @throws Exception
     */
    public List<BkmkMenuManageVO> selectBkmkMenuManageList(BkmkMenuManageVO bkmkMenuManageVO) throws Exception {
        return bkmkMenuManageMapper.selectBkmkMenuManageList(bkmkMenuManageVO);
    }

    /**
     * 조건에 맞는 바로가기메뉴관리 정보 목록의 건수를 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return int
     * @throws Exception
     */
    public int selectBkmkMenuManageListCnt(BkmkMenuManageVO bkmkMenuManageVO) throws Exception {
        return bkmkMenuManageMapper.selectBkmkMenuManageListCnt(bkmkMenuManageVO);
    }

    /**
     * 등록할 메뉴정보 목록을 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return List<BkmkMenuManageVO>
     * @throws Exception
     */
    public List<BkmkMenuManageVO> selectBkmkMenuList(BkmkMenuManageVO bkmkMenuManageVO) throws Exception {
        return bkmkMenuManageMapper.selectBkmkMenuList(bkmkMenuManageVO);
    }

    /**
     * 등록할 메뉴정보 목록의 건수를 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return int
     * @throws Exception
     */
    public int selectBkmkMenuListCnt(BkmkMenuManageVO bkmkMenuManageVO) throws Exception {
        return bkmkMenuManageMapper.selectBkmkMenuListCnt(bkmkMenuManageVO);
    }

    /**
     * 미리보기를 할 바로가기메뉴관리의 목록을 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return List<MenuManageVO>
     * @throws Exception
     */
    public List<MenuManageVO> selectBkmkPreview(BkmkMenuManageVO bkmkMenuManageVO) throws Exception {
        return bkmkMenuManageMapper.selectBkmkPreview(bkmkMenuManageVO);
    }

    /**
     * 선택된 메뉴의 URL 을 조회한다.
     *
     * @param bkmkMenuManage
     * @return String
     * @throws Exception
     */
    public String selectUrl(BkmkMenuManage bkmkMenuManage) throws Exception {
        return bkmkMenuManageMapper.selectUrl(bkmkMenuManage);
    }

}

