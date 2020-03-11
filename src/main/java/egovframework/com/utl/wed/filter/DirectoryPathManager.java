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
 * </pre>
 */
public class DirectoryPathManager {
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

	public static File getUniqueFile(final File file) {
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
