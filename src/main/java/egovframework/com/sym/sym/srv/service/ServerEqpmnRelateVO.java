package egovframework.com.sym.sym.srv.service;

import java.util.List;

/**
 * 개요
 * - 서버장비관계에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 서버장비관계의 목록 항목 및 조회조건을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 28-6-2010 오전 10:44:55
 */
public class ServerEqpmnRelateVO extends ServerEqpmnRelate {

	private static final long serialVersionUID = 1L;
	/**
	 * 서버장비관계 목록
	 */
	private List<?> serverEqpmnRelateList;
	/**
	 * 삭제대상 목록
	 */
	String delYn[];
	/**
	 * 서버ID 조회조건
	 */
	private String strServerId;

	/**
	 * 서버명 조회조건
	 */
	private String strServerNm;

	/**
	 * @return the serverEqpmnRelateList
	 */
	public List<?> getServerEqpmnRelateList() {
		return serverEqpmnRelateList;
	}

	/**
	 * @param serverEqpmnRelateList the serverEqpmnRelateList to set
	 */
	public void setServerEqpmnRelateList(List<?> serverEqpmnRelateList) {
		this.serverEqpmnRelateList = serverEqpmnRelateList;
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
	 * @return the strServerId
	 */
	public String getStrServerId() {
		return strServerId;
	}

	/**
	 * @param strServerId the strServerId to set
	 */
	public void setStrServerId(String strServerId) {
		this.strServerId = strServerId;
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