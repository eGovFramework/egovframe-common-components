package egovframework.com.cop.ems.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.ems.service.SndngMailVO;

/**
 * 발송메일을 상세 조회하는 DAO 클래스
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
@Repository("sndngMailDetailDAO")
public class SndngMailDetailDAO extends EgovComAbstractDAO {

	/**
	 * 발송메일을 상세 조회한다.
	 * 
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 */
	public SndngMailVO selectSndngMail(SndngMailVO vo) {
		return (SndngMailVO) selectOne("sndngMailDetailDAO.selectSndngMail", vo);
	}

	/**
	 * 발송메일을 삭제한다.
	 * 
	 * @param vo SndngMailVO
	 */
	public int deleteSndngMail(SndngMailVO vo) {
		return delete("sndngMailDetailDAO.deleteSndngMail", vo);
	}

}
