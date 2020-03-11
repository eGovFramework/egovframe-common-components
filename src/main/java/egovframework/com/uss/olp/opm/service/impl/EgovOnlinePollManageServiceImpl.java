package egovframework.com.uss.olp.opm.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.opm.service.EgovOnlinePollManageService;
import egovframework.com.uss.olp.opm.service.OnlinePollItem;
import egovframework.com.uss.olp.opm.service.OnlinePollManage;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 온라인POLL관리를 처리하는 ServiceImpl Class 구현
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
@Service("egovOnlinePollManageService")
public class EgovOnlinePollManageServiceImpl extends EgovAbstractServiceImpl
        implements EgovOnlinePollManageService {


    @Resource(name = "onlinePollManageDao")
    private OnlinePollManageDao dao;

    @Resource(name = "egovOnlinePollManageIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "egovOnlinePollItemIdGnrService")
    private EgovIdGnrService idgenOnlinePollItemService;

    /**
     * 온라인POLL관리를(을) 목록을 조회 한다.
     * @param OnlinePoll 회정정보가 담김 VO
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
     * 온라인POLL관리를(을) 상세조회 한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Override
	public OnlinePollManage selectOnlinePollManageDetail( OnlinePollManage onlinePollManage) throws Exception {
        return dao.selectOnlinePollManageDetail(onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 등록한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    @Override
	public void insertOnlinePollManage(OnlinePollManage onlinePollManage)throws Exception {
        String sMakeId = idgenService.getNextStringId();
        onlinePollManage.setPollId(sMakeId);
        dao.insertOnlinePollManage(onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 수정한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    @Override
	public void updateOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception {
        dao.updateOnlinePollManage(onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 삭제한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    @Override
	public void deleteOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception {
        dao.deleteOnlinePollManage(onlinePollManage);
    }

    /**
     * 온라인POLL관리를(을) 통계를 조회 한다.
     * @param onlinePollManage 온라인POLL관리 정보가 담김 VO
     * @throws Exception
     */
    @Override
	public List<?> selectOnlinePollManageStatistics(OnlinePollManage onlinePollManage) throws Exception {
        return dao.selectOnlinePollManageStatistics(onlinePollManage);
    }

    /**
     * 온라인POLL항목를(을) 조회한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    @Override
	public List<?> selectOnlinePollItemList(OnlinePollItem onlinePollItem) throws Exception {
        return dao.selectOnlinePollItemList(onlinePollItem);
    }

    /**
     * 온라인POLL항목를(을) 등록한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    @Override
	public void insertOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception {
        String sMakeId = idgenOnlinePollItemService.getNextStringId();
        onlinePollItem.setPollIemId(sMakeId);
        dao.insertOnlinePollItem(onlinePollItem);
    }

    /**
     * 온라인POLL항목를(을) 수정한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    @Override
	public void updateOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception {
        dao.updateOnlinePollItem(onlinePollItem);
    }

    /**
     * 온라인POLL항목를(을) 삭제한다.
     * @param onlinePollItem  온라인POLL항목 정보가 담김 VO
     * @throws Exception
     */
    @Override
	public void deleteOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception {
        dao.deleteOnlinePollItem(onlinePollItem);
    }
}
