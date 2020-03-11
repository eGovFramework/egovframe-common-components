package egovframework.com.uss.ion.rss.service;

import java.util.List;
import java.util.Map;
/**
 * RSS태그관리를 처리하는 Service Class 구현
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
public interface EgovRssTagManageService {

    /**
     * JDBC 테이블 목록을조회한다.
     * @return List
     * @throws Exception
     */
    public List<?> selectRssTagManageTableList() throws Exception;

    /**
     * JDBC 테이블 컬럼 목록을 조회한다.
     * @param map - 컬럼조회정보
     * @return List
     * @throws Exception
     */
    public List<?> selectRssTagManageTableColumnList(Map<?, ?> map) throws Exception;

    /**
	 * RSS태그관리 목록을 조회한다.
	 * @param rssManage -조회할 정보가 담긴 객체
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectRssTagManageList(RssManage rssManage) throws Exception;

    /**
     * RSS태그관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param rssManage  -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    public int selectRssTagManageListCnt(RssManage rssManage) throws Exception;

     /**
	 * RSS태그관리를(을) 상세조회 한다.
	 * @param rssManage -RSS태그관리 정보 담김 객체
	 * @return List
	 * @throws Exception
	 */
	public RssManage selectRssTagManageDetail(RssManage rssManage) throws Exception;

     /**
	 * RSS태그관리를(을) 등록한다.
	 * @param rssManage -RSS태그관리 정보 담김 객체
	 * @throws Exception
	 */
	void  insertRssTagManage(RssManage rssManage) throws Exception;

     /**
	 * RSS태그관리를(을) 수정한다.
	 * @param rssManage -RSS태그관리 정보 담김 객체
	 * @throws Exception
	 */
	void  updateRssTagManage(RssManage rssManage) throws Exception;

	/**
	 * RSS태그관리를(을) 삭제한다.
	 * @param rssManage -RSS태그관리 정보 담김 VO
	 * @throws Exception
	 */
	void  deleteRssTagManage(RssManage rssManage) throws Exception;

}
