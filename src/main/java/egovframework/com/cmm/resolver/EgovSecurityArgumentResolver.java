package egovframework.com.cmm.resolver;

import java.util.Iterator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Map타입 적용 파라미터 복호화를 위한 Custom ArgumentResolver 클래스
 * 
 * @author 표준프레임워크팀 이삼섭
 * @since 2024.07.09
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자        수정내용
 *  ----------     --------    ---------------------------
 *  2024.07.09     신용호        Map 타입에서 noteId 복호화 적용을 위한 ArgumentResolver 추가
 *
 *      </pre>
 */

public class EgovSecurityArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		return EgovSecurityMap.class.isAssignableFrom(parameter.getParameterType());
	}
	
	@Override 
	public Object resolveArgument(MethodParameter parameter
									, ModelAndViewContainer mavContainer
									, NativeWebRequest webRequest
									, WebDataBinderFactory binderFactory) throws Exception {
		
		EgovSecurityMap securityMap = new EgovSecurityMap();
		for(Iterator<String> iterator = webRequest.getParameterNames(); iterator.hasNext();) {
			String key = iterator.next();
			securityMap.put(key, webRequest.getParameter(key));
		}
		return securityMap;
	}

}
