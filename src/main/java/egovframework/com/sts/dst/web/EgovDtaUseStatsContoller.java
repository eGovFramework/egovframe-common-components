/**
 * 개요
 * - 자료이용현황 통계에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 자료이용현황 통계에 대한 등록, 조회 기능을 제공한다.
 * - 자료이용현황 통계의 조회기능은 목록조회, 상세조회로 구분된다.
 * 
 * 
 *     수정일       		 수정자                   수정내용
 *     -------          --------        ---------------------------
 *    2011.09.19     	 서준식 			초기 게시기간 설정
 * @author lee.m.j
 * @version 1.0
 * @created 08-9-2009 오후 1:40:19
 */

package egovframework.com.sts.dst.web;

import javax.annotation.Resource;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.sts.dst.service.DtaUseStatsVO;
import egovframework.com.sts.dst.service.EgovDtaUseStatsService;
import egovframework.com.utl.fcc.service.EgovDateUtil;
/*
*
* 데이터 사용 통계 컨트롤러 클래스
* */
@Controller
public class EgovDtaUseStatsContoller {
	
	@Resource(name = "egovDtaUseStatsService")
	EgovDtaUseStatsService egovDtaUseStatsService;
	
    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService egovCmmUseService;
    
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	/**
	 * 자료이용현황 통계정보의 대상목록을 조회한다.
	 * @param reprtStatsVO - 자료이용현황 VO
	 * @return String
	 */
	@RequestMapping("/sts/dst/selectDtaUseStatsListView.do")
	public String selectDtaUseStatsListView(@ModelAttribute("comDefaultCodeVO") ComDefaultCodeVO comDefaultCodeVO,
			                                 @ModelAttribute("pmDtaUseStats") DtaUseStatsVO dtaUseStatsVO, 
			                                 ModelMap model) throws Exception {
		
    	comDefaultCodeVO.setCodeId("COM042");
    	model.addAttribute("cmmCode042List", egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO)); 
    	
		dtaUseStatsVO.setPmFromDate(EgovDateUtil.addMonth(EgovDateUtil.getToday(), -1));
		dtaUseStatsVO.setPmToDate(EgovDateUtil.getToday());
		model.addAttribute("pmDtaUseStats", dtaUseStatsVO);
		
		return "egovframework/com/sts/dst/EgovDtaUseStatsList";
	}

	/**
	 * 자료이용현황 통계정보의 대상목록을 조회한다.
	 * @param reprtStatsVO - 자료이용현황 VO
	 * @return String
	 */
	@IncludedInfo(name="자료이용현황통계", listUrl="/sts/dst/selectDtaUseStatsListView.do", order = 161 ,gid = 30)
	@RequestMapping("/sts/dst/selectDtaUseStatsList.do")
	public String selectDtaUseStatsList(@RequestParam("pmFromDate") String pmFromDate,
            							@RequestParam("pmToDate") String pmToDate,
            							@ModelAttribute("dtaUseStatsVO") DtaUseStatsVO dtaUseStatsVO,
			                            @ModelAttribute("comDefaultCodeVO") ComDefaultCodeVO comDefaultCodeVO,
			                             ModelMap model) throws Exception {
		
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(dtaUseStatsVO.getPageIndex());
	    paginationInfo.setRecordCountPerPage(5);
		paginationInfo.setPageSize(dtaUseStatsVO.getPageSize());
		
		dtaUseStatsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		dtaUseStatsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		dtaUseStatsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		dtaUseStatsVO.setPmFromDate(pmFromDate);
		dtaUseStatsVO.setPmToDate(pmToDate);
		
		dtaUseStatsVO.setDtaUseStatsList(egovDtaUseStatsService.selectDtaUseStatsList(dtaUseStatsVO));
		model.addAttribute("dtaUseStatsList", dtaUseStatsVO.getDtaUseStatsList());
		
		int totPageCnt = egovDtaUseStatsService.selectDtaUseStatsListTotCnt(dtaUseStatsVO);
		paginationInfo.setTotalRecordCount(totPageCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		int totCnt = egovDtaUseStatsService.selectDtaUseStatsListBarTotCnt(dtaUseStatsVO);
		
		if (totCnt > 10 && totCnt <= 100) {
			if (dtaUseStatsVO.getMaxUnit() > 5.0f) {
				dtaUseStatsVO.setMaxUnit(5.0f);
			}
		} else if (totCnt > 100 && totCnt <= 1000) {
			if (dtaUseStatsVO.getMaxUnit() > 0.5f) {
				dtaUseStatsVO.setMaxUnit(0.5f);
			}
		} else if (dtaUseStatsVO.getMaxUnit() > 1000) {
			if (dtaUseStatsVO.getMaxUnit() > 0.05f) {
				dtaUseStatsVO.setMaxUnit(0.05f);
			}
		}

		dtaUseStatsVO.setDtaUseStatsBarList(egovDtaUseStatsService.selectDtaUseStatsBarList(dtaUseStatsVO));
		model.addAttribute("dtaUseStatsBarList", dtaUseStatsVO.getDtaUseStatsBarList());
		
    	comDefaultCodeVO.setCodeId("COM042");
    	model.addAttribute("cmmCode042List", egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO)); 
		
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		
/*		dtaUseStatsVO.setPmFromDate(EgovDateUtil.addMonth(EgovDateUtil.getToday(), -1));//2011.09.19
		dtaUseStatsVO.setPmToDate(EgovDateUtil.getToday());//2011.09.19
*/		
		return "egovframework/com/sts/dst/EgovDtaUseStatsList";
	}
	
	/**
	 * 자료이용현황 통계의 상세정보를 조회한다.
	 * @param reprtStatsVO - 자료이용현황 VO
	 * @return String
	 */
	@RequestMapping("/sts/dst/getDtaUseStats.do")
	public String selectDtaUseStats(@ModelAttribute("dtaUseStatsVO") DtaUseStatsVO dtaUseStatsVO,
			                         ModelMap model) throws Exception {
		
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(dtaUseStatsVO.getPageIndex());
	    paginationInfo.setRecordCountPerPage(dtaUseStatsVO.getPageUnit());
		paginationInfo.setPageSize(dtaUseStatsVO.getPageSize());
		
		dtaUseStatsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		dtaUseStatsVO.setLastIndex(paginationInfo.getLastRecordIndex());
		dtaUseStatsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		dtaUseStatsVO.setDtaUseStatsList(egovDtaUseStatsService.selectDtaUseStats(dtaUseStatsVO));
		model.addAttribute("dtaUseStatsList", dtaUseStatsVO.getDtaUseStatsList());
		
		int totCnt = egovDtaUseStatsService.selectDtaUseStatsTotCnt(dtaUseStatsVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		
		return "egovframework/com/sts/dst/EgovDtaUseStatsDetail";
	}


}
