package egovframework.com.uss.olp.opp.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.opp.service.EgovOnlinePollPartcptnService;
import egovframework.com.uss.olp.opp.service.OnlinePollPartcptn;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 온라인POLL참여를 처리하는 ServiceImpl Class 구현
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
 * </pre>
 */
@Service("egovOnlinePollPartcptnService")
public class EgovOnlinePollPartcptnServiceImpl extends EgovAbstractServiceImpl
        implements EgovOnlinePollPartcptnService {

    @Resource(name = "onlinePollPartcptnDao")
    private OnlinePollPartcptnDao dao;

    @Resource(name = "egovOnlinePollResultIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     *온라인POLL관리를(을) 목록을 한다.
     * @param searchVO 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Override
	public List<?> selectOnlinePollManageList(ComDefaultVO searchVO) throws Exception {
        return dao.selectOnlinePollManageList(searchVO);
    }

    /**
     * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Override
	public int selectOnlinePollManageListCnt(ComDefaultVO searchVO) throws Exception {
        return dao.selectOnlinePollManageListCnt(searchVO);
    }

    /**
     *  온라인POLL관리를(을) 상세조회 한다.
     * @param onlinePollPartcptn  온라인POLL항목 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Override
	public List<?> selectOnlinePollManageDetail( OnlinePollPartcptn onlinePollPartcptn) throws Exception {
        return dao.selectOnlinePollManageDetail(onlinePollPartcptn);
    }

    /**
     *  온라인POLL항목를(을) 상세조회 한다.
     * @param onlinePollPartcptn  온라인POLL 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Override
	public List<?> selectOnlinePollItemDetail( OnlinePollPartcptn onlinePollPartcptn) throws Exception {
        return dao.selectOnlinePollItemDetail(onlinePollPartcptn);
    }

    /**
     * 온라인POLL참여를(을) 등록한다.
     * @param onlinePollPartcptn  온라인POLL 정보가 담김 VO
     * @throws Exception
     */
    @Override
	public void insertOnlinePollResult(OnlinePollPartcptn onlinePollPartcptn)throws Exception {
        String sMakeId = idgenService.getNextStringId();
        onlinePollPartcptn.setPollResultId(sMakeId);
        dao.insertOnlinePollResult(onlinePollPartcptn);
    }

    /**
     * 온라인POLL통계를(을) 목록을 한다.
     * @param onlinePollPartcptn  온라인POLL 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Override
	public List<?> selectOnlinePollManageStatistics(OnlinePollPartcptn onlinePollPartcptn) throws Exception {
        return dao.selectOnlinePollManageStatistics(onlinePollPartcptn);
    }

    /**
     * 온라인POLL참여 여부를 조회한다.
     * @param onlinePollPartcptn 회정정보가 담김 VO
     * @return int
     * @throws Exception
     */
    @Override
	public int selectOnlinePollResult( OnlinePollPartcptn onlinePollPartcptn) throws Exception{
    	return dao.selectOnlinePollResult(onlinePollPartcptn);
    }


}
