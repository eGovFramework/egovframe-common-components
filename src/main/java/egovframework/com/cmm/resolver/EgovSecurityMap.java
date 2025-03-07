package egovframework.com.cmm.resolver;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.web.EgovComUtlController;

/**
 * Map타입 적용 파라미터 복호화를 위한 EgovSecurityMap 클래스
 * 
 * @author 표준프레임워크팀 신용호
 * @since 2024.07.09
 * @version 4.3
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자        수정내용
 *  ----------     --------    ---------------------------
 *  2024.07.09     신용호        Map 타입에서 noteId 복호화 적용을 위한 EgovSecurityMap 추가
 *
 *      </pre>
 */

public class EgovSecurityMap {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSecurityMap.class);
	Map<String, String> map = new HashMap<String, String>();

	public String get(String key) {
		
		return map.get(key);
	}

	public void put(String key, String value) {
		// 특정 암호화된 파라미터 복호화 처리
		switch (key) {
			case "noteId":
			case "noteTrnsmitId":
			case "noteRecptnId":
			case "reprtId":
				LOGGER.debug("===> {} : {}",key,value);
				value = EgovComUtlController.decryptId(value);
				break;
		}
		map.put(key, value);
	}

	public String toString() {
		return map.toString();
	}

}
