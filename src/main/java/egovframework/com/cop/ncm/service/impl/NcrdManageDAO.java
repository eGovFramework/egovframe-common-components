package egovframework.com.cop.ncm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.ncm.service.NameCard;
import egovframework.com.cop.ncm.service.NameCardUser;
import egovframework.com.cop.ncm.service.NameCardVO;

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
public class NcrdManageDAO extends EgovComAbstractDAO {

    // Logger log = Logger.getLogger(this.getClass());

    /**
     * 명함 정보를 삭제한다.
     *
     * @param nameCard
     * @throws Exception
     */
    public int deleteNcrdItemUser(NameCardVO nameCardVO){
        return delete("NcrdManageDAO.deleteNcrdItemUser", nameCardVO);
    }

    public int deleteNcrdItem(NameCardVO nameCardVO) {
        return delete("NcrdManageDAO.deleteNcrdItem", nameCardVO);
    }

    /**
     * 명함 정보를 등록한다.
     *
     * @param nameCard
     * @throws Exception
     */
    public int insertNcrdItem(NameCard nameCard) {
        return insert("NcrdManageDAO.insertNcrdItem", nameCard);
    }

    /**
     * 명함사용자 정보를 등록한다.
     *
     * @param ncrdUser
     * @throws Exception
     */
    public int insertNcrdUseInf(NameCardUser ncrdUser) {
        return insert("NcrdManageDAO.insertNcrdUseInf", ncrdUser);
    }

    /**
     * 명함 정보에 대한 상세정보를 조회한다.
     *
     * @param nameCard
     * @return
     * @throws Exception
     */
    public NameCardVO selectNcrdItem(NameCard nameCard) {
        return selectOne("NcrdManageDAO.selectNcrdItem", nameCard);
    }

    /**
     * 명함 정보에 대한 목록을 조회한다.
     *
     * @param nameCard
     * @return
     * @throws Exception
     */
    public List<NameCardVO> selectNcrdItemList(NameCardVO nameCardVO) {
        return selectList("NcrdManageDAO.selectNcrdItemList", nameCardVO);
    }

    /**
     *
     * @param nameCard
     * @return
     * @throws Exception
     */
    public int selectNcrdItemListCnt(NameCardVO nameCardVO) {
        return selectOne("NcrdManageDAO.selectNcrdItemListCnt", nameCardVO);
    }

    /**
     * 명함 정보에 대한 목록 전체 건수를 조회한다.
     *
     * @param nameCardUser
     * @return
     * @throws Exception
     */
    public List<NameCardUser> selectNcrdUseInfs(NameCardUser nameCardUser) {
        return selectList("NcrdManageDAO.selectNcrdUseInfs", nameCardUser);
    }

    /**
     * 명함사용자 정보에 대한 목록 전체 건수를 조회한다.
     *
     * @param ncrdUser
     * @return
     * @throws Exception
     */
    public int selectNcrdUseInfsCnt(NameCardUser nameCardUser) {
        return selectOne("NcrdManageDAO.selectNcrdUseInfsCnt", nameCardUser);
    }

    /**
     * 명함 정보를 수정한다.
     *
     * @param nameCard
     * @throws Exception
     */
    public int updateNcrdItem(NameCard nameCard) {
        return update("NcrdManageDAO.updateNcrdItem", nameCard);
    }

    /**
     * 명함사용자 정보를 수정한다.
     *
     * @param nameCardUser
     * @throws Exception
     */
    public int updateNcrdUseInf(NameCardUser nameCardUser) {
        return update("NcrdManageDAO.updateNcrdUseInf", nameCardUser);
    }

    /**
     * 내 명함 정보에 대한 목록을 조회한다.
     *
     * @param nameCardVO
     * @return
     * @throws Exception
     */
    public List<NameCardVO> selectMyNcrdItemList(NameCardVO nameCardVO) {
        return selectList("NcrdManageDAO.selectMyNcrdItemList", nameCardVO);
    }

    /**
     * 내 명함 정보에 대한 목록 전체 건수를 조회한다.
     *
     * @param nameCardVO
     * @return
     * @throws Exception
     */
    public int selectMyNcrdItemListCnt(NameCardVO nameCardVO) {
        return selectOne("NcrdManageDAO.selectMyNcrdItemListCnt", nameCardVO);
    }
}
