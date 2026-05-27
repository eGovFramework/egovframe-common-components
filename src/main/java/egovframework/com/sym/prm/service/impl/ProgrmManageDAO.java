package egovframework.com.sym.prm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.prm.service.ProgrmManageDtlVO;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import jakarta.annotation.Resource;

/**
 * 프로그램 목록관리및 프로그램변경관리에 대한 DAO 클래스를 정의한다.
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
@Repository("progrmManageDAO")
public class ProgrmManageDAO {

	@Resource(name = "progrmManageDAO")
	private ProgrmManageMapper progrmManageMapper;

	/**
     * 프로그램 목록을 조회
     *
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    public List<ProgrmManageVO> selectProgrmList(ComDefaultVO vo) throws Exception {
        return progrmManageMapper.selectProgrmList_D(vo);
    }

    /**
	 * 프로그램목록 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 */
    public int selectProgrmListTotCnt(ComDefaultVO vo) {
        return progrmManageMapper.selectProgrmListTotCnt_S(vo);
    }

	/**
	 * 프로그램 기본정보를 조회
	 * @param vo ProgrmManageVO
	 * @return ProgrmManageVO
	 * @exception Exception
	 */
	public ProgrmManageVO selectProgrm(ProgrmManageVO vo) throws Exception {
		return progrmManageMapper.selectProgrm_D(vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 등록
	 * @param vo ProgrmManageVO
	 */
	public void insertProgrm(ProgrmManageVO vo) {
		progrmManageMapper.insertProgrm_S(vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 수정
	 * @param vo ProgrmManageVO
	 */
	public void updateProgrm(ProgrmManageVO vo) {
		progrmManageMapper.updateProgrm_S(vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 삭제
	 * @param vo ProgrmManageVO
	 */
	public void deleteProgrm(ProgrmManageVO vo) {
		progrmManageMapper.deleteProgrm_S(vo);
	}

	/**
	 * 프로그램 파일 존재여부를 조회
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	public int selectProgrmNMTotCnt(ComDefaultVO vo) throws Exception {
		return progrmManageMapper.selectProgrmNMTotCnt(vo);
	}

	/**
	 * 프로그램변경요청 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<ProgrmManageDtlVO> selectProgrmChangeRequstList(ComDefaultVO vo) throws Exception {
		return progrmManageMapper.selectProgrmChangeRequstList_D(vo);
	}

    /**
	 * 프로그램변경요청 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 */
    public int selectProgrmChangeRequstListTotCnt(ComDefaultVO vo) {
        return progrmManageMapper.selectProgrmChangeRequstListTotCnt_S(vo);
    }

	/**
	 * 프로그램변경요청 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 * @exception Exception
	 */
	public ProgrmManageDtlVO selectProgrmChangeRequst(ProgrmManageDtlVO vo) throws Exception {
		return progrmManageMapper.selectProgrmChangeRequst_D(vo);
	}

	/**
	 * 프로그램변경요청을 등록
	 * @param vo ProgrmManageDtlVO
	 */
	public void insertProgrmChangeRequst(ProgrmManageDtlVO vo) {
		progrmManageMapper.insertProgrmChangeRequst_S(vo);
	}

	/**
	 * 프로그램변경요청을 수정
	 * @param vo ProgrmManageDtlVO
	 */
	public void updateProgrmChangeRequst(ProgrmManageDtlVO vo) {
		progrmManageMapper.updateProgrmChangeRequst_S(vo);
	}

	/**
	 * 프로그램변경요청을 삭제
	 * @param vo ProgrmManageDtlVO
	 */
	public void deleteProgrmChangeRequst(ProgrmManageDtlVO vo) {
		progrmManageMapper.deleteProgrmChangeRequst_S(vo);
	}

	/**
	 * 프로그램변경요청 요청번호MAX 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 */
	public ProgrmManageDtlVO selectProgrmChangeRequstNo(ProgrmManageDtlVO vo) {
		return progrmManageMapper.selectProgrmChangeRequstNo_D(vo);
	}

	/**
	 * 프로그램변경요청처리 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectChangeRequstProcessList(ComDefaultVO vo) throws Exception {
		return progrmManageMapper.selectChangeRequstProcessList_D(vo);
	}

    /**
	 * 프로그램변경요청처리 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 */
    public int selectChangeRequstListProcessTotCnt(ComDefaultVO vo) {
        return progrmManageMapper.selectChangeRequstProcessListTotCnt_S(vo);
    }

	/**
	 * 프로그램변경요청 처리 수정
	 * @param vo ProgrmManageDtlVO
	 */
	public void updateProgrmChangeRequstProcess(ProgrmManageDtlVO vo) {
		progrmManageMapper.updateProgrmChangeRequstProcess_S(vo);
	}

	/**
	 * 프로그램목록 전체삭제 초기화
	 * @return boolean
	 */
	public boolean deleteAllProgrm() {
		progrmManageMapper.deleteAllProgrm(new ProgrmManageVO());
		return true;
	}

	/**
	 * 프로그램변경내역 전체삭제 초기화
	 * @return boolean
	 */
	public boolean deleteAllProgrmDtls() {
		progrmManageMapper.deleteAllProgrmDtls(new ProgrmManageDtlVO());
		return true;
	}

    /**
	 * 프로그램목록 데이타 존재여부 조회한다.
	 * @return int
	 */
    public int selectProgrmListTotCnt() {
        return progrmManageMapper.selectProgrmListTotCnt(new ProgrmManageVO());
    }

	/**
	 * 프로그램변경요청자 Email 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 */
	public ProgrmManageDtlVO selectRqesterEmail(ProgrmManageDtlVO vo) {
		return progrmManageMapper.selectRqesterEmail(vo);
	}
}
