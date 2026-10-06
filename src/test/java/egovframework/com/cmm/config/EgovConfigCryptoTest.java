package egovframework.com.cmm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import egovframework.com.cmm.util.EgovPasswordResolver;

@Configuration
@ComponentScan(basePackages = "org.egovframe.rte.fdl.crypto")
public class EgovConfigCryptoTest {

	@Bean
	EgovPasswordResolver egovPasswordResolver() {
		return new EgovPasswordResolver();
	}

}
