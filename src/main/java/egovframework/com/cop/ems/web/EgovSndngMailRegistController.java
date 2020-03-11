package egovframework.com.cop.ems.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.EgovSndngMailRegistService;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * 발송메일등록, 발송요청XML파일 생성하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      	수정자          수정내용
 *  ----------     --------    ---------------------------
 *  2009.03.12  	박지욱          최초 생성
 *  2011.12.06  	이기하          메일 첨부파일이 기능 추가
 *  2015.05.08  	조정국          오류페이지 표시 경로 수정 - insertSndngMail()
 *
 *  </pre>
 */
@Controller
public class EgovSndngMailRegistController {

	/** EgovSndngMailRegistService */
	@Resource(name = "sndngMailRegistService")
	private EgovSndngMailRegistService sndngMailRegistService;

	/** EgovFileMngService */
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	/** EgovFileMngUtil */
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/** 파일구분자 */
	static final char FILE_SEPARATOR = File.separatorChar;

	/**
	 * 발송메일 등록화면으로 들어간다
	 * @param sndngMailVO SndngMailVO
	 * @return String
	 * @exception Exception
	 */
	@IncludedInfo(name = "메일발송", order = 360, gid = 40)
	@RequestMapping(value = "/cop/ems/insertSndngMailView.do")
	public String insertSndngMailView(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {

		model.addAttribute("resultInfo", sndngMailVO);
		return "egovframework/com/cop/ems/EgovMailRegist";
	}

	/**
	 * 발송메일을 등록한다
	 * @param multiRequest MultipartHttpServletRequest
	 * @param sndngMailVO SndngMailVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/cop/ems/insertSndngMail.do")
	public String insertSndngMail(final MultipartHttpServletRequest multiRequest, @ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model, HttpServletRequest request)
			throws Exception {

		String link = "N";
		if (sndngMailVO != null && sndngMailVO.getLink() != null && !sndngMailVO.getLink().equals("")) {
			link = sndngMailVO.getLink();
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

		List<FileVO> _result = new ArrayList<FileVO>();
		String _atchFileId = "";
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			_result = fileUtil.parseFileInf(files, "MSG_", 0, "", "");
			_atchFileId = fileMngService.insertFileInfs(_result); //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.

		}

		String orignlFileList = "";

		for (int i = 0; i < _result.size(); i++) {
			FileVO fileVO = _result.get(i);
			orignlFileList = fileVO.getOrignlFileNm();
		}

		if (sndngMailVO != null) {
			sndngMailVO.setAtchFileId(_atchFileId);
			sndngMailVO.setDsptchPerson(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
			sndngMailVO.setOrignlFileNm(orignlFileList);
		}

		// 발송메일을 등록한다.
		boolean result = sndngMailRegistService.insertSndngMail(sndngMailVO);
		if (result) {
			if (link.equals("N")) {
				return "redirect:/cop/ems/selectSndngMailList.do";
			} else {
				model.addAttribute("closeYn", "Y");
				return "egovframework/com/cop/ems/EgovMailRegist";
			}
		} else {
			return "egovframework/com/cmm/error/egovError";
		}
	}

	/**
	 * 발송메일 내용조회로 돌아간다.
	 * @param sndngMailVO SndngMailVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/cop/ems/backSndngMailRegist.do")
	public String backSndngMailRegist(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {

		return "redirect:/cop/ems/selectSndngMailList.do";
	}
}