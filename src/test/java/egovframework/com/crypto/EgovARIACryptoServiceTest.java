package egovframework.com.crypto;

import egovframework.com.crypto.config.EgovCryptoTestConfig;
import jakarta.annotation.Resource;
import org.apache.commons.codec.binary.Base64;
import org.egovframe.rte.fdl.crypto.EgovCryptoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 원본: org.egovframe.rte.fdl.crypto.EgovARIACryptoServiceTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EgovCryptoTestConfig.class)
public class EgovARIACryptoServiceTest {

    @Resource(name = "egov.ariaCryptoService")
    private EgovCryptoService cryptoService;

    @Resource(name = "password")
    private String password;

    private byte[] getHashValue(File file) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DigestOutputStream dout = new DigestOutputStream(baos, sha);
        byte[] data = new byte[1024];

        try (FileInputStream fis = new FileInputStream(file); BufferedInputStream is = new BufferedInputStream(fis)) {
            while (true) {
                int bytesRead = is.read(data);
                if (bytesRead < 0)
                    break;
                dout.write(data, 0, bytesRead);
            }
            dout.flush();
            return dout.getMessageDigest().digest();
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
        assertEquals(param, new String(decrypted, StandardCharsets.UTF_8));
    }

    @Test
    public void testString2() {
        Base64 base64 = new Base64();
        String[] testString = {
                "This is a testing...\nHello!",
                "한글 테스트입니다...",
                "!@#$%^&*()_+|~{}:\"<>?-=\\`[];',./"
        };

        for (String str : testString) {
            byte[] encrypted = cryptoService.encrypt(str.getBytes(StandardCharsets.UTF_8), password);
            String encryptedResult = new String(base64.encode(encrypted), StandardCharsets.UTF_8);
            byte[] decrypted = cryptoService.decrypt(base64.decode(encryptedResult.getBytes(StandardCharsets.UTF_8)), password);
            assertEquals(str, new String(decrypted, StandardCharsets.UTF_8));
        }
    }

    @Test
    public void testStringBase64() {
        Base64 base64 = new Base64();
        String[] testString = {
                "This is a testing...\nHello!",
                "한글 테스트입니다...",
                "!@#$^&*()_|~{}:<>?-=\\`[];',./"
        };

        for (String str : testString) {
            byte[] encrypted = cryptoService.encrypt(str.getBytes(StandardCharsets.UTF_8), password);
            String encryptedResult = new String(base64.encode(encrypted), StandardCharsets.UTF_8);
            byte[] decrypted = cryptoService.decrypt(base64.decode(encryptedResult.getBytes(StandardCharsets.UTF_8)), password);
            String decryptedResult = new String(decrypted, StandardCharsets.UTF_8);
            assertEquals(str, decryptedResult);
        }
    }

    @Test
    public void testStringBase64UrlEncoderDecoder() {
        Base64 base64 = new Base64();
        String[] testString = {
                "This is a testing...\nHello!",
                "한글 테스트입니다...",
                "!@#$^&*()_|~{}:<>?-=\\`[];',./"
        };

        for (String str : testString) {
            byte[] encrypted = cryptoService.encrypt(str.getBytes(StandardCharsets.UTF_8), password);
            String encryptedResult = new String(base64.encode(encrypted), StandardCharsets.UTF_8);
            byte[] decrypted = cryptoService.decrypt(base64.decode(encryptedResult.getBytes(StandardCharsets.UTF_8)), password);
            String decryptedResult = new String(decrypted, StandardCharsets.UTF_8);
            assertEquals(str, decryptedResult);
        }
    }

    @Test
    public void testFile() {
        String filePath = "/egovframework/crypto/file/test.txt";
        File srcFile = new File(Objects.requireNonNull(this.getClass().getResource(filePath)).getFile());

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
        } catch (Exception ioe) {
            fail(ioe.getMessage());
        }
    }
}
