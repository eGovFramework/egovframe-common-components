/*
 * CKEditor image upload module for Java.
 * Copyright guavatak (https://github.com/guavatak/ckeditor-upload-filter-java)
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
 *
 * @author guavatak (https://github.com/guavatak/ckeditor-upload-filter-java)
 */
package egovframework.com.utl.wed.filter;

import java.io.File;
import java.util.Calendar;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.sun.star.auth.InvalidArgumentException;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;

/**
 *  이미지 저장 처리 클래스
 * @author guavatak
 * @since 2014.12.04
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일        수정자       수정내용
 *  ----------  --------    ---------------------------
 *  2014.12.04  표준프레임워크  최초 적용 (패키지 변경 및 소스 정리)
 *  2018.12.28  신용호        getDirectoryPathByDateType() Month의 범위를 1~12가 되도록 수정
 *  2022.11.16  신용호        보안코드 점검 및 수정
 * 	2025.08.30  송하영        2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UnnecessarySemicolon(불필요한 괄호사용)
 * </pre>
 */
public class DirectoryPathManager {

	private static String fileStorePath = EgovProperties.getProperty("Globals.fileStorePath");

	public enum DIR_DATE_TYPE {
		DATE_POLICY_YYYY_MM_DD, DATE_POLICY_YYYY_MM, DATE_POLICY_YYYY
	}

	/**
	 * 2012/12/22/
	 * @param dateType
	 * @return
	 * @throws InvalidArgumentException
	 */
	public static String getDirectoryPathByDateType(DIR_DATE_TYPE policy) {

		Calendar calendar = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		sb.append(calendar.get(Calendar.YEAR)).append(File.separator);
		if (policy.ordinal() <= DIR_DATE_TYPE.DATE_POLICY_YYYY_MM.ordinal()) {
			sb.append(StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MONTH)+1), 2, '0')).append(File.separator);
		}
		if (policy.ordinal() <= DIR_DATE_TYPE.DATE_POLICY_YYYY_MM_DD.ordinal()) {
			sb.append(StringUtils.leftPad(String.valueOf(calendar.get(Calendar.DATE)), 2, '0')).append(File.separator);
		}

		return sb.toString();
	}

	/**
	 * 주어진 기본 디렉터리, 하위 디렉터리 및 원래 파일 이름에 대한 고유한 파일 이름을 생성합니다.
	 * 이 메서드는 주어진 이름을 가진 파일이 이미 존재하는 경우 파일의 기본 이름에 순차 번호를 추가하여 새 이름이 생성되도록 보장합니다.
	 *
	 * @param imageBaseDir 파일이 저장될 예정인 기본 디렉터리.
	 * @param subDir 기본 디렉터리 아래의 하위 디렉터리.
	 * @param fileName 파일의 원래 이름.
	 * @return 고유한 파일 경로를 가리키는 File 객체. 주어진 이름의 파일이 이미 존재하면, 기본 이름에 숫자를 추가하여 고유한 파일 이름이 생성됩니다.
	 */
	public static File getUniqueFile(String imageBaseDir, String subDir, String fileName) {

		String basePath = EgovWebUtil.filePathBlackList(imageBaseDir + subDir) + FilenameUtils.getName(fileName);
		File file = new File(joinPath(fileStorePath, basePath));

		if (!file.exists())
			return file;

		File tmpFile = new File(file.getAbsolutePath());
		File parentDir = tmpFile.getParentFile();
		int count = 1;
		String extension = FilenameUtils.getExtension(tmpFile.getName());
		String baseName = FilenameUtils.getBaseName(tmpFile.getName());
		do {
			tmpFile = new File(parentDir, baseName + "_" + count++ + "_." + extension);
		} while (tmpFile.exists());
		return tmpFile;
	}

	/**
	 * base와 sub 사이에 구분자가 정확히 하나만 오도록 경로를 결합한다.
	 * (Globals.fileStorePath 뒤에 구분자가 없는 상태에서 ck.image.dir 값을 그대로 이어붙이면
	 *  예: "allinone" + "ck_image" => "allinoneck_image" 처럼 디렉터리명이 뒤섞이는 문제를 방지한다.)
	 *
	 * @param base 기본 경로 (예: Globals.fileStorePath)
	 * @param sub 하위 경로
	 * @return 구분자가 정확히 하나로 결합된 경로
	 */
	private static String joinPath(String base, String sub) {
		String normalizedBase = StringUtils.isBlank(base) ? "" : base;
		String normalizedSub = StringUtils.isBlank(sub) ? "" : sub;

		if (normalizedBase.isEmpty()) {
			return normalizedSub;
		}

		boolean baseEndsWithSep = normalizedBase.endsWith(File.separator) || normalizedBase.endsWith("/");
		boolean subStartsWithSep = normalizedSub.startsWith(File.separator) || normalizedSub.startsWith("/");

		if (baseEndsWithSep && subStartsWithSep) {
			return normalizedBase + normalizedSub.substring(1);
		} else if (!baseEndsWithSep && !subStartsWithSep && !normalizedSub.isEmpty()) {
			return normalizedBase + File.separator + normalizedSub;
		}
		return normalizedBase + normalizedSub;
	}

}
