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

	public int checkExistUser(CommunityUser cmmntyUser) {
		return (Integer)selectOne("CommuManage.checkExistUser", cmmntyUser);
	}

	public void insertCommuUserRqst(CommunityUser cmmntyUser) {
		insert("CommuManage.insertCommuUserRqst", cmmntyUser);
	}

	public List<?> selectCommuUserList(CommunityUserVO cmmntyUserVO) {
		return list("CommuManage.selectCommuUserList", cmmntyUserVO);
	}

	public int selectCommuUserListCnt(CommunityUserVO cmmntyUserVO) {
		return (Integer)selectOne("CommuManage.selectCommuUserListCnt", cmmntyUserVO);
	}

	public void insertCommuUser(CommunityUserVO cmmntyUserVO) {
		update("CommuManage.insertCommuUser", cmmntyUserVO);
	}

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
