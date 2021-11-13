package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.cmy.service.CommunityUser;
import egovframework.com.cop.cmy.service.CommunityUserVO;
import egovframework.com.cop.cmy.service.CommunityVO;

@Repository("EgovCommuManageDAO")
public class EgovCommuManageDAO extends EgovComAbstractDAO{

	public CommunityUser selectSingleCommuUserDetail(CommunityUser cmmntyUser) {
		return (CommunityUser) selectOne("CommuManage.selectSingleCommuUserDetail", cmmntyUser);
	}

	public List<CommunityUser> selectCommuManagerList(CommunityVO cmmntyVO) {
		return selectList("CommuManage.selectCommuManagerList", cmmntyVO);
	}

	/**
	 * 기존 사용자 조건확인
	 * 
	 * @param cmmntyUser
	 * @return
	 */
	public int checkExistUser(CommunityUser cmmntyUser) {
		return (Integer) selectOne("CommuManage.checkExistUser", cmmntyUser);
	}

	public void insertCommuUserRqst(CommunityUser cmmntyUser) {
		insert("CommuManage.insertCommuUserRqst", cmmntyUser);
	}

	/**
	 * 커뮤니티 사용자 조회(멀티건)
	 * 
	 * @param cmmntyUserVO
	 * @return commuUsers
	 */
//	public List<?> selectCommuUserList(CommunityUserVO cmmntyUserVO) {
	public List<CommunityUser> selectCommuUserList(CommunityUserVO cmmntyUserVO) {
		return selectList("CommuManage.selectCommuUserList", cmmntyUserVO);
	}

	/**
	 * 커뮤니티 사용자 조회(멀티건) 카운트
	 * 
	 * @param cmmntyUserVO
	 * @return
	 */
	public int selectCommuUserListCnt(CommunityUserVO cmmntyUserVO) {
		return (Integer) selectOne("CommuManage.selectCommuUserListCnt", cmmntyUserVO);
	}

	public void insertCommuUser(CommunityUserVO cmmntyUserVO) {
		update("CommuManage.insertCommuUser", cmmntyUserVO);
	}

	/**
	 * 커뮤니티 사용자 삭제
	 * 
	 * @param cmmntyUserVO
	 */
	public void deleteCommuUser(CommunityUserVO cmmntyUserVO) {
		delete("CommuManage.deleteCommuUser", cmmntyUserVO);
	}

	public void insertCommuUserAdmin(CommunityUserVO cmmntyUserVO) {
		update("CommuManage.insertCommuUserAdmin", cmmntyUserVO);
	}

	public void deleteCommuUserAdmin(CommunityUserVO cmmntyUserVO) {
		update("CommuManage.deleteCommuUserAdmin", cmmntyUserVO);
	}

}
