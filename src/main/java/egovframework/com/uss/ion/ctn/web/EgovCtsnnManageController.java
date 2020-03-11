package egovframework.com.uss.ion.ctn.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ctn.service.CtsnnManage;
import egovframework.com.uss.ion.ctn.service.CtsnnManageVO;
import egovframework.com.uss.ion.ctn.service.EgovCtsnnManageService;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 개요
 * - 경조관리에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 경조관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 경조관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 *  * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2010.6.15	이용          최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */

@Controller
public class EgovCtsnnManageController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "egovCtsnnManageService")
    private EgovCtsnnManageService egovCtsnnManageService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

    @Autowired
	 private DefaultBeanValidator beanValidator;

    /**
	 * 경조관리 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/uss/ion/ctn/EgovCtsnnManageListView.do")
    public String selectCtsnnManageListView(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
                                            ModelMap model) throws Exception {
    	List<?> ctsnnCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM054");
		ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("ctsnnCodeList",    ctsnnCdCodeList);
        return "egovframework/com/uss/ion/ctn/EgovCtsnnManageList";
    }

	/**
	 * 경조관리정보를 관리하기 위해 등록된 경조관리 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="직원경조사관리",order = 890 ,gid = 50)
    @RequestMapping(value="/uss/ion/ctn/selectCtsnnManageList.do")
	 public String selectCtsnnManageList(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                                 ModelMap model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ctsnnManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ctsnnManageVO.getPageUnit());
		paginationInfo.setPageSize(ctsnnManageVO.getPageSize());

		ctsnnManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ctsnnManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ctsnnManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		ctsnnManageVO.setCtsnnManageList(egovCtsnnManageService.selectCtsnnManageList(ctsnnManageVO));

		int totCnt = egovCtsnnManageService.selectCtsnnManageListTotCnt(ctsnnManageVO);
		paginationInfo.setTotalRecordCount(totCnt);

    	List<?> ctsnnCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM054");
		ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("paginationInfo" ,   paginationInfo );
		model.addAttribute("ctsnnManageList",   ctsnnManageVO.getCtsnnManageList());
        model.addAttribute("ctsnnCodeList"  ,   ctsnnCdCodeList);
 		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/ctn/EgovCtsnnManageList";
	}

	/**
	 * 등록된 경조관리의 상세정보를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/EgovCtsnnManageDetail.do")
	 public String selectCtsnnManage(@ModelAttribute("ctsnnManage") CtsnnManage ctsnnManage,
			                         @ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                         @RequestParam Map<?, ?> commandMap,
			                         ModelMap model) throws Exception {

    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd"); // 상세정보 구분
    	ctsnnManageVO.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManageVO.getReqstDe()));

        // 등록 상세정보
    	CtsnnManageVO ctsnnManageVOTemp = egovCtsnnManageService.selectCtsnnManage(ctsnnManageVO);

    	model.addAttribute("ctsnnManageVO", ctsnnManageVOTemp);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		if(sCmd.equals("updt")){

	    	List<?> ctsnnCdCodeList = null;
	    	List<?> relateCodeList  = null;
	    	ComDefaultCodeVO vo  = new ComDefaultCodeVO();
			vo.setCodeId("COM054");
			ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
			vo.setCodeId("COM073");
			relateCodeList = cmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("ctsnnCodeList",    ctsnnCdCodeList);
			model.addAttribute("relateCodeList",    relateCodeList);

			CtsnnManage ctsnnManageTemp = new CtsnnManage();

			ctsnnManageTemp.setCtsnnId(ctsnnManageVOTemp.getCtsnnId());
			ctsnnManageTemp.setCtsnnNm(ctsnnManageVOTemp.getCtsnnNm());
			ctsnnManageTemp.setRemark(ctsnnManageVOTemp.getRemark());
			ctsnnManageTemp.setUsid(ctsnnManageVOTemp.getUsid());
			ctsnnManageTemp.setCtsnnCd(ctsnnManageVOTemp.getCtsnnCd());
			ctsnnManageTemp.setReqstDe(ctsnnManageVOTemp.getReqstDe());
			ctsnnManageTemp.setInfrmlSanctnId(ctsnnManageVOTemp.getInfrmlSanctnId());
			ctsnnManageTemp.setTrgterNm(ctsnnManageVOTemp.getTrgterNm());
			ctsnnManageTemp.setBrth(ctsnnManageVOTemp.getBrth());
			ctsnnManageTemp.setOccrrDe(ctsnnManageVOTemp.getOccrrDe());
			ctsnnManageTemp.setRelate(ctsnnManageVOTemp.getRelate());
			ctsnnManageTemp.setSanctnerId(ctsnnManageVOTemp.getSanctnerId());

			model.addAttribute("ctsnnManage", ctsnnManageTemp);
			return "egovframework/com/uss/ion/ctn/EgovCtsnnUpdt";
		}else{
			return "egovframework/com/uss/ion/ctn/EgovCtsnnDetail";
		}

	}

	/**
	 * 경조관리 등록 화면으로 이동한다.
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/EgovCtsnnRegist.do")
	 public String insertViewCtsnnManage(@ModelAttribute("ctsnnManage") CtsnnManage ctsnnManage,
                                         @ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                             ModelMap model) throws Exception {

    	List<?> ctsnnCdCodeList = null;
    	List<?> relateCodeList  = null;
    	ComDefaultCodeVO vo  = new ComDefaultCodeVO();
		vo.setCodeId("COM054");
		ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);
		vo.setCodeId("COM073");
		relateCodeList = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("ctsnnCodeList",    ctsnnCdCodeList);
		model.addAttribute("relateCodeList",    relateCodeList);
    	return "egovframework/com/uss/ion/ctn/EgovCtsnnRegist";
	}

	/**
	 * 경조관리정보를 신규로 등록한다.
	 * @param ctsnnManage - 경조관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/insertCtsnnManage.do")
	 public String insertCtsnnManage(   @ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			 							@ModelAttribute("ctsnnManage") CtsnnManage ctsnnManage,
			                            BindingResult bindingResult,
			                            SessionStatus status,
						                ModelMap model) throws Exception {

    	beanValidator.validate(ctsnnManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("ctsnnManageVO", ctsnnManageVO);
			return "egovframework/com/uss/ion/ctn/EgovCtsnnRegist";
		} else {
	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	ctsnnManage.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	egovCtsnnManageService.insertCtsnnManage(ctsnnManage);
	    	status.setComplete();
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	return "forward:/uss/ion/ctn/selectCtsnnManageList.do";
		}
	}

	/**
	 * 기 등록된 경조관리정보를 수정한다.
	 * @param ctsnnManage - 경조관리 model
	 * @return String - 리턴 Url
	 */
	 @RequestMapping(value="/uss/ion/ctn/updtCtsnnManage.do")
	 public String updtCtsnnManage(     @ModelAttribute("ctsnnManage") CtsnnManage ctsnnManage,
			 							@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
							            BindingResult bindingResult,
			                            SessionStatus status,
		                                ModelMap model) throws Exception {

		beanValidator.validate(ctsnnManageVO, bindingResult); //validation 수행

		if (bindingResult.hasErrors()) {
    		model.addAttribute("ctsnnManageVO", ctsnnManageVO);
			return "egovframework/com/uss/ion/ctn/EgovCtsnnUpdt";
		} else {

	    	//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	egovCtsnnManageService.updtCtsnnManage(ctsnnManage);
	    	return "forward:/uss/ion/ctn/selectCtsnnManageList.do";

		}
	}

	/**
	 * 기 등록된 경조관리정보를 삭제한다.
	 * @param ctsnnManage - 경조관리 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/deleteCtsnnManage.do")
	 public String deleteCtsnnManage(@ModelAttribute("ctsnnManage") CtsnnManage ctsnnManage,
			                         SessionStatus status,
			                         ModelMap model) throws Exception {

    	egovCtsnnManageService.deleteCtsnnManage(ctsnnManage);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/uss/ion/ctn/selectCtsnnManageList.do";
	}


    /*** 승인관련 ***/
	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록을 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return String - 리턴 Url
	 */
    @IncludedInfo(name="직원경조사승인관리",order = 891 ,gid = 50)
    @RequestMapping(value="/uss/ion/ctn/EgovCtsnnConfmList.do")
	 public String selectCtsnnManageConfmList(@ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                                  ModelMap model) throws Exception {
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ctsnnManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(ctsnnManageVO.getPageUnit());
		paginationInfo.setPageSize(ctsnnManageVO.getPageSize());

		ctsnnManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ctsnnManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ctsnnManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	ctsnnManageVO.setSanctnerId((user == null || user.getUniqId() == null) ? "" : user.getUniqId()); //사용자가 승인권자인지 조건값 setting   selectCtsnnManageList
    	ctsnnManageVO.setCtsnnManageList(egovCtsnnManageService.selectCtsnnManageConfmList(ctsnnManageVO));
		model.addAttribute("ctsnnManageList", ctsnnManageVO.getCtsnnManageList());

		int totCnt = egovCtsnnManageService.selectCtsnnManageConfmListTotCnt(ctsnnManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

    	List<?> ctsnnCdCodeList = null;
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM054");
		ctsnnCdCodeList = cmmUseService.selectCmmCodeDetail(vo);

        model.addAttribute("ctsnnCodeList"  ,   ctsnnCdCodeList);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/ctn/EgovCtsnnConfmList";
	}

	/**
	 * 경조승인관리 상세정보를 조회한다.
	 * @param ctsnnManageVO - 경조관리 VO
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/ctn/EgovCtsnnConfm.do")
	 public String selectCtsnnConfm( @ModelAttribute("ctsnnManageVO") CtsnnManageVO ctsnnManageVO,
			                         @ModelAttribute("ctsnnManage")   CtsnnManage   ctsnnManage,
							         ModelMap model) throws Exception {
    	ctsnnManageVO.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManageVO.getReqstDe()));

        // 등록 상세정보
    	CtsnnManageVO ctsnnManageVOTemp = egovCtsnnManageService.selectCtsnnManage(ctsnnManageVO);

		CtsnnManage ctsnnManageTemp = new CtsnnManage();
		ctsnnManageTemp.setCtsnnId(ctsnnManageVOTemp.getCtsnnId());
		ctsnnManageTemp.setCtsnnNm(ctsnnManageVOTemp.getCtsnnNm());
		ctsnnManageTemp.setRemark(ctsnnManageVOTemp.getRemark());
		ctsnnManageTemp.setUsid(ctsnnManageVOTemp.getUsid());
		ctsnnManageTemp.setCtsnnCd(ctsnnManageVOTemp.getCtsnnCd());
		ctsnnManageTemp.setReqstDe(ctsnnManageVOTemp.getReqstDe());
		ctsnnManageTemp.setInfrmlSanctnId(ctsnnManageVOTemp.getInfrmlSanctnId());
		ctsnnManageTemp.setTrgterNm(ctsnnManageVOTemp.getTrgterNm());
		ctsnnManageTemp.setBrth(ctsnnManageVOTemp.getBrth());
		ctsnnManageTemp.setOccrrDe(ctsnnManageVOTemp.getOccrrDe());
		ctsnnManageTemp.setRelate(ctsnnManageVOTemp.getRelate());
		ctsnnManageTemp.setSanctnerId(ctsnnManageVOTemp.getSanctnerId());

		model.addAttribute("ctsnnManage",   ctsnnManageTemp);
    	model.addAttribute("ctsnnManageVO", ctsnnManageVOTemp);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/ctn/EgovCtsnnConfm";
    }

	/**
	 * 기 등록된 경조관리정보를 승인 처리한다.
	 * @param ctsnnManage - 경조관리 model
	 * @return String - 리턴 Url
	 */
	 @RequestMapping(value="/uss/ion/ctn/updtCtsnnConfm.do")
	 public String updateCtsnnManageConfm( @ModelAttribute("ctsnnManage")   CtsnnManage   ctsnnManage,
			                               BindingResult bindingResult,
			                               SessionStatus status,
		                                   ModelMap model) throws Exception {


		beanValidator.validate(ctsnnManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("ctsnnManageVO", ctsnnManage);
			return "egovframework/com/uss/ion/ctn/EgovCtsnnConfm";
		} else {

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	ctsnnManage.setSanctnerId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	ctsnnManage.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
	    	ctsnnManage.setReqstDe(EgovStringUtil.removeMinusChar(ctsnnManage.getReqstDe()));

	    	egovCtsnnManageService.updtCtsnnManageConfm(ctsnnManage);
	    	return "forward:/uss/ion/ctn/EgovCtsnnConfmList.do";
		}
	}

	/**
	 * 경조관리정보 반려처리 화면을 호출한다.
	 * @param ctsnnManage
	 * @return  String
	 */
	@RequestMapping("/uss/ion/ctn/EgovCtsnnReturn.do")
	public String selectSanctnerListPopup(@ModelAttribute("ctsnnManage")   CtsnnManage   ctsnnManage,
										  @RequestParam Map<?, ?> commandMap,
                                          ModelMap model) throws Exception{
		return "egovframework/com/uss/ion/ctn/EgovCtsnnReturn";
	}

}
