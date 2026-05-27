package egovframework.com.sym.log.lgm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.log.lgm.service.SysLog;

/**
 * 로그관리(시스템)를 위한 Mapper 인터페이스
 *
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
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.lgm)
 *    2026. 5. 28.   dasomel        XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("SysLogDAO")
public interface SysLogMapper {

	/**
	 * 시스템 로그정보를 생성한다.
	 *
	 * @param sysLog
	 */
	void logInsertSysLog(SysLog sysLog);

	/**
	 * 시스템 로그 전날 요약 정보를 등록한다.
	 */
	void logInsertSysLogSummary();

	/**
	 * 시스템 로그 6개월 이전 로그를 삭제한다.
	 */
	void logDeleteSysLogSummary();

	/**
	 * 시스템 로그목록을 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog 목록
	 */
	List<SysLog> selectSysLogInf(SysLog sysLog);

	/**
	 * 시스템 로그정보 목록의 숫자를 조회한다.
	 *
	 * @param sysLog
	 * @return 건수
	 */
	int selectSysLogInfCnt(SysLog sysLog);

	/**
	 * 시스템 로그 상세정보를 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog
	 */
	SysLog selectSysLog(SysLog sysLog);

}
