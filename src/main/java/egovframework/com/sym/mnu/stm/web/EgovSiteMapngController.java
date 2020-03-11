package egovframework.com.sym.mnu.stm.web;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.stm.service.EgovSiteMapngService;
import egovframework.com.sym.mnu.stm.service.SiteMapngVO;

import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
    	LoginVO user =
			(LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	searchVO.setSearchKeyword((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
    	SiteMapngVO  resultVO = siteMapngService.selectSiteMapng(searchVO);
    	if(resultVO == null){
    		model.addAttribute("resultMsg", egovMessageSource.getMessage("comSymMnuStm.siteMapng.validate.siteMap"));
    		return "egovframework/com/sym/mnu/stm/EgovSiteMapng";
    	}
    	LOGGER.debug(resultVO.getBndeFileNm());
        model.addAttribute("resultVO", resultVO);

        return "egovframework/com/sym/mnu/stm/EgovSiteMapng";
    }
}