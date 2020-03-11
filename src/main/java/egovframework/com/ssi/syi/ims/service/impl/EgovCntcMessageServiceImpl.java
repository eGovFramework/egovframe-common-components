package egovframework.com.ssi.syi.ims.service.impl;

import java.util.List;

import egovframework.com.ssi.syi.ims.service.CntcMessage;
import egovframework.com.ssi.syi.ims.service.CntcMessageItem;
import egovframework.com.ssi.syi.ims.service.CntcMessageItemVO;
import egovframework.com.ssi.syi.ims.service.CntcMessageVO;
import egovframework.com.ssi.syi.ims.service.EgovCntcMessageService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 *
 * 연계메시지에 대한 서비스 구현클래스를 정의한다.
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
@Service("CntcMessageService")
public class EgovCntcMessageServiceImpl extends EgovAbstractServiceImpl implements EgovCntcMessageService {

	@Resource(name = "CntcMessageDAO")
	private CntcMessageDAO cntcMessageDAO;

	/**
	 * 연계메시지를 삭제한다.
	 */
	@Override
	public void deleteCntcMessage(CntcMessage cntcMessage) throws Exception {
		cntcMessageDAO.deleteCntcMessage(cntcMessage);
	}

	/**
	 * 연계메시지 항목을 삭제한다.
	 */
	@Override
	public void deleteCntcMessageItem(CntcMessageItem cntcMessageItem) throws Exception {
		cntcMessageDAO.deleteCntcMessageItem(cntcMessageItem);
	}

	/**
	 * 연계메시지를 등록한다.
	 */
	@Override
	public void insertCntcMessage(CntcMessage cntcMessage) throws Exception {
		cntcMessageDAO.insertCntcMessage(cntcMessage);
	}

	/**
	 * 연계메시지 항목을 등록한다.
	 */
	@Override
	public void insertCntcMessageItem(CntcMessageItem cntcMessageItem) throws Exception {
		cntcMessageDAO.insertCntcMessageItem(cntcMessageItem);
	}

	/**
	 * 연계메시지 상세항목을 조회한다.
	 */
	@Override
	public CntcMessage selectCntcMessageDetail(CntcMessage cntcMessage) throws Exception {
		CntcMessage ret = cntcMessageDAO.selectCntcMessageDetail(cntcMessage);
		return ret;
	}

	/**
	 * 연계메시지항목 상세항목을 조회한다.
	 */
	@Override
	public CntcMessageItem selectCntcMessageItemDetail(CntcMessageItem cntcMessageItem) throws Exception {
		CntcMessageItem ret = cntcMessageDAO.selectCntcMessageItemDetail(cntcMessageItem);
		return ret;
	}

	/**
	 * 연계메시지 목록을 조회한다.
	 */
	@Override
	public List<?> selectCntcMessageList(CntcMessageVO searchVO) throws Exception {
		return cntcMessageDAO.selectCntcMessageList(searchVO);
	}

	/**
	 * 연계메시지 총 갯수를 조회한다.
	 */
	@Override
	public int selectCntcMessageListTotCnt(CntcMessageVO searchVO) throws Exception {
		return cntcMessageDAO.selectCntcMessageListTotCnt(searchVO);
	}

	/**
	 * 연계메시지항목 목록을 조회한다.
	 */
	@Override
	public List<?> selectCntcMessageItemList(CntcMessageItemVO searchVO) throws Exception {
		return cntcMessageDAO.selectCntcMessageItemList(searchVO);
	}

	/**
	 * 연계메시지항목 총 갯수를 조회한다.
	 */
	@Override
	public int selectCntcMessageItemListTotCnt(CntcMessageItemVO searchVO) throws Exception {
		return cntcMessageDAO.selectCntcMessageItemListTotCnt(searchVO);
	}

	/**
	 * 연계메시지를 수정한다.
	 */
	@Override
	public void updateCntcMessage(CntcMessage cntcMessage) throws Exception {
		cntcMessageDAO.updateCntcMessage(cntcMessage);
	}

	/**
	 * 연계메시지 항목을 수정한다.
	 */
	@Override
	public void updateCntcMessageItem(CntcMessageItem cntcMessageItem) throws Exception {
		cntcMessageDAO.updateCntcMessageItem(cntcMessageItem);
	}

}
