package egovframework.com.sym.sym.srv.web;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.sym.srv.service.EgovServerService;
import egovframework.com.sym.sym.srv.service.Server;
import egovframework.com.sym.sym.srv.service.ServerEqpmn;
import egovframework.com.sym.sym.srv.service.ServerEqpmnRelate;
import egovframework.com.sym.sym.srv.service.ServerEqpmnRelateVO;
import egovframework.com.sym.sym.srv.service.ServerEqpmnVO;
import egovframework.com.sym.sym.srv.service.ServerVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 개요
 * - 서버정보관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 서버정보관리에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 * - 서버정보관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:31
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.28   이문준     최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 * </pre>
 */

@Controller
public class EgovServerController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovServerService")
    private EgovServerService egovServerService;

    /** ID Generation */
    @Resource(name="egovServerEqpmnIdGnrService")
    private EgovIdGnrService egovServerEqpmnIdGnrService;

    /** ID Generation */
    @Resource(name="egovServerIdGnrService")
    private EgovIdGnrService egovServerIdGnrService;

    @Autowired
	private DefaultBeanValidator beanValidator;

    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService EgovCmmUseService;

	/**
	 * 서버장비관리 목록화면으로 이동
	 * @return String
	 */
    @RequestMapping(value="/sym/sym/srv/selectServerEqpmnListView.do")
	public String selectServerEqpmnListView() throws Exception {
		return "egovframework/com/sym/sym/srv/EgovServerEqpmnList";
	}

	/**
	 * 서버장비를 관리하기 위해 등록된 서버장비목록을 조회한다.
	 * @param serverEqpmnVO - 서버장비 Vo
	 * @return String - 리턴 Url
	 *
	 * @param serverEqpmnVO
	 */
    @IncludedInfo(name="서버정보관리", order = 1170 ,gid = 60)
    @RequestMapping(value="/sym/sym/srv/selectServerEqpmnList.do")
	public String selectServerEqpmnList(@ModelAttribute("serverEqpmnVO") ServerEqpmnVO serverEqpmnVO,
			                             ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(serverEqpmnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(serverEqpmnVO.getPageUnit());
		paginationInfo.setPageSize(serverEqpmnVO.getPageSize());

		serverEqpmnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		serverEqpmnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		serverEqpmnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		serverEqpmnVO.setServerEqpmnList(egovServerService.selectServerEqpmnList(serverEqpmnVO));

		model.addAttribute("serverEqpmnList", serverEqpmnVO.getServerEqpmnList());

        int totCnt = egovServerService.selectServerEqpmnListTotCnt(serverEqpmnVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/sym/sym/srv/EgovServerEqpmnList";
	}

	/**
	 * 등록된 서버장비의 상세정보를 조회한다.
	 * @param serverEqpmnVO - 서버장비 Vo
	 * @return String - 리턴 Url
	 *
	 * @param serverEqpmnVO
	 */
    @RequestMapping(value="/sym/sym/srv/getServerEqpmn.do")
	public String selectServerEqpmn(@RequestParam("serverEqpmnId") String serverEqpmnId,
			                        @ModelAttribute("serverEqpmnVO") ServerEqpmnVO serverEqpmnVO,
					                 Model model) throws Exception {
		serverEqpmnVO.setServerEqpmnId(serverEqpmnId);
		model.addAttribute("serverEqpmn", egovServerService.selectServerEqpmn(serverEqpmnVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/sym/sym/srv/EgovServerEqpmnDetail";
	}

	/**
	 * 서버장비정보 등록 화면으로 이동한다.
	 * @param serverEqpmnVO - 서버장비 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/sym/srv/addViewServerEqpmn.do")
	public String insertViewServerEqpmn(@ModelAttribute("serverEqpmnVO") ServerEqpmnVO serverEqpmnVO,
			                             ModelMap model) throws Exception {

    	model.addAttribute("serverEqpmn", serverEqpmnVO);
    	return "egovframework/com/sym/sym/srv/EgovServerEqpmnRegist";
	}

	/**
	 * 서버장비정보를 신규로 등록한다.
	 * @param serverEqpmn - 서버장비 model
	 * @return String - 리턴 Url
	 *
	 * @param serverEqpmn
	 */
    @RequestMapping(value="/sym/sym/srv/addServerEqpmn.do")
	public String insertServerEqpmn(@ModelAttribute("serverEqpmnVO") ServerEqpmnVO serverEqpmnVO,
			                        @ModelAttribute("serverEqpmn") ServerEqpmn serverEqpmn,
			                         BindingResult bindingResult,
				                     ModelMap model) throws Exception {

		beanValidator.validate(serverEqpmn, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
    		model.addAttribute("serverEqpmnVO", serverEqpmnVO);
    		return "egovframework/com/sym/sym/srv/EgovServerEqpmnRegist";
		} else {
	   	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	   	    serverEqpmn.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
	   	    serverEqpmn.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
	   	    serverEqpmn.setServerEqpmnId(egovServerEqpmnIdGnrService.getNextStringId());
	   	    model.addAttribute("serverEqpmn", egovServerService.insertServerEqpmn(serverEqpmn, serverEqpmnVO));
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "egovframework/com/sym/sym/srv/EgovServerEqpmnDetail";
		}
	}

	/**
	 * 서버장비정보 수정 화면으로 이동한다.
	 * @param serverEqpmnVO - 서버장비 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/sym/srv/updtViewServerEqpmn.do")
	public String updateViewServerEqpmn(@RequestParam("serverEqpmnId") String serverEqpmnId,
			                            @ModelAttribute("serverEqpmnVO") ServerEqpmnVO serverEqpmnVO,
			                             ModelMap model) throws Exception {

    	serverEqpmnVO.setServerEqpmnId(serverEqpmnId);
		model.addAttribute("serverEqpmn", egovServerService.selectServerEqpmn(serverEqpmnVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	return "egovframework/com/sym/sym/srv/EgovServerEqpmnUpdt";
	}

	/**
	 * 기 등록된 서버장비정보를 수정한다.
	 * @param serverEqpmn - 서버장비 model
	 * @return String - 리턴 Url
	 *
	 * @param serverEqpmn
	 */
    @RequestMapping(value="/sym/sym/srv/updtServerEqpmn.do")
	public String updateServerEqpmn(@ModelAttribute("serverEqpmn") ServerEqpmn serverEqpmn,
                                     BindingResult bindingResult,
                                     SessionStatus status,
                                     ModelMap model) throws Exception {

		beanValidator.validate(serverEqpmn, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
    		model.addAttribute("serverEqpmnVO", serverEqpmn);
    		return "egovframework/com/sym/sym/srv/EgovServerEqpmnUpdt";
		} else {
	   	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	   	    serverEqpmn.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
	   	    egovServerService.updateServerEqpmn(serverEqpmn);
	   	    status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/sym/sym/srv/getServerEqpmn.do";
		}
	}

	/**
	 * 기 등록된 서버장비정보를 삭제한다.
	 * @param serverEqpmn - 서버장비 model
	 * @return String - 리턴 Url
	 *
	 * @param serverEqpmn
	 */
    @RequestMapping(value="/sym/sym/srv/removeServerEqpmn.do")
	public String deleteServerEqpmn(@RequestParam("serverEqpmnId") String serverEqpmnId,
                                    @ModelAttribute("serverEqpmn") ServerEqpmn serverEqpmn,
                                    ModelMap model) throws Exception {
		serverEqpmn.setServerEqpmnId(serverEqpmnId);
		egovServerService.deleteServerEqpmn(serverEqpmn);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sym/sym/srv/selectServerEqpmnList.do";
	}

	/**
	 * 서버정보관리 목록화면으로 이동
	 * @return String
	 */
    @RequestMapping(value="/sym/sym/srv/selectServerListView.do")
	public String selectServerListView() throws Exception {
		return "egovframework/com/sym/sym/srv/EgovServerList";
	}

	/**
	 * 서버정보를 관리하기 위해 등록된 서버목록을 조회한다.
	 * @param serverVO - 서버 Vo
	 * @return String - 리턴 Url
	 *
	 * @param serverVO
	 */
    @IncludedInfo(name="서버(S/W)목록", order = 1171 ,gid = 60)
    @RequestMapping(value="/sym/sym/srv/selectServerList.do")
	public String selectServerList(@ModelAttribute("serverVO") ServerVO serverVO,
			                        ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(serverVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(serverVO.getPageUnit());
		paginationInfo.setPageSize(serverVO.getPageSize());

		serverVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		serverVO.setLastIndex(paginationInfo.getLastRecordIndex());
		serverVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		serverVO.setServerList(egovServerService.selectServerList(serverVO));

		model.addAttribute("serverList", serverVO.getServerList());

        int totCnt = egovServerService.selectServerListTotCnt(serverVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/sym/sym/srv/EgovServerList";
	}

	/**
	 * 등록된 서버의 상세정보를 조회한다.
	 * @param serverVO - 서버 Vo
	 * @return String - 리턴 Url
	 *
	 * @param serverVO
	 */
    @RequestMapping(value="/sym/sym/srv/getServer.do")
	public String selectServer(@RequestParam("serverId") String serverId,
			                   @ModelAttribute("serverVO") ServerVO serverVO,
			                    Model model) throws Exception {

    	serverVO.setServerId(serverId);
    	model.addAttribute("server", egovServerService.selectServer(serverVO));
		model.addAttribute("serverEqpmnRelateDetailList", egovServerService.selectServerEqpmnRelateDetail(serverVO));
		model.addAttribute("serverEqpmnRelateDetailCount", egovServerService.selectServerEqpmnRelateDetailTotCnt(serverVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/sym/sym/srv/EgovServerDetail";
	}

	/**
	 * 서버정보 등록 화면으로 이동한다.
	 * @param serverVO - 서버 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/sym/srv/addViewServer.do")
	public String insertViewServer(@ModelAttribute("serverVO") ServerVO serverVO,
			                        ModelMap model) throws Exception {

    	model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM064"));
    	model.addAttribute("server", serverVO);
    	return "egovframework/com/sym/sym/srv/EgovServerRegist";
	}

	/**
	 * 서버정보를 신규로 등록한다.
	 * @param server - 서버 model
	 * @return String - 리턴 Url
	 *
	 * @param server
	 */
    @RequestMapping(value="/sym/sym/srv/addServer.do")
	public String insertServer(@ModelAttribute("serverVO") ServerVO serverVO,
			                   @ModelAttribute("server") Server server,
			                    BindingResult bindingResult,
			                    ModelMap model) throws Exception {

    	beanValidator.validate(server, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("serverVO", serverVO);
    		return "egovframework/com/sym/sym/srv/EgovServerRegist";
    	} else {
    		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		server.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
    		server.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
    		server.setServerId(egovServerIdGnrService.getNextStringId());
	   	    model.addAttribute("server", egovServerService.insertServer(server, serverVO));
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "egovframework/com/sym/sym/srv/EgovServerDetail";
    	}
	}

	/**
	 * 서버정보 수정 화면으로 이동한다.
	 * @param serverVO - 서버 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/sym/srv/updtViewServer.do")
	public String updateViewServer(@RequestParam("serverId") String serverId,
			                       @ModelAttribute("serverVO") ServerVO serverVO,
			                        ModelMap model) throws Exception {

    	serverVO.setServerId(serverId);
		model.addAttribute("server", egovServerService.selectServer(serverVO));
		model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM064"));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	return "egovframework/com/sym/sym/srv/EgovServerUpdt";
	}

	/**
	 * 기 등록된 서버정보를 수정한다.
	 * @param server - 서버 model
	 * @return String - 리턴 Url
	 *
	 * @param server
	 */
    @RequestMapping(value="/sym/sym/srv/updtServer.do")
	public String updateServer(@ModelAttribute("server") Server server,
			                    BindingResult bindingResult,
                                SessionStatus status,
                                ModelMap model) throws Exception {

		beanValidator.validate(server, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
    		model.addAttribute("serverVO", server);
    		return "egovframework/com/sym/sym/srv/EgovServerUpdt";
		} else {
	   	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	   	    server.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
	   	    egovServerService.updateServer(server);
	   	    status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/sym/sym/srv/getServer.do";
		}
	}

	/**
	 * 기 등록된 서버정보를 삭제한다.
	 * @param server - 서버 model
	 * @return String - 리턴 Url
	 *
	 * @param server
	 */
    @RequestMapping(value="/sym/sym/srv/removeServer.do")
	public String deleteServer(@RequestParam("serverId") String serverId,
                               @ModelAttribute("server") Server server,
                                ModelMap model) throws Exception {

		server.setServerId(serverId);
		egovServerService.deleteServer(server);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sym/sym/srv/selectServerList.do";
	}

	/**
	 * 서버장비관계정보를 관리하기 위해 대상 서버장비목록을 조회한다.
	 * @param serverEqpmnRelateVO - 서버장비관계 Vo
	 * @return String - 리턴 Url
	 *
	 * @param serverEqpmnRelateVO
	 */
    @RequestMapping(value="/sym/sym/srv/selectServerEqpmnRelateList.do")
	public String selectServerEqpmnRelateList(@RequestParam("strServerId") String strServerId,
			                                  @ModelAttribute("serverVO") ServerVO serverVO,
			                                  @ModelAttribute("serverEqpmnRelateVO") ServerEqpmnRelateVO serverEqpmnRelateVO,
			                                   ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(serverEqpmnRelateVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(serverEqpmnRelateVO.getPageUnit());
		paginationInfo.setPageSize(serverEqpmnRelateVO.getPageSize());

		serverEqpmnRelateVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		serverEqpmnRelateVO.setLastIndex(paginationInfo.getLastRecordIndex());
		serverEqpmnRelateVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		serverEqpmnRelateVO.setServerId(strServerId);
		serverVO.setServerId(strServerId);

		serverEqpmnRelateVO.setServerEqpmnRelateList(egovServerService.selectServerEqpmnRelateList(serverEqpmnRelateVO));

		model.addAttribute("serverEqpmnRelateList", serverEqpmnRelateVO.getServerEqpmnRelateList());
		model.addAttribute("server", egovServerService.selectServer(serverVO));

        int totCnt = egovServerService.selectServerEqpmnRelateListTotCnt(serverEqpmnRelateVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/sym/sym/srv/EgovServerEqpmnRelateRegist";
	}

	/**
	 * 서버장비관계정보를 등록 또는 삭제처리한다.
	 * @param serverEqpmnRelate - 서버장비관계 model
	 * @return String - 리턴 Url
	 *
	 * @param serverEqpmnRelate
	 */
    @RequestMapping(value="/sym/sym/srv/saveServerEqpmnRelate.do")
	public String saveServerEqpmnRelate(@RequestParam("serverId") String serverId,
                                        @RequestParam("serverEqpmnIds") String serverEqpmnIds,
                                        @RequestParam("regYns") String regYns,
                                        @ModelAttribute("serverEqpmnRelate") ServerEqpmnRelate serverEqpmnRelate,
                                         SessionStatus status,
   			                             ModelMap model) throws Exception {


    	String [] strServerEqpmnIds = serverEqpmnIds.split(";");
    	String [] strRegYns = regYns.split(";");

    	serverEqpmnRelate.setServerId(serverId);

    	for(int i=0; i<strServerEqpmnIds.length;i++) {
    		serverEqpmnRelate.setServerId(serverId);
    		serverEqpmnRelate.setServerEqpmnId(strServerEqpmnIds[i]);
    		if(strRegYns[i].equals("Y"))
    			egovServerService.insertServerEqpmnRelate(serverEqpmnRelate);
    		else
    			egovServerService.deleteServerEqpmnRelate(serverEqpmnRelate);
    	}

        status.setComplete();
        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
		return "forward:/sym/sym/srv/selectServerEqpmnRelateList.do";
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