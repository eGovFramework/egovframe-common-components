package egovframework.com.uss.umt;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;

import egovframework.com.utl.sim.service.EgovFileScrty;
import lombok.extern.slf4j.Slf4j;

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
 *   2019.08.07  신용호          최초 생성
 *   2019.11.29  신용호          EgovFileScrty.encryptPassword() 삭제 : SALT 미사용
 *   2026.07.11  이백행          [2026년 컨트리뷰션] 디버그 출력에 log.debug 적용
 *
 * </pre>
 */

@Slf4j
public class TestMakePwd {

	public static void main(String[] args) {
		
		/*Scanner input = new Scanner(System.in);  // Create a Scanner object
		log.debug("Enter memberID");
		String memberId = input.nextLine();
		log.debug("Enter Password");
		String memberPwd = input.nextLine();
	    input.close();*/
		String memberId = "admin";
		String memberPwd = "admin";
		
	    log.debug("==>> member Id = {}", memberId);
	    log.debug("==>> member Pwd = {}", memberPwd);
		
	    
	    try {
			String encryptPasswordSalt = EgovFileScrty.encryptPassword(memberPwd, memberId); // memberId를 SALT로 사용
			//String encryptPassword = EgovFileScrty.encryptPassword(memberPwd); // SALT 미사용 => v3.9에서 메소드 삭제함.
			log.debug("==>> EGOV > encryptPassword(Salt) = {}", encryptPasswordSalt);
			//log.debug("==>> EGOV > encryptPassword = {}", encryptPassword);
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
		    md.update(memberPwd.getBytes());
		    byte[] digest = md.digest();
		    String result = new String(Hex.encodeHexString(digest));
			log.debug("==>> MessageDigest(Spring Security sha-256) > encryptPassword = {}", result);
			
//			ShaPasswordEncoder encoder = new ShaPasswordEncoder(); 
//			String shaEncryptPassword = encoder.encodePassword(memberPwd,memberId);
//			log.debug("==>> ShaPasswordEncoder > encryptPassword = {}", shaEncryptPassword);

		
//			StandardPasswordEncoder stdEncoder = new StandardPasswordEncoder();
//			String stdEncryptPassword = stdEncoder.encode(memberPwd);
//			log.debug("==>> StandardPasswordEncoder > encryptPassword = {}", stdEncryptPassword);

			BCryptPasswordEncoder bcEncoder = new BCryptPasswordEncoder();
			String bcEncryptPassword = bcEncoder.encode(memberPwd);
			log.debug("==>> BCryptPasswordEncoder > encryptPassword = {}", bcEncryptPassword);

		} catch (Exception e) {
			throw new BaseRuntimeException(e);
		}
	}

}
