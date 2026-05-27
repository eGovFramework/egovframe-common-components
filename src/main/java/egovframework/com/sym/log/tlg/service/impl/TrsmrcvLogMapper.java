package egovframework.com.sym.log.tlg.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.log.tlg.service.TrsmrcvLog;

/**
 * 송수신 로그 관리를 위한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.tlg)
 *    2026. 5. 28.   dasomel        XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("trsmrcvLogMapper")
public interface TrsmrcvLogMapper {

	void logInsertTrsmrcvLog(TrsmrcvLog trsmrcvLog);

	void logInsertTrsmrcvLogSummary();

	void logDeleteTrsmrcvLogSummary();

	TrsmrcvLog selectTrsmrcvLog(TrsmrcvLog trsmrcvLog);

	List<TrsmrcvLog> selectTrsmrcvLogInf(TrsmrcvLog trsmrcvLog);

	int selectTrsmrcvLogInfCnt(TrsmrcvLog trsmrcvLog);
}
