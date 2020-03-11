package egovframework.com.sym.tbm.tbp.web;
import java.util.List;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.tbm.tbp.service.EgovTroblProcessService;
import egovframework.com.sym.tbm.tbp.service.TroblProcess;
import egovframework.com.sym.tbm.tbp.service.TroblProcessVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

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
 * -장애관리정보에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 장애관리정보에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 * - 장애관리정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:35
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
public class EgovTroblProcessController {




	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovTroblProcessService")
	private EgovTroblProcessService egovTroblProcessService;

    @Autowired
	private DefaultBeanValidator beanValidator;

    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService EgovCmmUseService;

	/**
	 * 장애처리관리 목록화면으로 이동
	 * @return String
	 */
    @RequestMapping(value="/sym/tbm/tbp/selectTroblProcessListView.do")
	public String selectTroblProcessListView() throws Exception {
		return "egovframework/com/sym/tbm/tbp/EgovTroblProcessList";
	}

	/**
	 * 장애처리정보를 관리하기 위해 대상 장애처리목록을 조회한다.
	 * @param troblManageVO - 장애처리 Vo
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="장애처리결과관리", order = 1190 ,gid = 60)
    @RequestMapping(value="/sym/tbm/tbp/selectTroblProcessList.do")
	public String selectTroblProcessList(@ModelAttribute("troblProcessVO") TroblProcessVO troblProcessVO,
			                              ModelMap model) throws Exception {

		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(troblProcessVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(troblProcessVO.getPageUnit());
		paginationInfo.setPageSize(troblProcessVO.getPageSize());

		troblProcessVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		troblProcessVO.setLastIndex(paginationInfo.getLastRecordIndex());
		troblProcessVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if(troblProcessVO.getStrTroblKnd() == null) troblProcessVO.setStrTroblKnd("00");
		if(troblProcessVO.getStrProcessSttus() == null) troblProcessVO.setStrProcessSttus("00");

		troblProcessVO.setTroblProcessList(egovTroblProcessService.selectTroblProcessList(troblProcessVO));

		model.addAttribute("troblProcessList", troblProcessVO.getTroblProcessList());

        int totCnt = egovTroblProcessService.selectTroblProcessListTotCnt(troblProcessVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("cmmCodeDetailList1", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM065"));
        model.addAttribute("cmmCodeDetailList2", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM068"));
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/sym/tbm/tbp/EgovTroblProcessList";
	}

	/**
	 * 등록된 장애처리의 상세정보를 조회한다.
	 * @param troblManageVO - 장애관리 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbp/getTroblProcess.do")
	public String selectTroblProcess(@RequestParam("troblId") String troblId,
			                         @ModelAttribute("troblProcessVO") TroblProcessVO troblProcessVO,
                                      ModelMap model) throws Exception {

    	troblProcessVO.setTroblId(troblId);
    	model.addAttribute("troblProcess", egovTroblProcessService.selectTroblProcess(troblProcessVO));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/sym/tbm/tbp/EgovTroblProcessRegist";
	}

	/**
	 * 장애처리정보를 신규로 등록한다.
	 * @param troblManage - 장애관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbp/addTroblProcess.do")
	public String insertTroblProcess(@ModelAttribute("troblProcess") TroblProcess troblProcess,
                                      BindingResult bindingResult,
                                      SessionStatus status,
                                      ModelMap model) throws Exception {

		beanValidator.validate(troblProcess, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
    		model.addAttribute("troblProcessVO", troblProcess);
    		return "egovframework/com/sym/tbm/tbp/EgovTroblProcess";
    	} else {
    		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		troblProcess.setTroblProcessTime(EgovStringUtil.removeMinusChar(troblProcess.getTroblProcessTime()));
    		troblProcess.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
    		troblProcess.setProcessSttus("C");
    		egovTroblProcessService.insertTroblProcess(troblProcess);
	   	    status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/sym/tbm/tbp/getTroblProcess.do";
    	}
	}

	/**
	 * 기 등록된 장애처리정보를 삭제한다.
	 * @param troblManage - 장애관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbp/removeTroblProcess.do")
	public String deleteTroblProcess(@RequestParam("troblId") String troblId,
			                         @ModelAttribute("troblProcess") TroblProcess troblProcess,
                                      ModelMap model) throws Exception {

    	troblProcess.setTroblId(troblId);
    	troblProcess.setProcessSttus("R");
		egovTroblProcessService.deleteTroblProcess(troblProcess);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sym/tbm/tbp/getTroblProcess.do";
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