package egovframework.com.cop.smt.mrm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.smt.mrm.service.MemoReprt;
import egovframework.com.cop.smt.mrm.service.MemoReprtVO;
import egovframework.com.cop.smt.mrm.service.ReportrVO;

/**
 * 메모보고에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("memoReprtMapper")
public interface MemoReprtMapper {

	List<ReportrVO> selectReportrList(ReportrVO reportrVO);

	int selectReportrListCnt(ReportrVO reportrVO);

	String selectWrterClsfNm(String wrterId);

	List<MemoReprtVO> selectMemoReprtList(MemoReprtVO memoReprtVO);

	int selectMemoReprtListCnt(MemoReprtVO memoReprtVO);

	MemoReprtVO selectMemoReprt(MemoReprtVO memoReprtVO);

	void updateMemoReprt(MemoReprt memoReprt);

	void readMemoReprt(MemoReprt memoReprt);

	void updateMemoReprtDrctMatter(MemoReprt memoReprt);

	void deleteMemoReprt(MemoReprtVO memoReprtVO);

	void insertMemoReprt(MemoReprt memoReprt);

}
