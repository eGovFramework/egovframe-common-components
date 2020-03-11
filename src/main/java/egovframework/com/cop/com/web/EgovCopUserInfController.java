package egovframework.com.cop.com.web;

import java.util.Map;

import egovframework.com.cop.com.service.EgovUserInfManageService;
import egovframework.com.cop.com.service.UserInfVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 협업기능에서 활용하는 사용자 정보 조회용 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.4.6   이삼섭          최초 생성
 *	 2011.07.21 안민정          커뮤니티 관련 메소드 분리 (->EgovCmyUserInfController)
 *
 * </pre>
 */
@Controller
public class EgovCopUserInfController {

	@Resource(name = "EgovUserInfManageService")
	private EgovUserInfManageService userInfService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	/**
	 * 사용자 정보에 대한 목록을 조회한다.
	 *
	 * @param userVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cop/com/selectUserList.do")
	public String selectUserList(@ModelAttribute("searchVO") UserInfVO userVO, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		String popFlag = (String) commandMap.get("PopFlag");
		String returnUrl = "egovframework/com/cop/com/EgovUserList";
		
		if ("Y".equals(popFlag)) {
			returnUrl = "egovframework/com/cop/com/EgovUserListPop";
		}
		
		userVO.setPageUnit(propertyService.getInt("pageUnit"));
		userVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(userVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userVO.getPageUnit());
		paginationInfo.setPageSize(userVO.getPageSize());

		userVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = userInfService.selectUserList(userVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("targetMethod", "selectUserList");
		model.addAttribute("trgetId", "");
		model.addAttribute("paginationInfo", paginationInfo);

		return returnUrl;
	}

}
