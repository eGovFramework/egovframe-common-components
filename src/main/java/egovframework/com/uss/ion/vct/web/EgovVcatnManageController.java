package egovframework.com.uss.ion.vct.web;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.vct.service.EgovVcatnManageService;
import egovframework.com.uss.ion.vct.service.VcatnManageVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * <pre>
 * 개요
 * - 휴가관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 휴가관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 휴가관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
 *   2025.08.19  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidReassigningParameters(넘겨받는 메소드 parameter 값을 직접 변경하는 코드 탐지)
 *
 *      </pre>
 */
@Controller
public class EgovVcatnManageController {

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovVcatnManageService")
	private EgovVcatnManageService egovVcatnManageService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovVcatnManageController.class);

	/**
	 * 휴가관리 목록화면 이동
	 *
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/uss/ion/vct/EgovVcatnManageListView.do")
	public String selectVcatnManageListView() throws Exception {

		return "egovframework/com/uss/ion/vct/EgovVcatnManageList";
	}

	/**
	 * 휴가관리정보를 관리하기 위해 등록된 휴가관리 목록을 조회한다.
	 *
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "휴가관리", order = 900, gid = 50)
	@RequestMapping(value = "/uss/ion/vct/EgovVcatnManageList.do")
	public String selectVcatnManageList(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, ModelMap model)
			throws Exception {

		String searchKeyword = vcatnManageVO.getSearchKeyword();

		java.util.Calendar cal = java.util.Calendar.getInstance();
		String[] yearList = new String[5];
		for (int x = 0; x < 5; x++) {
			yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) - x);
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (user == null) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		VcatnManageVO resultVO = egovVcatnManageService.selectIndvdlYrycManage(user.getUniqId());

		if (resultVO == null) {
			model.addAttribute("messageTemp",
                egovMessageSource.getMessage("comUssIonVct.vcatnManageList.validate.move")); // 휴가 사용을 위한 개인연차 등록을 위해 개인연차관리 콤포넌트로 이동
			return "egovframework/com/uss/ion/yrc/EgovIndvdlYrycManageList";
		} else {

			resultVO.setSearchKeyword(searchKeyword);

			/** paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(resultVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(resultVO.getPageUnit());
			paginationInfo.setPageSize(resultVO.getPageSize());

			resultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			resultVO.setLastIndex(paginationInfo.getLastRecordIndex());
			resultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			model.addAttribute("vcatnManageVO", resultVO);

			resultVO.setApplcntId(user.getUniqId());
			resultVO.setVcatnManageList(egovVcatnManageService.selectVcatnManageList(resultVO));

			model.addAttribute("vcatnManageList", resultVO.getVcatnManageList());

			int totCnt = egovVcatnManageService.selectVcatnManageListTotCnt(resultVO);
			paginationInfo.setTotalRecordCount(totCnt);

			String accessControll = user.getOrgnztId();

			model.addAttribute("access", accessControll);
			model.addAttribute("yearList", yearList);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

			return "egovframework/com/uss/ion/vct/EgovVcatnManageList";
		}
	}

	/**
	 * 등록된 휴가관리의 상세정보를 조회한다.
	 *
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/vct/EgovVcatnManageDetail.do")
	public String selectVcatnManage(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO,
			@RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd"); // 상세정보 구분
		vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
		vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));

		// 등록 상세정보
		VcatnManageVO deteil = egovVcatnManageService.selectVcatnManage(vcatnManageVO);
		if (deteil == null) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.select"));
			return "forward:/uss/ion/vct/EgovVcatnManageList.do";
		}

		model.addAttribute("vcatnManageVO", deteil);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if (sCmd.equals("updt")) {

			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM056");
			List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);

			model.addAttribute("vcatnSeCode", vcatnSeCodeList);
			return "egovframework/com/uss/ion/vct/EgovVcatnUpdt";
		} else {
			return "egovframework/com/uss/ion/vct/EgovVcatnDetail";
		}
	}

	/**
	 * 휴가관리 등록 화면으로 이동한다.
	 *
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/vct/EgovVcatnRegist.do")
	public String insertViewVcatnManage(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, ModelMap model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (user == null) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// user.getUniqId() null 체크
		String uniqId = user.getUniqId();
		if (uniqId == null || uniqId.trim().isEmpty()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		VcatnManageVO vcatnManageVO1 = null;
			// 2026.03.09 KISA 취약점 조치: try-catch 삭제
			vcatnManageVO1 = egovVcatnManageService.selectIndvdlYrycManage(uniqId);

		if (vcatnManageVO1 == null) {
			vcatnManageVO1 = new VcatnManageVO();
		}

		// 기본값 설정 (double 필드는 기본값이 0.0이지만 명시적으로 설정)
		if (vcatnManageVO1.getOccrncYrycCo() == 0.0) {
			vcatnManageVO1.setOccrncYrycCo(0.0);
		}
		if (vcatnManageVO1.getUseYrycCo() == 0.0) {
			vcatnManageVO1.setUseYrycCo(0.0);
		}
		if (vcatnManageVO1.getRemndrYrycCo() == 0.0) {
			vcatnManageVO1.setRemndrYrycCo(0.0);
		}

		vcatnManageVO1.setApplcntId(uniqId);
		if (user.getName() != null) {
			vcatnManageVO1.setApplcntNm(user.getName());
		} else {
			vcatnManageVO1.setApplcntNm("");
		}
		if (user.getOrgnztNm() != null) {
			vcatnManageVO1.setOrgnztNm(user.getOrgnztNm());
		} else {
			vcatnManageVO1.setOrgnztNm("");
		}

			// 2026.03.09 KISA 취약점 조치: try-catch 삭제
			if (vcatnManageVO1.getBgnde() != null && !vcatnManageVO1.getBgnde().trim().isEmpty()
					&& !vcatnManageVO1.getBgnde().contains("-") && vcatnManageVO1.getBgnde().trim().length() == 8) {
				vcatnManageVO1.setTempBgnde(EgovDateUtil.formatDate(vcatnManageVO1.getBgnde().trim(), "-"));
			}

			// 2026.03.09 KISA 취약점 조치: try-catch 삭제
			if (vcatnManageVO1.getEndde() != null && !vcatnManageVO1.getEndde().trim().isEmpty()
					&& !vcatnManageVO1.getEndde().contains("-") && vcatnManageVO1.getEndde().trim().length() == 8) {
				vcatnManageVO1.setTempEndde(EgovDateUtil.formatDate(vcatnManageVO1.getEndde().trim(), "-"));
			}

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM056");
		List<CmmnDetailCode> vcatnSeCodeList = null;
			// 2026.03.09 KISA 취약점 조치: try-catch 삭제
			vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);

		if (vcatnSeCodeList == null) {
			vcatnSeCodeList = new java.util.ArrayList<CmmnDetailCode>();
		}

		model.addAttribute("vcatnSeCode", vcatnSeCodeList);
		model.addAttribute("vcatnManageVO", vcatnManageVO1);

		return "egovframework/com/uss/ion/vct/EgovVcatnRegist";
	}

	/**
	 * 휴가관리정보를 신규로 등록한다.
	 *
	 * @param vcatnManage - 휴가관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/vct/insertVcatnManage.do")
	public String insertVcatnManage(@Valid @ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, BindingResult bindingResult,
			SessionStatus status, @RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		if (bindingResult.hasErrors()) {
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			if (user != null) {
				vcatnManageVO.setApplcntId(user.getUniqId() != null ? user.getUniqId() : "");
				vcatnManageVO.setApplcntNm(user.getName() != null ? user.getName() : "");
				vcatnManageVO.setOrgnztNm(user.getOrgnztNm() != null ? user.getOrgnztNm() : "");
				VcatnManageVO vcatnManageVO1 = egovVcatnManageService.selectIndvdlYrycManage(user.getUniqId());
				if (vcatnManageVO1 != null) {
					vcatnManageVO.setOccrncYrycCo(vcatnManageVO1.getOccrncYrycCo());
					vcatnManageVO.setUseYrycCo(vcatnManageVO1.getUseYrycCo());
					vcatnManageVO.setRemndrYrycCo(vcatnManageVO1.getRemndrYrycCo());
				}
			}
			if (vcatnManageVO.getBgnde() != null && !vcatnManageVO.getBgnde().isEmpty()) {
				if (vcatnManageVO.getBgnde().contains("-")) {
					vcatnManageVO.setTempBgnde(vcatnManageVO.getBgnde());
				} else {
					try {
						vcatnManageVO.setTempBgnde(EgovDateUtil.formatDate(vcatnManageVO.getBgnde(), "-"));
						//	 2026.02.28 KISA 취약점 조치
					} catch (IllegalArgumentException e) {
						LOGGER.warn("bgnde formatDate 변환 실패: {}", e.getMessage());
						vcatnManageVO.setTempBgnde(vcatnManageVO.getBgnde());
					}
				}
			}
			if (vcatnManageVO.getEndde() != null && !vcatnManageVO.getEndde().isEmpty()) {
				if (vcatnManageVO.getEndde().contains("-")) {
					vcatnManageVO.setTempEndde(vcatnManageVO.getEndde());
				} else {
					try {
						vcatnManageVO.setTempEndde(EgovDateUtil.formatDate(vcatnManageVO.getEndde(), "-"));
						//	 2026.02.28 KISA 취약점 조치
					} catch (IllegalArgumentException e) {
						LOGGER.warn("endde formatDate 변환 실패: {}", e.getMessage());
						vcatnManageVO.setTempEndde(vcatnManageVO.getEndde());
					}
				}
			}
			// 결재권자 표시값 유지 (제출값으로 VO 보강)
			String sanctnDtNmVal = commandMap.get("sanctnDtNm") == null ? "" : (String) commandMap.get("sanctnDtNm");
			String orgnztNmVal = commandMap.get("orgnztNm") == null ? "" : (String) commandMap.get("orgnztNm");
			vcatnManageVO.setSanctnerNm(sanctnDtNmVal);
			vcatnManageVO.setSanctnerOrgnztNm(orgnztNmVal);
			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM056");
			List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
			model.addAttribute("vcatnSeCode", vcatnSeCodeList);
			model.addAttribute("vcatnManageVO", vcatnManageVO);
			return "egovframework/com/uss/ion/vct/EgovVcatnRegist";
		}

		// 승인권자 소속명, 성명 유지
		model.addAttribute("infSanctnDtNm", commandMap.get("sanctnDtNm") == null ? "" : (String) commandMap.get("sanctnDtNm"));
		model.addAttribute("infOrgnztNm", commandMap.get("orgnztNm") == null ? "" : (String) commandMap.get("orgnztNm"));

		// bgndeView가 있으면 bgnde로 설정 (날짜 형식: yyyy-mm-dd -> yyyymmdd)
		String sBgndeView = commandMap.get("bgndeView") == null ? "" : (String) commandMap.get("bgndeView");
		if (!sBgndeView.equals("")) {
			// 날짜 형식 변환 (yyyy-mm-dd -> yyyymmdd)
			String bgndeValue = sBgndeView.replace("-", "");
			vcatnManageVO.setBgnde(bgndeValue);
		}

		// enddeView가 있으면 endde로 설정 (날짜 형식: yyyy-mm-dd -> yyyymmdd)
		String sEnddeView = commandMap.get("enddeView") == null ? "" : (String) commandMap.get("enddeView");
		if (!sEnddeView.equals("")) {
			// 날짜 형식 변환 (yyyy-mm-dd -> yyyymmdd)
			String enddeValue = sEnddeView.replace("-", "");
			vcatnManageVO.setEndde(enddeValue);
		}
		// enddeHidden이 있으면 우선 사용
		String sEnddeHidden = commandMap.get("enddeHidden") == null ? "" : (String) commandMap.get("enddeHidden");
		if (!sEnddeHidden.equals("")) {
			vcatnManageVO.setEndde(sEnddeHidden);
		}

		String sTemp = null;
		String sTempMessage = null;
		int iTemp = 0;

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (user != null) {
			if (vcatnManageVO.getSanctnerId() != null) {
				vcatnManageVO.setConfmAt("A");
			}
			vcatnManageVO.setApplcntId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			vcatnManageVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

			vcatnManageVO.setSearchKeyword(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
			// 시작일자 포함여부
			iTemp = egovVcatnManageService.selectVcatnManageDplctAt(vcatnManageVO);
			vcatnManageVO.setSearchKeyword(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));
			// 종료일자 포함여부
			iTemp += egovVcatnManageService.selectVcatnManageDplctAt(vcatnManageVO);

			if (iTemp == 0) {
				status.setComplete();
				sTemp = egovVcatnManageService.insertVcatnManage(vcatnManageVO);

				if (sTemp.equals("01")) {
					model.addAttribute("message", egovMessageSource.getMessage("comUssIonVct.common.inputSuccess"));
					return "forward:/uss/ion/vct/EgovVcatnManageList.do";
				} else {
					if (sTemp.equals("99")) {
						sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.vacationSelectError");
					} else if (sTemp.equals("09")) {
						sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.thatYearOnly");
					} else if (sTemp.equals("02")) {
						sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.vacationFail");
					} else if (sTemp.equals("03")) {
						sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.halfVacationFail");
					} else {
						sTempMessage = "undefined error";
					}
					model.addAttribute("errorMessage", sTempMessage);

					VcatnManageVO vcatnManageVO1 = egovVcatnManageService.selectIndvdlYrycManage(user.getUniqId());
					vcatnManageVO1.setApplcntId(user.getUniqId());
					vcatnManageVO1.setApplcntNm(user.getName());
					vcatnManageVO1.setOrgnztNm(user.getOrgnztNm());
					vcatnManageVO1.setTempBgnde(EgovDateUtil.formatDate(vcatnManageVO.getBgnde(), "-"));
					vcatnManageVO1.setTempEndde(EgovDateUtil.formatDate(vcatnManageVO.getEndde(), "-"));

					model.addAttribute("vcatnManageVO", vcatnManageVO1);
					ComDefaultCodeVO vo = new ComDefaultCodeVO();
					vo.setCodeId("COM056");
					List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
					model.addAttribute("vcatnSeCode", vcatnSeCodeList);

					return "egovframework/com/uss/ion/vct/EgovVcatnRegist";
				}
			} else {

				model.addAttribute("errorMessage",
						egovMessageSource.getMessage("comUssIonVct.common.validate.duplicate"));

				VcatnManageVO vcatnManageVO1 = egovVcatnManageService
						.selectIndvdlYrycManage(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
				vcatnManageVO1.setApplcntId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
				vcatnManageVO1.setApplcntNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));
				vcatnManageVO1.setOrgnztNm(user == null ? "" : EgovStringUtil.isNullToString(user.getOrgnztNm()));
				model.addAttribute("vcatnManageVO", vcatnManageVO1);

				ComDefaultCodeVO vo = new ComDefaultCodeVO();
				vo.setCodeId("COM056");
				List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
				model.addAttribute("vcatnSeCode", vcatnSeCodeList);

				return "egovframework/com/uss/ion/vct/EgovVcatnRegist";
			}
		} else {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
	}

	/**
	 * 기 등록된 휴가관리정보를 수정한다.
	 *
	 * @param vcatnManage - 휴가관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/vct/updtVcatnManage.do")
	public String updtVcatnManage(@Valid @ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, BindingResult bindingResult,
			SessionStatus status, ModelMap model) throws Exception {
		String sTemp = null;
		String sTempMessage = null;

		// KISA 보안취약점 조치 (2018-12-10, 신용호)
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		if (bindingResult.hasErrors()) {
			System.out.println("#########################");
			System.out.println("#########################");
			System.out.println("#########################");

			vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
			vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));
			VcatnManageVO detail = egovVcatnManageService.selectVcatnManage(vcatnManageVO);
			if (detail != null) {
				detail.setVcatnResn(vcatnManageVO.getVcatnResn());
				detail.setEndde(vcatnManageVO.getEndde());
				if (detail.getEndde() != null && !detail.getEndde().isEmpty() && !detail.getEndde().contains("-")) {
					try {
						detail.setTempEndde(EgovDateUtil.formatDate(detail.getEndde(), "-"));
						//	 2026.02.28 KISA 취약점 조치
					} catch (IllegalArgumentException e) {
						LOGGER.warn("endde formatDate 변환 실패: {}", e.getMessage());
						detail.setTempEndde(detail.getEndde());
					}
				} else if (detail.getEndde() != null && detail.getEndde().contains("-")) {
					detail.setTempEndde(detail.getEndde());
				}
				vcatnManageVO = detail;
			}
			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM056");
			List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
			model.addAttribute("vcatnSeCode", vcatnSeCodeList);
			model.addAttribute("vcatnManageVO", vcatnManageVO);
			return "egovframework/com/uss/ion/vct/EgovVcatnUpdt";
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (user != null) {
			// 221116 김혜준 2022 시큐어코딩 조치
			vcatnManageVO.setFrstRegisterId(EgovStringUtil.isNullToString(user.getUniqId()));
			sTemp = egovVcatnManageService.updtVcatnManage(vcatnManageVO);

			if (sTemp.equals("01")) {
				model.addAttribute("message", egovMessageSource.getMessage("comUssIonVct.common.inputSuccess"));
				return "forward:/uss/ion/vct/EgovVcatnManageList.do";
			} else {

				if (sTemp.equals("99")) {
					sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.vacationSelectError");
				} else if (sTemp.equals("09")) {
					sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.thatYearOnly");
				} else if (sTemp.equals("02")) {
					sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.vacationFail");
				} else if (sTemp.equals("03")) {
					sTempMessage = egovMessageSource.getMessage("comUssIonVct.common.validate.halfVacationFail");
				} else {
					sTempMessage = "undefined error";
				}

				model.addAttribute("errorMessage", sTempMessage);

				VcatnManageVO vcatnManageVO1 = egovVcatnManageService.selectIndvdlYrycManage(user.getUniqId());
				vcatnManageVO1.setApplcntId(user.getUniqId());
				vcatnManageVO1.setApplcntNm(user.getName());
				vcatnManageVO1.setOrgnztNm(user.getOrgnztNm());
				vcatnManageVO1.setTempBgnde(EgovDateUtil.formatDate(vcatnManageVO.getBgnde(), "-"));
				vcatnManageVO1.setTempEndde(EgovDateUtil.formatDate(vcatnManageVO.getEndde(), "-"));

				model.addAttribute("vcatnManageVO", vcatnManageVO1);
				ComDefaultCodeVO vo = new ComDefaultCodeVO();
				vo.setCodeId("COM056");
				List<CmmnDetailCode> vcatnSeCodeList = cmmUseService.selectCmmCodeDetail(vo);
				model.addAttribute("vcatnSeCode", vcatnSeCodeList);

				return "egovframework/com/uss/ion/vct/EgovVcatnUpdt";
			}
		} else {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
	}

	/**
	 * 기 등록된 휴가관리정보를 삭제한다.
	 *
	 * @param vcatnManage - 휴가관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/vct/deleteVcatnManage.do")
	public String deleteVcatnManage(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, SessionStatus status,
			ModelMap model) throws Exception {
		vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
		vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));
		egovVcatnManageService.deleteVcatnManage(vcatnManageVO);
		status.setComplete();
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/vct/EgovVcatnManageList.do";
	}

	/*** 승인관련 ***/
	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록을 조회한다.
	 *
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "휴가승인관리", order = 901, gid = 50)
	@RequestMapping(value = "/uss/ion/vct/EgovVcatnConfmList.do")
	public String selectVcatnManageConfmList(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO,
			ModelMap model) throws Exception {

		java.util.Calendar cal = java.util.Calendar.getInstance();
		String[] yearList = new String[5];
		for (int x = 0; x < 5; x++) {
			yearList[x] = Integer.toString(cal.get(java.util.Calendar.YEAR) - x);
		}

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vcatnManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(vcatnManageVO.getPageUnit());
		paginationInfo.setPageSize(vcatnManageVO.getPageSize());

		vcatnManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vcatnManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		vcatnManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (user == null) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		vcatnManageVO.setSanctnerId(user.getUniqId()); // 사용자가 승인권자인지 조건값 setting

		vcatnManageVO.setSearchKeyword(vcatnManageVO.getSearchYear() + vcatnManageVO.getSearchMonth());
		vcatnManageVO.setVcatnManageList(egovVcatnManageService.selectVcatnManageConfmList(vcatnManageVO));

		model.addAttribute("vcatnManageList", vcatnManageVO.getVcatnManageList());

		int totCnt = egovVcatnManageService.selectVcatnManageConfmListTotCnt(vcatnManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("yearList", yearList);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/vct/EgovVcatnConfmList";
	}

	/**
	 * 휴가승인관리 상세정보를 조회한다.
	 *
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/vct/EgovVcatnConfm.do")
	public String selectVcatnConfm(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, ModelMap model) throws Exception {
		vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
		vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));

		// 등록 상세정보
		VcatnManageVO vcatnManageVOTemp = egovVcatnManageService.selectVcatnManage(vcatnManageVO);
		if (vcatnManageVOTemp == null) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.select"));
			return "forward:/uss/ion/vct/EgovVcatnConfmList.do";
		}

		model.addAttribute("vcatnManageVO", vcatnManageVOTemp);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/vct/EgovVcatnConfm";
	}

	/**
	 * 신청된 휴가를 승인처리한다.
	 *
	 * @param vcatnManage - 휴가관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/vct/updtVcatnConfm.do")
	public String updtVcatnManageConfm(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, BindingResult bindingResult, SessionStatus status,
			ModelMap model) throws Exception {

		vcatnManageVO.setBgnde(EgovStringUtil.removeMinusChar(vcatnManageVO.getBgnde()));
		vcatnManageVO.setEndde(EgovStringUtil.removeMinusChar(vcatnManageVO.getEndde()));

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		// KISA 보안취약점 조치 (2018-12-10, 신용호)
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("vcatnManageVO", vcatnManageVO);
			return "egovframework/com/uss/ion/vct/EgovVcatnConfm";
		} else {
			vcatnManageVO.setSanctnerId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			vcatnManageVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

			egovVcatnManageService.updtVcatnManageConfm(vcatnManageVO);
			return "forward:/uss/ion/vct/EgovVcatnConfmList.do";
		}
	}

	/**
	 * 휴가정보 반려처리 화면을 호출한다.
	 *
	 * @param vcatnManage - 휴가관리 model
	 * @return String
	 *
	 * @param vcatnManage
	 */
	@RequestMapping("/uss/ion/vct/EgovVcatnReturn.do")
	public String selectSanctnerListPopup(@ModelAttribute("vcatnManageVO") VcatnManageVO vcatnManageVO, ModelMap model)
			throws Exception {
		return "egovframework/com/uss/ion/vct/EgovVcatnReturn";
	}
}
