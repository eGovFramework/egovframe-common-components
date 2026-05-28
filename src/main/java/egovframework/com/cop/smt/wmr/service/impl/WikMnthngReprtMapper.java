package egovframework.com.cop.smt.wmr.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.smt.wmr.service.ReportrVO;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprt;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprtVO;

/**
 * 주간월간보고를 위한 Mapper 인터페이스
 * @author 장철호
 * @since 2010.07.19
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.19  장철호          최초 생성
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 *
 * </pre>
 */
@EgovMapper("wikMnthngReprtMapper")
public interface WikMnthngReprtMapper {

	List<ReportrVO> selectReportrList(ReportrVO reportrVO);

	int selectReportrListCnt(ReportrVO reportrVO);

	String selectWrterClsfNm(String wrterId);

	List<WikMnthngReprtVO> selectWikMnthngReprtList(WikMnthngReprtVO wikMnthngReprtVO);

	WikMnthngReprtVO selectWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO);

	int updateWikMnthngReprt(WikMnthngReprt wikMnthngReprt);

	int insertWikMnthngReprt(WikMnthngReprt wikMnthngReprt);

	int deleteWikMnthngReprt(WikMnthngReprt wikMnthngReprt);

	int confirmWikMnthngReprt(WikMnthngReprt wikMnthngReprt);

	int selectWikMnthngReprtListCnt(WikMnthngReprtVO wikMnthngReprtVO);

}
