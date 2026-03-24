package egovframework.com.crypto;

import egovframework.com.crypto.config.EgovCryptoTestConfig;
import jakarta.annotation.Resource;
import org.apache.commons.codec.binary.Base64;
import org.egovframe.rte.fdl.crypto.EgovCryptoService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 원본: org.egovframe.rte.fdl.crypto.EgovEnvCryptoAdminTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EgovCryptoTestConfig.class)
public class EgovEnvCryptoAdminTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovEnvCryptoAdminTest.class);

    @Resource(name = "egov.ariaCryptoService")
    private EgovCryptoService cryptoService;

    @Resource(name = "password")
    private String password;

    @Resource(name = "egovPropertyConfigurer")
    private EgovPropertyService propertyService;

    @Test
    public void egovARIAVerify() {

        String[] arrCryptoString = {
                propertyService.getString("Globals.mysql.UserName"),
                propertyService.getString("Globals.mysql.Password"),
                propertyService.getString("Globals.mysql.Url")
        };

        Base64 base64 = new Base64();
        String label = "";

        for (int i = 0; i < arrCryptoString.length; i++) {
            if (i == 0) label = "사용자 아이디";
            if (i == 1) label = "사용자 비밀번호";
            if (i == 2) label = "접속 주소";

            String value = arrCryptoString[i];
            if (value == null || value.isEmpty()) {
                LOGGER.debug("{} EgovEnvCryptoAdminTest egovARIAVerify() Skip (empty)", label);
                continue;
            }
            byte[] encrypted = cryptoService.encrypt(value.getBytes(StandardCharsets.UTF_8), password);
            String encryptedResult = URLEncoder.encode(new String(base64.encode(encrypted), StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            byte[] decrypted = cryptoService.decrypt(base64.decode(URLDecoder.decode(encryptedResult, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8)), password);

            LOGGER.debug("{} EgovEnvCryptoAdminTest egovARIAVerify() Orignal : {}", label, value);
            LOGGER.debug("{} EgovEnvCryptoAdminTest egovARIAVerify() Encrypted : {}", label, encryptedResult);
            LOGGER.debug("{} EgovEnvCryptoAdminTest egovARIAVerify() Decrypted : {}", label, new String(decrypted, StandardCharsets.UTF_8));
        }
    }

}
