package egovframework.com.sym.sym.nwk.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.sym.nwk.service.Ntwrk;
import egovframework.com.sym.sym.nwk.service.NtwrkVO;

/**
 * <pre>
 * 개요
 * - 네트워크에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 네트워크에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 네트워크의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author lee.m.j
 * @since 2010.08.19
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  lee.m.j       최초 생성
 *   2025.07.23  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UnnecessaryBoxing(불필요한 WrapperObject 생성)
 *
 *      </pre>
 */
@Repository("ntwrkDAO")
public class NtwrkDAO extends EgovComAbstractDAO {

	/**
	 * 네트워크를 관리하기 위해 등록된 네트워크목록을 조회한다.
	 * 
	 * @param ntwrkVO - 네트워크 Vo
	 * @return List - 네트워크 목록
	 */
	public List<NtwrkVO> selectNtwrkList(NtwrkVO ntwrkVO) throws Exception {
		return selectList("ntwrkDAO.selectNtwrkList", ntwrkVO);
	}

	/**
	 * 네트워크목록 총 개수를 조회한다.
	 * 
	 * @param ntwrkVO - 네트워크 Vo
	 * @return int - 네트워크 카운트 수
	 */
	public int selectNtwrkListTotCnt(NtwrkVO ntwrkVO) throws Exception {
		return selectOne("ntwrkDAO.selectNtwrkListTotCnt", ntwrkVO);
	}

	/**
	 * 등록된 네트워크의 상세정보를 조회한다.
	 * 
	 * @param ntwrkVO - 네트워크 Vo
	 * @return NtwrkVO - 네트워크 Vo
	 */
	public NtwrkVO selectNtwrk(NtwrkVO ntwrkVO) throws Exception {
		return (NtwrkVO) selectOne("ntwrkDAO.selectNtwrk", ntwrkVO);
	}

	/**
	 * 네트워크정보를 신규로 등록한다.
	 * 
	 * @param ntwrk - 네트워크 model
	 */
	public void insertNtwrk(Ntwrk ntwrk) throws Exception {
		insert("ntwrkDAO.insertNtwrk", ntwrk);
	}

	/**
	 * 기 등록된 네트워크정보를 수정한다.
	 * 
	 * @param ntwrk - 네트워크 model
	 */
	public void updateNtwrk(Ntwrk ntwrk) throws Exception {
		update("ntwrkDAO.updateNtwrk", ntwrk);
	}

	/**
	 * 기 등록된 네트워크정보를 삭제한다.
	 * 
	 * @param ntwrk - 네트워크 model
	 */
	public void deleteNtwrk(Ntwrk ntwrk) throws Exception {
		delete("ntwrkDAO.deleteNtwrk", ntwrk);
	}

}
