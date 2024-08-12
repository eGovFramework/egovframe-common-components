package egovframework.com.sym.mnu.mpm.service;

import java.io.InputStream;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 메뉴관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * 
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *   2011.07.01  서준식			자기 메뉴 정보를 상위메뉴 정보로 참조하는 메뉴정보가 있는지 조회하는
 *   							selectUpperMenuNoByPk() 메서드 추가
 *   2024.08.13  이백행          시큐어코딩 Exception 제거
 *
 *      </pre>
 */

public interface EgovMenuManageService {

	/**
	 * 메뉴 상세정보를 조회
	 * 
	 * @param vo ComDefaultVO
	 * @return MenuManageVO
	 */
	MenuManageVO selectMenuManage(ComDefaultVO vo);

	/**
	 * 메뉴 목록을 조회
	 * 
	 * @param vo ComDefaultVO
	 * @return List
	 */
	List<EgovMap> selectMenuManageList(ComDefaultVO vo);

	/**
	 * 메뉴목록 총건수를 조회한다.
	 * 
	 * @param vo ComDefaultVO
	 * @return int
	 */
	int selectMenuManageListTotCnt(ComDefaultVO vo);

	/**
	 * 메뉴번호 존재 여부를 조회한다.
	 * 
	 * @param vo ComDefaultVO
	 * @return int
	 */
	int selectMenuNoByPk(MenuManageVO vo);

	int selectUpperMenuNoByPk(MenuManageVO vo);

	/**
	 * 메뉴 정보를 등록
	 * 
	 * @param vo MenuManageVO
	 */
	void insertMenuManage(MenuManageVO vo);

	/**
	 * 메뉴 정보를 수정
	 * 
	 * @param vo MenuManageVO
	 */
	void updateMenuManage(MenuManageVO vo);

	/**
	 * 메뉴 정보를 삭제
	 * 
	 * @param vo MenuManageVO
	 */
	void deleteMenuManage(MenuManageVO vo);

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * 
	 * @param checkedMenuNoForDel String
	 */
	void deleteMenuManageList(String checkedMenuNoForDel);

	/* 메뉴 생성 관리 */

	/**
	 * 메뉴 목록을 조회
	 * 
	 * @return List
	 */
	List<EgovMap> selectMenuList();

	/* ### 메뉴관련 프로세스 ### */
	/**
	 * MainMenu Head Menu 조회
	 * 
	 * @param vo MenuManageVO
	 * @return List
	 */
	List<?> selectMainMenuHead(MenuManageVO vo);

	/**
	 * MainMenu Head Left 조회
	 * 
	 * @param vo MenuManageVO
	 * @return List
	 */
	List<?> selectMainMenuLeft(MenuManageVO vo);

	/**
	 * MainMenu Head MenuURL 조회
	 * 
	 * @param iMenuNo int
	 * @param sUniqId String
	 * @return String
	 */
	String selectLastMenuURL(int iMenuNo, String sUniqId);

	/* 일괄처리 프로세스 */

	/**
	 * 메뉴일괄초기화 프로세스 메뉴목록테이블, 프로그램 목록테이블 전체 삭제
	 * 
	 * @return boolean
	 */
	boolean menuBndeAllDelete();

	/**
	 * 메뉴일괄등록 프로세스
	 * 
	 * @param vo          MenuManageVO
	 * @param inputStream InputStream
	 */
	String menuBndeRegist(MenuManageVO vo, InputStream inputStream);

}