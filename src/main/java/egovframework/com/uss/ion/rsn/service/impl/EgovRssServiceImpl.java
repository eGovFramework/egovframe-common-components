package egovframework.com.uss.ion.rsn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.rsn.service.EgovRssService;
import egovframework.com.uss.ion.rsn.service.RssInfo;

/**
 * RSS서비스를 처리하는 ServiceImpl Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2025.08.13  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Service("egovRssService")
public class EgovRssServiceImpl extends EgovAbstractServiceImpl implements EgovRssService {

	@Resource(name = "rssInfoDao")
	private RssDao dao;

	/**
	 * RSS서비스 테이블을 조회 한다.
	 * 
	 * @param param -조회할 정보가 담긴 객체
	 * @return List -조회한목록이담긴List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> selectRssTagServiceTable(Map<?, ?> param) throws Exception {

		List<?> listResult = dao.selectRssTagServiceTable(param);
		List<Map<String, String>> listReturn = new ArrayList<Map<String, String>>();

		String sBdtTitle = (String) param.get("BDT_TITLE");
		String sBdtLink = (String) param.get("BDT_LINK");
		String sBdtDescription = (String) param.get("BDT_DESCRIPTION");
		String sBdtTag = (String) param.get("BDT_TAG");
		String sBdtEtc = (String) param.get("BDT_ETC");

		Map<String, String> mapRow;

		for (int i = 0; i < listResult.size(); i++) {

			mapRow = (Map<String, String>) listResult.get(i);
			// null 처리
			String smBdtTitle = sBdtTitle == null ? "" : sBdtTitle;
			String smBdtLink = sBdtLink == null ? "" : sBdtLink;
			String smBdtDescription = sBdtDescription == null ? "" : sBdtDescription;
			String smBdtTag = sBdtTag == null ? "" : sBdtTag;
			String smBdtEtc = sBdtEtc == null ? "" : sBdtEtc;

			Object[] keys = mapRow.keySet().toArray();

			for (Object key : keys) {
				if (mapRow.get(key) instanceof String) {
					// null 처리
					if (mapRow.get(key) != null && key != null) {
						smBdtTitle = smBdtTitle.replaceAll("#" + key + "#", mapRow.get(key));
						smBdtLink = smBdtLink.replaceAll("#" + key + "#", mapRow.get(key));
						smBdtDescription = smBdtDescription.replaceAll("#" + key + "#", mapRow.get(key));
						smBdtTag = smBdtTag.replaceAll("#" + key + "#", mapRow.get(key));
						smBdtEtc = smBdtEtc.replaceAll("#" + key + "#", mapRow.get(key));
					}
				}
			}

			mapRow.put("BDT_TITLE", smBdtTitle);
			mapRow.put("BDT_LINK", smBdtLink);
			mapRow.put("BDT_DESCRIPTION", smBdtDescription);
			mapRow.put("BDT_TAG", smBdtTag);
			mapRow.put("BDT_ETC", smBdtEtc);

			listReturn.add(mapRow);

		}

		return listReturn;
	}

	/**
	 * RSS서비스를(을) 목록을 조회 한다.
	 * 
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
	 * 
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
	 * 
	 * @param rssInfo -조회할 정보가 담긴 객체
	 * @return Map -조회한정보가담긴Map
	 * @throws Exception
	 */
	@Override
	public Map<?, ?> selectRssTagServiceDetail(RssInfo rssInfo) throws Exception {
		return dao.selectRssTagServiceDetail(rssInfo);
	}

}
