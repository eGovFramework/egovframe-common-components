package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EgovPhoneFormatCheckTest {

	@ParameterizedTest(name = "telephone={0}, {1}, {2}")
	@MethodSource("invalidTelephoneParts")
	void rejectsInvalidTelephoneParts(String first, String middle, String last) {
		assertFalse(EgovFormatCheckUtil.checkFormatTell(first, middle, last));
	}

	@ParameterizedTest(name = "mobile={0}, {1}, {2}")
	@MethodSource("invalidMobileParts")
	void rejectsInvalidMobileParts(String first, String middle, String last) {
		assertFalse(EgovFormatCheckUtil.checkFormatCell(first, middle, last));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"-", "--", " ", "02123", "02123456789012", "02-abc-4567", "02-023-4567", "099-123-4567"})
	void rejectsInvalidTelephoneString(String value) {
		assertFalse(EgovFormatCheckUtil.checkFormatTell(value));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"-", "--", " ", "010123", "0101234567890", "010-abcd-5678", "010-0234-5678", "099-1234-5678"})
	void rejectsInvalidMobileString(String value) {
		assertFalse(EgovFormatCheckUtil.checkFormatCell(value));
	}

	@ParameterizedTest
	@MethodSource("validTelephoneParts")
	void acceptsTelephoneInAllOverloads(String first, String middle, String last) {
		assertTrue(EgovFormatCheckUtil.checkFormatTell(first, middle, last));
		assertTrue(EgovFormatCheckUtil.checkFormatTell(first + middle + last));
		assertTrue(EgovFormatCheckUtil.checkFormatTell(first + "-" + middle + "-" + last));
	}

	@ParameterizedTest
	@MethodSource("validMobileParts")
	void acceptsMobileInAllOverloads(String first, String middle, String last) {
		assertTrue(EgovFormatCheckUtil.checkFormatCell(first, middle, last));
		assertTrue(EgovFormatCheckUtil.checkFormatCell(first + middle + last));
		assertTrue(EgovFormatCheckUtil.checkFormatCell(first + "-" + middle + "-" + last));
	}

	static Stream<Arguments> invalidTelephoneParts() {
		return invalidParts("02", "123", "4567");
	}

	static Stream<Arguments> invalidMobileParts() {
		return invalidParts("010", "1234", "5678");
	}

	private static Stream<Arguments> invalidParts(String first, String middle, String last) {
		return Stream.concat(Stream.of(null, "", " ", "abc").flatMap(invalid -> Stream.of(
				Arguments.of(invalid, middle, last), Arguments.of(first, invalid, last), Arguments.of(first, middle, invalid))),
				Stream.of(Arguments.of(first, "12", last), Arguments.of(first, "12345", last),
						Arguments.of(first, middle, "123"), Arguments.of(first, middle, "12345"),
						Arguments.of(first, "023", last), Arguments.of("099", middle, last)));
	}

	static Stream<Arguments> validTelephoneParts() {
		return Stream.of(Arguments.of("02", "123", "4567"), Arguments.of("02", "1234", "5678"),
				Arguments.of("031", "123", "4567"), Arguments.of("0505", "123", "4567"));
	}

	static Stream<Arguments> validMobileParts() {
		return Stream.of(Arguments.of("010", "1234", "5678"), Arguments.of("011", "123", "4567"));
	}
}
