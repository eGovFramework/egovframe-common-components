package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityVO;

@Repository("EgovCommuMasterDAO")
public class EgovCommuMasterDAO extends EgovComAbstractDAO{

	public List<?> selectCommuMasterList(CommunityVO cmmntyVO) {
		return list("CommuMaster.selectCommuMasterList", cmmntyVO);
	}

	public int selectCommuMasterListCnt(CommunityVO cmmntyVO) {
		return (Integer)selectOne("CommuMaster.selectCommuMasterListCnt", cmmntyVO);
	}

	public void insertCommuMaster(Community community) {
		insert("CommuMaster.insertCommuMaster", community);
		
	}

	public CommunityVO selectCommuMasterDetail(CommunityVO cmmntyVO) {
		return (CommunityVO) selectOne("CommuMaster.selectCommuMasterDetail", cmmntyVO);
	}

	public void updateCommuMaster(Community community) {
		update("CommuMaster.updateCommuMaster", community);
	}

	public void deleteCommuMaster(Community community) {
		update("CommuMaster.deleteCommuMaster", community);
	}
	
    /**
     * 포트릿을 위한 커뮤니티 정보 목록 정보를 조회한다.
     *
     * @param cmmntyVO
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
    public List<CommunityVO> selectCommuMasterListPortlet(CommunityVO cmmntyVO) throws Exception {
		return (List<CommunityVO>) list("CommuMaster.selectCommuMasterListPortlet", cmmntyVO);
    }

}
