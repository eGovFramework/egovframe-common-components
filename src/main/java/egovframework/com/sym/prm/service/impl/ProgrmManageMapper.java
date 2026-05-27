package egovframework.com.sym.prm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.prm.service.ProgrmManageDtlVO;
import egovframework.com.sym.prm.service.ProgrmManageVO;

/**
 * 프로그램 목록관리 및 프로그램변경관리에 대한 MyBatis 매퍼 인터페이스를 정의한다.
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
 *
 * </pre>
 */
@EgovMapper("progrmManageDAO")
public interface ProgrmManageMapper {

	/**
	 * 프로그램 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	List<ProgrmManageVO> selectProgrmList_D(ComDefaultVO vo) throws Exception;

	/**
	 * 프로그램목록 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 */
	int selectProgrmListTotCnt_S(ComDefaultVO vo);

	/**
	 * 프로그램 기본정보를 조회
	 * @param vo ProgrmManageVO
	 * @return ProgrmManageVO
	 * @exception Exception
	 */
	ProgrmManageVO selectProgrm_D(ProgrmManageVO vo) throws Exception;

	/**
	 * 프로그램 기본정보 및 URL을 등록
	 * @param vo ProgrmManageVO
	 */
	void insertProgrm_S(ProgrmManageVO vo);

	/**
	 * 프로그램 기본정보 및 URL을 수정
	 * @param vo ProgrmManageVO
	 */
	void updateProgrm_S(ProgrmManageVO vo);

	/**
	 * 프로그램 기본정보 및 URL을 삭제
	 * @param vo ProgrmManageVO
	 */
	void deleteProgrm_S(ProgrmManageVO vo);

	/**
	 * 프로그램 파일 존재여부를 조회
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	int selectProgrmNMTotCnt(ComDefaultVO vo) throws Exception;

	/**
	 * 프로그램변경요청 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	List<ProgrmManageDtlVO> selectProgrmChangeRequstList_D(ComDefaultVO vo) throws Exception;

	/**
	 * 프로그램변경요청 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 */
	int selectProgrmChangeRequstListTotCnt_S(ComDefaultVO vo);

	/**
	 * 프로그램변경요청 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 * @exception Exception
	 */
	ProgrmManageDtlVO selectProgrmChangeRequst_D(ProgrmManageDtlVO vo) throws Exception;

	/**
	 * 프로그램변경요청을 등록
	 * @param vo ProgrmManageDtlVO
	 */
	void insertProgrmChangeRequst_S(ProgrmManageDtlVO vo);

	/**
	 * 프로그램변경요청을 수정
	 * @param vo ProgrmManageDtlVO
	 */
	void updateProgrmChangeRequst_S(ProgrmManageDtlVO vo);

	/**
	 * 프로그램변경요청을 삭제
	 * @param vo ProgrmManageDtlVO
	 */
	void deleteProgrmChangeRequst_S(ProgrmManageDtlVO vo);

	/**
	 * 프로그램변경요청 요청번호MAX 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 */
	ProgrmManageDtlVO selectProgrmChangeRequstNo_D(ProgrmManageDtlVO vo);

	/**
	 * 프로그램변경요청처리 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectChangeRequstProcessList_D(ComDefaultVO vo) throws Exception;

	/**
	 * 프로그램변경요청처리 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 */
	int selectChangeRequstProcessListTotCnt_S(ComDefaultVO vo);

	/**
	 * 프로그램변경요청처리를 수정
	 * @param vo ProgrmManageDtlVO
	 */
	void updateProgrmChangeRequstProcess_S(ProgrmManageDtlVO vo);

	/**
	 * 프로그램목록 전체삭제 초기화
	 * @param vo ProgrmManageVO
	 */
	void deleteAllProgrm(ProgrmManageVO vo);

	/**
	 * 프로그램변경내역 전체삭제 초기화
	 * @param vo ProgrmManageDtlVO
	 */
	void deleteAllProgrmDtls(ProgrmManageDtlVO vo);

	/**
	 * 프로그램목록 데이타 존재여부 조회한다.
	 * @param vo ProgrmManageVO
	 * @return int
	 */
	int selectProgrmListTotCnt(ProgrmManageVO vo);

	/**
	 * 프로그램변경요청자 Email 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 */
	ProgrmManageDtlVO selectRqesterEmail(ProgrmManageDtlVO vo);
}
