package egovframework.com.cmm.crypto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.egovframe.rte.fdl.crypto.config.EgovCryptoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * 암호화 키 기동 가드
 *
 * <p>egov-crypto-config.properties 의 키가 배포 기본값이거나 키와 해시가 맞지 않으면 "안전하지 않음" 으로 판정한다.
 * 기본 키는 공개 저장소에 노출된 값이라, 그대로 운영하면 DB 비밀번호 등 이 키로 암호화한 값을 누구나 풀 수 있다.
 * 실행환경 암호화 서비스는 기본 키를 경고만 하므로 공통컴포넌트에서 막는다.</p>
 *
 * <p>애플리케이션 기동은 막지 않는다. 판정 결과를 로그에 남기고, {@link EgovCryptoKeyGuardFilter} 가 키를 초기화하기 전까지
 * 모든 요청에 안내 페이지를 응답한다 - 사전 지식이 없는 개발자도 브라우저에서 이유와 조치 방법을 볼 수 있게 하기 위해서다.</p>
 *
 * <p>안전하지 않음 조건</p>
 * <ul>
 *   <li>algorithmKey 가 배포 기본 키다(설정 파일을 찾지 못해 실행환경이 기본 설정으로 대체한 경우 포함)</li>
 *   <li>algorithmKeyHash 가 배포 기본 키의 해시다(키만 바꾸고 해시를 그대로 둔 경우)</li>
 *   <li>algorithmKey 와 algorithmKeyHash 가 서로 맞지 않는다(실행환경은 첫 암·복호화 때에야 실패한다)</li>
 * </ul>
 *
 * <p>조치: 프로젝트 루트의 init-crypto-key.sh(Windows: init-crypto-key.bat)를 실행한다. 우회 설정은 두지 않는다.
 * 메시지는 message-cryptokey_{ko,en}.properties(comCmm.cryptoKey.*)에서 꺼낸다.</p>
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
public class EgovCryptoKeyGuard implements InitializingBean, MessageSourceAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovCryptoKeyGuard.class);

	/** 문제 메시지 키 */
	static final String PROBLEM_NO_CONFIG = "comCmm.cryptoKey.problem.noConfig";
	static final String PROBLEM_DEFAULT_KEY = "comCmm.cryptoKey.problem.defaultKey";
	static final String PROBLEM_DEFAULT_KEY_HASH = "comCmm.cryptoKey.problem.defaultKeyHash";
	static final String PROBLEM_MISMATCH = "comCmm.cryptoKey.problem.mismatch";

	private final EgovCryptoConfig cryptoConfig;

	private final String cryptoConfigPath;

	private MessageSource messageSource;

	private List<String> problems = Collections.emptyList();

	/**
	 * @param cryptoConfig 실행환경이 읽은 암호화 설정
	 * @param cryptoConfigPath 설정 파일 경로(Globals.CryptoConfigPath - 안내에만 쓴다)
	 */
	public EgovCryptoKeyGuard(EgovCryptoConfig cryptoConfig, String cryptoConfigPath) {
		this.cryptoConfig = cryptoConfig;
		this.cryptoConfigPath = cryptoConfigPath;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public void afterPropertiesSet() {
		problems = Collections.unmodifiableList(inspect(cryptoConfig));
		if (problems.isEmpty()) {
			LOGGER.debug("Crypto key check passed.");
		} else {
			LOGGER.error(describe(Locale.getDefault()));
		}
	}

	/** @return 키 설정이 안전하면 true */
	public boolean isSecure() {
		return problems.isEmpty();
	}

	/** @return 문제 메시지 키 목록(안전하면 빈 목록) */
	public List<String> getProblems() {
		return problems;
	}

	/** @return 설정 파일 경로(Globals.CryptoConfigPath, 지정하지 않았으면 빈 값) */
	public String getCryptoConfigPath() {
		return cryptoConfigPath;
	}

	/**
	 * 설정의 문제를 찾는다.
	 *
	 * @param config 암호화 설정
	 * @return 문제 메시지 키 목록(없으면 빈 목록)
	 */
	static List<String> inspect(EgovCryptoConfig config) {
		List<String> found = new ArrayList<>();
		if (config == null) {
			found.add(PROBLEM_NO_CONFIG);
			return found;
		}
		String key = config.getAlgorithmKey();
		String keyHash = config.getAlgorithmKeyHash();
		if (EgovCryptoKeyPolicy.isDefaultKey(key)) {
			found.add(PROBLEM_DEFAULT_KEY);
		}
		if (EgovCryptoKeyPolicy.isDefaultKeyHash(keyHash)) {
			found.add(PROBLEM_DEFAULT_KEY_HASH);
		}
		if (found.isEmpty() && !EgovCryptoKeyPolicy.matches(key, keyHash, config.getAlgorithm())) {
			found.add(PROBLEM_MISMATCH);
		}
		return found;
	}

	/**
	 * 문제·설정 파일·조치 방법을 한 덩어리 문장으로 만든다(로그용).
	 *
	 * @param locale 언어
	 * @return 안내 문장
	 */
	String describe(Locale locale) {
		String nl = System.lineSeparator();
		StringBuilder sb = new StringBuilder(512).append(message("comCmm.cryptoKey.guard.log.title", locale));
		for (String problem : problems) {
			sb.append(nl).append("  - ").append(message(problem, locale));
		}
		sb.append(nl).append("  ").append(message("comCmm.cryptoKey.configPath", locale, displayConfigPath(locale)));
		sb.append(nl).append("  ").append(message("comCmm.cryptoKey.action", locale));
		sb.append(nl).append("  ").append(message("comCmm.cryptoKey.guide", locale));
		return sb.toString();
	}

	/**
	 * 화면·로그에 보일 설정 파일 경로.
	 *
	 * @param locale 언어
	 * @return 경로, 지정하지 않았으면 "실행환경 기본 경로" 안내
	 */
	String displayConfigPath(Locale locale) {
		return (cryptoConfigPath == null || cryptoConfigPath.isBlank())
				? message("comCmm.cryptoKey.configPath.default", locale) : cryptoConfigPath;
	}

	/**
	 * 메시지를 꺼낸다. 메시지 소스가 없으면(단위 시험 등) 도구용 메시지 파일에서 꺼낸다.
	 */
	String message(String code, Locale locale, Object... args) {
		if (messageSource != null) {
			return messageSource.getMessage(code, args, code, locale);
		}
		return EgovCryptoKeyMessages.of(locale).get(code, args);
	}
}
