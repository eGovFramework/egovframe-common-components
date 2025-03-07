package egovframework.com.utl.sys.pxy.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.utl.sys.pxy.service.impl.ProxySvcDAO;

/**
 * 프록시서비스 처리 클래스
 *
 * @author 김진만
 * @since 2010.07.15
 * @version 1.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *  수정일                수정자             수정내용
 *  ----------   --------    ---------------------------
 *  2019.12.05   신용호              KISA 보안약점 조치 (경로조작및 자원 삽입, 부적절한 예외처리)
 * </pre>
 */
public class ProxyServer extends Thread {
	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyServer.class);

	ProxySvcDAO proxySvcDAO;
	EgovIdGnrService egovProxyLogIdGnrService;

	ServerSocket serverSocket = null;
	Socket client = null;
	Socket server = null;

	private String svcIp = null;
	private String localIp = null;
	private int localPort;
	private int remotePort;
	private String threadName = null;

	DataInputStream disReader;
	DataOutputStream dosWriter;

	byte[] request = new byte[1024];
	byte[] reply = new byte[4096];

	ProxyLog proxyLog = null;

	public ProxyServer(String svcHost, String localIp, int localPort, int remotePort, String threadName,
		ProxySvcDAO proxySvcDAO, EgovIdGnrService egovProxyLogIdGnrService) {

		try {
			setSvcIp(svcHost);
			setLocalIp(localIp);
			setLocalPort(localPort);
			setRemotePort(remotePort);
			setThreadName(threadName);

			this.proxySvcDAO = proxySvcDAO;
			this.egovProxyLogIdGnrService = egovProxyLogIdGnrService;

			serverSocket = SSLServerSocketFactory.getDefault().createServerSocket(localPort);//2022.01. Unencrypted Socket

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {

		runServer();
	}

	public void runServer() {

		boolean runningThread = true;

		try {
			while (runningThread) {

				try {
					serverSocket.setSoTimeout(2000);
					LOGGER.info("client wait......");

					client = serverSocket.accept();

				} catch (IOException ce) {
					LOGGER.debug("Socket server accept IO exception", ce);
					continue;
				}

				if (client.isConnected()) {

					insertProxyLog();

					LOGGER.info("client connect");
					InputStream streamFromClient = client.getInputStream();
					OutputStream streamToClient = client.getOutputStream();

					String svcIp = EgovWebUtil.filePathBlackList(getSvcIp());
					server = SSLSocketFactory.getDefault().createSocket(svcIp, remotePort);//2022.01. Unencrypted Socket 처리

					InputStream streamFromServer = server.getInputStream();
					OutputStream streamToServer = server.getOutputStream();

					ProxyThread proxyThread = new ProxyThread(client, streamFromClient, streamToClient,
						streamFromServer, streamToServer);
					Thread thread = new Thread(proxyThread, getThreadName() + "-" + server.getLocalPort());
					thread.start();

					int bytesRead;
					try {
						while ((bytesRead = streamFromServer.read(reply)) != -1) {
							streamToClient.write(reply, 0, bytesRead);
							streamToClient.flush();
						}
					} catch (IOException e) {
						LOGGER.debug("Socket IO exception", e);
					} finally {
						streamToClient.close();
						if (proxyThread.getIsStop()) {
							runningThread = false;
							break;
						}
					}
				}
			}

		} catch (IOException e) {
			LOGGER.debug("Server IO exception", e);
		} finally {
			EgovResourceCloseHelper.closeSockets(server);
			EgovResourceCloseHelper.closeSocketObjects(client, serverSocket);
		}
	}

	public void insertProxyLog() {

		try {

			proxyLog = new ProxyLog();

			proxyLog.setProxyId(getThreadName());

			proxyLog.setLogId(egovProxyLogIdGnrService.getNextStringId());

			//KISA 보안약점 조치 (2018-10-29, 윤창원)
			if (client.getInetAddress() != null) {
				if (!EgovWebUtil.isIPAddress((client.getInetAddress().getHostAddress()))) {
					throw new RuntimeException("IP is needed. (" + client.getInetAddress().getHostAddress() + ")");
				}
				proxyLog.setClntIp(client.getInetAddress().getHostAddress());
			}
			proxyLog.setClntPort(String.valueOf(getLocalPort()));
			proxyLog.setFrstRegisterId("SYSTEM");
			proxyLog.setLastUpdusrId("SYSTEM");

			LOGGER.info(proxyLog.getProxyId());
			LOGGER.info(proxyLog.getLogId());
			LOGGER.info(proxyLog.getClntIp());
			LOGGER.info(proxyLog.getClntPort());
			LOGGER.info(proxyLog.getFrstRegisterId());
			LOGGER.info(proxyLog.getLastUpdusrId());

			proxySvcDAO.insertProxyLog(proxyLog);

		} catch (SQLException e) {
			LOGGER.debug("proxyLog Insert Error", e);
		} catch (Exception e) {
			LOGGER.debug("proxyLog Insert Error", e);
		}
	}

	/**
	 * @return the svcIp
	 */
	public String getSvcIp() {
		return svcIp;
	}

	/**
	 * @param svcIp the svcIp to set
	 */
	public void setSvcIp(String svcIp) {
		this.svcIp = svcIp;
	}

	/**
	 * @return the localIp
	 */
	public String getLocalIp() {
		return localIp;
	}

	/**
	 * @param localIp the localIp to set
	 */
	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	/**
	 * @return the localPort
	 */
	public int getLocalPort() {
		return localPort;
	}

	/**
	 * @param localPort the localPort to set
	 */
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	/**
	 * @return the remotePort
	 */
	public int getRemotePort() {
		return remotePort;
	}

	/**
	 * @param remotePort the remotePort to set
	 */
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	/**
	 * @return the threadName
	 */
	public String getThreadName() {
		return threadName;
	}

	/**
	 * @param threadName the threadName to set
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
