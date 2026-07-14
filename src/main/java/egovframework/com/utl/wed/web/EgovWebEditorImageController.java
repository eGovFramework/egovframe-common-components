package egovframework.com.utl.wed.web;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.LoginVO;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
import org.egovframe.rte.fdl.crypto.EgovPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovFileUploadUtil;
import egovframework.com.utl.fcc.service.EgovFormBasedFileUtil;
import egovframework.com.utl.fcc.service.EgovFormBasedFileVo;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
 *   수정일                수정자          수정내용
 *  -----------   --------  ---------------------------
 *   2009.08.26   한성곤          최초 생성
 *   2017.08.31   장동한          path, physical 파라미터 노출 암호화 처리
 *   2017.12.12   장동한          출력 모듈 경로 변경 취약점 조치
 *   2018.03.07   신용호          URLEncode 처리
 *   2018.08.17   신용호          URL 암호화 보안 추가 조치
 *   2020.08.05   신용호          imageUploadCk Parameter 수정
 *   2022.07.12   이석곤          주석 파라미터 type명과 변수명 수정
 *
 * </pre>
 */
@Controller
public class EgovWebEditorImageController {

	/** 로그설정 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovWebEditorImageController.class);

	/** 첨부파일 위치 지정  => globals.properties */
	private final String uploadDir = EgovProperties.getProperty("Globals.fileStorePath");

	/** 허용할 확장자를 .확장자 형태로 연달아 기술한다. ex) .gif.jpg.jpeg.png => globals.properties */
	private final String extWhiteList = EgovProperties.getProperty("Globals.fileUpload.Extensions");

	/** 첨부 최대 파일 크기 지정 */
	private final long maxFileSize = 1024L * 1024L * 100L;   //업로드 최대 사이즈 설정 (100M)

	/** 암호화서비스 */
	@Resource(name = "egovEnvCryptoService")
	EgovEnvCryptoService cryptoService;

	@Resource(name = "egovEnvPasswordEncoderService")
	EgovPasswordEncoder egovPasswordEncoder;

	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 이미지 Upload 화면으로 이동한다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	


	/**
	 * 이미지 Upload를 처리한다.
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/utl/wed/insertImage.do")
	public String imageUpload(MultipartHttpServletRequest request, Model model) throws Exception {

		model.addAttribute("imageUpload", new ComDefaultVO());
		uploadImageFiles(request, model);
		return "egovframework/com/utl/wed/EgovInsertImage";
	}

	/**
	 * 이미지 Upload(CK에디터)를 처리한다.
	 *
	 * @param ckEditorFuncNum
	 * @param mRequest
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/utl/wed/insertImageCk.do")
	public String imageUploadCk(@RequestParam(value = "CKEditorFuncNum", required=false) String ckEditorFuncNum, MultipartHttpServletRequest mRequest, HttpServletResponse response, Model model) throws Exception {
		model.addAttribute("ckEditorFuncNum", parseCkEditorFuncNum(ckEditorFuncNum));
		uploadImageFiles(mRequest, model);
		return "egovframework/com/utl/wed/EgovUploadImageComplete";
	}

	/**
	 * CKEditor callback 함수 번호를 정수로 검증한다. (CkImageSaver와 동일 정책)
	 */
	private int parseCkEditorFuncNum(String ckEditorFuncNum) {
		if (ckEditorFuncNum == null || ckEditorFuncNum.isEmpty()) {
			return 1;
		}
		try {
			return Integer.parseInt(ckEditorFuncNum);
		} catch (NumberFormatException e) {
			LOGGER.warn("Invalid CKEditorFuncNum: {}", ckEditorFuncNum);
			return 1;
		}
	}

	/**
	 * @param mRequest
	 * @param model
	 * @throws Exception
	 */
	private void uploadImageFiles(MultipartHttpServletRequest mRequest, Model model) throws Exception {

		try {
			List<EgovFormBasedFileVo> list = EgovFileUploadUtil.uploadFilesExt(mRequest, uploadDir, maxFileSize, extWhiteList);
			if (list.size() > 0) {
				EgovFormBasedFileVo vo = list.get(0);	// 첫번째 이미지

				String url = mRequest.getContextPath()
						+ "/utl/web/imageSrc.do?"
						+ "path=" + this.encrypt(vo.getServerSubPath())
						+ "&physical=" + this.encrypt(vo.getPhysicalName())
						+ "&contentType=" + this.encrypt(vo.getContentType());

				model.addAttribute("url", url);
				model.addAttribute("msg",egovMessageSource.getMessage("success.file.transfer"));
			}
		} catch (SecurityException e) {
			model.addAttribute("url", "");
			model.addAttribute("msg",egovMessageSource.getMessage("errors.file.extension"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			model.addAttribute("url", "");
			model.addAttribute("msg",egovMessageSource.getMessage("errors.file.transfer"));
		}
	}

	/**
	 * 이미지 view를 제공한다.
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/utl/web/imageSrc.do",method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 2026.07.13 KISA 보안취약점 조치
		LoginVO _loginVO = egovAssertLoginUser();

		//2017.12.12 - 출력 모듈 경로 변경 취약점 조치
		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		String pathParam = EgovStringUtil.isNullToString(request.getParameter("path"));
		String physicalParam = EgovStringUtil.isNullToString(request.getParameter("physical"));
		String contentTypeParam = EgovStringUtil.isNullToString(request.getParameter("contentType"));
		String subPath = this.decrypt(pathParam);
		String physical = this.decrypt(physicalParam);
		String mimeType = this.decrypt(contentTypeParam);

		// 2026.07.13 KISA 보안취약점 조치 - 복호화 실패 시 평문 파라미터 사용 금지
		if (subPath.isEmpty() || physical.isEmpty() || subPath.equals(pathParam) || physical.equals(physicalParam)) {
			throw new FileNotFoundException();
		}

		if ((subPath.indexOf("..") >= 0) || (physical.indexOf("..") >= 0) ) {
			throw new Exception("Security Exception - illegal url called.");
		}

		Path uploadRoot = Paths.get(uploadDir).toAbsolutePath().normalize();
		Path resolvedPath = uploadRoot.resolve(subPath).resolve(physical).normalize();
		if (!resolvedPath.startsWith(uploadRoot)) {
			throw new FileNotFoundException();
		}

		String ext = "";
		if ( physical.lastIndexOf(".") > 0 ) {
			ext = physical.substring(physical.lastIndexOf(".") + 1,physical.length()).toLowerCase();
		}
		if ( ext == null ) {
			throw new FileNotFoundException();
		}

		if ( EgovFileUploadUtil.isAllowedExtension(ext, extWhiteList) ) {
			EgovFormBasedFileUtil.viewFile(response, uploadDir, subPath, physical, mimeType);
		} else {
			throw new FileNotFoundException();
		}
	}

	/**
	 * 암호화
	 *
	 * @param encrypt
	 * @return
	 */
	private String encrypt(String encrypt) {

		try {
			return cryptoService.encrypt(encrypt); // Handles URLEncoding.
			//return cryptoService.encryptNone(encrypt); // Does not handle URLEncoding.
		} catch(IllegalArgumentException e) {
			LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Running : "+ e.getMessage());
		}
		return encrypt;
	}

	/**
	 * 복호화
	 *
	 * @param decrypt
	 * @return
	 */
	private String decrypt(String decrypt){

		if (decrypt == null || decrypt.isEmpty()) {
			return "";
		}
		try {
			//return cryptoService.decrypt(decrypt); // Handles URLDecoding.
			return cryptoService.decryptNone(decrypt); // Does not handle URLDecoding.
		} catch(IllegalArgumentException e) {
			LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Running : "+ e.getMessage());
		}
		// 2026.07.13 KISA 보안취약점 조치 - 복호화 실패 시 평문 반환 금지
		return "";
	}


	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 로그인 사용자 확인
	 */
	private LoginVO egovAssertLoginUser() {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null || loginVO.getUniqId() == null || "".equals(loginVO.getUniqId())) {
			throw new IllegalStateException("인증 정보가 없습니다.");
		}
		return loginVO;
	}

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 관리자 또는 소유자
	 */
	private void egovAssertAdminOrOwner(String ownerUniqId) {
		LoginVO loginVO = egovAssertLoginUser();
		if (ownerUniqId != null && ownerUniqId.equals(loginVO.getUniqId())) {
			return;
		}
		java.util.List<String> auth = EgovUserDetailsHelper.getAuthorities();
		if (auth != null && auth.contains("ROLE_ADMIN")) {
			return;
		}
		throw new IllegalStateException("권한이 없습니다.");
	}

}