package egovframework.com.utl.sys.pxy.web;

import java.util.List;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.annotation.RequireAdmin;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.pxy.service.EgovProxySvcService;
import egovframework.com.utl.sys.pxy.service.ProxyLog;
import egovframework.com.utl.sys.pxy.service.ProxySvc;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * <pre>
 * 개요
 * - 프록시서비스정보에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 프록시서비스정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 프록시서비스정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author lee.m.j
 * @since 2010.06.28
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.28  lee.m.j      최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2019.12.05  신용호          KISA 보안약점 조치 (경로조작및 자원 삽입)
 *   2025.09.17  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(변수명에 밑줄 사용)
 *   2025.09.17  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidReassigningParameters(넘겨받는 메소드 parameter 값을 직접 변경하는 코드 탐지)
 *   2026.04.20  유지보수        국가사이버안보센터(NCSC)의 보안 점검 결과 반영을 통한 보안패치
 *
 *      </pre>
 */
@Controller
public class EgovProxySvcController {

	@Resource(name = "egovProxySvcService")
	private EgovProxySvcService egovProxySvcService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** ID Generation */
	@Resource(name = "egovProxySvcIdGnrService")

	private EgovIdGnrService egovProxySvcIdGnrService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService egovCmmUseService;

	/**
	 * 프록시서비스정보 목록화면 이동
	 *
	 * @return String
	 */
	@RequestMapping(value = "/utl/sys/pxy/selectProxySvcListView.do")
	public String selectProxySvcListView() throws Exception {
		return "egovframework/com/utl/sys/pxy/EgovProxySvcList";
	}

	/**
	 * 프록시서비스를 관리하기 위해 등록된 프록시정보 목록을 조회한다.
	 *
	 * @param proxySvc - 프록시서비스 Vo
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "프록시서비스", order = 2140, gid = 90)
	@RequireAdmin
	@RequestMapping(value = "/utl/sys/pxy/selectProxySvcList.do")
	public String selectProxySvcList(@ModelAttribute("proxySvc") ProxySvc proxySvc, Model model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(proxySvc.getPageIndex());
		paginationInfo.setRecordCountPerPage(proxySvc.getPageUnit());
		paginationInfo.setPageSize(proxySvc.getPageSize());

		proxySvc.setFirstIndex(paginationInfo.getFirstRecordIndex());
		proxySvc.setLastIndex(paginationInfo.getLastRecordIndex());
		proxySvc.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("proxySvcList", egovProxySvcService.selectProxySvcList(proxySvc));

		int totCnt = egovProxySvcService.selectProxySvcListTotCnt(proxySvc);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/utl/sys/pxy/EgovProxySvcList";
	}

	/**
	 * 등록된 프록시서비스의 상세정보를 조회한다.
	 *
	 * @param proxyId    - String
	 * @param proxySvc - 프록시서비스 Vo
	 * @return String - 리턴 Url
	 */
	@PostMapping("/utl/sys/pxy/getProxySvc.do")
	@RequireAdmin
	public String selectProxySvc(@ModelAttribute("proxySvc") ProxySvc proxySvc, Model model) throws Exception {
		ProxySvc proxySvcResult = egovProxySvcService.selectProxySvc(proxySvc);
		model.addAttribute("proxySvc", proxySvcResult);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/utl/sys/pxy/EgovProxySvcDetail";
	}

	/**
	 * 프록시서비스를 신규로 등록하는 화면으로 이동한다.
	 *
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/utl/sys/pxy/addViewProxySvc.do")
	@RequireAdmin
	public String insertViewProxySvc(@ModelAttribute("proxySvc") ProxySvc proxySvc, Model model) throws Exception {

		model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(), "COM072"));
		model.addAttribute("proxySvc", proxySvc);

		return "egovframework/com/utl/sys/pxy/EgovProxySvcRegist";
	}

	/**
	 * 프록시서비스를 신규로 등록한다.
	 *
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/utl/sys/pxy/addProxySvc.do")
	@RequireAdmin
	public String insertProxySvc(@Valid @ModelAttribute("proxySvc") ProxySvc proxySvc, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(), "COM072"));
			return "egovframework/com/utl/sys/pxy/EgovProxySvcRegist";
		} else {
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); // KISA 보안취약점 조치 (2018-12-10, 이정은)
			if (!isAuthenticated) {
				return "redirect:/uat/uia/egovLoginUsr.do";
			}

			proxySvc.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
			proxySvc.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
			proxySvc.setProxyId(egovProxySvcIdGnrService.getNextStringId());

			proxySvc.setProxyIp(EgovWebUtil.filePathBlackList(proxySvc.getProxyIp()));
			proxySvc.setSvcIp(EgovWebUtil.filePathBlackList(proxySvc.getSvcIp()));

			egovProxySvcService.insertProxySvc(proxySvc);
			redirectAttributes.addFlashAttribute("message", egovMessageSource.getMessage("success.common.insert"));

			return "egovframework/com/utl/sys/pxy/EgovProxySvcDetail";
		}
	}

	/**
	 * 기 등록된 프록시서비스를 수정하는 화면으로 이동한다.
	 *
	 * @param proxyId  - String
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/utl/sys/pxy/updtViewProxySvc.do")
	@RequireAdmin
	public String updateViewProxySvc(@ModelAttribute("proxySvc") ProxySvc proxySvc, Model model) throws Exception {
		model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(), "COM072"));
		model.addAttribute("proxySvc", egovProxySvcService.selectProxySvc(proxySvc));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/utl/sys/pxy/EgovProxySvcUpdt";
	}

	/**
	 * 기 등록된 프록시서비스를 수정한다.
	 *
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/utl/sys/pxy/updtProxySvc.do")
	@RequireAdmin
	public String updateProxySvc(@Valid @ModelAttribute("proxySvc") ProxySvc proxySvc, BindingResult bindingResult, SessionStatus status, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(), "COM072"));
			return "egovframework/com/utl/sys/pxy/EgovProxySvcUpdt";
		} else {
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated(); // KISA 보안취약점 조치 (2018-12-10, 이정은)
			if (!isAuthenticated) {
				return "redirect:/uat/uia/egovLoginUsr.do";
			}

			proxySvc.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
			proxySvc.setProxyIp(EgovWebUtil.filePathBlackList(proxySvc.getProxyIp()));
			proxySvc.setSvcIp(EgovWebUtil.filePathBlackList(proxySvc.getSvcIp()));

			egovProxySvcService.updateProxySvc(proxySvc);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

			return "forward:/utl/sys/pxy/getProxySvc.do";
		}
	}

	/**
	 * 기 등록된 프록시서비스를 삭제한다.
	 *
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@PostMapping("/utl/sys/pxy/removeProxySvc.do")
	@RequireAdmin
	public String deleteProxySvc(@ModelAttribute("proxySvc") ProxySvc proxySvc, RedirectAttributes redirectAttributes) throws Exception {
		egovProxySvcService.deleteProxySvc(proxySvc);
		redirectAttributes.addFlashAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/utl/sys/pxy/selectProxySvcList.do";
	}

	/**
	 * 프록시서비스정보 목록화면 이동
	 *
	 * @return String
	 */
	@RequestMapping(value = "/utl/sys/pxy/selectProxyLogListView.do")
	public String selectProxyLogListView(@ModelAttribute("proxyLog") ProxyLog proxyLog, Model model) throws Exception {
		proxyLog.setStrStartDate(EgovStringUtil.addMinusChar(EgovDateUtil.addMonth(EgovDateUtil.getToday(), -1)));
		proxyLog.setStrEndDate(EgovStringUtil.addMinusChar(EgovDateUtil.getToday()));
		model.addAttribute("proxyLog", proxyLog);

		return "egovframework/com/utl/sys/pxy/EgovProxyLogList";
	}

	/**
	 * 프록시서비스를 모니터링하기 위해 등록된 프록시로그 목록을 조회한다.
	 *
	 * @param proxyLog - 프록시로그 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/pxy/selectProxyLogList.do")
	@RequireAdmin
	public String selectProxyLogList(@ModelAttribute("proxyLog") ProxyLog proxyLog, Model model) throws Exception {
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(proxyLog.getPageIndex());
		paginationInfo.setRecordCountPerPage(proxyLog.getPageUnit());
		paginationInfo.setPageSize(proxyLog.getPageSize());

		proxyLog.setFirstIndex(paginationInfo.getFirstRecordIndex());
		proxyLog.setLastIndex(paginationInfo.getLastRecordIndex());
		proxyLog.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if (proxyLog.getStrStartDate() == null || proxyLog.getStrEndDate() == null) {
			proxyLog.setStrStartDate(EgovDateUtil.addMonth(EgovDateUtil.getToday(), -1));
			proxyLog.setStrEndDate(EgovDateUtil.getToday());
		} else {
			proxyLog.setStrStartDate(EgovStringUtil.removeMinusChar(proxyLog.getStrStartDate()));
			proxyLog.setStrEndDate(EgovStringUtil.removeMinusChar(proxyLog.getStrEndDate()));
		}

		model.addAttribute("proxyLogList", egovProxySvcService.selectProxyLogList(proxyLog));

		int totCnt = egovProxySvcService.selectProxyLogListTotCnt(proxyLog);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		proxyLog.setStrStartDate(EgovStringUtil.addMinusChar(proxyLog.getStrStartDate()));
		proxyLog.setStrEndDate(EgovStringUtil.addMinusChar(proxyLog.getStrEndDate()));
		model.addAttribute("proxyLog", proxyLog);

		return "egovframework/com/utl/sys/pxy/EgovProxyLogList";
	}

	/**
	 * 공통코드 호출
	 *
	 * @param comDefaultCodeVO ComDefaultCodeVO
	 * @param codeId           String
	 * @return List
	 * @exception Exception
	 */
	public List<CmmnDetailCode> getCmmCodeDetailList(ComDefaultCodeVO comDefaultCodeVO, String codeId) throws Exception {
		comDefaultCodeVO.setCodeId(codeId);
		return egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
	}

}