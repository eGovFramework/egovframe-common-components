package egovframework.com.cop.ems.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.ems.service.SndngMailVO;

/**
 * 발송메일 내역을 조회하는 DAO 클래스
 * 
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일         수정자       수정내용
 *  ----------    --------    ---------------------------
 *   2009.03.12  박지욱          최초 생성
 *   2024.09.03  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *      </pre>
 */
@Repository("sndngMailDtlsDAO")
public class SndngMailDtlsDAO extends EgovComAbstractDAO {

	/**
	 * 발송메일 목록을 조회한다.
	 * 
	 * @param vo ComDefaultVO
	 * @return List
	 */
	public List<SndngMailVO> selectSndngMailList(ComDefaultVO vo) {
		return selectList("SndngMailDtlsDAO.selectSndngMailList_D", vo);
	}

	/**
	 * 발송메일 총건수를 조회한다.
	 * 
	 * @param vo ComDefaultVO
	 * @return int
	 */
	public int selectSndngMailListTotCnt(ComDefaultVO vo) {
		return (Integer) selectOne("SndngMailDtlsDAO.selectSndngMailListTotCnt_S", vo);
	}
}
