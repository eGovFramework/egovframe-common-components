package egovframework.com.crypto;

import org.egovframe.rte.fdl.cryptography.EgovPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ ARIA Crypto 암호화 - HashedPassword 생성 샘플 구현
 * @author 표준프레임워크 신용호
 * @since 2019.04.23
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2022.12.22  신용호          최초 생성
 *
 * </pre>
 */

public class MakeHashedPassword {

	private static final Logger LOGGER = LoggerFactory.getLogger(MakeHashedPassword.class);
	 
	//계정암호화키 키
	static public String algorithmKey = "egovframe";
 
	//계정암호화 알고리즘(MD5, SHA-1, SHA-256)
	static public String algorithm = "SHA-256";
 
	//계정암호화키 블럭사이즈
	public int algorithmBlockSize = 1024;
	
	public static void main(String[] args) {
		
		EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
		egovPasswordEncoder.setAlgorithm(algorithm);
		 
		LOGGER.info("------------------------------------------------------");
		LOGGER.info("알고리즘(algorithm) : "+algorithm);
		LOGGER.info("알고리즘 키(algorithmKey) : "+algorithmKey);
		LOGGER.info("알고리즘 키 Hash(algorithmKeyHash) : "+egovPasswordEncoder.encryptPassword(algorithmKey));
	}

}
