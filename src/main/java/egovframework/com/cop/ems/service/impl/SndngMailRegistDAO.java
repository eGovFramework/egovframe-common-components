package egovframework.com.cop.ems.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.ems.service.SndngMailVO;

import org.springframework.stereotype.Repository;

/**
 * 발송메일을 등록하는 DAO 클래스
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
@Repository("sndngMailRegistDAO")
public class SndngMailRegistDAO extends EgovComAbstractDAO {

	/**
	 * 발송할 메일을 등록한다
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 * @exception Exception
	 */
	public SndngMailVO insertSndngMail(SndngMailVO vo) throws Exception {
		insert("sndngMailRegistDAO.insertSndngMail", vo);
		return new SndngMailVO() ;
	}

	/**
	 * 발송할 메일에 있는 첨부파일 목록을 조회한다.
	 * @param vo SndngMailVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectAtchmnFileList(SndngMailVO vo) throws Exception {
		return selectList("sndngMailRegistDAO.selectAtchmnFileList", vo);
	}

	/**
	 * 발송결과를 수정한다.
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 * @exception Exception
	 */
	public SndngMailVO updateSndngMail(SndngMailVO vo) throws Exception {
		update("sndngMailRegistDAO.updateSndngMail", vo);
		return new SndngMailVO();
	}
}
