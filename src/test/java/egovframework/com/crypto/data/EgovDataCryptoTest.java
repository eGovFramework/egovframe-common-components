package egovframework.com.crypto.data;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.DigestOutputStream;
import java.security.MessageDigest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.egovframe.rte.fdl.cryptography.EgovDigestService;

/**
 * ID Generation Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.11.28
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.11.28  신용호          최초 생성
 *
 * # WIKI 가이드
 * https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte2:fdl:encryption_decryption
 *
 * </pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath*:egovframework/spring/com/test-context-common.xml"
    ,"classpath*:egovframework/spring/com/test-context-crypto-data.xml"
    })
public class EgovDataCryptoTest {

    @Resource(name="ARIACryptoService")
    EgovCryptoService cryptoService;
    
    @Resource(name="digestService")
    EgovDigestService digestService;

    @Resource(name="generalCryptoService")
    EgovCryptoService generalCryptoService;

    private String password ="egovframe";
    
    //Encryption texts Guide Program
    @Test
    public void testString() {
    	
		String[] testString = {
			"This is a testing...\nHello!",
			"한글 테스트입니다...",
			"!@#$%^&*()_+|~{}:\"<>?-=\\`[];',./"
		};
 
		try {
		    for (String str : testString) {

			    byte[] encrypted = cryptoService.encrypt(str.getBytes("UTF-8"), password);
		 
				byte[] decrypted = cryptoService.decrypt(encrypted, password);
				String decryptedString = new String(decrypted, "UTF-8");
				System.out.println("===>>> "+decryptedString);
		 
				assertEquals(str, new String(decrypted, "UTF-8"));
		    }
		} catch (UnsupportedEncodingException uee) {
		    uee.printStackTrace();
		    Assert.fail();
		}
    }
    
    //Encryption File Guide Program
    @Test
    public void testFile() {

    	String filePath = "/egovframework/data/sample.png";
        File srcFile = new File(this.getClass().getResource(filePath).getFile());
    
        File trgtFile;
        File decryptedFile;
        try {
            trgtFile = File.createTempFile("tmp", "encrypted");
            trgtFile.deleteOnExit();
     
            cryptoService.encrypt(srcFile, password, trgtFile);
     
            decryptedFile = File.createTempFile("tmp", "decrypted");
            decryptedFile.deleteOnExit();
     
            cryptoService.decrypt(trgtFile, password, decryptedFile);
     
            Assert.assertTrue("Decrypted file not same!!", 
              checkFileWithHashFunction(srcFile, decryptedFile));
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    // 한글파일 예외상황 발생 
    @Test
    public void testFileException() {

    	String filePath = "/egovframework/data/sample.hwp";
        File srcFile = new File(this.getClass().getResource(filePath).getFile());
    
        File trgtFile;
        File decryptedFile;
        try {
            trgtFile = File.createTempFile("tmp", "encrypted");
            trgtFile.deleteOnExit();
     
            cryptoService.encrypt(srcFile, password, trgtFile);
     
            decryptedFile = File.createTempFile("tmp", "decrypted");
            decryptedFile.deleteOnExit();
     
            cryptoService.decrypt(trgtFile, password, decryptedFile);
     
            Assert.assertTrue("Decrypted file not same!!", 
              checkFileWithHashFunction(srcFile, decryptedFile));
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testFileOk_Atom() {

    	String filePath = "/egovframework/data/sample_test_ok(atom).hwp";
        File srcFile = new File(this.getClass().getResource(filePath).getFile());
    
        File trgtFile;
        File decryptedFile;
        try {
            trgtFile = File.createTempFile("tmp", "encrypted");
            trgtFile.deleteOnExit();
     
            cryptoService.encrypt(srcFile, password, trgtFile);
     
            decryptedFile = File.createTempFile("tmp", "decrypted");
            decryptedFile.deleteOnExit();
     
            cryptoService.decrypt(trgtFile, password, decryptedFile);
     
            Assert.assertTrue("Decrypted file not same!!", 
              checkFileWithHashFunction(srcFile, decryptedFile));
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testFileFail_Atom() {

    	String filePath = "/egovframework/data/sample_test_fail(atom).hwp";
        File srcFile = new File(this.getClass().getResource(filePath).getFile());
    
        File trgtFile;
        File decryptedFile;
        try {
            trgtFile = File.createTempFile("tmp", "encrypted");
            trgtFile.deleteOnExit();
     
            cryptoService.encrypt(srcFile, password, trgtFile);
     
            decryptedFile = File.createTempFile("tmp", "decrypted");
            decryptedFile.deleteOnExit();
     
            cryptoService.decrypt(trgtFile, password, decryptedFile);
     
            Assert.assertTrue("Decrypted file not same!!", 
              checkFileWithHashFunction(srcFile, decryptedFile));
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail(ex.getMessage());
        }
    }

    //Encryption Digest Guide Program
    @Test
    public void testDigest() {
		String data = "egovframe";
	 
		byte[] digested = digestService.digest(data.getBytes());
	 
		Assert.assertTrue(digestService.matches(data.getBytes(), digested));
    }
    
    //Encryption texts Guide Program
    @Test
    public void testStringGeneralCryptoService() {
		String[] testString = {
			"This is a testing...\nHello!",
			"한글 테스트입니다...",
			"!@#$%^&*()_+|~{}:\"<>?-=\\`[];',./"
		};
 
		try {
		    for (String str : testString) {
			byte[] encrypted = generalCryptoService.encrypt(str.getBytes("UTF-8"), password);
	 
			byte[] decrypted = generalCryptoService.decrypt(encrypted, password);
	 
			assertEquals(str, new String(decrypted, "UTF-8"));
		    }
		} catch (UnsupportedEncodingException uee) {
		    uee.printStackTrace();
		    Assert.fail();
		}
    }

    //Encryption BigDecimal Guide Program
    @Test
    public void testBigDecimal() {
		BigDecimal big = new BigDecimal(123456);
	 
		BigDecimal encrypted = generalCryptoService.encrypt(big, password);
	 
		BigDecimal decrypted = generalCryptoService.decrypt(encrypted, password);
	 
		assertEquals(big, decrypted);
    }
    
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
				} catch (Exception ignore) {
					// no-op
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ignore) {
						// no-op
				}
			}
		}
	}
    
    private boolean checkFileWithHashFunction(File srcFile, File trgtFile) throws Exception {

		byte[] srcByte = getHashValue(srcFile);

		byte[] trgtByte = getHashValue(trgtFile);

		if (srcByte.length != trgtByte.length) {
			System.out.println("=====> length not same!!");
			return false;
		}

		for (int i = 0; i < srcByte.length; i++) {
			if (srcByte[i] != trgtByte[i]) {
				System.out.println("=====> byte not same in " + i);
				return false;
			}
		}

		return true;
	}
}
