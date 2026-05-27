package egovframework.com.ssi.syi.ims.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.ssi.syi.ims.service.CntcMessage;
import egovframework.com.ssi.syi.ims.service.CntcMessageItem;
import egovframework.com.ssi.syi.ims.service.CntcMessageItemVO;
import egovframework.com.ssi.syi.ims.service.CntcMessageVO;

/**
 * 연계메시지에 대한 매퍼 인터페이스를 정의한다
 *
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("CntcMessageMapper")
public interface CntcMessageMapper {

	/**
	 * 연계메시지를 삭제한다.
	 * @param cntcMessage
	 */
	void deleteCntcMessage(CntcMessage cntcMessage);

	/**
	 * 연계메시지 항목을 삭제한다.
	 * @param cntcMessageItem
	 */
	void deleteCntcMessageItem(CntcMessageItem cntcMessageItem);

	/**
	 * 연계메시지를 등록한다.
	 * @param cntcMessage
	 */
	void insertCntcMessage(CntcMessage cntcMessage);

	/**
	 * 연계메시지 항목을 등록한다.
	 * @param cntcMessageItem
	 */
	void insertCntcMessageItem(CntcMessageItem cntcMessageItem);

	/**
	 * 연계메시지 상세항목을 조회한다.
	 * @param cntcMessage
	 * @return CntcMessage(연계메시지)
	 */
	CntcMessage selectCntcMessageDetail(CntcMessage cntcMessage);

	/**
	 * 연계메시지항목 상세항목을 조회한다.
	 * @param cntcMessageItem
	 * @return CntcMessageItem(연계메시지항목)
	 */
	CntcMessageItem selectCntcMessageItemDetail(CntcMessageItem cntcMessageItem);

	/**
	 * 연계메시지 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계메시지 목록)
	 */
	List<EgovMap> selectCntcMessageList(CntcMessageVO searchVO);

	/**
	 * 연계메시지 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계메시지 총 개수)
	 */
	int selectCntcMessageListTotCnt(CntcMessageVO searchVO);

	/**
	 * 연계메시지항목 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계메시지항목 목록)
	 */
	List<EgovMap> selectCntcMessageItemList(CntcMessageItemVO searchVO);

	/**
	 * 연계메시지항목 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계메시지항목 총 개수)
	 */
	int selectCntcMessageItemListTotCnt(CntcMessageItemVO searchVO);

	/**
	 * 연계메시지를 수정한다.
	 * @param cntcMessage
	 */
	void updateCntcMessage(CntcMessage cntcMessage);

	/**
	 * 연계메시지 항목을 수정한다.
	 * @param cntcMessageItem
	 */
	void updateCntcMessageItem(CntcMessageItem cntcMessageItem);

}
