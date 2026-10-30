package egovframework.com.cmm.interceptor;

import java.util.Collections;
import java.util.List;

import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 인증여부 체크 인터셉터
 * @author 공통서비스 개발팀 서준식
 * @since 2011.07.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  서준식          최초 생성
 *  2011.09.07  서준식          인증이 필요없는 URL을 패스하는 로직 추가
 *  2017.08.31  장동한          인증된 사용자 체크로직 변경 및 관리자 권한 체크 로직 추가
 *  2021.08.27  신용호          dummy모드 사용시 "60. 권한관리" 접근오류 수정
 *  2026.10.27                Spring Security 6.5 권장 PathPatternRequestMatcher로 전환
 *  </pre>
 */


public class AuthenticInterceptor implements HandlerInterceptor {

	/** 관리자 접근 권한 패턴 목록 */
	private List<String> adminAuthPatternList;

	/** 설정 시 파싱한 관리자 접근 권한 패턴 */
	private List<PathPatternRequestMatcher> adminAuthPatternMatchers;

	public List<String> getAdminAuthPatternList() {
		return adminAuthPatternList;
	}

	public void setAdminAuthPatternList(List<String> adminAuthPatternList) {
		this.adminAuthPatternList = Collections.unmodifiableList(adminAuthPatternList);
		PathPatternRequestMatcher.Builder matcherBuilder = PathPatternRequestMatcher.withDefaults();
		this.adminAuthPatternMatchers = adminAuthPatternList.stream().map(matcherBuilder::matcher).toList();
	}

	/**
	 * 인증된 사용자 여부로 인증 여부를 체크한다.
	 * 관리자 권한에 따라 접근 페이지 권한을 체크한다.
	 * @throws ModelAndViewDefiningException 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ModelAndViewDefiningException {
		//인증된사용자 여부
		boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		//미민증사용자 체크
		if(!isAuthenticated) {
			ModelAndView modelAndView = new ModelAndView("redirect:/uat/uia/egovLoginUsr.do");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		//인증된 권한 목록
		List<String> authList = EgovUserDetailsHelper.getAuthorities();
		//관리자인증여부
		boolean adminAuthUrlPatternMatcher = false;
		//관리자 URL 패턴 체크
		for(PathPatternRequestMatcher adminAuthPatternMatcher : adminAuthPatternMatchers){
			if(adminAuthPatternMatcher.matches(request)){
				adminAuthUrlPatternMatcher = true;
				break;
			}
		}
		//관리자 권한 체크
		if(adminAuthUrlPatternMatcher && !authList.contains("ROLE_ADMIN")){
			ModelAndView modelAndView = new ModelAndView("redirect:/uat/uia/egovLoginUsr.do?auth_error=1");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		return true;
	}

}
