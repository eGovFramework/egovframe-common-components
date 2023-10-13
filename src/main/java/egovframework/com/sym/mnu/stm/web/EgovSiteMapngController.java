package egovframework.com.sym.mnu.stm.web;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mcm.service.EgovMenuCreateManageService;
import egovframework.com.sym.mnu.mcm.service.MenuCreatVO;
import egovframework.com.sym.mnu.mcm.service.MenuSiteMapVO;
import egovframework.com.sym.mnu.stm.service.EgovSiteMapngService;

/**
 * 사이트맵 조회 처리를 하는 비즈니스 구현 클래스
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *   2011.07.29  서준식 		사이트 맵 생성 안했을 때 발생하는 오류 수정
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 * </pre>
 */

@Controller
public class EgovSiteMapngController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSiteMapngController.class);

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovSiteMapngService */
	@Resource(name = "siteMapngService")
    private EgovSiteMapngService siteMapngService;
	
	/** EgovMenuManageService */
	@Resource(name = "meunCreateManageService")
	private EgovMenuCreateManageService menuCreateManageService;
	
	/** EgovMessageSource */
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	/*사이트맵조회*/
    /**
     * 사이트맵 화면을 조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/mnu/stm/EgovSiteMapng"
     * @exception Exception
     */
	@IncludedInfo(name="사이트맵", order = 1101 ,gid = 60)
    @RequestMapping(value="/sym/mnu/stm/EgovSiteMapng.do")
    public String selectSiteMapng(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	searchVO.setSearchKeyword(user.getId());
    	// AuthorCode 검색
		MenuCreatVO menuVO = menuCreateManageService.selectAuthorByUsr(searchVO);

    	MenuSiteMapVO menuSiteMapVO = new MenuSiteMapVO();
    	menuSiteMapVO.setAuthorCode(menuVO.getAuthorCode());
    	List<EgovMap> resultList = menuCreateManageService.selectMenuCreatSiteMapList(menuSiteMapVO);

    	LOGGER.debug("Count SiteMap ResultList = "+resultList.size());
        model.addAttribute("resultList", resultList);
        model.addAttribute("authorCode", menuVO.getAuthorCode());

        return "egovframework/com/sym/mnu/stm/EgovSiteMapng";
    }
}