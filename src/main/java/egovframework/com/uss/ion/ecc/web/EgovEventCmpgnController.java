package egovframework.com.uss.ion.ecc.web;

import java.util.List;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ecc.service.EgovEventCmpgnService;
import egovframework.com.uss.ion.ecc.service.EventCmpgnVO;
import egovframework.com.uss.ion.ecc.service.TnextrlHrVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
/**
 * 행사/이벤트/캠페인을 처리하는 Controller Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2025.08.05  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovEventCmpgnController {

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "EgovEventCmpgnService")
	private EgovEventCmpgnService egovEventCmpgnService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/**
	 * 공통코드를 조회하여 Model에 추가한다.
	 *
	 * @param model ModelMap
	 * @param codeId 공통코드 ID
	 * @param attributeName Model에 추가할 속성명
	 * @throws Exception
	 */
	private void addCmmCodeToModel(ModelMap model, String codeId, String attributeName) throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId(codeId);
		List<CmmnDetailCode> codeList = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute(attributeName, codeList);
	}

	/**
	 * 행사/이벤트/캠페인 목록을 조회한다.
	 *
	 * @param eventCmpgnVO
	 * @param model
	 * @return "egovframework/com/uss/ion/ecc/EgovEventCmpgnList
	 * @throws Exception
	 */
	@IncludedInfo(name = "행사/이벤트/캠페인", order = 710, gid = 50)
	@RequestMapping(value = "/uss/ion/ecc/selectEventCmpgnList.do")
	public String selectEventCmpgnList(@ModelAttribute("eventCmpgnVO") EventCmpgnVO eventCmpgnVO, ModelMap model)
			throws Exception {

		/** EgovPropertyService.sample */
		eventCmpgnVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eventCmpgnVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eventCmpgnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eventCmpgnVO.getPageUnit());
		paginationInfo.setPageSize(eventCmpgnVO.getPageSize());

		eventCmpgnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eventCmpgnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eventCmpgnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<EventCmpgnVO> sampleList = egovEventCmpgnService.selectEventCmpgnList(eventCmpgnVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = egovEventCmpgnService.selectEventCmpgnListCnt(eventCmpgnVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/ecc/EgovEventCmpgnList";

	}

	/**
	 * 행사/이벤트/캠페인 목록을 조회한다.(Popup)
	 *
	 * @param eventCmpgnVO
	 * @param model
	 * @return "egovframework/com/uss/ion/ecc/EgovEventCmpgnList
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ecc/selectEventCmpgnListPopup.do")
	public String selectEventCmpgnListPopup(@ModelAttribute("eventCmpgnVO") EventCmpgnVO eventCmpgnVO, ModelMap model)
			throws Exception {

		/** EgovPropertyService.sample */
		eventCmpgnVO.setPageUnit(propertiesService.getInt("pageUnit"));
		eventCmpgnVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(eventCmpgnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(eventCmpgnVO.getPageUnit());
		paginationInfo.setPageSize(eventCmpgnVO.getPageSize());

		eventCmpgnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		eventCmpgnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		eventCmpgnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<EventCmpgnVO> sampleList = egovEventCmpgnService.selectEventCmpgnList(eventCmpgnVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = egovEventCmpgnService.selectEventCmpgnListCnt(eventCmpgnVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/ecc/EgovEventCmpgnListPopup";

	}

	/**
	 * 행사/이벤트/캠페인 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param eventCmpgnVO
	 * @param searchVO
	 * @param model
	 * @return "/uss/ion/ecc/EgovEventCmpgnDetail"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/selectEventCmpgnDetail.do")
	public String selectEventCmpgnDetail(EventCmpgnVO eventCmpgnVO, @ModelAttribute("eventCmpgnVO") EventCmpgnVO eventCmpgnVOSearch,
			ModelMap model) throws Exception {

		EventCmpgnVO vo = egovEventCmpgnService.selectEventCmpgnDetail(eventCmpgnVO);

		model.addAttribute("result", vo);

		return "egovframework/com/uss/ion/ecc/EgovEventCmpgnDetail";
	}

	/**
	 * 행사/이벤트/캠페인 등록전 단계
	 * 
	 * @param searchVO
	 * @param model
	 * @return "/uss/ion/ecc/EgovEventCmpgnRegist"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/insertEventCmpgnView.do")
	public String insertEventCmpgnView(@ModelAttribute("eventCmpgnVO") EventCmpgnVO eventCmpgnVO, ModelMap model)
			throws Exception {

		addCmmCodeToModel(model, "COM035", "eventTyCode");

		model.addAttribute("eventCmpgnVO", new EventCmpgnVO());

		return "egovframework/com/uss/ion/ecc/EgovEventCmpgnRegist";

	}

	/**
	 * 행사/이벤트/캠페인을 등록한다.
	 * 
	 * @param searchVO
	 * @param eventCmpgnVO
	 * @param bindingResult
	 * @return "forward:/uss/ion/ecc/selectEventCmpgnList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/insertEventCmpgn.do")
	public String insertEventCmpgn(@Valid @ModelAttribute("eventCmpgnVO") EventCmpgnVO eventCmpgnVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			addCmmCodeToModel(model, "COM035", "eventTyCode");
			return "egovframework/com/uss/ion/ecc/EgovEventCmpgnRegist";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

		eventCmpgnVO.setFrstRegisterId(frstRegisterId); // 최초등록자ID
		eventCmpgnVO.setLastUpdusrId(frstRegisterId); // 최종수정자ID

		egovEventCmpgnService.insertEventCmpgn(eventCmpgnVO);

		return "forward:/uss/ion/ecc/selectEventCmpgnList.do";
	}

	/**
	 * 행사/이벤트/캠페인을 수정하기 전 처리
	 * 
	 * @param eventId
	 * @param searchVO
	 * @param model
	 * @return "/uss/ion/ecc/EgovEventCmpgnUpdt"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/updateEventCmpgnView.do")
	public String updateEventCmpgnView(@RequestParam("eventId") String eventId,
			@ModelAttribute("eventCmpgnVO") EventCmpgnVO eventCmpgnVO, ModelMap model) throws Exception {

		addCmmCodeToModel(model, "COM035", "eventTyCode");

		// Primary Key 값 세팅
		eventCmpgnVO.setEventId(eventId);
		model.addAttribute("eventCmpgnVO", egovEventCmpgnService.selectEventCmpgnDetail(eventCmpgnVO));

		return "egovframework/com/uss/ion/ecc/EgovEventCmpgnUpdt";
	}

	/**
	 * 행사/이벤트/캠페인을 수정처리한다.
	 * 
	 * @param searchVO
	 * @param eventCmpgnVO
	 * @param bindingResult
	 * @return "forward:/uss/ion/ecc/selectEventCmpgnList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/updateEventCmpgn.do")
	public String updateEventCmpgn(@Valid @ModelAttribute("eventCmpgnVO") EventCmpgnVO eventCmpgnVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			addCmmCodeToModel(model, "COM035", "eventTyCode");
			return "egovframework/com/uss/ion/ecc/EgovEventCmpgnUpdt";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		eventCmpgnVO.setLastUpdusrId(lastUpdusrId); // 최종수정자ID

		egovEventCmpgnService.updateEventCmpgn(eventCmpgnVO);

		return "forward:/uss/ion/ecc/selectEventCmpgnList.do";

	}

	/**
	 * 행사/이벤트/캠페인을 삭제처리한다.
	 * 
	 * @param eventCmpgnVO
	 * @param searchVO
	 * @return "forward:/uss/ion/ecc/selectEventCmpgnList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/deleteEventCmpgn.do")
	public String deleteEventCmpgn(EventCmpgnVO eventCmpgnVO, @ModelAttribute("eventCmpgnVO") EventCmpgnVO eventCmpgnVOSearch)
			throws Exception {

		egovEventCmpgnService.deleteEventCmpgn(eventCmpgnVO);

		return "forward:/uss/ion/ecc/selectEventCmpgnList.do";
	}

	/**
	 * 외부인사정보 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @param tnextrlHrVO
	 * @param model
	 * @return "egovframework/com/uss/ion/ecc/EgovTnextrlHrList
	 * @throws Exception
	 */
	@IncludedInfo(name = "외부인사정보", order = 711, gid = 50)
	@RequestMapping(value = "/uss/ion/ecc/selectTnextrlHrList.do")
	public String selectTnextrlHrList(@ModelAttribute("tnextrlHrVO") TnextrlHrVO tnextrlHrVO, ModelMap model)
			throws Exception {

		/** EgovPropertyService.sample */
		tnextrlHrVO.setPageUnit(propertiesService.getInt("pageUnit"));
		tnextrlHrVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(tnextrlHrVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(tnextrlHrVO.getPageUnit());
		paginationInfo.setPageSize(tnextrlHrVO.getPageSize());

		tnextrlHrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		tnextrlHrVO.setLastIndex(paginationInfo.getLastRecordIndex());
		tnextrlHrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<TnextrlHrVO> sampleList = egovEventCmpgnService.selectTnextrlHrList(tnextrlHrVO);
		model.addAttribute("resultList", sampleList);

		int totCnt = egovEventCmpgnService.selectTnextrlHrListCnt(tnextrlHrVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/ecc/EgovTnextrlHrList";

	}

	/**
	 * 외부인사정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param tnextrlHrVO
	 * @param searchVO
	 * @param model
	 * @return "/uss/ion/ecc/EgovTnextrlHrDetail"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/selectTnextrlHrDetail.do")
	public String selectTnextrlHrDetail(TnextrlHrVO tnextrlHrVO, @ModelAttribute("tnextrlHrVO") TnextrlHrVO tnextrlHrVOSearch,
			ModelMap model) throws Exception {

		TnextrlHrVO vo = egovEventCmpgnService.selectTnextrlHrDetail(tnextrlHrVO);

		model.addAttribute("result", vo);

		return "egovframework/com/uss/ion/ecc/EgovTnextrlHrDetail";
	}

	/**
	 * 외부인사정보 등록전 단계
	 *
	 * @param searchVO
	 * @param model
	 * @return "/uss/ion/ecc/EgovTnextrlHrRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ecc/insertTnextrlHrView.do")
	public String insertTnextrlHrView(@ModelAttribute("tnextrlHrVO") TnextrlHrVO tnextrlHrVO, ModelMap model) throws Exception {

		addCmmCodeToModel(model, "COM014", "sexdstnCode"); // 성별
		addCmmCodeToModel(model, "COM034", "occpTyCode"); // 직업코드

		model.addAttribute("tnextrlHrVO", new TnextrlHrVO());

		return "egovframework/com/uss/ion/ecc/EgovTnextrlHrRegist";

	}

	/**
	 * 외부인사정보를 등록한다.
	 * 
	 * @param searchVO
	 * @param tnextrlHrVO
	 * @param bindingResult
	 * @return "forward:/uss/ion/ecc/selectTnextrlHrList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/insertTnextrlHr.do")
	public String insertTnextrlHr(@Valid @ModelAttribute("tnextrlHrVO") TnextrlHrVO tnextrlHrVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			addCmmCodeToModel(model, "COM014", "sexdstnCode");
			addCmmCodeToModel(model, "COM034", "occpTyCode");
			return "egovframework/com/uss/ion/ecc/EgovTnextrlHrRegist";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());

		tnextrlHrVO.setFrstRegisterId(frstRegisterId); // 최초등록자ID
		tnextrlHrVO.setLastUpdusrId(frstRegisterId); // 최종수정자ID

		egovEventCmpgnService.insertTnextrlHr(tnextrlHrVO);

		return "redirect:/uss/ion/ecc/selectTnextrlHrList.do";
	}

	/**
	 * 외부인사정보 수정전 단계
	 *
	 * @param extrlHrId
	 * @param searchVO
	 * @param model
	 * @return "/uss/ion/ecc/EgovTnextrlHrUpdt"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/ion/ecc/updateTnextrlHrView.do")
	public String updateTnextrlHrView(@RequestParam("extrlHrId") String extrlHrId,
			@ModelAttribute("tnextrlHrVO") TnextrlHrVO tnextrlHrVO, ModelMap model) throws Exception {

		addCmmCodeToModel(model, "COM014", "sexdstnCode"); // 성별
		addCmmCodeToModel(model, "COM034", "occpTyCode"); // 직업코드

		// Primary Key 값 세팅
		tnextrlHrVO.setExtrlHrId(extrlHrId);
		model.addAttribute("tnextrlHrVO", egovEventCmpgnService.selectTnextrlHrDetail(tnextrlHrVO));

		return "egovframework/com/uss/ion/ecc/EgovTnextrlHrUpdt";
	}

	/**
	 * 외부인사정보를 수정처리한다.
	 * 
	 * @param searchVO
	 * @param tnextrlHrVO
	 * @param bindingResult
	 * @return "redirect:/uss/ion/ecc/selectTnextrlHrList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/updateTnextrlHr.do")
	public String updateTnextrlHr(@Valid @ModelAttribute("tnextrlHrVO") TnextrlHrVO tnextrlHrVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			addCmmCodeToModel(model, "COM014", "sexdstnCode");
			addCmmCodeToModel(model, "COM034", "occpTyCode");
			return "egovframework/com/uss/ion/ecc/EgovTnextrlHrUpdt";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		tnextrlHrVO.setLastUpdusrId(lastUpdusrId); // 최종수정자ID

		egovEventCmpgnService.updateTnextrlHr(tnextrlHrVO);

		// redirect로 목록 요청하여 수정 폼의 eventId 등이 전달되지 않도록 함(전체 목록 표시)
		return "redirect:/uss/ion/ecc/selectTnextrlHrList.do";

	}

	/**
	 * 외부인사정보를 삭제처리한다.
	 * 
	 * @param tnextrlHrVO
	 * @param searchVO
	 * @return "forward:/uss/ion/ecc/selectTnextrlHrList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/ecc/deleteTnextrlHr.do")
	public String deleteTnextrlHr(TnextrlHrVO tnextrlHrVO, @ModelAttribute("tnextrlHrVO") TnextrlHrVO tnextrlHrVOSearch)
			throws Exception {

		egovEventCmpgnService.deleteTnextrlHr(tnextrlHrVO);

		return "forward:/uss/ion/ecc/selectTnextrlHrList.do";
	}

}
