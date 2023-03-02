package egovframework.com.sec.rnc.mip.mva.sp.comm.util;

import java.util.Base64;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.util
 * @FileName    : Base64Util.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : Base64 Util
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public class Base64Util {

	/**
	 * String to Base64 String
	 * 
	 * @MethodName : encode
	 * @param text String
	 * @return Base64 String
	 */
	public static String encode(String text) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(text.getBytes());
	}

	/**
	 * String to Base64 String with Padding
	 * 
	 * @MethodName : encodeWithPadding
	 * @param text String
	 * @return Base64 String
	 */
	public static String encodeWithPadding(String text) {
		return Base64.getUrlEncoder().encodeToString(text.getBytes());
	}

	/**
	 * Byte to Base64 String
	 * 
	 * @MethodName : encode
	 * @param data Byte 배열
	 * @return Base64 String
	 */
	public static String encode(byte[] data) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
	}

	/**
	 * Byte to Base64 String with Padding
	 * 
	 * @MethodName : encode
	 * @param data Byte 배열
	 * @return Base64 String
	 */
	public static String encodeWithPadding(byte[] data) {
		return Base64.getUrlEncoder().encodeToString(data);
	}

	/**
	 * Base64 String to String
	 * 
	 * @MethodName : decode
	 * @param text Base64 String
	 * @return String
	 */
	public static String decode(String text) {
		return new String(Base64.getUrlDecoder().decode(text));
	}

}
