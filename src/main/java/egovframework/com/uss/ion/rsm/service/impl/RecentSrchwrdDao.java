package egovframework.com.uss.ion.rsm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.rsm.service.RecentSrchwrd;

/**
 * 최근검색어를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
@Repository("onlineRecentSrchwrdDao")
public class RecentSrchwrdDao extends EgovComAbstractDAO {

    /**
     * 최근검색어관리를(을) 목록을 한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectRecentSrchwrdList(RecentSrchwrd searchVO) throws Exception {
        return selectList("RecentSrchwrd.selectRecentSrchwrd", searchVO);
    }

    /**
     * 최근검색어관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectRecentSrchwrdListCnt(RecentSrchwrd searchVO) throws Exception {
        return (Integer)selectOne("RecentSrchwrd.selectRecentSrchwrdCnt", searchVO);
    }

    /**
     * 최근검색어관리를(을) 상세조회 한다.
     * @param recentSrchwrdVO  최근검색어 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    public RecentSrchwrd selectRecentSrchwrdDetail(RecentSrchwrd recentSrchwrd) throws Exception {
        return (RecentSrchwrd)selectOne("RecentSrchwrd.selectRecentSrchwrdDetail", recentSrchwrd);
    }

    /**
     * 최근검색어관리를(을) 등록한다.
     * @param qrecentSrchwrdVO  최근검색어 정보가 담김 VO
     * @throws Exception
     */
    public void insertRecentSrchwrd(RecentSrchwrd recentSrchwrd) throws Exception {
        insert("RecentSrchwrd.insertRecentSrchwrd", recentSrchwrd);
    }

    /**
     * 최근검색어관리를(을) 수정한다.
     * @param recentSrchwrdVO  최근검색어 정보가 담김 VO
     * @throws Exception
     */
    public void updateRecentSrchwrd(RecentSrchwrd recentSrchwrd) throws Exception {
        update("RecentSrchwrd.updateRecentSrchwrd", recentSrchwrd);
    }

    /**
     * 최근검색어관리를(을) 삭제한다.
     * @param recentSrchwrdVO  최근검색어 정보가 담김 VO
     * @throws Exception
     */
    public void deleteRecentSrchwrd(RecentSrchwrd recentSrchwrd) throws Exception {
        delete("RecentSrchwrd.deleteRecentSrchwrd", recentSrchwrd);
    }

    /**
     * 최근검색어결과를(을) 목록을 한다.
     * @param recentSrchwrdVO  최근검색어 정보 담김 VO
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectRecentSrchwrdResultInquire(RecentSrchwrd recentSrchwrd) throws Exception {
        return selectList("RecentSrchwrd.selectRecentSrchwrdResultInquire", recentSrchwrd);
    }

    /**
     * 최근검색어결과를(을) 목록을 한다.
     * @param recentSrchwrdVO  최근검색어 정보 담김 VO
     * @return List
     * @throws Exception
     */
    public List<?> selectRecentSrchwrdResultList(RecentSrchwrd searchVO) throws Exception {
        return selectList("RecentSrchwrd.selectRecentSrchwrdResult", searchVO);
    }

    /**
     * 최근검색어결과를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectRecentSrchwrdResultListCnt(RecentSrchwrd searchVO) throws Exception {
        return (Integer)selectOne("RecentSrchwrd.selectRecentSrchwrdCntResult", searchVO);
    }

    /**
     * 최근검색어결과를를(을) 등록한다.
     * @param recentSrchwrd  최근검색어결과 정보가 담김 VO
     * @throws Exception
     */
    public void insertRecentSrchwrdResult(RecentSrchwrd recentSrchwrd) throws Exception {
        insert("RecentSrchwrd.insertRecentSrchwrdResult", recentSrchwrd);
    }

    /**
     * 최근검색어결과 건별 삭제
     * @param recentSrchwrd  최근검색어결과 정보가 담김 VO
     * @throws Exception
     */
    public void deleteRecentSrchwrdResult(RecentSrchwrd recentSrchwrd) throws Exception {
        delete("RecentSrchwrd.deleteRecentSrchwrdResult", recentSrchwrd);
    }

    /**
     * 최근검색어결과 관리별 삭제
     * @param recentSrchwrd  최근검색어결과 정보가 담김 VO
     * @throws Exception
     */
    public void deleteRecentSrchwrdResultAll(RecentSrchwrd recentSrchwrd) throws Exception {
        delete("RecentSrchwrd.deleteRecentSrchwrdResultAll", recentSrchwrd);
    }

}
