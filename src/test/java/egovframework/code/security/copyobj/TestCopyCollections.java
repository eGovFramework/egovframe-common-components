package egovframework.code.security.copyobj;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * TestCopyCollections Test Class 구현
 * <p>
 * Catch a list of specific exception subtypes instead.
 * <p>
 * 대신 특정 예외 하위 유형 목록을 잡아보세요.
 * 
 * @author 표준프레임워크 신용호
 * @since 2022.11.11
 * @version 4.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2022.11.11  신용호          최초 생성
 *   2025.07.10  이백행          2025년 컨트리뷰션 세미나 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidPrintStackTrace(오류 메시지를 통한 정보 노출)
 *
 *      </pre>
 */
@Slf4j
public class TestCopyCollections {

	public static void main(String[] args) {

		// JDK2
		// List<String> supplierNames = Arrays.asList("sup1", "sup2", "sup3");
		// JDK8
		List<String> supplierNames = Stream.of("sup1", "sup2", "sup3").collect(Collectors.toList());

		// List<String> copyNames = new ArrayList<>();
		List<String> copyNames;
		copyNames = Collections.unmodifiableList(supplierNames);
		// copyNames.addAll(supplierNames);
		// supplierNames.set(0,"sup1-edit");

		try {
			copyNames.set(0, "copy-edit");
		} catch (UnsupportedOperationException e) {
			if (log.isErrorEnabled()) {
				log.error("UnsupportedOperationException", e);
				log.error("copyNames.set(0, \"copy-edit\");");
				log.error("UnsupportedOperationExceptionm, 지원되지 않는 작업 예외");
				log.error("unmodifiableList, 수정 불가능한 목록");
			}
		}

		if (log.isDebugEnabled()) {
			log.debug(supplierNames.get(0));
			log.debug("{}", supplierNames.hashCode());
			log.debug(copyNames.get(0));
			log.debug("{}", copyNames.hashCode());
		}

	}

}