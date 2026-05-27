package egovframework.com.cop.stf.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.bbs.service.Satisfaction;
import egovframework.com.cop.bbs.service.SatisfactionVO;

/**
 * 만족도조사를 위한 Mapper 인터페이스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.29  한성곤          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("BBSSatisfactionDAO")
public interface BBSSatisfactionMapper {

    /**
     * 만족도조사에 대한 목록을 조회 한다.
     *
     * @param satisfactionVO
     * @return
     */
    List<SatisfactionVO> selectSatisfactionList(SatisfactionVO satisfactionVO);

    /**
     * 만족도조사에 대한 목록 건수를 조회 한다.
     *
     * @param satisfactionVO
     * @return
     */
    int selectSatisfactionListCnt(SatisfactionVO satisfactionVO);

    /**
     * 만족도조사를 등록한다.
     *
     * @param satisfaction
     */
    void insertSatisfaction(Satisfaction satisfaction);

    /**
     * 만족도조사를 삭제한다.
     *
     * @param satisfactionVO
     */
    void deleteSatisfaction(SatisfactionVO satisfactionVO);

    /**
     * 만족도조사에 대한 내용을 조회한다.
     *
     * @param satisfactionVO
     * @return
     */
    Satisfaction selectSatisfaction(SatisfactionVO satisfactionVO);

    /**
     * 만족도조사에 대한 내용을 수정한다.
     *
     * @param satisfaction
     */
    void updateSatisfaction(Satisfaction satisfaction);

    /**
     * 만족도조사에 대한 패스워드를 조회 한다.
     *
     * @param satisfaction
     * @return
     */
    String getSatisfactionPassword(Satisfaction satisfaction);

    /**
     * 만족도 전체 점수를 제공한다.
     *
     * @param satisfactionVO
     * @return
     */
    Float getSummary(SatisfactionVO satisfactionVO);
}
