package egovframework.com.uss.ion.bnt.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.excel.EgovExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.bnt.service.BndtCeckManage;
import egovframework.com.uss.ion.bnt.service.BndtCeckManageVO;
import egovframework.com.uss.ion.bnt.service.BndtDiary;
import egovframework.com.uss.ion.bnt.service.BndtDiaryVO;
import egovframework.com.uss.ion.bnt.service.BndtManage;
import egovframework.com.uss.ion.bnt.service.BndtManageVO;
import egovframework.com.uss.ion.bnt.service.EgovBndtManageService;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 개요
 * - 당직관리에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 당직관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 당직관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 *  * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일                수정자               수정내용
 *  ----------   ----------   ---------------------------
 *  2010.06.15   표준프레임워크     최초 생성
 *  2018.08.29   신용호             xlsx 처리 할수 있도록 selectBndtManageBndeX추가
 *  2020.11.02   신용호             KISA 보안약점 조치 - 널(null) 값 체크
 *  2022.11.11   김혜준			  	시큐어코딩 처리
 *
 *  </pre>
 */

@Service("egovBndtManageService")
public class EgovBndtManageServiceImpl extends EgovAbstractServiceImpl implements EgovBndtManageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBndtManageServiceImpl.class);
	
	@Resource(name = "excelZipService")
    private EgovExcelService excelZipService;

	@Resource(name="bndtManageDAO")
    private BndtManageDAO bndtManageDAO;

	/**
	 * 당직관리정보를 관리하기 위해 등록된 당직관리 목록을 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return List - 당직관리 목록
	 */
	@Override
	public List<BndtManageVO> selectBndtManageList(BndtManageVO bndtManageVO) throws Exception{
		return bndtManageDAO.selectBndtManageList(bndtManageVO);
	}

	/**
	 * 당직관리목록 총 개수를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int - 당직관리 카운트 수
	 */
	@Override
	public int selectBndtManageListTotCnt(BndtManageVO bndtManageVO) throws Exception {
		return bndtManageDAO.selectBndtManageListTotCnt(bndtManageVO);
	}

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
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
	 * @param bndtManage - 당직관리 model
	 */
	@Override
	public void insertBndtManage(BndtManage bndtManage) throws Exception {
		bndtManage.setBndtDe(EgovStringUtil.removeMinusChar(bndtManage.getBndtDe()));
		bndtManageDAO.insertBndtManage(bndtManage);
	}

	/**
	 * 기 등록된 당직관리정보를 수정한다.
	 * @param bndtManage - 당직관리 model
	 */
	@Override
	public void updtBndtManage(BndtManage bndtManage) throws Exception {
		bndtManage.setBndtDe(EgovStringUtil.removeMinusChar(bndtManage.getBndtDe()));
		bndtManageDAO.updtBndtManage(bndtManage);
	}

	/**
	 * 기 등록된 당직관리정보를 삭제한다.
	 * @param bndtManage - 당직관리 model
	 */
	@Override
	public void deleteBndtManage(BndtManage bndtManage) throws Exception {
		bndtManage.setBndtDe(EgovStringUtil.removeMinusChar(bndtManage.getBndtDe()));
		bndtManageDAO.deleteBndtManage(bndtManage);
	}

    /**
	 * 당직일지 개수를 조회한다.
	 * @param bndtManage - 당직관리
	 * @return int
	 * @exception Exception
	 */
    @Override
	public int selectBndtDiaryTotCnt(BndtManage bndtManage) throws Exception {
    	bndtManage.setBndtDe(EgovStringUtil.removeMinusChar(bndtManage.getBndtDe()));
		return bndtManageDAO.selectBndtDiaryTotCnt(bndtManage);
	}

    /***** 당직 체크관리 *****/

	/**
	 * 당직체크관리정보를 관리하기 위해 등록된 당직체크관리 목록을 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return List - 당직체크관리 목록
	 */
	@Override
	public List<BndtCeckManageVO> selectBndtCeckManageList(BndtCeckManageVO bndtCeckManageVO) throws Exception{
		return bndtManageDAO.selectBndtCeckManageList(bndtCeckManageVO);
	}

	/**
	 * 당직체크관리목록 총 개수를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int - 당직체크관리 카운트 수
	 */
	@Override
	public int selectBndtCeckManageListTotCnt(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		return bndtManageDAO.selectBndtCeckManageListTotCnt(bndtCeckManageVO);
	}

	/**
	 * 등록된 당직체크관리의 상세정보를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return BndtCeckManageVO - 당직체크관리 VO
	 */
	@Override
	public BndtCeckManageVO selectBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		return bndtManageDAO.selectBndtCeckManage(bndtCeckManageVO);
	}

	/**
	 * 당직체크관리정보를 신규로 등록한다.
	 * @param bndtCeckManage - 당직체크관리 model
	 */
	@Override
	public void insertBndtCeckManage(BndtCeckManage bndtCeckManage) throws Exception {
		bndtManageDAO.insertBndtCeckManage(bndtCeckManage);
	}

	/**
	 * 기 등록된 당직체크관리정보를 수정한다.
	 * @param bndtCeckManage - 당직체크관리 model
	 */
	@Override
	public void updtBndtCeckManage(BndtCeckManage bndtCeckManage) throws Exception {
		bndtManageDAO.updtBndtCeckManage(bndtCeckManage);
	}

	/**
	 * 기 등록된 당직체크관리정보를 삭제한다.
	 * @param bndtCeckManage - 당직체크관리 model
	 */
	@Override
	public void deleteBndtCeckManage(BndtCeckManage bndtCeckManage) throws Exception {
		bndtManageDAO.deleteBndtCeckManage(bndtCeckManage);
	}

    /**
	 * 당직체크 중복여부 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectBndtCeckManageDplctAt(BndtCeckManage bndtCeckManage) throws Exception {
		return bndtManageDAO.selectBndtCeckManageDplctAt(bndtCeckManage);
	}


    /***** 당직 일지 *****/

	/**
	 * 등록된 당직일지관리의 상세정보를 조회한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 * @return BndtDiaryVO - 당직일지관리 VO
	 */
	@Override
	public List<BndtDiaryVO> selectBndtDiary(BndtDiaryVO bndtDiaryVO) throws Exception {
		return bndtManageDAO.selectBndtDiary(bndtDiaryVO);
	}

	/**
	 * 당직일지관리정보를 신규로 등록한다.
	 * @param bndtDiary - 당직일지관리 model
	 * @param diaryForUpdt - String
	 */
	@Override
	public void insertBndtDiary(BndtDiary bndtDiary, String diaryForInsert) throws Exception {

		BndtDiary bndtDiaryTemp;
		String [] bndtDiaryValues = diaryForInsert.split("[@]");
		String [] sTempBndtDiary;
		String    sTemp=null;
		for (int i=0; i<bndtDiaryValues.length ; i++){
			bndtDiaryTemp = new BndtDiary();
			sTemp = bndtDiaryValues[i];
			sTempBndtDiary = sTemp.split("[$]");
			bndtDiaryTemp.setBndtDe(bndtDiary.getBndtDe());
			bndtDiaryTemp.setBndtId(bndtDiary.getBndtId());
			bndtDiaryTemp.setBndtCeckSe(sTempBndtDiary[0]);
			bndtDiaryTemp.setBndtCeckCd(sTempBndtDiary[1]);
			bndtDiaryTemp.setChckSttus(sTempBndtDiary[2]);
			bndtDiaryTemp.setFrstRegisterId(bndtDiary.getFrstRegisterId());

			bndtManageDAO.insertBndtDiary(bndtDiaryTemp);
		}
	}

	/**
	 * 기 등록된 당직일지관리정보를 수정한다.
	 * @param bndtDiary - 당직일지관리 model
	 * @param diaryForUpdt - String
	 */
	@Override
	public void updtBndtDiary(BndtDiary bndtDiary, String diaryForUpdt) throws Exception {

		BndtDiary bndtDiaryTemp;
		String [] bndtDiaryValues = diaryForUpdt.split("[@]");
		String [] sTempBndtDiary;
		String    sTemp=null;
		for (int i=0; i<bndtDiaryValues.length ; i++){
			bndtDiaryTemp = new BndtDiary();
			sTemp = bndtDiaryValues[i];
			sTempBndtDiary = sTemp.split("[$]");
			bndtDiaryTemp.setBndtDe(bndtDiary.getBndtDe());
			bndtDiaryTemp.setBndtId(bndtDiary.getBndtId());
			bndtDiaryTemp.setBndtCeckSe(sTempBndtDiary[0]);
			bndtDiaryTemp.setBndtCeckCd(sTempBndtDiary[1]);
			bndtDiaryTemp.setChckSttus(sTempBndtDiary[2]);
			bndtDiaryTemp.setLastUpdusrId(bndtDiary.getLastUpdusrId());

			bndtManageDAO.updtBndtDiary(bndtDiaryTemp);
		}
	}

	/**
	 * 기 등록된 당직일지관리정보를 삭제한다.
	 * @param bndtDiary - 당직일지관리 model
	 */
	@Override
	public void deleteBndtDiary(BndtDiary bndtDiary) throws Exception {
		bndtManageDAO.deleteBndtDiary(bndtDiary);
	}


	/*###  엑셀 일괄처리 프로세스 ###*/

	/**
	 * 당직자 excel생성
	 * @param  inputStream InputStream
	 * @return  String
	 * @exception Exception
	 */
	@Override
	public List<BndtManageVO> selectBndtManageBnde(InputStream inputStream)throws Exception{
//	    int bndtSheetRowCnt = 0;
//	    String xlsFile = null;
	    String sTempNm = null;
	    String sTempId = null;


	    List<BndtManageVO> list = new ArrayList<BndtManageVO>();

    	String   sBndtDe = null;
    	HSSFWorkbook hssfWB = (HSSFWorkbook) excelZipService.loadWorkbook(inputStream);
    	// 엑셀 파일 시트 개수 확인 sheet = 1
		if (hssfWB.getNumberOfSheets() == 1) {
            HSSFSheet bndtSheet  = hssfWB.getSheetAt(0);  //당직자 시트 가져오기
//            HSSFRow   bndtRow    = bndtSheet.getRow(1); //당직자 row 가져오기
//            bndtSheetRowCnt      = bndtRow.getPhysicalNumberOfCells(); //당직자 cell Cnt
            int rowsCnt=bndtSheet.getPhysicalNumberOfRows(); //행 개수 가져오기

            BndtManageVO checkBndtManageVO = new BndtManageVO();
            for(int j=1; j<rowsCnt; j++){ //row 루프
            	BndtManageVO bndtManageVO = new BndtManageVO();
                HSSFRow row=bndtSheet.getRow(j); //row 가져오기
                if(row!=null){
//                    int cells = row.getPhysicalNumberOfCells(); //cell 개수 가져오기
                    HSSFCell cell = null;
                	cell = row.getCell(0);  //당직일자
                	if(cell!=null){
                		 sBndtDe = cell.getStringCellValue();
                	}
                    cell = row.getCell(1); //당직자ID
                    if(cell!=null){
            	    	sTempId = cell.getStringCellValue();
                	}
                    cell = row.getCell(2); //당직자명
                    if(cell!=null){
            	    	sTempNm = cell.getStringCellValue();
                	}
                    checkBndtManageVO.setTempBndtNm(sTempNm); // 당직자ID
                    checkBndtManageVO.setTempBndtId(sTempId); // 당직자명
                    
                	//최두영 로직변경
                    bndtManageVO = bndtManageDAO.selectBndtManageBnde(checkBndtManageVO);
                    if (bndtManageVO == null) {
                    	bndtManageVO = new BndtManageVO();
                    	BeanUtils.copyProperties(checkBndtManageVO, bndtManageVO);
                    }
                    
                	bndtManageVO.setBndtDe(sBndtDe);
                	bndtManageVO.setDateWeek(getDateWeekInt(sBndtDe));
                    bndtManageVO.setTempBndtWeek(getDateWeekString(sBndtDe));
                    
                    list.add(bndtManageVO);
                }
            }
        }

		return  list ;
	}

	
	/*###  엑셀 일괄처리 프로세스 ###*/

	/**
	 * 당직자 excel생성 (Xlsx 처리)
	 * @param  inputStream InputStream
	 * @return  String
	 * @exception Exception
	 */
	@Override
	public List<BndtManageVO> selectBndtManageBndeX(InputStream inputStream)throws Exception{
//	    int bndtSheetRowCnt = 0;
//	    String xlsFile = null;
	    String sTempNm = null;
	    String sTempId = null;


	    List<BndtManageVO> list = new ArrayList<BndtManageVO>();

    	String   sBndtDe = null;
    	XSSFWorkbook hssfWB = null;// = (XSSFWorkbook) excelZipService.loadWorkbook(inputStream,null);
    	try {
    		hssfWB = new XSSFWorkbook(inputStream);

    	} catch (IOException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
    		LOGGER.debug("=====>>>>> ERR : IOException "+e.getMessage());
    	}

    	// 엑셀 파일 시트 개수 확인 sheet = 1
		if (hssfWB != null && hssfWB.getNumberOfSheets() == 1) {
            XSSFSheet bndtSheet  = hssfWB.getSheetAt(0);  //당직자 시트 가져오기
//            XSSFRow   bndtRow    = bndtSheet.getRow(1); //당직자 row 가져오기
//            bndtSheetRowCnt      = bndtRow.getPhysicalNumberOfCells(); //당직자 cell Cnt
            int rowsCnt=bndtSheet.getPhysicalNumberOfRows(); //행 개수 가져오기

            BndtManageVO checkBndtManageVO = new BndtManageVO();
            for(int j=1; j<rowsCnt; j++){ //row 루프
            	BndtManageVO bndtManageVO = new BndtManageVO();
                XSSFRow row=bndtSheet.getRow(j); //row 가져오기
                if(row!=null){
//                    int cells = row.getPhysicalNumberOfCells(); //cell 개수 가져오기
                    XSSFCell cell = null;
                	cell = row.getCell(0);  //당직일자
                	if(cell!=null){
                		 sBndtDe = cell.getStringCellValue();
                	}
                    cell = row.getCell(1); //당직자ID
                    if(cell!=null){
            	    	sTempId = cell.getStringCellValue();
                	}
                    cell = row.getCell(2); //당직자명
                    if(cell!=null){
            	    	sTempNm = cell.getStringCellValue();
                	}
                    checkBndtManageVO.setTempBndtNm(sTempNm); // 당직자ID
                    checkBndtManageVO.setTempBndtId(sTempId); // 당직자명
                    
                	//최두영 로직변경
                    bndtManageVO = bndtManageDAO.selectBndtManageBnde(checkBndtManageVO);
                    if (bndtManageVO == null) {
                    	bndtManageVO = new BndtManageVO();
                    	BeanUtils.copyProperties(checkBndtManageVO, bndtManageVO);
                    }
                    
                	bndtManageVO.setBndtDe(sBndtDe);
                	bndtManageVO.setDateWeek(getDateWeekInt(sBndtDe));
                    bndtManageVO.setTempBndtWeek(getDateWeekString(sBndtDe));
                    
                    list.add(bndtManageVO);
                }
            }
        }

		return  list ;
	}
	
	/**
	 * 당직정보를 일괄등록처리한다.
	 * @param bndtManageVO     - 당직관리 VO
	 * @param String           - 당직자정보
    */
	@Override
	public void insertBndtManageBnde(BndtManageVO bndtManageVO, String checkedBndtManageForInsert) throws Exception {
		BndtManage bndtManage;

		// 2022.11.11 시큐어코딩 처리
		if (StringUtils.isNotEmpty(checkedBndtManageForInsert)) {
			String[] bndtManageValues = checkedBndtManageForInsert.split("[$]");
			for (int i = 0; i < bndtManageValues.length; i++) {
				bndtManage = new BndtManage();
				String sTemp = bndtManageValues[i];
				String[] sTempBndtManage = sTemp.split(",");
				bndtManage.setBndtDe(sTempBndtManage[0]);
				bndtManage.setBndtId(sTempBndtManage[1]);
				bndtManage.setRemark("당직일괄등록");
				bndtManage.setFrstRegisterId(bndtManageVO.getFrstRegisterId());

				bndtManageDAO.insertBndtManage(bndtManage);
			}
		}
	}

    /**
	 * 당직관리 건수를 조회한다.
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
	 * @param annvrsryManageVO
	 * @return long (1~7로 요일을 리턴)
	 */
	@SuppressWarnings("static-access")
	private int getDateWeekInt(String sDate) throws Exception{
		Calendar target_day = Calendar.getInstance();
    	String sDayOfWeek = null;
    	int iWeek = 0;
    	sDayOfWeek = EgovStringUtil.removeMinusChar(sDate);
    	// KISA 보안약점 조치 - 널(null) 값 체크
    	if ( sDayOfWeek == null ) return 0;
   		target_day.set(Integer.parseInt(sDayOfWeek.substring(0,4)),Integer.parseInt(sDayOfWeek.substring(4,6))-1,Integer.parseInt(sDayOfWeek.substring(6,8)));
		iWeek = target_day.get(target_day.DAY_OF_WEEK);
		return iWeek;
	}

	/**
	 * 해당일자와 현재일자의 일수 계산
	 * @param annvrsryManageVO
	 * @return long
	 */
	private String getDateWeekString(String sDate) throws Exception{

    	String sDayOfWeek     = null;
    	String sDayOfWeekReturnValue  = null;
    	sDayOfWeek = EgovStringUtil.removeMinusChar(sDate);
        String[] dayOfWeek={"일","월","화","수","목","금","토"};
        Calendar target_day=new GregorianCalendar();
        
        if(sDayOfWeek != null && sDayOfWeek.length() >= 8) {
	        target_day.set(Integer.parseInt(sDayOfWeek.substring(0,4)),Integer.parseInt(sDayOfWeek.substring(4,6))-1,Integer.parseInt(sDayOfWeek.substring(6,8)));
			sDayOfWeekReturnValue = EgovDateUtil.formatDate(sDayOfWeek, "-")+" "+dayOfWeek[target_day.get(Calendar.DAY_OF_WEEK)-1];
        }

        return sDayOfWeekReturnValue;

	}
}
