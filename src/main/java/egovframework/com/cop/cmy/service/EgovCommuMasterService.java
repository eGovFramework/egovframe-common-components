package egovframework.com.cop.cmy.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovCommuMasterService {

	Map<String, Object> selectCommuMasterList(CommunityVO cmmntyVO);

	String insertCommuMaster(Community community) throws FdlException;

	CommunityVO selectCommuMaster(CommunityVO cmmntyVO) throws Exception;

	void updateCommuMaster(Community community);

	void deleteBBSMasterInf(Community community);
	
	List<CommunityVO> selectCommuMasterListPortlet(CommunityVO cmmntyVO) throws Exception;
}
