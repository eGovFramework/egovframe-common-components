package egovframework.com.dam.spe.req.service;

import java.util.List;
import java.util.Map;
/**
 * 지식정보제공/지식정보요청를 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2010.08.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.30  장동한          최초 생성
 *
 * </pre>
 */
public interface EgovRequestOfferService {

    /**
     * 삭제시 하위 답변 건수를 조회한다.
     * @param RequestOfferVO  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    public int selectRequestOfferDelCnt(Map<?, ?> map) throws Exception;

	/**
	 * 등록된 지식전문가 건수를 조회한다.
	 * @param map  조회할 정보가 담긴 객체
	 * @return List
	 * @throws Exception
	 */
	public boolean selectRequestOfferSpeCheck(Map<?, ?> map) throws Exception;

    /**
	 * 지식정보제공/지식정보요청 목록을 조회한다.
	 * @param searchVO  조회할 정보가 담긴 객체
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectRequestOfferList(RequestOfferVO RequestOfferVO) throws Exception;

    /**
     * 지식정보제공/지식정보요청를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    public int selectRequestOfferListCnt(RequestOfferVO RequestOfferVO) throws Exception;

     /**
	 * 지식정보제공/지식정보요청를(을) 상세조회 한다.
	 * @param RequestOfferVO  지식정보제공/지식정보요청 정보 담김 객체
	 * @return List
	 * @throws Exception
	 */
	public RequestOfferVO selectRequestOfferDetail(RequestOfferVO RequestOfferVO) throws Exception;

     /**
	 * 지식정보제공/지식정보요청를(을) 등록한다.
	 * @param RequestOfferVO  지식정보제공/지식정보요청 정보 담김 객체
	 * @throws Exception
	 */
	void  insertRequestOffer(RequestOfferVO RequestOfferVO) throws Exception;

     /**
	 * 지식정보제공/지식정보요청를(을) 수정한다.
	 * @param RequestOfferVO  지식정보제공/지식정보요청 정보 담김 객체
	 * @throws Exception
	 */
	void  updateRequestOffer(RequestOfferVO RequestOfferVO) throws Exception;

	/**
	 * 지식정보제공/지식정보요청를(을) 삭제한다.
	 * @param RequestOfferVO  지식정보제공/지식정보요청 정보 담김 VO
	 * @throws Exception
	 */
	void  deleteRequestOffer(RequestOfferVO RequestOfferVO) throws Exception;

}
