package egovframework.com.uss.ion.mtg.service;

import java.util.List;

/**
 * 개요
 * - 회의실관리에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 회의실관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public interface EgovMtgPlaceManageService {

	/**
	 * 회의실관리 정보를 관리하기 위해 등록된 회의실 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	public List<MtgPlaceManageVO> selectMtgPlaceManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 회의실관리 목록 총 개수를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 회의실관리 카운트 수
	 */
	public int selectMtgPlaceManageListTotCnt(MtgPlaceManageVO mtgPlaceManageVO) throws Exception ;
	
	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	public MtgPlaceManage selectMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 회의실관리 정보를 신규로 등록한다.
	 * @param mtgPlaceManage - 회의실관리 model
	 */
	public void insertMtgPlaceManage(MtgPlaceManage mtgPlaceManage, MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 기 등록된 회의실관리 정보를 수정한다.
	 * @param mtgPlaceManage - 회의실관리 model
	 */
	public void updtMtgPlaceManage(MtgPlaceManage mtgPlaceManage, MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 기 등록된 회의실관리 정보를 삭제한다.
	 * @param mtgPlaceManage - 회의실관리 model
	 */
	public void deleteMtgPlaceManage(MtgPlaceManage mtgPlaceManage) throws Exception;

	/**
	 * 회의실관리가 특정화면에 반영된 결과를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	//public List<MtgPlaceManageVO> selectMtgPlaceManageResult(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/******** 회의실 예약 관리 *************/
	/**
	 * 회의실 예약정보를 관리하기 위해 등록된 회의실 예약 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	public List<MtgPlaceManageVO> selectMtgPlaceResveManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 회의실예약 신청화면을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	public MtgPlaceManageVO selectMtgPlaceResve(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 등록된 회의실 예약 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	public MtgPlaceManageVO selectMtgPlaceResveDetail(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 회의실 예약정보를 신규로 등록한다.
	 * @param mtgPlaceManage - 회의실예약 model
	 */
	public void insertMtgPlaceResve(MtgPlaceResve mtgPlaceResve) throws Exception;

	/**
	 * 기 등록된 회의실 예약정보를 수정한다.
	 * @param mtgPlaceManage - 회의실예약 model
	 */
	public void updtMtgPlaceResve(MtgPlaceResve mtgPlaceResve) throws Exception;

	/**
	 * 기 등록된 회의실 예약정보를 삭제한다.
	 * @param mtgPlaceManage - 회의실예약 model
	 */
	public void deleteMtgPlaceResve(MtgPlaceResve mtgPlaceResve) throws Exception;
	
	/**
	 * 회의실 중복여부 체크.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 중복건수
	 */
	public int mtgPlaceResveDplactCeck(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;
}