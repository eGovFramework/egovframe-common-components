package egovframework.com.crypto;

import egovframework.com.crypto.config.EgovCryptoTestConfig;
import jakarta.annotation.Resource;
import org.egovframe.rte.fdl.crypto.EgovDigestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 원본: org.egovframe.rte.fdl.crypto.EgovDigestServiceTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EgovCryptoTestConfig.class)
public class EgovDigestServiceTest {

    @Resource(name = "egov.digestService")
    private EgovDigestService digestService;

    @Test
    public void testDigest() {
        String data = "egovframe";
        byte[] digested = digestService.digest(data.getBytes());
        assertTrue(digestService.matches(data.getBytes(), digested));
    }

}
