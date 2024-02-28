package egovframework.com.uss.sam.cpy.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.sam.cpy.service.CpyrhtPrtcPolicyDefaultVO;
import egovframework.com.uss.sam.cpy.service.CpyrhtPrtcPolicyVO;

/**
 *
 * 저작권보호정책내용을 처리하는 비즈니스 구현 클래스
 * 
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자       수정내용
 *  -----------    --------    ---------------------------
 *   2009.04.01     박정규       최초 생성
 *
 *      </pre>
 */
@Repository("CpyrhtPrtcPolicyDAO")
public class CpyrhtPrtcPolicyDAO extends EgovComAbstractDAO {

	/**
	 * 저작권보호정책 글 목록에 대한 상세내용을 조회한다.
	 * 
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
	public CpyrhtPrtcPolicyVO selectCpyrhtPrtcPolicyDetail(CpyrhtPrtcPolicyVO vo) throws Exception {

		return (CpyrhtPrtcPolicyVO) selectOne("CpyrhtPrtcPolicyDAO.selectCpyrhtPrtcPolicyDetail", vo);

	}

	/**
	 * 저작권보호정책 글 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
	public List<EgovMap> selectCpyrhtPrtcPolicyList(CpyrhtPrtcPolicyDefaultVO searchVO) throws Exception {

		return selectList("CpyrhtPrtcPolicyDAO.selectCpyrhtPrtcPolicyList", searchVO);

	}

	/**
	 * 저작권보호정책 글 총 개수를 조회한다.
	 * 
	 * @param searchVO
	 * @return 글 총 개수
	 */
	public int selectCpyrhtPrtcPolicyListTotCnt(CpyrhtPrtcPolicyDefaultVO searchVO) {

		return (Integer) selectOne("CpyrhtPrtcPolicyDAO.selectCpyrhtPrtcPolicyListTotCnt", searchVO);

	}

	/**
	 * 저작권보호정책 글을 등록한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void insertCpyrhtPrtcPolicyCn(CpyrhtPrtcPolicyVO vo) throws Exception {

		insert("CpyrhtPrtcPolicyDAO.insertCpyrhtPrtcPolicyCn", vo);

	}

	/**
	 * 저작권보호정책 글을 수정한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void updateCpyrhtPrtcPolicyCn(CpyrhtPrtcPolicyVO vo) throws Exception {

		update("CpyrhtPrtcPolicyDAO.updateCpyrhtPrtcPolicyCn", vo);

	}

	/**
	 * 저작권보호정책 글을 삭제한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void deleteCpyrhtPrtcPolicyCn(CpyrhtPrtcPolicyVO vo) throws Exception {

		delete("CpyrhtPrtcPolicyDAO.deleteCpyrhtPrtcPolicyCn", vo);

	}

}
