package egovframework.com.cop.com.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.com.service.UserInfVO;

/**
 * 협업 활용 사용자 정보 조회를 위한 Mapper 인터페이스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자       수정내용
 *  -----------    --------    ---------------------------
 *   2009.04.06     이삼섭       최초 생성
 *   2026.05.28     dasomel      @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("EgovUserInfManageDAO")
public interface EgovUserInfManageMapper {

    /**
     * 사용자 정보에 대한 목록을 조회한다.
     *
     * @param userVO
     * @return
     */
    List<UserInfVO> selectUserList(UserInfVO userVO);

    /**
     * 사용자 정보에 대한 목록 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     */
    int selectUserListCnt(UserInfVO userVO);

    /**
     * 커뮤니티 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     */
    List<UserInfVO> selectCmmntyUserList(UserInfVO userVO);

    /**
     * 커뮤니티 사용자 목록에 대한 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     */
    int selectCmmntyUserListCnt(UserInfVO userVO);

    /**
     * 커뮤니티 관리자 목록을 조회한다.
     *
     * @param userVO
     * @return
     */
    List<UserInfVO> selectCmmntyMngrList(UserInfVO userVO);

    /**
     * 커뮤니티 관리자 목록에 대한 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     */
    int selectCmmntyMngrListCnt(UserInfVO userVO);

    /**
     * 동호회 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     */
    List<UserInfVO> selectClubUserList(UserInfVO userVO);

    /**
     * 동호회 사용자 목록에 대한 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     */
    int selectClubUserListCnt(UserInfVO userVO);

    /**
     * 동호회 운영자 목록을 조회한다.
     *
     * @param userVO
     * @return
     */
    List<UserInfVO> selectClubOprtrList(UserInfVO userVO);

    /**
     * 동호회 운영자 목록에 대한 전체 건수를 조회한다.
     *
     * @param userVO
     * @return
     */
    int selectClubOprtrListCnt(UserInfVO userVO);

    /**
     * 동호회에 대한 모든 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     */
    List<UserInfVO> selectAllClubUser(UserInfVO userVO);

    /**
     * 커뮤니티에 대한 모든 사용자 목록을 조회한다.
     *
     * @param userVO
     * @return
     */
    List<UserInfVO> selectAllCmmntyUser(UserInfVO userVO);
}
