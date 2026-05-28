package egovframework.com.cop.smt.dsm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cop.smt.dsm.service.DiaryManageVO;
import jakarta.annotation.Resource;

/**
 * 일지관리를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 *
 * </pre>
 */
@Repository("diaryManageDao")
public class DiaryManageDao {

	@Resource(name = "diaryManageMapper")
	private DiaryManageMapper mapper;

	/**
	 * 일지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectDiaryManageList(ComDefaultVO searchVO) throws Exception {
		return mapper.selectDiaryManage(searchVO);
	}

	/**
	 * 일지관리를(을) 상세조회 한다.
	 * @param diaryManageVO - 일지관리 정보 담김 VO
	 * @return DiaryManageVO
	 * @throws Exception
	 */
	public DiaryManageVO selectDiaryManageDetail(DiaryManageVO diaryManageVO) throws Exception {
		return mapper.selectDiaryManageDetail(diaryManageVO);
	}

	/**
	 * 일지관리를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectDiaryManageListCnt(ComDefaultVO searchVO) throws Exception {
		return mapper.selectDiaryManageCnt(searchVO);
	}

	/**
	 * 일지관리를(을) 등록한다.
	 * @param diaryManageVO - 일지관리 정보 담김 VO
	 * @throws Exception
	 */
	public void insertDiaryManage(DiaryManageVO diaryManageVO) throws Exception {
		mapper.insertDiaryManage(diaryManageVO);
	}

	/**
	 * 일지관리를(을) 수정한다.
	 * @param diaryManageVO - 일지관리 정보 담김 VO
	 * @throws Exception
	 */
	public void updateDiaryManage(DiaryManageVO diaryManageVO) throws Exception {
		mapper.updateDiaryManage(diaryManageVO);
	}

	/**
	 * 일지관리를(을) 삭제한다.
	 * @param diaryManageVO - 일지관리 정보 담김 VO
	 * @throws Exception
	 */
	public void deleteDiaryManage(DiaryManageVO diaryManageVO) throws Exception {
		mapper.deleteDiaryManage(diaryManageVO);
	}

}
