package egovframework.com.uss.umt;

import java.security.MessageDigest;

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

public class TestMakePwd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Scanner input = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Enter memberID");
		String memberId = input.nextLine();
		System.out.println("Enter Password");
		String memberPwd = input.nextLine();
	    input.close();*/
		String memberId = "admin";
		String memberPwd = "admin";
		
	    System.out.println("==>> member Id = "+memberId);
	    System.out.println("==>> member Pwd = "+memberPwd);
		
	    
	    try {
			String encryptPasswordSalt = EgovFileScrty.encryptPassword(memberPwd, memberId); // memberId를 SALT로 사용
			//String encryptPassword = EgovFileScrty.encryptPassword(memberPwd); // SALT 미사용 => v3.9에서 메소드 삭제함.
			System.out.println("==>> EGOV > encryptPassword(Salt) = "+encryptPasswordSalt);
			//System.out.println("==>> EGOV > encryptPassword = "+encryptPassword);
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
		    md.update(memberPwd.getBytes());
		    byte[] digest = md.digest();
		    String result = new String(Hex.encodeHexString(digest));
			System.out.println("==>> MessageDigest(Spring Security sha-256) > encryptPassword = "+result);
			
//			ShaPasswordEncoder encoder = new ShaPasswordEncoder(); 
//			String shaEncryptPassword = encoder.encodePassword(memberPwd,memberId);
//			System.out.println("==>> ShaPasswordEncoder > encryptPassword = "+shaEncryptPassword);

		
//			StandardPasswordEncoder stdEncoder = new StandardPasswordEncoder();
//			String stdEncryptPassword = stdEncoder.encode(memberPwd);
//			System.out.println("==>> StandardPasswordEncoder > encryptPassword = "+stdEncryptPassword);

			BCryptPasswordEncoder bcEncoder = new BCryptPasswordEncoder();
			String bcEncryptPassword = bcEncoder.encode(memberPwd);
			System.out.println("==>> BCryptPasswordEncoder > encryptPassword = "+bcEncryptPassword);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
