package egovframework.com.utl.sys.pxy.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import egovframework.com.cmm.util.EgovResourceCloseHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyThread implements Runnable {

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyThread.class);

	@SuppressWarnings("unused")
	private Socket client = null;

	private InputStream streamFromClient = null;
	@SuppressWarnings("unused")
	private OutputStream streamToClient = null;
	@SuppressWarnings("unused")
	private InputStream streamFromServer = null;
	private OutputStream streamToServer = null;

	private boolean isStop = false;

	byte[] request = new byte[1024];
	byte[] reply = new byte[4096];

	public ProxyThread(Socket client) {
		this.client = client;
	}

	public ProxyThread(Socket client, InputStream streamFromClient, OutputStream streamToClient, InputStream streamFromServer, OutputStream streamToServer) throws IOException {
		this.client = client;
		this.streamFromClient = streamFromClient;
		this.streamToClient = streamToClient;
		this.streamFromServer = streamFromServer;
		this.streamToServer = streamToServer;
	}

	public void setIsStop(boolean isStop) {
		this.isStop = isStop;
	}

	public boolean getIsStop() {
		return this.isStop;
	}

	public void run() {

		int bytesRead;
		String strReceive = "";

		try {

			if (streamFromClient != null) {

				while ((bytesRead = streamFromClient.read(request)) != -1) {

					strReceive = new String(request, 0, bytesRead);

					if (strReceive.indexOf("stop") > -1) {
						setIsStop(true);
						break;
					}

					// streamToServer.write(strReceive.getBytes(), 0, strReceive.getBytes().length);
					streamToServer.write(request, 0, bytesRead);
					streamToServer.flush();
				}
			}
		} catch (IOException e) {
			LOGGER.debug("Server IO Error", e);
		} finally {
			EgovResourceCloseHelper.close(streamToServer);
		}
	}
}
