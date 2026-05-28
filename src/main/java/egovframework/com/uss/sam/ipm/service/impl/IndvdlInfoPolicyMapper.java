package egovframework.com.uss.sam.ipm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.sam.ipm.service.IndvdlInfoPolicy;

/**
 * 개인정보보호정책에 대한 데이터 접근 Mapper 인터페이스를 정의한다.
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
 *   2026.05.28     dasomel     XML 기반 DAO → @EgovMapper 인터페이스로 전환
 *
 *      </pre>
 */
@EgovMapper("indvdlInfoPolicyMapper")
public interface IndvdlInfoPolicyMapper {

	List<EgovMap> selectIndvdlInfoPolicy(ComDefaultVO searchVO);

	int selectIndvdlInfoPolicyCnt(ComDefaultVO searchVO);

	IndvdlInfoPolicy selectIndvdlInfoPolicyDetail(IndvdlInfoPolicy indvdlInfoPolicy);

	void insertIndvdlInfoPolicy(IndvdlInfoPolicy indvdlInfoPolicy);

	void updateIndvdlInfoPolicy(IndvdlInfoPolicy indvdlInfoPolicy);

	void deleteIndvdlInfoPolicy(IndvdlInfoPolicy indvdlInfoPolicy);

}
