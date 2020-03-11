package egovframework.com.utl.sys.pxy.service;

import java.util.List;

/**
 * 개요
 * - 프록시로그정보에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 프록시로그정보의 목록 항목, 조회조건, 삭제대상 목록 등을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:50
 */
public class ProxyLogVO extends ProxyLog {

	private static final long serialVersionUID = 1L;
	/**
	 * 프록시서비스접속 시작일자 조회조건
	 */
	public String strStartDate;
	/**
	 * 프록시서비스접속 종료일자 조회조건
	 */
	public String strEndDate;
	/**
	 * 프록시 로그 목록
	 */
	public List<?> proxyLogList;

	/**
	 * @return the strStartDate
	 */
	public String getStrStartDate() {
		return strStartDate;
	}
	/**
	 * @param strStartDate the strStartDate to set
	 */
	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}
	/**
	 * @return the strEndDate
	 */
	public String getStrEndDate() {
		return strEndDate;
	}
	/**
	 * @param strEndDate the strEndDate to set
	 */
	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}
	/**
	 * @return the proxyLogList
	 */
	public List<?> getProxyLogList() {
		return proxyLogList;
	}
	/**
	 * @param proxyLogList the proxyLogList to set
	 */
	public void setProxyLogList(List<?> proxyLogList) {
		this.proxyLogList = proxyLogList;
	}
}