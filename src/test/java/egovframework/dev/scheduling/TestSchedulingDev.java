package egovframework.dev.scheduling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Scheduling Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.03.05
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일        수정자      수정내용
 *  ----------  --------  ---------------------------
 *  2024.10.18  신용호      최초 생성
 *
 * </pre>
 */

public class TestSchedulingDev {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// ApplicationContext (BeanFactory 상속 받음)
		String[] configLocation = new String[]{"classpath*:egovframework/spring/dev/scheduling/context-*.xml"};

		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);

	}

}
