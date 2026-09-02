package egovframework.com.uss.ion.rwd.web;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rwd.service.EgovRwardManageService;
import egovframework.com.uss.ion.rwd.service.RwardManage;
import egovframework.com.uss.ion.rwd.service.RwardManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * <pre>
 * 개요
 * - 포상관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 포상관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 포상관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 이용
 * @since 2010.06.15
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.15  이용           최초 생성
 *   2011.08.16  정진오          VcatnManageVO Dependency 제거, 사용하지 않는 객체 선언
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2025.08.15  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovRwardManageController {

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovRwardManageService")
	private EgovRwardManageService egovRwardManageService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	// 첨부파일 관련
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/**
	 * 포상관리 목록화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/uss/ion/rwd/EgovRwardManageListView.do")
	public String selectRwardManageListView(/* @ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, */ // 2011.8.16
																												// 수정분
			ModelMap model) throws Exception {
		List<?> rwardCdCodeList = null;
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM055");
		rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("rwardCodeList", rwardCdCodeList);

		return "egovframework/com/uss/ion/rwd/EgovRwardManageList";
	}

	/**
	 * 포상관리정보를 관리하기 위해 등록된 포상관리 목록을 조회한다.
	 * 
	 * @param rwardManageVO - 포상관리 VO
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "포상관리", order = 920, gid = 50)
	@RequestMapping(value = "/uss/ion/rwd/selectRwardManageList.do")
	public String selectRwardManageList(@ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO, ModelMap model)
			throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rwardManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rwardManageVO.getPageUnit());
		paginationInfo.setPageSize(rwardManageVO.getPageSize());

		rwardManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rwardManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rwardManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		rwardManageVO.setRwardManageList(egovRwardManageService.selectRwardManageList(rwardManageVO));

		model.addAttribute("rwardManageList", rwardManageVO.getRwardManageList());

		int totCnt = egovRwardManageService.selectRwardManageListTotCnt(rwardManageVO);
		paginationInfo.setTotalRecordCount(totCnt);

		List<?> rwardCdCodeList = null;
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM055");
		rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("rwardCodeList", rwardCdCodeList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/rwd/EgovRwardManageList";
	}

	/**
	 * 등록된 포상관리의 상세정보를 조회한다.
	 * 
	 * @param rwardManageVO - 포상관리 VO
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uss/ion/rwd/EgovRwardManageDetail.do")
	public String selectRwardManage(@ModelAttribute("rwardManage") RwardManage rwardManage,
			@ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO, @RequestParam Map<?, ?> commandMap,
			ModelMap model) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd"); // 상세정보 구분
		rwardManageVO.setRwardDe(EgovStringUtil.removeMinusChar(rwardManageVO.getRwardDe()));

		// 등록 상세정보
		RwardManageVO rwardManageVOTemp = egovRwardManageService.selectRwardManage(rwardManageVO);

		model.addAttribute("rwardManageVO", rwardManageVOTemp);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if (sCmd.equals("updt")) {
			RwardManage rwardManage1 = new RwardManage();

			rwardManage1.setRwardId(rwardManageVOTemp.getRwardId());
			rwardManage1.setRwardNm(rwardManageVOTemp.getRwardNm());
			rwardManage1.setPblenCn(rwardManageVOTemp.getPblenCn());
			rwardManage1.setRwardManId(rwardManageVOTemp.getRwardManId());
			rwardManage1.setRwardCd(rwardManageVOTemp.getRwardCd());
			rwardManage1.setRwardDe(rwardManageVOTemp.getRwardDe());
			rwardManage1.setInfrmlSanctnId(rwardManageVOTemp.getInfrmlSanctnId());
			rwardManage1.setSanctnerId(rwardManageVOTemp.getSanctnerId());

			List<?> rwardCdCodeList = null;
			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM055");
			rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
			model.addAttribute("rwardCodeList", rwardCdCodeList);
			model.addAttribute("rwardManage", rwardManage1);
			return "egovframework/com/uss/ion/rwd/EgovRwardUpdt";
		} else {
			return "egovframework/com/uss/ion/rwd/EgovRwardDetail";
		}

	}

	/**
	 * 포상관리 등록 화면으로 이동한다.
	 * 
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uss/ion/rwd/EgovRwardRegist.do")
	public String insertViewRwardManage(@ModelAttribute("rwardManage") RwardManage rwardManage,
			@ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO, ModelMap model) throws Exception {
		List<?> rwardCdCodeList = null;
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM055");
		rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("rwardCodeList", rwardCdCodeList);
		return "egovframework/com/uss/ion/rwd/EgovRwardRegist";
	}

	/**
	 * 포상관리정보를 신규로 등록한다.
	 * 
	 * @param rwardManage - 포상관리 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uss/ion/rwd/insertRwardManage.do")
	public String insertRwardManage(final MultipartHttpServletRequest multiRequest,
			@Valid @ModelAttribute("rwardManage") RwardManage rwardManage, BindingResult bindingResult,
			@ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO, SessionStatus status, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("rwardManageVO", rwardManageVO);
			return "egovframework/com/uss/ion/rwd/EgovRwardRegist";
		} else {
			// 첨부파일 관련 첨부파일ID 생성
			List<FileVO> fvoList = null;
			String atchFileId = "";

			// final Map<String, MultipartFile> files = multiRequest.getFileMap();
			final List<MultipartFile> files = multiRequest.getFiles("file_1");

			if (!files.isEmpty()) {
				fvoList = fileUtil.parseFileInf(files, "RWD_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(fvoList); // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
			}
			// 리턴받은 첨부파일ID를 셋팅한다..
			rwardManage.setAtchFileId(atchFileId); // 첨부파일 ID

			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			rwardManage.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId()); // 최초등록자ID
			egovRwardManageService.insertRwardManage(rwardManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

			return "forward:/uss/ion/rwd/selectRwardManageList.do";

		}
	}

	/**
	 * 기 등록된 포상관리정보를 수정한다.
	 * 
	 * @param rwardManage - 포상관리 model
	 * @return String - 리턴 Url
	 */
	@SuppressWarnings("unused")
	@PostMapping("/uss/ion/rwd/updtRwardManage.do")
	public String updtRwardManage(@RequestParam("atchFileAt") String atchFileAt,
			final MultipartHttpServletRequest multiRequest, @Valid @ModelAttribute("rwardManage") RwardManage rwardManage, BindingResult bindingResult,
			@ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO, SessionStatus status, ModelMap model) throws Exception {

		// 신청자 본인 또는 관리자만 수정 가능하도록 소유권 검증
		RwardManageVO stored = egovRwardManageService.selectRwardManage(rwardManageVO);
		if (stored == null) {
			throw new IllegalStateException("포상 정보가 없습니다.");
		}
		egovAssertAdminOrOwner(stored.getFrstRegisterId());

		if (bindingResult.hasErrors()) {
			// 수정화면 진입(EgovRwardManageDetail.do?cmd=updt)과 동일하게 포상구분 코드목록을 다시 담는다.
			ComDefaultCodeVO rwardCdVo = new ComDefaultCodeVO();
			rwardCdVo.setCodeId("COM055");
			model.addAttribute("rwardCodeList", cmmUseService.selectCmmCodeDetail(rwardCdVo));
			model.addAttribute("rwardManageVO", rwardManageVO);
			model.addAttribute("rwardManage", rwardManage);
			return "egovframework/com/uss/ion/rwd/EgovRwardUpdt";
		} else {
			// 첨부파일 관련 ID 생성 start....
			// 첨부파일 ID 는 폼 값이 아니라 권한 확인에 사용한 조회 결과를 사용한다.
			String atchFileId = stored.getAtchFileId();
			rwardManage.setAtchFileId(atchFileId);

			// final Map<String, MultipartFile> files = multiRequest.getFileMap();
			final List<MultipartFile> files = multiRequest.getFiles("file_1");
			if (!files.isEmpty()) {
				// 신규 생성 여부도 요청값(atchFileAt)이 아니라 조회한 첨부파일 ID 의 존재 여부로 판단한다.
				if (atchFileId == null || atchFileId.isEmpty()) {

					List<FileVO> fvoList = fileUtil.parseFileInf(files, "RWD_", 0, atchFileId, "");
					atchFileId = fileMngService.insertFileInfs(fvoList);

					// 첨부파일 ID 셋팅
					rwardManage.setAtchFileId(atchFileId); // 첨부파일 ID

				} else {
					FileVO fvo = new FileVO();
					fvo.setAtchFileId(atchFileId);
					int fileKeyParam = fileMngService.getMaxFileSN(fvo);
					List<FileVO> fvoList = fileUtil.parseFileInf(files, "RWD_", fileKeyParam, atchFileId, "");
					fileMngService.updateFileInfs(fvoList);
				}
			}
			// 첨부파일 관련 ID 생성 end...
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));
			egovRwardManageService.updtRwardManage(rwardManage);
			return "forward:/uss/ion/rwd/selectRwardManageList.do";
		}
	}

	/**
	 * 기 등록된 포상관리정보를 삭제한다.
	 * 
	 * @param rwardManage - 포상관리 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uss/ion/rwd/deleteRwardManage.do")
	public String deleteRwardManage(@ModelAttribute("rwardManage") RwardManage rwardManage, SessionStatus status,
			ModelMap model) throws Exception {
		// 2026.07.13 KISA 보안취약점 조치
		LoginVO _loginVO = egovAssertLoginUser();

		// 신청자 본인 또는 관리자만 삭제 가능하도록 소유권 검증
		RwardManageVO storedForDelete = new RwardManageVO();
		storedForDelete.setRwardId(rwardManage.getRwardId());
		RwardManageVO stored = egovRwardManageService.selectRwardManage(storedForDelete);
		if (stored == null) {
			throw new IllegalStateException("포상 정보가 없습니다.");
		}
		egovAssertAdminOrOwner(stored.getFrstRegisterId());
		// 약식결재 삭제 대상도 권한 확인에 사용한 레코드로 고정한다.
		rwardManage.setInfrmlSanctnId(stored.getInfrmlSanctnId());

		rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));

		// 첨부파일 삭제를 위한 ID 생성 start....
		// 삭제 대상 첨부파일 ID 도 폼 값이 아니라 조회 결과를 사용한다.
		String atchFileId = stored.getAtchFileId();

		// 포상 삭제 처리
		egovRwardManageService.deleteRwardManage(rwardManage);

		// 첨부파일을 삭제하기 위한 Vo
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(atchFileId);

		fileMngService.deleteAllFileInf(fvo);
		// 첨부파일 삭제 End.............

		status.setComplete();
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/rwd/selectRwardManageList.do";
	}

	/*** 승인관련 ***/
	/**
	 * 포상관리정보 승인 처리를 위해 신청된 포상관리 목록을 조회한다.
	 * 
	 * @param rwardManageVO - 포상관리 VO
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "포상승인관리", order = 921, gid = 50)
	@RequestMapping(value = "/uss/ion/rwd/EgovRwardConfmList.do")
	public String selectRwardManageConfmList(@ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO,
			ModelMap model) throws Exception {
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rwardManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rwardManageVO.getPageUnit());
		paginationInfo.setPageSize(rwardManageVO.getPageSize());

		rwardManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rwardManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rwardManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (user == null) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		rwardManageVO.setSanctnerId(user.getUniqId()); // 사용자가 승인권자인지 조건값 setting selectRwardManageList

		rwardManageVO.setRwardManageList(egovRwardManageService.selectRwardManageConfmList(rwardManageVO));

		model.addAttribute("rwardManageList", rwardManageVO.getRwardManageList());

		int totCnt = egovRwardManageService.selectRwardManageConfmListTotCnt(rwardManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		List<?> rwardCdCodeList = null;
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM055");
		rwardCdCodeList = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("rwardCodeList", rwardCdCodeList);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/rwd/EgovRwardConfmList";
	}

	/**
	 * 포상승인관리 상세정보를 조회한다.
	 * 
	 * @param rwardManageVO - 포상관리 VO
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uss/ion/rwd/EgovRwardConfm.do")
	public String selectRwardConfm(@ModelAttribute("rwardManageVO") RwardManageVO rwardManageVO,
			@ModelAttribute("rwardManage") RwardManage rwardManage, ModelMap model) throws Exception {
		rwardManageVO.setRwardDe(EgovStringUtil.removeMinusChar(rwardManageVO.getRwardDe()));

		// 등록 상세정보
		RwardManageVO rwardManageVOTemp = egovRwardManageService.selectRwardManage(rwardManageVO);

		// 지정된 승인권자 또는 관리자만 승인상세를 열람 가능하도록 소유권 검증
		// (EgovRwardConfmList.do가 SANCTNER_ID=로그인 uniqId로만 목록을 필터링하는 것과 동일한 경계)
		egovAssertAdminOrOwner(rwardManageVOTemp == null ? null : rwardManageVOTemp.getSanctnerId());

		RwardManage rwardManageTemp = new RwardManage();

		rwardManageTemp.setRwardId(rwardManageVOTemp.getRwardId());
		rwardManageTemp.setRwardNm(rwardManageVOTemp.getRwardNm());
		rwardManageTemp.setPblenCn(rwardManageVOTemp.getPblenCn());
		rwardManageTemp.setRwardManId(rwardManageVOTemp.getRwardManId());
		rwardManageTemp.setRwardCd(rwardManageVOTemp.getRwardCd());
		rwardManageTemp.setRwardDe(rwardManageVOTemp.getRwardDe());
		rwardManageTemp.setSanctnerId(rwardManageVOTemp.getSanctnerId());
		rwardManageTemp.setInfrmlSanctnId(rwardManageVOTemp.getInfrmlSanctnId());

		model.addAttribute("rwardManage", rwardManageTemp);
		model.addAttribute("rwardManageVO", rwardManageVOTemp);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/rwd/EgovRwardConfm";
	}

	/**
	 * 신청된 포상을 승인처리한다.
	 * 
	 * @param rwardManage - 포상관리 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/uss/ion/rwd/updtRwardConfm.do")
	public String updtRwardManageConfm(@Valid @ModelAttribute("rwardManage") RwardManage rwardManage,
			BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		// KISA 보안취약점 조치 (2018-12-10, 신용호)
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 지정된 승인권자(SANCTNER_ID) 또는 관리자만 승인·반려 처리 가능하도록 검증
		RwardManageVO storedForConfm = new RwardManageVO();
		storedForConfm.setRwardId(rwardManage.getRwardId());
		RwardManageVO storedConfm = egovRwardManageService.selectRwardManage(storedForConfm);
		if (storedConfm == null) {
			throw new IllegalStateException("포상 정보가 없습니다.");
		}
		egovAssertAdminOrOwner(storedConfm.getSanctnerId());
		// 결재 갱신 대상을 권한 확인에 사용한 레코드로 고정한다. 폼이 보낸 약식결재ID 는 신뢰하지 않는다.
		rwardManage.setInfrmlSanctnId(storedConfm.getInfrmlSanctnId());

		if (bindingResult.hasErrors()) {
			model.addAttribute("rwardManageVO", storedConfm);
			return "egovframework/com/uss/ion/rwd/EgovRwardConfm";
		} else {

			rwardManage.setSanctnerId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
			rwardManage.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
			rwardManage.setRwardDe(EgovStringUtil.removeMinusChar(rwardManage.getRwardDe()));

			egovRwardManageService.updtRwardManageConfm(rwardManage);
			return "forward:/uss/ion/rwd/EgovRwardConfmList.do";
		}
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
