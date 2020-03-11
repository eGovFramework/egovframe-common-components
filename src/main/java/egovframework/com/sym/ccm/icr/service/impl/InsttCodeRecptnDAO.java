package egovframework.com.sym.ccm.icr.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.ccm.icr.service.InsttCodeRecptn;
import egovframework.com.sym.ccm.icr.service.InsttCodeRecptnVO;

import org.springframework.stereotype.Repository;

/**
 *
 * 기관코드에 대한 데이터 접근 클래스를 정의한다
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
 *   2011.09.05  서준식          beforeData에 대한 null체크 추가
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Repository("InsttCodeRecptnDAO")
public class InsttCodeRecptnDAO extends EgovComAbstractDAO {

	/**
	 * 기관코드수신을 처리한다.
	 * @param insttCode
	 * @throws Exception
	 */
	public void insertInsttCodeRecptn(InsttCodeRecptn insttCodeRecptn) throws Exception {
        insert("InsttCodeRecptnDAO.insertInsttCodeRecptn", insttCodeRecptn);
	}

	/**
	 * 기관코드를 등록한다.
	 * @param insttCode
	 * @throws Exception
	 */
	public void insertInsttCode(InsttCodeRecptn insttCodeRecptn) throws Exception {
		InsttCodeRecptn beforeData = (InsttCodeRecptn) selectOne("InsttCodeRecptnDAO.selectInsttCodeDetail", insttCodeRecptn);

		if (beforeData != null && beforeData.getInsttCode().equals(insttCodeRecptn.getInsttCode())) {//2011.09.05
			// 기등록 자료
			insttCodeRecptn.setProcessSe("10");
		} else {
			int rtnValue = update("InsttCodeRecptnDAO.insertInsttCode", insttCodeRecptn);
	        if (rtnValue != 1) {
	        	// 등록 오류
	        	insttCodeRecptn.setProcessSe("11");
	        }
        }
    	update("InsttCodeRecptnDAO.updateInsttCodeRecptn", insttCodeRecptn);
	}

	/**
	 * 기관코드를 수정한다.
	 * @param insttCode
	 * @throws Exception
	 */
	public void updateInsttCode(InsttCodeRecptn insttCodeRecptn) throws Exception {
		int rtnValue = update("InsttCodeRecptnDAO.updateInsttCode", insttCodeRecptn);
        if (rtnValue != 1) {
        	// 변경 오류
        	insttCodeRecptn.setProcessSe("12");
        }
    	update("InsttCodeRecptnDAO.updateInsttCodeRecptn", insttCodeRecptn);
	}

	/**
	 * 기관코드를 삭제한다.
	 * @param insttCode
	 * @throws Exception
	 */
	public void deleteInsttCode(InsttCodeRecptn insttCodeRecptn) throws Exception {
		int rtnValue = update("InsttCodeRecptnDAO.deleteInsttCode", insttCodeRecptn);
        if (rtnValue != 1) {
        	// 삭제 오류
        	insttCodeRecptn.setProcessSe("13");
        }
    	update("InsttCodeRecptnDAO.updateInsttCodeRecptn", insttCodeRecptn);
	}

	/**
	 * 기관코드 상세내역을 조회한다.
	 * @param insttCode
	 * @return InsttCode(기관코드)
	 */
	public InsttCodeRecptn selectInsttCodeDetail(InsttCodeRecptn insttCodeRecptn) throws Exception {
		return (InsttCodeRecptn) selectOne("InsttCodeRecptnDAO.selectInsttCodeDetail", insttCodeRecptn);
	}


    /**
	 * 기관코드수신 목록을 조회한다.
     * @param searchVO
     * @return List(기관코드 목록)
     * @throws Exception
     */
    public List<?> selectInsttCodeRecptnList(InsttCodeRecptnVO searchVO) throws Exception {
        return selectList("InsttCodeRecptnDAO.selectInsttCodeRecptnList", searchVO);
    }

    /**
	 * 기관코드수신 총 갯수를 조회한다.
     * @param searchVO
     * @return int(기관코드 총 갯수)
     */
    public int selectInsttCodeRecptnListTotCnt(InsttCodeRecptnVO searchVO) throws Exception {
        return (Integer)selectOne("InsttCodeRecptnDAO.selectInsttCodeRecptnListTotCnt", searchVO);
    }

    /**
	 * 기관코드 목록을 조회한다.
     * @param searchVO
     * @return List(기관코드 목록)
     * @throws Exception
     */
    public List<?> selectInsttCodeList(InsttCodeRecptnVO searchVO) throws Exception {
        return selectList("InsttCodeRecptnDAO.selectInsttCodeList", searchVO);
    }

    /**
	 * 기관코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(기관코드 총 갯수)
     */
    public int selectInsttCodeListTotCnt(InsttCodeRecptnVO searchVO) throws Exception {
        return (Integer)selectOne("InsttCodeRecptnDAO.selectInsttCodeListTotCnt", searchVO);
    }
}
