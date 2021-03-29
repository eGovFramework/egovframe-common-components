package egovframework.com.uss.olp.cns.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.EgovXssChecker;
import egovframework.com.uss.olp.cns.service.CnsltManageDefaultVO;
import egovframework.com.uss.olp.cns.service.CnsltManageVO;
import egovframework.com.uss.olp.cns.service.EgovCnsltManageService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 *
 * 상담내용을 처리하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovCnsltManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovCnsltManageController.class);
	
    @Resource(name = "CnsltManageService")
    private EgovCnsltManageService cnsltManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 개별 배포시 메인메뉴를 조회한다.
     * @param model
     * @return	"/uss/sam/cpy/"
     * @throws Exception
     */
    @RequestMapping(value="/uss/olp/cns/EgovMain.do")
    public String egovMain(ModelMap model) throws Exception {
    	return "egovframework/com/uss/olp/cns/EgovMain";
    }

    /**
     * 메뉴를 조회한다.
     * @param model
     * @return	"/uss/sam/cpy/EgovLeft"
     * @throws Exception
     */
    @RequestMapping(value="/uss/olp/cns/EgovLeft.do")
    public String egovLeft(ModelMap model) throws Exception {
    	return "egovframework/com/uss/olp/cns/EgovLeft";
    }


    /**
     * 상담정보 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return	"/uss/olp/cns/EgovCnsltListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="상담관리", order = 580 ,gid = 50)
    @RequestMapping(value="/uss/olp/cns/CnsltListInqire.do")
    public String selectCnsltList(@ModelAttribute("searchVO") CnsltManageDefaultVO searchVO, ModelMap model) throws Exception {

    	/** EgovPropertyService.SiteList */
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

        List<?> CnsltList = cnsltManageService.selectCnsltList(searchVO);
        model.addAttribute("resultList", CnsltList);

    	// 인증여부 체크
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	//isAuthenticated = false;

    	if(!isAuthenticated) {

    		model.addAttribute("certificationAt", "N");

    	} else	{

    		model.addAttribute("certificationAt", "Y");

    	}


        int totCnt = cnsltManageService.selectCnsltListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/olp/cns/EgovCnsltListInqire";
    }

    /**
     * 상담정보 목록에 대한 상세정보를 조회한다.
     * @param passwordConfirmAt
     * @param cnsltManageVO
     * @param searchVO
     * @param model
     * @return	"/uss/olp/cns/EgovCnsltDetailInqire"
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
	@RequestMapping("/uss/olp/cns/CnsltDetailInqire.do")
    public String	selectCnsltListDetail(@RequestParam("passwordConfirmAt") String passwordConfirmAt ,
    		CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO,
            ModelMap model) throws Exception {


		CnsltManageVO vo = cnsltManageService.selectCnsltListDetail(cnsltManageVO);

		vo.setPasswordConfirmAt(passwordConfirmAt);    		// 작성비밀번호 확인여부

		// 작성 비밀번호를 얻는다.
		String	writngPassword = vo.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
    	vo.setWritngPassword(EgovFileScrty.decode(writngPassword));

		model.addAttribute("result", vo);

        return	"egovframework/com/uss/olp/cns/EgovCnsltDetailInqire";
    }

    /**
     * Q&A 조회수를  수정처리 한다.
     * @param cnsltManageVO
     * @param searchVO
     * @return	"forward:/uss/olp/cns/CnsltDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olp/cns/CnsltInqireCoUpdt.do")
    public String updateCnsltInqireCo(
            CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO)
            throws Exception {

    	cnsltManageService.updateCnsltInqireCo(cnsltManageVO);

        return "forward:/uss/olp/cns/CnsltDetailInqire.do";

    }


    /**
     * 로그인이나 실명확인 처리를 한다.
     * @param cnsltManageVO
     * @param searchVO
     * @param model
     * @return	/uss/olp/cns/EgovLoginRealnmChoice
     * @throws Exception
     */
    @RequestMapping("/uss/olp/cns/LoginRealnmChoice.do")
    public String selectLoginRealnmChoice(
            CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO,
            Model	model)
            throws Exception {

        model.addAttribute("CnsltManageVO", new CnsltManageVO());

        return "egovframework/com/uss/olp/cns/EgovCnsltLoginRealnmChoice";
    }


    /**
     * 상담정보를 등록하기 위한 전 처리(인증체크)
     * @param searchVO
     * @param cnsltManageVO
     * @param model
     * @return	"/uss/olp/cns/EgovCnsltDtlsRegist"
     * @throws Exception
     */
    @RequestMapping("/uss/olp/cns/CnsltDtlsRegistView.do")
    public String insertCnsltDtlsView(
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO,
            CnsltManageVO cnsltManageVO,
            Model model)
            throws Exception {

    	// 인증여부 체크
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	//isAuthenticated = false;

    	if(!isAuthenticated) {

    		model.addAttribute("result", cnsltManageVO);

    		return "egovframework/com/uss/olp/cns/EgovCnsltDtlsRegist";

    	}


        // 로그인VO에서  사용자 정보 가져오기
        LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        String	wrterNm 	= loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName());					// 사용자명
        String	emailAdres 	= loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getEmail());					// email 주소

        cnsltManageVO.setWrterNm(wrterNm);							// 작성자명
        cnsltManageVO.setEmailAdres(emailAdres);    				// email 주소

        model.addAttribute("result", cnsltManageVO);

        return "egovframework/com/uss/olp/cns/EgovCnsltDtlsRegist";
    }

    /**
     * 상담정보를 등록한다.
     * @param multiRequest
     * @param searchVO
     * @param cnsltManageVO
     * @param bindingResult
     * @param model
     * @return	"forward:/uss/olp/cns/CnsltListInqire.do"
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
	@RequestMapping("/uss/olp/cns/CnsltDtlsRegist.do")
    public String insertCnsltDtls(
    		final MultipartHttpServletRequest multiRequest,		// 첨부파일을 위한...
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO,
            @ModelAttribute("cnsltManageVO") CnsltManageVO cnsltManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {

    	beanValidator.validate(cnsltManageVO, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/uss/olp/cns/EgovCnsltDtlsRegist";

		}

    	// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> _result = null;
		String _atchFileId = "";

		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if(!files.isEmpty()){
		 _result = fileUtil.parseFileInf(files, "CNSLT_", 0, "", "");
		 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		}

    	// 리턴받은 첨부파일ID를 셋팅한다..
		cnsltManageVO.setAtchFileId(_atchFileId);			// 첨부파일 ID

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String	frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	cnsltManageVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	cnsltManageVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID

    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = EgovStringUtil.isNullToString(cnsltManageVO.getWritngPassword());//KISA 보안약점 조치 (2018-10-29, 윤창원)

    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	cnsltManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));

        cnsltManageService.insertCnsltDtls(cnsltManageVO);

        return "forward:/uss/olp/cns/CnsltListInqire.do";
    }

    /**
     * 작성 비밀번호를 확인하기 위한 전 처리
     * @param cnsltManageVO
     * @param searchVO
     * @param model
     * @return	"/uss/olp/cns/EgovCnsltPasswordConfirm"
     * @throws Exception
     */
    @RequestMapping("/uss/olp/cns/CnsltPasswordConfirmView.do")
    public String selectPasswordConfirmView(
            CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO,
            Model	model)
            throws Exception {

        model.addAttribute("CnsltManageVO", new CnsltManageVO());

        return "egovframework/com/uss/olp/cns/EgovCnsltPasswordConfirm";
    }

    /**
     * 작성 비밀번호를 확인한다.
     * @param cnsltManageVO
     * @param searchVO
     * @param model
     * @return	"forward:/uss/olp/cns/CnsltDetailInqire.do"
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
	@RequestMapping("/uss/olp/cns/CnsltPasswordConfirm.do")
    public String selectPasswordConfirm(
            CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO,
            Model	model)
            throws Exception {

    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = EgovStringUtil.isNullToString(cnsltManageVO.getWritngPassword());//KISA 보안약점 조치 (2018-10-29, 윤창원)

    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	cnsltManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));

        int searchCnt = cnsltManageService.selectCnsltPasswordConfirmCnt(cnsltManageVO);

        if ( searchCnt > 0) {		// 작성 비밀번호가 일치하는 경우

        	// 상담정보를 수정할 수 있는 화면으로 이동.
        	return	"forward:/uss/olp/cns/CnsltDtlsUpdtView.do";

        } else	{					// 작성비밀번호가 틀린경우

        	// 작성비밀번호 확인 결과 세팅.
        	//cnsltManageVO.setPasswordConfirmAt("N");

        	String	passwordConfirmAt = "N";

        	// Q&A 상세조회 화면으로 이동.
        	return "forward:/uss/olp/cns/CnsltDetailInqire.do?passwordConfirmAt=" + passwordConfirmAt;


        }

    }

    /**
     * 상담정보를 수정하기 위한 전 처리(비밀번호 복호화)
     * @param cnsltManageVO
     * @param searchVO
     * @param model
     * @return	"/uss/olp/cns/EgovCnsltDtlsUpdt"
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
	@RequestMapping("/uss/olp/cns/CnsltDtlsUpdtView.do")
    public String updateCnsltDtlsView(
    		CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO, ModelMap model)
            throws Exception {

		CnsltManageVO vo = cnsltManageService.selectCnsltListDetail(cnsltManageVO);

		// 작성 비밀번호를 얻는다.
		String	writngPassword = vo.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
    	vo.setWritngPassword(EgovFileScrty.decode(writngPassword));

        // 복호화된 패스워드를 넘긴다..
        model.addAttribute("cnsltManageVO", vo);

        // result에도 세팅(jstl 사용을 위해)
        model.addAttribute(selectCnsltListDetail("Y", cnsltManageVO, searchVO, model));

        return "egovframework/com/uss/olp/cns/EgovCnsltDtlsUpdt";
    }

    /**
     * 상담정보를 수정처리한다.
     * @param atchFileAt
     * @param multiRequest
     * @param searchVO
     * @param cnsltManageVO
     * @param bindingResult
     * @param model
     * @return	"forward:/uss/olp/cns/CnsltListInqire.do"
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
	@RequestMapping("/uss/olp/cns/CnsltDtlsUpdt.do")
    public String updateCnsltDtls(@RequestParam("atchFileAt") String atchFileAt ,
    		final MultipartHttpServletRequest multiRequest,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO,
            @ModelAttribute("cnsltManageVO") CnsltManageVO cnsltManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {

    	// Validation
    	beanValidator.validate(cnsltManageVO, bindingResult);

		if(bindingResult.hasErrors()){

			return "egovframework/com/uss/olp/cns/EgovCnsltDtlsUpdt";

		}


    	// 첨부파일 관련 ID 생성 start....
		String _atchFileId = cnsltManageVO.getAtchFileId();

		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
		final List<MultipartFile> files = multiRequest.getFiles("file_1");

		if(!files.isEmpty()){

			if("N".equals(atchFileAt)){
				List<FileVO> _result = fileUtil.parseFileInf(files, "CNSLT_", 0, _atchFileId, "");
				_atchFileId = fileMngService.insertFileInfs(_result);

				// 첨부파일 ID 셋팅
				cnsltManageVO.setAtchFileId(_atchFileId);    	// 첨부파일 ID

			}else{
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(_atchFileId);
				int _cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO> _result = fileUtil.parseFileInf(files, "CNSLT_", _cnt, _atchFileId, "");
				fileMngService.updateFileInfs(_result);
			}
		}
		// 첨부파일 관련 ID 생성 end...


    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String	lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	cnsltManageVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID

    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = EgovStringUtil.isNullToString(cnsltManageVO.getWritngPassword());//KISA 보안약점 조치 (2018-10-29, 윤창원)

    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	cnsltManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));

    	cnsltManageService.updateCnsltDtls(cnsltManageVO);


    	return "forward:/uss/olp/cns/CnsltListInqire.do";

    }

    /**
     * 상담정보를 삭제처리한다.
     * @param cnsltManageVO
     * @param searchVO
     * @return	"forward:/uss/olp/cns/CnsltListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olp/cns/CnsltDtlsDelete.do")
    public String deleteCnsltDtls(
    		HttpServletRequest request,
            CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO)
            throws Exception {

    	//--------------------------------------------------------------------------------------------
    	// @ XSS 사용자권한체크 START
    	// param1 : 사용자고유ID(uniqId,esntlId)
    	//--------------------------------------------------------
    	LOGGER.debug("@ XSS 권한체크 START ----------------------------------------------");;
    	
    	//step1 DB에서 해당 게시물의 uniqId 조회
    	CnsltManageVO vo = cnsltManageService.selectCnsltListDetail(cnsltManageVO);
    	
    	//step2 EgovXssChecker 공통모듈을 이용한 권한체크
    	EgovXssChecker.checkerUserXss(request, vo.getFrstRegisterId()); 
      	LOGGER.debug("@ XSS 권한체크 END ------------------------------------------------");
    	//--------------------------------------------------------
    	// @ XSS 사용자권한체크 END
    	//--------------------------------------------------------------------------------------------

    	// 첨부파일 삭제를 위한 ID 생성 start....
		String _atchFileId = cnsltManageVO.getAtchFileId();

    	cnsltManageService.deleteCnsltDtls(cnsltManageVO);

    	// 첨부파일을 삭제하기 위한  Vo
    	FileVO fvo = new FileVO();
    	fvo.setAtchFileId(_atchFileId);

    	fileMngService.deleteAllFileInf(fvo);
    	// 첨부파일 삭제 End.............
	
        return "forward:/uss/olp/cns/CnsltListInqire.do";
    }


    /**
     * Q&A답변정보 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return	"/uss/olp/cns/EgovCnsltAnswerListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="상담답변관리", order = 581 ,gid = 50)
    @RequestMapping(value="/uss/olp/cnm/CnsltAnswerListInqire.do")
    public String selectCnsltAnswerList(@ModelAttribute("searchVO") CnsltManageDefaultVO searchVO, ModelMap model) throws Exception {

    	/** EgovPropertyService.SiteList */
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

        List<?> CnsltAnswerList = cnsltManageService.selectCnsltAnswerList(searchVO);
        model.addAttribute("resultList", CnsltAnswerList);

        int totCnt = cnsltManageService.selectCnsltAnswerListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/olp/cns/EgovCnsltAnswerListInqire";
    }

    /**
     * Q&A답변정보 목록에 대한 상세정보를 조회한다.
     * @param cnsltManageVO
     * @param searchVO
     * @param model
     * @return	"/uss/olp/cns/EgovCnsltAnswerDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/uss/olp/cnm/CnsltAnswerDetailInqire.do")
    public String	selectCnsltAnswerListDetail(
    		CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO,
            ModelMap model) throws Exception {

		CnsltManageVO vo = cnsltManageService.selectCnsltListDetail(cnsltManageVO);

		model.addAttribute("result", vo);

        return "egovframework/com/uss/olp/cns/EgovCnsltAnswerDetailInqire";
    }


    /**
     * Q&A답변정보를 수정하기 위한 전 처리(공통코드처리)
     * @param cnsltManageVO
     * @param searchVO
     * @param model
     * @return	"/uss/olp/cns/EgovCnsltDtlsAnswerUpdt"
     * @throws Exception
     */
    @RequestMapping("/uss/olp/cnm/CnsltDtlsAnswerUpdtView.do")
    public String updateCnsltDtlsAnswerView(
    		CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO, ModelMap model)
            throws Exception {


    	// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM028");

		List<?> _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("resultList", _result);

        // 변수명은 CoC 에 따라
        model.addAttribute(selectCnsltAnswerListDetail(cnsltManageVO, searchVO, model));

        return "egovframework/com/uss/olp/cns/EgovCnsltDtlsAnswerUpdt";
    }

    /**
     * Q&A답변정보를 수정처리한다.
     * @param cnsltManageVO
     * @param searchVO
     * @return	"forward:/uss/olp/cnm/CnsltAnswerListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/uss/olp/cnm/CnsltDtlsAnswerUpdt.do")
    public String updateCnsltDtlsAnswer(
            CnsltManageVO cnsltManageVO,
            @ModelAttribute("searchVO") CnsltManageDefaultVO searchVO)
            throws Exception {

    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String	lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

    	cnsltManageVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID

    	cnsltManageService.updateCnsltDtlsAnswer(cnsltManageVO);

        return "forward:/uss/olp/cnm/CnsltAnswerListInqire.do";

    }


}
