package egovframework.com.idgen;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ID Generation Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.03.05
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일        수정자      수정내용
 *  ----------  --------  ---------------------------
 *  2024.10.16  신용호      최초 생성
 *
 * # 테스트시 Run As > Run Configuration 메뉴 선택후
 *   Arguments 탭에서 VM Arguments 항목에 -Dspring.profiles.active=mysql 추가한다.
 * </pre>
 */

public class TestIDGenDev {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// ApplicationContext (BeanFactory 상속 받음)
		String[] configLocation = new String[]{"classpath*:egovframework/spring/com/context-*.xml"
											,"classpath*:egovframework/spring/com/idgn/context-*.xml"};

		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		EgovIdGnrService idgenService1 = (EgovIdGnrService)context.getBean("tableIdGnrService");

		try {
			String result = idgenService1.getNextStringId();
			System.out.println("=====>>>>> Result Next ID = "+result);
			System.out.println("=====>>>>> Result Next ID = "+idgenService1.getNextStringId());
			System.out.println("=====>>>>> Result Next ID = "+idgenService1.getNextStringId());
		} catch (FdlException e) {
			e.printStackTrace();
		}
		
		EgovIdGnrService idgenService2 = (EgovIdGnrService)context.getBean("sequenceIdGnrService");
		try {
			System.out.println("=====>>>>> Result Next String ID (Sequence) = "+idgenService2.getNextStringId());
		} catch (FdlException e) {
			e.printStackTrace();
		}
		
		EgovIdGnrService idgenService3 = (EgovIdGnrService)context.getBean("uuidIdGnrService");
		try {
			System.out.println("=====>>>>> Result Next String ID (UUID) = "+idgenService3.getNextStringId());
		} catch (FdlException e) {
			e.printStackTrace();
		}
		
	}

}
