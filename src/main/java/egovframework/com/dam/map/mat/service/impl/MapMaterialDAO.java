package egovframework.com.dam.map.mat.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;

import org.springframework.stereotype.Repository;

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
public class MapMaterialDAO extends EgovComAbstractDAO {

	/**
	 * 등록된 지식맵(지식유형) 정보를 조회 한다.
	 * @param mapMaterialVO- 지식맵(지식유형) VO
	 * @return String - 지식맵(지식유형)목록
	 *
	 * @param MapMaterialVO
	 */
	public List<?> selectMapMaterialList(MapMaterialVO searchVO) throws Exception {
		return  selectList("MapMaterialDAO.selectMapMaterialList", searchVO);
	}

	/**
	 * 지식맵(지식유형) 목록 총 갯수를 조회한다.
	 * @param MapMaterialVO - 지식맵(지식유형) Vo
	 * @return int - 지식맵(지식유형) 토탈 카운트 수
	 *
	 * @param MapMaterialVO
	 */
	public int selectMapMaterialTotCnt(MapMaterialVO searchVO) throws Exception {
		return  (Integer)selectOne("MapMaterialDAO.selectMapMaterialTotCnt", searchVO);
	}

	/**
	 * 지식맵(지식유형)상세 정보를 조회 한다.
	 * @param MapMaterialVO - 지식맵(지식유형) VO
	 * @return String - 지식맵(지식유형)VO
	 *
	 * @param MapMaterialVO
	 */
	public MapMaterial selectMapMaterial(MapMaterial mapMaterial) throws Exception {
		return (MapMaterial)selectOne("MapMaterialDAO.selectMapMaterial", mapMaterial);
	}

	/**
	 * 지식맵(지식유형) 정보를 신규로 등록한다.
	 * @param konTypeNm - 지식맵(지식유형) model
	 *
	 * @param MapMaterialVO
	 */
	public void insertMapMaterial(MapMaterial mapMaterial) throws Exception {
		insert("MapMaterialDAO.insertMapMaterial", mapMaterial);
	}

	/**
	 * 기 등록 된 지식맵(지식유형)링 정보를 수정 한다.
	 * @param konTypeNm - 지식맵(지식유형) model
	 *
	 * @param MapMaterialVO
	 */
	public void updateMapMaterial(MapMaterial mapMaterial) throws Exception {
		update("MapMaterialDAO.updateMapMaterial", mapMaterial);
	}

	/**
	 * 기 등록된 지식맵(지식유형) 정보를 삭제한다.
	 * @param konTypeNm - 지식맵(지식유형) model
	 *
	 * @param MapMaterialVO
	 */
	public void deleteMapMaterial(MapMaterial mapMaterial) throws Exception {
		delete("MapMaterialDAO.deleteMapMaterial", mapMaterial);
	}

	/**
	 * 지식유형코드 중복 여부 체크(위치 : 1260.지식맵관리(유형) > 등록)
	 * @param knoTypeCd
	 * @return 중복 여부
	 * @throws Exception
	 */
	public int knoTypeCdCheck(String knoTypeCd) throws Exception {
		return (Integer)selectOne("MapMaterialDAO.selectKnoTypeCdCheck", knoTypeCd);
	}
}