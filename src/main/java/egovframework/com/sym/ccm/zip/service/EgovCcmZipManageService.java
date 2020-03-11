package egovframework.com.sym.ccm.zip.service;

import java.io.InputStream;
import java.util.List;


/**
 *
 * 우편번호에 관한 서비스 인터페이스 클래스를 정의한다
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
 *
 * </pre>
 */
public interface EgovCcmZipManageService {

	/**
	 * 우편번호를 삭제한다.
	 * @param zip
	 * @throws Exception
	 */
	void deleteZip(Zip zip) throws Exception;

	/**
	 * 우편번호 전체를 삭제한다.
	 * @throws Exception
	 */
	void deleteAllZip() throws Exception;

	/**
	 * 우편번호를 등록한다.
	 * @param zip
	 * @throws Exception
	 */
	void insertZip(Zip zip) throws Exception;

	/**
	 * 우편번호 엑셀파일을 등록한다.
	 * @param zip
	 * @throws Exception
	 */
	void insertExcelZip(InputStream file) throws Exception;

	/**
	 * 우편번호 상세항목을 조회한다.
	 * @param zip
	 * @return Zip(우편번호)
	 * @throws Exception
	 */
	Zip selectZipDetail(Zip zip) throws Exception;

	/**
	 * 우편번호 목록을 조회한다.
	 * @param searchVO
	 * @return List(우편번호 목록)
	 * @throws Exception
	 */
	List<?> selectZipList(ZipVO searchVO) throws Exception;

    /**
	 * 우편번호 총 갯수를 조회한다.
     * @param searchVO
     * @return int(우편번호 총 갯수)
     */
    int selectZipListTotCnt(ZipVO searchVO) throws Exception;

	/**
	 * 우편번호를 수정한다.
	 * @param zip
	 * @throws Exception
	 */
	void updateZip(Zip zip) throws Exception;

}
