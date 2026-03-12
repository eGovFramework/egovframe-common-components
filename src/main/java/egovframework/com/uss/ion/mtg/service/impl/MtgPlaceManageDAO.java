/**
 * 개요
 * - 회의실관리에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 회의실관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

package egovframework.com.uss.ion.mtg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.mtg.service.MtgPlaceManageVO;
import egovframework.com.uss.ion.mtg.service.MtgPlaceResveVO;

@Repository("mtgPlaceManageDAO")
public class MtgPlaceManageDAO extends EgovComAbstractDAO {

	/**
	 * 회의실관리정보를 관리하기 위해 등록된 회의실관리 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	public List<MtgPlaceManageVO> selectMtgPlaceManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return selectList("mtgPlaceManageDAO.selectMtgPlaceManageList", mtgPlaceManageVO);
	}

    /**
	 * 회의실관리목록 총 개수를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectMtgPlaceManageListTotCnt(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
        return (Integer)selectOne("mtgPlaceManageDAO.selectMtgPlaceManageListTotCnt", mtgPlaceManageVO);
    }

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	public MtgPlaceManageVO selectMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO)  throws Exception {
		return (MtgPlaceManageVO) selectOne("mtgPlaceManageDAO.selectMtgPlaceManage", mtgPlaceManageVO);
	}

	/**
	 * 회의실관리정보를 신규로 등록한다.
	 */
	public void insertMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		insert("mtgPlaceManageDAO.insertMtgPlaceManage", mtgPlaceManageVO);
	}

	/**
	 * 기 등록된 회의실관리정보를 수정한다.
	 */
	public void updtMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		update("mtgPlaceManageDAO.updtMtgPlaceManage", mtgPlaceManageVO);
	}

	/**
	 * 기 등록된 회의실관리정보를 삭제한다.
	 */
	public void deleteMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		delete("mtgPlaceManageDAO.deleteMtgPlaceManage", mtgPlaceManageVO);
	}

	/******** 회의실 예약 관리 *************/

	/** 
	 * 회의실 ID 정보 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	public List<MtgPlaceManageVO> selectMtgPlaceIDList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return selectList("mtgPlaceManageDAO.selectMtgPlaceIDList", mtgPlaceManageVO);
	}
	
	/** 
	 * 회의실 예약정보를 관리하기 위해 등록된 회의실예약을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 목록
	 */
	public MtgPlaceManageVO selectMtgPlaceResveManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return (MtgPlaceManageVO) selectOne("mtgPlaceManageDAO.selectMtgPlaceResveManageList", mtgPlaceManageVO);
	}
	
	/**
	 * 회의실예약 신청화면을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	public MtgPlaceManageVO selectMtgPlaceResve(MtgPlaceManageVO mtgPlaceManageVO)  throws Exception {
		return (MtgPlaceManageVO) selectOne("mtgPlaceManageDAO.selectMtgPlaceResve", mtgPlaceManageVO);
	}

	/**
	 * 등록된 회의실예약 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	public MtgPlaceManageVO selectMtgPlaceResveDetail(MtgPlaceManageVO mtgPlaceManageVO)  throws Exception {
		return (MtgPlaceManageVO) selectOne("mtgPlaceManageDAO.selectMtgPlaceResveDetail", mtgPlaceManageVO);
	}
	
	/**
	 * 회의실예약 정보를 신규로 등록한다.
	 */
	public void insertMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) throws Exception {
		insert("mtgPlaceManageDAO.insertMtgPlaceResve", mtgPlaceResveVO);
	}

	/**
	 * 기 등록된 회의실예약 정보를 수정한다.
	 */
	public void updtMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) throws Exception {
		update("mtgPlaceManageDAO.updtMtgPlaceResve", mtgPlaceResveVO);
	}

	/**
	 * 기 등록된 회의실예약 정보를 삭제한다.
	 */
	public void deleteMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) throws Exception {
		delete("mtgPlaceManageDAO.deleteMtgPlaceResve", mtgPlaceResveVO);
	}	
	
	
	/**
	 * 회의실 중복여부 체크.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 중복건수
	 */
	public int mtgPlaceResveDplactCeck(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return (Integer)selectOne("mtgPlaceManageDAO.mtgPlaceResveDplactCeck", mtgPlaceManageVO);
	}
	
}
