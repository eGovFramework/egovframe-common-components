package egovframework.com.sec.rnc.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovResourceCloseHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * G4C 연계용    배포파일- SocketClient.java
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2011.10.10	 이기하			 보안점검 후속초치(디버거코드 주석처리)
 *
 * </pre>
 */
public class EgovSocketClient {
	private static final int MAX_SIZE_OF_MESSAGE = 1024 * 1024;
	private BufferedInputStream inStream = null;
	private BufferedOutputStream outStream = null;

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSocketClient.class);

	public EgovSocketClient() {
	}

	public byte[] execute(String pm_sData) {
		Socket socket = null;
		byte[] lm_bRecvData = null;
		try {
			socket = EgovTimedSocket.getSocketClient(Globals.LOCAL_IP, 1235, 5000);
			socket.setSoTimeout(5000);
			inStream = new BufferedInputStream(socket.getInputStream(), 512);
			outStream = new BufferedOutputStream(socket.getOutputStream(), 512);
			sendHeader(pm_sData.getBytes());
			sendData(pm_sData.getBytes());
			int lm_iRecvDataLength = recvHeader();
			lm_bRecvData = new byte[lm_iRecvDataLength];
			readAllByte(lm_bRecvData, 0, lm_iRecvDataLength, inStream);
		} catch (IOException e) {
			LOGGER.error("IO Exception", e);
		} finally {
			EgovResourceCloseHelper.close(outStream, inStream);
			EgovResourceCloseHelper.closeSockets(socket);
		}
		return lm_bRecvData;
	}

	private int recvHeader() {
		int dataLength = 0;

		try {
			// 정보 받기
			// 헤더정보 받기, 뒤에 따라올 데이터의 길이를 알려준다.
			byte[] recvHeader = new byte[4];

			if (inStream.read(recvHeader, 0, recvHeader.length) < 4) {
				//System.out.println("받은 데이터 헤더가 형식에 맞지 않습니다.");
				dataLength = -1;
			}

			int b1 = recvHeader[0];
			int b2 = recvHeader[1];
			int b3 = recvHeader[2];
			int b4 = recvHeader[3];

			b1 = b1 & 0xFF;
			b2 = b2 & 0xFF;
			b3 = b3 & 0xFF;
			b4 = b4 & 0xFF;

			dataLength = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;

			if (MAX_SIZE_OF_MESSAGE < dataLength) {
				dataLength = -1;
			}
		} catch (IOException e) {
			dataLength = -1;
		}

		return dataLength;
	}

	/**
	 * 서버에서 보낸 정보를 읽어들인다.
	 *
	 * @param        buf                        데이터를 읽어들일 버퍼
	 * @param        offset                데이터를 읽어들일 시작위치
	 * @param        length                읽어들일 데이터의 길이
	 * @param        in                        데이터를 읽어들일 입력 스트림
	 * @throws        IOException        데이터를 읽을때 발생하는 오류
	 */
	public void readAllByte(byte[] buf, int offset, int length, BufferedInputStream in) throws IOException {

		// 읽어 들인 전체 바이트
		int totalReadByteNum = 0;

		//처음으로 읽기를 시작한다.
		int tempReadByte = in.read(buf, offset, length);

		//읽은 결과가 -1이면
		if (tempReadByte == -1) {
			return;
		}

		//읽은 바이트수를 계산한다.
		totalReadByteNum += tempReadByte;

		//읽은 바이트 의 수가 받으려하는 바이트보다 작다면 다받을 때까지 받는다.
		while (totalReadByteNum < length) {
			int reReadToByteNum = length - totalReadByteNum;
			int reReadByteNum = in.read(buf, totalReadByteNum, reReadToByteNum);

			//읽은 결과가 -1이면
			if (reReadByteNum == -1) {
				break;
			}

			totalReadByteNum += reReadByteNum;
		}
	}

	/**
	 * 데이터의 길이를 소켓을 통해 전송한다.
	 *
	 * @param data 길이를 전송할 데이터의 byte 배열
	 */
	private void sendHeader(byte[] data) throws IOException {
		byte[] sendHeader = new byte[4];

		int value = data.length;
		int b1 = (value >>> 24) & 0xFF;
		int b2 = (value >>> 16) & 0xFF;
		int b3 = (value >>> 8) & 0xFF;
		int b4 = (value >>> 0) & 0xFF;

		sendHeader[0] = (byte) b1;
		sendHeader[1] = (byte) b2;
		sendHeader[2] = (byte) b3;
		sendHeader[3] = (byte) b4;
		outStream.write(sendHeader, 0, sendHeader.length);
		outStream.flush();
	}

	/**
	 * 소켓을 이용하여 데이터를 전송한다.
	 *
	 * @param data 전송할 데이터의 byte 배열
	 */
	private void sendData(byte[] data) throws IOException {
		outStream.write(data);
		outStream.flush();
	}

	/**
	 * 배포된 파일을 로컬에서 직접테스트시  참조할 수 있는 코드(직접실행시 사용)
	 */
	/** 2011.10.10 cmd 라인상에서 편의제공을 위해 제공, 필요없을시 삭제하여도 무방함
	public static void main(String agrs[])
	{
	    EgovSocketClient lm_oSocketClient = new EgovSocketClient();
	    try
	    {
	    	/*
	    	 * 서비스구분코드|인증서CN|이름|주민번호
	    	 *
	    	 * 인증서 파일 에서 숫자에 해당하는 값:예) SVR1310000001_***.***
	    	 * 1310000001이 인증서CN 값 이다
	    	 */
	/**
	        byte[] lm_bResult1 = lm_oSocketClient.execute("12|1310000001|홍길동|주민번호");
	        //System.out.println("결과:"+new String(lm_bResult1));
	    }
	    catch(Exception e)
	    {
	    	//e.printStackTrace()
	    	System.out.println(e);
	    }
	}
	*/
}
