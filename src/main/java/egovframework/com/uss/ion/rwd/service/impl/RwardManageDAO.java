

package egovframework.com.uss.ion.rwd.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractMapper;
import egovframework.com.uss.ion.rwd.service.RwardManage;
import egovframework.com.uss.ion.rwd.service.RwardManageVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 포상관리에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 포상관리에 대한 등록, 수정, 삭제, 조회, 승인처리 기능을 제공한다.
 * - 포상관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Repository("rwardManageDAO")
public class RwardManageDAO extends EgovComAbstractMapper {

	/**
	 * 포상관리정보를 관리하기 위해 등록된 포상관리 목록을 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return List - 포상관리 목록
	 */
	public List<RwardManageVO> selectRwardManageList(RwardManageVO rwardManageVO) throws Exception {
		return selectList("rwardManageDAO.selectRwardManageList", rwardManageVO);
	}

    /**
	 * 포상관리목록 총 갯수를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectRwardManageListTotCnt(RwardManageVO rwardManageVO) throws Exception {
        return (Integer)selectOne("rwardManageDAO.selectRwardManageListTotCnt", rwardManageVO);
    }

	/**
	 * 등록된 포상관리의 상세정보를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return RwardManageVO - 포상관리 VO
	 */
	public RwardManageVO selectRwardManage(RwardManageVO rwardManageVO)  throws Exception {
		return (RwardManageVO) selectOne("rwardManageDAO.selectRwardManage", rwardManageVO);
	}

	/**
	 * 포상관리정보를 신규로 등록한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void insertRwardManage(RwardManage rwardManage) throws Exception {
		insert("rwardManageDAO.insertRwardManage", rwardManage);
	}

	/**
	 * 기 등록된 포상관리정보를 수정한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void updtRwardManage(RwardManage rwardManage) throws Exception {
		update("rwardManageDAO.updtRwardManage", rwardManage);
	}

	/**
	 * 기 등록된 포상관리정보를 삭제한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void deleteRwardManage(RwardManage rwardManage) throws Exception {
        delete("rwardManageDAO.deleteRwardManage",rwardManage);
	}

    /*** 승인처리관련 ***/
	/**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록을 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return List - 포상관리 목록
	 */
	public List<RwardManageVO> selectRwardManageConfmList(RwardManageVO rwardManageVO) throws Exception {
		return selectList("rwardManageDAO.selectRwardManageConfmList", rwardManageVO);
	}

    /**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록 총 갯수를 조회한다.
	 * @param rwardManageVO - 포상관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectRwardManageConfmListTotCnt(RwardManageVO rwardManageVO) throws Exception {
        return (Integer)selectOne("rwardManageDAO.selectRwardManageConfmListTotCnt", rwardManageVO);
    }
	
	/**
	 *포상정보를 승인/반려처리 한다.
	 * @param rwardManage - 포상관리 model
	 */
	public void updtRwardManageConfm(RwardManage rwardManage) throws Exception {
		update("rwardManageDAO.updtRwardManageConfm", rwardManage);
	}
}
