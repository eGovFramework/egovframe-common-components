package egovframework.com.utl.wed.web;

import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovFileUploadUtil;
import egovframework.com.utl.fcc.service.EgovFormBasedFileUtil;
import egovframework.com.utl.fcc.service.EgovFormBasedFileVo;
import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;
import egovframework.rte.fdl.cryptography.impl.EgovARIACryptoServiceImpl;


/**
 * 웹에디터 이미지 upload 처리 Controller
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.08.26
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.26  한성곤          최초 생성
 *   2017.08.31  장동한         path, physical 파라미터 노출 암호화 처리
 *   2017.12.12  장동한         출력 모듈 경로 변경 취약점 조치
 *   2018.03.07  신용호         URLEncode 처리
 *   2018.08.17  신용호         URL 암호화 보안 추가 조치
 *
 * </pre>
 */
@Controller
public class EgovWebEditorImageController {
	
    /** 로그설정 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovWebEditorImageController.class);

    /** 첨부파일 위치 지정 */
    private final String uploadDir = EgovProperties.getProperty("Globals.fileStorePath");

    /** 첨부 최대 파일 크기 지정 */
    private final long maxFileSize = 1024 * 1024 * 100;   //업로드 최대 사이즈 설정 (100M)
    
    private final String pKey = "영문자 10자리이상, 알파벳및 숫자 및 특수기호 혼용 키지정"; // 반드시 10자리이상의 영문알파벳 및 숫자 및 특수기호 혼용 (필수)
    private final String extWhiteList = ".gif.jpg.jpeg.png"; // 허용할 확장자를 ".확장자" 형태로 연달아 기술한다.


    /**
     * 이미지 Upload 화면으로 이동한다.
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/utl/wed/insertImage.do", method=RequestMethod.GET)
    public String goInsertImage() throws Exception {

	return "egovframework/com/utl/wed/EgovInsertImage";
    }

    /**
     * 이미지 Upload를 처리한다.
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/utl/wed/insertImage.do", method=RequestMethod.POST)
    public String imageUpload(HttpServletRequest request, Model model) throws Exception {
	// Spring multipartResolver 미사용 시 (commons-fileupload 활용)
	//List<EgovFormBasedFileVo> list = EgovFormBasedFileUtil.uploadFiles(request, uploadDir, maxFileSize);

	// Spring multipartResolver 사용시
	List<EgovFormBasedFileVo> list = EgovFileUploadUtil.uploadFilesExt(request, uploadDir, maxFileSize);

	if (list.size() > 0) {
	    EgovFormBasedFileVo vo = list.get(0);	// 첫번째 이미지
	    
	    String url = request.getContextPath()
	    + "/utl/web/imageSrc.do?"
	    + "path=" + this.encrypt(vo.getServerSubPath())
	    + "&physical=" + this.encrypt(vo.getPhysicalName())
	    + "&contentType=" + this.encrypt(vo.getContentType()+"$$@"); // 암호화 구분자문자 $$@ 추가 

	    model.addAttribute("url", url);
	}

	return "egovframework/com/utl/wed/EgovInsertImage";
    }

    /**
     * 이미지 view를 제공한다.
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/utl/web/imageSrc.do",method=RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//2017.12.12 - 출력 모듈 경로 변경 취약점 조치
	String subPath = this.decrypt(request.getParameter("path"));
	String physical = this.decrypt(request.getParameter("physical"));
	String mimeType = this.decrypt(request.getParameter("contentType"));
	
	if ( mimeType.indexOf("$$@")>0 ) { // 암호화 처리 URL인가? (NEW)
		mimeType = mimeType.replaceAll("[$][$]@","");
		if (subPath.indexOf("..") >= 0 ) throw new Exception("Security Exception - illegal url called.");
		if (physical.indexOf("..") >= 0 ) throw new Exception("Security Exception - illegal url called.");

		String ext = "";
		if ( physical.lastIndexOf(".") > 0 )
			ext = physical.substring(physical.lastIndexOf(".") + 1,physical.length()).toLowerCase();
		if ( ext == null ) throw new FileNotFoundException();

		if (extWhiteList.indexOf(ext) >= 0)
			EgovFormBasedFileUtil.viewFile(response, uploadDir, subPath, physical, mimeType);
		else
			throw new FileNotFoundException();
	} else { // 이전 사용 이미지 인가? (OLD)
		subPath = request.getParameter("path");
		physical = request.getParameter("physical");
		mimeType = request.getParameter("contentType");
		if (subPath.indexOf("..") >= 0 ) throw new Exception("Security Exception - illegal url called.");
		if (physical.indexOf("..") >= 0 ) throw new Exception("Security Exception - illegal url called.");
		
		// 기존 호출방식에서 .이 포함되어 있지 않으므로 불필요한 요청 삭제
		subPath = subPath.replaceAll("\\.", "");
		physical = physical.replaceAll("\\.", "");

		EgovFormBasedFileUtil.viewFile(response, uploadDir, subPath, physical, mimeType);
	}
    }
    
    /**
     * 암호화
     *
     * @param encrypt
     */
    private String encrypt(String encrypt){
    	
    	EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
        EgovARIACryptoServiceImpl cryptoService = new EgovARIACryptoServiceImpl();
        egovPasswordEncoder.setAlgorithm("SHA-256");
    	String hashedPassword = egovPasswordEncoder.encryptPassword(pKey);
    	egovPasswordEncoder.setHashedPassword(hashedPassword);
    	cryptoService.setPasswordEncoder(egovPasswordEncoder);
    	cryptoService.setBlockSize(1024);
    	
    	try {
			return URLEncoder.encode(new String(new Base64().encode((byte[])cryptoService.encrypt(encrypt.getBytes("UTF-8"), pKey))),"UTF-8");
        } catch(IllegalArgumentException e) {
    		LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
        } catch (Exception e) {
        	LOGGER.error("[" + e.getClass() +"] :" + e.getMessage());
        } finally {
        	egovPasswordEncoder = null;
        	cryptoService = null;
        }
		return encrypt;
    }
    
    /**
     * 복호화
     *
     * @param decrypt
     */
    private String decrypt(String decrypt){
    	
    	EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
        EgovARIACryptoServiceImpl cryptoService = new EgovARIACryptoServiceImpl();
        egovPasswordEncoder.setAlgorithm("SHA-256");
    	String hashedPassword = egovPasswordEncoder.encryptPassword(pKey);
    	egovPasswordEncoder.setHashedPassword(hashedPassword);
    	cryptoService.setPasswordEncoder(egovPasswordEncoder);
    	cryptoService.setBlockSize(1024);

    	try {
			return new String((byte[])cryptoService.decrypt(new Base64().decode(decrypt.getBytes("UTF-8")), pKey), "UTF-8");
        } catch(IllegalArgumentException e) {
    		LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
        } catch (Exception e) {
        	LOGGER.error("[" + e.getClass() +"] :" + e.getMessage());
        } finally {
        	egovPasswordEncoder = null;
        	cryptoService = null;
        }
		return decrypt;
    }
    
}
