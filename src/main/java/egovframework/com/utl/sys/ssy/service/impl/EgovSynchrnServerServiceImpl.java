package egovframework.com.utl.sys.ssy.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import jakarta.annotation.Resource;

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
 *   2026.04.20    유지보수      국가사이버안보센터(NCSC)의 보안 점검 결과 반영을 통한 보안패치
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
	 * @param synchrnServer - 동기화대상 서버
	 * @return List - 동기화대상 서버 목록
	 */
	@Override
	public List<SynchrnServer> selectSynchrnServerList(SynchrnServer synchrnServer) throws Exception {
		return synchrnServerDAO.selectSynchrnServerList(synchrnServer);
	}

	/**
	 * 동기화대상 서버목록 총 개수를 조회한다.
	 * @param synchrnServer - 동기화대상 서버
	 * @return int - 동기화대상 서버 카운트 수
	 */
	@Override
	public int selectSynchrnServerListTotCnt(SynchrnServer synchrnServer) throws Exception {
		return synchrnServerDAO.selectSynchrnServerListTotCnt(synchrnServer);
	}

	/**
	 * 등록된 동기화대상 서버의 상세정보를 조회한다.
	 * @param synchrnServer - 동기화대상 서버
	 * @return SynchrnServer - 동기화대상 서버
	 */
	@Override
	public SynchrnServer selectSynchrnServer(SynchrnServer synchrnServer) throws Exception {
		return synchrnServerDAO.selectSynchrnServer(synchrnServer);
	}

	/**
	 * 등록된 동기화대상 서버의 파일 목록을 조회한다.
	 * @param synchrnServer - 동기화대상 서버
	 * @return List<String> - String Type List
	 */
	@Override
	public List<String> selectSynchrnServerFiles(SynchrnServer synchrnServer) throws Exception {

		List<String> list = new ArrayList<>();
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");
		final int timeoutMs = 3000; // 연동 가능 여부 확인 타임아웃(약 3초)

		// 1) FTP 연동 가능 여부 먼저 확인한다.
		if (!EgovWebUtil.isIPAddress(synchrnServer.getServerIp())) {
			return list;
		}

		try {
			// 연동 가능 여부 확인(connect/login) 단계에만 타임아웃 적용
			ftpClient.setDefaultTimeout(timeoutMs);
			ftpClient.setConnectTimeout(timeoutMs);

			InetAddress host = InetAddress.getByName(synchrnServer.getServerIp());
			ftpClient.connect(host, Integer.parseInt(synchrnServer.getServerPort()));
			ftpClient.setSoTimeout(timeoutMs);
			boolean isLogin = ftpClient.login(synchrnServer.getFtpId(), synchrnServer.getFtpPassword());
			// 2) 연동되지 않으면 빈 리스트를 반환한다.
			if (!isLogin) {
				return list;
			}
			// 연동 확인 이후 목록 조회 단계에서는 타임아웃 해제
			ftpClient.setSoTimeout(0);
			ftpClient.setDataTimeout(0);

			// 3) 연동되면 파일 목록 조회를 진행한다.
			ftpClient.changeWorkingDirectory(synchrnServer.getSynchrnLc());
			FTPFile[] fTPFile = ftpClient.listFiles(synchrnServer.getSynchrnLc());
			if (fTPFile != null) {
				for (FTPFile element : fTPFile) {
					if (element != null && element.isFile()) {
						list.add(element.getName());
					}
				}
			}
		} catch (IOException e) {
			EgovBasicLogger.debug("FTP connection/listing failed", e);
		} finally {
			closeFtpQuietly(ftpClient);
		}

		return list;
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 삭제한다.
	 * @param synchrnServer - 동기화대상 서버
	 */
	@Override
	public void deleteSynchrnServerFile(SynchrnServer synchrnServer) throws Exception {

		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");

		if (!EgovWebUtil.isIPAddress(synchrnServer.getServerIp())) {
			throw new RuntimeException("IP is needed. (" + synchrnServer.getServerIp() + ")");
		}

		InetAddress host = InetAddress.getByName(synchrnServer.getServerIp());

		ftpClient.connect(host, Integer.parseInt(synchrnServer.getServerPort()));
		ftpClient.login(synchrnServer.getFtpId(), synchrnServer.getFtpPassword());

		FTPFile[] fTPFile = null;

		try {
			ftpClient.changeWorkingDirectory(synchrnServer.getSynchrnLc());
			fTPFile = ftpClient.listFiles(synchrnServer.getSynchrnLc());

			for (FTPFile element : fTPFile) {
				//KISA 보안약점 조치 (2018-10-29, 윤창원)
				if (EgovStringUtil.isNullToString(synchrnServer.getDeleteFileNm()).equals(element.getName())) {
					ftpClient.deleteFile(element.getName());
				}
			}

			SynchrnServer updateReflct = new SynchrnServer();
			updateReflct.setServerId(synchrnServer.getServerId());
			updateReflct.setReflctAt("N");
			synchrnServerDAO.processSynchrn(updateReflct);

		} finally {
			ftpClient.logout();
		}
	}

	/**
	 * 등록된 동기화대상 서버의 파일을 다운로드 한다.
	 * @param synchrnServer - 동기화대상 서버
	 * @param fileNm - 다운로드 대상 파일
	 */
	@Override
	public void downloadFtpFile(SynchrnServer synchrnServer, String fileNm) throws Exception {

		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");

		if (!EgovWebUtil.isIPAddress(synchrnServer.getServerIp())) {
			throw new RuntimeException("IP is needed. (" + synchrnServer.getServerIp() + ")");
		}

		InetAddress host = InetAddress.getByName(synchrnServer.getServerIp());

		ftpClient.connect(host, Integer.parseInt(synchrnServer.getServerPort()));
		ftpClient.login(synchrnServer.getFtpId(), synchrnServer.getFtpPassword());
		ftpClient.changeWorkingDirectory(synchrnServer.getSynchrnLc());

		File downFile = new File(EgovWebUtil.filePathBlackList(synchrnServer.getFilePath() + fileNm));
		OutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(downFile);
			ftpClient.retrieveFile(fileNm, outputStream);
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

		ftpClient.logout();
	}

	/**
	 * 동기화대상 서버정보를 신규로 등록한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	@Override
	public SynchrnServer insertSynchrnServer(SynchrnServer synchrnServer) throws Exception {
		synchrnServerDAO.insertSynchrnServer(synchrnServer);
		synchrnServer.setServerId(synchrnServer.getServerId());
		return synchrnServerDAO.selectSynchrnServer(synchrnServer);
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 수정한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	@Override
	public void updateSynchrnServer(SynchrnServer synchrnServer) throws Exception {
		synchrnServerDAO.updateSynchrnServer(synchrnServer);
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 삭제한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	@Override
	public void deleteSynchrnServer(SynchrnServer synchrnServer) throws Exception {
		synchrnServerDAO.deleteSynchrnServer(synchrnServer);
	}

	/**
	 * 업로드 파일을 동기화대상 서버들을 대상으로 동기화 처리를 한다.
	 * @param synchrnServer - 동기화대상 서버
	 * @return boolean - 성공여부
	 */
	@Override
	public boolean processSynchrn(SynchrnServer synchrnServer, File[] uploadFile) throws Exception {

		List<SynchrnServer> synchrnServerList = synchrnServerDAO.processSynchrnServerList(synchrnServer);
		SynchrnServer result = new SynchrnServer();
		boolean reflctAt = false;
		boolean allConnected = true;

		for (SynchrnServer list : synchrnServerList) {
			reflctAt = processFtp(list.getServerIp(), Integer.parseInt(list.getServerPort()), list.getFtpId(), list.getFtpPassword(),
					list.getSynchrnLc(), list.getFilePath(), uploadFile);

			result.setServerId(list.getServerId());
			if (reflctAt) {
				result.setReflctAt("Y");
			} else {
				result.setReflctAt("N");
				allConnected = false;
			}

			synchrnServerDAO.processSynchrn(result);
		}

		return allConnected;
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

		List<String> list = new ArrayList<>();
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("euc-kr");

		try {
			if (!EgovWebUtil.isIPAddress(serverIp)) {
				throw new RuntimeException("IP is needed. (" + serverIp + ")");
			}

			InetAddress host = InetAddress.getByName(serverIp);
			ftpClient.connect(host, port);
			if (!ftpClient.login(user, password)) {
				throw new IOException("FTP Client Login Error");
			}
			ftpClient.enterLocalPassiveMode();

			String p = EgovStringUtil.isNullToString(synchrnPath);
			if ("".equals(p)) {
				p = "/";
			}
			if (!ftpClient.changeWorkingDirectory(p)) {
				return list;
			}

			FTPFile[] fTPFile = ftpClient.listFiles();
			if (fTPFile == null) {
				return list;
			}
			for (FTPFile element : fTPFile) {
				if (element != null) {
					list.add(element.getName());
				}
			}
		} finally {
			closeFtpQuietly(ftpClient);
		}
		return list;
	}

	/**
	 * 동기화 서버들을 대상으로 FTP Upload 처리를 한다.
	 * @param serverIp 서버 IP
	 * @param port 포트
	 * @param user FTP 사용자
	 * @param password FTP 비밀번호
	 * @param synchrnPath 동기화 경로
	 * @param filePath 로컬 파일 경로
	 * @param uploadFile 업로드 파일 배열
	 * @return boolean 성공 여부
	 */
	public boolean processFtp(String serverIp, int port, String user, String password, String synchrnPath, String filePath, File[] uploadFile) throws Exception {

		boolean upload = false;
		FTPClient ftpClient = new FTPClient();
		final int timeoutMs = 3000; // 동기화 시 FTP 연동 확인 타임아웃(약 3초)

		try {
			if (!EgovWebUtil.isIPAddress(serverIp)) { // 2011.10.25 보안점검 후속조치
				throw new RuntimeException("IP is needed. (" + serverIp + ")");
			}

			if (synchrnPath == null) {
				synchrnPath = "";
			}

			InetAddress host = InetAddress.getByName(serverIp);
			ftpClient.setDefaultTimeout(timeoutMs);
			ftpClient.setConnectTimeout(timeoutMs);
			ftpClient.connect(host, port);
			ftpClient.setSoTimeout(timeoutMs);
			if (!ftpClient.login(user, password)) {
				throw new Exception("FTP Client Login Error : \n");
			}
			ftpClient.enterLocalPassiveMode();

			if (synchrnPath.length() != 0) {
				ftpClient.changeWorkingDirectory(synchrnPath);
			}

			FTPFile[] fTPFile = ftpClient.listFiles(synchrnPath);
			if (fTPFile == null) {
				fTPFile = new FTPFile[0];
			}

			FileInputStream fis = null;
			try {
				if (uploadFile == null) {
					uploadFile = new File[0];
				}
				for (File element : uploadFile) {
					if (element != null && element.isFile()) {
						if (!isExist(fTPFile, element)) {
							fis = new FileInputStream(element);
							//ftpClient.setFileType(FTP.ASCII_FILE_TYPE); // TEXT FILE 전송
							ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // 바이너리 파일 전송
							ftpClient.storeFile(synchrnPath + element.getName(), fis);
						}
						if (fis != null) {
							fis.close();
						}
						fis = null;
					}
				}

				// 업로드 파일 목록에 없는  FTP 서버에 있는 파일을 삭제한다.
				fTPFile = ftpClient.listFiles(synchrnPath);
				if (fTPFile == null) {
					fTPFile = new FTPFile[0];
				}
				deleteFtpFile(ftpClient, fTPFile, uploadFile);

				upload = true;

			} catch (IOException ex) {
				EgovBasicLogger.debug("FTP IO error", ex);
			} finally {
				EgovResourceCloseHelper.close(fis);
			}

		} catch (SocketTimeoutException e) {
			EgovBasicLogger.debug("processFtp timeout (3s)", e);
			upload = false;
		} catch (IOException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			EgovBasicLogger.debug("processFtp error (IOException)", e);
			upload = false;
		} catch (Exception e) {
			EgovBasicLogger.debug("processFtp error", e);
			upload = false;
		} finally {
			closeFtpQuietly(ftpClient);
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

		for (FTPFile fTPFile : fTPFiles) {
			if (fTPFile.isFile()) {
				if (fTPFile.getName().equals(targetFile.getName())) {
					isExist = true;
				}
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

		for (FTPFile fTPFile : fTPFiles) {
			isExist = false;
			for (File element : uploadFile) {
				if (fTPFile.isFile()) {
					if (fTPFile.getName().equals(element.getName())) {
						isExist = true;
					}
				}
			}

			if (!isExist) {
				if (fTPFile.isFile()) {
					ftpClient.deleteFile(fTPFile.getName());
				}
			}
		}
	}

	/**
	 * 동기화 로컬 경로({@link #SYNCH_SERVER_PATH})에 있는 일반 파일만 나열한다. {@link #getFileName()}·{@link #getSyncLocalFiles()}의 공통 기준.
	 */
	private List<File> listLocalSyncPlainFiles() throws Exception {

		File uploadFile = new File(EgovWebUtil.filePathBlackList(SYNCH_SERVER_PATH));

		if (!uploadFile.exists()) {
			//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			if (uploadFile.mkdirs()) {
				LOGGER.debug("[file.mkdirs] uploadFile : Directory Creation Success");
			} else {
				LOGGER.error("[file.mkdirs] uploadFile : Directory Creation Fail");
			}
		}

		File[] fileList = uploadFile.listFiles();
		List<File> fileArray = new ArrayList<>();

		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		if (fileList != null) {
			for (File element : fileList) {
				if (element != null && element.isFile()) {
					fileArray.add(element);
				}
			}
		}

		return fileArray;
	}

	/**
	 * 업로드 파일의 목록을 조회한다.
	 * @param filePath - 업로드 경로
	 * @return List - 업로드 파일 리스트
	 */
	@Override
	public List<String> getFileName() throws Exception {

		List<String> fileArray = new ArrayList<>();
		for (File f : listLocalSyncPlainFiles()) {
			fileArray.add(f.getName());
		}

		return fileArray;
	}

	@Override
	public File[] getSyncLocalFiles() throws Exception {
		List<File> files = listLocalSyncPlainFiles();
		return files.toArray(new File[0]);
	}

	/**
	 * 동기화 대상 파일을 업로드 한다.
	 * @param file - 업로드 대상 파일
	 * @param newName - 업로드 대상 파일명
	 * @param stordFilePath - 업로드 경로
	 */
	@Override
	public void writeFile(MultipartFile multipartFile, String newName, SynchrnServer synchrnServer) throws Exception {

		//2026.02.28 KISA 취약점 조치
		if (multipartFile == null || multipartFile.isEmpty()) {
			throw new IOException("업로드 파일이 없습니다.");
		}

		List<SynchrnServer> synchrnServerList = synchrnServerDAO.processSynchrnServerList(synchrnServer);
		SynchrnServer processRow = new SynchrnServer();

		InputStream stream = null;
		OutputStream bos = null;

		try {
			stream = multipartFile.getInputStream();
			//2026.02.28 KISA 취약점 조치
			if (stream == null) {
				throw new IOException("업로드 파일 스트림을 열 수 없습니다.");
			}
	
			File cFile = new File(EgovWebUtil.filePathBlackList(SYNCH_SERVER_PATH));

			if (!cFile.isDirectory()) {
				//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
				if(cFile.mkdir()){
					LOGGER.debug("[file.mkdirs] cFile : Directory Creation Success");
				}else{
					LOGGER.error("[file.mkdirs] cFile : Directory Creation Fail");
				}
			}

			bos = new FileOutputStream(EgovWebUtil.filePathBlackList(SYNCH_SERVER_PATH + File.separator + FilenameUtils.getName(newName)));

			int bytesRead = 0;
			byte[] buffer = new byte[2048];

			while ((bytesRead = stream.read(buffer, 0, 2048)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			for (SynchrnServer item : synchrnServerList) {
				processRow.setServerId(item.getServerId());
				processRow.setReflctAt("N");
				synchrnServerDAO.processSynchrn(processRow);
			}

		} finally {
			EgovResourceCloseHelper.close(bos, stream);
		}
	}

	/**
	 * 업로드 파일을 삭제한다.
	 * @param synchrnServer - 동기화대상 서버
	 */
	@Override
	public void deleteFile(String deleteFiles, SynchrnServer synchrnServer) throws Exception {

		List<SynchrnServer> synchrnServerList = synchrnServerDAO.processSynchrnServerList(synchrnServer);
		SynchrnServer processRow = new SynchrnServer();

		String[] strDeleteFiles = deleteFiles.split(";");
		Set<String> allowedNames = new HashSet<>(getFileName());

		for (String strDeleteFile : strDeleteFiles) {
			if (EgovStringUtil.isNullToString(strDeleteFile).isEmpty()) {
				continue;
			}
			String nameOnly = FilenameUtils.getName(strDeleteFile);
			if (!allowedNames.contains(nameOnly)) {
				LOGGER.warn("[file.delete] skip: not in sync file list. {}", nameOnly);
				continue;
			}
			File uploadFile = new File(EgovWebUtil.filePathBlackList(SYNCH_SERVER_PATH + File.separator + nameOnly));
			//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			if (uploadFile.delete()) {
				LOGGER.debug("[file.delete] uploadFile : File Deletion Success");
			} else {
				LOGGER.error("[file.delete] uploadFile : File Deletion Fail");
			}
		}

		for (SynchrnServer item : synchrnServerList) {
			processRow.setServerId(item.getServerId());
			processRow.setReflctAt("N");
			synchrnServerDAO.processSynchrn(processRow);
		}
	}

	private static void closeFtpQuietly(FTPClient ftpClient) {
		if (ftpClient == null) {
			return;
		}
		try {
			if (ftpClient.isConnected()) {
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			EgovBasicLogger.debug("closeFtpQuietly", e);
		}
	}
}