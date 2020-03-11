package egovframework.com.ext.oauth.service;

import java.util.Map;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.ParameterList;

public class KakaoAPI20 extends DefaultApi20 implements OAuthConfig {
	
	private String apiKey = "";
	private KakaoAPI20() {
	}
	
	private static class InstanceHolder {
		private static final KakaoAPI20 INSTANCE = new KakaoAPI20();
	}
	
	public static KakaoAPI20 instance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return KAKAO_ACCESS_TOKEN+apiKey;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return KAKAO_AUTH;
	}
	
    public String getAuthorizationUrl(String responseType, String apiKey, String callback, String scope, String state,
            Map<String, String> additionalParams) {
    	this.apiKey = apiKey;
        final ParameterList parameters = new ParameterList(additionalParams);
        parameters.add(OAuthConstants.RESPONSE_TYPE, responseType);
        parameters.add(OAuthConstants.CLIENT_ID, apiKey);

        if (callback != null) {
            parameters.add(OAuthConstants.REDIRECT_URI, callback);
        }

        if (scope != null) {
            parameters.add(OAuthConstants.SCOPE, scope);
        }

        if (state != null) {
            parameters.add(OAuthConstants.STATE, state);
        }
        //System.out.println("===>>> "+parameters.appendTo(""));
        return parameters.appendTo(getAuthorizationBaseUrl());
    }

}
