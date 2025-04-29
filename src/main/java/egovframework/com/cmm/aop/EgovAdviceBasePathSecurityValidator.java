package egovframework.com.cmm.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import lombok.extern.slf4j.Slf4j;

/**
 * EgovAdviceBasePathSecurityValidator Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.04.25
 * @version 4.3
 * @see
 * <pre>
 *
 *  수정일         수정자          수정내용
 *  ----------   -----------   ---------------------------
 *  2025.04.01   신용호          최초 생성
 *  
 *  - String basePath 파라미터에 대해 AOP를 이용하여 보안강화 체크를 한다.
 *  - 보안성을 위해 basePath는 ROOT Path를 지정할수 없다.
 *  - basePath에 대해 다음 경로가 추가되어 화이트리스트 방식으로 점검한다. (필요시 화이트리스트를 추가한다)
 *    basePath가 다음 제한된 경로의 하위에 위치하는지 점검한다.
 *    1) Globals.fileStorePath # 파일 업로드 경로
 *    2) Globals.SynchrnServerPath # 파일 동기화 컴포넌트에서 사용할 파일 업로드 경로
 *
 */

@Slf4j
public class EgovAdviceBasePathSecurityValidator {
	
	public void beforeTargetMethod(JoinPoint thisJoinPoint) {
    	log.debug(" * AdviceBasePathValidator > beforeTargetMethod executed.");

        @SuppressWarnings("unused")
		Class<? extends Object> clazz = thisJoinPoint.getTarget().getClass();
        String className = thisJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = thisJoinPoint.getSignature().getName();

        // 현재 class, method 정보 및 method arguments 로깅
        log.debug("==> {}.{}()", className, methodName);
        
        // 메서드 파라미터 이름 가져오기
        MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        
        Object[] arguments = thisJoinPoint.getArgs();
        int argCount = 0;
        for (Object obj : arguments) {
        	String paramName = (parameterNames != null && argCount < parameterNames.length) ? parameterNames[argCount] : "arg" + argCount;
        	
        	log.debug(" - arg {} = {} : {} ", argCount, paramName, ToStringBuilder.reflectionToString(obj));
        	argCount++;
            // commons-lang 의 ToStringBuilder를 통해(reflection 을 이용)한 VO 정보 출력
        	
        	if (obj instanceof String) {

        	    log.debug(" - {} = \"{}\"", paramName, obj);
        		if ( "basePath".equals(paramName) ) {
        			if (!EgovFileBasePathSecurityValidator.validate(obj.toString())) {
        				throw new SecurityException("Unacceptable base path : " + obj);
        			}
        		}
        		
        	} else {
                // commons-lang 의 ToStringBuilder를 통해(reflection 을 이용)한 VO 정보 출력
        	    log.debug(" - {} = {}", paramName, ToStringBuilder.reflectionToString(obj));
        	}
        	
        }

    }

}
