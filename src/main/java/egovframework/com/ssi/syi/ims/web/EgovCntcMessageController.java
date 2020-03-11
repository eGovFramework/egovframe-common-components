package egovframework.com.ssi.syi.ims.web;

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

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.ssi.syi.ims.service.CntcMessage;
import egovframework.com.ssi.syi.ims.service.CntcMessageItem;
import egovframework.com.ssi.syi.ims.service.CntcMessageItemVO;
import egovframework.com.ssi.syi.ims.service.CntcMessageVO;
import egovframework.com.ssi.syi.ims.service.EgovCntcMessageService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 * 연계메시지 관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
public class EgovCntcMessageController {

	@Resource(name = "CntcMessageService")
    private EgovCntcMessageService cntcMessageService;

	/** EgovIdGnrService */
	@Resource(name="egovCntcMessageIdGnrService")
	private EgovIdGnrService idgenService;

	/** EgovIdGnrService */
	@Resource(name="egovCntcMessageItemIdGnrService")
	private EgovIdGnrService idgenServiceItem;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 연계메시지를 삭제한다.
	 * @param loginVO
	 * @param cntcMessage
	 * @param model
	 * @return "forward:/ssi/syi/ims/EgovCcmAdministCodeList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/ims/removeCntcMessage.do")
	public String deleteCntcMessage (CntcMessage cntcMessage
			, ModelMap model
			) throws Exception {
    	cntcMessageService.deleteCntcMessage(cntcMessage);
        return "forward:/ssi/syi/ims/getCntcMessageList.do";
	}

	/**
	 * 연계메시지항목을 삭제한다.
	 * @param loginVO
	 * @param cntcMessageItem
	 * @param model
	 * @return "forward:/ssi/syi/ims/EgovCcmAdministCodeList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/ims/removeCntcMessageItem.do")
	public String deleteCntcMessageItem (CntcMessageItem cntcMessageItem
			, ModelMap model
			) throws Exception {
    	cntcMessageService.deleteCntcMessageItem(cntcMessageItem);
        return "forward:/ssi/syi/ims/getCntcMessageList.do";
	}

	/**
	 * 연계메시지를 등록한다.
	 * @param loginVO
	 * @param cntcMessage
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/ssi/syi/ims/EgovCcmCntcMessageRegist"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/ims/addCntcMessage.do")
	public String insertCntcMessage (@ModelAttribute("cntcMessage") CntcMessage cntcMessage
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if   (sCmd.equals("")) {
			// 연계메시지 리스트박스 데이터
    		CntcMessageVO searchCntcMessageVO;
    		searchCntcMessageVO = new CntcMessageVO();
    		searchCntcMessageVO.setRecordCountPerPage(999999);
    		searchCntcMessageVO.setFirstIndex(0);
    		searchCntcMessageVO.setSearchCondition("CodeList");
            List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
            model.addAttribute("cntcMessageList", cntcMessageList);

            return "egovframework/com/ssi/syi/ims/EgovCntcMessageRegist";
    	} else if (sCmd.equals("Regist")) {

	    	beanValidator.validate(cntcMessage, bindingResult);
			if (bindingResult.hasErrors()){
				// 연계메시지 리스트박스 데이터
	    		CntcMessageVO searchCntcMessageVO;
	    		searchCntcMessageVO = new CntcMessageVO();
	    		searchCntcMessageVO.setRecordCountPerPage(999999);
	    		searchCntcMessageVO.setFirstIndex(0);
	    		searchCntcMessageVO.setSearchCondition("CodeList");
	            List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
	            model.addAttribute("cntcMessageList", cntcMessageList);

				return "egovframework/com/ssi/syi/ims/EgovCntcMessageRegist";
			}

	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	String	uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
	    	cntcMessage.setFrstRegisterId(uniqId);

	    	// ID Generation
	    	String sCntcMessageId = idgenService.getNextStringId();
	    	cntcMessage.setCntcMessageId(sCntcMessageId);

	    	cntcMessageService.insertCntcMessage(cntcMessage);
	        return "forward:/ssi/syi/ims/getCntcMessageList.do";
    	} else {
	        return "forward:/ssi/syi/ims/getCntcMessageList.do";
    	}
    }

	/**
	 * 연계메시지 항목을 등록한다.
	 * @param loginVO
	 * @param cntcMessageItem
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/ssi/syi/ims/EgovCcmCntcMessageRegist"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/ims/addCntcMessageItem.do")
	public String insertCntcMessageItem (@ModelAttribute("cntcMessageItem") CntcMessageItem cntcMessageItem
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if   (sCmd.equals("")) {

			// 연계메시지 리스트박스 데이터
    		CntcMessageVO searchCntcMessageVO;
    		searchCntcMessageVO = new CntcMessageVO();
    		searchCntcMessageVO.setRecordCountPerPage(999999);
    		searchCntcMessageVO.setFirstIndex(0);
    		searchCntcMessageVO.setSearchCondition("CodeList");
            List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
            model.addAttribute("cntcMessageList", cntcMessageList);

            return "egovframework/com/ssi/syi/ims/EgovCntcMessageItemRegist";
    	} else if (sCmd.equals("Regist")) {

	    	beanValidator.validate(cntcMessageItem, bindingResult);
			if (bindingResult.hasErrors()){
				// 연계메시지 리스트박스 데이터
	    		CntcMessageVO searchCntcMessageVO;
	    		searchCntcMessageVO = new CntcMessageVO();
	    		searchCntcMessageVO.setRecordCountPerPage(999999);
	    		searchCntcMessageVO.setFirstIndex(0);
	    		searchCntcMessageVO.setSearchCondition("CodeList");
	            List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
	            model.addAttribute("cntcMessageList", cntcMessageList);

				return "egovframework/com/ssi/syi/ims/EgovCntcMessageItemRegist";
			}

	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	String	uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
	    	cntcMessageItem.setFrstRegisterId(uniqId);

	    	// ID Generation
	    	String sItemId = idgenServiceItem.getNextStringId();
	    	cntcMessageItem.setItemId(sItemId);

	    	cntcMessageService.insertCntcMessageItem(cntcMessageItem);
	        return "forward:/ssi/syi/ims/getCntcMessageDetail.do";
    	} else {
	        return "forward:/ssi/syi/ims/getCntcMessageDetail.do";
    	}
    }

	/**
	 * 연계메시지 상세내역을 조회한다.
	 * @param loginVO
	 * @param cntcMessage
	 * @param model
	 * @return "egovframework/com/ssi/syi/ims/EgovCcmCntcMessageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/ssi/syi/ims/getCntcMessageDetail.do")
 	public String selectCntcMessageDetail (@ModelAttribute("cntcMessage") CntcMessage cntcMessage
			, @ModelAttribute("cntcMessageItemVO") CntcMessageItemVO cntcMessageItemVO
 			, ModelMap model
 			) throws Exception {
    	/* 연계메시지 상세 */
    	CntcMessage vo = cntcMessageService.selectCntcMessageDetail(cntcMessage);
		model.addAttribute("result", vo);

    	/* 연계메시지항목 리스트 */
		cntcMessageItemVO.setRecordCountPerPage(9999999);
		cntcMessageItemVO.setFirstIndex(0);

    	cntcMessageItemVO.setSearchCondition("CodeList");
        List<?> cntcMessageItemList = cntcMessageService.selectCntcMessageItemList(cntcMessageItemVO);
        model.addAttribute("cntcMessageItemList", cntcMessageItemList);

		return "egovframework/com/ssi/syi/ims/EgovCntcMessageDetail";
	}

    /**
	 * 연계메시지 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "egovframework/com/ssi/syi/ims/EgovCcmCntcMessageList"
     * @throws Exception
     */
	@IncludedInfo(name="연계메시지관리", listUrl="/ssi/syi/ims/getCntcMessageList.do", order = 1230,gid = 70)
    @RequestMapping(value="/ssi/syi/ims/getCntcMessageList.do")
	public String selectCntcMessageList (@ModelAttribute("searchVO") CntcMessageVO searchVO
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

        List<?> CmmnCodeList = cntcMessageService.selectCntcMessageList(searchVO);
        model.addAttribute("resultList", CmmnCodeList);

        int totCnt = cntcMessageService.selectCntcMessageListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/com/ssi/syi/ims/EgovCntcMessageList";
	}

	/**
	 * 연계메시지를 수정한다.
	 * @param loginVO
	 * @param cntcMessage
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/ssi/syi/ims/EgovCcmAdministCodeModify"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/ims/updateCntcMessage.do")
	public String updateCntcMessage (@ModelAttribute("cntcMessage") CntcMessage cntcMessage
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("")) {
			// 연계메시지 리스트박스 데이터
    		CntcMessageVO searchCntcMessageVO;
    		searchCntcMessageVO = new CntcMessageVO();
    		searchCntcMessageVO.setRecordCountPerPage(999999);
    		searchCntcMessageVO.setFirstIndex(0);
    		searchCntcMessageVO.setSearchCondition("CodeList");
            List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
            model.addAttribute("cntcMessageList", cntcMessageList);

            CntcMessage vo = cntcMessageService.selectCntcMessageDetail(cntcMessage);
    		model.addAttribute("cntcMessage", vo);

    		return "egovframework/com/ssi/syi/ims/EgovCntcMessageUpdt";
    	} else if (sCmd.equals("Modify")) {
            beanValidator.validate(cntcMessage, bindingResult);
    		if (bindingResult.hasErrors()){
    			// 연계메시지 리스트박스 데이터
        		CntcMessageVO searchCntcMessageVO;
        		searchCntcMessageVO = new CntcMessageVO();
        		searchCntcMessageVO.setRecordCountPerPage(999999);
        		searchCntcMessageVO.setFirstIndex(0);
        		searchCntcMessageVO.setSearchCondition("CodeList");
                List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
                model.addAttribute("cntcMessageList", cntcMessageList);

                CntcMessage vo = cntcMessageService.selectCntcMessageDetail(cntcMessage);
        		model.addAttribute("cntcMessage", vo);

        		return "egovframework/com/ssi/syi/ims/EgovCntcMessageUpdt";
    		}

	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	String	uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

	    	cntcMessage.setLastUpdusrId(uniqId);
	    	cntcMessageService.updateCntcMessage(cntcMessage);
	        return "forward:/ssi/syi/ims/getCntcMessageList.do";
    	} else {
    		return "forward:/ssi/syi/ims/getCntcMessageList.do";
    	}
    }

	/**
	 * 연계메시지항목을 수정한다.
	 * @param loginVO
	 * @param cntcMessageItem
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/ssi/syi/ims/EgovCcmAdministCodeModify"
	 * @throws Exception
	 */
    @RequestMapping(value="/ssi/syi/ims/updateCntcMessageItem.do")
	public String updateCntcMessageItem (@ModelAttribute("cntcMessageItem") CntcMessageItem cntcMessageItem
			, BindingResult bindingResult
			, @RequestParam Map<?, ?> commandMap
			, ModelMap model
			) throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("")) {
			// 연계메시지 리스트박스 데이터
    		CntcMessageVO searchCntcMessageVO;
    		searchCntcMessageVO = new CntcMessageVO();
    		searchCntcMessageVO.setRecordCountPerPage(999999);
    		searchCntcMessageVO.setFirstIndex(0);
    		searchCntcMessageVO.setSearchCondition("CodeList");
            List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
            model.addAttribute("cntcMessageList", cntcMessageList);

            CntcMessageItem vo = cntcMessageService.selectCntcMessageItemDetail(cntcMessageItem);
    		model.addAttribute("cntcMessageItem", vo);

    		return "egovframework/com/ssi/syi/ims/EgovCntcMessageItemUpdt";
    	} else if (sCmd.equals("Modify")) {
            beanValidator.validate(cntcMessageItem, bindingResult);
    		if (bindingResult.hasErrors()){
    			// 연계메시지 리스트박스 데이터
        		CntcMessageVO searchCntcMessageVO;
        		searchCntcMessageVO = new CntcMessageVO();
        		searchCntcMessageVO.setRecordCountPerPage(999999);
        		searchCntcMessageVO.setFirstIndex(0);
        		searchCntcMessageVO.setSearchCondition("CodeList");
                List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
                model.addAttribute("cntcMessageList", cntcMessageList);

                CntcMessageItem vo = cntcMessageService.selectCntcMessageItemDetail(cntcMessageItem);
        		model.addAttribute("cntcMessageItem", vo);

        		return "egovframework/com/ssi/syi/ims/EgovCntcMessageItemUpdt";
    		}

	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	String	uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

	    	cntcMessageItem.setLastUpdusrId(uniqId);
	    	cntcMessageService.updateCntcMessageItem(cntcMessageItem);
	        return "forward:/ssi/syi/ims/getCntcMessageList.do";
    	} else {
    		return "forward:/ssi/syi/ims/getCntcMessageList.do";
    	}
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