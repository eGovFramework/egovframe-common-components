package egovframework.com.uat.sso.service;

import java.io.IOException;

import egovframework.com.cmm.LoginVO;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * 
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 2.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information) 
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 2.    서준식        최초생성
 *  
 *  </pre>
 */

public interface EgovSSOService {
	
	/**
	 * SSO 통합 인증 서버에 인증여부를 확인 하는 메서드
	 * 
	 */
	public boolean hasTokenInSSOServer(ServletRequest request, ServletResponse response);
	
	/**
	 * SSO 통합 인증 서버에 인증 토큰 생성을 요청하는 메서드
	 * 
	 */
	public void requestIssueToken(ServletRequest request, ServletResponse response) throws Exception ;
	
	
	/**
	 * SSO 통합 인증 서버에 인증이 된 경우 인증 서버의 토큰을 활용하여 로컬 로그인을 처리하는 메서드
	 * 
	 */
	public void ssoLoginByServer(ServletRequest request, ServletResponse response) throws Exception;

	
	/**
	 * 토큰 정보를 바탕으로  loginVO 객체를 생성하는 메서드
	 * 
	 */
	public LoginVO getLoginVO(ServletRequest request, ServletResponse response);
	
	/**
	 * SSO 통합 인증 서버에 글로벌 로그아웃(토큰 삭제)을 요청하는 메서드
	 * 
	 */
	public void ssoLogout(ServletRequest request, ServletResponse response, String returnURL) throws IOException;
}
