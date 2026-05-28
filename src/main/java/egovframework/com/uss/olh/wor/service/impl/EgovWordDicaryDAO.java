package egovframework.com.uss.olh.wor.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.olh.wor.service.WordDicaryVO;

import jakarta.annotation.Resource;

@Repository("EgovWordDicaryDAO")
public class EgovWordDicaryDAO {

	@Resource(name = "EgovWordDicaryMapper")
	private EgovWordDicaryMapper egovWordDicaryMapper;

	public List<WordDicaryVO> selectWordDicaryList(WordDicaryVO searchVO) {
		return egovWordDicaryMapper.selectWordDicaryList(searchVO);
	}

	public int selectWordDicaryListCnt(WordDicaryVO searchVO) {
		return egovWordDicaryMapper.selectWordDicaryListCnt(searchVO);
	}

	public WordDicaryVO selectWordDicaryDetail(WordDicaryVO wordDicaryVO) {
		return egovWordDicaryMapper.selectWordDicaryDetail(wordDicaryVO);
	}

	public void insertWordDicary(WordDicaryVO wordDicaryVO) {
		egovWordDicaryMapper.insertWordDicary(wordDicaryVO);
	}

	public void updateWordDicary(WordDicaryVO wordDicaryVO) {
		egovWordDicaryMapper.updateWordDicary(wordDicaryVO);
	}

	public void deleteWordDicary(WordDicaryVO wordDicaryVO) {
		egovWordDicaryMapper.deleteWordDicary(wordDicaryVO);
	}

}
