package egovframework.com.utl.sys.ssy.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 개요
 * - 동기화대상 서버에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 동기화대상 서버에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 동기화대상 서버의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:34
 */
public interface EgovSynchrnServerService {

	/**
	 * 동기화대상 서버를 관리하기 위해 등록된 서버목록을 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return List - 동기화대상 서버 목록
	 */
	public List<SynchrnServerVO> selectSynchrnServerList(SynchrnServerVO synchrnServerVO) throws Exception;

	/**
	 * 동기화대상 서버 목록 총 갯수를 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return int - 동기화대상 서버 카운트 수
	 */
	public int selectSynchrnServerListTotCnt(SynchrnServerVO synchrnServerVO) throws Exception;

	/**
	 * 등록된 동기화대상 서버의 상세정보를 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return SynchrnServerVO - 동기화대상 서버 Vo
	 */
	public SynchrnServerVO selectSynchrnServer(SynchrnServerVO synchrnServerVO) throws Exception;

	/**
	 * 등록된 동기화대상 서버의 파일 목록을 조회한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return List<String> - String Type List
	 */
	public List<String> selectSynchrnServerFiles(SynchrnServerVO synchrnServerVO) throws Exception;
	
	/**
	 * 등록된 동기화대상 서버의 파일을 삭제한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 */
	public void deleteSynchrnServerFile(SynchrnServerVO synchrnServerVO) throws Exception;
	
	/**
	 * 등록된 동기화대상 서버의 파일을 다운로드 한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @param fileNm - 다운로드 대상 파일
	 */
	public void downloadFtpFile(SynchrnServerVO synchrnServerVO, String fileNm) throws Exception;
	
	/**
	 * 동기화대상 서버정보를 신규로 등록한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 * @return SynchrnServerVO - 동기화대상 서버 Vo
	 */
	public SynchrnServerVO insertSynchrnServer(SynchrnServer synchrnServer, SynchrnServerVO synchrnServerVO) throws Exception;

	/**
	 * 기 등록된 동기화대상 서버정보를 수정한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	public void updateSynchrnServer(SynchrnServer synchrnServer) throws Exception;

	/**
	 * 기 등록된 동기화대상 서버정보를 삭제한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	public void deleteSynchrnServer(SynchrnServer synchrnServer) throws Exception;

	/**
	 * 업로드 파일을 동기화대상 서버들을 대상으로 동기화 처리를 한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 * @return boolean - 성공여부
	 */
	public boolean processSynchrn(SynchrnServerVO synchrnServerVO, File[] uploadFile) throws Exception;

	/**
	 * 업로드 파일의 목록을 조회한다.
	 * @param filePath - 업로드 경로
	 * @return List - 업로드 파일 리스트
	 */
	public List<String> getFileName(String filePath) throws Exception;
	
	/**
	 * 동기화 대상 파일을 업로드 한다.
	 * @param file - 업로드 대상 파일
	 * @param newName - 업로드 대상 파일명
	 * @param stordFilePath - 업로드 경로
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 */
	public void writeFile(MultipartFile multipartFile, String newName, String stordFilePath, SynchrnServerVO synchrnServerVO) throws Exception;
	
	/**
	 * 업로드 파일을 삭제한다.
	 * @param synchrnServerVO - 동기화대상 서버 Vo
	 */
	public void deleteFile(String deleteFiles, String stordFilePath, SynchrnServerVO synchrnServerVO) throws Exception;
}