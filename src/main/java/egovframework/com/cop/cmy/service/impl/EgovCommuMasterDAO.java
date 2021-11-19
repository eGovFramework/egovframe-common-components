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

	/**
	 * 커뮤니티 마스터 조회(멀티건) 카운트
	 * 
	 * @param cmmntyVO
	 * @return commuMasterListCnt
	 */
	public int selectCommuMasterListCnt(CommunityVO cmmntyVO) {
		return (Integer) selectOne("CommuMaster.selectCommuMasterListCnt", cmmntyVO);
	}

	/**
	 * 커뮤니티 마스터 등록
	 * 
	 * @param community
	 */
	public void insertCommuMaster(Community community) {
		insert("CommuMaster.insertCommuMaster", community);
	}

	/**
	 * 커뮤니티 마스터 상세 조회(단건)
	 * 
	 * @param cmmntyVO
	 * @return commuMasterDetail
	 */
	public CommunityVO selectCommuMasterDetail(CommunityVO cmmntyVO) {
		return (CommunityVO) selectOne("CommuMaster.selectCommuMasterDetail", cmmntyVO);
	}

	/**
	 * 커뮤니티 마스터 수정
	 * 
	 * @param community
	 */
	public void updateCommuMaster(Community community) {
		update("CommuMaster.updateCommuMaster", community);
	}

	public void deleteCommuMaster(Community community) {
		update("CommuMaster.deleteCommuMaster", community);
	}
	
    /**
     * 포틀릿을 위한 커뮤니티 정보 목록 정보를 조회한다.
     *
     * @param cmmntyVO
     * @return commuMasterListPortlet
     * @throws Exception
     */
    public List<CommunityVO> selectCommuMasterListPortlet(CommunityVO cmmntyVO) throws Exception {
		return selectList("CommuMaster.selectCommuMasterListPortlet", cmmntyVO);
    }

}
