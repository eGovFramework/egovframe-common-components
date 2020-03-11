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
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.service.EgovProperties;

/**
 * Created by guava on 1/20/14.
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
 *  2014.12.04	표준프레임워크	최초 적용 (패키지 변경 및 소스 정리)
 *  2018.12.28	신용호             saveFile() 파라미터 수정
 * </pre>
 */
public class DefaultFileSaveManager implements FileSaveManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovProperties.class);
	
	@Override
	public String saveFile(FileItem fileItem, String imageBaseDir) {
		String originalFileName = FilenameUtils.getName(fileItem.getName());
		String relUrl;
		// filename
		String subDir = File.separator + DirectoryPathManager.getDirectoryPathByDateType(DirectoryPathManager.DIR_DATE_TYPE.DATE_POLICY_YYYY_MM);
		String fileName = RandomStringUtils.randomAlphanumeric(20) + "." + StringUtils.lowerCase(StringUtils.substringAfterLast(originalFileName, "."));

		File newFile = new File(imageBaseDir + subDir + fileName);
		File fileToSave = DirectoryPathManager.getUniqueFile(newFile.getAbsoluteFile());

		try {
			FileUtils.writeByteArrayToFile(fileToSave, fileItem.get());
		} catch (IOException e) {
			//KISA 보안약점 조치 (2018-10-29, 윤창원)
			LOGGER.debug("File IO exception" + e.getMessage());
		}

		String savedFileName = FilenameUtils.getName(fileToSave.getAbsolutePath());
		relUrl = StringUtils.replace(subDir, "\\", "/") + savedFileName;

		return relUrl;
	}
}
