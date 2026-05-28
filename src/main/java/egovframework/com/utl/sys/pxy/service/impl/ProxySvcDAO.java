package egovframework.com.utl.sys.pxy.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.utl.sys.pxy.service.ProxyLog;
import egovframework.com.utl.sys.pxy.service.ProxySvc;

/**
 * 개요
 * - 프록시서비스정보 및 프록시로그정보에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 프록시서비스정보 및 프록시로그정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 프록시서비스정보의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:51
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.28  lee.m.j          최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("proxySvcDAO")
public interface ProxySvcDAO {

	/**
	 * 프록시서비스를 관리하기 위해 등록된 프록시정보 목록을 조회한다.
	 *
	 * @param proxySvc - 프록시서비스 Vo
	 * @return List - 프록시서비스 목록
	 */
	List<ProxySvc> selectProxySvcList(ProxySvc proxySvc);

	/**
	 * 프록시서비스 목록 총 개수를 조회한다.
	 *
	 * @param proxySvc - 프록시서비스 Vo
	 * @return int - 프록시서비스 카운트 수
	 */
	int selectProxySvcListTotCnt(ProxySvc proxySvc);

	/**
	 * 등록된 프록시서비스의 상세정보를 조회한다.
	 *
	 * @param proxySvc - 프록시서비스 Vo
	 * @return ProxySvc - 프록시서비스 Vo
	 */
	ProxySvc selectProxySvc(ProxySvc proxySvc);

	/**
	 * 프록시서비스를 신규로 등록한다.
	 *
	 * @param proxySvc - 프록시서비스 model
	 */
	int insertProxySvc(ProxySvc proxySvc);

	/**
	 * 기 등록된 프록시서비스를 수정한다.
	 *
	 * @param proxySvc - 프록시서비스 model
	 */
	int updateProxySvc(ProxySvc proxySvc);

	/**
	 * 기 등록된 프록시서비스를 삭제한다.
	 *
	 * @param proxySvc - 프록시서비스 model
	 */
	int deleteProxySvc(ProxySvc proxySvc);

	/**
	 * 프록시서비스를 모니터링하기 위해 등록된 프록시로그 목록을 조회한다.
	 *
	 * @param proxyLog - 프록시로그 Vo
	 * @return List - 프록시로그 목록
	 */
	List<ProxyLog> selectProxyLogList(ProxyLog proxyLog);

	/**
	 * 프록시로그 목록 총 개수를 조회한다.
	 *
	 * @param proxyLog - 프록시로그 Vo
	 * @return int - 프록시로그 카운트 수
	 */
	int selectProxyLogListTotCnt(ProxyLog proxyLog);

	/**
	 * 프록시로그를 생성한다.
	 *
	 * @param proxyLog - 프록시로그 model
	 */
	int insertProxyLog(ProxyLog proxyLog);

}
