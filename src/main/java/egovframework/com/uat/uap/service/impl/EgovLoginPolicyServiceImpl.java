/**
 * 개요
 * - 로그인정책에 대한 ServiceImpl 클래스를 정의한다.
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
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.08.03   이문준            최초 생성
 *  2021.02.18   신용호            selectLoginPolicyResult() 삭제
 *  2024.02.05   이백행            보안약점 조치: 부적절한 예외 처리 (광범위한 예외객체 선언)
 * </pre>
 */

package egovframework.com.uat.uap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.uat.uap.service.EgovLoginPolicyService;
import egovframework.com.uat.uap.service.LoginPolicy;
import egovframework.com.uat.uap.service.LoginPolicyVO;

@Service("egovLoginPolicyService")
public class EgovLoginPolicyServiceImpl extends EgovAbstractServiceImpl implements EgovLoginPolicyService {

	@Resource(name = "loginPolicyDAO")
	LoginPolicyDAO loginPolicyDAO;

	/**
	 * 로그인정책 목록을 조회한다.
	 * 
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return List - 로그인정책 목록
	 */
	@Override
	public List<LoginPolicyVO> selectLoginPolicyList(LoginPolicyVO loginPolicyVO) {
		return loginPolicyDAO.selectLoginPolicyList(loginPolicyVO);
	}

	/**
	 * 로그인정책 목록 수를 조회한다.
	 * 
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return int
	 */
	@Override
	public int selectLoginPolicyListTotCnt(LoginPolicyVO loginPolicyVO) {
		return loginPolicyDAO.selectLoginPolicyListTotCnt(loginPolicyVO);
	}

	/**
	 * 로그인정책 목록의 상세정보를 조회한다.
	 * 
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return LoginPolicyVO - 로그인정책 VO
	 */
	@Override
	public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO) {
		return loginPolicyDAO.selectLoginPolicy(loginPolicyVO);
	}

	/**
	 * 로그인정책 정보를 신규로 등록한다.
	 * 
	 * @param loginPolicy - 로그인정책 model
	 */
	@Override
	public void insertLoginPolicy(LoginPolicy loginPolicy) {
		loginPolicyDAO.insertLoginPolicy(loginPolicy);
	}

	/**
	 * 기 등록된 로그인정책 정보를 수정한다.
	 * 
	 * @param loginPolicy - 로그인정책 model
	 */
	@Override
	public void updateLoginPolicy(LoginPolicy loginPolicy) {
		loginPolicyDAO.updateLoginPolicy(loginPolicy);
	}

	/**
	 * 기 등록된 로그인정책 정보를 삭제한다.
	 * 
	 * @param loginPolicy - 로그인정책 model
	 */
	@Override
	public void deleteLoginPolicy(LoginPolicy loginPolicy) {
		loginPolicyDAO.deleteLoginPolicy(loginPolicy);
	}

	/**
	 * 로그인정책에 대한 현재 반영되어 있는 결과를 조회한다.
	 * 
	 * @param loginPolicyVO - 로그인정책 VO
	 * @return LoginPolicyVO - 로그인정책 VO
	 */
	/*
	 * public LoginPolicyVO selectLoginPolicyResult(LoginPolicyVO loginPolicyVO) {
	 * return loginPolicyDAO.selectLoginPolicyResult(loginPolicyVO); }
	 */
}
