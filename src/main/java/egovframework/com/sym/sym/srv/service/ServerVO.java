package egovframework.com.sym.sym.srv.service;

import java.util.List;

/**
 * 개요
 * - 서버정보에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 서버정보의 목록 항목 및 조회조건을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 28-6-2010 오전 10:44:56
 */
public class ServerVO extends Server {

	private static final long serialVersionUID = 1L;

	/**
	 * 서버 목록
	 */
	private List<?> serverList;

	/**
	 * 삭제대상 목록
	 */
	String delYn[];
	/**
	 * 서버명 조회조건
	 */
	private String strServerNm;

	/**
	 * @return the serverList
	 */
	public List<?> getServerList() {
		return serverList;
	}
	/**
	 * @param serverList the serverList to set
	 */
	public void setServerList(List<?> serverList) {
		this.serverList = serverList;
	}
	/**
	 * @return the delYn
	 */
	public String[] getDelYn() {
		return delYn;
	}
	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String[] delYn) {
		this.delYn = delYn;
	}
	/**
	 * @return the strServerNm
	 */
	public String getStrServerNm() {
		return strServerNm;
	}
	/**
	 * @param strServerNm the strServerNm to set
	 */
	public void setStrServerNm(String strServerNm) {
		this.strServerNm = strServerNm;
	}
}