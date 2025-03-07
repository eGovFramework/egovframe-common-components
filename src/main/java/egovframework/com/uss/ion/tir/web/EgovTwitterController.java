package egovframework.com.uss.ion.tir.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;
//import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.tir.service.EgovTwitterTrnsmitService;
import egovframework.com.uss.ion.tir.service.TwitterInfo;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import twitter4j.CreateTweetResponse;

/**
 * 트위터 수신, 송신를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2010.10.04
 * @version 1.0
 * @see
 * <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.10.04  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovTwitterController {

//	@Autowired
//	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** 트위터 송신(목록) 서비스 */
	@Resource(name = "egovTwitterTrnsmitService")
	private EgovTwitterTrnsmitService egovTwitterTrnsmitService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovTwitterController.class);

	/**
	 * 트위터를 메인 인증 페이지조회
	 * @param commandMap 	-Request  Variable
	 * @return String 		-리턴 URL
	 * @throws Exception	-Exception Throws
	 */
	@IncludedInfo(name = "Twitter연동", order = 830, gid = 50)
	@RequestMapping(value = "/uss/ion/tir/selectTwitterMain.do")
	public String EgovTwitterMain(@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {
		return "egovframework/com/uss/ion/tir/EgovTwitterMain";
	}

	/**
	 * 트위터를 인증키 관리 페이지를 조회한다.
	 * @param model 		-Spring 제공하는 ModelMap
	 * @return String 		-리턴 URL
	 * @throws Exception	-Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/selectTwitterAccount.do", method = RequestMethod.GET)
	public String EgovTwitterAccountGet(ModelMap model) throws Exception {

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		HashMap<String, String> hmPram = new HashMap<String, String>();
		hmPram.put("usid", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		Map<?, ?> mapResult = egovTwitterTrnsmitService.selectTwitterAccount(hmPram);

		//Consumer key/Consumer secret 키 값 조회
		if (mapResult == null) {
			model.addAttribute("consumerKey", "");
			model.addAttribute("consumerSecret", "");
		} else {
			model.addAttribute("consumerKey", mapResult.get("CONSUMER_KEY"));
			model.addAttribute("consumerSecret", mapResult.get("CONSUMER_SECRET"));
		}

		return "egovframework/com/uss/ion/tir/EgovTwitterAccount";
	}

	/**
	 * 트위터를 인증키 관리 페이지를  수정한다.
	 * @param model 		-Spring 제공하는 ModelMap
	 * @return String 		-리턴 URL
	 * @throws Exception	-Exception Throws
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uss/ion/tir/selectTwitterAccount.do", method = RequestMethod.POST)
	public String EgovTwitterAccountPost(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String sConsumerKey = request.getParameter("ConsumerKey") == null ? "" : (String) request.getParameter("ConsumerKey");
		String sConsumerSecret = request.getParameter("ConsumerSecret") == null ? "" : (String) request.getParameter("ConsumerSecret");

		HashMap<String, String> hmPram = new HashMap<String, String>();
		hmPram.put("usid", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		Map<?, ?> mapResult = egovTwitterTrnsmitService.selectTwitterAccount(hmPram);

		hmPram.put("usid", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		hmPram.put("consumerKey", sConsumerKey);
		hmPram.put("consumerSecret", sConsumerSecret);
		hmPram.put("frstRegisterId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		hmPram.put("lastUpdusrId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		if (egovTwitterTrnsmitService.selectTwitterAccountCheck(hmPram) > 0) {
			egovTwitterTrnsmitService.updtTwitterAccount(hmPram);
		} else {
			egovTwitterTrnsmitService.insertTwitterAccount(hmPram);
		}

		//저장된키 키 Attribute 설정
		model.addAttribute("consumerKey", sConsumerKey);
		model.addAttribute("consumerSecret", sConsumerSecret);

		//트위터 세션정보 삭제
		WebUtils.setSessionAttribute(request, "sCONSUMER_KEY", null);
		WebUtils.setSessionAttribute(request, "sCONSUMER_SECRET", null);
		WebUtils.setSessionAttribute(request, "atoken", null);
		WebUtils.setSessionAttribute(request, "astoken", null);

		//저장메세지 설정
		String ReusltScript = "";

		ReusltScript += "<script type='text/javaScript' language='javascript'>";
		ReusltScript += "alert(' 작성된  트위터 인증키(ConsumerKey/ConsumerSecret)를 저장 하였습니다!  ');";
		ReusltScript += "</script>";

		model.addAttribute("reusltScript", ReusltScript);

		return "egovframework/com/uss/ion/tir/EgovTwitterAccount";
	}

	/**
	 * 트위터를 인증 페이지를 조회한다.
	 * @param model 		-Spring 제공하는 ModelMap
	 * @return String 		-리턴 URL
	 * @throws Exception	-Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/selectTwitterPopup.do")
	public String EgovTwitterPopupGet(ModelMap model) throws Exception {

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		HashMap<String, String> hmPram = new HashMap<String, String>();
		hmPram.put("usid", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		Map<?, ?> mapResult = egovTwitterTrnsmitService.selectTwitterAccount(hmPram);

		//Consumer key/Consumer secret 키 값 조회
		if (mapResult == null) {
			model.addAttribute("consumerKey", "");
			model.addAttribute("consumerSecret", "");
		} else {
			model.addAttribute("consumerKey", mapResult.get("CONSUMER_KEY"));
			model.addAttribute("consumerSecret", mapResult.get("CONSUMER_SECRET"));
		}

		return "egovframework/com/uss/ion/tir/EgovTwitterPopup";
	}

	/**
	 * 트위터를 인증 페이지를 조회한다.
	 * @param searchVO 		-트위터 Model
	 * @param commandMap 	-Request  Variable
	 * @param twitterInfo 	-트위터 Model
	 * @param model 		-Spring 제공하는 ModelMap
	 * @return String 		-리턴 URL
	 * @throws Exception	-Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/selectTwitterPopupActor.do")
	public String EgovTwitterPopupPost(@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception {

		String sCheckKey = commandMap.get("chkKey") == null ? "" : (String) commandMap.get("chkKey");

		String sConsumerKey = commandMap.get("ConsumerKey") == null ? "" : (String) commandMap.get("ConsumerKey");
		String sConsumerSecret = commandMap.get("ConsumerSecret") == null ? "" : (String) commandMap.get("ConsumerSecret");

		// Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		// 로그인 객체 선언
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		HashMap<String, String> hmPram = new HashMap<String, String>();
		hmPram.put("usid", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		hmPram.put("consumerKey", sConsumerKey);
		hmPram.put("consumerSecret", sConsumerSecret);
		hmPram.put("frstRegisterId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		hmPram.put("lastUpdusrId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

		LOGGER.info("EgovTwitterPopupPost>");
		LOGGER.info("selectTwitterAccountCheck>" + egovTwitterTrnsmitService.selectTwitterAccountCheck(hmPram));

		//Consumer key/Consumer secret 키 값 저장 체크시
		if (sCheckKey.equals("1")) {
			if (egovTwitterTrnsmitService.selectTwitterAccountCheck(hmPram) > 0) {
				egovTwitterTrnsmitService.updtTwitterAccount(hmPram);
			} else {
				egovTwitterTrnsmitService.insertTwitterAccount(hmPram);
			}

		} else {
			egovTwitterTrnsmitService.deleteTwitterAccount(hmPram);
		}

		return "egovframework/com/uss/ion/tir/EgovTwitterPopupActor";
	}

	/**
	 * 트위터를 인증 페이지를 조회한다.
	 * @param model 		-Spring 제공하는 ModelMap
	 * @return String 		-리턴 URL
	 * @throws Exception	-Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/selectTwitterPopupProcess.do")
	public String EgovTwitterPopupProcess(ModelMap model) throws Exception {
		return "egovframework/com/uss/ion/tir/EgovTwitterPopupProcess";
	}


	/**
	 * 트위터를 송신 페이지를 조회 한다.
	 * @param model 		-Spring 제공하는 ModelMap
	 * @return String 		-리턴 URL
	 * @throws Exception	-Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/registTwitterTrnsmit.do", method = RequestMethod.GET)
	public String EgovTwitterTrnsmitGet(ModelMap model, HttpServletRequest request) throws Exception {
		
		String sCONSUMER_KEY = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_KEY");
		String sCONSUMER_SECRET = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_SECRET");

		String atoken = (String) WebUtils.getSessionAttribute(request, "atoken");
		String astoken = (String) WebUtils.getSessionAttribute(request, "astoken");

		HashMap<String, Object> hmParam = new HashMap<String, Object>();
		// 인증키값 설정
		hmParam.put("sCONSUMER_KEY", sCONSUMER_KEY);
		hmParam.put("sCONSUMER_SECRET", sCONSUMER_SECRET);
		hmParam.put("atoken", atoken);
		hmParam.put("astoken", astoken);

		Map<?, ?> userResult = egovTwitterTrnsmitService.twitterUserAccount(hmParam); // 유저정보

		model.addAttribute("userID", userResult.get("userName"));
		model.addAttribute("userName", userResult.get("userScreenName"));
		model.addAttribute("twitterInfo", new TwitterInfo());

		return "egovframework/com/uss/ion/tir/EgovTwitterTrnsmit";
	}

	/**
	 * 트위터를 송신을 등록 처리 한다.
	 * @param searchVO 		-트위터 Model
	 * @param commandMap 	-Request Variable
	 * @param twitterInfo 	-트위터 Model
	 * @param request -HttpServletRequest 객체
	 * @param response -HttpServletResponse 객체
	 * @param model 		-Spring 제공하는 ModelMap
	 * @return String 		-리턴 URL
	 * @throws Exception	-Exception Throws
	 */
	@RequestMapping(value = "/uss/ion/tir/registTwitterTrnsmit.do", method = RequestMethod.POST)
	public String EgovTwitterTrnsmitPost(TwitterInfo twitterInfo, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		String sCONSUMER_KEY = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_KEY");
		String sCONSUMER_SECRET = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_SECRET");

		String atoken = (String) WebUtils.getSessionAttribute(request, "atoken");
		String astoken = (String) WebUtils.getSessionAttribute(request, "astoken");

		HashMap<String, Object> hmParam = new HashMap<String, Object>();

		// 인증키값 설정
		hmParam.put("sCONSUMER_KEY", sCONSUMER_KEY);
		hmParam.put("sCONSUMER_SECRET", sCONSUMER_SECRET);
		hmParam.put("atoken", atoken);
		hmParam.put("astoken", astoken);
		LOGGER.info("[Controller]===>>> atoken = " + atoken);
		LOGGER.info("[Controller]===>>> astoken = " + astoken);

		// 트위터 글 게시
		CreateTweetResponse tweetResult = egovTwitterTrnsmitService.twitterTrnsmitRegist(hmParam,
				twitterInfo.getTwitterText());
		Map<?, ?> userResult = egovTwitterTrnsmitService.twitterUserAccount(hmParam); // 유저정보

		twitterInfo.setTwitterTweetId(tweetResult.getId());
		twitterInfo.setTwitterText(tweetResult.getText());
		twitterInfo.setTwitterId((Long) userResult.get("userId"));
		twitterInfo.setTwitterScreenName(userResult.get("userScreenName").toString());
		twitterInfo.setTwitterNmae(userResult.get("userName").toString());
		twitterInfo.setTwitterCreatedAt((Date) userResult.get("userCreate_At"));
		twitterInfo.setTwitterProfileImageURL(userResult.get("userProfile_url").toString());

		model.addAttribute("twitterInfo", twitterInfo);

		return "egovframework/com/uss/ion/tir/EgovTwitterTrnsmitResult";
	}
	
	@RequestMapping(value = "/uss/ion/tir/twitterDelete.do")
	public String deleteTweet(@RequestParam("tweetID") String tID, HttpServletRequest request) throws Exception {

		tID = tID.replace("&quot;", "");
		LOGGER.info("트윗 아이디 >>> " + tID);

		String sCONSUMER_KEY = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_KEY");
		String sCONSUMER_SECRET = (String) WebUtils.getSessionAttribute(request, "sCONSUMER_SECRET");

		String atoken = (String) WebUtils.getSessionAttribute(request, "atoken");
		String astoken = (String) WebUtils.getSessionAttribute(request, "astoken");

		HashMap<String, Object> hmParam = new HashMap<String, Object>();

		// 인증키값 설정
		hmParam.put("sCONSUMER_KEY", sCONSUMER_KEY);
		hmParam.put("sCONSUMER_SECRET", sCONSUMER_SECRET);
		hmParam.put("atoken", atoken);
		hmParam.put("astoken", astoken);
		
		boolean deleteResult = egovTwitterTrnsmitService.twitterDelete(hmParam, tID);

		LOGGER.info("트윗 삭제");
		LOGGER.info("DELETERESULT >>> " + deleteResult);
		
		return "egovframework/com/uss/ion/tir/EgovTwitterMain";

	}
}
