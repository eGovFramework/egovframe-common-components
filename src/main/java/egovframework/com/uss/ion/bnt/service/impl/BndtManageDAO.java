package egovframework.com.uss.ion.bnt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.bnt.service.BndtCeckManage;
import egovframework.com.uss.ion.bnt.service.BndtCeckManageVO;
import egovframework.com.uss.ion.bnt.service.BndtDiary;
import egovframework.com.uss.ion.bnt.service.BndtDiaryVO;
import egovframework.com.uss.ion.bnt.service.BndtManage;
import egovframework.com.uss.ion.bnt.service.BndtManageVO;

/**
 * 개요
 * - 당직관리에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 당직관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 당직관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Repository("bndtManageDAO")
public class BndtManageDAO extends EgovComAbstractDAO {

	/**
	 * 당직관리정보를 관리하기 위해 등록된 당직관리 목록을 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return List - 당직관리 목록
	 */
	
	public List<BndtManageVO> selectBndtManageList(BndtManageVO bndtManageVO) throws Exception {
		return  selectList("bndtManageDAO.selectBndtManageList", bndtManageVO);
	}

    /**
	 * 당직관리목록 총 개수를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectBndtManageListTotCnt(BndtManageVO bndtManageVO) throws Exception {
        return (Integer)selectOne("bndtManageDAO.selectBndtManageListTotCnt", bndtManageVO);
    }

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return BndtManageVO - 당직관리 VO
	 */
	public BndtManageVO selectBndtManage(BndtManageVO bndtManageVO)  throws Exception {
		return (BndtManageVO) selectOne("bndtManageDAO.selectBndtManage", bndtManageVO);
	}

	/**
	 * 당직관리정보를 신규로 등록한다.
	 * @param bndtManage - 당직관리 model
	 */
	public void insertBndtManage(BndtManage bndtManage) throws Exception {
		insert("bndtManageDAO.insertBndtManage", bndtManage);
	}

	/**
	 * 기 등록된 당직관리정보를 수정한다.
	 * @param bndtManage - 당직관리 model
	 */
	public void updtBndtManage(BndtManage bndtManage) throws Exception {
		update("bndtManageDAO.updtBndtManage", bndtManage);
	}

	/**
	 * 기 등록된 당직관리정보를 삭제한다.
	 * @param bndtManage - 당직관리 model
	 */
	public void deleteBndtManage(BndtManage bndtManage) throws Exception {
        delete("bndtManageDAO.deleteBndtManage",bndtManage);
	}

    /**
	 * 당직일지 개수를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectBndtDiaryTotCnt(BndtManage bndtManage) throws Exception {
        return (Integer)selectOne("bndtManageDAO.selectBndtDiaryTotCnt", bndtManage);
    }
	
    /***** 당직 체크관리 *****/	

	/**
	 * 당직체크관리정보를 관리하기 위해 등록된 당직체크관리 목록을 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return List - 당직체크관리 목록
	 */
	public List<BndtCeckManageVO> selectBndtCeckManageList(BndtCeckManageVO bndtCeckManageVO) throws Exception {
		return selectList("bndtManageDAO.selectBndtCeckManageList", bndtCeckManageVO);
	}

    /**
	 * 당직체크관리목록 총 개수를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectBndtCeckManageListTotCnt(BndtCeckManageVO bndtCeckManageVO) throws Exception {
        return (Integer)selectOne("bndtManageDAO.selectBndtCeckManageListTotCnt", bndtCeckManageVO);
    }

	/**
	 * 등록된 당직체크관리의 상세정보를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return BndtCeckManageVO - 당직체크관리 VO
	 */
	public BndtCeckManageVO selectBndtCeckManage(BndtCeckManageVO bndtCeckManageVO)  throws Exception {
		return (BndtCeckManageVO) selectOne("bndtManageDAO.selectBndtCeckManage", bndtCeckManageVO);
	}

	/**
	 * 당직체크관리정보를 신규로 등록한다.
	 * @param bndtCeckManage - 당직체크관리 model
	 */
	public void insertBndtCeckManage(BndtCeckManage bndtCeckManage) throws Exception {
		insert("bndtManageDAO.insertBndtCeckManage", bndtCeckManage);
	}

	/**
	 * 기 등록된 당직체크관리정보를 수정한다.
	 * @param bndtCeckManage - 당직체크관리 model
	 */
	public void updtBndtCeckManage(BndtCeckManage bndtCeckManage) throws Exception {
		update("bndtManageDAO.updtBndtCeckManage", bndtCeckManage);
	}

	/**
	 * 기 등록된 당직체크관리정보를 삭제한다.
	 * @param bndtCeckManage - 당직체크관리 model
	 */
	public void deleteBndtCeckManage(BndtCeckManage bndtCeckManage) throws Exception {
        delete("bndtManageDAO.deleteBndtCeckManage",bndtCeckManage);
	}

    /**
	 * 당직체크 중복여부 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectBndtCeckManageDplctAt(BndtCeckManage bndtCeckManage) throws Exception {
        return (Integer)selectOne("bndtManageDAO.selectBndtCeckManageDplctAt", bndtCeckManage);
    }
	
    /***** 당직 일지 *****/

	/**
	 * 등록된 당직일지관리의 상세정보를 조회한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 * @return List - 당직일지관리 VO
	 */
	public List<BndtDiaryVO> selectBndtDiary(BndtDiaryVO bndtDiaryVO) throws Exception {
		return selectList("bndtManageDAO.selectBndtDiary", bndtDiaryVO);
	}
	
	/**
	 * 당직일지관리정보를 신규로 등록한다.
	 * @param bndtDiary - 당직일지관리 model
	 */
	public void insertBndtDiary(BndtDiary bndtDiary) throws Exception {

		insert("bndtManageDAO.insertBndtDiary", bndtDiary);
	}

	/**
	 * 기 등록된 당직일지관리정보를 수정한다.
	 * @param bndtDiary - 당직일지관리 model
	 */
	public void updtBndtDiary(BndtDiary bndtDiary) throws Exception {
		update("bndtManageDAO.updtBndtDiary", bndtDiary);
	}

	/**
	 * 기 등록된 당직일지관리정보를 삭제한다.
	 * @param bndtDiary - 당직일지관리 model
	 */
	public void deleteBndtDiary(BndtDiary bndtDiary) throws Exception {
        delete("bndtManageDAO.deleteBndtDiary",bndtDiary);
	}

	
	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return BndtManageVO - 당직관리 VO
	 */
	public BndtManageVO selectBndtManageBnde(BndtManageVO bndtManageVO)  throws Exception {
		return (BndtManageVO) selectOne("bndtManageDAO.selectBndtManageBnde", bndtManageVO);
	}
	
    /**
	 * 당직관리 등록건수 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectBndtManageMonthCnt(BndtManageVO bndtManageVO) throws Exception {
        return (Integer)selectOne("bndtManageDAO.selectBndtManageMonthCnt", bndtManageVO);
    }
}
