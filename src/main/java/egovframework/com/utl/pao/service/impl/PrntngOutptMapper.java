package egovframework.com.utl.pao.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.utl.pao.service.PrntngOutptVO;

/**
 * 인쇄출력(전자관인) MyBatis 매퍼 인터페이스
 *
 * @author 공통서비스 개발팀 이중호
 * @since 2009.02.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.01  이중호          최초 생성
 *
 * </pre>
 */
@EgovMapper("prntngOutptMapper")
public interface PrntngOutptMapper {

    /**
     * 주어진 조건에 따른 전자관인 정보를 조회한다.
     *
     * @param vo 조회 조건
     * @return PrntngOutptVO
     * @throws Exception
     */
    PrntngOutptVO selectErncsl(PrntngOutptVO vo) throws Exception;

}
