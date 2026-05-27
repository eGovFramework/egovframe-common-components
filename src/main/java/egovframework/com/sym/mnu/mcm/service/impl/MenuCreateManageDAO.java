package egovframework.com.sym.mnu.mcm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.com.sym.mnu.mcm.service.MenuSiteMapVO;
import jakarta.annotation.Resource;

/**
 * 메뉴생성, 사이트맵 생성에 대한 DAO 클래스를 정의한다.
 *
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
 *   2025.07.17  이백행          MenuCreateManageMapper(@EgovMapper)로 위임하도록 재작성
 *
 * </pre>
 */
@Repository("menuCreateManageDAO")
public class MenuCreateManageDAO extends EgovComAbstractDAO {

	@Resource(name = "menuCreateManageMapper")
	private MenuCreateManageMapper menuCreateManageMapper;

	/**
	 * ID 존재여부를 조회
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	public int selectUsrByPk(ComDefaultVO vo) throws Exception {
		return menuCreateManageMapper.selectUsrByPk(vo);
	}

	/**
	 * ID에 대한 권한코드를 조회
	 * @param vo ComDefaultVO
	 * @return MenuCreatVO
	 * @exception Exception
	 */
	public MenuCreatVO selectAuthorByUsr(ComDefaultVO vo) throws Exception {
		return menuCreateManageMapper.selectAuthorByUsr(vo);
	}

	/**
	 * 메뉴생성관리 내역을 조회
	 *
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<EgovMap> selectMenuCreatManagList(ComDefaultVO vo) throws Exception {
		return menuCreateManageMapper.selectMenuCreatManageList_D(vo);
	}

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 */
	public int selectMenuCreatManagTotCnt(ComDefaultVO vo) {
		return menuCreateManageMapper.selectMenuCreatManageTotCnt_S(vo);
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
		return menuCreateManageMapper.selectMenuCreatList_D(vo);
	}

	/**
	 * 메뉴생성내역 등록
	 * @param vo MenuCreatVO
	 */
	public void insertMenuCreat(MenuCreatVO vo) {
		menuCreateManageMapper.insertMenuCreat_S(vo);
	}

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	public List<EgovMap> selectMenuCreatSiteMapList(MenuSiteMapVO vo) throws Exception {
		return menuCreateManageMapper.selectMenuCreatSiteMapList_D(vo);
	}

	/**
	 * 사이트맵 등록
	 * @param vo MenuSiteMapVO
	 */
	public void creatSiteMap(MenuSiteMapVO vo) {
		menuCreateManageMapper.insertSiteMap_S(vo);
	}

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectSiteMapByUser(MenuSiteMapVO vo) throws Exception {
		return menuCreateManageMapper.selectSiteMapByUser(vo);
	}

	/**
	 * 메뉴생성내역 존재여부 조회한다.
	 * @param vo MenuCreatVO
	 * @return int
	 */
	public int selectMenuCreatCnt(MenuCreatVO vo) {
		return menuCreateManageMapper.selectMenuCreatCnt_S(vo);
	}

	/**
	 * 메뉴생성내역 수정
	 * @param vo MenuCreatVO
	 */
	public void updateMenuCreat(MenuCreatVO vo) {
		menuCreateManageMapper.updateMenuCreat_S(vo);
	}

	/**
	 * 메뉴생성내역 삭제
	 * @param vo MenuCreatVO
	 */
	public void deleteMenuCreat(MenuCreatVO vo) {
		menuCreateManageMapper.deleteMenuCreat_S(vo);
	}

	/**
	 * 사이트맵 존재여부 조회한다.
	 * @param vo MenuSiteMapVO
	 * @return int
	 */
	public int selectSiteMapCnt(MenuSiteMapVO vo) {
		return menuCreateManageMapper.selectSiteMapCnt_S(vo);
	}

}
