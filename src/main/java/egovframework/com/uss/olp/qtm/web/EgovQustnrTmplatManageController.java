package egovframework.com.uss.olp.qtm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qtm.service.EgovQustnrTmplatManageService;
import egovframework.com.uss.olp.qtm.service.QustnrTmplatManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

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

	@Autowired
	private DefaultBeanValidator beanValidator;

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

	@RequestMapping(value = "/uss/olp/qtm/EgovQustnrTmplatManageLeft.do")
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
	@RequestMapping(value = "/uss/olp/qtm/EgovQustnrTmplatManageImg.do")
	public void egovQustnrTmplatManageImg(
		HttpServletRequest request,
		HttpServletResponse response,
		QustnrTmplatManageVO qustnrTmplatManageVO,
		@RequestParam Map<?, ?> commandMap) throws Exception {

		Map<?, ?> mapResult = egovQustnrTmplatManageService
			.selectQustnrTmplatManageTmplatImagepathnm(qustnrTmplatManageVO);

		byte[] img = (byte[])mapResult.get("QUSTNR_TMPLAT_IMAGE_INFOPATHNM");

		String imgtype = "jpeg";
		// 2022.11.11 시큐어코딩 처리
		response.setHeader("Content-Type", imgtype);
		response.setHeader("Content-Length", "" + img.length);
		response.getOutputStream().write(img);
		response.getOutputStream().flush();
		response.getOutputStream().close();
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
	@RequestMapping(value = "/uss/olp/qtm/EgovQustnrTmplatManageDetail.do")
	public String egovQustnrTmplatManageDetail(
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		QustnrTmplatManageVO qustnrTmplatManageVO,
		@RequestParam Map<?, ?> commandMap,
		ModelMap model)
		throws Exception {

		String sLocationUrl = "egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if (sCmd.equals("del")) {
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
	@RequestMapping(value = "/uss/olp/qtm/EgovQustnrTmplatManageModify.do")
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
	@RequestMapping(value = "/uss/olp/qtm/EgovQustnrTmplatManageModifyActor.do")
	public String qustnrTmplatManageModifyActor(
		final MultipartHttpServletRequest multiRequest,
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		@RequestParam Map<?, ?> commandMap,
		@ModelAttribute("qustnrTmplatManageVO") QustnrTmplatManageVO qustnrTmplatManageVO,
		BindingResult bindingResult,
		ModelMap model)
		throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//서버  validate 체크
		beanValidator.validate(qustnrTmplatManageVO, bindingResult);
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
				if (file.getOriginalFilename() != "") {
					if (file.getName().equals("qestnrTmplatImage")) {
						qustnrTmplatManageVO.setQestnrTmplatImagepathnm(file.getBytes());
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
	@RequestMapping(value = "/uss/olp/qtm/EgovQustnrTmplatManageRegist.do")
	public String qustnrTmplatManageRegist(
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		@RequestParam Map<?, ?> commandMap,
		@ModelAttribute("qustnrTmplatManageVO") QustnrTmplatManageVO qustnrTmplatManageVO,
		ModelMap model)
		throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
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
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions.Images");
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
	@RequestMapping(value = "/uss/olp/qtm/EgovQustnrTmplatManageRegistActor.do")
	public String qustnrTmplatManageRegistActor(
		final MultipartHttpServletRequest multiRequest,
		@ModelAttribute("searchVO") ComDefaultVO searchVO,
		QustnrTmplatManageVO qustnrTmplatManageVO,
		ModelMap model)
		throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//아이디 설정
		qustnrTmplatManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		qustnrTmplatManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if (files != null && !files.isEmpty()) {
			for (MultipartFile file : files.values()) {
				LOGGER.info("getName => {}", file.getName()); // 파일의 파라미터 이름
				LOGGER.info("getOriginalFilename => {}", file.getOriginalFilename()); // 파일의 실제 이름

				// 2022.11.11 시큐어코딩 처리
				if (ObjectUtils.isNotEmpty(file.getName()) && ObjectUtils.isNotEmpty(file.getOriginalFilename())) {
					qustnrTmplatManageVO.setQestnrTmplatImagepathnm(file.getBytes());
				}
			}
		}

		egovQustnrTmplatManageService.insertQustnrTmplatManage(qustnrTmplatManageVO);

		return "redirect:/uss/olp/qtm/EgovQustnrTmplatManageList.do";
	}

}
