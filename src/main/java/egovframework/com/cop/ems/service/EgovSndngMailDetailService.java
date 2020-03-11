package egovframework.com.cop.ems.service;

/**
 * 발송메일을 상세 조회하는 비즈니스 인터페이스 클래스
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
public interface EgovSndngMailDetailService {

	/**
	 * 발송메일을 상세 조회한다.
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 * @exception Exception
	 */
	SndngMailVO selectSndngMail(SndngMailVO vo) throws Exception;

	/**
	 * 발송메일을 삭제한다.
	 * @param vo SndngMailVO
	 * @exception
	 */
	void deleteSndngMail(SndngMailVO vo) throws Exception;

	/**
	 * 첨부파일을 삭제한다.
	 * @param vo SndngMailVO
	 * @exception
	 */
	void deleteAtchmnFile(SndngMailVO vo) throws Exception;
}
