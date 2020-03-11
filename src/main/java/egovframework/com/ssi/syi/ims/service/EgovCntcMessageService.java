package egovframework.com.ssi.syi.ims.service;

import java.util.List;

/**
 *
 * 연계메시지에 관한 서비스 인터페이스 클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public interface EgovCntcMessageService {

	/**
	 * 연계메시지를 삭제한다.
	 * @param cntcMessage
	 * @throws Exception
	 */
	void deleteCntcMessage(CntcMessage cntcMessage) throws Exception;

	/**
	 * 연계메시지 항목을 삭제한다.
	 * @param cntcMessage
	 * @throws Exception
	 */
	void deleteCntcMessageItem(CntcMessageItem cntcMessageItem) throws Exception;

	/**
	 * 연계메시지를 등록한다.
	 * @param cntcMessage
	 * @throws Exception
	 */
	void insertCntcMessage(CntcMessage cntcMessage) throws Exception;

	/**
	 * 연계메시지 항목을 등록한다.
	 * @param cntcMessage
	 * @throws Exception
	 */
	void insertCntcMessageItem(CntcMessageItem cntcMessageItem) throws Exception;

	/**
	 * 연계메시지 상세항목을 조회한다.
	 * @param cntcMessage
	 * @return CntcMessage(연계메시지)
	 * @throws Exception
	 */
	CntcMessage selectCntcMessageDetail(CntcMessage cntcMessage) throws Exception;

	/**
	 * 연계메시지항목 상세항목을 조회한다.
	 * @param cntcMessage
	 * @return CntcMessage(연계메시지)
	 * @throws Exception
	 */
	CntcMessageItem selectCntcMessageItemDetail(CntcMessageItem cntcMessageItem) throws Exception;

	/**
	 * 연계메시지 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계메시지 목록)
	 * @throws Exception
	 */
	List<?> selectCntcMessageList(CntcMessageVO searchVO) throws Exception;

    /**
	 * 연계메시지 총 갯수를 조회한다.
     * @param searchVO
     * @return int(연계메시지 총 갯수)
     */
    int selectCntcMessageListTotCnt(CntcMessageVO searchVO) throws Exception;

	/**
	 * 연계메시지항목 목록을 조회한다.
	 * @param searchVO
	 * @return List(연계메시지 목록)
	 * @throws Exception
	 */
	List<?> selectCntcMessageItemList(CntcMessageItemVO searchVO) throws Exception;

    /**
	 * 연계메시지항목 총 갯수를 조회한다.
     * @param searchVO
     * @return int(연계메시지 총 갯수)
     */
    int selectCntcMessageItemListTotCnt(CntcMessageItemVO searchVO) throws Exception;

	/**
	 * 연계메시지를 수정한다.
	 * @param cntcMessage
	 * @throws Exception
	 */
	void updateCntcMessage(CntcMessage cntcMessage) throws Exception;

	/**
	 * 연계메시지 항목을 수정한다.
	 * @param cntcMessage
	 * @throws Exception
	 */
	void updateCntcMessageItem(CntcMessageItem cntcMessageItem) throws Exception;
}
