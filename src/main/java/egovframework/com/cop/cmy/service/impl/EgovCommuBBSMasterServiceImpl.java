package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.cmy.service.EgovCommuBBSMasterService;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovCommuBBSMasterService")
public class EgovCommuBBSMasterServiceImpl extends EgovAbstractServiceImpl implements EgovCommuBBSMasterService {

	@Resource(name = "EgovCommuBBSMasterDAO")
    private EgovCommuBBSMasterDAO egovCommuBBSMasterDao;
	
	@Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Override
	public List<BoardMasterVO> selectCommuBBSMasterListMain(BoardMasterVO boardMasterVO) {
		return egovCommuBBSMasterDao.selectCommuBBSMasterListMain(boardMasterVO);
	}


}
