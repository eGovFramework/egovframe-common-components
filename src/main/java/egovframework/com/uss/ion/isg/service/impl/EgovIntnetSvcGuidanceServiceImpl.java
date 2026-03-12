/**
 * 개요
 * - 인터넷서비스안내에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 인터넷서비스안내에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 인터넷서비스안내의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:03
 */

package egovframework.com.uss.ion.isg.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.isg.service.EgovIntnetSvcGuidanceService;
import egovframework.com.uss.ion.isg.service.IntnetSvcGuidanceVO;
import jakarta.annotation.Resource;

@Service("egovIntnetSvcGuidanceService")
public class EgovIntnetSvcGuidanceServiceImpl extends EgovAbstractServiceImpl implements EgovIntnetSvcGuidanceService {

	@Resource(name="intnetSvcGuidanceDAO")
	private IntnetSvcGuidanceDAO intnetSvcGuidanceDAO;

	/**
	 * 인터넷서비스안내정보를 관리하기 위해 등록된 인터넷서비스안내 목록을 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return List - 인터넷서비스안내 목록
	 */
	@Override
	public List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceList(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception{
		return intnetSvcGuidanceDAO.selectIntnetSvcGuidanceList(intnetSvcGuidanceVO);
	}

	/**
	 * 인터넷서비스안내목록 총 개수를 조회한다.
	 * @param mainImageVO - 인터넷서비스안내 VO
	 * @return int
	 */
	@Override
	public int selectIntnetSvcGuidanceListTotCnt(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return intnetSvcGuidanceDAO.selectIntnetSvcGuidanceListTotCnt(intnetSvcGuidanceVO);
	}

	/**
	 * 등록된 인터넷서비스안내의 상세정보를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 Vo
	 * @return IntnetSvcGuidanceVO - 인터넷서비스안내 Vo
	 */
	@Override
	public IntnetSvcGuidanceVO selectIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return intnetSvcGuidanceDAO.selectIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 인터넷서비스안내정보를 신규로 등록한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 */
	@Override
	public IntnetSvcGuidanceVO insertIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		intnetSvcGuidanceDAO.insertIntnetSvcGuidance(intnetSvcGuidanceVO);
		return selectIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 수정한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 */
	@Override
	public void updateIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		intnetSvcGuidanceDAO.updateIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 기 등록된 인터넷서비스안내정보를 삭제한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 */
	@Override
	public void deleteIntnetSvcGuidance(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception{
		intnetSvcGuidanceDAO.deleteIntnetSvcGuidance(intnetSvcGuidanceVO);
	}

	/**
	 * 인터넷서비스안내정보 적용결과를 조회한다.
	 * @param intnetSvcGuidanceVO - 인터넷서비스안내 VO
	 * @return List - 인터넷서비스안내 목록
	 */
	@Override
	public List<IntnetSvcGuidanceVO> selectIntnetSvcGuidanceResult(IntnetSvcGuidanceVO intnetSvcGuidanceVO) throws Exception {
		return intnetSvcGuidanceDAO.selectIntnetSvcGuidanceResult(intnetSvcGuidanceVO);
	}
}
