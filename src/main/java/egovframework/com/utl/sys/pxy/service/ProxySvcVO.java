package egovframework.com.utl.sys.pxy.service;

import java.util.List;

/**
 * 개요
 * - 프록시서비스정보에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 프록시서비스정보의 목록 항목, 조회조건 등을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:51
 */
public class ProxySvcVO extends ProxySvc {

	private static final long serialVersionUID = 1L;
	/**
	 * 프록시 명 조회조건
	 */
	private String strProxyNm;
	/**
	 * 프록시정보 목록
	 */
	private List<?> proxySvcList;
	/**
	 * 이전 서비스 상태
	 */
	private String strPreSvcSttus;

	/**
	 * @return the strProxyNm
	 */
	public String getStrProxyNm() {
		return strProxyNm;
	}
	/**
	 * @param strProxyNm the strProxyNm to set
	 */
	public void setStrProxyNm(String strProxyNm) {
		this.strProxyNm = strProxyNm;
	}
	/**
	 * @return the proxySvcList
	 */
	public List<?> getProxySvcList() {
		return proxySvcList;
	}
	/**
	 * @param proxySvcList the proxySvcList to set
	 */
	public void setProxySvcList(List<?> proxySvcList) {
		this.proxySvcList = proxySvcList;
	}
	/**
	 * @return the strPreSvcSttus
	 */
	public String getStrPreSvcSttus() {
		return strPreSvcSttus;
	}
	/**
	 * @param strPreSvcSttus the strPreSvcSttus to set
	 */
	public void setStrPreSvcSttus(String strPreSvcSttus) {
		this.strPreSvcSttus = strPreSvcSttus;
	}

}