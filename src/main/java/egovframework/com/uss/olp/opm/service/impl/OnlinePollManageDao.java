package egovframework.com.uss.olp.opm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.opm.service.OnlinePollItem;
import egovframework.com.uss.olp.opm.service.OnlinePollManage;
import jakarta.annotation.Resource;

/**
 * 온라인POLL관리를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@Repository("onlinePollManageDao")
public class OnlinePollManageDao {

    @Resource(name = "onlinePollManageMapper")
    private OnlinePollManageMapper onlinePollManageMapper;

    /**
     * 온라인POLL관리를(을) 목록을 한다.
     * @param searchVO  온라인POLL관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectOnlinePollManageList(ComDefaultVO searchVO) throws Exception {
        return onlinePollManageMapper.selectOnlinePollManage(searchVO);
    }

    /**
     * 온라인POLL관리를(을) 상세조회 한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @return OnlinePollManage
     * @throws Exception
     */
    public OnlinePollManage selectOnlinePollManageDetail(OnlinePollManage onlinePollManage) throws Exception {
        return onlinePollManageMapper.selectOnlinePollManageDetail(onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO 온라인POLL관리 정보가 담김 VO
     * @return int
     * @throws Exception
     */
    public int selectOnlinePollManageListCnt(ComDefaultVO searchVO) throws Exception {
        return onlinePollManageMapper.selectOnlinePollManageCnt(searchVO);
    }

    /**
     * 온라인POLL관리를(을) 등록한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    public void insertOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception {
        onlinePollManageMapper.insertOnlinePollManage(onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 수정한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    public void updateOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception {
        onlinePollManageMapper.updateOnlinePollManage(onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 삭제한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    public void deleteOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception {
        //온라인POLL 결과 정보 삭제
        onlinePollManageMapper.deleteOnlinePollResultAll(onlinePollManage);
        //온라인POLL 항목 정보 삭제
        onlinePollManageMapper.deleteOnlinePollItemAll(onlinePollManage);
        //온라인POLL 관리 정보 삭제
        onlinePollManageMapper.deleteOnlinePollManage(onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 통계를 조회 한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    public List<OnlinePollManage> selectOnlinePollManageStatistics(OnlinePollManage onlinePollManage) throws Exception {
        return onlinePollManageMapper.selectOnlinePollManageStatistics(onlinePollManage);
    }

    /**
     * 온라인POLL항목를(을) 조회한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectOnlinePollItemList(OnlinePollItem onlinePollItem) throws Exception {
        return onlinePollManageMapper.selectOnlinePollItem(onlinePollItem);
    }

    /**
     * 온라인POLL항목를(을) 등록한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    public void insertOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception {
        onlinePollManageMapper.insertOnlinePollItem(onlinePollItem);
    }

    /**
     * 온라인POLL항목를(을) 수정한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    public void updateOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception {
        onlinePollManageMapper.updateOnlinePollIteme(onlinePollItem);
    }

    /**
     * 온라인POLL항목를(을) 삭제한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    public void deleteOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception {
        //온라인POLL 결과 삭제
        onlinePollManageMapper.deleteOnlinePollResultIemid(onlinePollItem);
        //온라인POLL 항목 삭제
        onlinePollManageMapper.deleteOnlinePollItem(onlinePollItem);
    }
}
