package egovframework.com.sym.ccm.zip.service.impl;

import java.io.InputStream;
import java.util.List;

import egovframework.com.sym.ccm.zip.service.EgovCcmZipManageService;
import egovframework.com.sym.ccm.zip.service.Zip;
import egovframework.com.sym.ccm.zip.service.ZipVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.excel.EgovExcelService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 *
 * 우편번호에 대한 서비스 구현클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.11.22  이기하          엑셀업로드 시작위치 수정(2 -> 1)
 *
 * </pre>
 */
@Service("ZipManageService")
public class EgovCcmZipManageServiceImpl extends EgovAbstractServiceImpl implements EgovCcmZipManageService {

    @Resource(name="ZipManageDAO")
    private ZipManageDAO zipManageDAO;

    @Resource(name = "excelZipService")
    private EgovExcelService excelZipService;

	/**
	 * 우편번호를 삭제한다.
	 */
	@Override
	public void deleteZip(Zip zip) throws Exception {
		zipManageDAO.deleteZip(zip);
	}

	/**
	 * 우편번호 전체를 삭제한다.
	 */
	@Override
	public void deleteAllZip() throws Exception {
		zipManageDAO.deleteAllZip();
	}

	/**
	 * 우편번호를 등록한다.
	 */
	@Override
	public void insertZip(Zip zip) throws Exception {
    	zipManageDAO.insertZip(zip);
	}

	/**
	 * 우편번호 엑셀파일을 등록한다.
	 * @param zip
	 * @throws Exception
	 */
	@Override
	public void insertExcelZip(InputStream file) throws Exception {
		zipManageDAO.insertExcelZip();
		excelZipService.uploadExcel("ZipManageDAO.insertExcelZip", file, 1, 5000);
	}

	/**
	 * 우편번호 상세항목을 조회한다.
	 */
	@Override
	public Zip selectZipDetail(Zip zip) throws Exception {
    	Zip ret = zipManageDAO.selectZipDetail(zip);
    	return ret;
	}

	/**
	 * 우편번호 목록을 조회한다.
	 */
	@Override
	public List<?> selectZipList(ZipVO searchVO) throws Exception {
        return zipManageDAO.selectZipList(searchVO);
	}

	/**
	 * 우편번호 총 갯수를 조회한다.
	 */
	@Override
	public int selectZipListTotCnt(ZipVO searchVO) throws Exception {
        return zipManageDAO.selectZipListTotCnt(searchVO);
	}

	/**
	 * 우편번호를 수정한다.
	 */
	@Override
	public void updateZip(Zip zip) throws Exception {
		zipManageDAO.updateZip(zip);
	}

}
