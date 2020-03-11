package egovframework.com.ext.oauth.service;

import org.apache.commons.lang3.StringUtils;

import com.github.scribejava.apis.GoogleApi20;
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

	public boolean isGoogle() {
		return isGoogle;
	}

	public boolean isNaver() {
		return isNaver;
	}

	public boolean isKakao() {
		return isKakao;
	}
	
	public void setGoogle(boolean isGoogle) {
		this.isGoogle = isGoogle;
	}

	public void setNaver(boolean isNaver) {
		this.isNaver = isNaver;
	}

	public void setKakao(boolean isKakao) {
		this.isKakao = isKakao;
	}

	private String serviceName;
	private String clientId;
	private String clientSecret;
	private String redirectUrl;
	private DefaultApi20 api20Instance;
	private String profileUrl;
	
	private boolean isNaver;
	private boolean isGoogle;
	private boolean isKakao;
	
	public OAuthVO(String serviceName, String clientId, String clientSecret, String redirectUrl) {
		this.serviceName = serviceName;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUrl = redirectUrl;
		
		this.isGoogle = StringUtils.equalsIgnoreCase(GOOGLE_SERVICE_NAME, serviceName);
		this.isNaver = StringUtils.equalsIgnoreCase(NAVER_SERVICE_NAME, serviceName);
		this.isKakao = StringUtils.equalsIgnoreCase(KAKAO_SERVICE_NAME, serviceName);
		
		if (isGoogle) {
			this.api20Instance = GoogleApi20.instance();
			this.profileUrl = GOOGLE_PROFILE_URL;
		} else if (isNaver) {
			this.api20Instance = NaverAPI20.instance();
			this.profileUrl = NAVER_PROFILE_URL;
		} else if (isKakao) {
			this.api20Instance = KakaoAPI20.instance();
			this.profileUrl = KAKAO_PROFILE_URL;
		}
	}

}
