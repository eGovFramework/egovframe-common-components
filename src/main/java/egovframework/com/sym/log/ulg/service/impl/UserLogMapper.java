package egovframework.com.sym.log.ulg.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.log.ulg.service.UserLog;

/**
 * 사용자 로그 관리를 위한 Mapper 인터페이스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.ulg)
 *    2026. 5. 28.   dasomel        XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("userLogDAO")
public interface UserLogMapper {

	/**
	 * 사용자 로그정보를 생성한다.
	 */
	void logInsertUserLog();

	/**
	 * 사용자 로그정보 상세정보를 조회한다.
	 */
	UserLog selectUserLog(UserLog userLog);

	/**
	 * 사용자 로그정보 목록을 조회한다.
	 */
	List<UserLog> selectUserLogInf(UserLog userLog);

	/**
	 * 사용자 로그정보 목록의 숫자를 조회한다.
	 */
	int selectUserLogInfCnt(UserLog userLog);
}
