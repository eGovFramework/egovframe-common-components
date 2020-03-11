package egovframework.com.cop.sms.web;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.sms.service.EgovSmsInfoService;
import egovframework.com.cop.sms.service.Sms;
import egovframework.com.cop.sms.service.SmsVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 문자메시지 서비스 컨트롤러 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.18 한성곤          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovSmsInfoController {

	@Resource(name = "EgovSmsInfoService")
	protected EgovSmsInfoService smsInfoService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Autowired
	private DefaultBeanValidator beanValidator;

	//private static final Logger LOGGER = LoggerFactory.getLogger(EgovSmsInfoController.class);

	/**
	 * 문자메시지 목록을 조회한다.
	 *
	 * @param smsVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@IncludedInfo(name = "문자메시지", order = 310, gid = 40)
	@RequestMapping("/cop/sms/selectSmsList.do")
	public String selectSmsList(@ModelAttribute("searchVO") SmsVO smsVO, ModelMap model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

		smsVO.setUniqId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

		smsVO.setPageUnit(propertyService.getInt("pageUnit"));
		smsVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(smsVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(smsVO.getPageUnit());
		paginationInfo.setPageSize(smsVO.getPageSize());

		smsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = smsInfoService.selectSmsInfs(smsVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/sms/EgovSmsInfoList";
	}

	/**
	 * 문자메시지 전송(등록)을 위한 전송 페이지로 이동한다.
	 *
	 * @param smsVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cop/sms/addSms.do")
	public String addSms(@ModelAttribute("searchVO") SmsVO smsVO, ModelMap model) throws Exception {

		Sms sms = new Sms();

		model.addAttribute("sms", sms);

		return "egovframework/com/cop/sms/EgovSmsInfoRegist";
	}

	/**
	 * 문자메시지 전송을 요청한다.
	 *
	 * @param smsVO
	 * @param sms
	 * @param bindingResult
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cop/sms/insertSms.do")
	public String insertSms(@ModelAttribute("searchVO") SmsVO smsVO, @ModelAttribute("sms") Sms sms, BindingResult bindingResult, SessionStatus status, ModelMap model)
			throws Exception {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(sms, bindingResult);
		if (bindingResult.hasErrors()) {
			return "egovframework/com/cop/sms/EgovSmsInfoRegist";
		}

		// 서버 점검 추가
		/*
		if (true) {
		    model.addAttribute("msg", "서버와의 연결이 정상적이지 않습니다.");
		    return "egovframework/com/cop/sms/EgovSmsInfoRegist";
		}
		*/

		if (isAuthenticated) {
			sms.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

			smsInfoService.insertSmsInf(sms);
		}

		return "forward:/cop/sms/selectSmsList.do";
	}

	/**
	 * 문자메시지에 대한 상세정보를 조회한다.
	 *
	 * @param smsVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cop/sms/selectSms.do")
	public String selectSms(@ModelAttribute("searchVO") SmsVO smsVO, ModelMap model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

		SmsVO vo = smsInfoService.selectSmsInf(smsVO);

		model.addAttribute("sessionUniqId", user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		model.addAttribute("result", vo);

		return "egovframework/com/cop/sms/EgovSmsInfoDetail";
	}
}
