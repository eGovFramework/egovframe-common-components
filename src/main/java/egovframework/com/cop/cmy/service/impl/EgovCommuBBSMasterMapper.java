package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.bbs.service.BoardMasterVO;

/**
 * 개요
 * - 커뮤니티 게시판 마스터에 대한 Mapper 인터페이스를 정의한다.
 *
 * 상세내용
 * - 커뮤니티에 속한 게시판 목록 조회 기능을 제공한다.
 */
@EgovMapper
public interface EgovCommuBBSMasterMapper {

	/**
	 * 커뮤니티 게시판 목록을 조회한다.
	 * @param boardMasterVO - 게시판 마스터 VO
	 * @return List - 게시판 목록
	 */
	List<BoardMasterVO> selectCommuBBSMasterListMain(BoardMasterVO boardMasterVO);

}
