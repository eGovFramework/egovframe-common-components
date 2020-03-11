package egovframework.com.sym.ccm.zip.service.impl;

import java.io.InputStream;
import java.util.List;

import egovframework.com.sym.ccm.zip.service.EgovCcmRdnmadZipManageService;
import egovframework.com.sym.ccm.zip.service.Zip;
import egovframework.com.sym.ccm.zip.service.ZipVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.excel.EgovExcelService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 *
 * 우편번호에 대한 서비스 구현클래스를 정의한다
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
@Service("RdnmadZipService")
public class EgovCcmRdnmadZipServiceImpl extends EgovAbstractServiceImpl implements EgovCcmRdnmadZipManageService {

    @Resource(name="RdnmadZipDAO")
    private RdnmadZipDAO rdnmadZipDAO;

    @Resource(name = "excelRdnmadZipService")
    private EgovExcelService excelRdnmadZipService;

	/**
	 * 우편번호를 삭제한다.
	 */
	@Override
	public void deleteZip(Zip zip) throws Exception {
		rdnmadZipDAO.deleteZip(zip);
	}

	/**
	 * 우편번호 전체를 삭제한다.
	 */
	@Override
	public void deleteAllZip() throws Exception {
		rdnmadZipDAO.deleteAllZip();
	}

	/**
	 * 우편번호를 등록한다.
	 */
	@Override
	public void insertZip(Zip zip) throws Exception {
		rdnmadZipDAO.insertZip(zip);
	}

	/**
	 * 우편번호 엑셀파일을 등록한다.
	 * @param zip
	 * @throws Exception
	 */
	@Override
	public void insertExcelZip(InputStream file) throws Exception {
		rdnmadZipDAO.insertExcelZip();
		excelRdnmadZipService.uploadExcel("RdnmadZipDAO.insertExcelZip", file, 1, 5000);
	}

	/**
	 * 우편번호 상세항목을 조회한다.
	 */
	@Override
	public Zip selectZipDetail(Zip zip) throws Exception {
    	Zip ret = rdnmadZipDAO.selectZipDetail(zip);
    	return ret;
	}

	/**
	 * 우편번호 목록을 조회한다.
	 */
	@Override
	public List<?> selectZipList(ZipVO searchVO) throws Exception {
        return rdnmadZipDAO.selectZipList(searchVO);
	}

	/**
	 * 우편번호 총 갯수를 조회한다.
	 */
	@Override
	public int selectZipListTotCnt(ZipVO searchVO) throws Exception {
        return rdnmadZipDAO.selectZipListTotCnt(searchVO);
	}

	/**
	 * 우편번호를 수정한다.
	 */
	@Override
	public void updateZip(Zip zip) throws Exception {
		rdnmadZipDAO.updateZip(zip);
	}

}
