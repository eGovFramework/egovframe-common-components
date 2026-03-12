package egovframework.com.uss.ion.mtg.web;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.mtg.service.EgovMtgPlaceManageService;
import egovframework.com.uss.ion.mtg.service.MtgPlaceManageVO;
import egovframework.com.uss.ion.mtg.service.MtgPlaceResveVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * <pre>
 * 개요
 * - 회의실관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 회의실관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2025.08.08  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovMtgPlaceManageController {

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovMtgPlaceManageService")
	private EgovMtgPlaceManageService egovMtgPlaceManageService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	// 첨부파일 관련
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/**
	 * 회의실관리 목록화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/uss/ion/mtg/selectMtgPlaceManageListView.do")
	public String selectMtgPlaceManageListView() throws Exception {

		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceManageList";
	}

	/**
	 * 회의실관리정보를 관리하기 위해 등록된 회의실관리 목록을 조회한다.
	 * 
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "회의실관리", order = 870, gid = 50)
	@RequestMapping(value = "/uss/ion/mtg/selectMtgPlaceManageList.do")
	public String selectMtgPlaceManageList(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mtgPlaceManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mtgPlaceManageVO.getPageUnit());
		paginationInfo.setPageSize(mtgPlaceManageVO.getPageSize());

		mtgPlaceManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mtgPlaceManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mtgPlaceManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		mtgPlaceManageVO.setMtgPlaceManageList(egovMtgPlaceManageService.selectMtgPlaceManageList(mtgPlaceManageVO));

		int totCnt = egovMtgPlaceManageService.selectMtgPlaceManageListTotCnt(mtgPlaceManageVO);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("mtgPlaceManageList", mtgPlaceManageVO.getMtgPlaceManageList());
		model.addAttribute("mtgPlaceManageVO", mtgPlaceManageVO);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceManageList";
	}

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/mtg/selectMtgPlaceManage.do")
	public String selectMtgPlaceManage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM070");
		List<CmmnDetailCode> lcSeCodeList = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("lcSeCode", lcSeCodeList);
		model.addAttribute("mtgPlaceManageVO", egovMtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if (sCmd.equals("update")) {
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceUpdt";
		} else {
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceDetail";
		}
	}

	/**
	 * 회의실관리 등록 화면으로 이동한다.
	 * 
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/mtg/insertViewMtgPlace.do")
	public String insertViewMtgPlaceManage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			ModelMap model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM070");
		List<CmmnDetailCode> lcSeCodeList = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("lcSeCode", lcSeCodeList);

		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceRegist";
	}

	/**
	 * 회의실관리정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceManage - 회의실관리 model
	 * @return String - 리턴 Url
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uss/ion/mtg/insertMtgPlace.do")
	public String insertMtgPlaceManage(final MultipartHttpServletRequest multiRequest,
			@Valid @ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO, BindingResult bindingResult,
			SessionStatus status, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("mtgPlaceManageVO", mtgPlaceManageVO);
			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM070");
			model.addAttribute("lcSeCode", cmmUseService.selectCmmCodeDetail(vo));
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceRegist";
		}
		List<FileVO> fvoList = null;
		String atchFileId = "";
		final List<MultipartFile> files = multiRequest.getFiles("file_1");
		if (!files.isEmpty()) {
			fvoList = fileUtil.parseFileInf(files, "MTG_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(fvoList);
		}
		mtgPlaceManageVO.setAtchFileId(atchFileId);

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		status.setComplete();
		egovMtgPlaceManageService.insertMtgPlaceManage(mtgPlaceManageVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

		return "redirect:/uss/ion/mtg/selectMtgPlaceManageList.do";
	}

	/**
	 * 기 등록된 회의실관리정보를 수정한다.
	 * 
	 * @param mtgPlaceManage - 회의실관리 model
	 * @return String - 리턴 Url
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uss/ion/mtg/updtMtgPlace.do")
	public String updateMtgPlaceManage(final MultipartHttpServletRequest multiRequest,
			@RequestParam("atchFileAt") String atchFileAt,
			@Valid @ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO, BindingResult bindingResult,
			SessionStatus status, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("mtgPlaceManageVO", mtgPlaceManageVO);
			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM070");
			model.addAttribute("lcSeCode", cmmUseService.selectCmmCodeDetail(vo));
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceUpdt";
		}
		String atchFileId = mtgPlaceManageVO.getAtchFileId();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if (!files.isEmpty()) {
			if ("N".equals(atchFileAt)) {
				List<FileVO> fvoList = fileUtil.parseFileInf(files, "MTG_", 0, atchFileId, "");
				atchFileId = fileMngService.insertFileInfs(fvoList);
				mtgPlaceManageVO.setAtchFileId(atchFileId);
			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int fileKeyParam = fileMngService.getMaxFileSN(fvo);
				List<FileVO> fvoList = fileUtil.parseFileInf(files, "MTG_", fileKeyParam, atchFileId, "");
				fileMngService.updateFileInfs(fvoList);
			}
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		status.setComplete();
		egovMtgPlaceManageService.updtMtgPlaceManage(mtgPlaceManageVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

		return "redirect:/uss/ion/mtg/selectMtgPlaceManageList.do";
	}

	/**
	 * 기 등록된 회의실관리정보를 삭제한다.
	 * 
	 * @param mtgPlaceManage - 회의실관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/mtg/deleteMtgPlaceManage.do")
	public String deleteMtgPlaceManage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			SessionStatus status, ModelMap model) throws Exception {
		String atchFileId = mtgPlaceManageVO.getAtchFileId();

		egovMtgPlaceManageService.deleteMtgPlaceManage(mtgPlaceManageVO);

		FileVO fvo = new FileVO();
		fvo.setAtchFileId(atchFileId);
		fileMngService.deleteAllFileInf(fvo);

		status.setComplete();
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/mtg/selectMtgPlaceManageList.do";
	}

	/**
	 * 등록된 회의실관리의 이미지 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/mtg/selectMtgPlaceImage.do")
	public String selectMtgPlaceImage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			@RequestParam("sTmMtgPlaceId") String sTmMtgPlaceId, @RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {
		mtgPlaceManageVO.setMtgPlaceId(sTmMtgPlaceId);

		MtgPlaceManageVO resultVO = egovMtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO);

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(resultVO.getAtchFileId());
		List<FileVO> result = fileMngService.selectImageFileList(fileVO);

		model.addAttribute("fileList", result);
		model.addAttribute("mtgPlaceManageVO", egovMtgPlaceManageService.selectMtgPlaceManage(mtgPlaceManageVO));

		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceImageDetail";
	}

	/**** 회의실 예약 ****/

	/**
	 * 회의실예약 정보를 관리하기 위해 등록된 회의실예약 목록을 조회한다.
	 * 
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "회의실예약관리", order = 871, gid = 50)
	@RequestMapping(value = "/uss/ion/mtg/selectMtgPlaceResveManageList.do")
	public String selectMtgPlaceResveManageList(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			ModelMap model) throws Exception {
		/*
		 * ***************************************************************** // 캘런더 설정
		 * 로직
		 */
		Calendar calNow = Calendar.getInstance();
		/*
		 * String strYear = (String)commandMap.get("year"); String strMonth =
		 * (String)commandMap.get("month"); String strDay =(
		 * String)commandMap.get("day");
		 */
		String strSearchDay = "";

		int iNowYear = calNow.get(Calendar.YEAR);
		int iNowMonth = calNow.get(Calendar.MONTH);
		int iNowDay = calNow.get(Calendar.DATE);

		if (mtgPlaceManageVO.getResveDe() == null) {
			strSearchDay = Integer.toString(iNowYear);
			strSearchDay += dateTypeIntForString(iNowMonth + 1);
			strSearchDay += dateTypeIntForString(iNowDay);
			mtgPlaceManageVO.setResveDe(strSearchDay);
			mtgPlaceManageVO.setResveDeView(EgovDateUtil.formatDate(strSearchDay, "-"));
		} else {
			mtgPlaceManageVO.setResveDeView(EgovDateUtil.formatDate(mtgPlaceManageVO.getResveDe(), "-"));
		}

		mtgPlaceManageVO.setResveDe(EgovDateUtil.formatDate(mtgPlaceManageVO.getResveDe(), "-")); // formatDate
		// mtgPlaceManageVO.setResveDe(mtgPlaceManageVO.getResveDe());
		mtgPlaceManageVO
				.setMtgPlaceManageList(egovMtgPlaceManageService.selectMtgPlaceResveManageList(mtgPlaceManageVO));
		model.addAttribute("mtgPlaceManageList", mtgPlaceManageVO.getMtgPlaceManageList());
		model.addAttribute("mtgPlaceManageVO", mtgPlaceManageVO);
		// model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveManageList";
	}

	/**
	 * 회의실예약 신청 화면을 조회한다.
	 * 
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uss/ion/mtg/selectMtgPlaceResveManage.do")
	public String selectMtgPlaceResveManage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			BindingResult bindingResult, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		String sTempResveDe = mtgPlaceManageVO.getResveDe();
		String sTempResveBeginTm = mtgPlaceManageVO.getResveBeginTm();
		String sTempResveEndTm = mtgPlaceManageVO.getResveEndTm();

		MtgPlaceManageVO resultVO = egovMtgPlaceManageService.selectMtgPlaceResve(mtgPlaceManageVO);
		resultVO.setResveDe(sTempResveDe);
		resultVO.setResveBeginTm(sTempResveBeginTm);
		resultVO.setResveEndTm(sTempResveEndTm);
		resultVO.setResveDe(EgovDateUtil.formatDate(resultVO.getResveDe(), "-"));

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		resultVO.setMtgPlaceTemp4(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()));
		resultVO.setMtgPlaceTemp5(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getOrgnztNm()));
		MtgPlaceResveVO mtgPlaceResveVO = new MtgPlaceResveVO();
		mtgPlaceResveVO.setMtgPlaceId(resultVO.getMtgPlaceId());
		mtgPlaceResveVO.setResveDe(resultVO.getResveDe());
		mtgPlaceResveVO.setResveBeginTm(resultVO.getResveBeginTm());
		mtgPlaceResveVO.setResveEndTm(resultVO.getResveEndTm());
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		model.addAttribute("mtgPlaceManageVO", resultVO);
		model.addAttribute("mtgPlaceResveVO", mtgPlaceResveVO);
		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveRegist";
	}

	/**
	 * 등록된 회의실예약 상세정보를 조회한다.
	 * 
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/mtg/selectMtgPlaceResveManageDetail.do")
	public String selectMtgPlaceResveManageDetail(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			BindingResult bindingResult, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

		MtgPlaceManageVO resultVO = egovMtgPlaceManageService.selectMtgPlaceResveDetail(mtgPlaceManageVO);
		resultVO.setResveDe(EgovDateUtil.formatDate(resultVO.getResveDe(), "-"));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if (sCmd.equals("detail")) {
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			resultVO.setUsidTemp(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			String resveBeginTm = resultVO.getResveBeginTm();
			String resveEndTm = resultVO.getResveEndTm();
			if (resveBeginTm != null && resveBeginTm.length() == 3) {
				resveBeginTm = "0" + resveBeginTm.substring(0, 1) + ":" + resveBeginTm.substring(1, 3);
			} else if (resveBeginTm != null && resveBeginTm.length() == 4) {
				resveBeginTm = resveBeginTm.substring(0, 2) + ":" + resveBeginTm.substring(2, 4);
			}
			if (resveEndTm != null && resveEndTm.length() == 3) {
				resveEndTm = "0" + resveEndTm.substring(0, 1) + ":" + resveEndTm.substring(1, 3);
			} else if (resveEndTm != null && resveEndTm.length() == 4) {
				resveEndTm = resveEndTm.substring(0, 2) + ":" + resveEndTm.substring(2, 4);
			}

			resultVO.setResveBeginTm(resveBeginTm);
			resultVO.setResveEndTm(resveEndTm);
			MtgPlaceResveVO mtgPlaceResveVO = new MtgPlaceResveVO();
			mtgPlaceResveVO.setResveId(resultVO.getResveId());
			mtgPlaceResveVO.setMtgPlaceId(resultVO.getMtgPlaceId());
			mtgPlaceResveVO.setResveManId(resultVO.getResveManId());
			model.addAttribute("mtgPlaceManageVO", resultVO);
			model.addAttribute("mtgPlaceResveVO", mtgPlaceResveVO);
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveDetail";
		} else {
			MtgPlaceResveVO mtgPlaceResveVO = new MtgPlaceResveVO();
			mtgPlaceResveVO.setResveId(resultVO.getResveId());
			mtgPlaceResveVO.setMtgPlaceId(resultVO.getMtgPlaceId());
			mtgPlaceResveVO.setMtgSj(resultVO.getMtgSj());
			mtgPlaceResveVO.setResveManId(resultVO.getResveManId());
			mtgPlaceResveVO.setResveDe(resultVO.getResveDe());
			mtgPlaceResveVO.setResveBeginTm(resultVO.getResveBeginTm());
			mtgPlaceResveVO.setResveEndTm(resultVO.getResveEndTm());
			mtgPlaceResveVO.setAtndncNmpr(resultVO.getAtndncNmpr());
			mtgPlaceResveVO.setMtgCn(resultVO.getMtgCn());
			model.addAttribute("mtgPlaceManageVO", resultVO);
			model.addAttribute("mtgPlaceResveVO", mtgPlaceResveVO);
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveUpdt";
		}
	}

	/**
	 * 회의실예약 정보를 신규로 등록한다.
	 * 
	 * @param mtgPlaceResve - 회의실예약 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/mtg/insertMtgPlaceResve.do")
	public String insertMtgPlaceResveManage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			@Valid @ModelAttribute("mtgPlaceResveVO") MtgPlaceResveVO mtgPlaceResveVO, BindingResult bindingResult,
			@RequestParam(value = "dplactCeck", required = false) String dplactCeck,
			SessionStatus status, ModelMap model) throws Exception {

		if (!"Y".equals(dplactCeck)) {
			bindingResult.rejectValue("resveBeginTm", "comUssIonMtg.mtgPlaceResveRegist.dplactCeck",
					egovMessageSource.getMessage("comUssIonMtg.mtgPlaceResveRegist.dplactCeck"));
		}
		if (!bindingResult.hasErrors()) {
			MtgPlaceManageVO reqPlaceVO = new MtgPlaceManageVO();
			reqPlaceVO.setMtgPlaceId(mtgPlaceResveVO.getMtgPlaceId() != null ? mtgPlaceResveVO.getMtgPlaceId() : mtgPlaceManageVO.getMtgPlaceId());
			MtgPlaceManageVO placeVO = egovMtgPlaceManageService.selectMtgPlaceResve(reqPlaceVO);
			if (placeVO != null && placeVO.getOpnBeginTm() != null && placeVO.getOpnEndTm() != null
					&& mtgPlaceResveVO.getResveBeginTm() != null && mtgPlaceResveVO.getResveEndTm() != null) {
				int opnBegin = timeToMinutes(placeVO.getOpnBeginTm());
				int opnEnd = timeToMinutes(placeVO.getOpnEndTm());
				int resveBegin = timeToMinutes(mtgPlaceResveVO.getResveBeginTm());
				int resveEnd = timeToMinutes(mtgPlaceResveVO.getResveEndTm());
				if (resveBegin < opnBegin || resveEnd > opnEnd) {
					bindingResult.rejectValue("resveBeginTm", "comUssIonMtg.mtgPlaceResveTimeWithinOpen",
							egovMessageSource.getMessage("comUssIonMtg.mtgPlaceResveTimeWithinOpen"));
				}
			}
		}
		if (bindingResult.hasErrors()) {
			MtgPlaceManageVO reqPlaceVO = new MtgPlaceManageVO();
			reqPlaceVO.setMtgPlaceId(mtgPlaceResveVO.getMtgPlaceId() != null ? mtgPlaceResveVO.getMtgPlaceId() : mtgPlaceManageVO.getMtgPlaceId());
			MtgPlaceManageVO placeVO = egovMtgPlaceManageService.selectMtgPlaceResve(reqPlaceVO);
			if (placeVO != null) {
				placeVO.setResveDe(mtgPlaceResveVO.getResveDe() != null ? EgovDateUtil.formatDate(mtgPlaceResveVO.getResveDe(), "-") : null);
				placeVO.setResveBeginTm(mtgPlaceResveVO.getResveBeginTm());
				placeVO.setResveEndTm(mtgPlaceResveVO.getResveEndTm());
				LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
				placeVO.setMtgPlaceTemp4(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()));
				placeVO.setMtgPlaceTemp5(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getOrgnztNm()));
				model.addAttribute("mtgPlaceManageVO", placeVO);
			} else {
				model.addAttribute("mtgPlaceManageVO", mtgPlaceManageVO);
			}
			model.addAttribute("mtgPlaceResveVO", mtgPlaceResveVO);
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveRegist";
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		mtgPlaceResveVO.setResveManId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		mtgPlaceResveVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		mtgPlaceResveVO.setMtgPlaceId(mtgPlaceManageVO.getMtgPlaceId());

		status.setComplete();
		egovMtgPlaceManageService.insertMtgPlaceResve(mtgPlaceResveVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

		return "forward:/uss/ion/mtg/selectMtgPlaceResveManageList.do";
	}

	/**
	 * 기 등록된 회의실예약 정보를 수정한다.
	 * 
	 * @param mtgPlaceResve - 회의실예약 model
	 * @return String - 리턴 Url
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uss/ion/mtg/updtMtgPlaceResve.do")
	public String updtMtgPlaceResveManage(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			@Valid @ModelAttribute("mtgPlaceResveVO") MtgPlaceResveVO mtgPlaceResveVO, BindingResult bindingResult,
			@RequestParam(value = "dplactCeck", required = false) String dplactCeck,
			SessionStatus status, ModelMap model) throws Exception {

		if (!"Y".equals(dplactCeck)) {
			bindingResult.rejectValue("resveBeginTm", "comUssIonMtg.mtgPlaceResveRegist.dplactCeck",
					egovMessageSource.getMessage("comUssIonMtg.mtgPlaceResveRegist.dplactCeck"));
		}
		if (!bindingResult.hasErrors()) {
			MtgPlaceManageVO reqPlaceVO = new MtgPlaceManageVO();
			reqPlaceVO.setMtgPlaceId(mtgPlaceResveVO.getMtgPlaceId());
			MtgPlaceManageVO placeVO = egovMtgPlaceManageService.selectMtgPlaceResve(reqPlaceVO);
			if (placeVO != null && placeVO.getOpnBeginTm() != null && placeVO.getOpnEndTm() != null
					&& mtgPlaceResveVO.getResveBeginTm() != null && mtgPlaceResveVO.getResveEndTm() != null) {
				int opnBegin = timeToMinutes(placeVO.getOpnBeginTm());
				int opnEnd = timeToMinutes(placeVO.getOpnEndTm());
				int resveBegin = timeToMinutes(mtgPlaceResveVO.getResveBeginTm());
				int resveEnd = timeToMinutes(mtgPlaceResveVO.getResveEndTm());
				if (resveBegin < opnBegin || resveEnd > opnEnd) {
					bindingResult.rejectValue("resveBeginTm", "comUssIonMtg.mtgPlaceResveTimeWithinOpen",
							egovMessageSource.getMessage("comUssIonMtg.mtgPlaceResveTimeWithinOpen"));
				}
			}
		}
		if (bindingResult.hasErrors()) {
			mtgPlaceManageVO.setMtgPlaceId(mtgPlaceResveVO.getMtgPlaceId());
			mtgPlaceManageVO.setResveId(mtgPlaceResveVO.getResveId());
			MtgPlaceManageVO detailVO = egovMtgPlaceManageService.selectMtgPlaceResveDetail(mtgPlaceManageVO);
			if (detailVO != null) {
				detailVO.setResveDe(EgovDateUtil.formatDate(detailVO.getResveDe(), "-"));
				model.addAttribute("mtgPlaceManageVO", detailVO);
			} else {
				model.addAttribute("mtgPlaceManageVO", mtgPlaceManageVO);
			}
			model.addAttribute("mtgPlaceResveVO", mtgPlaceResveVO);
			return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveUpdt";
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		status.setComplete();
		egovMtgPlaceManageService.updtMtgPlaceResve(mtgPlaceResveVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

		return "forward:/uss/ion/mtg/selectMtgPlaceResveManageList.do";
	}

	/**
	 * 기 등록된 회의실예약 정보를 삭제한다.
	 * 
	 * @param mtgPlaceResve - 회의실예약 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/mtg/deleteMtgPlaceResve.do")
	public String deleteMtgPlaceResveManage(@ModelAttribute("mtgPlaceResveVO") MtgPlaceResveVO mtgPlaceResveVO,
			SessionStatus status, ModelMap model) throws Exception {

		egovMtgPlaceManageService.deleteMtgPlaceResve(mtgPlaceResveVO);
		status.setComplete();
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/mtg/selectMtgPlaceResveManageList.do";
	}

	/**
	 * 회의실 중복여부 체크.
	 * 
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 중복건수
	 */
	@RequestMapping(value = "/uss/ion/mtg/mtgPlaceResveDplactCeck.do")
	public String mtgPlaceResveDplactCeck(@ModelAttribute("mtgPlaceManageVO") MtgPlaceManageVO mtgPlaceManageVO,
			@RequestParam("sTmResveDe") String sTempResveDe, @RequestParam("sTmResveBeginTm") String sTempResveBeginTm,
			@RequestParam("sTmResveEndTm") String sTempResveEndTm,
			@RequestParam("sTmMtgPlaceId") String sTempMtgPlaceId, @RequestParam("sTmResveId") String sTempResveId,
			ModelMap model) throws Exception {
		mtgPlaceManageVO.setResveDe(sTempResveDe);
		mtgPlaceManageVO.setMtgPlaceId(sTempMtgPlaceId);
		mtgPlaceManageVO.setResveBeginTm(sTempResveBeginTm);
		mtgPlaceManageVO.setResveEndTm(sTempResveEndTm);
		mtgPlaceManageVO.setResveId(sTempResveId);
		int dplactCeckCnt = egovMtgPlaceManageService.mtgPlaceResveDplactCeck(mtgPlaceManageVO);
		model.addAttribute("dplactCeck", dplactCeckCnt);
		return "egovframework/com/uss/ion/mtg/EgovMtgPlaceResveDplactCeck";
	}

	/**
	 * 0을 붙여 반환
	 * 
	 * @return String
	 * @throws
	 */
	private String dateTypeIntForString(int iInput) {
		String sOutput = "";
		if (Integer.toString(iInput).length() == 1) {
			sOutput = "0" + Integer.toString(iInput);
		} else {
			sOutput = Integer.toString(iInput);
		}
		return sOutput;
	}

	/** 시간 문자열(HH:MM 또는 HHMM)을 분 단위로 변환 */
	private static int timeToMinutes(String s) {
		if (s == null) return 0;
		s = s.trim();
		if (s.contains(":")) {
			String[] p = s.split(":");
			int h = p.length > 0 ? Integer.parseInt(p[0].trim(), 10) : 0;
			int m = p.length > 1 ? Integer.parseInt(p[1].trim(), 10) : 0;
			return h * 60 + m;
		}
		if (s.length() >= 4) {
			int h = Integer.parseInt(s.substring(0, 2), 10);
			int m = Integer.parseInt(s.substring(2, 4), 10);
			return h * 60 + m;
		}
		if (s.length() == 3) {
			int h = Integer.parseInt(s.substring(0, 1), 10);
			int m = Integer.parseInt(s.substring(1, 3), 10);
			return h * 60 + m;
		}
		return 0;
	}
}
