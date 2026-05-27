package egovframework.com.sym.mnu.bmm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.mnu.bmm.service.BkmkMenuManage;
import egovframework.com.sym.mnu.bmm.service.BkmkMenuManageVO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

/**
 * @Class Name : BkmkMenuManageMapper.java
 * @Description : 바로가기메뉴관리 MyBatis Mapper 인터페이스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------    -------    -------------------
 *    2025.05.28  최초 생성
 *
 * @author 공통 컴포넌트 개발팀
 * @since 2025.05.28
 * @version 1.0
 * @see
 *
 */
@EgovMapper("bkmkMenuManageMapper")
public interface BkmkMenuManageMapper {

    /**
     * 바로가기메뉴관리 정보를 삭제한다.
     *
     * @param bkmkMenuManage
     * @throws Exception
     */
    void deleteBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception;

    /**
     * 바로가기메뉴관리 정보를 등록한다.
     *
     * @param bkmkMenuManage
     * @throws Exception
     */
    void insertBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception;

    /**
     * 바로가기메뉴관리 정보를 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return BkmkMenuManageVO
     * @throws Exception
     */
    BkmkMenuManageVO selectBkmkMenuManage(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

    /**
     * 조건에 맞는 바로가기메뉴관리 정보 목록을 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return List<BkmkMenuManageVO>
     * @throws Exception
     */
    List<BkmkMenuManageVO> selectBkmkMenuManageList(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

    /**
     * 조건에 맞는 바로가기메뉴관리 정보 목록의 건수를 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return int
     * @throws Exception
     */
    int selectBkmkMenuManageListCnt(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

    /**
     * 등록할 메뉴정보 목록을 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return List<BkmkMenuManageVO>
     * @throws Exception
     */
    List<BkmkMenuManageVO> selectBkmkMenuList(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

    /**
     * 등록할 메뉴정보 목록의 건수를 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return int
     * @throws Exception
     */
    int selectBkmkMenuListCnt(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

    /**
     * 미리보기를 할 바로가기메뉴관리의 목록을 조회한다.
     *
     * @param bkmkMenuManageVO
     * @return List<MenuManageVO>
     * @throws Exception
     */
    List<MenuManageVO> selectBkmkPreview(BkmkMenuManageVO bkmkMenuManageVO) throws Exception;

    /**
     * 선택된 메뉴의 URL 을 조회한다.
     *
     * @param bkmkMenuManage
     * @return String
     * @throws Exception
     */
    String selectUrl(BkmkMenuManage bkmkMenuManage) throws Exception;

}
