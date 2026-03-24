package egovframework.com.crypto;

import egovframework.com.crypto.config.EgovCryptoTestConfig;
import jakarta.annotation.Resource;
import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 원본: org.egovframe.rte.fdl.crypto.EgovARIAErrorTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EgovCryptoTestConfig.class)
public class EgovARIAErrorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovARIAErrorTest.class);

    @Resource(name = "egov.envCryptoService")
    private EgovEnvCryptoService cryptoService;

    @Test
    public void egovARIAVerify() {

        String[] arrCryptoString = {
                "ckimage/2018/12",
                "https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte4.2"
        };

        try {
            for (String s : arrCryptoString) {
                LOGGER.debug(" 원본(orignal) : {}", s);
                LOGGER.debug(" 인코딩(encrypted) : {}", cryptoService.encrypt(s));
                LOGGER.debug(" 디코딩(decrypted) : {}", cryptoService.decrypt(cryptoService.encrypt(s)));

                if (cryptoService.decrypt(cryptoService.encrypt(s)).equals(s)) {
                    LOGGER.debug(" 통과 !!!");
                } else {
                    LOGGER.debug(" 실패 !!!");
                    return;
                }

                LOGGER.debug("------------------------------------------------------");

                LOGGER.debug(" 인코딩(encrypted None) : {}", cryptoService.encryptNone(s));
                LOGGER.debug(" 디코딩(decrypted None) : {}", cryptoService.decryptNone(cryptoService.encrypt(s)));

                if (cryptoService.decryptNone(cryptoService.encryptNone(s)).equals(s)) {
                    LOGGER.debug(" 통과 !!!");
                } else {
                    LOGGER.debug(" 실패 !!!");
                    return;
                }

                LOGGER.debug("------------------------------------------------------");
            }
        } catch (IllegalArgumentException | EncryptionOperationNotPossibleException e) {
            LOGGER.debug("[{}] EgovARIAErrorTest egovARIAVerify() : {}", e.getClass().getName(), e.getMessage());
        }

    }

}
