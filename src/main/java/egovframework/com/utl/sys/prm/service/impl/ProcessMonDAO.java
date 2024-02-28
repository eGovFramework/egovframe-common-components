package egovframework.com.utl.sys.prm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.utl.sys.prm.service.ProcessMon;
import egovframework.com.utl.sys.prm.service.ProcessMonLog;
import egovframework.com.utl.sys.prm.service.ProcessMonLogVO;
import egovframework.com.utl.sys.prm.service.ProcessMonVO;

/**
 * 개요
 * - PROCESS모니터링에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - PROCESS모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - PROCESS모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 08-9-2010 오후 3:54:46
 */

@Repository("ProcessMonDAO")
public class ProcessMonDAO extends EgovComAbstractDAO {

	/**
     * 등록된 PROCESS모니터링 목록을 조회한다.
     *
     * @param processMonVO - PROCESS모니터링 Vo
     * @return List - PROCESS모니터링 목록
     */
    public List<ProcessMonVO> selectProcessMonList(ProcessMonVO processMonVO) throws Exception {
        return selectList("ProcessMonDAO.selectProcessMonList", processMonVO);
    }

    /**
     * PROCESS모니터링 목록 총 개수를 조회한다.
     *
     * @param ProcessMonVO - PROCESS모니터링 Vo
     * @return int - PROCESS모니터링 토탈 카운트 수
     */
    public int selectProcessMonTotCnt(ProcessMonVO processMonVO) throws Exception {
        return selectOne("ProcessMonDAO.selectProcessMonTotCnt", processMonVO);
    }

    /**
     * 등록된 PROCESS모니터링의 상세정보를 조회한다.
     *
     * @param processMonVO - PROCESS모니터링 Vo
     * @return processMonVO - PROCESS모니터링 Vo
     */
    public ProcessMonVO selectProcessMon(ProcessMonVO processMonVO) throws Exception {
        return selectOne("ProcessMonDAO.selectProcessMon", processMonVO);
    }

    /**
     * PROCESS모니터링 정보를 신규로 등록한다.
     *
     * @param processNm - PROCESS모니터링 model
     * @return int - 등록 결과
     */
    public int insertProcessMon(ProcessMon processMon) throws Exception {
        return insert("ProcessMonDAO.insertProcessMon", processMon);
    }

    /**
     * 기 등록된 PROCESS모니터링 정보를 수정한다.
     *
     * @param processNm - PROCESS모니터링 model
     * @return int - 수정 결과
     */
    public int updateProcessMon(ProcessMon processMon) throws Exception {
        return update("ProcessMonDAO.updateProcessMon", processMon);
    }

    /**
     * 기 등록된 PROCESS모니터링 정보를 삭제한다.
     *
     * @param processNm - PROCESS모니터링 model
     * @return int - 삭제 결과
     */
    public int deleteProcessMon(ProcessMon processMon) throws Exception {
        return delete("ProcessMonDAO.deleteProcessMon", processMon);
    }

    /**
     * 프로세스 모니터링로그 목록을 조회한다.
     *
     * @param ProcessMonVO - 프로세스모니터링로그 VO
     * @return List<ProcessMonLogVO> - 프로세스모니터링로그 List
     */
    public List<ProcessMonLogVO> selectProcessMonLogList(ProcessMonLogVO processMonLogVO) throws Exception {
        return selectList("ProcessMonDAO.selectProcessMonLogList", processMonLogVO);
    }

    /**
     * PROCESS모니터링로그 목록 총 개수를 조회한다.
     *
     * @param ProcessMonVO - PROCESS모니터링로그 Vo
     * @return int - PROCESS모니터링로그 토탈 카운트 수
     */
    public int selectProcessMonLogTotCnt(ProcessMonLogVO processMonLogVO) throws Exception {
        return selectOne("ProcessMonDAO.selectProcessMonLogTotCnt", processMonLogVO);
    }

    /**
     * 프로세스 모니터링로그의 상세정보를 조회한다.
     *
     * @param ProcessMonVO - 프로세스모니터링로그 model
     * @return ProcessMonVO - 프로세스모니터링로그 model
     */
    public ProcessMonLogVO selectProcessMonLog(ProcessMonLogVO processMonLogVO) {
        return selectOne("ProcessMonDAO.selectProcessMonLog", processMonLogVO);
    }

    /**
     * PROCESS모니터링로그 대상 정보를 등록한다.
     *
     * @param ProcessMonLog - 파일시스템모니터링 대상 model
     * @return int - 등록 결과
     */
    public int insertProcessMonLog(ProcessMonLog processMonLog) throws Exception {
        return insert("ProcessMonDAO.insertProcessMonLog", processMonLog);
    }

    /**
     * 프로세스 모니터링 결과 정보를 수정한다.
     *
     * @param ProcessMon - 프로세스 대상 model
     * @return int - 수정 결과
     */
    public int updateProcessMonSttus(ProcessMon processMon) throws Exception {
        return update("ProcessMonDAO.updateProcessMonSttus", processMon);
    }
}