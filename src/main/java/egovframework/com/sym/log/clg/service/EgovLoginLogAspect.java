package egovframework.com.sym.log.clg.service;

import javax.annotation.Resource;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * 시스템 로그 생성을 위한 ASPECT 클래스
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이삼섭          최초 생성
 *   2011.07.01  이기하          패키지 분리(sym.log -> sym.log.clg)
 *   2025.07.09  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UnnecessaryBoxing(불필요한 WrapperObject 생성)
 *
 *      </pre>
 */
public class EgovLoginLogAspect {

	@Resource(name = "EgovLoginLogService")
	private EgovLoginLogService loginLogService;

	/**
	 * 로그인 로그정보를 생성한다. EgovLoginController.actionMain Method
	 * 
	 * @param
	 * @return void
	 * @throws Exception
	 */
	public void logLogin() throws Throwable {

		String uniqId = null;
		String ip = null;

		/* Authenticated  */
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			uniqId = loginVO.getUniqId();
			ip = loginVO.getIp();
		}

		LoginLog loginLog = new LoginLog();
		loginLog.setLoginId(uniqId);
		loginLog.setLoginIp(ip);
		loginLog.setLoginMthd("I"); // 로그인:I, 로그아웃:O
		loginLog.setErrOccrrAt("N");
		loginLog.setErrorCode("");
		loginLogService.logInsertLoginLog(loginLog);

	}

	/**
	 * 로그아웃 로그정보를 생성한다. EgovLoginController.actionLogout Method
	 * 
	 * @param
	 * @return void
	 * @throws Exception
	 */
	public void logLogout() throws Throwable {

		String uniqId = null;
		String ip = null;

		/* Authenticated  */
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			uniqId = loginVO.getUniqId();
			ip = loginVO.getIp();
		}

		LoginLog loginLog = new LoginLog();
		loginLog.setLoginId(uniqId);
		loginLog.setLoginIp(ip);
		loginLog.setLoginMthd("O"); // 로그인:I, 로그아웃:O
		loginLog.setErrOccrrAt("N");
		loginLog.setErrorCode("");
		loginLogService.logInsertLoginLog(loginLog);
	}

}