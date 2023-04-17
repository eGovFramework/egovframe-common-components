package egovframework.com.utl.sim;

import java.io.IOException;
import java.util.List;

import egovframework.com.utl.sim.service.EgovNetworkState;

/**
 * Ping Test 및 네트워크 정보 가져오기 Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.03.27
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.03.27  신용호          최초 생성
 *
 * @ 특징
   - OS에 따라 다음을 설정하여야 한다.
   src/main/resources/egovframework/egovProps/globals.properties
   # 운영서버 타입(WINDOWS, UNIX)
   Globals.OsType = WINDOWS
   @ eGov WIKI : 네트워크 상태체크
   http://www.egovframe.go.kr/wiki/doku.php?id=egovframework:%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC%EC%83%81%ED%83%9C%EC%B2%B4%ED%81%AC
 * </pre>
 */

public class TestPingNetwork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EgovNetworkState networkState = new EgovNetworkState();

		try {
			String myIpaddress = networkState.getMyIPaddress();
			System.out.println("1. My IP Address = "+myIpaddress);
			System.out.println("2. Ping Test = "+networkState.getPingTest(myIpaddress));
			//System.out.println("1. My MAC Address = "+networkState.getMyMACAddress(myIpaddress));//getNetWorkInfo.sh
			List<String> myPortInfoList = networkState.getMyPortScan();
			for (String portInfo : myPortInfoList) {
				System.out.println("3. My Port Info. = "+portInfo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
