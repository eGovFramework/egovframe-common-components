package egovframework.com.cop.ems.service;

/**
 * 메일 솔루션과 연동해서 이용해서 메일을 보내는 서비스 클래스
 * @since 2011.09.09
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.09.09  서준식       최초 작성
 *
 *  </pre>
 */

public interface EgovSndngMailService {

	/**
	 * 메일을 발송한다
	 * @param vo SndngMailVO
	 * @return boolean
	 * @exception Exception
	 */
	boolean sndngMail(SndngMailVO vo) throws Exception;
}
