package egovframework.com.utl.sys.nsm.service.impl;
import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrng;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngLog;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngLogVO;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 네트워크서비스 모니터링대상에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 네트워크서비스 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 네트워크서비스 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 */
@Repository("NtwrkSvcMntrngDAO")
public class NtwrkSvcMntrngDAO extends EgovComAbstractDAO {

	/**
	 * 주어진 조건에 맞는 네트워크서비스 모니터링 대상 목록을 불러온다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return List<NtwrkSvcMntrngVO> - 네트워크서비스 모니터링 대상 List
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public List<NtwrkSvcMntrngVO> selectNtwrkSvcMntrngList(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception{
		return selectList("NtwrkSvcMntrngDAO.selectNtwrkSvcMntrngList", ntwrkSvcMntrngVO);
	}

	/**
	 * 주어진 조건에 맞는 네트워크서비스 모니터링 대상을 불러온다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return NtwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public NtwrkSvcMntrngVO selectNtwrkSvcMntrng(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception{
		return (NtwrkSvcMntrngVO)selectOne("NtwrkSvcMntrngDAO.selectNtwrkSvcMntrng", ntwrkSvcMntrngVO);
	}

	/**
	 * 네트워크서비스 모니터링 대상 정보를 수정한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링 대상 model
	 * 
	 * @param ntwrkSvcMntrng
	 */
	public void updateNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception{
		update("NtwrkSvcMntrngDAO.updateNtwrkSvcMntrng", ntwrkSvcMntrng);
	}

	/**
	 * 네트워크서비스 모니터링 대상 정보를 등록한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링 대상 model
	 * 
	 * @param ntwrkSvcMntrng
	 */
	public void insertNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception{
		insert("NtwrkSvcMntrngDAO.insertNtwrkSvcMntrng", ntwrkSvcMntrng);
	}
	
	/**
	 * 네트워크서비스 모니터링대상 등록을 위한 중복 조회를 수행한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return int
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public int selectNtwrkSvcMntrngCheck(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception{
		return (Integer)selectOne("NtwrkSvcMntrngDAO.selectNtwrkSvcMntrngCheck", ntwrkSvcMntrngVO);
	}
	
	/**
	 * 네트워크서비스 모니터링 대상 정보를 삭제한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링 대상 model
	 * 
	 * @param ntwrkSvcMntrng
	 */
	public void deleteNtwrkSvcMntrng(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception{
		delete("NtwrkSvcMntrngDAO.deleteNtwrkSvcMntrng", ntwrkSvcMntrng);
	}

	/**
	 * 네트워크서비스 모니터링대상 목록에 대한 전체 건수를 조회한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return int
	 * 
	 * @param ntwrkSvcMntrngVO
	 */
	public int selectNtwrkSvcMntrngListCnt(NtwrkSvcMntrngVO ntwrkSvcMntrngVO) throws Exception{
		return (Integer)selectOne("NtwrkSvcMntrngDAO.selectNtwrkSvcMntrngListCnt", ntwrkSvcMntrngVO);
	}
	
	/**
	 * 네트워크서비스 모니터링 결과를 수정한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링 대상 model
	 * 
	 * @param ntwrkSvcMntrng
	 */
	public void updateNtwrkSvcMntrngSttus(NtwrkSvcMntrng ntwrkSvcMntrng) throws Exception{
		update("NtwrkSvcMntrngDAO.updateNtwrkSvcMntrngSttus", ntwrkSvcMntrng);
	}
	
	/**
	 * 주어진 조건에 맞는 네트워크서비스 모니터링 로그 목록을 불러온다.
	 * @param NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링 로그 VO
	 * @return  List<NtwrkSvcMntrngLogVO> - 네트워크서비스 모니터링 로그 List
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public List<NtwrkSvcMntrngLogVO> selectNtwrkSvcMntrngLogList(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) throws Exception{
		return selectList("NtwrkSvcMntrngDAO.selectNtwrkSvcMntrngLogList", ntwrkSvcMntrngLogVO);
	}
	
	/**
	 * 네트워크서비스 모니터링 로그 목록에 대한 전체 건수를 조회한다.
	 * @param NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링 로그 VO
	 * @return int
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public int selectNtwrkSvcMntrngLogListCnt(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) throws Exception{
		return (Integer)selectOne("NtwrkSvcMntrngDAO.selectNtwrkSvcMntrngLogListCnt", ntwrkSvcMntrngLogVO);
	}
	
	/**
	 * 주어진 조건에 맞는 네트워크서비스 모니터링 로그를 불러온다.
	 * @param NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링 로그 VO
	 * @return NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링 로그 VO
	 * 
	 * @param ntwrkSvcMntrngLogVO
	 */
	public NtwrkSvcMntrngLogVO selectNtwrkSvcMntrngLog(NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO) throws Exception{
		return (NtwrkSvcMntrngLogVO)selectOne("NtwrkSvcMntrngDAO.selectNtwrkSvcMntrngLog", ntwrkSvcMntrngLogVO);
	}
	
	/**
	 * 네트워크서비스 모니터링 로그 정보를 등록한다.
	 * @param NtwrkSvcMntrngLog - 네트워크서비스 모니터링 로그 model
	 * 
	 * @param ntwrkSvcMntrngLog
	 */
	public void insertNtwrkSvcMntrngLog(NtwrkSvcMntrngLog ntwrkSvcMntrngLog) throws Exception{
		insert("NtwrkSvcMntrngDAO.insertNtwrkSvcMntrngLog", ntwrkSvcMntrngLog);
	}

}