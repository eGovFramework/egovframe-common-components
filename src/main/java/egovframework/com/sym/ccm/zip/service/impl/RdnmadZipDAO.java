package egovframework.com.sym.ccm.zip.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.ccm.zip.service.Zip;
import egovframework.com.sym.ccm.zip.service.ZipVO;

import org.springframework.stereotype.Repository;

/**
 *
 * 우편번호에 대한 데이터 접근 클래스를 정의한다
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
@Repository("RdnmadZipDAO")
public class RdnmadZipDAO extends EgovComAbstractDAO {

	/**
	 * 우편번호를 삭제한다.
	 * @param zip
	 * @throws Exception
	 */
	public void deleteZip(Zip zip) throws Exception {
		delete("RdnmadZipDAO.deleteZip", zip);
	}

	/**
	 * 우편번호 전체를 삭제한다.
	 * @throws Exception
	 */
	public void deleteAllZip() throws Exception {
		delete("RdnmadZipDAO.deleteAllZip", new Object());
	}

	/**
	 * 우편번호를 등록한다.
	 * @param zip
	 * @throws Exception
	 */
	public void insertZip(Zip zip) throws Exception {
        insert("RdnmadZipDAO.insertZip", zip);
	}

	/**
	 * 우편번호 엑셀파일을 등록한다.
	 * @param zip
	 * @throws Exception
	 */
	public void insertExcelZip() throws Exception {
		delete("RdnmadZipDAO.deleteAllZip", new Object());
	}


	/**
	 * 우편번호 상세항목을 조회한다.
	 * @param zip
	 * @return Zip(우편번호)
	 */
	public Zip selectZipDetail(Zip zip) throws Exception {
		return (Zip) selectOne("RdnmadZipDAO.selectZipDetail", zip);
	}


    /**
	 * 우편번호 목록을 조회한다.
     * @param searchVO
     * @return List(우편번호 목록)
     * @throws Exception
     */
    public List<?> selectZipList(ZipVO searchVO) throws Exception {
        return selectList("RdnmadZipDAO.selectZipList", searchVO);
    }

    /**
	 * 우편번호 총 갯수를 조회한다.
     * @param searchVO
     * @return int(우편번호 총 갯수)
     */
    public int selectZipListTotCnt(ZipVO searchVO) throws Exception {
        return (Integer)selectOne("RdnmadZipDAO.selectZipListTotCnt", searchVO);
    }

	/**
	 * 우편번호를 수정한다.
	 * @param zip
	 * @throws Exception
	 */
	public void updateZip(Zip zip) throws Exception {
		update("RdnmadZipDAO.updateZip", zip);
	}

}
