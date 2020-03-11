package egovframework.com.sym.ccm.zip.service.impl;

import egovframework.com.sym.ccm.zip.service.Zip;

import egovframework.rte.fdl.excel.EgovExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * Excel 우편번호 매핑 클래스
 * @author 공통서비스 개발팀 이기하
 * @since 2011.11.21
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일      	수정자           수정내용
 *  -----------    --------    ---------------------------
 *   2011.11.21		이기하           도로명주소 최초 생성
 *
 * </pre>
 */
public class EgovCcmExcelRdnmadZipMapping extends EgovExcelMapping {

	/**
	 * 우편번호 엑셀파일 맵핑
	 */
	@Override
	public Object mappingColumn(Row row) {
		HSSFCell cell0 = (HSSFCell) row.getCell(0);
    	HSSFCell cell1 = (HSSFCell) row.getCell(1);
    	HSSFCell cell2 = (HSSFCell) row.getCell(2);
    	HSSFCell cell3 = (HSSFCell) row.getCell(3);
    	HSSFCell cell4 = (HSSFCell) row.getCell(4);
    	HSSFCell cell5 = (HSSFCell) row.getCell(5);
    	HSSFCell cell6 = (HSSFCell) row.getCell(6);
    	HSSFCell cell7 = (HSSFCell) row.getCell(7);
    	HSSFCell cell8 = (HSSFCell) row.getCell(8);
    	HSSFCell cell9 = (HSSFCell) row.getCell(9);
    	HSSFCell cell10 = (HSSFCell) row.getCell(10);

		Zip vo = new Zip();

		vo.setRdmnCode       (EgovExcelUtil.getValue(cell0));
		vo.setSn             (Integer.parseInt(EgovExcelUtil.getValue(cell1)));
		vo.setCtprvnNm       (EgovExcelUtil.getValue(cell2));
		vo.setSignguNm       (EgovExcelUtil.getValue(cell3));
		vo.setRdmn           (EgovExcelUtil.getValue(cell4));
		vo.setBdnbrMnnm		 (EgovExcelUtil.getValue(cell5));
		vo.setBdnbrSlno 	 (EgovExcelUtil.getValue(cell6));
		vo.setZip		     (EgovExcelUtil.getValue(cell9));
		vo.setFrstRegisterId (EgovExcelUtil.getValue(cell10));

		if (cell6 != null) {vo.setBuldNm   		(EgovExcelUtil.getValue(cell7));}
		if (cell7 != null) {vo.setDetailBuldNm  (EgovExcelUtil.getValue(cell8));}

		return vo;
	}
}
