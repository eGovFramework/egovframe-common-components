package egovframework.com.sym.mnu.mcm.web;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mcm.service.EgovMenuCreateManageService;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.com.sym.mnu.mcm.service.MenuSiteMapVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 메뉴목록 관리및 메뉴생성, 사이트맵 생성을 처리하는 비즈니스 구현 클래스
 *
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *     수정일               수정자             수정내용
 *  ------------   --------    ---------------------------
 *   2009.03.20		이  용             최초 생성
 * 	 2011.07.29		서준식             사이트맵 저장경로 수정
 *	 2011.08.26		정진오             IncludedInfo annotation 추가
 *	 2013.06.17		이기하             사이트맵 생성시 경로 오류 수정
 *   2018.08.09		신용호             X-XSS 관련 크롬에서 오탐되는 부분 수정
 *   2018.09.10		신용호             selectMenuCreatManagList 불필요한 로직 제거
 * </pre>
 */

@Controller
public class EgovMenuCreateManageController {

	/* Validator */
//	@Autowired
//	private DefaultBeanValidator beanValidator;
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMenuManageService */
	@Resource(name = "meunCreateManageService")
	private EgovMenuCreateManageService menuCreateManageService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/*********** 메뉴 생성 관리 ***************/

	/**
	 * *메뉴생성목록을 조회한다.
	 *
	 * @param searchVO ComDefaultVO
	 * @return 출력페이지정보 "sym/mnu/mcm/EgovMenuCreatManage"
	 * @exception Exception
	 */
	@IncludedInfo(name = "메뉴생성관리", order = 1100, gid = 60)
	@RequestMapping(value = "/sym/mnu/mcm/EgovMenuCreatManageSelect.do")
	public String selectMenuCreatManagList(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model)
			throws Exception {
		String resultMsg = "";
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		// 내역 조회
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
		/*
         * if (searchVO.getSearchKeyword() != null && !searchVO.getSearchKeyword().equals("")) {
         * 
         * int IDcnt = menuCreateManageService.selectUsrByPk(searchVO); if (IDcnt == 0) { resultMsg = egovMessageSource.getMessage("info.nodata.msg"); } else { // AuthorCode 검색 MenuCreatVO vo = new MenuCreatVO(); vo = menuCreateManageService.selectAuthorByUsr(searchVO); searchVO.setSearchKeyword(vo.getAuthorCode()); } }
         */
		List<EgovMap> resultList = menuCreateManageService.selectMenuCreatManagList(searchVO);
        if (resultList.size() == 0) {
            resultMsg = egovMessageSource.getMessage("info.nodata.msg");
        }
        model.addAttribute("resultList", resultList);
		
		int totCnt = menuCreateManageService.selectMenuCreatManagTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("resultMsg", resultMsg);
		return "egovframework/com/sym/mnu/mcm/EgovMenuCreatManage";
	}

	/**
     * 메뉴생성 세부화면을 조회한다.
     *
     * @param menuCreatVO MenuCreatVO
     * @return 출력페이지정보 "sym/mnu/mcm/EgovMenuCreat"
     * @exception Exception
     */
    @RequestMapping(value = "/sym/mnu/mcm/EgovMenuCreatSelect.do")
    public String selectMenuCreatList(@ModelAttribute MenuCreatVO menuCreatVO, ModelMap model) throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "redirect:/uat/uia/egovLoginUsr.do";
        }
        List<EgovMap> resultList = menuCreateManageService.selectMenuCreatList(menuCreatVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("resultVO", menuCreatVO);

		return "egovframework/com/sym/mnu/mcm/EgovMenuCreat";
	}

	/**
	 * 메뉴생성처리 및 메뉴생성내역을 등록한다.
	 *
	 * @param checkedAuthorForInsert
	 *            String
	 * @param checkedMenuNoForInsert
	 *            String
	 * @return 출력페이지정보 등록처리시 "forward:/sym/mnu/mcm/EgovMenuCreatSelect.do"
	 * @exception Exception
	 */
	@RequestMapping("/sym/mnu/mcm/EgovMenuCreatInsert.do")
	public String insertMenuCreatList(@RequestParam("checkedAuthorForInsert") String checkedAuthorForInsert, @RequestParam("checkedMenuNoForInsert") String checkedMenuNoForInsert,
			@ModelAttribute("menuCreatVO") MenuCreatVO menuCreatVO, ModelMap model) throws Exception {
		String resultMsg = "";
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		String[] insertMenuNo = checkedMenuNoForInsert.split(",");
		if (insertMenuNo == null || (insertMenuNo.length == 0)) {
			resultMsg = egovMessageSource.getMessage("fail.common.insert");
		} else {
			menuCreateManageService.insertMenuCreatList(checkedAuthorForInsert, checkedMenuNoForInsert);
			resultMsg = egovMessageSource.getMessage("success.common.insert");
		}
		model.addAttribute("resultMsg", resultMsg);
		return "forward:/sym/mnu/mcm/EgovMenuCreatSelect.do";
	}

	/* 메뉴사이트맵 생성조회 */
	/**
	 * 메뉴사이트맵을 생성할 내용을 조회한다.
	 *
	 * @param menuSiteMapVO
	 *            MenuSiteMapVO
	 * @return 출력페이지정보 등록처리시 "sym/mnu/mcm/EgovMenuCreatSiteMap"
	 * @exception Exception
	 */
	@RequestMapping(value = "/sym/mnu/mcm/EgovMenuCreatSiteMapSelect.do")
	public String selectMenuCreatSiteMap(@ModelAttribute("menuSiteMapVO") MenuSiteMapVO menuSiteMapVO, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		List<EgovMap> list_menulist = menuCreateManageService.selectMenuCreatSiteMapList(menuSiteMapVO);
		model.addAttribute("list_menulist", list_menulist);
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		menuSiteMapVO.setCreatPersonId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
		model.addAttribute("resultVO", menuSiteMapVO);
		return "egovframework/com/sym/mnu/mcm/EgovMenuCreatSiteMap";
	}

	/**
	 * 메뉴사이트맵 생성처리 및 사이트맵을 등록한다.
	 * 개발환경에서 테스트용 함수로 보안 취약
	 *
	 * @param menuSiteMapVO
	 *            MenuSiteMapVO
	 * @param valueHtml
	 *            String
	 * @return 출력페이지정보 "sym/mnu/mcm/EgovMenuCreatSiteMap"
	 * @exception Exception
	 */
	/*
	@RequestMapping(value = "/sym/mnu/mcm/EgovMenuCreatSiteMapInsert.do")
	public String selectMenuCreatSiteMapInsert(@ModelAttribute("menuSiteMapVO") MenuSiteMapVO menuSiteMapVO, @RequestParam("valueHtml") String valueHtml, ModelMap model
			,HttpServletResponse response)
			throws Exception {
		boolean chkCreat = false;
		String resultMsg = "";
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		//menuSiteMapVO.setTmpRootPath(EgovProperties.RELATIVE_PATH_PREFIX
		//		+ ".." + System.getProperty("file.separator") + ".."
		//		+ System.getProperty("file.separator") + "..");

		// 사이트맵 파일 생성 위치 지정
		//String currentPath = EgovMenuCreateManageController.class.getResource("").getPath();
		String currentPath = EgovMenuCreateManageController.class.getProtectionDomain().getCodeSource() == null ? "" : EgovStringUtil.isNullToString(EgovMenuCreateManageController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		//System.out.println("===>>> currentPath = "+currentPath);
		String path = currentPath.substring(0, currentPath.lastIndexOf("WEB-INF"));
		menuSiteMapVO.setTmpRootPath(path);
		menuSiteMapVO.setBndeFilePath("/html/egovframework/com/sym/mnu/mcm/");
		//System.out.println("===>>> path = "+path);
		//System.out.println("===>>> menuSiteMapVO.getMapCreatId() = "+menuSiteMapVO.getMapCreatId());
		
		// 사이트맵 파일 생성 위치 지정 if ("WINDOWS".equals(Globals.OS_TYPE)) {
		// menuSiteMapVO
		// .setTmp_rootPath("D:/egovframework/workspace/egovcmm/src/main/webapp"
		// ); }else{menuSiteMapVO.setTmp_rootPath(
		// "/product/jeus/webhome/was_com/egovframework-com-1_0/egovframework-com-1_0_war___"
		// ); }
		
		chkCreat = menuCreateManageService.creatSiteMap(menuSiteMapVO, valueHtml);
		if (!chkCreat) {
			resultMsg = egovMessageSource.getMessage("fail.common.insert");
		} else {
			resultMsg = egovMessageSource.getMessage("success.common.insert");
		}
		List<?> list_menulist = menuCreateManageService.selectMenuCreatSiteMapList(menuSiteMapVO);
		
		model.addAttribute("list_menulist", list_menulist);
		model.addAttribute("resultVO", menuSiteMapVO);
		model.addAttribute("resultMsg", resultMsg);

		return "egovframework/com/sym/mnu/mcm/EgovMenuCreatSiteMap";
	}
	*/

	/* 메뉴사이트맵 생성조회 */
	/**
	 * 메뉴사이트맵을 생성할 내용을 조회한다.
	 *
	 * @param menuSiteMapVO
	 *            MenuSiteMapVO
	 * @return 출력페이지정보 등록처리시 "sym/mnu/mcm/EgovMenuCreatSiteMap"
	 * @exception Exception
	 */
	@RequestMapping(value = "/sym/mnu/mcm/EgovSiteMap.do")
	public String selectSiteMap(@ModelAttribute("menuCreatVO") MenuSiteMapVO menuSiteMapVO, ModelMap model) throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		menuSiteMapVO.setCreatPersonId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

		List<?> list_menulist = menuCreateManageService.selectSiteMapByUser(menuSiteMapVO);
		model.addAttribute("list_menulist", list_menulist);

		model.addAttribute("resultVO", menuSiteMapVO);
		return "egovframework/com/sym/mnu/mcm/EgovSiteMap";
	}

}
