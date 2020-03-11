package egovframework.com.sym.log.lgm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.log.lgm.service.SysLog;

/**
* @Class Name : SysLogDAO.java
* @Description : 로그관리(시스템)를 위한 데이터 접근 클래스
* @Modification Information
*
*    수정일         수정자         수정내용
*    -------        -------     -------------------
*    2009. 3. 11.   이삼섭         최초생성
*    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.lgm)
*
* @author 공통 서비스 개발팀 이삼섭
* @since 2009. 3. 11.
* @version
* @see
*
*/
@Repository("SysLogDAO")
public class SysLogDAO extends EgovComAbstractDAO{

	/**
	 * 시스템 로그정보를 생성한다.
	 *
	 * @param SysLog
	 * @return
	 * @throws Exception
	 */
	public void logInsertSysLog(SysLog sysLog) {
		insert("SysLog.logInsertSysLog", sysLog);
		
	}

	/**
	 * 시스템 로그정보를 요약한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void logInsertSysLogSummary() {
		insert("SysLog.logInsertSysLogSummary", null);
		delete("SysLog.logDeleteSysLogSummary", null);
		
	}

	/**
	 * 시스템 로그목록을 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog
	 * @throws Exception
	 */
	public List<?> selectSysLogInf(SysLog sysLog) {
		return list("SysLog.selectSysLogInf", sysLog);
	}

	/**
	 * 시스템 로그정보 목록의 숫자를 조회한다.
	 * @param sysLog
	 * @return
	 * @throws Exception
	 */
	public int selectSysLogInfCnt(SysLog sysLog) {
		return (Integer)selectOne("SysLog.selectSysLogInfCnt", sysLog);
	}

	/**
	 * 시스템 로그 상세정보를 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog
	 * @throws Exception
	 */
	public SysLog selectSysLog(SysLog sysLog) {
		return (SysLog) selectOne("SysLog.selectSysLog", sysLog);
	}
}
