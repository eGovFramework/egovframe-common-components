package egovframework.com.uss.olh.omm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.olh.omm.service.EgovOnlineManualService;
import egovframework.com.uss.olh.omm.service.OnlineManualVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovOnlineManualService")
public class EgovOnlineManualServiceImpl extends EgovAbstractServiceImpl implements EgovOnlineManualService {

	@Resource(name = "EgovOnlineManualDAO")
    private EgovOnlineManualDAO egovOnlineManualDao;

	@Resource(name = "egovOnlineMenualIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectOnlineManualList(OnlineManualVO searchVO) {
		return egovOnlineManualDao.selectOnlineManualList(searchVO);
	}

	@Override
	public int selectOnlineManualListCnt(OnlineManualVO searchVO) {
		return egovOnlineManualDao.selectOnlineManualListCnt(searchVO);
	}

	@Override
	public OnlineManualVO selectOnlineManualDetail(OnlineManualVO onlineManualVO) throws Exception {
		OnlineManualVO resultVO = egovOnlineManualDao.selectOnlineManualDetail(onlineManualVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	@Override
	public void insertOnlineManual(OnlineManualVO onlineManualVO) throws FdlException {
		String onlineMnlId = idgenService.getNextStringId();
		onlineManualVO.setOnlineMnlId(onlineMnlId);
		
		egovOnlineManualDao.insertOnlineManual(onlineManualVO);
		
	}

	@Override
	public void updateOnlineManual(OnlineManualVO onlineManualVO) {
		egovOnlineManualDao.updateOnlineManual(onlineManualVO);
	}

	@Override
	public void deleteOnlineManual(OnlineManualVO onlineManualVO) {
		egovOnlineManualDao.deleteOnlineManual(onlineManualVO);
	}

}
