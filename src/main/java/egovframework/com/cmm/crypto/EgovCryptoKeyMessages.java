package egovframework.com.cmm.crypto;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 암호화 키 초기화 도구(init-crypto-key)의 다국어 메시지
 *
 * <p>애플리케이션 메시지 소스가 읽는 파일과 같은 egovframework/message/com/cmm/crypto/message-cryptokey_{ko,en}.properties 를
 * java.util.ResourceBundle 로 읽는다. 도구는 스프링 컨텍스트 없이 실행되므로 애플리케이션 메시지 소스를 쓸 수 없다.
 * 언어가 한국어면 ko, 그 밖에는 en 을 쓴다.</p>
 *
 * @author 개발팀
 * @since 2026.10.02
 * @version 1.0
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.10.02  개발팀         최초 생성
 *
 *      </pre>
 */
final class EgovCryptoKeyMessages {

	/** 메시지 파일 기준 이름(애플리케이션 메시지 소스와 같은 파일) */
	static final String BASENAME = "egovframework/message/com/cmm/crypto/message-cryptokey";

	private final ResourceBundle bundle;

	private final Locale locale;

	private EgovCryptoKeyMessages(Locale locale) {
		this.locale = locale;
		this.bundle = ResourceBundle.getBundle(BASENAME, locale,
				ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES));
	}

	/**
	 * 언어에 맞는 메시지를 만든다.
	 *
	 * @param locale 원하는 언어(null 이면 OS 기본 언어)
	 * @return 한국어면 ko, 그 밖에는 en 메시지
	 */
	static EgovCryptoKeyMessages of(Locale locale) {
		Locale requested = (locale != null) ? locale : Locale.getDefault();
		return new EgovCryptoKeyMessages("ko".equals(requested.getLanguage()) ? Locale.KOREAN : Locale.ENGLISH);
	}

	Locale locale() {
		return locale;
	}

	/**
	 * 메시지를 꺼낸다. 인자가 있으면 MessageFormat 으로 채운다.
	 *
	 * @param code 메시지 키
	 * @param args 인자
	 * @return 메시지(키가 없으면 키 자체)
	 */
	String get(String code, Object... args) {
		String pattern;
		try {
			pattern = bundle.getString(code);
		} catch (MissingResourceException e) {
			return code;
		}
		return (args == null || args.length == 0) ? pattern : new MessageFormat(pattern, locale).format(args);
	}

	/**
	 * 키 규칙 위반을 메시지로 바꾼다.
	 *
	 * @param violation 위반
	 * @return 메시지
	 */
	String get(EgovCryptoKeyPolicy.Violation violation) {
		return get(violation.getCode(), violation.getArgs());
	}
}
