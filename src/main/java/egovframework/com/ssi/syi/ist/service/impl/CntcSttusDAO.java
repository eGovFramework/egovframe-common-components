package egovframework.com.ssi.syi.ist.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.ssi.syi.ist.service.CntcSttus;
import egovframework.com.ssi.syi.ist.service.CntcSttusVO;

/**
 *
 * 연계현황에 대한 데이터 접근 클래스를 정의한다
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
@Repository("CntcSttusDAO")
public class CntcSttusDAO extends EgovComAbstractDAO {


	/**
	 * 연계현황 상세항목을 조회한다.
	 * @param CntcSttus
	 * @return CntcSttus(연계현황)
	 */
	public CntcSttus selectCntcSttusDetail(CntcSttus CntcSttus) throws Exception {
		return (CntcSttus) selectOne("CntcSttusDAO.selectCntcSttusDetail", CntcSttus);
	}


    /**
	 * 연계현황 목록을 조회한다.
     * @param searchVO
     * @return List(연계현황 목록)
     * @throws Exception
     */
    public List<EgovMap> selectCntcSttusList(CntcSttusVO searchVO) throws Exception {
        return selectList("CntcSttusDAO.selectCntcSttusList", searchVO);
    }

    /**
	 * 연계현황 총 개수를 조회한다.
     * @param searchVO
     * @return int(연계현황 총 개수)
     */
    public int selectCntcSttusListTotCnt(CntcSttusVO searchVO) throws Exception {
        return (Integer)selectOne("CntcSttusDAO.selectCntcSttusListTotCnt", searchVO);
    }

}
