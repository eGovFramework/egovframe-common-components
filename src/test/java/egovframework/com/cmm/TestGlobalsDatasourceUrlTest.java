package egovframework.com.cmm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;

/**
 * 테스트 설정의 데이터베이스 접속 주소가 로컬을 가리키는지 확인한다.
 *
 * <p>이 파일은 테스트 클래스패스에서 같은 이름의 main 리소스를 덮어쓴다. 여기에 특정 개발 장비의
 * 주소가 남으면 로컬에 DB 를 세워도 테스트가 그 주소로 접속을 시도하고, 연결 거부가 아니라
 * TCP 타임아웃으로 끝나 원인을 찾기 어렵다.</p>
 */
class TestGlobalsDatasourceUrlTest {

	private static final String GLOBALS = "/egovframework/egovProps/globals.properties";

	@Test
	void everyDatasourceUrlPointsToLoopback() throws IOException {
		Properties globals = new Properties();
		try (InputStream in = getClass().getResourceAsStream(GLOBALS)) {
			assertTrue(in != null, GLOBALS + " 을 클래스패스에서 찾지 못했습니다.");
			globals.load(in);
		}

		List<String> urls = new ArrayList<>();
		List<String> remote = new ArrayList<>();
		for (Map.Entry<Object, Object> entry : globals.entrySet()) {
			String key = String.valueOf(entry.getKey());
			if (!key.startsWith("Globals.") || !key.endsWith(".Url")) {
				continue;
			}
			String url = String.valueOf(entry.getValue());
			urls.add(key);
			if (!url.contains("127.0.0.1") && !url.contains("localhost")) {
				remote.add(key + " = " + url);
			}
		}

		assertFalse(urls.isEmpty(), GLOBALS + " 에서 접속 주소를 찾지 못했습니다.");
		assertTrue(remote.isEmpty(), "로컬이 아닌 접속 주소가 있습니다 : " + remote);
	}
}
