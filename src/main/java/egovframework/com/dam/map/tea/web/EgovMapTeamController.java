package egovframework.com.dam.map.tea.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.dam.map.tea.service.EgovMapTeamService;
import egovframework.com.dam.map.tea.service.MapTeam;
import egovframework.com.dam.map.tea.service.MapTeamVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 개요
 * - 지식맵(조직별)에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 지식맵(조직별)에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식맵(조직별)의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 22-7-2010 오전 10:57:37
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일		 수정자		수정내용
 *  -------     --------    ---------------------------
 *   2010.7.22	박종선		최초 생성
 *   2011.8.26	정진오		IncludedInfo annotation 추가
 *   2018.8.03	신용호		updateMapTeam method 수정 않되는 문제 처리
 *
 * </pre>
 */

@Controller
public class EgovMapTeamController {

	@Resource(name = "MapTeamService")
    private EgovMapTeamService mapTeamService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;


	/**
	 * 등록된 지식맵(조직별) 정보를 조회 한다.
	 * @param mapTeamVO- 지식맵(조직별) VO
	 * @return String - 리턴 Url
	 *
	 * @param MapTeamVO
	 */
	@IncludedInfo(name="지식맵관리(조직)", listUrl="/dam/map/tea/EgovComDamMapTeamList.do", order = 1261 ,gid = 80)
	@RequestMapping(value="/dam/map/tea/EgovComDamMapTeamList.do")
	public String selectMapTeamList(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") MapTeamVO searchVO
			, ModelMap model
			) throws Exception{
		/** EgovPropertyService.mapTeam */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> MapTeamList = mapTeamService.selectMapTeamList(searchVO);
		model.addAttribute("resultList", MapTeamList);

		int totCnt = mapTeamService.selectMapTeamTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		return "egovframework/com/dam/map/tea/EgovComDamMapTeamList";
	}

	/**
	 * 지식맵(조직별)상세 정보를 조회 한다.
	 * @param MapTeamVO - 지식맵(조직별) VO
	 * @return String - 리턴 Url
	 *
	 * @param MapTeamVO
	 */
	@RequestMapping(value="/dam/map/tea/EgovComDamMapTeamDetail.do")
	public String selectMapTeamDetail(@ModelAttribute("loginVO") LoginVO loginVO
			, MapTeam mapTeam
			, ModelMap model
			) throws Exception {
		MapTeam vo = mapTeamService.selectMapTeamDetail(mapTeam);
		model.addAttribute("result", vo);
		return "egovframework/com/dam/map/tea/EgovComDamMapTeamDetail";
	}

	/**
	 * 지식맵(조직별) 정보를 신규로 등록한다.
	 * @param orgnztNm - 지식맵(조직별) model
	 * @return String - 리턴 Url
	 *
	 * @param mapTeam
	 */
	@RequestMapping(value="/dam/map/tea/EgovComDamMapTeamRegist.do")
	public String insertMapTeam(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("mapTeam") MapTeam mapTeam
			, BindingResult bindingResult
			) throws Exception {
		if (mapTeam.getOrgnztNm() == null
				||mapTeam.getOrgnztNm().equals("")) {
			return "egovframework/com/dam/map/tea/EgovComDamMapTeamRegist";
		}

		beanValidator.validate(mapTeam, bindingResult);
		if (bindingResult.hasErrors()){
			return "egovframework/com/dam/map/tea/EgovComDamMapTeamRegist";
		}

		mapTeam.setFrstRegisterId(loginVO.getUniqId());
		mapTeamService.insertMapTeam(mapTeam);
		return "forward:/dam/map/tea/EgovComDamMapTeamList.do";
	}

	/**
	 * 기 등록 된 지식맵(조직별)링 정보를 수정 한다.
	 * @param orgnztNm - 지식맵(조직별) model
	 * @return String - 리턴 Url
	 *
	 * @param mapTeam
	 */
	@RequestMapping(value="/dam/map/tea/EgovComDamMapTeamModify.do")
	public String updateMapTeam(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("mapTeam") MapTeam mapTeam
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "": (String)commandMap.get("cmd");
		if (sCmd.equals("")) {
			MapTeam vo = mapTeamService.selectMapTeamDetail(mapTeam);
			model.addAttribute("mapTeam", vo);
			return "egovframework/com/dam/map/tea/EgovComDamMapTeamModify";
		} else if (sCmd.equals("Modify")) {
			beanValidator.validate(mapTeam, bindingResult);
			if (bindingResult.hasErrors()){
				return "egovframework/com/dam/map/tea/EgovComDamMapTeamModify";
			}
			mapTeam.setFrstRegisterId(loginVO.getUniqId());
			mapTeamService.updateMapTeam(mapTeam);
			return "forward:/dam/map/tea/EgovComDamMapTeamList.do";
		} else {
			return "forward:/dam/map/tea/EgovComDamMapTeamList.do";
		}
	}

	/**
	 * 기 등록된 지식맵(조직별) 정보를 삭제한다.
	 * @param orgnztNm - 지식맵(조직별) model
	 * @return String - 리턴 Url
	 *
	 * @param orgnztNm
	 */
	@RequestMapping(value="/dam/map/tea/EgovComDamMapTeamRemove.do")
	public String deleteMapTeam(@ModelAttribute("loginVO") LoginVO loginVO
			, MapTeam mapTeam
			, ModelMap model
			) throws Exception {
		mapTeamService.deleteMapTeam(mapTeam);
		return "forward:/dam/map/tea/EgovComDamMapTeamList.do";
	}

}