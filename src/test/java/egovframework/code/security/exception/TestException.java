package egovframework.code.security.exception;

import egovframework.com.cmm.service.EgovProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * 시스템 프로퍼티와 전자정부 표준프레임워크 프로퍼티의 연계 동작을 확인한다.
 *
 * <p>{@code my.active} 시스템 프로퍼티를 직접 설정한 경우와
 * {@link EgovProperties}에서 조회한 값으로 설정한 경우를 로그로 비교한다.</p>
 * 
 * @author 표준프레임워크 신용호
 * @since 2022.11.11
 * @version 4.0
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2022.11.11  신용호          최초 생성
 *
   
 * </pre>
 */
@Slf4j
public class TestException {

	/**
	 * 시스템 프로퍼티의 초기값과 변경된 값을 순서대로 출력한다.
	 *
	 * @param args 명령행 인수(사용하지 않음)
	 */
	public static void main(String[] args) {
		// 시스템 프로퍼티를 설정하기 전의 값을 확인한다.
		log.debug("my.active={}", System.getProperty("my.active"));

		// 시스템 프로퍼티를 직접 설정한 후 변경 결과를 확인한다.
		System.setProperty("my.active", "OK");

		log.debug("my.active={}", System.getProperty("my.active"));

		// 프레임워크 프로퍼티 값을 조회해 시스템 프로퍼티에 반영한다.
		String egovNoneId = EgovProperties.getProperty("egov.none.id");

		log.debug("egov.none.id={}", egovNoneId);

		System.setProperty("my.active", egovNoneId);

		log.debug("my.active={}", System.getProperty("my.active"));
	}

}
