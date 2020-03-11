package egovframework.com.uss.olh.faq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.olh.faq.service.EgovFaqService;
import egovframework.com.uss.olh.faq.service.FaqVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovFaqService")
public class EgovFaqServiceImpl extends EgovAbstractServiceImpl implements EgovFaqService {

	@Resource(name = "EgovFaqDAO")
	private EgovFaqDAO egovFaqDao;

	/** ID Generation */
	@Resource(name = "egovFaqManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectFaqList(FaqVO searchVO) {
		return egovFaqDao.selectFaqList(searchVO);
	}

	@Override
	public int selectFaqListCnt(FaqVO searchVO) {
		return egovFaqDao.selectFaqListCnt(searchVO);
	}

	@Override
	public FaqVO selectFaqDetail(FaqVO searchVO) throws Exception {
		
		//조회수 증가
		egovFaqDao.updateFaqInqireCo(searchVO);
		
		FaqVO resultVO = egovFaqDao.selectFaqDetail(searchVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	@Override
	public void insertFaq(FaqVO faqVO) throws FdlException {
		String faqId = idgenService.getNextStringId();
		faqVO.setFaqId(faqId);
		egovFaqDao.insertFaq(faqVO);
	}

	@Override
	public void updateFaq(FaqVO faqVO) {
		egovFaqDao.updateFaq(faqVO);
	}

	@Override
	public void deleteFaq(FaqVO faqVO) {
		egovFaqDao.deleteFaq(faqVO);
	}

}
