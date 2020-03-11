package egovframework.com.uss.ion.rss.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.uss.ion.rss.service.EgovRssTagManageService;
import egovframework.com.uss.ion.rss.service.RssManage;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
/**
 * RSS태그관리를 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
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
@Service("egovRssManageService")
public class EgovRssTagManageServiceImpl extends EgovAbstractServiceImpl
        implements EgovRssTagManageService {

	/* RSS관리 DAO */
    @Resource(name = "rssManageDao")
    private RssTagManageDao dao;

    /* RSS ID Generator Service */
    @Resource(name = "egovRssTagManageIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     * JDBC 테이블 목록을조회한다.
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
    @Override
	public List<?> selectRssTagManageTableList() throws Exception {
    	return dao.selectRssTagManageTableList();
    }
    /**
     * JDBC 테이블 컬럼 목록을 조회한다.
     * @param map - 컬럼조회정보
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<?> selectRssTagManageTableColumnList(Map map) throws Exception {
    	return dao.selectRssTagManageTableColumnList(map);
    }
    /**
     * RSS태그관리를(을) 목록을 조회 한다.
     * @param rssManage -조회할 정보가 담긴 객체
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
    @Override
	public List<?> selectRssTagManageList(RssManage rssManage) throws Exception {
    	return dao.selectRssTagManageList(rssManage);
    }

    /**
     * RSS태그관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    @Override
	public int selectRssTagManageListCnt(RssManage rssManage) throws Exception {
        return dao.selectRssTagManageListCnt(rssManage);
    }

    /**
     * RSS태그관리를(을) 상세조회 한다.
     * @param searchVO -조회할 정보가 담긴 객체
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
    @Override
	public RssManage selectRssTagManageDetail(RssManage rssManage) throws Exception {
        return dao.selectRssTagManageDetail(rssManage);
    }

    /**
     * RSS태그관리를(을) 등록한다.
     * @param rssManage -RSS태그관리 정보가 담긴 객체
     * @throws Exception
     */
    @Override
	public void insertRssTagManage(RssManage rssManage)throws Exception {

    	rssManage.setRssId(idgenService.getNextStringId());

    	dao.insertRssTagManage(rssManage);
    }

    /**
     * RSS태그관리를(을) 수정한다.
     * @param rssManage -RSS태그관리 정보가 담긴 객체
     * @throws Exception
     */
    @Override
	public void updateRssTagManage(RssManage rssManage) throws Exception {
    	dao.updateRssTagManage(rssManage);
    }

    /**
     * RSS태그관리를(을) 삭제한다.
     * @param rssManage -RSS태그관리 정보가 담긴 객체
     * @throws Exception
     */
    @Override
	public void deleteRssTagManage(RssManage rssManage) throws Exception {
    	dao.deleteRssTagManage(rssManage);
    }

}
