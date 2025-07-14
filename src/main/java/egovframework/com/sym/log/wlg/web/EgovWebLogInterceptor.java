package egovframework.com.sym.log.wlg.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.log.wlg.service.EgovWebLogService;
import egovframework.com.sym.log.wlg.service.WebLog;

/**
 * @Class Name : EgovWebLogInterceptor.java
 * @Description : 웹로그 생성을 위한 인터셉터 클래스
 * @Modification Information
 * 
 *               <pre>
 *    수정일        수정자         수정내용
 *    -------      -------     -------------------

 *               </pre>
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 9.
 * @version
 * @see
 *
 */
/**
 * 사용자 계정을 처리하는 비즈니스 구현 클래스
 * <p>
 * <b>NOTE:</b> Exception 종류를 EgovBizException, RuntimeException 에서만 동작한다.
 * fail.common.msg 메세지키가 Message Resource 에 정의 되어 있어야 한다.
 * 
 * @author 공통컴포넌트 개발팀 홍길동
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.09  이삼섭          최초 생성
 *   2011.07.01  이기하          패키지 분리(sym.log -> sym.log.wlg)
 *   2025.07.15  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UnnecessaryBoxing(불필요한 WrapperObject 생성)
 *
 *      </pre>
 */
public class EgovWebLogInterceptor implements HandlerInterceptor {

	@Resource(name = "EgovWebLogService")
	private EgovWebLogService webLogService;

	/**
	 * 웹 로그정보를 생성한다.
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response, Object
	 *                           handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modeAndView) throws Exception {

		WebLog webLog = new WebLog();
		String reqURL = request.getRequestURI();

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			webLog.setRqesterId(loginVO.getUniqId());
		}

		webLog.setUrl(reqURL);

		webLog.setRqesterIp(request.getRemoteAddr());

		webLogService.logInsertWebLog(webLog);

	}
}
