package egovframework.com.cop.ems.service;

/**
 * 발송메일을 상세 조회하는 비즈니스 인터페이스 클래스
 * 
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *   2009.03.12  박지욱          최초 생성
 *   2024.09.03  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *      </pre>
 */
public interface EgovSndngMailDetailService {

	/**
	 * 발송메일을 상세 조회한다.
	 * 
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 */
	SndngMailVO selectSndngMail(SndngMailVO vo);

	/**
	 * 발송메일을 삭제한다.
	 * 
	 * @param vo SndngMailVO
	 */
	void deleteSndngMail(SndngMailVO vo);

	/**
	 * 첨부파일을 삭제한다.
	 * 
	 * @param vo SndngMailVO
	 */
	void deleteAtchmnFile(SndngMailVO vo);
}
