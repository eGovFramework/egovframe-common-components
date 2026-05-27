package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.cmy.service.CommunityUser;
import egovframework.com.cop.cmy.service.CommunityUserVO;
import egovframework.com.cop.cmy.service.CommunityVO;

/**
 * 개요
 * - 커뮤니티 사용자 관리에 대한 Mapper 인터페이스를 정의한다.
 *
 * 상세내용
 * - 커뮤니티 사용자에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 */
@EgovMapper
public interface EgovCommuManageMapper {

	/**
	 * 커뮤니티 사용자 단건 상세 정보를 조회한다.
	 * @param cmmntyUser - 커뮤니티 사용자 모델
	 * @return CommunityUser - 커뮤니티 사용자
	 */
	CommunityUser selectSingleCommuUserDetail(CommunityUser cmmntyUser);

	/**
	 * 커뮤니티 운영자 목록을 조회한다.
	 * @param cmmntyVO - 커뮤니티 VO
	 * @return List - 운영자 목록
	 */
	List<CommunityUser> selectCommuManagerList(CommunityVO cmmntyVO);

	/**
	 * 커뮤니티 사용자 존재 여부를 확인한다.
	 * @param cmmntyUser - 커뮤니티 사용자 모델
	 * @return int - 존재 여부(건수)
	 */
	int checkExistUser(CommunityUser cmmntyUser);

	/**
	 * 커뮤니티 가입 신청을 등록한다.
	 * @param cmmntyUser - 커뮤니티 사용자 모델
	 */
	void insertCommuUserRqst(CommunityUser cmmntyUser);

	/**
	 * 커뮤니티 사용자 목록을 조회한다.
	 * @param cmmntyUserVO - 커뮤니티 사용자 VO
	 * @return List - 사용자 목록
	 */
	List<CommunityUser> selectCommuUserList(CommunityUserVO cmmntyUserVO);

	/**
	 * 커뮤니티 사용자 목록 총 건수를 조회한다.
	 * @param cmmntyUserVO - 커뮤니티 사용자 VO
	 * @return int - 총 건수
	 */
	int selectCommuUserListCnt(CommunityUserVO cmmntyUserVO);

	/**
	 * 커뮤니티 사용자를 승인한다.
	 * @param cmmntyUserVO - 커뮤니티 사용자 VO
	 */
	void insertCommuUser(CommunityUserVO cmmntyUserVO);

	/**
	 * 커뮤니티 사용자를 탈퇴 처리한다.
	 * @param cmmntyUserVO - 커뮤니티 사용자 VO
	 */
	void deleteCommuUser(CommunityUserVO cmmntyUserVO);

	/**
	 * 커뮤니티 운영자로 지정한다.
	 * @param cmmntyUserVO - 커뮤니티 사용자 VO
	 */
	void insertCommuUserAdmin(CommunityUserVO cmmntyUserVO);

	/**
	 * 커뮤니티 운영자를 해제한다.
	 * @param cmmntyUserVO - 커뮤니티 사용자 VO
	 */
	void deleteCommuUserAdmin(CommunityUserVO cmmntyUserVO);

}
