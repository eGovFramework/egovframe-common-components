package egovframework.com.uss.umt;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;

import egovframework.com.utl.sim.service.EgovFileScrty;

/**
 * 비밀번호 생성 Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.08.07
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.08.07  신용호          최초 생성
 *  2019.11.29  신용호          EgovFileScrty.encryptPassword() 삭제 : SALT 미사용
 *
 * </pre>
 */

@Slf4j
public class TestMakePwd {

	public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
		String memberId = "admin";
		String memberPwd = "admin";
		
	    log.debug("==>> member Id = {}", memberId);
	    log.debug("==>> member Pwd = {}", memberPwd);

		String encryptPasswordSalt = EgovFileScrty.encryptPassword(memberPwd, memberId); // memberId를 SALT로 사용
		log.debug("==>> EGOV > encryptPassword(Salt) = {}", encryptPasswordSalt);
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(memberPwd.getBytes());
	    byte[] digest = md.digest();
	    String result = new String(Hex.encodeHexString(digest));
		log.debug("==>> MessageDigest(Spring Security sha-256) > encryptPassword = {}", result);

		BCryptPasswordEncoder bcEncoder = new BCryptPasswordEncoder();
		String bcEncryptPassword = bcEncoder.encode(memberPwd);
		log.debug("==>> BCryptPasswordEncoder > encryptPassword = {}", bcEncryptPassword);
	}

}
