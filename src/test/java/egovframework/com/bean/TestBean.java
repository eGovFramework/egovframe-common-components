package egovframework.com.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * XML 기반 빈설정에서 SpEL 표현식 사용하여 메소드 호출
 * 
 * @author 표준프레임워크 신용호
 * @since 2019.01.03
 * @version 4.0
 * @see
 * 
 *      <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2022.08.29  신용호          최초 생성
 *
 *      </pre>
 */

public class TestBean {

	/** 로그설정 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TestBean.class);
	private static MyBean myBean;
	private static MyBean myBean1;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:egovframework/spring/com/test-context-datetime.xml");

		myBean = context.getBean(MyBean.class);
		LOGGER.debug("currentTimeMills={}", myBean.getCurrentTimeMills());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException");
		}

		myBean1 = context.getBean(MyBean.class);
		LOGGER.debug("currentTimeMills={}", myBean1.getCurrentTimeMills());

		((ClassPathXmlApplicationContext) context).close();
	}

}