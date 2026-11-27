package egovframework.com.cmm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Configuration;

import egovframework.com.cmm.util.EgovPasswordResolver;

@Configuration
// 실행환경 4.3.2 : Crypto 간소화 설정은 @Configuration 이 아닌 XML 네임스페이스(<egov-crypto:config>)로 제공된다
@ImportResource("classpath:/egovframework/spring/com/context-crypto.xml")
public class EgovConfigCryptoTest {

	@Bean
	EgovPasswordResolver egovPasswordResolver() {
		return new EgovPasswordResolver();
	}

}
