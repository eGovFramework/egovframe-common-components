package egovframework.com.cop.smt.dsm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cop.smt.dsm.service.DiaryManageVO;

/**
 * 일지관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("diaryManageMapper")
public interface DiaryManageMapper {

	List<EgovMap> selectDiaryManage(ComDefaultVO searchVO);

	DiaryManageVO selectDiaryManageDetail(DiaryManageVO diaryManageVO);

	int selectDiaryManageCnt(ComDefaultVO searchVO);

	void insertDiaryManage(DiaryManageVO diaryManageVO);

	void updateDiaryManage(DiaryManageVO diaryManageVO);

	void deleteDiaryManage(DiaryManageVO diaryManageVO);

}
