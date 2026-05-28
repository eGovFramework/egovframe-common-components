package egovframework.com.dam.spe.req.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.dam.spe.req.service.RequestOfferVO;

/**
 * 지식정보제공/지식정보요청를 위한 Mapper 인터페이스
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel     @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper
public interface RequestOfferMapper {

	/**
	 * 삭제시 하위 답변 건수를 조회한다.
	 * @param map - 조회 조건 Map
	 * @return int
	 */
	int selectRequestOfferDelCnt(Map<?, ?> map);

	/**
	 * 등록된 지식전문가 건수를 조회한다.
	 * @param map - 조회 조건 Map
	 * @return int
	 */
	int selectRequestOfferSpeCnt(Map<?, ?> map);

	/**
	 * 지식정보제공/지식정보요청 목록을 조회한다.
	 * @param requestOfferVO - 지식정보 VO
	 * @return List
	 */
	List<EgovMap> selectRequestOffer(RequestOfferVO requestOfferVO);

	/**
	 * 지식정보제공/지식정보요청 목록 총 건수를 조회한다.
	 * @param requestOfferVO - 지식정보 VO
	 * @return int
	 */
	int selectRequestOfferCnt(RequestOfferVO requestOfferVO);

	/**
	 * 지식정보제공/지식정보요청 상세 정보를 조회한다.
	 * @param requestOfferVO - 지식정보 VO
	 * @return RequestOfferVO
	 */
	RequestOfferVO selectRequestOfferDetail(RequestOfferVO requestOfferVO);

	/**
	 * 지식정보제공/지식정보요청을 저장 등록한다.
	 * @param requestOfferVO - 지식정보 VO
	 */
	void insertRequestOfferSave(RequestOfferVO requestOfferVO);

	/**
	 * 답변 순번을 조회한다.
	 * @param requestOfferVO - 지식정보 VO
	 * @return int
	 */
	int selectRequestOfferReplySeq(RequestOfferVO requestOfferVO);

	/**
	 * 부모 답변 정보를 조회한다.
	 * @param requestOfferVO - 지식정보 VO
	 * @return Map
	 */
	Map<?, ?> selectRequestOfferReplyaAnsParents(RequestOfferVO requestOfferVO);

	/**
	 * 부모 답변 탐색 정보를 조회한다.
	 * @param hmParam - 조회 조건 Map
	 * @return Map
	 */
	Map<?, ?> selectRequestOfferReplyaAnsParentsSearch(Map<?, ?> hmParam);

	/**
	 * 답변 순서를 업데이트한다.
	 * @param requestOfferVO - 지식정보 VO
	 */
	void updateRequestOfferReply(RequestOfferVO requestOfferVO);

	/**
	 * 답변을 등록한다.
	 * @param requestOfferVO - 지식정보 VO
	 */
	void insertRequestOfferReply(RequestOfferVO requestOfferVO);

	/**
	 * 지식정보제공/지식정보요청을 수정한다.
	 * @param requestOfferVO - 지식정보 VO
	 */
	void updateRequestOffer(RequestOfferVO requestOfferVO);

	/**
	 * 지식정보제공/지식정보요청을 삭제한다.
	 * @param requestOfferVO - 지식정보 VO
	 */
	void deleteRequestOffer(RequestOfferVO requestOfferVO);

}
