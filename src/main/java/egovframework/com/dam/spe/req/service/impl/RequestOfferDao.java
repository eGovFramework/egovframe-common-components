package egovframework.com.dam.spe.req.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.dam.spe.req.service.RequestOfferVO;
import jakarta.annotation.Resource;

/**
 * 지식정보제공/지식정보요청를 처리하는 Dao Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.08.30
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.30  장동한          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@Repository("RequestOfferDao")
public class RequestOfferDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestOfferDao.class);

	@Resource(name = "requestOfferMapper")
	private RequestOfferMapper requestOfferMapper;

    /**
     * 삭제시 하위 답변 건수를 조회한다.
     * @param map  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    public int selectRequestOfferDelCnt(Map<?, ?> map) throws Exception {
    	return requestOfferMapper.selectRequestOfferDelCnt(map);
    }

    /**
     * 등록된 지식전문가 건수를 조회한다.
     * @param map  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    public int selectRequestOfferSpeCnt(Map<?, ?> map) throws Exception {
    	return requestOfferMapper.selectRequestOfferSpeCnt(map);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 목록을 한다.
     * @param requestOfferVO  조회할 정보가 담긴 객체
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectRequestOfferList(RequestOfferVO requestOfferVO) throws Exception {
    	return requestOfferMapper.selectRequestOffer(requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 목록 전체 건수를(을) 조회한다.
     * @param requestOfferVO  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    public int selectRequestOfferListCnt(RequestOfferVO requestOfferVO) throws Exception {
    	return requestOfferMapper.selectRequestOfferCnt(requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 상세조회 한다.
     * @param requestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     * @return RequestOfferVO
     * @throws Exception
     */
    public RequestOfferVO selectRequestOfferDetail(RequestOfferVO requestOfferVO) throws Exception {
    	return requestOfferMapper.selectRequestOfferDetail(requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 등록한다.
     * @param requestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     * @throws Exception
     */
    @SuppressWarnings("unused")
	public void insertRequestOffer(RequestOfferVO requestOfferVO) throws Exception {
    	if (requestOfferVO.getCmd().equals("save")) {
    		requestOfferMapper.insertRequestOfferSave(requestOfferVO);
    	} else if (requestOfferVO.getCmd().equals("reply")) {
    		int nSeq = requestOfferMapper.selectRequestOfferReplySeq(requestOfferVO);

    		Map<?, ?> mapAnsParents = requestOfferMapper.selectRequestOfferReplyaAnsParents(requestOfferVO);

    		//단말노드가 아닐때 탐색
    		if (mapAnsParents != null) {
	    		String sAnsParents = (String) mapAnsParents.get("knoId");

	    		LOGGER.info("sAnsParents>" + sAnsParents);

	    		//단말노드 검사
	    		while (true) {
	    			HashMap<String, String> hmParam = new HashMap<>();
	    			hmParam.put("ansParents", sAnsParents);
	    			mapAnsParents = requestOfferMapper.selectRequestOfferReplyaAnsParentsSearch(hmParam);
	    			LOGGER.info("mapAnsParentsSearch>" + mapAnsParents);

	    			if (mapAnsParents == null) {
	    				break;
	    			} else {
	    				sAnsParents = (String) mapAnsParents.get("knoId");
	    				nSeq = (Integer) mapAnsParents.get("ansSeq") + 1;
	    			}
	    		}
    		}

    		//단말노드가 없으면
    		if (nSeq != 1) {
    			requestOfferVO.setAnsSeq(nSeq);
    		}
    		LOGGER.info("LastSeq>" + nSeq);

    		requestOfferMapper.updateRequestOfferReply(requestOfferVO);
    		requestOfferMapper.insertRequestOfferReply(requestOfferVO);
    	}
    }

    /**
     * 지식정보제공/지식정보요청를(을) 수정한다.
     * @param requestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     * @throws Exception
     */
    public void updateRequestOffer(RequestOfferVO requestOfferVO) throws Exception {
    	requestOfferMapper.updateRequestOffer(requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 삭제한다.
     * @param requestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     * @throws Exception
     */
    public void deleteRequestOffer(RequestOfferVO requestOfferVO) throws Exception {
    	requestOfferMapper.deleteRequestOffer(requestOfferVO);
    }

}
