package egovframework.com.sym.mnu.mpm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

/**
 * 메뉴관리, 메뉴생성, 사이트맵 생성에 대한 Mapper 인터페이스를 정의한다.
 *
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *   2011.07.01  서준식          selectUpperMenuNoByPk() 메서드 추가
 *
 * </pre>
 */
@EgovMapper("menuManageMapper")
public interface MenuManageMapper {

    /**
     * 메뉴목록을 조회한다.
     *
     * @param vo ComDefaultVO
     * @return List
     */
    List<EgovMap> selectMenuManageList_D(ComDefaultVO vo);

    /**
     * 메뉴목록관리 총건수를 조회한다.
     *
     * @param vo ComDefaultVO
     * @return int
     */
    int selectMenuManageListTotCnt_S(ComDefaultVO vo);

    /**
     * 메뉴목록관리 기본정보를 조회한다.
     *
     * @param vo ComDefaultVO
     * @return MenuManageVO
     */
    MenuManageVO selectMenuManage_D(ComDefaultVO vo);

    /**
     * 메뉴목록 기본정보를 등록한다.
     *
     * @param vo MenuManageVO
     */
    void insertMenuManage_S(MenuManageVO vo);

    /**
     * 메뉴목록 기본정보를 수정한다.
     *
     * @param vo MenuManageVO
     */
    void updateMenuManage_S(MenuManageVO vo);

    /**
     * 메뉴목록 기본정보를 삭제한다.
     *
     * @param vo MenuManageVO
     */
    void deleteMenuManage_S(MenuManageVO vo);

    /**
     * 메뉴 전체목록을 조회한다.
     *
     * @param vo ComDefaultVO
     * @return List
     */
    List<EgovMap> selectMenuListT_D(ComDefaultVO vo);

    /**
     * 메뉴번호 존재여부를 조회한다.
     *
     * @param vo MenuManageVO
     * @return int
     */
    int selectMenuNoByPk(MenuManageVO vo);

    /**
     * 메뉴번호를 상위메뉴로 참조하고 있는 메뉴 존재여부를 조회한다.
     *
     * @param vo MenuManageVO
     * @return int
     */
    int selectUpperMenuNoByPk(MenuManageVO vo);

    /**
     * 메뉴정보 전체를 삭제한다.
     */
    void deleteAllMenuList();

    /**
     * 메뉴정보 존재여부 총건수를 조회한다.
     *
     * @param vo MenuManageVO
     * @return int
     */
    int selectMenuListTotCnt(MenuManageVO vo);

    /**
     * MainMenu Head Menu를 조회한다.
     *
     * @param vo MenuManageVO
     * @return List
     */
    List<EgovMap> selectMainMenuHead(MenuManageVO vo);

    /**
     * MainMenu Left Menu를 조회한다.
     *
     * @param vo MenuManageVO
     * @return List
     */
    List<EgovMap> selectMainMenuLeft(MenuManageVO vo);

    /**
     * MainMenu Head MenuURL을 조회한다.
     *
     * @param vo MenuManageVO
     * @return String
     */
    String selectLastMenuURL(MenuManageVO vo);

    /**
     * MainMenu Last MenuNo를 조회한다.
     *
     * @param vo MenuManageVO
     * @return int
     */
    int selectLastMenuNo(MenuManageVO vo);

    /**
     * MainMenu Last MenuNo 건수를 조회한다.
     *
     * @param vo MenuManageVO
     * @return int
     */
    int selectLastMenuNoCnt(MenuManageVO vo);

}
