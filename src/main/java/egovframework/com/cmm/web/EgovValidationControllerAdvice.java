package egovframework.com.cmm.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import egovframework.com.cmm.EgovMessageSource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ValidationException;
import org.springframework.context.NoSuchMessageException;

/**
 * Validation 오류 메시지를 공통으로 처리하는 ControllerAdvice
 *
 * @author 공통서비스 개발팀
 * @since 2024
 */
@ControllerAdvice
public class EgovValidationControllerAdvice {

	@Autowired
	private EgovMessageSource egovMessageSource;

	/**
	 * Jakarta Bean Validation을 수행하는 커스텀 Validator
	 */
	private class JakartaBeanValidator implements Validator {
		private final jakarta.validation.Validator jakartaValidator;

		public JakartaBeanValidator() {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			this.jakartaValidator = factory.getValidator();
		}

		@Override
		public boolean supports(Class<?> clazz) {
			// 모든 클래스에 대해 Jakarta Bean Validation을 수행
			return true;
		}

		@Override
		public void validate(Object target, Errors errors) {
			if (target == null) {
				return;
			}

			try {
				// Jakarta Bean Validation 수행
				Set<ConstraintViolation<Object>> violations = jakartaValidator.validate(target);

				// BindingResult에 에러 추가
				if (errors instanceof BindingResult) {
					BindingResult bindingResult = (BindingResult) errors;
					for (ConstraintViolation<Object> violation : violations) {
						try {
							String fieldName = violation.getPropertyPath().toString();
							String message = violation.getMessage();

							// 어노테이션 타입 확인
							Class<?> annotationType = violation.getConstraintDescriptor().getAnnotation().annotationType();

							// 어노테이션의 message 속성 가져오기
							String annotationMessage = getAnnotationMessage(violation);

							// 메시지 키를 실제 메시지로 변환
							String resolvedMessage = resolveMessage(message, fieldName, annotationType, annotationMessage, violation);

							bindingResult.rejectValue(fieldName, "validation.egov.error." + fieldName, resolvedMessage);
							// 2026.02.28 KISA 취약점 조치
						} catch (RuntimeException e) {
							// 개별 처리 중 예외 발생 시 로깅하고 계속 진행
							LoggerFactory.getLogger(EgovValidationControllerAdvice.class)
								.error("Error processing validation for field: " + violation.getPropertyPath(), e);
						}
					}
				}
				// 2026.02.28 KISA 취약점 조치
			} catch (ValidationException | IllegalArgumentException e) {
				// Validation 전체 과정에서 예외 발생 시 로깅
				LoggerFactory.getLogger(EgovValidationControllerAdvice.class)
					.error("Error during validation", e);
					throw new IllegalStateException("Validation processing failed", e);
			}
		}
	}

	/**
	 * 어노테이션의 message 속성 가져오기
	 * 사용자가 명시적으로 message 속성을 지정한 경우에만 값을 반환하고,
	 * 그렇지 않으면 null을 반환합니다.
	 *
	 * @param violation ConstraintViolation 객체
	 * @return 어노테이션의 message 속성 값 (사용자가 지정한 경우), null (지정하지 않은 경우)
	 */
	private String getAnnotationMessage(ConstraintViolation<Object> violation) {
		try {
			Annotation annotation = violation.getConstraintDescriptor().getAnnotation();
			Class<?> annotationClass = annotation.annotationType();
			java.lang.reflect.Method messageMethod = annotationClass.getMethod("message");

			// 어노테이션 인터페이스의 기본값 확인
			Object defaultValue = messageMethod.getDefaultValue();
			Object messageValue = messageMethod.invoke(annotation);

			if (messageValue == null) {
				return null;
			}

			String message = messageValue.toString();

			// 빈 문자열이면 null 반환
			if (message.isEmpty()) {
				return null;
			}

			// 기본값과 비교하여 기본값이면 null 반환
			if (defaultValue != null) {
				String defaultMessage = defaultValue.toString();
				if (message.equals(defaultMessage)) {
					// 기본값과 동일하면 사용자가 지정하지 않은 것으로 간주
					return null;
				}
			}

			// Jakarta Bean Validation의 기본 메시지 키 패턴인 경우도 null 반환
			// (예: "{jakarta.validation.constraints.NotNull.message}" 같은 기본값)
			if (message.startsWith("{jakarta.validation") ||
			    message.startsWith("{javax.validation") ||
			    message.startsWith("{org.egovframe.rte")) {
				// 기본 메시지 키 패턴이면 null 반환
				return null;
			}

			// 사용자가 명시적으로 지정한 메시지
			return message;
			// 2026.02.28 KISA 취약점 조치
		} catch (ReflectiveOperationException | SecurityException e) {
			return null;
		}
	}

	/**
	 * 메시지 내의 {키이름} 패턴을 메시지 소스에서 찾아서 대체
	 *
	 * @param message 원본 메시지
	 * @return 대체된 메시지
	 */
	private String replaceMessageKeys(String message) {
		if (message == null || message.isEmpty()) {
			return message;
		}

		// {키이름} 패턴을 찾아서 대체
		Pattern pattern = java.util.regex.Pattern.compile("\\{([^}]+)\\}");
		Matcher matcher = pattern.matcher(message);
		StringBuffer result = new StringBuffer();

		while (matcher.find()) {
			String messageKey = matcher.group(1);
			try {
				String replacement = egovMessageSource.getMessage(messageKey);
				matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
				// 2026.02.28 KISA 취약점 조치
			} catch (NoSuchMessageException e) {
				// 메시지 키를 찾을 수 없는 경우 원본 유지
				matcher.appendReplacement(result, matcher.group(0));
			}
		}
		matcher.appendTail(result);

		return result.toString();
	}

	/**
	 * 메시지 키를 실제 메시지로 변환
	 *
	 * @param message 기본(Default) 메시지 (키 또는 실제 메시지)
	 * @param fieldName 필드명
	 * @param annotationType 어노테이션 타입
	 * @param annotationMessage 어노테이션의 message 속성 값
	 * @param violation ConstraintViolation 객체
	 * @return 변환된 메시지
	 */
	private String resolveMessage(String defaultMessage, String fieldName, Class<?> annotationType, String annotationMessage, ConstraintViolation<Object> violation) {
		if (defaultMessage == null) {
			return "";
		}

		// @Size 어노테이션은 별도 처리
		if (annotationType != null && "Size".equals(annotationType.getSimpleName())) {
			String sizeMessage = resolveSizeConstraintMessage(defaultMessage, fieldName, annotationMessage, violation);
			if (sizeMessage != null) {
				return sizeMessage;
			}
		}

		// 사용자지정 메시지가 없으면
		if ( annotationMessage == null ) {
			// 어노테이션 타입으로 직접 판단
			if (annotationType != null) {
				String annotationName = annotationType.getSimpleName();

				// EgovNullCheck
				if (annotationName.equals("EgovNullCheck")) {
					return getValidationMessage("validation.required", defaultMessage); // 필수 입력항목입니다.
				}
				// EgovCnCheck - 법인등록번호
				else if (annotationName.equals("EgovCnCheck")) {
					return getValidationMessage("validation.cn.check", defaultMessage); // 법인등록번호 유효성 오류입니다.
				}
				// EgovCrnCheck - 사업자등록번호
				else if (annotationName.equals("EgovCrnCheck")) {
					return getValidationMessage("validation.crn.check", defaultMessage); // 사업자등록번호 유효성 오류입니다.
				}
				// EgovEmailCheck - 이메일
				else if (annotationName.equals("EgovEmailCheck")) {
					return getValidationMessage("validation.email.check", defaultMessage); // 이메일 형식 오류입니다.
				}
				// EgovEnglishCheck - 영문만
				else if (annotationName.equals("EgovEnglishCheck")) {
					return getValidationMessage("validation.english.check", defaultMessage); // 영문만 허용됩니다.
				}
				// EgovIPCheck - IP 형식
				else if (annotationName.equals("EgovIPCheck")) {
					return getValidationMessage("validation.ip.check", defaultMessage); // IP 형식 오류입니다.
				}
				// EgovKoreanCheck - 한글만
				else if (annotationName.equals("EgovKoreanCheck")) {
					return getValidationMessage("validation.korean.check", defaultMessage); // 한글만 허용됩니다.
				}
				// EgovMobilePhoneCheck - 휴대폰 번호
				else if (annotationName.equals("EgovMobilePhoneCheck")) {
					return getValidationMessage("validation.mobile.phone.check", defaultMessage); // 휴대폰 번호만 허용됩니다.
				}
				// EgovPhoneCheck - 전화 번호
				else if (annotationName.equals("EgovPhoneCheck")) {
					return getValidationMessage("validation.phone.check", defaultMessage); // 전화 번호만 허용됩니다.
				}
				// EgovPwdCheck - 비밀번호
				else if (annotationName.equals("EgovPwdCheck")) {
					return getValidationMessage("validation.pwd.check", defaultMessage); // 비밀번호 유효성 검증 오류입니다. 8자 이상 20자 이하, 공백 없는 영문자, 숫자, 특수문자의 조합 필수(동일한 문자열 3개 불가, 연속된 문자열 3개 불가)
				}
				// EgovRrnCheck - 주민등록번호
				else if (annotationName.equals("EgovRrnCheck")) {
					return getValidationMessage("validation.rrn.check", defaultMessage); // 주민등록번호 무결성 체크 오류입니다. (2020년 10월 이전)
				}
				// 기타 어노테이션
				else {
					// 어노테이션의 default message 사용
					return defaultMessage;
				}
			}

		} else {
			// 메시지 내에 {키이름} 패턴이 포함된 경우 (예: "{cop.nttCn}은 필수야." 또는 전체가 "{cop.nttCn}"인 경우)
			// replaceMessageKeys()를 사용하여 모든 {키이름} 패턴을 대체
			return replaceMessageKeys(annotationMessage);
		}

		// 기본: 필드 레이블과 함께 메시지 반환
		return defaultMessage;

	}

	/**
	 * @Size 유효성 검사에 대한 메시지 생성
	 */
	private String resolveSizeConstraintMessage(String defaultMessage, String fieldName, String annotationMessage, ConstraintViolation<Object> violation) {

		Integer max = getAnnotationValue(violation, "max");
		Integer min = getAnnotationValue(violation, "min");
		int valueLength = getValueLength(violation.getInvalidValue());

		// min 조건 판단
		boolean belowMin = (min != null && min > 0 && valueLength >= 0 && valueLength < min);
		// max 조건 판단
		boolean exceedMax = (max != null && max != Integer.MAX_VALUE && valueLength >= 0 && valueLength > max);

		// min 위반이면 minlength 메시지 사용
		if (belowMin) {
			return getValidationMessage("validation.size.min", "", new Object[]{min});
		}

		// max 위반이면 maxlength 메시지 사용
		if (exceedMax) {
			return getValidationMessage("validation.size.max", "", new Object[]{max});
		}

		// 위반 조건이 없으면 기본 메시지 반환
		return defaultMessage;
	}

	/**
	 * Size 대상 값의 길이 계산
	 */
	private int getValueLength(Object value) {
		if (value == null) {
			return -1;
		}
		if (value instanceof CharSequence) {
			return ((CharSequence) value).length();
		}
		if (value instanceof Collection) {
			return ((Collection<?>) value).size();
		}
		if (value instanceof Map) {
			return ((Map<?, ?>) value).size();
		}
		if (value.getClass().isArray()) {
			return Array.getLength(value);
		}
		return -1;
	}

	/**
	 * 어노테이션의 속성 값 추출
	 *
	 * @param violation ConstraintViolation 객체
	 * @param attributeName 속성명 (예: "max", "min")
	 * @return 속성 값 (Integer), 없으면 null
	 */
	// 2026.02.28 KISA 취약점 조치
	private Integer getAnnotationValue(
			ConstraintViolation<Object> violation,
			String attributeName) {

			if (violation == null || attributeName == null || attributeName.isEmpty()) {
			return null;
			}

			var descriptor = violation.getConstraintDescriptor();
			if (descriptor == null) {
			return null;
			}

			var attributes = descriptor.getAttributes();
			if (attributes == null) {
			return null;
			}

			Object value = attributes.get(attributeName);

			if (value instanceof Number) {
			return ((Number) value).intValue();
			}

			return null;
			}
	/**
	 * Validation 메시지를 properties 파일에서 조회
	 *
	 * @param messageKey 메시지 키
	 * @param defaultMessage 기본 메시지 (키를 찾을 수 없는 경우)
	 * @return 메시지
	 */
	private String getValidationMessage(String messageKey, String defaultMessage) {
		// 2026.02.28 KISA 취약점 조치
			return egovMessageSource.getReloadableResourceBundleMessageSource().getMessage(messageKey, null, defaultMessage,
					Locale.getDefault());

	}

	/**
	 * Validation 메시지를 properties 파일에서 조회 (파라미터 포함)
	 *
	 * @param messageKey 메시지 키
	 * @param defaultMessage 기본 메시지 (키를 찾을 수 없는 경우)
	 * @param args 메시지 파라미터
	 * @return 메시지
	 */
	private String getValidationMessage(String messageKey, String defaultMessage, Object[] args) {
		try {
			return egovMessageSource.getReloadableResourceBundleMessageSource().getMessage(messageKey, args, defaultMessage,
					Locale.getDefault());
					// 2026.02.28 KISA 취약점 조치
		} catch (RuntimeException e) {
			// 파라미터가 있는 경우 기본 메시지에 파라미터 대체
			if (args != null && args.length > 0 && defaultMessage != null) {
				String result = defaultMessage;
				for (int i = 0; i < args.length; i++) {
					result = result.replace("{" + i + "}", String.valueOf(args[i]));
				}
				return result;
			}
			return defaultMessage;
		}
	}

	/**
	 * 모든 Controller의 바인딩에 Jakarta Bean Validation을 자동으로 적용
	 * @Valid가 작동하지 않는 경우를 대비하여 글로벌하게 validation 수행
	 *
	 * 주석 처리: @Validated의 groups 기능과 충돌하므로 Spring 기본 validation 사용
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Jakarta Bean Validation을 수행하는 커스텀 Validator 추가
		binder.addValidators(new JakartaBeanValidator());
	}
}
