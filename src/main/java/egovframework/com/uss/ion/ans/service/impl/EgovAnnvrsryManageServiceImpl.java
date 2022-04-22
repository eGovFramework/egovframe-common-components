package egovframework.com.uss.ion.ans.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import egovframework.com.uss.ion.ans.service.AnnvrsryManage;
import egovframework.com.uss.ion.ans.service.AnnvrsryManageVO;
import egovframework.com.uss.ion.ans.service.EgovAnnvrsryManageService;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.excel.EgovExcelService;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 * 개요
 * - 기념일관리에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 기념일관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 기념일관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 *  * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      	수정자          	수정내용
 *  ----------  --------    ---------------------------
 *  2010.06.15	표준프레임워크   	최초 생성
 *  2018.11.30	최두영		selectAnnvrsryManageBnde에서 annvrsryManageVO의 null처리 추가
 *
 *  </pre>
 */

@Service("egovAnnvrsryManageService")
public class EgovAnnvrsryManageServiceImpl extends EgovAbstractServiceImpl implements EgovAnnvrsryManageService {

	@Resource(name = "annvrsryManageDAO")
	private AnnvrsryManageDAO annvrsryManageDAO;

	@Resource(name = "excelZipService")
	private EgovExcelService excelZipService;

	/** ID Generation */
	@Resource(name = "egovAnnvrsryManageIdGnrService")
	private EgovIdGnrService idgenAnnvrsryManageService;

	/**
	 * 기념일관리정보를 관리하기 위해 등록된 기념일관리 목록을 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return List - 기념일관리 목록
	 */
	public List<AnnvrsryManageVO> selectAnnvrsryManageList(AnnvrsryManageVO annvrsryManageVO) throws Exception {

		List<AnnvrsryManageVO> result = annvrsryManageDAO.selectAnnvrsryManageList(annvrsryManageVO);
		int num = result.size();

		for (int i = 0; i < num; i++) {
			AnnvrsryManageVO annvrsryManageVO1 = result.get(i);
			annvrsryManageVO1.setAnnvrsryDe(EgovDateUtil.formatDate(annvrsryManageVO1.getAnnvrsryDe(), "-"));
			result.set(i, annvrsryManageVO1);
		}
		return result;
	}

	/**
	 * 기념일관리목록 총 갯수를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return int - 기념일관리 카운트 수
	 */
	public int selectAnnvrsryManageListTotCnt(AnnvrsryManageVO annvrsryManageVO) throws Exception {
		return annvrsryManageDAO.selectAnnvrsryManageListTotCnt(annvrsryManageVO);
	}

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return AnnvrsryManageVO - 기념일관리 VO
	 */
	public AnnvrsryManageVO selectAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO) throws Exception {
		annvrsryManageVO.setAnnvrsryDe(EgovStringUtil.removeMinusChar(annvrsryManageVO.getAnnvrsryDe()));
		AnnvrsryManageVO annvrsryManageVOTemp = annvrsryManageDAO.selectAnnvrsryManage(annvrsryManageVO);
		annvrsryManageVOTemp.setAnnvrsryDe(EgovDateUtil.formatDate(annvrsryManageVOTemp.getAnnvrsryDe(), "-"));
		return annvrsryManageVOTemp;
	}

	/**
	 * 기념일관리정보를 신규로 등록한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	public void insertAnnvrsryManage(AnnvrsryManage annvrsryManage) throws Exception {
		annvrsryManage.setAnnvrsryDe(EgovStringUtil.removeMinusChar(annvrsryManage.getAnnvrsryDe()));

		String sAnnId = idgenAnnvrsryManageService.getNextStringId();
		annvrsryManage.setAnnId(sAnnId);
		annvrsryManageDAO.insertAnnvrsryManage(annvrsryManage);
	}

	/**
	 * 기 등록된 기념일관리정보를 수정한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	public void updateAnnvrsryManage(AnnvrsryManage annvrsryManage) throws Exception {
		annvrsryManage.setAnnvrsryDe(EgovStringUtil.removeMinusChar(annvrsryManage.getAnnvrsryDe()));
		annvrsryManageDAO.updateAnnvrsryManage(annvrsryManage);
	}

	/**
	 * 기 등록된 기념일관리정보를 삭제한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	public void deleteAnnvrsryManage(AnnvrsryManage annvrsryManage) throws Exception {
		annvrsryManage.setAnnvrsryDe(EgovStringUtil.removeMinusChar(annvrsryManage.getAnnvrsryDe()));
		annvrsryManageDAO.deleteAnnvrsryManage(annvrsryManage);
	}

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return AnnvrsryManageVO - 기념일관리 VO
	 */
	public List<AnnvrsryManageVO> selectAnnvrsryGdcc(AnnvrsryManageVO annvrsryManageVO) throws Exception {

		List<AnnvrsryManageVO> result_temp = annvrsryManageDAO.selectAnnvrsryGdcc(annvrsryManageVO);
		List<AnnvrsryManageVO> result = new ArrayList<AnnvrsryManageVO>();
		long lTemp = 0;
		int num = result_temp.size();

		for (int i = 0; i < num; i++) {
			AnnvrsryManageVO annvrsryManageVO1 = result_temp.get(i);
			lTemp = getDateCount(annvrsryManageVO1);

			if (lTemp >= 0 && lTemp < Long.parseLong(annvrsryManageVO1.getAnnvrsryBeginDe().replaceAll("\\p{Space}", ""))) {
				annvrsryManageVO1.setAnnvrsryDe(EgovDateUtil.formatDate(annvrsryManageVO1.getAnnvrsryDe(), "-"));
				result_temp.set(i, annvrsryManageVO1);
				result.add(result_temp.get(i));
			}
		}
		return result;
	}

	/**
	 * 기념일관리목록 총 갯수를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return int - 기념일관리 카운트 수
	 */
	public int selectAnnvrsryManageDplctAt(AnnvrsryManage annvrsryManage) throws Exception {
		return annvrsryManageDAO.selectAnnvrsryManageDplctAt(annvrsryManage);
	}

	/**
	 * 해당일자와 현재일자의 일수 계산
	 * @param annvrsryManageVO
	 * @return long
	 */
	private long getDateCount(AnnvrsryManageVO annvrsryManageVO) throws Exception {

		/* 날짜 사이의 기간 산출 */
		long resultDay = 0;
		Calendar to_day = Calendar.getInstance(); //Calendar객체를 생성합니다.
		Calendar target_day = Calendar.getInstance();

		String sAnnvrsryDe = null;

		sAnnvrsryDe = EgovStringUtil.removeMinusChar(annvrsryManageVO.getAnnvrsryDe());

		// 매년반복일 경우
		if ("1".equals(annvrsryManageVO.getReptitSe())) {
			sAnnvrsryDe = Integer.toString(to_day.get(Calendar.YEAR)) + (sAnnvrsryDe == null || sAnnvrsryDe.length() < 8 ? to_day.get(Calendar.MONTH) : sAnnvrsryDe.substring(4, 6)) + (sAnnvrsryDe == null || sAnnvrsryDe.length() < 8 ? to_day.get(Calendar.DATE) : sAnnvrsryDe.substring(6, 8));
		}

		// 음력인 경우 양력으로 환산
		if ("2".equals(annvrsryManageVO.getCldrSe())) {
			sAnnvrsryDe = EgovDateUtil.toSolar(sAnnvrsryDe, 0);
		}

		if (sAnnvrsryDe != null && !sAnnvrsryDe.equals("")) {
			target_day.set(Integer.parseInt(sAnnvrsryDe.substring(0, 4)), Integer.parseInt(sAnnvrsryDe.substring(4, 6)) - 1, Integer.parseInt(sAnnvrsryDe.substring(6, 8)));
		} else {
			target_day.set(to_day.get(Calendar.YEAR), to_day.get(Calendar.MONTH) + 1, to_day.get(Calendar.DATE));
		}

		long resultTime = target_day.getTime().getTime() - to_day.getTime().getTime(); // 차이 구하기
		if (resultTime > 0) {
			resultDay = resultTime / (1000 * 60 * 60 * 24);// 일로 바꾸기
		} else {
			resultDay = -1;
		}
		//annvrsryManageVO.setAnnvrsryBeginDe(Long.toString(resultDay));
		
		return resultDay;
	}

	/*###  엑셀 일괄처리 프로세스 ###*/

	/**
	 * 기념일정보 excel생성
	 * @param  inputStream InputStream
	 * @return  String
	 * @exception Exception
	 */
	public List<AnnvrsryManageVO> selectAnnvrsryManageBnde(InputStream inputStream) throws Exception {
		//int annvrsrySheetRowCnt = 0;
		//String xlsFile = null;

		String sTempId = null; //사용자ID
		String sTempNm = null; //사용자명
		String sTempAnnvrsryDe = null; //기념일자
		String sTempCldrSe = null; //양/음 구분
		String sTempAnnvrsrySe = null; //기념일구분
		String sTempAnnvrsryNm = null; //기념일명
		String sTempReptitSe = null; // 반복여부

		List<AnnvrsryManageVO> list = new ArrayList<AnnvrsryManageVO>();

		//String sBndtDe = null;
		HSSFWorkbook hssfWB = (HSSFWorkbook) excelZipService.loadWorkbook(inputStream);
		// 엑셀 파일 시트 갯수 확인 sheet = 1
		if (hssfWB.getNumberOfSheets() == 1) {
			HSSFSheet annvrsrySheet = hssfWB.getSheetAt(0); //기념일관리 시트 가져오기
			//HSSFRow annvrsryRow = annvrsrySheet.getRow(1); //기념일 row 가져오기
			//annvrsrySheetRowCnt = annvrsryRow.getPhysicalNumberOfCells(); //기념일 cell Cnt
			int rowsCnt = annvrsrySheet.getPhysicalNumberOfRows(); //행 갯수 가져오기

			//사용자ID	기념일자	양/음 구분	기념일구분	기념일명
			for (int j = 1; j < rowsCnt; j++) { //row 루프
				AnnvrsryManageVO annvrsryManageVO = new AnnvrsryManageVO();
				AnnvrsryManageVO annvrsryManageVOTemp = null;
				HSSFRow row = annvrsrySheet.getRow(j); //row 가져오기
				if (row != null) {
					//int cells = row.getPhysicalNumberOfCells(); //cell 갯수 가져오기
					HSSFCell cell = null;
					cell = row.getCell(0); //사용자ID
					if (cell != null) {
						sTempId = (String) cell.getStringCellValue();
					}

					cell = row.getCell(1); //사용자명
					if (cell != null) {
						sTempNm = (String) cell.getStringCellValue();
					}

					cell = row.getCell(2); //기념일자
					if (cell != null) {
						sTempAnnvrsryDe = (String) cell.getStringCellValue();
					}

					cell = row.getCell(3); //양/음구분
					if (cell != null) {
						sTempCldrSe = (String) cell.getStringCellValue();
					}

					cell = row.getCell(4); //기념일구분
					if (cell != null) {
						sTempAnnvrsrySe = (String) cell.getStringCellValue();
					}
					cell = row.getCell(5); //기념일명
					if (cell != null) {
						sTempAnnvrsryNm = (String) cell.getStringCellValue();
					}
					cell = row.getCell(6); //반복여부
					if (cell != null) {
						sTempReptitSe = (String) cell.getStringCellValue();
					}
					annvrsryManageVO.setUsid(sTempId); // 당직자ID
					annvrsryManageVO.setAnnvrsryTemp1(sTempNm); // 당직자명
					annvrsryManageVO.setAnnvrsrySe(sTempAnnvrsrySe);
					annvrsryManageVOTemp = annvrsryManageDAO.selectAnnvrsryManageBnde(annvrsryManageVO);
					if (annvrsryManageVOTemp != null) annvrsryManageVO = annvrsryManageVOTemp; // 기존에 등록되어 있는경우
					annvrsryManageVO.setAnnvrsrySe(sTempAnnvrsrySe);
					annvrsryManageVO.setAnnvrsryDe(EgovDateUtil.formatDate(sTempAnnvrsryDe, "-"));
					annvrsryManageVO.setCldrSe(sTempCldrSe);
					annvrsryManageVO.setAnnvrsryNm(sTempAnnvrsryNm);
					annvrsryManageVO.setReptitSe(sTempReptitSe);
					list.add(annvrsryManageVO);
				}
			}
		}

		return list;
	}

	/**
	 * 기념일정보를 일괄등록처리한다.
	 * @param annvrsryManageVO     - 기념일관리 VO
	 * @param String           - 기념일정보
	*/
	public void insertAnnvrsryManageBnde(AnnvrsryManageVO annvrsryManageVO, String checkedAnnvrsryManageForInsert) throws Exception {
		AnnvrsryManage annvrsryManage;
		//int insertCnt = 0;
		String[] annvrsryManageValues = checkedAnnvrsryManageForInsert.split("[$]");
		String[] sTempAnnvrsryManage;
		String sTemp = null;
		String sAnnId = null;

		if (checkedAnnvrsryManageForInsert != null && !checkedAnnvrsryManageForInsert.equals("")) {
			for (int i = 0; i < annvrsryManageValues.length; i++) {
				annvrsryManage = new AnnvrsryManage();
				sTemp = annvrsryManageValues[i];
				sTempAnnvrsryManage = sTemp.split(",");
				annvrsryManage.setUsid(sTempAnnvrsryManage[0]);

				annvrsryManage.setAnnvrsryDe(EgovStringUtil.removeMinusChar(sTempAnnvrsryManage[1]));
				annvrsryManage.setCldrSe(sTempAnnvrsryManage[2]);
				annvrsryManage.setAnnvrsrySe(sTempAnnvrsryManage[3]);
				annvrsryManage.setAnnvrsryNm(sTempAnnvrsryManage[4]);
				if ("Y".equals(sTempAnnvrsryManage[5])) {
					annvrsryManage.setReptitSe("1");
				}
				annvrsryManage.setAnnvrsryBeginDe("7");
				annvrsryManage.setAnnvrsrySetup("Y");
				annvrsryManage.setMemo("기념일 일괄등록");
				sAnnId = idgenAnnvrsryManageService.getNextStringId();
				annvrsryManage.setAnnId(sAnnId);

				annvrsryManage.setFrstRegisterId(annvrsryManageVO.getFrstRegisterId());
				annvrsryManageDAO.insertAnnvrsryManage(annvrsryManage);
			}
		}
	}

	/**
	 * 기념일관리 건수를 조회한다.
	 * @param annvrsryManage - 기념일관리
	 * @return int
	 * @exception Exception

	public int selectAnnvrsryManageMonthCnt(AnnvrsryManageVO annvrsryManageVO) throws Exception {
		return annvrsryManageDAO.selectAnnvrsryManageMonthCnt(annvrsryManageVO);
	}
	 */

}
