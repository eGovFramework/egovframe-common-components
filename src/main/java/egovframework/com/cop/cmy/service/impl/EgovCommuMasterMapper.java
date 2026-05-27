package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityVO;

/**
 * 개요
 * - 커뮤니티 마스터에 대한 Mapper 인터페이스를 정의한다.
 *
 * 상세내용
 * - 커뮤니티에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 * - 커뮤니티 조회 기능은 목록조회, 상세조회로 구분된다.
 */
@EgovMapper
public interface EgovCommuMasterMapper {

	/**
	 * 커뮤니티 목록을 조회한다.
	 * @param cmmntyVO - 커뮤니티 VO
	 * @return List - 커뮤니티 목록
	 */
	List<CommunityVO> selectCommuMasterList(CommunityVO cmmntyVO);

	/**
	 * 커뮤니티 목록 총 건수를 조회한다.
	 * @param cmmntyVO - 커뮤니티 VO
	 * @return int - 총 건수
	 */
	int selectCommuMasterListCnt(CommunityVO cmmntyVO);

	/**
	 * 커뮤니티를 등록한다.
	 * @param community - 커뮤니티 모델
	 * @return int - 등록 결과
	 */
	int insertCommuMaster(Community community);

	/**
	 * 커뮤니티 상세 정보를 조회한다.
	 * @param cmmntyVO - 커뮤니티 VO
	 * @return CommunityVO - 커뮤니티 VO
	 */
	CommunityVO selectCommuMasterDetail(CommunityVO cmmntyVO);

	/**
	 * 커뮤니티를 수정한다.
	 * @param community - 커뮤니티 모델
	 * @return int - 수정 결과
	 */
	int updateCommuMaster(Community community);

	/**
	 * 커뮤니티를 삭제한다.
	 * @param community - 커뮤니티 모델
	 * @return int - 삭제 결과
	 */
	int deleteCommuMaster(Community community);

	/**
	 * 포트릿을 위한 커뮤니티 목록을 조회한다.
	 * @param cmmntyVO - 커뮤니티 VO
	 * @return List - 커뮤니티 목록
	 */
	List<CommunityVO> selectCommuMasterListPortlet(CommunityVO cmmntyVO);

}
