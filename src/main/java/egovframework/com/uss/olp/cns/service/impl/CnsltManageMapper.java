package egovframework.com.uss.olp.cns.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.uss.olp.cns.service.CnsltManageDefaultVO;
import egovframework.com.uss.olp.cns.service.CnsltManageVO;

/**
 * 상담내용을 처리하는 Mapper 인터페이스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 */
@EgovMapper("cnsltManageMapper")
public interface CnsltManageMapper {

	/**
	 * 상담내용 글 목록에 대한 상세내용을 조회한다.
	 */
	CnsltManageVO selectCnsltListDetail(CnsltManageVO vo);

	/**
	 * 상담내용 글을 수정한다.(조회수를 수정)
	 */
	void updateCnsltInqireCo(CnsltManageVO vo);

	/**
	 * 상담내용 글 목록을 조회한다.
	 */
	List<EgovMap> selectCnsltList(CnsltManageDefaultVO searchVO);

	/**
	 * 상담내용 글 총 개수를 조회한다.
	 */
	int selectCnsltListTotCnt(CnsltManageDefaultVO searchVO);

	/**
	 * 상담내용 글을 등록한다.
	 */
	void insertCnsltDtls(CnsltManageVO vo);

	/**
	 * 작성비밀번호를 확인한다.
	 */
	int selectCnsltPasswordConfirmCnt(CnsltManageVO vo);

	/**
	 * 상담내용 글을 수정한다.
	 */
	void updateCnsltDtls(CnsltManageVO vo);

	/**
	 * 상담내용 글을 삭제한다.
	 */
	void deleteCnsltDtls(CnsltManageVO vo);

	/**
	 * 상담답변 글 목록에 대한 상세내용을 조회한다.
	 */
	CnsltManageVO selectCnsltAnswerDetail(CnsltManageVO vo);

	/**
	 * 상담답변 글 목록을 조회한다.
	 */
	List<EgovMap> selectCnsltAnswerList(CnsltManageDefaultVO searchVO);

	/**
	 * 상담답변 글 총 개수를 조회한다.
	 */
	int selectCnsltAnswerListTotCnt(CnsltManageDefaultVO searchVO);

	/**
	 * 상담답변 글을 수정한다.
	 */
	void updateCnsltDtlsAnswer(CnsltManageVO vo);

}
