package egovframework.com.uss.olh.wor.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovWordDicaryService {

	List<?> selectWordDicaryList(WordDicaryVO searchVO);

	int selectWordDicaryListCnt(WordDicaryVO searchVO);

	WordDicaryVO selectWordDicaryDetail(WordDicaryVO wordDicaryVO) throws Exception;

	void insertWordDicary(WordDicaryVO wordDicaryVO) throws FdlException;

	void updateWordDicary(WordDicaryVO wordDicaryVO);

	void deleteWordDicary(WordDicaryVO wordDicaryVO);

}
