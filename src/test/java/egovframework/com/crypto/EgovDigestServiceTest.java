package egovframework.com.crypto;

import javax.annotation.Resource;
import org.egovframe.rte.fdl.cryptography.EgovDigestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 원본: org.egovframe.rte.fdl.cryptography.EgovDigestServiceTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:/egovframework/crypto/context-crypto-test.xml")
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
