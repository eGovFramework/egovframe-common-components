package egovframework.com.sym.log.slg.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.log.slg.service.SysHistory;
import egovframework.com.sym.log.slg.service.SysHistoryVO;

/**
 * 시스템 이력정보를 관리하기 위한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2026. 5. 28.   dasomel        XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("sysHistoryMapper")
public interface SysHistoryMapper {

	int insertSysHistory(SysHistory history);

	void updateSysHistory(SysHistory history);

	void deleteSysHistory(SysHistory history);

	List<SysHistoryVO> selectSysHistoryList(SysHistoryVO historyVO);

	int selectSysHistoryListCnt(SysHistoryVO historyVO);

	SysHistoryVO selectSysHistory(SysHistoryVO historyVO);
}
