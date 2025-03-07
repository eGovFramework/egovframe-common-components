package egovframework.com.cop.smt.lsm.service.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.smt.lsm.service.EmplyrVO;
import egovframework.com.cop.smt.lsm.service.LeaderSchdul;
import egovframework.com.cop.smt.lsm.service.LeaderSchdulVO;
import egovframework.com.cop.smt.lsm.service.LeaderSttus;
import egovframework.com.cop.smt.lsm.service.LeaderSttusVO;

/**
 * 개요
 * - 간부일정에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 간부일정에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 간부일정의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:06
 */
@Repository("LeaderSchdulDAO")
public class LeaderSchdulDAO extends EgovComAbstractDAO {
	
	/**
	 * 주어진 조건에 맞는 사용자를 불러온다.
	 * @param EmplyrVO - 사용자 VO
	 * @return List- 사용자 List
	 * 
	 * @param emplyrVO
	 */	
	public List<EmplyrVO> selectEmplyrList(EmplyrVO emplyrVO) throws Exception{
		return selectList("LeaderSchdulDAO.selectEmplyrList", emplyrVO);
	}
	
	/**
	 * 사용자 목록에 대한 전체 건수를 조회한다.
	 * @param EmplyrVO - 사용자 VO
	 * @return int - 사용자 목록 개수
	 * 
	 * @param emplyrVO
	 */
	public int selectEmplyrListCnt(EmplyrVO emplyrVO) throws Exception{
		return (Integer)selectOne("LeaderSchdulDAO.selectEmplyrListCnt", emplyrVO);
	}
	
	/**
	 * 주어진 조건에 따른 간부일정 목록을 월별로 불러온다.
	 * @param LeaderSchdulVO - 간부일정 VO
	 * @return List - 간부일정 List
	 * 
	 * @param leaderSchdulVO
	 */
	
	public List<LeaderSchdulVO> selectLeaderSchdulList(LeaderSchdulVO leaderSchdulVO) throws Exception{
		return selectList("LeaderSchdulDAO.selectLeaderSchdulList", leaderSchdulVO);
	}

	/**
	 * 주어진 조건에 맞는 간부일정을 불러온다.
	 * @param LeaderSchdulVO - 간부일정 VO
	 * @return LeaderSchdulVO - 간부일정 VO
	 * 
	 * @param leaderSchdulVO
	 */
	public LeaderSchdulVO selectLeaderSchdul(LeaderSchdulVO leaderSchdulVO) throws Exception{
		return (LeaderSchdulVO)selectOne("LeaderSchdulDAO.selectLeaderSchdul", leaderSchdulVO);
	}

	/**
	 * 간부일정 정보를 수정한다.
	 * @param LeaderSchdul - 간부일정 model
	 * 
	 * @param leaderSchdul
	 */
	public void updateLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception{
		update("LeaderSchdulDAO.updateLeaderSchdul", leaderSchdul);
	}

	/**
	 * 간부일정 정보를 등록한다.
	 * @param LeaderSchdul - 간부일정 model
	 * 
	 * @param leaderSchdul
	 */
	public void insertLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception{
		insert("LeaderSchdulDAO.insertLeaderSchdul", leaderSchdul);
	}
	
	/**
	 * 간부일정 일자 정보를 등록한다.
	 * @param LeaderSchdul - 간부일정 model
	 * 
	 * @param leaderSchdul
	 */
	public void insertLeaderSchdulDe(LeaderSchdul leaderSchdul) throws Exception{
		insert("LeaderSchdulDAO.insertLeaderSchdulDe", leaderSchdul);
	}

	/**
	 * 간부일정 정보를 삭제한다.
	 * @param LeaderSchdul - 간부일정 model
	 * 
	 * @param leaderSchdul
	 */
	public void deleteLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception{
		delete("LeaderSchdulDAO.deleteLeaderSchdul", leaderSchdul);
	}
	
	/**
	 * 간부일정일자 정보를 삭제한다.
	 * @param LeaderSchdul - 간부일정 model
	 * 
	 * @param leaderSchdul
	 */
	public void deleteLeaderSchdulDe(LeaderSchdul leaderSchdul) throws Exception{
		delete("LeaderSchdulDAO.deleteLeaderSchdulDe", leaderSchdul);
	}
	
	/**
	 * 주어진 조건에 따른 간부상태 목록을 불러온다.
	 * @param LeaderSttusVO - 간부상태 VO
	 * @return List - 간부상태 List
	 * 
	 * @param LeaderSttusVO
	 */
	public List<LeaderSttusVO> selectLeaderSttusList(LeaderSttusVO leaderSttusVO) throws Exception{
		return selectList("LeaderSchdulDAO.selectLeaderSttusList", leaderSttusVO);
	}
	
	/**
	 * 간부상태 목록에 대한 전체 건수를 조회한다.
	 * @param LeaderSttusVO - 간부상태 VO
	 * @return int
	 * 
	 * @param LeaderSttusVO
	 */
	public int selectLeaderSttusListCnt(LeaderSttusVO leaderSttusVO) throws Exception{
		return (Integer)selectOne("LeaderSchdulDAO.selectLeaderSttusListCnt", leaderSttusVO);
	}
	
	/**
	 * 주어진 조건에 맞는 간부상태를 불러온다.
	 * @param LeaderSttusVO - 간부상태 VO
	 * @return LeaderSttusVO - 간부상태 VO
	 * 
	 * @param leaderSttusVO
	 */
	public LeaderSttusVO selectLeaderSttus(LeaderSttusVO leaderSttusVO) throws Exception{
		return (LeaderSttusVO)selectOne("LeaderSchdulDAO.selectLeaderSttus", leaderSttusVO);
	}

	/**
	 * 간부상태 정보를 수정한다.
	 * @param LeaderSttus - 간부상태 model
	 * 
	 * @param leaderSttus
	 */
	public void updateLeaderSttus(LeaderSttus leaderSttus) throws Exception{
		update("LeaderSchdulDAO.updateLeaderSttus", leaderSttus);
	}

	/**
	 * 간부상태 정보를 등록한다.
	 * @param LeaderSttus - 간부상태 model
	 * 
	 * @param leaderSttus
	 */
	public void insertLeaderSttus(LeaderSttus leaderSttus) throws Exception{
		insert("LeaderSchdulDAO.insertLeaderSttus", leaderSttus);
	}
	
	/**
	 * 간부상태 등록을 위한 중복 조회를 수행한다.
	 * @param LeaderSttus - 간부상태 model
	 * @return int
	 * 
	 * @param leaderSttus
	 */
	public int selectLeaderSttusCheck(LeaderSttus leaderSttus) throws Exception{
		return (Integer)selectOne("LeaderSchdulDAO.selectLeaderSttusCheck", leaderSttus);
	}

	/**
	 * 간부상태 정보를 삭제한다.
	 * @param LeaderSttus - 간부상태 model
	 * 
	 * @param leaderSttus
	 */
	public void deleteLeaderSttus(LeaderSttus leaderSttus) throws Exception{
		delete("LeaderSchdulDAO.deleteLeaderSttus", leaderSttus);
	}

}