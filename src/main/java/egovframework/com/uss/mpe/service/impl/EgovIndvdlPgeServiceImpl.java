package egovframework.com.uss.mpe.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.uss.mpe.service.EgovIndvdlPgeService;
import egovframework.com.uss.mpe.service.IndvdlPgeVO;

@Service("EgovIndvdlPgeService")
public class EgovIndvdlPgeServiceImpl extends EgovAbstractServiceImpl implements EgovIndvdlPgeService {

	@Resource(name="EgovIndvdlPgeDAO")
    private EgovIndvdlPgeDAO egovIndvdlPgeDao;
	
	 /** ID Generation */
	@Resource(name="egovIndvdlPgeIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<IndvdlPgeVO> selectIndvdlPgeList(IndvdlPgeVO searchVO) {
		return egovIndvdlPgeDao.selectIndvdlPgeList(searchVO);
	}

	@Override
	public int selectIndvdlPgeListCnt(IndvdlPgeVO searchVO) {
		return egovIndvdlPgeDao.selectIndvdlPgeListCnt(searchVO);
	}

	@Override
	public IndvdlPgeVO selectIndvdlPgeDetail(IndvdlPgeVO indvdlPgeVO) throws Exception {
		IndvdlPgeVO resultVO = egovIndvdlPgeDao.selectIndvdlPgeDetail(indvdlPgeVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public void insertIndvdlPge(IndvdlPgeVO indvdlPgeVO) throws FdlException {
		String cntntsId = idgenService.getNextStringId();
		indvdlPgeVO.setCntntsId(cntntsId);

    	egovIndvdlPgeDao.insertIndvdlPge(indvdlPgeVO);
	}

	@Override
	public void updateIndvdlPge(IndvdlPgeVO indvdlPgeVO) {
		egovIndvdlPgeDao.updateIndvdlPge(indvdlPgeVO);
	}

}
