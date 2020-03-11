package egovframework.com.cmm.service.impl;

import java.util.ArrayList;
import java.util.List;

import egovframework.com.cmm.service.EgovUserDetailsService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 6. 25.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information) 
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 12.    서준식        최초생성
 *  
 *  </pre>
 */

public class EgovUserDetailsSessionServiceImpl extends EgovAbstractServiceImpl implements EgovUserDetailsService {

	/**
	 * 인증된 사용자객체를 VO형식으로 가져온다.
	 * @return Object - 사용자 ValueObject
	 */
	public Object getAuthenticatedUser() {
		if (EgovUserDetailsHelper.isAuthenticated()) {
			return EgovUserDetailsHelper.getAuthenticatedUser();	
		}
		return null;
	}

	public List<String> getAuthorities() {
		// 권한 설정을 리턴한다.
		return EgovUserDetailsHelper.getAuthorities();
	}

	public Boolean isAuthenticated() {
		// 인증된 유저인지 확인한다.
		return EgovUserDetailsHelper.isAuthenticated();
	}

}
