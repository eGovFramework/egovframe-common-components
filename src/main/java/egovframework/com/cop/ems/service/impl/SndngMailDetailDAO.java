package egovframework.com.cop.ems.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.ems.service.SndngMailVO;

import org.springframework.stereotype.Repository;

/**
 * 발송메일을 상세 조회하는 DAO 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  박지욱          최초 생성
 *
 *  </pre>
 */
@Repository("sndngMailDetailDAO")
public class SndngMailDetailDAO extends EgovComAbstractDAO {

	/**
	 * 발송메일을 상세 조회한다.
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 * @exception Exception
	 */
	public SndngMailVO selectSndngMail(SndngMailVO vo) throws Exception {
		return (SndngMailVO) selectOne("sndngMailDetailDAO.selectSndngMail", vo);
	}

	/**
	 * 발송메일에 첨부된 파일목록을 조회한다.
	 * @param vo SndngMailVO
	 * @return List
	 * @exception
	 */
	public List<?> selectAtchmnFileList(SndngMailVO vo) {
		return list("sndngMailDetailDAO.selectAtchmnFileList", vo);
	}

	/**
	 * 발송메일을 삭제한다.
	 * @param vo SndngMailVO
	 * @exception
	 */
	public void deleteSndngMail(SndngMailVO vo) throws Exception {
		delete("sndngMailDetailDAO.deleteSndngMail", vo);
	}

	/**
	 * 첨부파일 목록을 삭제한다.
	 * @param vo SndngMailVO
	 * @exception
	 */
	public void deleteAtchmnFileList(SndngMailVO vo) throws Exception {
		delete("sndngMailDetailDAO.deleteAtchmnFileList", vo);
	}
}
