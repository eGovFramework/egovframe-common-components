package egovframework.com.dam.map.mat.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 지식맵(지식유형)에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 지식맵(지식유형)에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식맵(지식유형)의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:52
 */
@Repository("MapMaterialDAO")
public class MapMaterialDAO {

	@Resource(name = "mapMaterialMapper")
	private MapMaterialMapper mapMaterialMapper;

	/**
	 * 등록된 지식맵(지식유형) 목록을 조회한다.
	 * @param searchVO - 지식맵(지식유형) VO
	 * @return List - 지식맵(지식유형) 목록
	 */
	public List<MapMaterialVO> selectMapMaterialList(MapMaterialVO searchVO) throws Exception {
		return mapMaterialMapper.selectMapMaterialList(searchVO);
	}

	/**
	 * 지식맵(지식유형) 목록 총 개수를 조회한다.
	 * @param searchVO - 지식맵(지식유형) Vo
	 * @return int - 지식맵(지식유형) 토탈 카운트 수
	 */
	public int selectMapMaterialTotCnt(MapMaterialVO searchVO) throws Exception {
		return mapMaterialMapper.selectMapMaterialTotCnt(searchVO);
	}

	/**
	 * 지식맵(지식유형) 상세 정보를 조회한다.
	 * @param mapMaterial - 지식맵(지식유형) VO
	 * @return MapMaterial - 지식맵(지식유형) VO
	 */
	public MapMaterial selectMapMaterial(MapMaterial mapMaterial) throws Exception {
		return mapMaterialMapper.selectMapMaterial(mapMaterial);
	}

	/**
	 * 지식맵(지식유형) 정보를 신규로 등록한다.
	 * @param mapMaterial - 지식맵(지식유형) model
	 */
	public void insertMapMaterial(MapMaterial mapMaterial) throws Exception {
		mapMaterialMapper.insertMapMaterial(mapMaterial);
	}

	/**
	 * 기 등록 된 지식맵(지식유형) 정보를 수정한다.
	 * @param mapMaterial - 지식맵(지식유형) model
	 */
	public void updateMapMaterial(MapMaterial mapMaterial) throws Exception {
		mapMaterialMapper.updateMapMaterial(mapMaterial);
	}

	/**
	 * 기 등록된 지식맵(지식유형) 정보를 삭제한다.
	 * @param mapMaterial - 지식맵(지식유형) model
	 */
	public void deleteMapMaterial(MapMaterial mapMaterial) throws Exception {
		mapMaterialMapper.deleteMapMaterial(mapMaterial);
	}

	/**
	 * 지식유형코드 중복 여부 체크
	 * @param knoTypeCd
	 * @return 중복 여부
	 */
	public int knoTypeCdCheck(String knoTypeCd) throws Exception {
		return mapMaterialMapper.selectKnoTypeCdCheck(knoTypeCd);
	}

}
