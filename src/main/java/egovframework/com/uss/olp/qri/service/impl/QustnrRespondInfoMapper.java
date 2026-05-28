package egovframework.com.uss.olp.qri.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.qri.service.QustnrRespondInfoVO;

/**
 * 설문조사 Mapper 인터페이스
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
@EgovMapper("qustnrRespondInfoMapper")
public interface QustnrRespondInfoMapper {

    /**
     * 설문템플릿을 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    List<?> selectQustnrTmplatManages(Map<?, ?> map) throws Exception;

    /**
     * 설문템플릿 화이트리스트를 조회한다.
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrTmplatWhiteList() throws Exception;

    /**
     * 객관식 통계를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrRespondInfoManageStatistics1(Map<?, ?> map) throws Exception;

    /**
     * 주관식 통계를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrRespondInfoManageStatistics2(Map<?, ?> map) throws Exception;

    /**
     * 회원정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return Map
     * @throws Exception
     */
    Map<?, ?> selectQustnrRespondInfoManageEmplyrinfo(Map<?, ?> map) throws Exception;

    /**
     * 설문정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrRespondInfoManageComtnqestnrinfo(Map<?, ?> map) throws Exception;

    /**
     * 문항정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrRespondInfoManageComtnqustnrqesitm(Map<?, ?> map) throws Exception;

    /**
     * 항목정보를 조회한다.
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrRespondInfoManageComtnqustnriem(Map<?, ?> map) throws Exception;

    /**
     * 설문조사(설문등록)를(을) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     */
    List<EgovMap> selectQustnrRespondInfoManage(ComDefaultVO searchVO);

    /**
     * 설문조사(설문등록)를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    int selectQustnrRespondInfoManageCnt(ComDefaultVO searchVO) throws Exception;

    /**
     * 응답자결과(설문조사) 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrRespondInfo(ComDefaultVO searchVO) throws Exception;

    /**
     * 응답자결과(설문조사)를(을) 상세조회 한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @return List
     * @throws Exception
     */
    List<EgovMap> selectQustnrRespondInfoDetail(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception;

    /**
     * 응답자결과(설문조사)를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    int selectQustnrRespondInfoCnt(ComDefaultVO searchVO) throws Exception;

    /**
     * 응답자결과(설문조사)를(을) 등록한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    void insertQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception;

    /**
     * 응답자결과(설문조사)를(을) 수정한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    void updateQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception;

    /**
     * 응답자결과(설문조사)를(을) 삭제한다.
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    void deleteQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception;

}
