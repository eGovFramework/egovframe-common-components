package egovframework.com.validation.ipaddress;

import egovframework.com.cmm.EgovWebUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * IP Address Check Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2020.12.07
 * @version 3.10
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *   2020.12.07  신용호          최초 생성
 *   2026.07.11  이백행          [2026년 컨트리뷰션] 디버그 출력에 log.debug 적용
 *
 * # Java Lang - IP Address 관련 자리수를 테스트한다.
 * </pre>
 */

@Slf4j
public class IpAddressTest {

	public static void main(String[] args) {
		
		// 개선사항 : 0~255까지의 숫자 범위를 체크 하지는 못함.
		String addr[] = {"127.0.0.1", "235.0.0.88", "aa.bb.cc.dd", "257.256.256.256"};
		
		for (int i = 0 ; i < addr.length; i++) {
			log.debug("{}", EgovWebUtil.isIPAddress(addr[i]));
		}
	}

}
