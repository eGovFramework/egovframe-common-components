package egovframework.com.uss.olh.faq.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.olh.faq.service.FaqVO;

import jakarta.annotation.Resource;

@Repository("EgovFaqDAO")
public class EgovFaqDAO {

	@Resource(name = "EgovFaqMapper")
	private EgovFaqMapper egovFaqMapper;

	public List<FaqVO> selectFaqList(FaqVO searchVO) {
		return egovFaqMapper.selectFaqList(searchVO);
	}

	public int selectFaqListCnt(FaqVO searchVO) {
		return egovFaqMapper.selectFaqListCnt(searchVO);
	}

	public void updateFaqInqireCo(FaqVO searchVO) {
		egovFaqMapper.updateFaqInqireCo(searchVO);
	}

	public FaqVO selectFaqDetail(FaqVO searchVO) {
		return egovFaqMapper.selectFaqDetail(searchVO);
	}

	public void insertFaq(FaqVO faqVO) {
		egovFaqMapper.insertFaq(faqVO);
	}

	public void updateFaq(FaqVO faqVO) {
		egovFaqMapper.updateFaq(faqVO);
	}

	public void deleteFaq(FaqVO faqVO) {
		egovFaqMapper.deleteFaq(faqVO);
	}

}
