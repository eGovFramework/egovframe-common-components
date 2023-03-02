package egovframework.com.cmm.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.raonsecure.omnione.core.eoscommander.util.StringUtils;

import egovframework.com.cmm.EgovWebUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : EgovComUtlController.java
 * @Description : 공통유틸리티성 작업을 위한 Controller
 * @Modification Information
 * @
 * @ 수정일              수정자          수정내용
 * @ ----------  --------  ---------------------------
 *   2009.03.02  조재영      최초 생성
 *   2011.10.07  이기하      .action -> .do로 변경하면서 동일 매핑이 되어 삭제처리
 *   2015.11.12  김연호      한국인터넷진흥원 웹 취약점 개선
 *   2019.04.25  신용호      moveToPage() 화이트리스트 처리
 *   2022.11.11  김혜준      시큐어코딩 처리
 *
 *  @author 공통서비스 개발팀 조재영
 *  @since 2009.03.02
 *  @version 1.0
 *  @see
 *
 */
@Controller
public class EgovComUtlController {

    //@Resource(name = "egovUserManageService")
    //private EgovUserManageService egovUserManageService;
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovComUtlController.class);
	
	@Resource(name = "egovPageLinkWhitelist")
    protected List<String> egovWhitelist;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value="/EgovPageLink.do")
	public String moveToPage(@RequestParam("link") String linkPage){
		String link = "";

		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		// 2022.11.11 시큐어코딩 처리
		if (StringUtils.isEmpty(link)) {
			link="egovframework/com/cmm/egovError";
		}

		link = linkPage;
		link = link.replace(";", "");
		link = link.replace(".", "");

		// 화이트 리스트 처리
		// whitelist 목록에 있는 경우 결과가 true, 결과가 false인 경우 FAIL처리
		boolean isAuth = false;

		for (String urlPattern : egovWhitelist)
		{
			if (linkPage.matches(urlPattern))
			{
				isAuth = true;
				break;
			}
		}

		if (!isAuth) {
			LOGGER.debug("Page Link WhiteList Error! Please check whitelist!");
			linkPage = "egovframework/com/cmm/egovError";
		}
		
		// 안전한 경로 문자열로 조치
		link = EgovWebUtil.filePathBlackList(link);
		
		return link;
	}
	
    /**
	 * 모달조회
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/EgovModal.do")
    public String selectUtlJsonInquire()  throws Exception {
        return "egovframework/com/cmm/EgovModal";
    }
    
    /**
	 * validato rule dynamic Javascript
	 */
	@RequestMapping("/validator.do")
	public String validate(){
		return "egovframework/com/cmm/validator";
	}

}