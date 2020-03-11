package egovframework.com.uss.olh.wor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.olh.wor.service.EgovWordDicaryService;
import egovframework.com.uss.olh.wor.service.WordDicaryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovWordDicaryService")
public class EgovWordDicaryServiceImpl extends EgovAbstractServiceImpl implements EgovWordDicaryService {

	@Resource(name = "EgovWordDicaryDAO")
	private EgovWordDicaryDAO egovWordDicaryDao;

	/** ID Generation */
	@Resource(name = "egovWordDicaryIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectWordDicaryList(WordDicaryVO searchVO) {
		return egovWordDicaryDao.selectWordDicaryList(searchVO);
	}

	@Override
	public int selectWordDicaryListCnt(WordDicaryVO searchVO) {
		return egovWordDicaryDao.selectWordDicaryListCnt(searchVO);
	}

	@Override
	public WordDicaryVO selectWordDicaryDetail(WordDicaryVO wordDicaryVO) throws Exception {
		WordDicaryVO resultVO = egovWordDicaryDao.selectWordDicaryDetail(wordDicaryVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	@Override
	public void insertWordDicary(WordDicaryVO wordDicaryVO) throws FdlException {
		
		String wordId = idgenService.getNextStringId();
		wordDicaryVO.setWordId(wordId);

		egovWordDicaryDao.insertWordDicary(wordDicaryVO);
	}

	@Override
	public void updateWordDicary(WordDicaryVO wordDicaryVO) {
		egovWordDicaryDao.updateWordDicary(wordDicaryVO);
	}

	@Override
	public void deleteWordDicary(WordDicaryVO wordDicaryVO) {
		egovWordDicaryDao.deleteWordDicary(wordDicaryVO);
	}

}
