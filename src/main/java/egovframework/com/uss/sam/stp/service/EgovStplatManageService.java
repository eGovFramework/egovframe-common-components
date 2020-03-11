package egovframework.com.uss.sam.stp.service;

import java.util.List;

/**
 *
 * 약관내용을 처리하는 서비스 클래스
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
 *   2016.06.13  장동한          표준프레임워크 v3.6 개선
 *
 * </pre>
 */
public interface EgovStplatManageService {

    /**
	 * 약관정보 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
	StplatManageVO selectStplatDetail(StplatManageVO vo) throws Exception;

    /**
	 * 약관정보 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    List<?> selectStplatList(StplatManageDefaultVO searchVO) throws Exception;

    /**
	 * 약관정보 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    int selectStplatListTotCnt(StplatManageDefaultVO searchVO);

	/**
	 * 약관정보 글을 등록한다.
	 * @param vo
	 * @exception Exception
	 */
    void insertStplatCn(StplatManageVO vo) throws Exception;


	/**
	 * 약관정보 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    void updateStplatCn(StplatManageVO vo) throws Exception;

	/**
	 * 약관정보 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    void deleteStplatCn(StplatManageVO vo) throws Exception;


}
