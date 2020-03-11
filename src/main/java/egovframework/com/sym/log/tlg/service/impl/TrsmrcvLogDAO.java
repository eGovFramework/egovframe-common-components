package egovframework.com.sym.log.tlg.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.log.tlg.service.TrsmrcvLog;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TrsmrcvLogDAO.java
 * @Description : 송수신 로그 관리를 위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.tlg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Repository("trsmrcvLogDAO")
public class TrsmrcvLogDAO extends EgovComAbstractDAO {

	/**
	 * 송수신로그를 기록한다.
	 *
	 * @param TrsmrcvLog
	 * @return
	 * @throws Exception
	 */
	public void logInsertTrsmrcvLog(TrsmrcvLog trsmrcvLog) throws Exception{
		insert("TrsmrcvLogDAO.logInsertTrsmrcvLog", trsmrcvLog);
	}

	/**
	 * 송수신 로그정보를 요약한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void logInsertTrsmrcvLogSummary() throws Exception{
		insert("TrsmrcvLogDAO.logInsertTrsmrcvLogSummary", null);
		delete("TrsmrcvLogDAO.logDeleteTrsmrcvLogSummary", null);
	}

	/**
	 * 송수신 로그정보를 조회한다.
	 *
	 * @param trsmrcvLog
	 * @return trsmrcvLog
	 * @throws Exception
	 */
	public TrsmrcvLog selectTrsmrcvLog(TrsmrcvLog trsmrcvLog) throws Exception{

		return (TrsmrcvLog) selectOne("TrsmrcvLogDAO.selectTrsmrcvLog", trsmrcvLog);
	}

	/**
	 * 송수신 로그정보 목록을 조회한다.
	 *
	 * @param TrsmrcvLog
	 * @return
	 * @throws Exception
	 */
	public List<?> selectTrsmrcvLogInf(TrsmrcvLog trsmrcvLog) throws Exception{
		return selectList("TrsmrcvLogDAO.selectTrsmrcvLogInf", trsmrcvLog);
	}

	/**
	 * 송수신 로그정보 목록의 숫자를 조회한다.
	 * @param TrsmrcvLog
	 * @return
	 * @throws Exception
	 */
	public int selectTrsmrcvLogInfCnt(TrsmrcvLog trsmrcvLog) throws Exception{

		return (Integer)selectOne("TrsmrcvLogDAO.selectTrsmrcvLogInfCnt", trsmrcvLog);
	}

}
