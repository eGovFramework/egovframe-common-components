package egovframework.com.sym.log.lgm.service;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * @Class Name : EgovSysLogAspect.java
 * @Description : 시스템 로그 생성을 위한 ASPECT 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.lgm)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
public class EgovSysLogAspect {

	@Resource(name="EgovSysLogService")
	private EgovSysLogService sysLogService;

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 insert로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logInsert(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLog sysLog = new SysLog();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "C";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

	    	/* Authenticated  */
	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(isAuthenticated.booleanValue()) {
				LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
				uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
			    ip = (user == null || user.getIp() == null) ? "" : user.getIp();
	    	}

			sysLog.setSrvcNm(className);
			sysLog.setMethodNm(methodName);
			sysLog.setProcessSeCode(processSeCode);
			sysLog.setProcessTime(processTime);
			sysLog.setRqesterId(uniqId);
			sysLog.setRqesterIp(ip);

			sysLogService.logInsertSysLog(sysLog);

		}

	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 update로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logUpdate(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLog sysLog = new SysLog();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "U";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

	    	/* Authenticated  */
	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(isAuthenticated.booleanValue()) {
				LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
				uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
			    ip = (user == null || user.getIp() == null) ? "" : user.getIp();
	    	}

			sysLog.setSrvcNm(className);
			sysLog.setMethodNm(methodName);
			sysLog.setProcessSeCode(processSeCode);
			sysLog.setProcessTime(processTime);
			sysLog.setRqesterId(uniqId);
			sysLog.setRqesterIp(ip);

			sysLogService.logInsertSysLog(sysLog);

		}

	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 delete로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logDelete(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLog sysLog = new SysLog();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "D";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

	    	/* Authenticated  */
	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(isAuthenticated.booleanValue()) {
				LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
				uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
			    ip = (user == null || user.getIp() == null) ? "" : user.getIp();
	    	}

			sysLog.setSrvcNm(className);
			sysLog.setMethodNm(methodName);
			sysLog.setProcessSeCode(processSeCode);
			sysLog.setProcessTime(processTime);
			sysLog.setRqesterId(uniqId);
			sysLog.setRqesterIp(ip);

			sysLogService.logInsertSysLog(sysLog);

		}

	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 select로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logSelect(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLog sysLog = new SysLog();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "R";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

	    	/* Authenticated  */
	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(isAuthenticated.booleanValue()) {
				LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
				uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
				ip = (user == null || user.getIp() == null) ? "" : user.getIp();
	    	}

			sysLog.setSrvcNm(className);
			sysLog.setMethodNm(methodName);
			sysLog.setProcessSeCode(processSeCode);
			sysLog.setProcessTime(processTime);
			sysLog.setRqesterId(uniqId);
			sysLog.setRqesterIp(ip);

			sysLogService.logInsertSysLog(sysLog);

		}

	}

}
