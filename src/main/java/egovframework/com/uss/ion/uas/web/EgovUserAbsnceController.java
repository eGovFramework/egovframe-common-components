/**
 * 개요
 * - 사용자부재에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 사용자부재에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 사용자부재의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:09:35
 *  <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.8.03  이문준          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

package egovframework.com.uss.ion.uas.web;

import java.util.List;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.uas.service.EgovUserAbsnceService;
import egovframework.com.uss.ion.uas.service.UserAbsnceVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Controller
public class EgovUserAbsnceController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovUserAbsnceService")
    private EgovUserAbsnceService egovUserAbsnceService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 사용자부재정보를 관리하기 위해 등록된 사용자부재 목록을 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name="사용자부재관리", order = 790 ,gid = 50)
    @RequestMapping("/uss/ion/uas/selectUserAbsnceList.do")
	public String selectUserAbsnceList(@ModelAttribute("userAbsnceVO") UserAbsnceVO userAbsnceVO,
			                            ModelMap model) throws Exception {

		/** EgovPropertyService.SiteList */
		userAbsnceVO.setPageUnit(propertiesService.getInt("pageUnit"));
		userAbsnceVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userAbsnceVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userAbsnceVO.getPageUnit());
		paginationInfo.setPageSize(userAbsnceVO.getPageSize());

		userAbsnceVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userAbsnceVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userAbsnceVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// selAbsnceAt이 없으면 기본값 "A" 설정
		if (userAbsnceVO.getSelAbsnceAt() == null || "".equals(userAbsnceVO.getSelAbsnceAt())) {
			userAbsnceVO.setSelAbsnceAt("A");
		}

		List<UserAbsnceVO> resultList = egovUserAbsnceService.selectUserAbsnceList(userAbsnceVO);
		model.addAttribute("resultList", resultList);

		int totCnt = egovUserAbsnceService.selectUserAbsnceListTotCnt(userAbsnceVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/uas/EgovUserAbsnceList";
	}

	/**
	 * 등록된 사용자부재 상세정보를 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping("/uss/ion/uas/getUserAbsnce.do")
	public String selectUserAbsnce(@RequestParam("userId") String userId,
			                       @ModelAttribute("userAbsnceVO") UserAbsnceVO userAbsnceVO,
			                       ModelMap model) throws Exception {

		userAbsnceVO.setUserId(userId);
		UserAbsnceVO resultUserAbsnceVO = egovUserAbsnceService.selectUserAbsnce(userAbsnceVO);
		model.addAttribute("userAbsnceVO", resultUserAbsnceVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if(resultUserAbsnceVO.getRegYn().equals("N")) {
			return "egovframework/com/uss/ion/uas/EgovUserAbsnceRegist";
		} else {
			return "egovframework/com/uss/ion/uas/EgovUserAbsnceUpdt";
		}
	}

	/**
	 * 사용자부재정보를 신규로 등록한다.
	 * @param userAbsnce - 사용자부재 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping("/uss/ion/uas/addViewUserAbsnce.do")
	public String insertUserAbsnceView(@RequestParam("userId") String userId,
			                           @ModelAttribute("userAbsnceVO") UserAbsnceVO userAbsnceVO,
			                            ModelMap model) throws Exception {
    	userAbsnceVO.setUserId(userId);
    	UserAbsnceVO resultUserAbsnceVO = egovUserAbsnceService.selectUserAbsnce(userAbsnceVO);
    	model.addAttribute("userAbsnceVO", resultUserAbsnceVO);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/uas/EgovUserAbsnceRegist";
	}

	/**
	 * 사용자부재정보를 신규로 등록한다.
	 * @param userAbsnce - 사용자부재 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping("/uss/ion/uas/addUserAbsnce.do")
	public String insertUserAbsnce(@Valid @ModelAttribute("userAbsnceVO") UserAbsnceVO userAbsnceVO,
		                            BindingResult bindingResult,
			                        ModelMap model) throws Exception {

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("userAbsnceVO", userAbsnceVO);
			return "egovframework/com/uss/ion/uas/EgovUserAbsnceRegist";
		}

	   	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	   	userAbsnceVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

	   	UserAbsnceVO resultUserAbsnceVO = egovUserAbsnceService.insertUserAbsnce(userAbsnceVO);
		model.addAttribute("userAbsnceVO", resultUserAbsnceVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

		return "forward:/uss/ion/uas/selectUserAbsnceList.do";
	}

	/**
	 * 기 등록된 사용자부재정보를 수정한다.
	 * @param userAbsnce - 사용자부재 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping("/uss/ion/uas/updtUserAbsnce.do")
	public String updateUserAbsnce(@Valid @ModelAttribute("userAbsnceVO") UserAbsnceVO userAbsnceVO,
			                        BindingResult bindingResult,
			                        ModelMap model) throws Exception {

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("userAbsnceVO", userAbsnceVO);
			return "egovframework/com/uss/ion/uas/EgovUserAbsnceUpdt";
		}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	    userAbsnceVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

    	egovUserAbsnceService.updateUserAbsnce(userAbsnceVO);
		return "forward:/uss/ion/uas/selectUserAbsnceList.do";
	}

	/**
	 * 기 등록된 사용자부재정보를 삭제한다.
	 * @param userAbsnce - 사용자부재 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping("/uss/ion/uas/removeUserAbsnce.do")
	public String deleteUserAbsnce(@ModelAttribute("userAbsnceVO") UserAbsnceVO userAbsnceVO,
                                    ModelMap model) throws Exception {

		egovUserAbsnceService.deleteUserAbsnce(userAbsnceVO);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/uas/selectUserAbsnceList.do";
	}

	/**
	 * 기 등록된 사용자부재정보를 삭제한다.
	 * @param userAbsnce - 사용자부재 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping("/uss/ion/uas/removeUserAbsnceList.do")
	public String deleteUserAbsnceList(@RequestParam("userIds") String userIds ,
			                           @ModelAttribute("userAbsnceVO") UserAbsnceVO userAbsnceVO,
			                           ModelMap model) throws Exception {
    	//2026.03.23 kisa 보안점검 대응 조치
	    if (ObjectUtils.isEmpty(userIds)) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
	        return "forward:/uss/ion/uas/selectUserAbsnceList.do";
	    }

    	String [] strUserIds = userIds.split(";");

    	for (String strUserId : strUserIds) {
    		userAbsnceVO.setUserId(strUserId);
    		egovUserAbsnceService.deleteUserAbsnce(userAbsnceVO);
    	}

    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/uas/selectUserAbsnceList.do";
	}

	/**
	 * MyPage에 사용자부재정보를 제공하기 위해 목록을 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping("/uss/ion/uas/selectUserAbsnceMainList.do")
	public String selectUserAbsnceMainList(@ModelAttribute("userAbsnceVO") UserAbsnceVO userAbsnceVO,
			                                ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userAbsnceVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(5);
		paginationInfo.setPageSize(userAbsnceVO.getPageSize());

		userAbsnceVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userAbsnceVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userAbsnceVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		userAbsnceVO.setSelAbsnceAt("A");
		userAbsnceVO.setUserAbsnceList(egovUserAbsnceService.selectUserAbsnceList(userAbsnceVO));

		model.addAttribute("userAbsnceList", userAbsnceVO.getUserAbsnceList());

        return "egovframework/com/uss/ion/uas/EgovUserAbsnceMainList";
	}
}
