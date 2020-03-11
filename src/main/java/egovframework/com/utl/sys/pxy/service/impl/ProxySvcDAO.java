package egovframework.com.utl.sys.pxy.service.impl;
import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.utl.sys.pxy.service.ProxyLog;
import egovframework.com.utl.sys.pxy.service.ProxyLogVO;
import egovframework.com.utl.sys.pxy.service.ProxySvc;
import egovframework.com.utl.sys.pxy.service.ProxySvcVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 프록시서비스정보 및 프록시로그정보에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 프록시서비스정보 및 프록시로그정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 프록시서비스정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:51
 */
@Repository("proxySvcDAO")
public class ProxySvcDAO extends EgovComAbstractDAO {

	/**
	 * 프록시서비스를 관리하기 위해 등록된 프록시정보 목록을 조회한다.
	 * @param proxySvcVO - 프록시서비스 Vo
	 * @return List - 프록시서비스 목록
	 */
	public List<ProxySvcVO> selectProxySvcList(ProxySvcVO proxySvcVO) throws Exception {
		return selectList("proxySvcDAO.selectProxySvcList", proxySvcVO);
	}

	/**
	 * 프록시서비스 목록 총 갯수를 조회한다.
	 * @param proxySvcVO - 프록시서비스 Vo
	 * @return int - 프록시서비스 카운트 수
	 */
	public int selectProxySvcListTotCnt(ProxySvcVO proxySvcVO) throws Exception {
		return (Integer)selectOne("proxySvcDAO.selectProxySvcListTotCnt", proxySvcVO);
	}

	/**
	 * 등록된 프록시서비스의 상세정보를 조회한다.
	 * @param proxySvcVO - 프록시서비스 Vo
	 * @return proxySvcVO - 프록시서비스 Vo
	 */
	public ProxySvcVO selectProxySvc(ProxySvcVO proxySvcVO) throws Exception {
		return (ProxySvcVO) selectOne("proxySvcDAO.selectProxySvc", proxySvcVO);
	}

	/**
	 * 프록시서비스를 신규로 등록한다.
	 * @param proxySvc - 프록시서비스 model
	 */
	public void insertProxySvc(ProxySvc proxySvc) throws Exception {
		insert("proxySvcDAO.insertProxySvc", proxySvc);
	}

	/**
	 * 기 등록된 프록시서비스를 수정한다.
	 * @param proxySvc - 프록시서비스 model
	 */
	public void updateProxySvc(ProxySvc proxySvc) throws Exception {
		update("proxySvcDAO.updateProxySvc", proxySvc);
	}

	/**
	 * 기 등록된 프록시서비스를 삭제한다.
	 * @param proxySvc - 프록시서비스 model
	 */
	public void deleteProxySvc(ProxySvc proxySvc) throws Exception {
		delete("proxySvcDAO.deleteProxySvc", proxySvc);
	}

	/**
	 * 프록시서비스를 모니터링하기 위해 등록된 프록시로그 목록을 조회한다.
	 * @param proxyLogVO - 프록시로그 Vo
	 * @return List - 프록시로그 목록
	 */
	public List<ProxyLogVO> selectProxyLogList(ProxyLogVO proxyLogVO) throws Exception {
		return selectList("proxySvcDAO.selectProxyLogList", proxyLogVO);
	}

	/**
	 * 프록시로그 목록 총 갯수를 조회한다.
	 * @param proxyLogVO - 프록시로그 Vo
	 * @return int - 프록시로그 카운트 수
	 */
	public int selectProxyLogListTotCnt(ProxyLogVO proxyLogVO) throws Exception {
		return (Integer)selectOne("proxySvcDAO.selectProxyLogListTotCnt", proxyLogVO);
	}
	
	/**
	 * 프록시로그를 생성한다.
	 * @param proxyLog - 프록시로그 model
	 */
	public void insertProxyLog(ProxyLog proxyLog) throws Exception {
		insert("proxySvcDAO.insertProxyLog", proxyLog);
	}

}