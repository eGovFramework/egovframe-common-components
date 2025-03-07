package egovframework.com.utl.sys.srm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.utl.sys.srm.service.ServerResrceMntrng;
import egovframework.com.utl.sys.srm.service.ServerResrceMntrngVO;

/**
 * 개요
 * - 서버자원모니터링에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버자원모니터링에 대한 등록, 조회 기능을 제공한다.
 * @author lee.m.j
 * @version 1.0
 * @created 06-9-2010 오전 11:24:00
 */
@Repository("serverResrceMntrngDAO")
public class ServerResrceMntrngDAO extends EgovComAbstractDAO {

	/**
	 * 서버자원모니터링의 로그정보 목록을 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return List - 서버자원모니터링의 로그 목록
	 */
	public List<ServerResrceMntrngVO> selectServerResrceMntrngList(ServerResrceMntrngVO serverResrceMntrngVO)throws Exception {
		return selectList("serverResrceMntrngDAO.selectServerResrceMntrngList", serverResrceMntrngVO);
	}

	/**
	 * 서버자원모니터링의 로그정보 목록 총 개수를 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return int - 서버자원모니터링의 로그 카운트 수
	 */
	public int selectServerResrceMntrngListTotCnt(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		return (Integer)selectOne("serverResrceMntrngDAO.selectServerResrceMntrngListTotCnt", serverResrceMntrngVO);
	}

	/**
	 * 서버자원모니터링 로그의 상세정보를 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return ServerResrceMntrngVO - 서버자원모니터링 Vo
	 */
	public ServerResrceMntrngVO selectServerResrceMntrng(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		return (ServerResrceMntrngVO) selectOne("serverResrceMntrngDAO.selectServerResrceMntrng", serverResrceMntrngVO);
	}

	/**
	 * 서버자원모니터링 로그정보를 신규로 등록한다.
	 * @param serverResrceMntrng - 서버자원모니터링 model
	 */
	public void insertServerResrceMntrng(ServerResrceMntrng serverResrceMntrng) throws Exception {
		insert("serverResrceMntrngDAO.insertServerResrceMntrng", serverResrceMntrng);
	}

	/**
	 * 서버자원모티너링 대상서버의 목록을 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return ServerResrceMntrngVO - 서버자원모니터링 Vo
	 */
	public List<ServerResrceMntrngVO> selectMntrngServerList(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		return selectList("serverResrceMntrngDAO.selectMntrngServerList", serverResrceMntrngVO);
	}
	
	/**
	 * 서버자원모티너링 대상서버 목록 총 개수를 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return int - 서버자원모니터링의 로그 카운트 수
	 */
	public int selectMntrngServerListTotCnt(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		return (Integer)selectOne("serverResrceMntrngDAO.selectMntrngServerListTotCnt", serverResrceMntrngVO);
	}	
}