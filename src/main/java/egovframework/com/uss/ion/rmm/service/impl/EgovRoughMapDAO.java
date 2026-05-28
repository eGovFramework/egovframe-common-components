package egovframework.com.uss.ion.rmm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.rmm.service.RoughMapDefaultVO;
import egovframework.com.uss.ion.rmm.service.RoughMapVO;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 건물 위치정보에 대한 DB상의 접근, 변경을 처리한다.
 *
 * 상세내용
 * - 건물 위치정보에 대한 등록, 수정, 삭제 기능을 제공한다.
 * - 건물 위치정보의 조회기능은 목록, 상세조회로 구분된다.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일         수정자      수정내용
 *  -----------    ------    ---------
 *   2014.08.27    옥찬우      최초 생성
 *   2026.05.28    dasomel    @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 *
 * @author 옥찬우
 * @since 2014.08.27
 * @version 1.0
 */
@Repository("roughMapDAO")
public class EgovRoughMapDAO {

	@Resource(name = "roughMapMapper")
	private EgovRoughMapMapper mapper;

	/**
	 * 약도 목록을 조회한다.
	 * @param searchVO
	 * @return List 건물 위치정보 리스트
	 * @throws Exception
	 */
	public List<EgovMap> selectRoughMapList(RoughMapDefaultVO searchVO) throws Exception {
		return mapper.selectRoughMapList(searchVO);
	}

	/**
	 * 약도 목록의 건수를 조회 한다.
	 * @param searchVO
	 * @return int 건물 위치정보 목록 건수
	 */
	public int selectRoughMapListTotCnt(RoughMapDefaultVO searchVO) {
		return mapper.selectRoughMapListTotCnt(searchVO);
	}

	/**
	 * 약도를 조회한다.
	 * @param roughMapVO
	 * @return RoughMapVO
	 * @throws Exception
	 */
	public RoughMapVO selectRoughMap(RoughMapVO roughMapVO) throws Exception {
		return mapper.selectRoughMapDetail(roughMapVO);
	}

	/**
	 * 약도를 DB에 등록한다.
	 * @param roughMap
	 * @throws Exception
	 */
	public void insertRoughMap(RoughMapVO roughMap) throws Exception {
		mapper.insertRoughMap(roughMap);
	}

	/**
	 * 약도를 DB에서 수정한다.
	 * @param roughMap
	 * @throws Exception
	 */
	public void updateRoughMap(RoughMapVO roughMap) throws Exception {
		mapper.updateRoughMap(roughMap);
	}

	/**
	 * 약도를 DB에서 삭제한다.
	 * @param roughMap
	 * @throws Exception
	 */
	public void deleteRoughMap(RoughMapVO roughMap) throws Exception {
		mapper.deleteRoughMap(roughMap);
	}
}
