package egovframework.com.sym.log.slg.service;

import java.util.Map;



/**
 * @Class Name  : EgovSysHistoryService.java
 * @Description : 시스템 처리 이력관리를 위한 서비스 인터페이스
 * @Modification Information
 *
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.03.06       이삼섭                  최초 생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 03. 06
 * @version 1.0
 * @see
 *
 */
public interface EgovSysHistoryService {

	/**
	 * 시스템 이력정보를 등록한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 * @return
	 * @throws Exception
	 */
	public Map<?, ?> insertSysHistory(SysHistory history) throws Exception;

	/**
	 * 시스템 이력정보를 수정한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 * @return
	 * @throws Exception
	 */
	public void updateSysHistory(SysHistory history) throws Exception;

	/**
	 * 시스템 이력정보를 삭제한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 * @return
	 * @throws Exception
	 */
	public void deleteSysHistory(SysHistory history) throws Exception;

	/**
	 * 시스템 이력정보를 상세조회한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 * @return
	 * @throws Exception
	 */
	public SysHistoryVO selectSysHistory(SysHistoryVO historyVO) throws Exception;

	/**
	 * 시스템 이력정보 목록을 조회한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 * @return
	 * @throws Exception
	 */
	public Map<?, ?> selectSysHistoryList(SysHistoryVO historyVO) throws Exception;

}
