package egovframework.com.utl.sys.fsm.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.fsm.service.EgovFileSysMntrngService;
import egovframework.com.utl.sys.fsm.service.FileSysMntrng;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngLogVO;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngVO;
import egovframework.com.utl.sys.fsm.service.FileSystemChecker;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 파일시스템 모니터링대상에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 파일시스템 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 파일시스템 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:26
 *  <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.28   장철호     최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 * </pre>
 */

@Controller
public class EgovFileSysMntrngController {

	@Resource(name = "EgovFileSysMntrngService")
	protected EgovFileSysMntrngService fileSysMntrngService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 파일시스템 모니터링대상 정보에 대한 목록을 조회한다.
	 * @param FileSysMntrngVO
	 * @return  String
	 *
	 * @param fileSysMntrngVO
	 */
	@IncludedInfo(name = "파일시스템모니터링", order = 2130, gid = 90)
	@RequestMapping("/utl/sys/fsm/selectFileSysMntrngList.do")
	public String selectFileSysMntrngList(@ModelAttribute("searchVO") FileSysMntrngVO fileSysMntrngVO, ModelMap model) throws Exception {
		//로그인 객체 선언
		//LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		fileSysMntrngVO.setPageUnit(propertyService.getInt("pageUnit"));
		fileSysMntrngVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(fileSysMntrngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(fileSysMntrngVO.getPageUnit());
		paginationInfo.setPageSize(fileSysMntrngVO.getPageSize());

		fileSysMntrngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		fileSysMntrngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		fileSysMntrngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = fileSysMntrngService.selectFileSysMntrngList(fileSysMntrngVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/fsm/EgovFileSysMntrngList";
	}

	/**
	 * 파일시스템 모니터링 대상 정보의 등록페이지로 이동한다.
	 * @param FileSysMntrngVO - 파일시스템 모니터링 VO
	 * @return  String - 리턴 URL
	 *
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/addFileSysMntrng.do")
	public String addFileSysMntrng(@ModelAttribute("fileSysMntrngVO") FileSysMntrngVO fileSysMntrngVO, BindingResult bindingResult, ModelMap model) throws Exception {
		String sLocationUrl = "egovframework/com/utl/sys/fsm/EgovFileSysMntrngRegist";

		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		return sLocationUrl;
	}

	/**
	 * 파일시스템 모니터링 대상 정보의 수정페이지로 이동한다.
	 * @param FileSysMntrngVO - 파일시스템 모니터링 VO
	 * @return  String - 리턴 URL
	 *
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/modifyFileSysMntrng.do")
	public String modifyFileSysMntrng(@ModelAttribute("fileSysMntrngVO") FileSysMntrngVO fileSysMntrngVO, BindingResult bindingResult, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		FileSysMntrngVO resultVO = fileSysMntrngService.selectFileSysMntrng(fileSysMntrngVO);

		resultVO.setSearchCnd(fileSysMntrngVO.getSearchCnd());
		resultVO.setSearchWrd(fileSysMntrngVO.getSearchWrd());
		resultVO.setPageIndex(fileSysMntrngVO.getPageIndex());

		if (resultVO.getCreatDt() != null && !resultVO.getCreatDt().equals("")) {
			if (resultVO.getCreatDt().length() > 18) {
				resultVO.setCreatDt(resultVO.getCreatDt().substring(0, 19));
			}
		}

		model.addAttribute("fileSysMntrngVO", resultVO);

		return "egovframework/com/utl/sys/fsm/EgovFileSysMntrngUpdt";
	}

	/**
	 * 파일시스템 모니터링대상 정보를 조회한다.
	 * @param FileSysMntrngVO
	 * @return  String
	 *
	 * @param fileSysMntrngVO
	 */
	@RequestMapping("/utl/sys/fsm/selectFileSysMntrng.do")
	public String selectFileSysMntrng(@ModelAttribute("ntwrkSvcMntrngVO") FileSysMntrngVO fileSysMntrngVO, ModelMap model) throws Exception {
		FileSysMntrng fileSysMntrng = fileSysMntrngService.selectFileSysMntrng(fileSysMntrngVO);

		if (fileSysMntrng.getCreatDt() != null && !fileSysMntrng.getCreatDt().equals("")) {
			if (fileSysMntrng.getCreatDt().length() > 18) {
				fileSysMntrng.setCreatDt(fileSysMntrng.getCreatDt().substring(0, 19));
			}
		}
		model.addAttribute("fileSysMntrngVO", fileSysMntrng);

		return "egovframework/com/utl/sys/fsm/EgovFileSysMntrngDetail";
	}

	/**
	 * 파일시스템 모니터링대상 정보를 수정한다.
	 * @param FileSysMntrng
	 * @return  String
	 *
	 * @param fileSysMntrng
	 */
	@RequestMapping("/utl/sys/fsm/updateFileSysMntrng.do")
	public String updateFileSysMntrng(@ModelAttribute("fileSysMntrngVO") FileSysMntrngVO fileSysMntrngVO, BindingResult bindingResult, ModelMap model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(fileSysMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			FileSysMntrng fileSysMntrng = fileSysMntrngService.selectFileSysMntrng(fileSysMntrngVO);
			model.addAttribute("fileSysMntrng", fileSysMntrng);
			return "egovframework/com/utl/sys/fsm/EgovFileSysMntrngUpdt";
		}

		if (isAuthenticated) {
			fileSysMntrngVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
			fileSysMntrngService.updateFileSysMntrng(fileSysMntrngVO);
		}

		return "forward:/utl/sys/fsm/selectFileSysMntrngList.do";
	}

	/**
	 * 파일시스템 모니터링대상 정보를 등록한다.
	 * @param FileSysMntrng
	 * @return  String
	 *
	 * @param fileSysMntrng
	 */
	@RequestMapping("/utl/sys/fsm/insertFileSysMntrng.do")
	public String insertFileSysMntrng(@ModelAttribute("fileSysMntrngVO") FileSysMntrngVO fileSysMntrngVO, BindingResult bindingResult, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/utl/sys/fsm/EgovFileSysMntrngRegist";

		//서버  validate 체크
		beanValidator.validate(fileSysMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return sLocationUrl;
		}

		//아이디 설정
		fileSysMntrngVO.setFrstRegisterId((String) (loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId())));
		fileSysMntrngVO.setLastUpdusrId((String) (loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId())));

		fileSysMntrngService.insertFileSysMntrng(fileSysMntrngVO);
		sLocationUrl = "forward:/utl/sys/fsm/selectFileSysMntrngList.do";

		return sLocationUrl;
	}

	/**
	 * 파일시스템 모니터링대상 정보를 삭제한다.
	 * @param FileSysMntrng
	 * @return  String
	 *
	 * @param fileSysMntrng
	 */
	@RequestMapping("/utl/sys/fsm/deleteFileSysMntrng.do")
	public String deleteFileSysMntrng(@ModelAttribute("fileSysMntrngVO") FileSysMntrngVO fileSysMntrngVO, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}
		fileSysMntrngService.deleteFileSysMntrng(fileSysMntrngVO);
		return "forward:/utl/sys/fsm/selectFileSysMntrngList.do";
	}

	/**
	 * 파일시스템의 크기를 조회한다.
	 * @param FileSysMntrng
	 * @return  String
	 *
	 * @param fileSysMntrng
	 */
	@RequestMapping("/utl/sys/fsm/selectFileSysMg.do")
	public String selectFileSysMg(@ModelAttribute("fileSysMntrngVO") FileSysMntrngVO fileSysMntrngVO, ModelMap model) throws Exception {
		//System.out.println("FileSysNm" + fileSysMntrngVO.getFileSysNm());

		int totalSpaceFileSys = 0;
		try {
			totalSpaceFileSys = FileSystemChecker.totalSpaceGb(fileSysMntrngVO.getFileSysNm());
		} catch (IOException e) {
			model.addAttribute("notApplicableFileSys", "true");
		}
		model.addAttribute("fileSysMgValue", totalSpaceFileSys);
		model.addAttribute("fileSysMntrngVO", fileSysMntrngVO);

		return "egovframework/com/utl/sys/fsm/EgovFileSysMntrngRegist";
	}

	/**
	 * 파일시스템 모니터링로그 정보에 대한 목록을 조회한다.
	 * @param FileSysMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 * @return  String - 리턴 URL
	 *
	 * @param fileSysMntrngLogVO
	 */
	@RequestMapping("/utl/sys/fsm/selectFileSysMntrngLogList.do")
	public String selectFileSysMntrngLogList(@ModelAttribute("searchVO") FileSysMntrngLogVO fileSysMntrngLogVO, ModelMap model) throws Exception {
		//로그인 객체 선언
		//LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		fileSysMntrngLogVO.setPageUnit(propertyService.getInt("pageUnit"));
		fileSysMntrngLogVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(fileSysMntrngLogVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(fileSysMntrngLogVO.getPageUnit());
		paginationInfo.setPageSize(fileSysMntrngLogVO.getPageSize());

		fileSysMntrngLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		fileSysMntrngLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
		fileSysMntrngLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 조회기간설정
		if (fileSysMntrngLogVO.getSearchBgnDe() != null && fileSysMntrngLogVO.getSearchEndDe() != null) {
			if (!fileSysMntrngLogVO.getSearchBgnDe().equals("") && !fileSysMntrngLogVO.getSearchEndDe().equals("")) {
				fileSysMntrngLogVO.setSearchBgnDt(fileSysMntrngLogVO.getSearchBgnDe() + " " + fileSysMntrngLogVO.getSearchBgnHour());
				fileSysMntrngLogVO.setSearchEndDt(fileSysMntrngLogVO.getSearchEndDe() + " " + fileSysMntrngLogVO.getSearchEndHour());
			}
		}

		Map<String, Object> map = fileSysMntrngService.selectFileSysMntrngLogList(fileSysMntrngLogVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		@SuppressWarnings("unchecked")
		List<FileSysMntrngLogVO> list = (List<FileSysMntrngLogVO>) map.get("resultList");
		for (int k = 0; k < list.size(); k++) {
			FileSysMntrngLogVO logVO = list.get(k);

			if (logVO.getCreatDt() != null && !logVO.getCreatDt().equals("")) {
				if (logVO.getCreatDt().length() > 18) {
					logVO.setCreatDt(logVO.getCreatDt().substring(0, 19));
				}
			}

			list.set(k, logVO);
			//System.out.println(list.get(k).getCreatDt());
		}

		// 조회시작시
		model.addAttribute("searchBgnHour", (List<ComDefaultCodeVO>) getTimeHH());
		// 조회종료시
		model.addAttribute("searchEndHour", (List<ComDefaultCodeVO>) getTimeHH());

		model.addAttribute("resultList", list);
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/fsm/EgovFileSysMntrngLogList";
	}

	/**
	 * 파일시스템 모니터링로그 정보를 조회한다.
	 * @param FileSysMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 * @return  String - 리턴 URL
	 *
	 * @param fileSysMntrngLogVO
	 */
	@RequestMapping("/utl/sys/fsm/selectFileSysMntrngLog.do")
	public String selectFileSysMntrngLog(@ModelAttribute("fileSysMntrngLogVO") FileSysMntrngLogVO fileSysMntrngLogVO, ModelMap model) throws Exception {
		FileSysMntrngLogVO fileSysMntrngLog = fileSysMntrngService.selectFileSysMntrngLog(fileSysMntrngLogVO);

		if (fileSysMntrngLog.getCreatDt() != null && !fileSysMntrngLog.getCreatDt().equals("")) {
			if (fileSysMntrngLog.getCreatDt().length() > 18) {
				fileSysMntrngLog.setCreatDt(fileSysMntrngLog.getCreatDt().substring(0, 19));
			}
		}
		model.addAttribute("fileSysMntrngLog", fileSysMntrngLog);

		return "egovframework/com/utl/sys/fsm/EgovFileSysMntrngLogDetail";
	}

	/**
	 * 시간의 LIST를 반환한다.
	 * @return  List
	 * @throws
	 */
	private List<ComDefaultCodeVO> getTimeHH() {
		List<ComDefaultCodeVO> listHH = new ArrayList<ComDefaultCodeVO>();
		//HashMap hmHHMM;
		for (int i = 0; i < 24; i++) {
			String sHH = "";
			String strI = String.valueOf(i);
			if (i < 10) {
				sHH = "0" + strI;
			} else {
				sHH = strI;
			}

			ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
			codeVO.setCode(sHH);
			codeVO.setCodeNm(sHH + ":00");

			listHH.add(codeVO);
		}

		return listHH;
	}

}