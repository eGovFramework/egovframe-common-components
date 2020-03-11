package egovframework.com.sec.rgm.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sec.rgm.service.AuthorGroup;
import egovframework.com.sec.rgm.service.AuthorGroupVO;
import egovframework.com.sec.rgm.service.EgovAuthorGroupService;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
/**
 * 권한그룹에 관한 controller 클래스를 정의한다.
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
 *   2011.08.04  서준식          mberTyCodes 구분자 부분 추가
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 * </pre>
 */


@Controller
@SessionAttributes(types=SessionVO.class)
public class EgovAuthorGroupController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovAuthorGroupService")
    private EgovAuthorGroupService egovAuthorGroupService;
    
    @Resource(name = "egovAuthorManageService")
    private EgovAuthorManageService egovAuthorManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
	 * 권한 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/sec/rgm/EgovAuthorGroupListView.do")
    public String selectAuthorGroupListView() throws Exception {

        return "egovframework/com/sec/rgm/EgovAuthorGroupManage";
    }    

	/**
	 * 그룹별 할당된 권한 목록 조회
	 * @param authorGroupVO AuthorGroupVO
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */
    @IncludedInfo(name="권한그룹관리", listUrl="/sec/rgm/EgovAuthorGroupList.do", order = 70,gid = 20)
    @RequestMapping(value="/sec/rgm/EgovAuthorGroupList.do")
	public String selectAuthorGroupList(@ModelAttribute("authorGroupVO") AuthorGroupVO authorGroupVO,
			                            @ModelAttribute("authorManageVO") AuthorManageVO authorManageVO,
			                             ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorGroupVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorGroupVO.getPageUnit());
		paginationInfo.setPageSize(authorGroupVO.getPageSize());
		
		authorGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		authorGroupVO.setAuthorGroupList(egovAuthorGroupService.selectAuthorGroupList(authorGroupVO));
        model.addAttribute("authorGroupList", authorGroupVO.getAuthorGroupList());
        
        int totCnt = egovAuthorGroupService.selectAuthorGroupListTotCnt(authorGroupVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

    	authorManageVO.setAuthorManageList(egovAuthorManageService.selectAuthorAllList(authorManageVO));
        model.addAttribute("authorManageList", authorManageVO.getAuthorManageList());

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
        
        return "egovframework/com/sec/rgm/EgovAuthorGroupManage";
	}

	/**
	 * 그룹에 권한정보를 할당하여 데이터베이스에 등록
	 * @param userIds String
	 * @param authorCodes String
	 * @param regYns String
	 * @param authorGroup AuthorGroup
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/sec/rgm/EgovAuthorGroupInsert.do")
	public String insertAuthorGroup(@RequestParam("userIds") String userIds,
			                        @RequestParam("authorCodes") String authorCodes,
			                        @RequestParam("regYns") String regYns,
			                        @RequestParam("mberTyCodes") String mberTyCodes,// 2011.08.04 수정 부분
			                        @ModelAttribute("authorGroup") AuthorGroup authorGroup,
			                         ModelMap model) throws Exception {
		
    	String [] strUserIds = userIds.split(";");
    	String [] strAuthorCodes = authorCodes.split(";");
    	String [] strRegYns = regYns.split(";");
    	String [] strMberTyCodes = mberTyCodes.split(";");// 2011.08.04 수정 부분
    	
    	for(int i=0; i<strUserIds.length;i++) {
    		authorGroup.setUniqId(strUserIds[i]);
    		authorGroup.setAuthorCode(strAuthorCodes[i]);
    		authorGroup.setMberTyCode(strMberTyCodes[i]);// 2011.08.04 수정 부분
    		if(strRegYns[i].equals("N"))
    		    egovAuthorGroupService.insertAuthorGroup(authorGroup);
    		else 
    		    egovAuthorGroupService.updateAuthorGroup(authorGroup);
    	}

        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));		
		return "forward:/sec/rgm/EgovAuthorGroupList.do";
	}

	/**
	 * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
	 * @param userIds String
	 * @param authorGroup AuthorGroup
	 * @return String
	 * @exception Exception
	 */ 
	@RequestMapping(value="/sec/rgm/EgovAuthorGroupDelete.do")
	public String deleteAuthorGroup(@RequestParam("userIds") String userIds,
                                    @ModelAttribute("authorGroup") AuthorGroup authorGroup,
                                     ModelMap model) throws Exception {
		
    	String [] strUserIds = userIds.split(";");
    	for(int i=0; i<strUserIds.length;i++) {
    		authorGroup.setUniqId(strUserIds[i]);
    		egovAuthorGroupService.deleteAuthorGroup(authorGroup);
    	}
    	
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/sec/rgm/EgovAuthorGroupList.do";
	}



}