package egovframework.com.ext.oauth.service;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.apis.KakaoApi;
import com.github.scribejava.core.builder.api.DefaultApi20;

import lombok.Getter;
import lombok.Setter;

/**
 * OAuth VO
 *
 * @author 표프센
 * @since 2020.03.11
 * @version 3.9.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.03.11  표프센          최초 생성
 *   2025.06.25  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-ImmutableField(불변필드)
 *   2026.05.27  기여자          Lombok @Getter/@Setter 적용
 *
 *      </pre>
 */
@Getter
@Setter
public class OAuthVO implements OAuthConfig {

	private String serviceName;
	private String clientId;
	private String clientSecret;
	private String redirectUrl;
	private DefaultApi20 api20Instance;
	private String profileUrl;
	private String scope;

	@Setter(lombok.AccessLevel.NONE)
	private final String origin;

	public OAuthVO(String serviceName, String clientId, String clientSecret, String redirectUrl, String scope) {
		this.serviceName = serviceName;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUrl = redirectUrl;
		this.scope = scope;
		this.origin = serviceName;

		if (origin.equals(GOOGLE_SERVICE_NAME)) {
			this.api20Instance = GoogleApi20.instance();
			this.profileUrl = GOOGLE_PROFILE_URL;
		} else if (origin.equals(NAVER_SERVICE_NAME)) {
			this.api20Instance = NaverAPI20.instance();
			this.profileUrl = NAVER_PROFILE_URL;
		} else if (origin.equals(KAKAO_SERVICE_NAME)) {
			this.api20Instance = KakaoApi.instance();
			this.profileUrl = KAKAO_PROFILE_URL;
		}
	}

}
