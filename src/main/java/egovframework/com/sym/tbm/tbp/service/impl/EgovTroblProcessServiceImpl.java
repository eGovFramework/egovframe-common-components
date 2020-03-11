package egovframework.com.sym.tbm.tbp.service.impl;

import java.util.List;

import egovframework.com.sym.tbm.tbp.service.EgovTroblProcessService;
import egovframework.com.sym.tbm.tbp.service.TroblProcess;
import egovframework.com.sym.tbm.tbp.service.TroblProcessVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 장애처리결과 관리정보에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 장애처리결과 관리정보에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 * - 장애처리결과 관리정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:35
 */
@Service("egovTroblProcessService")
public class EgovTroblProcessServiceImpl extends EgovAbstractServiceImpl implements EgovTroblProcessService {

	@Resource(name="troblProcessDAO")
	private TroblProcessDAO troblProcessDAO;
	
	/**
	 * 장애처리정보를 관리하기 위해 대상 장애처리목록을 조회한다.
	 * @param troblProcessVO - 장애처리결과 Vo
	 * @return List - 장애처리결과 목록
	 */
	public List<TroblProcessVO> selectTroblProcessList(TroblProcessVO troblProcessVO) throws Exception {
		return troblProcessDAO.selectTroblProcessList(troblProcessVO);
	}

	/**
	 * 장애처리목록 총 갯수를 조회한다.
	 * @param troblProcessVO - 장애처리결과 Vo
	 * @return int - 장애처리결과 카운트 수
	 */
	public int selectTroblProcessListTotCnt(TroblProcessVO troblProcessVO) throws Exception {
		return troblProcessDAO.selectTroblProcessListTotCnt(troblProcessVO);
	}

	/**
	 * 등록된 장애처리의 상세정보를 조회한다.
	 * @param troblProcessVO - 장애처리결과 Vo
	 * @return troblProcessVO - 장애처리결과 Vo
	 */
	public TroblProcessVO selectTroblProcess(TroblProcessVO troblProcessVO) throws Exception {
		return troblProcessDAO.selectTroblProcess(troblProcessVO);
	}

	/**
	 * 장애처리정보를 신규로 등록한다.
	 * @param troblProcessVO - 장애처리결과 model
	 */
	public void insertTroblProcess(TroblProcess troblProcess) throws Exception {
		troblProcessDAO.insertTroblProcess(troblProcess);
	}

	/**
	 * 기 등록된 장애처리정보를 삭제한다.
	 * @param troblProcessVO - 장애처리결과 model
	 */
	public void deleteTroblProcess(TroblProcess troblProcess) throws Exception {
		troblProcessDAO.deleteTroblProcess(troblProcess);
	}

}