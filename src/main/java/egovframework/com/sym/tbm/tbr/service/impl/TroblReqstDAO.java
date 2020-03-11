package egovframework.com.sym.tbm.tbr.service.impl;
import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.tbm.tbr.service.TroblReqst;
import egovframework.com.sym.tbm.tbr.service.TroblReqstVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 장애신청정보에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 장애신청정보에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 * - 장애신청정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:58
 */
@Repository("troblReqstDAO")
public class TroblReqstDAO extends EgovComAbstractDAO {

	/**
	 * 장애요청을 관리하기 위해 등록된 장애요청목록을 조회한다.
	 * @param troblReqstVO - 장애신청 Vo
	 * @return List - 장애요청 목록
	 */
	@SuppressWarnings("unchecked")
	public List<TroblReqstVO> selectTroblReqstList(TroblReqstVO troblReqstVO) throws Exception {
		return selectList("troblReqstDAO.selectTroblReqstList", troblReqstVO);
	}

	/**
	 * 장애요청목록 총 갯수를 조회한다.
	 * @param troblReqstVO - 장애신청 Vo
	 * @return int - 장애요청 카운트 수
	 */
	public int selectTroblReqstListTotCnt(TroblReqstVO troblReqstVO) throws Exception {
		return (Integer)selectOne("troblReqstDAO.selectTroblReqstListTotCnt", troblReqstVO);
	}

	/**
	 * 등록된 장애요청의 상세정보를 조회한다.
	 * @param troblReqstVO - 장애신청 Vo
	 * @return troblReqstVO - 장애신청 Vo
	 */
	public TroblReqstVO selectTroblReqst(TroblReqstVO troblReqstVO) throws Exception {
		return (TroblReqstVO) selectOne("troblReqstDAO.selectTroblReqst", troblReqstVO);
	}

	/**
	 * 장애요청정보를 신규로 등록한다.
	 * @param troblReqst - 장애신청 model
	 * @param troblReqstVO - 장애신청 Vo
	 */
	public void insertTroblReqst(TroblReqst troblReqst) throws Exception {
		insert("troblReqstDAO.insertTroblReqst", troblReqst);
	}

	/**
	 * 기 등록된 장애요청정보를 수정한다.
	 * @param troblReqst - 장애신청 model
	 */
	public void updateTroblReqst(TroblReqst troblReqst) throws Exception {
		update("troblReqstDAO.updateTroblReqst", troblReqst);
	}

	/**
	 * 기 등록된 장애요청정보를 삭제한다.
	 * @param troblReqst - 장애신청 model
	 */
	public void deleteTroblReqst(TroblReqst troblReqst) throws Exception {
		delete("troblReqstDAO.deleteTroblReqst", troblReqst);
	}

	/**
	 * 장애처리를 요청한다.
	 * @param troblReqst - 장애신청 model
	 */
	public void requstTroblReqst(TroblReqst troblReqst) throws Exception {
		update("troblReqstDAO.requstTroblReqst", troblReqst);
	}

}