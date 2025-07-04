package egovframework.com.sym.ccm.acr.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 법정동코드에 대한 서비스 구현클래스를 정의한다. 단위 테스트
 * 
 * @author 이백행
 * @since 2025.07.05
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.07.05  이백행          2025년 컨트리뷰션 최초 생성
 *
 *      </pre>
 */
@Slf4j
public class TestEgovAdministCodeRecptnServiceImpl {

	@Test
	public void testA10requestString() {
		try {
			String requestString = EgovAdministCodeRecptnServiceImpl.requestString(1, 1);

			if (log.isDebugEnabled()) {
				log.debug("requestString={}", requestString);
			}
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("IOException requestString", e);
			}
		}
	}

	@Test
	public void testA10numberOfRows() {
		try {
			int numberOfRows = EgovAdministCodeRecptnServiceImpl.numberOfRows();

			if (log.isDebugEnabled()) {
				log.debug("numberOfRows={}", numberOfRows);
			}
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("IOException numberOfRows", e);
			}
		} catch (ParseException e) {
			if (log.isErrorEnabled()) {
				log.error("ParseException numberOfRows", e);
			}
		}
	}

	@Test
	public void testA10apiLink() {
		try {
			List<HashMap<String, String>> apiLink = EgovAdministCodeRecptnServiceImpl.apiLink();

			if (log.isDebugEnabled()) {
				log.debug("apiLink={}", apiLink);
				log.debug("size={}", apiLink.size());
			}

			int i = 1;

			for (HashMap<String, String> api : apiLink) {
				if (log.isDebugEnabled()) {
					log.debug("i, regionCd, api={}, {}, {}", i, api.get("regionCd"), api);
				}

				i++;
			}
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("IOException numberOfRows", e);
			}
		} catch (ParseException e) {
			if (log.isErrorEnabled()) {
				log.error("ParseException numberOfRows", e);
			}
		}
	}

}
