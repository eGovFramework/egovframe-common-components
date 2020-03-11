package egovframework.com.uss.mpe.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.uss.mpe.service.EgovIndvdlPgeService;
import egovframework.com.uss.mpe.service.IndvdlPgeVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개요
 * - 마이페이지에 대한 Controller 클래스를 정의한다.
 *
 * 상세내용
 * - 마이페이지 콘텐츠의 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 마이페이지 콘텐츠의 조회기능은 목록조회, 상세조회로 구분된다.
 * - 등록된 콘텐츠를 마이페이지에 추가, 삭제, 조회 기능을 제공한다.
 * @author 이창원
 * @version 1.0
 * @created 05-8-2009 오후 2:19:27
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일        수정자           수정내용
 *  ----------  ----------    ---------------------------
 *  2009.08.04  이창원          최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *  2016.8.31	김연호			표준프레임워크 3.6 개선
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class EgovIndvdlPgeController {

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "EgovIndvdlPgeService")
    private EgovIndvdlPgeService egovIndvdlPgeService;
    
    /**
	 * 컨텐츠 목록을 조회한다.
	 * @param indvdlPgeVO - 마이페이지 콘텐츠 Vo
	 * @return 
	 *
	 * @param indvdlPgeVO
	 */
	@IncludedInfo(name="마이페이지관리", order = 480 ,gid = 50)
	@RequestMapping(value="/uss/mpe/selectIndvdlPgeList.do")
	public String selectIndvdlPgeList(@ModelAttribute("searchVO") IndvdlPgeVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.IndvdlPgeList */
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

        List<?> list = egovIndvdlPgeService.selectIndvdlPgeList(searchVO);
        model.addAttribute("resultList", list);

        int totCnt = egovIndvdlPgeService.selectIndvdlPgeListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/uss/mpe/EgovIndvdlPgeList";
	}

	/**
     * 컨텐츠 목록에 대한 상세정보를 조회한다.
     * @param indvdlPgeVO
     * @param searchVO
     * @param model
     * @return	"/uss/mpe/EgovIndvdlPgeDetail"
     * @throws Exception
     */
     @RequestMapping("/uss/mpe/selectIndvdlPgeDetail.do")
     public String selectIndvdlPgeDetail(IndvdlPgeVO indvdlPgeVO, @ModelAttribute("searchVO") IndvdlPgeVO searchVO, ModelMap model) throws Exception {

 		IndvdlPgeVO vo = egovIndvdlPgeService.selectIndvdlPgeDetail(indvdlPgeVO);

 		model.addAttribute("result", vo);

         return	"egovframework/com/uss/mpe/EgovIndvdlPgeDetail";
     }
     
     /**
      * 컨텐츠 등록전 단계
      * @param searchVO
      * @param model
      * @return	"/uss/mpe/EgovIndvdlPgeRegist"
      * @throws Exception
      */
     @RequestMapping("/uss/mpe/insertIndvdlPgeView.do")
     public String insertIndvdlPgeView(@ModelAttribute("searchVO") IndvdlPgeVO searchVO, Model model) throws Exception {

         model.addAttribute("indvdlPgeVO", new IndvdlPgeVO());

         return "egovframework/com/uss/mpe/EgovIndvdlPgeRegist";

     }
     
     /**
      * 컨텐츠를 등록한다.
      * @param searchVO
      * @param indvdlPgeVO
      * @param bindingResult
      * @return	"forward:/uss/mpe/selectIndvdlPgeList.do"
      * @throws Exception
      */
      @RequestMapping("/uss/mpe/insertIndvdlPge.do")
      public String insertIndvdlPge(@ModelAttribute("searchVO") IndvdlPgeVO searchVO, @ModelAttribute("indvdlPgeVO") IndvdlPgeVO indvdlPgeVO, BindingResult bindingResult)
              throws Exception {

      	beanValidator.validate(indvdlPgeVO, bindingResult);
  		if(bindingResult.hasErrors()){
  			return "egovframework/com/uss/mpe/EgovIndvdlPgeRegist";
  		}

          egovIndvdlPgeService.insertIndvdlPge(indvdlPgeVO);

          return "forward:/uss/mpe/selectIndvdlPgeList.do";
      }
      
      /**
       * 컨텐츠정보 수정 전 처리
       * @param cntntsId
       * @param searchVO
       * @param model
       * @return	"/uss/mpe/EgovIndvdlPgeUpdt"
       * @throws Exception
       */
      @RequestMapping("/uss/mpe/updateIndvdlPgeView.do")
      public String updateIndvdlPgeView(@RequestParam("cntntsId") String cntntsId ,
              @ModelAttribute("searchVO") IndvdlPgeVO searchVO, ModelMap model)
              throws Exception {

    	  IndvdlPgeVO indvdlPgeVO = new IndvdlPgeVO();

          // Primary Key 값 세팅
          indvdlPgeVO.setCntntsId(cntntsId);

          model.addAttribute("indvdlPgeVO", egovIndvdlPgeService.selectIndvdlPgeDetail(indvdlPgeVO));

          return "egovframework/com/uss/mpe/EgovIndvdlPgeUpdt";
      }
      
      /**
       * 컨텐츠정보를 수정한다.
       * @param searchVO
       * @param indvdlPgeVO
       * @param bindingResult
       * @return	"forward:/uss/mpe/selectIndvdlPgeList.do"
       * @throws Exception
       */
      @RequestMapping("/uss/mpe/updateIndvdlPge.do")
      public String updateIndvdlPge(@ModelAttribute("searchVO") IndvdlPgeVO searchVO, @ModelAttribute("indvdlPgeVO") IndvdlPgeVO indvdlPgeVO, BindingResult bindingResult)
              throws Exception {

      	// Validation
      	beanValidator.validate(indvdlPgeVO, bindingResult);
  		if(bindingResult.hasErrors()){
  			return "egovframework/com/uss/mpe/EgovIndvdlPgeUpdt";
  		}

      	egovIndvdlPgeService.updateIndvdlPge(indvdlPgeVO);

          return "forward:/uss/mpe/selectIndvdlPgeList.do";

      }
	
}
