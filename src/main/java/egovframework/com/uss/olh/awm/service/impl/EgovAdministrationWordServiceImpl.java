package egovframework.com.uss.olh.awm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.olh.awm.service.AdministrationWordVO;
import egovframework.com.uss.olh.awm.service.EgovAdministrationWordService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovAdministrationWordService")
public class EgovAdministrationWordServiceImpl extends EgovAbstractServiceImpl implements EgovAdministrationWordService {

	@Resource(name = "EgovAdministrationWordDAO")
    private EgovAdministrationWordDAO egovAdministrationWordDao;

    @Resource(name = "egovAdministrationWordIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectAdministrationWordList(AdministrationWordVO searchVO) {
		return egovAdministrationWordDao.selectAdministrationWordList(searchVO);
	}

	@Override
	public int selectAdministrationWordListCnt(AdministrationWordVO searchVO) {
		return egovAdministrationWordDao.selectAdministrationWordListCnt(searchVO);
	}

	@Override
	public AdministrationWordVO selectAdministrationWordDetail(AdministrationWordVO administrationWord) throws Exception {
		AdministrationWordVO resultVO = egovAdministrationWordDao.selectAdministrationWordDetail(administrationWord);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	@Override
	public void insertAdministrationWord(AdministrationWordVO administrationWordVO) throws FdlException {
		String administWordId = idgenService.getNextStringId();
		administrationWordVO.setAdministWordId(administWordId);
		
		egovAdministrationWordDao.insertAdministrationWord(administrationWordVO);
	}

	@Override
	public void updateAdministrationWord(AdministrationWordVO administrationWordVO) {
		egovAdministrationWordDao.updateAdministrationWord(administrationWordVO);
	}

	@Override
	public void deleteAdministrationWord(AdministrationWordVO administrationWordVO) {
		egovAdministrationWordDao.deleteAdministrationWord(administrationWordVO);
	}

}
