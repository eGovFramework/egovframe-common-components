package egovframework.com.uss.ion.msi.web;

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
import egovframework.com.uss.ion.msi.service.EgovMainImageService;
import egovframework.com.uss.ion.msi.service.MainImageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Controller
public class EgovMainImageController {

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name = "egovMainImageIdGnrService")
	private EgovIdGnrService egovMainImageIdGnrService;

	@Resource(name = "egovMainImageService")
	private EgovMainImageService egovMainImageService;

	@RequestMapping("/uss/ion/msi/selectMainImageListView.do")
	public String selectMainImageListView() throws Exception {
		return "egovframework/com/uss/ion/msi/EgovMainImageList";
	}

	@IncludedInfo(name = "메인이미지관리", order = 770, gid = 50)
	@RequestMapping("/uss/ion/msi/selectMainImageList.do")
	public String selectMainImageList(@ModelAttribute("mainImageVO") MainImageVO mainImageVO, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mainImageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(mainImageVO.getPageUnit());
		paginationInfo.setPageSize(mainImageVO.getPageSize());

		mainImageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mainImageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		mainImageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		mainImageVO.setMainImageList(egovMainImageService.selectMainImageList(mainImageVO));

		model.addAttribute("mainImageList", mainImageVO.getMainImageList());

		int totCnt = egovMainImageService.selectMainImageListTotCnt(mainImageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return "egovframework/com/uss/ion/msi/EgovMainImageList";
	}

	/**
	 * 등록된 메인이미지의 상세정보를 조회한다.
	 */
	@RequestMapping(value = "/uss/ion/msi/getMainImage.do")
	public String selectMainImage(@RequestParam("imageId") String imageId,
			@ModelAttribute("mainImageVO") MainImageVO mainImageVO, ModelMap model) throws Exception {
		mainImageVO.setImageId(imageId);
		model.addAttribute("mainImageVO", egovMainImageService.selectMainImage(mainImageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/com/uss/ion/msi/EgovMainImageUpdt";
	}

	/**
	 * 메인이미지 등록 화면으로 이동한다.
	 */
	@RequestMapping(value = "/uss/ion/msi/addViewMainImage.do")
	public String insertViewMainImage(@ModelAttribute("mainImageVO") MainImageVO mainImageVO, ModelMap model) throws Exception {
		model.addAttribute("mainImageVO", mainImageVO);
		return "egovframework/com/uss/ion/msi/EgovMainImageRegist";
	}

	/**
	 * 메인이미지정보를 신규로 등록한다.
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uss/ion/msi/addMainImage.do")
	public String insertMainImage(final MultipartHttpServletRequest multiRequest,
			@Valid @ModelAttribute("mainImageVO") MainImageVO mainImageVO, BindingResult bindingResult,
			SessionStatus status, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("mainImageVO", mainImageVO);
			return "egovframework/com/uss/ion/msi/EgovMainImageRegist";
		}

		List<FileVO> result = null;
		String uploadFolder = "";
		String image = "";
		String atchFileId = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "MSI_", 0, "", uploadFolder);
			atchFileId = fileMngService.insertFileInfs(result);

			Iterator<FileVO> iter = result.iterator();
			while (iter.hasNext()) {
				FileVO vo = iter.next();
				image = vo.getOrignlFileNm();
			}
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		mainImageVO.setImageId(egovMainImageIdGnrService.getNextStringId());
		mainImageVO.setImage(image);
		mainImageVO.setImageFile(atchFileId);
		mainImageVO.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

		status.setComplete();
		model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
		model.addAttribute("mainImageVO", egovMainImageService.insertMainImage(mainImageVO));
		return "forward:/uss/ion/msi/selectMainImageList.do";
	}

	/**
	 * 기 등록된 메인이미지정보를 수정한다.
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/uss/ion/msi/updtMainImage.do")
	public String updateMainImage(final MultipartHttpServletRequest multiRequest,
			@Valid @ModelAttribute("mainImageVO") MainImageVO mainImageVO, BindingResult bindingResult,
			SessionStatus status, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("mainImageVO", mainImageVO);
			return "egovframework/com/uss/ion/msi/EgovMainImageUpdt";
		}

		List<FileVO> result = null;
		String uploadFolder = "";
		String image = "";
		String atchFileId = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "MSI_", 0, "", uploadFolder);
			atchFileId = fileMngService.insertFileInfs(result);

			FileVO vo = null;
			Iterator<FileVO> iter = result.iterator();
			while (iter.hasNext()) {
				vo = iter.next();
				image = vo.getOrignlFileNm();
			}
			if (vo == null) {
				mainImageVO.setAtchFile(false);
			} else {
				mainImageVO.setImage(image);
				mainImageVO.setImageFile(atchFileId);
				mainImageVO.setAtchFile(true);
			}
		} else {
			mainImageVO.setAtchFile(false);
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		mainImageVO.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

		egovMainImageService.updateMainImage(mainImageVO);
		return "forward:/uss/ion/msi/selectMainImageList.do";
	}

	/**
	 * 기 등록된 메인이미지정보를 삭제한다.
	 */
	@RequestMapping(value = "/uss/ion/msi/removeMainImage.do")
	public String deleteMainImage(@RequestParam("imageId") String imageId,
			@ModelAttribute("mainImageVO") MainImageVO mainImageVO, SessionStatus status, ModelMap model) throws Exception {
		mainImageVO.setImageId(imageId);
		egovMainImageService.deleteMainImage(mainImageVO);
		status.setComplete();
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/msi/selectMainImageList.do";
	}

	/**
	 * 기 등록된 메인이미지정보 목록을 일괄 삭제한다.
	 */
	@RequestMapping(value = "/uss/ion/msi/removeMainImageList.do")
	public String deleteMainImageList(@RequestParam("imageIds") String imageIds,
			@ModelAttribute("mainImageVO") MainImageVO mainImageVO, SessionStatus status, ModelMap model) throws Exception {
		String[] strImageIds = imageIds.split(";");
		for (String strImageId : strImageIds) {
			mainImageVO.setImageId(strImageId);
			egovMainImageService.deleteMainImage(mainImageVO);
		}
		status.setComplete();
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/uss/ion/msi/selectMainImageList.do";
	}

	@IncludedInfo(name = "메인이미지 반영결과보기", order = 771, gid = 50)
	@RequestMapping(value = "/uss/ion/msi/getMainImageResult.do")
	public String selectMainImageResult(@ModelAttribute("mainImageVO") MainImageVO mainImageVO, ModelMap model) throws Exception {
		List<MainImageVO> fileList = egovMainImageService.selectMainImageResult(mainImageVO);
		model.addAttribute("fileList", fileList);
		return "egovframework/com/uss/ion/msi/EgovMainImageView";
	}
}
