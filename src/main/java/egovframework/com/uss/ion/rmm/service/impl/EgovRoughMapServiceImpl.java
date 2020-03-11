package egovframework.com.uss.ion.rmm.service.impl;

import java.util.List;

import egovframework.com.uss.ion.rmm.service.EgovRoughMapService;
import egovframework.com.uss.ion.rmm.service.RoughMapDefaultVO;
import egovframework.com.uss.ion.rmm.service.RoughMapVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 위치정보연계에 대한 Service Interface를 구현한다.
 *
 * 상세내용
 * - 건물의 위치정보에 대한 등록, 수정, 삭제, 상세조회 기능을 제공한다.
 * - 건물의 위치정보의 조회기능은 목록, 상세조회로 구분된다.
 *
 * @author 옥찬우
 * @since 2014.08.27
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일			수정자		수정내용
 *  -----------		------		---------
 *   2014.08.27		옥찬우		최초 생성
 *
 * </pre>
 */

@Service("EgovRoughMapService")
public class EgovRoughMapServiceImpl extends EgovAbstractServiceImpl implements EgovRoughMapService {

    /** RoughMapDAO */
    @Resource(name="roughMapDAO")
    private EgovRoughMapDAO roughMapDAO;

    /** ID Generation */
    @Resource(name="egovRoughMapIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     * 건물의 위치정보 목록을 조회한다.
     * @param roughMapVO
     * @return Map<String, Object> 건물 위치정보 조회결과 리스트, 조회건수
     * @throws Exception
    */
    @Override
	public List<?> selectRoughMapList(RoughMapDefaultVO searchVO) throws Exception {
    	return roughMapDAO.selectRoughMapList(searchVO);
    }

	@Override
	public int selectRoughMapListTotCnt(RoughMapDefaultVO searchVO) {
		return roughMapDAO.selectRoughMapListTotCnt(searchVO);
	}

    /**
     * 건물의 위치정보를 조회한다.
     *
     * @param roughMapVO
     * @return Geolocation 건물의 위치정보
     * @throws Exception
    */
    @Override
	public RoughMapVO selectRoughMapDetail(RoughMapVO roughMapVO) throws Exception {
        return roughMapDAO.selectRoughMap(roughMapVO);
    }

    /**
     * 건물의 위치정보를 DB에 등록한다.
     * @param roughMap
     * @throws Exception
    */
    @Override
	public void insertRoughMap(RoughMapVO roughMap) throws Exception {
    	String roughMapId = idgenService.getNextStringId();

        roughMap.setRoughMapId(roughMapId);
        roughMapDAO.insertRoughMap(roughMap);
    }

    /**
     * 건물의 위치정보를 수정한다.
     * @param roughMap
     * @throws Exception
    */
    @Override
	public void updateRoughMap(RoughMapVO roughMap) throws Exception {
        roughMapDAO.updateRoughMap(roughMap);
    }

    /**
     * 건물의 위치정보를 삭제한다.
     * @param roughMap
     * @throws Exception
    */
    @Override
	public void deleteRoughMap(RoughMapVO roughMap) throws Exception {
        roughMapDAO.deleteRoughMap(roughMap);
    }
}
