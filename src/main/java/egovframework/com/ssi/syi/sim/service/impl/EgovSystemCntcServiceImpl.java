package egovframework.com.ssi.syi.sim.service.impl;

import java.util.List;

import egovframework.com.ssi.syi.sim.service.EgovSystemCntcService;
import egovframework.com.ssi.syi.sim.service.SystemCntc;
import egovframework.com.ssi.syi.sim.service.SystemCntcVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



/**
 *
 * 시스템연계에 대한 서비스 구현클래스를 정의한다.
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
@Service("SystemCntcService")
public class EgovSystemCntcServiceImpl extends EgovAbstractServiceImpl implements  EgovSystemCntcService {

    @Resource(name="SystemCntcDAO")
    private SystemCntcDAO systemCntcDAO;

    /**
	 * 시스템연계를 삭제한다.
	 */
	 @Override
	public void deleteSystemCntc(SystemCntc systemCntc) throws Exception {
    	systemCntcDAO.deleteSystemCntc(systemCntc);
	 }

	/**
	 * 시스템연계를 등록한다.
	 */
    @Override
	public void insertSystemCntc(SystemCntc systemCntc) throws Exception {
    	systemCntcDAO.insertSystemCntc(systemCntc);
	}

	/**
	 * 시스템연계 상세항목을 조회한다.
	 */
	@Override
	public SystemCntc selectSystemCntcDetail(SystemCntc systemCntc) throws Exception {
    	SystemCntc ret = systemCntcDAO.selectSystemCntcDetail(systemCntc);
    	return ret;
	}

	/**
	 * 시스템연계 승인/승인취소한다.
	 */
	@Override
	public void confirmSystemCntc(SystemCntc systemCntc) throws Exception {
        systemCntcDAO.confirmSystemCntc(systemCntc);
	}

	/**
	 * 시스템연계 목록을 조회한다.
	 */
	@Override
	public List<?> selectSystemCntcList(SystemCntcVO searchVO) throws Exception {
        return systemCntcDAO.selectSystemCntcList(searchVO);
	}

	/**
	 * 시스템연계 총 갯수를 조회한다.
	 */
	@Override
	public int selectSystemCntcListTotCnt(SystemCntcVO searchVO) throws Exception {
        return systemCntcDAO.selectSystemCntcListTotCnt(searchVO);
	}

	/**
	 * 시스템연계를 수정한다.
	 */
	@Override
	public void updateSystemCntc(SystemCntc systemCntc) throws Exception {
        systemCntcDAO.updateSystemCntc(systemCntc);
	}
}
