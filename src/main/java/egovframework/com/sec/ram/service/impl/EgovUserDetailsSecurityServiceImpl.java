package egovframework.com.sec.ram.service.impl;

import java.util.List;

import egovframework.com.cmm.service.EgovUserDetailsService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

/**
 *권한관리 인증에 대한 ServiceImpl 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *
 * </pre>
 */

public class EgovUserDetailsSecurityServiceImpl extends EgovAbstractServiceImpl implements EgovUserDetailsService {

	
	/**
	 * 인증된 사용자객체를 VO형식으로 가져온다.
	 * @return Object - 사용자 ValueObject
	 */
	public Object getAuthenticatedUser() {
		
		// 이 메소드의 경우 인증이 되지 않더라고 null을 리턴하지 않기 때문에 
		// 명시적으로 인증되지 않은 경우 null을 리턴하도록 수정함
		
		if (EgovUserDetailsHelper.isAuthenticated()) {
			return EgovUserDetailsHelper.getAuthenticatedUser();	
		}
		
		return null;
	}

	
	/**
	 * 인증된 사용자의 권한 정보를 가져온다.
	 * 예) [ROLE_ADMIN, ROLE_USER, ROLE_A, ROLE_B, ROLE_RESTRICTED, IS_AUTHENTICATED_FULLY, IS_AUTHENTICATED_REMEMBERED, IS_AUTHENTICATED_ANONYMOUSLY]
	 * @return List - 사용자 권한정보 목록
	 */
	public List<String> getAuthorities() {
		return EgovUserDetailsHelper.getAuthorities();
	}
	
	/**
	 * 인증된 사용자 여부를 체크한다.
	 * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)	
	 */

	public Boolean isAuthenticated() {
		return EgovUserDetailsHelper.isAuthenticated();
	}

}
