package egovframework.com.uss.ion.ctn.service;

import java.util.List;

/**
 * 개요
 * - 경조관리에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 경조관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 경조관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public interface EgovCtsnnManageService {

	/**
	 * 경조관리 정보를 관리하기 위해 등록된 로그인화면이미지 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return List - 경조관리 목록
	 */
	public List<CtsnnManageVO> selectCtsnnManageList(CtsnnManageVO ctsnnManageVO) throws Exception;

	/**
	 * 경조관리 목록 총 갯수를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return int - 경조관리 카운트 수
	 */
	public int selectCtsnnManageListTotCnt(CtsnnManageVO ctsnnManageVO) throws Exception ;
	
	/**
	 * 등록된 경조관리의 상세정보를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return CtsnnManageVO - 경조관리 VO
	 */
	public CtsnnManageVO selectCtsnnManage(CtsnnManageVO ctsnnManageVO) throws Exception;

	/**
	 * 경조관리 정보를 신규로 등록한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void insertCtsnnManage(CtsnnManage ctsnnManage) throws Exception;

	/**
	 * 기 등록된 경조관리 정보를 수정한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void updtCtsnnManage(CtsnnManage ctsnnManage) throws Exception;

	/**
	 * 기 등록된 경조관리 정보를 삭제한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void deleteCtsnnManage(CtsnnManage ctsnnManage) throws Exception;

    /*** 승인처리관련 ***/
	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return List - 경조관리 목록
	 */
	public List<CtsnnManageVO> selectCtsnnManageConfmList(CtsnnManageVO ctsnnManageVO) throws Exception;

	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록 총 갯수를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return int - 경조관리 카운트 수
	 */
	public int selectCtsnnManageConfmListTotCnt(CtsnnManageVO ctsnnManageVO) throws Exception ;
	
	/**
	 * 경조정보를 승인처리 한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void updtCtsnnManageConfm(CtsnnManage ctsnnManage) throws Exception;
}