package egovframework.com.uss.olp.qtm.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.ObjectUtils;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qtm.service.EgovQustnrTmplatManageService;
import egovframework.com.uss.olp.qtm.service.QustnrTmplatManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 설문템플릿 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일                수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.03.20   장동한            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2020.10.30   신용호            파일업로드 제한을위한 파라미터 전달
 *  2022.11.11   김혜준			   시큐어코딩 처리
 *
 * </pre>
 */

@Controller
public class EgovQustnrTmplatManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrTmplatManageController.class);

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovQustnrTmplatManageService")
	private EgovQustnrTmplatManageService egovQustnrTmplatManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@RequestMapping(value = "/uss/olp/qtm/EgovQustnrTmplatManageMain.do")
	public String egovQustnrTmplatManageMain(ModelMap model) throws Exception {
		return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageMain";
	}

	@RequestMapping("/uss/olp/qtm/EgovQustnrTmplatManageLeft.do")
	public String egovQustnrTmplatManageLeft(ModelMap model) throws Exception {
		return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageLeft";
	}

	/**
	 * 개별 배포시 메인메뉴를 조회한다.
	 * @param model
	 * @return	"/uss/sam/cpy/"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/olp/EgovMain.do")
	public String egovMain(ModelMap model) throws Exception {
		return "egovframework/com/uss/olp/qtm/EgovMain";
	}

	/**
	 * 메뉴를 조회한다.
	 * @param model
	 * @return	"/uss/sam/cpy/EgovLeft"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/olp/EgovLeft.do")
	public String egovLeft(ModelMap model) throws Exception {
		return "egovframework/com/uss/olp/qtm/EgovLeft";
	}

	/**
	 * 설문템플릿 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "설문템플릿관리", order = 610, gid = 50)
	@RequestMapping(value = "/uss/olp/qtm/EgovQustnrTmplatManageList.do")
	public String egovQustnrTmplatManageList(
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		@RequestParam Map<?, ?> commandMap,
		QustnrTmplatManageVO qustnrTmplatManageVO,
		ModelMap model)
		throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if (sCmd.equals("del")) {
			// 2026.07.13 KISA 보안취약점 조치 - 삭제는 POST만 허용
			jakarta.servlet.http.HttpServletRequest _req = ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes()).getRequest();
			if (!"POST".equalsIgnoreCase(_req.getMethod())) {
				throw new org.springframework.web.HttpRequestMethodNotSupportedException(_req.getMethod());
			}
			// 소유권/권한 검증 - 관리자만 삭제할 수 있다.
			List<String> _authorities = EgovUserDetailsHelper.getAuthorities();
			if (_authorities == null || !_authorities.contains("ROLE_ADMIN")) {
				throw new org.springframework.security.access.AccessDeniedException("삭제 권한이 없습니다.");
			}
			egovQustnrTmplatManageService.deleteQustnrTmplatManage(qustnrTmplatManageVO);
		}

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<EgovMap> resultList = egovQustnrTmplatManageService.selectQustnrTmplatManageList(searchVO);
        model.addAttribute("resultList", resultList);

		model.addAttribute("searchKeyword",
			commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
		model.addAttribute("searchCondition",
			commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

		int totCnt = egovQustnrTmplatManageService.selectQustnrTmplatManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageList";
	}

	/**
	 * 설문템플릿 이미지 목록을 상세조회 조회한다.
	 * @param request
	 * @param response
	 * @param qustnrTmplatManageVO
	 * @param commandMap
	 * @return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageImg"
	 * @throws Exception
	 */
	@GetMapping("/uss/olp/qtm/EgovQustnrTmplatManageImg.do")
	public void egovQustnrTmplatManageImg(
		HttpServletRequest request,
		HttpServletResponse response,
		QustnrTmplatManageVO qustnrTmplatManageVO,
		@RequestParam Map<?, ?> commandMap) throws Exception {

		Map<?, ?> mapResult = egovQustnrTmplatManageService
			.selectQustnrTmplatManageTmplatImagepathnm(qustnrTmplatManageVO);

		byte[] img = (byte[])mapResult.get("QUSTNR_TMPLAT_IMAGE_INFOPATHNM");

		// 2026.07.13 KISA 보안취약점 조치 - 저장된 바이트의 실제 이미지 시그니처를 검사하여
		// 올바른 Content-Type을 설정하고, 이미지가 아닌 경우 octet-stream으로 강제하여
		// 브라우저의 MIME 스니핑으로 인한 저장형 XSS를 차단한다.
		String imgtype = detectImageContentType(img);
		response.setHeader("Content-Type", imgtype);
		response.setHeader("X-Content-Type-Options", "nosniff");
		response.setHeader("Content-Length", "" + (img == null ? 0 : img.length));
		if (img != null) {
			response.getOutputStream().write(img);
		}
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 파일 시그니처(매직바이트)를 검사하여 실제 이미지 포맷의
	 * Content-Type을 반환한다. 알 수 없는 포맷은 application/octet-stream 으로 응답하여
	 * 브라우저가 HTML/JS로 스니핑/렌더링하지 못하도록 한다.
	 *
	 * @param bytes 응답으로 내려줄 원본 바이트
	 * @return 검증된 이미지 Content-Type 또는 application/octet-stream
	 */
	private String detectImageContentType(byte[] bytes) {
		if (bytes != null && bytes.length >= 4) {
			if ((bytes[0] & 0xFF) == 0xFF && (bytes[1] & 0xFF) == 0xD8 && (bytes[2] & 0xFF) == 0xFF) {
				return "image/jpeg";
			}
			if (bytes.length >= 8 && (bytes[0] & 0xFF) == 0x89 && bytes[1] == 0x50 && bytes[2] == 0x4E
					&& bytes[3] == 0x47) {
				return "image/png";
			}
			if (bytes[0] == 0x47 && bytes[1] == 0x49 && bytes[2] == 0x46 && bytes[3] == 0x38) {
				return "image/gif";
			}
			if (bytes[0] == 0x42 && bytes[1] == 0x4D) {
				return "image/bmp";
			}
		}
		return "application/octet-stream";
	}

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 업로드된 바이트가 실제 유효한 래스터 이미지인지 검증한다.
	 * 파일 시그니처(매직바이트)를 우선 확인하고, 추가로 ImageIO 디코딩이 가능한지 재검증하여
	 * 확장자만 이미지로 위장한 HTML/JS 등 임의 바이트의 업로드를 차단한다.
	 *
	 * @param bytes 업로드된 파일의 원본 바이트
	 * @return 유효한 이미지이면 true
	 */
	private boolean isValidImageBytes(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return false;
		}
		String detected = detectImageContentType(bytes);
		if ("application/octet-stream".equals(detected)) {
			return false;
		}
		try {
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
			return image != null;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 설문템플릿 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrTmplatManageVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageDetail"
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qtm/EgovQustnrTmplatManageDetail.do")
	public String egovQustnrTmplatManageDetail(
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		QustnrTmplatManageVO qustnrTmplatManageVO,
		@RequestParam Map<?, ?> commandMap,
		ModelMap model)
		throws Exception {

		String sLocationUrl = "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if (sCmd.equals("del")) {
			// 2026.07.13 KISA 보안취약점 조치 - 삭제는 POST만 허용
			jakarta.servlet.http.HttpServletRequest _req = ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes()).getRequest();
			if (!"POST".equalsIgnoreCase(_req.getMethod())) {
				throw new org.springframework.web.HttpRequestMethodNotSupportedException(_req.getMethod());
			}
			// 소유권/권한 검증 - 관리자만 삭제할 수 있다.
			List<String> _authorities = EgovUserDetailsHelper.getAuthorities();
			if (_authorities == null || !_authorities.contains("ROLE_ADMIN")) {
				throw new org.springframework.security.access.AccessDeniedException("삭제 권한이 없습니다.");
			}
			egovQustnrTmplatManageService.deleteQustnrTmplatManage(qustnrTmplatManageVO);
			sLocationUrl = "redirect:/uss/olp/qtm/EgovQustnrTmplatManageList.do";
		} else {
			List<EgovMap> resultList = egovQustnrTmplatManageService.selectQustnrTmplatManageDetail(qustnrTmplatManageVO);
            model.addAttribute("resultList", resultList);
		}

		return sLocationUrl;
	}

	/**
	 * 설문템플릿를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModify"
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qtm/EgovQustnrTmplatManageModify.do")
	public String qustnrTmplatManageModify(
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		@RequestParam Map<?, ?> commandMap,
		QustnrTmplatManageVO qustnrTmplatManageVO,
		ModelMap model)
		throws Exception {
		String sLocationUrl = "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModify";

//		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		List<EgovMap> resultList = egovQustnrTmplatManageService.selectQustnrTmplatManageDetail(qustnrTmplatManageVO);
        model.addAttribute("resultList", resultList);

		// 파일업로드 제한
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

		model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
		model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		return sLocationUrl;
	}

	/**
	 * 설문템플릿를 수정처리 한다.
	 * @param multiRequest
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrTmplatManageVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModifyActor"
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qtm/EgovQustnrTmplatManageModifyActor.do")
	public String qustnrTmplatManageModifyActor(
		final MultipartHttpServletRequest multiRequest,
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		@RequestParam Map<?, ?> commandMap,
		@Valid @ModelAttribute("qustnrTmplatManageVO") QustnrTmplatManageVO qustnrTmplatManageVO,
		BindingResult bindingResult,
		RedirectAttributes redirectAttributes,
		ModelMap model)
		throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		if (bindingResult.hasErrors()) {
			List<EgovMap> resultList = egovQustnrTmplatManageService.selectQustnrTmplatManageDetail(qustnrTmplatManageVO);
            model.addAttribute("resultList", resultList);

			// 파일업로드 제한
			String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
			String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

			model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
			model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
			return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModify";
		}

		//아이디 설정
		qustnrTmplatManageVO
			.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		qustnrTmplatManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if (!files.isEmpty()) {
			for (MultipartFile file : files.values()) {
				LOGGER.info("getName => {}", file.getName());
				LOGGER.info("getOriginalFilename => {}", file.getOriginalFilename());

				// 파일 수정여부 확인
				if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
					if (file.getName().equals("qestnrTmplatImage")) {
						byte[] fileBytes = file.getBytes();
						// 2026.07.13 KISA 보안취약점 조치 - 서버측 실제 이미지(매직바이트+디코딩) 검증
						if (!isValidImageBytes(fileBytes)) {
							bindingResult.rejectValue("qestnrTmplatImage", "file.invalid", "유효한 이미지 파일이 아닙니다.");
							List<EgovMap> resultList = egovQustnrTmplatManageService
								.selectQustnrTmplatManageDetail(qustnrTmplatManageVO);
							model.addAttribute("resultList", resultList);

							String whiteListFileUploadExtensions = EgovProperties
								.getProperty("Globals.fileUpload.Extensions");
							String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");
							model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
							model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
							return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModify";
						}
						qustnrTmplatManageVO.setQestnrTmplatImagepathnm(fileBytes);
					}
				}
			}
		}
		egovQustnrTmplatManageService.updateQustnrTmplatManage(qustnrTmplatManageVO);

		return "redirect:/uss/olp/qtm/EgovQustnrTmplatManageList.do";
	}

	/**
	 * 설문템플릿를 등록한다. / 초기등록페이지
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageRegist"
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qtm/EgovQustnrTmplatManageRegist.do")
	public String qustnrTmplatManageRegist(
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		@RequestParam Map<?, ?> commandMap,
		@ModelAttribute("qustnrTmplatManageVO") QustnrTmplatManageVO qustnrTmplatManageVO,
		RedirectAttributes redirectAttributes,
		ModelMap model)
		throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

		//아이디 설정
		qustnrTmplatManageVO
			.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		qustnrTmplatManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		// 파일업로드 제한
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

		model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
		model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		return sLocationUrl;
	}

	/**
	 * 설문템플릿를 등록 처리 한다.  / 등록처리
	 * @param multiRequest
	 * @param searchVO
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageRegistActor"
	 * @throws Exception
	 */
	@PostMapping("/uss/olp/qtm/EgovQustnrTmplatManageRegistActor.do")
	public String qustnrTmplatManageRegistActor(
		final MultipartHttpServletRequest multiRequest,
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		@Valid QustnrTmplatManageVO qustnrTmplatManageVO,BindingResult bindingResult,
		RedirectAttributes redirectAttributes,
		ModelMap model)
		throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		// 유효성 검증, 실패시 포워딩
				if(bindingResult.hasErrors()) {
					System.out.println("####파라미터검증에러"+ bindingResult.getAllErrors());//확인용 로그
					String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
					String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

					model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
					model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
					return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageRegist";
				}
				
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//아이디 설정
		qustnrTmplatManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		qustnrTmplatManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		boolean fileExists = false;
		

		if (files != null && !files.isEmpty()) {
			for (MultipartFile file : files.values()) {
				LOGGER.info("getName => {}", file.getName()); // 파일의 파라미터 이름
				LOGGER.info("getOriginalFilename => {}", file.getOriginalFilename()); // 파일의 실제 이름

				// 2022.11.11 시큐어코딩 처리
				if (ObjectUtils.isNotEmpty(file.getName()) && ObjectUtils.isNotEmpty(file.getOriginalFilename())
						&& !file.isEmpty()) {
					byte[] fileBytes = file.getBytes();
					// 2026.07.13 KISA 보안취약점 조치 - 서버측 실제 이미지(매직바이트+디코딩) 검증
					if (!isValidImageBytes(fileBytes)) {
						bindingResult.rejectValue(
								"qestnrTmplatImage",
								"file.invalid",
								"유효한 이미지 파일이 아닙니다.");
						String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
						String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

						model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
						model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
						return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageRegist";
					}
					qustnrTmplatManageVO.setQestnrTmplatImagepathnm(fileBytes);
					fileExists = true;
					break;
				}
			}
		}
		if(!fileExists) {
			bindingResult.rejectValue(
					"qestnrTmplatImage",
					"file.empty",// 메세지 파일에 해당 태그는 없지만 채워놓음
					"템플릿 이미지를 선택해주세요.");
			String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
			String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

			model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
			model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
			return "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageRegist";
		}
		
		egovQustnrTmplatManageService.insertQustnrTmplatManage(qustnrTmplatManageVO);

		return "redirect:/uss/olp/qtm/EgovQustnrTmplatManageList.do";
	}

}
