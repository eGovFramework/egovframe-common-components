package egovframework.com.sym.log.slg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import egovframework.com.sym.log.slg.service.EgovSysHistoryService;
import egovframework.com.sym.log.slg.service.SysHistory;
import egovframework.com.sym.log.slg.service.SysHistoryVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * @Class Name : EgovSysHistoryServiceImpl.java
 * @Description : 시스템 이력관리를 위한 서비스 구현 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 9.   이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 9.
 * @version
 * @see
 *
 */
@Service("EgovSysHistoryService")
public class EgovSysHistoryServiceImpl extends EgovAbstractServiceImpl implements
		EgovSysHistoryService {

	@Resource(name="sysHistoryDAO")
	private SysHistoryDAO sysHistoryDAO;

	/**
	 * 시스템 이력정보를 등록한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<?, ?> insertSysHistory(SysHistory history) throws Exception {

//		String histId = "HIST_"+20091021144553005; yyyyMMddhhmmssSSS
		String histId = "HT_"+EgovStringUtil.getTimeStamp();
		history.setHistId(histId);

		sysHistoryDAO.insertSysHistory(history);

		return null;
	}

	/**
	 * 시스템 이력정보를 수정한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateSysHistory(SysHistory history) throws Exception {

		sysHistoryDAO.updateSysHistory(history);
	}

	/**
	 * 시스템 이력정보를 삭제한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 * @return
	 * @throws Exception
	 */
	@Override
	public void deleteSysHistory(SysHistory history) throws Exception {

		sysHistoryDAO.deleteSysHistory(history);
	}

	/**
     * 시스템 이력정보 목록을 조회한다.
     * 
     * @param history - 시스템 이력정보가 담긴 모델 객체
     * @return
     * @throws Exception
     */
    @Override
    public void selectSysHistoryList(SysHistoryVO historyVO, ModelMap model) throws Exception {
        List<SysHistoryVO> resultList = sysHistoryDAO.selectSysHistorList(historyVO);
        int totCnt = sysHistoryDAO.selectSysHistortListCnt(historyVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("resultCnt", totCnt);
    }

	/**
	 * 시스템 이력정보를 상세조회한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 * @return
	 * @throws Exception
	 */
	@Override
	public SysHistoryVO selectSysHistory(SysHistoryVO historyVO) throws Exception {
		return sysHistoryDAO.selectSysHistory(historyVO);
	}

}
