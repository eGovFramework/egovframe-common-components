package egovframework.com.cop.bbs.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;

/**
 * 게시판 추가기능(댓글관리, 만족도조사) 관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("bbsAddedOptionsMapper")
public interface BBSAddedOptionsMapper {

	int insertAddedOptionsInf(BoardMaster boardMaster) throws Exception;

	BoardMasterVO selectAddedOptionsInf(BoardMaster vo) throws Exception;

	void updateAddedOptionsInf(BoardMaster boardMaster) throws Exception;

}
