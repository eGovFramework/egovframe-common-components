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
 *  수정일              수정자             수정내용
 *  ----------  --------    ---------------------------
 *  2014.12.04  표준프레임워크   최초 적용 (패키지 변경 및 소스 정리)
 *  2018.12.28  신용호             getDirectoryPathByDateType() Month의 범위를 1~12가 되도록 수정
 *  2022.11.16  신용호             보안코드 점검 및 수정
 * </pre>
 */
public class DirectoryPathManager {
	
	private static String fileStorePath = EgovProperties.getProperty("Globals.fileStorePath");
	
	public enum DIR_DATE_TYPE {
		DATE_POLICY_YYYY_MM_DD, DATE_POLICY_YYYY_MM, DATE_POLICY_YYYY
	};

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
		
		File file = new File(fileStorePath + EgovWebUtil.filePathBlackList(imageBaseDir + subDir) + FilenameUtils.getName(fileName));
		
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

}
