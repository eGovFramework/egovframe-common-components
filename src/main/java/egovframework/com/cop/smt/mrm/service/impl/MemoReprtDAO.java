package egovframework.com.cop.smt.mrm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.smt.mrm.service.MemoReprt;
import egovframework.com.cop.smt.mrm.service.MemoReprtVO;
import egovframework.com.cop.smt.mrm.service.ReportrVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 메모보고에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 메모보고에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 메모보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:14:53
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("MemoReprtDAO")
public class MemoReprtDAO {

	@Resource(name = "memoReprtMapper")
	private MemoReprtMapper mapper;

	public List<ReportrVO> selectReportrList(ReportrVO reportrVO) throws Exception {
		return mapper.selectReportrList(reportrVO);
	}

	public int selectReportrListCnt(ReportrVO reportrVO) throws Exception {
		return mapper.selectReportrListCnt(reportrVO);
	}

	public String selectWrterClsfNm(String wrterId) throws Exception {
		return mapper.selectWrterClsfNm(wrterId);
	}

	public List<MemoReprtVO> selectMemoReprtList(MemoReprtVO memoReprtVO) throws Exception {
		memoReprtVO.setSearchBgnDe(memoReprtVO.getSearchBgnDe().replaceAll("-", ""));
		memoReprtVO.setSearchEndDe(memoReprtVO.getSearchEndDe().replaceAll("-", ""));

		List<MemoReprtVO> resultList = mapper.selectMemoReprtList(memoReprtVO);
		for (int i = 0; i < resultList.size(); i++) {
			MemoReprtVO resultVO = resultList.get(i);
			resultVO.setReprtDe(EgovDateUtil.convertDate(resultVO.getReprtDe(), "0000", "yyyy-MM-dd"));
			resultList.set(i, resultVO);
		}
		return resultList;
	}

	public MemoReprtVO selectMemoReprt(MemoReprtVO memoReprtVO) throws Exception {
		MemoReprtVO resultVO = mapper.selectMemoReprt(memoReprtVO);
		resultVO.setReprtDe(EgovDateUtil.convertDate(resultVO.getReprtDe(), "0000", "yyyy-MM-dd"));
		return resultVO;
	}

	public void readMemoReprt(MemoReprt memoReprt) throws Exception {
		mapper.readMemoReprt(memoReprt);
	}

	public void updateMemoReprt(MemoReprt memoReprt) throws Exception {
		memoReprt.setReprtDe(EgovStringUtil.isNullToString(memoReprt.getReprtDe()).replaceAll("-", ""));//KISA 보안약점 조치 (2018-10-29, 윤창원)
		mapper.updateMemoReprt(memoReprt);
	}

	public void updateMemoReprtDrctMatter(MemoReprt memoReprt) throws Exception {
		mapper.updateMemoReprtDrctMatter(memoReprt);
	}

	public void insertMemoReprt(MemoReprt memoReprt) throws Exception {
		memoReprt.setReprtDe(EgovStringUtil.isNullToString(memoReprt.getReprtDe()).replaceAll("-", ""));//KISA 보안약점 조치 (2018-10-29, 윤창원)
		mapper.insertMemoReprt(memoReprt);
	}

	public void deleteMemoReprt(MemoReprtVO memoReprtVO) throws Exception {
		mapper.deleteMemoReprt(memoReprtVO);
	}

	public int selectMemoReprtListCnt(MemoReprtVO memoReprtVO) throws Exception {
		memoReprtVO.setSearchBgnDe(memoReprtVO.getSearchBgnDe().replaceAll("-", ""));
		memoReprtVO.setSearchEndDe(memoReprtVO.getSearchEndDe().replaceAll("-", ""));
		return mapper.selectMemoReprtListCnt(memoReprtVO);
	}

}
