package egovframework.com.uss.olh.faq.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovFaqService {

	List<?> selectFaqList(FaqVO searchVO);

	int selectFaqListCnt(FaqVO searchVO);

	FaqVO selectFaqDetail(FaqVO searchVO) throws Exception;

	void insertFaq(FaqVO faqVO) throws FdlException;

	void updateFaq(FaqVO faqVO);

	void deleteFaq(FaqVO faqVO);

}
