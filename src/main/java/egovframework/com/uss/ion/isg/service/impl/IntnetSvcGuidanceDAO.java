/**
 * 개요
 * - 인터넷서비스안내에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 인터넷서비스안내에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 인터넷서비스안내의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:52
 */

package egovframework.com.uss.ion.isg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.isg.service.IntnetSvcGuidance;
import egovframework.com.uss.ion.isg.service.IntnetSvcGuidanceVO;

@Repository("intnetSvcGuidanceDAO")
public class IntnetSvcGuidanceDAO extends EgovComAbstractDAO {

	/**
	 * 인터넷서비스안내정보를 관리하기 위해 등록된 인터넷서비스안내 목록을 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return List - 인터넷서비스안내 목록
	 */	
	public List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceList(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return selectList("intnetSvcGuidanceDAO.selectIntnetSvcGuidanceList", intnetSvcGuidanceVO);
	}

    /**
	 * 인터넷서비스안내목록 총 개수를 조회한다.
	 * @param mainImageVO - 인터넷서비스안내 VO
	 * @return int
	 */
    public int selectIntnetSvcGuidanceListTotCnt(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
        return (Integer)selectOne("intnetSvcGuidanceDAO.selectIntnetSvcGuidanceListTotCnt", intnetSvcGuidanceVO);
    }
	
	/**
	 * 등록된 인터넷서비스안내의 상세정보를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return IntnetSvcGuidanceVO - 인터넷서비스안내 VO
	 */
	public IntnetSvcGuidanceVO selectIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return (IntnetSvcGuidanceVO) selectOne("intnetSvcGuidanceDAO.selectIntnetSvcGuidance", intnetSvcGuidanceVO);
	}

	/**
	 * 인터넷서비스안내정보를 신규로 등록한다.
	 * @param intnetSvcGuidance - 인터넷서비스안내 model
	 */
	public void insertIntnetSvcGuidance(IntnetSvcGuidance intnetSvcGuidance) throws Exception {
		insert("intnetSvcGuidanceDAO.insertIntnetSvcGuidance", intnetSvcGuidance);
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 수정한다.
	 * @param intnetSvcGuidance - 인터넷서비스안내 model
	 */
	public void updateIntnetSvcGuidance(IntnetSvcGuidance intnetSvcGuidance) throws Exception {
		update("intnetSvcGuidanceDAO.updateIntnetSvcGuidance", intnetSvcGuidance);
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 삭제한다.
	 * @param intnetSvcGuidance - 인터넷서비스안내 model
	 */
	public void deleteIntnetSvcGuidance(IntnetSvcGuidance intnetSvcGuidance) throws Exception {
		delete("intnetSvcGuidanceDAO.deleteIntnetSvcGuidance", intnetSvcGuidance);
	}
	
	/**
	 * 인터넷서비스안내정보 적용결과를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return List - 인터넷서비스안내 목록
	 */
	public List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceResult(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return selectList("intnetSvcGuidanceDAO.selectIntnetSvcGuidanceResult", intnetSvcGuidanceVO);
	}	
}
