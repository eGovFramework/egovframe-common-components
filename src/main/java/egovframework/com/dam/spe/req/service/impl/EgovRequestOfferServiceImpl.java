package egovframework.com.dam.spe.req.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.com.dam.spe.req.service.EgovRequestOfferService;
import egovframework.com.dam.spe.req.service.RequestOfferVO;
/**
 * 지식정보제공/지식정보요청를 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
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
@Service("egovRequestOffeService")
public class EgovRequestOfferServiceImpl extends EgovAbstractServiceImpl
        implements EgovRequestOfferService {

    @Resource(name = "RequestOfferDao")
    private RequestOfferDao dao;

    /* RSS ID Generator Service */
    @Resource(name = "egovRequestOfferIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     * 삭제시 하위 답변 건수를 조회한다.
     * @param RequestOfferVO  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    @Override
	public int selectRequestOfferDelCnt(Map<?, ?> map) throws Exception {
    	return dao.selectRequestOfferDelCnt(map);
    }

	/**
	 * 등록된 지식전문가 건수를 조회한다.
	 * @param map  조회할 정보가 담긴 객체
	 * @return List
	 * @throws Exception
	 */
	@Override
	public boolean selectRequestOfferSpeCheck(Map<?, ?> map) throws Exception{

		int nSpeCnt = dao.selectRequestOfferSpeCnt(map);

		boolean booleanRtn = false;

		if(nSpeCnt > 0){
			booleanRtn = true;
		}

		return booleanRtn;
	}

    /**
     * 지식정보제공/지식정보요청를(을) 목록을 조회 한다.
     * @param requestOfferVO 조회할 정보가 담긴 객체
     * @return List
     * @throws Exception
     */
    @Override
	public List<EgovMap> selectRequestOfferList(RequestOfferVO requestOfferVO) throws Exception {
    	return dao.selectRequestOfferList(requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    @Override
	public int selectRequestOfferListCnt(RequestOfferVO requestOfferVO) throws Exception {
        return dao.selectRequestOfferListCnt(requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 상세조회 한다.
     * @param searchVO 조회할 정보가 담긴 객체
     * @return List
     * @throws Exception
     */
    @Override
	public RequestOfferVO selectRequestOfferDetail(RequestOfferVO requestOfferVO) throws Exception {
        return dao.selectRequestOfferDetail(requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 등록한다.
     * @param requestOfferVO 지식정보제공/지식정보요청 정보가 담긴 객체
     * @throws Exception
     */
    @Override
	public void insertRequestOffer(RequestOfferVO requestOfferVO)throws Exception {

    	requestOfferVO.setKnoId(idgenService.getNextStringId());

    	dao.insertRequestOffer(requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 수정한다.
     * @param requestOfferVO 지식정보제공/지식정보요청 정보가 담긴 객체
     * @throws Exception
     */
    @Override
	public void updateRequestOffer(RequestOfferVO requestOfferVO) throws Exception {
    	dao.updateRequestOffer(requestOfferVO);
    }

    /**
     * 지식정보제공/지식정보요청를(을) 삭제한다.
     * @param requestOfferVO 지식정보제공/지식정보요청 정보가 담긴 객체
     * @throws Exception
     */
    @Override
	public void deleteRequestOffer(RequestOfferVO requestOfferVO) throws Exception {
    	dao.deleteRequestOffer(requestOfferVO);
    }

}
