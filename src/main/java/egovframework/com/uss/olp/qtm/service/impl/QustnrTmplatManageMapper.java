package egovframework.com.uss.olp.qtm.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.qtm.service.QustnrTmplatManageVO;

/**
 * 설문템플릿 Mapper 인터페이스
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
@EgovMapper("qustnrTmplatManageMapper")
public interface QustnrTmplatManageMapper {

    /**
     * 템플릿파일명을 조회한다.
     * @param qustnrTmplatManageVO - 조회할 정보가 담긴 VO
     * @return Map
     */
    Map<?, ?> selectQustnrTmplatManageTmplatImagepathnm(QustnrTmplatManageVO qustnrTmplatManageVO);

    /**
     * 설문템플릿 목록을 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     */
    List<EgovMap> selectQustnrTmplatManage(ComDefaultVO searchVO);

    /**
     * 설문템플릿를(을) 상세조회 한다.
     * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
     * @return List
     */
    List<EgovMap> selectQustnrTmplatManageDetail(QustnrTmplatManageVO qustnrTmplatManageVO);

    /**
     * 설문템플릿를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return int
     */
    int selectQustnrTmplatManageCnt(ComDefaultVO searchVO);

    /**
     * 설문템플릿를(을) 등록한다.
     * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
     */
    void insertQustnrTmplatManage(QustnrTmplatManageVO qustnrTmplatManageVO);

    /**
     * 설문템플릿를(을) 수정한다.
     * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
     */
    void updateQustnrTmplatManage(QustnrTmplatManageVO qustnrTmplatManageVO);

    /**
     * 설문응답자를 삭제한다.
     * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
     */
    void deleteQustnrRespondManage(QustnrTmplatManageVO qustnrTmplatManageVO);

    /**
     * 설문조사(설문결과)를 삭제한다.
     * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
     */
    void deleteQustnrRespondInfo(QustnrTmplatManageVO qustnrTmplatManageVO);

    /**
     * 설문항목을 삭제한다.
     * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
     */
    void deleteQustnrItemManage(QustnrTmplatManageVO qustnrTmplatManageVO);

    /**
     * 설문문항을 삭제한다.
     * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
     */
    void deleteQustnrQestnManage(QustnrTmplatManageVO qustnrTmplatManageVO);

    /**
     * 설문관리를 삭제한다.
     * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
     */
    void deleteQustnrManage(QustnrTmplatManageVO qustnrTmplatManageVO);

    /**
     * 설문템플릿를(을) 삭제한다.
     * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
     */
    void deleteQustnrTmplatManage(QustnrTmplatManageVO qustnrTmplatManageVO);

}
