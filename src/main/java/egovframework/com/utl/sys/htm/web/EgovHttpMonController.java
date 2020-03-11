package egovframework.com.utl.sys.htm.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.htm.service.EgovHttpMonService;
import egovframework.com.utl.sys.htm.service.HttpMntrngChecker;
import egovframework.com.utl.sys.htm.service.HttpMon;
import egovframework.com.utl.sys.htm.service.HttpMonLog;
import egovframework.com.utl.sys.htm.service.HttpMonLogVO;
import egovframework.com.utl.sys.htm.service.HttpMonVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - HTTP서비스모니터링에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - HTTP서비스모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - HTTP서비스모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 17-6-2010 오후 5:12:43
 *  <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.17   박종선     최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 * </pre>
 */

@Controller
public class EgovHttpMonController {

	@Resource(name = "EgovHttpMonService")
	protected EgovHttpMonService egovHttpMonService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
	 * 등록된 HTTP서비스모니터링 정보를 조회 한다.
	 * @param httpMonVO- HTTP서비스모니터링 VO
	 * @return String - 리턴 Url
	 *
	 * @param httpMonVO
	 */
    @IncludedInfo(name="HTTP서비스모니터링", order = 2100 ,gid = 90)
    @RequestMapping(value="/utl/sys/htm/EgovComUtlHttpMonList.do")
	public String selectHttpMonList (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") HttpMonVO searchVO
			, ModelMap model
			) throws Exception {
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

        List<?> HttpMonList = egovHttpMonService.selectHttpMonList(searchVO);
        model.addAttribute("resultList", HttpMonList);

        int totCnt = egovHttpMonService.selectHttpMonTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonList";
	}

	/**
	 * HTTP서비스모니터링상세 정보를 조회 한다.
	 * @param HttpMonVO - HTTP서비스모니터링 VO
	 * @return String - 리턴 Url
	 *
	 * @param httpMonVO
	 */
	@RequestMapping(value="/utl/sys/htm/EgovComUtlHttpMonDetail.do")
 	public String selectHttpMonDetail (@ModelAttribute("loginVO") LoginVO loginVO
 			, HttpMon httpMon
 			, ModelMap model
 			) throws Exception {
		HttpMon vo = egovHttpMonService.selectHttpMonDetail(httpMon);
		model.addAttribute("result", vo);

		//System.out.println("SiteUrl============================컨트롤러 파라미터 확인========================>" + vo.getSiteUrl());
    	//model.addAttribute("siteUrl", HttpMntrngChecker.getPrductStatus(vo.getSiteUrl()));

		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonDetail";
	}

	/**
	 * Http서비스모니터링 정보를 신규로 등록한다.
	 * @param siteUrl - Http서비스모니터링 model
	 * @return String - 리턴 Url
	 *
	 * @param siteUrl
	 */
    @RequestMapping(value="/utl/sys/htm/EgovComUtlHttpMonRegist.do")
	public String insertHttpMon (
			@ModelAttribute("httpMon") HttpMon httpMon
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {

    	// Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	if   (httpMon.getWebKind() == null
    		||httpMon.getWebKind().equals("")) {
    		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonRegist";
    	}

        beanValidator.validate(httpMon, bindingResult);
		if (bindingResult.hasErrors()){
    		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonRegist";
		}

		//아이디 설정
		httpMon.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		httpMon.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		egovHttpMonService.insertHttpMon(httpMon);
        return "forward:/utl/sys/htm/EgovComUtlHttpMonList.do";
    }

	/**
	 * 기 등록 된 Http서비스모니터링 정보를 수정 한다.
	 * @param siteUrl - Http서비스모니터링 model
	 * @return String - 리턴 Url
	 *
	 * @param siteUrl
	 */
    @RequestMapping(value="/utl/sys/htm/EgovComUtlHttpMonModify.do")
	public String updateHttpMon (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("httpMon") HttpMon httpMon
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("")) {
    		HttpMon vo = egovHttpMonService.selectHttpMonDetail(httpMon);
    		model.addAttribute("httpMon", vo);

    		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonModify";
    	} else if (sCmd.equals("Modify")) {
            beanValidator.validate(httpMon, bindingResult);
    		if (bindingResult.hasErrors()){
    			HttpMon vo = egovHttpMonService.selectHttpMonDetail(httpMon);
        		model.addAttribute("httpMon", vo);

        		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonModify";
    		}
    		httpMon.setLastUpdusrId(loginVO.getUniqId());
    		egovHttpMonService.updateHttpMon(httpMon);
	        return "forward:/utl/sys/htm/EgovComUtlHttpMonList.do";
    	} else {
    		return "forward:/utl/sys/htm/EgovComUtlHttpMonList.do";
    	}
    }

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * @return String - 리턴 Url
	 *
	 * @param siteUrl
	 */
    @RequestMapping(value="/utl/sys/htm/EgovComUtlHttpMonRemove.do")
	public String deleteHttpMon (@ModelAttribute("loginVO") LoginVO loginVO
			, HttpMon cmmWebKind
			, ModelMap model
			) throws Exception {
    	egovHttpMonService.deleteHttpMon(cmmWebKind);
        return "forward:/utl/sys/htm/EgovComUtlHttpMonList.do";
	}

	/**
	 * HTTP 서비스 상태를 조회한다.
	 * @param httpMon
	 * @return  String
	 *
	 * @param httpSttusCd
	 */
    @RequestMapping("/utl/sys/htm/selectHttpMonSttus.do")
	public String selectProcessSttus(
			@ModelAttribute("httpMonVO") HttpMonVO httpMonVO
			, ModelMap model
			) throws Exception {

		System.out.println("SiteUrl" + httpMonVO.getSiteUrl());
    	model.addAttribute("httpSttusCd", HttpMntrngChecker.getPrductStatus(httpMonVO.getSiteUrl()));
    	model.addAttribute("httpMonVO", httpMonVO);


		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonRegist";
	}

	/**
	 * 등록된 HTTP서비스모니터링로그 정보를 조회 한다.
	 * @param httpMonVO- HTTP서비스모니터링 VO
	 * @return String - 리턴 Url
	 *
	 * @param httpMonVO
	 */
    @RequestMapping(value="/utl/sys/htm/EgovComUtlHttpMonLogList.do")
	public String selectHttpMonLogList (@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") HttpMonLogVO httpMonLogVO
			, ModelMap model
			) throws Exception {
    	/** EgovPropertyService.sample */
    	httpMonLogVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	httpMonLogVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(httpMonLogVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(httpMonLogVO.getPageUnit());
		paginationInfo.setPageSize(httpMonLogVO.getPageSize());

		httpMonLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		httpMonLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
		httpMonLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 조회기간설정
		if(httpMonLogVO.getSearchBgnDe() != null && httpMonLogVO.getSearchEndDe() != null){
			if(!httpMonLogVO.getSearchBgnDe().equals("") && !httpMonLogVO.getSearchEndDe().equals("")){
				httpMonLogVO.setSearchBgnDt(httpMonLogVO.getSearchBgnDe() + " " + httpMonLogVO.getSearchBgnHour());
				httpMonLogVO.setSearchEndDt(httpMonLogVO.getSearchEndDe() + " " + httpMonLogVO.getSearchEndHour());
			}
		}

		Map<String, Object> map = egovHttpMonService.selectHttpMonLogList(httpMonLogVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		// 조회시작시
    	model.addAttribute("searchBgnHour", getTimeHH());
    	// 조회종료시
    	model.addAttribute("searchEndHour", getTimeHH());

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonLogList";

	}

	/**
	 * HTTP서비스모니터링로그상세 정보를 조회 한다.
	 * @param HttpMonVO - HTTP서비스모니터링 VO
	 * @return String - 리턴 Url
	 *
	 * @param httpMonVO
	 */
	@RequestMapping(value="/utl/sys/htm/EgovComUtlHttpMonDetailLog.do")
 	public String selectHttpMonDetailLog (@ModelAttribute("loginVO") LoginVO loginVO
 			, HttpMonLog httpMonLog
 			, ModelMap model
 			) throws Exception {
		HttpMonLog vo = egovHttpMonService.selectHttpMonDetailLog(httpMonLog);
		model.addAttribute("result", vo);

		return "egovframework/com/utl/sys/htm/EgovComUtlHttpMonDetailLog";
	}

	/**
	 * 시간의 LIST를 반환한다.
	 * @return  List
	 * @throws
	 */
	private List<ComDefaultCodeVO> getTimeHH (){
    	ArrayList<ComDefaultCodeVO> listHH = new ArrayList<ComDefaultCodeVO>();
    	//HashMap hmHHMM;
    	for(int i=0;i < 24; i++){
    		String sHH = "";
    		String strI = String.valueOf(i);
    		if(i<10){
    			sHH = "0" + strI;
    		}else{
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
