package egovframework.com.ssi.syi.ist.service.impl;

import java.util.List;

import egovframework.com.ssi.syi.ist.service.CntcSttus;
import egovframework.com.ssi.syi.ist.service.CntcSttusVO;
import egovframework.com.ssi.syi.ist.service.EgovCntcSttusService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



/**
 *
 * 연계현황에 대한 서비스 구현클래스를 정의한다.
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
@Service("CntcSttusService")
public class EgovCntcSttusServiceImpl extends EgovAbstractServiceImpl implements  EgovCntcSttusService {

    @Resource(name="CntcSttusDAO")
    private CntcSttusDAO cntcSttusDAO;

	/**
	 * 연계현황 상세항목을 조회한다.
	 */
	@Override
	public CntcSttus selectCntcSttusDetail(CntcSttus CntcSttus) throws Exception {
    	CntcSttus ret = cntcSttusDAO.selectCntcSttusDetail(CntcSttus);
    	return ret;
	}

	/**
	 * 연계현황 목록을 조회한다.
	 */
	@Override
	public List<?> selectCntcSttusList(CntcSttusVO searchVO) throws Exception {
        return cntcSttusDAO.selectCntcSttusList(searchVO);
	}

	/**
	 * 연계현황 총 갯수를 조회한다.
	 */
	@Override
	public int selectCntcSttusListTotCnt(CntcSttusVO searchVO) throws Exception {
        return cntcSttusDAO.selectCntcSttusListTotCnt(searchVO);
	}

}
