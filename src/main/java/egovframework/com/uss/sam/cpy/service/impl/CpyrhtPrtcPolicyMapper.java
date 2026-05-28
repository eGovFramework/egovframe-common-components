package egovframework.com.uss.sam.cpy.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.uss.sam.cpy.service.CpyrhtPrtcPolicyDefaultVO;
import egovframework.com.uss.sam.cpy.service.CpyrhtPrtcPolicyVO;

/**
 * 저작권보호정책을 관리하기 위한 Mapper 인터페이스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일         수정자       수정내용
 *  ----------    --------    ---------------------------
 *   2009.04.01    박정규       최초 생성
 *
 * </pre>
 */
@EgovMapper
public interface CpyrhtPrtcPolicyMapper {

    /**
     * 저작권보호정책 글 목록에 대한 상세내용을 조회한다.
     * @param vo - 저작권보호정책 VO
     * @return 저작권보호정책 상세정보
     */
    CpyrhtPrtcPolicyVO selectCpyrhtPrtcPolicyDetail(CpyrhtPrtcPolicyVO vo);

    /**
     * 저작권보호정책 글 목록을 조회한다.
     * @param searchVO - 검색 VO
     * @return 글 목록
     */
    List<EgovMap> selectCpyrhtPrtcPolicyList(CpyrhtPrtcPolicyDefaultVO searchVO);

    /**
     * 저작권보호정책 글 총 개수를 조회한다.
     * @param searchVO - 검색 VO
     * @return 글 총 개수
     */
    int selectCpyrhtPrtcPolicyListTotCnt(CpyrhtPrtcPolicyDefaultVO searchVO);

    /**
     * 저작권보호정책 글을 등록한다.
     * @param vo - 저작권보호정책 VO
     */
    void insertCpyrhtPrtcPolicyCn(CpyrhtPrtcPolicyVO vo);

    /**
     * 저작권보호정책 글을 수정한다.
     * @param vo - 저작권보호정책 VO
     */
    void updateCpyrhtPrtcPolicyCn(CpyrhtPrtcPolicyVO vo);

    /**
     * 저작권보호정책 글을 삭제한다.
     * @param vo - 저작권보호정책 VO
     */
    void deleteCpyrhtPrtcPolicyCn(CpyrhtPrtcPolicyVO vo);

}
