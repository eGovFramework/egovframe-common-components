package egovframework.com.uss.olh.omm.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;

public interface EgovOnlineManualService {

	List<OnlineManualVO> selectOnlineManualList(OnlineManualVO searchVO);

	int selectOnlineManualListCnt(OnlineManualVO searchVO);

	OnlineManualVO selectOnlineManualDetail(OnlineManualVO onlineManualVO) throws Exception;

	void insertOnlineManual(OnlineManualVO onlineManualVO) throws FdlException;

	void updateOnlineManual(OnlineManualVO onlineManualVO);

	void deleteOnlineManual(OnlineManualVO onlineManualVO);

}
