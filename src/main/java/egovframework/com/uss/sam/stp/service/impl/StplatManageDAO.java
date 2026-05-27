package egovframework.com.uss.sam.stp.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.sam.stp.service.StplatManageDefaultVO;
import egovframework.com.uss.sam.stp.service.StplatManageVO;


/**
 *
 * 약관내용을 처리하는 DAO 인터페이스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *   2016.06.13  장동한          표준프레임워크 v3.6 개선
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("StplatManageDAO")
public interface StplatManageDAO {

    /**
	 * 약관정보 글 목록에 대한 상세내용을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 */
    StplatManageVO selectStplatDetail(StplatManageVO vo);

    /**
	 * 약관정보 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 */
    List<StplatManageVO> selectStplatList(StplatManageDefaultVO searchVO);

    /**
	 * 약관정보 글 총 개수를 조회한다.
	 * @param searchVO
	 * @return 글 총 개수
	 */
    int selectStplatListTotCnt(StplatManageDefaultVO searchVO);

	/**
	 * 약관정보 글을 등록한다.
	 * @param vo
	 */
    void insertStplatCn(StplatManageVO vo);

	/**
	 * 약관정보 글을 수정한다.
	 * @param vo
	 */
    void updateStplatCn(StplatManageVO vo);

	/**
	 * 약관정보 글을 삭제한다.
	 * @param vo
	 */
    void deleteStplatCn(StplatManageVO vo);

}
