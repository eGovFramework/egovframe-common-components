package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityVO;

@Repository("EgovCommuMasterDAO")
public class EgovCommuMasterDAO {

	@Resource(name = "egovCommuMasterMapper")
	private EgovCommuMasterMapper egovCommuMasterMapper;

	public List<CommunityVO> selectCommuMasterList(CommunityVO cmmntyVO) {
		return egovCommuMasterMapper.selectCommuMasterList(cmmntyVO);
	}

	public int selectCommuMasterListCnt(CommunityVO cmmntyVO) {
		return egovCommuMasterMapper.selectCommuMasterListCnt(cmmntyVO);
	}

	public int insertCommuMaster(Community community) {
		return egovCommuMasterMapper.insertCommuMaster(community);
	}

	public CommunityVO selectCommuMasterDetail(CommunityVO cmmntyVO) {
		return egovCommuMasterMapper.selectCommuMasterDetail(cmmntyVO);
	}

	public int updateCommuMaster(Community community) {
		return egovCommuMasterMapper.updateCommuMaster(community);
	}

	public int deleteCommuMaster(Community community) {
		return egovCommuMasterMapper.deleteCommuMaster(community);
	}

	/**
	 * 포트릿을 위한 커뮤니티 정보 목록 정보를 조회한다.
	 *
	 * @param cmmntyVO
	 * @return
	 */
	public List<CommunityVO> selectCommuMasterListPortlet(CommunityVO cmmntyVO) {
		return egovCommuMasterMapper.selectCommuMasterListPortlet(cmmntyVO);
	}

}
