package egovframework.com.auth.session.config;

import org.egovframe.rte.fdl.access.config.EgovAccessConfig;
import org.egovframe.rte.fdl.access.config.EgovAccessConfigReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Properties;

/**
 * 테스트 시 globals.properties의 Globals.AccessConfigPath 값을 읽어
 * 해당 경로의 프로퍼티로 EgovAccessConfig 빈을 생성한다.
 * (globals.properties → AccessConfigPath → 해당 경로 설정 파일 로드)
 * EgovAccessConfiguration의 egovAccessConfig 대신 이 빈이 사용되도록 @Primary 적용.
 *
 * <p>리소스는 {@code classpath:egovframework/session/globals.properties} 를 사용한다.</p>
 *
 * @author 유지보수
 * @version 5.0
 * @since 2025.06.01
 */
@Configuration
public class EgovAccessTestConfig {

    private static final String GLOBALS_PROPERTIES = "egovframework/session/globals.properties";

    @Bean
    @Primary
    public EgovAccessConfig egovAccessConfig(ApplicationContext applicationContext) throws Exception {
        Properties globals = PropertiesLoaderUtils.loadProperties(new ClassPathResource(GLOBALS_PROPERTIES));
        String accessConfigPath = globals.getProperty("Globals.AccessConfigPath");
        if (accessConfigPath == null || accessConfigPath.isEmpty()) {
            accessConfigPath = "classpath:egovframework/session/egov-access-config.properties";
        } else if (!accessConfigPath.startsWith("classpath:") && !accessConfigPath.startsWith("file:")) {
            accessConfigPath = "classpath:" + accessConfigPath.trim();
        }
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = applicationContext.getClassLoader();
        }
        ResourceLoader resourceLoader = new DefaultResourceLoader(cl);
        EgovAccessConfigReader reader = new EgovAccessConfigReader(accessConfigPath.trim(), resourceLoader);
        return reader.readConfig();
    }

}
