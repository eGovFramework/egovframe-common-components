package egovframework.com.utl.sim.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class Name : EgovFileScrty.java
 * @Description : 파일 및 텍스트 문자열 암호화 처리하는 구현 클래스
 * @Modification Information
 * 
 *               <pre>
 *    수정일                 수정자              수정내용
 *    ----------    -------     -------------------
 *    2019.11.29	신용호		encryptPassword(String data) 삭제 : KISA 보안약점 조치 (비밀번호 해시함수 적용 시 솔트를 사용하여야 함)
 *    2022.11.16	신용호        소스코드 보안 조치
 *   2024.07.29  이백행          시큐어코딩 Exception 제거
 *               </pre>
 * 
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.08.26
 * @version 1.0
 */
@Slf4j
public class EgovFileScrty {

	private static final String STORE_FILE_PATH = EgovProperties.getProperty("Globals.fileStorePath");
	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	static final int BUFFER_SIZE = 1024;

	/**
	 * 파일을 암호화하는 기능
	 *
	 * @param String source 암호화할 파일
	 * @param String target 암호화된 파일
	 * @return boolean result 암호화여부 True/False
	 */
	public static boolean encryptFile(String source, String target) {

		// 암호화 여부
		boolean result = false;

		File srcFile = new File(EgovWebUtil.filePathBlackList(STORE_FILE_PATH + FilenameUtils.getName(source)));

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		byte[] buffer = new byte[BUFFER_SIZE];

		try {
			if (srcFile.exists() && srcFile.isFile()) {

				input = new BufferedInputStream(new FileInputStream(srcFile));
				output = new BufferedOutputStream(new FileOutputStream(
						EgovWebUtil.filePathBlackList(STORE_FILE_PATH + FilenameUtils.getName(target))));

				int length = 0;
				while ((length = input.read(buffer)) >= 0) {
					byte[] data = new byte[length];
					System.arraycopy(buffer, 0, data, 0, length);
					output.write(encodeBinary(data).getBytes());
					output.write(System.getProperty("line.separator").getBytes());
				}
				result = true;
			}
		} catch (IOException e) {
			String msg = "IOException encryptFile";
			if (log.isErrorEnabled()) {
				log.error(msg);
			}
			throw new BaseRuntimeException(msg, e);
		} finally {
			EgovResourceCloseHelper.close(input, output);
		}

		return result;
	}

	/**
	 * 파일을 복호화하는 기능
	 *
	 * @param String source 복호화할 파일
	 * @param String target 복호화된 파일
	 * @return boolean result 복호화여부 True/False
	 */
	public static boolean decryptFile(String source, String target) {

		// 복호화 여부
		boolean result = false;

		File srcFile = new File(EgovWebUtil.filePathBlackList(STORE_FILE_PATH + FilenameUtils.getName(source)));

		BufferedReader input = null;
		BufferedOutputStream output = null;

		// byte[] buffer = new byte[BUFFER_SIZE];
		String line = null;

		try {
			if (srcFile.exists() && srcFile.isFile()) {

				input = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile)));
				output = new BufferedOutputStream(new FileOutputStream(
						EgovWebUtil.filePathBlackList(STORE_FILE_PATH + FilenameUtils.getName(target))));

				while ((line = input.readLine()) != null) {
					byte[] data = line.getBytes();
					output.write(decodeBinary(new String(data)));
				}

				result = true;
			}
		} catch (IOException e) {
			String msg = "IOException decryptFile";
			if (log.isErrorEnabled()) {
				log.error(msg);
			}
			throw new BaseRuntimeException(msg, e);
		} finally {
			EgovResourceCloseHelper.close(input, output);
		}

		return result;
	}

	/**
	 * 데이터를 암호화하는 기능
	 *
	 * @param byte[] data 암호화할 데이터
	 * @return String result 암호화된 데이터
	 */
	public static String encodeBinary(byte[] data) {
		if (data == null) {
			return "";
		}

		return new String(Base64.encodeBase64(data));
	}

	/**
	 * 데이터를 암호화하는 기능
	 *
	 * @param String data 암호화할 데이터
	 * @return String result 암호화된 데이터
	 */
	@Deprecated
	public static String encode(String data) {
		return encodeBinary(data.getBytes());
	}

	/**
	 * 데이터를 복호화하는 기능
	 *
	 * @param String data 복호화할 데이터
	 * @return String result 복호화된 데이터
	 */
	public static byte[] decodeBinary(String data) {
		return Base64.decodeBase64(data.getBytes());
	}

	/**
	 * 데이터를 복호화하는 기능
	 *
	 * @param String data 복호화할 데이터
	 * @return String result 복호화된 데이터
	 */
	@Deprecated
	public static String decode(String data) {
		return new String(decodeBinary(data));
	}

	/**
	 * 비밀번호를 암호화하는 기능(복호화가 되면 안되므로 SHA-256 인코딩 방식 적용)
	 * 
	 * @param password 암호화될 패스워드
	 * @param id       salt로 사용될 사용자 ID 지정
	 * @return @
	 */
	public static String encryptPassword(String password, String id) {

		if (password == null)
			return "";
		if (id == null)
			return ""; // KISA 보안약점 조치 (2018-12-11, 신용호)

		byte[] hashValue = null; // 해쉬값

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			String msg = "NoSuchAlgorithmException encryptPassword";
			if (log.isErrorEnabled()) {
				log.error(msg);
			}
			throw new BaseRuntimeException(msg, e);
		}

		md.reset();
		md.update(id.getBytes());

		hashValue = md.digest(password.getBytes());

		return new String(Base64.encodeBase64(hashValue));
	}

	/**
	 * 비밀번호를 암호화하는 기능(복호화가 되면 안되므로 SHA-256 인코딩 방식 적용)
	 * 
	 * @param data 암호화할 비밀번호
	 * @param salt Salt
	 * @return 암호화된 비밀번호 @
	 */
	public static String encryptPassword(String data, byte[] salt) {

		if (data == null) {
			return "";
		}

		byte[] hashValue = null; // 해쉬값

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			String msg = "NoSuchAlgorithmException encryptPassword";
			if (log.isErrorEnabled()) {
				log.error(msg);
			}
			throw new BaseRuntimeException(msg, e);
		}

		md.reset();
		md.update(salt);

		hashValue = md.digest(data.getBytes());

		return new String(Base64.encodeBase64(hashValue));
	}

	/**
	 * 비밀번호를 암호화된 패스워드 검증(salt가 사용된 경우만 적용).
	 * 
	 * @param data    원 패스워드
	 * @param encoded 해쉬처리된 패스워드(Base64 인코딩)
	 * @return @
	 */
	public static boolean checkPassword(String data, String encoded, byte[] salt) {
		byte[] hashValue = null; // 해쉬값

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			String msg = "NoSuchAlgorithmException checkPassword";
			if (log.isErrorEnabled()) {
				log.error(msg);
			}
			throw new BaseRuntimeException(msg, e);
		}

		md.reset();
		md.update(salt);
		hashValue = md.digest(data.getBytes());

		return MessageDigest.isEqual(hashValue, Base64.decodeBase64(encoded.getBytes()));
	}

	/*
	 * public static void main(String[] args) { try { String password = "abc";
	 * String salt = "def";
	 * 
	 * String first = encryptPassword(password, salt.getBytes()); String second =
	 * encryptPassword(password, salt.getBytes()); System.out.println(password +
	 * " => " + first + " : " + checkPassword(password, first, salt.getBytes()));
	 * System.out.println(password + " => " + second + " : " +
	 * checkPassword(password, second, salt.getBytes())); } catch (Exception ex) {
	 * ex.printStackTrace(); } }
	 */
}