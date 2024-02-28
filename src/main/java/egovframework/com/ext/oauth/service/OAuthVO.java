package egovframework.com.ext.oauth.service;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.apis.KakaoApi;
import com.github.scribejava.core.builder.api.DefaultApi20;

public class OAuthVO implements OAuthConfig {
	public String getService() {
		return serviceName;
	}

	public void setService(String service) {
		this.serviceName = service;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public DefaultApi20 getApi20Instance() {
		return api20Instance;
	}

	public void setApi20Instance(DefaultApi20 api20Instance) {
		this.api20Instance = api20Instance;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getOrigin() {
		return origin;
	}
	
	// scope 추가
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	private String serviceName;
	private String clientId;
	private String clientSecret;
	private String redirectUrl;
	private DefaultApi20 api20Instance;
	private String profileUrl;
	private String scope;
	
	private String origin;
	
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
