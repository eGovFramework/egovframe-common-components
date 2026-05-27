package egovframework.com.sym.log.plg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.sym.log.plg.service.PrivacyLog;
import jakarta.annotation.Resource;

/**
 * 개인정보조회 로그 관리를 위한 데이터 접근 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2026. 5. 28.   dasomel        @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("privacyLogDAO")
public class PrivacyLogDAO {

	@Resource(name = "privacyLogMapper")
	private PrivacyLogMapper privacyLogMapper;

	public void insertPrivacyLog(PrivacyLog privacyLog) {
		privacyLogMapper.insertPrivacyLog(privacyLog);
	}

	public List<PrivacyLog> selectPrivacyLogList(PrivacyLog privacyLog) {
		return privacyLogMapper.selectPrivacyLogList(privacyLog);
	}

	public int selectPrivacyLogListCount(PrivacyLog privacyLog) {
		return privacyLogMapper.selectPrivacyLogListCount(privacyLog);
	}

	public PrivacyLog selectPrivacyLog(PrivacyLog privacyLog) {
		return privacyLogMapper.selectPrivacyLog(privacyLog);
	}
}
