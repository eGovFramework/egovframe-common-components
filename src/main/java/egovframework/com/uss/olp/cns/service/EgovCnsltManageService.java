package egovframework.com.uss.olp.cns.service;

import java.util.List;


/**
 *
 * 상담내용을 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
public interface EgovCnsltManageService {

    /**
	 * 상담내용 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
	CnsltManageVO selectCnsltListDetail(CnsltManageVO vo) throws Exception;

	/**
	 * 상담내용 글을 수정한다.(조회수를 수정)
	 * @param vo
	 * @exception Exception
	 */
    void updateCnsltInqireCo(CnsltManageVO vo) throws Exception;

    /**
	 * 상담내용 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    List<?> selectCnsltList(CnsltManageDefaultVO searchVO) throws Exception;

    /**
	 * 상담내용 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    int selectCnsltListTotCnt(CnsltManageDefaultVO searchVO);

	/**
	 * 상담내용 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 CnsltManageVO
	 * @exception Exception
	 */
    void insertCnsltDtls(CnsltManageVO vo) throws Exception;

    /**
	 * 작성비밀번호를 확인한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 * @exception
	 */
    int selectCnsltPasswordConfirmCnt(CnsltManageVO vo);

	/**
	 * 상담내용 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    void updateCnsltDtls(CnsltManageVO vo) throws Exception;

	/**
	 * 상담내용 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    void deleteCnsltDtls(CnsltManageVO vo) throws Exception;


    /**
	 * 상담답변 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
	CnsltManageVO selectCnsltAnswerListDetail(CnsltManageVO vo) throws Exception;


    /**
	 * 상담답변 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    List<?> selectCnsltAnswerList(CnsltManageDefaultVO searchVO) throws Exception;

    /**
	 * 상담답변 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 * @exception
	 */
    int selectCnsltAnswerListTotCnt(CnsltManageDefaultVO searchVO);

	/**
	 * 상담답변 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    void updateCnsltDtlsAnswer(CnsltManageVO vo) throws Exception;



}

