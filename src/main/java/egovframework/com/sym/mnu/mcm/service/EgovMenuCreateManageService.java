package egovframework.com.sym.mnu.mcm.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;


/**
 * 메뉴관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *
 * </pre>
 */
public interface EgovMenuCreateManageService {

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
	 * @return List
	 * @exception Exception
	 */
	MenuCreatVO selectAuthorByUsr(ComDefaultVO vo) throws Exception;


	/**
	 * 메뉴생성관리 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMenuCreatManagList(ComDefaultVO vo) throws Exception;

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	int selectMenuCreatManagTotCnt(ComDefaultVO vo) throws Exception;

	/**
	 * 메뉴생성 내역을 조회
	 * @param  vo MenuCreatVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMenuCreatList(MenuCreatVO vo) throws Exception;


	/**
	 * 화면에 조회된 메뉴정보로 메뉴생성내역 데이터베이스에서 입력
	 * @param checkedScrtyForInsert String
	 * @param checkedMenuNoForInsert String
	 * @exception Exception
	 */
	void insertMenuCreatList(String checkedScrtyForInsert, String checkedMenuNoForInsert) throws Exception;

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMenuCreatSiteMapList(MenuSiteMapVO vo) throws Exception;

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	 List<?> selectSiteMapByUser(MenuSiteMapVO vo) throws Exception;

	 /**
	 * 사이트맵 등록
	 * @param vo MenuSiteMapVO
	 * @param vHtmlValue String
	 * @return boolean
	 * @exception Exception
	 */
	 boolean creatSiteMap(MenuSiteMapVO vo, String vHtmlValue) throws Exception;
}
