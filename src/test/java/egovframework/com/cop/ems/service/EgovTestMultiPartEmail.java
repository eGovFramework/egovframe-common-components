package egovframework.com.cop.ems.service;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;

/**
 * 발송메일에 첨부파일용으로 사용되는 VO 테스트 클래스
 * 
 * @author 이백행
 * @since 2024.09.28
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일      	수정자          수정내용
 *  ----------     --------    ---------------------------
 *   2024.09.28  이백행          컨트리뷰션 최초 생성
 *
 *      </pre>
 */

public class EgovTestMultiPartEmail extends EgovMultiPartEmail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4771117818240167794L;

	@Override
	public String send() throws EmailException {
		return null;
	}

	@Override
	public String send(String addTo, String subject, String msg, EmailAttachment attachment) throws EmailException {
		return null;
	}

}
