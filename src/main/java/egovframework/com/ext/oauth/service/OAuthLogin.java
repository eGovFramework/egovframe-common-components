package egovframework.com.ext.oauth.service;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;


public class OAuthLogin {
	private OAuth20Service oauthService;
	private OAuthVO oauthVO;
	
	public OAuthLogin(OAuthVO oauthVO) {
		this.oauthService = new ServiceBuilder(oauthVO.getClientId())
				.apiSecret(oauthVO.getClientSecret())
				.callback(oauthVO.getRedirectUrl())
				.scope("profile")
				.build(oauthVO.getApi20Instance());
		
		this.oauthVO = oauthVO;
	}
	
	public String getOAuthURL() {
		return this.oauthService.getAuthorizationUrl();
	}

	public OAuthUniversalUser getUserProfile(String code) throws Exception {
		//System.out.println("===>>> oauthService.getApiKey() = "+oauthService.getApiKey());
		//System.out.println("===>>> oauthService.getApiSecret() = "+oauthService.getApiSecret());
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
		
		OAuthRequest request = new OAuthRequest(Verb.GET, this.oauthVO.getProfileUrl());
		oauthService.signRequest(accessToken, request);
		
		Response response = oauthService.execute(request);
		return parseJson(response.getBody());
	}

	private OAuthUniversalUser parseJson(String body) throws Exception {
		//System.out.println("============================\n" + body + "\n==================");
		OAuthUniversalUser user = new OAuthUniversalUser();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(body);
		
		if (this.oauthVO.isGoogle()) {
			String id = rootNode.get("id").asText();
			user.setServiceName(OAuthConfig.GOOGLE_SERVICE_NAME);
			if (oauthVO.isGoogle())
				user.setUserId(id);
			user.setNickName(rootNode.get("displayName").asText());
			JsonNode nameNode = rootNode.path("name");
			String uname = nameNode.get("familyName").asText() + nameNode.get("givenName").asText();
			user.setUserName(uname);

			Iterator<JsonNode> iterEmails = rootNode.path("emails").elements();
			while(iterEmails.hasNext()) {
				JsonNode emailNode = iterEmails.next();
				String type = emailNode.get("type").asText();
				if (StringUtils.equals(type, "account")) {
					user.setEmail(emailNode.get("value").asText());
					break;
				}
			}
			
		} else if (this.oauthVO.isNaver()) {
			user.setServiceName(OAuthConfig.NAVER_SERVICE_NAME);
			JsonNode resNode = rootNode.get("response");
			user.setUserId(resNode.get("id").asText());
			user.setNickName(resNode.get("nickname").asText());
			user.setEmail(resNode.get("email").asText());
			
		} else if (this.oauthVO.isKakao()) {
			user.setServiceName(OAuthConfig.KAKAO_SERVICE_NAME);
			JsonNode resNode = rootNode.get("properties");
			user.setUserId(rootNode.get("id").asText());
			user.setNickName(resNode.get("nickname").asText());
		}
		
		return user;
	}
	
}
