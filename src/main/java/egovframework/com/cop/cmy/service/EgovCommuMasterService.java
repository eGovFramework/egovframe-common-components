package egovframework.com.cop.cmy.service;

import java.util.List;
import java.util.Map;

public interface EgovCommuMasterService {

	Map<String, Object> selectCommuMasterList(CommunityVO cmmntyVO);

	String insertCommuMaster(Community community) throws Exception;

	CommunityVO selectCommuMaster(CommunityVO cmmntyVO) throws Exception;

	void updateCommuMaster(Community community);

	void deleteBBSMasterInf(Community community);

	List<CommunityVO> selectCommuMasterListPortlet(CommunityVO cmmntyVO);
}
