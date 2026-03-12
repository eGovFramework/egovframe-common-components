package egovframework.com.uss.ion.bnt.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.bnt.service.BndtCeckManageVO;
import egovframework.com.uss.ion.bnt.service.BndtDiaryVO;
import egovframework.com.uss.ion.bnt.service.BndtManageVO;
import egovframework.com.uss.ion.bnt.service.EgovBndtManageService;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovFileUploadUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * <pre>
 * 개요
 * - 당직관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 당직관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 당직관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 *
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56 *
 *
 *          <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일              수정자             수정내용
 *  ----------  --------    ---------------------------

 *
 *          </pre>
 */
/**
 * <pre>
 * 개요
 * - 당직관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 당직관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 당직관리의 조회기능은 목록조회, 상세조회로 구분된다.
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
 *   2010.06.15 이용            최초 생성
 *   2011.08.26 정진오           IncludedInfo annotation 추가
 *   2018.08.29 신용호           xlsx 업로드 할수 있도록 수정
 *   2020.11.02 신용호           KISA 보안약점 조치 - 자원해제
 *   2025.08.05  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovBndtManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBndtManageController.class);

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovBndtManageService")
	private EgovBndtManageService egovBndtManageService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/**
	 * 공통코드를 조회한다.
	 * @return List<CmmnDetailCode>
	 */
	private List<CmmnDetailCode> bndtCeckSeCode() {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM071");
		return cmmUseService.selectCmmCodeDetail(vo);
	}

	/**
	 * 당직관리 목록화면 이동
	 *
	 * @return String
	 * @exception Exception
	 */
	@SuppressWarnings("unused")
	@IncludedInfo(name = "당직관리", order = 910, gid = 50)
	@RequestMapping("/uss/ion/bnt/EgovBndtManageList.do")
	public String selectBndtManageListView(@ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO,
			@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		java.util.Calendar cal = java.util.Calendar.getInstance();

		String sYear = (String) commandMap.get("year");
		String sMonth = (String) commandMap.get("month");

		int iYear = cal.get(java.util.Calendar.YEAR);
		int iMonth = cal.get(java.util.Calendar.MONTH);
		int iDate = cal.get(java.util.Calendar.DATE);

		// 검색 설정
		String sSearchDate = "";
		if (sYear == null || sMonth == null) {
			sSearchDate += Integer.toString(iYear);
			sSearchDate += Integer.toString(iMonth + 1).length() == 1 ? "0" + Integer.toString(iMonth + 1)
					: Integer.toString(iMonth + 1);
		} else {
			iYear = Integer.parseInt(sYear);
			iMonth = Integer.parseInt(sMonth);
			sSearchDate += sYear;
			sSearchDate += Integer.toString(iMonth + 1).length() == 1 ? "0" + Integer.toString(iMonth + 1)
					: Integer.toString(iMonth + 1);
		}
		bndtManageVO.setBndtDe(sSearchDate);

		bndtManageVO.setBndtManageList(egovBndtManageService.selectBndtManageList(bndtManageVO));
		model.addAttribute("bndtManageList", bndtManageVO.getBndtManageList());

		return "egovframework/com/uss/ion/bnt/EgovBndtManageList";
	}

	/**
	 * 당직관리정보를 관리하기 위해 등록된 당직관리 목록을 조회한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/selectBndtManageList.do")
	public String selectBndtManageList(@ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO, ModelMap model)
			throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bndtManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(bndtManageVO.getPageUnit());
		paginationInfo.setPageSize(bndtManageVO.getPageSize());

		bndtManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bndtManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bndtManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		bndtManageVO.setBndtManageList(egovBndtManageService.selectBndtManageList(bndtManageVO));

		model.addAttribute("bndtManageList", bndtManageVO.getBndtManageList());

		int totCnt = egovBndtManageService.selectBndtManageListTotCnt(bndtManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/bnt/EgovBndtManageList";
	}

	/**
	 * 등록된 당직관리의 상세정보를 조회한다.
	 *
	 * @param bndtManageVO - 당직관리 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/EgovBndtManageDetail.do")
	public String selectBndtManage(@ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO,
			@RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd"); // 상세정보 구분

		BndtManageVO resultVO = egovBndtManageService.selectBndtManage(bndtManageVO);

		model.addAttribute("bndtManageVO", resultVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if (sCmd.equals("updt")) {
			return "egovframework/com/uss/ion/bnt/EgovBndtManageUpdt";
		} else {
			return "egovframework/com/uss/ion/bnt/EgovBndtManageDetail";
		}
	}

	/**
	 * 당직관리 등록 화면으로 이동한다.
	 *
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/EgovBndtManageRegist.do")
	public String insertViewBndtManage(@ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO, ModelMap model) throws Exception {

		bndtManageVO.setBndtDe(EgovDateUtil.formatDate(bndtManageVO.getBndtDe(), "-"));
		model.addAttribute("bndtManageVO", bndtManageVO);

		return "egovframework/com/uss/ion/bnt/EgovBndtManageRegist";
	}

	/**
	 * 당직관리정보를 신규로 등록한다.
	 *
	 * @param bndtManage - 당직관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/insertBndtManage.do")
	public String insertBndtManage(@Valid @ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO, BindingResult bindingResult,
			ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("bndtManageVO", bndtManageVO);
			return "egovframework/com/uss/ion/bnt/EgovBndtManageRegist";
		} else {
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			bndtManageVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
			egovBndtManageService.insertBndtManage(bndtManageVO);

			return "forward:/uss/ion/bnt/EgovBndtManageList.do";
		}
	}

	/**
	 * 기 등록된 당직관리정보를 수정한다.
	 *
	 * @param bndtManage - 당직관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/updtBndtManage.do")
	public String updtBndtManage(@Valid @ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO, BindingResult bindingResult,
			ModelMap model) throws Exception {


		if (bindingResult.hasErrors()) {
			model.addAttribute("bndtManageVO", bndtManageVO);
			return "egovframework/com/uss/ion/bnt/EgovBndtManageUpdt";
		} else {
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			bndtManageVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
			egovBndtManageService.updtBndtManage(bndtManageVO);
			return "forward:/uss/ion/bnt/EgovBndtManageList.do";

		}
	}

	/**
	 * 기 등록된 당직관리정보를 삭제한다.
	 *
	 * @param bndtManage - 당직관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/deleteBndtManage.do")
	public String deleteBndtManage(@ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO,
			ModelMap model) throws Exception {

		int iDiaryTotCnt = egovBndtManageService.selectBndtDiaryTotCnt(bndtManageVO);
		if (iDiaryTotCnt == 0) {
			egovBndtManageService.deleteBndtManage(bndtManageVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
			return "forward:/uss/ion/bnt/EgovBndtManageList.do";
		} else {

			bndtManageVO = egovBndtManageService.selectBndtManage(bndtManageVO);

			model.addAttribute("bndtManageVO", bndtManageVO);
			model.addAttribute("errorMessage", "당직일지를 삭제하신 후 당직정보를 삭제 하세요.");

			return "egovframework/com/uss/ion/bnt/EgovBndtManageUpdt";
		}
	}

	/****** 당직체크 관리 ******/
	/**
	 * 당직체크정보를 관리하기 위해 등록된 당직체크 목록을 조회한다.
	 *
	 * @param bndtCeckManageVO - 당직체크 VO
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "당직체크관리", order = 911, gid = 50)
	@RequestMapping(value = "/uss/ion/bnt/EgovBndtCeckManageList.do")
	public String selectBndtCeckManageList(@ModelAttribute("bndtCeckManageVO") BndtCeckManageVO bndtCeckManageVO,
			ModelMap model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bndtCeckManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(bndtCeckManageVO.getPageUnit());
		paginationInfo.setPageSize(bndtCeckManageVO.getPageSize());

		bndtCeckManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bndtCeckManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bndtCeckManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("bndtCeckSeList", bndtCeckSeCode());

		bndtCeckManageVO.setBndtCeckManageList(egovBndtManageService.selectBndtCeckManageList(bndtCeckManageVO));
		model.addAttribute("bndtCeckManageList", bndtCeckManageVO.getBndtCeckManageList());

		int totCnt = egovBndtManageService.selectBndtCeckManageListTotCnt(bndtCeckManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("bndtCeckManageVO", bndtCeckManageVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/bnt/EgovBndtCeckManageList";
	}

	/**
	 * 등록된 당직체크의 상세정보를 조회한다.
	 *
	 * @param bndtCeckManageVO - 당직체크 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/EgovBndtCeckManage.do")
	public String selectBndtCeckManage(@ModelAttribute("bndtCeckManageVO") BndtCeckManageVO bndtCeckManageVO,
			@RequestParam Map<?, ?> commandMap,
			ModelMap model) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd"); // 상세정보 구분

		BndtCeckManageVO resultBndtCeckManageVO = egovBndtManageService.selectBndtCeckManage(bndtCeckManageVO);

		model.addAttribute("bndtCeckManageVO", resultBndtCeckManageVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if (sCmd.equals("updt")) {
			return "egovframework/com/uss/ion/bnt/EgovBndtCeckManageUpdt";
		} else {
			return "egovframework/com/uss/ion/bnt/EgovBndtCeckManageDetail";
		}
	}

	/**
	 * 당직체크 등록 화면으로 이동한다.
	 *
	 * @return String - 리턴 Url
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uss/ion/bnt/EgovBndtCeckManageRegist.do")
	public String insertViewBndtCeckManage(@ModelAttribute("bndtCeckManageVO") BndtCeckManageVO bndtCeckManageVO,
			ModelMap model) throws Exception {
		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		model.addAttribute("bndtCeckSeList", bndtCeckSeCode());

		bndtCeckManageVO.setBndtCeckCd("");
		bndtCeckManageVO.setBndtCeckCdNm("");
		bndtCeckManageVO.setBndtCeckSe("");
		model.addAttribute("bndtCeckManageVO", bndtCeckManageVO);
		return "egovframework/com/uss/ion/bnt/EgovBndtCeckManageRegist";
	}

	/**
	 * 당직체크정보를 신규로 등록한다.
	 *
	 * @param bndtCeckManage - 당직체크 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/insertBndtCeckManage.do")
	public String insertBndtCeckManage(@Valid @ModelAttribute("bndtCeckManageVO") BndtCeckManageVO bndtCeckManageVO, BindingResult bindingResult,
			ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("bndtCeckSeList", bndtCeckSeCode());
			return "egovframework/com/uss/ion/bnt/EgovBndtCeckManageRegist";
		} else {
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			bndtCeckManageVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			if (egovBndtManageService.selectBndtCeckManageDplctAt(bndtCeckManageVO) == 0) {
				egovBndtManageService.insertBndtCeckManage(bndtCeckManageVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
				return "forward:/uss/ion/bnt/EgovBndtCeckManageList.do";
			} else {
				model.addAttribute("bndtCeckSeList", bndtCeckSeCode());
				model.addAttribute("dplctMessage", "이미 등록된 데이타입니다. 해당 데이타를 확인해 주세요");
				return "egovframework/com/uss/ion/bnt/EgovBndtCeckManageRegist";
			}
		}
	}

	/**
	 * 기 등록된 당직체크정보를 수정한다.
	 *
	 * @param bndtCeckManage - 당직체크 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/updtBndtCeckManage.do")
	public String updtBndtCeckManage(@Valid @ModelAttribute("bndtCeckManageVO") BndtCeckManageVO bndtCeckManageVO, BindingResult bindingResult,
			ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			BndtCeckManageVO resultBndtCeckManageVO = egovBndtManageService.selectBndtCeckManage(bndtCeckManageVO);
			if (resultBndtCeckManageVO != null) {
				bndtCeckManageVO.setBndtCeckTemp1(resultBndtCeckManageVO.getBndtCeckTemp1());
			}
			model.addAttribute("bndtCeckManageVO", bndtCeckManageVO);
			return "egovframework/com/uss/ion/bnt/EgovBndtCeckManageUpdt";
		} else {

			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			bndtCeckManageVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
			egovBndtManageService.updtBndtCeckManage(bndtCeckManageVO);
			return "forward:/uss/ion/bnt/EgovBndtCeckManageList.do";

		}
	}

	/**
	 * 기 등록된 당직체크정보를 삭제한다.
	 *
	 * @param bndtCeckManage - 당직체크 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/deleteBndtCeckManage.do")
	public String deleteBndtCeckManage(@ModelAttribute("bndtCeckManageVO") BndtCeckManageVO bndtCeckManageVO,
			ModelMap model) throws Exception {

		egovBndtManageService.deleteBndtCeckManage(bndtCeckManageVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/bnt/EgovBndtCeckManageList.do";
	}

	/****** 당직일지 ******/

	/**
	 * 등록된 당직일지의 정보를 조회한다.
	 *
	 * @param bndtDiaryVO - 당직일지 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/selectBndtDiary.do")
	public String selectBndtDiary(@ModelAttribute("bndtDiaryVO") BndtDiaryVO bndtDiaryVO,
			@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd"); // 상세정보 구분

		model.addAttribute("bndtDiaryList", egovBndtManageService.selectBndtDiary(bndtDiaryVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if (sCmd.equals("insert")) {
			bndtDiaryVO.setBndtDe(bndtDiaryVO.getBndtDe());
			bndtDiaryVO.setBndtId(bndtDiaryVO.getBndtId());
			model.addAttribute("bndtDiaryVO", bndtDiaryVO);
			return "egovframework/com/uss/ion/bnt/EgovBndtDiaryRegist";
		} else if (sCmd.equals("updt")) {
			return "egovframework/com/uss/ion/bnt/EgovBndtDiaryUpdt";
		} else {
			return "egovframework/com/uss/ion/bnt/EgovBndtDiaryDetail";
		}
	}

	/**
	 * 당직일지정보를 신규로 등록한다.
	 *
	 * @param bndtDiary - 당직일지 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/insertBndtDiary.do")
	public String insertBndtDiary(
			@RequestParam(value = "bndtCeckSe", required = false) String[] bndtCeckSe,
			@RequestParam(value = "bndtCeckCd", required = false) String[] bndtCeckCd,
			@RequestParam(value = "chckSttus", required = false) String[] chckSttus,
			@ModelAttribute("bndtDiaryVO") BndtDiaryVO bndtDiaryVO,
			ModelMap model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		bndtDiaryVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		// 배열 크기에 맞춰 diaryForInsert 문자열 생성
		StringBuilder diaryForInsert = new StringBuilder();
		if (bndtCeckSe != null) {
			for (int i = 0; i < bndtCeckSe.length; i++) {
				if (i > 0) {
					diaryForInsert.append("@");
				}

				String se = (bndtCeckSe[i] != null) ? bndtCeckSe[i] : "";
				String cd = (bndtCeckCd != null && i < bndtCeckCd.length && bndtCeckCd[i] != null) ? bndtCeckCd[i] : "";
				String sttus = "";

				// chckSttus 배열 처리
				if ("99".equals(se)) {
					// 텍스트 필드인 경우: 배열에서 해당 인덱스의 값 사용
					if (chckSttus != null && i < chckSttus.length && chckSttus[i] != null) {
						sttus = chckSttus[i].trim();
					} else {
						sttus = "";
					}
				} else {
					if (chckSttus != null && i < chckSttus.length && chckSttus[i] != null
							&& (chckSttus[i].equals("1") || chckSttus[i].equals("2"))) {
						sttus = chckSttus[i];
					} else {
						// 라디오 버튼이 선택되지 않은 경우 기본값(양호) 설정
						sttus = "1";
					}
				}

				diaryForInsert.append(se).append("$").append(cd).append("$").append(sttus);
			}
		}

		egovBndtManageService.insertBndtDiary(bndtDiaryVO, diaryForInsert.toString());
		model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

		return "forward:/uss/ion/bnt/EgovBndtManageList.do";
	}

	/**
	 * 기 등록된 당직일지정보를 수정한다.
	 *
	 * @param bndtDiary - 당직일지 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/updtBndtDiary.do")
	public String updtBndtDiary(
			@RequestParam(value = "bndtCeckSe", required = false) String[] bndtCeckSe,
			@RequestParam(value = "bndtCeckCd", required = false) String[] bndtCeckCd,
			@RequestParam(value = "chckSttus", required = false) String[] chckSttus,
			@ModelAttribute("bndtDiaryVO") BndtDiaryVO bndtDiaryVO,
			ModelMap model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		bndtDiaryVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		// 배열 크기에 맞춰 diaryForUpdt 문자열 생성
		StringBuilder diaryForUpdt = new StringBuilder();
		if (bndtCeckSe != null) {
			for (int i = 0; i < bndtCeckSe.length; i++) {
				if (i > 0) {
					diaryForUpdt.append("@");
				}

				String se = (bndtCeckSe[i] != null) ? bndtCeckSe[i] : "";
				String cd = (bndtCeckCd != null && i < bndtCeckCd.length && bndtCeckCd[i] != null) ? bndtCeckCd[i] : "";
				String sttus = "";

				// chckSttus 배열 처리
				if ("99".equals(se)) {
					// 텍스트 필드인 경우: 배열에서 해당 인덱스의 값 사용
					if (chckSttus != null && i < chckSttus.length && chckSttus[i] != null) {
						sttus = chckSttus[i].trim();
					} else {
						sttus = "";
					}
				} else {
					if (chckSttus != null && i < chckSttus.length && chckSttus[i] != null
							&& (chckSttus[i].equals("1") || chckSttus[i].equals("2"))) {
						sttus = chckSttus[i];
					} else {
						// 라디오 버튼이 선택되지 않은 경우 기본값(양호) 설정
						sttus = "1";
					}
				}

				diaryForUpdt.append(se).append("$").append(cd).append("$").append(sttus);
			}
		}

		egovBndtManageService.updtBndtDiary(bndtDiaryVO, diaryForUpdt.toString());
		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

		return "forward:/uss/ion/bnt/EgovBndtManageList.do";
	}

	/**
	 * 기 등록된 당직일지정보를 삭제한다.
	 *
	 * @param bndtDiary - 당직일지 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/deleteBndtDiary.do")
	public String deleteBndtDiary(@ModelAttribute("bndtDiaryVO") BndtDiaryVO bndtDiaryVO,
			ModelMap model) throws Exception {

		egovBndtManageService.deleteBndtDiary(bndtDiaryVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/bnt/EgovBndtManageList.do";
	}

	/**
	 * 당직일괄등록화면 호출 및 당직일괄등록처리 프로세스
	 *
	 * @param bndtManageVO BndtManageVO
	 * @param request      HttpServletRequest
	 * @return 출력페이지정보 "ion/bnt/EgovBndtManageListPop"
	 * @exception Exception
	 */
	@RequestMapping(value = "/uss/ion/bnt/EgovBndtManageListPop.do")
	public String selectBndtManageBnde(final HttpServletRequest request,
			@ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO, @RequestParam Map<?, ?> commandMap,
			ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		model.addAttribute("bndtManageList", Collections.emptyList());
		return "egovframework/com/uss/ion/bnt/EgovBndtManageBndeListPop";
	}

	@RequestMapping(value = "/uss/ion/bnt/EgovBndtManageListPopAction.do")
	public String selectBndtManageBndeAction(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO, @RequestParam Map<?, ?> commandMap,
			ModelMap model) throws Exception {
		String resultMsg = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd"); // 상세정보 구분

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		if (sCmd.equals("bnde")) {
			model.addAttribute("bndtManageList", Collections.emptyList());
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			MultipartFile file;
			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();
				file = entry.getValue();
				if (!"".equals(file.getOriginalFilename())) {
					String ext = EgovFileUploadUtil.getFileExtension(file.getOriginalFilename());
					if (ext != null && "xlsx".equalsIgnoreCase(ext)) {
						// 2026.02.28 KISA 보안약점 조치
						try (InputStream is = new ByteArrayInputStream(file.getBytes())) {
							List<BndtManageVO> bndeList = egovBndtManageService.selectBndtManageBndeX(is);
							// 문제 상황 : selectBndtManageBndeX 인터페이스에서 throws Exception을 선언하여 메서드 호출 코드가 Exception 처리를 위임받음
							model.addAttribute("bndtManageList", bndeList != null ? bndeList : Collections.emptyList());
						} catch (IOException e) {
							// catch (IOException e) + catch (Exception e)를 분리하고, IOException을 먼저 명시적으로 처리
							LOGGER.warn("xlsx 파일 읽기 중 I/O 오류: {}", e.getMessage(),e);
							model.addAttribute("bndtManageList", Collections.emptyList());
							resultMsg = egovMessageSource.getMessage("fail.common.msg") + " (xlsx: " + e.getMessage() + ")";
						} catch (Exception e) {
							// selectBndtManageBndeX가 선언한 throws Exception를 처리하기 위해 catch (Exception e)을 불가피하게 유지
							LOGGER.warn("xlsx 당직일괄등록 처리 중 오류: {}", e.getMessage(),e);
							model.addAttribute("bndtManageList", Collections.emptyList());
							resultMsg = egovMessageSource.getMessage("fail.common.msg") + " (xlsx: " + e.getMessage() + ")";
						}
					} else if (ext != null && "xls".equalsIgnoreCase(ext)) {
						try (InputStream is = file.getInputStream()) {
							model.addAttribute("bndtManageList", egovBndtManageService.selectBndtManageBnde(is));
						}
					} else {
						throw new RuntimeException(egovMessageSource.getMessage("errors.file.extension"));
					}
				} else {
					resultMsg = egovMessageSource.getMessage("fail.common.msg");
				}
			}
			model.addAttribute("resultMsg", resultMsg);
		}
		return "egovframework/com/uss/ion/bnt/EgovBndtManageBndeListPop";
	}

	/**
	 * 당직정보를 일괄등록처리한다.
	 *
	 * @param checkedBndtManageForInsert - 당직자정보 (형식: "날짜,ID$날짜,ID$...")
	 * @param searchKeyword - 검색 키워드 (연월 YYYYMM 형식)
	 * @param bndtManageVO - 당직관리 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/bnt/insertBndtManageBnde.do")
	public String insertBndtManageBnde(
			@RequestParam(value = "checkedBndtManageForInsert", required = false) String checkedBndtManageForInsert,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword,
			@ModelAttribute("bndtManageVO") BndtManageVO bndtManageVO, ModelMap model)
			throws Exception {

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 입력값 검증
		if (checkedBndtManageForInsert == null || checkedBndtManageForInsert.trim().isEmpty()) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.msg"));
			return "egovframework/com/uss/ion/bnt/EgovBndtManageBndeListPop";
		}

		// searchKeyword 검증 및 설정 (selectBndtManageMonthCnt에서 사용)
		if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
			String[] firstRecord = checkedBndtManageForInsert.split("[$]");
			if (firstRecord.length > 0 && firstRecord[0].contains(",")) {
				String firstDate = firstRecord[0].split(",")[0].trim();
				if (firstDate.length() >= 6) {
					searchKeyword = firstDate.substring(0, 6); // YYYYMM
				}
			}
		}

		if (searchKeyword == null || searchKeyword.trim().isEmpty() || searchKeyword.length() < 6) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.msg"));
			return "egovframework/com/uss/ion/bnt/EgovBndtManageBndeListPop";
		}

		// bndtManageVO에 searchKeyword 설정 (selectBndtManageMonthCnt에서 사용)
		if (bndtManageVO == null) {
			bndtManageVO = new BndtManageVO();
		}
		bndtManageVO.setSearchKeyword(searchKeyword.trim());

		// 월별 데이터 존재 여부 확인
		int iTemp = egovBndtManageService.selectBndtManageMonthCnt(bndtManageVO);
		if (iTemp == 0) {
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			bndtManageVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			// 일괄 등록 처리
			egovBndtManageService.insertBndtManageBnde(bndtManageVO, checkedBndtManageForInsert);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "egovframework/com/uss/ion/bnt/EgovBndtManageList";
		} else {
			// 안전한 substring 처리
			String year = "";
			String month = "";
			String searchKeywordTrim = searchKeyword.trim();
			try {
				if (searchKeywordTrim.length() >= 4) {
					year = searchKeywordTrim.substring(0, 4);
				}
				if (searchKeywordTrim.length() >= 6) {
					month = searchKeywordTrim.substring(4, 6);
				}
			} catch (StringIndexOutOfBoundsException e) {
				year = searchKeywordTrim.length() >= 4 ? searchKeywordTrim.substring(0, 4) : "";
				month = searchKeywordTrim.length() >= 6 ? searchKeywordTrim.substring(4, 6) : "";
			}

			String sTempMessage = year + "년" + month + "월 데이터가 존재합니다.";
			model.addAttribute("message", sTempMessage);
			return "egovframework/com/uss/ion/bnt/EgovBndtManageBndeListPop";
		}
	}

}
