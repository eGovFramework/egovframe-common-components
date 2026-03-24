package egovframework.com.sec.ram.web;

import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.sec.ram.service.AuthorRoleManage;
import egovframework.com.sec.ram.service.AuthorRoleManageVO;
import egovframework.com.sec.ram.service.EgovAuthorRoleManageService;
import jakarta.annotation.Resource;
import org.egovframe.rte.fdl.access.bean.AuthorityResourceMetadata;
import org.egovframe.rte.fdl.security.bean.EgovReloadableFilterInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 권한별 롤관리에 관한 controller 클래스를 정의한다.
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
 *   2011.09.07  서준식          롤 등록시 이미 등록된 경우 데이터 중복 에러 발생 문제 수정
 * </pre>
 */
@Controller
public class EgovAuthorRoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovAuthorRoleController.class);

	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "egovAuthorRoleManageService")
    private EgovAuthorRoleManageService egovAuthorRoleManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    private EgovPropertyService propertiesService;

    /** Security Role Reload  */
    @Autowired(required = false)
    private EgovReloadableFilterInvocationSecurityMetadataSource egovReloadableFilterInvocationSecurityMetadataSource;

    /** Access(Session) Role Reload */
    @Autowired(required = false)
    private AuthorityResourceMetadata authorityResourceMetadata;

    /**
	 * 권한 롤 관계 화면 이동
	 * @return "egovframework/com/sec/ram/EgovDeptAuthorList"
	 * @exception Exception
	 */
    @RequestMapping("/sec/ram/EgovAuthorRoleListView.do")
    public String selectAuthorRoleListView() throws Exception {
        return "egovframework/com/sec/ram/EgovAuthorRoleManage";
    }

	/**
	 * 권한별 할당된 롤 목록 조회
	 *
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sec/ram/EgovAuthorRoleList.do")
	public String selectAuthorRoleList(@ModelAttribute("authorRoleManageVO") AuthorRoleManageVO authorRoleManageVO,
			                            ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorRoleManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorRoleManageVO.getPageUnit());
		paginationInfo.setPageSize(authorRoleManageVO.getPageSize());

		authorRoleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorRoleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorRoleManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		authorRoleManageVO.setAuthorRoleList(egovAuthorRoleManageService.selectAuthorRoleList(authorRoleManageVO));
        model.addAttribute("authorRoleList", authorRoleManageVO.getAuthorRoleList());
        model.addAttribute("searchVO", authorRoleManageVO);

        int totCnt = egovAuthorRoleManageService.selectAuthorRoleListTotCnt(authorRoleManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/sec/ram/EgovAuthorRoleManage";
	}

	/**
	 * 권한정보에 롤을 할당하여 데이터베이스에 등록
	 * @param authorCode String
	 * @param roleCodes String
	 * @param regYns String
	 * @param authorRoleManage AuthorRoleManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/sec/ram/EgovAuthorRoleInsert.do")
	public String insertAuthorRole(@RequestParam("authorCode") String authorCode,
			                       @RequestParam("roleCodes") String roleCodes,
			                       @RequestParam("regYns") String regYns,
			                       @RequestParam Map<?, ?> commandMap,
			                       @ModelAttribute("authorRoleManage") AuthorRoleManage authorRoleManage,
			                         ModelMap model) throws Exception {
		// 2026.03.23 kisa 보안점검 대응 조치
		if (roleCodes != null && regYns != null) {
	    	String [] strRoleCodes = roleCodes.split(";");
	    	String [] strRegYns = regYns.split(";");
	
	    	authorRoleManage.setRoleCode(authorCode);
	
	    	for(int i=0; i<strRoleCodes.length;i++) {
	
	    		authorRoleManage.setRoleCode(strRoleCodes[i]);
	    		authorRoleManage.setRegYn(strRegYns[i]);
	    		if(strRegYns[i].equals("Y")){
	    			egovAuthorRoleManageService.deleteAuthorRole(authorRoleManage);//2011.09.07
	    			egovAuthorRoleManageService.insertAuthorRole(authorRoleManage);
	    		}else {
	    			egovAuthorRoleManageService.deleteAuthorRole(authorRoleManage);
	    		}
	    	}
		}

    	if ("security".equals(EgovProperties.getProperty("Globals.Auth").trim())) {
    		if (egovReloadableFilterInvocationSecurityMetadataSource != null) {
    			egovReloadableFilterInvocationSecurityMetadataSource.reload();
    			LOGGER.info("### Security - Role Mappings reloaded after author role update.");
    		} else {
    			LOGGER.debug("### Security - EgovReloadableFilterInvocationSecurityMetadataSource bean not available.");
    		}
    	} else if ("session".equals(EgovProperties.getProperty("Globals.Auth").trim())) {
    		if (authorityResourceMetadata != null) {
    			authorityResourceMetadata.reload();
    			LOGGER.info("### Access(Session) - Role Mappings reloaded after author role update.");
    		} else {
    			LOGGER.debug("### Access(Session) - AuthorityResourceMetadata bean not available.");
    		}
    	} else {
    		LOGGER.debug("### Role Mappings reloaded after author role update.");
    	}

    	return "redirect:/sec/ram/EgovAuthorRoleList.do?searchKeyword="+authorCode;
	}
}