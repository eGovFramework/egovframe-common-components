package egovframework.com.utl.sys.pxy.web;

import java.util.List;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sys.pxy.service.EgovProxySvcService;
import egovframework.com.utl.sys.pxy.service.ProxyLogVO;
import egovframework.com.utl.sys.pxy.service.ProxySvc;
import egovframework.com.utl.sys.pxy.service.ProxySvcVO;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 개요
 * - 프록시서비스정보에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 프록시서비스정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 프록시서비스정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:26
 *  <pre>
 * == 개정이력(Modification Information) ==
 *
 *  수정일               수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2010.06.28   lee.m.j    최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2019.12.05   신용호            KISA 보안약점 조치 (경로조작및 자원 삽입)
 *  
 * </pre>
 */

@Controller
public class EgovProxySvcController {

	@Resource(name="egovProxySvcService")
	private EgovProxySvcService egovProxySvcService;

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Autowired
	private DefaultBeanValidator beanValidator;

    /** ID Generation */
    @Resource(name="egovProxySvcIdGnrService")
    private EgovIdGnrService egovProxySvcIdGnrService;


    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService EgovCmmUseService;

	/**
	 * 프록시서비스정보 목록화면 이동
	 * @return String
	 */
	@RequestMapping(value="/utl/sys/pxy/selectProxySvcListView.do")
	public String selectProxySvcListView() throws Exception {
		return "egovframework/com/utl/sys/pxy/EgovProxySvcList";
	}

	/**
	 * 프록시서비스를 관리하기 위해 등록된 프록시정보 목록을 조회한다.
	 * @param proxySvcVO - 프록시서비스 Vo
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name="프록시서비스", order = 2140 ,gid = 90)
	@RequestMapping(value="/utl/sys/pxy/selectProxySvcList.do")
	public String selectProxySvcList(@ModelAttribute("proxySvcVO") ProxySvcVO proxySvcVO,
                                      ModelMap model) throws Exception {

		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(proxySvcVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(proxySvcVO.getPageUnit());
		paginationInfo.setPageSize(proxySvcVO.getPageSize());

		proxySvcVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		proxySvcVO.setLastIndex(paginationInfo.getLastRecordIndex());
		proxySvcVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("proxySvcList", egovProxySvcService.selectProxySvcList(proxySvcVO));

        int totCnt = egovProxySvcService.selectProxySvcListTotCnt(proxySvcVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/utl/sys/pxy/EgovProxySvcList";
	}

	/**
	 * 등록된 프록시서비스의 상세정보를 조회한다.
	 * @param proxyId - String
	 * @param proxySvcVO - 프록시서비스 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/utl/sys/pxy/getProxySvc.do")
	public String selectProxySvc(@RequestParam("proxyId") String proxyId,
			                     @ModelAttribute("proxySvcVO") ProxySvcVO proxySvcVO,
			                      ModelMap model) throws Exception {

		proxySvcVO.setProxyId(proxyId);
		proxySvcVO = egovProxySvcService.selectProxySvc(proxySvcVO);
		model.addAttribute("proxySvc", proxySvcVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/utl/sys/pxy/EgovProxySvcDetail";
	}

	/**
	 * 프록시서비스를 신규로 등록하는 화면으로 이동한다.
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/utl/sys/pxy/addViewProxySvc.do")
	public String insertViewProxySvc(@ModelAttribute("proxySvcVO") ProxySvcVO proxySvcVO,
			                          ModelMap model) throws Exception {

		model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM072"));
		model.addAttribute("proxySvc", proxySvcVO);
		return "egovframework/com/utl/sys/pxy/EgovProxySvcRegist";
	}

	/**
	 * 프록시서비스를 신규로 등록한다.
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/utl/sys/pxy/addProxySvc.do")
	public String insertProxySvc(@ModelAttribute("proxySvcVO") ProxySvcVO proxySvcVO,
			                     @ModelAttribute("proxySvc") ProxySvc proxySvc,
			                      BindingResult bindingResult,
			                      ModelMap model) throws Exception {

		beanValidator.validate(proxySvc, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
    		model.addAttribute("proxySvcVO", proxySvcVO);
			return "egovframework/com/utl/sys/pxy/EgovProxySvcRegist";
		} else {
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	//KISA 보안취약점 조치 (2018-12-10, 이정은)

	        if(!isAuthenticated) {
	            return "egovframework/com/uat/uia/EgovLoginUsr";
	        }
			proxySvc.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
			proxySvc.setProxyId(egovProxySvcIdGnrService.getNextStringId());

			proxySvc.setProxyIp(EgovWebUtil.filePathBlackList(proxySvc.getProxyIp()));
			proxySvc.setSvcIp(EgovWebUtil.filePathBlackList(proxySvc.getSvcIp()));
			model.addAttribute("proxySvc", egovProxySvcService.insertProxySvc(proxySvcVO, proxySvc));
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "egovframework/com/utl/sys/pxy/EgovProxySvcDetail";
		}
	}

	/**
	 * 기 등록된 프록시서비스를 수정하는 화면으로 이동한다.
	 * @param proxyId - String
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/utl/sys/pxy/updtViewProxySvc.do")
	public String updateViewProxySvc(@RequestParam("proxyId") String proxyId,
			                         @ModelAttribute("proxySvcVO") ProxySvcVO proxySvcVO,
			                          ModelMap model) throws Exception {

		proxySvcVO.setProxyId(proxyId);
		model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM072"));
		model.addAttribute("proxySvc", egovProxySvcService.selectProxySvc(proxySvcVO));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/utl/sys/pxy/EgovProxySvcUpdt";
	}

	/**
	 * 기 등록된 프록시서비스를 수정한다.
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/utl/sys/pxy/updtProxySvc.do")
	public String updateProxySvc(@ModelAttribute("proxySvc") ProxySvc proxySvc,
			                     @ModelAttribute("proxySvcVO") ProxySvcVO proxySvcVO,
                                  BindingResult bindingResult,
                                  SessionStatus status,
                                  ModelMap model) throws Exception {

		beanValidator.validate(proxySvc, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("proxySvcVO", proxySvc);
    		return "egovframework/com/utl/sys/pxy/EgovProxySvcUpdt";
    	} else {
    		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		
    		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	//KISA 보안취약점 조치 (2018-12-10, 이정은)

            if(!isAuthenticated) {
                return "egovframework/com/uat/uia/EgovLoginUsr";
            }
    		proxySvc.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
    		
    		proxySvc.setProxyIp(EgovWebUtil.filePathBlackList(proxySvc.getProxyIp()));
			proxySvc.setSvcIp(EgovWebUtil.filePathBlackList(proxySvc.getSvcIp()));
    		egovProxySvcService.updateProxySvc(proxySvcVO, proxySvc);
	   	    status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/utl/sys/pxy/getProxySvc.do";
    	}
	}

	/**
	 * 기 등록된 프록시서비스를 삭제한다.
	 * @param proxySvc - 프록시서비스 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/utl/sys/pxy/removeProxySvc.do")
	public String deleteProxySvc(@RequestParam("proxyId") String proxyId,
			                     @ModelAttribute("proxySvc") ProxySvc proxySvc,
			                      ModelMap model) throws Exception {

		proxySvc.setProxyId(proxyId);
		egovProxySvcService.deleteProxySvc(proxySvc);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/utl/sys/pxy/selectProxySvcList.do";
	}

	/**
	 * 프록시서비스정보 목록화면 이동
	 * @return String
	 */
	@RequestMapping(value="/utl/sys/pxy/selectProxyLogListView.do")
	public String selectProxyLogListView(@ModelAttribute("pmProxyLogVO") ProxyLogVO proxyLogVO,
			                              ModelMap model) throws Exception {

		proxyLogVO.setStrStartDate(EgovStringUtil.addMinusChar(EgovDateUtil.addMonth(EgovDateUtil.getToday(), -1)));
		proxyLogVO.setStrEndDate(EgovStringUtil.addMinusChar(EgovDateUtil.getToday()));

		model.addAttribute("pmProxyLogVO", proxyLogVO);

		return "egovframework/com/utl/sys/pxy/EgovProxyLogList";
	}

	/**
	 * 프록시서비스를 모니터링하기 위해 등록된 프록시로그 목록을 조회한다.
	 * @param proxyLogVO - 프록시로그 Vo
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/utl/sys/pxy/selectProxyLogList.do")
	public String selectProxyLogList(@ModelAttribute("proxyLogVO") ProxyLogVO proxyLogVO,
			                         @ModelAttribute("pmProxyLogVO") ProxyLogVO pmProxyLogVO,
                                      ModelMap model) throws Exception {
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(proxyLogVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(proxyLogVO.getPageUnit());
		paginationInfo.setPageSize(proxyLogVO.getPageSize());

		proxyLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		proxyLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
		proxyLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if(proxyLogVO.getStrStartDate() == null || proxyLogVO.getStrEndDate() == null) {
			proxyLogVO.setStrStartDate(EgovDateUtil.addMonth(EgovDateUtil.getToday(), -1));
			proxyLogVO.setStrEndDate(EgovDateUtil.getToday());
		} else {
			proxyLogVO.setStrStartDate(EgovStringUtil.removeMinusChar(proxyLogVO.getStrStartDate()));
			proxyLogVO.setStrEndDate(EgovStringUtil.removeMinusChar(proxyLogVO.getStrEndDate()));
		}

		model.addAttribute("proxyLogList", egovProxySvcService.selectProxyLogList(proxyLogVO));

        int totCnt = egovProxySvcService.selectProxyLogListTotCnt(proxyLogVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        pmProxyLogVO.setStrStartDate(EgovStringUtil.addMinusChar(proxyLogVO.getStrStartDate()));
        pmProxyLogVO.setStrEndDate(EgovStringUtil.addMinusChar(proxyLogVO.getStrEndDate()));
        model.addAttribute("pmProxyLogVO", pmProxyLogVO);

		return "egovframework/com/utl/sys/pxy/EgovProxyLogList";
	}

    /**
	 * 공통코드 호출
	 * @param comDefaultCodeVO ComDefaultCodeVO
	 * @param codeId String
	 * @return List
	 * @exception Exception
	 */
    public List<?> getCmmCodeDetailList(ComDefaultCodeVO comDefaultCodeVO, String codeId)  throws Exception {
    	comDefaultCodeVO.setCodeId(codeId);
    	return EgovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
    }


}