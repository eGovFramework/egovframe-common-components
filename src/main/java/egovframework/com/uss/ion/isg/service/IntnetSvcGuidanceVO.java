/**
 * 개요
 * - 인터넷서비스안내에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 인터넷서비스안내의 목록 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:52
 */

package egovframework.com.uss.ion.isg.service;

import java.util.List;

public class IntnetSvcGuidanceVO extends IntnetSvcGuidance {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 인터넷서비스 목록
	 */
	List <IntnetSvcGuidanceVO> intnetSvcGuidanceList;
	/**
	 * 삭제여부
	 */	
	String [] delYn;
	
	/**
	 * @return the intnetSvcGuidanceList
	 */
	public List<IntnetSvcGuidanceVO> getIntnetSvcGuidanceList() {
		return intnetSvcGuidanceList;
	}
	/**
	 * @param intnetSvcGuidanceList the intnetSvcGuidanceList to set
	 */
	public void setIntnetSvcGuidanceList(
			List<IntnetSvcGuidanceVO> intnetSvcGuidanceList) {
		this.intnetSvcGuidanceList = intnetSvcGuidanceList;
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
	
	
}
