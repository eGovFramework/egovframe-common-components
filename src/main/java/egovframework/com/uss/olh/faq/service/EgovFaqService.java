package egovframework.com.uss.olh.faq.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;

public interface EgovFaqService {

	List<FaqVO> selectFaqList(FaqVO searchVO);

	int selectFaqListCnt(FaqVO searchVO);

	FaqVO selectFaqDetail(FaqVO searchVO) throws Exception;

	// 조회수를 올리지 않는 단건 조회 — 권한 확인·수정·삭제용
	FaqVO selectFaqDetailNoCount(FaqVO searchVO) throws Exception;

	void insertFaq(FaqVO faqVO) throws FdlException;

	void updateFaq(FaqVO faqVO);

	void deleteFaq(FaqVO faqVO);

}
