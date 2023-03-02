package egovframework.com.utl.sys.ssy.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.ssy.service.EgovSynchrnServerService;
import egovframework.com.utl.sys.ssy.service.SynchrnServer;
import egovframework.com.utl.sys.ssy.service.SynchrnServerVO;

/**
 * 개요
 * - 동기화대상 서버에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 동기화대상 서버에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 동기화대상 서버의 조회기능은 목록조회, 상세조회로 구분된다.
 * - 2015.03.31	 업로드 파일의 목록을 조회시 업로드 디렉토리가 없을 경우 생성하도록 수정 
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:34
 * 
 *      수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2017-02-08    이정은        시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2018-11-12    이정은        processFtp() FILE_TYPE 설정 수정
 * 
 */
@Service("egovSynchrnServerService")
public class EgovSynchrnServerServiceImpl extends EgovAbstractServiceImpl implements EgovSynchrnServerService {
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSynchrnServerServiceImpl.class);
	private static final String SYNCH_SERVER_PATH = EgovProperties.getProperty("Globals.SynchrnServerPath");

	@Resource(name = "synchrnServerDAO")
	private SynchrnServerDAO synchrnServerDAO;

	/**
	 * 동기화대상 서버를 관리하기 위해 등록된 동기화대상 서버목록을 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return List - 동기화대상 서버 목록
	 */
	public List<SynchrnServerVO> selectSynchrnServerList(SynchrnServerVO synchrnServerVO) throws Exception {
		return synchrnServerDAO.selectSynchrnServerList(synchrnServerVO);
	}

	/**
	 * 동기화대상 서버목록 총 개수를 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return int - 동기화대상 서버 카운트 수
	 */
	public int selectSynchrnServerListTotCnt(SynchrnServerVO synchrnServerVO) throws Exception {
		return synchrnServerDAO.selectSynchrnServerListTotCnt(synchrnServerVO);
	}

	/**
	 * 등록된 동기화대상 서버의 상세정보를 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return synchrnServerVO - 동기화대상 서버 Vo
	 */
	public SynchrnServerVO selectSynchrnServer(SynchrnServerVO synchrnServerVO) throws Exception {
		return synchrnServerDAO.selectSynchrnServer(synchrnServerVO);
	}

	/**
	 * 등록된 동기화대상 서버의 파일 목록을 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return List<String> - String Type List
	 */
	public List<String> selectSynchrnServerFiles(SynchrnServerVO synchrnServerVO) throws Exception {

		List<String> list = new ArrayList<String>();

		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.setControlEncoding("euc-kr");

			if (!EgovWebUtil.isIPAddress(synchrnServerVO.getServerIp())) {
				throw new RuntimeException("IP is needed. (" + synchrnServerVO.getServerIp() + ")");
			}

			InetAddress host = InetAddress.getByName(synchrnServerVO.getServerIp());

			ftpClient.connect(host, Integer.parseInt(synchrnServerVO.getServerPort()));
			boolean isLogin = ftpClient.login(synchrnServerVO.getFtpId(), synchrnServerVO.getFtpPassword());
			if (!isLogin)
				throw new Exception("FTP Client Login Error : \n");

			FTPFile[] fTPFile = null;

			try {
				ftpClient.changeWorkingDirectory(synchrnServerVO.getSynchrnLc());
				fTPFile = ftpClient.listFiles(synchrnServerVO.getSynchrnLc());

				for (int i = 0; i < fTPFile.length; i++) {
					if (fTPFile[i].isFile())
						list.add(fTPFile[i].getName());
				}
			} finally {
				ftpClient.logout();
			}

		} catch (IOException e) {
			list.add("noList");
		}

		return list;
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 삭제한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 */
	public void deleteSynchrnServerFile(SynchrnServerVO synchrnServerVO) throws Exception {

		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");

		if (!EgovWebUtil.isIPAddress(synchrnServerVO.getServerIp())) {
			throw new RuntimeException("IP is needed. (" + synchrnServerVO.getServerIp() + ")");
		}

		InetAddress host = InetAddress.getByName(synchrnServerVO.getServerIp());

		ftpClient.connect(host, Integer.parseInt(synchrnServerVO.getServerPort()));
		ftpClient.login(synchrnServerVO.getFtpId(), synchrnServerVO.getFtpPassword());

		FTPFile[] fTPFile = null;

		try {
			ftpClient.changeWorkingDirectory(synchrnServerVO.getSynchrnLc());
			fTPFile = ftpClient.listFiles(synchrnServerVO.getSynchrnLc());

			for (int i = 0; i < fTPFile.length; i++) {
				//KISA 보안약점 조치 (2018-10-29, 윤창원)
				if (EgovStringUtil.isNullToString(synchrnServerVO.getDeleteFileNm()).equals(fTPFile[i].getName()))
					ftpClient.deleteFile(fTPFile[i].getName());
			}

			SynchrnServer synchrnServer = new SynchrnServer();
			synchrnServer.setServerId(synchrnServerVO.getServerId());
			synchrnServer.setReflctAt("N");
			synchrnServerDAO.processSynchrn(synchrnServer);

		} finally {
			ftpClient.logout();
		}
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 다운로드 한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @param fileNm - 다운로드 대상 파일
	 */
	public void downloadFtpFile(SynchrnServerVO synchrnServerVO, String fileNm) throws Exception {

		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");

		if (!EgovWebUtil.isIPAddress(synchrnServerVO.getServerIp())) {
			throw new RuntimeException("IP is needed. (" + synchrnServerVO.getServerIp() + ")");
		}

		InetAddress host = InetAddress.getByName(synchrnServerVO.getServerIp());

		ftpClient.connect(host, Integer.parseInt(synchrnServerVO.getServerPort()));
		ftpClient.login(synchrnServerVO.getFtpId(), synchrnServerVO.getFtpPassword());
		ftpClient.changeWorkingDirectory(synchrnServerVO.getSynchrnLc());

		File downFile = new File(EgovWebUtil.filePathBlackList(synchrnServerVO.getFilePath() + fileNm));
		OutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(downFile);
			ftpClient.retrieveFile(fileNm, outputStream);
		} finally {
			if (outputStream != null)
				outputStream.close();
		}

		ftpClient.logout();
	}

	/**
	 * 동기화대상 서버정보를 신규로 등록한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 * @param synchrnServerVO    - 동기화대상 서버 VO
	 */
	public SynchrnServerVO insertSynchrnServer(SynchrnServer synchrnServer, SynchrnServerVO synchrnServerVO) throws Exception {
		synchrnServerDAO.insertSynchrnServer(synchrnServer);
		synchrnServerVO.setServerId(synchrnServer.getServerId());
		return synchrnServerDAO.selectSynchrnServer(synchrnServerVO);
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 수정한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	public void updateSynchrnServer(SynchrnServer synchrnServer) throws Exception {
		synchrnServerDAO.updateSynchrnServer(synchrnServer);
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 삭제한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	public void deleteSynchrnServer(SynchrnServer synchrnServer) throws Exception {
		synchrnServerDAO.deleteSynchrnServer(synchrnServer);
	}

	/**
	 * 업로드 파일을 동기화대상 서버들을 대상으로 동기화 처리를 한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return boolean - 성공여부
	 */
	public boolean processSynchrn(SynchrnServerVO synchrnServerVO, File[] uploadFile) throws Exception {

		List<SynchrnServerVO> synchrnServerList = synchrnServerDAO.processSynchrnServerList(synchrnServerVO);
		Iterator<SynchrnServerVO> iterator = synchrnServerList.iterator();
		SynchrnServer synchrnServer = new SynchrnServer();
		boolean reflctAt = false;

		while (iterator.hasNext()) {
			SynchrnServerVO SynchrnServerVo = iterator.next();

			reflctAt = processFtp(SynchrnServerVo.getServerIp(), Integer.parseInt(SynchrnServerVo.getServerPort()), SynchrnServerVo.getFtpId(), SynchrnServerVo.getFtpPassword(),
					SynchrnServerVo.getSynchrnLc(), synchrnServerVO.getFilePath(), uploadFile);

			synchrnServer.setServerId(SynchrnServerVo.getServerId());
			if (reflctAt) {
				synchrnServer.setReflctAt("Y");
			} else {
				synchrnServer.setReflctAt("N");
			}

			synchrnServerDAO.processSynchrn(synchrnServer);
		}

		return true;
	}

	/**
	 * FTP 서버에 있는 화일 목록을 조회한다.
	 * @param serverIp - String
	 * @param port - int
	 * @param user - String
	 * @param password - String
	 * @param synchrnPath - String
	 * @return List - 화일 목록
	 */
	public List<String> getFtpFileList(String serverIp, int port, String user, String password, String synchrnPath) throws Exception {

		List<String> list = new ArrayList<String>();
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");

		if (!EgovWebUtil.isIPAddress(serverIp)) {
			throw new RuntimeException("IP is needed. (" + serverIp + ")");
		}

		InetAddress host = InetAddress.getByName(serverIp);

		ftpClient.connect(host, port);
		ftpClient.login(user, password);

		ftpClient.changeWorkingDirectory(synchrnPath);
		FTPFile[] fTPFile = ftpClient.listFiles(synchrnPath);
		for (int i = 0; i < fTPFile.length; i++) {
			list.add(fTPFile[i].getName());
		}
		return list;
	}

	/**
	 * 동기화 서버들을 대상으로 FTP Upload 처리를 한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return boolean - 성공여부
	 */
	public boolean processFtp(String serverIp, int port, String user, String password, String synchrnPath, String filePath, File[] uploadFile) throws Exception {

		boolean upload = false;

		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.setControlEncoding("euc-kr");

			if (!EgovWebUtil.isIPAddress(serverIp)) { // 2011.10.25 보안점검 후속조치
				throw new RuntimeException("IP is needed. (" + serverIp + ")");
			}

			InetAddress host = InetAddress.getByName(serverIp);

			ftpClient.connect(host, port);
			if (!ftpClient.login(user, password))
				throw new Exception("FTP Client Login Error : \n");

			if (synchrnPath.length() != 0)
				ftpClient.changeWorkingDirectory(synchrnPath);

			FTPFile[] fTPFile = ftpClient.listFiles(synchrnPath);

			FileInputStream fis = null;
			try {
				for (int i = 0; i < uploadFile.length; i++) {
					if (uploadFile[i].isFile()) {
						if (!isExist(fTPFile, uploadFile[i])) {
							fis = new FileInputStream(uploadFile[i]);
							//ftpClient.setFileType(FTP.ASCII_FILE_TYPE); // TEXT FILE 전송
							ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // 바이너리 파일 전송
							ftpClient.storeFile(synchrnPath + uploadFile[i].getName(), fis);
						}
						if (fis != null) {
							fis.close();
						}
					}
				}

				// 업로드 파일 목록에 없는  FTP 서버에 있는 파일을 삭제한다.
				fTPFile = ftpClient.listFiles(synchrnPath);
				deleteFtpFile(ftpClient, fTPFile, uploadFile);

				upload = true;

			} catch (IOException ex) {
				EgovBasicLogger.debug("FTP IO error", ex);
			} finally {
				EgovResourceCloseHelper.close(fis);
			}
			ftpClient.logout();

		} catch (IOException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			EgovBasicLogger.debug("processFtp error (IOException)", e);
			upload = false;
		} catch (Exception e) {
			EgovBasicLogger.debug("processFtp error", e);
			upload = false;
		}

		return upload;
	}

	/**
	 * 동기화 서버에 upload 할 파일이 존재하는지 확인한다.
	 * @param fTPFiles - 동기화대상 서버의 파일 목록
	 * @param targetFile - 동기화대상 파일
	 * @return boolean - 존재여부
	 */
	public boolean isExist(FTPFile[] fTPFiles, File targetFile) throws Exception {

		boolean isExist = false;

		for (int i = 0; i < fTPFiles.length; i++) {
			if (fTPFiles[i].isFile()) {
				if (fTPFiles[i].getName().equals(targetFile.getName()))
					isExist = true;
			}
		}

		return isExist;
	}

	/**
	 * 동기화 서버의 파일 목록 중 upload 파일 목록에 없는 파일은 삭제한다.
	 * @param fTPFiles - 동기화대상 서버의 파일 목록
	 * @param uploadFile - 업로드 파일 목록
	 * @return boolean - 존재여부
	 */
	public void deleteFtpFile(FTPClient ftpClient, FTPFile[] fTPFiles, File[] uploadFile) throws Exception {

		boolean isExist = false;

		for (int i = 0; i < fTPFiles.length; i++) {
			isExist = false;
			for (int j = 0; j < uploadFile.length; j++) {
				if (fTPFiles[i].isFile()) {
					if (fTPFiles[i].getName().equals(uploadFile[j].getName())) {
						isExist = true;
					}
				}
			}

			if (!isExist) {
				if (fTPFiles[i].isFile()) {
					ftpClient.deleteFile(fTPFiles[i].getName());
				}
			}
		}
	}

	/**
	 * 업로드 파일의 목록을 조회한다.
	 * @param filePath - 업로드 경로
	 * @return List - 업로드 파일 리스트
	 */
	public List<String> getFileName(String filePath) throws Exception {

		File uploadFile = new File(EgovWebUtil.filePathBlackList(filePath));
		
		if(!uploadFile.exists()){
			//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			if(uploadFile.mkdirs()){
				LOGGER.debug("[file.mkdirs] uploadFile : Directory Creation Success");
			}else{
				LOGGER.error("[file.mkdirs] uploadFile : Directory Creation Fail");
			}
		}
				
		File[] fileList = uploadFile.listFiles();
		List<String> fileArray = new ArrayList<String>();

		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					fileArray.add(fileList[i].getName());
				}
			}
		}
		
		return fileArray;
	}

	/**
	 * 동기화 대상 파일을 업로드 한다.
	 * @param file - 업로드 대상 파일
	 * @param newName - 업로드 대상 파일명
	 * @param stordFilePath - 업로드 경로
	 */
	public void writeFile(MultipartFile multipartFile, String newName, SynchrnServerVO synchrnServerVO) throws Exception {

		List<SynchrnServerVO> synchrnServerList = synchrnServerDAO.processSynchrnServerList(synchrnServerVO);
		Iterator<SynchrnServerVO> iterator = synchrnServerList.iterator();
		SynchrnServer synchrnServer = new SynchrnServer();

		InputStream stream = null;
		OutputStream bos = null;

		try {
			stream = multipartFile.getInputStream();
			File cFile = new File(EgovWebUtil.filePathBlackList(SYNCH_SERVER_PATH));

			if (!cFile.isDirectory())
				//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
				if(cFile.mkdir()){
					LOGGER.debug("[file.mkdirs] cFile : Directory Creation Success");
				}else{
					LOGGER.error("[file.mkdirs] cFile : Directory Creation Fail");
				}

			bos = new FileOutputStream(EgovWebUtil.filePathBlackList(SYNCH_SERVER_PATH + File.separator + FilenameUtils.getName(newName)));

			int bytesRead = 0;
			byte[] buffer = new byte[2048];

			while ((bytesRead = stream.read(buffer, 0, 2048)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			while (iterator.hasNext()) {
				SynchrnServerVO SynchrnServerVo = iterator.next();
				synchrnServer.setServerId(SynchrnServerVo.getServerId());
				synchrnServer.setReflctAt("N");
				synchrnServerDAO.processSynchrn(synchrnServer);
			}

		} finally {
			EgovResourceCloseHelper.close(bos, stream);
		}
	}

	/**
	 * 업로드 파일을 삭제한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 */
	public void deleteFile(String deleteFiles, SynchrnServerVO synchrnServerVO) throws Exception {

		List<SynchrnServerVO> synchrnServerList = synchrnServerDAO.processSynchrnServerList(synchrnServerVO);
		Iterator<SynchrnServerVO> iterator = synchrnServerList.iterator();
		SynchrnServer synchrnServer = new SynchrnServer();

		String[] strDeleteFiles = deleteFiles.split(";");

		for (int i = 0; i < strDeleteFiles.length; i++) {
			File uploadFile = new File(EgovWebUtil.filePathBlackList(SYNCH_SERVER_PATH + FilenameUtils.getName(strDeleteFiles[i])));
			//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			if(uploadFile.delete()){
				LOGGER.debug("[file.delete] uploadFile : File Deletion Success");
			}else{
				LOGGER.error("[file.delete] uploadFile : File Deletion Fail");
			}			
		}

		while (iterator.hasNext()) {
			SynchrnServerVO SynchrnServerVo = iterator.next();
			synchrnServer.setServerId(SynchrnServerVo.getServerId());
			synchrnServer.setReflctAt("N");
			synchrnServerDAO.processSynchrn(synchrnServer);
		}
	}
}