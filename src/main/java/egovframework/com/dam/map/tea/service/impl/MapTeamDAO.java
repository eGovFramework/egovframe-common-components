package egovframework.com.dam.map.tea.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.dam.map.tea.service.MapTeam;
import egovframework.com.dam.map.tea.service.MapTeamVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 지식맵(조직별)에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 지식맵(조직별)에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식맵(조직별)의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 22-7-2010 오전 10:57:44
 */
@Repository("MapTeamDAO")
public class MapTeamDAO extends EgovComAbstractDAO{

	/**
	 * 등록된 지식맵(조직별) 목록을 조회 한다.
	 * @param mapTeamVO- 지식맵(조직별) VO
	 * @return String - 지식맵(조직별) 목록
	 *
	 * @param MapTeamVO
	 */
	public List<?> selectMapTeamList(MapTeamVO searchVO) throws Exception {
		return selectList("MapTeamDAO.selectMapTeamList", searchVO);
	}

	/**
	 * 지식맵(조직별) 목록 총 갯수를 조회한다.
	 * @param MapTeamVO - 지식맵(조직별) Vo
	 * @return int - 지식맵(조직별) 토탈 카운트 수
	 *
	 * @param MapTeamVO
	 */
	public int selectMapTeamTotCnt(MapTeamVO searchVO) throws Exception {
		return (Integer)selectOne("MapTeamDAO.selectMapTeamTotCnt", searchVO);
	}

	/**
	 * 지식맵(조직별)상세 정보를 조회 한다.
	 * @param MapTeamVO - 지식맵(조직별) VO
	 * @return String - 지식맵(조직별) VO
	 *
	 * @param MapTeamVO
	 */
	public MapTeam selectMapTeamDetail(MapTeam mapTeam) throws Exception {
		return (MapTeam)selectOne("MapTeamDAO.selectMapTeamDetail", mapTeam);
	}

	/**
	 * 지식맵(조직별) 정보를 신규로 등록한다.
	 * @param siteUrl - 지식맵(조직별) model
	 *
	 * @param orgnztNm
	 */
	public void insertMapTeam(MapTeam mapTeam) throws Exception {
		insert("MapTeamDAO.insertMapTeam", mapTeam);
	}

	/**
	 * 기 등록 된 지식맵(조직별) 정보를 수정 한다.
	 * @param siteUrl - 지식맵(조직별) model
	 *
	 * @param orgnztNm
	 */
	public void updateMapTeam(MapTeam mapTeam) throws Exception {
		update("MapTeamDAO.updateMapTeam", mapTeam);
	}

	/**
	 * 기 등록된 지식맵(조직별) 정보를 삭제한다.
	 * @param siteUrl - 지식맵(조직별) model
	 *
	 * @param orgnztNm
	 */
	public void deleteMapTeam(MapTeam mapTeam) throws Exception {
		delete("MapTeamDAO.deleteMapTeam", mapTeam);
	}

}