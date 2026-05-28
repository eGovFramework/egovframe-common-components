package egovframework.com.uss.ion.bnt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.bnt.service.BndtCeckManageVO;
import egovframework.com.uss.ion.bnt.service.BndtDiaryVO;
import egovframework.com.uss.ion.bnt.service.BndtManageVO;

/**
 * 개요
 * - 당직관리에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 당직관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 당직관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.15  이용           최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("bndtManageDAO")
public interface BndtManageDAO {

	/**
	 * 당직관리정보를 관리하기 위해 등록된 당직관리 목록을 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return List - 당직관리 목록
	 */
	List<BndtManageVO> selectBndtManageList(BndtManageVO bndtManageVO);

	/**
	 * 당직관리목록 총 개수를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 */
	int selectBndtManageListTotCnt(BndtManageVO bndtManageVO);

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return BndtManageVO - 당직관리 VO
	 */
	BndtManageVO selectBndtManage(BndtManageVO bndtManageVO);

	/**
	 * 당직관리정보를 신규로 등록한다.
	 * @param bndtManageVO - 당직관리 VO
	 */
	void insertBndtManage(BndtManageVO bndtManageVO);

	/**
	 * 기 등록된 당직관리정보를 수정한다.
	 * @param bndtManageVO - 당직관리 VO
	 */
	void updtBndtManage(BndtManageVO bndtManageVO);

	/**
	 * 기 등록된 당직관리정보를 삭제한다.
	 * @param bndtManageVO - 당직관리 VO
	 */
	void deleteBndtManage(BndtManageVO bndtManageVO);

	/**
	 * 당직일지 개수를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 */
	int selectBndtDiaryTotCnt(BndtManageVO bndtManageVO);

	/**
	 * 당직체크관리정보를 관리하기 위해 등록된 당직체크관리 목록을 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return List - 당직체크관리 목록
	 */
	List<BndtCeckManageVO> selectBndtCeckManageList(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 당직체크관리목록 총 개수를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int
	 */
	int selectBndtCeckManageListTotCnt(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 등록된 당직체크관리의 상세정보를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return BndtCeckManageVO - 당직체크관리 VO
	 */
	BndtCeckManageVO selectBndtCeckManage(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 당직체크관리정보를 신규로 등록한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 */
	void insertBndtCeckManage(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 기 등록된 당직체크관리정보를 수정한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 */
	void updtBndtCeckManage(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 기 등록된 당직체크관리정보를 삭제한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 */
	void deleteBndtCeckManage(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 당직체크 중복여부 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int
	 */
	int selectBndtCeckManageDplctAt(BndtCeckManageVO bndtCeckManageVO);

	/**
	 * 등록된 당직일지관리의 상세정보를 조회한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 * @return List - 당직일지관리 VO
	 */
	List<BndtDiaryVO> selectBndtDiary(BndtDiaryVO bndtDiaryVO);

	/**
	 * 당직일지관리정보를 신규로 등록한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 */
	void insertBndtDiary(BndtDiaryVO bndtDiaryVO);

	/**
	 * 기 등록된 당직일지관리정보를 수정한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 */
	void updtBndtDiary(BndtDiaryVO bndtDiaryVO);

	/**
	 * 기 등록된 당직일지관리정보를 삭제한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 */
	void deleteBndtDiary(BndtDiaryVO bndtDiaryVO);

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return BndtManageVO - 당직관리 VO
	 */
	BndtManageVO selectBndtManageBnde(BndtManageVO bndtManageVO);

	/**
	 * 당직관리 등록건수 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 */
	int selectBndtManageMonthCnt(BndtManageVO bndtManageVO);

}
