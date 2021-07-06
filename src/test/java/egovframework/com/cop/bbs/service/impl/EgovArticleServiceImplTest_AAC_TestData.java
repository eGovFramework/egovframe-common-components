package egovframework.com.cop.bbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.javaparser.utils.Log;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EgovArticleServiceImplTest_AAC_TestData {

	private final EgovBBSMasterDAO egovBBSMasterDAO;
//	private final EgovArticleDAO egovArticleDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService egovNttIdGnrService;

	@Resource(name = "egovBlogIdGnrService")
	private EgovIdGnrService egovBlogIdGnrService;

	public BoardMaster insertBBSMasterInf()  {
		BoardMaster boardMaster = new BoardMaster();
		try {
			boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			Log.error(e.getMessage());
		}

		egovBBSMasterDAO.insertBBSMasterInf(boardMaster);

		return boardMaster;
	}

}