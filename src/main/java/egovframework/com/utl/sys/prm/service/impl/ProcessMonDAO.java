package egovframework.com.utl.sys.prm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.utl.sys.prm.service.ProcessMon;
import egovframework.com.utl.sys.prm.service.ProcessMonLog;
import egovframework.com.utl.sys.prm.service.ProcessMonLogVO;
import egovframework.com.utl.sys.prm.service.ProcessMonVO;

/**
 * 개요
 * - PROCESS모니터링에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - PROCESS모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - PROCESS모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * @author 박종선
 * @version 1.0
 * @created 08-9-2010 오후 3:54:46
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.09.08  박종선           최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("ProcessMonDAO")
public interface ProcessMonDAO {

	/**
	 * 등록된 PROCESS모니터링 목록을 조회한다.
	 *
	 * @param processMonVO - PROCESS모니터링 Vo
	 * @return List - PROCESS모니터링 목록
	 */
	List<ProcessMonVO> selectProcessMonList(ProcessMonVO processMonVO);

	/**
	 * PROCESS모니터링 목록 총 개수를 조회한다.
	 *
	 * @param processMonVO - PROCESS모니터링 Vo
	 * @return int - PROCESS모니터링 토탈 카운트 수
	 */
	int selectProcessMonTotCnt(ProcessMonVO processMonVO);

	/**
	 * 등록된 PROCESS모니터링의 상세정보를 조회한다.
	 *
	 * @param processMonVO - PROCESS모니터링 Vo
	 * @return ProcessMonVO - PROCESS모니터링 Vo
	 */
	ProcessMonVO selectProcessMon(ProcessMonVO processMonVO);

	/**
	 * PROCESS모니터링 정보를 신규로 등록한다.
	 *
	 * @param processMon - PROCESS모니터링 model
	 * @return int - 등록 결과
	 */
	int insertProcessMon(ProcessMon processMon);

	/**
	 * 기 등록된 PROCESS모니터링 정보를 수정한다.
	 *
	 * @param processMon - PROCESS모니터링 model
	 * @return int - 수정 결과
	 */
	int updateProcessMon(ProcessMon processMon);

	/**
	 * 기 등록된 PROCESS모니터링 정보를 삭제한다.
	 *
	 * @param processMon - PROCESS모니터링 model
	 * @return int - 삭제 결과
	 */
	int deleteProcessMon(ProcessMon processMon);

	/**
	 * 프로세스 모니터링로그 목록을 조회한다.
	 *
	 * @param processMonLogVO - 프로세스모니터링로그 VO
	 * @return List - 프로세스모니터링로그 List
	 */
	List<ProcessMonLogVO> selectProcessMonLogList(ProcessMonLogVO processMonLogVO);

	/**
	 * PROCESS모니터링로그 목록 총 개수를 조회한다.
	 *
	 * @param processMonLogVO - PROCESS모니터링로그 Vo
	 * @return int - PROCESS모니터링로그 토탈 카운트 수
	 */
	int selectProcessMonLogTotCnt(ProcessMonLogVO processMonLogVO);

	/**
	 * 프로세스 모니터링로그의 상세정보를 조회한다.
	 *
	 * @param processMonLogVO - 프로세스모니터링로그 model
	 * @return ProcessMonLogVO - 프로세스모니터링로그 model
	 */
	ProcessMonLogVO selectProcessMonLog(ProcessMonLogVO processMonLogVO);

	/**
	 * PROCESS모니터링로그 대상 정보를 등록한다.
	 *
	 * @param processMonLog - PROCESS모니터링로그 model
	 * @return int - 등록 결과
	 */
	int insertProcessMonLog(ProcessMonLog processMonLog);

	/**
	 * 프로세스 모니터링 결과 정보를 수정한다.
	 *
	 * @param processMon - 프로세스 model
	 * @return int - 수정 결과
	 */
	int updateProcessMonSttus(ProcessMon processMon);

}
