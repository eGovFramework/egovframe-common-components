package egovframework.com.uss.ion.rsn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import egovframework.com.uss.ion.rsn.service.EgovRssService;
import egovframework.com.uss.ion.rsn.service.RssInfo;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
/**
 * RSS서비스를 처리하는 ServiceImpl Class 구현
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
@Service("egovRssService")
public class EgovRssServiceImpl extends EgovAbstractServiceImpl
        implements EgovRssService {

    @Resource(name = "rssInfoDao")
    private RssDao dao;

    /**
     * RSS서비스 테이블을 조회 한다.
     * @param param -조회할 정보가 담긴 객체
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> selectRssTagServiceTable(Map<?, ?> param) throws Exception{

		List<?> listResult = dao.selectRssTagServiceTable(param);
		List<Map<String, String>> listReturn = new ArrayList<Map<String, String>>();

		String sBDT_TITLE = (String)param.get("BDT_TITLE");
		String sBDT_LINK = (String)param.get("BDT_LINK");
		String sBDT_DESCRIPTION = (String)param.get("BDT_DESCRIPTION");
		String sBDT_TAG = (String)param.get("BDT_TAG");
		String sBDT_ETC = (String)param.get("BDT_ETC");

		Map<String, String> mapRow;

		for(int i=0;i < listResult.size();i++){

			mapRow = (Map<String, String>)listResult.get(i);
			//null 처리
			String sM_BDT_TITLE = sBDT_TITLE == null ? "": sBDT_TITLE;
			String sM_BDT_LINK = sBDT_LINK == null ? "": sBDT_LINK;
			String sM_BDT_DESCRIPTION = sBDT_DESCRIPTION == null ? "": sBDT_DESCRIPTION;
			String sM_BDT_TAG = sBDT_TAG == null ? "": sBDT_TAG;
			String sM_BDT_ETC = sBDT_ETC == null ? "": sBDT_ETC;

			Object[] Keys = mapRow.keySet().toArray();

			for(Object key : Keys){
				if(mapRow.get(key) instanceof String ){
					//null 처리
					if(mapRow.get(key) != null && key != null){
					sM_BDT_TITLE=sM_BDT_TITLE.replaceAll("#"+key+"#", mapRow.get(key));
					sM_BDT_LINK=sM_BDT_LINK.replaceAll("#"+key+"#", mapRow.get(key));
					sM_BDT_DESCRIPTION=sM_BDT_DESCRIPTION.replaceAll("#"+key+"#", mapRow.get(key));
					sM_BDT_TAG=sM_BDT_TAG.replaceAll("#"+key+"#", mapRow.get(key));
					sM_BDT_ETC=sM_BDT_ETC.replaceAll("#"+key+"#", mapRow.get(key));
					}
				}
			}

			mapRow.put("BDT_TITLE", sM_BDT_TITLE);
			mapRow.put("BDT_LINK", sM_BDT_LINK);
			mapRow.put("BDT_DESCRIPTION", sM_BDT_DESCRIPTION);
			mapRow.put("BDT_TAG", sM_BDT_TAG);
			mapRow.put("BDT_ETC", sM_BDT_ETC);

			listReturn.add(mapRow);

		}

		return listReturn;
	}

    /**
     * RSS서비스를(을) 목록을 조회 한다.
     * @param rssInfo -조회할 정보가 담긴 객체
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
    @Override
	public List<?> selectRssTagServiceList(RssInfo rssInfo) throws Exception {
    	return dao.selectRssTagServiceList(rssInfo);
    }

    /**
     * RSS서비스를(을) 목록 전체 건수를(을) 조회한다.
     * @param rssInfo -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    @Override
	public int selectRssTagServiceListCnt(RssInfo rssInfo) throws Exception {
        return dao.selectRssTagServiceListCnt(rssInfo);
    }

    /**
     * RSS서비스를(을) 상세조회 한다.
     * @param rssInfo -조회할 정보가 담긴 객체
     * @return Map -조회한정보가담긴Map
     * @throws Exception
     */
    @Override
	public Map<?, ?> selectRssTagServiceDetail(RssInfo rssInfo) throws Exception {
        return dao.selectRssTagServiceDetail(rssInfo);
    }



}
