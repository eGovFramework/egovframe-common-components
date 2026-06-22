package egovframework.com.ssi.syi.sim.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.ssi.syi.sim.service.SystemCntc;
import egovframework.com.ssi.syi.sim.service.SystemCntcVO;

/**
 *
 * 시스템연계에 대한 데이터 접근 클래스를 정의한다
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
 *   2026.06.16  이백행          [2026년 컨트리뷰션] 불필요한 예외(throws Exception) 제거
 *
 * Copyright (C) 2009 by MOPAS  All rights reserved.
 * </pre>
 */
@Repository("SystemCntcDAO")
public class SystemCntcDAO extends EgovComAbstractDAO {

	/**
	 * 시스템연계를 삭제한다.
	 * @param systemCntc
	 */
	public void deleteSystemCntc(SystemCntc systemCntc) {
        delete("SystemCntcDAO.deleteSystemCntc", systemCntc);
	}

	/**
	 * 시스템연계를 등록한다.
	 * @param systemCntc
	 */
	public void insertSystemCntc(SystemCntc systemCntc) {
        insert("SystemCntcDAO.insertSystemCntc", systemCntc);
	}

	/**
	 * 시스템연계 상세항목을 조회한다.
	 * @param systemCntc
	 * @return SystemCntc(시스템연계)
	 */
	public SystemCntc selectSystemCntcDetail(SystemCntc systemCntc) {
		return (SystemCntc) selectOne("SystemCntcDAO.selectSystemCntcDetail", systemCntc);
	}

	/**
	 * 시스템연계 승인/승인취소한다.
	 * @param systemCntc
	 */
	public void confirmSystemCntc(SystemCntc systemCntc) {
        update("SystemCntcDAO.confirmSystemCntc", systemCntc);
	}


    /**
	 * 시스템연계 목록을 조회한다.
     * @param searchVO
     * @return List(시스템연계 목록)
     */
    public List<EgovMap> selectSystemCntcList(SystemCntcVO searchVO) {
        return selectList("SystemCntcDAO.selectSystemCntcList", searchVO);
    }

    /**
	 * 시스템연계 총 개수를 조회한다.
     * @param searchVO
     * @return int(시스템연계 총 개수)
     */
    public int selectSystemCntcListTotCnt(SystemCntcVO searchVO) {
        return (Integer)selectOne("SystemCntcDAO.selectSystemCntcListTotCnt", searchVO);
    }

	/**
	 * 시스템연계를 수정한다.
	 * @param systemCntc
	 */
	public void updateSystemCntc(SystemCntc systemCntc) {
		update("SystemCntcDAO.updateSystemCntc", systemCntc);
	}

}
