package egovframework.com.sec.rmt.web;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sec.rmt.service.EgovRoleManageService;
import egovframework.com.sec.rmt.service.RoleManage;
import egovframework.com.sec.rmt.service.RoleManageVO;

/**
 * 롤관리에 관한 controller 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */


@Controller
@SessionAttributes(types=SessionVO.class)
public class EgovRoleManageController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovRoleManageService")
    private EgovRoleManageService egovRoleManageService;

    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService egovCmmUseService;

    @Resource(name = "egovAuthorManageService")
    private EgovAuthorManageService egovAuthorManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Message ID Generation */
    @Resource(name="egovRoleIdGnrService")
    private EgovIdGnrService egovRoleIdGnrService;

    @Autowired
	private DefaultBeanValidator beanValidator;

    /**
	 * 롤 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/sec/rmt/EgovRoleListView.do")
    public String selectRoleListView()
            throws Exception {
        return "egovframework/com/sec/rmt/EgovRoleManage";
    }

	/**
	 * 등록된 롤 정보 목록 조회
	 * @param roleManageVO RoleManageVO
	 * @return String
	 * @exception Exception
	 */
    @IncludedInfo(name="롤관리", listUrl="/sec/rmt/EgovRoleList.do", order = 90,gid = 20)
    @RequestMapping(value="/sec/rmt/EgovRoleList.do")
	public String selectRoleList(@ModelAttribute("roleManageVO") RoleManageVO roleManageVO,
			                      ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(roleManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(roleManageVO.getPageUnit());
		paginationInfo.setPageSize(roleManageVO.getPageSize());

		roleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		roleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		roleManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		roleManageVO.setRoleManageList(egovRoleManageService.selectRoleList(roleManageVO));
        model.addAttribute("roleList", roleManageVO.getRoleManageList());

        int totCnt = egovRoleManageService.selectRoleListTotCnt(roleManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/sec/rmt/EgovRoleManage";
	}

	/**
	 * 등록된 롤 정보 조회
	 * @param roleCode String
	 * @param roleManageVO RoleManageVO
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sec/rmt/EgovRole.do")
	public String selectRole(@RequestParam("roleCode") String roleCode,
	                         @ModelAttribute("roleManageVO") RoleManageVO roleManageVO,
	                         @ModelAttribute("authorManageVO") AuthorManageVO authorManageVO,
		                      ModelMap model) throws Exception {

    	roleManageVO.setRoleCode(roleCode);

    	authorManageVO.setAuthorManageList(egovAuthorManageService.selectAuthorAllList(authorManageVO));

    	model.addAttribute("roleManage", egovRoleManageService.selectRole(roleManageVO));
        model.addAttribute("authorManageList", authorManageVO.getAuthorManageList());
        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM029"));

        return "egovframework/com/sec/rmt/EgovRoleUpdate";
	}

    /**
	 * 롤 등록화면 이동
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/sec/rmt/EgovRoleInsertView.do")
    public String insertRoleView(@ModelAttribute("authorManageVO") AuthorManageVO authorManageVO,
    								@ModelAttribute("roleManage") RoleManage roleManage,
    									ModelMap model) throws Exception {

    	authorManageVO.setAuthorManageList(egovAuthorManageService.selectAuthorAllList(authorManageVO));
        model.addAttribute("authorManageList", authorManageVO.getAuthorManageList());
        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM029"));

        return "egovframework/com/sec/rmt/EgovRoleInsert";
    }

    /**
	 * 공통코드 호출
	 * @param comDefaultCodeVO ComDefaultCodeVO
	 * @param codeId String
	 * @return List
	 * @exception Exception
	 */
    public List<CmmnDetailCode> getCmmCodeDetailList(ComDefaultCodeVO comDefaultCodeVO, String codeId)  throws Exception {
    	comDefaultCodeVO.setCodeId(codeId);
    	return egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
    }

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
	 * @param roleManage RoleManage
	 * @param roleManageVO RoleManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sec/rmt/EgovRoleInsert.do")
	public String insertRole(@ModelAttribute("roleManage") RoleManage roleManage,
			                 @ModelAttribute("roleManageVO") RoleManageVO roleManageVO,
			                  BindingResult bindingResult,
                              ModelMap model) throws Exception {

    	beanValidator.validate(roleManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
			return "egovframework/com/sec/rmt/EgovRoleInsert";
		} else {
    	    String roleTyp = roleManage.getRoleTyp();
	    	if("method".equals(roleTyp))//KISA 보안약점 조치 (2018-10-29, 윤창원)
	    		roleTyp = "mtd";
	    	else if("pointcut".equals(roleTyp))//KISA 보안약점 조치 (2018-10-29, 윤창원)
	    		roleTyp = "pct";
	    	else roleTyp = "web";

	    	roleManage.setRoleCode(roleTyp.concat("-").concat(egovRoleIdGnrService.getNextStringId()));
	    	roleManageVO.setRoleCode(roleManage.getRoleCode());

	        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM029"));
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	        model.addAttribute("roleManage", egovRoleManageService.insertRole(roleManage, roleManageVO));

	        //return "egovframework/com/sec/rmt/EgovRoleUpdate";
	        return "forward:/sec/rmt/EgovRoleList.do";
		}
	}

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
	 * @param roleManage RoleManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sec/rmt/EgovRoleUpdate.do")
	public String updateRole(@ModelAttribute("roleManage") RoleManage roleManage,
			BindingResult bindingResult,
            ModelMap model) throws Exception {

    	beanValidator.validate(roleManage, bindingResult); //validation 수행
    	if (bindingResult.hasErrors()) {
			return "egovframework/com/sec/rmt/EgovRoleUpdate";
		} else {
    	egovRoleManageService.updateRole(roleManage);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
    	//return "forward:/sec/rmt/EgovRole.do";
    	return "forward:/sec/rmt/EgovRoleList.do";
		}
	}

	/**
	 * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param roleManage RoleManage
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sec/rmt/EgovRoleDelete.do")
	public String deleteRole(@ModelAttribute("roleManage") RoleManage roleManage,
            ModelMap model) throws Exception {
    	egovRoleManageService.deleteRole(roleManage);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/sec/rmt/EgovRoleList.do";

	}

	/**
	 * 불필요한 그룹정보 목록을 화면에 조회하여 데이터베이스에서 삭제
	 * @param roleCodes String
	 * @param roleManage RoleManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/sec/rmt/EgovRoleListDelete.do")
	public String deleteRoleList(@RequestParam("roleCodes") String roleCodes,
			                     @ModelAttribute("roleManage") RoleManage roleManage,
	                              Model model) throws Exception {
    	String [] strRoleCodes = roleCodes.split(";");
    	for(int i=0; i<strRoleCodes.length;i++) {
    		roleManage.setRoleCode(strRoleCodes[i]);
    		egovRoleManageService.deleteRole(roleManage);
    	}

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sec/rmt/EgovRoleList.do";
	}

}