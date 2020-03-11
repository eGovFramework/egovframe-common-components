package egovframework.com.utl.sys.srm.service;

import java.util.List;

/**
 * 개요
 * - 서버자원모니터링에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버자원모니터링의 목록 항목, 조회조건, 삭제대상 등을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 06-9-2010 오전 11:24:00
 */
public class ServerResrceMntrngVO extends ServerResrceMntrng {

	private static final long serialVersionUID = 1L;
	/**
	 * 서버자원모니터링 서버명 조회조건
	 */
	private String strServerNm;
	/**
	 * 시작일자 검색조건
	 */
	private String strStartDt;
	/**
	 * 종료일자 검색조건
	 */
	private String strEndDt;
	/**
	 * 서버자원모니터링 목록
	 */
	private List<ServerResrceMntrngVO> serverResrceMntrngList;
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
	/**
	 * @return the strStartDt
	 */
	public String getStrStartDt() {
		return strStartDt;
	}
	/**
	 * @param strStartDt the strStartDt to set
	 */
	public void setStrStartDt(String strStartDt) {
		this.strStartDt = strStartDt;
	}
	/**
	 * @return the strEndDt
	 */
	public String getStrEndDt() {
		return strEndDt;
	}
	/**
	 * @param strEndDt the strEndDt to set
	 */
	public void setStrEndDt(String strEndDt) {
		this.strEndDt = strEndDt;
	}
	/**
	 * @return the serverResrceMntrngList
	 */
	public List<ServerResrceMntrngVO> getServerResrceMntrngList() {
		return serverResrceMntrngList;
	}
	/**
	 * @param serverResrceMntrngList the serverResrceMntrngList to set
	 */
	public void setServerResrceMntrngList(List<ServerResrceMntrngVO> serverResrceMntrngList) {
		this.serverResrceMntrngList = serverResrceMntrngList;
	}

}