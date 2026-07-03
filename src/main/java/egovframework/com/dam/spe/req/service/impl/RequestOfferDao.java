package egovframework.com.dam.spe.req.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.dam.spe.req.service.RequestOfferVO;
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
 *
 * </pre>
 */
@Repository("RequestOfferDao")
public class RequestOfferDao extends EgovComAbstractDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestOfferDao.class);

    /**
     * 삭제시 하위 답변 건수를 조회한다.
     * @param RequestOfferVO  조회할 정보가 담긴 객체
     * @return int
     */
    public int selectRequestOfferDelCnt(Map<?, ?> map) {
    	return (Integer)selectOne("RequestOffer.selectRequestOfferDelCnt", map);
    }


    /**
     * 등록된 지식전문가 건수를 조회한다.
     * @param RequestOfferVO  조회할 정보가 담긴 객체
     * @return int
     */
    public int selectRequestOfferSpeCnt(Map<?, ?> map) {
    	return (Integer)selectOne("RequestOffer.selectRequestOfferSpeCnt", map);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 목록을 한다.
     * @param requestOfferVO  조회할 정보가 담긴 객체
     * @return List
     */
    public List<EgovMap> selectRequestOfferList(RequestOfferVO requestOfferVO) {
    	return selectList("RequestOffer.selectRequestOffer",requestOfferVO);

    }

    /**
     * 지식정보제공/지식정보요청를(을) 목록 전체 건수를(을) 조회한다.
     * @param requestOfferVO  조회할 정보가 담긴 객체
     * @return int
     */
    public int selectRequestOfferListCnt(RequestOfferVO requestOfferVO) {
    	return (Integer)selectOne("RequestOffer.selectRequestOfferCnt", requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 상세조회 한다.
     * @param requestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     * @return RequestOfferVO
     */
    public RequestOfferVO selectRequestOfferDetail(RequestOfferVO requestOfferVO) {
    	return (RequestOfferVO)selectOne("RequestOffer.selectRequestOfferDetail", requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 등록한다.
     * @param qindvdlInfoPolicy  지식정보제공/지식정보요청 정보가 담김 객체
     */
    @SuppressWarnings("unused")
	public void insertRequestOffer(RequestOfferVO requestOfferVO) {
    	if(requestOfferVO.getCmd().equals("save")){
    		insert("RequestOffer.insertRequestOfferSave", requestOfferVO);
    	}else if(requestOfferVO.getCmd().equals("reply")){
    		int nSeq = (Integer)selectOne("RequestOffer.selectRequestOfferReplySeq", requestOfferVO);

    		Map<?, ?> mapAnsParents = (Map<?, ?>)selectOne("RequestOffer.selectRequestOfferReplyaAnsParents", requestOfferVO);

    		//단말노드가 아닐때 탐색
    		if(mapAnsParents != null){
	    		Map<?, ?> mapAnsParentsSearch = null;
	    		String sAnsParents = (String)mapAnsParents.get("knoId");

	    		LOGGER.info("sAnsParents>" + sAnsParents);

	    		//단말노드 검사
	    		while(true){
	    			HashMap<String, String> hmParam = new HashMap<>();
	    			hmParam.put("ansParents", sAnsParents);
	    			mapAnsParents = (Map<?, ?>)selectOne("RequestOffer.selectRequestOfferReplyaAnsParentsSearch", hmParam);
	    			LOGGER.info("mapAnsParentsSearch>" + mapAnsParents);

	    			if(mapAnsParents == null){
	    				break;
	    			//1레벨 일때 처리
	    			//}else if(mapAnsParents == null){
	    			}else{
	    				sAnsParents = (String)mapAnsParents.get("knoId");
	    				nSeq=(Integer)mapAnsParents.get("ansSeq") + 1;
	    			}
	    		}
    		}

    		//단말노드가 없으면
    		if( nSeq != 1){
    			requestOfferVO.setAnsSeq(nSeq);
    		}
    		LOGGER.info("LastSeq>" + nSeq);

    		update("RequestOffer.updateRequestOfferReply", requestOfferVO);
    		insert("RequestOffer.insertRequestOfferReply", requestOfferVO);

    	}

    }

    /**
     * 지식정보제공/지식정보요청를(을) 수정한다.
     * @param requestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     */
    public void updateRequestOffer(RequestOfferVO requestOfferVO) {
    	 update("RequestOffer.updateRequestOffer", requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 삭제한다.
     * @param requestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     */
    public void deleteRequestOffer(RequestOfferVO requestOfferVO) {
    	delete("RequestOffer.deleteRequestOffer", requestOfferVO);
    }

}
