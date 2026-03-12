package egovframework.com.sym.log.lgm.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import jakarta.annotation.Resource;

/**
 * 시스템 로그 생성을 위한 ASPECT 클래스
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이삼섭          최초 생성
 *   2011.07.01  이기하          패키지 분리(sym.log -> sym.log.lgm)
 *   2025.07.11  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UnnecessaryBoxing(불필요한 WrapperObject 생성)
 *
 *      </pre>
 */
public class EgovSysLogAspect {

	@Resource(name = "EgovSysLogService")
	private EgovSysLogService sysLogService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSysLogAspect.class);

	/**
	 * 시스템 로그정보를 생성한다. sevice Class의 insert로 시작되는 Method
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
		} finally {
			stopWatch.stop();
			saveSysLogSafely(joinPoint, stopWatch, "C");
		}

	}

	/**
	 * 시스템 로그정보를 생성한다. sevice Class의 update로 시작되는 Method
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

		} finally {
			stopWatch.stop();
			saveSysLogSafely(joinPoint, stopWatch, "U");
		}

	}

	/**
	 * 시스템 로그정보를 생성한다. sevice Class의 delete로 시작되는 Method
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

		} finally {
			stopWatch.stop();
			saveSysLogSafely(joinPoint, stopWatch, "D");
		}

	}

	/**
	 * 시스템 로그정보를 생성한다. sevice Class의 select로 시작되는 Method
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

		} finally {
			stopWatch.stop();
			saveSysLogSafely(joinPoint, stopWatch, "R");
		}

	}
	// 2026.03.09 KISA 취약점 조치
	private void saveSysLogSafely(ProceedingJoinPoint joinPoint, StopWatch stopWatch, String processSeCode) {
		String className =  "unknown";
		String methodName = "unknown";
		try {
			Object target = joinPoint.getTarget();
			if (target != null) {
				className = target.getClass().getName();
			}

			if (joinPoint.getSignature() != null) {
				methodName = joinPoint.getSignature().getName();
			}
			SysLog sysLog = new SysLog();
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());

			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			if (loginVO != null) {
					sysLog.setRqesterId(loginVO.getUniqId());
					sysLog.setRqesterIp(loginVO.getIp());
			}

			sysLog.setSrvcNm(className);
			sysLog.setMethodNm(methodName);
			sysLog.setProcessSeCode(processSeCode);
			sysLog.setProcessTime(processTime);

			sysLogService.logInsertSysLog(sysLog);
		// PMD suppression: 호출 인터페이스(EgovSysLogService)가 throws Exception으로
		// 선언되어 있어 구체적인 예외 타입 지정 불가
		} catch (Exception logEx) { // NOPMD - EgovSysLogService 인터페이스가 throws Exception으로 선언되어 불가피
				// 로그 저장 실패가 원본 업무 예외를 덮지 않도록 별도 처리
				LOGGER.error("시스템 로그 저장 실패: {}.{}",  className, methodName, logEx);
		}
	}
}
