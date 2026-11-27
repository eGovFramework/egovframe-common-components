package egovframework.com.utl.sys.pxy.service;

import java.util.List;

/**
 * 개요 - 프록시서비스정보에 대한 Service Interface를 정의한다.
 *
 * 상세내용 - 프록시서비스정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다. - 프록시서비스정보의 조회기능은 목록조회, 상세조회로
 * 구분된다.
 *
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:27
 */
public interface EgovProxySvcService {
	/**
     * 프록시서비스를 관리하기 위해 등록된 프록시정보 목록을 조회한다.
     *
     * @param proxySvc - 프록시서비스 Vo
     * @return List - 프록시서비스 목록
     *
     */
    public List<ProxySvc> selectProxySvcList(ProxySvc proxySvc) throws Exception;

    /**
     * 프록시서비스 목록 총 개수를 조회한다.
     *
     * @param proxySvc - 프록시서비스 Vo
     * @return int - 프록시서비스 카운트 수
     */
    public int selectProxySvcListTotCnt(ProxySvc proxySvc) throws Exception;

    /**
     * 등록된 프록시서비스의 상세정보를 조회한다.
     *
     * @param proxySvc - 프록시서비스 Vo
     * @return proxySvc - 프록시서비스 Vo
     */
    public ProxySvc selectProxySvc(ProxySvc proxySvc) throws Exception;

    /**
     * 프록시서비스를 신규로 등록한다.
     *
     * @param ProxySvc - 프록시서비스 VO
     * @param proxySvc   - 프록시서비스 model
     * @return proxySvc - 프록시서비스 Vo
     */
    public ProxySvc insertProxySvc(ProxySvc proxySvc) throws Exception;

    /**
     * 기 등록된 프록시서비스를 수정한다.
     *
     * @param proxySvc - 프록시서비스 model
     */
    public void updateProxySvc(ProxySvc proxySvc) throws Exception;

    /**
     * 기 등록된 프록시서비스를 삭제한다.
     *
     * @param proxySvc - 프록시서비스 model
     */
    public void deleteProxySvc(ProxySvc proxySvc) throws Exception;

    /**
     * 프록시서비스를 모니터링하기 위해 등록된 프록시로그 목록을 조회한다.
     *
     * @param proxyLog - 프록시로그 Vo
     * @return List - 프록시로그 목록
     */
    public List<ProxyLog> selectProxyLogList(ProxyLog proxyLog) throws Exception;

    /**
     * 프록시로그 목록 총 개수를 조회한다.
     *
     * @param proxyLog - 프록시로그 Vo
     * @return int - 프록시서비스 카운트 수
     */
    public int selectProxyLogListTotCnt(ProxyLog proxyLog) throws Exception;

    /**
     * 프록시로그를 생성한다.
     *
     * @param proxyLog - 프록시로그 model
     */
    public void insertProxyLog(ProxyLog proxyLog) throws Exception;

    /**
     * 프록시서버를 실행한다.
     *
     * @param proxySvc - 프록시서비스 model
     */
    public void runProxyServer(ProxySvc proxySvc) throws Exception;

}