/**
 * 개요
 * - 사용자부재에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 사용자부재에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 사용자부재의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:09:36
 */

package egovframework.com.uss.ion.uas.service;

import java.util.List;

public interface EgovUserAbsnceService {
	
	/**
	 * 사용자부재정보를 관리하기 위해 등록된 사용자부재 목록을 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return List - 사용자부재 목록
	 */
	public List<UserAbsnceVO> selectUserAbsnceList(UserAbsnceVO userAbsnceVO) throws Exception;
	
	/**
	 * 사용자부재목록 총 갯수를 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return int - 사용자부재 카운트 수
	 */
	public int selectUserAbsnceListTotCnt(UserAbsnceVO userAbsnceVO) throws Exception;
	
	/**
	 * 등록된 사용자부재 상세정보를 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return UserAbsnceVO - 사용자부재 VO
	 */
	public UserAbsnceVO selectUserAbsnce(UserAbsnceVO userAbsnceVO) throws Exception;

	/**
	 * 사용자부재정보를 신규로 등록한다.
	 * @param userAbsnce - 사용자부재 model
	 * @param userAbsnceVO - 사용자부재 VO
	 */
	public UserAbsnceVO insertUserAbsnce(UserAbsnce userAbsnce, UserAbsnceVO userAbsnceVO) throws Exception;

	/**
	 * 기 등록된 사용자부재정보를 수정한다.
	 * @param userAbsnce - 사용자부재 model
	 */
	public void updateUserAbsnce(UserAbsnce userAbsnce) throws Exception;

	/**
	 * 기 등록된 사용자부재정보를 삭제한다.
	 * @param userAbsnce - 사용자부재 model
	 */
	public void deleteUserAbsnce(UserAbsnce userAbsnce) throws Exception;

	/**
	 * 사용자부재정보가 특정화면에 반영된 결과를 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return UserAbsnceVO - 사용자부재 VO
	 */
	public UserAbsnceVO selectUserAbsnceResult(UserAbsnceVO userAbsnceVO) throws Exception;
}
