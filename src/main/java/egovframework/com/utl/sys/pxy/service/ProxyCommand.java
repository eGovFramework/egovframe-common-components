package egovframework.com.utl.sys.pxy.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.util.EgovResourceCloseHelper;

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
 *  2019.12.05   신용호              KISA 보안약점 조치 (경로조작및 자원 삽입)
 * </pre>
 */

public class ProxyCommand {

	Socket clientSocket;
	DataInputStream disReader;
	DataOutputStream dosWriter;

	String strReceive = null;
	String strLog = null;

	private String proxyIp;
	private int proxyPort;

	public ProxyCommand(String proxyIp, int proxyPort) {
		setProxyIp(proxyIp);
		setProxyPort(proxyPort);
	}

	public void runCommand(String msg) {
		try {
			proxyIp = EgovWebUtil.filePathBlackList(proxyIp);
			clientSocket = new Socket(proxyIp, proxyPort);

			disReader = new DataInputStream(clientSocket.getInputStream());
			dosWriter = new DataOutputStream(clientSocket.getOutputStream());

			dosWriter.writeUTF(msg);
			dosWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			CloseSocket();
		}
	}

	private void CloseSocket() {
		EgovResourceCloseHelper.close(disReader, dosWriter);
		EgovResourceCloseHelper.closeSockets(clientSocket);
	}

	/**
	 * @return the proxyIp
	 */
	public String getProxyIp() {
		return proxyIp;
	}

	/**
	 * @param proxyIp the proxyIp to set
	 */
	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	/**
	 * @return the proxyPort
	 */
	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * @param proxyPort the proxyPort to set
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}
}
