package egovframework.com.utl.sys.srm.service;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

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
	@Getter
	@Setter
	private String strServerNm;
	/**
	 * 시작일자 검색조건
	 */
	@Getter
	@Setter
	private String strStartDt;
	/**
	 * 종료일자 검색조건
	 */
	@Getter
	@Setter
	private String strEndDt;
	/**
	 * 서버자원모니터링 목록
	 */
	private List<ServerResrceMntrngVO> serverResrceMntrngList;
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
		this.serverResrceMntrngList = Collections.unmodifiableList(serverResrceMntrngList);
	}

}
