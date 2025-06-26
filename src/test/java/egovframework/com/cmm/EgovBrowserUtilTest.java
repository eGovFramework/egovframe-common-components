package egovframework.com.cmm;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EgovBrowserUtilTest{


	private final String userAgent;
	private final String expectedType;
	private final String expectedVersion;

	public EgovBrowserUtilTest(String userAgent, String expectedType, String expectedVersion) {
		this.userAgent = userAgent;
		this.expectedType = expectedType;
		this.expectedVersion = expectedVersion;
	}

	@Parameterized.Parameters(name = "{index}: {1} {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
			{"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)", "MSIE", "10.0"},
			{"Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko", "MSIE", "11.0"},
			{"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Edge/12.10240", "Edge", "12.10240"},
			{"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:85.0) Gecko/20100101 Firefox/85.0", "Firefox", "85.0"},
			{"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 OPR/76.0.4017.123", "Opera", "76.0"},
			{"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/91.0.4472.124 Safari/537.36", "Chrome", "91.0"},
			{"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 Version/14.0.3 Safari/605.1.15", "Safari", "14.0"},
			{"Mozilla/5.0 (...) Whale/3.19.166.21 Chrome/92.0.4515.131 Safari/537.36", "Whale", "3.19"},
			{"SomeRandomBrowser/1.2.3", "Other", "0.0"}
		});
	}

	@Test
	public void testGetBrowser() {
		HashMap<String, String> result = EgovBrowserUtil.getBrowser(userAgent);
		assertEquals(expectedType, result.get("type"));
		assertEquals(expectedVersion, result.get("version"));
	}

}