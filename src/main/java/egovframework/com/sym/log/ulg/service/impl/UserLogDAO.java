package egovframework.com.sym.log.ulg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.log.ulg.service.UserLog;

/**
 * @Class Name : UserLogDAO.java
 * @Description : 사용로그 관리를 위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.ulg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Repository("userLogDAO")
public class UserLogDAO extends EgovComAbstractDAO {

	/**
	 * 사용자 로그정보를 생성한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void logInsertUserLog() throws Exception{
		insert("UserLog.logInsertUserLog", null);
	}

	/**
	 * 사용자 로그정보 상세정보를 조회한다.
	 *
	 * @param userLog
	 * @return userLog
	 * @throws Exception
	 */
	public UserLog selectUserLog(UserLog userLog) throws Exception{

		return (UserLog) selectOne("UserLog.selectUserLog", userLog);
	}

	/**
	 * 사용자 로그정보 목록을 조회한다.
	 *
	 * @param UserLog
	 * @return
	 * @throws Exception
	 */
	public List<?> selectUserLogInf(UserLog userLog) throws Exception{
		return list("UserLog.selectUserLogInf", userLog);
	}

	/**
	 * 사용자 로그정보 목록의 숫자를 조회한다.
	 * @param UserLog
	 * @return
	 * @throws Exception
	 */
	public int selectUserLogInfCnt(UserLog userLog) throws Exception{

		return (Integer)selectOne("UserLog.selectUserLogInfCnt", userLog);
	}

}
