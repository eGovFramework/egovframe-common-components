package egovframework.com.uss.ion.evt.service;

import java.util.List;

/**
 * 개요
 * - 행사관리에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 행사관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 행사관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public interface EgovEventManageService {

	/**
	 * 행사관리 정보를 관리하기 위해 등록된 행사목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	public List<EventManageVO> selectEventManageList(EventManageVO eventManageVO) throws Exception;

	/**
	 * 행사관리 목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int - 행사관리 카운트 수
	 */
	public int selectEventManageListTotCnt(EventManageVO eventManageVO) throws Exception ;
	
	/**
	 * 등록된 행사관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return EventManageVO - 행사관리 VO
	 */
	public EventManageVO selectEventManage(EventManageVO eventManageVO) throws Exception;

	/**
	 * 행사관리 정보를 신규로 등록한다.
	 * @param eventManage - 행사관리 model
	 */
	public void insertEventManage(EventManage eventManage) throws Exception;

	/**
	 * 기 등록된 행사관리 정보를 수정한다.
	 * @param eventManage - 행사관리 model
	 */
	public void updtEventManage(EventManage eventManage) throws Exception;

	/**
	 * 기 등록된 행사관리 정보를 삭제한다.
	 * @param eventManage - 행사관리 model
	 */
	public void deleteEventManage(EventManage eventManage) throws Exception;

	

	/***  행사접수관리  ****/	
	
	/**
	 * 행사접수정보를 관리하기 위해 등록된 행사관리 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사접수관리 목록
	 */
	public List<EventManageVO> selectEventAtdrnList(EventManageVO eventManageVO) throws Exception;

	/**
	 * 행사관리 목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int - 행사접수관리 카운트 수
	 */
	public int selectEventAtdrnListTotCnt(EventManageVO eventManageVO) throws Exception ;

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사접수승인 목록
	 */
	public List<EventManageVO> selectEventRceptConfmList(EventManageVO eventManageVO) throws Exception;

	/**
	 * 행사접수승인/반려 처리를 위해 등록된 행사접수 목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int - 행사접수승인 카운트 수
	 */
	public int selectEventRceptConfmListTotCnt(EventManageVO eventManageVO) throws Exception ;
	
	/**
	 * 행사일자, 행사구분 조건에 따른 행사명 목록을 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사명 목록
	 */
	public List<EventManageVO> selectEventNmList(EventManageVO eventManageVO) throws Exception;
	
	/**
	 * 등록된 행사관리의 상세정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return EventManageVO - 행사관리 VO
	 */
	public EventManageVO selectEventAtdrn(EventManageVO eventManageVO) throws Exception;

	/**
	 * 행사관리 정보를 신규로 등록한다.
	 * @param eventManage - 행사관리 model
	 */
	public void insertEventAtdrn(EventAtdrn eventAtdrn) throws Exception;

	/**
	 * 기 등록된 행사관리 정보를 삭제한다.
	 * @param eventManage - 행사관리 model
	 */
	public void deleteEventAtdrn(EventAtdrn eventAtdrn) throws Exception;

	/**
	 * 기 등록된 행사관리 정보를 승인/반려처리한다.
	 * @param eventManage - 행사관리 model
	 */
	public void updtEventAtdrn(EventAtdrn eventAtdrn, String checkedEventRceptForConfm) throws Exception;

	/**
	 * 행사접수자 정보를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return List - 행사관리 목록
	 */
	public List<EventManageVO> selectEventReqstAtdrnList(EventManageVO eventManageVO) throws Exception;

	/**
	 * 행사접수자 목록 총 갯수를 조회한다.
	 * @param eventManageVO - 행사관리 VO
	 * @return int - 행사관리 카운트 수
	 */
	public int selectEventReqstAtdrnListTotCnt(EventManageVO eventManageVO) throws Exception ;
	
}