/**
 * 개요
 * - 인터넷서비스안내에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 인터넷서비스안내에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 인터넷서비스안내의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:03
 */

package egovframework.com.uss.ion.isg.service;

import java.util.List;

public interface EgovIntnetSvcGuidanceService {
	
	/**
	 * 인터넷서비스안내정보를 관리하기 위해 등록된 인터넷서비스안내 목록을 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return List - 인터넷서비스안내 목록
	 */
	public List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceList(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception;

	/**
	 * 인터넷서비스안내목록 총 갯수를 조회한다.
	 * @param mainImageVO - 인터넷서비스안내 VO
	 * @return int
	 */
	public int selectIntnetSvcGuidanceListTotCnt(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception;
	
	/**
	 * 등록된 인터넷서비스안내의 상세정보를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return IntnetSvcGuidanceVO - 인터넷서비스안내 VO
	 */
	public IntnetSvcGuidanceVO selectIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception;

	/**
	 * 인터넷서비스안내정보를 신규로 등록한다.
	 * @param intnetSvcGuidance - 인터넷서비스안내 model
	 */
	public IntnetSvcGuidanceVO insertIntnetSvcGuidance(IntnetSvcGuidance intnetSvcGuidance, IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception;

	/**
	 * 기 등록된 인터넷서비스안내정보를 수정한다.
	 * @param intnetSvcGuidance - 인터넷서비스안내 model
	 */
	public void updateIntnetSvcGuidance(IntnetSvcGuidance intnetSvcGuidance) throws Exception;

	/**
	 * 기 등록된 인터넷서비스안내정보를 삭제한다.
	 * @param intnetSvcGuidance - 인터넷서비스안내 model
	 */
	public void deleteIntnetSvcGuidance(IntnetSvcGuidance intnetSvcGuidance) throws Exception;
	
	/**
	 * 인터넷서비스안내정보 적용결과를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return List - 인터넷서비스안내 목록
	 */
	public List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceResult(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception;
	
}
