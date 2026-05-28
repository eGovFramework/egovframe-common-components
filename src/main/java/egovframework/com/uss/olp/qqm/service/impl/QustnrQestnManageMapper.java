package egovframework.com.uss.olp.qqm.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.qqm.service.QustnrQestnManageVO;

/**
 * 설문문항을 처리하는 Mapper 인터페이스
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *
 * </pre>
 */
@EgovMapper("qustnrQestnManageMapper")
public interface QustnrQestnManageMapper {

    /**
     * 설문조사 응답자답변내용결과/기타답변내용결과 통계를 조회한다.
     * @param map - 설문지 정보가 담김 Parameter
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrManageStatistics2(Map<?, ?> map) throws Exception;

    /**
     * 설문조사 통계를 조회한다.
     * @param map - 설문지 정보가 담김 Parameter
     * @return List
     * @throws Exception
     */
    List<?> selectQustnrManageStatistics(Map<?, ?> map) throws Exception;

    /**
     * 설문지정보 설문제목을 조회한다.
     * @param map - 설문지 정보가 담김 Parameter
     * @return Map
     * @throws Exception
     */
    Map<?, ?> selectQustnrManageQestnrSj(Map<?, ?> map) throws Exception;

    /**
     * 설문문항 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    List<?> selectQustnrQestnManage(ComDefaultVO searchVO) throws Exception;

    /**
     * 설문문항를(을) 상세조회 한다.
     * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrQestnManageDetail(QustnrQestnManageVO qustnrQestnManageVO) throws Exception;

    /**
     * 설문문항를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    int selectQustnrQestnManageCnt(ComDefaultVO searchVO) throws Exception;

    /**
     * 설문문항를(을) 등록한다.
     * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
     * @throws Exception
     */
    void insertQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception;

    /**
     * 설문문항를(을) 수정한다.
     * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
     * @throws Exception
     */
    void updateQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception;

    /**
     * 설문조사(설문결과)를 삭제한다.
     * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
     * @throws Exception
     */
    void deleteQustnrRespondInfo(QustnrQestnManageVO qustnrQestnManageVO) throws Exception;

    /**
     * 설문항목을 삭제한다.
     * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
     * @throws Exception
     */
    void deleteQustnrItemManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception;

    /**
     * 설문문항를(을) 삭제한다.
     * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
     * @throws Exception
     */
    void deleteQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception;

}
