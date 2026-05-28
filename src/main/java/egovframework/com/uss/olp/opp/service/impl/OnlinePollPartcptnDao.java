package egovframework.com.uss.olp.opp.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.opp.service.OnlinePollPartcptn;
import jakarta.annotation.Resource;

/**
 * 온라인POLL참여를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2011.10.27  서준식          온라인 POLL 중복 투표 방지 기능 추가
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@Repository("onlinePollPartcptnDao")
public class OnlinePollPartcptnDao {

    @Resource(name = "onlinePollPartcptnMapper")
    private OnlinePollPartcptnMapper onlinePollPartcptnMapper;

    /**
     * 온라인POLL관리를(을) 목록을 한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectOnlinePollManageList(ComDefaultVO searchVO) throws Exception {
        return onlinePollPartcptnMapper.selectOnlinePollManageList(searchVO);
    }

    /**
     * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectOnlinePollManageListCnt(ComDefaultVO searchVO) throws Exception {
        return onlinePollPartcptnMapper.selectOnlinePollManageListCnt(searchVO);
    }

    /**
     * 온라인POLL관리를(을) 상세조회 한다.
     * @param onlinePollPartcptn  온라인POLL 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectOnlinePollManageDetail(OnlinePollPartcptn onlinePollPartcptn) throws Exception {
        return onlinePollPartcptnMapper.selectOnlinePollManageDetail(onlinePollPartcptn);
    }

    /**
     * 온라인POLL항목를(을) 상세조회 한다.
     * @param onlinePollPartcptn  온라인POLL 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectOnlinePollItemDetail(OnlinePollPartcptn onlinePollPartcptn) throws Exception {
        return onlinePollPartcptnMapper.selectOnlinePollItem(onlinePollPartcptn);
    }

    /**
     * 온라인POLL참여를(을) 등록한다.
     * @param onlinePollPartcptn  온라인POLL 정보가 담김 VO
     * @throws Exception
     */
    public void insertOnlinePollResult(OnlinePollPartcptn onlinePollPartcptn) throws Exception {
        onlinePollPartcptnMapper.insertOnlinePollResult(onlinePollPartcptn);
    }

    /**
     * 온라인POLL통계를(을) 조회한다.
     * @param onlinePollPartcptn  온라인POLL 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectOnlinePollManageStatistics(OnlinePollPartcptn onlinePollPartcptn) throws Exception {
        return onlinePollPartcptnMapper.selectOnlinePollPartcptnStatistics(onlinePollPartcptn);
    }

    /**
     * 온라인POLL참여 여부를 조회한다.
     * @param onlinePollPartcptn 회정정보가 담김 VO
     * @return int
     * @throws Exception
     */
    public int selectOnlinePollResult(OnlinePollPartcptn onlinePollPartcptn) throws Exception {
        return onlinePollPartcptnMapper.selectOnlinePollResult(onlinePollPartcptn);
    }

}
