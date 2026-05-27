package egovframework.com.sym.mnu.mcm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.com.sym.mnu.mcm.service.MenuSiteMapVO;

/**
 * 메뉴생성, 사이트맵 생성에 대한 Mapper 인터페이스를 정의한다.
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
 *   2011.06.30  서 준 식   최초 생성
 *   2025.07.17  이백행          @EgovMapper 어노테이션 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("menuCreateManageMapper")
public interface MenuCreateManageMapper {

	/**
	 * ID 존재여부를 조회
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	int selectUsrByPk(ComDefaultVO vo) throws Exception;

	/**
	 * ID에 대한 권한코드를 조회
	 * @param vo ComDefaultVO
	 * @return MenuCreatVO
	 * @exception Exception
	 */
	MenuCreatVO selectAuthorByUsr(ComDefaultVO vo) throws Exception;

	/**
	 * 메뉴생성관리 내역을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	List<EgovMap> selectMenuCreatManageList_D(ComDefaultVO vo) throws Exception;

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 */
	int selectMenuCreatManageTotCnt_S(ComDefaultVO vo);

	/**
	 * 메뉴생성 내역을 조회
	 * @param vo MenuCreatVO
	 * @return List
	 * @exception Exception
	 */
	List<EgovMap> selectMenuCreatList_D(MenuCreatVO vo) throws Exception;

	/**
	 * 메뉴생성내역 등록
	 * @param vo MenuCreatVO
	 */
	void insertMenuCreat_S(MenuCreatVO vo);

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	List<EgovMap> selectMenuCreatSiteMapList_D(MenuSiteMapVO vo) throws Exception;

	/**
	 * 사이트맵 등록
	 * @param vo MenuSiteMapVO
	 */
	void insertSiteMap_S(MenuSiteMapVO vo);

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectSiteMapByUser(MenuSiteMapVO vo) throws Exception;

	/**
	 * 메뉴생성내역 존재여부 조회한다.
	 * @param vo MenuCreatVO
	 * @return int
	 */
	int selectMenuCreatCnt_S(MenuCreatVO vo);

	/**
	 * 메뉴생성내역 수정
	 * @param vo MenuCreatVO
	 */
	void updateMenuCreat_S(MenuCreatVO vo);

	/**
	 * 메뉴생성내역 삭제
	 * @param vo MenuCreatVO
	 */
	void deleteMenuCreat_S(MenuCreatVO vo);

	/**
	 * 사이트맵 존재여부 조회한다.
	 * @param vo MenuSiteMapVO
	 * @return int
	 */
	int selectSiteMapCnt_S(MenuSiteMapVO vo);

}
