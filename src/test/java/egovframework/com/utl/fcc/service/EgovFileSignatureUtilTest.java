/*
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * {@link EgovFileSignatureUtil}의 매직넘버 기반 확장자 위·변조 탐지를 검증한다.
 */
class EgovFileSignatureUtilTest {

	private static final byte[] PDF = { 0x25, 0x50, 0x44, 0x46, 0x2D, 0x31, 0x2E, 0x37 }; // %PDF-1.7
	private static final byte[] PNG = { (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A };
	private static final byte[] HWP_CFBF = { (byte) 0xD0, (byte) 0xCF, 0x11, (byte) 0xE0, (byte) 0xA1, (byte) 0xB1,
			0x1A, (byte) 0xE1 };
	private static final byte[] HWPX_ZIP = { 0x50, 0x4B, 0x03, 0x04, 0x14, 0x00, 0x00, 0x00 };
	private static final byte[] JSP_TEXT = "<%@ page %>".getBytes();

	@Test
	void matches_genuineFiles() {
		assertTrue(EgovFileSignatureUtil.matches(PDF, "report.pdf"));
		assertTrue(EgovFileSignatureUtil.matches(PNG, "image.PNG"), "확장자 대소문자 무관");
		assertTrue(EgovFileSignatureUtil.matches(HWP_CFBF, "doc.hwp"));
		assertTrue(EgovFileSignatureUtil.matches(HWPX_ZIP, "doc.hwpx"));
	}

	@Test
	void matches_detectsSpoofedExtension() {
		// jsp 텍스트를 .jpg로 위장
		assertFalse(EgovFileSignatureUtil.matches(JSP_TEXT, "evil.jpg"), "위장된 확장자는 탐지되어야 한다");
		// PNG 내용을 .pdf로 위장
		assertFalse(EgovFileSignatureUtil.matches(PNG, "fake.pdf"));
	}

	@Test
	void matches_unknownExtension_passes() {
		// 등록되지 않은 확장자는 검증 대상이 아니라 통과
		assertTrue(EgovFileSignatureUtil.matches(JSP_TEXT, "note.txt"));
		assertTrue(EgovFileSignatureUtil.matches(JSP_TEXT, "noextension"));
	}

	@Test
	void matches_nullOrShortContent() {
		assertFalse(EgovFileSignatureUtil.matches((byte[]) null, "a.pdf"));
		assertFalse(EgovFileSignatureUtil.matches(new byte[] { 0x25 }, "a.pdf"), "시그니처보다 짧으면 불일치");
	}

	@Test
	void matches_stream() throws IOException {
		assertTrue(EgovFileSignatureUtil.matches(new ByteArrayInputStream(PDF), "report.pdf"));
		assertFalse(EgovFileSignatureUtil.matches(new ByteArrayInputStream(JSP_TEXT), "evil.jpg"));
	}

	@Test
	void detect_returnsCanonicalType() {
		assertEquals("pdf", EgovFileSignatureUtil.detect(PDF));
		assertEquals("png", EgovFileSignatureUtil.detect(PNG));
		assertEquals("cfbf", EgovFileSignatureUtil.detect(HWP_CFBF));
		assertEquals("zip", EgovFileSignatureUtil.detect(HWPX_ZIP));
		assertNull(EgovFileSignatureUtil.detect(JSP_TEXT));
	}
}
