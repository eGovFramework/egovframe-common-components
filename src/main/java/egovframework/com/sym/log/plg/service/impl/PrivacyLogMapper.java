package egovframework.com.sym.log.plg.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.log.plg.service.PrivacyLog;

/**
 * 개인정보조회 로그 관리를 위한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2026. 5. 28.   dasomel        XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("privacyLogMapper")
public interface PrivacyLogMapper {

	void insertPrivacyLog(PrivacyLog privacyLog);

	List<PrivacyLog> selectPrivacyLogList(PrivacyLog privacyLog);

	int selectPrivacyLogListCount(PrivacyLog privacyLog);

	PrivacyLog selectPrivacyLog(PrivacyLog privacyLog);
}
