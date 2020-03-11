package egovframework.com.uss.ion.ans.service;

import java.io.InputStream;
import java.util.List;

/**
 * 개요
 * - 기념일관리에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 기념일관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 기념일관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public interface EgovAnnvrsryManageService {

	/**
	 * 기념일관리 정보를 관리하기 위해 등록된 기념일관리 목록을 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return List - 기념일관리 목록
	 */
	public List<AnnvrsryManageVO> selectAnnvrsryManageList(AnnvrsryManageVO annvrsryManageVO) throws Exception;

	/**
	 * 기념일관리 목록 총 갯수를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return int - 기념일관리 카운트 수
	 */
	public int selectAnnvrsryManageListTotCnt(AnnvrsryManageVO annvrsryManageVO) throws Exception ;
	
	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return AnnvrsryManageVO - 기념일관리 VO
	 */
	public AnnvrsryManageVO selectAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO) throws Exception;

	/**
	 * 기념일관리 정보를 신규로 등록한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	public void insertAnnvrsryManage(AnnvrsryManage annvrsryManage) throws Exception;

	/**
	 * 기 등록된 기념일관리 정보를 수정한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	public void updateAnnvrsryManage(AnnvrsryManage annvrsryManage) throws Exception;

	/**
	 * 기 등록된 기념일관리 정보를 삭제한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	public void deleteAnnvrsryManage(AnnvrsryManage annvrsryManage) throws Exception;

	/**
	 * 등록된 기념일관리의 알림 화면을 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO 
	 * @return AnnvrsryManageVO - 기념일관리 VO
	 */
	public List<AnnvrsryManageVO> selectAnnvrsryGdcc(AnnvrsryManageVO annvrsryManageVO) throws Exception;

    /**
	 * 기념일관리 등록시 중복여부를 조회한다.
	 * @param annvrsryManage - 기념일관리 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectAnnvrsryManageDplctAt(AnnvrsryManage annvrsryManage) throws Exception ;

	/**
	 * 기념일정보 excel생성
	 * @param  inputStream InputStream
	 * @return  String
	 * @exception Exception
	 */
	public List<AnnvrsryManageVO> selectAnnvrsryManageBnde(InputStream inputStream)throws Exception;
	
	/**
	 * 기념일정보를 일괄등록처리한다.
	 * @param annvrsryManageVO     - 기념일관리 VO
	 * @param String           - 기념일정보
    */
	public void insertAnnvrsryManageBnde(AnnvrsryManageVO annvrsryManageVO, 
			                             String checkedAnnvrsryManageForInsert) throws Exception;	
}