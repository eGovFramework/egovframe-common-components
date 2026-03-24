/**
 * 개요
 * - 배너에 대한 controller 클래스를 정의한다.
 *
 * 상세내용
 * - 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:07:11
 *  * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.8.3	lee.m.j          최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */

package egovframework.com.uss.ion.bnr.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.bnr.service.BannerVO;
import egovframework.com.uss.ion.bnr.service.EgovBannerService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Controller
public class EgovBannerController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "egovBannerService")
    private EgovBannerService egovBannerService;

    /** Message ID Generation */
    @Resource(name="egovBannerIdGnrService")
    private EgovIdGnrService egovBannerIdGnrService;

    /**
	 * 배너 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/uss/ion/bnr/selectBannerListView.do")
    public String selectBannerListView() throws Exception {

        return "egovframework/com/uss/ion/bnr/EgovBannerList";
    }

	/**
	 * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return String - 리턴 URL
	 * @throws Exception
	 */
    @IncludedInfo(name="배너관리", order = 740 ,gid = 50)
    @RequestMapping(value="/uss/ion/bnr/selectBannerList.do")
	public String selectBannerList(@ModelAttribute("bannerVO") BannerVO bannerVO,
                             		ModelMap model) throws Exception{

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bannerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(bannerVO.getPageUnit());
		paginationInfo.setPageSize(bannerVO.getPageSize());

		bannerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bannerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bannerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		bannerVO.setBannerList(egovBannerService.selectBannerList(bannerVO));

		model.addAttribute("bannerList", bannerVO.getBannerList());

        int totCnt = egovBannerService.selectBannerListTotCnt(bannerVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/bnr/EgovBannerList";
	}

	/**
	 * 등록된 배너의 상세정보를 조회한다.
	 * @param bannerVO - 배너 Vo
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/bnr/getBanner.do")
	public String selectBanner(@RequestParam("bannerId") String bannerId,
			                   @ModelAttribute("bannerVO") BannerVO bannerVO,
			                   ModelMap model) throws Exception {

    	bannerVO.setBannerId(bannerId);

    	model.addAttribute("bannerVO", egovBannerService.selectBanner(bannerVO));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	return "egovframework/com/uss/ion/bnr/EgovBannerUpdt";
	}

	/**
	 * 배너등록 화면으로 이동한다.
	 * @param banner - 배너 model
	 * @return String - 리턴 Url
	 */
    @RequestMapping(value="/uss/ion/bnr/addViewBanner.do")
	public String insertViewBanner(@ModelAttribute("bannerVO") BannerVO bannerVO,
			                        ModelMap model) throws Exception {

    	model.addAttribute("bannerVO", bannerVO);
    	return "egovframework/com/uss/ion/bnr/EgovBannerRegist";
	}

	/**
	 * 배너정보를 신규로 등록한다.
	 * @param banner - 배너 model
	 * @return String - 리턴 Url
	 */
    @SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/bnr/addBanner.do")
	public String insertBanner(final MultipartHttpServletRequest multiRequest,
			                   @Valid @ModelAttribute("bannerVO") BannerVO bannerVO,
			                    BindingResult bindingResult,
			                    SessionStatus status,
			                    ModelMap model) throws Exception {

    	if (bindingResult.hasErrors()) {
    		model.addAttribute("bannerVO", bannerVO);
			return "egovframework/com/uss/ion/bnr/EgovBannerRegist";
		} else {
	    	List<FileVO> result = null;

	    	String uploadFolder = "";
	    	String bannerImage = "";
	    	String bannerImageFile = "";
	    	String atchFileId = "";

	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    	if(!files.isEmpty()){
	    	    result = fileUtil.parseFileInf(files, "BNR_", 0, "", uploadFolder);
	    	    atchFileId = fileMngService.insertFileInfs(result);

	        	FileVO vo = result.get(0);
	        	Iterator<FileVO> iter = result.iterator();

	        	while (iter.hasNext()) {
	        	    vo = iter.next();
	        	    bannerImage = vo.getOrignlFileNm();
	        	    bannerImageFile = vo.getStreFileNm();
	        	}
	    	}

	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	bannerVO.setBannerId(egovBannerIdGnrService.getNextStringId());
	    	bannerVO.setBannerImage(bannerImage);
	    	bannerVO.setBannerImageFile(atchFileId);
	    	bannerVO.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
	    	status.setComplete();
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    	model.addAttribute("bannerVO", egovBannerService.insertBanner(bannerVO));

			return "forward:/uss/ion/bnr/selectBannerList.do";

		}
	}

	/**
	 * 기 등록된 배너정보를 수정한다.
	 * @param banner - 배너 model
	 * @return String - 리턴 Url
	 */
    @SuppressWarnings("unused")
	@RequestMapping(value="/uss/ion/bnr/updtBanner.do")
	public String updateBanner(final MultipartHttpServletRequest multiRequest,
			                   @Valid @ModelAttribute("bannerVO") BannerVO bannerVO,
			                    BindingResult bindingResult,
                                SessionStatus status,
                                ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("bannerVO", bannerVO);
			return "egovframework/com/uss/ion/bnr/EgovBannerUpdt";
		} else {

			List<FileVO> result = null;

			String uploadFolder = "";
			String bannerImage = "";
			String bannerImageFile = "";
			String atchFileId = "";

			final Map<String, MultipartFile> files = multiRequest.getFileMap();

			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "BNR_", 0, "", uploadFolder);
				atchFileId = fileMngService.insertFileInfs(result);

				FileVO vo = null;
				Iterator<FileVO> iter = result.iterator();

				while (iter.hasNext()) {
					vo = iter.next();
					bannerImage = vo.getOrignlFileNm();
					bannerImageFile = vo.getStreFileNm();
				}

				if (vo == null) {
					bannerVO.setAtchFile(false);
				} else {
					bannerVO.setBannerImage(bannerImage);
					bannerVO.setBannerImageFile(atchFileId);
					bannerVO.setAtchFile(true);

				}
			} else {
				bannerVO.setAtchFile(false);
			}

			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			bannerVO.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

			egovBannerService.updateBanner(bannerVO);
			return "forward:/uss/ion/bnr/selectBannerList.do";

		}
	}

	/**
	 * 기 등록된 배너정보를 삭제한다.
	 * @param banner Banner
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/uss/ion/bnr/removeBanner.do")
	public String deleteBanner(@RequestParam("bannerId") String bannerId,
			                   @ModelAttribute("bannerVO") BannerVO bannerVO,
			                    SessionStatus status,
			                    ModelMap model) throws Exception {

    	bannerVO.setBannerId(bannerId);
    	egovBannerService.deleteBanner(bannerVO);
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/bnr/selectBannerList.do";
	}

	/**
	 * 기 등록된 배너정보목록을 일괄 삭제한다.
	 * @param banners String
	 * @param banner Banner
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/uss/ion/bnr/removeBannerList.do")
	public String deleteBannerList(@RequestParam("bannerIds") String bannerIds,
			                       @ModelAttribute("bannerVO") BannerVO bannerVO,
			                        SessionStatus status,
			                        ModelMap model) throws Exception {
    	// 2026.03.23 kisa 보안점검 대응 조치
    	  if (bannerIds != null) {
	    	String [] strBannerIds = bannerIds.split(";");
	
	    	for (String strBannerId : strBannerIds) {
	    		bannerVO.setBannerId(strBannerId);
	    		egovBannerService.deleteBanner(bannerVO);
	    	}
    	  }
    	status.setComplete();
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/bnr/selectBannerList.do";
	}

	/**
	 * 배너가 특정화면에 반영된 결과를 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/uss/ion/bnr/getBannerImage.do")
	public String selectBannerResult(@ModelAttribute("bannerVO") BannerVO bannerVO,
                                      ModelMap model) throws Exception {

		List<BannerVO> fileList = egovBannerService.selectBannerResult(bannerVO);
		model.addAttribute("fileList", fileList);
		model.addAttribute("resultType", bannerVO.getResultType());

		return "egovframework/com/uss/ion/bnr/EgovBannerView";
	}

	/**
	 * MyPage에 배너정보를 제공하기 위해 목록을 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return String - 리턴 URL
	 * @throws Exception
	 */
	@IncludedInfo(name="MYPAGE배너관리", order = 741 ,gid = 50)
    @RequestMapping(value="/uss/ion/bnr/selectBannerMainList.do")
	public String selectBannerMainList(@ModelAttribute("bannerVO") BannerVO bannerVO,
                             		ModelMap model) throws Exception{

    	/** 로그인 사용자와 동일한 ID로 등록된 배너만 조회 */
    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	if (loginVO != null) {
    		bannerVO.setUserId(EgovStringUtil.isNullToString(loginVO.getId()));
    	}

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bannerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(5);
		paginationInfo.setPageSize(bannerVO.getPageSize());

		bannerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bannerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bannerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		bannerVO.setBannerList(egovBannerService.selectBannerList(bannerVO));

		model.addAttribute("bannerList", bannerVO.getBannerList());

		return "egovframework/com/uss/ion/bnr/EgovBannerMainList";
	}
}
