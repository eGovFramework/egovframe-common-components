package egovframework.com.uss.ion.pwm.service;

import java.util.List;

/**
 * 개요
 * - 팝업창에 대한 Service Interface를 정의한다.
 *
 * 상세내용
 * - 팝업창에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 팝업창의 조회기능은 목록조회, 상세조회, 팝업사용자 보기로 구분된다.
 * @author 이창원
 * @version 1.0
 * @created 05-8-2009 오후 2:19:58
 */
public interface EgovPopupManageService {

	/**
	 * 기 등록된 팝업창정보를 삭제한다.
	 * @param popupManage - 팝업창 model
	 * @return boolean - 반영성공 여부
	 *
	 * @param popupManage
	 */
	public void deletePopup(PopupManageVO popupManageVO) throws Exception;

	/**
	 * 팝업창정보를 신규로 등록한다.
	 * @param popupManage - 팝업창 model
	 * @return boolean - 반영성공 여부
	 *
	 * @param popupManage
	 */
	public void insertPopup(PopupManageVO popupManageVO) throws Exception;

	/**
	 * 기 등록된 팝업창정보를 수정한다.
	 * @param popupManage - 팝업창 model
	 * @return boolean - 반영성공 여부
	 *
	 * @param popupManage
	 */
	public void updatePopup(PopupManageVO popupManageVO) throws Exception;

	/**
	 * 팝업창을 사용자 화면에서 볼수 있는 정보들을 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return popupManageVO - 팝업창 Vo
	 *
	 * @param popupManageVO
	 */
	public PopupManageVO selectPopup(PopupManageVO popupManageVO) throws Exception;

	/**
	 * 팝업창의 취약점을 관리하기 위해 등록된 팝업창 화이트리스트를 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return List - 팝업창 화이트리스트 목록
	 *
	 * @param popupManageVO
	 */
	public List<?> selectPopupWhiteList() throws Exception;
	
	/**
	 * 팝업창를 관리하기 위해 등록된 팝업창목록을 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return List - 팝업창 목록
	 *
	 * @param popupManageVO
	 */
	public List<?> selectPopupList(PopupManageVO popupManageVO) throws Exception;

	/**
	 * 팝업창를 관리하기 위해 등록된 팝업창목록을 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return List - 팝업창 목록
	 *
	 * @param popupManageVO
	 */
	public int selectPopupListCount(PopupManageVO popupManageVO) throws Exception;

	/**
	 * 팝업창을 사용하기 위해 팝업창목록을 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return List - 팝업창 목록
	 *
	 * @param popupManageVO
	 */
	public List<?> selectPopupMainList(PopupManageVO popupManageVO) throws Exception;

}