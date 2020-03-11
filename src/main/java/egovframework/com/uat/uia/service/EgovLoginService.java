package egovframework.com.uat.uia.service;

import java.util.Map;

import egovframework.com.cmm.LoginVO;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 인터페이스 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성 
 *  2011.08.26  서준식          EsntlId를 이용한 로그인 추가
 *  2017.07.21  장동한 			로그인인증제한 작업
 *  </pre>
 */
public interface EgovLoginService {
	
	/**
     * 2011.08.26
	 * EsntlId를 이용한 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO actionLoginByEsntlId(LoginVO vo) throws Exception;
	
	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    LoginVO actionLogin(LoginVO vo) throws Exception;
    
    /**
	 * 인증서 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    LoginVO actionCrtfctLogin(LoginVO vo) throws Exception;
    
    /**
	 * 아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    LoginVO searchId(LoginVO vo) throws Exception;
    
    /**
	 * 비밀번호를 찾는다.
	 * @param vo LoginVO
	 * @return boolean
	 * @exception Exception
	 */
    boolean searchPassword(LoginVO vo) throws Exception;
    
    
    /**
	 * 로그인인증제한을 처리한다.
	 * @param vo LoginVO
	 * @return boolean
	 * @exception Exception
	 */
    String processLoginIncorrect(LoginVO vo, Map<?,?> mapLockUserInfo) throws Exception;
    
    /**
	 * 로그인인증제한을 조회한다.
	 * @param vo LoginVO
	 * @param Map mapLockUserInfo
	 * @return boolean
	 * @exception Exception
	 */
    Map<?,?> selectLoginIncorrect(LoginVO vo) throws Exception;

}
