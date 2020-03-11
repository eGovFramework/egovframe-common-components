package egovframework.com.sym.ccm.icr.service.impl;

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
import egovframework.com.sym.ccm.icr.service.EgovInsttCodeRecptnService;
import egovframework.com.sym.ccm.icr.service.InsttCodeRecptn;
import egovframework.com.sym.ccm.icr.service.InsttCodeRecptnVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * 기관코드에 대한 서비스 구현클래스를 정의한다.
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
 *   2011.09.05	 서준식          파일 읽기 무한 루프 오류 수정
 *   2011.10.07  이기하          finally문을 추가하여 에러시 자원반환할 수 있도록 추가
 *   2017-02-08	  이정은	  시큐어코딩(ES) - 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Service("InsttCodeRecptnService")
public class EgovInsttCodeRecptnServiceImpl extends EgovAbstractServiceImpl implements EgovInsttCodeRecptnService {
	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovInsttCodeRecptnServiceImpl.class);

	@Resource(name = "InsttCodeRecptnDAO")
	private InsttCodeRecptnDAO insttCodeRecptnDAO;

	/** EgovIdGnrService */
	@Resource(name = "egovInsttCodeRecptnIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 기관코드수신을 처리한다.
	 */
	public void insertInsttCodeRecptn() throws Exception {
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
		String CODUCH = "CODUCH"; // DocCode선언

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
			tmp = br.readLine();
			while (tmp != null) {
				temp += tmp;
				tmp = br.readLine();
			}
			egovLogger.debug("List command console output : {}", temp);

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
				readList = in.readLine();
				while (readList != null) {
					listCount++;
					readList = in.readLine();
					if (listCount < 4 || readList == null) {

						continue;
					}

					String messageID = null; // messageID
					String docCode = null; // DocCode
					String fileID = null; // fileID

					if (readList.length() > 56) { //기관코드 리스트 수신 row가 56자보다 긴지 점검
						messageID = readList.substring(0, 20);
						fileID = readList.substring(20, readList.indexOf(" "));
						docCode = readList.substring(50, 56);

						if (CODUCH.equals(docCode)) {
							// 연계파일을 가져온다.
							try {
								systemCmdFull = rcvMesgFullCmd + " " + userId + " " + userPw + " " + messageID + " *ALL*ALL " + rcvDir + fileID;
								Runtime runtime = Runtime.getRuntime();
								Process process = runtime.exec(systemCmdFull);

								is = process.getInputStream();
								br = new BufferedReader(new InputStreamReader(is));
								
								String tmp;
								String temp = "";
								tmp = br.readLine();
								while (tmp != null) {
									temp += tmp;
									tmp = br.readLine();
								}

								egovLogger.debug("Message command console output : {}", temp);
							} finally {
								EgovResourceCloseHelper.close(br, is);
							}
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
					//throw new Exception("recvList filename or rcvold path is not valid!!!");
				}
			}
		} catch (IOException e) {
			egovLogger.error("IOException", e);
			throw e;
		} finally {
			EgovResourceCloseHelper.close(in);
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
					readData = in.readLine();
					while (readData != null) {

						InsttCodeRecptn insttCodeRecptn = new InsttCodeRecptn();

						String tokenData[] = readData.split("	", 25);
						int tokenLength = tokenData.length;

						String strTmp = null;
						for (int i = 0; i < tokenLength; i++) {
							strTmp = tokenData[i].trim();
							tokenData[i] = strTmp;
						}

						if (tokenLength >= 25) {
							// 마지막 문자 ';' 제거
							tokenData[tokenLength - 1] = tokenData[tokenLength - 1].replace(";", "").trim();

							// 서열설정 3자리 숫자로 맞춤
							if (tokenData[8].length() == 1) {
								tokenData[8] = "00" + tokenData[8];
							} else if (tokenData[8].length() == 2) {
								tokenData[8] = "0" + tokenData[8];
							}

							// 숫자형 변환 전 처리
							tokenData[24] = (tokenData[24] == null || "".equals(tokenData[24])) ? "0" : tokenData[24];

							buf += "\n" + "[ F]" + dataFile.getName() // 파일명
									+ "\n";

							// 명령 변경구분코드로 변환
							strTmp = "INS".equals(tokenData[0]) ? "01" : ("UPD".equals(tokenData[0]) ? "02" : ("DEL".equals(tokenData[0]) ? "03" : ""));

							// 실제 연계 항목 Mapping 작업
							insttCodeRecptn.setChangeSeCode(strTmp); 				// 명령                              		:: 변경구분코드
							insttCodeRecptn.setOccrrDe(tokenData[1]); 					// 날짜                              		:: 발생일자
							insttCodeRecptn.setEtcCode(tokenData[2]); 				// 2자리코드 <적용:기타코드>	:: 기타코드
							insttCodeRecptn.setInsttCode(tokenData[3]); 				// 기관코드                          	:: 기관코드
							insttCodeRecptn.setAllInsttNm(tokenData[4]); 				// 기관명(전체)                      	:: 전체기관명
							insttCodeRecptn.setLowestInsttNm(tokenData[5]); 		// 기관명(최하위)                   	:: 최하위기관명
							insttCodeRecptn.setInsttAbrvNm(tokenData[6]); 			// 기관명(약어)                      	:: 기관약칭명
							insttCodeRecptn.setOdr(tokenData[7]); 						// 차수                              		:: 차수
							insttCodeRecptn.setOrd(tokenData[8]); 						// 서열                             		:: 서열
							insttCodeRecptn.setInsttOdr(tokenData[9]); 				// 소속기관차수                     	:: 기관차수
							insttCodeRecptn.setUpperInsttCode(tokenData[10]);	// 차상위기관코드                  	:: 상위기관코드
							insttCodeRecptn.setBestInsttCode(tokenData[11]); 		// 최상위기관코드               		:: 최상위기관코드
							insttCodeRecptn.setReprsntInsttCode(tokenData[12]); // 대표기관코드                      :: 대표기관코드
							insttCodeRecptn.setInsttTyLclas(tokenData[13]); 			// 기관유형(대)                      	:: 기관유형대분류
							insttCodeRecptn.setInsttTyMclas(tokenData[14]); 		// 기관유형(중)                     	:: 기관유형중분류
							insttCodeRecptn.setInsttTySclas(tokenData[15]); 			// 기관유형(소)                      	:: 기관유형소분류
							insttCodeRecptn.setTelno(tokenData[16]); 					// 전화번호                 			:: 전화번호
							insttCodeRecptn.setFxnum(tokenData[17]); 				// 팩스번호                          	:: 팩스번호
							insttCodeRecptn.setCreatDe(tokenData[18]); 				// 생성일자                          	:: 생성일자
							insttCodeRecptn.setAblDe(tokenData[19]); 					// 폐지일자                          	:: 폐지일자
							insttCodeRecptn.setAblEnnc(tokenData[20]); 				// 폐지구분                          	:: 폐지유무
							insttCodeRecptn.setChangede(tokenData[21]); 			// 변경일자                          	:: 변경일자
							insttCodeRecptn.setChangeTime(tokenData[22]); 		// 변경시간                          	:: 변경시간
							insttCodeRecptn.setBsisDe(tokenData[23]); 					// 기초날짜                          	:: 기초일자
							insttCodeRecptn.setSortOrdr(Integer.parseInt(tokenData[24]));	// 트리순서(트리서열) <적용:정렬순서>:: 정렬순서

							// 작업일자
							if (insttCodeRecptn.getOccrrDe().equals("") || insttCodeRecptn.getOccrrDe() == null) {
								insttCodeRecptn.setOccrrDe(strdate.substring(0, 8));
							}

							// 작업일련번호 확인 Generation
							int iOpertSn = idgenService.getNextIntegerId();
							insttCodeRecptn.setOpertSn(iOpertSn);

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
						// KISA 보안취약점 조치 (2018-12-10, 신용호)
						String uniqId = "";
						if (loginVO!=null) loginVO.getUniqId();
						insttCodeRecptn.setFrstRegisterId(uniqId);
						insttCodeRecptn.setLastUpdusrId(uniqId);

						// changeSeCode 변경구분코드
						// '01' 기관생성
						// '02' 기관변경
						// '03' 기관말소

						// processSe 처리구분
						// '00' 수신처리
						// '01' 처리완료
						// '11' 생성오류
						// '12' 변경오류
						// '13' 말소오류

						// 작업구분 - 수신
						insttCodeRecptn.setProcessSe("00");
						insttCodeRecptnDAO.insertInsttCodeRecptn(insttCodeRecptn);

						// 작업구분 - 처리
						insttCodeRecptn.setProcessSe("01");
						if ("01".equals(insttCodeRecptn.getChangeSeCode())) {
							// 기관생성
							insttCodeRecptnDAO.insertInsttCode(insttCodeRecptn);
						} else if ("02".equals(insttCodeRecptn.getChangeSeCode())) {
							// 기관변경
							insttCodeRecptnDAO.updateInsttCode(insttCodeRecptn);
						} else if ("03".equals(insttCodeRecptn.getChangeSeCode())) {
							// 기관말소
							insttCodeRecptnDAO.deleteInsttCode(insttCodeRecptn);
						}
						readData = in.readLine();//2011.09.05
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
				egovLogger.error("IOException", e);
			} finally {
				EgovResourceCloseHelper.close(fin, sin, in);

				fileCount++;
			}

		} while (fileCount < recvFileList.length);

	}

	/**
	 * 기관코드 상세내역을 조회한다.
	 */
	public InsttCodeRecptn selectInsttCodeDetail(InsttCodeRecptn insttCodeRecptn) throws Exception {
		InsttCodeRecptn ret = (InsttCodeRecptn) insttCodeRecptnDAO.selectInsttCodeDetail(insttCodeRecptn);
		return ret;
	}

	/**
	 * 기관코드수신 목록을 조회한다.
	 */
	public List<?> selectInsttCodeRecptnList(InsttCodeRecptnVO searchVO) throws Exception {
		return insttCodeRecptnDAO.selectInsttCodeRecptnList(searchVO);
	}

	/**
	 * 기관코드수신 총 갯수를 조회한다.
	 */
	public int selectInsttCodeRecptnListTotCnt(InsttCodeRecptnVO searchVO) throws Exception {
		return insttCodeRecptnDAO.selectInsttCodeRecptnListTotCnt(searchVO);
	}

	/**
	 * 기관코드 목록을 조회한다.
	 */
	public List<?> selectInsttCodeList(InsttCodeRecptnVO searchVO) throws Exception {
		return insttCodeRecptnDAO.selectInsttCodeList(searchVO);
	}

	/**
	 * 기관코드 총 갯수를 조회한다.
	 */
	public int selectInsttCodeListTotCnt(InsttCodeRecptnVO searchVO) throws Exception {
		return insttCodeRecptnDAO.selectInsttCodeListTotCnt(searchVO);
	}

}
