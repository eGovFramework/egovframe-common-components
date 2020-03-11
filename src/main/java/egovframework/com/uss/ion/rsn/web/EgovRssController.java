package egovframework.com.uss.ion.rsn.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.uss.ion.rsn.service.EgovRssService;
import egovframework.com.uss.ion.rsn.service.RssInfo;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;
/**
 * RSS서비스를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.16  장동한          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovRssController {

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** egovOnlinePollService */
    @Resource(name = "egovRssService")
    private EgovRssService egovRssService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * RSS서비스 목록을 조회한다.
     * @param searchVO -검색정보가 담긴 객체
     * @param commandMap -Request Variable
     * @param -RSS서비스 객체
     * @param model -Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @IncludedInfo(name="RSS태그서비스", order = 822 ,gid = 50)
    @RequestMapping(value = "/uss/ion/rsn/listRssTagService.do")
    public String EgovRssTagServiceList(
            @ModelAttribute("searchVO") RssInfo searchVO,
            @RequestParam Map<?, ?> commandMap,
            RssInfo rssInfo, ModelMap model)
            throws Exception {

        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> reusltList = egovRssService.selectRssTagServiceList(searchVO);
        model.addAttribute("resultList", reusltList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));

        int totCnt = egovRssService.selectRssTagServiceListCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

    	return "egovframework/com/uss/ion/rsn/EgovRssTagServiceList";

    }

    /**
     * RSS서비스 목록을 상세조회 조회한다.
     * @param rssInfo -RSS서비스 객체
     * @param commandMap -Request Variable
     * @param model -Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/uss/ion/rsn/detailRssTagService.do")
    public String EgovRssTagServiceDetail(
            RssInfo rssInfo,
            @RequestParam Map<?, ?> commandMap,
            ModelMap model) throws Exception {

    		String sRssId = commandMap.get("rssId") == null ? "" : (String) commandMap.get("rssId");

    		if(!sRssId.equals("")){

    			Map<String, String> mapRssInfo = (Map<String, String>) egovRssService.selectRssTagServiceDetail(rssInfo);
    			model.addAttribute("mapRssInfo",mapRssInfo);

    			mapRssInfo.put("TRGET_SVC_TABLE", EgovWebUtil.removeSQLInjectionRisk(mapRssInfo.get("TRGET_SVC_TABLE")));	// 2012.11 KISA 보안조치

    			model.addAttribute("mapRssInfoList", egovRssService.selectRssTagServiceTable(mapRssInfo));

    		}

        	return "egovframework/com/uss/ion/rsn/EgovRssTagService";
    }


}
