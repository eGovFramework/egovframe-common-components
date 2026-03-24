package egovframework.com.crypto.config;

import org.egovframe.rte.fdl.crypto.EgovCryptoService;
import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
import org.egovframe.rte.fdl.crypto.config.EgovCryptoConfig;
import org.egovframe.rte.fdl.crypto.config.EgovCryptoConfigReader;
import org.egovframe.rte.fdl.crypto.config.EgovCryptoConfiguration;
import org.egovframe.rte.fdl.crypto.impl.EgovEnvCryptoServiceImpl;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.fdl.property.impl.EgovPropertyServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Test Crypto Java Configuration 클래스
 * <p>
 * 테스트 시 globals.properties를 직접 읽어 Globals.CryptoConfigPath 값을 사용하고,
 * 해당 경로의 프로퍼티로 EgovCryptoConfig 빈을 생성한다.
 * (globals.properties → CryptoConfigPath → 해당 경로 설정 파일 로드)
 * EgovCryptoConfiguration의 egovCryptoConfig 대신 이 빈이 사용되도록 @Primary 적용.
 * </p>
 * <p>
 * 리소스: {@code classpath:egovframework/crypto/globals.properties}
 * </p>
 *
 * @author 유지보수
 * @version 5.0
 * @since 2025.06.01
 */
@Configuration
@Import(EgovCryptoConfiguration.class)
public class EgovCryptoTestConfig {

    private static final String GLOBALS_PROPERTIES = "egovframework/crypto/globals.properties";

    @Bean
    @Primary
    public EgovCryptoConfig egovCryptoConfig(ApplicationContext applicationContext) throws Exception {
        Properties globals = PropertiesLoaderUtils.loadProperties(new ClassPathResource(GLOBALS_PROPERTIES));
        String cryptoConfigPath = globals.getProperty("Globals.CryptoConfigPath");
        if (cryptoConfigPath == null || cryptoConfigPath.isEmpty()) {
            cryptoConfigPath = "classpath:egovframework/crypto/egov-crypto-config.properties";
        } else if (!cryptoConfigPath.startsWith("classpath:") && !cryptoConfigPath.startsWith("file:")) {
            cryptoConfigPath = "classpath:" + cryptoConfigPath.trim();
        } else {
            cryptoConfigPath = cryptoConfigPath.trim();
        }
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = applicationContext.getClassLoader();
        }
        ResourceLoader resourceLoader = new DefaultResourceLoader(cl);
        EgovCryptoConfigReader reader = new EgovCryptoConfigReader(cryptoConfigPath, resourceLoader);
        return reader.readConfig();
    }

    @Bean(name = "egovPropertyConfigurer")
    public EgovPropertyService egovPropertyConfigurer(EgovCryptoConfig cryptoConfig) {
        EgovPropertyServiceImpl service = new EgovPropertyServiceImpl();
        Set<Map<String, String>> extFileName = Set.of(
                Map.of("encoding", "UTF-8", "filename", cryptoConfig.getCryptoPropertyLocation())
        );
        service.setExtFileName(extFileName);
        return service;
    }

    @Bean
    public EgovEnvCryptoServiceInitializer egovEnvCryptoServiceInitializer(
            EgovEnvCryptoServiceImpl egovEnvCryptoService,
            @Qualifier("egovPropertyConfigurer") EgovPropertyService egovPropertyConfigurer) {
        return new EgovEnvCryptoServiceInitializer(egovEnvCryptoService, egovPropertyConfigurer);
    }

    @Bean(name = "cryptoConfig")
    public EgovCryptoConfig cryptoConfig(EgovCryptoConfig egovCryptoConfig) {
        return egovCryptoConfig;
    }

    @Bean(name = "egov.envCryptoService")
    public EgovEnvCryptoService egovEnvCryptoServiceAlias(EgovEnvCryptoServiceImpl egovEnvCryptoService) {
        return egovEnvCryptoService;
    }

    @Bean(name = "egov.ariaCryptoService")
    public EgovCryptoService egovARIACryptoServiceAlias(
            org.egovframe.rte.fdl.crypto.impl.EgovARIACryptoServiceImpl egovARIACryptoService) {
        return egovARIACryptoService;
    }

    @Bean(name = "egov.digestService")
    public org.egovframe.rte.fdl.crypto.EgovDigestService egovDigestServiceAlias(
            org.egovframe.rte.fdl.crypto.impl.EgovDigestServiceImpl egovDigestService) {
        return egovDigestService;
    }

    @Bean(name = "generalCryptoService")
    @Primary
    public org.egovframe.rte.fdl.crypto.impl.EgovGeneralCryptoServiceImpl generalCryptoServiceForTest(
            org.egovframe.rte.fdl.crypto.impl.EgovGeneralCryptoServiceImpl egovGeneralCryptoService) {
        egovGeneralCryptoService.setAlgorithm("PBEWithSHA1AndDESede");
        return egovGeneralCryptoService;
    }

    @Bean(name = "password")
    public String password(EgovCryptoConfig cryptoConfig) {
        return cryptoConfig.getAlgorithmKey();
    }

    private static final class EgovEnvCryptoServiceInitializer implements InitializingBean {
        private final EgovEnvCryptoServiceImpl service;
        private final EgovPropertyService propertyConfigurer;

        EgovEnvCryptoServiceInitializer(EgovEnvCryptoServiceImpl service, EgovPropertyService propertyConfigurer) {
            this.service = service;
            this.propertyConfigurer = propertyConfigurer;
        }

        @Override
        public void afterPropertiesSet() {
            service.setCryptoConfigurer(propertyConfigurer);
            service.init();
        }
    }
}
