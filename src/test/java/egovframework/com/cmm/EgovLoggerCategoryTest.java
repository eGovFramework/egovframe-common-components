package egovframework.com.cmm;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;

import egovframework.com.uss.ion.fbk.web.EgovFacebookController;
import egovframework.com.utl.wed.filter.DefaultFileSaveManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * LOGGER 카테고리가 선언 클래스와 일치하는지 확인한다.
 * log4j2.xml 의 PatternLayout 이 %c(카테고리)를 출력하고 패키지 단위로 로그 레벨을
 * 조정하므로, 다른 클래스로 바인딩되면 로그 출처 표기와 레벨 조정이 모두 어긋난다.
 * @author 표준프레임워크센터
 * @since 2026.09.01
 * @version 1.0
 */
public class EgovLoggerCategoryTest {

	static Stream<Arguments> data() {
		return Stream.of(
			Arguments.of(EgovFacebookController.class),
			Arguments.of(DefaultFileSaveManager.class)
		);
	}

	@ParameterizedTest(name = "{index}: {0}")
	@MethodSource("data")
	void testLoggerBoundToDeclaringClass(Class<?> clazz) throws Exception {
		Field field = clazz.getDeclaredField("LOGGER");
		field.setAccessible(true);
		Logger logger = (Logger) field.get(null);
		assertEquals(clazz.getName(), logger.getName());
	}

}
