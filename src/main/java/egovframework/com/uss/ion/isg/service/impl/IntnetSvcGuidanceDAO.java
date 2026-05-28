/**
 * 개요
 * - 인터넷서비스안내에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 인터넷서비스안내에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 인터넷서비스안내의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.03  lee.m.j         최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 *
 * @author lee.m.j
 * @version 1.0
 */

package egovframework.com.uss.ion.isg.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.isg.service.IntnetSvcGuidanceVO;
import jakarta.annotation.Resource;

@Repository("intnetSvcGuidanceDAO")
public class IntnetSvcGuidanceDAO {

	@Resource(name = "intnetSvcGuidanceMapper")
	private IntnetSvcGuidanceMapper mapper;

	/**
	 * 인터넷서비스안내정보를 관리하기 위해 등록된 인터넷서비스안내 목록을 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return List - 인터넷서비스안내 목록
	 */
	public List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceList(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return mapper.selectIntnetSvcGuidanceList(intnetSvcGuidanceVO);
	}

	/**
	 * 인터넷서비스안내목록 총 개수를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return int
	 */
	public int selectIntnetSvcGuidanceListTotCnt(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return mapper.selectIntnetSvcGuidanceListTotCnt(intnetSvcGuidanceVO);
	}

	/**
	 * 등록된 인터넷서비스안내의 상세정보를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return IntnetSvcGuidanceVO - 인터넷서비스안내 VO
	 */
	public IntnetSvcGuidanceVO selectIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return mapper.selectIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 인터넷서비스안내정보를 신규로 등록한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 */
	public void insertIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		mapper.insertIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 수정한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 */
	public void updateIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		mapper.updateIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 삭제한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 */
	public void deleteIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		mapper.deleteIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 인터넷서비스안내정보 적용결과를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return List - 인터넷서비스안내 목록
	 */
	public List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceResult(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return mapper.selectIntnetSvcGuidanceResult(intnetSvcGuidanceVO);
	}
}
