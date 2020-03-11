package egovframework.com.uss.ion.yrc.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.yrc.service.EgovIndvdlYrycManageService;
import egovframework.com.uss.ion.yrc.service.IndvdlYrycManage;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.string.EgovDateUtil;

/**
 * 개요
 * - 개인연차관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 개인연차관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * @author 표준프레임워크센터
 * @version 1.0
 * @created 2014.11.14
 * <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *     수정일      	수정자          수정내용
 *  -----------    --------    ---------------------------
 *   2014.11.14		이기하          최초 생성
 *
 * </pre>
 */

@Controller
public class EgovIndvdlYrycManageController {

    @Resource(name = "egovIndvdlYrycManageService")
    private EgovIndvdlYrycManageService egovIndvdlYrycManageService;

    @Autowired
	 private DefaultBeanValidator beanValidator;

	/**
	 * 개인연차관리정보를 관리하기 위해 등록된 개인연차관리 목록을 조회한다.
	 * @param IndvdlYrycManage - 개인연차관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="개인연차관리", order = 902 ,gid = 50)
    @RequestMapping(value="/uss/ion/yrc/EgovIndvdlYrycManageList.do")
	public String selectIndvdlYrycManageList(IndvdlYrycManage indvdlYrycManage, ModelMap model) throws Exception {

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	if (user == null) {
    		return "egovframework/com/uat/uia/EgovLoginUsr";
    	}
    	
    	indvdlYrycManage.setMberId(user.getUniqId());

    	List<?> indvdlYrycManageList = egovIndvdlYrycManageService.selectIndvdlYrycManageList(indvdlYrycManage);
		model.addAttribute("resultList", indvdlYrycManageList);

		return "egovframework/com/uss/ion/yrc/EgovIndvdlYrycManageList";
	}
    /**
	 * 개인별연차관리 등록 화면으로 이동한다.
	 * @param indvdlYrycManage - 연차관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/yrc/EgovIndvdlYrycRegist.do", method=RequestMethod.GET)
	public String insertViewIndvdlYrycManage(@ModelAttribute IndvdlYrycManage indvdlYrycManage, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	indvdlYrycManage.setMberId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
    	indvdlYrycManage.setMberNm(user == null ? "" : EgovStringUtil.isNullToString(user.getName()));

    	List<?> indvdlYrycManageList = egovIndvdlYrycManageService.selectIndvdlYrycManageList(indvdlYrycManage);
    	indvdlYrycManage.setOccrrncYear(EgovDateUtil.getCurrentYearAsString());

    	int totCnt = egovIndvdlYrycManageService.selectIndvdlYrycManageListTotCnt(indvdlYrycManage);

		model.addAttribute("resultList", indvdlYrycManageList);
		model.addAttribute("totCnt", totCnt);

		return "egovframework/com/uss/ion/yrc/EgovIndvdlYrycRegist";
	}

	/**
	 * 개인별연차관리 등록한다.
	 * @param indvdlYrycManage - 연차관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/yrc/EgovIndvdlYrycRegist.do", method=RequestMethod.POST)
	public String insertIndvdlYrycManage(@ModelAttribute IndvdlYrycManage indvdlYrycManage, BindingResult bindingResult,
								   ModelMap model) throws Exception {

		beanValidator.validate(indvdlYrycManage, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
			model.addAttribute("indvdlYrycManage", indvdlYrycManage);
			return "egovframework/com/uss/ion/yrc/EgovIndvdlYrycRegist";
		} else {
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			indvdlYrycManage.setMberId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
			indvdlYrycManage.setRemndrYrycCo(indvdlYrycManage.getOccrncYrycCo() - indvdlYrycManage.getUseYrycCo());

			int totCnt = egovIndvdlYrycManageService.selectIndvdlYrycManageListTotCnt(indvdlYrycManage);

			if (totCnt >= 1) {
				egovIndvdlYrycManageService.updtIndvdlYrycManage(indvdlYrycManage);
			} else {
				egovIndvdlYrycManageService.insertIndvdlYrycManage(indvdlYrycManage);
			}

			List<?> indvdlYrycManageList = egovIndvdlYrycManageService.selectIndvdlYrycManageList(indvdlYrycManage);
			model.addAttribute("resultList", indvdlYrycManageList);
			model.addAttribute("totCnt", totCnt);

			return "egovframework/com/uss/ion/yrc/EgovIndvdlYrycManageList";
		}
	}

	/**
	 * 개인별연차관리 삭제한다.
	 * @param indvdlYrycManage - 연차관리 model
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/uss/ion/yrc/deleteIndvdlYryc.do", method=RequestMethod.POST)
	public String deleteIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		indvdlYrycManage.setMberId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		int totCnt = egovIndvdlYrycManageService.selectIndvdlYrycManageListTotCnt(indvdlYrycManage);

		if (totCnt >= 1) {
			egovIndvdlYrycManageService.deleteIndvdlYrycManage(indvdlYrycManage);
		}

		return "egovframework/com/uss/ion/yrc/EgovIndvdlYrycManageList";
	}

}
