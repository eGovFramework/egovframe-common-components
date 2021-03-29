package egovframework.com.dam.spe.req.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.map.mat.service.EgovMapMaterialService;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;
import egovframework.com.dam.map.tea.service.EgovMapTeamService;
import egovframework.com.dam.map.tea.service.MapTeamVO;
import egovframework.com.dam.spe.req.service.EgovRequestOfferService;
import egovframework.com.dam.spe.req.service.RequestOfferVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 지식정보제공/지식정보요청를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2010.08.30
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2010.08.30   장동한            최초 생성
 *  2011.08.26   정진오            IncludedInfo annotation 추가
 *  2019.12.09   신용호            KISA 보안약점 조치 (위험한 형식 파일 업로드)
 *
 * </pre>
 */

@Controller
public class EgovRequestOfferController {

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovRequestOffeService */
    @Resource(name = "egovRequestOffeService")
    private EgovRequestOfferService egovRequestOfferVOService;

    /** MapTeamService */
	@Resource(name = "MapTeamService")
    private EgovMapTeamService mapTeamService;

	@Resource(name = "MapMaterialService")
	public EgovMapMaterialService mapMaterialService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    /**
     * 지식정보제공/지식정보요청 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param RequestOfferVO
     * @param model
     * @return "egovframework/com/dam/spe/req/EgovRequestOfferVOList"
     * @throws Exception
     */
	@IncludedInfo(name="지식정보제공", listUrl="/dam/spe/req/listRequestOffer.do", order = 1291,gid = 80)
    @RequestMapping(value = "/dam/spe/req/listRequestOffer.do")
    public String EgovRequestOfferList(
            @ModelAttribute("searchVO") RequestOfferVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            RequestOfferVO RequestOfferVO, ModelMap model)
            throws Exception {

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "egovframework/com/uat/uia/EgovLoginUsr";
	    }

        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

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

        List<?> reusltList = egovRequestOfferVOService.selectRequestOfferList(searchVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        int totCnt = egovRequestOfferVOService.selectRequestOfferListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        //(지식전문가/지식사용자) 검사 및 설정
        HashMap<String, String> hmParam = new HashMap<String, String>();
        hmParam.put("speId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        //지식전문가 일때
        if(egovRequestOfferVOService.selectRequestOfferSpeCheck(hmParam)){
        	model.addAttribute("IS_SPE", "Y");
        }else{
        	model.addAttribute("IS_SPE", "N");
        	model.addAttribute("USER_UNIQ_ID", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        }

    	return "egovframework/com/dam/spe/req/EgovComDamRequestOfferList";

    }

    /**
     * 지식정보제공/지식정보요청 목록을 상세조회 조회한다.
     * @param searchVO
     * @param RequestOfferVO
     * @param commandMap
     * @param model
     * @return "egovframework/com/dam/spe/req/EgovRequestOfferVODetail"
     * @throws Exception
     */
    @RequestMapping(value = "/dam/spe/req/detailRequestOffer.do")
    public String EgovRequestOfferDetail(
            @ModelAttribute("searchVO") RequestOfferVO searchVO,
            RequestOfferVO requestOfferVO,
            @RequestParam Map<?, ?> commandMap,
            ModelMap model) throws Exception {

		//Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "egovframework/com/uat/uia/EgovLoginUsr";
	    }
        // 로그인 객체 선언
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        String sLocationUrl = "egovframework/com/dam/spe/req/EgovComDamRequestOfferDetail";

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

        if (sCmd.equals("del")) {

			HashMap<String, String> hmParam = new HashMap<String, String>();
			hmParam.put("ansParents", requestOfferVO.getKnoId());

        	//하위답변 검색 건수를 체크
        	if(egovRequestOfferVOService.selectRequestOfferDelCnt(hmParam) > 0){
        		//에러 메세지 출력
                String ReusltScript = "";

                ReusltScript += "<script type='text/javaScript' language='javascript'>";
                ReusltScript += "alert(' 하위 답변이 등록되어 있어 삭제할수 없습니다!  ');";
                ReusltScript += "</script>";

                model.addAttribute("reusltScript", ReusltScript);

                sCmd = "delMsg";
        	}else{
        		egovRequestOfferVOService.deleteRequestOffer(requestOfferVO);
            	sLocationUrl = "forward:/dam/spe/req/listRequestOffer.do";
        	}
        }

        if (!sCmd.equals("del")) {
            //상세정보 불러오기
        	RequestOfferVO requestOfferVOs = egovRequestOfferVOService.selectRequestOfferDetail(requestOfferVO);
            model.addAttribute("requestOfferVO", requestOfferVOs);

            //조직유형 불러오기
			MapTeamVO mapTeamVO = new MapTeamVO();
			mapTeamVO.setRecordCountPerPage(999999);
			mapTeamVO.setFirstIndex(0);
			mapTeamVO.setSearchCondition("MaterialList");
            List<?> MapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
            model.addAttribute("mapTeamList", MapTeamList);

            //지식유형코드불러오기
            MapMaterialVO searchMatVO = new MapMaterialVO();
            searchMatVO.setRecordCountPerPage(999999);
            searchMatVO.setFirstIndex(0);
            searchMatVO.setSearchCondition("orgnztId");
            searchMatVO.setSearchKeyword(requestOfferVOs.getOrgnztId());
            List<?> MapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
            model.addAttribute("mapMaterialList", MapMaterialList);

            //(지식전문가/지식사용자) 검사 및 설정
            HashMap<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("speId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));


            //아이디 설정
            model.addAttribute("USER_UNIQ_ID", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            //지식전문가 일때
            if(egovRequestOfferVOService.selectRequestOfferSpeCheck(hmParam)){
            	model.addAttribute("IS_SPE", "Y");
            }else{
            	model.addAttribute("IS_SPE", "N");
            }

        }

        return sLocationUrl;

    }

    /**
     * 지식정보제공/지식정보요청를 수정 조회 한다.
     * @param searchVO
     * @param commandMap
     * @param RequestOfferVO
     * @param bindingResult
     * @param model
     * @return "egovframework/com/dam/spe/req/EgovRequestOfferVORegist"
     * @throws Exception
     */
    @RequestMapping(value = "/dam/spe/req/updtRequestOffer.do")
    public String EgovRequestOfferModify(
           @ModelAttribute("searchVO") RequestOfferVO searchVO,
           @RequestParam Map<?, ?> commandMap,
           @ModelAttribute("requestOfferVO") RequestOfferVO requestOfferVO,
           MapMaterial mapMaterial,
           ModelMap model) throws Exception {

            // 0. Spring Security 사용자권한 처리
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
                return "egovframework/com/uat/uia/EgovLoginUsr";
            }
            String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

            RequestOfferVO requestOfferVOs = new RequestOfferVO();

            if(!sCmd.equals("change")){
            //수정정보 불러오기
            requestOfferVOs = egovRequestOfferVOService.selectRequestOfferDetail(requestOfferVO);
            model.addAttribute("requestOfferVO", requestOfferVOs);
            }

            //조직유형 불러오기
			MapTeamVO mapTeamVO = new MapTeamVO();
			mapTeamVO.setRecordCountPerPage(999999);
			mapTeamVO.setFirstIndex(0);
			mapTeamVO.setSearchCondition("MaterialList");
            List<?> MapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
            model.addAttribute("mapTeamList", MapTeamList);

            //지식유형코드불러오기
            MapMaterialVO searchMatVO = new MapMaterialVO();
            searchMatVO.setRecordCountPerPage(999999);
            searchMatVO.setFirstIndex(0);
            searchMatVO.setSearchCondition("orgnztId");
            if(sCmd.equals("change")){
            	 searchMatVO.setSearchKeyword(requestOfferVO.getOrgnztId());
            }else{
            	 searchMatVO.setSearchKeyword(requestOfferVOs.getOrgnztId());
            }

            List<?> MapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
            model.addAttribute("mapMaterialList", MapMaterialList);

        	// 파일업로드 제한
        	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
        	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

            model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
            model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
            
            return "egovframework/com/dam/spe/req/EgovComDamRequestOfferUpdt";
    }

    /**
     * 지식정보제공/지식정보요청를 수정한다.
     * @param searchVO
     * @param commandMap
     * @param RequestOfferVO
     * @param bindingResult
     * @param model
     * @return "egovframework/com/dam/spe/req/EgovRequestOfferVOUpdt"
     * @throws Exception
     */
    @RequestMapping(value = "/dam/spe/req/updtRequestOfferActor.do")
    public String EgovRequestOfferModifyActor(
    		final MultipartHttpServletRequest multiRequest,
            @ModelAttribute("searchVO") RequestOfferVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("RequestOfferVO") RequestOfferVO RequestOfferVO,
            BindingResult bindingResult, ModelMap model) throws Exception {

            // 0. Spring Security 사용자권한 처리
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
                return "egovframework/com/uat/uia/EgovLoginUsr";
            }

            // 로그인 객체 선언
            LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        	// 파일업로드 제한
        	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
        	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

            model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
            model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);
            
            String sLocationUrl = "egovframework/com/dam/spe/req/EgovComDamRequestOfferUpdt";

            String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

            if (sCmd.equals("save")) {

                //서버  validate 체크
                beanValidator.validate(RequestOfferVO, bindingResult);
                if(bindingResult.hasErrors()){
                    return sLocationUrl;
                }
                //아이디 설정
                RequestOfferVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                RequestOfferVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
            	// 첨부파일 관련 ID 생성 start....
        		String _atchFileId = RequestOfferVO.getAtchFileId();

        		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
        		final List<MultipartFile> files = multiRequest.getFiles("file_1");

        		if(!files.isEmpty()){
        			String atchFileAt = commandMap.get("atchFileAt") == null ? "" : (String)commandMap.get("atchFileAt");
        			if("N".equals(atchFileAt)){
        				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", 0, _atchFileId, "");
        				_atchFileId = fileMngService.insertFileInfs(_result);

        				// 첨부파일 ID 셋팅
        				RequestOfferVO.setAtchFileId(_atchFileId);    	// 첨부파일 ID

        			}else{
        				FileVO fvo = new FileVO();
        				fvo.setAtchFileId(_atchFileId);
        				int _cnt = fileMngService.getMaxFileSN(fvo);
        				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", _cnt, _atchFileId, "");
        				fileMngService.updateFileInfs(_result);
        			}
        		}
                //저장
                egovRequestOfferVOService.updateRequestOffer(RequestOfferVO);
                sLocationUrl = "forward:/dam/spe/req/listRequestOffer.do";
            } else {

                //수정정보 불러오기
                RequestOfferVO requestOfferVO = egovRequestOfferVOService.selectRequestOfferDetail(RequestOfferVO);
                model.addAttribute("requestOfferVO", requestOfferVO);
            }

            return sLocationUrl;
    }

    /**
     * 지식정보제공/지식정보요청를 등록 조회 한다.
     * @param searchVO
     * @param commandMap
     * @param RequestOfferVO
     * @param bindingResult
     * @param model
     * @return "egovframework/com/dam/spe/req/EgovRequestOfferVORegist"
     * @throws Exception
     */
    @RequestMapping(value = "/dam/spe/req/registRequestOffer.do")
    public String EgovRequestOfferRegist(
          // @ModelAttribute("searchVO") RequestOfferVO searchVO,
    	   @RequestParam Map<?, ?> commandMap,
           @ModelAttribute("requestOfferVO") RequestOfferVO requestOfferVO,
           @ModelAttribute("mapMaterial") MapMaterial mapMaterial,
           ModelMap model) throws Exception {

    		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

            // 0. Spring Security 사용자권한 처리
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
                return "egovframework/com/uat/uia/EgovLoginUsr";
            }

			MapTeamVO mapTeamVO = new MapTeamVO();
			mapTeamVO.setRecordCountPerPage(999999);
			mapTeamVO.setFirstIndex(0);
			mapTeamVO.setSearchCondition("MaterialList");
            List<?> MapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
            model.addAttribute("mapTeamList", MapTeamList);

            MapMaterialVO searchMatVO = new MapMaterialVO();
            searchMatVO.setRecordCountPerPage(999999);
            searchMatVO.setFirstIndex(0);
            searchMatVO.setSearchCondition("orgnztId");
            searchMatVO.setSearchKeyword(requestOfferVO.getOrgnztId());

            //if (mapMaterial.getOrgnztId().equals("")) {
            //	EgovMap emp = (EgovMap)MapTeamList.get(0);
            //	mapMaterial.setOrgnztId(emp.get("orgnztId").toString());
            //}

            List<?> MapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
            model.addAttribute("mapMaterialList", MapMaterialList);

            model.addAttribute("cmd", sCmd);
            
        	// 파일업로드 제한
        	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
        	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

            model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
            model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

            return "egovframework/com/dam/spe/req/EgovComDamRequestOfferRegist";
    }

    /**
     * 지식정보제공/지식정보요청를 등록을 처리 한다.
     * @param searchVO
     * @param commandMap
     * @param RequestOfferVO
     * @param bindingResult
     * @param model
     * @return "egovframework/com/dam/spe/req/EgovRequestOfferVORegist"
     * @throws Exception
     */
    @RequestMapping(value = "/dam/spe/req/registRequestOfferActor.do")
    public String EgovRequestOfferRegistActor(
    		final MultipartHttpServletRequest multiRequest,
            @ModelAttribute("searchVO") RequestOfferVO searchVO,
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("requestOfferVO") RequestOfferVO requestOfferVO,
            BindingResult bindingResult, ModelMap model) throws Exception {

            // 0. Spring Security 사용자권한 처리
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
                return "egovframework/com/uat/uia/EgovLoginUsr";
            }

            // 로그인 객체 선언
            LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            
        	// 파일업로드 제한
        	String whiteListFileUploadExtensions = EgovProperties.getProperty("Globals.fileUpload.Extensions");
        	String fileUploadMaxSize = EgovProperties.getProperty("Globals.fileUpload.maxSize");

            model.addAttribute("fileUploadExtensions", whiteListFileUploadExtensions);
            model.addAttribute("fileUploadMaxSize", fileUploadMaxSize);

            String sLocationUrl = "egovframework/com/dam/spe/req/EgovComDamRequestOfferRegist";

            String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");

            if(sCmd.equals("save") || sCmd.equals("reply")) {
                //서버  validate 체크
                beanValidator.validate(requestOfferVO, bindingResult);
                if(bindingResult.hasErrors()){
                    return sLocationUrl;
                }

            	// 첨부파일 관련 첨부파일ID 생성
        		String _atchFileId = "";

        		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
        		final List<MultipartFile> files = multiRequest.getFiles("file_1");

        		if(!files.isEmpty()){
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
    				_atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.

                	// 리턴받은 첨부파일ID를 셋팅한다..
            		requestOfferVO.setAtchFileId(_atchFileId);
        		}

                //아이디 설정
                requestOfferVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                requestOfferVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

                //(지식전문가/지식사용자) 검사 및 설정
                HashMap<String, String> hmParam = new HashMap<String, String>();
                hmParam.put("speId", loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

                //지식전문가 일때
                if(sCmd.equals("reply") && egovRequestOfferVOService.selectRequestOfferSpeCheck(hmParam)){
                	requestOfferVO.setSpeId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                //지식전문가 아니고 reply 일때
                }else if(sCmd.equals("reply")){
                	return "egovframework/com/dam/spe/req/EgovComDamRequestOfferRegist";
                //일반사용자일때
                }else{
                	requestOfferVO.setEmplyrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                }

                //저장
                egovRequestOfferVOService.insertRequestOffer(requestOfferVO);

                sLocationUrl = "forward:/dam/spe/req/listRequestOffer.do";
            }

            return sLocationUrl;
    }


}
