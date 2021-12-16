package egovframework.com.cop.ems.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.impl.FileManageDAO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.AtchmnFileVO;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { SndngMailRegistDAOTest_Configuration.class })
public class SndngMailRegistDAOTest_selectAtchmnFileList extends EgovTestV1 {

	@Resource(name = "sndngMailRegistDAO")
	private SndngMailRegistDAO sndngMailRegistDAO;

	@Resource(name = "egovMailMsgIdGnrService")
	private EgovIdGnrService egovMailMsgIdGnrService;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;

	@Resource
	private FileManageDAO fileManageDAO;

	// testData
	String today;
	LoginVO authenticatedUser;

	FileVO fileVO;
	SndngMailVO sndngMailVO;

	// given
	SndngMailVO vo;

	// when
	List<AtchmnFileVO> atchmnFiles;

	@Test
//	@Commit
	public void test() {
		log.debug("test");
		testData();
		testData_insertFileInf();
		testData_insertSndngMail();
		given();
		when();
		then();
	}

	void testData() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		int pageUnit = propertiesService.getInt("pageUnit");
		int pageSize = propertiesService.getInt("pageSize");
		log.debug("pageUnit={}", pageUnit);
		log.debug("pageSize={}", pageSize);
	}

	void testData_insertFileInf() {
		fileVO = new FileVO();
		try {
			fileVO.setAtchFileId(egovFileIdGnrService.getNextStringId()); // 첨부파일ID
		} catch (FdlException e) {
			log.error("egovFileIdGnrService FdlException");
		}

		fileVO.setFileSn("0");
		fileVO.setFileMg("0");

		fileVO.setFileStreCours("test 파일저장경로" + today);
		fileVO.setStreFileNm("test 저장파일명" + today);
		fileVO.setOrignlFileNm("test 원파일명" + today);
		fileVO.setFileExtsn("txt");

		try {
			fileManageDAO.insertFileInf(fileVO);
		} catch (Exception e) {
			log.error("insertFileInf Exception");
		}
	}

	void testData_insertSndngMail() {
		sndngMailVO = new SndngMailVO();
		try {
			sndngMailVO.setMssageId(egovMailMsgIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("egovMailMsgIdGnrService FdlException");
		}
		sndngMailVO.setDsptchPerson("test 발신자" + today);
		sndngMailVO.setRecptnPerson("test 수신자" + today);
		sndngMailVO.setSj("test 제목" + today);
		sndngMailVO.setSndngResultCode("R"); // 발송결과코드(COM024): C(완료), F(실패), R(요청)
		sndngMailVO.setEmailCn("test 이메일내용" + today);
		sndngMailVO.setAtchFileId(fileVO.getAtchFileId()); // 첨부파일ID

		try {
			sndngMailRegistDAO.insertSndngMail(sndngMailVO);
		} catch (Exception e) {
			log.error("insertSndngMail Exception");
		}
	}

	void given() {
		vo = new SndngMailVO();
		vo.setAtchFileId(fileVO.getAtchFileId());
	}

	void when() {
		try {
			atchmnFiles = sndngMailRegistDAO.selectAtchmnFileList(vo);
		} catch (Exception e) {
			log.error("selectAtchmnFileList Exception");
		}
	}

	void then() {
		log.debug("atchFileId={}, {}", atchmnFiles.get(0).getAtchFileId(), vo.getAtchFileId());
		log.debug("fileSn={}, {}", atchmnFiles.get(0).getFileSn(), fileVO.getFileSn());
		log.debug("fileStreCours={}, {}", atchmnFiles.get(0).getFileStreCours(), fileVO.getFileStreCours());
		log.debug("streFileNm={}, {}", atchmnFiles.get(0).getStreFileNm(), fileVO.getStreFileNm());
		log.debug("orignlFileNm={}, {}", atchmnFiles.get(0).getOrignlFileNm(), fileVO.getOrignlFileNm());
		log.debug("fileExtsn={}, {}", atchmnFiles.get(0).getFileExtsn(), fileVO.getFileExtsn());
		log.debug("fileMg={}, {}", atchmnFiles.get(0).getFileMg(), fileVO.getFileMg());

		assertEquals(atchmnFiles.get(0).getAtchFileId(), vo.getAtchFileId());
		assertEquals(atchmnFiles.get(0).getFileSn(), fileVO.getFileSn());
		assertEquals(atchmnFiles.get(0).getFileStreCours(), fileVO.getFileStreCours());
		assertEquals(atchmnFiles.get(0).getStreFileNm(), fileVO.getStreFileNm());
		assertEquals(atchmnFiles.get(0).getOrignlFileNm(), fileVO.getOrignlFileNm());
		assertEquals(atchmnFiles.get(0).getFileExtsn(), fileVO.getFileExtsn());
		assertEquals(String.valueOf(atchmnFiles.get(0).getFileMg()), fileVO.getFileMg());
	}

}