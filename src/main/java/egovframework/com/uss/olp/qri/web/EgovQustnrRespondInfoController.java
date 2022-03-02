package egovframework.com.uss.olp.qri.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qri.service.EgovQustnrRespondInfoService;
import egovframework.com.uss.olp.qri.service.QustnrRespondInfoVO;
import egovframework.com.uss.olp.qrm.service.EgovQustnrRespondManageService;
import egovframework.com.uss.olp.qrm.service.QustnrRespondManageVO;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;
/**
 * 설문조사 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자             수정내용
 *  ----------   --------   ---------------------------
 *  2009.03.20   장동한            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2019.05.16 	 신용호             egovQustnrRespondInfoManageTemplate() 메소드 삭제 (보안취약점 대응)
 *
 * </pre>
 */


@Controller
public class EgovQustnrRespondInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrRespondInfoController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovQustnrRespondInfoService")
	private EgovQustnrRespondInfoService egovQustnrRespondInfoService;

	@Resource(name = "egovQustnrRespondManageService")
	private EgovQustnrRespondManageService egovQustnrRespondManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;


	/**
	 * 설문템플릿을 적용한다.
	 * @param searchVO
	 * @param request
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/template/template"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qri/template/template")
	public String egovQustnrRespondInfoManageTemplate(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			HttpServletRequest request,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {


		String sTemplateUrl = (String)commandMap.get("templateUrl");

		LOGGER.debug("qestnrId=> {}", commandMap.get("qestnrId"));
		LOGGER.debug("qestnrTmplatId=> {}", commandMap.get("qestnrTmplatId"));
		LOGGER.debug("templateUrl=> {}", commandMap.get("templateUrl"));

 		//설문템플릿정보
		model.addAttribute("QustnrTmplatManage",  egovQustnrRespondInfoService.selectQustnrTmplatManage(commandMap));

    	//설문정보
    	model.addAttribute("Comtnqestnrinfo",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqestnrinfo(commandMap));
    	//문항정보
    	model.addAttribute("Comtnqustnrqesitm",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnrqesitm(commandMap));
    	//항목정보
    	model.addAttribute("Comtnqustnriem",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnriem(commandMap));
    	//설문템플릿ID 설정
    	model.addAttribute("qestnrTmplatId",  commandMap.get("qestnrTmplatId") == null ? "" : (String)commandMap.get("qestnrTmplatId") );
       	//설문지정보ID 설정
    	model.addAttribute("qestnrId",  commandMap.get("qestnrId") == null ? "" : (String)commandMap.get("qestnrId"));

        //객관식통계 답안
    	model.addAttribute("qestnrStatistic1", egovQustnrRespondInfoService.selectQustnrRespondInfoManageStatistics1(commandMap));

        //주관식통계 답안
    	model.addAttribute("qestnrStatistic2", egovQustnrRespondInfoService.selectQustnrRespondInfoManageStatistics2(commandMap));

    	//이전 주소
    	model.addAttribute("returnUrl", request.getHeader("REFERER"));

		// 안전한 경로 문자열로 조치
    	sTemplateUrl = EgovWebUtil.filePathBlackList(sTemplateUrl);

		// 화이트 리스트 체크
		List<?> popupWhiteList = egovQustnrRespondInfoService.selectQustnrTmplatWhiteList();
		LOGGER.debug("QustnrTmplat > WhiteList Count = {}",popupWhiteList.size());
		if ( sTemplateUrl == null ) sTemplateUrl = "";
		for(Object obj : popupWhiteList){
            EgovMap map = (EgovMap)obj;
			LOGGER.debug("QustnrTmplat > whiteList fileUrl = "+map.get("qestnrTmplatCours"));
            if ( sTemplateUrl.equals(map.get("qestnrTmplatCours")) ) {
            	return sTemplateUrl;
            }
        }
		
		LOGGER.debug("QustnrTmplat > WhiteList mismatch! Please check Admin page!");
		return "egovframework/com/cmm/egovError";
	}

	/**
	 * 설문조사 전체 통계를 조회한다.
	 * @param searchVO
	 * @param request
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/qnn/EgovQustnrRespondInfoManageStatistics"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qnn/EgovQustnrRespondInfoManageStatistics.do")
	public String egovQustnrRespondInfoManageStatistics(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			HttpServletRequest request,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/uss/olp/qnn/EgovQustnrRespondInfoManageStatistics";

    	 //설문정보
    	 model.addAttribute("Comtnqestnrinfo",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqestnrinfo(commandMap));
    	 //문항정보
    	 model.addAttribute("Comtnqustnrqesitm",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnrqesitm(commandMap));
    	 //항목정보
    	 model.addAttribute("Comtnqustnriem",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnriem(commandMap));
    	 //설문템플릿ID 설정
    	 model.addAttribute("qestnrTmplatId",  commandMap.get("qestnrTmplatId") == null ? "" : (String)commandMap.get("qestnrTmplatId") );
       	 //설문지정보ID 설정
    	 model.addAttribute("qestnrId",  commandMap.get("qestnrId") == null ? "" : (String)commandMap.get("qestnrId"));

         //객관식통계 답안
    	 model.addAttribute("qestnrStatistic1", egovQustnrRespondInfoService.selectQustnrRespondInfoManageStatistics1(commandMap));

         //주관식통계 답안
    	 model.addAttribute("qestnrStatistic2", egovQustnrRespondInfoService.selectQustnrRespondInfoManageStatistics2(commandMap));

    	 //이전 주소
    	 model.addAttribute("returnUrl", request.getHeader("REFERER"));



		return sLocationUrl;
	}

	/**
	 * 설문조사(설문등록) 목록을 조회한다.
	 * @param searchVO
	 * @param request
	 * @param response
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/qnn/EgovQustnrRespondInfoManageList"
	 * @throws Exception
	 */
	@IncludedInfo(name="설문조사", order = 600 ,gid = 50)
	@RequestMapping(value="/uss/olp/qnn/EgovQustnrRespondInfoManageList.do")
	public String egovQustnrRespondInfoManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> sampleList = egovQustnrRespondInfoService.selectQustnrRespondInfoManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrRespondInfoService.selectQustnrRespondInfoManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olp/qnn/EgovQustnrRespondInfoManageList";
	}

	/**
	 * 설문조사(설문등록)를 등록한다.
	 * @param searchVO
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/uss/olp/qnn/EgovQustnrRespondInfoManageRegist"
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/uss/olp/qnn/EgovQustnrRespondInfoManageRegist.do")
	public String egovQustnrRespondInfoManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map commandMap,
			HttpServletRequest request,
    		ModelMap model)
    throws Exception {
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(loginVO == null){ loginVO = new LoginVO();}

		String sLocationUrl = "egovframework/com/uss/olp/qnn/EgovQustnrRespondInfoManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);


     	//성별코드조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM014");
    	List<CmmnDetailCode> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode014", listComCode);

    	//직업코드조회
    	voComCode.setCodeId("COM034");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode034", listComCode);

        if(sCmd.equals("save")){

    		//설문조사 처리 START
    		String sKey ="";
    		String sVal ="";
           	for(Object key:commandMap.keySet()){

           		sKey = key.toString();

           		//설문문항정보 추출
           		if(sKey.length() > 6 && sKey.substring(0, 6).equals("QQESTN")){


           			//설문조사 등록
	           		//객관식 답안 처리
	           		if( commandMap.get("TY_"+key).equals("1") ){
	           			
	           			String[] arrayParam = request.getParameterValues(key.toString());
	           			
           				if( arrayParam.length == 1 ){
	           				sVal = arrayParam[0];

		           			QustnrRespondInfoVO qustnrRespondInfoVO = new QustnrRespondInfoVO();

		           			qustnrRespondInfoVO.setQestnrTmplatId((String) commandMap.get("qestnrTmplatId"));
		           			qustnrRespondInfoVO.setQestnrId((String) commandMap.get("qestnrId"));
		           			qustnrRespondInfoVO.setQestnrQesitmId(sKey);
		           			qustnrRespondInfoVO.setQustnrIemId(sVal);

		           			qustnrRespondInfoVO.setRespondAnswerCn("");

		           			qustnrRespondInfoVO.setRespondNm((String) commandMap.get("respondNm"));
		           			qustnrRespondInfoVO.setEtcAnswerCn((String) commandMap.get("ETC_" + sVal));


		            		qustnrRespondInfoVO.setFrstRegisterId(loginVO.getUniqId());
		            		qustnrRespondInfoVO.setLastUpdusrId(loginVO.getUniqId());

		           			egovQustnrRespondInfoService.insertQustnrRespondInfo(qustnrRespondInfoVO);
           				}else{
	        				String[] arrVal = arrayParam;
	        				for(int g=0; g < arrVal.length; g++ ){
	        					//("QQESTN arr :" + arrVal[g]);
			           			QustnrRespondInfoVO qustnrRespondInfoVO = new QustnrRespondInfoVO();

			           			qustnrRespondInfoVO.setQestnrTmplatId((String) commandMap.get("qestnrTmplatId"));
			           			qustnrRespondInfoVO.setQestnrId((String) commandMap.get("qestnrId"));
			           			qustnrRespondInfoVO.setQestnrQesitmId(sKey);
			           			qustnrRespondInfoVO.setQustnrIemId(arrVal[g]);

			           			qustnrRespondInfoVO.setRespondAnswerCn("");

			           			qustnrRespondInfoVO.setRespondNm((String) commandMap.get("respondNm"));
			           			qustnrRespondInfoVO.setEtcAnswerCn((String) commandMap.get("ETC_" + arrVal[g]));


			            		qustnrRespondInfoVO.setFrstRegisterId(loginVO.getUniqId());
			            		qustnrRespondInfoVO.setLastUpdusrId(loginVO.getUniqId());

			           			egovQustnrRespondInfoService.insertQustnrRespondInfo(qustnrRespondInfoVO);
	        				}
           				}


	           		//주관식 답안 처리
	           		}else if( commandMap.get("TY_"+key).equals("2") ){
	           			QustnrRespondInfoVO qustnrRespondInfoVO = new QustnrRespondInfoVO();

	           			qustnrRespondInfoVO.setQestnrTmplatId((String) commandMap.get("qestnrTmplatId"));
	           			qustnrRespondInfoVO.setQestnrId((String) commandMap.get("qestnrId"));
	           			qustnrRespondInfoVO.setQestnrQesitmId(sKey);
	           			qustnrRespondInfoVO.setQustnrIemId(null);

	           			qustnrRespondInfoVO.setRespondAnswerCn((String) commandMap.get(sKey));

	           			qustnrRespondInfoVO.setRespondNm((String) commandMap.get("respondNm"));
	           			qustnrRespondInfoVO.setEtcAnswerCn(null);

	            		qustnrRespondInfoVO.setFrstRegisterId(loginVO.getUniqId());
	            		qustnrRespondInfoVO.setLastUpdusrId(loginVO.getUniqId());


	           			egovQustnrRespondInfoService.insertQustnrRespondInfo(qustnrRespondInfoVO);
	           		}


           		}
        	}

       		//설문응답자 처리
       		QustnrRespondManageVO qustnrRespondManageVO = new QustnrRespondManageVO();

       		qustnrRespondManageVO.setQestnrId((String) commandMap.get("qestnrId"));
       		qustnrRespondManageVO.setQestnrTmplatId((String) commandMap.get("qestnrTmplatId"));

       		qustnrRespondManageVO.setSexdstnCode((String) commandMap.get("sexdstnCode"));
       		qustnrRespondManageVO.setOccpTyCode((String) commandMap.get("occpTyCode"));
       		qustnrRespondManageVO.setBrth((String) commandMap.get("brth"));
       		qustnrRespondManageVO.setRespondNm((String) commandMap.get("respondNm"));

       		qustnrRespondManageVO.setFrstRegisterId(loginVO.getUniqId());
       		qustnrRespondManageVO.setLastUpdusrId(loginVO.getUniqId());
       		egovQustnrRespondManageService.insertQustnrRespondManage(qustnrRespondManageVO);

           	String ResultScript = "";

           	ResultScript += "<script type='text/javaScript' language='javascript'>";
        	ResultScript += "alert(' 설문참여에 응해주셔서 감사합니다!  ');";
           	ResultScript += "</script>";

           	model.addAttribute("resultScript", ResultScript);
        	sLocationUrl = "forward:/uss/olp/qnn/EgovQustnrRespondInfoManageList.do";
        }else{

        	 if(loginVO.getUniqId() != null){
	        	 commandMap.put("uniqId", loginVO.getUniqId());
	        	 //사용자정보
	        	 model.addAttribute("Emplyrinfo",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageEmplyrinfo(commandMap));
        	 }
        	 
     		 //설문템플릿정보
    		 model.addAttribute("QustnrTmplatManage",  egovQustnrRespondInfoService.selectQustnrTmplatManage(commandMap));

        	 //설문정보
        	 model.addAttribute("Comtnqestnrinfo",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqestnrinfo(commandMap));
        	 //문항정보
        	 model.addAttribute("Comtnqustnrqesitm",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnrqesitm(commandMap));
        	 //항목정보
        	 model.addAttribute("Comtnqustnriem",  egovQustnrRespondInfoService.selectQustnrRespondInfoManageComtnqustnriem(commandMap));
        	 //설문템플릿ID 설정
        	 model.addAttribute("qestnrTmplatId",  commandMap.get("qestnrTmplatId") == null ? "" : (String)commandMap.get("qestnrTmplatId") );
           	 //설문지정보ID 설정
        	 model.addAttribute("qestnrId",  commandMap.get("qestnrId") == null ? "" : (String)commandMap.get("qestnrId"));

        }

		return sLocationUrl;
	}

	/**
	 * 응답자결과(설문조사) 목록을 조회한다.
	 * @param searchVO
	 * @param request
	 * @param commandMap
	 * @param qustnrRespondInfoVO
	 * @param model
	 * @return "egovframework/com/uss/olp/qri/EgovQustnrRespondInfoList"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qri/EgovQustnrRespondInfoList.do")
	public String egovQustnrRespondInfoList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			HttpServletRequest request,
			@RequestParam Map<?, ?> commandMap,
			QustnrRespondInfoVO qustnrRespondInfoVO,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(loginVO == null){ loginVO = new LoginVO();}

		String sSearchMode = commandMap.get("searchMode") == null ? "" : (String)commandMap.get("searchMode");

		//설문지정보에서 넘어오면 자동검색 설정
		if(sSearchMode.equals("Y")){
			searchVO.setSearchCondition("QESTNR_ID");
			searchVO.setSearchKeyword(qustnrRespondInfoVO.getQestnrId());
		}

    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> sampleList = egovQustnrRespondInfoService.selectQustnrRespondInfoList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrRespondInfoService.selectQustnrRespondInfoListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olp/qri/EgovQustnrRespondInfoList";
	}

	/**
	 * 응답자결과(설문조사) 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrRespondInfoVO
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qri/EgovQustnrRespondInfoDetail.do")
	public String egovQustnrRespondInfoDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			QustnrRespondInfoVO qustnrRespondInfoVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "egovframework/com/uss/olp/qri/EgovQustnrRespondInfoDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			egovQustnrRespondInfoService.deleteQustnrRespondInfo(qustnrRespondInfoVO);
			sLocationUrl = "redirect:/uss/olp/qri/EgovQustnrRespondInfoList.do";
		}else{
	        List<?> sampleList = egovQustnrRespondInfoService.selectQustnrRespondInfoDetail(qustnrRespondInfoVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	 * 응답자결과(설문조사)를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param request
	 * @param qustnrRespondInfoVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/uss/olp/qri/EgovQustnrRespondInfoModify"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qri/EgovQustnrRespondInfoModify.do")
	public String qustnrRespondInfoModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			HttpServletRequest request,
			@ModelAttribute("qustnrRespondInfoVO") QustnrRespondInfoVO qustnrRespondInfoVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(loginVO == null){ loginVO = new LoginVO();}

		String sLocationUrl = "egovframework/com/uss/olp/qri/EgovQustnrRespondInfoModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

        if(sCmd.equals("save")){
    		//서버  validate 체크
            beanValidator.validate(qustnrRespondInfoVO, bindingResult);
    		if(bindingResult.hasErrors()){
    	        return sLocationUrl;
    		}

    		//아이디 설정
    		qustnrRespondInfoVO.setFrstRegisterId(loginVO.getUniqId());
    		qustnrRespondInfoVO.setLastUpdusrId(loginVO.getUniqId());

        	egovQustnrRespondInfoService.updateQustnrRespondInfo(qustnrRespondInfoVO);
        	sLocationUrl = "redirect:/uss/olp/qri/EgovQustnrRespondInfoList.do";
		}else{
	        List<?> sampleList = egovQustnrRespondInfoService.selectQustnrRespondInfoDetail(qustnrRespondInfoVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	 * 응답자결과(설문조사)를 등록한다.
	 * @param searchVO
	 * @param commandMap
	 * @param request
	 * @param qustnrRespondInfoVO
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/uss/olp/qri/EgovQustnrRespondInfoRegist"
	 * @throws Exception
	 */
	@RequestMapping(value="/uss/olp/qri/EgovQustnrRespondInfoRegist.do")
	public String qustnrRespondInfoRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			HttpServletRequest request,
			@ModelAttribute("qustnrRespondInfoVO") QustnrRespondInfoVO qustnrRespondInfoVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(loginVO == null){ loginVO = new LoginVO();}

		String sLocationUrl = "egovframework/com/uss/olp/qri/EgovQustnrRespondInfoRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

        if(sCmd.equals("save")){
    		//서버  validate 체크
            beanValidator.validate(qustnrRespondInfoVO, bindingResult);
    		if(bindingResult.hasErrors()){
    	        return sLocationUrl;
    		}

    		//아이디 설정
    		qustnrRespondInfoVO.setFrstRegisterId(loginVO.getUniqId());
    		qustnrRespondInfoVO.setLastUpdusrId(loginVO.getUniqId());

        	egovQustnrRespondInfoService.insertQustnrRespondInfo(qustnrRespondInfoVO);
        	sLocationUrl = "redirect:/uss/olp/qri/EgovQustnrRespondInfoList.do";
        }

		return sLocationUrl;
	}
}


