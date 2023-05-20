package egovframework.com.uss.olh.qna.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;

public interface EgovQnaService {

	List<QnaVO> selectQnaList(QnaVO searchVO);

	int selectQnaListCnt(QnaVO searchVO);

	QnaVO selectQnaDetail(QnaVO qnaVO) throws Exception;

	void updateQnaInqireCo(QnaVO qnaVO);

	void insertQna(QnaVO qnaVO) throws FdlException;

	void updateQna(QnaVO qnaVO);

	void deleteQna(QnaVO qnaVO);

	List<QnaVO> selectQnaAnswerList(QnaVO searchVO);

	int selectQnaAnswerListCnt(QnaVO searchVO);

	void updateQnaAnswer(QnaVO qnaVO);

}
