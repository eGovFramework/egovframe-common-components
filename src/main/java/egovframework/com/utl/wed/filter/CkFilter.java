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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *  Filter class
 * @author guavatak
 * @since 2014.12.04
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일              수정자              수정내용
 *  ----------  --------    ---------------------------
 *  2014.12.04	표준프레임워크	최초 적용 (패키지 변경 및 소스 정리)
 *  2018.12.28	신용호             CkImageSaver 수정
 * </pre>
 */
public class CkFilter implements Filter {
    private static final Log log = LogFactory.getLog(CkFilter.class);

    private static final String IMAGE_BASE_DIR_KEY = "ck.image.dir";
    private static final String IMAGE_BASE_URL_KEY = "ck.image.url";
    private static final String IMAGE_ALLOW_TYPE_KEY = "ck.image.type.allow";
    private static final String IMAGE_SAVE_CLASS_KEY = "ck.image.save.class";

    private CkImageSaver ckImageSaver;

	public void init(FilterConfig filterConfig) throws ServletException {
		String properties = filterConfig.getInitParameter("properties");
		InputStream inStream = Thread.currentThread().getContextClassLoader()	.getResourceAsStream(properties);
		Properties props = new Properties();
		try {
			props.load(inStream);
		} catch (IOException e) {
            log.error(e);
		} finally { //KISA 보안약점 조치 (2018-10-29, 윤창원)
			try {
				inStream.close();
			} catch (IOException e) {
	            log.error(e);
			}
		}

		String imageBaseDir = (String) props.get(IMAGE_BASE_DIR_KEY);
		String imageDomain = (String) props.get(IMAGE_BASE_URL_KEY);

		String[] allowFileTypeArr = null;
		String allowFileType = (String) props.get(IMAGE_ALLOW_TYPE_KEY);
		if (StringUtils.isNotBlank(allowFileType)) {
			allowFileTypeArr = StringUtils.split(allowFileType, ",");
		}

        String saveManagerClass = (String) props.get(IMAGE_SAVE_CLASS_KEY);

        ckImageSaver = new CkImageSaver(imageBaseDir, imageDomain, allowFileTypeArr, saveManagerClass);

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getContentType() == null || request.getContentType().indexOf("multipart") == -1) {
			// contentType 이 multipart 가 아니라면 스킵한다.
			chain.doFilter(request, response);
		} else {
            ckImageSaver.saveAndReturnUrlToClient(request, response);

        }
	}

    public void destroy() {
    	// no-op
	}
}
