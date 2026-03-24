package egovframework.com.crypto;

import egovframework.com.crypto.config.EgovCryptoTestConfig;
import jakarta.annotation.Resource;
import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 원본: org.egovframe.rte.fdl.crypto.EgovEnvCryptoUserTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EgovCryptoTestConfig.class)
public class EgovEnvCryptoUserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovEnvCryptoUserTest.class);

    @Resource(name = "egov.envCryptoService")
    private EgovEnvCryptoService cryptoService;

    @Resource(name = "egovPropertyConfigurer")
    private EgovPropertyService propertyService;

    @Test
    public void testEgovEnvCryptoUser() {

        String[] arrCryptoString = {
                propertyService.getString("Globals.mysql.UserName"),
                propertyService.getString("Globals.mysql.Password"),
                propertyService.getString("Globals.mysql.Url"),
                propertyService.getString("Globals.mysql.DriverClassName")
        };

        String label = "";
        try {
            for (int i = 0; i < arrCryptoString.length; i++) {
                if (i == 0) label = "사용자 아이디";
                if (i == 1) label = "사용자 비밀번호";
                if (i == 2) label = "접속 주소";
                if (i == 2) label = "드라이버명";

                LOGGER.debug(label + " 원본(orignal):" + arrCryptoString[i]);
                LOGGER.debug(label + " 인코딩(encrypted):" + cryptoService.encrypt(arrCryptoString[i]));
            }
        } catch (IllegalArgumentException e) {
            LOGGER.debug("[{}] : {}", e.getClass().getName(), e.getMessage());
        }
    }

}
