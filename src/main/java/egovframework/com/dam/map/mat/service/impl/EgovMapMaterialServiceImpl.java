package egovframework.com.dam.map.mat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.dam.map.mat.service.EgovMapMaterialService;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;

/**
 * <pre>
 * 개요
 * - 지식맵(지식유형)에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 지식맵(지식유형)에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식맵(지식유형)의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 박종선
 * @since 2010.08.12
 * @version 1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.12  박종선          최초 생성
 *   2025.06.14  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(필드 명명 규칙)
 *
 *      </pre>
 */
@Service("MapMaterialService")
public class EgovMapMaterialServiceImpl extends EgovAbstractServiceImpl implements EgovMapMaterialService {

	@Resource(name = "MapMaterialDAO")
	private MapMaterialDAO mapMaterialDAO;

	/**
	 * 등록된 지식맵(지식유형) 정보를 조회 한다.
	 * 
	 * @param mapMaterialVO- 지식맵(지식유형) VO
	 * @return String - 지식맵(지식유형)목록
	 *
	 * @param MapMaterialVO
	 */
	@Override
	public List<MapMaterialVO> selectMapMaterialList(MapMaterialVO searchVO) throws Exception {
		return mapMaterialDAO.selectMapMaterialList(searchVO);
	}

	/**
	 * 지식맵(지식유형) 목록 총 개수를 조회한다.
	 * 
	 * @param MapMaterialVO - 지식맵(지식유형) Vo
	 * @return int - 지식맵(지식유형) 토탈 카운트 수
	 *
	 * @param MapMaterialVO
	 */
	@Override
	public int selectMapMaterialTotCnt(MapMaterialVO searchVO) throws Exception {
		return mapMaterialDAO.selectMapMaterialTotCnt(searchVO);
	}

	/**
	 * 지식맵(지식유형)상세 정보를 조회 한다.
	 * 
	 * @param MapMaterialVO - 지식맵(지식유형) VO
	 * @return String - 지식맵(지식유형)VO
	 *
	 * @param MapMaterialVO
	 */
	@Override
	public MapMaterial selectMapMaterial(MapMaterial mapMaterial) throws Exception {
		MapMaterial mtm = mapMaterialDAO.selectMapMaterial(mapMaterial);
		return mtm;
	}

	/**
	 * 지식맵(지식유형) 정보를 신규로 등록한다.
	 * 
	 * @param konTypeNm     - 지식맵(지식유형) model
	 *
	 * @param MapMaterialVO
	 */
	@Override
	public void insertMapMaterial(MapMaterial mapMaterial) throws Exception {
		mapMaterialDAO.insertMapMaterial(mapMaterial);
	}

	/**
	 * 기 등록 된 지식맵(지식유형)링 정보를 수정 한다.
	 * 
	 * @param konTypeNm     - 지식맵(지식유형) model
	 *
	 * @param MapMaterialVO
	 */
	@Override
	public void updateMapMaterial(MapMaterial mapMaterial) throws Exception {
		mapMaterialDAO.updateMapMaterial(mapMaterial);
	}

	/**
	 * 기 등록된 지식맵(지식유형) 정보를 삭제한다.
	 * 
	 * @param konTypeNm     - 지식맵(지식유형) model
	 *
	 * @param MapMaterialVO
	 */
	@Override
	public void deleteMapMaterial(MapMaterial mapMaterial) throws Exception {
		mapMaterialDAO.deleteMapMaterial(mapMaterial);
	}

	/**
	 * 지식유형코드 중복 여부 체크(위치 : 1260.지식맵관리(유형) > 등록)
	 * 
	 * @param knoTypeCd
	 * @return 중복여부
	 * @throws Exception
	 */
	@Override
	public int knoTypeCdCheck(String knoTypeCd) throws Exception {
		return mapMaterialDAO.knoTypeCdCheck(knoTypeCd);
	}
}