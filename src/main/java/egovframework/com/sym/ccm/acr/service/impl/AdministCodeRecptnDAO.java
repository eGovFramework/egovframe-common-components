package egovframework.com.sym.ccm.acr.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptn;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptnVO;

/**
 *
 * 법정동코드에 대한 데이터 접근 클래스를 정의한다
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
 *   2009.04.01  이중호         최초 생성
 *   2024.10.29	 권태성			법정동코드 저장 시 NullPointerException 수정(insertAdministCode())
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Repository("AdministCodeRecptnDAO")
public class AdministCodeRecptnDAO extends EgovComAbstractDAO {

	/**
	 * 법정동코드수신을 처리한다.
	 * @param administCode
	 * @throws Exception
	 */
	public void insertAdministCodeRecptn(AdministCodeRecptn administCodeRecptn) throws Exception {
        insert("AdministCodeRecptnDAO.insertAdministCodeRecptn", administCodeRecptn);
	}

	/**
	 * 법정동코드를 등록한다.
	 * @param administCode
	 * @throws Exception
	 */
	public void insertAdministCode(AdministCodeRecptn administCodeRecptn) throws Exception {
		AdministCodeRecptn beforeData = (AdministCodeRecptn) selectOne("AdministCodeRecptnDAO.selectAdministCodeDetail", administCodeRecptn);

		if (beforeData != null
				&& beforeData.getAdministZoneCode().equals(administCodeRecptn.getAdministZoneCode())
				&& beforeData.getAdministZoneSe().equals(administCodeRecptn.getAdministZoneSe())
		) {
			// 기등록 자료
			administCodeRecptn.setProcessSe("10");
		} else {
			int rtnValue = update("AdministCodeRecptnDAO.insertAdministCode", administCodeRecptn);
	        if (rtnValue != 1) {
	        	// 등록 오류
	        	administCodeRecptn.setProcessSe("11");
	        }
        }
    	update("AdministCodeRecptnDAO.updateAdministCodeRecptn", administCodeRecptn);
	}

	/**
	 * 법정동코드를 수정한다.
	 * @param administCode
	 * @throws Exception
	 */
	public void updateAdministCode(AdministCodeRecptn administCodeRecptn) throws Exception {
		int rtnValue = update("AdministCodeRecptnDAO.updateAdministCode", administCodeRecptn);
        if (rtnValue != 1) {
        	// 변경 오류
        	administCodeRecptn.setProcessSe("12");
        }
    	update("AdministCodeRecptnDAO.updateAdministCodeRecptn", administCodeRecptn);
	}

	/**
	 * 법정동코드를 삭제한다.
	 * @param administCode
	 * @throws Exception
	 */
	public void deleteAdministCode(AdministCodeRecptn administCodeRecptn) throws Exception {
		int rtnValue = update("AdministCodeRecptnDAO.deleteAdministCode", administCodeRecptn);
        if (rtnValue != 1) {
        	// 삭제 오류
        	administCodeRecptn.setProcessSe("13");
        }
    	update("AdministCodeRecptnDAO.updateAdministCodeRecptn", administCodeRecptn);
	}

	/**
	 * 법정동코드 상세내역을 조회한다.
	 * @param administCode
	 * @return AdministCode(법정동코드)
	 */
	public AdministCodeRecptn selectAdministCodeDetail(AdministCodeRecptn administCodeRecptn) throws Exception {
		return (AdministCodeRecptn) selectOne("AdministCodeRecptnDAO.selectAdministCodeDetail", administCodeRecptn);
	}


    /**
	 * 법정동코드수신 목록을 조회한다.
     * @param searchVO
     * @return List(법정동코드 목록)
     * @throws Exception
     */
    public List<EgovMap> selectAdministCodeRecptnList(AdministCodeRecptnVO searchVO) throws Exception {
        return selectList("AdministCodeRecptnDAO.selectAdministCodeRecptnList", searchVO);
    }

    /**
	 * 법정동코드수신 총 개수를 조회한다.
     * @param searchVO
     * @return int(법정동코드 총 개수)
     */
    public int selectAdministCodeRecptnListTotCnt(AdministCodeRecptnVO searchVO) throws Exception {
        return (Integer)selectOne("AdministCodeRecptnDAO.selectAdministCodeRecptnListTotCnt", searchVO);
    }

    /**
	 * 법정동코드 목록을 조회한다.
     * @param searchVO
     * @return List(법정동코드 목록)
     * @throws Exception
     */
    public List<EgovMap> selectAdministCodeList(AdministCodeRecptnVO searchVO) throws Exception {
        return selectList("AdministCodeRecptnDAO.selectAdministCodeList", searchVO);
    }

    /**
	 * 법정동코드 총 개수를 조회한다.
     * @param searchVO
     * @return int(법정동코드 총 개수)
     */
    public int selectAdministCodeListTotCnt(AdministCodeRecptnVO searchVO) throws Exception {
        return (Integer)selectOne("AdministCodeRecptnDAO.selectAdministCodeListTotCnt", searchVO);
    }
}
