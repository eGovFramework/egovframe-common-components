package egovframework.com.uss.olp.opp.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 온라인POLL참여를 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2011.10.27  서준식          온라인 POLL 중복 투표 방지 기능 추가
 *
 * </pre>
 */
public interface EgovOnlinePollPartcptnService {

	/**
	 * 온라인POLL관리를(을) 목록을 한다.
	 * @param OnlinePoll 회정정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectOnlinePollManageList(ComDefaultVO searchVO) throws Exception;

	/**
	 * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO  조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectOnlinePollManageListCnt(ComDefaultVO searchVO) throws Exception;

	/**
	 *  온라인POLL관리를(을) 상세조회 한다.
	 * @param onlinePollPartcptn 회정정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectOnlinePollManageDetail(OnlinePollPartcptn onlinePollPartcptn) throws Exception;

	/**
	 *  온라인POLL항목를(을) 상세조회 한다.
	 * @param OnlinePoll 회정정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectOnlinePollItemDetail(OnlinePollPartcptn onlinePollPartcptn) throws Exception;

	/**
	 * 온라인POLL참여를(을) 등록한다.
	 * @param onlinePollPartcptn  온라인POLL결과 정보가 담김 VO
	 * @throws Exception
	 */
	public void insertOnlinePollResult(OnlinePollPartcptn onlinePollPartcptn) throws Exception;

	/**
	 * 온라인POLL참여 통계를 조회한다.
	 * @param onlinePollPartcptn 회정정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectOnlinePollManageStatistics(OnlinePollPartcptn onlinePollPartcptn) throws Exception;

	/**
	 * 온라인POLL참여 여부를 조회한다.
	 * @param onlinePollPartcptn 회정정보가 담김 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectOnlinePollResult(OnlinePollPartcptn onlinePollPartcptn) throws Exception;
}
