package egovframework.com.uss.ion.bnt.service;

import java.io.InputStream;
import java.util.List;

/**
 * 개요
 * - 당직관리에 대한 Service Interface를 정의한다.
 *
 * 상세내용
 * - 당직관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 당직관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

public interface EgovBndtManageService {

	/**
	 * 당직관리 정보를 관리하기 위해 등록된 당직관리 목록을 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return List - 당직관리 목록
	 */
	public List<BndtManageVO> selectBndtManageList(BndtManageVO bndtManageVO) throws Exception;

	/**
	 * 당직관리 목록 총 갯수를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int - 당직관리 카운트 수
	 */
	public int selectBndtManageListTotCnt(BndtManageVO bndtManageVO) throws Exception ;

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return BndtManageVO - 당직관리 VO
	 */
	public BndtManageVO selectBndtManage(BndtManageVO bndtManageVO) throws Exception;

	/**
	 * 당직관리 정보를 신규로 등록한다.
	 * @param bndtManage - 당직관리 model
	 */
	public void insertBndtManage(BndtManage bndtManage) throws Exception;

	/**
	 * 기 등록된 당직관리 정보를 수정한다.
	 * @param bndtManage - 당직관리 model
	 */
	public void updtBndtManage(BndtManage bndtManage) throws Exception;

	/**
	 * 기 등록된 당직관리 정보를 삭제한다.
	 * @param bndtManage - 당직관리 model
	 */
	public void deleteBndtManage(BndtManage bndtManage) throws Exception;

    /**
	 * 당직일지 갯수를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectBndtDiaryTotCnt(BndtManage bndtManage) throws Exception;

    /***** 당직 체크관리 *****/
	/**
	 * 당직체크관리 정보를 관리하기 위해 등록된 당직체크관리 목록을 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return List - 당직체크관리 목록
	 */
	public List<BndtCeckManageVO> selectBndtCeckManageList(BndtCeckManageVO bndtCeckManageVO) throws Exception;

	/**
	 * 당직체크관리 목록 총 갯수를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int - 당직체크관리 카운트 수
	 */
	public int selectBndtCeckManageListTotCnt(BndtCeckManageVO bndtCeckManageVO) throws Exception ;

	/**
	 * 등록된 당직체크관리의 상세정보를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return BndtCeckManageVO - 당직체크관리 VO
	 */
	public BndtCeckManageVO selectBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) throws Exception;

	/**
	 * 당직체크관리 정보를 신규로 등록한다.
	 * @param bndtCeckManage - 당직체크관리 model
	 */
	public void insertBndtCeckManage(BndtCeckManage bndtCeckManage) throws Exception;

	/**
	 * 기 등록된 당직체크관리 정보를 수정한다.
	 * @param bndtCeckManage - 당직체크관리 model
	 */
	public void updtBndtCeckManage(BndtCeckManage bndtCeckManage) throws Exception;

	/**
	 * 기 등록된 당직체크관리 정보를 삭제한다.
	 * @param bndtCeckManage - 당직체크관리 model
	 */
	public void deleteBndtCeckManage(BndtCeckManage bndtCeckManage) throws Exception;


    /**
	 * 당직체크 중복여부 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectBndtCeckManageDplctAt(BndtCeckManage bndtCeckManage) throws Exception ;


    /***** 당직 일지 *****/

	/**
	 * 등록된 당직일지관리의 상세정보를 조회한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 * @return BndtDiaryVO - 당직일지관리 VO
	 */
	public List<?> selectBndtDiary(BndtDiaryVO bndtDiaryVO) throws Exception;

	/**
	 * 당직일지관리 정보를 신규로 등록한다.
	 * @param bndtDiary - 당직일지관리 model
	 */
	public void insertBndtDiary(BndtDiary bndtDiary, String diaryForInsert) throws Exception;

	/**
	 * 기 등록된 당직일지관리 정보를 수정한다.
	 * @param bndtDiary - 당직일지관리 model
	 */
	public void updtBndtDiary(BndtDiary bndtDiary, String diaryForUpdt) throws Exception;

	/**
	 * 기 등록된 당직일지관리 정보를 삭제한다.
	 * @param bndtDiary - 당직일지관리 model
	 */
	public void deleteBndtDiary(BndtDiary bndtDiary) throws Exception;

	/**
	 * 당직자 excel생성
	 * @param  inputStream InputStream
	 * @return  String
	 * @exception Exception
	 */
	public List<BndtManageVO> selectBndtManageBnde(InputStream inputStream) throws Exception;

	/**
	 * 당직자 excel생성 (Xlsx사용시)
	 * @param  inputStream InputStream
	 * @return  String
	 * @exception Exception
	 */
	public List<BndtManageVO> selectBndtManageBndeX(InputStream inputStream) throws Exception;
	
	/**
	 * 당직정보를 일괄등록처리한다.
	 * @param bndtManageVO     - 당직관리 VO
	 * @param String           - 당직자정보
	 */
	public void insertBndtManageBnde(BndtManageVO bndtManageVO, String checkedBndtManageForInsert) throws Exception;

    /**
	 * 당직관리 등록건수 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectBndtManageMonthCnt(BndtManageVO bndtManageVO) throws Exception;
}