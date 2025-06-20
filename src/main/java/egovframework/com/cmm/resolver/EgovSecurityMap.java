package egovframework.com.cmm.resolver;

import java.util.HashMap;
import java.util.Map;

import egovframework.com.cmm.web.EgovComUtlController;
import lombok.extern.slf4j.Slf4j;

/**
 * Map타입 적용 파라미터 복호화를 위한 EgovSecurityMap 클래스
 * 
 * @author 표준프레임워크팀 신용호
 * @since 2024.07.09
 * @version 4.3.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2024.07.09  신용호          Map 타입에서 noteId 복호화 적용을 위한 EgovSecurityMap 추가
 *   2025.05.24  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-SwitchStmtsShouldHaveDefault(스위치 명령문에는 기본값이 있어야 합니다.), AvoidReassigningParameters(매개변수 재할당 방지)
 *
 *      </pre>
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자        수정내용
 *  ----------     --------    ---------------------------
 *
 * 
 *      </pre>
 */
@Slf4j
public class EgovSecurityMap {

	Map<String, String> map = new HashMap<>();

	public String get(String key) {
		return map.get(key);
	}

	public void put(String key, String value) {
		String value2 = value;

		// 특정 암호화된 파라미터 복호화 처리
		switch (key) {
		case "noteId":

		case "noteTrnsmitId":

		case "noteRecptnId":

		case "reprtId":
			log.debug("===> {} : {}", key, value);
			value2 = EgovComUtlController.decryptId(value);
			break;

		default:
			break;
		}

		map.put(key, value2);
	}

	@Override
	public String toString() {
		return map.toString();
	}

}
