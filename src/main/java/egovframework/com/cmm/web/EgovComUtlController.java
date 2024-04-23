package egovframework.com.cmm.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
 *   2023.05.23  신용호      moveToPage() 추가 보완 조치
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
	public String moveToPage(@RequestParam(value="linkIndex",required=true,defaultValue="0") Integer linkIndex){

		String link = "";
		// 화이트 리스트가 비었는지 확인
		if (egovWhitelist == null || egovWhitelist.isEmpty() || egovWhitelist.size() <= linkIndex) {
			link="egovframework/com/cmm/egovError";
			return link;
		}

		link = egovWhitelist.get(linkIndex);

		link = link.replace(";", "");
		link = link.replace("%", "");
		link = link.replace(".", "");

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
