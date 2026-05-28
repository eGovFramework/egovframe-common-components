package egovframework.com.uss.olh.qna.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.olh.qna.service.QnaVO;

import jakarta.annotation.Resource;

@Repository("EgovQnaDAO")
public class EgovQnaDAO {

	@Resource(name = "EgovQnaMapper")
	private EgovQnaMapper egovQnaMapper;

	public List<QnaVO> selectQnaList(QnaVO searchVO) {
		return egovQnaMapper.selectQnaList(searchVO);
	}

	public int selectQnaListCnt(QnaVO searchVO) {
		return egovQnaMapper.selectQnaListCnt(searchVO);
	}

	public QnaVO selectQnaDetail(QnaVO qnaVO) {
		return egovQnaMapper.selectQnaDetail(qnaVO);
	}

	public void updateQnaInqireCo(QnaVO qnaVO) {
		egovQnaMapper.updateQnaInqireCo(qnaVO);
	}

	public void insertQna(QnaVO qnaVO) {
		egovQnaMapper.insertQna(qnaVO);
	}

	public void updateQna(QnaVO qnaVO) {
		egovQnaMapper.updateQna(qnaVO);
	}

	public void deleteQna(QnaVO qnaVO) {
		egovQnaMapper.deleteQna(qnaVO);
	}

	public List<QnaVO> selectQnaAnswerList(QnaVO searchVO) {
		return egovQnaMapper.selectQnaAnswerList(searchVO);
	}

	public int selectQnaAnswerListCnt(QnaVO searchVO) {
		return egovQnaMapper.selectQnaAnswerListCnt(searchVO);
	}

	public void updateQnaAnswer(QnaVO qnaVO) {
		egovQnaMapper.updateQnaAnswer(qnaVO);
	}

}
