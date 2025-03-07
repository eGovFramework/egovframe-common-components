package egovframework.com.sym.tbm.tbr.web;
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
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.tbm.tbr.service.EgovTroblReqstService;
import egovframework.com.sym.tbm.tbr.service.TroblReqst;
import egovframework.com.sym.tbm.tbr.service.TroblReqstVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 개요
 * -장애신청정보에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 장애신청정보에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 * - 장애신청정보의 조회기능은 목록조회, 상세조회로 구분된다.
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
public class EgovTroblReqstController {




	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovTroblReqstService")
	private EgovTroblReqstService egovTroblReqstService;

    /** ID Generation */
    @Resource(name="egovTroblIdGnrService")
    private EgovIdGnrService egovTroblIdGnrService;

    @Autowired
	private DefaultBeanValidator beanValidator;

    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService EgovCmmUseService;

	/**
	 * 장애요청관리 목록화면으로 이동
	 * @return String
	 */
    @RequestMapping(value="/sym/tbm/tbr/selectTroblReqstListView.do")
	public String selectTroblReqstListView() throws Exception {
		return "egovframework/com/sym/tbm/tbr/EgovTroblReqstList";
	}

	/**
	 * 장애요청을 관리하기 위해 등록된 장애요청목록을 조회한다.
	 * @param troblReqstVO - 장애신청관리 Vo
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="장애신청관리", order = 1180 ,gid = 60)
    @RequestMapping(value="/sym/tbm/tbr/selectTroblReqstList.do")
	public String selectTroblReqstList(@ModelAttribute("troblReqstVO") TroblReqstVO troblReqstVO,
                                        ModelMap model) throws Exception {

		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(troblReqstVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(troblReqstVO.getPageUnit());
		paginationInfo.setPageSize(troblReqstVO.getPageSize());

		troblReqstVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		troblReqstVO.setLastIndex(paginationInfo.getLastRecordIndex());
		troblReqstVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		if(troblReqstVO.getStrTroblKnd() == null) troblReqstVO.setStrTroblKnd("00");
		if(troblReqstVO.getStrProcessSttus() == null) troblReqstVO.setStrProcessSttus("00");

		troblReqstVO.setTroblReqstList(egovTroblReqstService.selectTroblReqstList(troblReqstVO));

		model.addAttribute("troblReqstList", troblReqstVO.getTroblReqstList());

        int totCnt = egovTroblReqstService.selectTroblReqstListTotCnt(troblReqstVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("cmmCodeDetailList1", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM065"));
        model.addAttribute("cmmCodeDetailList2", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM068"));
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/sym/tbm/tbr/EgovTroblReqstList";
	}

	/**
	 * 등록된 장애요청의 상세정보를 조회한다.
	 * @param troblReqstVO - 장애신청관리 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbr/getTroblReqst.do")
	public String selectTroblReqst(@RequestParam("troblId") String troblId,
			                       @ModelAttribute("troblReqstVO") TroblReqstVO troblReqstVO,
					                Model model) throws Exception {

    	troblReqstVO.setTroblId(troblId);
		model.addAttribute("troblReqst", egovTroblReqstService.selectTroblReqst(troblReqstVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/sym/tbm/tbr/EgovTroblReqstDetail";
	}

	/**
	 * 장애요청정보  등록 화면으로 이동한다.
	 * @param troblReqstVO - 장애신청관리 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbr/addViewTroblReqst.do")
	public String insertViewTroblReqst(@ModelAttribute("troblReqstVO") TroblReqstVO troblReqstVO,
                                        ModelMap model) throws Exception {

    	model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM065"));
		model.addAttribute("troblReqst", troblReqstVO);
		return "egovframework/com/sym/tbm/tbr/EgovTroblReqstRegist";
	}

	/**
	 * 장애요청정보를 신규로 등록한다.
	 * @param troblReqst - 장애신청관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbr/addTroblReqst.do")
	public String insertTroblReqst(@ModelAttribute("troblReqstVO") TroblReqstVO troblReqstVO,
			                       @ModelAttribute("troblReqst") TroblReqst troblReqst,
				                    BindingResult bindingResult,
			                        ModelMap model) throws Exception {

		beanValidator.validate(troblReqst, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
    		model.addAttribute("troblReqstVO", troblReqstVO);
			return "egovframework/com/sym/tbm/tbr/EgovTroblReqstRegist";
		} else {
	   	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	   	    troblReqst.setTroblOccrrncTime(EgovStringUtil.removeMinusChar(troblReqst.getTroblOccrrncTime()));
	   	    troblReqst.setTroblRequstTime(EgovStringUtil.removeMinusChar(troblReqst.getTroblRequstTime()));
	   	    troblReqst.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
	   	    troblReqst.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
	   	    troblReqst.setProcessSttus("A");
	   	    troblReqst.setTroblId(egovTroblIdGnrService.getNextStringId());

	   	    model.addAttribute("troblReqst", egovTroblReqstService.insertTroblReqst(troblReqst, troblReqstVO));
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "egovframework/com/sym/tbm/tbr/EgovTroblReqstDetail";
		}
	}

	/**
	 * 장애요청정보 수정 화면으로 이동한다.
	 * @param troblReqstVO - 장애신청관리 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbr/updtViewTroblReqst.do")
    public String updateViewTroblReqst(@RequestParam("troblId") String troblId,
    		                           @ModelAttribute("troblReqstVO") TroblReqstVO troblReqstVO,
                                        Model model) throws Exception {

    	troblReqstVO.setTroblId(troblId);
    	model.addAttribute("troblReqst", egovTroblReqstService.selectTroblReqst(troblReqstVO));
    	model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM065"));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/sym/tbm/tbr/EgovTroblReqstUpdt";
	}

	/**
	 * 기 등록된 장애요청정보를 수정한다.
	 * @param troblReqst - 장애신청관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbr/updtTroblReqst.do")
	public String updateTroblReqst(@ModelAttribute("troblReqst") TroblReqst troblReqst,
			                        BindingResult bindingResult,
                                    SessionStatus status,
                                    ModelMap model) throws Exception {

    	beanValidator.validate(troblReqst, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("troblReqstVO", troblReqst);
    		return "egovframework/com/sym/tbm/EgovTroblReqstUpdt";
    	} else {
    		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	   	    troblReqst.setTroblOccrrncTime(EgovStringUtil.removeMinusChar(troblReqst.getTroblOccrrncTime()));
	   	    troblReqst.setTroblRequstTime(EgovStringUtil.removeMinusChar(troblReqst.getTroblRequstTime()));
    		troblReqst.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
    		egovTroblReqstService.updateTroblReqst(troblReqst);
	   	    status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/sym/tbm/tbr/getTroblReqst.do";
    	}
	}

	/**
	 * 기 등록된 장애요청정보를 삭제한다.
	 * @param troblReqst - 장애신청관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbr/removeTroblReqst.do")
	public String deleteTroblReqst(@RequestParam("troblId") String troblId,
			                       @ModelAttribute("troblReqst") TroblReqst troblReqst,
	                                ModelMap model) throws Exception {

    	troblReqst.setTroblId(troblId);
    	egovTroblReqstService.deleteTroblReqst(troblReqst);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sym/tbm/tbr/selectTroblReqstList.do";
	}

	/**
	 * 장애처리를 요청한다.
	 * @param troblReqst - 장애신청관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbr/requstTroblReqst.do")
	public String requstTroblReqst(@RequestParam("troblId") String troblId,
		                           @ModelAttribute("troblReqst") TroblReqst troblReqst,
			                        SessionStatus status,
	                                ModelMap model) throws Exception {

    	troblReqst.setTroblId(troblId);
    	troblReqst.setProcessSttus("R");
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		troblReqst.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
		egovTroblReqstService.requstTroblReqst(troblReqst);
   	    status.setComplete();
		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		return "forward:/sym/tbm/tbr/getTroblReqst.do";
	}

	/**
	 * 장애처리취소를 요청한다.
	 * @param troblReqst - 장애신청관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/sym/tbm/tbr/requstTroblReqstCancl.do")
	public String requstTroblReqstCancl(@RequestParam("troblId") String troblId,
                                        @ModelAttribute("troblReqst") TroblReqst troblReqst,
			                             SessionStatus status,
	                                     ModelMap model) throws Exception {

    	troblReqst.setTroblId(troblId);
    	troblReqst.setProcessSttus("A");
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		troblReqst.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
		egovTroblReqstService.requstTroblReqst(troblReqst);
   	    status.setComplete();
		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		return "forward:/sym/tbm/tbr/getTroblReqst.do";
	}

    /**
	 * 공통코드 호출
	 * @param comDefaultCodeVO ComDefaultCodeVO
	 * @param codeId String
	 * @return List
	 * @exception Exception
	 */
    public List<CmmnDetailCode> getCmmCodeDetailList(ComDefaultCodeVO comDefaultCodeVO, String codeId)  throws Exception {
    	comDefaultCodeVO.setCodeId(codeId);
    	return EgovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
    }
}