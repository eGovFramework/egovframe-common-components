package egovframework.com.uss.ion.pwm.web;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.pwm.service.EgovPopupManageService;
import egovframework.com.uss.ion.pwm.service.PopupManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 팝업창에 대한 Controller를 정의한다.
 *
 * 상세내용
 * - 팝업창에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 팝업창의 조회기능은 목록조회, 상세조회로, 사용자 화면 보기로 구분된다.
 * @author 이창원
 * @version 1.0
 * @created 05-8-2009 오후 2:19:57
 * <pre>
  * << 개정이력(Modification Information) >>
  *
  *  수정일              수정자           수정내용
  *  ---------   --------   ---------------------------
  *  2009.08.05   이창원           최초 생성
  *  2011.08.26   정진오           IncludedInfo annotation 추가
  *  2019.05.17   신용호           취약점 조치 및 보완
  *
  * </pre>
 */

@Controller
public class EgovPopupManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovPopupManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovPopupManageService */
	@Resource(name = "egovPopupManageService")
	private EgovPopupManageService egovPopupManageService;

	/**
	 * 팝업창관리 목록을 조회한다.
	 * @param popupManageVO
	 * @param model
	 * @return "egovframework/com/uss/ion/pwm/listPopupManage"
	 * @throws Exception
	 */
	@IncludedInfo(name = "팝업창관리", order = 720, gid = 50)
	@RequestMapping(value = "/uss/ion/pwm/listPopup.do")
	public String egovPopupManageList(@RequestParam Map<?, ?> commandMap, PopupManageVO popupManageVO, ModelMap model)
		throws Exception {

		/** EgovPropertyService.sample */
		popupManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
		popupManageVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(popupManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(popupManageVO.getPageUnit());
		paginationInfo.setPageSize(popupManageVO.getPageSize());

		popupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		popupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		popupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> reusltList = egovPopupManageService.selectPopupList(popupManageVO);
		model.addAttribute("resultList", reusltList);

		model.addAttribute("searchKeyword",
			commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
		model.addAttribute("searchCondition",
			commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

		int totCnt = egovPopupManageService.selectPopupListCount(popupManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/pwm/EgovPopupList";
	}

	/**
	 * 통합링크관리 목록을 상세조회 조회한다.
	 * @param popupManageVO
	 * @param commandMap
	 * @param model
	 * @return
	 *         "/uss/ion/pwm/detailPopupManage"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/pwm/detailPopup.do")
	public String egovPopupManageDetail(PopupManageVO popupManageVO, @RequestParam Map<?, ?> commandMap, ModelMap model)
		throws Exception {

		String sLocationUrl = "egovframework/com/uss/ion/pwm/EgovPopupDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if (sCmd.equals("del")) {
			egovPopupManageService.deletePopup(popupManageVO);
			sLocationUrl = "forward:/uss/ion/pwm/listPopup.do";
		} else {
			//상세정보 불러오기
			PopupManageVO popupManageVOs = egovPopupManageService.selectPopup(popupManageVO);
			model.addAttribute("popupManageVO", popupManageVOs);
		}

		return sLocationUrl;
	}

	/**
	 * 통합링크관리를 수정한다.
	 * @param searchVO
	 * @param popupManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 *         "/uss/ion/pwm/updtPopupManage"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/pwm/updtPopup.do")
	public String egovPopupManageUpdt(@RequestParam Map<?, ?> commandMap, PopupManageVO popupManageVO,
		BindingResult bindingResult, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/uss/ion/pwm/EgovPopupUpdt";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		//팝업창시작일자(시)
		model.addAttribute("ntceBgndeHH", getTimeHH());
		//팝업창시작일자(분)
		model.addAttribute("ntceBgndeMM", getTimeMM());
		//팝업창종료일자(시)
		model.addAttribute("ntceEnddeHH", getTimeHH());
		//팝업창정료일자(분)
		model.addAttribute("ntceEnddeMM", getTimeMM());

		if (sCmd.equals("save")) {
			sLocationUrl = "forward:/uss/ion/pwm/listPopup.do";
			//서버  validate 체크
			beanValidator.validate(popupManageVO, bindingResult);
			if (bindingResult.hasErrors()) {
				return sLocationUrl;
			}
			//아이디 설정
			popupManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			popupManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			//저장
			egovPopupManageService.updatePopup(popupManageVO);
		} else {

			PopupManageVO popupManageVOs = egovPopupManageService.selectPopup(popupManageVO);

			String sNtceBgnde = popupManageVOs.getNtceBgnde();
			String sNtceEndde = popupManageVOs.getNtceEndde();

			popupManageVOs.setNtceBgndeHH(sNtceBgnde.substring(8, 10));
			popupManageVOs.setNtceBgndeMM(sNtceBgnde.substring(10, 12));

			popupManageVOs.setNtceEnddeHH(sNtceEndde.substring(8, 10));
			popupManageVOs.setNtceEnddeMM(sNtceEndde.substring(10, 12));

			model.addAttribute("popupManageVO", popupManageVOs);
		}

		return sLocationUrl;
	}

	/**
	 * 통합링크관리를 등록한다.
	 * @param searchVO
	 * @param popupManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 *         "/uss/ion/pwm/registPopupManage"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/pwm/registPopup.do")
	public String egovPopupManageRegist(@RequestParam Map<?, ?> commandMap,
		@ModelAttribute("popupManageVO") PopupManageVO popupManageVO, BindingResult bindingResult,
		ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/uss/ion/pwm/EgovPopupRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

		if (sCmd.equals("save")) {
			//서버  validate 체크
			beanValidator.validate(popupManageVO, bindingResult);
			if (bindingResult.hasErrors()) {
				return sLocationUrl;
			}
			//아이디 설정
			popupManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			popupManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			//저장
			egovPopupManageService.insertPopup(popupManageVO);

			sLocationUrl = "forward:/uss/ion/pwm/listPopup.do";
		}

		//팝업창시작일자(시)
		model.addAttribute("ntceBgndeHH", getTimeHH());
		//팝업창시작일자(분)
		model.addAttribute("ntceBgndeMM", getTimeMM());
		//팝업창종료일자(시)
		model.addAttribute("ntceEnddeHH", getTimeHH());
		//팝업창정료일자(분)
		model.addAttribute("ntceEnddeMM", getTimeMM());

		return sLocationUrl;
	}

	/**
	 * 팝업창정보를 조회한다.
	 * @param commandMap
	 * @param popupManageVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/pwm/ajaxPopupManageInfo.do")
	public void egovPopupManageInfoAjax(@RequestParam Map<?, ?> commandMap, HttpServletResponse response,
		PopupManageVO popupManageVO) throws Exception {

		response.setHeader("Content-Type", "text/html;charset=utf-8");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));

		LOGGER.debug("commandMap : {}", commandMap);
		LOGGER.debug("popupManageVO : {}", popupManageVO);

		PopupManageVO popupManageVOs = egovPopupManageService.selectPopup(popupManageVO);

		String sPrint = "";
		sPrint = popupManageVOs.getFileUrl();
		sPrint = sPrint + "||" + popupManageVOs.getPopupWSize();
		sPrint = sPrint + "||" + popupManageVOs.getPopupHSize();
		sPrint = sPrint + "||" + popupManageVOs.getPopupHlc();
		sPrint = sPrint + "||" + popupManageVOs.getPopupWlc();
		sPrint = sPrint + "||" + popupManageVOs.getStopVewAt();
		out.print(EgovWebUtil.clearXSSMinimum(sPrint));//2022.01 Potential XSS in Servlet
		out.flush();
	}

	/**
	 * 팝업창을 오픈 한다.
	 * @param commandMap
	 * @param popupManageVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/pwm/openPopupManage.do")
	public String egovPopupManagePopupOpen(@RequestParam("fileUrl") String fileUrl,
		@RequestParam("stopVewAt") String stopVewAt, @RequestParam("popupId") String popupId,
		ModelMap model) throws Exception {

		model.addAttribute("stopVewAt", stopVewAt);
		model.addAttribute("popupId", popupId);

		fileUrl = EgovWebUtil.filePathBlackList(fileUrl);

		List<?> popupWhiteList = egovPopupManageService.selectPopupWhiteList();
		LOGGER.debug("Open Popup > WhiteList Count = {}", popupWhiteList.size());
		if (fileUrl == null) {
			fileUrl = "";
		}
		for (Object obj : popupWhiteList) {
			EgovMap map = (EgovMap)obj;
			LOGGER.debug("Open Popup > whiteList fileUrl = " + map.get("fileUrl"));
			if (fileUrl.equals(map.get("fileUrl"))) {
				return fileUrl;
			}
		}
		//System.out.println("===>>> "+popupWhiteList.size());
		LOGGER.debug("Open Popup > WhiteList mismatch! Please check Admin page!");
		return "egovframework/com/cmm/egovError";
	}

	/**
	 * 팝업창관리 메인 테스트 목록을 조회한다.
	 * @param popupManageVO
	 * @param model
	 * @return "egovframework/com/uss/ion/pwm/listMainPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/pwm/listMainPopup.do")
	public String egovPopupManageMainList(PopupManageVO popupManageVO, ModelMap model) throws Exception {

		List<?> reusltList = egovPopupManageService.selectPopupMainList(popupManageVO);
		model.addAttribute("resultList", reusltList);

		return "egovframework/com/uss/ion/pwm/EgovPopupMainList";
	}

	/**
	 * 시간을 LIST를 반환한다.
	 * @return  List
	 * @throws
	 */
	@SuppressWarnings("unused")
	private List<ComDefaultCodeVO> getTimeHH() {
		ArrayList<ComDefaultCodeVO> listHH = new ArrayList<ComDefaultCodeVO>();
		HashMap<?, ?> hmHHMM;
		for (int i = 0; i <= 24; i++) {
			String sHH = "";
			String strI = String.valueOf(i);
			if (i < 10) {
				sHH = "0" + strI;
			} else {
				sHH = strI;
			}

			ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
			codeVO.setCode(sHH);
			codeVO.setCodeNm(sHH);

			listHH.add(codeVO);
		}

		return listHH;
	}

	/**
	 * 분을 LIST를 반환한다.
	 * @return  List
	 * @throws
	 */
	@SuppressWarnings("unused")
	private List<ComDefaultCodeVO> getTimeMM() {
		ArrayList<ComDefaultCodeVO> listMM = new ArrayList<ComDefaultCodeVO>();
		HashMap<?, ?> hmHHMM;
		for (int i = 0; i <= 60; i++) {

			String sMM = "";
			String strI = String.valueOf(i);
			if (i < 10) {
				sMM = "0" + strI;
			} else {
				sMM = strI;
			}

			ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
			codeVO.setCode(sMM);
			codeVO.setCodeNm(sMM);

			listMM.add(codeVO);
		}
		return listMM;
	}

	/**
	 * 0을 붙여 반환
	 * @return  String
	 * @throws
	 */
	public String dateTypeIntForString(int iInput) {
		String sOutput = "";
		if (Integer.toString(iInput).length() == 1) {
			sOutput = "0" + Integer.toString(iInput);
		} else {
			sOutput = Integer.toString(iInput);
		}

		return sOutput;
	}
}