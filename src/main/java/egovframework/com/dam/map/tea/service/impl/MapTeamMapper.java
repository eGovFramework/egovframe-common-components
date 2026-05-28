package egovframework.com.dam.map.tea.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.dam.map.tea.service.MapTeam;
import egovframework.com.dam.map.tea.service.MapTeamVO;

/**
 * 지식맵(조직별)에 대한 Mapper 인터페이스
 * @author 박종선
 * @version 1.0
 */
@EgovMapper("mapTeamMapper")
public interface MapTeamMapper {

	/**
	 * 등록된 지식맵(조직별) 목록을 조회한다.
	 */
	List<MapTeamVO> selectMapTeamList(MapTeamVO searchVO);

	/**
	 * 지식맵(조직별) 목록 총 개수를 조회한다.
	 */
	int selectMapTeamTotCnt(MapTeamVO searchVO);

	/**
	 * 지식맵(조직별) 상세 정보를 조회한다.
	 */
	MapTeam selectMapTeamDetail(MapTeam mapTeam);

	/**
	 * 지식맵(조직별) 정보를 등록한다.
	 */
	void insertMapTeam(MapTeam mapTeam);

	/**
	 * 지식맵(조직별) 정보를 수정한다.
	 */
	void updateMapTeam(MapTeam mapTeam);

	/**
	 * 지식맵(조직별) 정보를 삭제한다.
	 */
	void deleteMapTeam(MapTeam mapTeam);

}
