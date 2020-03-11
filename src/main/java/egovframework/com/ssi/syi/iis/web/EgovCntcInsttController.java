package egovframework.com.ssi.syi.iis.web;

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
import egovframework.com.ssi.syi.iis.service.CntcInstt;
import egovframework.com.ssi.syi.iis.service.CntcInsttVO;
import egovframework.com.ssi.syi.iis.service.CntcService;
import egovframework.com.ssi.syi.iis.service.CntcServiceVO;
import egovframework.com.ssi.syi.iis.service.CntcSystem;
import egovframework.com.ssi.syi.iis.service.CntcSystemVO;
import egovframework.com.ssi.syi.iis.service.EgovCntcInsttService;
import egovframework.com.ssi.syi.ims.service.CntcMessageVO;
import egovframework.com.ssi.syi.ims.service.EgovCntcMessageService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 * 연계기관 관리에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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
 *   2011.8.26	 정진오			IncludedInfo annotation 추가
 *   2011.09.14  서준식			연계시스템 수정시 입력 데이터 표신 안되는 문제 수정
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */

@Controller
public class EgovCntcInsttController {

	@Resource(name = "CntcInsttService")
	private EgovCntcInsttService cntcInsttService;

	@Resource(name = "CntcMessageService")
	private EgovCntcMessageService cntcMessageService;

	/** EgovIdGnrService */
	@Resource(name = "egovCntcInsttIdGnrService")
	private EgovIdGnrService idgenService;

	/** EgovIdGnrService */
	@Resource(name = "egovCntcSystemIdGnrService")
	private EgovIdGnrService idgenServiceSys;

	/** EgovIdGnrService */
	@Resource(name = "egovCntcServiceIdGnrService")
	private EgovIdGnrService idgenServiceSvc;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 연계기관을 삭제한다.
	 * @param loginVO
	 * @param cntcInstt
	 * @param model
	 * @return "forward:/ssi/syi/iis/EgovCcmAdministCodeList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/removeCntcInstt.do")
	public String deleteCntcInstt(CntcInstt cntcInstt, ModelMap model) throws Exception {
		cntcInsttService.deleteCntcInstt(cntcInstt);
		return "forward:/ssi/syi/iis/getCntcInsttList.do";
	}

	/**
	 * 연계시스템을 삭제한다.
	 * @param loginVO
	 * @param cntcSystem
	 * @param model
	 * @return "forward:/ssi/syi/iis/EgovCcmAdministCodeList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/removeCntcSystem.do")
	public String deleteCntcSystem(CntcSystem cntcSystem, ModelMap model) throws Exception {
		cntcInsttService.deleteCntcSystem(cntcSystem);
		return "forward:/ssi/syi/iis/getCntcInsttList.do";
	}

	/**
	 * 연계서비스를 삭제한다.
	 * @param loginVO
	 * @param cntcSystem
	 * @param model
	 * @return "forward:/ssi/syi/iis/EgovCcmAdministCodeList.do"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/removeCntcService.do")
	public String deleteCntcService(CntcService cntcService, ModelMap model) throws Exception {
		cntcInsttService.deleteCntcService(cntcService);
		return "forward:/ssi/syi/iis/getCntcInsttList.do";
	}

	/**
	 * 연계기관을 등록한다.
	 * @param loginVO
	 * @param cntcInstt
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/ssi/syi/iis/EgovCntcInsttRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/addCntcInstt.do")
	public String insertCntcInstt(@ModelAttribute("cntcInstt") CntcInstt cntcInstt, BindingResult bindingResult, @RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("")) {

			return "egovframework/com/ssi/syi/iis/EgovCntcInsttRegist";
		} else if (sCmd.equals("Regist")) {

			beanValidator.validate(cntcInstt, bindingResult);
			if (bindingResult.hasErrors()) {

				return "egovframework/com/ssi/syi/iis/EgovCntcInsttRegist";
			}

			// 로그인VO에서  사용자 정보 가져오기
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
			cntcInstt.setFrstRegisterId(uniqId);

			// ID Generation
			String sInsttId = idgenService.getNextStringId();
			cntcInstt.setInsttId(sInsttId);

			cntcInsttService.insertCntcInstt(cntcInstt);

			return "forward:/ssi/syi/iis/getCntcInsttList.do";
		} else {
			return "forward:/ssi/syi/iis/getCntcInsttList.do";
		}
	}

	/**
	 * 연계시스템을 등록한다.
	 * @param loginVO
	 * @param cntcSystem
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/ssi/syi/iis/EgovCntcSystemRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/addCntcSystem.do")
	public String insertCntcSystem(@ModelAttribute("cntcSystem") CntcSystem cntcSystem, BindingResult bindingResult, @RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("")) {
			// 연계기관 리스트박스 데이터
			CntcInsttVO searchCntcInsttVO;
			searchCntcInsttVO = new CntcInsttVO();
			searchCntcInsttVO.setRecordCountPerPage(999999);
			searchCntcInsttVO.setFirstIndex(0);
			searchCntcInsttVO.setSearchCondition("CodeList");
			List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
			model.addAttribute("cntcInsttList", cntcInsttList);

			return "egovframework/com/ssi/syi/iis/EgovCntcSystemRegist";
		} else if (sCmd.equals("Regist")) {

			beanValidator.validate(cntcSystem, bindingResult);
			if (bindingResult.hasErrors()) {
				// 연계기관 리스트박스 데이터
				CntcInsttVO searchCntcInsttVO;
				searchCntcInsttVO = new CntcInsttVO();
				searchCntcInsttVO.setRecordCountPerPage(999999);
				searchCntcInsttVO.setFirstIndex(0);
				searchCntcInsttVO.setSearchCondition("CodeList");
				List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
				model.addAttribute("cntcInsttList", cntcInsttList);

				return "egovframework/com/ssi/syi/iis/EgovCntcSystemRegist";
			}

			// 로그인VO에서  사용자 정보 가져오기
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
			cntcSystem.setFrstRegisterId(uniqId);

			// ID Generation
			String sSysId = idgenServiceSys.getNextStringId();
			cntcSystem.setSysId(sSysId);

			cntcInsttService.insertCntcSystem(cntcSystem);
			return "forward:/ssi/syi/iis/getCntcInsttDetail.do";
		} else {
			return "forward:/ssi/syi/iis/getCntcInsttDetail.do";
		}
	}

	/**
	 * 연계서비스를 등록한다.
	 * @param loginVO
	 * @param cntcService
	 * @param bindingResult
	 * @param model
	 * @return "egovframework/com/ssi/syi/iis/EgovCntcServiceRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/addCntcService.do")
	public String insertCntcService(@ModelAttribute("cntcService") CntcService cntcService, BindingResult bindingResult, @RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("")) {
			// 연계기관 리스트박스 데이터
			CntcInsttVO searchCntcInsttVO;
			searchCntcInsttVO = new CntcInsttVO();
			searchCntcInsttVO.setRecordCountPerPage(999999);
			searchCntcInsttVO.setFirstIndex(0);
			searchCntcInsttVO.setSearchCondition("CodeList");
			List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
			model.addAttribute("cntcInsttList", cntcInsttList);

			// 연계시스템 리스트박스 데이터
			CntcSystemVO searchCntcSystemVO;
			searchCntcSystemVO = new CntcSystemVO();
			searchCntcSystemVO.setRecordCountPerPage(999999);
			searchCntcSystemVO.setFirstIndex(0);
			searchCntcSystemVO.setSearchCondition("CodeList");
			if (cntcService.getInsttId().equals("")) {
				if (cntcInsttList.size() > 0) {
					EgovMap emp = (EgovMap) cntcInsttList.get(0);
					cntcService.setInsttId(emp.get("insttId").toString());
				}
			}
			searchCntcSystemVO.setInsttId(cntcService.getInsttId());
			List<?> cntcSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
			model.addAttribute("cntcSystemList", cntcSystemList);

			// 연계메시지 리스트박스 데이터
			CntcMessageVO searchCntcMessageVO;
			searchCntcMessageVO = new CntcMessageVO();
			searchCntcMessageVO.setRecordCountPerPage(999999);
			searchCntcMessageVO.setFirstIndex(0);
			searchCntcMessageVO.setSearchCondition("CodeList");
			List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
			model.addAttribute("cntcMessageList", cntcMessageList);

			return "egovframework/com/ssi/syi/iis/EgovCntcServiceRegist";
		} else if (sCmd.equals("Regist")) {

			beanValidator.validate(cntcService, bindingResult);
			if (bindingResult.hasErrors()) {
				// 연계기관 리스트박스 데이터
				CntcInsttVO searchCntcInsttVO;
				searchCntcInsttVO = new CntcInsttVO();
				searchCntcInsttVO.setRecordCountPerPage(999999);
				searchCntcInsttVO.setFirstIndex(0);
				searchCntcInsttVO.setSearchCondition("CodeList");
				List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
				model.addAttribute("cntcInsttList", cntcInsttList);

				// 연계시스템 리스트박스 데이터
				CntcSystemVO searchCntcSystemVO;
				searchCntcSystemVO = new CntcSystemVO();
				searchCntcSystemVO.setRecordCountPerPage(999999);
				searchCntcSystemVO.setFirstIndex(0);
				searchCntcSystemVO.setSearchCondition("CodeList");
				if (cntcService.getInsttId().equals("")) {
					if (cntcInsttList.size() > 0) {
						EgovMap emp = (EgovMap) cntcInsttList.get(0);
						cntcService.setInsttId(emp.get("insttId").toString());
					}
				}
				searchCntcSystemVO.setInsttId(cntcService.getInsttId());
				List<?> cntcSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
				model.addAttribute("cntcSystemList", cntcSystemList);

				// 연계메시지 리스트박스 데이터
				CntcMessageVO searchCntcMessageVO;
				searchCntcMessageVO = new CntcMessageVO();
				searchCntcMessageVO.setRecordCountPerPage(999999);
				searchCntcMessageVO.setFirstIndex(0);
				searchCntcMessageVO.setSearchCondition("CodeList");
				List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
				model.addAttribute("cntcMessageList", cntcMessageList);

				return "egovframework/com/ssi/syi/iis/EgovCntcServiceRegist";
			}

			// 로그인VO에서  사용자 정보 가져오기
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
			cntcService.setFrstRegisterId(uniqId);

			// ID Generation
			String sSvcId = idgenServiceSvc.getNextStringId();
			cntcService.setSvcId(sSvcId);

			cntcInsttService.insertCntcService(cntcService);
			return "forward:/ssi/syi/iis/getCntcInsttDetail.do";
		} else {
			return "forward:/ssi/syi/iis/getCntcInsttDetail.do";
		}
	}

	/**
	 * 연계기관 상세내역을 조회한다.
	 * @param loginVO
	 * @param cntcInstt
	 * @param model
	 * @return "egovframework/com/ssi/syi/iis/EgovCcmCntcInsttDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/getCntcInsttDetail.do")
	public String selectCntcInsttDetail(@ModelAttribute("cntcInstt") CntcInstt cntcInstt, @ModelAttribute("cntcSystemVO") CntcSystemVO cntcSystemVO,
			@ModelAttribute("cntcServiceVO") CntcServiceVO cntcServiceVO, ModelMap model) throws Exception {
		// 연계메시지 리스트박스 데이터
		CntcMessageVO searchCntcMessageVO;
		searchCntcMessageVO = new CntcMessageVO();
		searchCntcMessageVO.setRecordCountPerPage(999999);
		searchCntcMessageVO.setFirstIndex(0);
		searchCntcMessageVO.setSearchCondition("CodeList");
		List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
		model.addAttribute("cntcMessageList", cntcMessageList);

		/* 연계기관 상세 */
		CntcInstt vo = cntcInsttService.selectCntcInsttDetail(cntcInstt);
		model.addAttribute("result", vo);

		/* 연계시스템 리스트 */
		cntcSystemVO.setRecordCountPerPage(999999);
		cntcSystemVO.setFirstIndex(0);
		cntcSystemVO.setSearchCondition("CodeList");
		List<?> cntcSystemList = cntcInsttService.selectCntcSystemList(cntcSystemVO);
		model.addAttribute("cntcSystemList", cntcSystemList);

		/* 연계서비스 리스트 */
		cntcServiceVO.setRecordCountPerPage(999999);
		cntcServiceVO.setFirstIndex(0);
		cntcServiceVO.setSearchCondition("CodeList_InsttId");
		List<?> cntcServiceList = cntcInsttService.selectCntcServiceList(cntcServiceVO);
		model.addAttribute("cntcServiceList", cntcServiceList);

		return "egovframework/com/ssi/syi/iis/EgovCntcInsttDetail";
	}

	/**
	 * 연계기관 목록을 조회한다.
	 * @param loginVO
	 * @param searchVO
	 * @param model
	 * @return "egovframework/com/ssi/syi/iis/EgovCntcInsttList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "연계기관관리", listUrl = "/ssi/syi/iis/getCntcInsttList.do", order = 1240, gid = 70)
	@RequestMapping(value = "/ssi/syi/iis/getCntcInsttList.do")
	public String selectCntcInsttList(@ModelAttribute("searchVO") CntcInsttVO searchVO, ModelMap model) throws Exception {
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

		List<?> CmmnCodeList = cntcInsttService.selectCntcInsttList(searchVO);
		model.addAttribute("resultList", CmmnCodeList);

		int totCnt = cntcInsttService.selectCntcInsttListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/ssi/syi/iis/EgovCntcInsttList";
	}

	/**
	 * 연계기관을 수정한다.
	 * @param loginVO
	 * @param cntcInstt
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/ssi/syi/iis/EgovCntcInsttUpdt"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/updateCntcInstt.do")
	public String updateCntcInstt(@ModelAttribute("cntcInstt") CntcInstt cntcInstt, BindingResult bindingResult, @RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("")) {
			CntcInstt vo = cntcInsttService.selectCntcInsttDetail(cntcInstt);
			model.addAttribute("cntcInstt", vo);

			return "egovframework/com/ssi/syi/iis/EgovCntcInsttUpdt";
		} else if (sCmd.equals("Modify")) {
			beanValidator.validate(cntcInstt, bindingResult);
			if (bindingResult.hasErrors()) {
				CntcInstt vo = cntcInsttService.selectCntcInsttDetail(cntcInstt);
				model.addAttribute("cntcInstt", vo);

				return "egovframework/com/ssi/syi/iis/EgovCntcInsttUpdt";
			}

			// 로그인VO에서  사용자 정보 가져오기
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

			cntcInstt.setLastUpdusrId(uniqId);
			cntcInsttService.updateCntcInstt(cntcInstt);
			return "forward:/ssi/syi/iis/getCntcInsttList.do";
		} else {
			return "forward:/ssi/syi/iis/getCntcInsttList.do";
		}
	}

	/**
	 * 연계시스템을 수정한다.
	 * @param loginVO
	 * @param cntcInstt
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/ssi/syi/iis/EgovCntcSystemModify"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/updateCntcSystem.do")
	public String updateCntcSystem(@ModelAttribute("cntcSystem") CntcSystem cntcSystem, BindingResult bindingResult, @RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("")) {
			// 연계기관 리스트박스 데이터
			CntcInsttVO searchCntcInsttVO;
			searchCntcInsttVO = new CntcInsttVO();
			searchCntcInsttVO.setRecordCountPerPage(999999);
			searchCntcInsttVO.setFirstIndex(0);
			searchCntcInsttVO.setSearchCondition("CodeList");
			List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
			model.addAttribute("cntcInsttList", cntcInsttList);

			// 연계시스템 리스트박스 데이터 2011.09.14
			CntcSystemVO searchCntcSystemVO;
			searchCntcSystemVO = new CntcSystemVO();
			searchCntcSystemVO.setRecordCountPerPage(999999);
			searchCntcSystemVO.setFirstIndex(0);
			searchCntcSystemVO.setSearchCondition("CodeList");
			if (cntcSystem.getInsttId().equals("")) {
				if (cntcInsttList.size() > 0) {
					EgovMap emp = (EgovMap) cntcInsttList.get(0);
					cntcSystem.setInsttId(emp.get("insttId").toString());
				}
			}
			searchCntcSystemVO.setInsttId(cntcSystem.getInsttId());
			List<?> cntcSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
			model.addAttribute("cntcSystemList", cntcSystemList);

			// 연계메시지 리스트박스 데이터 2011.09.14
			CntcMessageVO searchCntcMessageVO;
			searchCntcMessageVO = new CntcMessageVO();
			searchCntcMessageVO.setRecordCountPerPage(999999);
			searchCntcMessageVO.setFirstIndex(0);
			searchCntcMessageVO.setSearchCondition("CodeList");
			List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
			model.addAttribute("cntcMessageList", cntcMessageList);

			CntcSystem vo = cntcInsttService.selectCntcSystemDetail(cntcSystem);
			model.addAttribute("cntcSystem", vo);

			return "egovframework/com/ssi/syi/iis/EgovCntcSystemUpdt";
		} else if (sCmd.equals("Modify")) {
			beanValidator.validate(cntcSystem, bindingResult);
			if (bindingResult.hasErrors()) {
				// 연계기관 리스트박스 데이터
				CntcInsttVO searchCntcInsttVO;
				searchCntcInsttVO = new CntcInsttVO();
				searchCntcInsttVO.setRecordCountPerPage(999999);
				searchCntcInsttVO.setFirstIndex(0);
				searchCntcInsttVO.setSearchCondition("CodeList");
				List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
				model.addAttribute("cntcInsttList", cntcInsttList);

				// 연계시스템 리스트박스 데이터 2011.09.14
				CntcSystemVO searchCntcSystemVO;
				searchCntcSystemVO = new CntcSystemVO();
				searchCntcSystemVO.setRecordCountPerPage(999999);
				searchCntcSystemVO.setFirstIndex(0);
				searchCntcSystemVO.setSearchCondition("CodeList");
				if (cntcSystem.getInsttId().equals("")) {
					if (cntcInsttList.size() > 0) {
						EgovMap emp = (EgovMap) cntcInsttList.get(0);
						cntcSystem.setInsttId(emp.get("insttId").toString());
					}
				}
				searchCntcSystemVO.setInsttId(cntcSystem.getInsttId());
				List<?> cntcSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
				model.addAttribute("cntcSystemList", cntcSystemList);

				// 연계메시지 리스트박스 데이터 2011.09.14
				CntcMessageVO searchCntcMessageVO;
				searchCntcMessageVO = new CntcMessageVO();
				searchCntcMessageVO.setRecordCountPerPage(999999);
				searchCntcMessageVO.setFirstIndex(0);
				searchCntcMessageVO.setSearchCondition("CodeList");
				List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
				model.addAttribute("cntcMessageList", cntcMessageList);

				CntcSystem vo = cntcInsttService.selectCntcSystemDetail(cntcSystem);
				model.addAttribute("cntcSystem", vo);

				return "egovframework/com/ssi/syi/iis/EgovCntcSystemUpdt";
			}

			// 로그인VO에서  사용자 정보 가져오기
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

			cntcSystem.setLastUpdusrId(uniqId);
			cntcInsttService.updateCntcSystem(cntcSystem);
			return "forward:/ssi/syi/iis/getCntcInsttList.do";
		} else {
			return "forward:/ssi/syi/iis/getCntcInsttList.do";
		}
	}

	/**
	 * 연계서비스를 수정한다.
	 * @param loginVO
	 * @param cntcService
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "egovframework/com/ssi/syi/iis/EgovCntcServiceModify"
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssi/syi/iis/updateCntcService.do")
	public String updateCntcService(@ModelAttribute("cntcService") CntcService cntcService, BindingResult bindingResult, @RequestParam Map<?, ?> commandMap, ModelMap model)
			throws Exception {
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("")) {
			// 연계기관 리스트박스 데이터
			CntcInsttVO searchCntcInsttVO;
			searchCntcInsttVO = new CntcInsttVO();
			searchCntcInsttVO.setRecordCountPerPage(999999);
			searchCntcInsttVO.setFirstIndex(0);
			searchCntcInsttVO.setSearchCondition("CodeList");
			List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
			model.addAttribute("cntcInsttList", cntcInsttList);

			// 연계시스템 리스트박스 데이터
			CntcSystemVO searchCntcSystemVO;
			searchCntcSystemVO = new CntcSystemVO();
			searchCntcSystemVO.setRecordCountPerPage(999999);
			searchCntcSystemVO.setFirstIndex(0);
			searchCntcSystemVO.setSearchCondition("CodeList");
			if (cntcService.getInsttId().equals("")) {
				if (cntcInsttList.size() > 0) {
					EgovMap emp = (EgovMap) cntcInsttList.get(0);
					cntcService.setInsttId(emp.get("insttId").toString());
				}
			}
			searchCntcSystemVO.setInsttId(cntcService.getInsttId());
			List<?> cntcSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
			model.addAttribute("cntcSystemList", cntcSystemList);

			// 연계메시지 리스트박스 데이터
			CntcMessageVO searchCntcMessageVO;
			searchCntcMessageVO = new CntcMessageVO();
			searchCntcMessageVO.setRecordCountPerPage(999999);
			searchCntcMessageVO.setFirstIndex(0);
			searchCntcMessageVO.setSearchCondition("CodeList");
			List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
			model.addAttribute("cntcMessageList", cntcMessageList);

			CntcService vo = cntcInsttService.selectCntcServiceDetail(cntcService);
			model.addAttribute("cntcService", vo);

			return "egovframework/com/ssi/syi/iis/EgovCntcServiceUpdt";
		} else if (sCmd.equals("Modify")) {
			beanValidator.validate(cntcService, bindingResult);
			if (bindingResult.hasErrors()) {
				// 연계기관 리스트박스 데이터
				CntcInsttVO searchCntcInsttVO;
				searchCntcInsttVO = new CntcInsttVO();
				searchCntcInsttVO.setRecordCountPerPage(999999);
				searchCntcInsttVO.setFirstIndex(0);
				searchCntcInsttVO.setSearchCondition("CodeList");
				List<?> cntcInsttList = cntcInsttService.selectCntcInsttList(searchCntcInsttVO);
				model.addAttribute("cntcInsttList", cntcInsttList);

				// 연계시스템 리스트박스 데이터
				CntcSystemVO searchCntcSystemVO;
				searchCntcSystemVO = new CntcSystemVO();
				searchCntcSystemVO.setRecordCountPerPage(999999);
				searchCntcSystemVO.setFirstIndex(0);
				searchCntcSystemVO.setSearchCondition("CodeList");
				if (cntcService.getInsttId().equals("")) {
					if (cntcInsttList.size() > 0) {
						EgovMap emp = (EgovMap) cntcInsttList.get(0);
						cntcService.setInsttId(emp.get("insttId").toString());
					}
				}
				searchCntcSystemVO.setInsttId(cntcService.getInsttId());
				List<?> cntcSystemList = cntcInsttService.selectCntcSystemList(searchCntcSystemVO);
				model.addAttribute("cntcSystemList", cntcSystemList);

				// 연계메시지 리스트박스 데이터
				CntcMessageVO searchCntcMessageVO;
				searchCntcMessageVO = new CntcMessageVO();
				searchCntcMessageVO.setRecordCountPerPage(999999);
				searchCntcMessageVO.setFirstIndex(0);
				searchCntcMessageVO.setSearchCondition("CodeList");
				List<?> cntcMessageList = cntcMessageService.selectCntcMessageList(searchCntcMessageVO);
				model.addAttribute("cntcMessageList", cntcMessageList);

				CntcService vo = cntcInsttService.selectCntcServiceDetail(cntcService);
				model.addAttribute("cntcService", vo);

				return "egovframework/com/ssi/syi/iis/EgovCntcServiceUpdt";
			}

			// 로그인VO에서  사용자 정보 가져오기
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			String uniqId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

			cntcService.setLastUpdusrId(uniqId);
			cntcInsttService.updateCntcService(cntcService);
			return "forward:/ssi/syi/iis/getCntcInsttList.do";
		} else {
			return "forward:/ssi/syi/iis/getCntcInsttList.do";
		}
	}

	/**
	 * Map 내용을 확인한다.
	 * @param commandMap
	 * @return
	 */
	public String printParameterMap(@RequestParam Map<?, ?> commandMap) {
		String ret = "";
		for (Object key : commandMap.keySet()) {
			Object value = commandMap.get(key);

			ret += "key:" + key.toString() + " value:" + value.toString();
		}
		return ret;
	}

}