package egovframework.com.sym.mnu.mcm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.mnu.mcm.service.EgovMenuCreateManageService;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.com.sym.mnu.mcm.service.MenuSiteMapVO;

/**
 * 메뉴목록, 사이트맵 생성을 처리하는 비즈니스 구현 클래스를 정의한다.
 * 
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이용           최초 생성
 *   2011.07.01  서준식          EgovMenuManageServiceImpl에서 메뉴 생성 관련 부분 분리
 *   2011.10.07  이기하          finally문을 추가하여 에러시 자원반환할 수 있도록 추가
 *   2011.10.12  이기하          사이트맵 생성시 특수문자 치환
 *   2025.07.16  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Service("meunCreateManageService")
public class EgovMenuCreateManageServiceImpl extends EgovAbstractServiceImpl implements EgovMenuCreateManageService {

	@Resource(name = "menuCreateManageDAO")
	private MenuCreateManageDAO menuCreateManageDAO;

	/**
	 * ID 존재여부를 조회
	 * 
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectUsrByPk(ComDefaultVO vo) throws Exception {
		return menuCreateManageDAO.selectUsrByPk(vo);
	}

	/**
	 * 메뉴생성 내역을 조회
	 * 
	 * @param vo MenuCreatVO
	 * @return List
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> selectMenuCreatList(MenuCreatVO vo) throws Exception {
		return menuCreateManageDAO.selectMenuCreatList(vo);
	}

	/**
	 * 화면에 조회된 메뉴정보로 메뉴생성내역 데이터베이스에서 입력
	 * 
	 * @param checkedAuthorForInsert String
	 * @param checkedMenuNoForInsert String
	 * @exception Exception
	 */
	@Override
	public void insertMenuCreatList(String checkedAuthorForInsert, String checkedMenuNoForInsert) throws Exception {
		MenuCreatVO menuCreatVO = null;
		String[] insertMenuNo = checkedMenuNoForInsert.split(",");

		String insertAuthor = checkedAuthorForInsert;
		menuCreatVO = new MenuCreatVO();
		menuCreatVO.setAuthorCode(insertAuthor);
		int resultMenuCreatCnt = menuCreateManageDAO.selectMenuCreatCnt(menuCreatVO);

		// 이전에 존재하는 권한코드에 대한 메뉴설정내역 삭제
		if (resultMenuCreatCnt > 0) {
			menuCreateManageDAO.deleteMenuCreat(menuCreatVO);
		}
		for (int i = 0; i < insertMenuNo.length; i++) {
			menuCreatVO.setAuthorCode(insertAuthor);
			menuCreatVO.setMenuNo(Integer.parseInt(insertMenuNo[i]));
			menuCreateManageDAO.insertMenuCreat(menuCreatVO);
		}
	}

	/**
	 * 메뉴생성관리 목록을 조회
	 * 
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> selectMenuCreatManagList(ComDefaultVO vo) throws Exception {
		return menuCreateManageDAO.selectMenuCreatManagList(vo);
	}

	/**
	 * ID에 대한 권한코드를 조회
	 * 
	 * @param vo ComDefaultVO
	 * @return MenuCreatVO
	 * @exception Exception
	 */
	@Override
	public MenuCreatVO selectAuthorByUsr(ComDefaultVO vo) throws Exception {
		return menuCreateManageDAO.selectAuthorByUsr(vo);
	}

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * 
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectMenuCreatManagTotCnt(ComDefaultVO vo) throws Exception {
		return menuCreateManageDAO.selectMenuCreatManagTotCnt(vo);
	}

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * 
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	@Override
	public List<EgovMap> selectMenuCreatSiteMapList(MenuSiteMapVO vo) throws Exception {
		return menuCreateManageDAO.selectMenuCreatSiteMapList(vo);
	}

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * 
	 * @param vo MenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	@Override
	public List<?> selectSiteMapByUser(MenuSiteMapVO vo) throws Exception {
		return menuCreateManageDAO.selectSiteMapByUser(vo);
	}

	/**
	 * 사이트맵 등록 개발환경에서 테스트용 함수로 보안 취약
	 * 
	 * @param menuSiteMapvo MenuSiteMapVO
	 * @param vHtmlValue    String
	 * @return boolean
	 * @exception Exception
	 */
	/*
	 * public boolean creatSiteMap(MenuSiteMapVO menuSiteMapvo, String vHtmlValue)
	 * throws Exception { boolean chkCreat = false; String vSiteMapName = null; int
	 * SiteMapCnt = 0; //String newMapCreatId = null; MenuCreatVO menuCreatVO = new
	 * MenuCreatVO();
	 * 
	 * menuCreatVO.setMenuNo(menuSiteMapvo.getMenuNo());
	 * menuCreatVO.setAuthorCode(menuSiteMapvo.getAuthorCode()); //vSiteMapName =
	 * menuSiteMapvo.getTmp_rootPath()+"/"+menuSiteMapvo.getBndeFileNm();
	 * vSiteMapName = menuSiteMapvo.getTmpRootPath() +
	 * menuSiteMapvo.getBndeFilePath() + menuSiteMapvo.getBndeFileNm(); chkCreat =
	 * siteMapCreat(vSiteMapName, vHtmlValue); if (chkCreat) { SiteMapCnt =
	 * menuCreateManageDAO.selectSiteMapCnt(menuSiteMapvo); if (SiteMapCnt > 0) {
	 * menuCreatVO.setMapCreatId(menuSiteMapvo.getMapCreatId() +
	 * Integer.toString(SiteMapCnt));
	 * menuSiteMapvo.setMapCreatId(menuSiteMapvo.getMapCreatId() +
	 * Integer.toString(SiteMapCnt)); } else {
	 * menuCreatVO.setMapCreatId(menuSiteMapvo.getMapCreatId()); }
	 * menuCreateManageDAO.creatSiteMap(menuSiteMapvo);
	 * menuCreateManageDAO.updateMenuCreat(menuCreatVO);
	 * 
	 * } return chkCreat; }
	 */

	/**
	 * 메뉴생성 사이트맵 Html 파일 생성 개발환경에서 테스트용 함수로 보안 취약
	 * 
	 * @param vSiteMapName String
	 * @param vHtmlValue   String
	 * @return boolean
	 * @exception Exception
	 */
	/*
	 * private boolean siteMapCreat(String vSiteMapName, String vHtmlValue) throws
	 * Exception { boolean success = false; String FileName = null; char
	 * FILE_SEPARATOR = File.separatorChar; BufferedWriter out = null; try {
	 * FileName = vSiteMapName.replace('\\', FILE_SEPARATOR).replace('/',
	 * FILE_SEPARATOR); File file = new
	 * File(EgovWebUtil.filePathBlackList(FileName)); out = new BufferedWriter(new
	 * FileWriter(file));
	 * 
	 * // 사이트맵 생성시 특수문자 치환 vHtmlValue = vHtmlValue.replaceAll("&lt;", "<");
	 * vHtmlValue = vHtmlValue.replaceAll("&gt;", ">"); vHtmlValue =
	 * vHtmlValue.replaceAll("&quot;", "\""); vHtmlValue =
	 * vHtmlValue.replaceAll("&apos;", "'");
	 * 
	 * out.write(vHtmlValue); success = true; } catch (IOException e) {
	 * LOGGER.error("IOException", e); } finally {
	 * EgovResourceCloseHelper.close(out); }
	 * 
	 * return success; }
	 */
}