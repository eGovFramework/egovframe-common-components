package egovframework.com.utl.sys.pxy.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
/**
 * 프록시 스레드 클래스는 클라이언트와 서버 간의 통신을 중계합니다.
 */
public class ProxyThread implements Runnable {

	/** 로그 출력을 위한 로거 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyThread.class);

	/** 클라이언트 소켓 */
	@SuppressWarnings("unused")
	private Socket client = null;

	/** 클라이언트로부터의 입력 스트림 */
	private InputStream streamFromClient = null;
	/** 클라이언트로의 출력 스트림 */
	@SuppressWarnings("unused")
	private OutputStream streamToClient = null;
	/** 서버로부터의 입력 스트림 */
	@SuppressWarnings("unused")
	private InputStream streamFromServer = null;
	/** 서버로의 출력 스트림 */
	private OutputStream streamToServer = null;

	/** 스레드 중지 여부를 표시하는 플래그 */
	private boolean isStop = false;

	/** 요청 데이터 버퍼 */
	byte[] request = new byte[1024];
	/** 응답 데이터 버퍼 */
	byte[] reply = new byte[4096];

	/**
	 * 주어진 클라이언트 소켓을 사용하여 ProxyThread 객체를 생성합니다.
	 *
	 * @param client 클라이언트 소켓
	 */
	public ProxyThread(Socket client) {
		this.client = client;
	}

	/**
	 * 스트림 및 클라이언트 소켓을 사용하여 ProxyThread 객체를 생성합니다.
	 *
	 * @param client 클라이언트 소켓
	 * @param streamFromClient 클라이언트로부터의 입력 스트림
	 * @param streamToClient 클라이언트로의 출력 스트림
	 * @param streamFromServer 서버로부터의 입력 스트림
	 * @param streamToServer 서버로의 출력 스트림
	 */
	public ProxyThread(Socket client, InputStream streamFromClient, OutputStream streamToClient, InputStream streamFromServer, OutputStream streamToServer) throws IOException {
		this.client = client;
		this.streamFromClient = streamFromClient;
		this.streamToClient = streamToClient;
		this.streamFromServer = streamFromServer;
		this.streamToServer = streamToServer;
	}

	/**
	 * 스레드 중지 여부를 설정합니다.
	 *
	 * @param isStop 스레드 중지 여부
	 */
	public void setIsStop(boolean isStop) {
		this.isStop = isStop;
	}

	/**
	 * 스레드 중지 여부를 반환합니다.
	 *
	 * @return 스레드 중지 여부
	 */
	public boolean getIsStop() {
		return this.isStop;
	}

	/**
	 * 프록시 스레드의 주 실행 메서드입니다.
	 * 클라이언트에서 서버로의 데이터 전달을 처리합니다.
	 */
	@Override
	public void run() {

		int bytesRead;
		String strReceive = "";

		try {
			if (streamFromClient != null) {
				while ((bytesRead = streamFromClient.read(request)) != -1) {

					strReceive = new String(request, 0, bytesRead);

					// 'stop' 문자열을 받으면 스레드를 중지합니다.
					if (strReceive.indexOf("stop") > -1) {
						setIsStop(true);
						break;
					}

					// 클라이언트로부터 받은 데이터를 서버로 전송합니다.
					streamToServer.write(request, 0, bytesRead);
					streamToServer.flush();
				}
			}
		} catch (IOException e) {
			LOGGER.debug("Server IO Error", e);
		} finally {
			// 자원을 안전하게 닫습니다.
			EgovResourceCloseHelper.close(streamToServer);
		}
	}
}
