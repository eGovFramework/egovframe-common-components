package egovframework.com.uss.umt.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import egovframework.com.uss.umt.service.MberManageInsertVO;
import egovframework.com.uss.umt.service.MberManageVO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * mberNm(회원명) 국제 실명 지원 검증 테스트.
 * 기존 50자 제한을 유지하면서 한글/영문 전용이 아닌 유니코드 전체 문자,
 * 하이픈, 아포스트로피, 내부 공백을 허용하는지 확인한다.
 */
class MberManageVOMberNmTest {

	private static ValidatorFactory factory;
	private static Validator validator;

	@BeforeAll
	static void setUpValidator() {
		// Avoid EL-based message interpolation, which requires an EL implementation
		// that may not be on the test classpath; property validity is what's under test.
		factory = Validation.byProvider(org.hibernate.validator.HibernateValidator.class)
				.configure()
				.messageInterpolator(new ParameterMessageInterpolator())
				.buildValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	static void closeFactory() {
		factory.close();
	}

	@Test
	void acceptsInternationalNamesWithinFiftyCharacters() {
		String[] names = {
				"Alexandria Catherine Montgomery-Smythe", // 39 chars, longer than the 20-char mberId limit
				"Muhammad Abdul Rahman Al-Fulan",
				"Jean-Pierre d'Arcy", // straight apostrophe
				"O’Connor", // typographic apostrophe
				"José María Álvarez", // Latin diacritics
				"Nguyễn Thị Minh Khai", // Vietnamese
				"Абдуллаев Джамшид", // Cyrillic
				"محمد عبد الرحمن", // Arabic
				"王小明", // CJK
				"홍길동" // existing Korean name still works
		};

		for (String name : names) {
			assertFieldValid(name, "expected mberNm to be accepted: " + name);
		}
	}

	@Test
	void acceptsNameAtMaximumLength() {
		String name = "A".repeat(50);
		assertFieldValid(name, "50-character name should be accepted");
	}

	@Test
	void rejectsNameBeyondMaximumLength() {
		String name = "A".repeat(51);
		assertFieldInvalid(name, "51-character name should be rejected");
	}

	@Test
	void acceptsMultiByteScriptNameAtMaximumLength() {
		// Regression guard for byte-vs-character DB semantics: 50 Hangul characters
		// (150 bytes in UTF-8) must validate as 50 characters, not be rejected or
		// truncated as if the limit were byte-based.
		String hangulName = "가".repeat(50);
		assertFieldValid(hangulName, "50 Hangul characters should be accepted as 50 characters, not bytes");

		String cjkName = "王".repeat(50);
		assertFieldValid(cjkName, "50 CJK characters should be accepted as 50 characters, not bytes");
	}

	@Test
	void rejectsEmptyName() {
		assertFieldInvalid("", "empty mberNm should be rejected");
	}

	@Test
	void whitespaceOnlyNamePassesNullCheckButIsNotUseful() {
		// @EgovNullCheck uses ObjectUtils.isEmpty, which treats "   " as non-empty.
		// Document current Bean Validation behavior; blank names are a separate concern.
		assertFieldValid("   ", "whitespace-only mberNm currently passes @EgovNullCheck");
	}

	@Test
	void preservesInternalSpacesWithoutTruncation() {
		String name = "Jean Paul  Marie"; // multiple internal spaces, still under 50 chars
		MberManageVO vo = vo(name);
		assertEquals(name, vo.getMberNm(), "internal spaces must not be collapsed or trimmed away");
		assertFieldValid(name, "name with multiple internal spaces should be accepted");
	}

	@Test
	void insertVoInheritsSameMberNmConstraint() {
		MberManageInsertVO vo = new MberManageInsertVO();
		vo.setMberNm("Jean-Pierre d'Arcy");
		Set<ConstraintViolation<MberManageInsertVO>> violations = validator.validateProperty(vo, "mberNm");
		assertTrue(violations.isEmpty(), "MberManageInsertVO should accept international names via inherited mberNm");
	}

	private MberManageVO vo(String mberNm) {
		MberManageVO vo = new MberManageVO();
		vo.setMberNm(mberNm);
		return vo;
	}

	private void assertFieldValid(String mberNm, String message) {
		Set<ConstraintViolation<MberManageVO>> violations = validator.validateProperty(vo(mberNm), "mberNm");
		assertTrue(violations.isEmpty(), message + " - violations: " + violations);
	}

	private void assertFieldInvalid(String mberNm, String message) {
		Set<ConstraintViolation<MberManageVO>> violations = validator.validateProperty(vo(mberNm), "mberNm");
		assertFalse(violations.isEmpty(), message);
	}

}
