package egovframework.com.cop.ems.service.impl;

import java.io.File;
import java.util.List;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cop.ems.service.AtchmnFileVO;
import egovframework.com.cop.ems.service.EgovSndngMailRegistService;
import egovframework.com.cop.ems.service.EgovSndngMailService;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovXMLDoc;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import noNamespace.SndngMailDocument;

import org.springframework.stereotype.Service;

/**
 * 발송메일등록, 발송요청XML파일 생성하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  박지욱          최초 생성
 *  2011.07.27  서준식          메일 발송내역 DB 저장시 첨부파일이 없으면 NULL로 변경
 *  2011.12.06  이기하          메일 첨부파일이 기능 추가
 *  2015.02.02  표준프레임워크  메일 첨부파일 오류 수정
 *  </pre>
 */
@Service("sndngMailRegistService")
public class EgovSndngMailRegistServiceImpl extends EgovAbstractServiceImpl implements EgovSndngMailRegistService {

	/** SndngMailRegistDAO */
	@Resource(name = "sndngMailRegistDAO")
	private SndngMailRegistDAO sndngMailRegistDAO;

	/** Message ID Generation */
	@Resource(name = "egovMailMsgIdGnrService")
	private EgovIdGnrService egovMailMsgIdGnrService;

	@Resource(name = "egovSndngMailService")
	private EgovSndngMailService egovSndngMailService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	/**
	 * 발송할 메일을 등록한다
	 * @param vo SndngMailVO
	 * @return boolean
	 * @exception Exception
	 */
	@Override
	public boolean insertSndngMail(SndngMailVO vo) throws Exception {
		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		String recptnPersons = EgovStringUtil.isNullToString(vo.getRecptnPerson()).replaceAll(" ", "");
		String[] recptnPersonList = recptnPersons.split(";");

		for (int j = 0; j < recptnPersonList.length; j++) {

			// 1-0.메세지ID를 생성한다.
			String mssageId = egovMailMsgIdGnrService.getNextStringId();

			// 1-1.발송메일  데이터를 만든다.
			SndngMailVO mailVO = new SndngMailVO();
			mailVO.setMssageId(mssageId);
			mailVO.setDsptchPerson(vo.getDsptchPerson());
			mailVO.setRecptnPerson(recptnPersonList[j]);
			mailVO.setSj(vo.getSj());
			//mailVO.setEmailCn(EgovStringUtil.checkHtmlView(vo.getEmailCn()));
			mailVO.setEmailCn(vo.getEmailCn());
			mailVO.setSndngResultCode("R"); // 발송결과 요청

			if (vo.getAtchFileId() == null || vo.getAtchFileId().equals("")) {
				mailVO.setAtchFileId(null);
				mailVO.setFileStreCours(null);
				mailVO.setOrignlFileNm(null);
			} else {
				FileVO fileVO = new FileVO();
				fileVO.setAtchFileId(vo.getAtchFileId());
				fileVO.setFileSn("0");
				FileVO fvo = fileService.selectFileInf(fileVO);

				File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());

				mailVO.setAtchFileId(vo.getAtchFileId());
				mailVO.setFileStreCours(uFile.getPath());
				mailVO.setOrignlFileNm(vo.getOrignlFileNm());
			}

			// 1-3.발송메일을 등록한다.
			sndngMailRegistDAO.insertSndngMail(mailVO);

			// 1-4.메일을 발송한다.
			boolean sendingMailResult = egovSndngMailService.sndngMail(mailVO);

			if (!sendingMailResult) {
				mailVO.setSndngResultCode("F"); // 발송결과 실패
				sndngMailRegistDAO.updateSndngMail(mailVO); // 발송상태를 DB에 업데이트 한다.
				return false;
			}

			// 1-5.발송메일 요청XML 파일을 생성한다.
			trnsmitXmlData(mailVO);
		}
		return true;
	}

	/**
	 * 발송할 메일을 XML파일로 만들어 저장한다.
	 * @param vo SndngMailVO
	 * @return boolean
	 * @exception Exception
	 */
	@Override
	public boolean trnsmitXmlData(SndngMailVO vo) throws Exception {

		// 1. 첨부파일 목록 (원파일명, 저장파일명)
		String orignlFileList = "";
		String streFileList = "";
		List<?> atchmnFileList = sndngMailRegistDAO.selectAtchmnFileList(vo);
		for (int i = 0; i < atchmnFileList.size(); i++) {
			AtchmnFileVO fileVO = (AtchmnFileVO) atchmnFileList.get(i);
			String orignlFile = fileVO.getOrignlFileNm();
			String streFile = fileVO.getFileStreCours() + fileVO.getStreFileNm();
			orignlFileList += orignlFile + ";";
			streFileList += streFile + ";";
		}

		// 2. XML데이터를 만든다.
		SndngMailDocument mailDoc;
		SndngMailDocument.SndngMail mailElement;
		mailDoc = SndngMailDocument.Factory.newInstance();
		mailElement = mailDoc.addNewSndngMail();
		mailElement.setMssageId(vo.getMssageId());
		mailElement.setDsptchPerson(vo.getDsptchPerson());
		mailElement.setRecptnPerson(vo.getRecptnPerson());
		mailElement.setSj(vo.getSj());
		mailElement.setEmailCn(vo.getEmailCn());
		mailElement.setSndngResultCode(vo.getSndngResultCode());
		mailElement.setOrignlFileList(orignlFileList);
		mailElement.setStreFileList(streFileList);

		// 2. XML파일로 저장한다.
		String xmlFile = Globals.MAIL_REQUEST_PATH + vo.getMssageId() + ".xml";
		boolean result = EgovXMLDoc.getClassToXML(mailDoc, xmlFile);
		if (result == true) {
			recptnXmlData(xmlFile);
		}
		return result;
	}

	/**
	 * 발송메일 발송결과 XML파일을 읽어 발송결과코드에 수정한다.
	 * @param xml String
	 * @return boolean
	 * @exception Exception
	 */
	@Override
	public boolean recptnXmlData(String xmlFile) throws Exception {

		// 1. XML파일에서 발송결과코드를 가져온다.
		SndngMailDocument mailDoc = EgovXMLDoc.getXMLToClass(xmlFile);
		SndngMailDocument.SndngMail mailElement = mailDoc.getSndngMail();
		SndngMailVO sndngMailVO = new SndngMailVO();
		sndngMailVO.setMssageId(mailElement.getMssageId());
		sndngMailVO.setSndngResultCode("C"); // 발송결과 완료

		// 2. DB에 업데이트 한다.
		sndngMailRegistDAO.updateSndngMail(sndngMailVO);

		return true;
	}
}
