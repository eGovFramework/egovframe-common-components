package egovframework.com.uss.ion.ctn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.ctn.service.CtsnnManage;
import egovframework.com.uss.ion.ctn.service.CtsnnManageVO;

/**
 * 개요
 * - 경조관리에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 경조관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 경조관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Repository("ctsnnManageDAO")
public class CtsnnManageDAO extends EgovComAbstractDAO {

	/**
	 * 경조관리정보를 관리하기 위해 등록된 경조관리 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return List - 경조관리 목록
	 */	
	public List<CtsnnManageVO> selectCtsnnManageList(CtsnnManageVO ctsnnManageVO) throws Exception {
		return selectList("ctsnnManageDAO.selectCtsnnManageList", ctsnnManageVO);
	}

    /**
	 * 경조관리목록 총 개수를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectCtsnnManageListTotCnt(CtsnnManageVO ctsnnManageVO) throws Exception {
        return (Integer)selectOne("ctsnnManageDAO.selectCtsnnManageListTotCnt", ctsnnManageVO);
    }

	/**
	 * 등록된 경조관리의 상세정보를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return CtsnnManageVO - 경조관리 VO
	 */
	public CtsnnManageVO selectCtsnnManage(CtsnnManageVO ctsnnManageVO)  throws Exception {
		return (CtsnnManageVO) selectOne("ctsnnManageDAO.selectCtsnnManage", ctsnnManageVO);
	}

	/**
	 * 경조관리정보를 신규로 등록한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void insertCtsnnManage(CtsnnManage ctsnnManage) throws Exception {
		insert("ctsnnManageDAO.insertCtsnnManage", ctsnnManage);
	}

	/**
	 * 기 등록된 경조관리정보를 수정한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void updtCtsnnManage(CtsnnManage ctsnnManage) throws Exception {
		update("ctsnnManageDAO.updateCtsnnManage", ctsnnManage);
	}

	/**
	 * 기 등록된 경조관리정보를 삭제한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void deleteCtsnnManage(CtsnnManage ctsnnManage) throws Exception {
        delete("ctsnnManageDAO.deleteCtsnnManage",ctsnnManage);
	}

    /*** 승인처리관련 ***/
	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return List - 경조관리 목록
	 */	
	public List<CtsnnManageVO> selectCtsnnManageConfmList(CtsnnManageVO ctsnnManageVO) throws Exception {
		return selectList("ctsnnManageDAO.selectCtsnnManageConfmList", ctsnnManageVO);
	}

    /**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록 총 개수를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectCtsnnManageConfmListTotCnt(CtsnnManageVO ctsnnManageVO) throws Exception {
        return (Integer)selectOne("ctsnnManageDAO.selectCtsnnManageConfmListTotCnt", ctsnnManageVO);
    }
	
	/**
	 *경조정보를 승인처리 한다.
	 * @param ctsnnManage - 경조관리 model
	 */
	public void updtCtsnnManageConfm(CtsnnManage ctsnnManage) throws Exception {
		update("ctsnnManageDAO.updtCtsnnManageConfm", ctsnnManage);
	}
}
