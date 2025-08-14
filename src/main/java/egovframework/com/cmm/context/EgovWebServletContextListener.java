package egovframework.com.cmm.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import egovframework.com.cmm.service.EgovProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * 데이터베이스 설정을 spring.profiles.active 방식으로 처리
 * <p>
 * (공통컴포넌트 특성상 데이터베이스별 분리/개발,검증,운영서버로 분리 가능)
 * 
 * @author 장동한
 * @since 2016.06.23
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  홍길동          최초 생성
 *   2016.06.23  장동한          최초 생성
 *   2017.03.03  조성원          시큐어코딩(ES)-오류 메시지를 통한 정보노출[CWE-209]
 *   2025.07.12  이백행          2025년 컨트리뷰션 `-Dspring.profiles.active=mysql-hikari,security` 동작하지 않아 리팩토링
 *
 *      </pre>
 */
@Slf4j
public class EgovWebServletContextListener implements ServletContextListener {

	public EgovWebServletContextListener() {
		setEgovProfileSetting();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		setEgovProfileSetting();
	}

	private void setEgovProfileSetting() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("===========================Start EgovServletContextLoad START ===========");
				log.debug("Setting spring.profiles.active>" + System.getProperty("spring.profiles.active"));
			}

			if (System.getProperty("spring.profiles.active") == null) {
				System.setProperty("spring.profiles.active", EgovProperties.getProperty("Globals.DbType") + ","
						+ EgovProperties.getProperty("Globals.Auth"));
			}

			if (log.isDebugEnabled()) {
				log.debug("Setting spring.profiles.active>" + System.getProperty("spring.profiles.active"));
				log.debug("===========================END   EgovServletContextLoad END ===========");
			}
			// 2017.03.03 조성원 시큐어코딩(ES)-오류 메시지를 통한 정보노출[CWE-209]
		} catch (IllegalArgumentException e) {
			if (log.isErrorEnabled()) {
				log.error("[IllegalArgumentException] Try/Catch...usingParameters Runing", e);
			}
		} catch (RuntimeException e) {
			if (log.isErrorEnabled()) {
				log.error("[" + e.getClass() + "] search fail", e);
			}
		}
	}

}
