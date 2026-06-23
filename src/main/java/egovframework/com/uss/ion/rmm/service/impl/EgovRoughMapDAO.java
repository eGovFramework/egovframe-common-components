package egovframework.com.uss.ion.rmm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.rmm.service.RoughMapDefaultVO;
import egovframework.com.uss.ion.rmm.service.RoughMapVO;

/**
 * 개요
 * - 건물 위치정보에 대한 DB상의 접근, 변경을 처리한다.
 *
 * 상세내용
 * - 건물 위치정보에 대한 등록, 수정, 삭제 기능을 제공한다.
 * - 건물 위치정보의 조회기능은 목록, 상세조회로 구분된다.
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
 *   2026.06.18		이백행		[2026년 컨트리뷰션] 불필요한 예외(throws Exception) 제거
 *
 * </pre>
 */

@Repository("roughMapDAO")
public class EgovRoughMapDAO extends EgovComAbstractDAO {

    /**
     * 약도 목록을 조회한다.
     * @param roughMapVO
     * @return List<RoughMapVO> 건물 위치정보 리스트
    */
	public List<EgovMap> selectRoughMapList(RoughMapDefaultVO searchVO) {
        return selectList("RoughMapDAO.selectRoughMapList", searchVO);
    }

    /**
     * 약도 목록의 건수를 조회 한다.
     * @param roughMapVO
     * @return int 건물 위치정보 목록 건수
    */
    public int selectRoughMapListTotCnt(RoughMapDefaultVO searchVO){
        return (Integer)selectOne("RoughMapDAO.selectRoughMapListTotCnt", searchVO);
    }

    /**
     * 약도를 조회한다.
     * @param roughMapVO
     * @return RoughMap
    */
    public RoughMapVO selectRoughMap(RoughMapVO roughMapVO) {
        return (RoughMapVO)selectOne("RoughMapDAO.selectRoughMapDetail", roughMapVO);
    }

    /**
     * 약도를 DB에 등록한다.
     *
     * @param roughMap
     */
    public void insertRoughMap(RoughMapVO roughMap) {
        insert("RoughMapDAO.insertRoughMap", roughMap);
    }

    /**
     * 약도를 DB에서 수정한다.
     *
     * @param roughMap
     */
    public void updateRoughMap(RoughMapVO roughMap) {
            update("RoughMapDAO.updateRoughMap", roughMap);
    }

    /**
     * 약도를 DB에서 삭제한다.
     *
     * @param roughMap
     */
    public void deleteRoughMap(RoughMapVO roughMap) {
            delete("RoughMapDAO.deleteRoughMap", roughMap);
    }
}