package egovframework.com.uss.ion.bnt.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.excel.EgovExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.bnt.service.BndtCeckManageVO;
import egovframework.com.uss.ion.bnt.service.BndtDiaryVO;
import egovframework.com.uss.ion.bnt.service.BndtManageVO;
import egovframework.com.uss.ion.bnt.service.EgovBndtManageService;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;

/**
 * <pre>
 * 개요
 * - 당직관리에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 당직관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 당직관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 *
 * @author 이용
 * @since 2010.06.15
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.15  표준프레임워크     최초 생성
 *   2018.08.29  신용호          xlsx 처리 할수 있도록 selectBndtManageBndeX추가
 *   2020.11.02  신용호          KISA 보안약점 조치 - 널(null) 값 체크
 *   2022.11.11  김혜준          시큐어코딩 처리
 *   2025.08.04  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-CloseResource(부적절한 자원 해제)
 *   2025.08.04  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Service("egovBndtManageService")
public class EgovBndtManageServiceImpl extends EgovAbstractServiceImpl implements EgovBndtManageService {

	@Resource(name = "excelZipService")
	private EgovExcelService excelZipService;

	@Resource(name = "bndtManageDAO")
	private BndtManageDAO bndtManageDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBndtManageServiceImpl.class);
	/**
	 * 당직관리정보를 관리하기 위해 등록된 당직관리 목록을 조회한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 * @return List - 당직관리 목록
	 */
	@Override
	public List<BndtManageVO> selectBndtManageList(BndtManageVO bndtManageVO) throws Exception {
		return bndtManageDAO.selectBndtManageList(bndtManageVO);
	}

	/**
	 * 당직관리목록 총 개수를 조회한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 * @return int - 당직관리 카운트 수
	 */
	@Override
	public int selectBndtManageListTotCnt(BndtManageVO bndtManageVO) throws Exception {
		return bndtManageDAO.selectBndtManageListTotCnt(bndtManageVO);
	}

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 * @return BndtManageVO - 당직관리 VO
	 */
	@Override
	public BndtManageVO selectBndtManage(BndtManageVO bndtManageVO) throws Exception {
		bndtManageVO.setBndtDe(EgovStringUtil.removeMinusChar(bndtManageVO.getBndtDe()));
		BndtManageVO bndtManageVOTemp = new BndtManageVO();
		bndtManageVOTemp = bndtManageDAO.selectBndtManage(bndtManageVO);
		bndtManageVOTemp.setBndtDe(EgovDateUtil.formatDate(bndtManageVOTemp.getBndtDe(), "-"));

		return bndtManageVOTemp;
	}

	/**
	 * 당직관리정보를 신규로 등록한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 */
	@Override
	public void insertBndtManage(BndtManageVO bndtManageVO) throws Exception {
		bndtManageVO.setBndtDe(EgovStringUtil.removeMinusChar(bndtManageVO.getBndtDe()));
		bndtManageDAO.insertBndtManage(bndtManageVO);
	}

	/**
	 * 기 등록된 당직관리정보를 수정한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 */
	@Override
	public void updtBndtManage(BndtManageVO bndtManageVO) throws Exception {
		bndtManageVO.setBndtDe(EgovStringUtil.removeMinusChar(bndtManageVO.getBndtDe()));
		bndtManageDAO.updtBndtManage(bndtManageVO);
	}

	/**
	 * 기 등록된 당직관리정보를 삭제한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 */
	@Override
	public void deleteBndtManage(BndtManageVO bndtManageVO) throws Exception {
		bndtManageVO.setBndtDe(EgovStringUtil.removeMinusChar(bndtManageVO.getBndtDe()));
		bndtManageDAO.deleteBndtManage(bndtManageVO);
	}

	/**
	 * 당직일지 개수를 조회한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectBndtDiaryTotCnt(BndtManageVO bndtManageVO) throws Exception {
		bndtManageVO.setBndtDe(EgovStringUtil.removeMinusChar(bndtManageVO.getBndtDe()));
		return bndtManageDAO.selectBndtDiaryTotCnt(bndtManageVO);
	}

	/***** 당직 체크관리 *****/

	/**
	 * 당직체크관리정보를 관리하기 위해 등록된 당직체크관리 목록을 조회한다.
	 *
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return List - 당직체크관리 목록
	 */
	@Override
	public List<BndtCeckManageVO> selectBndtCeckManageList(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		return bndtManageDAO.selectBndtCeckManageList(bndtCeckManageVO);
	}

	/**
	 * 당직체크관리목록 총 개수를 조회한다.
	 *
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int - 당직체크관리 카운트 수
	 */
	@Override
	public int selectBndtCeckManageListTotCnt(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		return bndtManageDAO.selectBndtCeckManageListTotCnt(bndtCeckManageVO);
	}

	/**
	 * 등록된 당직체크관리의 상세정보를 조회한다.
	 *
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return BndtCeckManageVO - 당직체크관리 VO
	 */
	@Override
	public BndtCeckManageVO selectBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		return bndtManageDAO.selectBndtCeckManage(bndtCeckManageVO);
	}

	/**
	 * 당직체크관리정보를 신규로 등록한다.
	 *
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 */
	@Override
	public void insertBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		bndtManageDAO.insertBndtCeckManage(bndtCeckManageVO);
	}

	/**
	 * 기 등록된 당직체크관리정보를 수정한다.
	 *
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 */
	@Override
	public void updtBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		bndtManageDAO.updtBndtCeckManage(bndtCeckManageVO);
	}

	/**
	 * 기 등록된 당직체크관리정보를 삭제한다.
	 *
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 */
	@Override
	public void deleteBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		bndtManageDAO.deleteBndtCeckManage(bndtCeckManageVO);
	}

	/**
	 * 당직체크 중복여부 조회한다.
	 *
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectBndtCeckManageDplctAt(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		return bndtManageDAO.selectBndtCeckManageDplctAt(bndtCeckManageVO);
	}

	/***** 당직 일지 *****/

	/**
	 * 등록된 당직일지관리의 상세정보를 조회한다.
	 *
	 * @param bndtDiaryVO - 당직일지관리 VO
	 * @return BndtDiaryVO - 당직일지관리 VO
	 */
	@Override
	public List<BndtDiaryVO> selectBndtDiary(BndtDiaryVO bndtDiaryVO) throws Exception {
		bndtDiaryVO.setBndtDe(EgovStringUtil.removeMinusChar(bndtDiaryVO.getBndtDe()));
		return bndtManageDAO.selectBndtDiary(bndtDiaryVO);
	}

	/**
	 * 당직일지관리정보를 신규로 등록한다.
	 *
	 * @param bndtDiaryVO    - 당직일지관리 VO
	 * @param diaryForInsert - String
	 */
	@Override
	public void insertBndtDiary(BndtDiaryVO bndtDiaryVO, String diaryForInsert) throws Exception {

		BndtDiaryVO bndtDiaryTemp;
		String[] bndtDiaryValues = diaryForInsert.split("[@]");
		String[] sTempBndtDiary;
		for (String sTemp : bndtDiaryValues) {
			bndtDiaryTemp = new BndtDiaryVO();
			sTempBndtDiary = sTemp.split("[$]");
			bndtDiaryTemp.setBndtDe(bndtDiaryVO.getBndtDe());
			bndtDiaryTemp.setBndtId(bndtDiaryVO.getBndtId());
			bndtDiaryTemp.setBndtCeckSe(sTempBndtDiary[0]);
			bndtDiaryTemp.setBndtCeckCd(sTempBndtDiary[1]);
			bndtDiaryTemp.setChckSttus(sTempBndtDiary[2]);
			bndtDiaryTemp.setFrstRegisterId(bndtDiaryVO.getFrstRegisterId());

			bndtManageDAO.insertBndtDiary(bndtDiaryTemp);
		}
	}

	/**
	 * 기 등록된 당직일지관리정보를 수정한다.
	 *
	 * @param bndtDiaryVO    - 당직일지관리 VO
	 * @param diaryForUpdt - String
	 */
	@Override
	public void updtBndtDiary(BndtDiaryVO bndtDiaryVO, String diaryForUpdt) throws Exception {

		BndtDiaryVO bndtDiaryTemp;
		String[] bndtDiaryValues = diaryForUpdt.split("[@]");
		String[] sTempBndtDiary;
		for (String sTemp : bndtDiaryValues) {
			bndtDiaryTemp = new BndtDiaryVO();
			sTempBndtDiary = sTemp.split("[$]");
			bndtDiaryTemp.setBndtDe(bndtDiaryVO.getBndtDe());
			bndtDiaryTemp.setBndtId(bndtDiaryVO.getBndtId());
			bndtDiaryTemp.setBndtCeckSe(sTempBndtDiary[0]);
			bndtDiaryTemp.setBndtCeckCd(sTempBndtDiary[1]);
			bndtDiaryTemp.setChckSttus(sTempBndtDiary[2]);
			bndtDiaryTemp.setLastUpdusrId(bndtDiaryVO.getLastUpdusrId());

			bndtManageDAO.updtBndtDiary(bndtDiaryTemp);
		}
	}

	/**
	 * 기 등록된 당직일지관리정보를 삭제한다.
	 *
	 * @param bndtDiaryVO - 당직일지관리 VO
	 */
	@Override
	public void deleteBndtDiary(BndtDiaryVO bndtDiaryVO) throws Exception {
		bndtManageDAO.deleteBndtDiary(bndtDiaryVO);
	}

	/* ### 엑셀 일괄처리 프로세스 ### */

	/**
	 * 엑셀 셀 값을 문자열로 가져오기 (HSSFCell용)
	 *
	 * @param cell HSSFCell
	 * @return String
	 */
	private String getCellValueAsString(HSSFCell cell) {
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
				// 숫자 셀의 경우 정수로 표시되면 소수점 제거
				double numericValue = cell.getNumericCellValue();
				if (numericValue == Math.floor(numericValue)) {
					return String.valueOf((long) numericValue);
				} else {
					return String.valueOf(numericValue);
				}
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case FORMULA:
				// 수식 셀의 경우 계산된 값을 가져옴
				try {
					return cell.getStringCellValue();
					// 2026.03.09 KISA 취약점 조치
				} catch (IllegalStateException e) {
					LOGGER.debug("FORMULA 셀 문자열 값 읽기 실패, 숫자 값으로 재시도: {}", e.getMessage());
					try {
						double numValue = cell.getNumericCellValue();
						if (numValue == Math.floor(numValue)) {
							return String.valueOf((long) numValue);
						} else {
							return String.valueOf(numValue);
						}
						// 2026.03.09 KISA 취약점 조치
					} catch (IllegalStateException ex) {
						LOGGER.debug("FORMULA 셀 숫자 값 읽기 실패: {}", ex.getMessage());
						return "";
					}
				}
			case BLANK:
				return "";
			default:
				return "";
		}
	}

	/**
	 * 엑셀 셀 값을 문자열로 가져오기 (Cell용 - xlsx)
	 * 당직일자 등 getDateWeekInt/getDateWeekString에서 YYYYMMDD 형식을 기대하므로,
	 * 날짜 셀은 yyyyMMdd로 변환한다.
	 *
	 * @param cell Cell
	 * @return String
	 */
	private String getCellValueAsString(Cell cell) {
		if (cell == null) {
			return "";
		}
		CellType cellType;
		try {
			cellType = cell.getCellType();
			if (cellType == CellType.FORMULA) {
				cellType = cell.getCachedFormulaResultType();
			}
			// 2026.03.09 KISA 취약점 조치
		} catch (IllegalStateException e) {
			LOGGER.debug("셀 타입 조회 실패: {}", e.getMessage());
			return "";
		}
		switch (cellType) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
				// xlsx에서 날짜 셀은 NUMERIC으로 저장됨. YYYYMMDD 형식으로 변환해야 getDateWeekInt 등에서 파싱 가능
				if (DateUtil.isCellDateFormatted(cell)) {
					try {
						Date date = cell.getDateCellValue();
						return date == null ? "" : new SimpleDateFormat("yyyyMMdd", Locale.ROOT).format(date);
						// 2026.03.09 KISA 취약점 조치
					} catch (IllegalStateException e) {
						double n = cell.getNumericCellValue();
						return (n == Math.floor(n)) ? String.valueOf((long) n) : String.valueOf(n);
					}
				}
				double numericValue = cell.getNumericCellValue();
				if (numericValue == Math.floor(numericValue)) {
					return String.valueOf((long) numericValue);
				}
				return String.valueOf(numericValue);
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case BLANK:
				return "";
			default:
				return "";
		}
	}

	/**
	 * 당직자 excel생성
	 *
	 * @param inputStream InputStream
	 * @return String
	 * @exception Exception
	 */
	@Override
	public List<BndtManageVO> selectBndtManageBnde(InputStream inputStream) throws Exception {
		String sTempNm = null;
		String sTempId = null;

	    List<BndtManageVO> list = new ArrayList<>();

		String sBndtDe = null;
		HSSFWorkbook workbook = (HSSFWorkbook) excelZipService.loadWorkbook(inputStream);

		if (workbook.getNumberOfSheets() == 1) {
			HSSFSheet sheet = workbook.getSheetAt(0); // 당직자 시트 가져오기
			int rowsCnt = sheet.getPhysicalNumberOfRows(); // 행 개수 가져오기

			BndtManageVO checkBndtManageVO = new BndtManageVO();
			for (int j = 1; j < rowsCnt; j++) { // row 루프
				BndtManageVO bndtManageVO = new BndtManageVO();
				HSSFRow row = sheet.getRow(j); // row 가져오기
				if (row != null) {
					HSSFCell cell = null;
					cell = row.getCell(0); // 당직일자
					sBndtDe = getCellValueAsString(cell);
					cell = row.getCell(1); // 당직자ID
					sTempId = getCellValueAsString(cell);
					cell = row.getCell(2); // 당직자명
					sTempNm = getCellValueAsString(cell);
					checkBndtManageVO.setTempBndtNm(sTempId); // 당직자ID
					checkBndtManageVO.setTempBndtId(sTempNm); // 당직자명

					// 최두영 로직변경
					bndtManageVO = bndtManageDAO.selectBndtManageBnde(checkBndtManageVO);
					if (bndtManageVO == null) {
						bndtManageVO = new BndtManageVO();
						BeanUtils.copyProperties(checkBndtManageVO, bndtManageVO);
					}

					bndtManageVO.setBndtDe(sBndtDe);
					bndtManageVO.setBndtId(sTempId);
					bndtManageVO.setTempBndtNm(sTempNm);
					bndtManageVO.setDateWeek(getDateWeekInt(sBndtDe));
					bndtManageVO.setTempBndtWeek(getDateWeekString(sBndtDe));

					list.add(bndtManageVO);
				}
			}
		}

		return list;
	}

	/* ### 엑셀 일괄처리 프로세스 ### */

	/**
	 * 당직자 excel생성 (Xlsx 처리)
	 *
	 * @param inputStream InputStream
	 * @return String
	 * @exception Exception
	 */
	@Override
	public List<BndtManageVO> selectBndtManageBndeX(InputStream inputStream) throws Exception {
		String sTempNm = null;
		String sTempId = null;

	    List<BndtManageVO> list = new ArrayList<>();

		String sBndtDe = null;
		Workbook workbook = null;
		try {
			workbook = excelZipService.loadWorkbook(inputStream, new XSSFWorkbook());

			if (workbook.getNumberOfSheets() >= 1) {
				Sheet sheet = workbook.getSheetAt(0); // 당직자 시트 가져오기
				if (sheet != null) {
					// xlsx에서는 getPhysicalNumberOfRows()가 0을 반환할 수 있으므로 getLastRowNum() 사용 (0-based)
					int lastRowNum = sheet.getLastRowNum();

					BndtManageVO checkBndtManageVO = new BndtManageVO();
					for (int j = 1; j <= lastRowNum; j++) { // row 루프 (1=헤더 다음 첫 데이터행)
						BndtManageVO bndtManageVO = new BndtManageVO();
						Row row = sheet.getRow(j); // row 가져오기
						if (row != null) {
							Cell cell = null;
							cell = row.getCell(0); // 당직일자
							sBndtDe = getCellValueAsString(cell);
							// 당직일자가 비어 있으면 해당 행 스킵 (getDateWeekInt 등 파싱 예외 방지)
							if (sBndtDe == null || sBndtDe.trim().isEmpty() || sBndtDe.trim().length() < 8) {
								continue;
							}
							cell = row.getCell(1); // 당직자ID
							sTempId = getCellValueAsString(cell);
							cell = row.getCell(2); // 당직자명
							sTempNm = getCellValueAsString(cell);
							checkBndtManageVO.setTempBndtNm(sTempId); // 당직자ID
							checkBndtManageVO.setTempBndtId(sTempNm); // 당직자명

							// 최두영 로직변경
							bndtManageVO = bndtManageDAO.selectBndtManageBnde(checkBndtManageVO);
							if (bndtManageVO == null) {
								bndtManageVO = new BndtManageVO();
								BeanUtils.copyProperties(checkBndtManageVO, bndtManageVO);
							}

							bndtManageVO.setBndtDe(sBndtDe);
							bndtManageVO.setBndtId(sTempId);
							bndtManageVO.setTempBndtNm(sTempNm);
							bndtManageVO.setDateWeek(getDateWeekInt(sBndtDe));
							bndtManageVO.setTempBndtWeek(getDateWeekString(sBndtDe));

							list.add(bndtManageVO);
						}
					}
				}
			}

			return list;
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					// 2026.02.28 KISA 취약점 조치: 로거 추가
					LOGGER.warn("리소스 정리 중 예외 발생",e);
				}
			}
		}
	}

	/**
	 * 당직정보를 일괄등록처리한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 * @param String       - 당직자정보
	 */
	@Override
	public void insertBndtManageBnde(BndtManageVO bndtManageVO, String checkedBndtManageForInsert) throws Exception {
		BndtManageVO bndtManageTemp;

		// 2022.11.11 시큐어코딩 처리
		if (StringUtils.isNotEmpty(checkedBndtManageForInsert)) {
			String[] bndtManageValues = checkedBndtManageForInsert.split("[$]");
			for (String sTemp : bndtManageValues) {
				bndtManageTemp = new BndtManageVO();
				String[] sTempBndtManage = sTemp.split(",");
				bndtManageTemp.setBndtDe(sTempBndtManage[0]);
				bndtManageTemp.setBndtId(sTempBndtManage[1]);
				bndtManageTemp.setRemark("당직일괄등록");
				bndtManageTemp.setFrstRegisterId(bndtManageVO.getFrstRegisterId());

				bndtManageDAO.insertBndtManage(bndtManageTemp);
			}
		}
	}

	/**
	 * 당직관리 건수를 조회한다.
	 *
	 * @param bndtManage - 당직관리
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectBndtManageMonthCnt(BndtManageVO bndtManageVO) throws Exception {
		return bndtManageDAO.selectBndtManageMonthCnt(bndtManageVO);
	}

	/**
	 * 해당일자와 현재일자의 일수 계산 (요일을 구함)
	 *
	 * @param annvrsryManageVO
	 * @return long (1~7로 요일을 리턴)
	 */
	@SuppressWarnings("static-access")
	private int getDateWeekInt(String sDate) throws Exception {
		Calendar targetDate = Calendar.getInstance();
		String sDayOfWeek = null;
		int iWeek = 0;
		sDayOfWeek = EgovStringUtil.removeMinusChar(sDate);
		// KISA 보안약점 조치 - 널(null) 값 체크
		if (sDayOfWeek == null) {
			return 0;
		}
		targetDate.set(Integer.parseInt(sDayOfWeek.substring(0, 4)), Integer.parseInt(sDayOfWeek.substring(4, 6)) - 1,
				Integer.parseInt(sDayOfWeek.substring(6, 8)));
		iWeek = targetDate.get(targetDate.DAY_OF_WEEK);
		return iWeek;
	}

	/**
	 * 해당일자와 현재일자의 일수 계산
	 *
	 * @param annvrsryManageVO
	 * @return long
	 */
	private String getDateWeekString(String sDate) throws Exception {

		String sDayOfWeek = null;
		String sDayOfWeekReturnValue = null;
		sDayOfWeek = EgovStringUtil.removeMinusChar(sDate);
		String[] dayOfWeek = { "일", "월", "화", "수", "목", "금", "토" };
		Calendar targetDate = new GregorianCalendar();

		if (sDayOfWeek != null && sDayOfWeek.length() >= 8) {
			targetDate.set(Integer.parseInt(sDayOfWeek.substring(0, 4)),
					Integer.parseInt(sDayOfWeek.substring(4, 6)) - 1, Integer.parseInt(sDayOfWeek.substring(6, 8)));
			sDayOfWeekReturnValue = EgovDateUtil.formatDate(sDayOfWeek, "-") + " "
					+ dayOfWeek[targetDate.get(Calendar.DAY_OF_WEEK) - 1];
		}

		return sDayOfWeekReturnValue;
	}

}
