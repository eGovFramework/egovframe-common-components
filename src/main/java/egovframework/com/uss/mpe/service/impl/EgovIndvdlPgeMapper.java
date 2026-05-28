package egovframework.com.uss.mpe.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.mpe.service.IndvdlPgeVO;

/**
 * 개인페이지 관리에 대한 Mapper 인터페이스
 * @version 1.0
 */
@EgovMapper("egovIndvdlPgeMapper")
public interface EgovIndvdlPgeMapper {

	/**
	 * 개인페이지 목록을 조회한다.
	 */
	List<IndvdlPgeVO> selectIndvdlPgeList(IndvdlPgeVO searchVO);

	/**
	 * 개인페이지 목록 총 개수를 조회한다.
	 */
	int selectIndvdlPgeListCnt(IndvdlPgeVO searchVO);

	/**
	 * 개인페이지 상세 정보를 조회한다.
	 */
	IndvdlPgeVO selectIndvdlPgeDetail(IndvdlPgeVO indvdlPgeVO);

	/**
	 * 개인페이지를 등록한다.
	 */
	void insertIndvdlPge(IndvdlPgeVO indvdlPgeVO);

	/**
	 * 개인페이지를 수정한다.
	 */
	void updateIndvdlPge(IndvdlPgeVO indvdlPgeVO);

}
