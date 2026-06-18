package egovframework.com.utl.slm;

import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpSession;

/**
 * @Class Name : EgovMultiLoginPreventor.java
 * @Description : 중복 로그인 방지를 위해 사용자의 로그인 아이디와 세션 아이디를 관리하는 구현 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2014.09.30	표준프레임워크		최초생성
* @author YJ Kwon
 * @since 2014.09.30
 * @version 3.5
 */
public class EgovMultiLoginPreventor {

	public static ConcurrentHashMap<String, HttpSession> loginUsers = new ConcurrentHashMap<>();

	/**
	 * 사용자의 로그인 아이디로 생성된 세션이 있는지를 확인한다
	 * */
	public static boolean findByLoginId(String loginId) {
		return loginUsers.containsKey(loginId);
	}

	/**
	 * 사용자의 로그인 아이디로 이미 존재하는 세션을 무효화한다
	 * */
	public static void invalidateByLoginId(String loginId) {
		// 맵은 로그인 아이디를 키로 사용하므로 단건 조회로 충분하다.
		// 조회 결과를 지역변수에 담아, 다른 스레드(valueUnbound)가 엔트리를 동시에
		// 제거하여 null이 반환되는 경우에도 NPE가 발생하지 않도록 가드한다.
		HttpSession session = loginUsers.get(loginId);
		if (session != null) {
			session.invalidate();
		}
	}
}
