package egovframework.com.cop.smt.dsm.service;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 일지관리를 처리하는 Service Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *   2024.08.30  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *      </pre>
 */
public interface EgovDiaryManageService {

	/**
	 * 일지관리 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 */
	public List<EgovMap> selectDiaryManageList(ComDefaultVO searchVO);

	/**
	 * 일지관리를(을) 상세조회 한다.
	 * 
	 * @param diaryManageVO - 일지관리 정보 담김 VO
	 * @return DiaryManageVO
	 */
	public DiaryManageVO selectDiaryManageDetail(DiaryManageVO diaryManageVO);

	/**
	 * 일지관리를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 */
	public int selectDiaryManageListCnt(ComDefaultVO searchVO);

	/**
	 * 일지관리를(을) 등록한다.
	 * 
	 * @param diaryManageVO - 일지관리 정보 담김 VO
	 */
	void insertDiaryManage(DiaryManageVO diaryManageVO) throws Exception;

	/**
	 * 일지관리를(을) 수정한다.
	 * 
	 * @param diaryManageVO - 일지관리 정보 담김 VO
	 */
	void updateDiaryManage(DiaryManageVO diaryManageVO);

	/**
	 * 일지관리를(을) 삭제한다.
	 * 
	 * @param diaryManageVO - 일지관리 정보 담김 VO
	 */
	void deleteDiaryManage(DiaryManageVO diaryManageVO);

}
