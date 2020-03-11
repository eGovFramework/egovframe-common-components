package egovframework.com.cop.smt.lsm.service;

import java.util.List;
import java.util.Map;

/**
 * 개요
 * - 간부일정에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 간부일정에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 간부일정의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:05
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *
 * </pre>
 */
public interface EgovLeaderSchdulService {
	
	/**
	 * 사용자 목록을 조회한다.
	 * @param EmplyrVO
	 * @return  Map<String, Object>
	 * 
	 * @param emplyrVO
	 */
	public Map<String, Object> selectEmplyrList(EmplyrVO emplyrVO) throws Exception;
	
	/**
	 * 월별 간부일정 목록을 조회한다.
	 * @param LeaderSchdulVO
	 * @return  List
	 * 
	 * @param leaderSchdulVo
	 */
	public List<LeaderSchdulVO> selectLeaderSchdulList(LeaderSchdulVO leaderSchdulVo) throws Exception;

	/**
	 * 간부일정 정보를 조회한다.
	 * @param LeaderSchdulVO
	 * @return  LeaderSchdulVO
	 * 
	 * @param leaderSchdulVO
	 */
	public LeaderSchdulVO selectLeaderSchdul(LeaderSchdulVO leaderSchdulVO) throws Exception;

	/**
	 * 간부일정 정보를 수정한다.
	 * @param LeaderSchdul
	 * 
	 * @param leaderSchdul
	 */
	public void updateLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception;

	/**
	 * 간부일정 정보를 등록한다.
	 * @param LeaderSchdul
	 * 
	 * @param leaderSchdul
	 */
	public void insertLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception;

	/**
	 * 간부일정 정보를 삭제한다.
	 * @param LeaderSchdul
	 * 
	 * @param leaderSchdul
	 */
	public void deleteLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception;
	
	/**
	 * 간부상태 목록을 조회한다.
	 * @param LeaderSttusVO - 간부상태 VO
	 * @return  Map<String, Object>
	 * 
	 * @param leaderSttusVO
	 */
	public Map<String, Object> selectLeaderSttusList(LeaderSttusVO leaderSttusVO) throws Exception;
	
	/**
	 * 간부상태 정보를 조회한다.
	 * @param LeaderSttusVO - 간부상태 VO
	 * @return  LeaderSttusVO
	 * 
	 * @param leaderSttusVO
	 */
	public LeaderSttusVO selectLeaderSttus(LeaderSttusVO leaderSttusVO) throws Exception;

	/**
	 * 간부상태 정보를 수정한다.
	 * @param LeaderSttus - 간부상태 model
	 * 
	 * @param leaderSttus
	 */
	public void updateLeaderSttus(LeaderSttus leaderSttus) throws Exception;

	/**
	 * 간부상태 정보를 등록한다.
	 * @param LeaderSttus - 간부상태 model
	 * 
	 * @param leaderSttus
	 */
	public void insertLeaderSttus(LeaderSttus leaderSttus) throws Exception;
	
	/**
	 * 간부상태를 등록하기 위한 중복 조회를 수행한다.
	 * @param LeaderSttus - 간부상태 model
	 * @return  int 
	 * 
	 * @param leaderSttus
	 */
	public int selectLeaderSttusCheck(LeaderSttus leaderSttus) throws Exception;
	
	/**
	 * 간부상태 정보를 삭제한다.
	 * @param LeaderSttus - 간부상태 model
	 * 
	 * @param leaderSttus
	 */
	public void deleteLeaderSttus(LeaderSttus leaderSttus) throws Exception;
}