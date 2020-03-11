package egovframework.com.utl.sys.nsm.service;

import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.util.EgovBasicLogger;

/**
 * 개요
 * - 네트워크서비스 모니터링을 위한 Check 클래스
 * 
 * 상세내용
 * - 소켓으로 네트워크서비스에 접속한 뒤 해당 결과를 제공한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 * 
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2017-03-03   조성원      시큐어코딩(ES) - 부적절한 예외 처리[CWE-253, CWE-440, CWE-756]
 */



public class NtwrkSvcMntrngChecker {
	


	/**
	 * 네트워크서비스 모니터링을 수행한다.
	 * @param String - 네트워크시스템 IP
	 * @param int - 네트워크시스템 포트
	 * @return  NtwrkSvcMntrngResult - 네트워크서비스 모니터링 결과
	 * 
	 * @param sysIp 
	 * @param sysPort 
	 */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NtwrkSvcMntrngChecker.class);
	
	public static NtwrkSvcMntrngResult check(String sysIp, int sysPort) {
		
		Socket clientSocket = null;
		
		try {
			
			clientSocket = new Socket(sysIp, sysPort);
			
			return new NtwrkSvcMntrngResult(true, null);
		} catch (IOException e) {
			//log.error("네트워크서비스모니터링 에러 : " + e.getMessage());
			//log.debug(e.getMessage(), e);
			return new NtwrkSvcMntrngResult(false, e);
		} catch (Exception e) {
			//log.error("네트워크서비스모니터링 에러 : " + e.getMessage());
			//log.debug(e.getMessage(), e);
			return new NtwrkSvcMntrngResult(false, e);
		} finally {
			if(clientSocket != null){
				try{clientSocket.close();
				} catch (IOException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
					LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
					return new NtwrkSvcMntrngResult(false, e);

				// 2017-03-03   조성원      시큐어코딩(ES) - 부적절한 예외 처리[CWE-253, CWE-440, CWE-756]
				}catch(Exception e){
					
					LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
					return new NtwrkSvcMntrngResult(false, e);
					
				}
			}
		}
		
	}

}
