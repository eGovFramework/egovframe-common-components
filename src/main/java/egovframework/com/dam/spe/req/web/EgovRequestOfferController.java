package egovframework.com.dam.spe.req.web;

import egovframework.com.cmm.annotation.RequireAdmin;
import egovframework.com.cmm.exception.EgovAccessDeniedException;
import egovframework.com.cmm.util.EgovAuthorizationHelper;
import egovframework.com.cmm.util.EgovAttachmentGrants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.map.mat.service.EgovMapMaterialService;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;
import egovframework.com.dam.map.tea.service.EgovMapTeamService;
import egovframework.com.dam.map.tea.service.MapTeamVO;
import egovframework.com.dam.spe.req.service.EgovRequestOfferService;
import egovframework.com.dam.spe.req.service.RequestOfferVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * 지식정보제공/지식정보요청를 처리하는 Controller Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2010.08.30
 * @version 1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.30  장동한          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2019.12.09  신용호          KISA 보안약점 조치 (위험한 형식 파일 업로드)
 *   2025.06.18  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(지역 변수 명명 규칙)
 *
 *      </pre>
 */
@Controller
public class EgovRequestOfferController {

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

	/** egovRequestOffeService */
	@Resource(name = "egovRequestOffeService")
	private EgovRequestOfferService egovRequestOfferVOService;

	/** MapTeamService */
	@Resource(name = "MapTeamService")
	private EgovMapTeamService mapTeamService;

	@Resource(name = "MapMaterialService")
	public EgovMapMaterialService mapMaterialService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	// 첨부파일 관련
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/**
	 * 지식정보제공/지식정보요청 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @param commandMap
	 * @param requestOfferVO
	 * @param model
	 * @return "egovframework/com/dam/spe/req/EgovRequestOfferVOList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "지식정보제공", listUrl = "/dam/spe/req/listRequestOffer.do", order = 1291, gid = 80)
	@RequestMapping(value = "/dam/spe/req/listRequestOffer.do")
	public String EgovRequestOfferList(@ModelAttribute("searchVO") RequestOfferVO searchVO,
			@RequestParam Map<?, ?> commandMap, RequestOfferVO requestOfferVO,
			ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

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

		List<EgovMap> resultList = egovRequestOfferVOService.selectRequestOfferList(searchVO);
		model.addAttribute("resultList", resultList);

		model.addAttribute("searchKeyword",
				commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
		model.addAttribute("searchCondition",
				commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

		int totCnt = egovRequestOfferVOService.selectRequestOfferListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		// (지식전문가/지식사용자) 검사 및 설정
		HashMap<String, String> hmParam = new HashMap<String, String>();
		hmParam.put("speId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		// 지식전문가 일때
		if (egovRequestOfferVOService.selectRequestOfferSpeCheck(hmParam)) {
			model.addAttribute("IS_SPE", "Y");
		} else {
			model.addAttribute("IS_SPE", "N");
			model.addAttribute("USER_UNIQ_ID",
					loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		}

		return "egovframework/com/dam/spe/req/EgovComDamRequestOfferList";

	}

	/**
	 * 지식정보제공/지식정보요청 목록을 상세조회 조회한다.
	 * 
	 * @param searchVO
	 * @param RequestOfferVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/dam/spe/req/EgovRequestOfferVODetail"
	 * @throws Exception
	 */
	@PostMapping("/dam/spe/req/detailRequestOffer.do")
	@RequireAdmin
	public String EgovRequestOfferDetail(@ModelAttribute("searchVO") RequestOfferVO searchVO,
			RequestOfferVO requestOfferVO, @RequestParam Map<?, ?> commandMap,
			ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/dam/spe/req/EgovComDamRequestOfferDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		if (sCmd.equals("del")) {
			RequestOfferVO stored = EgovAuthorizationHelper.requireTarget(egovRequestOfferVOService.selectRequestOfferDetail(requestOfferVO));
			EgovAuthorizationHelper.assertOwner(writerOf(stored));
			assertDesignatedExpert(stored);

			HashMap<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("ansParents", requestOfferVO.getKnoId());

			// 하위답변 검색 건수를 체크
			if (egovRequestOfferVOService.selectRequestOfferDelCnt(hmParam) > 0) {
				// 에러 메세지 출력
				String reusltScript = "";

				reusltScript += "<script type='text/javaScript' language='javascript'>";
				reusltScript += "alert(' 하위 답변이 등록되어 있어 삭제할수 없습니다!  ');";
				reusltScript += "</script>";

				model.addAttribute("reusltScript", reusltScript);

				sCmd = "delMsg";
			} else {
				egovRequestOfferVOService.deleteRequestOffer(requestOfferVO);
				sLocationUrl = "forward:/dam/spe/req/listRequestOffer.do";
			}
		}

		if (!sCmd.equals("del")) {
			// 상세정보 불러오기
			RequestOfferVO requestOfferVOs = egovRequestOfferVOService.selectRequestOfferDetail(requestOfferVO);
			if (requestOfferVOs == null) {
				return "forward:/dam/spe/req/listRequestOffer.do";
			}
			model.addAttribute("requestOfferVO", requestOfferVOs);

			// 조직유형 불러오기
			MapTeamVO mapTeamVO = new MapTeamVO();
			mapTeamVO.setRecordCountPerPage(999999);
			mapTeamVO.setFirstIndex(0);
			mapTeamVO.setSearchCondition("MaterialList");
			List<MapTeamVO> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
			model.addAttribute("mapTeamList", mapTeamList);

			// 지식유형코드불러오기
			MapMaterialVO searchMatVO = new MapMaterialVO();
			searchMatVO.setRecordCountPerPage(999999);
			searchMatVO.setFirstIndex(0);
			searchMatVO.setSearchCondition("orgnztId");
			searchMatVO.setSearchKeyword(requestOfferVOs.getOrgnztId());
			List<MapMaterialVO> mapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
			model.addAttribute("mapMaterialList", mapMaterialList);

			// (지식전문가/지식사용자) 검사 및 설정
			HashMap<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("speId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

			// 아이디 설정
			model.addAttribute("USER_UNIQ_ID",
					loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			// 지식전문가 일때
			if (egovRequestOfferVOService.selectRequestOfferSpeCheck(hmParam)) {
				model.addAttribute("IS_SPE", "Y");
			} else {
				model.addAttribute("IS_SPE", "N");
			}

		}

		return sLocationUrl;

	}

	/**
	 * 지식정보제공/지식정보요청를 수정 조회 한다.
	 *
	 * @param searchVO
	 * @param requestOfferVO
	 * @param model
	 * @return "egovframework/com/dam/spe/req/EgovComDamRequestOfferUpdt"
	 * @throws Exception
	 */
	@PostMapping("/dam/spe/req/updtRequestOffer.do")
	@RequireAdmin
	public String EgovRequestOfferModify(@ModelAttribute("searchVO") RequestOfferVO searchVO,
			@ModelAttribute("requestOfferVO") RequestOfferVO requestOfferVO,
			ModelMap model, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 수정정보 불러오기
		RequestOfferVO requestOfferVOs = egovRequestOfferVOService.selectRequestOfferDetail(requestOfferVO);
		if (requestOfferVOs == null) {
			return "forward:/dam/spe/req/listRequestOffer.do";
		}
		EgovAuthorizationHelper.assertOwner(writerOf(requestOfferVOs));
		assertDesignatedExpert(requestOfferVOs);
		EgovAttachmentGrants.allowDelete(request, requestOfferVOs.getAtchFileId());
		model.addAttribute("requestOfferVO", requestOfferVOs);

		// 조직유형 불러오기
		MapTeamVO mapTeamVO = new MapTeamVO();
		mapTeamVO.setRecordCountPerPage(999999);
		mapTeamVO.setFirstIndex(0);
		mapTeamVO.setSearchCondition("MaterialList");
		List<MapTeamVO> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
		model.addAttribute("mapTeamList", mapTeamList);

		// 지식유형코드불러오기
		MapMaterialVO searchMatVO = new MapMaterialVO();
		searchMatVO.setRecordCountPerPage(999999);
		searchMatVO.setFirstIndex(0);
		searchMatVO.setSearchCondition("orgnztId");
		searchMatVO.setSearchKeyword(requestOfferVOs.getOrgnztId());

		List<MapMaterialVO> mapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
		model.addAttribute("mapMaterialList", mapMaterialList);

		// 파일업로드 제한
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

		model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
		model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		return "egovframework/com/dam/spe/req/EgovComDamRequestOfferUpdt";
	}

	/**
	 * 지식정보제공/지식정보요청를 수정한다.
	 *
	 * @param multiRequest
	 * @param searchVO
	 * @param requestOfferVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/dam/spe/req/EgovComDamRequestOfferUpdt"
	 * @throws Exception
	 */
	@PostMapping("/dam/spe/req/updtRequestOfferActor.do")
	@RequireAdmin
	public String EgovRequestOfferModifyActor(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") RequestOfferVO searchVO,
			@Valid @ModelAttribute("requestOfferVO") RequestOfferVO requestOfferVO, BindingResult bindingResult,
			ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		RequestOfferVO storedOffer = EgovAuthorizationHelper.requireTarget(egovRequestOfferVOService.selectRequestOfferDetail(requestOfferVO));
		EgovAuthorizationHelper.assertOwner(writerOf(storedOffer));
		assertDesignatedExpert(storedOffer);
		// 첨부 그룹은 요청값이 아니라 저장된 원본의 것만 쓴다(남의 첨부 ID 저장 → 삭제 허가 우회 차단)
		requestOfferVO.setAtchFileId(Objects.toString(storedOffer.getAtchFileId(), ""));
		// 전문가·요청자·답변 위치도 원본 값을 쓴다(요청값으로 타인 명의·다른 글 답변으로 바꾸기 차단)
		requestOfferVO.setSpeId(storedOffer.getSpeId());
		requestOfferVO.setEmplyrId(storedOffer.getEmplyrId());
		requestOfferVO.setAnsParents(storedOffer.getAnsParents());
		requestOfferVO.setAnsDepth(storedOffer.getAnsDepth());
		requestOfferVO.setAnsSeq(storedOffer.getAnsSeq());
		requestOfferVO.setAnsNumber(storedOffer.getAnsNumber());
		// 파일업로드 제한
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

		model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
		model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		if (bindingResult.hasErrors()) {
			// 조직유형 불러오기
			MapTeamVO mapTeamVO = new MapTeamVO();
			mapTeamVO.setRecordCountPerPage(999999);
			mapTeamVO.setFirstIndex(0);
			mapTeamVO.setSearchCondition("MaterialList");
			List<MapTeamVO> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
			model.addAttribute("mapTeamList", mapTeamList);

			// 지식유형코드불러오기
			MapMaterialVO searchMatVO = new MapMaterialVO();
			searchMatVO.setRecordCountPerPage(999999);
			searchMatVO.setFirstIndex(0);
			searchMatVO.setSearchCondition("orgnztId");
			searchMatVO.setSearchKeyword(requestOfferVO.getOrgnztId());
			List<MapMaterialVO> mapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
			model.addAttribute("mapMaterialList", mapMaterialList);

			return "egovframework/com/dam/spe/req/EgovComDamRequestOfferUpdt";
		}

		// 아이디 설정 (원 작성자는 유지, 최종수정자만 갱신)
		requestOfferVO.setFrstRegisterId(storedOffer == null ? "" : EgovStringUtil.isNullToString(storedOffer.getFrstRegisterId()));
		requestOfferVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		// 첨부파일 관련 ID 생성 start....
		String atchFileId = requestOfferVO.getAtchFileId();

		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if (!files.isEmpty()) {
			if (atchFileId == null || atchFileId.isEmpty()) {
				List<FileVO> fvoList = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(fvoList);

				// 첨부파일 ID 셋팅
				requestOfferVO.setAtchFileId(atchFileId);
			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int fileKeyParam = fileMngService.getMaxFileSN(fvo);
				List<FileVO> fvoList = fileUtil.parseFileInf(files, "DSCH_", fileKeyParam, atchFileId, "");
				fileMngService.updateFileInfs(fvoList);
			}
		}

		// 저장
		egovRequestOfferVOService.updateRequestOffer(requestOfferVO);

		return "forward:/dam/spe/req/listRequestOffer.do";
	}

	/**
	 * 지식정보제공/지식정보요청를 등록 조회 한다.
	 * 
	 * @param searchVO
	 * @param commandMap
	 * @param RequestOfferVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/dam/spe/req/EgovRequestOfferVORegist"
	 * @throws Exception
	 */
	@PostMapping("/dam/spe/req/registRequestOffer.do")
	@RequireAdmin
	public String EgovRequestOfferRegist(
			// @ModelAttribute("searchVO") RequestOfferVO searchVO,
			@RequestParam Map<?, ?> commandMap, @ModelAttribute("requestOfferVO") RequestOfferVO requestOfferVO,
			@ModelAttribute("mapMaterial") MapMaterial mapMaterial, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		MapTeamVO mapTeamVO = new MapTeamVO();
		mapTeamVO.setRecordCountPerPage(999999);
		mapTeamVO.setFirstIndex(0);
		mapTeamVO.setSearchCondition("MaterialList");
		List<MapTeamVO> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
		model.addAttribute("mapTeamList", mapTeamList);

		MapMaterialVO searchMatVO = new MapMaterialVO();
		searchMatVO.setRecordCountPerPage(999999);
		searchMatVO.setFirstIndex(0);
		searchMatVO.setSearchCondition("orgnztId");
		searchMatVO.setSearchKeyword(requestOfferVO.getOrgnztId());

		// if (mapMaterial.getOrgnztId().equals("")) {
		// EgovMap emp = (EgovMap)MapTeamList.get(0);
		// mapMaterial.setOrgnztId(emp.get("orgnztId").toString());
		// }

		List<MapMaterialVO> mapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
		model.addAttribute("mapMaterialList", mapMaterialList);

		model.addAttribute("cmd", sCmd);

		// 파일업로드 제한
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

		model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
		model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		return "egovframework/com/dam/spe/req/EgovComDamRequestOfferRegist";
	}

	/**
	 * 지식정보제공/지식정보요청를 등록을 처리 한다.
	 *
	 * @param multiRequest
	 * @param searchVO
	 * @param commandMap
	 * @param requestOfferVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/dam/spe/req/EgovComDamRequestOfferRegist"
	 * @throws Exception
	 */
	@PostMapping("/dam/spe/req/registRequestOfferActor.do")
	@RequireAdmin
	public String EgovRequestOfferRegistActor(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") RequestOfferVO searchVO, @RequestParam Map<?, ?> commandMap,
			@Valid @ModelAttribute("requestOfferVO") RequestOfferVO requestOfferVO, BindingResult bindingResult,
			ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			redirectAttributes.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// 파일업로드 제한
		String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
		String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

		model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
		model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

		// cmd 파라미터 (reply: 답변, 그 외: 일반 등록)
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		if (bindingResult.hasErrors()) {
			// 조직유형 불러오기
			MapTeamVO mapTeamVO = new MapTeamVO();
			mapTeamVO.setRecordCountPerPage(999999);
			mapTeamVO.setFirstIndex(0);
			mapTeamVO.setSearchCondition("MaterialList");
			List<MapTeamVO> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
			model.addAttribute("mapTeamList", mapTeamList);

			// 지식유형코드불러오기
			MapMaterialVO searchMatVO = new MapMaterialVO();
			searchMatVO.setRecordCountPerPage(999999);
			searchMatVO.setFirstIndex(0);
			searchMatVO.setSearchCondition("orgnztId");
			searchMatVO.setSearchKeyword(requestOfferVO.getOrgnztId());
			List<MapMaterialVO> mapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
			model.addAttribute("mapMaterialList", mapMaterialList);

			model.addAttribute("cmd", sCmd);
			return "egovframework/com/dam/spe/req/EgovComDamRequestOfferRegist";
		}

		// 아이디 설정
		requestOfferVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		requestOfferVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		// (지식전문가/지식사용자) 검사 및 설정
		HashMap<String, String> hmParam = new HashMap<String, String>();
		hmParam.put("speId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		// 지식전문가 답변일때
		if (sCmd.equals("reply") && egovRequestOfferVOService.selectRequestOfferSpeCheck(hmParam)) {
			requestOfferVO.setSpeId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		// 지식전문가 아니고 reply 일때 (권한 없음)
		} else if (sCmd.equals("reply")) {
			// 조직유형 불러오기
			MapTeamVO mapTeamVO = new MapTeamVO();
			mapTeamVO.setRecordCountPerPage(999999);
			mapTeamVO.setFirstIndex(0);
			mapTeamVO.setSearchCondition("MaterialList");
			List<MapTeamVO> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
			model.addAttribute("mapTeamList", mapTeamList);

			// 지식유형코드불러오기
			MapMaterialVO searchMatVO = new MapMaterialVO();
			searchMatVO.setRecordCountPerPage(999999);
			searchMatVO.setFirstIndex(0);
			searchMatVO.setSearchCondition("orgnztId");
			searchMatVO.setSearchKeyword(requestOfferVO.getOrgnztId());
			List<MapMaterialVO> mapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
			model.addAttribute("mapMaterialList", mapMaterialList);

			model.addAttribute("cmd", sCmd);
			return "egovframework/com/dam/spe/req/EgovComDamRequestOfferRegist";
		// 일반사용자일때
		} else {
			requestOfferVO.setEmplyrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		}

		// 첨부파일 관련 첨부파일ID 생성
		String atchFileId = "";

		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if (!files.isEmpty()) {
			List<FileVO> fvoList = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(fvoList);
		}
		// 새 자료에는 이번 요청에서 만든 첨부만 연결한다(요청값 atchFileId 로 남의 첨부 그룹 연결 → 삭제 허가 취득 차단)
		requestOfferVO.setAtchFileId(atchFileId);

		// 저장
		egovRequestOfferVOService.insertRequestOffer(requestOfferVO);

		return "forward:/dam/spe/req/listRequestOffer.do";
	}


	/**
	 * 작성자 — 요청 글은 요청한 사람, 전문가 답변(speId 있음)은 답변한 지정 전문가.
	 */
	private static String writerOf(RequestOfferVO stored) {
		return EgovStringUtil.isEmpty(stored.getSpeId()) ? stored.getFrstRegisterId() : stored.getSpeId();
	}

	/**
	 * 전문가 답변은 지금도 지식전문가로 지정된 사람만 고친다(답변 등록과 같은 기준).
	 */
	private void assertDesignatedExpert(RequestOfferVO stored) throws Exception {
		if (EgovStringUtil.isEmpty(stored.getSpeId())) {
			return;
		}
		HashMap<String, String> hmParam = new HashMap<String, String>();
		hmParam.put("speId", stored.getSpeId());
		if (!egovRequestOfferVOService.selectRequestOfferSpeCheck(hmParam)) {
			throw new EgovAccessDeniedException("권한이 없습니다.");
		}
	}
}
