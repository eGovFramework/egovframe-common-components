package egovframework.com.utl.sys.prm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.utl.sys.prm.service.EgovProcessMonService;
import egovframework.com.utl.sys.prm.service.ProcessMon;
import egovframework.com.utl.sys.prm.service.ProcessMonLog;
import egovframework.com.utl.sys.prm.service.ProcessMonLogVO;
import egovframework.com.utl.sys.prm.service.ProcessMonVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - PROCESS모니터링에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - PROCESS모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - PROCESS모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 08-9-2010 오후 3:54:46
 */

@Service("EgovProcessMonService")
public class EgovProcessMonServiceImpl extends EgovAbstractServiceImpl implements EgovProcessMonService {

	@Resource(name = "ProcessMonDAO")
	private ProcessMonDAO processMonDAO;

	@Resource(name="egovProcessMonIdGnrService")
	private EgovIdGnrService idgenServiceProcessMon;

	@Resource(name="egovProcessMonLogIdGnrService")
	private EgovIdGnrService idgenServiceProcessMonLog;

	/**
	 * 등록된 PROCESS모니터링 목록을 조회한다.
	 * @param processMonVO - PROCESS모니터링 Vo
	 * @return List - PROCESS모니터링 목록
	 *
	 * @param processMonVO
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List selectProcessMonList(ProcessMonVO processMonVO) throws Exception {
        return processMonDAO.selectProcessMonList(processMonVO);
	}

	/**
	 * PROCESS모니터링 목록 총 갯수를 조회한다.
	 * @param processMonVO - PROCESS모니터링 Vo
	 * @return int - PROCESS모니터링 토탈 카운트 수
	 *
	 * @param processMonVO
	 */
	@Override
	public int selectProcessMonTotCnt(ProcessMonVO processMonVO) throws Exception {
        return processMonDAO.selectProcessMonTotCnt(processMonVO);
	}

	/**
	 * 등록된 PROCESS모니터링의 상세정보를 조회한다.
	 * @param processMonVO - PROCESS모니터링 Vo
	 * @return processMonVO - PROCESS모니터링 Vo
	 *
	 * @param processMonVO
	 */
	@Override
	public ProcessMonVO selectProcessMon(ProcessMonVO processMonVO) throws Exception {
		return processMonDAO.selectProcessMon(processMonVO);
	}

	/**
	 * PROCESS모니터링 정보를 신규로 등록한다.
	 * @param processNm - PROCESS모니터링 model
	 *
	 * @param processNm
	 */
	@Override
	public void insertProcessMon(ProcessMon processMon) throws Exception {
		processMon.setProcessId(idgenServiceProcessMon.getNextStringId());
		processMonDAO.insertProcessMon(processMon);
	}

	/**
	 * 기 등록된 PROCESS모니터링 정보를 수정한다.
	 * @param processNm - PROCESS모니터링 model
	 *
	 * @param processNm
	 */
	@Override
	public void updateProcessMon(ProcessMon processMon) throws Exception {
		processMonDAO.updateProcessMon(processMon);
	}

	/**
	 * 기 등록된 PROCESS모니터링 정보를 삭제한다.
	 * @param processNm - PROCESS모니터링 model
	 *
	 * @param processNm
	 */
	@Override
	public void deleteProcessMon(ProcessMon processMon) throws Exception {
		processMonDAO.deleteProcessMon(processMon);
	}

	/**
	 * 프로세스 모니터링로그 목록을 조회한다.
	 * @param ProcessMonVO - 프로세스모니터링로그 VO
	 * @return  List<ProcessMonVO> - 프로세스모니터링로그 List
	 *
	 * @param processMonLogVO
	 */
	@Override
	public Map<String, Object> selectProcessMonLogList(ProcessMonLogVO processMonLogVO) throws Exception {
		List<ProcessMonLogVO> result = processMonDAO.selectProcessMonLogList(processMonLogVO);
		int cnt = processMonDAO.selectProcessMonLogTotCnt(processMonLogVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 프로세스 모니터링로그의 상세정보를 조회한다.
	 * @param ProcessMonVO - 프로세스모니터링로그 model
	 * @return  ProcessMonVO - 프로세스모니터링로그 model
	 *
	 * @param processMonLogVO
	 */
	@Override
	public ProcessMonLogVO selectProcessMonLog(ProcessMonLogVO processMonLogVO) throws Exception {
		return processMonDAO.selectProcessMonLog(processMonLogVO);
	}

	/**
	 * 프로세스 모니터링로그를 등록한다.
	 * @param processMonLog - 프로세스 모니터링로그 model
	 *
	 * @param processMonLog
	 */
	@Override
	public void insertProcessMonLog(ProcessMonLog processMonLog) throws Exception{
		processMonDAO.insertProcessMonLog(processMonLog);
	}

	/**
	 * 프로세스 모니터링 결과를 수정한다.
	 * @param processMonLog - 프로세스 모니터링대상 model
	 *
	 * @param processMonLog
	 */
	@Override
	public void updateProcessMonSttus(ProcessMon processMon) throws Exception{
		processMonDAO.updateProcessMonSttus(processMon);

		ProcessMonLog processMonLog = new ProcessMonLog();
		processMonLog.setProcessId(processMon.getProcessId());
		processMonLog.setLogId(idgenServiceProcessMonLog.getNextStringId());
		processMonLog.setProcessNm(processMon.getProcessNm());
		processMonLog.setProcsSttus(processMon.getProcsSttus());
		processMonLog.setCreatDt(processMon.getCreatDt());
		processMonLog.setLogInfo(processMon.getLogInfo());
		processMonLog.setMngrNm(processMon.getMngrNm());
		processMonLog.setMngrEmailAddr(processMon.getMngrEmailAddr());
		processMonLog.setFrstRegisterId(processMon.getFrstRegisterId());
		processMonLog.setFrstRegisterPnttm(processMon.getFrstRegisterPnttm());
		processMonLog.setLastUpdusrId(processMon.getLastUpdusrId());
		insertProcessMonLog(processMonLog);
	}

}