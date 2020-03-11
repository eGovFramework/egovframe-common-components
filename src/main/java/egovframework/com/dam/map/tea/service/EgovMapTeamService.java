package egovframework.com.dam.map.tea.service;

import java.util.List;

/**
 * 개요
 * - 지식맵(조직별)에 대한 Service Interface를 정의한다.
 *
 * 상세내용
 * - 지식맵(조직별)에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식맵(조직별)의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 22-7-2010 오전 10:57:37
 */
public interface EgovMapTeamService {

	/**
	 * 등록된 지식맵(조직별) 목록을 조회 한다.
	 * @param mapTeamVO- 지식맵(조직별) VO
	 * @return String - 지식맵(조직별) 목록
	 *
	 * @param MapTeamVO
	 */
	List<?> selectMapTeamList(MapTeamVO searchVO) throws Exception;

	/**
	 * 지식맵(조직별) 목록 총 갯수를 조회한다.
	 * @param MapTeamVO - 지식맵(조직별) Vo
	 * @return int - 지식맵(조직별) 토탈 카운트 수
	 *
	 * @param MapTeamVO
	 */
	int selectMapTeamTotCnt(MapTeamVO searchVO) throws Exception;

	/**
	 * 지식맵(조직별)상세 정보를 조회 한다.
	 * @param MapTeamVO - 지식맵(조직별) VO
	 * @return String - 지식맵(조직별) VO
	 *
	 * @param MapTeamVO
	 */
	MapTeam selectMapTeamDetail(MapTeam mapTeam) throws Exception;

	/**
	 * 지식맵(조직별) 정보를 신규로 등록한다.
	 * @param siteUrl - 지식맵(조직별) model
	 *
	 * @param orgnztNm
	 */
	void insertMapTeam(MapTeam mapTeam) throws Exception;

	/**
	 * 기 등록 된 지식맵(조직별) 정보를 수정 한다.
	 * @param siteUrl - 지식맵(조직별) model
	 *
	 * @param orgnztNm
	 */
	void updateMapTeam(MapTeam mapTeam) throws Exception;

	/**
	 * 기 등록된 지식맵(조직별) 정보를 삭제한다.
	 * @param siteUrl - 지식맵(조직별) model
	 *
	 * @param orgnztNm
	 */
	void deleteMapTeam(MapTeam mapTeam) throws Exception;

}