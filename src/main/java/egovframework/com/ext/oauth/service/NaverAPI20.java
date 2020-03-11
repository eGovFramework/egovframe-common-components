package egovframework.com.ext.oauth.service;

import java.util.Map;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.ParameterList;

public class NaverAPI20 extends DefaultApi20 implements OAuthConfig {
	private NaverAPI20() {
	}
	
	private static class InstanceHolder {
		private static final NaverAPI20 INSTANCE = new NaverAPI20();
	}
	
	public static NaverAPI20 instance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return NAVER_ACCESS_TOKEN;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return NAVER_AUTH;
	}
	
    public String getAuthorizationUrl(String responseType, String apiKey, String callback, String scope, String state,
            Map<String, String> additionalParams) {
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
