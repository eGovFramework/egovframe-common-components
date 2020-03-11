package egovframework.com.uss.ion.rmm.service;

import java.util.List;

/**
 * 개요
 * - 위치정보연계에 대한 Service Interface를 정의한다.
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

public interface EgovRoughMapService {

    /**
     * 건물의 위치정보 목록을 조회하는 Service interface 메서드
     * @param roughMapVO
     * @return Map<String, Object> 주변건물 위치정보 리스트
     * @throws Exception
    */
    List<?> selectRoughMapList(RoughMapDefaultVO searchVO) throws Exception;

    /**
     * 글 총 갯수를 조회한다
     * @param searchVO
     * @return 총 갯수
     */
    int selectRoughMapListTotCnt(RoughMapDefaultVO searchVO);

    /**
     * 건물의 위치정보를 상세조회하는 Service interface 메서드
     * @param roughMapVO
     * @return RoughMap 주변건물 위치정보
     * @throws Exception
    */
    RoughMapVO selectRoughMapDetail(RoughMapVO roughMapVO) throws Exception;

    /**
     * 건물의 위치정보를 등록하는 Service interface 메서드
     * @param roughMap
     * @throws Exception
    */
    void insertRoughMap(RoughMapVO roughMap) throws Exception;

    /**
     * 건물의 위치정보를 수정하는 Service interface 메서드
     * @param roughMap
     * @throws Exception
    */
    void updateRoughMap(RoughMapVO roughMap) throws Exception;

    /**
     * 건물의 위치정보를 삭제하는 Service interface 메서드
     * @param roughMap
     * @throws Exception
    */
    void deleteRoughMap(RoughMapVO roughMap) throws Exception;

}