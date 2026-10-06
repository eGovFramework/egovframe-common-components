package egovframework.com.cmm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.uss.ion.noi.service.NotificationVO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * EgovValidationControllerAdvice가 사용자 지정 message를 어노테이션 종류와 무관하게 존중하는지 확인한다.
 *
 * resolveMessage()는 annotationMessage가 있으면 replaceMessageKeys()로 해석해 돌려주지만,
 * @Size만 그 판단 앞에서 resolveSizeConstraintMessage()로 새어나가 공용 validation.size.min/max
 * 메시지로 덮어쓴다. 아래 두 테스트는 동일한 message 속성을 @Size와 @NotBlank에 각각 붙여
 * 그 비대칭을 드러낸다.
 */
class EgovValidationControllerAdviceSizeMessageTest {

	private static final String CUSTOM_KEY = "ussIonNoi.notificationVO.bhNtfcIntrvlRequired";

	private Locale savedLocale;
	private ReloadableResourceBundleMessageSource bundle;
	private EgovValidationControllerAdvice advice;

	@BeforeEach
	void setUp() throws Exception {
		savedLocale = Locale.getDefault();
		Locale.setDefault(Locale.KOREA);

		bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasenames("classpath:/egovframework/message/com/uss/ion/noi/message",
				"classpath:/egovframework/message/com/message-validation");

		EgovMessageSource egovMessageSource = new EgovMessageSource();
		egovMessageSource.setReloadableResourceBundleMessageSource(bundle);

		advice = new EgovValidationControllerAdvice();
		Field field = EgovValidationControllerAdvice.class.getDeclaredField("egovMessageSource");
		field.setAccessible(true);
		field.set(advice, egovMessageSource);
	}

	@AfterEach
	void tearDown() {
		Locale.setDefault(savedLocale);
	}

	/** 형제 경로: @Size가 아닌 제약은 사용자 지정 message를 그대로 돌려준다. */
	@Test
	void nonSizeConstraint_keepsUserSuppliedMessage() {
		String actual = validateAndGetMessage(new Fixture(), "fixture", "blank");
		assertEquals(bundle.getMessage(CUSTOM_KEY, null, Locale.KOREA), actual);
	}

	/** @Size도 같은 message 속성을 같은 방식으로 존중해야 한다. */
	@Test
	void sizeConstraint_keepsUserSuppliedMessage() {
		String actual = validateAndGetMessage(new Fixture(), "fixture", "sized");
		assertEquals(bundle.getMessage(CUSTOM_KEY, null, Locale.KOREA), actual);
	}

	/** message 속성이 없는 @Size는 종전대로 공용 validation.size.max 메시지를 쓴다. */
	@Test
	void sizeConstraintWithoutMessage_stillUsesCommonSizeMessage() {
		String actual = validateAndGetMessage(new Fixture(), "fixture", "capped");
		assertEquals(bundle.getMessage("validation.size.max", new Object[] { 2 }, Locale.KOREA), actual);
	}

	/** 저장소에 실재하는 사례: NotificationVO.bhNtfcIntrvl. */
	@Test
	void notificationVO_bhNtfcIntrvl_keepsUserSuppliedMessage() {
		String actual = validateAndGetMessage(new NotificationVO(), "notificationVO", "bhNtfcIntrvl");
		assertEquals(bundle.getMessage(CUSTOM_KEY, null, Locale.KOREA), actual);
	}

	private String validateAndGetMessage(Object target, String objectName, String fieldName) {
		WebDataBinder binder = new WebDataBinder(target, objectName);
		advice.initBinder(binder);
		binder.validate();

		BindingResult result = binder.getBindingResult();
		assertNotNull(result.getFieldError(fieldName), fieldName + "에 검증 오류가 등록되지 않았다");
		return result.getFieldError(fieldName).getDefaultMessage();
	}

	public static class Fixture {

		@Size(min = 1, message = "{" + CUSTOM_KEY + "}")
		private String[] sized = new String[0];

		@NotBlank(message = "{" + CUSTOM_KEY + "}")
		private String blank = "";

		@Size(max = 2)
		private String capped = "abc";

		public String[] getSized() {
			return sized.clone();
		}

		public String getBlank() {
			return blank;
		}

		public String getCapped() {
			return capped;
		}
	}
}
