package egovframework.com.uss.sam.ipm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.sam.ipm.service.IndvdlInfoPolicy;

/**
 * 개인정보보호정책를 처리하는 Dao Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see
 * 
 *      <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일          수정자       수정내용
 *  -----------    --------    ---------------------------
 *   2009.07.03     장동한       최초 생성
 *
 *      </pre>
 */
@Repository("onlineIndvdlInfoPolicyDao")
public class IndvdlInfoPolicyDao extends EgovComAbstractDAO {

	/**
	 * 개인정보보호정책를(을) 목록을 한다.
	 * 
	 * @param searchVO 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectIndvdlInfoPolicyList(ComDefaultVO searchVO) throws Exception {
		return selectList("IndvdlInfoPolicy.selectIndvdlInfoPolicy", searchVO);
	}

	/**
	 * 개인정보보호정책를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectIndvdlInfoPolicyListCnt(ComDefaultVO searchVO) throws Exception {
		return (Integer) selectOne("IndvdlInfoPolicy.selectIndvdlInfoPolicyCnt", searchVO);
	}

	/**
	 * 개인정보보호정책를(을) 상세조회 한다.
	 * 
	 * @param indvdlInfoPolicy 개인정보보호정책 정보가 담김 VO
	 * @return IndvdlInfoPolicy
	 * @throws Exception
	 */
	public IndvdlInfoPolicy selectIndvdlInfoPolicyDetail(IndvdlInfoPolicy indvdlInfoPolicy) throws Exception {
		return (IndvdlInfoPolicy) selectOne("IndvdlInfoPolicy.selectIndvdlInfoPolicyDetail", indvdlInfoPolicy);
	}

	/**
	 * 개인정보보호정책를(을) 등록한다.
	 * 
	 * @param qindvdlInfoPolicy 개인정보보호정책 정보가 담김 VO
	 * @throws Exception
	 */
	public void insertIndvdlInfoPolicy(IndvdlInfoPolicy indvdlInfoPolicy) throws Exception {
		insert("IndvdlInfoPolicy.insertIndvdlInfoPolicy", indvdlInfoPolicy);
	}

	/**
	 * 개인정보보호정책를(을) 수정한다.
	 * 
	 * @param indvdlInfoPolicy 개인정보보호정책 정보가 담김 VO
	 * @throws Exception
	 */
	public void updateIndvdlInfoPolicy(IndvdlInfoPolicy indvdlInfoPolicy) throws Exception {
		update("IndvdlInfoPolicy.updateIndvdlInfoPolicy", indvdlInfoPolicy);
	}

	/**
	 * 개인정보보호정책를(을) 삭제한다.
	 * 
	 * @param indvdlInfoPolicy 개인정보보호정책 정보가 담김 VO
	 * @throws Exception
	 */
	public void deleteIndvdlInfoPolicy(IndvdlInfoPolicy indvdlInfoPolicy) throws Exception {
		delete("IndvdlInfoPolicy.deleteIndvdlInfoPolicy", indvdlInfoPolicy);
	}

}
