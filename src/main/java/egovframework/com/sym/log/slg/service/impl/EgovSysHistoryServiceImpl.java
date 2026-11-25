package egovframework.com.sym.log.slg.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import egovframework.com.sym.log.slg.service.EgovSysHistoryService;
import egovframework.com.sym.log.slg.service.SysHistory;
import egovframework.com.sym.log.slg.service.SysHistoryVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;

/**
 * @Class Name : EgovSysHistoryServiceImpl.java
 * @Description : 시스템 이력관리를 위한 서비스 구현 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 9.   이삼섭
 *    2026. 10. 27.             동일 밀리초 이력 ID 충돌 시 저장점 복원 후 재시도
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

	private static final int MAX_INSERT_ATTEMPTS = 10;

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

		TransactionStatus transaction;
		try {
			transaction = TransactionAspectSupport.currentTransactionStatus();
		} catch (NoTransactionException e) {
			// 프록시를 통하지 않는 직접 호출에는 복원할 트랜잭션이 없다.
			transaction = null;
		}

		for (int attempt = 1; ; attempt++) {
			history.setHistId(createHistId());
			Object savepoint = transaction == null ? null : transaction.createSavepoint();
			try {
				sysHistoryDAO.insertSysHistory(history);
				if (transaction != null) {
					transaction.releaseSavepoint(savepoint);
				}
				return null;
			} catch (DuplicateKeyException e) {
				// PostgreSQL 등은 중복 키 오류 후 저장점까지 복원해야 다음 INSERT가 가능하다.
				if (transaction != null) {
					transaction.rollbackToSavepoint(savepoint);
					transaction.releaseSavepoint(savepoint);
				}
				if (attempt >= MAX_INSERT_ATTEMPTS) {
					throw e;
				}
			}

			// 이전 ID에 1을 더하지 않고 실제 시각이 전진한 뒤 다시 생성한다.
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw e;
			}
		}
	}

	String createHistId() {
		return "HT_" + EgovStringUtil.getTimeStamp();
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
