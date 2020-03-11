package egovframework.com.ssi.syi.iis.service.impl;

import java.util.List;

import egovframework.com.ssi.syi.iis.service.CntcInstt;
import egovframework.com.ssi.syi.iis.service.CntcInsttVO;
import egovframework.com.ssi.syi.iis.service.CntcService;
import egovframework.com.ssi.syi.iis.service.CntcServiceVO;
import egovframework.com.ssi.syi.iis.service.CntcSystem;
import egovframework.com.ssi.syi.iis.service.CntcSystemVO;
import egovframework.com.ssi.syi.iis.service.EgovCntcInsttService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 *
 * 연계기관에 대한 서비스 구현클래스를 정의한다.
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
@Service("CntcInsttService")
public class EgovCntcInsttServiceImpl extends EgovAbstractServiceImpl implements  EgovCntcInsttService {


    @Resource(name="CntcInsttDAO")
    private CntcInsttDAO cntcInsttDAO;

    /**
	 * 연계기관을 삭제한다.
	 */
	 @Override
	public void deleteCntcInstt(CntcInstt cntcInstt) throws Exception {
    	cntcInsttDAO.deleteCntcInstt(cntcInstt);
	 }

    /**
	 * 연계시스템을 삭제한다.
	 */
	 @Override
	public void deleteCntcSystem(CntcSystem cntcSystem) throws Exception {
    	cntcInsttDAO.deleteCntcSystem(cntcSystem);
	 }

	/**
	 * 연계서비스를 삭제한다.
	 */
	 @Override
	public void deleteCntcService(CntcService cntcService) throws Exception {
    	cntcInsttDAO.deleteCntcService(cntcService);
	 }


	 /**
	 * 연계기관을 등록한다.
	 */
    @Override
	public void insertCntcInstt(CntcInstt cntcInstt) throws Exception {
    	cntcInsttDAO.insertCntcInstt(cntcInstt);
	}

	/**
	 * 연계시스템을 등록한다.
	 */
    @Override
	public void insertCntcSystem(CntcSystem cntcSystem) throws Exception {
    	cntcInsttDAO.insertCntcSystem(cntcSystem);
	}

	/**
	 * 연계서비스를 등록한다.
	 */
    @Override
	public void insertCntcService(CntcService cntcService) throws Exception {
    	cntcInsttDAO.insertCntcService(cntcService);
	}

    /**
	 * 연계기관 상세항목을 조회한다.
	 */
	@Override
	public CntcInstt selectCntcInsttDetail(CntcInstt cntcInstt) throws Exception {
    	CntcInstt ret = cntcInsttDAO.selectCntcInsttDetail(cntcInstt);
    	return ret;
	}

    /**
	 * 연계시스템 상세항목을 조회한다.
	 */
	@Override
	public CntcSystem selectCntcSystemDetail(CntcSystem cntcSystem) throws Exception {
		CntcSystem ret = cntcInsttDAO.selectCntcSystemDetail(cntcSystem);
    	return ret;
	}

    /**
	 * 연계서비스 상세항목을 조회한다.
	 */
	@Override
	public CntcService selectCntcServiceDetail(CntcService cntcService) throws Exception {
		CntcService ret = cntcInsttDAO.selectCntcServiceDetail(cntcService);
    	return ret;
	}

	/**
	 * 연계기관 목록을 조회한다.
	 */
	@Override
	public List<?> selectCntcInsttList(CntcInsttVO searchVO) throws Exception {
        return cntcInsttDAO.selectCntcInsttList(searchVO);
	}

	/**
	 * 연계기관 총 갯수를 조회한다.
	 */
	@Override
	public int selectCntcInsttListTotCnt(CntcInsttVO searchVO) throws Exception {
        return cntcInsttDAO.selectCntcInsttListTotCnt(searchVO);
	}

	/**
	 * 연계시스템 목록을 조회한다.
	 */
	@Override
	public List<?> selectCntcSystemList(CntcSystemVO searchVO) throws Exception {
        return cntcInsttDAO.selectCntcSystemList(searchVO);
	}

	/**
	 * 연계시스템 총 갯수를 조회한다.
	 */
	@Override
	public int selectCntcSystemListTotCnt(CntcSystemVO searchVO) throws Exception {
        return cntcInsttDAO.selectCntcSystemListTotCnt(searchVO);
	}

	/**
	 * 연계서비스 목록을 조회한다.
	 */
	@Override
	public List<?> selectCntcServiceList(CntcServiceVO searchVO) throws Exception {
        return cntcInsttDAO.selectCntcServiceList(searchVO);
	}

	/**
	 * 연계서비스 총 갯수를 조회한다.
	 */
	@Override
	public int selectCntcServiceListTotCnt(CntcServiceVO searchVO) throws Exception {
        return cntcInsttDAO.selectCntcServiceListTotCnt(searchVO);
	}

	/**
	 * 연계기관을 수정한다.
	 */
	@Override
	public void updateCntcInstt(CntcInstt cntcInstt) throws Exception {
        cntcInsttDAO.updateCntcInstt(cntcInstt);
	}

	/**
	 * 연계시스템을 수정한다.
	 */
	@Override
	public void updateCntcSystem(CntcSystem cntcSystem) throws Exception {
        cntcInsttDAO.updateCntcSystem(cntcSystem);
	}

	/**
	 * 연계서비스를 수정한다.
	 */
	@Override
	public void updateCntcService(CntcService cntcService) throws Exception {
        cntcInsttDAO.updateCntcService(cntcService);
	}

}
