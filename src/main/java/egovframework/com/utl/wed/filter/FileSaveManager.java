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

import org.apache.commons.fileupload.FileItem;

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
public interface FileSaveManager {
    /**
     *
     * @param fileItem
     * @param imageBaseDir 기본 이미지 저장 디렉토리. 이 디렉토리 아래로 모든 파일을 넣어도 되고, 폴더를 구분하여 넣어도 된다. 이 파라미터에는 마지막 디렉토리 구분자는 포함되지 않는다.
     * @return 이미지 파일을 액세스 할 수 있는 URL 을 반환한다. 반환된 URL 은 ckeditor 에게 전달되어 즉시 사용자 브라우져에 이미지가 나타나게 된다.
     */
    String saveFile(FileItem fileItem, String imageBaseDir);
}

