package egovframework.com.security.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;
import org.springframework.security.config.BeanIds;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.egovframe.rte.fdl.security.config.EgovSecurityConfig;
import org.egovframe.rte.fdl.security.config.EgovSecurityConfigReader;

/**
 * 테스트 시 globals.properties의 Globals.SecurityConfigPath 값을 읽어
 * 해당 경로의 프로퍼티로 EgovSecurityConfig 빈을 생성한다.
 * 리소스: {@code classpath:egovframework/security/globals.properties}
 *
 * @author 유지보수
 * @version 5.0
 * @since 2025.06.01
 */
@Configuration
public class EgovSecurityTestConfig {

    private static final String GLOBALS_PROPERTIES = "egovframework/security/globals.properties";

    @Bean
    @Primary
    public EgovSecurityConfig egovSecurityConfig(ApplicationContext applicationContext) throws Exception {
        Properties globals = PropertiesLoaderUtils.loadProperties(new ClassPathResource(GLOBALS_PROPERTIES));
        String securityConfigPath = globals.getProperty("Globals.SecurityConfigPath");
        if (securityConfigPath == null || securityConfigPath.isEmpty()) {
            securityConfigPath = "classpath:egovframework/security/egov-security-config.properties";
        } else if (!securityConfigPath.startsWith("classpath:") && !securityConfigPath.startsWith("file:")) {
            securityConfigPath = "classpath:" + securityConfigPath.trim();
        } else {
            securityConfigPath = securityConfigPath.trim();
        }
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = applicationContext.getClassLoader();
        }
        ResourceLoader resourceLoader = new DefaultResourceLoader(cl);
        EgovSecurityConfigReader reader = new EgovSecurityConfigReader(securityConfigPath, resourceLoader);
        return reader.readConfig();
    }

    @Bean
    public static BeanDefinitionRegistryPostProcessor testSecurityBeanAliasRegistrar() {
        return new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
                registry.registerAlias("authenticationManager", BeanIds.AUTHENTICATION_MANAGER);
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            }
        };
    }

    @Bean(name = { BeanIds.SPRING_SECURITY_FILTER_CHAIN, BeanIds.FILTER_CHAIN_PROXY })
    public FilterChainProxy springSecurityFilterChain(SecurityFilterChain securityFilterChain) {
        return new FilterChainProxy(List.of(securityFilterChain));
    }

    @Bean(name = "delegatingMethodSecurityMetadataSource")
    public DelegatingMethodSecurityMetadataSource delegatingMethodSecurityMetadataSource() {
        return new DelegatingMethodSecurityMetadataSource(Collections.emptyList());
    }
}
