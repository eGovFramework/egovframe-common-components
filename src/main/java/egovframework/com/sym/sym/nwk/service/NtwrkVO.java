/**
 * <pre>개요
 * - 네트워크에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 네트워크의 목록 항목, 조회조건 등을 관리한다.</pre> 
 * @author lee.m.j
 * @version 1.0
 * @created 19-8-2010 오후 4:34:37
 */

package egovframework.com.sym.sym.nwk.service;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class NtwrkVO extends Ntwrk {

	private static final long serialVersionUID = 1L;

	/**
	 * 관리항목
	 */
	private String strManageIem;
	/**
	 * 사용자명
	 */
	private String strUserNm;
	/**
	 * 네트워크목록
	 */
	private List<NtwrkVO> ntwrkList;
	/**
	 * 삭제여부
	 */
	@Getter
	@Setter
	private String delYn[];

	/**
	 * @return the strManageIem
	 */

	public String getStrManageIem() {
		return strManageIem;
	}

	/**
	 * @param strManageIem the strManageIem to set
	 */
	public void setStrManageIem(String strManageIem) {
		this.strManageIem = strManageIem;
	}

	/**
	 * @return the strUserNm
	 */
	public String getStrUserNm() {
		return strUserNm;
	}

	/**
	 * @param strUserNm the strUserNm to set
	 */
	public void setStrUserNm(String strUserNm) {
		this.strUserNm = strUserNm;
	}

	/**
	 * @return the ntwrkList
	 */
	public List<NtwrkVO> getNtwrkList() {
		return ntwrkList;
	}

	/**
	 * @param ntwrkList the ntwrkList to set
	 */
	public void setNtwrkList(List<NtwrkVO> ntwrkList) {
		this.ntwrkList = Collections.unmodifiableList(ntwrkList);
	}

}
