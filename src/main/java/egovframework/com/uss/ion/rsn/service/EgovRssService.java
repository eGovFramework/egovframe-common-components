package egovframework.com.uss.ion.rsn.service;

import java.util.List;
import java.util.Map;
/**
 * RSS서비스를 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
public interface EgovRssService {

    /**
     * RSS서비스 테이블을 조회 한다.
     * @param param -조회할 정보가 담긴 객체
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
	public List<?> selectRssTagServiceTable(Map<?, ?> param) throws Exception;

    /**
	 * RSS서비스 목록을 조회한다.
	 * @param rssInfo -조회할 정보가 담긴 객체
	 * @return List -조회한목록이담긴List
	 * @throws Exception
	 */
	public List<?> selectRssTagServiceList(RssInfo rssInfo) throws Exception;

    /**
     * RSS서비스를(을) 목록 전체 건수를(을) 조회한다.
     * @param rssInfo -조회할 정보가 담긴 객체
     * @return -조회한건수가담긴Integer
     * @throws Exception
     */
    public int selectRssTagServiceListCnt(RssInfo rssInfo) throws Exception;

     /**
	 * RSS서비스를(을) 상세조회 한다.
	 * @param rssInfo -조회할 정보가 담긴 객체
	 * @return int -조회한건수가담긴Integer
	 * @throws Exception
	 */
	public Map<?, ?> selectRssTagServiceDetail(RssInfo rssInfo) throws Exception;

}
