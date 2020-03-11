package egovframework.com.uss.olh.wor.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.olh.wor.service.WordDicaryVO;

@Repository("EgovWordDicaryDAO")
public class EgovWordDicaryDAO extends EgovComAbstractDAO {

	public List<?> selectWordDicaryList(WordDicaryVO searchVO) {
		return list("WordDicary.selectWordDicaryList", searchVO);
	}

	public int selectWordDicaryListCnt(WordDicaryVO searchVO) {
		return (Integer)selectOne("WordDicary.selectWordDicaryListCnt", searchVO);
	}

	public WordDicaryVO selectWordDicaryDetail(WordDicaryVO wordDicaryVO) {
		return (WordDicaryVO)selectOne("WordDicary.selectWordDicaryDetail", wordDicaryVO);
	}

	public void insertWordDicary(WordDicaryVO wordDicaryVO) {
		insert("WordDicary.insertWordDicary", wordDicaryVO);
	}

	public void updateWordDicary(WordDicaryVO wordDicaryVO) {
		update("WordDicary.updateWordDicary", wordDicaryVO);
	}

	public void deleteWordDicary(WordDicaryVO wordDicaryVO) {
		delete("WordDicary.deleteWordDicary", wordDicaryVO);
	}

}
