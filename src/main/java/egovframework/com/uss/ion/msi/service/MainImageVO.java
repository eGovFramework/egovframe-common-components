/**
 * 개요
 * - 메인화면이미지에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 메인화면이미지의 목록 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:58
 */

package egovframework.com.uss.ion.msi.service;

import java.util.List;

public class MainImageVO extends MainImage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 메인화면이미지 목록
	 */	
	List<MainImageVO> mainImageList;
	/**
	 * 삭제대상 목록
	 */		
    String[] delYn;

	/**
	 * @return the mainImageList
	 */
	public List<MainImageVO> getMainImageList() {
		return mainImageList;
	}
	/**
	 * @param mainImageList the mainImageList to set
	 */
	public void setMainImageList(List<MainImageVO> mainImageList) {
		this.mainImageList = mainImageList;
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
