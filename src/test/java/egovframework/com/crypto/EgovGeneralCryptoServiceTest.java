package egovframework.com.crypto;

import egovframework.com.crypto.config.EgovCryptoTestConfig;
import jakarta.annotation.Resource;
import org.egovframe.rte.fdl.crypto.EgovCryptoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 원본: org.egovframe.rte.fdl.crypto.EgovGeneralCryptoServiceTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EgovCryptoTestConfig.class)
public class EgovGeneralCryptoServiceTest {

    @Resource(name = "generalCryptoService")
    private EgovCryptoService cryptoService;

    @Resource(name = "password")
    private String password;

    private byte[] getHashValue(File file) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DigestOutputStream dout = new DigestOutputStream(baos, sha);
        byte[] data = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream is = null;

        try {
            fis = new FileInputStream(file);
            is = new BufferedInputStream(fis);
            while (true) {
                int bytesRead = is.read(data);
                if (bytesRead < 0)
                    break;
                dout.write(data, 0, bytesRead);
            }
            dout.flush();
            byte[] result = dout.getMessageDigest().digest();
            return result;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    fail(e.getMessage());
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    fail(e.getMessage());
                }
            }
        }
    }

    private boolean checkFileWithHashFunction(File srcFile, File trgtFile) throws Exception {
        byte[] srcByte = getHashValue(srcFile);
        byte[] trgtByte = getHashValue(trgtFile);

        if (srcByte.length != trgtByte.length) {
            return false;
        }

        for (int i = 0; i < srcByte.length; i++) {
            if (srcByte[i] != trgtByte[i]) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void testString() {
        String param = "aaa";
        byte[] encrypted = cryptoService.encrypt(param.getBytes(StandardCharsets.UTF_8), password);
        byte[] decrypted = cryptoService.decrypt(encrypted, password);
        String decryptedResult = new String(decrypted, StandardCharsets.UTF_8);
        assertEquals(param, decryptedResult);
    }

    @Test
    public void testBigDecimal() {
        BigDecimal big = new BigDecimal(123456);
        BigDecimal encrypted = cryptoService.encrypt(big, password);
        BigDecimal decrypted = cryptoService.decrypt(encrypted, password);
        assertEquals(big, decrypted);
    }

    @Test
    public void testFile() {
        String filePath = "/egovframework/crypto/file/test.hwp";
        File srcFile = new File(Objects.requireNonNull(this.getClass().getResource(filePath), filePath).getFile());
        File trgtFile;
        File decryptedFile;

        try {
            trgtFile = File.createTempFile("tmp", "encrypted");
            trgtFile.deleteOnExit();
            cryptoService.encrypt(srcFile, password, trgtFile);
            decryptedFile = File.createTempFile("tmp", "decrypted");
            decryptedFile.deleteOnExit();
            cryptoService.decrypt(trgtFile, password, decryptedFile);
            assertTrue(checkFileWithHashFunction(srcFile, decryptedFile));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
