package egovframework.com.uss.ion.vct.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.vct.service.IndvdlYrycManage;
import egovframework.com.uss.ion.vct.service.VcatnManage;
import egovframework.com.uss.ion.vct.service.VcatnManageVO;

/**
 * 개요
 * - 휴가관리에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 휴가관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 휴가관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Repository("vcatnManageDAO")
public class VcatnManageDAO extends EgovComAbstractDAO {

	/**
	 * 휴가관리정보를 관리하기 위해 등록된 휴가관리 목록을 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return List - 휴가관리 목록
	 */
	public List<VcatnManageVO> selectVcatnManageList(VcatnManageVO vcatnManageVO) throws Exception {
		return selectList("vcatnManageDAO.selectVcatnManageList", vcatnManageVO);
	}

    /**
	 * 휴가관리목록 총 개수를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectVcatnManageListTotCnt(VcatnManageVO vcatnManageVO) throws Exception {
        return (Integer)selectOne("vcatnManageDAO.selectVcatnManageListTotCnt", vcatnManageVO);
    }

	/**
	 * 등록된 휴가관리의 상세정보를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return VcatnManageVO - 휴가관리 VO
	 */
	public VcatnManageVO selectVcatnManage(VcatnManageVO vcatnManageVO)  throws Exception {
		return (VcatnManageVO) selectOne("vcatnManageDAO.selectVcatnManage", vcatnManageVO);
	}

	/**
	 * 휴가관리정보를 신규로 등록한다.
	 * @param vcatnManage - 휴가관리 model
	 */
	public void insertVcatnManage(VcatnManage vcatnManage) throws Exception {
		insert("vcatnManageDAO.insertVcatnManage", vcatnManage);
	}

	/**
	 * 기 등록된 휴가관리정보를 수정한다.
	 * @param vcatnManage - 휴가관리 model
	 */
	public void updtVcatnManage(VcatnManage vcatnManage) throws Exception {
		update("vcatnManageDAO.updateVcatnManage", vcatnManage);
	}

	/**
	 * 기 등록된 휴가관리정보를 삭제한다.
	 * @param vcatnManage - 휴가관리 model
	 */
	public void deleteVcatnManage(VcatnManage vcatnManage) throws Exception {
        delete("vcatnManageDAO.deleteVcatnManage",vcatnManage);
	}

    /**
	 * 휴가일자 중복여부 체크
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectVcatnManageDplctAt(VcatnManageVO vcatnManageVO) throws Exception {
        return (Integer)selectOne("vcatnManageDAO.selectVcatnManageDplctAt", vcatnManageVO);
    }
	
	
    /*** 승인관련 ***/	
	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록을 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return List - 휴가관리 목록
	 */
	public List<VcatnManageVO> selectVcatnManageConfmList(VcatnManageVO vcatnManageVO) throws Exception {
		return selectList("vcatnManageDAO.selectVcatnManageConfmList", vcatnManageVO);
	}

    /**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록 총 개수를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectVcatnManageConfmListTotCnt(VcatnManageVO vcatnManageVO) throws Exception {
        return (Integer)selectOne("vcatnManageDAO.selectVcatnManageConfmListTotCnt", vcatnManageVO);
    }
	
	/**
	 * 신청된 휴가를 승인처리한다.
	 * @param vcatnManage - 휴가관리 model
	 */
	public void updtVcatnManageConfm(VcatnManage vcatnManage) throws Exception {
		update("vcatnManageDAO.updateVcatnManageConfm", vcatnManage);
	}	



    /*** 연차관련 ***/	
	/**
	 * 개인별 연차관리의 상세정보를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return VcatnManageVO - 휴가관리 VO
	 */
	public VcatnManageVO selectIndvdlYrycManage(VcatnManageVO vcatnManageVO)  throws Exception {
		return (VcatnManageVO) selectOne("vcatnManageDAO.selectIndvdlYrycManage", vcatnManageVO);
	}
	
	
	/**
	 * 연차정보를 수정처리한다.
	 * @param vcatnManage - 휴가관리 model
	 */
	public void updtIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {

		   
		update("vcatnManageDAO.updateIndvdlYrycManage", indvdlYrycManage);
	}

}
