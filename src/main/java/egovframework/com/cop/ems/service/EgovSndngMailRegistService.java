package egovframework.com.cop.ems.service;

/**
 * 발송메일등록, 발송요청XML파일 생성하는 비즈니스 인터페이스 클래스
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
 *  2009.03.12  박지욱          최초 생성
 *   2024.07.29  이백행          시큐어코딩 Exception 제거
 *
 *      </pre>
 */
public interface EgovSndngMailRegistService {

	/**
	 * 발송할 메일을 등록한다
	 * 
	 * @param vo SndngMailVO
	 * @return boolean
	 */
	boolean insertSndngMail(SndngMailVO vo);

	/**
	 * 발송할 메일을 XML파일로 만들어 저장한다.
	 * 
	 * @param vo SndngMailVO
	 * @return boolean
	 */
	public boolean trnsmitXmlData(SndngMailVO vo);

	/**
	 * 발송메일 발송결과 XML파일을 읽어 발송결과코드에 수정한다.
	 * 
	 * @param xml String
	 * @return boolean
	 */
	public boolean recptnXmlData(String xml);
}
