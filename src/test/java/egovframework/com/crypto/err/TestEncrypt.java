package egovframework.com.crypto.err;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.egovframe.rte.fdl.cryptography.impl.EgovEnvCryptoServiceImpl;

/**
 * egovframework.rte.fdl.crypto v3.7 이하의 오류 테스트
 * @author 표준프레임워크 신용호
 * @since 2019.01.03
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.01.03  신용호          최초 생성
 *
 * </pre>
 */

public class TestEncrypt {

    /** 로그설정 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TestEncrypt.class);
	private static EgovEnvCryptoService cryptoService;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	//WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
    	//ApplicationContext context = new ClassPathXmlApplicationContext("classpath:egovframework/spring/com/context-crypto.xml");
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:egovframework/spring/com/test-context-crypto.xml");
    	//EgovEnvCryptoService cryptoService = (EgovEnvCryptoService)factory.getBean("egovEnvCryptoService");
    	cryptoService = context.getBean(EgovEnvCryptoServiceImpl.class);

		//String orgStr = "TESTSTR";
		String orgStr = "";
		for (int ii=1; ii < 300; ii++ ) {
			orgStr = createRndString(ii);
			LOGGER.debug("===>>> --------------------------------------------------------------");
			LOGGER.debug("===>>> orgStr String : "+ orgStr);
			LOGGER.debug("===>>> orgStr Length = "+ orgStr.length());
			String encStr = TestEncrypt.encrypt(orgStr);

			//LOGGER.debug("===>>> Encrypt String : "+ encStr);
			String decStr = TestEncrypt.decrypt(encStr);
			LOGGER.debug("===>>> Decrypt String : "+ decStr);
			
			if ( orgStr.equals(decStr) ) LOGGER.debug("===> SUCCESS");
			else LOGGER.debug("===> FAIL~~~~~~");
		}

	}
	
	private static String createRndString(int length) {
		
		StringBuffer result = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		    	result.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		    	result.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		    	result.append((rnd.nextInt(10)));
		        break;
		    }
		}

		return result.toString();
	}
	
    /**
     * 암호화
     *
     * @param encrypt
     */
    private static String encrypt(String encrypt) {
    	
    	try {
			return cryptoService.encrypt(encrypt);
        } catch(IllegalArgumentException e) {
    		LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
        } catch (Exception e) {
        	LOGGER.error("[" + e.getClass() +"] :" + e.getMessage());
        }
		return encrypt;
    }
    
    /**
     * 복호화
     *
     * @param decrypt
     */
    private static String decrypt(String decrypt){

    	try {
			return cryptoService.decrypt(decrypt);
        } catch(IllegalArgumentException e) {
    		LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
        } catch (Exception e) {
        	LOGGER.error("[" + e.getClass() +"] :" + e.getMessage());
        }
		return decrypt;
    }


}
