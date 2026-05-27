package egovframework.com.ssi.syi.ims.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.ssi.syi.ims.service.CntcMessage;
import egovframework.com.ssi.syi.ims.service.CntcMessageItem;
import egovframework.com.ssi.syi.ims.service.CntcMessageItemVO;
import egovframework.com.ssi.syi.ims.service.CntcMessageVO;

/**
 * 연계메시지에 대한 데이터 접근 클래스를 정의한다
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
@Repository("CntcMessageDAO")
public class CntcMessageDAO {

	private final CntcMessageMapper cntcMessageMapper;

	public CntcMessageDAO(CntcMessageMapper cntcMessageMapper) {
		this.cntcMessageMapper = cntcMessageMapper;
	}

	/**
	 * 연계메시지를 삭제한다.
	 * @param cntcMessage
	 * @throws Exception
	 */
	public void deleteCntcMessage(CntcMessage cntcMessage) throws Exception {
		cntcMessageMapper.deleteCntcMessage(cntcMessage);
	}

	/**
	 * 연계메시지 항목을 삭제한다.
	 * @param cntcMessageItem
	 * @throws Exception
	 */
	public void deleteCntcMessageItem(CntcMessageItem cntcMessageItem) throws Exception {
		cntcMessageMapper.deleteCntcMessageItem(cntcMessageItem);
	}

	/**
	 * 연계메시지를 등록한다.
	 * @param cntcMessage
	 * @throws Exception
	 */
	public void insertCntcMessage(CntcMessage cntcMessage) throws Exception {
		cntcMessageMapper.insertCntcMessage(cntcMessage);
	}

	/**
	 * 연계메시지 항목을 등록한다.
	 * @param cntcMessageItem
	 * @throws Exception
	 */
	public void insertCntcMessageItem(CntcMessageItem cntcMessageItem) throws Exception {
		cntcMessageMapper.insertCntcMessageItem(cntcMessageItem);
	}

	/**
	 * 연계메시지 상세항목을 조회한다.
	 * @param cntcMessage
	 * @return CntcMessage(연계메시지)
	 */
	public CntcMessage selectCntcMessageDetail(CntcMessage cntcMessage) throws Exception {
		return cntcMessageMapper.selectCntcMessageDetail(cntcMessage);
	}

	/**
	 * 연계메시지항목 상세항목을 조회한다.
	 * @param cntcMessageItem
	 * @return CntcMessageItem(연계메시지항목)
	 */
	public CntcMessageItem selectCntcMessageItemDetail(CntcMessageItem cntcMessageItem) throws Exception {
		return cntcMessageMapper.selectCntcMessageItemDetail(cntcMessageItem);
	}

	/**
	 * 연계메시지 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계메시지 목록)
	 * @throws Exception
	 */
	public List<EgovMap> selectCntcMessageList(CntcMessageVO searchVO) throws Exception {
		return cntcMessageMapper.selectCntcMessageList(searchVO);
	}

	/**
	 * 연계메시지 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계메시지 총 개수)
	 */
	public int selectCntcMessageListTotCnt(CntcMessageVO searchVO) throws Exception {
		return cntcMessageMapper.selectCntcMessageListTotCnt(searchVO);
	}

	/**
	 * 연계메시지항목 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계메시지항목 목록)
	 * @throws Exception
	 */
	public List<EgovMap> selectCntcMessageItemList(CntcMessageItemVO searchVO) throws Exception {
		return cntcMessageMapper.selectCntcMessageItemList(searchVO);
	}

	/**
	 * 연계메시지항목 총 개수를 조회한다.
	 * @param searchVO
	 * @return int(연계메시지항목 총 개수)
	 */
	public int selectCntcMessageItemListTotCnt(CntcMessageItemVO searchVO) throws Exception {
		return cntcMessageMapper.selectCntcMessageItemListTotCnt(searchVO);
	}

	/**
	 * 연계메시지를 수정한다.
	 * @param cntcMessage
	 * @throws Exception
	 */
	public void updateCntcMessage(CntcMessage cntcMessage) throws Exception {
		cntcMessageMapper.updateCntcMessage(cntcMessage);
	}

	/**
	 * 연계메시지 항목을 수정한다.
	 * @param cntcMessageItem
	 * @throws Exception
	 */
	public void updateCntcMessageItem(CntcMessageItem cntcMessageItem) throws Exception {
		cntcMessageMapper.updateCntcMessageItem(cntcMessageItem);
	}

}
