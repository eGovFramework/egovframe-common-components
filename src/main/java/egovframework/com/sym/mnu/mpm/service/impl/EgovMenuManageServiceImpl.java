package egovframework.com.sym.mnu.mpm.service.impl;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import egovframework.com.sym.prm.service.impl.ProgrmManageDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.BaseException;
import egovframework.rte.fdl.excel.EgovExcelService;

/**
 * 메뉴목록관리, 생성, 사이트맵을 처리하는 비즈니스 구현 클래스를 정의한다.
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
 *   2011.07.01  서준식			자기 메뉴 정보를 상위메뉴 정보로 참조하는 메뉴정보가 있는지 조회하는
 *   										selectUpperMenuNoByPk() 메서드 추가
 *   2017-02-13  이정은          시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2019-12-06  신용호          KISA 보안약점 조치 (부적절한 예외처리)
 *
 * </pre>
 */

@Service("meunManageService")
public class EgovMenuManageServiceImpl extends EgovAbstractServiceImpl implements EgovMenuManageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovMenuManageServiceImpl.class);

	@Resource(name = "menuManageDAO")
	private MenuManageDAO menuManageDAO;
	@Resource(name = "progrmManageDAO")
	private ProgrmManageDAO progrmManageDAO;
	@Resource(name = "excelZipService")
	private EgovExcelService excelZipService;

	@Resource(name = "multipartResolver")
	CommonsMultipartResolver mailmultipartResolver;

	/**
	 * 메뉴 상세정보를 조회
	 * @param vo ComDefaultVO
	 * @return MenuManageVO
	 * @exception Exception
	 */
	public MenuManageVO selectMenuManage(ComDefaultVO vo) throws Exception {
		return menuManageDAO.selectMenuManage(vo);
	}

	/**
	 * 메뉴 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMenuManageList(ComDefaultVO vo) throws Exception {
		return menuManageDAO.selectMenuManageList(vo);
	}

	/**
	 * 메뉴목록 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMenuManageListTotCnt(ComDefaultVO vo) throws Exception {
		return menuManageDAO.selectMenuManageListTotCnt(vo);
	}

	/**
	 * 메뉴번호를 상위메뉴로 참조하고 있는 메뉴 존재여부를 조회
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	public int selectUpperMenuNoByPk(MenuManageVO vo) throws Exception {
		return menuManageDAO.selectUpperMenuNoByPk(vo);
	}

	/**
	 * 메뉴번호 존재 여부를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMenuNoByPk(MenuManageVO vo) throws Exception {
		return menuManageDAO.selectMenuNoByPk(vo);
	}

	/**
	 * 메뉴 정보를 등록
	 * @param vo MenuManageVO
	 * @exception Exception
	 */
	public void insertMenuManage(MenuManageVO vo) throws Exception {
		menuManageDAO.insertMenuManage(vo);
	}

	/**
	 * 메뉴 정보를 수정
	 * @param vo MenuManageVO
	 * @exception Exception
	 */
	public void updateMenuManage(MenuManageVO vo) throws Exception {
		menuManageDAO.updateMenuManage(vo);
	}

	/**
	 * 메뉴 정보를 삭제
	 * @param vo MenuManageVO
	 * @exception Exception
	 */
	public void deleteMenuManage(MenuManageVO vo) throws Exception {
		menuManageDAO.deleteMenuManage(vo);
	}

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * @param checkedMenuNoForDel String
	 * @exception Exception
	 */
	public void deleteMenuManageList(String checkedMenuNoForDel) throws Exception {
		MenuManageVO vo = null;

		String[] delMenuNo = checkedMenuNoForDel.split(",");

		if (delMenuNo == null || (delMenuNo.length == 0)) {
			throw new java.lang.Exception("String Split Error!");
		}
		for (int i = 0; i < delMenuNo.length; i++) {
			vo = new MenuManageVO();
			vo.setMenuNo(Integer.parseInt(delMenuNo[i]));
			menuManageDAO.deleteMenuManage(vo);
		}
	}

	/*  메뉴 생성 관리  */

	/**
	 * 메뉴 목록을 조회
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMenuList() throws Exception {
		return menuManageDAO.selectMenuList();
	}

	/*### 메뉴관련 프로세스 ###*/
	/**
	 * MainMenu Head Menu 조회
	 * @param vo MenuManageVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMainMenuHead(MenuManageVO vo) throws Exception {
		return menuManageDAO.selectMainMenuHead(vo);
	}

	/**
	 * MainMenu Head Left 조회
	 * @param vo MenuManageVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMainMenuLeft(MenuManageVO vo) throws Exception {
		return menuManageDAO.selectMainMenuLeft(vo);
	}

	/**
	 * MainMenu Head MenuURL 조회
	 * @param  iMenuNo  int
	 * @param  sUniqId  String
	 * @return String
	 * @exception Exception
	 */
	public String selectLastMenuURL(int iMenuNo, String sUniqId) throws Exception {
		MenuManageVO vo = new MenuManageVO();
		vo.setMenuNo(selectLastMenuNo(iMenuNo, sUniqId));
		return menuManageDAO.selectLastMenuURL(vo);
	}

	/**
	 * MainMenu Head Menu MenuNo 조회
	 * @param  iMenuNo  int
	 * @param  sUniqId  String
	 * @return String
	 * @exception Exception
	 */
	private int selectLastMenuNo(int iMenuNo, String sUniqId) throws Exception {
		int chkMenuNo = iMenuNo;
		int cntMenuNo = 0;
		for (; chkMenuNo > -1;) {
			chkMenuNo = selectLastMenuNoChk(chkMenuNo, sUniqId);
			if (chkMenuNo > 0) {
				cntMenuNo = chkMenuNo;
			}
		}
		return cntMenuNo;
	}

	/**
	 * MainMenu Head Menu Last MenuNo 조회
	 * @param  iMenuNo  int
	 * @param  sUniqId  String
	 * @return String
	 * @exception Exception
	 */
	private int selectLastMenuNoChk(int iMenuNo, String sUniqId) throws Exception {
		MenuManageVO vo = new MenuManageVO();
		vo.setMenuNo(iMenuNo);
		vo.setTempValue(sUniqId);
		int chkMenuNo = 0;
		int cntMenuNo = 0;
		cntMenuNo = menuManageDAO.selectLastMenuNoCnt(vo);
		if (cntMenuNo > 0) {
			chkMenuNo = menuManageDAO.selectLastMenuNo(vo);
		} else {
			chkMenuNo = -1;
		}
		return chkMenuNo;
	}

	/*### 일괄처리 프로세스 ###*/
	/**
	 * 메뉴일괄초기화 프로세스 메뉴목록테이블, 프로그램 목록테이블 전체 삭제
	 * @return boolean
	 * @exception Exception
	 */
	public boolean menuBndeAllDelete() throws Exception {
		if (!deleteAllProgrmDtls()) {
			return false;
		} // 프로그램변경요청 테이블
		if (!deleteAllMenuList()) {
			return false;
		} // 메뉴정보 테이블
		if (!deleteAllProgrm()) {
			return false;
		} // 프로그램목록 테이블
		return true;
	}

	/**
	 * 메뉴일괄등록 프로세스
	 * @param  vo MenuManageVO
	 * @param  inputStream InputStream
	 * @exception Exception
	 */
	public String menuBndeRegist(MenuManageVO vo, InputStream inputStream) throws Exception {

		String message = bndeRegist(inputStream);
		String sMessage = null;

		switch (Integer.parseInt(message)) {
			case 99:
				LOGGER.debug("프로그램목록/메뉴정보테이블 데이타 존재오류 - 초기화 하신 후 다시 처리하세요.");
				sMessage = "프로그램목록/메뉴정보테이블 데이타 존재오류 - 초기화 하신 후 다시 처리하세요.";
				break;
			case 90:
				LOGGER.debug("파일존재하지 않음.");
				sMessage = "파일존재하지 않음.";
				break;
			case 91:
				LOGGER.debug("프로그램시트의 cell 갯수 오류.");
				sMessage = "프로그램시트의 cell 갯수 오류.";
				break;
			case 92:
				LOGGER.debug("메뉴정보시트의 cell 갯수 오류.");
				sMessage = "메뉴정보시트의 cell 갯수 오류.";
				break;
			case 93:
				LOGGER.debug("엑셀 시트갯수 오류.");
				sMessage = "엑셀 시트갯수 오류.";
				break;
			case 95:
				LOGGER.debug("메뉴정보 입력시 에러.");
				sMessage = "메뉴정보 입력시 에러.";
				break;
			case 96:
				LOGGER.debug("프로그램목록입력시 에러.");
				sMessage = "프로그램목록입력시 에러.";
				break;
			default:
				LOGGER.debug("일괄배치처리 완료.");
				sMessage = "일괄배치처리 완료.";
				break;
		}
		LOGGER.debug(message);
		return sMessage;
	}

	/**
	 * 메뉴목록_프로그램목록 일괄생성
	 * @param  inputStream InputStream
	 * @return  String
	 * @exception Exception
	 */
	private String bndeRegist(InputStream inputStream) throws Exception {
		boolean success = false;
		String requestValue = null;
		int progrmSheetRowCnt = 0;
		int menuSheetRowCnt = 0;
		//String xlsFile = null;
		try {
			/*
			오류 메세지 정보
			message = "99";	//프로그램목록테이블 데이타 존재오류.
			message = "99";	//메뉴정보테이블 데이타 존재오류.
			message = "90";	//파일존재하지 않음.
			message = "91";	//프로그램시트의 cell 갯수 오류
			message = "92";	//메뉴정보시트의 cell 갯수 오류
			message = "93";	//엑셀 시트갯수 오류
			message = "95";	//메뉴정보 입력시 에러
			message = "96";	//프로그램목록입력시 에러
			message = "0";	//일괄배치처리 완료
			*/

			if (progrmManageDAO.selectProgrmListTotCnt() > 0) {
				return requestValue = "99";
			} //프로그램목록테이블 데이타 존재오류.
			if (menuManageDAO.selectMenuListTotCnt() > 0) {
				return requestValue = "99";
			} //메뉴정보테이블 데이타 존재오류.

			HSSFWorkbook hssfWB = (HSSFWorkbook) excelZipService.loadWorkbook(inputStream);
			// 엑셀 파일 시트 갯수 확인 sheet = 2  첫번째시트 = 프로그램목록  두번째시트 = 메뉴목록
			if (hssfWB.getNumberOfSheets() == 2) {
				HSSFSheet progrmSheet = hssfWB.getSheetAt(0); //프로그램목록 시트 가져오기
				HSSFSheet menuSheet = hssfWB.getSheetAt(1); //메뉴정보 시트 가져오기
				HSSFRow progrmRow = progrmSheet.getRow(1); //프로그램 row 가져오기
				HSSFRow menuRow = menuSheet.getRow(1); //메뉴정보 row 가져오기
				progrmSheetRowCnt = progrmRow.getPhysicalNumberOfCells(); //프로그램 cell Cnt
				menuSheetRowCnt = menuRow.getPhysicalNumberOfCells(); //메뉴정보 cell Cnt

				// 프로그램 시트 파일 데이타 검증 cell = 5개
				if (progrmSheetRowCnt != 5) {
					return requestValue = "91"; //프로그램시트의 cell 갯수 오류
				}

				// 메뉴목록 시트 파일 데이타 검증  cell = 8개
				if (menuSheetRowCnt != 8) {
					return requestValue = "92"; //메뉴정보시트의 cell 갯수 오류
				}

				/* sheet1번 = 프로그램목록 ,  sheet2번 = 메뉴정보 */
				success = progrmRegist(progrmSheet);
				if (success) {
					success = menuRegist(menuSheet);
					if (success) {
						return requestValue = "0"; // 일괄배치처리 완료
					} else {
						deleteAllProgrmDtls();
						deleteAllProgrm();
						deleteAllMenuList();
						return requestValue = "95"; // 메뉴정보 입력시 에러
					}
				} else {
					deleteAllProgrmDtls();
					deleteAllProgrm();
					return requestValue = "96"; // 프로그램목록입력시 에러
				}
			} else {
				return requestValue = "93"; // 엑셀 시트갯수 오류
			}
		} catch (BaseException e) {
			LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
			requestValue = "99";
			
		} catch (Exception e) {
			//2017.02.13 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
			requestValue = "99";
		}
		return requestValue;
	}

	/**
	 * 프로그램목록 일괄등록
	 * @param  progrmSheet HSSFSheet
	 * @return  boolean
	 * @exception Exception
	 */
	private boolean progrmRegist(HSSFSheet progrmSheet) {
		int count = 0;
		boolean success = false;
		try {
			int rows = progrmSheet.getPhysicalNumberOfRows(); //행 갯수 가져오기
			for (int j = 1; j < rows; j++) { //row 루프
				ProgrmManageVO vo = new ProgrmManageVO();
				HSSFRow row = progrmSheet.getRow(j); //row 가져오기
				if (row != null) {
					//int cells = row.getPhysicalNumberOfCells(); //cell 갯수 가져오기

					HSSFCell cell = null;
					cell = row.getCell(0); //프로그램명
					if (cell != null) {
						vo.setProgrmFileNm("" + cell.getStringCellValue());
					}
					cell = row.getCell(1); //프로그램한글명
					if (cell != null) {
						vo.setProgrmKoreanNm("" + cell.getStringCellValue());
					}
					cell = row.getCell(2); //프로그램저장경로
					if (cell != null) {
						vo.setProgrmStrePath("" + cell.getStringCellValue());
					}
					cell = row.getCell(3); //프로그램 URL
					if (cell != null) {
						vo.setURL("" + cell.getStringCellValue());
					}
					cell = row.getCell(4); //프로그램설명
					if (cell != null) {
						vo.setProgrmDc("" + cell.getStringCellValue());
					}
				}
				if (insertProgrm(vo)) {
					count++;
				}
			}
			if (count == rows - 1) {
				success = true;
			} else {
				success = false;
			}
		} catch (SQLException e) {
			LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
			success = false;
			
		} catch (Exception e) {
			//2017.02.13 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
			success = false;
		}
		return success;
	}

	/**
	 * 메뉴정보 일괄등록
	 * @param menuSheet HSSFSheet
	 * @return boolean
	 * @exception Exception
	 */
	private boolean menuRegist(HSSFSheet menuSheet) throws Exception {
		boolean success = false;
		int count = 0;
		try {
			int rows = menuSheet.getPhysicalNumberOfRows(); //행 갯수 가져오기
			for (int j = 1; j < rows; j++) { //row 루프
				MenuManageVO vo = new MenuManageVO();
				HSSFRow row = menuSheet.getRow(j); //row 가져오기
				if (row != null) {
					//int cells = row.getPhysicalNumberOfCells(); //cell 갯수 가져오기
					HSSFCell cell = null;
					cell = row.getCell(0); //메뉴번호
					if (cell != null) {
						Double doubleCell = new Double(cell.getNumericCellValue());
						vo.setMenuNo(Integer.parseInt("" + doubleCell.longValue()));
					}
					cell = row.getCell(1); //메뉴순서
					if (cell != null) {
						Double doubleCell = new Double(cell.getNumericCellValue());
						vo.setMenuOrdr(Integer.parseInt("" + doubleCell.longValue()));
					}
					cell = row.getCell(2); //메뉴명
					if (cell != null) {
						vo.setMenuNm("" + cell.getStringCellValue());
					}
					cell = row.getCell(3); //상위메뉴번호
					if (cell != null) {
						Double doubleCell = new Double(cell.getNumericCellValue());
						vo.setUpperMenuId(Integer.parseInt("" + doubleCell.longValue()));
					}
					cell = row.getCell(4); //메뉴설명
					if (cell != null) {
						vo.setMenuDc("" + cell.getStringCellValue());
					}
					cell = row.getCell(5); //관련이미지경로
					if (cell != null) {
						vo.setRelateImagePath("" + cell.getStringCellValue());
					}
					cell = row.getCell(6); //관련이미지명
					if (cell != null) {
						vo.setRelateImageNm("" + cell.getStringCellValue());
					}
					cell = row.getCell(7); //프로그램파일명
					if (cell != null) {
						vo.setProgrmFileNm("" + cell.getStringCellValue());
					}
				}
				if (insertMenuManageBind(vo)) {
					count++;
				}
			}
			if (count == rows - 1) {
				success = true;
			} else {
				success = false;
			}
		} catch (SQLException e) {
			LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
			success = false;
			
		} catch (Exception e) {
			//2017.02.13 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
			success = false;
		}
		
		return success;
	}

	/**
	 * 메뉴정보 전체데이타 초기화
	 * @return boolean
	 * @exception Exception
	 */
	private boolean deleteAllMenuList() throws Exception {
		return menuManageDAO.deleteAllMenuList();
	}

	/**
	 * 프로그램 정보를 등록
	 * @param  vo ProgrmManageVO
	 * @return boolean
	 * @exception Exception
	 */
	private boolean insertProgrm(ProgrmManageVO vo) throws Exception {
		progrmManageDAO.insertProgrm(vo);
		return true;
	}

	/**
	 * 메뉴정보를 일괄 등록
	 * @param  vo MenuManageVO
	 * @return boolean
	 * @exception Exception
	 */
	private boolean insertMenuManageBind(MenuManageVO vo) throws Exception {
		menuManageDAO.insertMenuManage(vo);
		return true;
	}

	/**
	 * 프로그램 정보 전체데이타 초기화
	 * @return boolean
	 * @exception Exception
	 */
	private boolean deleteAllProgrm() throws Exception {
		progrmManageDAO.deleteAllProgrm();
		return true;
	}

	/**
	 * 프로그램변경내역 정보 전체데이타 초기화
	 * @return boolean
	 * @exception Exception
	 */
	private boolean deleteAllProgrmDtls() throws Exception {
		progrmManageDAO.deleteAllProgrmDtls();
		return true;
	}
}