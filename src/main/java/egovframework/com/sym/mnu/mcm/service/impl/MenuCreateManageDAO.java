package egovframework.com.sym.mnu.mcm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.com.sym.mnu.mcm.service.MenuSiteMapVO;

/**
 * 메뉴생성, 사이트맵 생성에 대한 DAO 클래스를 정의한다. *
 * @author 공통컴포넌트 개발팀 서준식
 * @since 2011.06.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.30  서 준 식   최초 생성(MenuManageDAO 클래스로 부터 분리
 *   					   메소드들을 MenuManageDAO 클래스에서 분리해옮)
 *
 * </pre>
 */

@Repository("menuCreateManageDAO")
public class MenuCreateManageDAO extends EgovComAbstractDAO{



	/**
	 * ID 존재여부를 조회
	 * @param vo MenuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectUsrByPk(ComDefaultVO vo) throws Exception{
		return (Integer)selectOne("menuManageDAO.selectUsrByPk", vo);
	}

	/**
	 * ID에 대한 권한코드를 조회
	 * @param vo MenuCreatVO
	 * @return int
	 * @exception Exception
	 */
	public MenuCreatVO selectAuthorByUsr(ComDefaultVO vo) throws Exception{
		return (MenuCreatVO)selectOne("menuManageDAO.selectAuthorByUsr", vo);
	}

	/**
     * 메뉴생성관리 내역을 조회
     * 
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    public List<EgovMap> selectMenuCreatManagList(ComDefaultVO vo) throws Exception {
        return selectList("menuManageDAO.selectMenuCreatManageList_D", vo);
    }

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
    public int selectMenuCreatManagTotCnt(ComDefaultVO vo) {
        return (Integer)selectOne("menuManageDAO.selectMenuCreatManageTotCnt_S", vo);
    }

    /*********** 메뉴 생성 관리 ***************/
    /**
     * 메뉴생성 내역을 조회
     * 
     * @param vo MenuCreatVO
     * @return List
     * @exception Exception
     */
    public List<EgovMap> selectMenuCreatList(MenuCreatVO vo) throws Exception {
        return selectList("menuManageDAO.selectMenuCreatList_D", vo);
    }

	/**
	 * 메뉴생성내역 등록
	 * @param vo MenuCreatVO
	 * @exception Exception
	 */
	public void insertMenuCreat(MenuCreatVO vo){
		insert("menuManageDAO.insertMenuCreat_S", vo);
	}

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	public List<EgovMap> selectMenuCreatSiteMapList(MenuSiteMapVO vo) throws Exception{
		return selectList("menuManageDAO.selectMenuCreatSiteMapList_D", vo);
	}



	/**
	 * 사이트맵 등록
	 * @param vo MenuSiteMapVO
	 * @exception Exception
	 */
	public void creatSiteMap(MenuSiteMapVO vo){
		insert("menuManageDAO.insertSiteMap_S", vo);
	}

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectSiteMapByUser(MenuSiteMapVO vo) throws Exception{
		return selectList("menuManageDAO.selectSiteMapByUser", vo);
	}

	/**
	 * 메뉴생성내역 존재여부 조회한다.
	 * @param vo MenuCreatVO
	 * @return int
	 * @exception Exception
	 */
    public int selectMenuCreatCnt(MenuCreatVO vo) {
        return (Integer)selectOne("menuManageDAO.selectMenuCreatCnt_S", vo);
    }


	/**
	 * 메뉴생성내역 수정
	 * @param vo MenuCreatVO
	 * @exception Exception
	 */
	public void updateMenuCreat(MenuCreatVO vo){
		update("menuManageDAO.updateMenuCreat_S", vo);
	}


	/**
	 * 메뉴생성내역 삭제
	 * @param vo MenuCreatVO
	 * @exception Exception
	 */
	public void deleteMenuCreat(MenuCreatVO vo){
		delete("menuManageDAO.deleteMenuCreat_S", vo);
	}

	/**
	 * 사이트맵 존재여부 조회한다.
	 * @param vo MenuSiteMapVO
	 * @return int
	 * @exception Exception
	 */
    public int selectSiteMapCnt(MenuSiteMapVO vo) {
        return (Integer)selectOne("menuManageDAO.selectSiteMapCnt_S", vo);
    }

}
