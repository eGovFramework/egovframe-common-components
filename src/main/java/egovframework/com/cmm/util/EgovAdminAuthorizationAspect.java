package egovframework.com.cmm.util;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

public class EgovAdminAuthorizationAspect {

	public void assertAdmin() throws ModelAndViewDefiningException {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			ModelAndView modelAndView = new ModelAndView("redirect:/uat/uia/egovLoginUsr.do");
			throw new ModelAndViewDefiningException(modelAndView);
		}

		List<String> authorities = EgovUserDetailsHelper.getAuthorities();
		if (authorities == null || !authorities.contains("ROLE_ADMIN")) {
			ModelAndView modelAndView = new ModelAndView("redirect:/sec/ram/accessDenied.do");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
}
