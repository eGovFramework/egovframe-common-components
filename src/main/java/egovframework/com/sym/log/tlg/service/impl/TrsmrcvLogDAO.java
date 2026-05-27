package egovframework.com.sym.log.tlg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.sym.log.tlg.service.TrsmrcvLog;
import jakarta.annotation.Resource;

/**
 * @Class Name : TrsmrcvLogDAO.java
 * @Description : 송수신 로그 관리를 위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.tlg)
 *    2026. 5. 28.   dasomel        @EgovMapper 인터페이스 위임 방식으로 전환
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 */
@Repository("trsmrcvLogDAO")
public class TrsmrcvLogDAO {

	@Resource(name = "trsmrcvLogMapper")
	private TrsmrcvLogMapper trsmrcvLogMapper;

	public void logInsertTrsmrcvLog(TrsmrcvLog trsmrcvLog) {
		trsmrcvLogMapper.logInsertTrsmrcvLog(trsmrcvLog);
	}

	public void logInsertTrsmrcvLogSummary() {
		trsmrcvLogMapper.logInsertTrsmrcvLogSummary();
		trsmrcvLogMapper.logDeleteTrsmrcvLogSummary();
	}

	public TrsmrcvLog selectTrsmrcvLog(TrsmrcvLog trsmrcvLog) {
		return trsmrcvLogMapper.selectTrsmrcvLog(trsmrcvLog);
	}

	public List<TrsmrcvLog> selectTrsmrcvLogInf(TrsmrcvLog trsmrcvLog) {
		return trsmrcvLogMapper.selectTrsmrcvLogInf(trsmrcvLog);
	}

	public int selectTrsmrcvLogInfCnt(TrsmrcvLog trsmrcvLog) {
		return trsmrcvLogMapper.selectTrsmrcvLogInfCnt(trsmrcvLog);
	}
}
