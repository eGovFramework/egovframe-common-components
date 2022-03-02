package egovframework.com.sec.pki.web;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.egovframe.rte.fdl.cryptography.EgovPasswordEncoder;

/**
 * 암호화/복호화 관한 controller 클래스를 정의한다.
 * @author 공통서비스 개발팀 신용호
 * @since 2018.12.03
 * @version 3.8
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일               수정자              수정내용
 *  ----------   --------    ---------------------------
 *  2018.12.03   신용호              최초 생성
 * </pre>
 */

@Controller
public class EgovCryptoController {

    /** 로그설정 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovCryptoController.class);
	
	/** 암호화서비스 */
	@Resource(name = "egovEnvCryptoService")
	EgovEnvCryptoService cryptoService;
	
	@Resource(name = "egovEnvPasswordEncoderService")
	EgovPasswordEncoder egovPasswordEncoder;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
    /**
     * 암호화/복호화 입력 및 요청 페이지를 호출한다.
     * 
     * @return
     */
	@IncludedInfo(name="암호화/복호화", listUrl="/sec/pki/EgovCryptoInfo.do", order = 2200 ,gid = 90)
    @RequestMapping(value="/sec/pki/EgovCryptoInfo.do")
    public String displayCryptoInfo( @RequestParam Map<?, ?> commandMap,
							    		ModelMap model) throws Exception {
        // 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	
    	String plainText = (String)commandMap.get("plainText");

    	if ( plainText != null ) {
    	
	    	int plainTextLen = plainText.length(); 
	    	String cryptText = encrypt(plainText);
	    	String decryptText = decrypt(cryptText);
	    	int decryptTextLen = decryptText.length();
	    	
	    	model.addAttribute("plainText", plainText);
	    	model.addAttribute("plainTextLen", plainTextLen);
	    	model.addAttribute("cryptText", cryptText);
	    	model.addAttribute("decryptText", decryptText);
	    	model.addAttribute("decryptTextLen", decryptTextLen);
    	}
    	
    	return "egovframework/com/sec/pki/EgovCryptoInfo";
    }
	
    /**
     * 암호화
     *
     * @param encrypt
     */
    private String encrypt(String encrypt) {

    	try {
    		//return cryptoService.encrypt(encrypt); // Handles URLEncoding.
			return cryptoService.encryptNone(encrypt); // Does not handle URLEncoding.
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
    private String decrypt(String decrypt){

    	try {
    		//return cryptoService.decrypt(decrypt); // Handles URLDecoding.
			return cryptoService.decryptNone(decrypt); // Does not handle URLDecoding.
        } catch(IllegalArgumentException e) {
    		LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
        } catch (Exception e) {
        	LOGGER.error("[" + e.getClass() +"] :" + e.getMessage());
        }
		return decrypt;
    }

}