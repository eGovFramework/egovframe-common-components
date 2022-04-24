package egovframework.com.dam.spe.req.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.impl.EgovComAbstractMapper;
import egovframework.com.dam.spe.req.service.RequestOfferVO;

import org.springframework.stereotype.Repository;
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
public class RequestOfferDao extends EgovComAbstractMapper {

    /**
     * 삭제시 하위 답변 건수를 조회한다.
     * @param RequestOfferVO  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    public int selectRequestOfferDelCnt(Map<?, ?> map) throws Exception {
    	return (Integer)selectOne("RequestOffer.selectRequestOfferDelCnt", map);
    }


    /**
     * 등록된 지식전문가 건수를 조회한다.
     * @param RequestOfferVO  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    public int selectRequestOfferSpeCnt(Map<?, ?> map) throws Exception {
    	return (Integer)selectOne("RequestOffer.selectRequestOfferSpeCnt", map);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 목록을 한다.
     * @param RequestOfferVO  조회할 정보가 담긴 객체
     * @return List
     * @throws Exception
     */
    public List<?> selectRequestOfferList(RequestOfferVO RequestOfferVO) throws Exception {
    	return selectList("RequestOffer.selectRequestOffer",RequestOfferVO);

    }

    /**
     * 지식정보제공/지식정보요청를(을) 목록 전체 건수를(을) 조회한다.
     * @param RequestOfferVO  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    public int selectRequestOfferListCnt(RequestOfferVO RequestOfferVO) throws Exception {
    	return (Integer)selectOne("RequestOffer.selectRequestOfferCnt", RequestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 상세조회 한다.
     * @param RequestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     * @return RequestOfferVO
     * @throws Exception
     */
    public RequestOfferVO selectRequestOfferDetail(RequestOfferVO RequestOfferVO) throws Exception {
    	return (RequestOfferVO)selectOne("RequestOffer.selectRequestOfferDetail", RequestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 등록한다.
     * @param qindvdlInfoPolicy  지식정보제공/지식정보요청 정보가 담김 객체
     * @throws Exception
     */
    @SuppressWarnings("unused")
	public void insertRequestOffer(RequestOfferVO RequestOfferVO) throws Exception {
    	if(RequestOfferVO.getCmd().equals("save")){
    		insert("RequestOffer.insertRequestOfferSave", RequestOfferVO);
    	}else if(RequestOfferVO.getCmd().equals("reply")){
    		int nSeq = (Integer)selectOne("RequestOffer.selectRequestOfferReplySeq", RequestOfferVO);

    		Map<?, ?> mapAnsParents = (Map<?, ?>)selectOne("RequestOffer.selectRequestOfferReplyaAnsParents", RequestOfferVO);

    		//단말노드가 아닐때 탐색
    		if(mapAnsParents != null){
	    		Map<?, ?> mapAnsParentsSearch = null;
	    		String sAnsParents = (String)mapAnsParents.get("knoId");

	    		System.out.println("sAnsParents>" + sAnsParents);

	    		//단말노드 검사
	    		while(true){
	    			HashMap<String, String> hmParam = new HashMap<String, String>();
	    			hmParam.put("ansParents", sAnsParents);
	    			mapAnsParents = (Map<?, ?>)selectOne("RequestOffer.selectRequestOfferReplyaAnsParentsSearch", hmParam);
	    			System.out.println("mapAnsParentsSearch>" + mapAnsParents);

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
    			RequestOfferVO.setAnsSeq(nSeq);
    		}
    		System.out.println("LastSeq>" + nSeq);

    		update("RequestOffer.updateRequestOfferReply", RequestOfferVO);
    		insert("RequestOffer.insertRequestOfferReply", RequestOfferVO);

    	}

    }

    /**
     * 지식정보제공/지식정보요청를(을) 수정한다.
     * @param RequestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     * @throws Exception
     */
    public void updateRequestOffer(RequestOfferVO RequestOfferVO) throws Exception {
    	 update("RequestOffer.updateRequestOffer", RequestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 삭제한다.
     * @param RequestOfferVO  지식정보제공/지식정보요청 정보가 담김 객체
     * @throws Exception
     */
    public void deleteRequestOffer(RequestOfferVO RequestOfferVO) throws Exception {
    	delete("RequestOffer.deleteRequestOffer", RequestOfferVO);
    }

}
