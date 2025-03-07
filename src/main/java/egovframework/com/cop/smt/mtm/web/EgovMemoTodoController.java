package egovframework.com.cop.smt.mtm.web;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.mtm.service.EgovMemoTodoService;
import egovframework.com.cop.smt.mtm.service.MemoTodo;
import egovframework.com.cop.smt.mtm.service.MemoTodoVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 개요
 * - 메모할일에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 메모할일에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 메모할일의 조회기능은 목록조회, 상세조회, 오늘의 할일조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:46
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovMemoTodoController {
	
	 
	 

	@Resource(name="EgovMemoTodoService")
    protected EgovMemoTodoService memoTodoService;
	
	@Resource(name="propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
    
    //Logger log = Logger.getLogger(this.getClass());

	/**
	 * 메모할일 정보에 대한 목록을 조회한다.
	 * @param MemoTodoVO - 메모할일 VO
	 * @return  String - 리턴 URL
	 * 
	 * @param memoTodoVO
	 */
    @IncludedInfo(name="메모할일관리", order = 420 ,gid = 40)
    @RequestMapping("/cop/smt/mtm/selectMemoTodoList.do")
	public String selectMemoTodoList(@ModelAttribute("searchVO") MemoTodoVO memoTodoVO, ModelMap model) throws Exception{
    	//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }
		
		memoTodoVO.setPageUnit(propertyService.getInt("pageUnit"));
		memoTodoVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(memoTodoVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(memoTodoVO.getPageUnit());
		paginationInfo.setPageSize(memoTodoVO.getPageSize());

		memoTodoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		memoTodoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		memoTodoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		memoTodoVO.setSearchId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
	
		Map<String, Object> map = memoTodoService.selectMemoTodoList(memoTodoVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/smt/mtm/EgovMemoTodoList";
	}

	/**
	 * 메모할일 정보를 조회한다.
	 * @param MemoTodoVO - 메모할일 VO
	 * @return  String - 리턴 URL
	 * 
	 * @param memoTodoVO
	 */
    @RequestMapping("/cop/smt/mtm/selectMemoTodo.do")
	public String selectMemoTodo(@ModelAttribute("memoTodoVO") MemoTodoVO memoTodoVO, ModelMap model) throws Exception{
    	MemoTodo memoTodo = memoTodoService.selectMemoTodo(memoTodoVO);
		model.addAttribute("memoTodo", memoTodo);
    	
  
		return "egovframework/com/cop/smt/mtm/EgovMemoTodoDetail";
	}

	/**
	 * 메모할일 정보의 등록페이지로 이동한다.
	 * @param MemoTodo - 메모할일 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoTodo
	 */
    @RequestMapping("/cop/smt/mtm/addMemoTodo.do")
	public String addMemoTodo(@ModelAttribute("memoTodoVO") MemoTodoVO memoTodoVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	String sLocationUrl = "egovframework/com/cop/smt/mtm/EgovMemoTodoRegist"; 
		
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	
    	// 1. 로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
    	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.KOREA);
		memoTodoVO.setTodoDe(formatter.format(new java.util.Date()));
    	memoTodoVO.setWrterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
    	memoTodoVO.setWrterNm(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()));
    	
    	//할일시작일자(시)
    	model.addAttribute("todoBeginHour", (List<ComDefaultCodeVO>)getTimeHH());
    	//할일시작일자(분)
    	model.addAttribute("todoBeginMin", (List<ComDefaultCodeVO>)getTimeMM());
    	//할일종료일자(시)
    	model.addAttribute("todoEndHour", (List<ComDefaultCodeVO>)getTimeHH());
    	//할일정료일자(분)
    	model.addAttribute("todoEndMin", (List<ComDefaultCodeVO>)getTimeMM());
    	
    	return sLocationUrl; 	
	}

	/**
	 * 메모할일 정보의 수정페이지로 이동한다.
	 * @param MemoTodo - 메모할일 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoTodo
	 */
    @RequestMapping("/cop/smt/mtm/modifyMemoTodo.do")
	public String modifyMemoTodo(@ModelAttribute("memoTodoVO") MemoTodoVO memoTodoVO, BindingResult bindingResult, ModelMap model) throws Exception{
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
		
    	//할일시작일자(시)
    	model.addAttribute("todoBeginHour", (List<ComDefaultCodeVO>)getTimeHH());
    	//할일시작일자(분)
    	model.addAttribute("todoBeginMin", (List<ComDefaultCodeVO>)getTimeMM());
    	//할일종료일자(시)
    	model.addAttribute("todoEndHour", (List<ComDefaultCodeVO>)getTimeHH());
    	//할일정료일자(분)
    	model.addAttribute("todoEndMin", (List<ComDefaultCodeVO>)getTimeMM());
    	
    	MemoTodoVO resultVO = memoTodoService.selectMemoTodo(memoTodoVO);
		resultVO.setSearchCnd(memoTodoVO.getSearchCnd());
		resultVO.setSearchWrd(memoTodoVO.getSearchWrd());
		resultVO.setSearchBgnDe(memoTodoVO.getSearchBgnDe());
		resultVO.setSearchEndDe(memoTodoVO.getSearchEndDe());
		resultVO.setSearchDe(memoTodoVO.getSearchDe());
		resultVO.setPageIndex(memoTodoVO.getPageIndex());
        model.addAttribute("memoTodoVO", resultVO);
        
		return "egovframework/com/cop/smt/mtm/EgovMemoTodoUpdt";
	}

	/**
	 * 메모할일 정보를 수정한다.
	 * @param MemoTodo - 메모할일 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoTodo
	 */
    @RequestMapping("/cop/smt/mtm/updateMemoTodo.do")
	public String updateMemoTodo(@ModelAttribute("memoTodoVO") MemoTodoVO memoTodoVO, BindingResult bindingResult, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(memoTodoVO, bindingResult);
		if (bindingResult.hasErrors()) {
			MemoTodo memoTodo = memoTodoService.selectMemoTodo(memoTodoVO);
		    model.addAttribute("memoTodo", memoTodo);
		    return "egovframework/com/cop/smt/mtm/EgovMemoTodoUpdt";
		}

		if (isAuthenticated) {
			memoTodoVO.setTodoBeginTime(memoTodoVO.getTodoDe() + memoTodoVO.getTodoBeginHour() + memoTodoVO.getTodoBeginMin());
			memoTodoVO.setTodoEndTime(memoTodoVO.getTodoDe() + memoTodoVO.getTodoEndHour() + memoTodoVO.getTodoEndMin());
			
    		memoTodoVO.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));		    
    		memoTodoService.updateMemoTodo(memoTodoVO);
		}

		return "forward:/cop/smt/mtm/selectMemoTodoList.do";
	}

	/**
	 * 메모할일 정보를 등록한다.
	 * @param MemoTodo - 메모할일 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoTodo
	 */
    @RequestMapping("/cop/smt/mtm/insertMemoTodo.do")
	public String insertMemoTodo(@ModelAttribute("memoTodoVO") MemoTodoVO memoTodoVO, BindingResult bindingResult, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		String sLocationUrl = "egovframework/com/cop/smt/mtm/EgovMemoTodoRegist"; 
		
		//서버  validate 체크
        beanValidator.validate(memoTodoVO, bindingResult);
		if(bindingResult.hasErrors()){
			return sLocationUrl;
		}
		
		memoTodoVO.setTodoBeginTime(memoTodoVO.getTodoDe() + memoTodoVO.getTodoBeginHour() + memoTodoVO.getTodoBeginMin());
		memoTodoVO.setTodoEndTime(memoTodoVO.getTodoDe() + memoTodoVO.getTodoEndHour() + memoTodoVO.getTodoEndMin());
		//아이디 설정
		memoTodoVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		memoTodoVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
		memoTodoService.insertMemoTodo(memoTodoVO);
    	sLocationUrl = "forward:/cop/smt/mtm/selectMemoTodoList.do";
        
        return sLocationUrl;
	}

	/**
	 * 메모할일 정보를 삭제한다.
	 * @param MemoTodo - 메모할일 model
	 * @return  String - 리턴 URL
	 * 
	 * @param memoTodo
	 */
    @RequestMapping("/cop/smt/mtm/deleteMemoTodo.do")
	public String deleteMemoTodo(@ModelAttribute("memoTodoVO") MemoTodoVO memoTodoVO, ModelMap model) throws Exception{
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/uat/uia/egovLoginUsr.do";
    	}
    	memoTodoService.deleteMemoTodo(memoTodoVO);
		return "forward:/cop/smt/mtm/selectMemoTodoList.do";
	}

	/**
	 * 메모할일 정보 중 오늘의 할일 대한 목록을 조회한다.
	 * @param MemoTodoVO - 메모할일 VO
	 * @return  String - 리턴 URL
	 * 
	 * @param memoTodoVO
	 */
    @RequestMapping("/cop/smt/mtm/selectMemoTodoListToday.do")
	public String selectMemoTodoListToday(@ModelAttribute("searchVO") MemoTodoVO memoTodoVO, ModelMap model) throws Exception{
		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "redirect:/uat/uia/egovLoginUsr.do";
        }
		
		memoTodoVO.setSearchId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.KOREA);
		String strToday = formatter.format(new java.util.Date());
		memoTodoVO.setSearchBgnDe(strToday + "0000");
		memoTodoVO.setSearchEndDe(strToday + "2359");
		
		List<MemoTodoVO> memoTodoList = memoTodoService.selectMemoTodoListToday(memoTodoVO);
		model.addAttribute("resultList", memoTodoList);
		model.addAttribute("resultToday", strToday);

		return "egovframework/com/cop/smt/mtm/EgovMemoTodoListToday";
	}
    
    /**
	 * 시간의 LIST를 반환한다.
	 * @return  List
	 * @throws 
	 */
	private List<ComDefaultCodeVO> getTimeHH (){
    	ArrayList<ComDefaultCodeVO> listHH = new ArrayList<ComDefaultCodeVO>();
    	//HashMap hmHHMM;
    	for(int i=0;i < 24; i++){
    		String sHH = "";
    		String strI = String.valueOf(i);
    		if(i<10){
    			sHH = "0" + strI;
    		}else{
    			sHH = strI;
    		}
    		
    		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
    		codeVO.setCode(sHH);
    		codeVO.setCodeNm(sHH);
    		
    		listHH.add(codeVO);
    	}
    	
    	return listHH;
	}

	/**
	 * 분의 LIST를 반환한다.
	 * @return  List
	 * @throws 
	 */
	private List<ComDefaultCodeVO> getTimeMM (){
    	ArrayList<ComDefaultCodeVO> listMM = new ArrayList<ComDefaultCodeVO>();
    	//HashMap hmHHMM;
    	for(int i=0;i < 60; i++){
    		
    		String sMM = "";
    		String strI = String.valueOf(i);
    		if(i<10){
    			sMM = "0" + strI;
    		}else{
    			sMM = strI;
    		}

    		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
    		codeVO.setCode(sMM);
    		codeVO.setCodeNm(sMM);
    		
    		listMM.add(codeVO);
    	}
    	return listMM;
	}

}