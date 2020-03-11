package egovframework.com.utl.sys.nsm.web;
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
import egovframework.com.utl.sys.nsm.service.EgovNtwrkSvcMntrngService;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrng;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngLogVO;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 네트워크서비스 모니터링대상에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 네트워크서비스 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 네트워크서비스 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:42
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
public class EgovNtwrkSvcMntrngController {

	@Resource(name="EgovNtwrkSvcMntrngService")
    protected EgovNtwrkSvcMntrngService ntwrkSvcMntrngService;

	@Resource(name="propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    private DefaultBeanValidator beanValidator;

	/**
	 * 네트워크서비스 모니터링대상 정보에 대한 목록을 조회한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * @return  String - 리턴 URL
	 *
	 * @param ntwrkSvcMntrngVO
	 */
    @SuppressWarnings("unused")
	@IncludedInfo(name="네트워크서비스모니터링", order = 2120 ,gid = 90)
    @RequestMapping("/utl/sys/nsm/selectNtwrkSvcMntrngList.do")
	public String selectNtwrkSvcMntrngList(@ModelAttribute("searchVO") NtwrkSvcMntrngVO ntwrkSvcMntrngVO, ModelMap model) throws Exception{
    	//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		ntwrkSvcMntrngVO.setPageUnit(propertyService.getInt("pageUnit"));
		ntwrkSvcMntrngVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ntwrkSvcMntrngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ntwrkSvcMntrngVO.getPageUnit());
		paginationInfo.setPageSize(ntwrkSvcMntrngVO.getPageSize());

		ntwrkSvcMntrngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ntwrkSvcMntrngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ntwrkSvcMntrngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = ntwrkSvcMntrngService.selectNtwrkSvcMntrngList(ntwrkSvcMntrngVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/nsm/EgovNtwrkSvcMntrngList";
	}

    /**
	 * 네트워크서비스 모니터링 대상 정보의 등록페이지로 이동한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return  String - 리턴 URL
	 *
	 * @param ntwrkSvcMntrngVO
	 */
    @RequestMapping("/utl/sys/nsm/addNtwrkSvcMntrng.do")
	public String addNtwrkSvcMntrng(@ModelAttribute("ntwrkSvcMntrngVO") NtwrkSvcMntrngVO ntwrkSvcMntrngVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	String sLocationUrl = "egovframework/com/utl/sys/nsm/EgovNtwrkSvcMntrngRegist";

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	return sLocationUrl;
	}

    /**
	 * 네트워크서비스 모니터링 대상 정보의 수정페이지로 이동한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링 대상 VO
	 * @return  String - 리턴 URL
	 *
	 * @param ntwrkSvcMntrngVO
	 */
    @RequestMapping("/utl/sys/nsm/modifyNtwrkSvcMntrng.do")
	public String modifyNtwrkSvcMntrng(@ModelAttribute("ntwrkSvcMntrngVO") NtwrkSvcMntrngVO ntwrkSvcMntrngVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

    	NtwrkSvcMntrngVO resultVO = ntwrkSvcMntrngService.selectNtwrkSvcMntrng(ntwrkSvcMntrngVO);

    	//시스템 IP 설정
    	String[] sysIps = resultVO.getSysIp().split("[.]");
    	resultVO.setSysIp1(sysIps[0]);
    	resultVO.setSysIp2(sysIps[1]);
    	resultVO.setSysIp3(sysIps[2]);
    	resultVO.setSysIp4(sysIps[3]);

		resultVO.setSearchCnd(ntwrkSvcMntrngVO.getSearchCnd());
		resultVO.setSearchWrd(ntwrkSvcMntrngVO.getSearchWrd());
		resultVO.setPageIndex(ntwrkSvcMntrngVO.getPageIndex());

		if(resultVO.getCreatDt() != null && !resultVO.getCreatDt().equals("")){
			if(resultVO.getCreatDt().length() > 18){
				resultVO.setCreatDt(resultVO.getCreatDt().substring(0, 19));
			}
		}

        model.addAttribute("ntwrkSvcMntrngVO", resultVO);

		return "egovframework/com/utl/sys/nsm/EgovNtwrkSvcMntrngUpdt";
	}

	/**
	 * 네트워크서비스 모니터링대상 정보를 조회한다.
	 * @param NtwrkSvcMntrngVO - 네트워크서비스 모니터링대상 VO
	 * @return  String - 리턴 URL
	 *
	 * @param ntwrkSvcMntrngVO
	 */
    @RequestMapping("/utl/sys/nsm/selectNtwrkSvcMntrng.do")
	public String selectNtwrkSvcMntrng(@ModelAttribute("ntwrkSvcMntrngVO") NtwrkSvcMntrngVO ntwrkSvcMntrngVO, ModelMap model) throws Exception{
    	NtwrkSvcMntrng ntwrkSvcMntrng = ntwrkSvcMntrngService.selectNtwrkSvcMntrng(ntwrkSvcMntrngVO);

    	//시스템 IP 설정
    	String[] sysIps = ntwrkSvcMntrng.getSysIp().split("[.]");
    	ntwrkSvcMntrng.setSysIp1(sysIps[0]);
    	ntwrkSvcMntrng.setSysIp2(sysIps[1]);
    	ntwrkSvcMntrng.setSysIp3(sysIps[2]);
    	ntwrkSvcMntrng.setSysIp4(sysIps[3]);

    	if(ntwrkSvcMntrng.getCreatDt() != null && !ntwrkSvcMntrng.getCreatDt().equals("")){
			if(ntwrkSvcMntrng.getCreatDt().length() > 18){
				ntwrkSvcMntrng.setCreatDt(ntwrkSvcMntrng.getCreatDt().substring(0, 19));
			}
		}
		model.addAttribute("ntwrkSvcMntrngVO", ntwrkSvcMntrng);


		return "egovframework/com/utl/sys/nsm/EgovNtwrkSvcMntrngDetail";
	}

	/**
	 * 네트워크서비스 모니터링대상 정보를 수정한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * @return  String - 리턴 URL
	 *
	 * @param ntwrkSvcMntrng
	 */
    @RequestMapping("/utl/sys/nsm/updateNtwrkSvcMntrng.do")
	public String updateNtwrkSvcMntrng(@ModelAttribute("ntwrkSvcMntrngVO") NtwrkSvcMntrngVO ntwrkSvcMntrngVO, BindingResult bindingResult, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(ntwrkSvcMntrngVO, bindingResult);
		if (bindingResult.hasErrors()) {
			NtwrkSvcMntrng ntwrkSvcMntrng = ntwrkSvcMntrngService.selectNtwrkSvcMntrng(ntwrkSvcMntrngVO);
		    model.addAttribute("ntwrkSvcMntrng", ntwrkSvcMntrng);
		    return "egovframework/com/utl/sys/nsm/EgovNtwrkSvcMntrngUpdt";
		}

		if (isAuthenticated) {
			//시스템 IP 설정
			String sysIp = "";
			sysIp += ntwrkSvcMntrngVO.getSysIp1();
			sysIp += ".";
			sysIp += ntwrkSvcMntrngVO.getSysIp2();
			sysIp += ".";
			sysIp += ntwrkSvcMntrngVO.getSysIp3();
			sysIp += ".";
			sysIp += ntwrkSvcMntrngVO.getSysIp4();
			ntwrkSvcMntrngVO.setSysIp(sysIp);

    		ntwrkSvcMntrngVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
    		ntwrkSvcMntrngService.updateNtwrkSvcMntrng(ntwrkSvcMntrngVO);
		}

		return "forward:/utl/sys/nsm/selectNtwrkSvcMntrngList.do";
	}

	/**
	 * 네트워크서비스 모니터링대상 정보를 등록한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * @return  String - 리턴 URL
	 *
	 * @param ntwrkSvcMntrng
	 */
    @RequestMapping("/utl/sys/nsm/insertNtwrkSvcMntrng.do")
	public String insertNtwrkSvcMntrng(@ModelAttribute("ntwrkSvcMntrngVO") NtwrkSvcMntrngVO ntwrkSvcMntrngVO, BindingResult bindingResult, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "egovframework/com/utl/sys/nsm/EgovNtwrkSvcMntrngRegist";

		//서버  validate 체크
        beanValidator.validate(ntwrkSvcMntrngVO, bindingResult);
		if(bindingResult.hasErrors()){
			return sLocationUrl;
		}

		//시스템 IP 설정
		String sysIp = "";
		sysIp += ntwrkSvcMntrngVO.getSysIp1();
		sysIp += ".";
		sysIp += ntwrkSvcMntrngVO.getSysIp2();
		sysIp += ".";
		sysIp += ntwrkSvcMntrngVO.getSysIp3();
		sysIp += ".";
		sysIp += ntwrkSvcMntrngVO.getSysIp4();
		ntwrkSvcMntrngVO.setSysIp(sysIp);

		//아이디 설정
		ntwrkSvcMntrngVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		ntwrkSvcMntrngVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		if(ntwrkSvcMntrngService.selectNtwrkSvcMntrngCheck(ntwrkSvcMntrngVO) > 0){
			model.addAttribute("ntwrkSvcMntrngDuplicated", "true");
			sLocationUrl = "forward:/utl/sys/nsm/addNtwrkSvcMntrng.do";
		}else{
			ntwrkSvcMntrngService.insertNtwrkSvcMntrng(ntwrkSvcMntrngVO);
	    	sLocationUrl = "forward:/utl/sys/nsm/selectNtwrkSvcMntrngList.do";
		}

        return sLocationUrl;
	}

	/**
	 * 네트워크서비스 모니터링대상 정보를 삭제한다.
	 * @param NtwrkSvcMntrng - 네트워크서비스 모니터링대상 model
	 * @return  String - 리턴 URL
	 *
	 * @param ntwrkSvcMntrng
	 */
    @RequestMapping("/utl/sys/nsm/deleteNtwrkSvcMntrng.do")
	public String deleteNtwrkSvcMntrng(@ModelAttribute("ntwrkSvcMntrngVO") NtwrkSvcMntrngVO ntwrkSvcMntrngVO, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	ntwrkSvcMntrngService.deleteNtwrkSvcMntrng(ntwrkSvcMntrngVO);
		return "forward:/utl/sys/nsm/selectNtwrkSvcMntrngList.do";
	}

	/**
	 * 네트워크서비스 모니터링로그 정보에 대한 목록을 조회한다.
	 * @param NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 * @return  String - 리턴 URL
	 *
	 * @param ntwrkSvcMntrngLogVO
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping("/utl/sys/nsm/selectNtwrkSvcMntrngLogList.do")
	public String selectNtwrkSvcMntrngLogList(@ModelAttribute("searchVO") NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO, ModelMap model) throws Exception{
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		ntwrkSvcMntrngLogVO.setPageUnit(propertyService.getInt("pageUnit"));
		ntwrkSvcMntrngLogVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ntwrkSvcMntrngLogVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ntwrkSvcMntrngLogVO.getPageUnit());
		paginationInfo.setPageSize(ntwrkSvcMntrngLogVO.getPageSize());

		ntwrkSvcMntrngLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ntwrkSvcMntrngLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ntwrkSvcMntrngLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 조회기간설정
		if(ntwrkSvcMntrngLogVO.getSearchBgnDe() != null && ntwrkSvcMntrngLogVO.getSearchEndDe() != null){
			if(!ntwrkSvcMntrngLogVO.getSearchBgnDe().equals("") && !ntwrkSvcMntrngLogVO.getSearchEndDe().equals("")){
				ntwrkSvcMntrngLogVO.setSearchBgnDt(ntwrkSvcMntrngLogVO.getSearchBgnDe() + " " + ntwrkSvcMntrngLogVO.getSearchBgnHour());
				ntwrkSvcMntrngLogVO.setSearchEndDt(ntwrkSvcMntrngLogVO.getSearchEndDe() + " " + ntwrkSvcMntrngLogVO.getSearchEndHour());
			}
		}

		Map<String, Object> map = ntwrkSvcMntrngService.selectNtwrkSvcMntrngLogList(ntwrkSvcMntrngLogVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);

		List<NtwrkSvcMntrngLogVO> list = (List<NtwrkSvcMntrngLogVO>)map.get("resultList");
		for(int k=0; k<list.size(); k++){
			NtwrkSvcMntrngLogVO logVO = list.get(k);

			if(logVO.getCreatDt() != null && !logVO.getCreatDt().equals("")){
				if(logVO.getCreatDt().length() > 18){
					logVO.setCreatDt(logVO.getCreatDt().substring(0, 19));
				}
			}

			list.set(k, logVO);
			//System.out.println(list.get(k).getCreatDt());
		}

		// 조회시작시
    	model.addAttribute("searchBgnHour", getTimeHH());
    	// 조회종료시
    	model.addAttribute("searchEndHour", getTimeHH());

		model.addAttribute("resultList", list);
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/utl/sys/nsm/EgovNtwrkSvcMntrngLogList";
	}

	/**
	 * 네트워크서비스 모니터링로그 정보를 조회한다.
	 * @param NtwrkSvcMntrngLogVO - 네트워크서비스 모니터링로그 VO
	 * @return  String - 리턴 URL
	 *
	 * @param ntwrkSvcMntrngLogVO
	 */
	@RequestMapping("/utl/sys/nsm/selectNtwrkSvcMntrngLog.do")
	public String selectNtwrkSvcMntrngLog(@ModelAttribute("ntwrkSvcMntrngLogVO") NtwrkSvcMntrngLogVO ntwrkSvcMntrngLogVO, ModelMap model) throws Exception{
		NtwrkSvcMntrngLogVO ntwrkSvcMntrngLog = ntwrkSvcMntrngService.selectNtwrkSvcMntrngLog(ntwrkSvcMntrngLogVO);
		if(ntwrkSvcMntrngLog.getCreatDt() != null && !ntwrkSvcMntrngLog.getCreatDt().equals("")){
			if(ntwrkSvcMntrngLog.getCreatDt().length() > 18){
				ntwrkSvcMntrngLog.setCreatDt(ntwrkSvcMntrngLog.getCreatDt().substring(0, 19));
			}
		}
		model.addAttribute("ntwrkSvcMntrngLog", ntwrkSvcMntrngLog);


		return "egovframework/com/utl/sys/nsm/EgovNtwrkSvcMntrngLogDetail";
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