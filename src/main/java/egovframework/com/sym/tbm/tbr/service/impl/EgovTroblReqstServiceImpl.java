package egovframework.com.sym.tbm.tbr.service.impl;

import java.util.List;

import egovframework.com.sym.tbm.tbr.service.EgovTroblReqstService;
import egovframework.com.sym.tbm.tbr.service.TroblReqst;
import egovframework.com.sym.tbm.tbr.service.TroblReqstVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 장애신청 정보에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 장애신청 정보에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 * - 장애신청 정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:35
 */
@Service("egovTroblReqstService")
public class EgovTroblReqstServiceImpl extends EgovAbstractServiceImpl implements EgovTroblReqstService {

	@Resource(name="troblReqstDAO")
	private TroblReqstDAO troblReqstDAO;
	
	/**
	 * 장애요청을 관리하기 위해 등록된 장애요청목록을 조회한다.
	 * @param troblReqstVO - 장애관리 Vo
	 * @return List - 장애요청 목록
	 */
	public List<TroblReqstVO> selectTroblReqstList(TroblReqstVO troblReqstVO) throws Exception {
		return troblReqstDAO.selectTroblReqstList(troblReqstVO);
	}

	/**
	 * 장애요청목록 총 갯수를 조회한다.
	 * @param troblReqstVO - 장애신청관리 Vo
	 * @return int - 장애요청 카운트 수
	 */
	public int selectTroblReqstListTotCnt(TroblReqstVO troblReqstVO) throws Exception {
		return troblReqstDAO.selectTroblReqstListTotCnt(troblReqstVO);
	}
	
	/**
	 * 등록된 장애요청의 상세정보를 조회한다.
	 * @param troblReqstVO - 장애신청관리 Vo
	 * @return troblReqstVO - 장애신청관리 Vo
	 */
	public TroblReqstVO selectTroblReqst(TroblReqstVO troblReqstVO) throws Exception {
		return troblReqstDAO.selectTroblReqst(troblReqstVO);
	}

	/**
	 * 장애요청정보를 신규로 등록한다.
	 * @param troblReqst - 장애신청 model
	 * @param troblReqstVO - 장애신청관리 Vo
	 */
	public TroblReqstVO insertTroblReqst(TroblReqst troblReqst, TroblReqstVO troblReqstVO) throws Exception {
		troblReqstDAO.insertTroblReqst(troblReqst);
		troblReqstVO.setTroblId(troblReqst.getTroblId());
		return troblReqstDAO.selectTroblReqst(troblReqstVO);
	}

	/**
	 * 기 등록된 장애요청정보를 수정한다.
	 * @param troblReqst - 장애신청 model
	 */
	public void updateTroblReqst(TroblReqst troblReqst) throws Exception {
		troblReqstDAO.updateTroblReqst(troblReqst);
	}

	/**
	 * 기 등록된 장애요청정보를 삭제한다.
	 * @param troblReqst - 장애신청 model
	 */
	public void deleteTroblReqst(TroblReqst troblReqst) throws Exception {
		troblReqstDAO.deleteTroblReqst(troblReqst);
	}

	/**
	 * 장애처리를 요청한다.
	 * @param troblReqst - 장애신청 model
	 */
	public void requstTroblReqst(TroblReqst troblReqst) throws Exception {
		troblReqstDAO.requstTroblReqst(troblReqst);
	}
}