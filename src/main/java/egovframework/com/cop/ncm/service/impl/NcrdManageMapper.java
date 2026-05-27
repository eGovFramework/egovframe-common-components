package egovframework.com.cop.ncm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.ncm.service.NameCard;
import egovframework.com.cop.ncm.service.NameCardUser;
import egovframework.com.cop.ncm.service.NameCardVO;

/**
 * 명함정보를 관리하기 위한 Mapper 인터페이스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일         수정자       수정내용
 *  ----------    --------    ---------------------------
 *   2009.3.28     이삼섭       최초 생성
 *
 * </pre>
 */
@EgovMapper
public interface NcrdManageMapper {

    /**
     * 명함사용자 정보를 삭제한다.
     * @param nameCardVO - 명함 VO
     * @return 삭제 건수
     */
    int deleteNcrdItemUser(NameCardVO nameCardVO);

    /**
     * 명함 정보를 삭제한다.
     * @param nameCardVO - 명함 VO
     * @return 삭제 건수
     */
    int deleteNcrdItem(NameCardVO nameCardVO);

    /**
     * 명함 정보를 등록한다.
     * @param nameCard - 명함 정보
     * @return 등록 건수
     */
    int insertNcrdItem(NameCard nameCard);

    /**
     * 명함사용자 정보를 등록한다.
     * @param ncrdUser - 명함사용자 정보
     * @return 등록 건수
     */
    int insertNcrdUseInf(NameCardUser ncrdUser);

    /**
     * 명함 정보에 대한 상세정보를 조회한다.
     * @param nameCard - 명함 정보
     * @return 명함 상세정보 VO
     */
    NameCardVO selectNcrdItem(NameCard nameCard);

    /**
     * 명함 정보에 대한 목록을 조회한다.
     * @param nameCardVO - 명함 VO
     * @return 명함 목록
     */
    List<NameCardVO> selectNcrdItemList(NameCardVO nameCardVO);

    /**
     * 명함 목록 전체 건수를 조회한다.
     * @param nameCardVO - 명함 VO
     * @return 전체 건수
     */
    int selectNcrdItemListCnt(NameCardVO nameCardVO);

    /**
     * 명함사용자 목록을 조회한다.
     * @param nameCardUser - 명함사용자 정보
     * @return 명함사용자 목록
     */
    List<NameCardUser> selectNcrdUseInfs(NameCardUser nameCardUser);

    /**
     * 명함사용자 목록 전체 건수를 조회한다.
     * @param nameCardUser - 명함사용자 정보
     * @return 전체 건수
     */
    int selectNcrdUseInfsCnt(NameCardUser nameCardUser);

    /**
     * 명함 정보를 수정한다.
     * @param nameCard - 명함 정보
     * @return 수정 건수
     */
    int updateNcrdItem(NameCard nameCard);

    /**
     * 명함사용자 정보를 수정한다.
     * @param nameCardUser - 명함사용자 정보
     * @return 수정 건수
     */
    int updateNcrdUseInf(NameCardUser nameCardUser);

    /**
     * 내 명함 정보에 대한 목록을 조회한다.
     * @param nameCardVO - 명함 VO
     * @return 내 명함 목록
     */
    List<NameCardVO> selectMyNcrdItemList(NameCardVO nameCardVO);

    /**
     * 내 명함 목록 전체 건수를 조회한다.
     * @param nameCardVO - 명함 VO
     * @return 전체 건수
     */
    int selectMyNcrdItemListCnt(NameCardVO nameCardVO);
}
