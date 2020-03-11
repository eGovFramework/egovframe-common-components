package egovframework.com.uss.olp.opm.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 온라인POLL관리를 처리하는 Service Class 구현
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
 *
 * </pre>
 */
public interface EgovOnlinePollManageService {


    /**
    * 온라인POLL관리 목록을 조회한다.
    * @param searchVO  조회할 정보가 담긴 VO
    * @return List
    * @throws Exception
    */
    public List<?> selectOnlinePollManageList(ComDefaultVO searchVO) throws Exception;

    /**
    * 온라인POLL관리를(을) 상세조회 한다.
    * @param onlinePollManage 온라인POLL관리 정보 담김 VO
    * @return List
    * @throws Exception
    */
    public OnlinePollManage selectOnlinePollManageDetail(OnlinePollManage onlinePollManage) throws Exception;

    /**
    * 온라인POLL관리를(을) 목록 전체 건수를(을) 조회한다.
    * @param searchVO 조회할 정보가 담긴 VO
    * @return int
    * @throws Exception
    */
    public int selectOnlinePollManageListCnt(ComDefaultVO searchVO) throws Exception;

    /**
    * 온라인POLL관리를(을) 등록한다.
    * @param onlinePollManage 온라인POLL관리 정보 담김 VO
    * @throws Exception
    */
    void  insertOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception;

    /**
    * 온라인POLL관리를(을) 수정한다.
    * @param onlinePollManage 온라인POLL관리 정보 담김 VO
    * @throws Exception
    */
    void  updateOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception;

    /**
    * 온라인POLL관리를(을) 삭제한다.
    * @param onlinePollManage 온라인POLL관리 정보 담김 VO
    * @throws Exception
    */
    void  deleteOnlinePollManage(OnlinePollManage onlinePollManage) throws Exception;

    /**
     * 온라인POLL관리를(을) 통계를 조회 한다.
     * @param onlinePollManage 온라인POLL관리 정보 담김 VO
     * @throws Exception
     */
    public List<?> selectOnlinePollManageStatistics(OnlinePollManage onlinePollManage) throws Exception;

    /**
    * 온라인POLL항목를(을) 조회한다.
    * @param onlinePollItem 온라인POLL항목 정보가 담김 VO
    * @throws Exception
    */
    public List<?> selectOnlinePollItemList(OnlinePollItem onlinePollItem) throws Exception;

    /**
    * 온라인POLL항목를(을) 등록한다.
    * @param onlinePollItem 온라인POLL항목 정보가 담김 VO
    * @throws Exception
    */
    public void insertOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception;

    /**
    * 온라인POLL항목를(을) 수정한다.
    * @param onlinePollItem 온라인POLL항목 정보가 담김 VO
    * @throws Exception
    */
    public void updateOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception;


    /**
    * 온라인POLL항목를(을) 삭제한다.
    * @param onlinePollItem 온라인POLL항목 정보가 담김 VO
    * @throws Exception
    */
    public void deleteOnlinePollItem(OnlinePollItem onlinePollItem) throws Exception;
}
