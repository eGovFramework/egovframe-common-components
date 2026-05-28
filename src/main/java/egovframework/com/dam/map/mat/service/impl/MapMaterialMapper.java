package egovframework.com.dam.map.mat.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;

/**
 * 지식맵(지식유형)에 대한 Mapper 인터페이스
 * @author 박종선
 * @version 1.0
 */
@EgovMapper("mapMaterialMapper")
public interface MapMaterialMapper {

	/**
	 * 등록된 지식맵(지식유형) 목록을 조회한다.
	 */
	List<MapMaterialVO> selectMapMaterialList(MapMaterialVO searchVO);

	/**
	 * 지식맵(지식유형) 목록 총 개수를 조회한다.
	 */
	int selectMapMaterialTotCnt(MapMaterialVO searchVO);

	/**
	 * 지식맵(지식유형) 상세 정보를 조회한다.
	 */
	MapMaterial selectMapMaterial(MapMaterial mapMaterial);

	/**
	 * 지식맵(지식유형) 정보를 등록한다.
	 */
	void insertMapMaterial(MapMaterial mapMaterial);

	/**
	 * 지식맵(지식유형) 정보를 수정한다.
	 */
	void updateMapMaterial(MapMaterial mapMaterial);

	/**
	 * 지식맵(지식유형) 정보를 삭제한다.
	 */
	void deleteMapMaterial(MapMaterial mapMaterial);

	/**
	 * 지식유형코드 중복 여부를 체크한다.
	 */
	int selectKnoTypeCdCheck(String knoTypeCd);

}
