package egovframework.com.sym.log.slg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.log.slg.service.SysHistory;
import egovframework.com.sym.log.slg.service.SysHistoryVO;

/**
 * @Class Name : SysHistoryDAO.java
 * @Description : 시스템 이력정보를 관리하기 위한 데이터 처리 클래스
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
@Repository("sysHistoryDAO")
public class SysHistoryDAO extends EgovComAbstractDAO {


	/**
	 * 시스템 이력정보를 생성한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 */
	public int insertSysHistory(SysHistory history) throws Exception{
		return (Integer)insert("SysHistoryDAO.insertSysHistory", history);
	}


	/**
	 * 시스템 이력정보를 수정한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 */
	public void updateSysHistory(SysHistory history) throws Exception{
		update("SysHistoryDAO.updateSysHistory", history);
	}

	/**
	 * 시스템 이력정보를 삭제한다.
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 */
	public void deleteSysHistory(SysHistory history) throws Exception{
		delete("SysHistoryDAO.deleteSysHistory", history);
	}


	/**
	 * 시스템 이력정보 목록을 조회한다.
	 *
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 */
	public List<SysHistoryVO> selectSysHistorList(SysHistoryVO historyVO) throws Exception{
		return selectList("SysHistoryDAO.selectSysHistoryList", historyVO);
	}

	/**
	 * 시스템 이력정보 목록의 글 개수를 조회한다.
	 * @param history
	 * @return
	 * @throws Exception
	 */
	public int selectSysHistortListCnt(SysHistoryVO historyVO) throws Exception{
		return (Integer)selectOne("SysHistoryDAO.selectSysHistoryListCnt", historyVO);
	}

	/**
	 * 시스템 이력정보를 조회한다.
	 *
	 * @param history - 시스템 이력정보가 담긴 모델 객체
	 */
	public SysHistoryVO selectSysHistory(SysHistoryVO historyVO) throws Exception{

		return (SysHistoryVO) selectOne("SysHistoryDAO.selectSysHistory", historyVO);
	}



}
