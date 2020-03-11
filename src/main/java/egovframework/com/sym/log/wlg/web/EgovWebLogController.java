package egovframework.com.sym.log.wlg.web;

import java.util.HashMap;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.sym.log.wlg.service.EgovWebLogService;
import egovframework.com.sym.log.wlg.service.WebLog;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Class Name : EgovWebLogController.java
 * @Description : 시스템 로그정보를 관리하기 위한 컨트롤러 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *    2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */

@Controller
public class EgovWebLogController {

	@Resource(name="EgovWebLogService")
	private EgovWebLogService webLogService;

	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;

	/**
	 * 웹 로그 목록 조회
	 *
	 * @param webLog
	 * @return sym/log/wlg/EgovWebLogList
	 * @throws Exception
	 */
	@IncludedInfo(name="웹로그관리", listUrl="/sym/log/wlg/SelectWebLogList.do", order = 1070 ,gid = 60)
	@RequestMapping(value="/sym/log/wlg/SelectWebLogList.do")
	public String selectWebLogInf(@ModelAttribute("searchVO") WebLog webLog,
			ModelMap model) throws Exception{

		webLog.setPageUnit(propertyService.getInt("pageUnit"));
		webLog.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(webLog.getPageIndex());
		paginationInfo.setRecordCountPerPage(webLog.getPageUnit());
		paginationInfo.setPageSize(webLog.getPageSize());

		webLog.setFirstIndex(paginationInfo.getFirstRecordIndex());
		webLog.setLastIndex(paginationInfo.getLastRecordIndex());
		webLog.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		HashMap<?, ?> _map = (HashMap<?, ?>)webLogService.selectWebLogInf(webLog);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));

		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/sym/log/wlg/EgovWebLogList";
	}

	/**
	 * 웹 로그 상세 조회
	 *
	 * @param webLog
	 * @param model
	 * @return sym/log/wlg/EgovWebLogInqire
	 * @throws Exception
	 */
	@RequestMapping(value="/sym/log/wlg/SelectWebLogDetail.do")
	public String selectWebLog(@ModelAttribute("searchVO") WebLog webLog,
			@RequestParam("requstId") String requstId,
			ModelMap model) throws Exception{

		webLog.setRequstId(requstId.trim());

		WebLog vo = webLogService.selectWebLog(webLog);
		model.addAttribute("result", vo);
		return "egovframework/com/sym/log/wlg/EgovWebLogDetail";
	}

}
