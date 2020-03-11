package egovframework.com.sym.tbm.tbp.service;

import java.util.List;

/**
 * 개요
 * - 장애처리결과정보에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 장애처리결과관리에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 장애처리결과관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:35
 */
public interface EgovTroblProcessService {

	/**
	 * 장애처리정보를 관리하기 위해 대상 장애처리목록을 조회한다.
	 * @param troblManageVO - 장애처리 Vo
	 * @return List - 장애처리 목록
	 */
	public List<TroblProcessVO> selectTroblProcessList(TroblProcessVO troblProcessVO) throws Exception;

	/**
	 * 장애처리정보목록 총 갯수를 조회한다.
	 * @param troblManageVO - 장애관리 Vo
	 * @return List - 장애처리 목록
	 */
	public int selectTroblProcessListTotCnt(TroblProcessVO troblProcessVO) throws Exception;
	
	/**
	 * 등록된 장애처리의 상세정보를 조회한다.
	 * @param troblManageVO - 장애관리 Vo
	 * @return troblManageVO - 장애관리 Vo
	 */
	public TroblProcessVO selectTroblProcess(TroblProcessVO troblProcessVO) throws Exception;

	/**
	 * 장애처리정보를 신규로 등록한다.
	 * @param troblManage - 장애관리 model
	 */
	public void insertTroblProcess(TroblProcess troblProcess) throws Exception;

	/**
	 * 기 등록된 장애처리정보를 삭제한다.
	 * @param troblManage - 장애관리 model
	 */
	public void deleteTroblProcess(TroblProcess troblProcess) throws Exception;

}