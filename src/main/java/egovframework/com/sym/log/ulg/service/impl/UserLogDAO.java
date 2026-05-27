package egovframework.com.sym.log.ulg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.sym.log.ulg.service.UserLog;
import jakarta.annotation.Resource;

/**
 * @Class Name : UserLogDAO.java
 * @Description : 사용로그 관리를 위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.ulg)
 *    2026. 5. 28.   dasomel        @EgovMapper 인터페이스 위임 방식으로 전환
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Repository("userLogDAO")
public class UserLogDAO {

	@Resource(name = "userLogMapper")
	private UserLogMapper userLogMapper;

	/**
	 * 사용자 로그정보를 생성한다.
	 */
	public void logInsertUserLog() {
		userLogMapper.logInsertUserLog();
	}

	/**
	 * 사용자 로그정보 상세정보를 조회한다.
	 */
	public UserLog selectUserLog(UserLog userLog) {
		return userLogMapper.selectUserLog(userLog);
	}

	/**
	 * 사용자 로그정보 목록을 조회한다.
	 */
	public List<UserLog> selectUserLogInf(UserLog userLog) {
		return userLogMapper.selectUserLogInf(userLog);
	}

	/**
	 * 사용자 로그정보 목록의 숫자를 조회한다.
	 */
	public int selectUserLogInfCnt(UserLog userLog) {
		return userLogMapper.selectUserLogInfCnt(userLog);
	}
}
