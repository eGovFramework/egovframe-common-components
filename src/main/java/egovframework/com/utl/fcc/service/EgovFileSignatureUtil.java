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

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 파일 콘텐츠의 매직넘버(시그니처)를 검사해 선언된 확장자와 실제 내용이 일치하는지 검증하는 유틸리티.
 *
 * <p>확장자 문자열 화이트리스트만으로는 {@code evil.jsp}를 {@code .jpg}로 위장한 업로드를 막지 못한다.
 * 이 유틸은 파일 앞부분 바이트 시그니처를 확인해 확장자 위·변조를 탐지한다. 한국 공공에서 널리 쓰이는
 * HWP(CFBF)·HWPX(ZIP) 등 사무 문서 포맷을 포함한다.</p>
 *
 * <p>시그니처가 등록되지 않은 확장자는 이 유틸의 검증 대상이 아니므로 {@link #matches}가 true를 반환한다
 * (확장자 화이트리스트 등 별도 정책으로 통제한다).</p>
 *
 * @author z3rotig4r
 * @since 2026.07.27
 * @version 1.0
 */
public final class EgovFileSignatureUtil {

	/** 시그니처 판별에 필요한 헤더 최대 바이트 수. */
	private static final int HEADER_LENGTH = 8;

	/** 확장자(소문자) → 허용 시그니처(바이트 접두사) 목록. */
	private static final Map<String, List<byte[]>> EXT_SIGNATURES = new HashMap<>();

	private static final byte[] SIG_PDF = { 0x25, 0x50, 0x44, 0x46 }; // %PDF
	private static final byte[] SIG_JPG = { (byte) 0xFF, (byte) 0xD8, (byte) 0xFF };
	private static final byte[] SIG_PNG = { (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A };
	private static final byte[] SIG_GIF = { 0x47, 0x49, 0x46, 0x38 }; // GIF8
	private static final byte[] SIG_BMP = { 0x42, 0x4D }; // BM
	private static final byte[] SIG_ZIP = { 0x50, 0x4B, 0x03, 0x04 }; // PK.. (zip/hwpx/docx/xlsx/pptx/jar)
	private static final byte[] SIG_CFBF = { (byte) 0xD0, (byte) 0xCF, 0x11, (byte) 0xE0, (byte) 0xA1, (byte) 0xB1,
			0x1A, (byte) 0xE1 }; // hwp/doc/xls/ppt(구 오피스)

	static {
		register("pdf", SIG_PDF);
		register("jpg", SIG_JPG);
		register("jpeg", SIG_JPG);
		register("png", SIG_PNG);
		register("gif", SIG_GIF);
		register("bmp", SIG_BMP);
		register("zip", SIG_ZIP);
		register("hwpx", SIG_ZIP);
		register("docx", SIG_ZIP);
		register("xlsx", SIG_ZIP);
		register("pptx", SIG_ZIP);
		register("jar", SIG_ZIP);
		register("hwp", SIG_CFBF);
		register("doc", SIG_CFBF);
		register("xls", SIG_CFBF);
		register("ppt", SIG_CFBF);
	}

	private EgovFileSignatureUtil() {
	}

	private static void register(String ext, byte[]... signatures) {
		EXT_SIGNATURES.computeIfAbsent(ext, k -> new java.util.ArrayList<>()).addAll(Arrays.asList(signatures));
	}

	/**
	 * 파일 내용이 선언된 파일명(확장자)의 시그니처와 일치하는지 확인한다.
	 *
	 * @param content  파일 내용(최소 앞부분 바이트)
	 * @param fileName 확장자를 포함한 파일명
	 * @return 확장자에 등록된 시그니처와 일치하거나, 확장자가 등록 대상이 아니면 true. 위·변조로 불일치하면 false.
	 */
	public static boolean matches(byte[] content, String fileName) {
		String ext = extensionOf(fileName);
		List<byte[]> signatures = EXT_SIGNATURES.get(ext);
		if (signatures == null) {
			return true; // 검증 대상 확장자가 아님
		}
		if (content == null) {
			return false;
		}
		for (byte[] sig : signatures) {
			if (startsWith(content, sig)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 스트림의 앞부분을 읽어 선언된 확장자의 시그니처와 일치하는지 확인한다. 스트림 위치는 소비된다.
	 *
	 * @param in       파일 입력 스트림
	 * @param fileName 확장자를 포함한 파일명
	 * @return {@link #matches(byte[], String)} 참조
	 * @throws IOException 스트림 읽기 실패 시
	 */
	public static boolean matches(InputStream in, String fileName) throws IOException {
		if (in == null) {
			return false;
		}
		byte[] header = new byte[HEADER_LENGTH];
		int read = 0;
		int r;
		while (read < HEADER_LENGTH && (r = in.read(header, read, HEADER_LENGTH - read)) != -1) {
			read += r;
		}
		return matches(read == HEADER_LENGTH ? header : Arrays.copyOf(header, read), fileName);
	}

	/**
	 * 파일 내용의 시그니처로 판별한 표준 포맷명을 반환한다.
	 *
	 * @param content 파일 내용
	 * @return "pdf"/"jpg"/"png"/"gif"/"bmp"/"zip"/"cfbf" 중 하나, 판별 불가 시 null
	 */
	public static String detect(byte[] content) {
		if (content == null) {
			return null;
		}
		if (startsWith(content, SIG_PDF)) {
			return "pdf";
		}
		if (startsWith(content, SIG_JPG)) {
			return "jpg";
		}
		if (startsWith(content, SIG_PNG)) {
			return "png";
		}
		if (startsWith(content, SIG_GIF)) {
			return "gif";
		}
		if (startsWith(content, SIG_BMP)) {
			return "bmp";
		}
		if (startsWith(content, SIG_ZIP)) {
			return "zip";
		}
		if (startsWith(content, SIG_CFBF)) {
			return "cfbf";
		}
		return null;
	}

	private static boolean startsWith(byte[] content, byte[] prefix) {
		if (content.length < prefix.length) {
			return false;
		}
		for (int i = 0; i < prefix.length; i++) {
			if (content[i] != prefix[i]) {
				return false;
			}
		}
		return true;
	}

	private static String extensionOf(String fileName) {
		if (fileName == null) {
			return "";
		}
		int dot = fileName.lastIndexOf('.');
		if (dot < 0 || dot == fileName.length() - 1) {
			return "";
		}
		return fileName.substring(dot + 1).toLowerCase(Locale.ROOT);
	}
}
