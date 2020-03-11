package egovframework.com.utl.sys.prm.service;

import java.util.List;
import java.util.Map;

/**
 * 개요
 * - PROCESS모니터링에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - PROCESS모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - PROCESS모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 08-9-2010 오후 3:54:45
 */
public interface EgovProcessMonService {

	/**
	 * 등록된 PROCESS모니터링 목록을 조회한다.
	 * @param processMonVO - PROCESS모니터링 Vo
	 * @return List - PROCESS모니터링 목록
	 * 
	 * @param processMonVO
	 */
	public List<ProcessMon> selectProcessMonList(ProcessMonVO processMonVO) throws Exception;
	
	/**
	 * PROCESS모니터링 목록 총 갯수를 조회한다.
	 * @param HttpMonVO - PROCESS모니터링 Vo
	 * @return int - PROCESS모니터링 토탈 카운트 수
	 * 
	 * @param httpMonVO
	 */
	int selectProcessMonTotCnt(ProcessMonVO searchVO) throws Exception;	

	/**
	 * 등록된 PROCESS모니터링의 상세정보를 조회한다.
	 * @param processMonVO - PROCESS모니터링 Vo
	 * @return processMonVO - PROCESS모니터링 Vo
	 * 
	 * @param processMonVO
	 */
	ProcessMonVO selectProcessMon(ProcessMonVO processMonVO) throws Exception;

	/**
	 * PROCESS모니터링 정보를 신규로 등록한다.
	 * @param processNm - PROCESS모니터링 model
	 * 
	 * @param processNm
	 */
	public void insertProcessMon(ProcessMon processMon) throws Exception;

	/**
	 * 기 등록된 PROCESS모니터링 정보를 수정한다.
	 * @param processNm - PROCESS모니터링 model
	 * 
	 * @param processNm
	 */
	public void updateProcessMon(ProcessMon processMon) throws Exception;

	/**
	 * 기 등록된 PROCESS모니터링 정보를 삭제한다.
	 * @param processNm - PROCESS모니터링 model
	 * 
	 * @param processNm
	 */
	public void deleteProcessMon(ProcessMon processMon) throws Exception;

	/**
	 * 프로세스 모니터링로그 목록을 조회한다.
	 * @param ProcessMonVO - 프로세스모니터링로그 VO
	 * @return  List<ProcessMonVO> - 프로세스모니터링로그 List
	 * 
	 * @param processMonVO
	 */
	public Map<String, Object> selectProcessMonLogList(ProcessMonLogVO processMonLogVO) throws Exception;

	/**
	 * 프로세스 모니터링로그의 상세정보를 조회한다.
	 * @param ProcessMonVO - 프로세스모니터링로그 model
	 * @return  ProcessMonVO - 프로세스모니터링로그 model
	 * 
	 * @param processMonVO
	 */
	public ProcessMonLogVO selectProcessMonLog(ProcessMonLogVO processMonLogVO) throws Exception;
	
	/**
	 * 프로세스 모니터링 결과를 수정한다.
	 * @param ProcessMon - 프로세스 모니터링대상 model
	 * 
	 * @param processMon
	 */
	public void updateProcessMonSttus(ProcessMon processMon) throws Exception;
	
	/**
	 * 프로세스 모니터링로그를 등록한다.
	 * @param ProcessMonLog - 프로세스 모니터링로그 model
	 * 
	 * @param processMonLog
	 */
	public void insertProcessMonLog(ProcessMonLog processMonLog) throws Exception;	

}