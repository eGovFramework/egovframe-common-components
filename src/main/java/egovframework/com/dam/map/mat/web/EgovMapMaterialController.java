package egovframework.com.dam.map.mat.web;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.dam.map.mat.service.EgovMapMaterialService;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;
import egovframework.com.dam.map.tea.service.EgovMapTeamService;
import egovframework.com.dam.map.tea.service.MapTeamVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * <pre>
 * 개요
 * - 지식맵(지식유형)에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 지식맵(지식유형)에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식맵(지식유형)의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 박종선
 * @since 2010.08.12
 * @version 1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.12  박종선          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2025.06.14  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(지역 변수 명명 규칙)
 *
 *      </pre>
 */

@Controller
public class EgovMapMaterialController {

	@Resource(name = "MapTeamService")
	private EgovMapTeamService mapTeamService;

	@Resource(name = "MapMaterialService")
	public EgovMapMaterialService mapMaterialService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 등록된 지식맵(지식유형) 정보를 조회 한다.
	 * 
	 * @param mapMaterialVO- 지식맵(지식유형) VO
	 * @return String - 리턴 Url
	 *
	 * @param MapMaterialVO
	 */
	@IncludedInfo(name = "지식맵관리(유형)", listUrl = "/dam/map/mat/EgovComDamMapMaterialList.do", order = 1260, gid = 80)
	@RequestMapping(value = "/dam/map/mat/EgovComDamMapMaterialList.do")
	public String selectMapMaterialList(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("searchVO") MapMaterialVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.mapMaterial */
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

		List<MapMaterialVO> resultList = mapMaterialService.selectMapMaterialList(searchVO);
		model.addAttribute("resultList", resultList);

		int totCnt = mapMaterialService.selectMapMaterialTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/dam/map/mat/EgovComDamMapMaterialList";
	}

	/**
	 * 지식맵(지식유형)상세 정보를 조회 한다.
	 * 
	 * @param MapMaterialVO - 지식맵(지식유형) VO
	 * @return String - 리턴 Url
	 *
	 * @param MapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/EgovComDamMapMaterial.do")
	public String selectMapMaterial(@ModelAttribute("loginVO") LoginVO loginVO, MapMaterial mapMaterial, ModelMap model)
			throws Exception {
		MapMaterial vo = mapMaterialService.selectMapMaterial(mapMaterial);
		model.addAttribute("result", vo);
		return "egovframework/com/dam/map/mat/EgovComDamMapMaterialDetail";
	}

	/**
	 * 지식맵(지식유형) 등록 화면으로 이동한다.
	 *
	 * @param loginVO - 로그인 정보
	 * @param model - ModelMap
	 * @return String - 리턴 Url
	 * @throws Exception
	 */
	@RequestMapping(value = "/dam/map/mat/EgovComDamMapMaterialRegistView.do")
	public String insertMapMaterialView(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("mapMaterial") MapMaterialVO mapMaterial, ModelMap model) throws Exception {

		MapTeamVO mapTeamVO = new MapTeamVO();
		mapTeamVO.setRecordCountPerPage(999999);
		mapTeamVO.setFirstIndex(0);
		mapTeamVO.setSearchCondition("MapTeamList");
		List<MapTeamVO> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
		model.addAttribute("mapTeamList", mapTeamList);

		return "egovframework/com/dam/map/mat/EgovComDamMapMaterialRegist";
	}

	/**
	 * 지식맵(지식유형) 정보를 신규로 등록한다.
	 *
	 * @param konTypeNm - 지식맵(지식유형) model
	 * @return String - 리턴 Url
	 *
	 * @param MapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/EgovComDamMapMaterialRegist.do")
	public String insertMapMaterial(@ModelAttribute("loginVO") LoginVO loginVO,
			@Valid @ModelAttribute("mapMaterial") MapMaterialVO mapMaterial, BindingResult bindingResult, ModelMap model)
			throws Exception {

		if (bindingResult.hasErrors()) {
			MapTeamVO mapTeamVO = new MapTeamVO();
			mapTeamVO.setRecordCountPerPage(999999);
			mapTeamVO.setFirstIndex(0);
			mapTeamVO.setSearchCondition("MapTeamList");
			List<MapTeamVO> mapTeamList = mapTeamService.selectMapTeamList(mapTeamVO);
			model.addAttribute("mapTeamList", mapTeamList);

			return "egovframework/com/dam/map/mat/EgovComDamMapMaterialRegist";
		}

		mapMaterial.setFrstRegisterId(loginVO.getUniqId());
		mapMaterialService.insertMapMaterial(mapMaterial);
		return "forward:/dam/map/mat/EgovComDamMapMaterialList.do";
	}

	/**
	 * 지식맵(지식유형) 수정 화면으로 이동한다.
	 *
	 * @param loginVO - 로그인 정보
	 * @param searchVO - 검색조건
	 * @param mapMaterial - 지식맵(지식유형) model
	 * @param model - ModelMap
	 * @return String - 리턴 Url
	 * @throws Exception
	 */
	@RequestMapping(value = "/dam/map/mat/EgovComDamMapMaterialModifyView.do")
	public String updateMapMaterialView(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("searchVO") MapMaterialVO searchVO,
			@ModelAttribute("mapMaterial") MapMaterial mapMaterial, ModelMap model) throws Exception {

		MapMaterial vo = mapMaterialService.selectMapMaterial(mapMaterial);
		model.addAttribute("mapMaterial", vo);

		return "egovframework/com/dam/map/mat/EgovComDamMapMaterialModify";
	}

	/**
	 * 기 등록 된 지식맵(지식유형) 정보를 수정 한다.
	 *
	 * @param konTypeNm - 지식맵(지식유형) model
	 * @return String - 리턴 Url
	 *
	 * @param MapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/EgovComDamMapMaterialModify.do")
	public String updateMapMaterial(@ModelAttribute("loginVO") LoginVO loginVO,
			@ModelAttribute("searchVO") MapMaterialVO searchVO,
			@Valid @ModelAttribute("mapMaterial") MapMaterial mapMaterial, BindingResult bindingResult,
			ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			//MapMaterial vo = mapMaterialService.selectMapMaterial(mapMaterial);
			//model.addAttribute("mapMaterial", vo);
			return "egovframework/com/dam/map/mat/EgovComDamMapMaterialModify";
		}

		mapMaterial.setFrstRegisterId(loginVO.getUniqId());
		mapMaterialService.updateMapMaterial(mapMaterial);
		return "forward:/dam/map/mat/EgovComDamMapMaterialList.do";
	}

	/**
	 * 기 등록된 지식맵(지식유형) 정보를 삭제한다.
	 * 
	 * @param konTypeNm - 지식맵(지식유형) model
	 * @return String - 리턴 Url
	 *
	 * @param MapMaterialVO
	 */
	@RequestMapping(value = "/dam/map/mat/EgovComDamMapMaterialRemove.do")
	public String deleteMapMaterial(@ModelAttribute("loginVO") LoginVO loginVO, MapMaterial mapMaterial, ModelMap model)
			throws Exception {
		mapMaterialService.deleteMapMaterial(mapMaterial);
		return "forward:/dam/map/mat/EgovComDamMapMaterialList.do";
	}

	/**
	 * 지식유형코드 중복 여부 체크(위치 : 1260.지식맵관리(유형) > 등록)
	 * 
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dam/map/mat/EgovKnoTypeCdCheckAjax.do")
	public ModelAndView EgovKnoTypeCdCheckAjax(@RequestParam Map<String, Object> commandMap) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");

		String knoTypeCd = (String) commandMap.get("knoTypeCd");
		int checkCount = mapMaterialService.knoTypeCdCheck(knoTypeCd);
		modelAndView.addObject("checkCount", checkCount);

		return modelAndView;
	}
}