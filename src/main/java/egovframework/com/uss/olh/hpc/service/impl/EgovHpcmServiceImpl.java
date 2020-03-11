package egovframework.com.uss.olh.hpc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.uss.olh.hpc.service.EgovHpcmService;
import egovframework.com.uss.olh.hpc.service.HpcmDefaultVO;
import egovframework.com.uss.olh.hpc.service.HpcmVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovHpcmService")
public class EgovHpcmServiceImpl extends EgovAbstractServiceImpl implements EgovHpcmService {

	@Resource(name="EgovHpcmDAO")
    private EgovHpcmDAO egovHpcmDao;

    /** ID Generation */
	@Resource(name="egovHpcmManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<?> selectHpcmList(HpcmDefaultVO searchVO) {
		return egovHpcmDao.selectHpcmList(searchVO);
	}

	@Override
	public int selectHpcmListCnt(HpcmDefaultVO searchVO) {
		return egovHpcmDao.selectHpcmListCnt(searchVO);
	}

	@Override
	public HpcmVO selectHpcmDetail(HpcmVO hpcmManageVO) throws Exception {
		HpcmVO resultVO = egovHpcmDao.selectHpcmDetail(hpcmManageVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public void insertHpcm(HpcmVO hpcmVO) throws FdlException {

		String hpcmId = idgenService.getNextStringId();
		hpcmVO.setHpcmId(hpcmId);

    	egovHpcmDao.insertHpcm(hpcmVO);
	}

	@Override
	public void updateHpcm(HpcmVO hpcmVO) {
		egovHpcmDao.updateHpcm(hpcmVO);
	}

	@Override
	public void deleteHpcmCn(HpcmVO hpcmVO) {
		egovHpcmDao.deleteHpcm(hpcmVO);
	}

}
