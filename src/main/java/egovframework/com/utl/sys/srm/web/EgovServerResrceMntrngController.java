package egovframework.com.utl.sys.srm.web;

import javax.annotation.Resource;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.srm.service.EgovServerResrceMntrngService;
import egovframework.com.utl.sys.srm.service.ServerResrceMntrngVO;

/**
 * <pre>
 * 개요
 * - 서버자원모니터링에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버자원모니터링에 대한 등록, 조회 기능을 제공한다.
 * </pre>
 * 
 * @author lee.m.j
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.09.06  lee.m.j      최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2025.09.18  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidReassigningParameters(넘겨받는 메소드 parameter 값을 직접 변경하는 코드 탐지)
 *
 *      </pre>
 */
@Controller
public class EgovServerResrceMntrngController {

	@Resource(name = "egovServerResrceMntrngService")
	private EgovServerResrceMntrngService egovServerResrceMntrngService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 서버자원모니터링의 대상정보 목록을 조회한다.
	 * 
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name = "서버자원모니터링-대상목록", order = 2170, gid = 90)
	@RequestMapping(value = "/utl/sys/srm/selectMntrngServerList.do")
	public String selectMntrngServerList(
			@ModelAttribute("serverResrceMntrngVO") ServerResrceMntrngVO serverResrceMntrngVO, ModelMap model)
			throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(serverResrceMntrngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(serverResrceMntrngVO.getPageUnit());
		paginationInfo.setPageSize(serverResrceMntrngVO.getPageSize());

		serverResrceMntrngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		serverResrceMntrngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		serverResrceMntrngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("mntrngServerList",
				egovServerResrceMntrngService.selectMntrngServerList(serverResrceMntrngVO));

		int totCnt = egovServerResrceMntrngService.selectMntrngServerListTotCnt(serverResrceMntrngVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/utl/sys/srm/EgovMntrngServerList";
	}

	/**
	 * 서버자원모니터링의 로그정보 목록화면 이동
	 * 
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/srm/selectServerResrceMntrngListView.do")
	public String selectServerResrceMntrngListView(
			@ModelAttribute("pmServerResrceMntrng") ServerResrceMntrngVO pmServerResrceMntrng, ModelMap model)
			throws Exception {

		pmServerResrceMntrng
				.setStrStartDt(EgovStringUtil.addMinusChar(EgovDateUtil.addMonth(EgovDateUtil.getToday(), -1)));
		pmServerResrceMntrng.setStrEndDt(EgovStringUtil.addMinusChar(EgovDateUtil.getToday()));
		model.addAttribute("pmServerResrceMntrng", pmServerResrceMntrng);

		return "egovframework/com/utl/sys/srm/EgovServerResrceMntrngList";
	}

	/**
	 * 서버자원모니터링의 로그정보 목록을 조회한다.
	 * 
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/srm/selectServerResrceMntrngList.do")
	public String selectServerResrceMntrngList(
			@ModelAttribute("serverResrceMntrngVO") ServerResrceMntrngVO serverResrceMntrngVO,
			@ModelAttribute("pmServerResrceMntrng") ServerResrceMntrngVO pmServerResrceMntrng, ModelMap model)
			throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(serverResrceMntrngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(serverResrceMntrngVO.getPageUnit());
		paginationInfo.setPageSize(serverResrceMntrngVO.getPageSize());

		serverResrceMntrngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		serverResrceMntrngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		serverResrceMntrngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if (serverResrceMntrngVO.getStrStartDt() == null || serverResrceMntrngVO.getStrEndDt() == null) {
			serverResrceMntrngVO.setStrStartDt(EgovDateUtil.addMonth(EgovDateUtil.getToday(), -1));
			serverResrceMntrngVO.setStrEndDt(EgovDateUtil.getToday());
		} else {
			serverResrceMntrngVO.setStrStartDt(EgovStringUtil.removeMinusChar(serverResrceMntrngVO.getStrStartDt()));
			serverResrceMntrngVO.setStrEndDt(EgovStringUtil.removeMinusChar(serverResrceMntrngVO.getStrEndDt()));
		}

		model.addAttribute("serverResrceMntrngList",
				egovServerResrceMntrngService.selectServerResrceMntrngList(serverResrceMntrngVO));

		int totCnt = egovServerResrceMntrngService.selectServerResrceMntrngListTotCnt(serverResrceMntrngVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		pmServerResrceMntrng.setStrStartDt(EgovStringUtil.addMinusChar(serverResrceMntrngVO.getStrStartDt()));
		pmServerResrceMntrng.setStrEndDt(EgovStringUtil.addMinusChar(serverResrceMntrngVO.getStrEndDt()));
		model.addAttribute("pmServerResrceMntrng", pmServerResrceMntrng);

		return "egovframework/com/utl/sys/srm/EgovServerResrceMntrngList";
	}

	/**
	 * 서버자원모니터링 로그의 상세정보를 조회한다.
	 * 
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/utl/sys/srm/getServerResrceMntrng.do")
	public String selectServerResrceMntrng(@RequestParam("logId") String logId,
			@RequestParam("strStartDt") String strStartDt, @RequestParam("strEndDt") String strEndDt,
			@ModelAttribute("serverResrceMntrngVO") ServerResrceMntrngVO serverResrceMntrngVO,
			@ModelAttribute("pmServerResrceMntrng") ServerResrceMntrngVO pmServerResrceMntrng, ModelMap model)
			throws Exception {
		serverResrceMntrngVO.setLogId(logId);
		ServerResrceMntrngVO serverResrceMntrng = egovServerResrceMntrngService
				.selectServerResrceMntrng(serverResrceMntrngVO);

		pmServerResrceMntrng.setStrStartDt(strStartDt);
		pmServerResrceMntrng.setStrEndDt(strEndDt);

		model.addAttribute("serverResrceMntrng", serverResrceMntrng);
		model.addAttribute("pmServerResrceMntrng", pmServerResrceMntrng);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/utl/sys/srm/EgovServerResrceMntrngDetail";
	}

}