package egovframework.com.sec.rnc.mip.mva.sp.comm.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.util
 * @FileName    : Generator.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : 난수 생성 Util
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public class Generator {

	/**
	 * 거래코드 생성 - 현재시간 yyyyMMddhhmmssSSS + 시큐어난수 (8자리)
	 * 
	 * @MethodName : genTrxcode
	 * @return 거래코드
	 */
	public static String genTrxcode() {
		Date today = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddhhmmssSSS", Locale.KOREA);
		String second = secRandom(4); // 4자리 생성하고 hex code로 표현되므로 8개 자리가 나옴

		String first = formater.format(today);
		String result = first + second;

		return result;
	}

	/**
	 * Nonce 생성
	 * 
	 * @MethodName : genNonce
	 * @return Nonce
	 */
	public static String genNonce() {
		Date today = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddhhmmssSSSS", Locale.KOREA);

		String first = formater.format(today);
		String second = secRandom(11); // 16진수 11개 자릿수 -> 스트링 -> 바이트배열 22바이트 + 18바이트 = 40바이트
		String result = first + second;

		return result;
	}

	/**
	 * 난수 생성
	 * 
	 * @MethodName : SecRandom
	 * @param genNum
	 * @return 난수
	 */
	public static String secRandom(int genNum) {
		SecureRandom random = new SecureRandom();

		byte bytes[] = new byte[genNum];

		random.nextBytes(bytes);

		return bytesToHexString(bytes);
	}

	/** Base Hex Chars */
	private final static char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

	/**
	 * Byte Array to Hex String
	 * 
	 * @MethodName : bytesToHexString
	 * @param bytes Byte Array
	 * @return Hex String
	 */
	public static String bytesToHexString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		char[] hexChars = new char[bytes.length * 2];

		for (int i = 0; i < bytes.length; i++) {
			int value = bytes[i] & 0xff;
			
			hexChars[i * 2] = HEX_CHARS[value >>> 4];
			hexChars[i * 2 + 1] = HEX_CHARS[value & 0x0f];
		}

		return new String(hexChars);
	}

}
