package egovframework.com.sym.ccm.acr.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptn;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptnVO;
import egovframework.com.sym.ccm.acr.service.EgovAdministCodeRecptnService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * 법정동코드에 대한 서비스 구현클래스를 정의한다.
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.10.07  이기하          finally문을 추가하여 에러시 자원반환할 수 있도록 추가
 *   2017-02-08	  이정은	  시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Service("AdministCodeRecptnService")
public class EgovAdministCodeRecptnServiceImpl extends EgovAbstractServiceImpl implements EgovAdministCodeRecptnService {

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovAdministCodeRecptnServiceImpl.class);

	@Resource(name = "AdministCodeRecptnDAO")
	private AdministCodeRecptnDAO administCodeRecptnDAO;

	/** EgovIdGnrService */
	@Resource(name = "egovAdministCodeRecptnIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 법정동코드수신을 처리한다.
	 */
	public void insertAdministCodeRecptn() throws Exception {
		SimpleDateFormat sDate = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
		String strdate = sDate.format(new java.util.Date());

		String rcvDir = EgovProperties.getProperty("CNTC.INSTTCODE.DIR.rcv");
		String rcvOldDir = EgovProperties.getProperty("CNTC.INSTTCODE.DIR.rcvold");
		String binDir = EgovProperties.getProperty("CNTC.INSTTCODE.DIR.bin");

		String rcvListCmd = EgovProperties.getProperty("CNTC.INSTTCODE.CMD.edircv");
		String rcvMesgCmd = EgovProperties.getProperty("CNTC.INSTTCODE.CMD.edircvmsg");

		String userId = EgovProperties.getProperty("CNTC.INSTTCODE.INFO.userid");
		String userPw = EgovProperties.getProperty("CNTC.INSTTCODE.INFO.userpw");

		String rcvListName = "unSEENlst";
		String CODULD = "CODULD"; // DocCode선언

		String rcvListFullCmd = binDir + rcvListCmd;
		String rcvMesgFullCmd = binDir + rcvMesgCmd;
		String rcvListFullName = rcvDir + rcvListName + "." + strdate;

		String systemCmdFull = null;

		FileInputStream fin = null;
		InputStreamReader sin = null;
		BufferedReader in = null;

		File listFile = null;
		File dataFile = null;

		File recvOldFile = null;

		InputStream is = null;
		BufferedReader br = null;

		// 연계목록을 가져온다.
		try {
			systemCmdFull = rcvListFullCmd + " " + userId + " " + userPw + " *UNSEEN *ALL*ALL " + rcvListFullName;
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(systemCmdFull);

			is = process.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String tmp;
			String temp = "";
			//KISA 보안약점 조치 (2018-10-29, 윤창원)
			while (true) {
				tmp = br.readLine();
				if (tmp == null) break;
				temp += tmp;
			}

			egovLogger.debug("List command console output : {}", temp);

		} catch (IOException e) {
			egovLogger.error("IO Exception", e);
			throw e;
		} finally {
			EgovResourceCloseHelper.close(br, is);
		}

		// 연계목록을 확인하여 연계파일을 가져온다.
		try {
			listFile = new File(rcvListFullName);

			if (listFile.exists()) {
				fin = new FileInputStream(listFile);
				sin = new InputStreamReader(fin);
				in = new BufferedReader(sin);

				String readList = null;
				int listCount = 0;

				// 연계목록을 확인
				//KISA 보안약점 조치 (2018-10-29, 윤창원)
				while (true) {
					readList = in.readLine();
					if (readList == null) {
						break;
					}
					
					listCount++;

					if (listCount < 5) {
						continue;
					}

					String messageID = null; // messageID
					String docCode = null; // DocCode
					String fileID = null; // fileID

					messageID = readList.substring(0, 20);
					fileID = readList.substring(20, readList.indexOf(" "));
					docCode = readList.substring(50, 56);

					if (CODULD.equals(docCode)) {
						// 연계파일을 가져온다.
						try {
							systemCmdFull = rcvMesgFullCmd + " " + userId + " " + userPw + " " + messageID + " *ALL*ALL " + rcvDir + fileID;
							Runtime runtime = Runtime.getRuntime();
							Process process = runtime.exec(systemCmdFull);

							is = process.getInputStream();
							br = new BufferedReader(new InputStreamReader(is));
							String tmp;
							String temp = "";
							//KISA 보안약점 조치 (2018-10-29, 윤창원)
							while (true) {
								tmp = br.readLine();
								if (tmp == null) {
									break;
								}
								temp += tmp;
							}
	
							egovLogger.debug("Message command console output : {}", temp);
							
						} finally {
							EgovResourceCloseHelper.close(br, is);
						}
					}
				}
				
				EgovResourceCloseHelper.close(in);

				// 연계파일 수신이 완료되면  listFile:=rcvListFullName 파일을 recvOldFileDir 로 이동한다.
				recvOldFile = new File(rcvOldDir + listFile.getName());
				if (listFile.isFile()) {
					if (recvOldFile.getParentFile() != null) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
						if (recvOldFile.getParentFile().isDirectory()) {
							//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
							if(listFile.renameTo(recvOldFile)){
								LOGGER.debug("[file.renameTo] listFile : File Rename Successs ");
							}else{							
								LOGGER.error("[file.renameTo] listFile : File Rename Fail ");
							}
						}
					}
				} else {
					// 진행종료
					processException("recvList filename or rcvold path is not valid!!!");
				}
			}
		} catch (IOException e) {
			egovLogger.error("IOException", e);
			throw e;
		} finally {
			EgovResourceCloseHelper.close(fin, sin, in);
		}

		// 수신디렉토리의 모든 연계파일을 확인하여 연계정보를 처리한다.
		String buf = "";
		//String buf2[] = null;

		File recvFileDir = new File(rcvDir);
		File recvFileList[] = recvFileDir.listFiles();

		int fileCount = 0;

		do {
			if (recvFileList[fileCount] == null) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
				fileCount++;
				continue;
			}
			
			if (recvFileList[fileCount].getName().indexOf(".rec") > -1) {
				dataFile = new File(recvFileList[fileCount].getPath());
			} else {
				fileCount++;
				continue;
			}

			buf += "\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n";

			String readData = null;

			try {
				if (dataFile.exists()) {

					fin = new FileInputStream(dataFile);
					sin = new InputStreamReader(fin);
					in = new BufferedReader(sin);
					//KISA 보안약점 조치 (2018-10-29, 윤창원)
					while (true) {
						readData = in.readLine();
						if (readData == null) {
							break;
						}

						AdministCodeRecptn administCodeRecptn = new AdministCodeRecptn();

						String tokenData[] = readData.split("	", 12);
						int tokenLength = tokenData.length;

						String strTmp = null;
						for (int i = 0; i < tokenLength; i++) {
							strTmp = tokenData[i].trim();
							tokenData[i] = strTmp;
						}

						buf += "\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
						buf += "tokenLength" + Integer.toString(tokenLength);

						if (tokenLength >= 12) {
							// 마지막 문자 ';' 제거
							tokenData[tokenLength - 1] = tokenData[tokenLength - 1].replace(";", "").trim();

							buf += "\n" + "[ F]" + dataFile.getName() // 파일명
									+ "\n";

							// 명령 변경구분코드로 변환
							strTmp = "INS".equals(tokenData[0]) ? "01" : ("UPD".equals(tokenData[0]) ? "02" : ("DEL".equals(tokenData[0]) ? "03" : ""));

							// 기본셋팅
							administCodeRecptn.setAdministZoneSe("1"); 								// 행정구역구분 1.법정동; 2.행정동

							// 실제 연계 항목 Mapping 작업
							administCodeRecptn.setChangeSeCode(strTmp);							// 명령                	:: 변경구분코드
							administCodeRecptn.setOccrrDe(tokenData[1]); 							// 날짜                	:: 발생일자
							administCodeRecptn.setAdministZoneCode(tokenData[2]); 			// 행정구역코드     	:: 행정구역코드
							administCodeRecptn.setAdministZoneNm(tokenData[7]); 			// 행정구역명        	:: 행정구역명
							administCodeRecptn.setLowestAdministZoneNm(tokenData[8]); 	// 최하위행정구역명	:: 최하위행정구역명
							administCodeRecptn.setCtprvnCode(tokenData[3]); 					// 시도코드            	:: 시도코드
							administCodeRecptn.setSignguCode(tokenData[4]); 					// 시군구코드         	:: 시군구코드
							administCodeRecptn.setEmdCode(tokenData[5]); 						// 읍면동코드         	:: 읍면동코드
							administCodeRecptn.setLiCode(tokenData[6]); 							// 리코드               	:: 리코드
							administCodeRecptn.setCreatDe(tokenData[9]); 							// 생성일자            	:: 생성일자
							administCodeRecptn.setAblDe(tokenData[10]); 							// 폐지일자            	:: 폐지일자
							administCodeRecptn.setAblEnnc(tokenData[11]); 						// 폐지유무            	:: 폐지유무
							administCodeRecptn.setUseAt(tokenData[11]); 							// 폐지유무            	:: 폐지유무

							// 작업일자
							if (administCodeRecptn.getOccrrDe().equals("") || administCodeRecptn.getOccrrDe() == null) {
								administCodeRecptn.setOccrrDe(strdate.substring(0, 8));
							}

							// 상위기관코드 계산
							String upperCode = administCodeRecptn.getSignguCode().equals("000") ? "" : (administCodeRecptn.getEmdCode().equals("000") ? administCodeRecptn
									.getCtprvnCode() + "000" + "000" + "00" : (administCodeRecptn.getLiCode().equals("00") ? administCodeRecptn.getCtprvnCode()
									+ administCodeRecptn.getSignguCode() + "000" + "00" : administCodeRecptn.getCtprvnCode() + administCodeRecptn.getSignguCode()
									+ administCodeRecptn.getEmdCode() + "00"));
							administCodeRecptn.setUpperAdministZoneCode(upperCode);

							// 작업일련번호 확인 Generation
							int iOpertSn = idgenService.getNextIntegerId();
							administCodeRecptn.setOpertSn(iOpertSn);

							buf += "\n-all--------------\n";

							for (int i = 0; i < tokenLength; i++) {
								buf += "SPLIT [" + Integer.toString(tokenData[i].length()) + "]>>>>>> " + Integer.toString(i) + "	: [" + tokenData[i] + "]\n";
							}

						} else {

							LOGGER.debug("\n\n*****************************************************************");
							LOGGER.debug(buf);
							LOGGER.debug("\n\n*****************************************************************");
							LOGGER.debug(readData);

							continue;
						}
						LOGGER.debug("\n\n*****************************************************************");
						LOGGER.debug("\n\n*****************************************************************");
						LOGGER.debug(buf);

						buf += "\n---------------\n";

						// 로그인VO에서  사용자 정보 가져오기
						LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
						String uniqId = loginVO.getUniqId();
						administCodeRecptn.setFrstRegisterId(uniqId);
						administCodeRecptn.setLastUpdusrId(uniqId);

						// changeSeCode 변경구분코드
						// '01' 코드생성
						// '02' 코드변경
						// '03' 코드말소

						// processSe 처리구분
						// '00' 수신처리
						// '01' 처리완료
						// '11' 생성오류
						// '12' 변경오류
						// '13' 말소오류

						// 작업구분 - 수신
						administCodeRecptn.setProcessSe("00");
						administCodeRecptnDAO.insertAdministCodeRecptn(administCodeRecptn);

						// 작업구분 - 처리
						administCodeRecptn.setProcessSe("01");
						if ("01".equals(administCodeRecptn.getChangeSeCode())) {
							// 코드생성
							administCodeRecptnDAO.insertAdministCode(administCodeRecptn);
						} else if ("02".equals(administCodeRecptn.getChangeSeCode())) {
							// 코드변경
							administCodeRecptnDAO.updateAdministCode(administCodeRecptn);
						} else if ("03".equals(administCodeRecptn.getChangeSeCode())) {
							// 코드말소
							administCodeRecptnDAO.deleteAdministCode(administCodeRecptn);
						}
					}
					EgovResourceCloseHelper.close(in);

					// 연계파일 수신이 완료되면  dataFile 파일을 recvOldFileDir 로 이동한다.
					recvOldFile = new File(rcvOldDir + dataFile.getName());
					if (dataFile.isFile()) {
						if (recvOldFile.getParentFile() != null) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
							if (recvOldFile.getParentFile().isDirectory()) {
								dataFile.renameTo(recvOldFile);
							}
						}
					} else {
						// 진행종료
						processException("dataFile filename or rcvold path is not valid!!!");
						//throw new Exception("dataFile filename or rcvold path is not valid!!!");
					}
				}
			} catch (IOException e) {
				LOGGER.error("IO Exception", e);
			} finally {
				EgovResourceCloseHelper.close(in);

				fileCount++;
			}

		} while (fileCount < recvFileList.length);

	}

	/**
	 * 법정동코드 상세내역을 조회한다.
	 */
	public AdministCodeRecptn selectAdministCodeDetail(AdministCodeRecptn administCodeRecptn) throws Exception {
		AdministCodeRecptn ret = (AdministCodeRecptn) administCodeRecptnDAO.selectAdministCodeDetail(administCodeRecptn);
		return ret;
	}

	/**
	 * 법정동코드수신 목록을 조회한다.
	 */
	public List<?> selectAdministCodeRecptnList(AdministCodeRecptnVO searchVO) throws Exception {
		return administCodeRecptnDAO.selectAdministCodeRecptnList(searchVO);
	}

	/**
	 * 법정동코드수신 총 갯수를 조회한다.
	 */
	public int selectAdministCodeRecptnListTotCnt(AdministCodeRecptnVO searchVO) throws Exception {
		return administCodeRecptnDAO.selectAdministCodeRecptnListTotCnt(searchVO);
	}

	/**
	 * 법정동코드 목록을 조회한다.
	 */
	public List<?> selectAdministCodeList(AdministCodeRecptnVO searchVO) throws Exception {
		return administCodeRecptnDAO.selectAdministCodeList(searchVO);
	}

	/**
	 * 법정동코드 총 갯수를 조회한다.
	 */
	public int selectAdministCodeListTotCnt(AdministCodeRecptnVO searchVO) throws Exception {
		return administCodeRecptnDAO.selectAdministCodeListTotCnt(searchVO);
	}

}
