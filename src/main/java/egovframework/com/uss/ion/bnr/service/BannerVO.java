/**
 * 개요
 * - 배너에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 배너의 목록 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:07:13
 */

package egovframework.com.uss.ion.bnr.service;

import java.util.List;

public class BannerVO extends Banner {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;	
	/**
	 * 배너 목록
	 */	
	List<BannerVO> bannerList;
	/**
	 * 삭제대상 목록
	 */		
    String[] delYn;
	/**
	 * 결과 반영 타입
	 * vertical : 세로
	 * horizontal : 가로
	 */		
    String resultType = "horizontal";

	/**
	 * @return the bannerList
	 */
	public List<BannerVO> getBannerList() {
		return bannerList;
	}
	/**
	 * @param bannerList the bannerList to set
	 */
	public void setBannerList(List<BannerVO> bannerList) {
		this.bannerList = bannerList;
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
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}
	/**
	 * @param resultType the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
    
    
}
