package egovframework.com.cop.cmy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityVO;
import egovframework.com.cop.cmy.service.EgovCommuMasterService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovCommuMasterService")
public class EgovCommuMasterServiceImpl extends EgovAbstractServiceImpl implements EgovCommuMasterService{

	@Resource(name = "EgovCommuMasterDAO")
    private EgovCommuMasterDAO egovCommuMasterDAO;

    @Resource(name = "egovCmmntyIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Override
	public Map<String, Object> selectCommuMasterList(CommunityVO cmmntyVO) {
		
		List<?> result = egovCommuMasterDAO.selectCommuMasterList(cmmntyVO);
		int cnt = egovCommuMasterDAO.selectCommuMasterListCnt(cmmntyVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@Override
	public String insertCommuMaster(Community community) throws FdlException {
		//게시판 ID 채번
		String cmmntyId = idgenService.getNextStringId();
		community.setCmmntyId(cmmntyId);
		
		egovCommuMasterDAO.insertCommuMaster(community);
		
		return cmmntyId;
	}

	@Override
	public CommunityVO selectCommuMaster(CommunityVO cmmntyVO) throws Exception {
		CommunityVO resultVO = egovCommuMasterDAO.selectCommuMasterDetail(cmmntyVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public void updateCommuMaster(Community community) {
		egovCommuMasterDAO.updateCommuMaster(community);
	}

	@Override
	public void deleteBBSMasterInf(Community community) {
		egovCommuMasterDAO.deleteCommuMaster(community);
	}

	@Override
	public List<CommunityVO> selectCommuMasterListPortlet(CommunityVO cmmntyVO) throws Exception {
		return egovCommuMasterDAO.selectCommuMasterListPortlet(cmmntyVO);
	}

}
