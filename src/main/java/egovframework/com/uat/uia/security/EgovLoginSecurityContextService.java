package egovframework.com.uat.uia.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.LoginVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class EgovLoginSecurityContextService {

	private final AuthenticationManager authenticationManager;
	private final SecurityContextRepository securityContextRepository;

	public EgovLoginSecurityContextService(AuthenticationManager authenticationManager,
			SecurityContextRepository securityContextRepository) {
		this.authenticationManager = authenticationManager;
		this.securityContextRepository = securityContextRepository;
	}

	public void saveAuthenticatedSecurityContext(LoginVO resultVO, HttpServletRequest request,
			HttpServletResponse response) {
		UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
				.unauthenticated(resultVO.getUserSe().concat(resultVO.getId()), resultVO.getUniqId());

		Authentication authResult = authenticationManager.authenticate(token);

		UsernamePasswordAuthenticationToken authenticatedToken = new UsernamePasswordAuthenticationToken(resultVO,
				authResult.getCredentials(), authResult.getAuthorities());

		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authenticatedToken);
		SecurityContextHolder.setContext(context);
		securityContextRepository.saveContext(context, request, response);
	}
}
