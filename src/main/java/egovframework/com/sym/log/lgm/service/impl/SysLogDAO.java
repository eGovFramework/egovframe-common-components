package egovframework.com.sym.log.lgm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.sym.log.lgm.service.SysLog;
import jakarta.annotation.Resource;

/**
* @Class Name : SysLogDAO.java
* @Description : 로그관리(시스템)를 위한 데이터 접근 클래스
* @Modification Information
*
*    수정일         수정자         수정내용
*    -------        -------     -------------------
*    2009. 3. 11.   이삼섭         최초생성
*    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.lgm)
*    2026. 5. 28.   dasomel        SysLogMapper 위임으로 전환
*
* @author 공통 서비스 개발팀 이삼섭
* @since 2009. 3. 11.
* @version
* @see
*
*/
@Repository("SysLogDAO")
public class SysLogDAO {

	@Resource(name = "sysLogMapper")
	private SysLogMapper sysLogMapper;

	/**
	 * 시스템 로그정보를 생성한다.
	 *
	 * @param sysLog
	 */
	public void logInsertSysLog(SysLog sysLog) {
		sysLogMapper.logInsertSysLog(sysLog);
	}

	/**
	 * 시스템 로그정보를 요약한다.
	 */
	public void logInsertSysLogSummary() {
		sysLogMapper.logInsertSysLogSummary();
		sysLogMapper.logDeleteSysLogSummary();
	}

	/**
	 * 시스템 로그목록을 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog 목록
	 */
	public List<SysLog> selectSysLogInf(SysLog sysLog) {
		return sysLogMapper.selectSysLogInf(sysLog);
	}

	/**
	 * 시스템 로그정보 목록의 숫자를 조회한다.
	 *
	 * @param sysLog
	 * @return 건수
	 */
	public int selectSysLogInfCnt(SysLog sysLog) {
		return sysLogMapper.selectSysLogInfCnt(sysLog);
	}

	/**
	 * 시스템 로그 상세정보를 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog
	 */
	public SysLog selectSysLog(SysLog sysLog) {
		return sysLogMapper.selectSysLog(sysLog);
	}
}
