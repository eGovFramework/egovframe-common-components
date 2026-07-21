package egovframework.com.idgen;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * ID Generation Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.03.05
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *   2019.03.05  신용호          최초 생성
 *   2026.07.11  이백행          [2026년 컨트리뷰션] 디버그 출력에 log.debug 적용
 *
 * # 테스트시 Run As > Run Configuration 메뉴 선택후
 *   Arguments 탭에서 VM Arguments 항목에 -Dspring.profiles.active=mysql 추가한다.
 * </pre>
 */
@Slf4j
public class TestIDGen {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// ApplicationContext (BeanFactory 상속 받음)
		String[] configLocation = new String[]{"classpath*:egovframework/spring/com/context-*.xml"
											,"classpath*:egovframework/spring/com/idgn/context-*.xml"};

		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		//EgovIdGnrService idgenService = (EgovIdGnrService)context.getBean("egovAdbkIdGnrService");
		EgovIdGnrService idgenService = (EgovIdGnrService)context.getBean("testTableIdGnrService");

		try {
			String result = idgenService.getNextStringId();
			log.debug("=====>>>>> Result Next ID = {}", result);
			log.debug("=====>>>>> Result Next ID = {}", idgenService.getNextStringId());
			log.debug("=====>>>>> Result Next ID = {}", idgenService.getNextStringId());
			//log.debug("=====>>>>> Result Next ID = {}", idgenService.getNextIntegerId());
			//log.debug("=====>>>>> Result Next ID = {}", idgenService.getNextIntegerId());
			//log.debug("=====>>>>> Result Next ID = {}", idgenService.getNextIntegerId());
			//log.debug("=====>>>>> Result Next ID = {}", idgenService.getNextBigDecimalId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}
		
		//BeanFactory
		//Resource r = new FileSystemResource("/WEB-INF/applicationContext_board.xml");
		//BeanFactory f = new XmlBeanFactory(r);
	}

}
