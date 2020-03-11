/**
 * 개요
 * - 로그인화면이미지에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인화면이미지의 목록 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:56
 */

package egovframework.com.uss.ion.lsi.service;

import java.util.List;

public class LoginScrinImageVO extends LoginScrinImage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 로그인화면이미지 목록
	 */	
	List<LoginScrinImageVO> loginScrinImageList;
	/**
	 * 삭제대상 목록
	 */		
    String[] delYn;
    
	/**
	 * @return the loginScrinImageList
	 */
	public List<LoginScrinImageVO> getLoginScrinImageList() {
		return loginScrinImageList;
	}
	/**
	 * @param loginScrinImageList the loginScrinImageList to set
	 */
	public void setLoginScrinImageList(List<LoginScrinImageVO> loginScrinImageList) {
		this.loginScrinImageList = loginScrinImageList;
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
