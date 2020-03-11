package egovframework.com.uss.olp.opm.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.olp.opm.service.OnlinePollItem;
import egovframework.com.uss.olp.opm.service.OnlinePollManage;

import org.springframework.stereotype.Repository;

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
 *
 * </pre>
 */
@Repository("onlinePollManageDao")
public class OnlinePollManageDao extends EgovComAbstractDAO {

    /**
     * 온라인POLL관리를(을) 목록을 한다.
     * @param onlinePollVO  온라인POLL관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    public List<?> selectOnlinePollManageList(ComDefaultVO searchVO) throws Exception {
        return list("OnlinePollManage.selectOnlinePollManage", searchVO);
    }

    /**
     * 온라인POLL관리를(을) 상세조회 한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    public OnlinePollManage selectOnlinePollManageDetail(OnlinePollManage onlinePollManage) throws Exception {
        return (OnlinePollManage)selectOne("OnlinePollManage.selectOnlinePollManageDetail", onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @return int
     * @throws Exception
     */
    public int selectOnlinePollManageListCnt(ComDefaultVO searchVO) throws Exception {
        return (Integer)selectOne("OnlinePollManage.selectOnlinePollManageCnt", searchVO);
    }

    /**
     * 온라인POLL관리를(을) 등록한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    public void insertOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception {
        insert("OnlinePollManage.insertOnlinePollManage", onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 수정한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    public void updateOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception {
        update("OnlinePollManage.updateOnlinePollManage", onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 삭제한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    public void deleteOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception {
        //온라인POLL 결과 정보 삭제
        delete("OnlinePollManage.deleteOnlinePollResultAll", onlinePollManage);
        //온라인POLL 항목 정보 삭제
        delete("OnlinePollManage.deleteOnlinePollItemAll", onlinePollManage);
        //온라인POLL 관리 정보 삭제
        delete("OnlinePollManage.deleteOnlinePollManage", onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 통계를 조회 한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    public List<?> selectOnlinePollManageStatistics(OnlinePollManage onlinePollManage) throws Exception {
        return list("OnlinePollManage.selectOnlinePollManageDetail", onlinePollManage);
    }

    /**
     * 온라인POLL항목를(을) 조회한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    public List<?> selectOnlinePollItemList(OnlinePollItem onlinePollItem) throws Exception {
        return list("OnlinePollManage.selectOnlinePollItem", onlinePollItem);
    }

    /**
     * 온라인POLL항목를(을) 등록한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    public void insertOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception {
        insert("OnlinePollManage.insertOnlinePollItem", onlinePollItem);
    }

    /**
     * 온라인POLL항목를(을) 수정한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    public void updateOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception {
        update("OnlinePollManage.updateOnlinePollIteme", onlinePollItem);
    }

    /**
     * 온라인POLL항목를(을) 삭제한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    public void deleteOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception {
        //온라인POLL 결과 삭제
        delete("OnlinePollManage.deleteOnlinePollResultIemid", onlinePollItem);
        //온라인POLL 항목 삭제
        delete("OnlinePollManage.deleteOnlinePollItem", onlinePollItem);
    }
}
