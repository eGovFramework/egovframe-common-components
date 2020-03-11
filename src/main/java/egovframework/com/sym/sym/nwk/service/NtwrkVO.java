/**
 * 개요
 * - 네트워크에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 네트워크의 목록 항목, 조회조건 등을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 19-8-2010 오후 4:34:37
 */

package egovframework.com.sym.sym.nwk.service;

import java.util.List;

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
    private List<?> ntwrkList;
	/**
	 * 삭제여부
	 */
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
	public List<?> getNtwrkList() {
		return ntwrkList;
	}
	/**
	 * @param ntwrkList the ntwrkList to set
	 */
	public void setNtwrkList(List<?> ntwrkList) {
		this.ntwrkList = ntwrkList;
	}
	/**
	 * @return the delYn
	 */
	public String[] getDelYn() {
		String[] ret = null;

		if (delYn != null) {
			ret = new String[delYn.length];

			for (int i = 0; i < delYn.length; i++) {
				ret[i] = delYn[i];
			}
		}
		return ret;
	}
	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String[] delYn) {
		this.delYn = new String[delYn.length];

		for (int i = 0; i <  delYn.length; ++i) {
			this.delYn[i] = delYn[i];
		}
	}


}
