package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.cmy.service.CommunityUser;
import egovframework.com.cop.cmy.service.CommunityUserVO;
import egovframework.com.cop.cmy.service.CommunityVO;

@Repository("EgovCommuManageDAO")
public class EgovCommuManageDAO {

	@Resource(name = "egovCommuManageMapper")
	private EgovCommuManageMapper egovCommuManageMapper;

	public CommunityUser selectSingleCommuUserDetail(CommunityUser cmmntyUser) {
		return egovCommuManageMapper.selectSingleCommuUserDetail(cmmntyUser);
	}

	public List<CommunityUser> selectCommuManagerList(CommunityVO cmmntyVO) {
		return egovCommuManageMapper.selectCommuManagerList(cmmntyVO);
	}

	public int checkExistUser(CommunityUser cmmntyUser) {
		return egovCommuManageMapper.checkExistUser(cmmntyUser);
	}

	public void insertCommuUserRqst(CommunityUser cmmntyUser) {
		egovCommuManageMapper.insertCommuUserRqst(cmmntyUser);
	}

	public List<CommunityUser> selectCommuUserList(CommunityUserVO cmmntyUserVO) {
		return egovCommuManageMapper.selectCommuUserList(cmmntyUserVO);
	}

	public int selectCommuUserListCnt(CommunityUserVO cmmntyUserVO) {
		return egovCommuManageMapper.selectCommuUserListCnt(cmmntyUserVO);
	}

	public void insertCommuUser(CommunityUserVO cmmntyUserVO) {
		egovCommuManageMapper.insertCommuUser(cmmntyUserVO);
	}

	public void deleteCommuUser(CommunityUserVO cmmntyUserVO) {
		egovCommuManageMapper.deleteCommuUser(cmmntyUserVO);
	}

	public void insertCommuUserAdmin(CommunityUserVO cmmntyUserVO) {
		egovCommuManageMapper.insertCommuUserAdmin(cmmntyUserVO);
	}

	public void deleteCommuUserAdmin(CommunityUserVO cmmntyUserVO) {
		egovCommuManageMapper.deleteCommuUserAdmin(cmmntyUserVO);
	}

}
