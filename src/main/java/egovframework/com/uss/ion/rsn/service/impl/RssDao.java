package egovframework.com.uss.ion.rsn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.rsn.service.RssInfo;
/**
 * RSS서비스를 처리하는 Dao Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.06.16
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
@Repository("rssInfoDao")
public class RssDao extends EgovComAbstractDAO {

    /**
     * RSS서비스 테이블을 조회 한다.
     * @param param -조회할 정보가 담긴 객체
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
    public List<?> selectRssTagServiceTable(Map<?, ?> param) throws Exception {
    	return selectList("RssTagService.selectRssTagServiceTable",param);
    }

    /**
     * RSS서비스를(을) 목록을 한다.
     * @param rssInfo -조회할 정보가 담긴 객체
     * @return List
     * @throws Exception
     */
    public List<?> selectRssTagServiceList(RssInfo rssInfo) throws Exception {
    	return selectList("RssTagService.selectRssTagService",rssInfo);
    }

    /**
     * RSS서비스를(을) 목록 전체 건수를(을) 조회한다.
     * @param rssInfo -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    public int selectRssTagServiceListCnt(RssInfo rssInfo) throws Exception {
    	return (Integer)selectOne("RssTagService.selectRssTagServiceCnt", rssInfo);
    }

    /**
     * RSS서비스를(을) 상세조회 한다.
     * @param rssInfo -조회할 정보가 담긴 객체
     * @return Map -조회한정보가담긴Map
     * @throws Exception
     */
    public Map<?, ?> selectRssTagServiceDetail(RssInfo rssInfo) throws Exception {
    	return (Map<?, ?>)selectOne("RssTagService.selectRssTagServiceDetail", rssInfo);
    }



}
