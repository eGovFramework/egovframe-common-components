package egovframework.com.uss.ion.ans.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.ans.service.AnnvrsryManage;
import egovframework.com.uss.ion.ans.service.AnnvrsryManageVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 기념일관리에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 기념일관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 기념일관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Repository("annvrsryManageDAO")
public class AnnvrsryManageDAO extends EgovComAbstractDAO {

	/**
	 * 기념일관리정보를 관리하기 위해 등록된 기념일관리 목록을 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return List - 기념일관리 목록
	 */	
	public List<AnnvrsryManageVO> selectAnnvrsryManageList(AnnvrsryManageVO annvrsryManageVO) throws Exception {
		return selectList("annvrsryManageDAO.selectAnnvrsryManageList", annvrsryManageVO);
	}

    /**
	 * 기념일관리목록 총 갯수를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectAnnvrsryManageListTotCnt(AnnvrsryManageVO annvrsryManageVO) throws Exception {
        return (Integer)selectOne("annvrsryManageDAO.selectAnnvrsryManageListTotCnt", annvrsryManageVO);
    }

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return AnnvrsryManageVO - 기념일관리 VO
	 */
	public AnnvrsryManageVO selectAnnvrsryManage(AnnvrsryManageVO annvrsryManageVO)  throws Exception {
		return (AnnvrsryManageVO) selectOne("annvrsryManageDAO.selectAnnvrsryManage", annvrsryManageVO);
	}

	/**
	 * 기념일관리정보를 신규로 등록한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	public void insertAnnvrsryManage(AnnvrsryManage annvrsryManage) throws Exception {
		insert("annvrsryManageDAO.insertAnnvrsryManage", annvrsryManage);
	}

	/**
	 * 기 등록된 기념일관리정보를 수정한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	public void updateAnnvrsryManage(AnnvrsryManage annvrsryManage) throws Exception {
		update("annvrsryManageDAO.updateAnnvrsryManage", annvrsryManage);
	}

	/**
	 * 기 등록된 기념일관리정보를 삭제한다.
	 * @param annvrsryManage - 기념일관리 model
	 */
	public void deleteAnnvrsryManage(AnnvrsryManage annvrsryManage) throws Exception {
        delete("annvrsryManageDAO.deleteAnnvrsryManage",annvrsryManage);
	}

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return AnnvrsryManageVO - 기념일관리 VO
	 */	
	public List<AnnvrsryManageVO> selectAnnvrsryGdcc(AnnvrsryManageVO annvrsryManageVO)  throws Exception {
		return selectList("annvrsryManageDAO.selectAnnvrsryGdcc", annvrsryManageVO);
	}

    /**
	 * 기념일관리 등록시 중복여부를 조회한다.
	 * @param annvrsryManage - 기념일관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectAnnvrsryManageDplctAt(AnnvrsryManage annvrsryManage) throws Exception {
        return (Integer)selectOne("annvrsryManageDAO.selectAnnvrsryManageDplctAt", annvrsryManage);
    }

	/**
	 * 등록된 기념일관리의 상세정보를 조회한다.
	 * @param annvrsryManageVO - 기념일관리 VO
	 * @return AnnvrsryManageVO - 기념일관리 VO
	 */
	public AnnvrsryManageVO selectAnnvrsryManageBnde(AnnvrsryManageVO annvrsryManageVO)  throws Exception {
		return (AnnvrsryManageVO) selectOne("annvrsryManageDAO.selectAnnvrsryManageBnde", annvrsryManageVO);
	}

}
