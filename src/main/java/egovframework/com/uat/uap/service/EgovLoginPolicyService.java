/**
 * 개요
 * - 로그인정책에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 로그인정책에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 로그인정책의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:54
 *   <pre>
 * == 개정이력(Modification Information) ==
 * 
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2009.8.3    이문준     최초 생성
 * </pre>
 */

package egovframework.com.uat.uap.service;

import java.util.List;


public interface EgovLoginPolicyService {
	
	/**
	 * 로그인정책 목록을 조회한다.
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return List - 로그인정책 목록
	 */
	public List<LoginPolicyVO> selectLoginPolicyList(LoginPolicyVO loginPolicyVO) throws Exception;

	/**
	 * 로그인정책 목록 수를 조회한다.
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return int
	 */
	public int selectLoginPolicyListTotCnt(LoginPolicyVO loginPolicyVO) throws Exception;
	
	/**
	 * 로그인정책 목록의 상세정보를 조회한다.
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return LoginPolicyVO - 로그인정책 VO
	 */
	public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO) throws Exception;

	/**
	 * 로그인정책 정보를 신규로 등록한다.
	 * @param loginPolicy - 로그인정책 model
	 */
	public void insertLoginPolicy(LoginPolicy loginPolicy) throws Exception;

	/**
	 * 기 등록된 로그인정책 정보를 수정한다.
	 * @param loginPolicy - 로그인정책 model
	 */
	public void updateLoginPolicy(LoginPolicy loginPolicy) throws Exception;

	/**
	 * 기 등록된 로그인정책 정보를 삭제한다.
	 * @param loginPolicy - 로그인정책 model
	 */
	public void deleteLoginPolicy(LoginPolicy loginPolicy) throws Exception;

	/**
	 * 로그인정책에 대한 현재 반영되어 있는 결과를 조회한다.
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return LoginPolicyVO - 로그인정책 VO
	 */
	public LoginPolicyVO selectLoginPolicyResult(LoginPolicyVO loginPolicyVO) throws Exception;
}
