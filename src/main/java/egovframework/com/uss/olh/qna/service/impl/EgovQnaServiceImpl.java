package egovframework.com.uss.olh.qna.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.olh.qna.service.EgovQnaService;
import egovframework.com.uss.olh.qna.service.QnaVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovQnaService")
public class EgovQnaServiceImpl extends EgovAbstractServiceImpl implements EgovQnaService {

	@Resource(name = "EgovQnaDAO")
	private EgovQnaDAO egovQnaDao;

	/** ID Generation */
	@Resource(name = "egovQnaManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectQnaList(QnaVO searchVO) {
		return egovQnaDao.selectQnaList(searchVO);
	}

	@Override
	public int selectQnaListCnt(QnaVO searchVO) {
		return egovQnaDao.selectQnaListCnt(searchVO);
	}

	@Override
	public QnaVO selectQnaDetail(QnaVO qnaVO) throws Exception {
		QnaVO resultVO = egovQnaDao.selectQnaDetail(qnaVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	@Override
	public void updateQnaInqireCo(QnaVO qnaVO) {
		egovQnaDao.updateQnaInqireCo(qnaVO);
	}

	@Override
	public void insertQna(QnaVO qnaVO) throws FdlException {
		String qaId = idgenService.getNextStringId();
		qnaVO.setQaId(qaId);
		
		egovQnaDao.insertQna(qnaVO);
	}

	@Override
	public void updateQna(QnaVO qnaVO) {
		egovQnaDao.updateQna(qnaVO);
	}

	@Override
	public void deleteQna(QnaVO qnaVO) {
		egovQnaDao.deleteQna(qnaVO);
	}

	@Override
	public List<?> selectQnaAnswerList(QnaVO searchVO) {
		return egovQnaDao.selectQnaAnswerList(searchVO);
	}

	@Override
	public int selectQnaAnswerListCnt(QnaVO searchVO) {
		return egovQnaDao.selectQnaAnswerListCnt(searchVO);
	}

	@Override
	public void updateQnaAnswer(QnaVO qnaVO) {
		egovQnaDao.updateQnaAnswer(qnaVO);
	}

}
