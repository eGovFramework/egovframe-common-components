package egovframework.com.ssi.syi.ist.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.ssi.syi.ist.service.CntcSttus;
import egovframework.com.ssi.syi.ist.service.CntcSttusVO;
import egovframework.com.ssi.syi.ist.service.EgovCntcSttusService;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * 연계현황 관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */

@Controller
public class EgovCntcSttusController {

	@Resource(name = "CntcSttusService")
    private EgovCntcSttusService cntcSttusService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/**
	 * 연계현황 상세내역을 조회한다.
	 * @param loginVO
	 * @param CntcSttus
	 * @param model
	 * @return "egovframework/com/cmm/sym/ccm/EgovCcmCntcSttusDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/ssi/syi/ist/getCntcSttusDetail.do")
 	public String selectCntcSttusLogDetail (CntcSttus CntcSttus
 			, ModelMap model
 			) throws Exception {
    	CntcSttus vo = cntcSttusService.selectCntcSttusDetail(CntcSttus);
		model.addAttribute("result", vo);

		return "egovframework/com/ssi/syi/ist/EgovCntcSttusDetail";
	}

    /**
	 * 연계현황 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "egovframework/com/cmm/sym/ccm/EgovCcmCntcSttusList"
     * @throws Exception
     */
	@IncludedInfo(name="연계현황관리", listUrl="/ssi/syi/ist/getCntcSttusList.do", order = 1220,gid = 70)
    @RequestMapping(value="/ssi/syi/ist/getCntcSttusList.do")
	public String selectCntcSttusLogList (@ModelAttribute("searchVO") CntcSttusVO searchVO
			, ModelMap model
			) throws Exception {
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

        List<?> CmmnCodeList = cntcSttusService.selectCntcSttusList(searchVO);
        model.addAttribute("resultList", CmmnCodeList);

        int totCnt = cntcSttusService.selectCntcSttusListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/ssi/syi/ist/EgovCntcSttusList";
	}

    /**
     * Map 내용을 확인한다.
     * @param commandMap
     * @return
     */
	public String printParameterMap(@RequestParam Map<?, ?> commandMap){
		String ret = "";
       	for(Object key:commandMap.keySet()){
    		Object value = commandMap.get(key);

    		ret += "key:" + key.toString() + " value:" + value.toString();
    	}
       	return ret;
	}

}