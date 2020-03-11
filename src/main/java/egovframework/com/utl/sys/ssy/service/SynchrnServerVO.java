package egovframework.com.utl.sys.ssy.service;

import java.io.File;
import java.util.List;

/**
 * 개요
 * - 동기화대상 서버에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 동기화대상 서버의 목록 항목, 조회조건 등을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:57
 */

public class SynchrnServerVO extends SynchrnServer {

	private static final long serialVersionUID = 1L;
	/**
	 * 동기화대상 서버명 조회조건
	 */
	private String strSynchrnServerNm;
	/**
	 * 동기화대상 서버 목록
	 */
	private List<?> synchrnServerList;
	/**
	 * 동기화 대상 파일
	 */
	private File synchrnFile;
	/**
	 * 삭제 대상 파일
	 */
	private String deleteFileNm;
	/**
	 * FTP 서버 파일 목록
	 */
	private List<?> ftpFileList;
	/**
	 * 업로드 위치
	 */
	private String filePath;
	/**
	 * 반영여부
	 */
	private String strReflctAt;
	/**
	 * @return the strSynchrnServerNm
	 */
	public String getStrSynchrnServerNm() {
		return strSynchrnServerNm;
	}
	/**
	 * @param strSynchrnServerNm the strSynchrnServerNm to set
	 */
	public void setStrSynchrnServerNm(String strSynchrnServerNm) {
		this.strSynchrnServerNm = strSynchrnServerNm;
	}
	/**
	 * @return the synchrnServerList
	 */
	public List<?> getSynchrnServerList() {
		return synchrnServerList;
	}
	/**
	 * @param synchrnServerList the synchrnServerList to set
	 */
	public void setSynchrnServerList(List<?> synchrnServerList) {
		this.synchrnServerList = synchrnServerList;
	}
	/**
	 * @return the synchrnFile
	 */
	public File getSynchrnFile() {
		return synchrnFile;
	}
	/**
	 * @param synchrnFile the synchrnFile to set
	 */
	public void setSynchrnFile(File synchrnFile) {
		this.synchrnFile = synchrnFile;
	}
	/**
	 * @return the deleteFileNm
	 */
	public String getDeleteFileNm() {
		return deleteFileNm;
	}
	/**
	 * @param deleteFileNm the deleteFileNm to set
	 */
	public void setDeleteFileNm(String deleteFileNm) {
		this.deleteFileNm = deleteFileNm;
	}
	/**
	 * @return the ftpFileList
	 */
	public List<?> getFtpFileList() {
		return ftpFileList;
	}
	/**
	 * @param ftpFileList the ftpFileList to set
	 */
	public void setFtpFileList(List<?> ftpFileList) {
		this.ftpFileList = ftpFileList;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the strReflctAt
	 */
	public String getStrReflctAt() {
		return strReflctAt;
	}
	/**
	 * @param strReflctAt the strReflctAt to set
	 */
	public void setStrReflctAt(String strReflctAt) {
		this.strReflctAt = strReflctAt;
	}

}