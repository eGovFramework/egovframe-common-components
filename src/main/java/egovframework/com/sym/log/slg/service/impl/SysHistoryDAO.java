package egovframework.com.sym.log.slg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.sym.log.slg.service.SysHistory;
import egovframework.com.sym.log.slg.service.SysHistoryVO;
import jakarta.annotation.Resource;

/**
 * 시스템 이력정보를 관리하기 위한 데이터 처리 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 9.   이삼섭         최초생성
 *    2026. 5. 28.  dasomel       @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("sysHistoryDAO")
public class SysHistoryDAO {

	@Resource(name = "sysHistoryMapper")
	private SysHistoryMapper sysHistoryMapper;

	public int insertSysHistory(SysHistory history) {
		return sysHistoryMapper.insertSysHistory(history);
	}

	public void updateSysHistory(SysHistory history) {
		sysHistoryMapper.updateSysHistory(history);
	}

	public void deleteSysHistory(SysHistory history) {
		sysHistoryMapper.deleteSysHistory(history);
	}

	public List<SysHistoryVO> selectSysHistorList(SysHistoryVO historyVO) {
		return sysHistoryMapper.selectSysHistoryList(historyVO);
	}

	public int selectSysHistortListCnt(SysHistoryVO historyVO) {
		return sysHistoryMapper.selectSysHistoryListCnt(historyVO);
	}

	public SysHistoryVO selectSysHistory(SysHistoryVO historyVO) {
		return sysHistoryMapper.selectSysHistory(historyVO);
	}
}
