package egovframework.com.dam.spe.spe.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.map.mat.service.EgovMapMaterialService;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;
import egovframework.com.dam.map.tea.service.EgovMapTeamService;
import egovframework.com.dam.map.tea.service.MapTeamVO;
import egovframework.com.dam.spe.spe.service.EgovKnoSpecialistService;
import egovframework.com.dam.spe.spe.service.KnoSpecialist;
import egovframework.com.dam.spe.spe.service.KnoSpecialistVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 지식전문가에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 지식전문가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식전문가의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:42
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------        --------    ---------------------------
 *   2010.8.12  박종선          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovKnoSpecialistController {

	@Resource(name = "MapMaterialService")
	public EgovMapMaterialService mapMaterialService;

	@Resource(name = "MapTeamService")
    private EgovMapTeamService mapTeamService;

	@Resource(name = "KnoSpecialistService")
    private EgovKnoSpecialistService knoSpecialistService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
	 * 등록된 지식전문가 정보를 조회 한다.
	 * @param KnoSpecialistVO- 지식전문가 VO
	 * @return String - 리턴 Url
	 *
	 * @param KnoSpecialistVO
	 */
    @IncludedInfo(name="지식전문가관리", listUrl="/dam/spe/spe/EgovComDamSpecialistList.do", order = 1270,gid = 80)
	@RequestMapping(value="/dam/spe/spe/EgovComDamSpecialistList.do")
	public String selectKnoSpecialistList(@ModelAttribute("loginVO") LoginVO loginVO
			, @ModelAttribute("searchVO") KnoSpecialistVO searchVO
			, ModelMap model
			) throws Exception {

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

		List<?> KnoSpecialistList = knoSpecialistService.selectKnoSpecialistList(searchVO);
		model.addAttribute("resultList", KnoSpecialistList);

		int totCnt = knoSpecialistService.selectKnoSpecialistTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/dam/spe/spe/EgovComDamSpecialistList";
	}

	/**
	 * 지식전문가 상세 정보를 조회 한다.
	 * @param KonSpecialistVO - 지식전문가 VO
	 * @return String - 리턴 Url
	 *
	 * @param KonSpecialistVO
	 */
	@RequestMapping(value="/dam/spe/spe/EgovComDamSpecialist.do")
	public String selectKnoSpecialist(@ModelAttribute("loginVO") LoginVO loginVO
			, KnoSpecialist knoSpecialist
			, ModelMap model
			, @RequestParam Map<?, ?> commandMap
			) throws Exception {
		KnoSpecialist vo = knoSpecialistService.selectKnoSpecialist(knoSpecialist);
		model.addAttribute("result", vo);
		return "egovframework/com/dam/spe/spe/EgovComDamSpecialistDetail";
	}

	/**
	 * 지식전문가 정보를 신규로 등록한다.
	 * @param speNm - 지식전문가 model
	 * @return String - 리턴 Url
	 *
	 * @param speNm
	 */
	@RequestMapping(value="/dam/spe/spe/EgovComDamSpecialistRegist.do")
	public String insertKnoSpecialist(
			@ModelAttribute("knoSpecialist") KnoSpecialist knoSpecialist
			, @ModelAttribute("mapMaterial") MapMaterial mapMaterial
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {

    	// Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if (knoSpecialist.getSpeId() == null
			||knoSpecialist.getSpeId().equals("")
			||sCmd.equals("")) {

			MapTeamVO searchVO;
			searchVO = new MapTeamVO();
			searchVO.setRecordCountPerPage(999999);
			searchVO.setFirstIndex(0);
			searchVO.setSearchCondition("MaterialList");
            List<?> MapTeamList = mapTeamService.selectMapTeamList(searchVO);
            model.addAttribute("mapTeamList", MapTeamList);

            MapMaterialVO searchMatVO;
            searchMatVO = new MapMaterialVO();
            searchMatVO.setRecordCountPerPage(999999);
            searchMatVO.setFirstIndex(0);
            searchMatVO.setSearchCondition("orgnztId");
            searchMatVO.setSearchKeyword(mapMaterial.getOrgnztId());

            List<?> MapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
            model.addAttribute("mapMaterialList", MapMaterialList);

			return "egovframework/com/dam/spe/spe/EgovComDamSpecialistRegist";

		} else if (sCmd.equals("Regist")) {

			beanValidator.validate(knoSpecialist, bindingResult);
			if (bindingResult.hasErrors()){

				MapTeamVO searchVO;
				searchVO = new MapTeamVO();
				searchVO.setRecordCountPerPage(999999);
				searchVO.setFirstIndex(0);
	            List<?> MapTeamList = mapTeamService.selectMapTeamList(searchVO);
	            model.addAttribute("mapTeamList", MapTeamList);

	            MapMaterialVO searchMatVO;
	            searchMatVO = new MapMaterialVO();
	            searchMatVO.setRecordCountPerPage(999999);
	            searchMatVO.setFirstIndex(0);
	            searchMatVO.setSearchCondition("orgnztId");

	            if (mapMaterial.getOrgnztId().equals("")) {
	            	EgovMap emp = (EgovMap)MapTeamList.get(0);
	            	mapMaterial.setOrgnztId(emp.get("orgnztId").toString());
	            }
	            searchMatVO.setSearchKeyword(mapMaterial.getOrgnztId());

	            List<?> MapMaterialList = mapMaterialService.selectMapMaterialList(searchMatVO);
	            model.addAttribute("mapMaterialList", MapMaterialList);

				return "egovframework/com/dam/spe/spe/EgovComDamSpecialistRegist";
			}


			//아이디 설정
			knoSpecialist.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			knoSpecialist.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

			knoSpecialistService.insertKnoSpecialist(knoSpecialist);
			return "forward:/dam/spe/spe/EgovComDamSpecialistList.do";

		} else {
			return "forward:/dam/spe/spe/EgovComDamSpecialistList.do";
		}

	}


	/**
	 * 기 등록 된 지식전문가 정보를 수정 한다.
	 * @param speNm - 지식전문가 model
	 * @return String - 리턴 Url
	 *
	 * @param speNm
	 */
	@RequestMapping(value="/dam/spe/spe/EgovComDamSpecialistModify.do")
	public String updateKnoSpecialist(@ModelAttribute("speId") KnoSpecialist knoSpecialist
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sCmd = commandMap.get("cmd") == null ? "": (String)commandMap.get("cmd");
		if (sCmd.equals("")) {
			KnoSpecialist vo = knoSpecialistService.selectKnoSpecialist(knoSpecialist);
			model.addAttribute("knoSpecialist", vo);
			return "egovframework/com/dam/spe/spe/EgovComDamSpecialistModify";
		} else if (sCmd.equals("Modify")) {
			beanValidator.validate(knoSpecialist, bindingResult);
			if (bindingResult.hasErrors()){
				KnoSpecialist vo = knoSpecialistService.selectKnoSpecialist(knoSpecialist);
				model.addAttribute("knoSpecialist", vo);
				return "egovframework/com/dam/spe/spe/EgovComDamSpecialistModify";
			}

        	// 아이디 설정
			knoSpecialist.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			knoSpecialist.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

			knoSpecialistService.updateKnoSpecialist(knoSpecialist);
			return "forward:/dam/spe/spe/EgovComDamSpecialistList.do";
		} else {
			return "forward:/dam/spe/spe/EgovComDamSpecialistList.do";
		}
	}

	/**
	 * 기 등록된 지식전문가 정보를 삭제한다.
	 * @param siteUrl - 지식전문가 model
	 * @return String - 리턴 Url
	 *
	 * @param speNm
	 */
	@RequestMapping(value="/dam/spe/spe/EgovComDamSpecialistRemove.do")
	public String deleteKnoSpecialist(@ModelAttribute("loginVO") LoginVO loginVO
			, KnoSpecialist knoSpecialist
			, ModelMap model
			) throws Exception {
		knoSpecialistService.deleteKnoSpecialist(knoSpecialist);
		return "forward:/dam/spe/spe/EgovComDamSpecialistList.do";
	}

}