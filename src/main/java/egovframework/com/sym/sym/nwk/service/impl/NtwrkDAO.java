/**
 * 개요
 * - 네트워크에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 네트워크에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 네트워크의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 19-8-2010 오후 4:34:37
 */

package egovframework.com.sym.sym.nwk.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.sym.nwk.service.Ntwrk;
import egovframework.com.sym.sym.nwk.service.NtwrkVO;

import org.springframework.stereotype.Repository;

@Repository("ntwrkDAO")
public class NtwrkDAO extends EgovComAbstractDAO {

	/**
	 * 네트워크를 관리하기 위해 등록된 네트워크목록을 조회한다.
	 * @param ntwrkVO - 네트워크 Vo
	 * @return List - 네트워크 목록
	 */
    @SuppressWarnings("unchecked")
	public List<NtwrkVO> selectNtwrkList(NtwrkVO ntwrkVO) throws Exception {
        return selectList("ntwrkDAO.selectNtwrkList", ntwrkVO);
    }
    
	/**
	 * 네트워크목록 총 갯수를 조회한다.
	 * @param ntwrkVO - 네트워크 Vo
	 * @return int - 네트워크 카운트 수
	 */
    public int selectNtwrkListTotCnt(NtwrkVO ntwrkVO) throws Exception {
        return ((Integer)selectOne("ntwrkDAO.selectNtwrkListTotCnt", ntwrkVO)).intValue();
    }
    
	/**
	 * 등록된 네트워크의 상세정보를 조회한다.
	 * @param ntwrkVO - 네트워크 Vo
	 * @return NtwrkVO - 네트워크 Vo
	 */
    public NtwrkVO selectNtwrk(NtwrkVO ntwrkVO) throws Exception {
        return (NtwrkVO)selectOne("ntwrkDAO.selectNtwrk", ntwrkVO);
    }
    
	/**
	 * 네트워크정보를 신규로 등록한다.
	 * @param ntwrk - 네트워크 model
	 */
    public void insertNtwrk(Ntwrk ntwrk) throws Exception {
        insert("ntwrkDAO.insertNtwrk", ntwrk);
    }
    
	/**
	 * 기 등록된 네트워크정보를 수정한다.
	 * @param ntwrk - 네트워크 model
	 */
    public void updateNtwrk(Ntwrk ntwrk) throws Exception {
        update("ntwrkDAO.updateNtwrk", ntwrk);
    }
    
	/**
	 * 기 등록된 네트워크정보를 삭제한다.
	 * @param ntwrk - 네트워크 model
	 */
    public void deleteNtwrk(Ntwrk ntwrk) throws Exception {
        delete("ntwrkDAO.deleteNtwrk", ntwrk);
    }

}
