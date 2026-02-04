package egovframework.com.dam.map.tea.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import egovframework.com.dam.map.tea.service.EgovMapTeamService;
import egovframework.com.dam.map.tea.service.MapTeam;
import egovframework.com.dam.map.tea.service.MapTeamVO;

/**
 * <pre>
 * 개요
 * - 지식맵(조직별)에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 지식맵(조직별)에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식맵(조직별)의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 박종선
 * @since 2010.07.22
 * @version 1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.22  박종선          최초 생성
 *   2025.06.16  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(필드 명명 규칙)
 *
 *      </pre>
 */
@Service("MapTeamService")
public class EgovMapTeamServiceImpl extends EgovAbstractServiceImpl implements EgovMapTeamService {

	@Resource(name = "MapTeamDAO")
	private MapTeamDAO mapTeamDAO;

	/**
	 * 등록된 지식맵(조직별) 목록을 조회 한다.
	 * 
	 * @param mapTeamVO- 지식맵(조직별) VO
	 * @return String - 지식맵(조직별) 목록
	 *
	 * @param MapTeamVO
	 */
	@Override
	public List<MapTeamVO> selectMapTeamList(MapTeamVO searchVO) throws Exception {
		return mapTeamDAO.selectMapTeamList(searchVO);
	}

	/**
	 * 지식맵(조직별) 목록 총 개수를 조회한다.
	 * 
	 * @param MapTeamVO - 지식맵(조직별) Vo
	 * @return int - 지식맵(조직별) 토탈 카운트 수
	 *
	 * @param MapTeamVO
	 */
	@Override
	public int selectMapTeamTotCnt(MapTeamVO searchVO) throws Exception {
		return mapTeamDAO.selectMapTeamTotCnt(searchVO);
	}

	/**
	 * 지식맵(조직별)상세 정보를 조회 한다.
	 * 
	 * @param MapTeamVO - 지식맵(조직별) VO
	 * @return String - 지식맵(조직별) VO
	 *
	 * @param MapTeam
	 */
	@Override
	public MapTeam selectMapTeamDetail(MapTeam mapTeam) throws Exception {
		MapTeam mtm = mapTeamDAO.selectMapTeamDetail(mapTeam);
		return mtm;
	}

	/**
	 * 지식맵(조직별) 정보를 신규로 등록한다.
	 * 
	 * @param siteUrl  - 지식맵(조직별) model
	 *
	 * @param orgnztNm
	 */
	@Override
	public void insertMapTeam(MapTeam mapTeam) throws Exception {
		try {
			mapTeamDAO.insertMapTeam(mapTeam);
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("이미 등록된 조직ID입니다.", e);
		}
	}

	/**
	 * 기 등록 된 지식맵(조직별) 정보를 수정 한다.
	 * 
	 * @param siteUrl  - 지식맵(조직별) model
	 *
	 * @param orgnztNm
	 */
	@Override
	public void updateMapTeam(MapTeam mapTeam) throws Exception {
		mapTeamDAO.updateMapTeam(mapTeam);
	}

	/**
	 * 기 등록된 지식맵(조직별) 정보를 삭제한다.
	 * 
	 * @param siteUrl  - 지식맵(조직별) model
	 *
	 * @param orgnztNm
	 */
	@Override
	public void deleteMapTeam(MapTeam mapTeam) throws Exception {
		mapTeamDAO.deleteMapTeam(mapTeam);
	}

}