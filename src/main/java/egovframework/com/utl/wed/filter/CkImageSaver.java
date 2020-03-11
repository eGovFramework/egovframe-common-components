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
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cryptography.EgovEnvCryptoService;

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
 *  2016.04.21	장동한		공통컴포넌트 V3.6 수정
 *  2018.12.11	신용호		KISA 보안취약점 등 수정
 *  2018.12.28	신용호		업로드 이미지 URL 생성 부분 수정
 * </pre>
 */
public class CkImageSaver {
	private static final Log log = LogFactory.getLog(CkFilter.class);

	private static final String FUNC_NO = "CKEditorFuncNum";

	private String imageBaseDir;
	private String imageDomain;
	private String[] allowFileTypeArr;
	
    private String fileStorePath = EgovProperties.getProperty("Globals.fileStorePath");
    
	private FileSaveManager fileSaveManager;

	public CkImageSaver(String imageBaseDir, String imageDomain, String[] allowFileTypeArr, String saveManagerClass) {
		this.imageBaseDir = EgovWebUtil.filePathBlackList(imageBaseDir);
		
		if ((EgovStringUtil.isNullToString(imageBaseDir)).endsWith("/")) {
			StringUtils.removeEnd(imageBaseDir, "/");
		}
		if ((EgovStringUtil.isNullToString(imageBaseDir)).endsWith("\\")) {
			StringUtils.removeEnd(imageBaseDir, "\\");
		}

		this.imageDomain = EgovWebUtil.filePathBlackList(imageDomain);
		if ((EgovStringUtil.isNullToString(imageDomain)).endsWith("/")) {
			StringUtils.removeEnd(imageDomain, "/");
		}

		this.allowFileTypeArr = allowFileTypeArr;

		if (StringUtils.isBlank(saveManagerClass)) {
			fileSaveManager = new DefaultFileSaveManager();
		} else {
			try {
				Class<?> klass = Class.forName(saveManagerClass);
				fileSaveManager = (FileSaveManager) klass.newInstance();
			} catch (ClassNotFoundException e) {
				log.error(e);
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				log.error(e);
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				log.error(e);
				throw new RuntimeException(e);
			}
		}
	}

	public void saveAndReturnUrlToClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Parse the request
		try {
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			List<FileItem> /* FileItem */items = upload.parseRequest(request);
			// We upload just one file at the same time
			FileItem uplFile = items.get(0);

			String errorMessage = null;
			String relUrl = null;

			if (isAllowFileType(FilenameUtils.getName(uplFile.getName()))) {
				String uploadFilePath = fileSaveManager.saveFile(uplFile, fileStorePath+imageBaseDir);
				//System.out.println("===>>> uploadFilePath = "+uploadFilePath);
				
				String fileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('/') + 1);
				String filePath = imageBaseDir+uploadFilePath.substring(0,uploadFilePath.lastIndexOf('/'));
				
				relUrl = request.getContextPath()
					    + "/utl/web/imageSrc.do?"
					    + "path=" + this.encrypt(filePath,request)
					    + "&physical=" + this.encrypt(fileName,request)
					    + "&contentType=" + this.encrypt(uplFile.getContentType(),request);
				
				//System.out.println("===>>> relUrl = "+relUrl);
			} else {
				errorMessage = "Restricted Image Format";
			}

			StringBuffer sb = new StringBuffer();
			sb.append("<script type=\"text/javascript\">\n");
			// Compressed version of the document.domain automatic fix script.
			// The original script can be found at [fckeditor_dir]/_dev/domain_fix_template.js
			// sb.append("(function(){var d=document.domain;while (true){try{var A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n");
			// KISA 보안약점 조치 (2018-12-11, 신용호)
			String funcNo = EgovWebUtil.clearXSSMaximum(request.getParameter(FUNC_NO));
			sb.append("window.parent.CKEDITOR.tools.callFunction(").append(funcNo).append(", '");
			sb.append(relUrl);
			if (errorMessage != null) {
				sb.append("', '").append(errorMessage);
			}
			sb.append("');\n </script>");

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();

			out.print(sb.toString());
			out.flush();
			out.close();

		} catch (FileUploadException e) {
			log.error(e);
		}
	}

	protected boolean isAllowFileType(String fileName) {
		boolean isAllow = false;
		if (allowFileTypeArr != null && allowFileTypeArr.length > 0) {
			for (String allowFileType : allowFileTypeArr) {
				if (StringUtils.equalsIgnoreCase(allowFileType, StringUtils.substringAfterLast(fileName, "."))) {
					isAllow = true;
					break;
				}
			}
		} else {
			isAllow = true;
		}

		return isAllow;
	}
	
    /**
     * 암호화
     *
     * @param encrypt
     */
    private String encrypt(String encrypt,HttpServletRequest request) {
    	
    	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
    	EgovEnvCryptoService cryptoService = (EgovEnvCryptoService)wac.getBean("egovEnvCryptoService");
    	
    	try {
    		return cryptoService.encrypt(encrypt);
        } catch(IllegalArgumentException e) {
        	log.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
        } catch (Exception e) {
        	log.error("[" + e.getClass() +"] :" + e.getMessage());
        }
		return encrypt;
    }

}
