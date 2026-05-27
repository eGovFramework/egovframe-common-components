package egovframework.com.sym.ccm.zip.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.sym.ccm.zip.service.Zip;
import egovframework.com.sym.ccm.zip.service.ZipVO;

/**
 * 도로명주소 우편번호 관리에 대한 데이터 접근 매퍼 인터페이스
 * @author 공통서비스 개발팀 이기하
 * @since 2011.11.21
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일          수정자           수정내용
 *  -----------    --------    ---------------------------
 *   2011.11.21     이기하           도로명주소 최초 생성
 *
 * </pre>
 */
@EgovMapper("RdnmadZipMapper")
public interface RdnmadZipMapper {

	/**
	 * 우편번호를 삭제한다.
	 * @param zip
	 */
	void deleteZip(Zip zip);

	/**
	 * 우편번호 전체를 삭제한다.
	 */
	void deleteAllZip();

	/**
	 * 우편번호를 등록한다.
	 * @param zip
	 */
	void insertZip(Zip zip);

	/**
	 * 우편번호 엑셀파일을 등록한다.
	 */
	void insertExcelZip();

	/**
	 * 우편번호 상세항목을 조회한다.
	 * @param zip
	 * @return Zip(우편번호)
	 */
	Zip selectZipDetail(Zip zip);

	/**
	 * 우편번호 목록을 조회한다.
	 * @param searchVO
	 * @return List(우편번호 목록)
	 */
	List<EgovMap> selectZipList(ZipVO searchVO);

	/**
	 * 우편번호 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(우편번호 총 개수)
	 */
	int selectZipListTotCnt(ZipVO searchVO);

	/**
	 * 우편번호를 수정한다.
	 * @param zip
	 */
	void updateZip(Zip zip);

}
