package egovframework.com.uss.ion.bnt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.bnt.service.BndtCeckManageVO;
import egovframework.com.uss.ion.bnt.service.BndtDiaryVO;
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
	
	public List<BndtManageVO> selectBndtManageList(BndtManageVO bndtManageVO) {
		return  selectList("bndtManageDAO.selectBndtManageList", bndtManageVO);
	}

    /**
	 * 당직관리목록 총 개수를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 */
    public int selectBndtManageListTotCnt(BndtManageVO bndtManageVO) {
        return (Integer)selectOne("bndtManageDAO.selectBndtManageListTotCnt", bndtManageVO);
    }

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return BndtManageVO - 당직관리 VO
	 */
	public BndtManageVO selectBndtManage(BndtManageVO bndtManageVO) {
		return (BndtManageVO) selectOne("bndtManageDAO.selectBndtManage", bndtManageVO);
	}

	/**
	 * 당직관리정보를 신규로 등록한다.
	 * @param bndtManageVO - 당직관리 VO
	 */
	public void insertBndtManage(BndtManageVO bndtManageVO) {
		insert("bndtManageDAO.insertBndtManage", bndtManageVO);
	}

	/**
	 * 기 등록된 당직관리정보를 수정한다.
	 * @param bndtManageVO - 당직관리 VO
	 */
	public void updtBndtManage(BndtManageVO bndtManageVO) {
		update("bndtManageDAO.updtBndtManage", bndtManageVO);
	}

	/**
	 * 기 등록된 당직관리정보를 삭제한다.
	 * @param bndtManageVO - 당직관리 VO
	 */
	public void deleteBndtManage(BndtManageVO bndtManageVO) {
        delete("bndtManageDAO.deleteBndtManage",bndtManageVO);
	}

    /**
	 * 당직일지 개수를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 */
    public int selectBndtDiaryTotCnt(BndtManageVO bndtManageVO) {
        return (Integer)selectOne("bndtManageDAO.selectBndtDiaryTotCnt", bndtManageVO);
    }
	
    /***** 당직 체크관리 *****/	

	/**
	 * 당직체크관리정보를 관리하기 위해 등록된 당직체크관리 목록을 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return List - 당직체크관리 목록
	 */
	public List<BndtCeckManageVO> selectBndtCeckManageList(BndtCeckManageVO bndtCeckManageVO) {
		return selectList("bndtManageDAO.selectBndtCeckManageList", bndtCeckManageVO);
	}

    /**
	 * 당직체크관리목록 총 개수를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int
	 */
    public int selectBndtCeckManageListTotCnt(BndtCeckManageVO bndtCeckManageVO) {
        return (Integer)selectOne("bndtManageDAO.selectBndtCeckManageListTotCnt", bndtCeckManageVO);
    }

	/**
	 * 등록된 당직체크관리의 상세정보를 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return BndtCeckManageVO - 당직체크관리 VO
	 */
	public BndtCeckManageVO selectBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) {
		return (BndtCeckManageVO) selectOne("bndtManageDAO.selectBndtCeckManage", bndtCeckManageVO);
	}

	/**
	 * 당직체크관리정보를 신규로 등록한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 */
	public void insertBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) {
		insert("bndtManageDAO.insertBndtCeckManage", bndtCeckManageVO);
	}

	/**
	 * 기 등록된 당직체크관리정보를 수정한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 */
	public void updtBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) {
		update("bndtManageDAO.updtBndtCeckManage", bndtCeckManageVO);
	}

	/**
	 * 기 등록된 당직체크관리정보를 삭제한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 */
	public void deleteBndtCeckManage(BndtCeckManageVO bndtCeckManageVO) {
        delete("bndtManageDAO.deleteBndtCeckManage",bndtCeckManageVO);
	}

    /**
	 * 당직체크 중복여부 조회한다.
	 * @param bndtCeckManageVO - 당직체크관리 VO
	 * @return int
	 */
    public int selectBndtCeckManageDplctAt(BndtCeckManageVO bndtCeckManageVO) {
        return (Integer)selectOne("bndtManageDAO.selectBndtCeckManageDplctAt", bndtCeckManageVO);
    }
	
    /***** 당직 일지 *****/

	/**
	 * 등록된 당직일지관리의 상세정보를 조회한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 * @return List - 당직일지관리 VO
	 */
	public List<BndtDiaryVO> selectBndtDiary(BndtDiaryVO bndtDiaryVO) {
		return selectList("bndtManageDAO.selectBndtDiary", bndtDiaryVO);
	}
	
	/**
	 * 당직일지관리정보를 신규로 등록한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 */
	public void insertBndtDiary(BndtDiaryVO bndtDiaryVO) {

		insert("bndtManageDAO.insertBndtDiary", bndtDiaryVO);
	}

	/**
	 * 기 등록된 당직일지관리정보를 수정한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 */
	public void updtBndtDiary(BndtDiaryVO bndtDiaryVO) {
		update("bndtManageDAO.updtBndtDiary", bndtDiaryVO);
	}

	/**
	 * 기 등록된 당직일지관리정보를 삭제한다.
	 * @param bndtDiaryVO - 당직일지관리 VO
	 */
	public void deleteBndtDiary(BndtDiaryVO bndtDiaryVO) {
        delete("bndtManageDAO.deleteBndtDiary",bndtDiaryVO);
	}

	
	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return BndtManageVO - 당직관리 VO
	 */
	public BndtManageVO selectBndtManageBnde(BndtManageVO bndtManageVO) {
		return (BndtManageVO) selectOne("bndtManageDAO.selectBndtManageBnde", bndtManageVO);
	}
	
    /**
	 * 당직관리 등록건수 조회한다.
	 * @param bndtManageVO - 당직관리 VO
	 * @return int
	 */
    public int selectBndtManageMonthCnt(BndtManageVO bndtManageVO) {
        return (Integer)selectOne("bndtManageDAO.selectBndtManageMonthCnt", bndtManageVO);
    }
}
