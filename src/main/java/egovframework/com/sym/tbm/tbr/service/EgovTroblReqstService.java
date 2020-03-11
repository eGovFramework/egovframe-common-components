package egovframework.com.sym.tbm.tbr.service;

import java.util.List;

/**
 * 개요
 * - 장애신청정보에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 장애신청 관리에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 장애신청 관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:35
 */
public interface EgovTroblReqstService {

	/**
	 * 장애요청을 관리하기 위해 등록된 장애요청목록을 조회한다.
	 * @param troblReqstVO - 장애신청 Vo
	 * @return List - 장애요청 목록
	 */
	public List<TroblReqstVO> selectTroblReqstList(TroblReqstVO troblReqstVO) throws Exception;

	/**
	 * 장애요청목록 총 갯수를 조회한다.
	 * @param troblReqstVO - 장애신청 Vo
	 * @return int - 장애요청 카운트 수
	 */
	public int selectTroblReqstListTotCnt(TroblReqstVO troblReqstVO) throws Exception;
	
	/**
	 * 등록된 장애요청의 상세정보를 조회한다.
	 * @param troblReqstVO - 장애신청 Vo
	 * @return troblReqstVO - 장애신청 Vo
	 */
	public TroblReqstVO selectTroblReqst(TroblReqstVO troblReqstVO) throws Exception;

	/**
	 * 장애요청정보를 신규로 등록한다.
	 * @param troblReqst - 장애신청 model
	 * @param troblReqstVO - 장애신청 Vo
	 */
	public TroblReqstVO insertTroblReqst(TroblReqst troblReqst, TroblReqstVO troblReqstVO) throws Exception;

	/**
	 * 기 등록된 장애요청정보를 수정한다.
	 * @param troblReqst - 장애신청 model
	 */
	public void updateTroblReqst(TroblReqst troblReqst) throws Exception;

	/**
	 * 기 등록된 장애요청정보를 삭제한다.
	 * @param troblReqst - 장애신청 model
	 */
	public void deleteTroblReqst(TroblReqst troblReqst) throws Exception;

	/**
	 * 장애처리를 요청한다.
	 * @param troblReqst - 장애신청 model
	 */
	public void requstTroblReqst(TroblReqst troblReqst) throws Exception;


}