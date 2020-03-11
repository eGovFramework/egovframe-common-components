package egovframework.com.uss.sam.cpy.service;

import java.util.List;

/**
 *
 * 저작권보호정책내용을 처리하는 서비스 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
public interface EgovCpyrhtPrtcPolicyService {

    /**
	 * 저작권보호정책내용 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
	CpyrhtPrtcPolicyVO selectCpyrhtPrtcPolicyDetail(CpyrhtPrtcPolicyVO vo) throws Exception;

    /**
	 * 저작권보호정책내용 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    List<?> selectCpyrhtPrtcPolicyList(CpyrhtPrtcPolicyDefaultVO searchVO) throws Exception;

    /**
	 * 저작권보호정책내용 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    int selectCpyrhtPrtcPolicyListTotCnt(CpyrhtPrtcPolicyDefaultVO searchVO);

	/**
	 * 저작권보호정책내용 글을 등록한다.
	 * @param vo
	 * @exception Exception
	 */
    void insertCpyrhtPrtcPolicyCn(CpyrhtPrtcPolicyVO vo) throws Exception;


	/**
	 * 저작권보호정책내용 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    void updateCpyrhtPrtcPolicyCn(CpyrhtPrtcPolicyVO vo) throws Exception;

	/**
	 * 저작권보호정책내용 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    void deleteCpyrhtPrtcPolicyCn(CpyrhtPrtcPolicyVO vo) throws Exception;


}
