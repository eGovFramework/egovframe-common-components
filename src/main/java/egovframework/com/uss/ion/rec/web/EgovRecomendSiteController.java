package egovframework.com.uss.ion.rec.web;

import java.util.List;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rec.service.EgovRecomendSiteService;
import egovframework.com.uss.ion.rec.service.RecomendSiteVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 추천사이트처리를 하는 Controller 클래스
 * 
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *   2011.08.26  정진오          IncludedInfo annotation 추가
 *   2016.08.22  김연호          표준프레임워크 3.6 개선
 *   2025.08.12  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(final이 아닌 변수는 밑줄을 포함할 수 없음)
 *
 *      </pre>
 */
@Controller
public class EgovRecomendSiteController {

	@Resource(name = "EgovRecomendSiteService")
	private EgovRecomendSiteService egovRecomendSiteService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 추천사이트정보 목록을 조회한다.
	 * 
	 * @param recomendSiteVO
	 * @param model
	 * @return "/uss/ion/rec/EgovRecomendSiteList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "추천사이트관리", order = 700, gid = 50)
	@RequestMapping(value = "/uss/ion/rec/selectRecomendSiteList.do")
	public String selectRecomendSiteList(@ModelAttribute("recomendSiteVO") RecomendSiteVO recomendSiteVO, ModelMap model) throws Exception {

		/** EgovPropertyService.SiteList */
		recomendSiteVO.setPageUnit(propertiesService.getInt("pageUnit"));
		recomendSiteVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(recomendSiteVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(recomendSiteVO.getPageUnit());
		paginationInfo.setPageSize(recomendSiteVO.getPageSize());

		recomendSiteVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		recomendSiteVO.setLastIndex(paginationInfo.getLastRecordIndex());
		recomendSiteVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<RecomendSiteVO> resultList = egovRecomendSiteService.selectRecomendSiteList(recomendSiteVO);
		model.addAttribute("resultList", resultList);

		int totCnt = egovRecomendSiteService.selectRecomendSiteListCnt(recomendSiteVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/ion/rec/EgovRecomendSiteList";
	}

	/**
	 * 추천사이트정보 목록에 대한 상세정보를 조회한다.
	 * 
	 * @param recomendSiteVO
	 * @param model
	 * @return "/uss/ion/rec/EgovRecomendSiteDetail"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/rec/selectRecomendSiteDetail.do")
	public String selectRecomendSiteDetail(RecomendSiteVO recomendSiteVO, ModelMap model) throws Exception {
		RecomendSiteVO vo = egovRecomendSiteService.selectRecomendSiteDetail(recomendSiteVO);
		model.addAttribute("recomendSiteVO", vo);

		return "egovframework/com/uss/ion/rec/EgovRecomendSiteDetail";
	}

	/**
	 * 추천사이트정보를 등록하기 전 처리
	 * 
	 * @param recomendSiteVO
	 * @param model
	 * @return "/uss/ion/rec/EgovRecomendSiteRegist"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/rec/insertRecomendSiteView.do")
	public String insertRecomendSiteView(@ModelAttribute("recomendSiteVO") RecomendSiteVO recomendSiteVO, ModelMap model) throws Exception {
		model.addAttribute("recomendSiteVO", recomendSiteVO);

		return "egovframework/com/uss/ion/rec/EgovRecomendSiteRegist";

	}

	/**
	 * 추천사이트정보를 등록한다.
	 * 
	 * @param recomendSiteVO
	 * @param bindingResult
	 * @return "forward:/uss/ion/rec/selectRecomendSiteList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/rec/insertRecomendSite.do")
	public String insertRecomendSite(@Valid @ModelAttribute("recomendSiteVO") RecomendSiteVO recomendSiteVO, BindingResult bindingResult, ModelMap model)throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("recomendSiteVO", recomendSiteVO);
			return "egovframework/com/uss/ion/rec/EgovRecomendSiteRegist";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String frstRegisterId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		recomendSiteVO.setFrstRegisterId(frstRegisterId); // 최초등록자ID
		recomendSiteVO.setLastUpdusrId(frstRegisterId); // 최종수정자ID

		egovRecomendSiteService.insertRecomendSite(recomendSiteVO);

		return "forward:/uss/ion/rec/selectRecomendSiteList.do";
	}

	/**
	 * 추천사이트정보를 수정하기 전 처리
	 * 
	 * @param recomendSiteVO
	 * @param model
	 * @return "/uss/ion/rec/EgovRecomendSiteUpdt"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/rec/updateRecomendSiteView.do")
	public String updateRecomendSiteView(@ModelAttribute("recomendSiteVO") RecomendSiteVO recomendSiteVO, ModelMap model) throws Exception {
		model.addAttribute("recomendSiteVO", egovRecomendSiteService.selectRecomendSiteDetail(recomendSiteVO));

		return "egovframework/com/uss/ion/rec/EgovRecomendSiteUpdt";
	}

	/**
	 * 추천사이트정보를 수정처리한다.
	 * 
	 * @param recomendSiteVO
	 * @param bindingResult
	 * @return "forward:/uss/ion/rec/selectRecomendSiteList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/rec/updateRecomendSite.do")
	public String updateRecomendSite(@Valid @ModelAttribute("recomendSiteVO") RecomendSiteVO recomendSiteVO, BindingResult bindingResult, ModelMap model)throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("recomendSiteVO", recomendSiteVO);
			return "egovframework/com/uss/ion/rec/EgovRecomendSiteUpdt";
		}

		// 로그인VO에서 사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String lastUpdusrId = loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId());
		recomendSiteVO.setLastUpdusrId(lastUpdusrId); // 최종수정자ID

		egovRecomendSiteService.updateRecomendSite(recomendSiteVO);

		return "forward:/uss/ion/rec/selectRecomendSiteList.do";

	}

	/**
	 * 추천사이트정보를 삭제처리한다.
	 * 
	 * @param recomendSiteVO
	 * @return "forward:/uss/ion/rec/selectRecomendSiteList.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/ion/rec/deleteRecomendSite.do")
	public String deleteRecomendSite(@ModelAttribute("recomendSiteVO") RecomendSiteVO recomendSiteVO) throws Exception {
		egovRecomendSiteService.deleteRecomendSite(recomendSiteVO);

		return "forward:/uss/ion/rec/selectRecomendSiteList.do";
	}

}
