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
	 */
	public List<MtgPlaceManageVO> selectMtgPlaceManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 회의실관리 목록 총 개수를 조회한다.
	 */
	public int selectMtgPlaceManageListTotCnt(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 */
	public MtgPlaceManageVO selectMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 회의실관리 정보를 신규로 등록한다.
	 */
	public void insertMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 기 등록된 회의실관리 정보를 수정한다.
	 */
	public void updtMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 기 등록된 회의실관리 정보를 삭제한다.
	 */
	public void deleteMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 회의실 예약정보를 관리하기 위해 등록된 회의실 예약 목록을 조회한다.
	 */
	public List<MtgPlaceManageVO> selectMtgPlaceResveManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 회의실예약 신청화면을 조회한다.
	 */
	public MtgPlaceManageVO selectMtgPlaceResve(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 등록된 회의실 예약 상세정보를 조회한다.
	 */
	public MtgPlaceManageVO selectMtgPlaceResveDetail(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;

	/**
	 * 회의실 예약정보를 신규로 등록한다.
	 */
	public void insertMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) throws Exception;

	/**
	 * 기 등록된 회의실 예약정보를 수정한다.
	 */
	public void updtMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) throws Exception;

	/**
	 * 기 등록된 회의실 예약정보를 삭제한다.
	 */
	public void deleteMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) throws Exception;

	/**
	 * 회의실 중복여부 체크.
	 */
	public int mtgPlaceResveDplactCeck(MtgPlaceManageVO mtgPlaceManageVO) throws Exception;
}
