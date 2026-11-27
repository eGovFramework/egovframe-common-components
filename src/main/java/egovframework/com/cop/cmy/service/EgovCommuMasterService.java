package egovframework.com.cop.cmy.service;

import java.util.List;
import java.util.Map;

public interface EgovCommuMasterService {

	Map<String, Object> selectCommuMasterList(CommunityVO cmmntyVO);

	String insertCommuMaster(Community community);

	CommunityVO selectCommuMaster(CommunityVO cmmntyVO);

	void updateCommuMaster(Community community);

	void deleteBBSMasterInf(Community community);
	
	List<CommunityVO> selectCommuMasterListPortlet(CommunityVO cmmntyVO);
}
