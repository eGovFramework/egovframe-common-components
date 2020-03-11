package egovframework.com.uss.ion.rmm.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rmm.service.EgovRoughMapService;
import egovframework.com.uss.ion.rmm.service.RoughMapDefaultVO;
import egovframework.com.uss.ion.rmm.service.RoughMapVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * -  약도 관리에 대한 Controller를 정의한다.
 *
 * 상세내용
 * -  약도에 대한 등록, 수정, 삭제, 조회, 상세조회 요청 사항을 Service와 매핑 처리한다.
 *
 * @author 옥찬우
 * @since 2014.08.27
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일			수정자		수정내용
 *  -----------		------		---------
 *   2014.08.27		옥찬우		최초 생성
 *
 * </pre>
 */

@Controller
public class EgovRoughMapController {

    /** EgovRoughMapService */
    @Resource(name="EgovRoughMapService")
    private EgovRoughMapService egovRoughMapService;

    /** EgovPropertyService */
    @Resource(name="propertiesService")
    protected EgovPropertyService propertyService;

    /** DefaultBeanValidator */
    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 약도 목록 조회 Service interface 호출 및 결과를 반환한다.
     * @param RoughMapDefaultVO
     * @param model
     * @return String 약도 목록 조회 화면
     * @throws Exception
    */
    @IncludedInfo(name="약도 관리", order = 943, gid = 50)
    @RequestMapping("/com/uss/ion/rmm/selectRoughMapList.do")
    public String selectRoughMapList(@ModelAttribute("searchVO") RoughMapDefaultVO searchVO, ModelMap model) throws Exception {

        // 권한 체크
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        searchVO.setPageUnit(propertyService.getInt("pageUnit"));
        searchVO.setPageSize(propertyService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> roughMapList = egovRoughMapService.selectRoughMapList(searchVO);

        int totCnt = egovRoughMapService.selectRoughMapListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("resultList", roughMapList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/ion/rmm/EgovRoughMapList";
    }

    /**
     * 약도 상세조회 Service interface 호출 및 결과를 반환한다.
     * @param RoughMapDefaultVO
     * @param model
     * @return String 건물 위치정보 상세조회 화면
     * @throws Exception
    */
    @RequestMapping("/com/uss/ion/rmm/selectRoughMapDetail.do")
    public String selectRoughMap(RoughMapVO roughMapVO, ModelMap model) throws Exception {

        // 권한 체크
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        RoughMapVO roughMap = egovRoughMapService.selectRoughMapDetail(roughMapVO);
        model.addAttribute("roughMap", roughMap);

        return "egovframework/com/uss/ion/rmm/EgovRoughMapDetail";
    }

    /**
     * 약도 등록 화면으로 이동한다.
     * @param RoughMapDefaultVO
     * @param model
     * @return String 건물 위치정보 등록 화면
     * @throws Exception
    */
    @RequestMapping(value="/com/uss/ion/rmm/registRoughMap.do")
    public String goRoughMapRegist(@ModelAttribute("roughMap") RoughMapVO roughMap, Model model) throws Exception {
        // 권한 체크
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        return "egovframework/com/uss/ion/rmm/EgovRoughMapRegist";
    }

    /**
     * 약도 등록 Service interface 호출 및 결과를 반환한다.
     * @param RoughMapVO
     * @return String 건물 위치정보 목록 조회 화면
     * @throws Exception
    */
    @RequestMapping("/com/uss/ion/rmm/insertRoughMap.do")
    public String insertRoughMap(@ModelAttribute("roughMap") RoughMapVO roughMap, BindingResult bindingResult) throws Exception {

        // 권한 체크
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

    	beanValidator.validate(roughMap, bindingResult);

		if(bindingResult.hasErrors()){
			return "egovframework/com/uss/ion/rmm/EgovRoughMapRegist";
		}

        LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        if (isAuthenticated) {
            roughMap.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
            roughMap.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));    	// 최종수정자ID
            egovRoughMapService.insertRoughMap(roughMap);
        }

        return "forward:/com/uss/ion/rmm/selectRoughMapList.do";
    }

    /**
     * 약도 수정 화면으로 이동한다.
     * @param RoughMapDefaultVO
     * @param model
     * @return String 건물 위치정보 수정 화면
     * @throws Exception
    */
    @RequestMapping(value="/com/uss/ion/rmm/updateRoughMapView.do")
    public String goRoughMapUpdt(@ModelAttribute("roughMap") RoughMapVO roughMap, ModelMap model) throws Exception {

        // 권한 체크
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        roughMap = egovRoughMapService.selectRoughMapDetail(roughMap);

        model.addAttribute("result", roughMap);
        model.addAttribute("roughMap", roughMap);

        return "egovframework/com/uss/ion/rmm/EgovRoughMapUpdt";
    }

    /**
     * 약도 수정 Service interface 호출 및 결과를 반환한다.
     * @param RoughMapVO
     * @return String 건물 위치정보 목록 조회 화면
     * @throws Exception
    */
    @RequestMapping(value="/com/uss/ion/rmm/updateRoughMap.do")
    public String updateRoughMap(@ModelAttribute("roughMap") RoughMapVO roughMap, BindingResult bindingResult) throws Exception {

        // 권한 체크
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

    	beanValidator.validate(roughMap, bindingResult);

		if(bindingResult.hasErrors()){
			return "egovframework/com/uss/ion/rmm/EgovRoughMapUpdt";
		}

        LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        if (isAuthenticated) {
            roughMap.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
            egovRoughMapService.updateRoughMap(roughMap);
        }

        return "forward:/com/uss/ion/rmm/selectRoughMapList.do";
    }

    /**
     * 약도 삭제 Service interface 호출 및 결과를 반환한다.
     * @param RoughMapVO
     * @return String 건물 위치정보 목록 조회 화면
     * @throws Exception
    */
    @RequestMapping(value="/com/uss/ion/rmm/deleteRoughMap.do")
    public String deleteRoughMap(@ModelAttribute("roughMap") RoughMapVO roughMap) throws Exception {

        // 권한 체크
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        // 사용자 인증여부 판단
        if (isAuthenticated) {
            roughMap.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
            egovRoughMapService.deleteRoughMap(roughMap);
        }

        return "forward:/com/uss/ion/rmm/selectRoughMapList.do";
    }

}