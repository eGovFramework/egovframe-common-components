package egovframework.com.ext.oauth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * OAuth 로그인
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
 *   2025.06.25  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-ImmutableField(불변필드), FieldNamingConventions(필드 명명 규칙), CloseResource(리소스 닫기), UnusedPrivateMethod(사용되지 않는 개인 메서드)
 *
 *      </pre>
 */
public class OAuthLogin {
	private final OAuth20Service oauthService;
	private final OAuthVO oauthVO;

	private static final ObjectMapper MAPPER = new ObjectMapper(); // 매 요청마다 객체를 생성하지 않기 위함

	private static final Logger LOGGER = LoggerFactory.getLogger(OAuthLogin.class);

	public OAuthLogin(OAuthVO oauthVO) {
		this.oauthService = new ServiceBuilder(oauthVO.getClientId()).apiSecret(oauthVO.getClientSecret())
				.callback(oauthVO.getRedirectUrl()).build(oauthVO.getApi20Instance());

		this.oauthVO = oauthVO;
	}

	// 230825 카카오톡 scope 변경으로 인한 scope 처리 추가
	public String getOAuthURL() {
		if (oauthVO.getOrigin().equals("naver")) { // naver의 경우 state가 필수 조건에 포함
			return this.oauthService.getAuthorizationUrl() + "&state=test&scope=" + oauthVO.getScope();
		} else { // 필수, 추가 동의 항목 포함
			return this.oauthService.getAuthorizationUrl() + "&scope=" + oauthVO.getScope();
		}
	}

	public OAuthUniversalUser getUserProfile(String code) throws Exception {
		// System.out.println("===>>> oauthService.getApiKey() =
		// "+oauthService.getApiKey());
		// System.out.println("===>>> oauthService.getApiSecret() =
		// "+oauthService.getApiSecret());
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);

		OAuthRequest request = new OAuthRequest(Verb.GET, this.oauthVO.getProfileUrl());
		oauthService.signRequest(accessToken, request);

		try (Response response = oauthService.execute(request);) {
			return parseJson(response.getBody());
		}
	}

	private OAuthUniversalUser parseJson(String body) throws Exception {
		LOGGER.info("============================\n" + body + "\n==================");
		OAuthUniversalUser user = new OAuthUniversalUser();

		JsonNode rootNode = MAPPER.readTree(body);

		if (oauthVO.getOrigin().equals("google")) {
			user.setServiceName(OAuthConfig.GOOGLE_SERVICE_NAME);
			user.setUserId(rootNode.get("sub").asText());
			String uname = rootNode.get("name").asText();
			user.setUserName(uname);
//			getEmails(user, rootNode);

		} else if (oauthVO.getOrigin().equals("naver")) {
			user.setServiceName(OAuthConfig.NAVER_SERVICE_NAME);
			JsonNode resNode = rootNode.get("response");
			user.setUserId(resNode.get("id").asText());
			user.setNickName(resNode.get("nickname").asText());
			user.setEmail(resNode.get("email").asText());

		} else if (oauthVO.getOrigin().equals("kakao")) {
			user.setServiceName(OAuthConfig.KAKAO_SERVICE_NAME);
			JsonNode resNode = rootNode.get("properties");
			user.setUserId(rootNode.get("id").asText());
			user.setNickName(resNode.get("nickname").asText());
		}

		return user;
	}

//	private void getEmails(OAuthUniversalUser user, JsonNode rootNode) {
//		Iterator<JsonNode> iterEmails = rootNode.path("emails").elements();
//		while (iterEmails.hasNext()) {
//			JsonNode emailNode = iterEmails.next();
//			String type = emailNode.get("type").asText();
//			if (StringUtils.equals(type, "account")) {
//				user.setEmail(emailNode.get("value").asText());
//				break;
//			}
//		}
//	}

}
