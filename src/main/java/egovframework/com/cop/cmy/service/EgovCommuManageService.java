package egovframework.com.cop.cmy.service;

import java.util.Map;

public interface EgovCommuManageService {

	Map<String, Object> selectCommuInf(CommunityVO cmmntyVO);

	String checkCommuUserDetail(CommunityUser cmmntyUser);

	void insertCommuUserRqst(CommunityUser cmmntyUser);

	Map<String, Object> selectCommuUserList(CommunityUserVO cmmntyUserVO);

	Boolean selectIsCommuAdmin(CommunityUserVO userVO);

	void insertCommuUser(CommunityUserVO cmmntyUserVO);

	void deleteCommuUser(CommunityUserVO cmmntyUserVO);

	void insertCommuUserAdmin(CommunityUserVO cmmntyUserVO);

	void deleteCommuUserAdmin(CommunityUserVO cmmntyUserVO);

}
