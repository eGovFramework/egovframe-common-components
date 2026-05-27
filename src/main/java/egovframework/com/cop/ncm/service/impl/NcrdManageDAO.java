package egovframework.com.cop.ncm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.ncm.service.NameCard;
import egovframework.com.cop.ncm.service.NameCardUser;
import egovframework.com.cop.ncm.service.NameCardVO;
import jakarta.annotation.Resource;

/**
 * 명함정보를 관리하기 위한 데이터 접근 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
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
@Repository("NcrdManageDAO")
public class NcrdManageDAO {

    @Resource(name = "NcrdManageMapper")
    private NcrdManageMapper ncrdManageMapper;

    /**
     * 명함사용자 정보를 삭제한다.
     *
     * @param nameCardVO
     */
    public int deleteNcrdItemUser(NameCardVO nameCardVO) {
        return ncrdManageMapper.deleteNcrdItemUser(nameCardVO);
    }

    /**
     * 명함 정보를 삭제한다.
     *
     * @param nameCardVO
     */
    public int deleteNcrdItem(NameCardVO nameCardVO) {
        return ncrdManageMapper.deleteNcrdItem(nameCardVO);
    }

    /**
     * 명함 정보를 등록한다.
     *
     * @param nameCard
     */
    public int insertNcrdItem(NameCard nameCard) {
        return ncrdManageMapper.insertNcrdItem(nameCard);
    }

    /**
     * 명함사용자 정보를 등록한다.
     *
     * @param ncrdUser
     */
    public int insertNcrdUseInf(NameCardUser ncrdUser) {
        return ncrdManageMapper.insertNcrdUseInf(ncrdUser);
    }

    /**
     * 명함 정보에 대한 상세정보를 조회한다.
     *
     * @param nameCard
     * @return 명함 상세정보 VO
     */
    public NameCardVO selectNcrdItem(NameCard nameCard) {
        return ncrdManageMapper.selectNcrdItem(nameCard);
    }

    /**
     * 명함 정보에 대한 목록을 조회한다.
     *
     * @param nameCardVO
     * @return 명함 목록
     */
    public List<NameCardVO> selectNcrdItemList(NameCardVO nameCardVO) {
        return ncrdManageMapper.selectNcrdItemList(nameCardVO);
    }

    /**
     * 명함 목록 전체 건수를 조회한다.
     *
     * @param nameCardVO
     * @return 전체 건수
     */
    public int selectNcrdItemListCnt(NameCardVO nameCardVO) {
        return ncrdManageMapper.selectNcrdItemListCnt(nameCardVO);
    }

    /**
     * 명함사용자 목록을 조회한다.
     *
     * @param nameCardUser
     * @return 명함사용자 목록
     */
    public List<NameCardUser> selectNcrdUseInfs(NameCardUser nameCardUser) {
        return ncrdManageMapper.selectNcrdUseInfs(nameCardUser);
    }

    /**
     * 명함사용자 목록 전체 건수를 조회한다.
     *
     * @param nameCardUser
     * @return 전체 건수
     */
    public int selectNcrdUseInfsCnt(NameCardUser nameCardUser) {
        return ncrdManageMapper.selectNcrdUseInfsCnt(nameCardUser);
    }

    /**
     * 명함 정보를 수정한다.
     *
     * @param nameCard
     */
    public int updateNcrdItem(NameCard nameCard) {
        return ncrdManageMapper.updateNcrdItem(nameCard);
    }

    /**
     * 명함사용자 정보를 수정한다.
     *
     * @param nameCardUser
     */
    public int updateNcrdUseInf(NameCardUser nameCardUser) {
        return ncrdManageMapper.updateNcrdUseInf(nameCardUser);
    }

    /**
     * 내 명함 정보에 대한 목록을 조회한다.
     *
     * @param nameCardVO
     * @return 내 명함 목록
     */
    public List<NameCardVO> selectMyNcrdItemList(NameCardVO nameCardVO) {
        return ncrdManageMapper.selectMyNcrdItemList(nameCardVO);
    }

    /**
     * 내 명함 목록 전체 건수를 조회한다.
     *
     * @param nameCardVO
     * @return 전체 건수
     */
    public int selectMyNcrdItemListCnt(NameCardVO nameCardVO) {
        return ncrdManageMapper.selectMyNcrdItemListCnt(nameCardVO);
    }
}
