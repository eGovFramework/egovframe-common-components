package egovframework.com.utl.sys.pxy.service.impl;

import java.io.File;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.utl.sys.pxy.service.EgovProxySvcService;
import egovframework.com.utl.sys.pxy.service.ProxyCommand;
import egovframework.com.utl.sys.pxy.service.ProxyLog;
import egovframework.com.utl.sys.pxy.service.ProxyServer;
import egovframework.com.utl.sys.pxy.service.ProxySvc;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 프록시서비스정보에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 프록시서비스정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 프록시서비스정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:27
 */
@Service("egovProxySvcService")
public class EgovProxySvcServiceImpl extends EgovAbstractServiceImpl implements EgovProxySvcService {

	// 파일구분자
    static final char FILE_SEPARATOR = File.separatorChar;

    /** ID Generation */
    @Resource(name = "egovProxyLogIdGnrService")

    private EgovIdGnrService egovProxyLogIdGnrService;

    @Resource(name = "proxySvcDAO")
    private ProxySvcDAO proxySvcDAO;

    /**
     * 프록시서비스를 관리하기 위해 등록된 프록시정보 목록을 조회한다.
     *
     * @param proxySvc - 프록시서비스 Vo
     * @return List - 프록시서비스 목록
     */
    @Override
    public List<ProxySvc> selectProxySvcList(ProxySvc proxySvc) throws Exception {
        return proxySvcDAO.selectProxySvcList(proxySvc);
    }

    /**
     * 프록시서비스 목록 총 개수를 조회한다.
     *
     * @param proxySvc - 프록시서비스 Vo
     * @return int - 프록시서비스 카운트 수
     */
    @Override
    public int selectProxySvcListTotCnt(ProxySvc proxySvc) throws Exception {
        return proxySvcDAO.selectProxySvcListTotCnt(proxySvc);
    }

    /**
     * 등록된 프록시서비스의 상세정보를 조회한다.
     *
     * @param proxySvc - 프록시서비스 Vo
     * @return proxySvc - 프록시서비스 Vo
     */
    @Override
    public ProxySvc selectProxySvc(ProxySvc proxySvc) throws Exception {
        return proxySvcDAO.selectProxySvc(proxySvc);
    }

    /**
     * 프록시서비스를 신규로 등록한다.
     *
     * @param ProxySvc - 프록시서비스 VO
     * @param proxySvc   - 프록시서비스 model
     * @return proxySvc - 프록시서비스 Vo
     */
    @Override
    public ProxySvc insertProxySvc(ProxySvc proxySvc) throws Exception {
        proxySvcDAO.insertProxySvc(proxySvc);

        if ("01".equals(proxySvc.getSvcSttus())) {// KISA 보안약점 조치 (2018-10-29, 윤창원)
            proxySvc.setStrPreSvcSttus("02");
            runProxyServer(proxySvc);
        }

        return proxySvcDAO.selectProxySvc(proxySvc);
    }

    /**
     * 기 등록된 프록시서비스를 수정한다.
     *
     * @param proxySvc - 프록시서비스 model
     */
    @Override
    public void updateProxySvc(ProxySvc proxySvc) throws Exception {
        proxySvcDAO.updateProxySvc(proxySvc);
        runProxyServer(proxySvc);
    }

    /**
     * 기 등록된 프록시서비스를 삭제한다.
     *
     * @param proxySvc - 프록시서비스 model
     */
    @Override
    public void deleteProxySvc(ProxySvc proxySvc) throws Exception {
        proxySvcDAO.deleteProxySvc(proxySvc);
    }

    /**
     * 프록시서비스를 모니터링하기 위해 등록된 프록시로그 목록을 조회한다.
     *
     * @param proxyLog - 프록시로그 Vo
     * @return List - 프록시로그 목록
     */
    @Override
    public List<ProxyLog> selectProxyLogList(ProxyLog proxyLog) throws Exception {
        return proxySvcDAO.selectProxyLogList(proxyLog);
    }

    /**
     * 프록시로그 목록 총 개수를 조회한다.
     *
     * @param proxyLog - 프록시로그 Vo
     * @return int - 프록시로그 카운트 수
     */
    @Override
    public int selectProxyLogListTotCnt(ProxyLog proxyLog) throws Exception {
        return proxySvcDAO.selectProxyLogListTotCnt(proxyLog);
    }

    /**
     * 프록시로그를 생성한다.
     *
     * @param proxyLog - 프록시로그 model
     */
    @Override
    public void insertProxyLog(ProxyLog proxyLog) throws Exception {
        proxySvcDAO.insertProxyLog(proxyLog);
    }

    /**
     * 프록시서버를 실행한다.
     *
     * @param proxySvc - 프록시서비스 model
     */
    @Override
    public void runProxyServer(ProxySvc proxySvc) throws Exception {
        // KISA 보안약점 조치 (2018-10-29, 윤창원)
        if (!"01".equals(proxySvc.getStrPreSvcSttus()) && "01".equals(proxySvc.getSvcSttus())) {
            ProxyServer proxyServer = new ProxyServer(proxySvc.getSvcIp(), proxySvc.getProxyIp(), Integer.parseInt(proxySvc.getProxyPort()), Integer.parseInt(proxySvc.getSvcPort()), proxySvc.getProxyId(), proxySvcDAO, egovProxyLogIdGnrService);
            proxyServer.start();
        } else if ("01".equals(proxySvc.getStrPreSvcSttus()) && !"01".equals(proxySvc.getSvcSttus())) {
            ProxyCommand proxyCommand = new ProxyCommand(proxySvc.getProxyIp(), Integer.parseInt(proxySvc.getProxyPort()));
            proxyCommand.runCommand("stop");
        }
    }

}