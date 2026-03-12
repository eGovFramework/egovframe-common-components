/**
 * 개요
 * - 인터넷서비스안내에 대한 controller 클래스를 정의한다.
 *
 *
 * 상세내용
 * - 인터넷서비스안내에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 인터넷서비스안내의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:02
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2010.08.03	lee.m.j		최초 생성
 *  2011.08.26	정진오		IncludedInfo annotation 추가
 *  2023.06.09	김수용		NSR 보안조치 (특수문자 복원 기능 제거)
 *
 *  </pre>
 */

package egovframework.com.uss.ion.isg.web;

import java.util.List;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.isg.service.EgovIntnetSvcGuidanceService;
import egovframework.com.uss.ion.isg.service.IntnetSvcGuidanceVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Controller
public class EgovIntnetSvcGuidanceController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovIntnetSvcGuidanceService")
    private EgovIntnetSvcGuidanceService egovIntnetSvcGuidanceService;

    /** Message ID Generation */
    @Resource(name="egovIntnetSvcGuidanceIdGnrService")
    private EgovIdGnrService egovIntnetSvcGuidanceIdGnrService;

    /**
	 * 인터넷서비스안내 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/uss/ion/isg/selectIntnetSvcGuidanceListView.do")
    public String selectIntnetSvcGuidanceListView() throws Exception {

        return "egovframework/com/uss/ion/isg/EgovIntnetSvcGuidanceList";
    }

	/**
	 * 인터넷서비스안내정보를 관리하기 위해 등록된 인터넷서비스안내 목록을 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="인터넷서비스안내및관리", order = 800 ,gid = 50)
	@RequestMapping("/uss/ion/isg/selectIntnetSvcGuidanceList.do")
	public String selectIntnetSvcGuidanceList(@ModelAttribute("intnetSvcGuidanceVO") IntnetSvcGuidanceVO intnetSvcGuidanceVO,
                                               ModelMap model ) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(intnetSvcGuidanceVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(intnetSvcGuidanceVO.getPageUnit());
		paginationInfo.setPageSize(intnetSvcGuidanceVO.getPageSize());

		intnetSvcGuidanceVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		intnetSvcGuidanceVO.setLastIndex(paginationInfo.getLastRecordIndex());
		intnetSvcGuidanceVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		intnetSvcGuidanceVO.setIntnetSvcGuidanceList(egovIntnetSvcGuidanceService.selectIntnetSvcGuidanceList(intnetSvcGuidanceVO));

		model.addAttribute("intnetSvcGuidanceList", intnetSvcGuidanceVO.getIntnetSvcGuidanceList());

        int totCnt = egovIntnetSvcGuidanceService.selectIntnetSvcGuidanceListTotCnt(intnetSvcGuidanceVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/isg/EgovIntnetSvcGuidanceList";
	}

	/**
	 * 등록된 인터넷서비스안내의 상세정보를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uss/ion/isg/getIntnetSvcGuidance.do")
	public String selectIntnetSvcGuidance(@RequestParam("intnetSvcId") String intnetSvcId,
			                              @ModelAttribute("intnetSvcGuidanceVO") IntnetSvcGuidanceVO intnetSvcGuidanceVO,
			                              ModelMap model) throws Exception {

		intnetSvcGuidanceVO.setIntnetSvcId(intnetSvcId);
		model.addAttribute("intnetSvcGuidanceVO", egovIntnetSvcGuidanceService.selectIntnetSvcGuidance(intnetSvcGuidanceVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/isg/EgovIntnetSvcGuidanceUpdt";
	}

	/**
	 * 인터넷서비스안내정보를 신규 등록을 위해 등록화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uss/ion/isg/addViewIntnetSvcGuidance.do")
    public String insertIntnetSvcGuidanceView(@ModelAttribute("intnetSvcGuidanceVO") IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {

        return "egovframework/com/uss/ion/isg/EgovIntnetSvcGuidanceRegist";
    }

	/**
	 * 인터넷서비스안내정보를 신규로 등록한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uss/ion/isg/addIntnetSvcGuidance.do")
	public String insertIntnetSvcGuidance(@Valid @ModelAttribute("intnetSvcGuidanceVO") IntnetSvcGuidanceVO intnetSvcGuidanceVO,
			                               BindingResult bindingResult,
			                               ModelMap model) throws Exception {

    	if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/ion/isg/EgovIntnetSvcGuidanceRegist";
		} else {
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	intnetSvcGuidanceVO.setIntnetSvcId(egovIntnetSvcGuidanceIdGnrService.getNextStringId());
	    	intnetSvcGuidanceVO.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

	    	model.addAttribute("intnetSvcGuidanceVO", egovIntnetSvcGuidanceService.insertIntnetSvcGuidance(intnetSvcGuidanceVO));
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

//			return "egovframework/com/uss/ion/isg/EgovIntnetSvcGuidanceUpdt";
			return "forward:/uss/ion/isg/selectIntnetSvcGuidanceList.do";
		}
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 수정한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uss/ion/isg/updtIntnetSvcGuidance.do")
	public String updateIntnetSvcGuidance(@Valid @ModelAttribute("intnetSvcGuidanceVO") IntnetSvcGuidanceVO intnetSvcGuidanceVO,
			                              BindingResult bindingResult,
			                              ModelMap model) throws Exception {

    	if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/ion/isg/EgovIntnetSvcGuidanceUpdt";
		} else {
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	intnetSvcGuidanceVO.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

			egovIntnetSvcGuidanceService.updateIntnetSvcGuidance(intnetSvcGuidanceVO);
//			return "forward:/uss/ion/isg/getIntnetSvcGuidance.do";
			return "forward:/uss/ion/isg/selectIntnetSvcGuidanceList.do";
		}
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 삭제한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uss/ion/isg/removeIntnetSvcGuidance.do")
	public String deleteIntnetSvcGuidance(@ModelAttribute("intnetSvcGuidanceVO") IntnetSvcGuidanceVO intnetSvcGuidanceVO,
			                               ModelMap model) throws Exception {

    	egovIntnetSvcGuidanceService.deleteIntnetSvcGuidance(intnetSvcGuidanceVO);

    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/isg/selectIntnetSvcGuidanceList.do";
	}


	/**
	 * 인터넷서비스안내정보 적용결과를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping("/uss/ion/isg/selectIntnetSvcGuidanceResultList.do")
	public String selectIntnetSvcGuidanceResult(@ModelAttribute("intnetSvcGuidanceVO") IntnetSvcGuidanceVO intnetSvcGuidanceVO,
                                               ModelMap model ) throws Exception {

		List<IntnetSvcGuidanceVO> intnetSvcGuidanceList = egovIntnetSvcGuidanceService.selectIntnetSvcGuidanceResult(intnetSvcGuidanceVO);

		for (IntnetSvcGuidanceVO element : intnetSvcGuidanceList) {
			element.setIntnetSvcDc(element.getIntnetSvcDc());
		}

		model.addAttribute("intnetSvcGuidanceList", intnetSvcGuidanceList);

		return "egovframework/com/uss/ion/isg/EgovIntnetSvcGuidanceView";
	}
}
